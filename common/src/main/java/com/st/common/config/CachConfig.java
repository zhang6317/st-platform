package com.st.common.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
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
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
//           if (target instanceof Target.HardCodedTarget)
            String pre = target.getClass().getName();
            return pre + "." + method.getName() + ":" + Arrays.asList(params).toString();
        };
    }
}
