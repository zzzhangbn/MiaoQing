package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表9-3  20xx年度监测县(市、区)水稻倒伏灾害表
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_09_03 {

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
     * 全县种植面积（万亩）
     */
      private String field_5;

      /**
     * 倒伏时间（月/日-月/日）
     */
      private String field_6;

      /**
     * 倒伏面积（万亩）
     */
      private String field_7;

      /**
     * 倒伏程度（级别）
     */
      private String field_8;

      /**
     * 主要品种
     */
      private String field_9;

      /**
     * 占比（%）
     */
      private String field_10;

      /**
     * 其中：成灾面积（万亩）
     */
      private String field_11;

      /**
     * 其中：绝产面积（万亩）
     */
      private String field_12;

      /**
     * 产量损失（%）
     */
      private String field_13;

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
