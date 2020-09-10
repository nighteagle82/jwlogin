package cn.ne.jwt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface IMemberAndRoleDao {

    String BASE_SELECT = " SELECT id, mid, rid FROM member_role ";
    String BASE_INSERT = " INSERT INTO member_role(mid, rid) VALUES ";

    // 根据用户编号查询所有的角色ID
    @Select(value = "SELECT rid FROM member_role WHERE mid=#{mid}")
    Set<String> findAllByMemberId(@Param("mid") String mid);
}
