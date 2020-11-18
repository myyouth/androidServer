package com.zjKingdee.androidServer.dao.primary;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zjKingdee.androidServer.entity.PermissionInfoEntity;

public interface PermissionMapper {
	@Insert("insert into t_power (power_name,create_time) values (#{entity.permissionName},#{entity.permissionTime})")
	void createPermissionInfo(@Param("entity")PermissionInfoEntity entity);
	
	@Select("select count(*) from t_power where power_name=#{permissionName}")
	int searchPermissionInfo(String permissionName);
	
	@Select("select top ${size} power_id permissionId,power_name permissionName,create_time permissionTime,rowNumber "+
			"from (select row_number() over(order by power_id) as rowNumber,*  from t_power where power_name like #{permissionName})A where rowNumber>#{index}")
	List<PermissionInfoEntity> searchAllPermissionInfo(@Param("index")String index,@Param("size")String size,@Param("permissionName")String permissionName);
	
	@Select("select count(*) from t_power where power_name like #{permissionName}")
	int searchPermissionSum(@Param("permissionName")String permissionName);
	
	@Delete({"<script>",
		"<foreach collection='data' item='item' index='index' separator=';'>",
		"delete from t_role_power where power_id=(select power_id from t_power where power_name=#{item.permissionName})",
		"delete from t_power where power_name=#{item.permissionName}",
		"</foreach>",
	 	"</script>"})
	void delectPermissionInfo(@Param("data")List<PermissionInfoEntity> entitys);
}
