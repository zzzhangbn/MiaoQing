package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 表2 2021年度水稻监测点建档卡
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_01_table_02_01 {
      /**
     * 主键id
     */
      private int id;

      /**
     * 监测点代码
     */
      private String field_1;

      /**
     * 监测年份
     */
      private Date field_2;

      /**
     * 市
     */
      private String field_3;

      /**
     * 县（市、区）
     */
      private String field_4;

      /**
     * 镇（乡）
     */
      private String field_5;

      /**
     * 村名
     */
      private String field_6;

      /**
     * 户主
     */
      private String field_7;

      /**
     * 种植面积（亩）
     */
      private String field_8;

      /**
     * 地区类型
     */
      private String field_9;

      /**
     * 土壤类型
     */
      private String field_10;

      /**
     * 前茬类型
     */
      private String field_11;

      /**
     * 水管方式
     */
      private String field_12;

      /**
     * 全氮（g/Kg）
     */
      private String field_13;

      /**
     * 速效磷（g/Kg）
     */
      private String field_14;

      /**
     * 速效钾（g/Kg）
     */
      private String field_15;

      /**
     * 有机质（g/Kg）
     */
      private String field_16;

      /**
     * 秸秆还田量（kg/亩）
     */
      private String field_17;

      /**
     * 名称
     */
      private String field_18;

      /**
     * 稻作类型
     */
      private String field_19;

      /**
     * 品系类型
     */
      private String field_20;

      /**
     * 栽种方式
     */
      private String field_21;

      /**
     * 播期
     */
      private Date field_22;

      /**
     * 移栽秧龄（天）
     */
      private String field_23;

      /**
     * 移栽叶龄（叶）
     */
      private String field_24;

      /**
     * 基本苗（万/亩）
     */
      private String field_25;

      /**
     * 收获日期
     */
      private Date field_26;

      /**
     * 亩有效穗（万）
     */
      private String field_27;

      /**
     * 穗总粒数（个）
     */
      private String field_28;

      /**
     * 结实率（%）
     */
      private String field_29;

      /**
     * 千粒重（g）
     */
      private String field_30;

      /**
     * 理论产量（kg/亩）
     */
      private String field_31;

      /**
     * 实收产量（kg/亩）
     */
      private String field_32;

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

      /**
       * 状态
       */
      private String continue_use;



}
