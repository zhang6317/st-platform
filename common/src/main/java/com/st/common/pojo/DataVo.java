package com.st.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangH
 * @date: 2019/6/1 23:05
 * @description:
 */
@ApiModel
@Data
public class DataVo<T> implements Serializable {

    public static DataVo ok() {
        DataVo dataVO = new DataVo();
        dataVO.setCode(0);
        dataVO.setMessage("success");
        return dataVO;
    }

    public static DataVo error(int code) {
        DataVo dataVO = new DataVo();
        dataVO.setCode(code);
        return dataVO;
    }

    public DataVo data(T object) {
        this.setData(object);
        return this;
    }

    public DataVo message(String message) {
        this.setMessage(message);
        return this;
    }

    @ApiModelProperty(name = "请求状态码", notes = "请求状态码 0为成功 1为失败  其他为异常")
    private int code;

    @ApiModelProperty(notes = "请求结果说明")
    private String message;

    @ApiModelProperty(notes = "请求结果数据")
    private T data;

}
