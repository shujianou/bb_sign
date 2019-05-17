package com.banban.app.common.api.audit;

import com.banban.app.common.api.BaseApi;
import com.banban.app.common.bean.AuditListQuery;
import com.banban.app.common.bean.CommonParams;
import com.banban.app.common.d.SignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by Vim 2019/4/18 12:18
 *
 * @author Vim
 */
@Service
public class AuditApi extends BaseApi {


    public ResponseEntity list(String token, String orgId, AuditListQuery query, Integer page, Integer pageSize) {
        CommonParams params = new CommonParams();

        params.setAppVersion("1.0.1");
        //获取用户TOKEN
        params.setToken(token);
        params.setOrgId(orgId);
        params.setChannel(null);
        params.setLongOrgId(1L);
        params.setPage(page);
        params.setLanguage("zh");
        params.setOriginatorId(Long.valueOf(query.getUserId()));
        params.setPagesize(pageSize);
        params.setPropertyId(1);

        params.setAppKey(SignUtils.APP_KEY);

        String signRequest = SignUtils.getSignRequest(params, query);
        System.out.println(signRequest);

        return restTemplate.postForEntity("http://sapi.distrii.com:80/banbanbao-api/secAuditTypeInfoRestful/queryAuditListByStatus", new HttpEntity<>(signRequest,HEADERS), Map.class);
    }


    @Autowired
    private RestTemplate restTemplate;


    public static class Type {
        public static final Integer 我发起的 = 1;
        public static final Integer 待我审批 = 2;
        public static final Integer 我已审批 = 3;
        public static final Integer 抄送我的 = 3;
    }

    public static class FormType {
        public static final Integer 我的调休 = 6;
        public static final Integer 我的加班 = 4;
    }
}
