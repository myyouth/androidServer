package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zjKingdee.androidServer.entity.PermissionInfoEntity;
import com.zjKingdee.androidServer.entity.RoleInfoEntity;
import com.zjKingdee.androidServer.entity.UseInfoEntity;

public interface RoleInfoMapper {
	@Insert("insert into t_role (role_name,create_time) values (#{entity.roleName},#{entity.roleTime})")
	void createRoleInfo(@Param("entity")RoleInfoEntity entity);
	
	@Select("select count(*) from t_role where role_name=#{roleName}")
	int searchRoleInfo(String roleName);
	
	@Select("select top ${size} role_id roleId,role_name roleName,create_time roleTime,rowNumber "+
			"from (select row_number() over(order by role_id) as rowNumber,*  from t_role where role_name like #{roleName})A where rowNumber>#{index}")
	List<RoleInfoEntity> searchAllRoleInfo(@Param("index")String index,@Param("size")String size,@Param("roleName")String roleName);
	
	@Select("select count(*) from t_role where role_name like #{roleName}")
	int searchRoleSum(@Param("roleName")String roleName);
	
	@Delete({"<script>",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"delete from t_role_power where role_id=(select role_id from t_role where role_name=#{item.roleName})", 
		"delete from t_user_role where role_id=(select role_id from t_role where role_name=#{item.roleName})",
		"delete from t_role where role_name=#{item.roleName}",
		"</foreach>",
	 	"</script>"})
	void delectRoleInfo(@Param("data")List<RoleInfoEntity> entitys);
	
	@Select("select role_id roleId,role_name roleName,create_time roleTime from t_role")
	List<RoleInfoEntity> searchAllRoleInfoForUser();
    /**
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */
	@Select("select user_id userId,user_name userName,create_time userTime from t_user_info")
	List<UseInfoEntity> searchAllUserInfoForRole();
	
	@Insert({"<script>",
		"insert into t_user_role (user_id,role_id,create_time) values ",
		"<foreach collection='userIds' item='userId' index='index' separator=','>",
		"(#{userId},#{roleId},#{dateTime})",
		"</foreach>",
	 	"</script>"})
	void createUsersInRole(@Param("roleId")String roleId,@Param("dateTime")String dateTime,@Param("userIds")List<String> userIds);
	
	@Select("select t2.user_name from t_user_role t1 left join t_user_info t2 on t1.user_id=t2.user_id where t1.role_id=#{roleId} and t1.user_id=#{userId}")
	String searchUserInfoInRole(@Param("roleId")String roleId,@Param("userId")String userId);
	
	@Select("select count(*) from t_role where role_id=#{roleId}")
	int checkRole(@Param("roleId")String roleId);
	
	@Select("select top ${size} user_id userId,user_number userNumber,user_name userName,create_time userTime,rowNumber "+
			"from (select row_number() over(order by use_role_id) as rowNumber,t1.user_id,t2.user_name,t1.create_time,t2.user_number "+
			"from t_user_role t1 left join t_user_info t2 on t2.user_id=t1.user_id where t1.role_id = #{roleId} and t2.user_number like #{userNumber} and t2.user_name like #{userName})A where rowNumber>#{index}")
	List<UseInfoEntity> searchAllUsersInRole(@Param("index")String index,@Param("size")String size,@Param("roleId")String roleId,@Param("userName")String userName,@Param("userNumber")String userNumber);
	
	@Select("select count(*) from t_user_role t1 left join t_user_info t2 on t2.user_id=t1.user_id where t1.role_id = #{roleId} and t2.user_number like #{userNumber} and t2.user_name like #{userName}")
	int AllUsersInRoleSum(@Param("roleId")String roleId,@Param("userName")String userName,@Param("userNumber")String userNumber);
	
	@Delete({"<script>",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"delete from t_user_role where role_id=#{roleId} and user_id=#{item.userId}",
		"</foreach>",
	 	"</script>"})
	void delectUsersInfoInRole(@Param("data")List<UseInfoEntity> entitys,@Param("roleId")String roleId);
	/**
     * ---------------------------------------------------------------------------------------------------------------------------------------------------------
     */
	@Select("select power_id permissionId,power_name permissionName,create_time permissionTime from t_power")
	List<PermissionInfoEntity> searchAllPermissionInfoForRole();
	
	@Insert({"<script>",
		"insert into t_role_power (power_id,role_id,create_time) values ",
		"<foreach collection='permissionIds' item='permissionId' index='index' separator=','>",
		"(#{permissionId},#{roleId},#{dateTime})",
		"</foreach>",
	 	"</script>"})
	void createPermissionsInRole(@Param("roleId")String roleId,@Param("dateTime")String dateTime,@Param("permissionIds")List<String> permissionIds);
	
	@Select("select t2.power_name from t_role_power t1 left join t_power t2 on t1.power_id=t2.power_id where t1.role_id=#{roleId} and t1.power_id=#{permissionId}")
	String searchPermissionsInfoInRole(@Param("roleId")String roleId,@Param("permissionId")String permissionId);
	
	@Select("select top ${size} power_id permissionId,power_name permissionName,create_time permissionTime,rowNumber "+
			"from (select row_number() over(order by role_power_id) as rowNumber,t1.power_id,t2.power_name,t1.create_time "+
			"from t_role_power t1 left join t_power t2 on t2.power_id=t1.power_id where t1.role_id = #{roleId} and t2.power_name like #{powerName})A where rowNumber>#{index}")
	List<PermissionInfoEntity> searchAllPermissionsInRole(@Param("index")String index,@Param("size")String size,@Param("roleId")String roleId,@Param("powerName")String powerName);
	
	@Select("select count(*) from t_role_power t1 left join t_power t2 on t2.power_id=t1.power_id where t1.role_id = #{roleId} and t2.power_name like #{powerName}")
	int AllPermissionsInRoleSum(@Param("roleId")String roleId,@Param("powerName")String powerName);
	
	@Delete({"<script>",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"delete from t_role_power where role_id=#{roleId} and power_id=#{item.permissionId}",
		"</foreach>",
	 	"</script>"})
	void delectPermissionsInfoInRole(@Param("data")List<PermissionInfoEntity> entitys,@Param("roleId")String roleId);
}
