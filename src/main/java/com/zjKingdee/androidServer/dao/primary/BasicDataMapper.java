package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zjKingdee.androidServer.entity.BasicDataEntity;
import com.zjKingdee.androidServer.entity.QCJCXEntity;

/**
 * 基础资料
 * @author Yinqi				
 * @date 2020年3月24日
 */
public interface BasicDataMapper {
	/**
	 * 获取所有职员信息
	 */
	@Select("select FItemID id,FName text from t_Emp")
	List<BasicDataEntity> searchAllEmpInfo();
	
	/**
	 * 获取所有用户信息
	 */
	@Select("select FUserID id,FName text from t_user")
	List<BasicDataEntity> searchAllUserInfo();
	
	/**
	 * 获取所有部门信息
	 * @return
	 */
	@Select("select FItemID id,FName text from t_Department")
	List<BasicDataEntity> searchAllDeptInfo();
	
	/**
	 * 获取所有客户信息
	 * @return
	 */
	@Select("select FItemID id,FName text from t_Organization")
	List<BasicDataEntity> searchAllCustomerInfo();
	
	/**
	 * 获取所有供应商信息
	 * @return
	 */
	@Select("select FItemID id,FName text from t_Supplier")
	List<BasicDataEntity> searchAllSupplierInfo();
	
	/**
	 * 获取所有仓库信息
	 * @return
	 */
	@Select("select FItemID id,FName text from t_Stock")
	List<BasicDataEntity> searchAllStockInfo();
	/**
	 * 获取仓位信息
	 * @return
	 */
	@Select("select FSPID id,FName text from t_StockPlace")
	List<BasicDataEntity> searchAllStockPlaceInfo();
	
	/**
	 * 获取仓位信息
	 * @return
	 */
	@Select("select FSPID id,FName text from t_StockPlace where FNumber=#{number}")
	BasicDataEntity searchStockPlaceInfo(String number);

	/**
	 * 获取仓位id
	 * @param number
	 * @return
	 */
	@Select("select FSPID from t_StockPlace where FNumber=#{number}")
	String searchStockPlaceID(String number);
	
	/**
	 * 更新即时库存
	 */
	@Update("exec CheckInventory")
	void updateInventory();
	
	/**
	 * 获取物料对应检测项
	 * @param mateNumber
	 * @return
	 */
	@Select("select i1.FEntryID qcEntry,i3.FNumber qcNumber,i3.FName qcName from ICQCSchemeEntry i1 left join ICQCScheme i2 on i2.FInterID=i1.FInterID " + 
			"left join QMBGroupInfo i3 on i3.FID=i1.FQCItemID "+
			"left join t_ICItem i4 on i4.FInspectionProject=i1.FInterID where i4.FNumber=#{mateNumber}")
	List<QCJCXEntity> searchQCJCXForFA(String mateNumber);
}
