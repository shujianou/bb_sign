package com.banban.app.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vim 2019/4/17 16:59
 *
 * @author Vim
 */
@Data
public class UserInfoOut implements Serializable {
    private Integer userId;
    private String userLevel;
    private String userPhone;
    private String userPw;
    private String userName;
    private String userIdcard;
    private String userSex;
    private String userAge;
    private String userMail;
    private Integer userStatus;
    private String namePreafix;
    private Date activeTime;
    private String nomalCompanyId;
    private Date createTime;
    private String userSource;
    private Integer findType;
    private String photoUrl;
    private Date startTime;
    private Date endTime;
    private String cardNumber;
    private String companyName;
    private String companyCode;
    private int numStatus;
    private boolean operator;
}
