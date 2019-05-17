package com.banban.app.common.api.login;

import com.banban.app.common.api.BaseApi;
import com.banban.app.common.bean.UserLoginInfo;
import com.banban.app.common.d.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Vim 2019/4/17 16:14
 *
 * @author Vim
 */
@Service
public class LoginApi extends BaseApi {

    /**
     * 获取考勤记录
     */
    public ResponseEntity login(String account, String password) {
        UserLoginInfo loginInfo = new UserLoginInfo();
        loginInfo.setChannelId("190e35f7e00e3848c47");
        loginInfo.setChannelType("2");
        loginInfo.setCode(password);
        loginInfo.setPhone(account);
        loginInfo.setNum(0);
        loginInfo.setState("1");
        loginInfo.setCountryNumber("86");
        loginInfo.setType(0);

        String signRequest = SignUtils.getSignRequest(null, loginInfo);
        System.out.println(signRequest);

        return restTemplate.postForEntity("http://sapi.distrii.com/banbanbao-api/ofclogin/userGlobalLogin", new HttpEntity<>(signRequest, HEADERS), Map.class);
    }


    @Autowired
    private RestTemplate restTemplate;
}
