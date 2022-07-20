package com.ricemonitoring.controller;

import com.ricemonitoring.mapper.WeatherMapper;
import com.ricemonitoring.pojo.conditions_analysis;
import com.ricemonitoring.service.ConditionsAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:41
 */
@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class ConditionsAnalysisController {
    @Autowired
    ConditionsAnalysisService conditionsAnalysisService;

    /*************************************一季稻开始*********************************************/

    /*
    通过表名查询弄清分析需要展示的字段
     */
    @PostMapping("/getFileds")
    public List<conditions_analysis> getFileds(@RequestParam("tableName") String tableName){
        return conditionsAnalysisService.getFileds(tableName);
    }

    /*
    通过表名和字段名一级地区名查出数据用于显示
     */
    @PostMapping("/getData")
    public List<Integer> getData(@RequestParam("tableId") String tableId,
                                             @RequestParam("city") String city,
                                             @RequestParam("columns")String columns){
        List<Integer> datas = conditionsAnalysisService.getData(tableId,city,columns);
        return datas;
    }
    /*
    表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
    crop_01_01_table_01_02Analysis_1：通过控制县名查出各品系的占比
    crop_01_01_table_01_02Analysis_2：通过控制品系查出该品系在个县的占比
     */
    @PostMapping("/crop_01_01_table_01_02Analysis_1")
    public Map<String, Object> crop_01_01_table_01_02Analysis_1(@RequestParam("area") String area,@RequestParam("year") String year) throws Exception {
//        System.out.println(year);
        Map<String, Object> map = null;
        try {
//            System.out.println(1234);
//            System.out.println(year);
            map = conditionsAnalysisService.crop_01_01_table_01_02Analysis_1(area,year);
            //System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
//    @PostMapping("/crop_01_01_table_01_02Analysis_2")
//    public Map<String, Object> crop_01_01_table_01_02Analysis_2(@RequestParam("variety") String variety){
//        Map<String, Object> map = null;
//        try {
//            map = conditionsAnalysisService.crop_01_01_table_01_02Analysis_2(variety);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }

    /*
    表3-2 年度监测县(市、区)水稻苗情评价动态列表（移栽稻）
    crop_01_01_table_03_02Analysis_1
     */
    @PostMapping("/crop_01_01_table_03_02Analysis_1")
    public Map<String, Object> crop_01_01_table_03_02Analysis_1(@RequestParam("area") String area,
                                                                @RequestParam("areaCate")String areaCate,
                                                                @RequestParam("year")String year) throws Exception {
//        System.out.println(year);
        Map<String, Object> data = null;
        try {
            data = conditionsAnalysisService.crop_01_01_table_03_02Analysis_1(area,areaCate,year);
//            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /*
    表4-1 年度监测县(市、区)中稻生育进度调查表（移栽稻）
    crop_01_01_table_04_01Analysis_1:不同地区类型下不同进程中的平均叶龄和平均茎蘖数
    crop_01_01_table_04_01Analysis_2:
     */
    @PostMapping("/crop_01_01_table_04_01Analysis_1")
    public Map<String, Object> crop_01_01_table_04_01Analysis_1(@RequestParam("areaCate") String areaCate,
                                                                @RequestParam("riceCate") String riceCate,
                                                                @RequestParam("cultivationMethod") String cultivationMethod,
                                                                @RequestParam("year")String year){
        //System.out.println(year);
        Map<String, Object> map = new HashMap<>();
        try {
             map= conditionsAnalysisService.crop_01_01_table_04_01Analysis_1(areaCate,riceCate,cultivationMethod,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/crop_01_01_table_04_01Analysis_2")
    public Map<String, Object> crop_01_01_table_04_01Analysis_2(@RequestParam("area") String area,
                                                                @RequestParam("year")String year){
        Map<String, Object> datas = new HashMap<>();
        try {
            datas = conditionsAnalysisService.crop_01_01_table_04_01Analysis_2(area,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    /*
    表4-3 20xx年度水稻监测点茎蘖数叶面积构成表
    crop_01_01_table_04_03Analysis_1：通过地区类型求得平均值
    crop_01_01_table_04_03Analysis_2:不同地区类型下的分葉数
     */
    @PostMapping("/crop_01_01_table_04_03Analysis_1")
    public Map<String, Object> crop_01_01_table_04_03Analysis_1(@RequestParam("areaCate") String areaCate,
                                                            @RequestParam("strainCategory")String strainCategory,
                                                            @RequestParam("cultivationMethod")String cultivationMethod,
                                                                @RequestParam("year")String year){
//        System.out.println(year);
        Map<String, Object> map = new HashMap<>();
        try {
             map = conditionsAnalysisService.crop_01_01_table_04_03Analysis_1(areaCate, strainCategory, cultivationMethod,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @PostMapping("/crop_01_01_table_04_03Analysis_2")
    public Map<String, Object> crop_01_01_table_04_03Analysis_2(@RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_01_table_04_03Analysis_2(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
    表7-1：年度监测县(市、区)水稻单季稻苗情监测预测产表
    crop_01_01_table_07_01Analysis_1：柱状图展示理论产量(kg/亩)
     */
    @PostMapping("/crop_01_01_table_07_01Analysis_1")
    public Map<String, Object> crop_01_01_table_07_01Analysis_1( @RequestParam("year")String year){
//        System.out.println(year);
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_01_table_07_01Analysis_1(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    表7-2：年度监测县(市、区)水稻单季稻收获进度调查
    crop_01_01_table_07_02Analysis_1：柱状图展示理论产量(kg/亩)
     */
    @PostMapping("/crop_01_01_table_07_02Analysis_1")
    public Map<String, Object> crop_01_01_table_07_02Analysis_1(@RequestParam("year")String year){
//        System.out.println(year);
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_01_table_07_02Analysis_1(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /*
    表7-3：年度监测县(市、区)水稻单季稻测产表
    crop_01_01_table_07_02Analysis_1：柱状图展示三大地区类型下的理论产量(kg/亩)
     */
    @PostMapping("/crop_01_01_table_07_03Analysis_1")
    public Map<String, Object> crop_01_01_table_07_03Analysis_1(@RequestParam("year")String year){
        System.out.println(year);
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_01_table_07_03Analysis_1(year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    表10-1：年度监测县(市、区)水稻单季稻测产表
    getMonitoringCode:通过县查出所有的监测点代码
     */
    @PostMapping("/getMonitoringCode")
    public List<String> getMonitoringCode(@RequestParam("area") String area,@RequestParam("year") String year){
//        System.out.println(1111);
//        System.out.println(conditionsAnalysisService.getMonitoringCode(area,year));
//        System.out.println(1111);
        return conditionsAnalysisService.getMonitoringCode(area,year);
    }

    @PostMapping("/crop_01_01_table_10_01Analysis_1")
    public Map<String, Object> crop_01_01_table_10_01Analysis_1(@RequestParam("monitoring_code")String monitoring_code,
                                                                @RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_01_table_10_01Analysis_1(monitoring_code,year);
//            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @GetMapping("/crop_01_01_table_10_01Analysis_2")
    public Map<String, Object> crop_01_01_table_10_01Analysis_2(String areaCate,@RequestParam("year") String year){
//        System.out.println(year);
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_01_table_10_01Analysis_2(areaCate,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*************************************一季稻结束*********************************************/
    /*************************************二季稻开始*********************************************/
    /*
    表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
     */
    /*
    表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
     */
    @PostMapping("/crop_01_02_table_01_02Analysis_1")
    public Map<String, Object> crop_01_02_table_01_02Analysis_1(@RequestParam("area")String area,@RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_02_table_01_02Analysis_1(area,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    表3-2-1  20xx年度监测县(市、区)水稻苗情评价动态列表（晚稻）
     */
    @PostMapping("/crop_01_02_table_03_06Analysis_1")
    public Map<String, Object> crop_01_02_table_03_06Analysis_1(@RequestParam("areaCate")String areaCate,
                                                                @RequestParam("area")String area,
                                                                @RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_02_table_03_06Analysis_1(areaCate,area,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    表4-1  20xx年度监测县(市、区)双季稻生育进度调查表（移栽稻）
     */
    @PostMapping("/crop_01_02_table_04_01Analysis_1")
    public Map<String, Object> crop_01_02_table_04_01Analysis_1(@RequestParam("areaCate")String areaCate,
                                                                @RequestParam("riceCate")String riceCate,
                                                                @RequestParam("cultivationMethod")String cultivationMethod,
                                                                @RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_02_table_04_01Analysis_1(areaCate,riceCate,cultivationMethod,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    表4-3  20xx年度水稻监测点茎蘖数叶面积构成表
     */
    @PostMapping("/crop_01_02_table_04_03Analysis_1")
    public Map<String, Object> crop_01_02_table_04_03Analysis_1(@RequestParam("areaCate")String areaCate,
                                                                @RequestParam("strainCategory")String strainCategory,
                                                                @RequestParam("cultivationMethod")String cultivationMethod,
                                                                @RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_02_table_04_03Analysis_1(areaCate, strainCategory, cultivationMethod,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /*
    表10-1 20xx年度水稻监测点灌浆速率调查表（早稻）
     */
    @PostMapping("/getMonitoringCode_2")
    public List<String> getMonitoringCode_2(@RequestParam("area") String area,
                                            @RequestParam("year")String year){
        return conditionsAnalysisService.getMonitoringCode_2(area,year);
    }

    @PostMapping("/crop_01_02_table_10_01Analysis_1")
    public Map<String, Object> crop_01_02_table_10_01Analysis_1(@RequestParam("monitoring_code")String monitoring_code,
                                                                @RequestParam("year")String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_02_table_10_01Analysis_1(monitoring_code,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/crop_01_02_table_10_01Analysis_2")
    public Map<String, Object> crop_01_02_table_10_01Analysis_2(String areaCate,String year){
        Map<String, Object> map = new HashMap<>();
        try {
            map = conditionsAnalysisService.crop_01_02_table_10_01Analysis_2(areaCate,year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }







}
