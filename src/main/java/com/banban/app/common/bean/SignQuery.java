package com.banban.app.common.bean;

import java.util.Date;

/**
 * Created by Vim 2019/4/17 0017 上午 2:07
 *
 * @author Vim
 */
public class SignQuery {

    private String companyId;

    private Float lat;

    private Float lng;

    private Date macRecordTime;

    private Object picList;

    private Integer ruleId;

    private String signOutAddress;

    private String signOutDesc;

    private Integer signType;

    private Integer userId;

    public static final Integer 签到 = 1;

    public static final Integer 签退 = 2;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Date getMacRecordTime() {
        return macRecordTime;
    }

    public void setMacRecordTime(Date macRecordTime) {
        this.macRecordTime = macRecordTime;
    }

    public Object getPicList() {
        return picList;
    }

    public void setPicList(Object picList) {
        this.picList = picList;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getSignOutAddress() {
        return signOutAddress;
    }

    public void setSignOutAddress(String signOutAddress) {
        this.signOutAddress = signOutAddress;
    }

    public String getSignOutDesc() {
        return signOutDesc;
    }

    public void setSignOutDesc(String signOutDesc) {
        this.signOutDesc = signOutDesc;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
