package com.st.common.config;

import com.movie.common.component.FeignHystrixConcurrencyStrategy;
import com.movie.common.component.FeignRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangH
 * @date: 2019/10/20 10:12
 * @description:
 */
@Configuration
@ComponentScan("com.movie.common.service")
@EnableFeignClients(basePackages = {"com.movie.common.service"})
@Slf4j
public class FeignConfig {

    @Bean
    public FeignRequestInterceptor feignRequestInterceptor(){
        return new FeignRequestInterceptor();
    }

    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy(){
        return new FeignHystrixConcurrencyStrategy();
    }


}
