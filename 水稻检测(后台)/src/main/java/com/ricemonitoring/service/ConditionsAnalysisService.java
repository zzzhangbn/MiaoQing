package com.ricemonitoring.service;

import com.ricemonitoring.pojo.conditions_analysis;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:50
 */
public interface ConditionsAnalysisService {
    /*************************************一季稻开始*********************************************/
    //通过表名查出表中农情分析需要展示的字段
    List<conditions_analysis> getFileds(String tableName);

    List<Integer> getData(String tableId, String city, String columns);
    /*
    表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
    crop_01_01_table_01_02Analysis_1：通过控制县名查出各品系的占比
    crop_01_01_table_01_02Analysis_2：通过控制品系查出该品系在个县的占比
     */
    Map<String,Object> crop_01_01_table_01_02Analysis_1(String area,String years) throws Exception;
//    Map<String, Object> crop_01_01_table_01_02Analysis_2(String variety);

    /*
    表3-2 年度监测县(市、区)水稻苗情评价动态列表（移栽稻）
    crop_01_01_table_03_02Analysis_1
     */
    Map<String, Object> crop_01_01_table_03_02Analysis_1(String area, String areaCate,String years) throws Exception;
    /*
    表4-1 年度监测县(市、区)中稻生育进度调查表（移栽稻）
    crop_01_01_table_04_01Analysis_1:不同地区类型下不同进程中的平均叶龄和平均茎蘖数
     */
    Map<String, Object> crop_01_01_table_04_01Analysis_1(String areaCate,String riceCate,String cultivationMethod,String years) throws Exception;

    Map<String, Object> crop_01_01_table_04_01Analysis_2(String area,String year) throws Exception;

    Map<String, Object> crop_01_01_table_04_03Analysis_1(String areaCate, String strainCategory, String cultivationMethod,String years) throws Exception;

    Map<String, Object> crop_01_01_table_04_03Analysis_2(String year);

    Map<String, Object> crop_01_01_table_07_01Analysis_1(String years) throws Exception;

    Map<String, Object> crop_01_01_table_07_02Analysis_1(String years) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> crop_01_01_table_07_03Analysis_1(String years) throws Exception;

    Map<String, Object> crop_01_01_table_10_01Analysis_1(String monitoring_code,String year) throws Exception;

    Map<String, Object> crop_01_01_table_10_01Analysis_2(String areaCate,String years) throws Exception;

    List<String> getMonitoringCode(String crop,String years);

    /*************************************一季稻结束*********************************************/
    /*************************************二季稻开始*********************************************/
    Map<String, Object> crop_01_02_table_01_02Analysis_1(String area,String year) throws Exception;

    Map<String, Object> crop_01_02_table_03_06Analysis_1(String areaCate,String area,String year) throws Exception;

    Map<String, Object> crop_01_02_table_04_01Analysis_1(String areaCate,String riceCate,String cultivationMethod,String year) throws Exception;

    Map<String, Object> crop_01_02_table_04_03Analysis_1(String areaCate, String strainCategory, String cultivationMethod,String year) throws Exception;

    Map<String, Object> crop_01_02_table_10_01Analysis_1(String monitoring_code,String year) throws Exception;

    Map<String, Object> crop_01_02_table_10_01Analysis_2(String areaCate,String year) throws Exception;

    List<String> getMonitoringCode_2(String crop,String year);
}
