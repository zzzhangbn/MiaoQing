package com.ricemonitoring.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_03_03 {


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
     * 进程1（移栽后7天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_5_1;

      /**
     * 进程1（移栽后7天）-一类苗（万亩）-数据1
     */
      private String field_5_2_1;

      /**
     * 进程1（移栽后7天）-一类苗（万亩）-占比
     */
      private String field_5_2_2;

      /**
     * 进程1（移栽后7天）-二类苗（万亩）-数据1
     */
      private String field_5_3_1;

      /**
     * 进程1（移栽后7天）-二类苗（万亩）-占比
     */
      private String field_5_3_2;

      /**
     * 进程1（移栽后7天）-三类苗（万亩）-数据1
     */
      private String field_5_4_1;

      /**
     * 进程1（移栽后7天）-三类苗（万亩）-占比
     */
      private String field_5_4_2;

      /**
     * 进程2（移栽后12天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_6_1;

      /**
     * 进程2（移栽后12天）-一类苗（万亩）-数据1
     */
      private String field_6_2_1;

      /**
     * 进程2（移栽后12天）-一类苗（万亩）-占比
     */
      private String field_6_2_2;

      /**
     * 进程2（移栽后12天）-二类苗（万亩）-数据1
     */
      private String field_6_3_1;

      /**
     * 进程2（移栽后12天）-二类苗（万亩）-占比
     */
      private String field_6_3_2;

      /**
     * 进程2（移栽后12天）-三类苗（万亩）-数据1
     */
      private String field_6_4_1;

      /**
     * 进程2（移栽后12天）-三类苗（万亩）-占比
     */
      private String field_6_4_2;

      /**
     * 进程3（移栽后17天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_7_1;

      /**
     * 进程3（移栽后17天）-一类苗（万亩）-数据1
     */
      private String field_7_2_1;

      /**
     * 进程3（移栽后17天）-一类苗（万亩）-占比
     */
      private String field_7_2_2;

      /**
     * 进程3（移栽后17天）-二类苗（万亩）-数据1
     */
      private String field_7_3_1;

      /**
     * 进程3（移栽后17天）-二类苗（万亩）-占比
     */
      private String field_7_3_2;

      /**
     * 进程3（移栽后17天）-三类苗（万亩）-数据1
     */
      private String field_7_4_1;

      /**
     * 进程3（移栽后17天）-三类苗（万亩）-占比
     */
      private String field_7_4_2;

      /**
     * 进程4（移栽后22天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_8_1;

      /**
     * 进程4（移栽后22天）-一类苗（万亩）-数据1
     */
      private String field_8_2_1;

      /**
     * 进程4（移栽后22天）-一类苗（万亩）-占比
     */
      private String field_8_2_2;

      /**
     * 进程4（移栽后22天）-二类苗（万亩）-数据1
     */
      private String field_8_3_1;

      /**
     * 进程4（移栽后22天）-二类苗（万亩）-占比
     */
      private String field_8_3_2;

      /**
     * 进程4（移栽后22天）-三类苗（万亩）-数据1
     */
      private String field_8_4_1;

      /**
     * 进程4（移栽后22天）-三类苗（万亩）-占比
     */
      private String field_8_4_2;

      /**
     * 进程5（移栽后27天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_9_1;

      /**
     * 进程5（移栽后27天）-一类苗（万亩）-数据1
     */
      private String field_9_2_1;

      /**
     * 进程5（移栽后27天）-一类苗（万亩）-占比
     */
      private String field_9_2_2;

      /**
     * 进程5（移栽后27天）-二类苗（万亩）-数据1
     */
      private String field_9_3_1;

      /**
     * 进程5（移栽后27天）-二类苗（万亩）-占比
     */
      private String field_9_3_2;

      /**
     * 进程5（移栽后27天）-三类苗（万亩）-数据1
     */
      private String field_9_4_1;

      /**
     * 进程5（移栽后27天）-三类苗（万亩）-占比
     */
      private String field_9_4_2;

      /**
     * 进程6（移栽后32天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_10_1;

      /**
     * 进程6（移栽后32天）-一类苗（万亩）-数据1
     */
      private String field_10_2_1;

      /**
     * 进程6（移栽后32天）-一类苗（万亩）-占比
     */
      private String field_10_2_2;

      /**
     * 进程6（移栽后32天）-二类苗（万亩）-数据1
     */
      private String field_10_3_1;

      /**
     * 进程6（移栽后32天）-二类苗（万亩）-占比
     */
      private String field_10_3_2;

      /**
     * 进程6（移栽后32天）-三类苗（万亩）-数据1
     */
      private String field_10_4_1;

      /**
     * 进程6（移栽后32天）-三类苗（万亩）-占比
     */
      private String field_10_4_2;

      /**
     * 进程7（移栽后42天）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_11_1;

      /**
     * 进程7（移栽后42天）-一类苗（万亩）-数据1
     */
      private String field_11_2_1;

      /**
     * 进程7（移栽后42天）-一类苗（万亩）-占比
     */
      private String field_11_2_2;

      /**
     * 进程7（移栽后42天）-二类苗（万亩）-数据1
     */
      private String field_11_3_1;

      /**
     * 进程7（移栽后42天）-二类苗（万亩）-占比
     */
      private String field_11_3_2;

      /**
     * 进程7（移栽后42天）-三类苗（万亩）-数据1
     */
      private String field_11_4_1;

      /**
     * 进程7（移栽后42天）-三类苗（万亩）-占比
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
