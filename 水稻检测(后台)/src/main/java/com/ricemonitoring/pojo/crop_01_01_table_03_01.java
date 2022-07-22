package com.ricemonitoring.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 表3-1  2021年度监测县(市、区)水稻播栽进度调查表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_01_table_03_01 {


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
     * 播种日期-育秧
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_4_1;

      /**
     * 播种日期-直播
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private  Date field_4_2;

      /**
     * 秧田面积（万亩）
     */
      private String field_5;

      /**
     * 其中集中育秧-数据1
     */
      private String field_6_1;

      /**
     * 其中集中育秧-占比
     */
      private String field_6_2;

      /**
     * 折合大田面积（万亩）-数据1
     */
      private String field_7_1;

      /**
     * 折合大田面积（万亩）-占比
     */
      private String field_7_2;

      /**
     * 其中集中育秧-数据1
     */
      private String field_8_1;

      /**
     * 其中集中育秧-占比
     */
      private String field_8_2;

      /**
     * 播栽期进度1（5月20日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private  Date field_9_1;

      /**
     * 播载期进度1（5月20日）-育秧已播种面积（万亩）-数据1
     */
      private String field_9_2_1;

      /**
     * 播栽期进度1（5月20日）-育秧已播种面积（万亩）-占比
     */
      private String field_9_2_2;

      /**
     * 播栽期进度1（5月20日）-已栽插面积（万亩）
     */
      private String field_9_3;

      /**
     * 播栽期进度1（5月20日）-直播已播种面积（万亩）
     */
      private String field_9_4;

      /**
     * 播栽期进度1（5月20日）-小计
     */
      private String field_9_5;

      /**
     * 播栽期进度1（5月20日）-占比
     */
      private String field_9_6;

      /**
     * 播栽期进度2（5月30日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private  Date field_10_1;

      /**
     * 播载期进度2（5月30日）-育秧已播种面积（万亩）-数据1
     */
      private String field_10_2_1;

      /**
     * 播栽期进度2（5月30日）-育秧已播种面积（万亩）-占比
     */
      private String field_10_2_2;

      /**
     * 播栽期进度2（5月30日）-已栽插面积（万亩）
     */
      private String field_10_3;

      /**
     * 播栽期进度2（5月30日）-直播已播种面积（万亩）
     */
      private String field_10_4;

      /**
     * 播栽期进度2（5月30日）-小计
     */
      private String field_10_5;

      /**
     * 播栽期进度2（5月30日）-占比
     */
      private String field_10_6;

      /**
     * 播栽期进度3（6月10日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private  Date field_11_1;

      /**
     * 播栽期进度3（6月10日）-育秧已播种面积（万亩）-数据1
     */
      private String field_11_2_1;

      /**
     * 播栽期进度3（6月10日）-育秧已播种面积（万亩）-占比
     */
      private String field_11_2_2;

      /**
     * 播栽期进度3（6月10日）-已栽插面积（万亩）
     */
      private String field_11_3;

      /**
     * 播栽期进度3（6月10日）-直播已播种面积（万亩）
     */
      private String field_11_4;

      /**
     * 播栽期进度3（6月10日）-小计
     */
      private String field_11_5;

      /**
     * 播栽期进度3（6月10日）-占比
     */
      private String field_11_6;

      /**
     * 播栽期进度4（6月20日）-日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private  Date field_12_1;

      /**
     * 播栽期进度4（6月20日）-已栽插面积（万亩）
     */
      private String field_12_2;

      /**
     * 播栽期进度4（6月20日）-直播已播种面积（万亩）
     */
      private String field_12_3;

      /**
     * 播栽期进度4（6月20日）-小计
     */
      private String field_12_4;

      /**
     * 播栽期进度4（6月20日）-占比
     */
      private String field_12_5;

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
