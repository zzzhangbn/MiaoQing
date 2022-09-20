package com.ricemonitoring.controller;


import com.ricemonitoring.pojo.person_login;
import com.ricemonitoring.service.accountManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

@RestController
//解决跨域问题
@CrossOrigin
public class AccountManageController {

    @Autowired
    accountManageService accountManageService;

    // 获取绑定信息
    @PostMapping("/accountChange")
    Map<String,Object> getAccount(@RequestParam("name") String name){
        return accountManageService.getAccount(name);
    }

    // 快速登录绑定账号
    @PostMapping("/loginQuick")
    Map<String, Object> loginQuick(@RequestBody person_login person){
        return accountManageService.loginQuick(person);
    }

    // 添加绑定账号
    @PostMapping("/loginIn")
    Map<String, Object> loginIn(@RequestParam("name") String name,
                                @RequestBody person_login person){
        return accountManageService.loginIn(name,person);
    }

    // 解除绑定
    @PostMapping("/deleteAccount")
    Map<String, Object> deleteAccount(@RequestParam("name") String name,
                                      @RequestParam("account") String account){
        return accountManageService.deleteAccount(name,account);
    }
}
