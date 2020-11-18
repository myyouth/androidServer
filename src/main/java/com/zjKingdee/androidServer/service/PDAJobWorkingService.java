package com.zjKingdee.androidServer.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjKingdee.androidServer.dao.primary.JobWorkingMapper;
import com.zjKingdee.androidServer.entity.BadDescriptionEntity;
import com.zjKingdee.androidServer.entity.ProcessBookingEntity;
import com.zjKingdee.androidServer.entity.ProcessBookingEntryEntity;

@Service
public class PDAJobWorkingService {
	private static final Logger LOG = LoggerFactory.getLogger(PDAJobWorkingService.class);
	@Autowired
	private JobWorkingMapper jbMapper;
	/**
	 * 获取所有不良描述
	 */
	public JSONArray selectAllBadDes() {
		List<BadDescriptionEntity> entitys=jbMapper.getAllBadDes();
		JSONArray json=JSONArray.parseArray(JSON.toJSONString(entitys));
		return json;
	}
	
	/**
	 * 获取工序卡信息
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	public JSONObject selectAllProcessBook(String number) throws Exception {
		number=jbMapper.getNumberByBatNo(number)==null?"":jbMapper.getNumberByBatNo(number);
		List<ProcessBookingEntity> entitys=jbMapper.getAllProcessBookByNumber(number);
		String qty=jbMapper.selectProcessBookQty(number);
		JSONObject obj=new JSONObject();
        String orderNumber="";
        String materialNumber="";
        for(int i=1;i<=entitys.size();i++) {
        	ProcessBookingEntity entity=entitys.get(i-1);
        	String inputNumber=entity.getInputNumber();
        	if(i==1) {
        		if(new BigDecimal(qty).subtract(new BigDecimal(inputNumber)).compareTo(BigDecimal.ZERO)==1) {
        			orderNumber=entity.getOrderNumber();
        			materialNumber=entity.getMaterialNumber();
                	obj.put("processID", entity.getProcessID());
                	obj.put("text", entity.getProcessNumber());
                	obj.put("unitTime",entity.getUnitTime());
        			break;
        		}
        	}else {
        		String outputNumber=jbMapper.selectTotalOutputNumber(number, i-1+"");
        		if(new BigDecimal(outputNumber).subtract(new BigDecimal(inputNumber)).compareTo(BigDecimal.ZERO)==1) {
        			orderNumber=entity.getOrderNumber();
        			materialNumber=entity.getMaterialNumber();
                	obj.put("processID", entity.getProcessID());
                	obj.put("text", entity.getProcessNumber());
                	obj.put("unitTime",entity.getUnitTime());
        			break;
        		}
        	}
        }
        JSONObject json=new JSONObject();
        json.put("orderNumber",orderNumber);
        json.put("transferCardNumber",number);
        json.put("materialNumber",materialNumber);
        json.put("processes", obj);
        JSONObject parseObj=parseProcessBadDesExcel();
        json.put("hourlyWage",new BigDecimal((Double)parseObj.get("hourlyWage")));
        Map map=(Map)parseObj.get("info");
        List<String> list=(List<String>)map.get(obj.get("text"));
        List<BadDescriptionEntity> badDecs=jbMapper.getAllBadDes();
        List<BadDescriptionEntity> badDecsForProcess=new ArrayList<BadDescriptionEntity>();
        if(list!=null&&list.size()!=0) {
        	for(BadDescriptionEntity entity:badDecs) {
            	if(list.contains(entity.getText())) {
            		badDecsForProcess.add(entity);
            	}
            }
        }
        json.put("badDec",JSONArray.parseArray(JSON.toJSONString(badDecsForProcess)));
		return json;
	}
	/**
	 * 报工
	 * @throws Exception 
	 */
	public void updateProcessBook(String submitData) throws Exception {
		ProcessBookingEntity entity=(ProcessBookingEntity) JSONObject.toJavaObject(JSONObject.parseObject(submitData), ProcessBookingEntity.class);
		ProcessBookingEntryEntity entryEntity = jbMapper.selectProcessBookingByNumAndProcess(entity);
		checkProcessBook(entity, entryEntity);
		entryEntity=dealProcessBook(entity, entryEntity);
		entryEntity.setDateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		jbMapper.updateProcessBooking(entryEntity);
		jbMapper.updateProcessBookingTotalTask(entity.getTransferCardNumber());
		jbMapper.updateBeginDateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), entryEntity.getTransferCardID(), Integer.valueOf(entryEntity.getProcessID())+1+"");
	}
	/**
	 * 报工前进行校验
	 * @param entity
	 * @throws Exception 
	 */
	public void checkProcessBook(ProcessBookingEntity entity,ProcessBookingEntryEntity entryEntity) throws Exception {
		if(entity.getUserID().equals(entryEntity.getUserID1())||entity.getUserID().equals(entryEntity.getUserID2())||entity.getUserID().equals(entryEntity.getUserID3())) {
			throw new RuntimeException("当前用户报工信息已提交,无法重复报工");
		}
		if(!entryEntity.getUserID1().equals("0")&&!entryEntity.getUserID2().equals("0")&&!entryEntity.getUserID3().equals("0")) {
			throw new RuntimeException("当前工序报工已完成，无法再次报工");
		}
		if(entity.getBadID().length()>0&&!entryEntity.getBadID1().equals("0")&&!entryEntity.getBadID2().equals("0")&&!entryEntity.getBadID1().equals(entity.getBadID())&&!entryEntity.getBadID2().equals(entity.getBadID())) {
			throw new RuntimeException("当前工序报工,不良项目不符合条件");
		}
		String totalOutputNumber=jbMapper.selectTotalOutputNumber(entity.getTransferCardNumber(), String.valueOf(Integer.valueOf(entity.getProcessID())-1));
		String totalinputNumber=jbMapper.selectTotalInputNumber(entity.getTransferCardNumber(), entity.getProcessID());
		if(totalOutputNumber!=null&&totalOutputNumber.length()>0) {
			if(new BigDecimal(totalinputNumber).add(new BigDecimal(entity.getInputNumber())).subtract(new BigDecimal(totalOutputNumber)).compareTo(BigDecimal.ZERO)==1) {
				throw new RuntimeException("当前报工数量超出上一道工序总产出数量");
			}
		}else {
			String qty=jbMapper.selectProcessBookQty(entity.getTransferCardNumber());
			if(new BigDecimal(totalinputNumber).add(new BigDecimal(entity.getInputNumber())).subtract(new BigDecimal(qty)).compareTo(BigDecimal.ZERO)==1) {
				throw new RuntimeException("当前报工数量超出总投入数量");
			}
		}
	}
	/**
	 * 报工数据整理
	 * @param entity
	 * @param entryEntity
	 */
	public ProcessBookingEntryEntity dealProcessBook(ProcessBookingEntity entity,ProcessBookingEntryEntity entryEntity) {
		entryEntity.setTaskTime1(new BigDecimal(entryEntity.getTaskTime1()).toPlainString());
		entryEntity.setTaskTime2(new BigDecimal(entryEntity.getTaskTime2()).toPlainString());
		entryEntity.setTaskTime3(new BigDecimal(entryEntity.getTaskTime3()).toPlainString());
		if(entryEntity.getUserID1().equals("0")) {
			entryEntity.setUserID1(entity.getUserID());
			entryEntity.setInputNumber1(entity.getOutputNumber());
			entryEntity.setBadNumber1(entity.getBadNumber());
			entryEntity.setTaskTime1(entity.getTaskTime());
		}else if(entryEntity.getUserID2().equals("0")) {
			entryEntity.setUserID2(entity.getUserID());
			entryEntity.setUserID2(entity.getUserID());
			entryEntity.setInputNumber2(entity.getOutputNumber());
			entryEntity.setBadNumber2(entity.getBadNumber());
			entryEntity.setTaskTime2(entity.getTaskTime());
		}else if(entryEntity.getUserID3().equals("0")) {
			entryEntity.setUserID3(entity.getUserID());
			entryEntity.setUserID3(entity.getUserID());
			entryEntity.setInputNumber3(entity.getOutputNumber());
			entryEntity.setBadNumber3(entity.getBadNumber());
			entryEntity.setTaskTime3(entity.getTaskTime());
		}
		if(entity.getBadID().length()>0 && (entryEntity.getBadID1().equals("0")||entryEntity.getBadID1().equals(entity.getBadID()))) {
			entryEntity.setBadID1(entity.getBadID());
			entryEntity.setNg1(new BigDecimal(entity.getBadNumber()).add(new BigDecimal(entryEntity.getNg1())).stripTrailingZeros().toPlainString());
		}else if(entity.getBadID().length()>0 && (entryEntity.getBadID2().equals("0")||entryEntity.getBadID2().equals(entity.getBadID()))) {
			entryEntity.setBadID2(entity.getBadID());
			entryEntity.setNg2(new BigDecimal(entity.getBadNumber()).add(new BigDecimal(entryEntity.getNg2())).stripTrailingZeros().toPlainString());
		}
		BigDecimal taskTime1=new BigDecimal(entryEntity.getTaskTime1());
		BigDecimal taskTime2=new BigDecimal(entryEntity.getTaskTime2());
		BigDecimal taskTime3=new BigDecimal(entryEntity.getTaskTime3());
		entryEntity.setTotalTaskTime(taskTime1.add(taskTime2).add(taskTime3).toString());
		BigDecimal InputNumber1=new BigDecimal(entryEntity.getInputNumber1()).add(new BigDecimal(entryEntity.getBadNumber1()));
		BigDecimal InputNumber2=new BigDecimal(entryEntity.getInputNumber2()).add(new BigDecimal(entryEntity.getBadNumber2()));
		BigDecimal InputNumber3=new BigDecimal(entryEntity.getInputNumber3()).add(new BigDecimal(entryEntity.getBadNumber3()));
		entryEntity.setTotalInputNumber(InputNumber1.add(InputNumber2).add(InputNumber3).stripTrailingZeros().toPlainString());
		BigDecimal OutputNumber1=new BigDecimal(entryEntity.getInputNumber1());
		BigDecimal OutputNumber2=new BigDecimal(entryEntity.getInputNumber2());
		BigDecimal OutputNumber3=new BigDecimal(entryEntity.getInputNumber3());
		entryEntity.setTotalOutputNumber(OutputNumber1.add(OutputNumber2).add(OutputNumber3).stripTrailingZeros().toPlainString());
		BigDecimal totalInputNumber=new BigDecimal(entryEntity.getTotalInputNumber());
		BigDecimal totalOutputNumber=new BigDecimal(entryEntity.getTotalOutputNumber());
		entryEntity.setQualificateRate(totalOutputNumber.divide(totalInputNumber,4,BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal("100")).toString());
		entryEntity.setTransferCardID(jbMapper.selectProcessBookID(entity.getTransferCardNumber()));
		entryEntity.setProcessID(entity.getProcessID());
		return entryEntity;
	}
	
	/**
	 * 获取excel数据
	 * @throws Exception
	 */
	public JSONObject parseProcessBadDesExcel() throws Exception{
		InputStream input=new FileInputStream(System.getProperty("user.dir")+"\\JRBYConfig.xls");
		HSSFWorkbook work=new HSSFWorkbook(input);
		HSSFSheet sheet=work.getSheetAt(0);
		HSSFRow badDecRow=sheet.getRow(1);
		int cells=badDecRow.getPhysicalNumberOfCells();
		Map<Integer,String> map=new HashMap<Integer,String>();
		for(int i=2;i<cells;i++) {
			HSSFCell cell=badDecRow.getCell(i);
			String cellValue=cell.getStringCellValue();
			map.put(i, cellValue);
		}
		int rows=sheet.getPhysicalNumberOfRows();
		Map<String,List<String>> info=new HashMap<String, List<String>>();
		for(int i=2;i<rows;i++) {
			HSSFRow row=sheet.getRow(i);
			int processRows=row.getPhysicalNumberOfCells();
			List<String> list=new ArrayList<String>();
			for(int j=2;j<processRows;j++) {
				String value=row.getCell(j).getStringCellValue();
				if("Y".equals(value)) {
					list.add(map.get(j));
				}
			}
			info.put(row.getCell(1).getStringCellValue(), list);
		}
		Double hourlyWage=work.getSheetAt(1).getRow(0).getCell(1).getNumericCellValue();
		JSONObject obj=new JSONObject();
		obj.put("hourlyWage", hourlyWage);
		obj.put("info", info);
		return obj;
	}
}
