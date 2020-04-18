package com.st.common.service.user.feign;

import com.st.common.pojo.DataVo;
import com.st.common.pojo.PageData;
import com.st.common.service.auth.pojo.Menu;
import com.st.common.service.user.pojo.User;
import com.st.common.service.user.pojo.UserParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author: zhangH
 * @date: 2019/11/10 11:54
 * @description:
 */
@FeignClient(value = "SERVICE-USER", fallback = UserFeignFallback.class)
public interface UserFeign {


    /**
     * 获取用户信息
     * @return 返回值
     */
    @ApiOperation(value = "获取用户信息")// 使用该注解描述接口方法信息
    @GetMapping("userInfo")
    DataVo<User> getUserInfo();

    /**
     *手机号密码登陆系统
     * @param mobile 手机号
     * @param password 密码
     * @return 返回值
     */
    @ApiOperation(value = "手机号密码登陆系统")// 使用该注解描述接口方法信息
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
    })
    @GetMapping("loginByMobileAndPassword")
    DataVo<Object> loginByMobileAndPassword(String mobile, String password);

    /**
     * 登出系统
     * @return 返回值
     */
    @ApiOperation(value = "登出系统")// 使用该注解描述接口方法信息
    @GetMapping("logout")
    DataVo<Object> logout();

    /**
     * 获取用户可以访问的菜单
     * @return 返回值
     */
    @ApiOperation(value = "获取用户菜单")// 使用该注解描述接口方法信息
    @GetMapping("userMenus")
    DataVo<List<Menu>> getUserMenus();

    /**
     * 获取用户列表
     * @return 返回值
     */
    @ApiOperation(value = "获取用户列表")// 使用该注解描述接口方法信息
    @GetMapping("userList")
    DataVo<PageData<User>> getUserList(UserParam userParam);
}
