<template>
    <div class="demo-image__placeholder">
      <template v-if="column_id != ''">
        <router-link to='/traceBackData'>
          <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
         </router-link>
      </template>
      <template v-if="column_id == ''|| column_id == null">
        <router-link to='/tablemain'>
              <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
          </router-link>
      </template>
      <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上报</el-button>
              <div id="containner_div" style="width: 100%;height: 70%;">
 
                <table id="sum_tab" style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                  <tr >
                    <td colspan="6" style="background-color: #6E8B3D;color: #FFFFFF;">年度监测县(市、区)水稻栽插概况调查表</td>
                  </tr>
                  <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 15%;">
                      县（市、区）
                    </td>
                    <td style="width: 15%;">全县总面积（万亩）</td>
                    <td style="width: 15%;">地区类型</td>
                    <td style="width: 15%;">稻作类型</td>
                    <td style="width: 15%;">栽植方式（万亩）</td>
                    <td style="width: 15%;">前茬口类型（万亩）</td>
                  </tr>
                  <template v-for="(item,index) in data">
                  <tr>
                    <td>
                    
                        <el-input v-model="data[index].field_1" :disabled="data[index].field_1 != null && data[index].field_1 != ''"></el-input>
                      
                    </td>
                    <td>
     
                      
                        <el-input v-model="data[index].field_2" @blur.prevent="checkFirstFieldInput($event)" :disabled="data[index].field_2 != null && data[index].field_2 != ''&& column_id == ''"></el-input>
                      
                    </td>
                    <td>
                      
                        <el-input v-model="data[index].field_3" @blur.prevent="checkFirstFieldInput($event)" :disabled="data[index].field_3 != null && data[index].field_3 != ''"></el-input>
                     
                    </td>
                    <td>
                      
                        <el-select v-model="data[index].field_4" placeholder="请选择" @focus="brandKeyChange1" :disabled="data[index].field_4 != null && data[index].field_4 != ''&& column_id == ''">
                          <el-option
                              v-for="item in daoZuoTypeData"
                              :key="item.id"
                              :label="item.list"
                              :value="item.list">
                          </el-option>
                        </el-select>
                      
                    </td>
                    <td>
                      <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                      <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                  </tr>
                </template>
                </table>
              </div>

            <!--字段详情-->
            <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="91%" >
            <table style="width: 100%;border: 1px solid lightgray;line-height: 40px;" border="1" cellspacing="0">
              <template v-if="tableFlag == '栽植方式（万亩）'">
                <tr style="background-color: #CDBE70;color: green;">
                  <td colspan="2">人工移栽</td>
                  <td colspan="2">机械插秧</td>
                  <td colspan="2">盘育抛栽</td>
                  <td colspan="2">无盘旱育抛栽</td>
                  <td colspan="2">撒直播(旱)</td>
                  <td colspan="2">撒直播(水)</td>
                  <td colspan="2">机条播(旱)</td>
                  <td colspan="2">机条播(水)</td>
                  <td colspan="2">小计</td>
                </tr>
                <tr>
                  <td style="width: 6%;">
                    
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" @blur="onBlur('field_5_1_1','field_5_1_2')" v-model="data[fieldRowIndex].field_5_1_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_1_1 != null && data[fieldRowIndex].field_5_1_1 != ''&& column_id == ''"></input>
                 
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                     disabled v-model="data[fieldRowIndex].field_5_1_2"></input>
                  </td>
                  <td style="width: 6%;">
               
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" @blur="onBlur('field_5_2_1','field_5_2_2')" v-model="data[fieldRowIndex].field_5_2_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_2_1 != null && data[fieldRowIndex].field_5_2_1 != ''&& column_id == ''"></input>
                
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                     disabled v-model="data[fieldRowIndex].field_5_2_2"></input>
                  </td>
                  <td style="width: 6%;">
                    
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                    class="secondFieldDetail" @blur="onBlur('field_5_3_1','field_5_3_2')" v-model="data[fieldRowIndex].field_5_3_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_3_1 != null && data[fieldRowIndex].field_5_3_1 != ''&& column_id == ''"></input>
          
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_5_3_2"></input>
                  </td>
                  <td style="width: 6%;">
                 
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                    class="secondFieldDetail" @blur="onBlur('field_5_4_1','field_5_4_2')" v-model="data[fieldRowIndex].field_5_4_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_4_1 != null && data[fieldRowIndex].field_5_4_1 != ''&& column_id == ''"></input>
                
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_5_4_2"></input>
                  </td>
                  <td style="width: 6%;">
                    
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" @blur="onBlur('field_5_5_1','field_5_5_2')" v-model="data[fieldRowIndex].field_5_5_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_5_1 != null && data[fieldRowIndex].field_5_5_1 != ''&& column_id == ''"></input>
           
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_5_5_2"></input>
                  </td>
                  <td style="width: 6%;">
           
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                    class="secondFieldDetail" @blur="onBlur('field_5_6_1','field_5_6_2')" v-model="data[fieldRowIndex].field_5_6_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_6_1 != null && data[fieldRowIndex].field_5_6_1 != ''&& column_id == ''"></input>
              
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_5_6_2"></input>
                  </td>
                  <td style="width: 6%;">
             
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" @blur="onBlur('field_5_7_1','field_5_7_2')" v-model="data[fieldRowIndex].field_5_7_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_7_1 != null && data[fieldRowIndex].field_5_7_1 != ''&& column_id == ''"></input>
           
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_5_7_2"></input>
                  </td>
                  <td style="width: 6%;">
           
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" @blur="onBlur('field_5_8_1','field_5_8_2')" v-model="data[fieldRowIndex].field_5_8_1" @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_5_8_1 != null && data[fieldRowIndex].field_5_8_1 != ''&& column_id == ''"></input>
 
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_5_8_2"></input>
                  </td>
                  <td id="editResult" style="width: 7%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    v-model="data[fieldRowIndex].field_5_9_1" disabled></input>
                  </td>
                  <td style="width: 6%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    v-model="data[fieldRowIndex].field_5_9_2" disabled></input>
                  </td>
                </tr>
              </template>
              <template v-if="tableFlag == '前茬口类型（万亩）'">
                <tr style="background-color: #CDBE70;color: green;">
                  <td colspan="2">水稻</td>
                  <td colspan="2">小麦</td>
                  <td colspan="2">油菜</td>
                  <td colspan="2">空闲田</td>
                  <td colspan="2">绿肥</td>
                  <td colspan="2">蔬菜等经作</td>
                  <td colspan="2">其它</td>
                  <td colspan="2">小计</td>
                </tr>
                <tr>
                  <td style="width: 6%;">
                   
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                    @blur="onBlur('field_6_1_1','field_6_1_2')"
                    class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_1_1"  @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_6_1_1 != null && data[fieldRowIndex].field_6_1_1 != '' && column_id == ''"></input>

                  </td>
                  <td style="width: 5%;">
                    <input  style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_1_2"></input>
                  </td>
                  <td style="width: 6%;">
                    
                    <input @blur="onBlur('field_6_2_1','field_6_2_2')" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                     class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_2_1"  @blur.prevent="checkFirstFieldInput($event)"
                     :disabled="data[fieldRowIndex].field_6_2_1 != null && data[fieldRowIndex].field_6_2_1 != '' && column_id == ''"></input>
                    
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_2_2"></input>
                  </td>
                  <td style="width: 6%;">
                   
                    <input @blur="onBlur('field_6_3_1','field_6_3_2')" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_3_1"  @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_6_3_1 != null && data[fieldRowIndex].field_6_3_1 != '' && column_id == ''"></input>
               
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_3_2"></input>
                  </td>
                  <td style="width: 6%;">
               
                    <input @blur="onBlur('field_6_4_1','field_6_4_2')" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_4_1"  @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_6_4_1 != null && data[fieldRowIndex].field_6_4_1 != '' && column_id == ''"></input>
         
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_4_2"></input>
                  </td>
                  <td style="width: 6%;">
                 
                    <input @blur="onBlur('field_6_5_1','field_6_5_2')" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_5_1"  @blur.prevent="checkFirstFieldInput($event)"
                    :disabled="data[fieldRowIndex].field_6_5_1 != null && data[fieldRowIndex].field_6_5_1 != '' && column_id == ''"></input>
                  
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_5_2"></input>
                  </td>
                  <td style="width: 6%;">
                
                    <input @blur="onBlur('field_6_6_1','field_6_6_2')" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                     class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_6_1"  @blur.prevent="checkFirstFieldInput($event)"
                     :disabled="data[fieldRowIndex].field_6_6_1 != null && data[fieldRowIndex].field_6_6_1 != '' && column_id == ''"></input>

                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_6_2"></input>
                  </td>
                  <td style="width: 6%;">
                
                    <input @blur="onBlur('field_6_7_1','field_6_7_2')" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                     class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_7_1"  @blur.prevent="checkFirstFieldInput($event)"
                     :disabled="data[fieldRowIndex].field_6_7_1 != null && data[fieldRowIndex].field_6_7_1 != '' && column_id == ''"></input>
      
                  </td>
                  <td style="width: 5%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" disabled v-model="data[fieldRowIndex].field_6_7_2"></input>
                  </td>
                  <td id="editResult1" style="width: 7%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;" 
                    v-model="data[fieldRowIndex].field_6_8_1" disabled></input>
                  </td>
                  <td style="width: 6%;">
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;text-align: center;font-size: inherit;height: 40px;line-height: 40px;"
                     v-model="data[fieldRowIndex].field_6_8_2" disabled></input>
                  </td>
                </tr>
              </template>           
            </table>          
          <span slot="footer" class="dialog-footer">
            <el-button @click="cancelOp">关 闭</el-button>
            <el-button type="primary" @click="getInputInfo(tableFlag)">确 定</el-button>
          </span>
        </el-dialog>
    </div>
  </template>
  <script src="../../../public/js/Vue.min.js"></script>
  <script>
    import axios from 'axios'
  export default {
      data() {
          return {
                data:[],
                detialDialogVisible:false,       
                tableFlag:'',//保存当前点击的字段名
                fieldRowIndex:0,//存储二级字段所在行位置
                fieldName:'',//下拉框所属的字段名称，
                daoZuoTypeData:[],//稻作类型
                copyData:[],
                column_id:'',
                tableName:'',
          }
      },

      methods:{
        
        //清 空
        getInputInfo(name){

          //console.log(name);
          this.detialDialogVisible = false;
        },
        onBlur(x1,x2){
          if(x1 == 'field_5_8_1'){
            if(this.data[this.fieldRowIndex].field_5_1_1!=null && this.data[this.fieldRowIndex].field_5_2_1!=null && this.data[this.fieldRowIndex].field_5_3_1!=null && this.data[this.fieldRowIndex].field_5_4_1!=null && this.data[this.fieldRowIndex].field_5_5_1!=null && this.data[this.fieldRowIndex].field_5_6_1!=null && this.data[this.fieldRowIndex].field_5_7_1!=null && this.data[this.fieldRowIndex].field_5_8_1!=null&& Number(this.data[this.fieldRowIndex].field_5_1_1)+Number(this.data[this.fieldRowIndex].field_5_2_1)+Number(this.data[this.fieldRowIndex].field_5_3_1)+Number(this.data[this.fieldRowIndex].field_5_4_1)+Number(this.data[this.fieldRowIndex].field_5_5_1)+Number(this.data[this.fieldRowIndex].field_5_6_1)+Number(this.data[this.fieldRowIndex].field_5_7_1)+Number(this.data[this.fieldRowIndex].field_5_8_1)!=Number(this.data[this.fieldRowIndex].field_2)){
                // //console.log(Number(this.data[this.fieldRowIndex].field_5_1_1)+Number(this.data[this.fieldRowIndex].field_5_2_1)+Number(this.data[this.fieldRowIndex].field_5_3_1)+Number(this.data[this.fieldRowIndex].field_5_4_1)+Number(this.data[this.fieldRowIndex].field_5_5_1)+Number(this.data[this.fieldRowIndex].field_5_6_1)+Number(this.data[this.fieldRowIndex].field_5_7_1)+Number(this.data[this.fieldRowIndex].field_5_8_1));
              this.data[this.fieldRowIndex].field_5_9_1 = "填写有误！";
            }else if(this.data[this.fieldRowIndex].field_5_1_1!=null && this.data[this.fieldRowIndex].field_5_2_1!=null && this.data[this.fieldRowIndex].field_5_3_1!=null && this.data[this.fieldRowIndex].field_5_4_1!=null && this.data[this.fieldRowIndex].field_5_5_1!=null && this.data[this.fieldRowIndex].field_5_6_1!=null && this.data[this.fieldRowIndex].field_5_7_1!=null && this.data[this.fieldRowIndex].field_5_8_1!=null&& Number(this.data[this.fieldRowIndex].field_5_1_1)+Number(this.data[this.fieldRowIndex].field_5_2_1)+Number(this.data[this.fieldRowIndex].field_5_3_1)+Number(this.data[this.fieldRowIndex].field_5_4_1)+Number(this.data[this.fieldRowIndex].field_5_5_1)+Number(this.data[this.fieldRowIndex].field_5_6_1)+Number(this.data[this.fieldRowIndex].field_5_7_1)+Number(this.data[this.fieldRowIndex].field_5_8_1)==Number(this.data[this.fieldRowIndex].field_2)){
              this.data[this.fieldRowIndex].field_5_9_1 = "核对正确！";
            }else{
              this.data[this.fieldRowIndex].field_5_9_1 = null;
            }
          }
          if(x2 == 'field_5_8_2'){
            if(this.data[this.fieldRowIndex].field_5_1_1!=null && this.data[this.fieldRowIndex].field_5_2_1!=null && this.data[this.fieldRowIndex].field_5_3_1!=null && this.data[this.fieldRowIndex].field_5_4_1!=null && this.data[this.fieldRowIndex].field_5_5_1!=null && this.data[this.fieldRowIndex].field_5_6_1!=null && this.data[this.fieldRowIndex].field_5_7_1!=null && this.data[this.fieldRowIndex].field_5_8_1!=null && this.data[this.fieldRowIndex].field_5_7_1!=null && this.data[this.fieldRowIndex].field_5_8_1!=null){
              this.data[this.fieldRowIndex].field_5_9_2 = (Math.round(( (Number(this.data[this.fieldRowIndex].field_5_1_1)+Number(this.data[this.fieldRowIndex].field_5_2_1)+Number(this.data[this.fieldRowIndex].field_5_3_1)+Number(this.data[this.fieldRowIndex].field_5_4_1)+Number(this.data[this.fieldRowIndex].field_5_5_1)+Number(this.data[this.fieldRowIndex].field_5_6_1)+Number(this.data[this.fieldRowIndex].field_5_7_1)+Number(this.data[this.fieldRowIndex].field_5_8_1))/Number(this.data[this.fieldRowIndex].field_2) )* 100*100)/100).toFixed(2)+"%";
              // //console.log(this.data[this.fieldRowIndex].field_5_9_2);
            }else{
              this.data[this.fieldRowIndex].field_5_9_2 = null;
            }
          }
          if(x1 == 'field_6_7_1'){
            if(this.data[this.fieldRowIndex].field_6_1_1!=null && this.data[this.fieldRowIndex].field_6_2_1!=null && this.data[this.fieldRowIndex].field_6_3_1!=null && this.data[this.fieldRowIndex].field_6_4_1!=null && this.data[this.fieldRowIndex].field_6_5_1!=null && this.data[this.fieldRowIndex].field_6_6_1!=null && this.data[this.fieldRowIndex].field_6_7_1!=null && Number(this.data[this.fieldRowIndex].field_6_1_1)+Number(this.data[this.fieldRowIndex].field_6_2_1)+Number(this.data[this.fieldRowIndex].field_6_3_1)+Number(this.data[this.fieldRowIndex].field_6_4_1)+Number(this.data[this.fieldRowIndex].field_6_5_1)+Number(this.data[this.fieldRowIndex].field_6_6_1)+Number(this.data[this.fieldRowIndex].field_6_7_1)!=Number(this.data[this.fieldRowIndex].field_2)){
              this.data[this.fieldRowIndex].field_6_8_1 = "填写有误！";
            }else if(this.data[this.fieldRowIndex].field_6_1_1!=null && this.data[this.fieldRowIndex].field_6_2_1!=null && this.data[this.fieldRowIndex].field_6_3_1!=null && this.data[this.fieldRowIndex].field_6_4_1!=null && this.data[this.fieldRowIndex].field_6_5_1!=null && this.data[this.fieldRowIndex].field_6_6_1!=null && this.data[this.fieldRowIndex].field_6_7_1!=null && Number(this.data[this.fieldRowIndex].field_6_1_1)+Number(this.data[this.fieldRowIndex].field_6_2_1)+Number(this.data[this.fieldRowIndex].field_6_3_1)+Number(this.data[this.fieldRowIndex].field_6_4_1)+Number(this.data[this.fieldRowIndex].field_6_5_1)+Number(this.data[this.fieldRowIndex].field_6_6_1)+Number(this.data[this.fieldRowIndex].field_6_7_1)==Number(this.data[this.fieldRowIndex].field_2)){
              this.data[this.fieldRowIndex].field_6_8_1 = "核对正确！";
            }else{
              this.data[this.fieldRowIndex].field_6_8_1 = null;
            }
          }
          if(x2 == 'field_6_7_2'){
            if(this.data[this.fieldRowIndex].field_6_1_1!=null && this.data[this.fieldRowIndex].field_6_2_1!=null && this.data[this.fieldRowIndex].field_6_3_1!=null && this.data[this.fieldRowIndex].field_6_4_1!=null && this.data[this.fieldRowIndex].field_6_5_1!=null && this.data[this.fieldRowIndex].field_6_6_1!=null && this.data[this.fieldRowIndex].field_6_7_1!=null ){
              this.data[this.fieldRowIndex].field_6_8_2 =(Math.round(((Number(this.data[this.fieldRowIndex].field_6_1_1)+Number(this.data[this.fieldRowIndex].field_6_2_1)+Number(this.data[this.fieldRowIndex].field_6_3_1)+Number(this.data[this.fieldRowIndex].field_6_4_1)+Number(this.data[this.fieldRowIndex].field_6_5_1)+Number(this.data[this.fieldRowIndex].field_6_6_1)+Number(this.data[this.fieldRowIndex].field_6_7_1))/Number(this.data[this.fieldRowIndex].field_2))* 100*100)/100).toFixed(2)+"%";
            }else{
              this.data[this.fieldRowIndex].field_6_8_2 =null;
            }
          }
          this.data[this.fieldRowIndex].field_5_9_2 = (Math.round(( (Number(this.data[this.fieldRowIndex].field_5_1_1)+Number(this.data[this.fieldRowIndex].field_5_2_1)+Number(this.data[this.fieldRowIndex].field_5_3_1)+Number(this.data[this.fieldRowIndex].field_5_4_1)+Number(this.data[this.fieldRowIndex].field_5_5_1)+Number(this.data[this.fieldRowIndex].field_5_6_1)+Number(this.data[this.fieldRowIndex].field_5_7_1)+Number(this.data[this.fieldRowIndex].field_5_8_1))/Number(this.data[this.fieldRowIndex].field_2) )* 100*100)/100).toFixed(2)+"%";
          this.data[this.fieldRowIndex].field_6_8_2 =(Math.round(((Number(this.data[this.fieldRowIndex].field_6_1_1)+Number(this.data[this.fieldRowIndex].field_6_2_1)+Number(this.data[this.fieldRowIndex].field_6_3_1)+Number(this.data[this.fieldRowIndex].field_6_4_1)+Number(this.data[this.fieldRowIndex].field_6_5_1)+Number(this.data[this.fieldRowIndex].field_6_6_1)+Number(this.data[this.fieldRowIndex].field_6_7_1))/Number(this.data[this.fieldRowIndex].field_2))* 100*100)/100).toFixed(2)+"%";
          this.data[this.fieldRowIndex][x2] = (Math.round((this.data[this.fieldRowIndex][x1]/this.data[this.fieldRowIndex].field_2)* 100*100)/100).toFixed(2)+"%";
        },

        //检查input框的输入是否符合要求
        checkFirstFieldInput(e){
          var strValue = e.target.value;
          if(!isNaN(strValue)){ 
        }else{
          // e.target.parentNode.nextSibling.innerHTML = "X";
          e.target.value = "";
          alert("请输入数字！");
        }
        },

        handleClose(done) {
        var inputs = document.getElementsByClassName("secondFieldDetail");
        // if(inputs.length < this.secondList.length){
        //     this.$confirm('还未录入完成，确认退出？')
        //       .then(_ => {
        //         //不保存当前数据
        //         // this.clearAllInputs();
        //         done();
        //       })
        //       .catch(_ => {});          
        // }
        // else if(inputs.length == this.secondList.length){
        //   this.$confirm('录入数据是否保存？')
        //       .then(_ => {
        //         // this.clearAllInputs();
        //         done();
        //       })
        //       .catch(_ => {}); 
        // }else{
        //   this.$confirm('确认关闭？')
        //       .then(_ => {
        //         //不保存当前数据
        //         // this.clearAllInputs();
        //         done();
        //       })
        //       .catch(_ => {}); 
        // }
      },
      //取消按钮
      cancelOp(){
        this.detialDialogVisible = false;
      },
            //获取稻作类型数据
          brandKeyChange1:function(){
          axios({
              url:'/getSelectContent',
              method:'post',
              params:{
                  fieldName:"稻作类型",
                  riceType:'double'
              },
          }).then(res=>{

              this.daoZuoTypeData = res.data;

          }).catch(err=>{
              return this.$message.error("获取稻作类型失败！");
          });
          },

            //保存
            getFormList(flag){
              var date = this.getNowTime();
              for(var i = 0 ;i < this.data.length ; ++i){
                this.data[i].modification_time = date;
              }
              var list = this.data;
              var str = '';
              for(var i = 0 ; i < list.length ; ++i){
                  str += JSON.stringify(list[i]) + "#";
              }
              
              this.saveInputData(str);
              if(this.column_id != '' && this.tableName != ''){
                this.fixDataBackState(this.tableName,this.column_id);
              }

            },

            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTableTwo1',
                method:'post',
                params:{
                  data:list
                },
              }).then(res=>{
                //console.log(res);
                this.$message.success("操作成功！");
              }).catch(err=>{
                return this.$message.error("数据保存失败！");
              })

            },
            
          //修改数据退还部分状态
          fixDataBackState(tableName,id){
              axios({
                url:'/fixDataBackState',
                method:'post',
                params:{
                  tableName:tableName,
                  tableId:id
                }
              }).then(

              ).catch(
                _=>{
                  return this.$message.error("数据修改失败！");
                }
              );
            },
            //填写详细信息
            fillDetailInfo(e){
              var Index = e.target.parentNode.cellIndex;
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
              this.fieldRowIndex = this.fieldRowIndex-2;
              //console.log(this.fieldRowIndex);
              var getTdInnerHTML="";    
              getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
              this.tableFlag = getTdInnerHTML;
         
              // this.secondChooseField(getTdInnerHTML);
              this.detialDialogVisible = true;
            },

        // 获取当前时间
        getNowTime: function () {
              let dateTime
              let yy = new Date().getFullYear()
              let mm = new Date().getMonth() + 1
              let dd = new Date().getDate()
              let hh = new Date().getHours()
              let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes()
                :
                new Date().getMinutes()
              let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds()
                :
                new Date().getSeconds()
                if(hh < 10){
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
                }else{
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                }
              return dateTime
            },

        //查询该用户下所有的已知数据条数
        checkUserAllKnownData1(people,column_id){
          axios({
            url:'/checkUserAllKnownDataTwo1',
            method:'post',
            params:{
              UserID:people,
              column_id:''
            }
          }).then(res=>{           
            this.data = res.data;
            const json = JSON.stringify(res.data);
            this.copyData = JSON.parse(json);
          }).catch(err=>{
            return this.$message.error("用户所有信息查询失败！");
          });
        }
      },

      created(){


        //登录监测--判断当前用户是否在数据库中已经有填过数据，有填过的话，从数据库中读入县（市、区）、地区类型
        var people = localStorage.getItem('ms_username');
        if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
          this.column_id = this.$route.query.column_id;
          this.tableName = this.$route.query.record_table;
        }
        this.checkUserAllKnownData1(people,this.column_id);
        // this.getSelectContent3();
        
      },

      watch:{ //可以监听某个值的变化，如果变化则执行相应的函数

      },
  }
  </script>
<!-- <script>Vue.config.productionTip=false</script> -->
  <style>
    .demo-image__placeholder{
      
    }
 .el-dialog{
   top: 15%;
   left: 4%;
 }
 table tr, table tr td { border:1px solid lightgray; }
  </style>