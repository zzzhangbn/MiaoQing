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
    public class crop_01_02_table_07_02{

      /**
     * id,自增长
     */
      private int id;

      /**
     * 县市区
     */
      private String field_1;

      /**
     * 地区类型(沿淮地区、江淮地区、沿江地区）
     */
      private String field_2;

      /**
     * 总面积(万亩)
     */
      private String field_3;

      /**
     * 收获进度1（7月15日）-日期
     */
      private String field_4_1;

      /**
     * 收获面积（万亩）
     */
      private String field_4_2_1;

      /**
     * 收获面积（万亩）,占比（自动计算与秧田面积比例）
     */
      private String field_4_2_2;

      /**
     * 收获进度2（7月25日））-日期
     */
      private String field_5_1;

      /**
     * 收获面积（万亩）
     */
      private String field_5_2_1;

      /**
     * 收获面积（万亩）,占比（自动计算与秧田面积比例）
     */
      private String field_5_2_2;

      /**
     * 收获进度3（8月5日）-日期
     */
      private String field_6_1;

      /**
     * 收获面积（万亩）
     */
      private String field_6_2_1;

      /**
     * 收获面积（万亩）,占比（自动计算与秧田面积比例）
     */
      private String field_6_2_2;

      /**
     * 早稻实测产-面积(万亩)
     */
      private String field_7_1;

      /**
     * 早稻实测产-亩有效穗(万)
     */
      private String field_7_2;

      /**
     * 早稻实测产-穗总粒数(个)
     */
      private String field_7_3;

      /**
     * 早稻实测产-结实率(％)
     */
      private String field_7_4;

      /**
     * 早稻实测产-千粒重(g)
     */
      private String field_7_5;

      /**
     * 早稻实测产-产量(kg/亩),自动生成
     */
      private String field_7_6;

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
