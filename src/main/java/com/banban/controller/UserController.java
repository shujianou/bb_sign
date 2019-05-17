package com.banban.controller;

import com.banban.app.common.api.login.LoginApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Irany 2019/5/17 10:17
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public Object login(String account, String password) {
        return loginApi.login(account, password).getBody();
    }

    @Autowired
    private LoginApi loginApi;
}
