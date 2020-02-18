package com.st.common.service.wx.miniapp.feign;

import com.movie.common.pojo.DataVo;
import com.movie.common.component.FallBack;
import org.springframework.stereotype.Component;

/**
 * @author: zhangH
 * @date: 2019/11/8 15:27
 * @description:
 */
@Component
public class WxMiniappFeignFallBack extends FallBack implements WxMiniappFeign {

    @Override
    public DataVo getUserOpenId(Integer channelId, String code) throws Exception {
        return defaultFallBack();
    }

    @Override
    public DataVo getUserInfo(Integer channelId, String sessionKry, String encryptedData, String ivStr) throws Exception {
        return defaultFallBack();
    }
}
