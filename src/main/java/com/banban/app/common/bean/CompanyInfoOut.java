package com.banban.app.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vim 2019/4/17 17:00
 *
 * @author Vim
 */
@Data
public class CompanyInfoOut implements Serializable {

    private String companyId;
    private String companyCodeId;
    private String companyName;
    private Integer companyLevel;
    private String companyAddress;
    private String companyMail;
    private String companyEmail;
    private String companyPhone;
    private String companyBusiness;
    private String companyIntroduction;
    private Integer companyStatus;
    private Long picId;
    private Date createTime;
    private String photoUrl;
    private String photoDesc;
}
