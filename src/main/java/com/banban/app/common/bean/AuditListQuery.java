package com.banban.app.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Vim 2019/4/18 12:21
 *
 * @author Vim
 */
@Data
public class AuditListQuery implements Serializable {

    private String companyId;

    private Integer userId;

    private Integer typeId;
}
