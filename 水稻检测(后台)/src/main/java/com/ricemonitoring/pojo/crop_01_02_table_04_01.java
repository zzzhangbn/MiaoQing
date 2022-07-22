package com.ricemonitoring.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 表4-1  20xx年度监测县(市、区)双季稻生育进度调查表（移栽稻）
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_04_01 {

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
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_8_1;

      /**
     * 叶龄(叶)
     */
      private String field_8_2;

      /**
     * 基本苗（万/亩）
     */
      private String field_8_3;

      /**
     * 亩穴数（万）
     */
      private String field_8_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_9_1;

      /**
     * 
生育进程

     */
      private String field_9_2;

      /**
     * 叶龄(叶)
     */
      private String field_9_3;

      /**
     * 茎蘖数(万/亩)
     */
      private String field_9_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_10_1;

      /**
     * 生育进程
     */
      private String field_10_2;

      /**
     * 叶龄(叶)
     */
      private String field_10_3;

      /**
     * 茎蘖数(万/亩)
     */
      private String field_10_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_11_1;

      /**
     * 生育进程
     */
      private String field_11_2;

      /**
     * 叶龄(叶)
     */
      private String field_11_3;

      /**
     * 茎蘖数(万/亩)
     */
      private String field_11_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_12_1;

      /**
     * 生育进程
     */
      private String field_12_2;

      /**
     * 叶龄(叶)
     */
      private String field_12_3;

      /**
     * 茎蘖数(万/亩)

     */
      private String field_12_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_13_1;

      /**
     * 生育进程
     */
      private String field_13_2;

      /**
     * 叶龄(叶)
     */
      private String field_13_3;

      /**
     * 茎蘖数(万/亩)
     */
      private String field_13_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_14_1;

      /**
     * 生育进程
     */
      private String field_14_2;

      /**
     * 叶龄(叶)
     */
      private String field_14_3;

      /**
     * 茎蘖数(万/亩)
     */
      private String field_14_4;

      /**
     * 日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_15_1;

      /**
     * 生育进程
     */
      private String field_15_2;

      /**
     * 叶龄(叶)
     */
      private String field_15_3;

      /**
     * 茎蘖数(万/亩)
     */
      private String field_15_4;

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
