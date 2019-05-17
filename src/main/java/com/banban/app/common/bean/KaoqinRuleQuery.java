package com.banban.app.common.bean;

import java.io.Serializable;

/**
 * Created by Irany 2019/5/17 9:31
 */
public class KaoqinRuleQuery implements Serializable {
    private String companyId;

    private Integer userId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
