package com.banban.controller;

import com.alibaba.fastjson.JSONObject;
import com.banban.app.common.api.kaoqin.KaoqinApi;
import com.banban.app.common.api.login.LoginApi;
import com.banban.app.common.bean.CompanyUserInfo;
import com.banban.app.common.bean.SignQuery;
import com.banban.bean.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 签到/签退
 * Created by Vim 2019/4/17 16:10
 *
 * @author Vim
 */
@RequestMapping("sign")
@RestController
public class SignController {


    /**
     * 签到
     */
    @RequestMapping
    public Object sign(String companyId, Integer userId, Integer ruleId, String token, String location) {
        if (StringUtils.isBlank(location) || !location.contains(",")) {
            return R.fail("错误参数");
        }

        SignQuery signQuery = new SignQuery();
        signQuery.setCompanyId(companyId);
        signQuery.setRuleId(ruleId);
        signQuery.setUserId(userId);

        String[] locationSplit = location.split(",");
        signQuery.setLat(Float.valueOf(locationSplit[0]));
        signQuery.setLng(Float.valueOf(locationSplit[1]));

        signQuery.setSignType(SignQuery.签到);

        return kaoqinApi.sign(token, companyId, signQuery).getBody();

    }

    /**
     * 签退
     */
    @RequestMapping("out")
    public Object signOut(String companyId, Integer userId, Integer ruleId, String token, Integer address) {
        SignQuery signQuery = new SignQuery();
        signQuery.setCompanyId(companyId);
        signQuery.setRuleId(1523);
        signQuery.setUserId(userId);
        setAddress(address, signQuery);
        signQuery.setSignType(SignQuery.签退);

        return kaoqinApi.sign(token, companyId, signQuery).getBody();
    }

    private void setAddress(Integer address, SignQuery signQuery) {
        if (address == 1) {
            signQuery.setLat(ADDRESS_MAP.get(1)[0]);
            signQuery.setLng(ADDRESS_MAP.get(1)[1]);
        } else if (address == 2) {
            signQuery.setLat(ADDRESS_MAP.get(2)[0]);
            signQuery.setLng(ADDRESS_MAP.get(2)[1]);
        }
    }

    /**
     * 登录
     */
    @RequestMapping("login")
    public Object login(String account, String password) {
        ResponseEntity loginResponse = loginApi.login(account, password);
        Map<String, Object> loginResponseMap = (Map<String, Object>) loginResponse.getBody();

        String status = (String) loginResponseMap.get("status");

        if (!StringUtils.equals(status, "0000")) {
            return loginResponseMap;
        }

        Map<String, Object> dataMap = (Map<String, Object>) loginResponseMap.get("data");

        this.token = (String) dataMap.get("token");
        this.companyUserInfo = JSONObject.parseObject(JSONObject.toJSONString(dataMap.get("companyUserInfo")), CompanyUserInfo.class);
        this.userId = companyUserInfo.getUserInfoOut().getUserId();
        this.companyId = companyUserInfo.getCompanyInfoOut().getCompanyId();
        this.orgId = companyUserInfo.getCompanyInfoOut().getCompanyId();

        return loginResponseMap;
    }

    @Autowired
    private LoginApi loginApi;

    @Autowired
    private KaoqinApi kaoqinApi;


    private String token;
    private Integer userId;
    private String orgId;
    private String companyId;

    private CompanyUserInfo companyUserInfo;

    private static final HashMap<Integer, Float[]> ADDRESS_MAP = new HashMap<>();

    static {
        ADDRESS_MAP.put(1, new Float[]{31.263952F, 121.52243F});
        ADDRESS_MAP.put(2, new Float[]{31.236125F, 121.52289F});
    }


}
