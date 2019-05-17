package com.banban.controller;

import com.alibaba.fastjson.JSONObject;
import com.banban.app.common.api.audit.AuditApi;
import com.banban.app.common.bean.AuditListQuery;
import com.banban.bean.AuditInfo;
import com.banban.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Vim 2019/4/18 12:26
 *
 * @author Vim
 */
@RequestMapping("audit")
@RestController
public class AuditController {


    /**
     * 获取考勤数据
     */
    @PostMapping("data")
    public Object data(String token, String companyId, Integer userId) {
        Map<String, Object> data = new HashMap<>();

        AtomicReference<Long> breakTime = new AtomicReference<>(0L);
        AtomicReference<Long> overTime = new AtomicReference<>(0L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        AuditListQuery query = new AuditListQuery();
        query.setCompanyId(companyId);
        query.setUserId(userId);
        query.setTypeId(AuditApi.Type.我发起的);
        Map<String, Object> bodyMap = (Map<String, Object>) auditApi.list(token, companyId, query, 0, 1000).getBody();

        String status = (String) bodyMap.get("status");

        if (StringUtils.equals("0000", status)) {
            Map<String, Object> infoMap = (Map<String, Object>) bodyMap.get("data");
            List<AuditInfo> auditInfos = JSONObject.parseArray(JSONObject.toJSONString(infoMap.get("info")), AuditInfo.class);

            CountDownLatch countDownLatch = new CountDownLatch(auditInfos.size());
            for (AuditInfo auditInfo : auditInfos) {
                Future<?> future = threadPoolTaskExecutor.submit(() -> {
                    try {
                        if (AuditApi.FormType.我的调休.equals(auditInfo.getAuditFormId())) {
                            breakTime.set(breakTime.get() + dateFormat.parse(auditInfo.getNotControlContent()).getTime() - dateFormat.parse(auditInfo.getControlContent()).getTime());
                        } else if (AuditApi.FormType.我的加班.equals(auditInfo.getAuditFormId())) {
                            overTime.set(overTime.get() + (dateFormat.parse(auditInfo.getNotControlContent()).getTime() - dateFormat.parse(auditInfo.getControlContent()).getTime()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                });

                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            data.put("realBreakDay", msToDay(breakTime.get()));
            data.put("realOverDay", msToDay(overTime.get()));
        }
        data.put("status", status);
        data.put("message", bodyMap.get("message"));
        return data;
    }


    private String msToDay(long ms) {
        long day = ms / DD;
        long hour = (ms - day * DD) / HH;

        String strDay = day < 10 ? "0" + day : "" + day;
        String strHour = hour < 10 ? "0" + hour : "" + hour;

        return strDay + "天" + strHour + "时";
    }

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private AuditApi auditApi;


    private static final int SS = 1000;
    private static final int MI = SS * 60;
    private static final int HH = MI * 60;
    private static final int DD = HH * 24;
}
