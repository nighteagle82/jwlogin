package cn.ne.demo.dao;

import cn.ne.demo.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface INewsDao {

   String BASE_SELECT = " SELECT nid, title, content FROM news ";

    @Select(value = BASE_SELECT + " where nid=#{nid};")
    News findById(Long nid);

    @Select(value = BASE_SELECT)
    List<News> findAll();
}
