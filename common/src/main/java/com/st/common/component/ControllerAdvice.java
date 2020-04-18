package com.st.common.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.st.common.pojo.DataVo;
import com.st.common.util.BizException;
import com.st.common.util.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author: zhangH
 * @date: 2019/10/4 00:17
 * @description: 打印返回日志用
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice implements RequestBodyAdvice {

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     * @throws UnsupportedEncodingException
     */
    @ExceptionHandler(value = Exception.class)
    public void errorHandler(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception ex) throws IOException {
        DataVo<Object> dataVo = null;
        if (ex instanceof BizException){
            dataVo = ((BizException) ex).toDataVo();
        } else if(ex instanceof NoHandlerFoundException){
            dataVo = new DataVo<>();
            dataVo.setCode(ResultEnum.PATH_LACK_ERROT.code());
            dataVo.setMessage(ResultEnum.PATH_LACK_ERROT.message());
            httpServletResponse.setStatus(404);
        } else {
            dataVo = new DataVo<>();
            dataVo.setCode(ResultEnum.FAIL.code());
            dataVo.setMessage(ResultEnum.FAIL.message());
        }

        String result = JSON.toJSONString(dataVo, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullStringAsEmpty);

        // 出参日志
        log.error("{}【返回异常信息】 type: {} {} {}", System.getProperty("line.separator"), ex.getClass(), System.getProperty("line.separator"), result);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(result);
        httpServletResponse.getWriter().flush();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method=methodParameter.getMethod();
        log.info("{}.{}:{}",method.getDeclaringClass().getSimpleName(),method.getName(), JSON.toJSONString(o));
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method=methodParameter.getMethod();
        log.info("{}.{}",method.getDeclaringClass().getSimpleName(),method.getName());
        return o;
    }
}
