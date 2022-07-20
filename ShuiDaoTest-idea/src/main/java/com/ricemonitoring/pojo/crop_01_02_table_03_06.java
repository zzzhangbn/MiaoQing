package com.ricemonitoring.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class crop_01_02_table_03_06 {
    /**
     * 主键id
     */
    private int id;

    /**
     * 县（市、区）
     */
    private String field_1;

    /**
     * 地区类型
     */
    private String field_2;

    /**
     * 稻作类型
     */
    private String field_3;

    /**
     * 全县总面积（万亩）
     */
    private String field_4;

    /**
     * 进程1（大田直播后20天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_5_1;

    /**
     * 进程1（大田直播后20天）-一类苗（万亩）-数据1
     */
    private String field_5_2_1;

    /**
     * 进程1（大田直播后20天）-一类苗（万亩）-占比
     */
    private String field_5_2_2;

    /**
     * 进程1（大田直播后20天）-二类苗（万亩）-数据1
     */
    private String field_5_3_1;

    /**
     * 进程1（大田直播后20天）-二类苗（万亩）-占比
     */
    private String field_5_3_2;

    /**
     * 进程1（大田直播后20天）-三类苗（万亩）-数据1
     */
    private String field_5_4_1;

    /**
     * 进程1（大田直播后20天）-三类苗（万亩）-占比
     */
    private String field_5_4_2;

    /**
     * 进程2（大田直播后28天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_6_1;

    /**
     * 进程2（大田直播后28天）-一类苗（万亩）-数据1
     */
    private String field_6_2_1;

    /**
     * 进程2（大田直播后28天）-一类苗（万亩）-占比
     */
    private String field_6_2_2;

    /**
     * 进程2（大田直播后28天）-二类苗（万亩）-数据1
     */
    private String field_6_3_1;

    /**
     * 进程2（大田直播后28天）-二类苗（万亩）-占比
     */
    private String field_6_3_2;

    /**
     * 进程2（大田直播后28天）-三类苗（万亩）-数据1
     */
    private String field_6_4_1;

    /**
     * 进程2（大田直播后28天）-三类苗（万亩）-占比
     */
    private String field_6_4_2;

    /**
     * 进程3（直播后35天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_7_1;

    /**
     * 进程3（直播后35天）-一类苗（万亩）-数据1
     */
    private String field_7_2_1;

    /**
     * 进程3（直播后35天）-一类苗（万亩）-占比
     */
    private String field_7_2_2;

    /**
     * 进程3（直播后35天）-二类苗（万亩）-数据1
     */
    private String field_7_3_1;

    /**
     * 进程3（直播后35天）-二类苗（万亩）-占比
     */
    private String field_7_3_2;

    /**
     * 进程3（直播后35天）-三类苗（万亩）-数据1
     */
    private String field_7_4_1;

    /**
     * 进程3（直播后35天）-三类苗（万亩）-占比
     */
    private String field_7_4_2;

    /**
     * 进程4（大田直播后42天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_8_1;

    /**
     * 进程4（大田直播后42天）-一类苗（万亩）-数据1
     */
    private String field_8_2_1;

    /**
     * 进程4（大田直播后42天）-一类苗（万亩）-占比
     */
    private String field_8_2_2;

    /**
     * 进程4（大田直播后42天）-二类苗（万亩）-数据1
     */
    private String field_8_3_1;

    /**
     * 进程4（大田直播后42天）-二类苗（万亩）-占比
     */
    private String field_8_3_2;

    /**
     * 进程4（大田直播后42天）-三类苗（万亩）-数据1
     */
    private String field_8_4_1;

    /**
     * 进程4（大田直播后42天）-三类苗（万亩）-占比
     */
    private String field_8_4_2;

    /**
     * 进程5（大田直播后49天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_9_1;

    /**
     * 进程5（大田直播后49天）-一类苗（万亩）-数据1
     */
    private String field_9_2_1;

    /**
     * 进程5（大田直播后49天）-一类苗（万亩）-占比
     */
    private String field_9_2_2;

    /**
     * 进程5（大田直播后49天）-二类苗（万亩）-数据1
     */
    private String field_9_3_1;

    /**
     * 进程5（大田直播后49天）-二类苗（万亩）-占比
     */
    private String field_9_3_2;

    /**
     * 进程5（大田直播后49天）-三类苗（万亩）-数据1
     */
    private String field_9_4_1;

    /**
     * 进程5（大田直播后49天）-三类苗（万亩）-占比
     */
    private String field_9_4_2;

    /**
     * 进程6（大田直播后56天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_10_1;

    /**
     * 进程6（大田直播后56天）-一类苗（万亩）-数据1
     */
    private String field_10_2_1;

    /**
     * 进程6（大田直播后56天）-一类苗（万亩）-占比
     */
    private String field_10_2_2;

    /**
     * 进程6（大田直播后56天）-二类苗（万亩）-数据1
     */
    private String field_10_3_1;

    /**
     * 进程6（大田直播后56天）-二类苗（万亩）-占比
     */
    private String field_10_3_2;

    /**
     * 进程6（大田直播后56天）-三类苗（万亩）-数据1
     */
    private String field_10_4_1;

    /**
     * 进程6（大田直播后56天）-三类苗（万亩）-占比
     */
    private String field_10_4_2;

    /**
     * 进程7（大田直播后66天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_11_1;

    /**
     * 进程7（大田直播后66天）-一类苗（万亩）-数据1
     */
    private String field_11_2_1;

    /**
     * 进程7（大田直播后66天）-一类苗（万亩）-占比
     */
    private String field_11_2_2;

    /**
     * 进程7（大田直播后66天）-二类苗（万亩）-数据1
     */
    private String field_11_3_1;

    /**
     * 进程7（大田直播后66天）-二类苗（万亩）-占比
     */
    private String field_11_3_2;

    /**
     * 进程7（大田直播后66天）-三类苗（万亩）-数据1
     */
    private String field_11_4_1;

    /**
     * 进程7（大田直播后66天）-三类苗（万亩）-占比
     */
    private String field_11_4_2;

    /**
     * 进程8（大田直播后76天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_12_1;

    /**
     * 进程8（大田直播后76天）-一类苗（万亩）-数据1
     */
    private String field_12_2_1;

    /**
     * 进程8（大田直播后76天）-一类苗（万亩）-占比
     */
    private String field_12_2_2;

    /**
     * 进程8（大田直播后76天）-二类苗（万亩）-数据1
     */
    private String field_12_3_1;

    /**
     * 进程8（大田直播后76天）-二类苗（万亩）-占比
     */
    private String field_12_3_2;

    /**
     * 进程8（大田直播后76天）-三类苗（万亩）-数据1
     */
    private String field_12_4_1;

    /**
     * 进程8（大田直播后76天）-三类苗（万亩）-占比
     */
    private String field_12_4_2;

    /**
     * 进程9（大田直播后86天）-日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date field_13_1;

    /**
     * 进程9（大田直播后86天）-一类苗（万亩）-数据1
     */
    private String field_13_2_1;

    /**
     * 进程9（大田直播后86天）-一类苗（万亩）-占比
     */
    private String field_13_2_2;

    /**
     * 进程9（大田直播后86天）-二类苗（万亩）-数据1
     */
    private String field_13_3_1;

    /**
     * 进程9（大田直播后86天）-二类苗（万亩）-占比
     */
    private String field_13_3_2;

    /**
     * 进程9（大田直播后86天）-三类苗（万亩）-数据1
     */
    private String field_13_4_1;

    /**
     * 进程9（大田直播后86天）-三类苗（万亩）-占比
     */
    private String field_13_4_2;

    /**
     * 修改时间
     */
    private String modification_time;

    /**
     * 修改人
     */
    private String people;

    /**
     * 状态
     */
    private String in_use;
}
