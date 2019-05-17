package com.banban.app.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Irany 2019/5/17 9:46
 */
@Data
public class KaoqinRuleInfo implements Serializable {

    private Integer lookSignPermit;

    private List<RuleOutInfo> listSecOfcSignRuleOutParam;

    @Data
    public class RuleOutInfo{
        private Integer elasticTime;
        private Integer elasticType;
        private String ruleName;
        private String signEndTime;
        private Integer signRange;
        private List<SignLocation> signRuleOutList;
        private String signStartTime;
    }

    @Data
    public class SignLocation{
        private Float lat;

        private Float lng;

        private String signAddress;
    }
}
