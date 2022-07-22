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
    public class crop_01_02_table_03_01 {

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
     * 播种日期-育秧
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_5_1;

      /**
     * 播种日期-直播
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_5_2;

      /**
     * 秧田面积（万亩）
     */
      private String field_6;

      /**
     * 其中集中育秧-数据1
     */
      private String field_7_1;

      /**
     * 其中集中育秧-占比
     */
      private String field_7_2;

      /**
     * 折合大田面积（万亩）-数据1
     */
      private String field_8_1;

      /**
     * 折合大田面积（万亩）-占比
     */
      private String field_8_2;

      /**
     * 其中集中育秧-数据1
     */
      private String field_9_1;

      /**
     * 其中集中育秧-占比
     */
      private String field_9_2;

      /**
     * 播栽期进度1（3月20日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_10_1;

      /**
     * 播栽期进度1（3月20日）-育秧已播种面积（万亩）-数据1
     */
      private String field_10_2_1;

      /**
     * 播栽期进度1（3月20日）-育秧已播种面积（万亩）-占比
     */
      private String field_10_2_2;

      /**
     * 播栽期进度1（3月20日）-已栽插面积（万亩）
     */
      private String field_10_3;

      /**
     * 播栽期进度1（3月20日）-直播已播种面积（万亩）
     */
      private String field_10_4;

      /**
     * 播栽期进度1（3月20日）-小计
     */
      private String field_10_5;

      /**
     * 播栽期进度1（3月20日）-占比
     */
      private String field_10_6;

      /**
     * 播栽期进度2（3月31日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_11_1;

      /**
     * 播栽期进度2（3月31日）-育秧已播种面积（万亩）-数据1
     */
      private String field_11_2_1;

      /**
     * 播栽期进度2（3月31日）-育秧已播种面积（万亩）-占比
     */
      private String field_11_2_2;

      /**
     * 播栽期进度2（3月31日）-已栽插面积（万亩）
     */
      private String field_11_3;

      /**
     * 播栽期进度2（3月31日）-直播已播种面积（万亩）
     */
      private String field_11_4;

      /**
     * 播栽期进度2（3月31日）-小计
     */
      private String field_11_5;

      /**
     * 播栽期进度2（3月31日）-占比
     */
      private String field_11_6;

      /**
     * 播栽期进度3（4月15日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_12_1;

      /**
     * 播栽期进度3（4月15日）-育秧已播种面积（万亩）-数据1
     */
      private String field_12_2_1;

      /**
     * 播栽期进度3（4月15日）-育秧已播种面积（万亩）-占比
     */
      private String field_12_2_2;

      /**
     * 播栽期进度3（4月15日）-已栽插面积（万亩）
     */
      private String field_12_3;

      /**
     * 播栽期进度3（4月15日）-直播已播种面积（万亩）
     */
      private String field_12_4;

      /**
     * 播栽期进度3（4月15日）-小计
     */
      private String field_12_5;

      /**
     * 播栽期进度3（4月15日）-占比
     */
      private String field_12_6;

      /**
     * 播栽期进度4（4月25日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_13_1;

      /**
     * 播栽期进度4（4月25日）-已栽插面积（万亩）
     */
      private String field_13_2;

      /**
     * 播栽期进度4（4月25日）-直播已播种面积（万亩）
     */
      private String field_13_3;

      /**
     * 播栽期进度4（4月25日）-小计
     */
      private String field_13_4;

      /**
     * 播栽期进度4（4月25日）-占比
     */
      private String field_13_5;

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
