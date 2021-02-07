package cn.ne.demo;

import cn.ne.demo.dao.INewsDao;
import cn.ne.demo.pojo.News;
import cn.ne.demo.service.INewsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisCacheApp.class)
@WebAppConfiguration
public class CacheTest {

    private Logger logger = LoggerFactory.getLogger(CacheTest.class);


    @Autowired
    private INewsService newsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testGet(){
        logger.info("News ==> {}", this.newsService.get(69L));
    }

    @Test
    public void testRedis(){
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        valueOperations.set("redis","test-connect-to-redis");
        System.out.println(valueOperations.get("redis"));
        System.out.println(valueOperations.get("news:cn.ne.demo.service.impl.NewsServiceImpl.get.69"));
    }


    @Test
    public void testFindByIdWithRedisCache() {
        logger.info("【第一次查询】{}", this.newsService.get(69L));
        logger.info("================ 分割线 ================ ");
        logger.info("【第二次查询】{}", this.newsService.get(69L));
    }


    @Test
    public void testPager() {
        // 取得分页后包装的数据
        PageInfo<News> all = this.newsService.findAll(1, 3);

        //生成JSON
        Map<String,Object> data = new HashMap<>();
        data.put("code",0);
        data.put("msg","succ");
        data.put("data", all);
        String json = JSON.toJSONString(data);
        System.out.println(json);

        //取出json中的数据
        JSONObject jsonObjectData = JSONObject.parseObject(JSONObject.parseObject(json).getString("data"));
        String listString = jsonObjectData.getString("list");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> " + listString);
        List<News> newsList = JSONObject.parseArray(listString, News.class);
        newsList.forEach(System.out::println);


    }


}
