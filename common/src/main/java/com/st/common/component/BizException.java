package com.st.common.component;

import com.movie.common.pojo.DataVo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class BizException extends Exception {

    public final static int DEFAULT_RROR = 10001;

    public final static int SERVICE_RROR = 20001;

    public final static int PATHE_RROR = 70001;

    public final static int PARAM_LACK_ERROR = 80001;
    public final static int PARAM_TYPE_ERROR = 80002;

    public final static int RESULT_LACK_ERROR = 90001;
    public final static int RESULT_TOO_MANY_ERROR = 90002;

    private int code;

    private final static HashMap<Integer, String> ERROR_MAP = new HashMap<>();

    public BizException(int code){
        super(setMessage(code, null));
    }

    public BizException(int code, String message) {
        super(setMessage(code, message));
        this.code = code;
    }

    static {
        ERROR_MAP.put(DEFAULT_RROR, "系统错误");
        ERROR_MAP.put(SERVICE_RROR, "第三方接口");
        ERROR_MAP.put(PATHE_RROR, "接口路径不存在");
        ERROR_MAP.put(PARAM_LACK_ERROR, "缺少参数");
        ERROR_MAP.put(PARAM_TYPE_ERROR, "参数类型");
        ERROR_MAP.put(RESULT_LACK_ERROR, "结果为空");
        ERROR_MAP.put(RESULT_TOO_MANY_ERROR, "查询到多条数据");
    }

    private static String setMessage(int code, String message) {
        String temp = ERROR_MAP.get(code);
        if (StringUtils.isNotBlank(message)) {
            return temp + " " + message;
        } else {
            return temp;
        }
    }

    public static DataVo toDataVo(Exception e) {
        BizException ex = null;
        if (e instanceof  BizException){
            ex = (BizException) e;
        } else {
            ex = new BizException(BizException.DEFAULT_RROR, e.getMessage());
        }
        return DataVo.error(ex.getCode()).message(ex.getMessage());
    }

    public int getCode() {
        return code;
    }
}
