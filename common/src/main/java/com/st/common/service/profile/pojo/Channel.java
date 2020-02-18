package com.st.common.service.profile.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_channel")
@Api("渠道实体")
public class Channel implements Serializable {

  @TableId(type = IdType.AUTO)
  @ApiModelProperty(notes = "渠道id")
  private long channelId;

  @ApiModelProperty(notes = "渠道名称")
  private String channelName;

  @ApiModelProperty(notes = "渠道类型")
  private long channelType;

  @ApiModelProperty(notes = "appid")
  private String appId;

  @ApiModelProperty(notes = "appKey1")
  private String appKey1;

  @ApiModelProperty(notes = "appKey2")
  private String appKey2;

  @ApiModelProperty(notes = "appKey3")
  private String appKey3;

  @ApiModelProperty(notes = "创建时间")
  private Date createTime;

  @ApiModelProperty(notes = "更新时间")
  private Date updateTime;

  @ApiModelProperty(notes = "备注")
  private String note;

}
