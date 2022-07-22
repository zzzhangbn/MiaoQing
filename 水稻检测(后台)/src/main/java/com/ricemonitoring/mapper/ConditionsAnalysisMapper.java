package com.ricemonitoring.mapper;

import com.ricemonitoring.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:44
 */
@Mapper
@Repository
public interface ConditionsAnalysisMapper {
    //通过表名查询其id
    String getIdByTableName(String tableName);

    //通过id查出该表中农情分析需要展示的字段
    List<conditions_analysis> getFileds(String tableId);

    String getDataFileds(@Param("tableId") String tableId, @Param("dataColumn") String dataColumn);

    String getData(@Param("tableId") String tableId, @Param("field") String field, @Param("city") String city);
    //通过表的id和字段名查出字段id
    String getField(@Param(("tableId")) String tableId, @Param("fieldName") String fieldName);

    List<crop_01_01_table_01_02> crop_01_01_table_01_02Analysis_1(@Param("area") String area,
                                                                  @Param("year") String year);
    //通过水稻品种查出该品种的数据
    List<crop_01_01_table_01_02> crop_01_01_table_01_02Analysis_2(@Param("dataField") String dataField, @Param("nameField") String nameField,
                                                                  @Param("variety") String variety, @Param("year") String year);
    //  通过县市查出表3-2中的数据
    crop_01_01_table_03_02 crop_01_01_table_03_02Analysis_1(@Param("area") String area,
                                                            @Param("areaCate") String areaCate,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_01_table_04_01 crop_01_01_table_04_01Analysis_1(@Param("areaCate") String areaCate,
                                                            @Param("riceCate") String riceCate,
                                                            @Param("cultivationMethod") String cultivationMethod,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_01_table_04_01 crop_01_01_table_04_01Analysis_2(@Param("area") String area,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_01_table_04_03 crop_01_01_table_04_03Analysis_1(@Param("areaCate") String areaCate,
                                                            @Param("strainCategory") String strainCategory,
                                                            @Param("cultivationMethod") String cultivationMethod,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_01_table_04_03 crop_01_01_table_04_03Analysis_2(@Param("areaCate") String areaCate,
                                                            @Param("year") String year);

    crop_01_01_table_07_01 crop_01_01_table_07_01Analysis_1(@Param("cate") String cate, @Param("year") String year);

    crop_01_01_table_07_02 crop_01_01_table_07_02Analysis_1(@Param("cate") String cate,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_01_table_07_03 crop_01_01_table_07_03Analysis_1(@Param("cate") String cate, @Param("year") String year);

    crop_01_01_table_10_01 crop_01_01_table_10_01Analysis_1(String monitoring_code,String year);
    crop_01_01_table_10_01 crop_01_01_table_10_01Analysis_2(@Param("areaCate") String areaCate,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);


    List<String> getMonitoringCode(String area, @Param("year") String year);

    List<crop_01_01_table_01_02> crop_01_02_table_01_02Analysis_1(@Param("area") String area,
                                                                  @Param("year") String year);

    crop_01_02_table_03_04 crop_01_02_table_03_06Analysis_1(@Param("areaCate") String areaCate,
                                                            @Param("area") String area,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_02_table_04_01 crop_01_02_table_04_01Analysis_1(@Param("areaCate") String areaCate,
                                                            @Param("riceCate") String riceCate,
                                                            @Param("cultivationMethod") String cultivationMethod,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_02_table_04_03 crop_01_02_table_04_03Analysis_1(@Param("areaCate") String areaCate,
                                                            @Param("strainCategory") String strainCategory,
                                                            @Param("cultivationMethod") String cultivationMethod,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    crop_01_02_table_10_01 crop_01_02_table_10_01Analysis_1(@Param("monitoring_code") String monitoring_code,
                                                            @Param("year") String year);

    crop_01_02_table_10_01 crop_01_02_table_10_01Analysis_2(@Param("areaCate") String areaCate,
                                                            @Param("sqlFields") List<String> sqlFields,
                                                            @Param("year") String year);

    List<String> getMonitoringCode_2(String area,String year);

    List<crop_01_01_table_01_02> getTotalArea_2_1(String year);

//    研究报告
    List<crop_01_01_table_01_02> getTotalArea_2_1(@Param("year")String year,@Param("country")String country);

}
