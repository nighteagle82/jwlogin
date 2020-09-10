package cn.ne.demo;

import cn.ne.demo.service.INewsService;
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

}
