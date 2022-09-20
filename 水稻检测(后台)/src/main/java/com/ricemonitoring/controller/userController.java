package com.ricemonitoring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 9:44
 */
@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class userController {


    @Autowired
    UserService userService;
    /*
    用户注册
    param
        user
     */
    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody person_register person){
        return userService.register(person);
    }
    /*
    用户登录
    param
        user
     */
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody person_login person){
        return userService.login(person);
    }

    /*
    用户修改账户密码
     */
    @PostMapping("/changePwd")
    public Map<String,Object> changePwd(@RequestBody person_login person){
        return userService.changePwd(person);
    }

    /*
    短信验证码登录
    */
    @PostMapping("/phonelogin")
    public Map<String, Object> phonelogin(@RequestParam("phone") String phone){
        return userService.phonelogin(phone);
    }
    @PostMapping("/pLogin")
    public Map<String, Object> pLogin(@RequestBody phone_login pLogin){
        return userService.Loginp(pLogin);
    }
    /*
    查出所有的人员信息供超级管理员审核
     */
    @PostMapping("/getAllPerson")
    public List<person_register> getAllPerson(){
        return userService.getAllPerson();
    }

    /*
    审批人员的信息
    param：
        person //人员信息
        shenpiValue //审批意见
     */
    @PostMapping("/shenpi")
    public Map<String,Object> shenpi(@RequestParam("person")String person1,
                                     @RequestParam("shenpiValue")String shenpiValue) throws Exception {
        person_register person = JSONObject.parseObject(person1, person_register.class);
        Map<String, Object> map = userService.shenpi(person, shenpiValue);
        return map;
    }

    /*
    根据一级标签获取二级标签
    param:
        cropId
     */
    @PostMapping("/getSubCropList")
    public List<sys_code> getSubCropList(@RequestParam("cropId")String cropId){
        List<sys_code> subCropList = null;
        try {
            subCropList = userService.getSubCropList(cropId);
        } catch (Exception e) {
            //System.out.println("后台出错！");
        }
        return subCropList;
    }
    /*
    人员账号分配
    param:
        area
        checkedPoints
     */
    @PostMapping("/accountAllocation")
    public Map<String,Object> accountAllocation(@RequestParam("area")String area,
                                                @RequestParam("crop")String crop,
                                                @RequestParam("people")String people){

        List<person_people> person_people = JSON.parseArray(people, person_people.class);
        //new 一个map
        Map <String,Object> map = new HashMap<>();
        //new一个person_people对象，并将值赋值进去
        person_people person = new person_people();
        person.setArea(area);
        person.setCrop(crop);
        String name = "";
        String phone = "";
        //将联系人信息转化为字符串
        for (int i =0;i<person_people.size();i++) {
            if (person_people.size()>1){
                if (i==person_people.size()-1){
                    name+= person_people.get(i).getName();
                    phone += person_people.get(i).getPhone();
                    continue;
                }
                name+= person_people.get(i).getName()+"/";
                phone += person_people.get(i).getPhone()+"/";
            }else{
                name+= person_people.get(i).getName();
                phone += person_people.get(i).getPhone();
            }

        }
        person.setPhone(phone);
        person.setName(name);
//        try {
            map = userService.accountAllocation(person);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return map;
    }

    /*
    通过作物类型查询苗情监测员的账号
    param:
        crop
     */
    @PostMapping("/getAccounts")
    public List<person_people> getAccounts(String crop){
        return userService.getAccount(crop);
    }

    /*
    获取到所限的县以及对应的作物信息
     */
    @GetMapping("/getAllAreaCode")
    public List<sum_select_list> getallAreaCode(){
        return userService.getallAreaCode();
    }

    /*
    账户弃用功能
    param:
        account
     */
    @PostMapping("/accountDeprecation")
    public void accountDeprecation(@RequestParam("account")String account){
        userService.accountDeprecation(account);
    }

    /*
    监测点分配
     */
    @PostMapping("/addMonitoringPoint")
    public void addMonitoringPoint(@RequestBody monitoring_point monitoringPoint){
        userService.addMonitoringPoint(monitoringPoint);
    }



    /*
    通过作物类型查出所有的监测点信息
     */
    @PostMapping("/getMonitoringPoint")
    public List<monitoring_point> getMonitoringPoint(@RequestParam("crop") String crop){
        //System.out.println(crop);
        List<monitoring_point> lists = userService.getMonitoringPoint(crop);
        return lists;
    }


}
