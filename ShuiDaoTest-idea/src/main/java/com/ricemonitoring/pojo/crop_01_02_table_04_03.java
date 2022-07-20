package com.ricemonitoring.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 表4-3  20xx年度水稻监测点茎蘖数叶面积构成表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_04_03{

      /**
     * 序号
     */
      private int id;

      /**
     * 县（市、区）
     */
      private String field_1;

      /**
     * 监测田块
     */
      private String field_2;

      /**
     * 地区类型
     */
      private String field_3;

      /**
     * 稻作类型
     */
      private String field_4;

      /**
     * 品系类型
     */
      private String field_5;

      /**
     * 品种名称
     */
      private String field_6;

      /**
     * 栽培方式
     */
      private String field_7;

      /**
     * 目标产量(kg/亩)
     */
      private String field_8;

      /**
     * 基本苗（万/亩）
     */
      private String field_9;

      /**
     * 测定日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_10_1;

      /**
     * 株高(cm)
     */
      private String field_10_2;

      /**
     * 总茎蘖数(万/亩)
     */
      private String field_10_3;

      /**
     * 平均单茎绿叶数
     */
      private String field_10_4;

      /**
     * 倒4与倒3叶色比(大于、等于、小于)
     */
      private String field_10_5;

      /**
     * 1叶分蘖个数
     */
      private String field_10_6;

      /**
     * 2叶分蘖个数
     */
      private String field_10_7;

      /**
     * 2叶分蘖个数
     */
      private String field_10_8;

      /**
     * 测定日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_11_1;

      /**
     * 株高(cm)
     */
      private String field_11_2;

      /**
     * 总茎蘖数(万/亩)
     */
      private String field_11_3;

      /**
     * 平均单茎绿叶数
     */
      private String field_11_4;

      /**
     * 
倒4与倒3叶色比(大于、等于、小于)
     */
      private String field_11_5;

      /**
     * 测定日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_12_1;

      /**
     * 株高(cm)
     */
      private String field_12_2;

      /**
     * 总茎蘖数(万/亩)
     */
      private String field_12_3;

      /**
     * 平均单茎绿叶数
     */
      private String field_12_4;

      /**
     * 倒4与倒3叶色比(大于、等于、小于)
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
