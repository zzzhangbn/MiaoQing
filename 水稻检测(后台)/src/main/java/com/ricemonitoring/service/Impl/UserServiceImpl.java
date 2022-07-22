package com.ricemonitoring.service.Impl;

import com.ricemonitoring.mapper.UserMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.UserService;
import com.ricemonitoring.util.MD5Utils;
import com.ricemonitoring.util.email;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 10:29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    email email;
    /*
    用户注册功能
    param:
        person_register person
     */
    @Override
    public Map<String,Object> register(person_register person) {
        Map<String,Object> map = new HashMap<>();
        //获取当前时间并转化为标准的格式
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = formatter.format(new Date());
        person.setApply_date(date);
        //将audit属性设置为待审批
        person.setAudit("1");
        //执行注册功能
        try {
            userMapper.register(person);
            map.put("token",200);
            map.put("msg","注册成功，请等待管理员审核！");
            //发送邮件通知超级管理员审核
        } catch (Exception e) {
            map.put("token",500);
            map.put("msg","注册时发生错误，请重新注册");
        }
        return map;
    }
    /*
    用户的登录功能
    param:
        person
     */
    @Override
    public Map<String,Object> login(person_login person) {
        Map<String,Object> map = new HashMap<>();
        //判断用户账号是否存在
        person_login person_login = userMapper.isExist(person);
        person_login fault = userMapper.getFault(person);
        if (person_login==null){
            //账户不存在
            map.put("token",404);
            map.put("msg","你输入的账户不存在，请重新输入！");
            return map;
        }else if (fault != null){
            //账户存在
            person.setPassword(MD5Utils.inputPassToFormPass(person.getPassword()));
            person_login person1 = userMapper.login(person);
            if (person1!=null){
                //账户密码正确
                map.put("token",200);
                map.put("person",person1);
                map.put("msg","登录成功");
                userMapper.faultEmpty(person);
            }else {
                    //账户密码不正确次数小于10
                    map.put("token",500);
                    map.put("msg","你输入的账户密码错误，请重新输入");
                    userMapper.faultAdd(person);
            }
            return map;
        }else {
            map.put("token",501);
            map.put("msg","账号错误次数过多，已封号。请联系管理员解封");
            return map;
        }
    }
    /*
    查出所有的人员信息
     */
    @Override
    public List<person_register> getAllPerson() {
        return userMapper.getAllPerson();
    }
    /*
    人员信息审核
     */
    @Override
    public Map<String, Object> shenpi(person_register person, String shenpiValue) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (shenpiValue.equals("2")){
            String account = "2021";
            person.setAudit(shenpiValue);
            //同意审核，将人员信息添加到用户表person_people和登录表person_login
            //人员信息表
            person_people person_people = new person_people();
            BeanUtils.copyProperties(person,person_people);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(new Date());
            person_people.setAccount(account);
            person_people.setUse_date(date);
            person_people.setIn_use(1);
            //登陆表
            person_login person_login = new person_login();
            BeanUtils.copyProperties(person,person_login);
            person_login.setAccount(account);
            person_login.setIn_use(1);

            //调用mapper完成数据库操作
            userMapper.addToPerson_people(person_people);
            userMapper.addToPerson_login(person_people);
            userMapper.changeAudit(person);
            map.put("msg","账户审核通过!");
        }else{
            //审核不通过，邮件通知用户重新注册
            map.put("msg","账户审核不通过！");
        }
        return map;
    }
    /*
    根据cropId查寻二级作物分类
     */
    @Override
    public List<sys_code> getSubCropList(String cropId) {
        return userMapper.getSubCropList(cropId);
    }
    /*
    苗情监测员账号分配
     */
    @Override
    public Map<String,Object> accountAllocation(person_people person) {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");

        //判断地区账号是否存在
        List<person_people> accounts = userMapper.getAccount(person.getCrop());
        boolean flag = false;
        for (person_people account : accounts) {
            if (person.getArea().equals(account.getArea())){
                flag = true;
            }
        }
        if (flag==true){
            map.put("token","500");
            map.put("msg","你选择的地区已存在账号，请重新操作");
            return map;
        }
        sum_select_list areaCode = userMapper.getAreaCodeByArea(person.getArea());
        //县的代码代号
        String AreaCode = userMapper.getCode(person.getArea());
        person.setArea(AreaCode);
        //1.生成一个账号
        String code = person.getCrop().substring(5,7);
        person.setAccount(areaCode.getArea_code()+sdf2.format(new Date())+code);
        //2.根据地区查出作物类型
        //作物类型为水稻时，判断二级作物类型
        if(person.getCrop().equals("crop_01")){
            switch (areaCode.getRice_type()){
                case "one":
                    person.setSub_crop("crop_01_01");
                    break;
                case "double":
                    person.setSub_crop("crop_01_02");
                    break;
            }
        }
        //获取当前时间
        person.setUse_date(sdf.format(new Date()));
        //设置人员类型为苗情监测员
        person.setAccess("2");
        //设置初始密码
        String password = "123456";
        person.setPassword(MD5Utils.inputPassToFormPass(password));
        person.setIn_use(1);
        //2.人员信息输入到数据库中
        userMapper.addToPerson_login(person);
        userMapper.addToPerson_people(person);
        map.put("token","200");
        map.put("msg","账户分配成功");
        return map;
    }
    /*
    获取某一作物类型下的苗情监测员的账号
    param:
        crop
     */
    @Override
    public List<person_people> getAccount(String crop) {
        List<person_people> accounts = userMapper.getAccount(crop);
        return accounts;
    }
    /*
    账户弃用
     */
    @Override
    public void accountDeprecation(String account) {
        userMapper.accountDeprecation1(account);
        userMapper.accountDeprecation2(account);
    }
    /*
    检测点分配：
    param:
        addMonitoringPoint
     */
    @Override
    public void addMonitoringPoint(monitoring_point monitoringPoint) {
        //1.根据地区查出该地区的监测点数量
        int num = userMapper.getMonitoringPointNum(monitoringPoint.getArea());
        String code =  "";
        //2.生成监测点编码
        if (num>0&&num<10){
            //监测点数量在1-9之间
            code = monitoringPoint.getArea()+"_00"+(num+1);
        }else if(num==0){
            //监测点数量为0
            code = monitoringPoint.getArea()+"_00"+(num+1);
        }else{
            //监测点数量大于10
            code = monitoringPoint.getArea()+"_00"+(num+1);
        }
        //3.监测点状态设置为正常
        monitoringPoint.setCode(code);
        monitoringPoint.setIn_use(1);

        //4.监测点数据插入到监测点表中
        userMapper.addMonitoringPoint(monitoringPoint);
        //5.在带有检测地块的数据表中产生记录
        //所需要新建数据记录的数据表格名
        String[] tables = {"crop_01_01_table_04_01","crop_01_01_table_04_02","crop_01_01_table_04_03","crop_01_01_table_04_04",
                "crop_01_01_table_05_01","crop_01_01_table_06_01","crop_01_01_table_06_02","crop_01_01_table_06_03",
                "crop_01_01_table_06_04","crop_01_01_table_06_05","crop_01_01_table_09_07", "crop_01_01_table_10_01"};
        for (String tableId : tables) {
//            userMapper.generateRecord(tableId,monitoringPoint.getArea(),monitoringPoint.getCode());
        }

    }
    /*
    根据作物类型获取监测点数据
     */
    @Override
    public List<monitoring_point> getMonitoringPoint(@RequestParam("crop") String crop) {
        return userMapper.getMonitoringPoint(crop);
    }

    @Override
    public List<sum_select_list> getallAreaCode() {
        return userMapper.getAllAreaCode();
    }
    /*
    用户账户密码修改
     */
    @Override
    public Map<String, Object> changePwd(person_login person) {
        Map<String, Object> map = new HashMap<>();
        //判断原有账户密码是否正确
        String pwd = MD5Utils.inputPassToFormPass(person.getPassword());
        person.setPassword(pwd);
        person_login exist = userMapper.isExist(person);
        if (exist==null){
            map.put("status","404");
            map.put("msg","用户名或密码有误");
        }else{
            //用户原有密码正确，更新密码
            person.setNewPassword(MD5Utils.inputPassToFormPass(person.getNewPassword()));
            userMapper.updatePwd(person);
            map.put("status","200");
            map.put("msg","密码修改成功");
        }
        return map;
    }
}
