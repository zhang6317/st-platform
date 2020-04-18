package com.st.common.service.user.feign;

import com.st.common.pojo.DataVo;
import com.st.common.pojo.PageData;
import com.st.common.service.auth.pojo.Menu;
import com.st.common.service.user.pojo.User;
import com.st.common.service.user.pojo.UserParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author: zhangH
 * @date: 2019/11/10 11:54
 * @description:
 */
@Component
public class UserFeignFallback implements UserFeign {


    @Override
    public DataVo<User> getUserInfo() {
        return null;
    }

    @GetMapping("loginByMobileAndPassword")
    @Override
    public DataVo<Object> loginByMobileAndPassword(String mobile, String password) {
        return null;
    }

    @Override
    public DataVo<Object> logout() {
        return null;
    }

    @Override
    public DataVo<List<Menu>> getUserMenus() {
        return null;
    }

    @Override
    public DataVo<PageData<User>> getUserList(UserParam userParam) {
        return null;
    }
}
