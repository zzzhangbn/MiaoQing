package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表9-4  20xx年度监测县(市、区)水稻低温冷害表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_09_04 {

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
     * 低温冷害（时期与症状）
     */
      private String field_5;

      /**
     * 低温冷害发生时间（日期）
     */
      private String field_6;

      /**
     * 全县种植面积（万亩）
     */
      private String field_7;

      /**
     * 冷害总面积（万亩）
     */
      private String field_8;

      /**
     * 冷害级别（万亩）：1级
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
