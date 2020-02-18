package com.st.common.service.user.feign;

import com.movie.common.component.FallBack;
import com.movie.common.pojo.DataVo;
import com.movie.common.service.profile.feign.ProfileFeignFallBack;
import com.movie.common.service.user.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zhangH
 * @date: 2019/11/10 11:54
 * @description:
 */
@Component
public class UserFeignFallback extends FallBack implements UserFeign {

    @Override
    public DataVo<User> userLogin(Integer channelId, String code, String encryptedData, String ivStr) throws Exception {
        return defaultFallBack();
    }
}
