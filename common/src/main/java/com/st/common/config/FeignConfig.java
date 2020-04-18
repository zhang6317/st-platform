package com.st.common.config;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.st.common.component.FeignHystrixConcurrencyStrategy;
import com.st.common.component.FeignRequestInterceptor;
import com.st.common.custom.DateConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangH
 * @date: 2019/10/20 10:12
 * @description:
 */
@Configuration
@ComponentScan("com.st.common.service")
@EnableFeignClients(basePackages = {"com.st.common.service"})
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


    @Bean
    public HttpMessageConverters fastJsonConverter(){
        // 1.需要先定义一个convert 转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.添加fastJson的配置信息,比如，是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 空值特别处理
        // WriteNullListAsEmpty 将Collection类型字段的字段空值输出为[]
        // WriteNullStringAsEmpty 将字符串类型字段的空值输出为空字符串 ""
        // WriteNullNumberAsZero 将数值类型字段的空值输出为0
        // WriteNullBooleanAsFalse 将Boolean类型字段的空值输出为false
        // DisableCircularReferenceDetect 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.DisableCircularReferenceDetect);
        //日期格式化
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        fastMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        fastMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        fastMediaTypes.add(MediaType.APPLICATION_PDF);
        fastMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        fastMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        fastMediaTypes.add(MediaType.APPLICATION_XML);
        fastMediaTypes.add(MediaType.IMAGE_GIF);
        fastMediaTypes.add(MediaType.IMAGE_JPEG);
        fastMediaTypes.add(MediaType.IMAGE_PNG);
        fastMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        fastMediaTypes.add(MediaType.TEXT_HTML);
        fastMediaTypes.add(MediaType.TEXT_MARKDOWN);
        fastMediaTypes.add(MediaType.TEXT_PLAIN);
        fastMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        // 3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public DateConverter dateConverter(){
        return new DateConverter();
    }

}
