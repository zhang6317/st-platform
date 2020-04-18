package com.st.common.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author: zhangH
 * @date: 2020/4/6 09:23
 * @description:
 */
@Data
public class PageParam {

    @ApiModelProperty(notes = "分页条数")
    @NotEmpty(message = "分页条数不能为空")
    private long pageSize;

    @ApiModelProperty(notes = "当前页数")
    @NotEmpty(message = "当前页数不能为空")
    private long currentPage;

}
