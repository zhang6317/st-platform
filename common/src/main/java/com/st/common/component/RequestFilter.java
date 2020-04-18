package com.st.common.component;


import com.st.common.custom.RequestWrapper;
import com.st.common.custom.ResponseWrapper;
import com.st.common.util.BizException;
import com.st.common.util.ResultEnum;
import com.st.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author zhang
 * controller全局异常处理  日志打印
 */
@WebFilter(filterName = "logFilter")
@Slf4j
public class RequestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 日志打印
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");

        // 包装request和response
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);

        // 入参日志
        String servletPath = request.getServletPath();
        String url = request.getRequestURI();

        // 静态文件直接放行
        String[] statics = {".css", ".html", ".jsp", ".js", ".png", ".jpg"};
        for (String suffix : statics) {
            if (servletPath.contains(suffix)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        // 获取路径和表单参数
        StringBuilder formParam = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        if (parameterNames.hasMoreElements()) {
            while (true) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                formParam.append(paramName).append("=").append(paramValue);
                if (parameterNames.hasMoreElements()) {
                    formParam.append("&");
                } else {
                    break;
                }
            }
        }

        log.info("{}【请求信息】 path: {}  param: {}", System.getProperty("line.separator"), url, formParam);
        String body = new String(IOUtils.toByteArray(requestWrapper.getInputStream()), StandardCharsets.UTF_8);
        if (StringUtils.isNotBlank(body)) {
            log.info("{}【请求体】 {} {}", System.getProperty("line.separator"), System.getProperty("line.separator"), body);
        }

        String result = null;

        filterChain.doFilter(requestWrapper, responseWrapper);
        result = new String(responseWrapper.getResponseData(), StringUtil.DEFAULT_CHARTSET);

        if (StringUtil.isBlank(result)) {
            throw new BizException(ResultEnum.FAIL, "返回信息为空");
        }

        // 当日志长度小于4096时打印日志
        final int size = 4096;
        if (result.length() < size) {
            log.info("{}【返回信息】 status: {} {} {}", System.getProperty("line.separator"), response.getStatus(), System.getProperty("line.separator"), result);
        } else {
            log.info("{}【返回信息】 请求体超过4096 略过返回日志", System.getProperty("line.separator"));
        }


        ServletOutputStream outputStream = response.getOutputStream();
        // 真正返回数据
        outputStream.write(result.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void destroy() {

    }
}
