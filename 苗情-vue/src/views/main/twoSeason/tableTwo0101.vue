<template>
    <div class="demo-image__placeholder">
      <el-row>
        <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:5px">
          <span>{{tableLabel}}</span>
        </div>
      </el-row>

      <el-row>
        <template v-if="column_id == ''|| column_id == null">
          <router-link :to="{name:'/tableTwoMain',params: {activeNum: 3}}">
            <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上报</el-button> -->
          </router-link>
        </template>
        
        <template v-if="column_id != ''&& column_id != null">
          <router-link to='/traceBackData'>
            <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
          </router-link>
        </template>
      </el-row>

      <el-table :data="data"  :header-cell-style="headerStyle" >
                <!-- <el-table-column :label="tableLabel" align="center"> -->
                 
                  <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                  
                  <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed />
                  <el-table-column align="center"  label="全县总面积（万亩）" fixed >
                    <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_2"  readonly></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column align="center" prop="field_3" width="130%" label="地区类型" fixed/>
                  <el-table-column align="center"  label="栽植方式（万亩）">
                    <el-table-column align="center" label="人工移栽">
                      <el-table-column align="center" prop="field_4_2_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                          class="secondFieldDetail" @blur="onBlur('field_5_1_1','field_5_1_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_1_1" @blur.prevent="checkFirstFieldInput($event)" 
                          :disabled="copyData[fieldRowIndex=scope.$index].field_5_1_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_1_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                          placeholder="自动计算"
                          disabled v-model="data[fieldRowIndex=scope.$index].field_5_1_2" ></input>
                      </template>
                      </el-table-column>

                    </el-table-column>
                    <el-table-column align="center" label="机械插秧">
                      <el-table-column align="center" prop="" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" @blur="onBlur('field_5_2_1','field_5_2_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_2_1" @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_5_2_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_2_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_5_2_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="盘育抛栽">
                      <el-table-column align="center"  label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        class="secondFieldDetail" @blur="onBlur('field_5_3_1','field_5_3_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_3_1" @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_5_3_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_3_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_5_3_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="无盘旱育抛栽">
                      <el-table-column align="center"  label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        class="secondFieldDetail" @blur="onBlur('field_5_4_1','field_5_4_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_4_1" @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_5_4_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_4_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_4_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_5_4_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                  
                    <el-table-column align="center" label="撒直播(旱)">
                      <el-table-column align="center" prop="field_4_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" @blur="onBlur('field_5_5_1','field_5_5_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_5_1" @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_5_5_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_5_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_4_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                         placeholder="自动计算"
                         disabled v-model="data[fieldRowIndex=scope.$index].field_5_5_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="撒直播(水)">
                      <el-table-column align="center" prop="field_4_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        class="secondFieldDetail" @blur="onBlur('field_5_6_1','field_5_6_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_6_1" @blur.prevent="checkFirstFieldInput($event)" 
                       :disabled="copyData[fieldRowIndex=scope.$index].field_5_6_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_6_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_4_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_5_6_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="机条播(旱)">
                      <el-table-column align="center" prop="field_4_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" @blur="onBlur('field_5_7_1','field_5_7_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_7_1" @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_5_7_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_7_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_4_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_5_7_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="机条播(水)">
                      <el-table-column align="center" prop="field_4_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" @blur="onBlur('field_5_8_1','field_5_8_2',scope.$index)" v-model="data[fieldRowIndex=scope.$index].field_5_8_1" @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_5_8_1 !=='' && copyData[fieldRowIndex=scope.$index].field_5_8_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_4_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_5_8_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="小计">
                      <el-table-column align="center" prop="field_4_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        v-model="data[fieldRowIndex=scope.$index].field_5_9_1" disabled></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_4_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        v-model="data[fieldRowIndex=scope.$index].field_5_9_2" disabled></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                  </el-table-column>
                  <el-table-column  align="center"  label="前茬口类型（万亩）">
                    
                    <el-table-column align="center" label="水稻">
                      <el-table-column align="center" prop="field_5_2_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        @blur="onBlur('field_6_1_1','field_6_1_2',scope.$index)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_6_1_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_1_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_1_1"  @blur.prevent="checkFirstFieldInput($event)"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_2_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input  style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_6_1_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="小麦">
                      <el-table-column align="center" prop="field_5_3_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input @blur="onBlur('field_6_2_1','field_6_2_2',scope.$index)" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_2_1"  @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_6_2_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_2_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_3_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_6_2_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="油菜">
                      <el-table-column align="center" prop="field_5_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input @blur="onBlur('field_6_3_1','field_6_3_2',scope.$index)" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_3_1"  @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_6_3_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_3_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_6_3_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="空闲田">
                      <el-table-column align="center" prop="field_5_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input @blur="onBlur('field_6_4_1','field_6_4_2',scope.$index)" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_4_1"  @blur.prevent="checkFirstFieldInput($event)" 
                       :disabled="copyData[fieldRowIndex=scope.$index].field_6_4_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_4_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_6_4_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="绿肥">
                      <el-table-column align="center" prop="field_5_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input @blur="onBlur('field_6_5_1','field_6_5_2',scope.$index)" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_5_1"  @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_6_5_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_5_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                         placeholder="自动计算"
                         disabled v-model="data[fieldRowIndex=scope.$index].field_6_5_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="蔬菜等经作">
                      <el-table-column align="center" prop="field_5_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input @blur="onBlur('field_6_6_1','field_6_6_2',scope.$index)" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_6_1"  @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_6_6_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_6_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_6_6_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="其它">
                      <el-table-column align="center" prop="field_5_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input @blur="onBlur('field_6_7_1','field_6_7_2',scope.$index)" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_6_7_1"  @blur.prevent="checkFirstFieldInput($event)" 
                        :disabled="copyData[fieldRowIndex=scope.$index].field_6_7_1 !=='' && copyData[fieldRowIndex=scope.$index].field_6_7_1 !== null && copyData[fieldRowIndex=scope.$index].in_use != '5' && column_id == ''"></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        disabled v-model="data[fieldRowIndex=scope.$index].field_6_7_2"></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center" label="小计">
                      <el-table-column align="center" prop="field_5_4_1" label="面积（万亩）" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                        placeholder="自动计算"
                        v-model="data[fieldRowIndex=scope.$index].field_6_8_1" disabled></input>
                      </template>
                      </el-table-column>
                      <el-table-column align="center" prop="field_5_4_2" label="（占比）自动计算" >
                      <template slot-scope="scope">
                        <input style="background-color: #f5f7fa;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                        placeholder="自动计算"
                        v-model="data[fieldRowIndex=scope.$index].field_6_8_2" disabled></input>
                      </template>
                      </el-table-column>
                    </el-table-column>
                  </el-table-column>
                
                  
                
            
        
                  <el-table-column align="center"  fixed="right" width="252px" label="操作">
                    <template slot-scope="scope">
                      <!-- 当in_use非0，非5时，不可点击暂存 -->
                      <template v-if="data[fieldRowIndex=scope.$index].in_use == '0' || data[fieldRowIndex=scope.$index].in_use == '5'">
                        <el-button @click="getTemporaryFormList(fieldRowIndex=scope.$index)">暂存</el-button>
                      </template>
                    
                    <el-button @click="getFormList(fieldRowIndex=scope.$index)">上报</el-button>
                    <el-button>撤回</el-button>
                    <!-- <template slot-scope="scope">
                      <el-button type="danger" size="mini" v-if="scope.row.in_use == 3" @click="backRevision(scope.$index,scope.row)" >返回</el-button>
                    </template> -->
                    </template>
                  </el-table-column>  
                <!-- </el-table-column> -->
              </el-table>
    </div>
</template>
<script src="../../../public/js/Vue.min.js">
</script>
<script>
import axios from "axios";
import * as math from "mathjs";
import template1 from "../../pages/researchReport/template/template1.vue";
export default {
  components: { template1 },
  data() {
    return {
      tableLabel: "",
      data: [],
      copyData: [],
      detialDialogVisible: false,
      tableFlag: "", //保存当前点击的字段名
      fieldRowIndex: 0, //存储二级字段所在行位置
      fieldName: "", //下拉框所属的字段名称，
      daoZuoTypeData: [], //稻作类型
      column_id: "",
      tableName: "",
      // tableId:'',
      showHeaderStatus: false,
    };
  },

  methods: {
    initData() {
      this.tableLabel = "年度监测县(市、区)水稻栽插概况调查表";
    },

    headerStyle({ row, column, rowIndex, columnIndex }) {
      //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
      if (rowIndex === 2 || 3) {
        // return {display:"none"};
        return {
          height: "0",
          padding: "2px",
          fontSize: "7px",
          background: "#6E8B3D",
          color: "#FFFFFF",
        };
      }
      if (rowIndex === 0) {
        return {
          height: "0",
          padding: "2px",
          fontSize: "7px",
          background: "#6E8B3D",
          color: "#FFFFFF",
        };
      }
      if (rowIndex == 1) {
        // return {background:'#eee',color:'#606266'};
        return {
          height: "0",
          padding: "1px",
          fontSize: "5px",
          background: "#6E8B3D",
          color: "#FFFFFF",
        };
      }
    },

    //表格颜色
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 0) {
        return "yello";
      }
      return "";
    },

    //处理小数点
    add(value) {
      const precision = 14;
      return Number(math.format(value, precision));
    },
    //清 空
    getInputInfo(name) {
      this.detialDialogVisible = false;
    },
    onBlur(x1, x2, fieldRowIndex) {
      this.fieldRowIndex = fieldRowIndex;
      console.log(this.fieldRowIndex);
      if (x1 == "field_5_8_1") {
        if (
          this.data[this.fieldRowIndex].field_5_1_1 != null &&
          this.data[this.fieldRowIndex].field_5_2_1 != null &&
          this.data[this.fieldRowIndex].field_5_3_1 != null &&
          this.data[this.fieldRowIndex].field_5_4_1 != null &&
          this.data[this.fieldRowIndex].field_5_5_1 != null &&
          this.data[this.fieldRowIndex].field_5_6_1 != null &&
          this.data[this.fieldRowIndex].field_5_7_1 != null &&
          this.data[this.fieldRowIndex].field_5_8_1 != null &&
          this.add(
            Number(this.data[this.fieldRowIndex].field_5_1_1) +
              Number(this.data[this.fieldRowIndex].field_5_2_1) +
              Number(this.data[this.fieldRowIndex].field_5_3_1) +
              Number(this.data[this.fieldRowIndex].field_5_4_1) +
              Number(this.data[this.fieldRowIndex].field_5_5_1) +
              Number(this.data[this.fieldRowIndex].field_5_6_1) +
              Number(this.data[this.fieldRowIndex].field_5_7_1) +
              Number(this.data[this.fieldRowIndex].field_5_8_1)
          ) != Number(this.data[this.fieldRowIndex].field_2)
        ) {
          this.data[this.fieldRowIndex].field_5_9_1 = "填写有误！";
        } else if (
          this.data[this.fieldRowIndex].field_5_1_1 != null &&
          this.data[this.fieldRowIndex].field_5_2_1 != null &&
          this.data[this.fieldRowIndex].field_5_3_1 != null &&
          this.data[this.fieldRowIndex].field_5_4_1 != null &&
          this.data[this.fieldRowIndex].field_5_5_1 != null &&
          this.data[this.fieldRowIndex].field_5_6_1 != null &&
          this.data[this.fieldRowIndex].field_5_7_1 != null &&
          this.data[this.fieldRowIndex].field_5_8_1 != null &&
          this.add(
            Number(this.data[this.fieldRowIndex].field_5_1_1) +
              Number(this.data[this.fieldRowIndex].field_5_2_1) +
              Number(this.data[this.fieldRowIndex].field_5_3_1) +
              Number(this.data[this.fieldRowIndex].field_5_4_1) +
              Number(this.data[this.fieldRowIndex].field_5_5_1) +
              Number(this.data[this.fieldRowIndex].field_5_6_1) +
              Number(this.data[this.fieldRowIndex].field_5_7_1) +
              Number(this.data[this.fieldRowIndex].field_5_8_1)
          ) == Number(this.data[this.fieldRowIndex].field_2)
        ) {
          this.data[this.fieldRowIndex].field_5_9_1 = this.add(
            Number(this.data[this.fieldRowIndex].field_5_1_1) +
              Number(this.data[this.fieldRowIndex].field_5_2_1) +
              Number(this.data[this.fieldRowIndex].field_5_3_1) +
              Number(this.data[this.fieldRowIndex].field_5_4_1) +
              Number(this.data[this.fieldRowIndex].field_5_5_1) +
              Number(this.data[this.fieldRowIndex].field_5_6_1) +
              Number(this.data[this.fieldRowIndex].field_5_7_1) +
              Number(this.data[this.fieldRowIndex].field_5_8_1)
          );
        } else {
          this.data[this.fieldRowIndex].field_5_9_1 = null;
        }
      }
      if (x2 == "field_5_8_2") {
        if (
          this.data[this.fieldRowIndex].field_5_1_1 != null &&
          this.data[this.fieldRowIndex].field_5_2_1 != null &&
          this.data[this.fieldRowIndex].field_5_3_1 != null &&
          this.data[this.fieldRowIndex].field_5_4_1 != null &&
          this.data[this.fieldRowIndex].field_5_5_1 != null &&
          this.data[this.fieldRowIndex].field_5_6_1 != null &&
          this.data[this.fieldRowIndex].field_5_7_1 != null &&
          this.data[this.fieldRowIndex].field_5_8_1 != null &&
          this.data[this.fieldRowIndex].field_5_7_1 != null &&
          this.data[this.fieldRowIndex].field_5_8_1 != null
        ) {
          this.data[this.fieldRowIndex].field_5_9_2 =
            (
              Math.round(
                ((Number(this.data[this.fieldRowIndex].field_5_1_1) +
                  Number(this.data[this.fieldRowIndex].field_5_2_1) +
                  Number(this.data[this.fieldRowIndex].field_5_3_1) +
                  Number(this.data[this.fieldRowIndex].field_5_4_1) +
                  Number(this.data[this.fieldRowIndex].field_5_5_1) +
                  Number(this.data[this.fieldRowIndex].field_5_6_1) +
                  Number(this.data[this.fieldRowIndex].field_5_7_1) +
                  Number(this.data[this.fieldRowIndex].field_5_8_1)) /
                  Number(this.data[this.fieldRowIndex].field_2)) *
                  100 *
                  100
              ) / 100
            ).toFixed(2) + "%";
        } else {
          this.data[this.fieldRowIndex].field_5_9_2 = null;
        }
        if (this.data[this.fieldRowIndex].field_5_9_2 == "100.00%") {
          this.data[this.fieldRowIndex].field_5_9_1 = this.add(
            Number(this.data[this.fieldRowIndex].field_5_1_1) +
              Number(this.data[this.fieldRowIndex].field_5_2_1) +
              Number(this.data[this.fieldRowIndex].field_5_3_1) +
              Number(this.data[this.fieldRowIndex].field_5_4_1) +
              Number(this.data[this.fieldRowIndex].field_5_5_1) +
              Number(this.data[this.fieldRowIndex].field_5_6_1) +
              Number(this.data[this.fieldRowIndex].field_5_7_1) +
              Number(this.data[this.fieldRowIndex].field_5_8_1)
          );
        }
      }
      if (x1 == "field_6_7_1") {
        if (
          this.data[this.fieldRowIndex].field_6_1_1 != null &&
          this.data[this.fieldRowIndex].field_6_2_1 != null &&
          this.data[this.fieldRowIndex].field_6_3_1 != null &&
          this.data[this.fieldRowIndex].field_6_4_1 != null &&
          this.data[this.fieldRowIndex].field_6_5_1 != null &&
          this.data[this.fieldRowIndex].field_6_6_1 != null &&
          this.data[this.fieldRowIndex].field_6_7_1 != null &&
          Number(this.data[this.fieldRowIndex].field_6_1_1) +
            Number(this.data[this.fieldRowIndex].field_6_2_1) +
            Number(this.data[this.fieldRowIndex].field_6_3_1) +
            Number(this.data[this.fieldRowIndex].field_6_4_1) +
            Number(this.data[this.fieldRowIndex].field_6_5_1) +
            Number(this.data[this.fieldRowIndex].field_6_6_1) +
            Number(this.data[this.fieldRowIndex].field_6_7_1) !=
            Number(this.data[this.fieldRowIndex].field_2)
        ) {
          this.data[this.fieldRowIndex].field_6_8_1 = "填写有误！";
        } else if (
          this.data[this.fieldRowIndex].field_6_1_1 != null &&
          this.data[this.fieldRowIndex].field_6_2_1 != null &&
          this.data[this.fieldRowIndex].field_6_3_1 != null &&
          this.data[this.fieldRowIndex].field_6_4_1 != null &&
          this.data[this.fieldRowIndex].field_6_5_1 != null &&
          this.data[this.fieldRowIndex].field_6_6_1 != null &&
          this.data[this.fieldRowIndex].field_6_7_1 != null &&
          Number(this.data[this.fieldRowIndex].field_6_1_1) +
            Number(this.data[this.fieldRowIndex].field_6_2_1) +
            Number(this.data[this.fieldRowIndex].field_6_3_1) +
            Number(this.data[this.fieldRowIndex].field_6_4_1) +
            Number(this.data[this.fieldRowIndex].field_6_5_1) +
            Number(this.data[this.fieldRowIndex].field_6_6_1) +
            Number(this.data[this.fieldRowIndex].field_6_7_1) ==
            Number(this.data[this.fieldRowIndex].field_2)
        ) {
          this.data[this.fieldRowIndex].field_6_8_1 = this.add(
            Number(this.data[this.fieldRowIndex].field_6_1_1) +
              Number(this.data[this.fieldRowIndex].field_6_2_1) +
              Number(this.data[this.fieldRowIndex].field_6_3_1) +
              Number(this.data[this.fieldRowIndex].field_6_4_1) +
              Number(this.data[this.fieldRowIndex].field_6_5_1) +
              Number(this.data[this.fieldRowIndex].field_6_6_1) +
              Number(this.data[this.fieldRowIndex].field_6_7_1)
          );
        } else {
          this.data[this.fieldRowIndex].field_6_8_1 = null;
        }
      }
      if (x2 == "field_6_7_2") {
        if (
          this.data[this.fieldRowIndex].field_6_1_1 != null &&
          this.data[this.fieldRowIndex].field_6_2_1 != null &&
          this.data[this.fieldRowIndex].field_6_3_1 != null &&
          this.data[this.fieldRowIndex].field_6_4_1 != null &&
          this.data[this.fieldRowIndex].field_6_5_1 != null &&
          this.data[this.fieldRowIndex].field_6_6_1 != null &&
          this.data[this.fieldRowIndex].field_6_7_1 != null
        ) {
          this.data[this.fieldRowIndex].field_6_8_2 =
            (
              Math.round(
                ((Number(this.data[this.fieldRowIndex].field_6_1_1) +
                  Number(this.data[this.fieldRowIndex].field_6_2_1) +
                  Number(this.data[this.fieldRowIndex].field_6_3_1) +
                  Number(this.data[this.fieldRowIndex].field_6_4_1) +
                  Number(this.data[this.fieldRowIndex].field_6_5_1) +
                  Number(this.data[this.fieldRowIndex].field_6_6_1) +
                  Number(this.data[this.fieldRowIndex].field_6_7_1)) /
                  Number(this.data[this.fieldRowIndex].field_2)) *
                  100 *
                  100
              ) / 100
            ).toFixed(2) + "%";
        } else {
          this.data[this.fieldRowIndex].field_6_8_2 = null;
        }
      }
      this.data[this.fieldRowIndex][x2] =
        (
          Math.round(
            (this.data[this.fieldRowIndex][x1] /
              this.data[this.fieldRowIndex].field_2) *
              100 *
              100
          ) / 100
        ).toFixed(2) + "%";
      this.data[this.fieldRowIndex].field_5_9_2 =
        (
          Math.round(
            ((Number(this.data[this.fieldRowIndex].field_5_1_1) +
              Number(this.data[this.fieldRowIndex].field_5_2_1) +
              Number(this.data[this.fieldRowIndex].field_5_3_1) +
              Number(this.data[this.fieldRowIndex].field_5_4_1) +
              Number(this.data[this.fieldRowIndex].field_5_5_1) +
              Number(this.data[this.fieldRowIndex].field_5_6_1) +
              Number(this.data[this.fieldRowIndex].field_5_7_1) +
              Number(this.data[this.fieldRowIndex].field_5_8_1)) /
              Number(this.data[this.fieldRowIndex].field_2)) *
              100 *
              100
          ) / 100
        ).toFixed(2) + "%";
      this.data[this.fieldRowIndex].field_6_8_2 =
        (
          Math.round(
            ((Number(this.data[this.fieldRowIndex].field_6_1_1) +
              Number(this.data[this.fieldRowIndex].field_6_2_1) +
              Number(this.data[this.fieldRowIndex].field_6_3_1) +
              Number(this.data[this.fieldRowIndex].field_6_4_1) +
              Number(this.data[this.fieldRowIndex].field_6_5_1) +
              Number(this.data[this.fieldRowIndex].field_6_6_1) +
              Number(this.data[this.fieldRowIndex].field_6_7_1)) /
              Number(this.data[this.fieldRowIndex].field_2)) *
              100 *
              100
          ) / 100
        ).toFixed(2) + "%";
      this.data[this.fieldRowIndex].field_5_9_1 = this.add(
        Number(this.data[this.fieldRowIndex].field_5_1_1) +
          Number(this.data[this.fieldRowIndex].field_5_2_1) +
          Number(this.data[this.fieldRowIndex].field_5_3_1) +
          Number(this.data[this.fieldRowIndex].field_5_4_1) +
          Number(this.data[this.fieldRowIndex].field_5_5_1) +
          Number(this.data[this.fieldRowIndex].field_5_6_1) +
          Number(this.data[this.fieldRowIndex].field_5_7_1) +
          Number(this.data[this.fieldRowIndex].field_5_8_1)
      );
      this.data[this.fieldRowIndex].field_6_8_1 = this.add(
        Number(this.data[this.fieldRowIndex].field_6_1_1) +
          Number(this.data[this.fieldRowIndex].field_6_2_1) +
          Number(this.data[this.fieldRowIndex].field_6_3_1) +
          Number(this.data[this.fieldRowIndex].field_6_4_1) +
          Number(this.data[this.fieldRowIndex].field_6_5_1) +
          Number(this.data[this.fieldRowIndex].field_6_6_1) +
          Number(this.data[this.fieldRowIndex].field_6_7_1)
      );
    },

    //检查input框的输入是否符合要求
    checkFirstFieldInput(e) {
      var strValue = e.target.value;
      if (!isNaN(strValue)) {
      } else {
        // e.target.parentNode.nextSibling.innerHTML = "X";
        e.target.value = "";
        alert("请输入数字！");
      }
    },

    handleClose(done) {
      var inputs = document.getElementsByClassName("secondFieldDetail");
    },
    //取消按钮
    cancelOp() {
      this.detialDialogVisible = false;
    },
    //获取稻作类型数据
    brandKeyChange1: function() {
      axios({
        url: "/getSelectContent",
        method: "post",
        params: {
          fieldName: "稻作类型",
          riceType: "double",
        },
      })
        .then((res) => {
          this.daoZuoTypeData = res.data;
        })
        .catch((err) => {
          return this.$message.error("获取稻作类型失败！");
        });
    },

    //暂存数据
    getTemporaryFormList(fieldRowIndex) {
      this.$confirm("是否暂存？")
        .then((_) => {
          var date = this.getNowTime();

          this.data[fieldRowIndex].modification_time = date;
          this.data[fieldRowIndex].in_use = 5;

          this.saveInputData(this.data[fieldRowIndex]);

          if (this.column_id != "" && this.tableName != "") {
            this.fixDataBackState(this.tableName, this.column_id);
          }
        })
        .catch((_) => {
          return this.$$message.info("您取消了操作！");
        });
    },

    //保存
    getFormList(fieldRowIndex) {
      this.$confirm("是否上报？")
        .then((_) => {
          var date = this.getNowTime();

          this.data[fieldRowIndex].modification_time = date;
          this.data[fieldRowIndex].in_use = 1;

          if (
            (this.data[fieldRowIndex].field_5_9_2 != "100.00%" &&
              this.data[fieldRowIndex].field_5_9_2 != "0.00%") ||
            (this.data[fieldRowIndex].field_6_8_2 != "100.00%" &&
              this.data[fieldRowIndex].field_6_8_2 != "0.00%")
          ) {
            return this.$message.error("上报失败！请检查填写数据情况");
          }
          this.saveInputData(this.data[fieldRowIndex]);

          if (this.column_id != "" && this.tableName != "") {
            this.fixDataBackState(this.tableName, this.column_id);
          }
        })
        .catch((_) => {
          return this.$$message.info("您取消了操作！");
        });
    },
    //生成保存函数
    saveInputData(list) {
      axios({
        url: "/getSituationInputDataTableTwo1",
        method: "post",
        params: {
          data: list,
        },
      })
        .then((res) => {
          this.$message.success("操作成功！");
        })
        .catch((err) => {
          return this.$message.error("数据保存失败！");
        });
    },
    //修改数据退还部分状态
    fixDataBackState(tableName, id) {
      axios({
        url: "/fixDataBackState",
        method: "post",
        params: {
          tableName: tableName,
          tableId: id,
        },
      })
        .then()
        .catch((_) => {
          return this.$message.error("数据修改失败！");
        });
    },

    //填写详细信息
    fillDetailInfo(e) {
      var Index = e.target.parentNode.cellIndex;
      this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex; //把当前点击字段所在行保存
      this.fieldRowIndex = this.fieldRowIndex - 2;

      var getTdInnerHTML = "";
      getTdInnerHTML =
        e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index]
          .innerHTML;
      this.tableFlag = getTdInnerHTML;

      // this.secondChooseField(getTdInnerHTML);
      this.detialDialogVisible = true;
    },

    // 获取当前时间
    getNowTime: function() {
      let dateTime;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth() + 1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf =
        new Date().getMinutes() < 10
          ? "0" + new Date().getMinutes()
          : new Date().getMinutes();
      let ss =
        new Date().getSeconds() < 10
          ? "0" + new Date().getSeconds()
          : new Date().getSeconds();
      if (hh < 10) {
        dateTime =
          yy + "-" + mm + "-" + dd + " " + "0" + hh + ":" + mf + ":" + ss;
      } else {
        dateTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
      }
      return dateTime;
    },

    //查询该用户下所有的已知数据条数
    checkUserAllKnownData1(people, column_id) {
      axios({
        url: "/checkUserAllKnownDataTwo1",
        method: "post",
        params: {
          UserID: people,
          column_id: column_id,
          startYear: 0,
          endYear: 0,
        },
      })
        .then((res) => {
          this.data = res.data;
          console.log(this.data);
          const json = JSON.stringify(res.data);
          this.copyData = JSON.parse(json);
        })
        .catch((err) => {
          return this.$message.error("用户所有信息查询失败！");
        });
    },
  },

  created() {
    this.initData();
    //登录监测--判断当前用户是否在数据库中已经有填过数据，有填过的话，从数据库中读入县（市、区）、地区类型
    var people = localStorage.getItem("ms_username");
    if (
      this.$route.query.column_id != null &&
      this.$route.query.column_id != undefined &&
      this.$route.query.column_id != ""
    ) {
      this.column_id = this.$route.query.column_id;
      this.tableName = this.$route.query.record_table;
    }
    if (
      this.$route.query.column_id == null ||
      this.$route.query.column_id == undefined ||
      this.$route.query.column_id == ""
    ) {
      this.column_id = "";
    }
    this.checkUserAllKnownData1(people, this.column_id);
  },
};
</script>
<!-- <script>Vue.config.productionTip=false</script> -->
<style  lang="less">
.el-table .yello {
  background: bisque;
}
.el-table .header-row {
  background: "#FF0000";
}
.demo-image__placeholder {
}
.el-dialog {
  top: 15%;
  left: 4%;
}
table tr,
table tr td {
  border: 1px solid lightgray;
}
</style>
