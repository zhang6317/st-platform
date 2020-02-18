package com.st.common.service.profile.feign;

import com.movie.common.pojo.DataVo;
import com.movie.common.service.profile.pojo.Channel;
import com.movie.common.component.FallBack;
import org.springframework.stereotype.Component;

/**
 * @author: zhangH
 * @date: 2019/11/5 01:41
 * @description:
 */
@Component
public class ProfileFeignFallBack extends FallBack implements  ProfileFeign {

    @Override
    public DataVo getChannelByChannelId(Integer channelId) {
        return defaultFallBack();
    }

    @Override
    public DataVo<Channel> createSession() {
        return defaultFallBack();
    }
}
