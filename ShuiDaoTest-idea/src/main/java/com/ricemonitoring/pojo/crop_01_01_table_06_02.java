package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

/**
 * <p>
 * 表6-2  20xx年度水稻监测点化除管理记录表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_01_table_06_02 {

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
     * 除草剂名称
     */
      private String field_7_2;

      /**
     * 亩用量/kg
     */
      private String field_7_3;

      /**
     * 方法
     */
      private String field_7_4;

      /**
     * 生育进程
     */
      private String field_7_5;

      /**
     * 日期
     */
      private String field_8_1;

      /**
     * 除草剂名称
     */
      private String field_8_2;

      /**
     * 亩用量/kg
     */
      private String field_8_3;

      /**
     * 方法
     */
      private String field_8_4;

      /**
     * 生育进程
     */
      private String field_8_5;

      /**
     * 日期
     */
      private String field_9_1;

      /**
     * 除草剂名称
     */
      private String field_9_2;

      /**
     * 亩用量/kg
     */
      private String field_9_3;

      /**
     * 方法
     */
      private String field_9_4;

      /**
     * 生育进程
     */
      private String field_9_5;

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
