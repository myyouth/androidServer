package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.zjKingdee.androidServer.entity.BadDescriptionEntity;
import com.zjKingdee.androidServer.entity.ProcessBookingEntity;
import com.zjKingdee.androidServer.entity.ProcessBookingEntryEntity;

/**
 * 生产报工
 * @author Yinqi				
 * @date 2020年1月3日
 */
public interface JobWorkingMapper {
	/**
	 * 获取所有不良描述
	 * @return
	 */
	@Select("select FID,FName from t_BOS200000004")
	@Results({
		@Result(property = "BadID",  column = "FID"),
		@Result(property = "text", column = "FName")
	})
	List<BadDescriptionEntity> getAllBadDes();
	/**
	 * 通过编号查询工序卡数据
	 * @param number
	 * @return
	 */
	@Select("select t1.FBillNo,t1.FText1,t2.FIndex,t3.FName,t2.FDecimal1,t2.FInteger9,t4.FShortNumber from t_BOSGXLZK t1 "
			+ "left join t_BOSGXLZKEntry t2 on t1.Fid=t2.FID "
			+ "left join t_BOSXGX t3 on t2.FBase2=t3.FID "
			+ "left join t_ICItem t4 on t1.FBase=t4.FItemID "
			+ "where FBillNo=#{number} order by t2.FIndex")
	@Results({
		@Result(property = "transferCardNumber",  column = "FBillNo"),
		@Result(property = "orderNumber", column = "FText1"),
		@Result(property = "processID",  column = "FIndex"),
		@Result(property = "processNumber", column = "FName"),
		@Result(property = "unitTime", column = "FDecimal1"),
		@Result(property = "inputNumber", column = "FInteger9"),
		@Result(property = "materialNumber", column = "FShortNumber")
	})
	List<ProcessBookingEntity> getAllProcessBookByNumber(String number);
	
	@Select("select FBillNo from t_BOSGXLZK where FText3=#{number}")
	String getNumberByBatNo(String number);
	/**
	 * 获取工序报工明细
	 * @param entity
	 * @return
	 */
	@Select("select FBase3,FInteger,FInteger6,FDecimal2,FBase5,FInteger1,FInteger2,FDecimal3,FBase6,"
			+ "FInteger3,FInteger4,FDecimal4,FBase4,FInteger7,FBase8,FInteger8 "
			+ "from t_BOSGXLZKEntry where Fid= (select FID from t_BOSGXLZK where FBillNo=#{transferCardNumber}) and FIndex=#{processID}")
	@Results({
		@Result(property = "userID1",  column = "FBase3"),
		@Result(property = "inputNumber1", column = "FInteger"),
		@Result(property = "badNumber1",  column = "FInteger6"),
		@Result(property = "taskTime1", column = "FDecimal2"),
		@Result(property = "userID2",  column = "FBase5"),
		@Result(property = "inputNumber2", column = "FInteger1"),
		@Result(property = "badNumber2",  column = "FInteger2"),
		@Result(property = "taskTime2", column = "FDecimal3"),
		@Result(property = "userID3",  column = "FBase6"),
		@Result(property = "inputNumber3", column = "FInteger3"),
		@Result(property = "badNumber3",  column = "FInteger4"),
		@Result(property = "taskTime3", column = "FDecimal4"),
		@Result(property = "badID1",  column = "FBase4"),
		@Result(property = "ng1", column = "FInteger7"),
		@Result(property = "badID2",  column = "FBase8"),
		@Result(property = "ng2", column = "FInteger8")
	})
	ProcessBookingEntryEntity selectProcessBookingByNumAndProcess(ProcessBookingEntity entity);
	/**
	 * 获取工序合计产出数
	 * @param transferCardNumber
	 * @param processID
	 * @return
	 */
	@Select("select FInteger10 from t_BOSGXLZKEntry where Fid=("
			+ "select FID from t_BOSGXLZK where FBillNo=#{transferCardNumber}) and FIndex=#{processID}")
	String selectTotalOutputNumber(@Param("transferCardNumber")String transferCardNumber,@Param("processID")String processID);
	/**
	 * 获取工序合计投入数
	 * @param transferCardNumber
	 * @param processID
	 * @return
	 */
	@Select("select FInteger9 from t_BOSGXLZKEntry where Fid=("
			+ "select FID from t_BOSGXLZK where FBillNo=#{transferCardNumber}) and FIndex=#{processID}")
	String selectTotalInputNumber(@Param("transferCardNumber")String transferCardNumber,@Param("processID")String processID);
	/**
	 * 获取工序流转卡id
	 * @param transferCardNumber
	 * @return
	 */
	@Select("select FID from t_BOSGXLZK where FBillNo=#{transferCardNumber}")
	String selectProcessBookID(String transferCardNumber);
	/**
	 * 获取工序流转卡投入数
	 * @param transferCardNumber
	 * @return
	 */
	@Select("select FQty from t_BOSGXLZK where FBillNo=#{transferCardNumber}")
	String selectProcessBookQty(String transferCardNumber);
	/**
	 * 更新工序报工明细
	 * @param entity
	 */
	@Update("update t_BOSGXLZKEntry set FInteger9=#{totalInputNumber},FInteger10=#{totalOutputNumber},FDecimal=#{qualificateRate}," + 
			"FBase3=#{userID1},FInteger=#{inputNumber1},FInteger6=#{badNumber1},FDecimal2=#{taskTime1}," + 
			"FBase5=#{userID2},FInteger1=#{inputNumber2},FInteger2=#{badNumber2},FDecimal3=#{taskTime2}," + 
			"FBase6=#{userID3},FInteger3=#{inputNumber3},FInteger4=#{badNumber3},FDecimal4=#{taskTime3}," + 
			"FBase4=#{badID1},FInteger7=#{ng1},FBase8=#{badID2},FInteger8=#{ng2}," + 
			"FDecimal6=#{totalTaskTime},FComboBox=1,FInteger5=1,FDate2=#{dateTime} where FID=#{transferCardID} and FIndex=#{processID}")
	void updateProcessBooking(ProcessBookingEntryEntity entity);
	/**
	 * 更新工序合计时长
	 */
	@Update("update t_BOSGXLZK set FDecimal5=("
			+ "select SUM(FDecimal6) from t_BOSGXLZKEntry where FID=("
			+ "select FID from t_BOSGXLZK where FBillNo=#{transferCardNumber})) where FBillNo=#{transferCardNumber}")
	void updateProcessBookingTotalTask(String transferCardNumber);
	/**
	 * 更新工序开始时间
	 */
	@Update("update t_BOSGXLZKEntry set FDate1=#{dateTime} where FID=#{transferCardID} and FIndex=#{processID}")
	void updateBeginDateTime(@Param("dateTime")String dateTime,@Param("transferCardID")String transferCardID,@Param("processID")String processID);
	/**
	 * 新增报工信息
	 * @param entity
	 */
	@Insert("insert into JobWorkingLog values (#{transferCardNumber},#{orderNumber},#{processID},#{inputNumber},#{outputNumber},#{taskTime},#{badNumber},#{badID},#{userID})")
	void insertLog(ProcessBookingEntity entity);
	/**
	 * 查询报工信息
	 * @param transferCardNumber
	 * @param processID
	 * @param userID
	 * @return
	 */
	@Select("select Id from JobWorkingLog where TransferCardNumber=#{transferCardNumber} and ProcessID=#{processID} and UserID=#{userID}")
	String selectLog(@Param("transferCardNumber")String transferCardNumber,@Param("processID")String processID,@Param("userID")String userID);
	/**
	 *  查询报工信息
	 * @param transferCardNumber
	 * @param processID
	 * @return
	 */
	@Select("select TransferCardNumber,OrderNumber,ProcessID,InputNumber,OutputNumber,TaskTime,BadNumber,BadID,UserID"
			+ " from JobWorkingLog where TransferCardNumber=#{transferCardNumber} "
			+ "and ProcessID=#{processID}")
	@Results({
		@Result(property = "transferCardNumber",  column = "TransferCardNumber"),
		@Result(property = "orderNumber", column = "OrderNumber"),
		@Result(property = "processID",  column = "ProcessID"),
		@Result(property = "inputNumber", column = "InputNumber"),
		@Result(property = "outputNumber",  column = "OutputNumber"),
		@Result(property = "taskTime",  column = "TaskTime"),
		@Result(property = "badNumber", column = "BadNumber"),
		@Result(property = "badID",  column = "BadID"),
		@Result(property = "userID", column = "UserID")
	})
	List<ProcessBookingEntity> selectLogForProcessID(@Param("transferCardNumber")String transferCardNumber,@Param("processID")String processID);
	/**
	 * 更新报工信息
	 * @param entity
	 * @param id
	 */
	@Update("update JobWorkingLog set InputNumber=#{entity.inputNumber} ,OutputNumber=#{entity.outputNumber},TaskTime=#{entity.taskTime},BadNumber=#{entity.badNumber},BadID=#{entity.badID} where Id=#{id}")
	void updateLog(@Param("entity")ProcessBookingEntity entity,@Param("id")String id);
}
