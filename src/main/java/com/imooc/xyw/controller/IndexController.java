package com.imooc.xyw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IDEA
 * author:RicardoXu
 * Date:2019/3/22
 * Time:00:19
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/index","/"})
    @ResponseBody
    public String index(){
        return "hello world!";
    }
}
