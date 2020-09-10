package cn.ne.jwt.dao;


import cn.ne.jwt.model.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IMemberDao {

    String BASE_SELECT = " SELECT id, mid, name, password, locked FROM member ";
    String BASE_INSERT = " INSERT INTO member (mid, name, password, locked) VALUES ";

    @Select(value = BASE_SELECT + "WHERE mid=#{mid}")
    Member findByMid(String mid);
}
