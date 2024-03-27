package io.github.loserya.dao;

import io.github.loserya.entity.LoserTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LoserDao {

    // 不分库不分表--start 在主数据源只有一直表
    @Select("select * from sys_user where id = #{id}")
    LoserTable queryUserById(long ignore, @Param("id") long id);
    // 不分库不分表--end


    // 分库分表--start 在所有库存在多张表
    @Select("select * from sys_loser where id = #{id}")
    LoserTable queryById(long ignore, @Param("id") long id);

    @Insert("insert into sys_loser (id,name, age, create_by, create_time, update_by, update_time, delete_flag, version) " +
            "values (#{id},#{name}, #{age}, #{createBy}, #{createTime}, #{updateBy}, #{updateTime}, #{deleteFlag}, #{version})")
    int insert(LoserTable loserTable);

    @Delete("delete from sys_loser where id = #{id}")
    int deleteById(long id);
    // 分库分表--end


    // 分库不分表--end 在所有的库 都只有一张表
    @Insert("insert into sys_lusir (id,name, age, create_by, create_time, update_by, update_time, delete_flag, version) " +
            "values (#{id},#{name}, #{age}, #{createBy}, #{createTime}, #{updateBy}, #{updateTime}, #{deleteFlag}, #{version})")
    int insert01(LoserTable loserTable);

    @Select("select * from sys_lusir where id = #{id}")
    LoserTable queryById01(long ignore, @Param("id") long id);

    @Delete("delete from sys_lusir where id = #{id}")
    int deleteById01(long id);
    // 分库不分表--end


    // 不分库分表--end 在主数据源有多张表
    @Insert("insert into sys_alan (id,name, age, create_by, create_time, update_by, update_time, delete_flag, version) " +
            "values (#{id},#{name}, #{age}, #{createBy}, #{createTime}, #{updateBy}, #{updateTime}, #{deleteFlag}, #{version})")
    int insert02(LoserTable loserTable);

    @Select("select * from sys_alan where id = #{id}")
    LoserTable queryById02(long ignore, @Param("id") long id);

    @Delete("delete from sys_alan where id = #{id}")
    int deleteById02(long id);
    // 不分库分表--end

}

