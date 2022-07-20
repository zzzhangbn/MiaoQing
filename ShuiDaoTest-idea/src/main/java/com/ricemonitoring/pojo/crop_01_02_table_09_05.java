package com.ricemonitoring.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 表9-5  20xx年度监测县(市、区)高温热害表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class crop_01_02_table_09_05 {

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
     * 高温热害(生育阶段与症状)
     */
      private String field_5;

      /**
     * 高温热害发生时间（日期）
     */
      private String field_6;

      /**
     * 全县种植面积（万亩）
     */
      private String field_7;

      /**
     * 热害总面积（万亩）
     */
      private String field_8;

      /**
     * 热害级别（万亩）：1级
     */
      private String field_9_1;

      /**
     * 2级
     */
      private String field_9_2;

      /**
     * 3级
     */
      private String field_9_3;

      /**
     * 4级
     */
      private String field_9_4;

      /**
     * 5级
     */
      private String field_9_5;

      /**
     * 主要品种名称
     */
      private String field_10;

      /**
     * 占比（%）
     */
      private String field_11;

      /**
     * 其中：成灾面积（万亩）
     */
      private String field_12;

      /**
     * 其中：绝收面积（万亩）
     */
      private String field_13;

      /**
     * 产量损失（%）
     */
      private String field_14;

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
