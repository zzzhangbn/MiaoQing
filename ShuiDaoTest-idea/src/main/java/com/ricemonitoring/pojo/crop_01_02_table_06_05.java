package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表6-5  20xx年度水稻监测点化学调节剂使用记录表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_06_05{


      /**
     * id,自增长
     */
      private Integer id;

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
     * 使用时间
     */
      private String field_7_1;

      /**
     * 化调剂名称
     */
      private String field_7_2;

      /**
     * 亩用量/kg
     */
      private String field_7_3;

      /**
     * 使用方法
     */
      private String field_7_4;

      /**
     * 生育进程
     */
      private String field_7_5;

      /**
     * 使用时间
     */
      private String field_8_1;

      /**
     * 化调剂名称
     */
      private String field_8_2;

      /**
     * 亩用量/kg
     */
      private String field_8_3;

      /**
     * 使用方法
     */
      private String field_8_4;

      /**
     * 生育进程
     */
      private String field_8_5;

      /**
     * 使用时间
     */
      private String field_9_1;

      /**
     * 化调剂名称
     */
      private String field_9_2;

      /**
     * 亩用量/kg
     */
      private String field_9_3;

      /**
     * 使用方法
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
     * 修改人员
     */
      private String people;

      /**
     * 使用状态
     */
      private String in_use;


}
