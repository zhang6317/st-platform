package com.st.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author: zhangH
 * @date: 2019/11/10 15:55
 * @description:
 */
public class SessionUtil {

    public static final String USER = "SESSION:USER";

    public static final String ACCESS = "SESSION:ACCESS";

    public static <T> T getSesionBean(String sessionName,Class<T> tClass){
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        return (T)session.getAttribute(sessionName);
    }

    public static void setSessionBean(String sessionName, Object bean){
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        session.setAttribute(sessionName,bean);
    }

}
