package com.st.common.service.profile.feign;

import com.movie.common.pojo.DataVo;
import com.movie.common.service.profile.pojo.Channel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zhangH
 * @date: 2019/11/5 01:41
 * @description:
 */
@FeignClient(value = "SERVICE-PROFILE", fallback = ProfileFeignFallBack.class)
public interface ProfileFeign {

    @ApiOperation(value = "获取渠道信息")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId", value = "渠道d", dataType = "Integer")
    })// 使用该注解描述方法参数信息，此处需要注意的是paramType参数，需要配置成path，否则在UI中访问接口方法时，会报错
    @GetMapping("/getChannelByChannelId")
    @Cacheable(cacheNames = "SERVICE-PROFILE:channel", key = "#channelId", unless = "#result.data == null")
    public DataVo<Channel> getChannelByChannelId(@RequestParam Integer channelId);

    @ApiOperation(value = "创建session")// 使用该注解描述接口方法信息
    @GetMapping("/createSession")
    public DataVo<Channel> createSession();
}
