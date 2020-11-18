package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zjKingdee.androidServer.entity.UseInfoEntity;

public interface UserInfoMapper {
	
	@Select("select top ${size} user_id userId,user_name userName,user_number userNumber,user_password userPassword,create_time userTime,roleName roleName,rowNumber "+
			"from (select row_number() over(order by t1.user_number) as rowNumber,*,roleName=(STUFF((select ','+t3.role_name from t_user_role t2 left join t_role t3 on t3.role_id=t2.role_id where t2.user_id=t1.user_id for xml path('')),1,1,'')) " + 
			"from t_user_info t1 where user_name like #{userName} and user_number like #{userNumber})A where rowNumber>#{index}")
	List<UseInfoEntity> searchAllUserInfo(@Param("index")String index,@Param("size")String size,@Param("userName")String userName,@Param("userNumber")String userNumber);
	
	@Select("select count(*) from t_user_info where user_name like #{userName} and user_number like #{userNumber}")
	int searchUserSum(@Param("userName")String userName,@Param("userNumber")String userNumber);
	
	@Select("select count(*) from t_user_info where user_id=#{userId}")
	int searchUseInfo(String userId);
	
	@Insert("insert into t_user_info values(#{entity.userId},(select FName from t_user where FUserID=#{entity.userId}),#{entity.userId},"
			+ "#{entity.userPassword},#{entity.userTime})")
	int createUserInfo(@Param("entity")UseInfoEntity entity); 
	
	@Delete({"<script>",
			"<foreach collection='data' item='item' index='index' separator=';'>",
			"delete from t_user_info where user_id=#{item.userId}",
			"delete from t_user_role where user_id=#{item.userId}",
			"</foreach>",
		 	"</script>"})
	void delectUserInfo(@Param("data")List<UseInfoEntity> entitys);
	
	@Update("update t_user_info set user_password=#{entity.userPassword} where user_id=#{entity.userId}")
	void reviseUseInfo(@Param("entity")UseInfoEntity entity);
}
