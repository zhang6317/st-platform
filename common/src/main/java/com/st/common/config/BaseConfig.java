package com.st.common.config;

import com.st.common.component.ControllerAdvice;
import com.st.common.component.MyCookieSerializer;
import com.st.common.component.RequestFilter;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: zhangH
 * @date: 2019/10/20 22:53
 * @description:
 */
@EnableEurekaClient
@Configuration
@EnableRedissonHttpSession
@Import({RequestFilter.class, ControllerAdvice.class, MyCookieSerializer.class})
public class BaseConfig {

}
