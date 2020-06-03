package com.turing.demo.docker.controller;

import org.jboss.logging.Param;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@PropertySource("classpath:mysql.properties")
public class HelloController {
    @Value("${env}")
    private String env;
    @Value("${msg}")
    private String msg;
    @Value("${mysql.hostname}")
    private String hostname;
    @Value("${mysql.port}")
    private String port;

    @RequestMapping("/hello")
    @ResponseBody
    String sayHello(@RequestParam("name") String name) {
        return "Version 2: Hello, "  + name  + "!";
    }

    @RequestMapping("/config")
    @ResponseBody
    public Map<String, Object> getConfig() {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("env", env);
            map.put("msg", msg);
            map.put("mysql_host", hostname);
            map.put("mysql_port", port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
 }
