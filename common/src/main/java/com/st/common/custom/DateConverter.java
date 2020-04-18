package com.st.common.custom;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * @author: zhangH
 * @date: 2020/4/8 08:01
 * @description:
 */
@Slf4j
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(@NonNull String param) {
        try {
            if (StringUtils.isBlank(param)) {
                return null;
            } else if (param.length() == 10) {
                String datePattern = "yyyy-MM-dd";
                return DateUtils.parseDate(param, datePattern);
            } else {
                String timePattern = "yyyy-MM-dd HH:mm:ss";
                return DateUtils.parseDate(param, timePattern);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
