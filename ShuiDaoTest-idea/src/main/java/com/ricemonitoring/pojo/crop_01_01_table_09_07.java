package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表9-7    20xx年度水稻监测点洪涝灾害表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_01_table_09_07 {


      /**
     * id，自增长
     */
      private int id;

      /**
     * 县（市、区）
     */
      private String field_1;

      /**
     * 监测田地
     */
      private String field_2;

      /**
     * 地区类型
     */
      private String field_3;

      /**
     * 品种名称
     */
      private String field_4;

      /**
     * 种植方式
     */
      private String field_5;

      /**
     * 淹水日期
     */
      private String field_6;

      /**
     * 退水日期
     */
      private String field_7;

      /**
     * 生育阶段
     */
      private String field_8;

      /**
     * 面积（亩）
     */
      private String field_9;

      /**
     * 淹水高度（露叶尖、没顶）
     */
      private String field_10;

      /**
     * 淹水天数
     */
      private String field_11_1;

      /**
     * 其中没顶天数
     */
      private String field_11_2;

      /**
     * 程度
     */
      private String field_12;

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
