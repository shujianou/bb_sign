package com.banban.app.common.api.kaoqin;

import com.banban.app.common.api.BaseApi;
import com.banban.app.common.bean.CommonParams;
import com.banban.app.common.bean.KaoqinRuleQuery;
import com.banban.app.common.d.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 考勤规则API
 * Created by Irany 2019/5/17 9:20
 */
@Component
public class RuleApi extends BaseApi {


    public ResponseEntity ruleInfo(Integer userId,String companyId,String token) {
        KaoqinRuleQuery query = new KaoqinRuleQuery();
        query.setCompanyId(companyId);
        query.setUserId(userId);

        CommonParams params = new CommonParams();

        params.setAppVersion("1.0.1");

        //获取用户TOKEN
        params.setToken(token);
        params.setOrgId(companyId);
        params.setChannel(null);
        params.setLongOrgId(1L);
        params.setOriginatorId(Long.valueOf(userId));
        params.setPropertyId(1);
        params.setPage(0);
        params.setLanguage("zh");
        params.setPagesize(5);
        params.setAppKey(SignUtils.APP_KEY);


        String signRequest = SignUtils.getSignRequest(params, query);

        return restTemplate.postForEntity("http://sapi.distrii.com:80/banbanbao-api/signRule/findRuleByMe", new HttpEntity<>(signRequest, HEADERS), Map.class);

    }


    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        new RuleApi().ruleInfo(121100, "0b446420687fff4201689d831ca90013", "0e6f9cb01c1e81a562456fc56a3bbb18");
    }
}
