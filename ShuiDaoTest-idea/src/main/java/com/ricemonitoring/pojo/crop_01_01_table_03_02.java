package com.ricemonitoring.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 表3-2  2021年度监测县(市、区)水稻苗情评价动态列表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_01_table_03_02{
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
     * 全县总面积（万亩）
     */
      private String field_3;

      /**
     * 进程1（移栽后7天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_4_1;

      /**
     * 进程1（移栽后7天）-一类苗（万亩）-数据1
     */
      private String field_4_2_1;

      /**
     * 进程1（移栽后7天）-一类苗（万亩）-占比
     */
      private String field_4_2_2;

      /**
     * 进程1（移栽后7天）-二类苗（万亩）-数据1
     */
      private String field_4_3_1;

      /**
     * 进程1（移栽后7天）-二类苗（万亩）-占比
     */
      private String field_4_3_2;

      /**
     * 进程1（移栽后7天）-三类苗（万亩）-数据1
     */
      private String field_4_4_1;

      /**
     * 进程1（移栽后7天）-三类苗（万亩）-占比
     */
      private String field_4_4_2;

      /**
     * 进程2（移栽后14天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_5_1;

      /**
     * 进程2（移栽后14天）-一类苗（万亩）-数据1
     */
      private String field_5_2_1;

      /**
     * 进程2（移栽后14天）-一类苗（万亩）-占比
     */
      private String field_5_2_2;

      /**
     * 进程2（移栽后14天）-二类苗（万亩）-数据1
     */
      private String field_5_3_1;

      /**
     * 进程2（移栽后14天）-二类苗（万亩）-占比
     */
      private String field_5_3_2;

      /**
     * 进程2（移栽后14天）-三类苗（万亩）-数据1
     */
      private String field_5_4_1;

      /**
     * 进程2（移栽后14天）-三类苗（万亩）-占比
     */
      private String field_5_4_2;

      /**
     * 进程3（移栽后21天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_6_1;

      /**
     * 进程3（移栽后21天）-一类苗（万亩）-数据1
     */
      private String field_6_2_1;

      /**
     * 进程3（移栽后21天）-一类苗（万亩）-占比
     */
      private String field_6_2_2;

      /**
     * 进程3（移栽后21天）-二类苗（万亩）-数据1
     */
      private String field_6_3_1;

      /**
     * 进程3（移栽后21天）-二类苗（万亩）-占比
     */
      private String field_6_3_2;

      /**
     * 进程3（移栽后21天）-三类苗（万亩）-数据1
     */
      private String field_6_4_1;

      /**
     * 进程3（移栽后21天）-三类苗（万亩）-占比
     */
      private String field_6_4_2;

      /**
     * 进程4（移栽后28天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_7_1;

      /**
     * 进程4（移栽后28天）-一类苗（万亩）-数据1
     */
      private String field_7_2_1;

      /**
     * 进程4（移栽后28天）-一类苗（万亩）-占比
     */
      private String field_7_2_2;

      /**
     * 进程4（移栽后28天）-二类苗（万亩）-数据1
     */
      private String field_7_3_1;

      /**
     * 进程4（移栽后28天）-二类苗（万亩）-占比
     */
      private String field_7_3_2;

      /**
     * 进程4（移栽后28天）-三类苗（万亩）-数据1
     */
      private String field_7_4_1;

      /**
     * 进程4（移栽后28天）-三类苗（万亩）-占比
     */
      private String field_7_4_2;

      /**
     * 进程5（移栽后35天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_8_1;

      /**
     * 进程5（移栽后35天）-一类苗（万亩）-数据1
     */
      private String field_8_2_1;

      /**
     * 进程5（移栽后35天）-一类苗（万亩）-占比
     */
      private String field_8_2_2;

      /**
     * 进程5（移栽后35天）-二类苗（万亩）-数据1
     */
      private String field_8_3_1;

      /**
     * 进程5（移栽后35天）-二类苗（万亩）-占比
     */
      private String field_8_3_2;

      /**
     * 进程5（移栽后35天）-三类苗（万亩）-数据1
     */
      private String field_8_4_1;

      /**
     * 进程5（移栽后35天）-三类苗（万亩）-占比
     */
      private String field_8_4_2;

      /**
     * 进程6（移栽后42天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_9_1;

      /**
     * 进程6（移栽后42天）-一类苗（万亩）-数据1
     */
      private String field_9_2_1;

      /**
     * 进程6（移栽后42天）-一类苗（万亩）-占比
     */
      private String field_9_2_2;

      /**
     * 进程6（移栽后42天）-二类苗（万亩）-数据1
     */
      private String field_9_3_1;

      /**
     * 进程6（移栽后42天）-二类苗（万亩）-占比
     */
      private String field_9_3_2;

      /**
     * 进程6（移栽后42天）-三类苗（万亩）-数据1
     */
      private String field_9_4_1;

      /**
     * 进程6（移栽后42天）-三类苗（万亩）-占比
     */
      private String field_9_4_2;

      /**
     * 进程7（移栽后52天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_10_1;

      /**
     * 进程7（移栽后52天）-一类苗（万亩）-数据1
     */
      private String field_10_2_1;

      /**
     * 进程7（移栽后52天）-一类苗（万亩）-占比
     */
      private String field_10_2_2;

      /**
     * 进程7（移栽后52天）-二类苗（万亩）-数据1
     */
      private String field_10_3_1;

      /**
     * 进程7（移栽后52天）-二类苗（万亩）-占比
     */
      private String field_10_3_2;

      /**
     * 进程7（移栽后52天）-三类苗（万亩）-数据1
     */
      private String field_10_4_1;

      /**
     * 进程7（移栽后52天）-三类苗（万亩）-占比
     */
      private String field_10_4_2;

      /**
     * 进程8（移栽后62天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_11_1;

      /**
     * 进程8（移栽后62天）-一类苗（万亩）-数据1
     */
      private String field_11_2_1;

      /**
     * 进程8（移栽后62天）-一类苗（万亩）-占比
     */
      private String field_11_2_2;

      /**
     * 进程8（移栽后62天）-二类苗（万亩）-数据1
     */
      private String field_11_3_1;

      /**
     * 进程8（移栽后62天）-二类苗（万亩）-占比
     */
      private String field_11_3_2;

      /**
     * 进程8（移栽后62天）-三类苗（万亩）-数据1
     */
      private String field_11_4_1;

      /**
     * 进程8（移栽后62天）-三类苗（万亩）-占比
     */
      private String field_11_4_2;

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
