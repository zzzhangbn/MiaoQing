package com.ricemonitoring.mapper;


import com.ricemonitoring.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface researchReportMonitorMapper {

    void upload(@Param("fileName") String fileName, @Param("crop") String crop,
                @Param("date") String date, @Param("url") String url, @Param("userId") String userId);

    List<research_report> getFiles(@Param("crop") String crop, @Param("year") String year,@Param("people")String people);

    crop_01_01_table_01_01 getStubbleArea(@Param("year") String year, @Param("country") String country);

    crop_01_01_table_01_02 getCateData(@Param("year") String year,
                                       @Param("fields") List<String> fields);

    List<String> getTotalArea(@Param("year") String year, @Param("country") String country);

    String table_3_1(@Param("year") String year, @Param("country") String country);

    List<crop_01_01_table_03_01> table_3_11(@Param("year") String year, @Param("country") String country);

    crop_01_01_table_04_01 table_4_1(@Param("cropCate") String cropCate,
                                     @Param("tableName") String tableName,
                                     @Param("year") String year,
                                     @Param("country") String country);

    void saveReport(Map<String, Object> map);

    List<crop_01_01_table_07_01> table_7_1(@Param("year") String year, @Param("area1") String area1);

    List<crop_01_01_table_04_01> table_4_1_2(@Param("year") String year, @Param("area1") String area1);

    List<crop_01_01_table_04_02> table_4_2(@Param("year") String year, @Param("area1") String area1);

    List<crop_01_01_table_03_02> table_3_2(@Param("year") String year, @Param("area1") String area1);

    List<crop_01_01_table_07_02> table_7_2(@Param("year") String year, @Param("area1") String area1);

    crop_01_01_table_07_03 table_7_3(@Param("year") String year, @Param("area1") String area1);

    String getArea(String country);

//    检测县双季稻早晚稻

    crop_01_02_table_07_02 tableTwo_7_2(@Param("year") String year, @Param("area1") String area1);

    crop_01_02_table_07_03 tableTwo_7_3(@Param("year") String year, @Param("area1") String area1);

//    省双季稻早晚稻
    crop_01_02_table_07_02 tableTwoZao_7_2(@Param("year") String year);

    crop_01_02_table_07_03 tableTwoWan_7_3(@Param("year") String year);

    crop_01_02_table_07_03 tableTwoWanDao_7_3(@Param("year") String year,@Param("people") String people);


}
