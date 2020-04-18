package com.st.common.service.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: zhangH
 * @date: 2019/11/9 11:57
 * @description:
 */
@Data
@TableName("t_user")
@Api("用户实体")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(notes = "用户id")
    private Long userId;

    @ApiModelProperty(notes = "渠道用户id1")
    private String channelUserId1;

    @ApiModelProperty(notes = "渠道用户id2")
    private String channelUserId2;

    @ApiModelProperty(notes = "用户角色id")
    @TableField(exist = false)
    private List<Integer> roleIds;

    @ApiModelProperty(notes = "昵称")
    private String nickName;

    @ApiModelProperty(notes = "性别")
    private String gender;

    @ApiModelProperty(notes = "身份证号")
    private String idCardNo;

    @ApiModelProperty(notes = "手机号")
    private String mobile;

    @ApiModelProperty(notes = "密码 ")
    private String password;

    @ApiModelProperty(notes = "语言")
    private String language;

    @ApiModelProperty(notes = "城市")
    private String city;

    @ApiModelProperty(notes = "省份")
    private String province;

    @ApiModelProperty(notes = "国家")
    private String country;

    @ApiModelProperty(notes = "头像")
    private String avatarUrl;

    @ApiModelProperty(notes = "小程序sessionKey")
    @TableField(exist = false)
    private String sessionKey;

    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

    @ApiModelProperty(notes = "更新时间")
    private Date updateTime;

    @ApiModelProperty(notes = "备注")
    private String note;
}
