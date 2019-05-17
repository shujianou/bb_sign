package com.banban.app.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vim 2019/4/17 17:02
 *
 * @author Vim
 */
@Data
public class CompanyUserInfo implements Serializable {

    private UserInfoOut userInfoOut;

    private CompanyInfoOut companyInfoOut;
}
