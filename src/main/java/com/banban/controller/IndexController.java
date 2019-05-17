package com.banban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vim 2019/4/17 18:13
 *
 * @author Vim
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping
    public String index() {
        return "index";
    }
}
