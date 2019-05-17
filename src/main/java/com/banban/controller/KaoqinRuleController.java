package com.banban.controller;

import com.banban.app.common.api.kaoqin.RuleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考勤规则
 * Created by Irany 2019/5/17 9:52
 */
@RequestMapping("rule")
@RestController
public class KaoqinRuleController {

    @PostMapping("info")
    public Object info(Integer userId, String companyId, String token) {
        return ruleApi.ruleInfo(userId, companyId, token).getBody();
    }

    @Autowired
    private RuleApi ruleApi;
}
