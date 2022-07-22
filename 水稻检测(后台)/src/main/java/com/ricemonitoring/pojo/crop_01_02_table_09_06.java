package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表9-6  20xx年度监测县(市、区)水稻涝害调查表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_09_06 {

      /**
     * id,自增长
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
     * 品系类型
     */
      private String field_4;

      /**
     * 受灾统计表（万亩）：填报日期
     */
      private String field_5_1;

      /**
     * 生育阶段与灾害症状
     */
      private String field_5_2;

      /**
     * 受灾面积
     */
      private String field_5_3;

      /**
     * 其中：成灾面积
     */
      private String field_5_4;

      /**
     * 其中：绝收面积
     */
      private String field_5_5;

      /**
     * 需补改种面积
     */
      private String field_5_6;

      /**
     * 已补改种面积
     */
      private String field_5_7;

      /**
     * 受灾统计表（万亩）：填报日期
     */
      private String field_6_1;

      /**
     * 生育进程、阶段
     */
      private String field_6_2;

      /**
     * 受灾面积
     */
      private String field_6_3;

      /**
     * 其中：成灾面积
     */
      private String field_6_4;

      /**
     * 其中：绝收面积
     */
      private String field_6_5;

      /**
     * 需补改种面积
     */
      private String field_6_6;

      /**
     * 已补改种面积
     */
      private String field_6_7;

      /**
     * 受灾统计表（万亩）：填报日期
     */
      private String field_7_1;

      /**
     * 生育进程、阶段
     */
      private String field_7_2;

      /**
     * 受灾面积
     */
      private String field_7_3;

      /**
     * 其中：成灾面积
     */
      private String field_7_4;

      /**
     * 其中：绝收面积
     */
      private String field_7_5;

      /**
     * 需补改种面积
     */
      private String field_7_6;

      /**
     * 已补改种面积
     */
      private String field_7_7;

      /**
     * 修改日期
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
