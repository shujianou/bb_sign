package com.banban.app.common.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.Serializable;

/**
 * Created by Vim 2019/4/17 16:42
 *
 * @author Vim
 */
public class BaseApi implements Serializable {


    protected static HttpHeaders HEADERS = new HttpHeaders();

    static {
        HEADERS.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        HEADERS.add("Accept", MediaType.APPLICATION_JSON.toString());
    }

}
