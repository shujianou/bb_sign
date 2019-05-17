//
// Decompiled by Jadx - 766ms
//
package com.banban.app.common.d;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.banban.app.common.bean.CommonParams;
import com.banban.app.common.bean.UserLoginInfo;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: SignUtils */
public class SignUtils {

    public static String a(Map<String, Object> map, String str) {
        Map treeMap = new TreeMap<>();
        for (String str2 : map.keySet()) {
            treeMap.put(str2, JSONObject.toJSONString(map.get(str2)));
        }
        return b(treeMap, str);
    }

    public static String b(Map<String, String> map, String str) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String a = a(treeMap);
        if (StringUtils.isNotEmpty(str)) {
            a = a + "&key=" + str;
        }
        return new String(j.a(a(a.replace("\\", "\\\\")))).toUpperCase();
    }

    public static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : map.keySet()) {
            if (!q.a((CharSequence) map.get(str))) {
                stringBuffer.append("&").append(str).append("=").append((String) map.get(str));
            }
        }
        return stringBuffer.substring(1);
    }

    public static byte[] a(String str) {
        return a(c(str));
    }

    private static byte[] c(String str) {
        return q.a(str);
    }

    public static byte[] a(byte[] bArr) {
        return a().digest(bArr);
    }

    private static MessageDigest a() {
        return b("MD5");
    }

    static MessageDigest b(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getSignRequest(CommonParams commonParams,Object object) {
        Map<String, Object> requestData = new HashMap<>();

        CommonParams params = commonParams;

        if (params == null) {
            params = new CommonParams();
            params.setAppVersion("1.0.1");

            //获取用户TOKEN
            params.setToken("");
            params.setOrgId("");
            params.setChannel(null);
            params.setLongOrgId(1L);
            params.setPage(0);
            params.setOriginatorId(null);
            params.setPagesize(5);
            params.setAppKey(APP_KEY);
        }

        requestData.put("commonParam", params);
        requestData.put("object", object);


        String sign = SignUtils.a(requestData, SIGN_TOKEN);
        params.setSign(sign);
        requestData.put("commonParam", params);

        return JSONObject.toJSONString(requestData, SerializerFeature.WriteMapNullValue);
    }

    public static final String APP_KEY = "bbl40c2n1uq5zcvcvz";
    public static final String SIGN_TOKEN = "8e6c0b64cd2bc372bda41d76aca49318";


    public static void main(String[] args) {
        Map<String, Object> requestData = new HashMap<>();

        CommonParams params = new CommonParams();

        params.setAppVersion("1.0.1");

        //获取用户TOKEN
        params.setToken("");
        params.setOrgId("");
        params.setChannel(null);
        params.setLongOrgId(1L);
        params.setPage(0);
        params.setOriginatorId(null);
        params.setPagesize(5);
        params.setAppKey(APP_KEY);


        UserLoginInfo loginInfo = new UserLoginInfo();
        loginInfo.setChannelId("190e35f7e00e3848c47");
        loginInfo.setChannelType("2");
        loginInfo.setCode("qwe54399");
        loginInfo.setCountryNumber("86");
        loginInfo.setNum(0);
        loginInfo.setPhone("17621335544");
        loginInfo.setState("1");
        loginInfo.setType(0);

        requestData.put("commonParam", params);
        requestData.put("object", loginInfo);


        String sign = SignUtils.a(requestData, SIGN_TOKEN);
        params.setSign(sign);
        requestData.put("commonParam", params);

        System.out.println(JSONObject.toJSONString(requestData));
        System.out.println("APP签名:" + sign);
    }
}
