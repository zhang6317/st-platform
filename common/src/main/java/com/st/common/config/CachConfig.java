package com.st.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangH
 * @date: 2019/10/20 22:52
 * @description:
 */
@EnableCaching
@Configuration
public class CachConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    RedissonSpringCacheManager redissonSpringCacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<>();
        config.put(appName, new CacheConfig(30 * 60 * 100L, 15 * 60 * 1000L));
        return new RedissonSpringCacheManager(redissonClient, config);
    }
}
