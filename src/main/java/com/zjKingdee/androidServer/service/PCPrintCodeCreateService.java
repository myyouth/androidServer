package com.zjKingdee.androidServer.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.WriterException;
import com.zjKingdee.androidServer.dao.primary.BarcodeNumberMapper;
import com.zjKingdee.androidServer.dao.primary.DocumentInfoMapper;
import com.zjKingdee.androidServer.entity.BarcodeInfoEntity;
import com.zjKingdee.androidServer.entity.DocumentInfoEntity;
import com.zjKingdee.androidServer.entity.MaterialInfoEntity;
import com.zjKingdee.androidServer.utils.BarcodeUtil;
import com.zjKingdee.androidServer.utils.PrintUtil;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfPrinter;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.LcmPdfUtil;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfImage;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfTemplate;
import com.zjKingdee.androidServer.utils.PDF.pdfprint.printer.element.LcmPdfText;
@Service
public class PCPrintCodeCreateService {
	@Autowired
	private DocumentInfoMapper mapper;
	@Autowired
	private BarcodeNumberMapper barMapper;
	@Autowired
	private PDABarcodeNumberService service;
	public Map<String,Object> getAllDocInfo(String typeDoc,JSONObject paramObj){
		Map<String,Object> map=new HashMap<String,Object>();
		List<DocumentInfoEntity> list=new ArrayList<DocumentInfoEntity>();
		String pageSize=paramObj.getString("pageSize");
		Integer pageIndex=(Integer.valueOf(paramObj.getString("pageIndex"))-1)*10;
		String paramValue=paramObj.getString("param").equals("number")?"%"+paramObj.getString("value")+"%":"%%";
		String paramValue2=paramObj.getString("param").equals("name")?"%"+paramObj.getString("value")+"%":"%%";
		String stockID="";
		if(typeDoc.indexOf("&&&")>0) {
			stockID=typeDoc.split("&&&")[1];
			typeDoc=typeDoc.split("&&&")[0];
		}
		if("SCRWD".equals(typeDoc)) {
			list=mapper.getAllDocInfo(String.valueOf(pageIndex),pageSize,paramValue,"ICMO",false,"");
			String pageTatal=mapper.getDocCount("ICMO",paramValue,false,"");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("SLTZD".equals(typeDoc)) {
			list=mapper.getAllDocInfo(String.valueOf(pageIndex),pageSize,paramValue,"POInStock",false,"");
			String pageTatal=mapper.getDocCount("POInStock",paramValue,false,"");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("QTRKD".equals(typeDoc)) {
			list=mapper.getAllDocInfo(String.valueOf(pageIndex),pageSize,paramValue,"ICStockBill",true,"10");
			String pageTatal=mapper.getDocCount("ICStockBill",paramValue,true,"10");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("SCHBD".equals(typeDoc)) {
			list=mapper.getAllDocInfo(String.valueOf(pageIndex),pageSize,paramValue,"ICMORpt",true,"551");
			String pageTatal=mapper.getDocCount("ICMORpt",paramValue,true,"551");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("DBD".equals(typeDoc)) {
			list=mapper.getAllDocInfo(String.valueOf(pageIndex),pageSize,paramValue,"ICStockBill",true,"41");
			String pageTatal=mapper.getDocCount("ICStockBill",paramValue,true,"41");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("CK".equals(typeDoc)) {
			list=mapper.getAllStockInfo(String.valueOf(pageIndex),pageSize,paramValue,paramValue2,"t_Stock");
			String pageTatal=mapper.getStockCount(paramValue,paramValue2,"t_Stock");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("CW".equals(typeDoc)) {
			list=mapper.getAllStockPlaceInfo(String.valueOf(pageIndex),pageSize,paramValue,paramValue2,"t_StockPlace",stockID);
			Iterator<DocumentInfoEntity> it = list.iterator();
	        while(it.hasNext()){
	        	DocumentInfoEntity entity = it.next();
	            if("*".equals(entity.getmNumber())){
	                it.remove();
	            }
	        }
			String pageTatal=mapper.getStockPlaceCount(paramValue,paramValue2,"t_StockPlace",stockID);
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("GYS".equals(typeDoc)) {
			list=mapper.getAllSupplyInfo(String.valueOf(pageIndex),pageSize,paramValue,paramValue2,"t_Supplier");
			String pageTatal=mapper.getStockCount(paramValue,paramValue2,"t_Supplier");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("WL".equals(typeDoc)) {
			list=mapper.getAllMateInfo(String.valueOf(pageIndex),pageSize,paramValue,paramValue2,"t_icitem");
			String pageTatal=mapper.getMateCount(paramValue,paramValue2,"t_icitem");
			map.put("data",list);
			map.put("pageTotal",pageTatal);
		}else if("TMLB".equals(typeDoc)) {
			List<BarcodeInfoEntity> list1=new ArrayList<BarcodeInfoEntity>();
			list1=barMapper.searchBarcodeListInfo(String.valueOf(pageIndex),pageSize,paramValue);
			String pageTatal=barMapper.getMailFileCount(paramValue);
			map.put("data",list1);
			map.put("pageTotal",pageTatal);
		}
		return map; 
	}
	
	public List<MaterialInfoEntity> getAllMaterialInfo(String typeDoc,String mNumber){
		List<MaterialInfoEntity> list=new ArrayList<MaterialInfoEntity>();
		if("SLTZD".equals(typeDoc)) {
			list=mapper.getAllSLTZDMaterialInfo(mNumber);
			for(MaterialInfoEntity entity:list) {
				entity.setmSum(new BigDecimal(entity.getmSum()).stripTrailingZeros().toPlainString());
				entity.setType("收料通知单");
				entity.setTypeNumber(mNumber);
				entity.setMinSum("1");
				String position=entity.getPosition();
				position=position.equals("*")?"":position;
				entity.setPosition(position);
				String supply=entity.getSupplier();
				supply=supply.equals("*")?"":supply;
				entity.setSupplier(supply);
			}
		}else if("SCRWD".equals(typeDoc)) {
			list=mapper.getAllSCRWDMaterialInfo(mNumber);
			for(MaterialInfoEntity entity:list) {
				entity.setmSum(new BigDecimal(entity.getmSum()).stripTrailingZeros().toPlainString());
				entity.setType("生产任务单");
				entity.setTypeNumber(mNumber);
				entity.setMinSum("1");
				String supply=entity.getSupplier();
				supply=supply==null||supply.equals("*")?"":supply;
				entity.setSupplier(supply);
			}
		}else if("SCHBD".equals(typeDoc)) {
			list=mapper.getAllSCHBDMaterialInfo(mNumber);
			for(MaterialInfoEntity entity:list) {
				entity.setmSum(new BigDecimal(entity.getmSum()).stripTrailingZeros().toPlainString());
				entity.setType("生产汇报单");
				entity.setTypeNumber(mNumber);
				entity.setMinSum("1");
				String supply=entity.getSupplier();
				supply=supply==null||supply.equals("*")?"":supply;
				entity.setSupplier(supply);
			}
		}else if("QTRKD".equals(typeDoc)) {
			list=mapper.getAllQTRKDMaterialInfo(mNumber,"10");
			for(MaterialInfoEntity entity:list) {
				entity.setmSum(new BigDecimal(entity.getmSum()).stripTrailingZeros().toPlainString());
				entity.setType("其他入库单");
				entity.setTypeNumber(mNumber);
				entity.setMinSum("1");
				String position=entity.getPosition();
				position=position.equals("*")?"":position;
				entity.setPosition(position);
				String supply=entity.getSupplier();
				supply=supply.equals("*")?"":supply;
				entity.setSupplier(supply);
			}
		}else if("DBD".equals(typeDoc)) {
			list=mapper.getAllQTRKDMaterialInfo(mNumber,"41");
			for(MaterialInfoEntity entity:list) {
				entity.setmSum(new BigDecimal(entity.getmSum()).stripTrailingZeros().toPlainString());
				entity.setType("调拨单");
				entity.setTypeNumber(mNumber);
				entity.setMinSum("1");
				String position=entity.getPosition();
				position=position.equals("*")?"":position;
				entity.setPosition(position);
				String supply=entity.getSupplier();
				supply=supply==null||supply.equals("*")?"":supply;
				entity.setSupplier(supply);
			}
		}else if("WL".equals(typeDoc)) {
			List<DocumentInfoEntity> entityList=JSONArray.parseArray(mNumber, DocumentInfoEntity.class);
			list=mapper.getAllMaterialInfo(entityList);
			for(MaterialInfoEntity entity:list) {
				entity.setMinSum("1");
				entity.setmSum("1");
				entity.setBatch("");
				entity.setPosition("");
				entity.setPositionId("");
				entity.setSupplier("");
				entity.setSupplierId("");
				entity.setWarehouse("");
				entity.setWarehouseId("");
			}
		}
		return list;
	}
	
	/**
	 * 创建条码
	 * @param list
	 * @return
	 */
	public Map<String,Object> createAllBarcode(List<MaterialInfoEntity> list){
		Map<String,Object> map=new HashMap<String,Object>();
		List<BarcodeInfoEntity> data=new ArrayList<BarcodeInfoEntity>();
		int pageTatal=0;
		int size=1;
		for(int j=0;j<list.size();j++) {
			MaterialInfoEntity entity=list.get(j);
			String zbm=entity.getZBM();
			if(zbm==null)
				zbm="";
			String[] zbms=zbm.split("-");
			int lSum=Integer.valueOf(entity.getlSum());
			int mSum=Integer.valueOf(entity.getmSum());
			int minSum=Integer.valueOf(entity.getMinSum());
			pageTatal+=lSum;
			for(int i=1;i<=lSum;i++) {
				BarcodeInfoEntity info=new BarcodeInfoEntity();
				if(i*minSum>mSum) {
					info.setMaterialQty(String.valueOf(mSum-((i-1)*minSum)));
				}else {
					info.setMaterialQty(entity.getMinSum());
				}
				info.setId(String.valueOf(size));
				info.setmId(entity.getmId());
				info.setMaterielNumber(entity.getmNumber());
				info.setMaterialName(entity.getmName());
				info.setBarcodNumber(barMapper.getBarcodeNumber("ZJFC"));
				info.setMaterialUnitID(entity.getUnitId());
				info.setMaterialUnitName(entity.getUnitName());
				info.setTypeName(entity.getType());
				info.setTypeNumber(entity.getTypeNumber());
				info.setMaterialBatch(entity.getBatch());
				info.setSupplyID(entity.getSupplierId());
				info.setSupplyName(entity.getSupplier());
				info.setStockID(entity.getWarehouseId());
				info.setStockName(entity.getWarehouse());
				info.setStockPlaceID(entity.getPositionId());
				info.setStockPlaceName(entity.getPosition());
				info.setDepartmentID(entity.getWorkShopId());
				info.setDepartmentName(entity.getWorkShop());
				if(zbm.length()>0) {
					info.setZbm(String.valueOf(Integer.valueOf(zbms[0])+i-1));
				}
				size+=1;
				data.add(info);
			}
		}
		barMapper.insertBarcodeNumber(data,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),"2");
		map.put("data",data);
		map.put("pageTotal",pageTatal);
		return map;
	}
	
	/**
	 * 条码创建校验
	 * @return 
	 */
	public boolean checkBarcodeInfo(List<BarcodeInfoEntity> data) {
		String ZBMs="";
		for(BarcodeInfoEntity entity:data) {
			
			int num=barMapper.checkBarcodeInfo(ZBMs);
		}
		return true;
	}
	
	/**
	 * 获取打印机列表
	 * @throws MalformedURLException 
	 * @throws RemoteException 
	 */
	public JSONArray getPrintMachineList() {
		List<String> machines=PrintUtil.getAllPrint();
		JSONArray array=new JSONArray();
		for(String machine:machines) {
			JSONObject obj=new JSONObject();
			obj.put("name", machine);
			array.add(obj);
		}
		return array;
	}
	
	/**
	 * 打印条码
	 * @param printMachine
	 * @param labelName
	 * @param codes
	 * @return
	 * @throws Exception 
	 */
	public String printBarcode(String printMachine,String labelName,JSONArray codes) throws Exception{
//		BarcodeServerLocator locator=new BarcodeServerLocator();
//		BarcodeServerSoapStub stub=new BarcodeServerSoapStub(new URL(env.getProperty("printMachineUrl")),locator);
//		System.out.println(codes.toJSONString()+":"+labelName+":"+printMachine);
//		String log=stub.print(codes.toJSONString(), printMachine, Integer.valueOf(labelName));
		for(int i=0;i<codes.size();i++) {
			JSONObject obj=JSONObject.parseObject((JSONArray.parseArray(service.searchMaterial((String)codes.get(i), 2,"收料通知单")).get(0)).toString());
			LcmPdfTemplate template = new LcmPdfTemplate(System.getProperty("user.dir")+"\\src\\main\\java\\com\\zjKingdee\\androidServer\\utils\\PDF\\barcode.pdf");
			template.add(new LcmPdfText("产品编码", obj.getString("mNumber")));
			template.add(new LcmPdfText("产品名称", obj.getString("mName")));
			template.add(new LcmPdfText("自编码", obj.getString("zBM")));
			template.add(new LcmPdfText("数量", obj.getString("mSum")+obj.getString("unitName")));
			File barcodeFile=BarcodeUtil.generateQRCodeImage(obj.getString("barcodeNumber"), 800, 800);
			template.add(new LcmPdfImage("物料条码", "MyQRCode.png"));
			barcodeFile=BarcodeUtil.generateQRCodeImage(obj.getString("typeNumber"), 500, 500);
			template.add(new LcmPdfImage("源单编码", "MyQRCode.png"));
			LcmPdfPrinter printer = new LcmPdfPrinter(LcmPdfUtil.SimSuncss,5.0f);
			printer.addTempl(template);
			File pdfFile=printer.print(System.getProperty("user.dir")+"\\barcode.pdf");
			PrintUtil.JPGPrint(pdfFile,printMachine);
			if(pdfFile.exists()) {
				pdfFile.delete();
			}
			if(barcodeFile.exists()) {
				barcodeFile.delete();
			}
		}
		return "打印成功";
	}
}
