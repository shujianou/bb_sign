package com.banban.app.common;

import com.banban.app.common.api.kaoqin.KaoqinApi;
import com.banban.app.common.bean.SignQuery;

/**
 * Created by Vim 2019/4/17 0017 上午 1:26
 *
 * @author Vim
 */
public class Application {
    public static void main(String[] args) {
        String token = "cedfbfb7b339e85388e98e665b9f7413";
        Integer userId = 121100;
        String orgId = "0b446420687fff4201689d831ca90013";
        String companyId = "0b446420687fff4201689d831ca90013";
        KaoqinApi kaoqinApi = new KaoqinApi();
        kaoqinApi.locationInfo(token, userId, orgId, companyId);


        SignQuery signQuery = new SignQuery();
        signQuery.setCompanyId(companyId);
        signQuery.setRuleId(1523);
        signQuery.setLat(31.2639531678F);
        signQuery.setLng(121.5224280139F);
        signQuery.setSignType(1);
        signQuery.setUserId(userId);

        kaoqinApi.sign(token, orgId, signQuery);
    }
}
