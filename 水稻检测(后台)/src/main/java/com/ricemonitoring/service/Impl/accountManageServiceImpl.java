package com.ricemonitoring.service.Impl;


import com.ricemonitoring.mapper.AccountManageMapper;
import com.ricemonitoring.mapper.UserMapper;
import com.ricemonitoring.pojo.account_manage;
import com.ricemonitoring.pojo.person_login;
import com.ricemonitoring.service.accountManageService;
import com.ricemonitoring.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class accountManageServiceImpl implements accountManageService {

    @Autowired
    AccountManageMapper accountManageMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Object> getAccount(String name) {
        Map<String,Object> map = new HashMap<>();
        List<account_manage> account = accountManageMapper.getAccount(name);
        map.put("account", account);
        map.put("token", 200);
        return map;
    }

    @Override
    public Map<String, Object> loginQuick(person_login person) {
        person_login login = accountManageMapper.loginQuick(person);
        Map<String, Object> map = new HashMap<>();
        map.put("person",login);
        return map;
    }

    @Override
    public Map<String, Object> loginIn(String name, person_login person) {
        Map<String, Object> map = new HashMap<>();
        account_manage manage = new account_manage();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(new Date());
        manage.setName(name);
        manage.setAccount(person.getAccount());
        manage.setTime(time);
        person_login exist = userMapper.isExist(person);
        account_manage exist1 = accountManageMapper.isExist(manage);
        if (exist1 != null){
            map.put("token", 401);
            map.put("msg", "你输入的账户已存在");
            return map;
        }
        if (exist == null){
            //账号不存在
            map.put("token", 404);
            map.put("msg", "你输入的账户不存在，请重新输入！");
        }else {
            person.setPassword(MD5Utils.inputPassToFormPass(person.getPassword()));
            person_login isAccountTure = userMapper.login(person);
            if (isAccountTure != null) {
                //账户密码正确
                map.put("token", 200);
                map.put("person", isAccountTure);
                map.put("msg", "绑定成功");
                accountManageMapper.accountAdd(manage);
            } else {
                //账户密码不正确次数小于10
                map.put("token", 500);
                map.put("msg", "你输入的账户密码错误，请重新输入");
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAccount(String name, String account) {
        account_manage manage = new account_manage();
        manage.setName(name);
        manage.setAccount(account);
        accountManageMapper.deleteAccount(manage);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg","解绑成功");
        return map;
    }
}
