package cn.ne.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class BeanConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

//    @Bean
//    public RedisUtil redisUtil(){
//        return new RedisUtil();
//    }

//    @Bean
//    public RedisProperties getRedisProperties(){
//        return new RedisProperties();
//    }

}
