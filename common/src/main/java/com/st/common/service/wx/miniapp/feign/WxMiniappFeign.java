package com.st.common.service.wx.miniapp.feign;

import com.movie.common.pojo.DataVo;
import com.movie.common.service.profile.feign.ProfileFeignFallBack;
import com.movie.common.service.user.pojo.User;
import com.movie.common.service.wx.pojo.WxIdVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zhangH
 * @date: 2019/11/8 15:27
 * @description:
 */
@FeignClient(value = "SERVICE-WX-MINIAPP", fallback = WxMiniappFeignFallBack.class)
public interface WxMiniappFeign {

    @ApiOperation(value = "获取用户小程序openid")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId", value = "渠道d", dataType = "Integer"),
            @ApiImplicitParam(name = "code", value = "小程序登陆code", dataType = "String")
    })// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
    @GetMapping("getUserOpenId")
    DataVo<WxIdVo> getUserOpenId(@RequestParam Integer channelId, @RequestParam String code) throws Exception;

    @ApiOperation(value = "获取用户小程序用户信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId", value = "渠道d", dataType = "Integer"),
            @ApiImplicitParam(name = "encryptedData", value = "小程序登陆加密串", dataType = "String")
    })// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
    @GetMapping("getUserInfo")
    DataVo<User> getUserInfo(@RequestParam Integer channelId, @RequestParam String sessionKry, @RequestParam String encryptedData, @RequestParam String ivStr) throws Exception;
}
