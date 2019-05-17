//
// Decompiled by Jadx - 643ms
//
package com.banban.app.common.bean;

public class CommonParams {
    private String appKey;
    private String appVersion = "1.0.1";
    private Integer channel;
    private Long longOrgId;
    private String orgId;
    private Long originatorId;
    private Integer page;
    private Integer pagesize;
    private String sign;
    private String token;

    private String language;

    private Integer propertyId;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getChannel() {
        return this.channel;
    }

    public void setChannel(Integer num) {
        this.channel = num;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String str) {
        this.orgId = str;
    }

    public Long getOriginatorId() {
        return this.originatorId;
    }

    public void setOriginatorId(Long l) {
        this.originatorId = l;
    }

    public String toString() {
        return "CommonParams [appVersion=" + this.appVersion + ", orgId=" + this.orgId + ", originatorId=" + this.originatorId + "]";
    }

    public Long getLongOrgId() {
        return this.longOrgId;
    }

    public void setLongOrgId(Long l) {
        this.longOrgId = l;
    }

    public Integer getPage() {
        return this.page;
    }

    public CommonParams setPage(Integer num) {
        this.page = num;
        return this;
    }

    public Integer getPagesize() {
        return this.pagesize;
    }

    public CommonParams setPagesize(Integer num) {
        this.pagesize = num;
        return this;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
