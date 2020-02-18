package com.st.common.service.wx.pojo;

import lombok.Data;

/**
 * @author: zhangH
 * @date: 2019/11/9 08:35
 * @description:
 */
@Data
public class WxIdVo {

    private String openid;

    private String unionid;

    private String sessionKey;
}
