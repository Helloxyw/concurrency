package com.imooc.xyw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IDEA
 * author:RicardoXu
 * Date:2019/3/26
 * Time:22:50
 */
@Controller
@Slf4j
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
