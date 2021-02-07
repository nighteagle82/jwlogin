package cn.ne.demo.service;

import cn.ne.demo.pojo.News;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface INewsService {

    News get(Long nid);

    PageInfo<News> findAll(Integer currentPage, Integer pageSize);

}
