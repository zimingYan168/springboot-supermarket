package com.springboot.supermarket.controller;/*
 @author yanziming
 @date 2020/4/7 - 15:41
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public Map<String,Object> map(){

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from shopuser");
        return list.get(0);

    }

}
