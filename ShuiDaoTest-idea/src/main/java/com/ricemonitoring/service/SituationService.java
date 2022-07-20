package com.ricemonitoring.service;

import com.ricemonitoring.pojo.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SituationService {


    //查询该用户下所有的表1-2数据
    List<crop_01_01_table_01_02> getUserAllData(String people,String column_id,int flag,int currentYear);

    //查询该用户下所有的表1-2数据，品系名对应
    List<crop_01_01_table_01_02> getByPinZhongName(String people,String pin_zhong_name,String column_id,int flag,int currentYear);

    //3-2表数据对比分析
    Map<String, Object> dataComparison_03_02(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //表3-3表数据对比分析
    Map<String, Object> dataComparison_03_03(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //二季稻：表3-3表数据对比分析
    Map<String, Object> dataComparison_two_03_03(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //二季稻：表3-4表数据对比分析
    Map<String, Object> dataComparison_two_03_04(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //二季稻：表3-5表数据对比分析
    Map<String, Object> dataComparison_two_03_05(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //二季稻：表3-6表数据对比分析
    Map<String, Object> dataComparison_two_03_06(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //表4-1表数据对比分析
    Map<String, Object> dataComparison_04_01(String userId, int startYear, int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //二季稻：表4-1表数据对比分析
    Map<String, Object> dataComparison_two_04_01(String userId, int startYear, int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //表4-2表数据对比分析
    Map<String, Object> dataComparison_04_02(String userId, int startYear, int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //二季稻：表4-2表数据对比分析
    Map<String, Object> dataComparison_two_04_02(String userId, int startYear, int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //表10-1表数据对比分析
    Map<String, Object> dataComparison_10_01(String userId,String point, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //表10-1表数据对比分析
    Map<String, Object> dataTwoComparison_10_01(String userId,String point, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    //表10-1表数据对比分析
    Map<String, Object> dataTwoComparison_10_02(String userId,String point, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    //获取品种信息
    List<table_variety> getPinZhongInfo(String varieties);

    //查询该用户下所有的表1-1数据
    List<crop_01_01_table_01_01> getUserAllData01(String people);

//    //判断该用户是否首次登录表1-2
//    List<crop_01_01_table_01_02> judgeUserFirstLoginTable2(String UserID);

    //获取下拉框内容
    List<sum_select_list> getSelectContentList(String fieldName,String riceType);

    //查询该用户所属的县市区和地区类型
//    List<crop_01_01_table_02_01> checkUserBelong(String people);

    //获取监测点代码
    List<monitoring_point> getMonitorCode(String UserID);

    //保存 表1-1  2021年度监测县(市、区)水稻栽插概况调查表 数据
    int getInputDataTable1(List<crop_01_01_table_01_01> data);

    //查询表1-1 下该用户所有的数据数
    List<crop_01_01_table_01_01> checkUserAllKnownData1(String UserID,String column_id,int startYear,int endYear);

    //保存 表1-2  2021年度监测县(市、区)水稻生产主推品种调查表 数据
    int getInputDataTable2(crop_01_01_table_01_02 obj1);

    //保存 表2-1  20xx年度水稻监测点建档卡 数据
    int getInputDataTable3(crop_01_01_table_02_01 obj1);

    //保存 表3-1   数据
    int getInputDataTable5(List<crop_01_01_table_03_01> obj1);

    //保存 表3-2   数据
    int getInputDataTable0302(List<crop_01_01_table_03_02> obj1);

    //保存 表3-3   数据
    int getInputDataTable0303(List<crop_01_01_table_03_03> obj1);

    //保存 表4-1   数据
    int getInputDataTable0401(List<crop_01_01_table_04_01> obj1);

    //保存 表4-2   数据
    int getInputDataTable0402(List<crop_01_01_table_04_02> obj1);

    //保存 表4-3   数据
    int getInputDataTable0403(List<crop_01_01_table_04_03> obj1);

    //保存 表4-4   数据
    int getInputDataTable0404(List<crop_01_01_table_04_04> obj1);

    //保存 表8-1   数据
    int getInputDataTable0801(crop_01_01_table_08_01 obj1);

    //保存监测点建档卡数据至表1-1中
    int insertMonitorInfoToTable0101(String area, String areaCate,String people,String time);

    //更新监测点建档卡数据至表1-1中
    int updateMonitorInfoToTable0101(String area, String areaCate,String people,String time);

    //读取建档卡表2-1内容至表2-2中
    int getMonitorCardContent(String people);

    //读取建档卡内容至表3-2中
    int getMonitorCardContentInto0302(String people);

    //读取建档卡内容至表3-3中
    int getMonitorCardContentInto0303(String people);

    //读取建档卡内容至表3-1中
    int getMonitorCardContentInto0301(String people);

    //读取建档卡内容至表4-1中
    int insertMonitorInfoToTable0401(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //更新建档卡内容至表4-1中
    int updateMonitorInfoToTable0401(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表4-2中
    int insertMonitorInfoToTable0402(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表4-2中
    int updateMonitorInfoToTable0402(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表4-3中
    int insertMonitorInfoToTable0403(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time);

    //更新建档卡内容至表4-3中
    int updateMonitorInfoToTable0403(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time);

    //读取建档卡内容至表4-4中
    int insertMonitorInfoToTable0404(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表4-4中
    int updateMonitorInfoToTable0404(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinXiType,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表5-1中
    int insertMonitorInfoToTable0501(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表5-1中
    int updateMonitorInfoToTable0501(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-1中
    int insertMonitorInfoToTable0601(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-1中
    int updateMonitorInfoToTable0601(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-2中
    int insertMonitorInfoToTable0602(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-2中
    int updateMonitorInfoToTable0602(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-3中
    int insertMonitorInfoToTable0603(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-3中
    int updateMonitorInfoToTable0603(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-4中
    int insertMonitorInfoToTable0604(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-4中
    int updateMonitorInfoToTable0604(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-5中
    int insertMonitorInfoToTable0605(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //读取建档卡内容至表6-5中
    int updateMonitorInfoToTable0605(String area,String MonitorBlock,String areaCate,
                                     String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //查询该用户所有表2-1的数据
    List<crop_01_01_table_02_01> getUserAllData0201(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表2-1的数据
    List<crop_01_02_table_02_01> getUserAllDataTwo0201(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-1的数据
    List<crop_01_01_table_03_01> getUserAllData0301(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-2的数据
    List<crop_01_01_table_03_02> getUserAllData0302(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-3的数据
    List<crop_01_01_table_03_03> getUserAllData0303(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表4-1的数据
    List<crop_01_01_table_04_01> getUserAllData0401(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表4-2的数据
    List<crop_01_01_table_04_02> getUserAllData0402(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表4-3的数据
    List<crop_01_01_table_04_03> getUserAllData0403(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表4-4的数据
    List<crop_01_01_table_04_04> getUserAllData0404(String people,String column_id,String point,int startYear,int endYear);

    //查询该用户所有表8-1的数据
    List<crop_01_01_table_08_01> getUserAllData0801(String people,String point,String column_id,int startYear,int endYear);

    //查询该账号下所有监测点信息
    ArrayList<crop_01_02_table_02_01> getCurrentAccountMonitorInfo(String account);

    //读取该用户下所有表2-2的数据
    List<crop_01_01_table_02_02> getUserAllData0202(String people);

    //保存监测点建档卡数据至表1-2中
    int insertMonitorInfoToTable0102(String area,String people,String time);

    //保存监测点建档卡数据至表3-1中
    int insertMonitorInfoToTable0301(String area,String areaCate,String people,String time);

    //更新监测点建档卡数据至表3-1中
    int updateMonitorInfoToTable0301(String area,String areaCate,String people,String time);

    //保存监测点建档卡数据至表3-2中
    int insertMonitorInfoToTable0302(String area,String areaCate,String people,String time);

    //更新监测点建档卡数据至表3-2中
    int updateMonitorInfoToTable0302(String area,String areaCate,String people,String time);

    //保存监测点建档卡数据至表3-3中
    int insertMonitorInfoToTable0303(String area,String areaCate,String people,String time);

    //更新监测点建档卡数据至表3-3中
    int updateMonitorInfoToTable0303(String area,String areaCate,String people,String time);

    //保存监测点建档卡数据至表7-1中
    int insertMonitorInfoToTable0701(String area,String areaCate,String people,String time);

    //更新监测点建档卡数据至表7-1中
    int updateMonitorInfoToTable0701(String area,String areaCate,String people,String time);

    //保存监测点建档卡数据至表7-2中
    int insertMonitorInfoToTable0702(String area,String areaCate,String people,String time);

    //更新监测点建档卡数据至表7-2中
    int updateMonitorInfoToTable0702(String area,String areaCate,String people,String time);

    //保存监测点建档卡数据至表7-3中
    int insertMonitorInfoToTable0703(String area,String areaCate,String people,String time);

    //更新监测点建档卡数据至表7-3中
    int updateMonitorInfoToTable0703(String area,String areaCate,String people,String time);

    //读取建档卡内容至表10-1中
    int insertMonitorInfoToTable1001(String area,String MonitorBlock,String areaCate,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //更新建档卡内容至表10-1中
    int updateMonitorInfoToTable1001(String area,String MonitorBlock,String areaCate,
                                     String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表8-1中
    int insertMonitorInfoToTable0801(String area,String MonitorBlock,String areaCate,
                                     String zaiZhiType,
                                     String people,String time);

    //更新监测点建档卡数据至表8-1中
    int updateMonitorInfoToTable0801(String area,String MonitorBlock,String areaCate,
                                     String zaiZhiType,
                                     String people,String time);

    //查询 表06-01 获取监测点代码
    List<crop_01_01_table_06_01> getAllData0601(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-02 获取监测点代码
    List<crop_01_01_table_06_02> getAllData0602(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-03 获取监测点代码
    List<crop_01_01_table_06_03> getAllData0603(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-04 获取监测点代码
    List<crop_01_01_table_06_04> getAllData0604(String people,String point,String column_id,int beginYear,int endYear);

    //查询 表06-05 获取监测点代码
    List<crop_01_01_table_06_05> getAllData0605(String people,String point,String column_id,int beginYear,int endYear);

    //查询 表07-01 获取监测点代码
    List<crop_01_01_table_07_01> getAllData0701(String people,String column_id,int beginYear,int endYear);

    //查询 表07-02 获取监测点代码
    List<crop_01_01_table_07_02> getAllData0702(String people,String column_id,int beginYear,int endYear);

    //查询 表07-03 获取监测点代码
    List<crop_01_01_table_07_03> getAllData0703(String people,String column_id,int beginYear,int endYear);

    //查询 表09-01 获取监测点代码
    List<crop_01_01_table_09_01> getAllData0901(String account,String column_id,int beginYear,int endYear);

    //查询 表09-02 获取监测点代码
    List<crop_01_01_table_09_02> getAllData0902(String account,String year,String column_id,int beginYear,int endYear);

    //查询 表09-03 获取监测点代码
    List<crop_01_01_table_09_03> getAllData0903(String account,String column_id,int beginYear,int endYear);

    //查询 表09-04 获取监测点代码
    List<crop_01_01_table_09_04> getAllData0904(String account,String column_id,int beginYear,int endYear);

    //查询 表09-05 获取监测点代码
    List<crop_01_01_table_09_05> getAllData0905(String account,String column_id,int beginYear,int endYear);

    //查询 表09-06 获取监测点代码
    List<crop_01_01_table_09_06> getAllData0906(String account,String column_id,int beginYear,int endYear);

    //查询 表09-07 获取监测点代码
    List<crop_01_01_table_09_07> getAllData0907(String account,String point,String column_id,int beginYear,int endYear);

    //查询 表10-01 获取监测点代码
    List<crop_01_01_table_10_01> getAllData1001(String people,String point,String column_id,int beginYear,int endYear);

    //查询 表02-01 自动获取监测点信息
    List<crop_01_01_table_02_01> getAllData0201(String point);


    //查询监测点
    List<monitoring_point> getMonitoringPoint(String account);

    //查询 表05-01 获取监测点代码
    List<crop_01_01_table_05_01> getAllData0501(String people,String point,String column_id,int startYear,int endYear);

    //更新 表05-01
    int getInputDataTable0501(List<crop_01_01_table_05_01> obj1);

    //保存 表6-1
    int getInputDataTable0601(List<crop_01_01_table_06_01> obj1);

    //保存 表6-2
    int getInputDataTable0602(List<crop_01_01_table_06_02> obj1);

    //保存 表6-3
    int getInputDataTable0603(List<crop_01_01_table_06_03> obj1);

    //保存 表6-4
    int getInputDataTable0604(List<crop_01_01_table_06_04> obj1);

    //保存 表6-5
    int getInputDataTable0605(List<crop_01_01_table_06_05> obj1);


    //保存 表7-1
    int getInputDataTable0701(crop_01_01_table_07_01 obj1);

    //保存 表7-2
    int getInputDataTable0702(crop_01_01_table_07_02 obj1);

    //保存 表7-3
    int getInputDataTable0703(crop_01_01_table_07_03 obj1);

    //保存 表9-1
    int getInputDataTable0901(crop_01_01_table_09_01 obj1);

    //保存 表9-2
    int getInputDataTable0902(crop_01_01_table_09_02 obj1);

    //保存 表9-3
    int getInputDataTable0903(crop_01_01_table_09_03 obj1);

    //保存 表09-07
    int getInputDataTable0907(List<crop_01_01_table_09_07> obj1);

    //保存 表9-4
    int getInputDataTable0904(crop_01_01_table_09_04 obj1);

    //保存 表9-5
    int getInputDataTable0905(crop_01_01_table_09_05 obj1);

    //保存 表9-6
    int getInputDataTable0906(crop_01_01_table_09_06 obj1);


    //保存 表10-1
    int getInputDataTable1001(List<crop_01_01_table_10_01> obj1);

    /***************************************二季稻**********************************/
    /***************************************查询开始**********************************/
    //查询 表02-02-01 自动获取监测点信息
    List<crop_01_02_table_02_01> getAllDataTwo0201(String account,String point);

    //查询 表05-01 获取监测点代码
    List<crop_01_02_table_05_01> getAllDataTwo0501(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-01 获取监测点代码
    List<crop_01_02_table_06_01> getAllDataTwo0601(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-02 获取监测点代码
    List<crop_01_02_table_06_02> getAllDataTwo0602(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-03 获取监测点代码
    List<crop_01_02_table_06_03> getAllDataTwo0603(String people,String point,String column_id,int startYear,int endYear);

    //查询 表06-04 获取监测点代码
    List<crop_01_02_table_06_04> getAllDataTwo0604(String people,String point,String column_id,int beginYear,int endYear);

    //查询 表06-05 获取监测点代码
    List<crop_01_02_table_06_05> getAllDataTwo0605(String people,String point,String column_id,int beginYear,int endYear);

    //查询 表07-01 获取监测点代码
    List<crop_01_02_table_07_01> getAllDataTwo0701(String people,String column_id,int beginYear,int endYear);

    //查询 表07-02 获取监测点代码
    List<crop_01_02_table_07_02> getAllDataTwo0702(String people,String column_id,int beginYear,int endYear);

    //查询 表07-03 获取监测点代码
    List<crop_01_02_table_07_03> getAllDataTwo0703(String people,String column_id,int beginYear,int endYear);

    //查询 表07-04 获取监测点代码
    List<crop_01_02_table_07_04> getAllDataTwo0704(String people,String column_id,int beginYear,int endYear);

    //查询 表09-01 获取监测点代码
    List<crop_01_02_table_09_01> getAllDataTwo0901(String account,String column_id,int beginYear,int endYear);

    //查询 表09-02 获取监测点代码
    List<crop_01_02_table_09_02> getAllDataTwo0902(String account,String year,String column_id,int beginYear,int endYear);

    //查询 表09-03 获取监测点代码
    List<crop_01_02_table_09_03> getAllDataTwo0903(String account,String column_id,int beginYear,int endYear);

    //查询 表09-04 获取监测点代码
    List<crop_01_02_table_09_04> getAllDataTwo0904(String account,String column_id,int beginYear,int endYear);

    //查询 表09-05 获取监测点代码
    List<crop_01_02_table_09_05> getAllDataTwo0905(String account,String column_id,int beginYear,int endYear);

    //查询 表09-06 获取监测点代码
    List<crop_01_02_table_09_06> getAllDataTwo0906(String account,String column_id,int beginYear,int endYear);

    //查询 表09-07 获取监测点代码
    List<crop_01_02_table_09_07> getAllDataTwo0907(String account,String point,String column_id,int beginYear,int endYear);

    //查询 表10-01 获取监测点代码
    List<crop_01_02_table_10_01> getAllDataTwo1001(String people,String point,String column_id,int beginYear,int endYear);

    //查询 表10-02 获取监测点代码
    List<crop_01_02_table_10_02> getAllDataTwo1002(String people,String point,String column_id,int beginYear,int endYear);
    /***************************************查询结束**********************************/
    /***************************************保存开始**********************************/
    //保存 表05-01
    int getInputDataTableTwo0501(List<crop_01_02_table_05_01> obj1);

    //保存 表6-1
    int getInputDataTableTwo0601(List<crop_01_02_table_06_01> obj1);

    //保存 表6-2
    int getInputDataTableTwo0602(List<crop_01_02_table_06_02> obj1);

    //保存 表6-3
    int getInputDataTableTwo0603(List<crop_01_02_table_06_03> obj1);

    //保存 表6-4
    int getInputDataTableTwo0604(List<crop_01_02_table_06_04> obj1);

    //保存 表6-5
    int getInputDataTableTwo0605(List<crop_01_02_table_06_05> obj1);

    //保存 表7-1
    int getInputDataTableTwo0701(crop_01_02_table_07_01 obj1);

    //保存 表7-2
    int getInputDataTableTwo0702(crop_01_02_table_07_02 obj1);

    //保存 表7-3
    int getInputDataTableTwo0703(crop_01_02_table_07_03 obj1);

    //保存 表7-4
    int getInputDataTableTwo0704(crop_01_02_table_07_04 obj1);

    //保存 表9-1
    int getInputDataTableTwo0901(crop_01_02_table_09_01 obj1);

    //保存 表9-2
    int getInputDataTableTwo0902(crop_01_02_table_09_02 obj1);

    //保存 表9-3
    int getInputDataTableTwo0903(crop_01_02_table_09_03 obj1);

    //保存 表9-4
    int getInputDataTableTwo0904(crop_01_02_table_09_04 obj1);

    //保存 表9-5
    int getInputDataTableTwo0905(crop_01_02_table_09_05 obj1);

    //保存 表9-6
    int getInputDataTableTwo0906(crop_01_02_table_09_06 obj1);

    //保存 表09-07
    int getInputDataTableTwo0907(crop_01_02_table_09_07 obj1);

    //保存 表10-1
    int getInputDataTableTwo1001(crop_01_02_table_10_01 obj1);

    //保存 表10-2
    int getInputDataTableTwo1002(crop_01_02_table_10_02 obj1);
    /***************************************保存结束**********************************/
    /********************************监测点开始*************************************/
    //查询本年度监测点
    List<crop_01_02_table_02_01> getAllDataPointTwo0201(String people,String myDate);

    //查询历史年度监测点
    List<crop_01_02_table_02_01> getAllDataHistoryPointTwo0201(String people,String myDate);

    int updateInputDataTable3(crop_01_01_table_02_01 obj1);


    int updateInputDataTableTwo3(crop_01_02_table_02_01 obj1);
    /********************************监测点结束*************************************/
    /***************************************二季稻**********************************/


///////////////////////////////////////////////////////////////////////////////////////////////////////
    //查询该用户下所有的表1-2数据
    List<crop_01_02_table_01_02> getUserAllDataTwo(String people,String column_id,int flag,int currentYear);

    //查询该用户下所有的表1-2数据，品系名对应
    List<crop_01_02_table_01_02> getTwoByPinZhongName(String people,String pin_zhong_name,String column_id,int flag,int currentYear);

    //查询该用户下所有的表1-1数据
    List<crop_01_02_table_01_01> getUserAllDataTwo01(String people);


    //获取监测点代码
    List<monitoring_point> getMonitorCodeTwo(String UserID);

    //保存 表1-1  2021年度监测县(市、区)水稻栽插概况调查表 数据
    int getInputDataTableTwo1(List<crop_01_02_table_01_01> data);

    //查询表1-1 下该用户所有的数据数
    List<crop_01_02_table_01_01> checkUserAllKnownDataTwo1(String UserID,String column_id,int startYear,int endYear);

    //保存 表1-2  2021年度监测县(市、区)水稻生产主推品种调查表 数据
    int getInputDataTableTwo2(crop_01_02_table_01_02 obj1);

    //保存 表2-1  20xx年度水稻监测点建档卡 数据
    int getInputDataTableTwo3(crop_01_02_table_02_01 obj1);

    //保存 表3-1   数据
    int getInputDataTableTwo5(List<crop_01_02_table_03_01> obj1);

    //保存 表3-2   数据
    int getInputDataTableTwo0302(List<crop_01_02_table_03_02> obj1);

    //保存 表3-3   数据
    int getInputDataTableTwo0303(List<crop_01_02_table_03_03> obj1);

    //保存 表3-4   数据
    int getInputDataTableTwo0304(List<crop_01_02_table_03_04> obj1);

    //保存 表3-5   数据
    int getInputDataTableTwo0305(List<crop_01_02_table_03_05> obj1);

    //保存 表3-6   数据
    int getInputDataTableTwo0306(List<crop_01_02_table_03_06> obj1);

    //保存 表4-1   数据
    int getInputDataTableTwo0401(List<crop_01_02_table_04_01> obj1);

    //保存 表4-2   数据
    int getInputDataTableTwo0402(List<crop_01_02_table_04_02> obj1);

    //保存 表4-3   数据
    int getInputDataTableTwo0403(List<crop_01_02_table_04_03> obj1);

    //保存 表4-4   数据
    int getInputDataTableTwo0404(List<crop_01_02_table_04_04> obj1);

    //保存 表8-1   数据
    int getInputDataTableTwo0801(crop_01_02_table_08_01 obj1);

    //保存 表8-2   数据
    int getInputDataTableTwo0802(crop_01_02_table_08_02 obj1);

    //保存监测点建档卡数据至表1-1中
    int insertMonitorInfoToTableTwo0101(String area, String areaCate,String people,String time);

    //保存监测点建档卡数据至表1-1中
    int updateMonitorInfoToTableTwo0101(String area, String areaCate,String people,String time);

    //读取建档卡表2-1内容至表2-2中
    int getMonitorCardContentTwo(String people);

    //读取建档卡内容至表3-1中
    int getMonitorCardContentIntoTwo0301(String people);

    //读取建档卡内容至表3-2中
    int getMonitorCardContentIntoTwo0302(String people);

    //读取建档卡内容至表3-3中
    int getMonitorCardContentIntoTwo0303(String people);
//
//    //读取建档卡内容至表4-1中
//    int insertMonitorInfoToTableTwo0401(String area,String MonitorBlock,String areaCate,
//                                        String daoZuoType,String pinXiType,
//                                        String pinZhongType,String zaiZhiType,String people,String time);
//
//    //读取建档卡内容至表4-2中
//    int insertMonitorInfoToTableTwo0402(String area,String MonitorBlock,String areaCate,
//                                        String daoZuoType,String pinXiType,
//                                        String pinZhongType,String zaiZhiType,String people,String time);
//
//    //读取建档卡内容至表4-3中
//    int insertMonitorInfoToTableTwo0403(String area,String MonitorBlock,String areaCate,
//                                        String daoZuoType,String pinXiType,
//                                        String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time);
//
//    //读取建档卡内容至表4-4中
//    int insertMonitorInfoToTableTwo0404(String area,String MonitorBlock,String areaCate,
//                                        String daoZuoType,String pinXiType,
//                                        String pinZhongType,String zaiZhiType,String people,String time);

    //查询该用户所有表3-1的数据
    List<crop_01_02_table_03_01> getUserAllDataTwo0301(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-2的数据
    List<crop_01_02_table_03_02> getUserAllDataTwo0302(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-3的数据
    List<crop_01_02_table_03_03> getUserAllDataTwo0303(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-4的数据
    List<crop_01_02_table_03_04> getUserAllDataTwo0304(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-5的数据
    List<crop_01_02_table_03_05> getUserAllDataTwo0305(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表3-6的数据
    List<crop_01_02_table_03_06> getUserAllDataTwo0306(String people,String column_id,int startYear,int endYear);

    //查询该用户所有表4-1的数据
    List<crop_01_02_table_04_01> getUserAllDataTwo0401(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表4-2的数据
    List<crop_01_02_table_04_02> getUserAllDataTwo0402(String people,String column_id,String monitor_location,int startYear,int endYear);
    //查询该用户所有表4-2的数据用于各区查看总界面
    List<crop_01_02_table_04_02> getUserAllDataTwo0402new(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表4-3的数据
    List<crop_01_02_table_04_03> getUserAllDataTwo0403(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表4-4的数据
    List<crop_01_02_table_04_04> getUserAllDataTwo0404(String people,String column_id,String monitor_location,int startYear,int endYear);

    //查询该用户所有表8-1的数据
    List<crop_01_02_table_08_01> getUserAllDataTwo0801(String people,String point,String column_id,int startYear,int endYear);

    //查询该用户所有表8-2的数据
    List<crop_01_02_table_08_02> getUserAllDataTwo0802(String people,String point,String column_id,int startYear,int endYear);

    //读取该用户下所有表2-2的数据
    List<crop_01_02_table_02_02> getUserAllDataTwo0202(String people);
//
//    //保存监测点建档卡数据至表1-2中
//    int insertMonitorInfoToTableTwo0102(String area,String people,String time);
//
//    //保存监测点建档卡数据至表3-1中
//    int insertMonitorInfoToTableTwo0301(String area,String areaCate,String daoZuoType,String people,String time);
//
//    //保存监测点建档卡数据至表3-2中
//    int insertMonitorInfoToTableTwo0302(String area,String areaCate,String daoZuoType,String people,String time);
//
//    //保存监测点建档卡数据至表3-3中
//    int insertMonitorInfoToTableTwo0303(String area,String areaCate,String daoZuoType,String people,String time);
//
//    //保存监测点建档卡数据至表3-4中
//    int insertMonitorInfoToTableTwo0304(String area,String areaCate,String daoZuoType,String people,String time);
//
//    //保存监测点建档卡数据至表3-5中
//    int insertMonitorInfoToTableTwo0305(String area,String areaCate,String people,String time);
//
//    //保存监测点建档卡数据至表3-6中
//    int insertMonitorInfoToTableTwo0306(String area,String areaCate,String daoZuoType,String people,String time);
//
//    //保存监测点建档卡数据至表5-1中
//    int insertMonitorInfoToTableTwo0501(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表6-1中
//    int insertMonitorInfoToTableTwo0601(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表6-2中
//    int insertMonitorInfoToTableTwo0602(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表6-3中
//    int insertMonitorInfoToTableTwo0603(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表6-4中
//    int insertMonitorInfoToTableTwo0604(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表6-5中
//    int insertMonitorInfoToTableTwo0605(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表7-1中
//    int insertMonitorInfoToTableTwo0701(String area,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表7-2中
//    int insertMonitorInfoToTableTwo0702(String area,String areaCate,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表7-3中
//    int insertMonitorInfoToTableTwo0703(String area,String areaCate,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表7-4中
//    int insertMonitorInfoToTableTwo0704(String area,String areaCate,String daoZuoType,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表9-3中
//    int insertMonitorInfoToTableTwo0903(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表9-4中
//    int insertMonitorInfoToTableTwo0904(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表9-5中
//    int insertMonitorInfoToTableTwo0905(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表9-6中
//    int insertMonitorInfoToTableTwo0906(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);
//
//    //二季稻 保存监测点建档卡数据至表9-7中
//    int insertMonitorInfoToTableTwo0907(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinXiType,String people,String time);
//
//    //保存监测点建档卡数据至表10-1中
//    int insertMonitorInfoToTableTwo1001(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表10-2中
//    int insertMonitorInfoToTableTwo1002(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);
//
//    //保存监测点建档卡数据至表8-1中
//    int insertMonitorInfoToTableTwo0801(String area,String areaCate,
//                                        String zaiZhiType,
//                                        String people,String time);
//
//    //保存监测点建档卡数据至表8-2中
//    int insertMonitorInfoToTableTwo0802(String area,String areaCate,
//                                        String zaiZhiType,
//                                        String people,String time);

    //保存监测点建档卡数据至表1-2中
    int insertMonitorInfoToTableTwo0102(String area,String people,String time);

    //保存监测点建档卡数据至表3-1中
    int insertMonitorInfoToTableTwo0301(String area,String areaCate,String daoZuoType,String people,String time);

    //修改监测点建档卡数据至表3-1中
    int updateMonitorInfoToTableTwo0301(String area,String areaCate,String daoZuoType,String people,String time);

    //保存监测点建档卡数据至表3-2中
    int insertMonitorInfoToTableTwo0302(String area,String areaCate,String daoZuoType,String people,String time);

    //修改监测点建档卡数据至表3-2中
    int updateMonitorInfoToTableTwo0302(String area,String areaCate,String daoZuoType,String people,String time);

    //保存监测点建档卡数据至表3-3中
    int insertMonitorInfoToTableTwo0303(String area,String areaCate,String daoZuoType,String people,String time);

    //修改监测点建档卡数据至表3-3中
    int updateMonitorInfoToTableTwo0303(String area,String areaCate,String daoZuoType,String people,String time);

    //保存监测点建档卡数据至表3-4中
    int insertMonitorInfoToTableTwo0304(String area,String areaCate,String daoZuoType,String people,String time);

    //修改监测点建档卡数据至表3-4中
    int updateMonitorInfoToTableTwo0304(String area,String areaCate,String daoZuoType,String people,String time);

    //保存监测点建档卡数据至表3-5中
    int insertMonitorInfoToTableTwo0305(String area,String areaCate,String people,String time);

    //修改监测点建档卡数据至表3-5中
    int updateMonitorInfoToTableTwo0305(String area,String areaCate,String people,String time);

    //保存监测点建档卡数据至表3-6中
    int insertMonitorInfoToTableTwo0306(String area,String areaCate,String daoZuoType,String people,String time);

    //修改监测点建档卡数据至表3-6中
    int updateMonitorInfoToTableTwo0306(String area,String areaCate,String daoZuoType,String people,String time);

    //保存建档卡内容至表4-1中
    int insertMonitorInfoToTableTwo0401(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String people,String time);

    //修改建档卡内容至表4-1中
    int updateMonitorInfoToTableTwo0401(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String people,String time);

    //保存建档卡内容至表4-2中
    int insertMonitorInfoToTableTwo0402(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String people,String time);

    //修改建档卡内容至表4-2中
    int updateMonitorInfoToTableTwo0402(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String people,String time);

    //保存建档卡内容至表4-3中
    int insertMonitorInfoToTableTwo0403(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time);

    //修改建档卡内容至表4-3中
    int updateMonitorInfoToTableTwo0403(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time);

    //保存建档卡内容至表4-4中
    int insertMonitorInfoToTableTwo0404(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String people,String time);

    //修改建档卡内容至表4-4中
    int updateMonitorInfoToTableTwo0404(String area,String MonitorBlock,String areaCate,
                                        String daoZuoType,String pinXiType,
                                        String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表5-1中
    int insertMonitorInfoToTableTwo0501(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表5-1中
    int updateMonitorInfoToTableTwo0501(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表6-1中
    int insertMonitorInfoToTableTwo0601(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表6-1中
    int updateMonitorInfoToTableTwo0601(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表6-2中
    int insertMonitorInfoToTableTwo0602(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表6-2中
    int updateMonitorInfoToTableTwo0602(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表6-3中
    int insertMonitorInfoToTableTwo0603(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表6-3中
    int updateMonitorInfoToTableTwo0603(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表6-4中
    int insertMonitorInfoToTableTwo0604(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表6-4中
    int updateMonitorInfoToTableTwo0604(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表6-5中
    int insertMonitorInfoToTableTwo0605(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表6-5中
    int updateMonitorInfoToTableTwo0605(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //二季稻 保存监测点建档卡数据至表7-1中
    int insertMonitorInfoToTableTwo0701(String area,String people,String time);

    //二季稻 保存监测点建档卡数据至表7-2中
    int insertMonitorInfoToTableTwo0702(String area,String areaCate,String people,String time);

    //二季稻 修改监测点建档卡数据至表7-2中
    int updateMonitorInfoToTableTwo0702(String area,String areaCate,String people,String time);

    //二季稻 保存监测点建档卡数据至表7-3中
    int insertMonitorInfoToTableTwo0703(String area,String areaCate,String people,String time);

    //二季稻 修改监测点建档卡数据至表7-3中
    int updateMonitorInfoToTableTwo0703(String area,String areaCate,String people,String time);

    //二季稻 保存监测点建档卡数据至表7-4中
    int insertMonitorInfoToTableTwo0704(String area,String areaCate,String daoZuoType,String people,String time);

    //二季稻 修改监测点建档卡数据至表7-4中
    int updateMonitorInfoToTableTwo0704(String area,String areaCate,String daoZuoType,String people,String time);

    //保存监测点建档卡数据至表8-1中
    int insertMonitorInfoToTableTwo0801(String area,String MonitorBlock,String areaCate,
                                        String zaiZhiType,
                                        String people,String time);

    //修改监测点建档卡数据至表8-1中
    int updateMonitorInfoToTableTwo0801(String area,String MonitorBlock,String areaCate,
                                        String zaiZhiType,
                                        String people,String time);

    //保存监测点建档卡数据至表8-2中
    int insertMonitorInfoToTableTwo0802(String area,String MonitorBlock,String areaCate,
                                        String zaiZhiType,
                                        String people,String time);

    //修改监测点建档卡数据至表8-2中
    int updateMonitorInfoToTableTwo0802(String area,String MonitorBlock,String areaCate,
                                        String zaiZhiType,
                                        String people,String time);

    //二季稻 保存监测点建档卡数据至表9-3中
    int insertMonitorInfoToTableTwo0903(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 修改监测点建档卡数据至表9-3中
    int updateMonitorInfoToTableTwo0903(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 保存监测点建档卡数据至表9-4中
    int insertMonitorInfoToTableTwo0904(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 修改监测点建档卡数据至表9-4中
    int updateMonitorInfoToTableTwo0904(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 保存监测点建档卡数据至表9-5中
    int insertMonitorInfoToTableTwo0905(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 修改监测点建档卡数据至表9-5中
    int updateMonitorInfoToTableTwo0905(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 保存监测点建档卡数据至表9-6中
    int insertMonitorInfoToTableTwo0906(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 修改监测点建档卡数据至表9-6中
    int updateMonitorInfoToTableTwo0906(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 保存监测点建档卡数据至表9-7中
    int insertMonitorInfoToTableTwo0907(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //二季稻 修改监测点建档卡数据至表9-7中
    int updateMonitorInfoToTableTwo0907(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinXiType,String people,String time);

    //保存监测点建档卡数据至表10-1中
    int insertMonitorInfoToTableTwo1001(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表10-1中
    int updateMonitorInfoToTableTwo1001(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //保存监测点建档卡数据至表10-2中
    int insertMonitorInfoToTableTwo1002(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);

    //修改监测点建档卡数据至表10-2中
    int updateMonitorInfoToTableTwo1002(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time);


    //数据退还列表
    List<table_record_back> getDataBackListInfo(String people);

    //修改数据退还状态
    int fixDataBackState(String tableName,String tableId);

    /////////////////////////////////////////////////////////////////
    //查询本年度监测点
    List<crop_01_01_table_02_01> getAllDataPoint0201(String people,String myDate);

    //查询历史年度监测点
    List<crop_01_01_table_02_01> getAllDataHistoryPoint0201(String people,String myDate);

    //查询该用户所在的县
    List<sys_code> getAreaInfo(String people);

    //查询所有品种
    List<table_variety> getAllVarieties();

    List<person_login> getPerson(String people);

}
