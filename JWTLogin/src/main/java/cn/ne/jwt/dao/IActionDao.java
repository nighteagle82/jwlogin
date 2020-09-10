package cn.ne.jwt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface IActionDao {

    String BASE_SELECT = " SELECT id,actid,title,rid FROM action ";
    String BASE_INSERT = " INSERT INTO action (actid,title,rid) VALUES ";

    // 根据用户编号获取对应的所有权限
    @Select(value = " SELECT actid FROM action WHERE rid IN ( SELECT rid FROM member_role WHERE mid=#{mid} ); ")
    Set<String> findAllByMemberId(String mid);
}
