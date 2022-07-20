package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public class crop_01_01_table_09_02 {


      /**
     * id,自增长
     */
      private Integer id;

      /**
     * 县（市、区）
     */
      private String field_1_1;


    /**
     * 年份
     */
      private String field_1_2;
      /**
     * 抽穗期前15天的-日平均气温
     */
      private String field_2_1;

      /**
     * 抽穗期前15天的-日最高气温
     */
      private String field_2_2;

      /**
     * 抽穗期前15天的-降雨量
     */
      private String field_2_3;

      /**
     * 抽穗期前15天的-日照时数
     */
      private String field_2_4;

      /**
     * 抽穗期前15天的-晴好天气天数
     */
      private String field_2_5;

      /**
     * 抽穗期前15天的-阴雨天气天数
     */
      private String field_2_6;

      /**
     * 抽穗期后15天的-日平均气温
     */
      private String field_3_1;

      /**
     * 抽穗期后15天的-日最高气温
     */
      private String field_3_2;

      /**
     * 抽穗期后15天的-降雨量
     */
      private String field_3_3;

      /**
     * 抽穗期后15天的-日照时数
     */
      private String field_3_4;

      /**
     * 抽穗期后15天的-晴好天气天数
     */
      private String field_3_5;

      /**
     * 抽穗期后15天的-阴雨天气天数
     */
      private String field_3_6;

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
