package com.ricemonitoring.service;

import com.ricemonitoring.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 10:29
 */

public interface UserService {
    //用户注册功能
    Map<String,Object> register(person_register person);
    //用户的登录功能
    Map<String,Object> login(person_login person);
    //用户短信验证码登录
    Map<String,Object> phonelogin(String phone);

    Map<String,Object> Loginp(phone_login pLogin);
    //查出所有的人员信息
    List<person_register> getAllPerson();
    //人员信息的审核
    Map<String,Object> shenpi(person_register person, String shenpiValue) throws Exception;
    //根据cropId查询子作物分类
    List<sys_code> getSubCropList(String cropId);
    //人员账号的分配功能
    Map<String,Object> accountAllocation(person_people person);
    //获取分配分配的苗情监测员的账号
    List<person_people> getAccount(String crop);

    void accountDeprecation(String account);

    void addMonitoringPoint(monitoring_point monitoringPoint);

    List<monitoring_point> getMonitoringPoint(String crop);

    List<sum_select_list> getallAreaCode();

    Map<String, Object> changePwd(person_login person);
}
