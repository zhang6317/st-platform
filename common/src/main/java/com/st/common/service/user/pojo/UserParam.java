package com.st.common.service.user.pojo;

import com.st.common.pojo.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: zhangH
 * @date: 2020/4/9 09:24
 * @description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Api("用户实体")
public class UserParam extends PageParam {

    @ApiModelProperty(notes = "昵称")
    private String nickName;

    @ApiModelProperty(notes = "手机号")
    private String mobile;
}
