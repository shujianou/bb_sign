package com.banban.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vim 2019/4/18 12:30
 *
 * @author Vim
 */
@Data
public class AuditInfo implements Serializable {
    private String userIcon;
    private String auditName;
    private String auditTime;
    private Integer auditFormId;
    private Integer applyId;
    private String applyNo;
    private String notControlName;
    private String notControlContent;
    private String controlName;
    private String controlContent;
    private String controlName2;
    private String controlContent2;
    private Date startAuditTime;
    private Date endAuditTime;
    private Integer auditStatusWeb;
    private String auditStatus;
    private String notifyType;
    private Integer copyMeNum;
    private Integer waitMeAuditNum;
    private Integer totalNum;
    private String score;
    private String finalAuditPer;
    private String nikeName;
}
