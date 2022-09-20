package com.ricemonitoring.service;

import com.ricemonitoring.pojo.person_login;

import java.util.Map;

public interface accountManageService {

    //获取账号绑定信息
    Map<String,Object> getAccount(String name);

    //绑定账号快速登录
    Map<String, Object> loginQuick(person_login person);

    //添加绑定账号
    Map<String, Object> loginIn(String name,person_login person);

    //账号解绑
    Map<String, Object> deleteAccount(String name, String account);
}
