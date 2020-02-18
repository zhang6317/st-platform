package com.st.common.service.user.feign;

import com.movie.common.pojo.DataVo;
import com.movie.common.service.profile.feign.ProfileFeignFallBack;
import com.movie.common.service.profile.pojo.Channel;
import com.movie.common.service.user.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: zhangH
 * @date: 2019/11/10 11:54
 * @description:
 */
@FeignClient(value = "SERVICE-USER", fallback = ProfileFeignFallBack.class)
public interface UserFeign {


    @ApiOperation(value = "获取用户小程序用户信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId", value = "渠道d", dataType = "Integer"),
            @ApiImplicitParam(name = "code", value = "小程序登陆coden", dataType = "String"),
            @ApiImplicitParam(name = "encryptedData", value = "小程序登陆加密串", dataType = "String"),
            @ApiImplicitParam(name = "ivStr", value = "小程序解密iv", dataType = "String")
    })// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
    @GetMapping("userLogin")
    DataVo<User> userLogin(Integer channelId, String code, String encryptedData, String ivStr) throws Exception;
}
