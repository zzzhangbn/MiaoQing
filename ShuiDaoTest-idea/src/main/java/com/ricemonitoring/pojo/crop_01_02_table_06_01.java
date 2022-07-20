package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表6-1  20xx年度水稻监测点水分管理记录表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_06_01{


      /**
     * id,自增长
     */
      private int id;

      /**
     * 县（市、区）
     */
      private String field_1;

      /**
     * 监测点代码
     */
      private String field_2;

      /**
     * 地区类型
     */
      private String field_3;

      /**
     * 稻作类型（中籼稻、中粳稻）
     */
      private String field_4;

      /**
     * 品种名称
     */
      private String field_5;

      /**
     * 栽植方式
     */
      private String field_6;

      /**
     * 日期
     */
      private String field_7_1;

      /**
     * 深度（cm）
     */
      private String field_7_2;

      /**
     * 日期
     */
      private String field_8_1;

      /**
     * 深度（cm）
     */
      private String field_8_2;

      /**
     * 日期
     */
      private String field_9_1;

      /**
     * 深度（cm）
     */
      private String field_9_2;

      /**
     * 日期
     */
      private String field_10_1;

      /**
     * 深度（cm）
     */
      private String field_10_2;

      /**
     * 日期
     */
      private String field_11_1;

      /**
     * 深度（cm）
     */
      private String field_11_2;

      /**
     * 日期
     */
      private String field_12_1;

      /**
     * 深度（cm）
     */
      private String field_12_2;

      /**
     * 日期
     */
      private String field_13_1;

      /**
     * 深度（cm）
     */
      private String field_13_2;

      /**
     * 修改时间
     */
      private String modification_time;

      /**
     * 修改人员
     */
      private String people;

      /**
     * 使用状态
     */
      private String in_use;


}