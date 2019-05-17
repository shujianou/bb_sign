package com.banban.app.common.api.kaoqin;

import com.banban.app.common.api.BaseApi;
import com.banban.app.common.api.audit.AuditApi;
import com.banban.app.common.bean.CommonParams;
import com.banban.app.common.bean.KaoqinInfoQuery;
import com.banban.app.common.bean.LocationInfoQuery;
import com.banban.app.common.bean.SignQuery;
import com.banban.app.common.d.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Vim 2019/4/17 0017 上午 1:51
 *
 * @author Vim
 */
@Service
public class KaoqinApi extends BaseApi {


    /**
     * 获取考勤记录
     * @param token 用户token
     * @param orgId 部门ID
     * @param companyId 公司ID
     * @param ruleId 考勤规则ID 1478
     * @param userId 用户ID
     */
    public void kaoqinInfo(String token,Integer ruleId,Integer userId,String orgId,String companyId) {
        KaoqinInfoQuery query = new KaoqinInfoQuery();
        query.setCompanyId(companyId);
        query.setRuleId(ruleId);
        query.setUserId(userId);

        CommonParams params = new CommonParams();

        params.setAppVersion("1.0.1");

        //获取用户TOKEN
        params.setToken(token);
        params.setOrgId(orgId);
        params.setChannel(null);
        params.setLongOrgId(1L);
        params.setPage(0);
        params.setLanguage("zh");
        params.setOriginatorId(null);
        params.setPagesize(5);
        params.setAppKey(SignUtils.APP_KEY);

        System.out.println(SignUtils.getSignRequest(params, query));
    }

    /**
     * 获取定位信息
     */
    public void locationInfo(String token,Integer userId,String orgId,String companyId) {
        LocationInfoQuery locationInfoQuery = new LocationInfoQuery();
        locationInfoQuery.setCompanyId(companyId);
        locationInfoQuery.setUserId(userId);

        CommonParams params = new CommonParams();

        params.setAppVersion("1.0.1");

        //获取用户TOKEN
        params.setToken(token);
        params.setOrgId(orgId);
        params.setChannel(null);
        params.setLongOrgId(1L);
        params.setPage(0);
        params.setLanguage("zh");
        params.setOriginatorId(null);
        params.setPagesize(5);
        params.setAppKey(SignUtils.APP_KEY);

        System.out.println(SignUtils.getSignRequest(params, locationInfoQuery));
    }

    /**
     * 签到
     */
    public ResponseEntity<Map> sign(String token, String orgId, SignQuery query) {

        CommonParams params = new CommonParams();

        params.setAppVersion("1.0.1");
        //获取用户TOKEN
        params.setToken(token);
        params.setOrgId(orgId);
        params.setChannel(null);
        params.setLongOrgId(1L);
        params.setPage(0);
        params.setLanguage("zh");
        params.setOriginatorId(Long.valueOf(query.getUserId()));
        params.setPagesize(5);
        params.setAppKey(SignUtils.APP_KEY);

        String signRequest = SignUtils.getSignRequest(params, query);
        System.out.println(signRequest);


        return restTemplate.postForEntity("http://sapi.distrii.com:80/banbanbao-api/secSign/sign", new HttpEntity<>(signRequest, HEADERS), Map.class);
    }

    @Autowired
    private RestTemplate restTemplate;

}
