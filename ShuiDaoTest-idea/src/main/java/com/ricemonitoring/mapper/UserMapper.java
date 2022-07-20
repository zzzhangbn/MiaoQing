package com.ricemonitoring.mapper;

import com.ricemonitoring.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 10:30
 */
@Mapper
@Repository
public interface UserMapper {
    //用户注册功能
    void register(person_register person);

    /*
    用户的登录功能
     */
    //1.查看是否存在此账号
    person_login isExist(person_login person);
    //2.查看账户密码是否正确
    person_login login(person_login person);

    //查出所有的人员信息
    List<person_register> getAllPerson();

    //人员注册表审核状态的变化为已审核
    void changeAudit(person_register person);
    //将人员信息添加到人员信息表
    void addToPerson_people(person_people person);
    //将人员信息添加到人员登陆表
    void addToPerson_login(person_people person);
    List<sys_code> getSubCropList(String cropId);

    //通过作物类型获取所有的苗情监测员账号
    List<person_people> getAccount(String crop);
    //账户弃用-更新人员表的状态
    void accountDeprecation1(String account);
    //账户弃用-更新登录表的状态
    void accountDeprecation2(String account);

    //根据code_name 查出code
    String getCode(String codeName);

    int getMonitoringPointNum(String area);

    void addMonitoringPoint(monitoring_point monitoringPoint);

    void generateRecord(@Param("tableId") String tableId, @Param("area") String area, @Param("code") String code);

    List<monitoring_point> getMonitoringPoint(String crop);

    sum_select_list getAreaCodeByArea(String area);

    List<sum_select_list> getAllAreaCode();

    List<person_login>  AccountExist(@Param("area") String area, @Param("crop") String crop);

    void updatePwd(person_login person);
}

