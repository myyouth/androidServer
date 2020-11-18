package com.zjKingdee.androidServer.dao.primary;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zjKingdee.androidServer.entity.UseInfoEntity;

public interface LoginMapper {
	@Select("select user_id userId,user_name userName,user_number userNumber,roleName=(STUFF((select ','+t3.role_name from t_user_role t2 left join t_role t3 on t3.role_id=t2.role_id where t2.user_id=t1.user_id for xml path('')),1,1,'')) " + 
			"from t_user_info t1 where t1.user_name = #{userName} and t1.user_password = #{password}")
	public UseInfoEntity login(@Param("userName")String userName,@Param("password")String password);
	
	@Select("select user_number from t_user_info where user_id=#{staffID}")
	public String selectForStaffNumber(String staffID);
	
	@Update("update t_user_info set user_password=#{password} where user_id=#{id}")
	public void revisePassword(@Param("id")String id,@Param("password")String password);
	
	@Select("select name=(STUFF((select ','+t3.power_name from t_role_power t2 left join t_power t3 on t3.power_id=t2.power_id "
			+ "where t1.role_id=t2.role_id for xml path('')),1,1,'')) from t_role t1 where t1.role_name=#{roleName}")
	public String searchPermission(String roleName);
}
