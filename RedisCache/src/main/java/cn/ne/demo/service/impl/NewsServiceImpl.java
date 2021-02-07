package cn.ne.demo.service.impl;


import cn.ne.demo.dao.INewsDao;
import cn.ne.demo.pojo.News;
import cn.ne.demo.service.INewsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: SpringCache注解建议加在实现类中。【否则springboot1中无法生效，springboot2正常。】

@Service
@CacheConfig(cacheNames = "news")   // 配置 统一的缓存名称
public class NewsServiceImpl implements INewsService {

    @Autowired
    private INewsDao newsDao;

    // 在指定的方法上使用缓存注解启用缓存。
    @Cacheable(unless = "#result==null")
    @Override
    public News get(Long nid) {
        return this.newsDao.findById(nid);
    }

    @Cacheable(unless = "#result==null")
    @Override
    public PageInfo<News> findAll(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return new PageInfo<>(this.newsDao.findAll());

    }


}
