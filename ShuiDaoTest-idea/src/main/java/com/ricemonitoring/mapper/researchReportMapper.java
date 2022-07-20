package com.ricemonitoring.mapper;

import com.ricemonitoring.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:33
 */
@Repository
@Mapper
public interface researchReportMapper {
    void upload(@Param("fileName") String fileName, @Param("crop") String crop,
                @Param("date") String date, @Param("url") String url, @Param("userId") String userId);

    List<research_report> getFiles(@Param("crop") String crop,
                                   @Param("year") String year,
                                   @Param("account") String account);
    String getaccount(@Param("code_name") String code_name);
    String getaccount2(@Param("area") String area);

    crop_01_01_table_01_01 getStubbleArea(String year);

    crop_01_01_table_01_02 getCateData(@Param("year") String year,
                                       @Param("fields") List<String> fields);

    List<String> getTotalArea(String year);

    String table_3_1(String year);

    crop_01_01_table_04_01 table_4_1(@Param("cropCate") String cropCate,
                                     @Param("tableName") String tableName,
                                     @Param("year") String year);

    void saveReport(Map<String, Object> map);

    List<crop_01_01_table_07_01> table_7_1();

    List<crop_01_01_table_04_01> table_4_1_2();

    List<crop_01_01_table_04_02> table_4_2();

    List<crop_01_01_table_03_02> table_3_2();

    List<crop_01_01_table_07_02> table_7_2();

    crop_01_01_table_07_03 table_7_3(int year);
    //########################双季稻早稻#####################################
    crop_01_02_table_03_01 table_double_0301(@Param("account") String account,
                                             @Param("year") String year);

    crop_01_02_table_01_01 table_double_1_1(@Param("account") String account,
                                            @Param("year") String year,
                                            @Param("sql") String sql);

    crop_01_02_table_04_01 table_double_0401(@Param("account") String account,
                                             @Param("year") String year,
                                             @Param("cultivationMethod") String cultivationMethod,
                                             @Param("riceType") String riceType);

    crop_01_02_table_04_02 table_double_0402(@Param("account") String account,
                                             @Param("year") String year,
                                             @Param("riceType") String riceType);

    crop_01_02_table_03_03 table_double_0303(@Param("account") String account,
                                             @Param("year") String year,
                                             @Param("sql") String sql);

    crop_01_02_table_07_01 table_double_0701(@Param("account") String account,
                                             @Param("year") String year,
                                             @Param("sql") String sql);

    crop_01_02_table_03_04 table_double_0304(@Param("account") String account,
                                             @Param("year") String year,
                                             @Param("sql") String sql);

    Double table_double_0702(@Param("account") String account,
                             @Param("year") Integer year);
}
