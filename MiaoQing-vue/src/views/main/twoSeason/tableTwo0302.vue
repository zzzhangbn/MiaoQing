<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id == ''|| column_id == null">
       <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
              <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
          </router-link>
      </template>
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
              <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
             </router-link>
          </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;" @click="getFormList('1')">上 报</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr>
                    <td colspan="16" style="background-color: #6E8B3D;color: #FFFFFF;">年度监测县(市、区)水稻播栽进度调查表（晚稻）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td rowspan="2">县（市、区）</td>
                    <td rowspan="2">地区类型</td>
                    <td rowspan="2">稻作类型</td>
                    <td rowspan="2">全县总面积（万亩）</td>
                    <td colspan="2">开始播种日期</td>
                    <td  colspan="3">秧田面积（万亩）</td>
                    <td  colspan="4">折合大田面积（万亩）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td >育秧</td>
                    <td >直播</td>
                    <td style="text-align: center;width: 4%;" ><span style="width: 70%;height: 7%;">数值</span></td>
                    <td style="text-align: center;" colspan="2">其中集中育秧</td>
                    <td style="width: 6%;text-align: center;"><span style="width: 70%;">数值</span></td>
                    <td style="width: 6%;text-align: center;"><span style="width: 70%;">占比</span></td>
                    <td style="text-align: center;" colspan="2">其中集中育秧</td>
                </tr>
                <template v-for="(item,index) in data">
                <tr>
                    <td>
                        <el-input v-model="data[index].field_1" readonly></el-input>
                    </td>
                    <td style="width: 7%;">
                        <el-input v-model="data[index].field_2" style="width: 100%;" readonly></el-input>
                    </td>
                    <td>
                        <template v-if="copyData[index].field_3!=''&& data[index].field_3 != null && column_id == ''">
                            <el-input v-model="data[index].field_3" readonly></el-input>
                        </template>
                        <template v-else="data[index].field_3 ==''|| data[index].field_3 == null || column_id != ''">
                            <el-input v-model="data[index].field_3" @blur.prevent="checkFirstFieldInput($event)" ></el-input>
                        </template>
                        
                    </td>
                    <td>
                        <template v-if="copyData[index].field_4!=''&& data[index].field_4 != null && column_id == ''">
                            <el-input v-model="data[index].field_4" readonly></el-input>
                        </template>
                        <template v-else="data[index].field_4 ==''|| data[index].field_4 == null || column_id != ''">
                            <el-input v-model="data[index].field_4" @blur.prevent="checkFirstFieldInput($event)" ></el-input>
                        </template>
                        
                    </td>
                    <td >
                        <template v-if="copyData[index].field_5_1 != null && copyData[index].field_5_1 != '' && column_id == ''">
                            <el-date-picker
                            v-model="data[index].field_5_1" readonly
                            type="date"
                            placeholder="选择日期"></el-date-picker>
                        </template>
                        <template v-else="copyData[index].field_5_1 == null || copyData[index].field_5_1 == '' || column_id != ''">
                            <el-date-picker
                            v-model="data[index].field_5_1"
                            type="date"
                            placeholder="选择日期"></el-date-picker>
                        </template>
                    </td>
                    <td >
                        <template v-if="copyData[index].field_5_2 != null && copyData[index].field_5_2 != '' && column_id == ''">
                            <el-date-picker
                            v-model="data[index].field_5_2" readonly
                            type="date"
                            placeholder="选择日期"></el-date-picker>
                        </template>
                        <template v-else="copyData[index].field_5_2 == null || copyData[index].field_5_2 == '' || column_id != ''">
                            <el-date-picker
                            v-model="data[index].field_5_2"
                            type="date"
                            placeholder="选择日期">
                        </el-date-picker>
                        </template>
                    </td>

                    <td style="width:5%">
                        <el-input v-model="data[index].field_6" :disabled="copyData[index].field_6 !=='' && copyData[index].field_6 !== null && column_id == ''"></el-input>
                    </td>

                        <td style="width:5%">
                            <el-input v-model="data[index].field_7_1" @blur.prevent="getField62($event,index)" 
                            :disabled="copyData[index].field_7_1 !=='' && copyData[index].field_7_1 !== null && column_id == ''"></el-input>
                        </td>
                        <td style="width:6%">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                            color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;width: 100%;" v-model="data[index].field_7_2" disabled ></input>
                        </td>
                    
                    
                        <td style="width:4%">
                            <el-input v-model="data[index].field_8_1" @blur.prevent="getField72($event)" 
                            :disabled="copyData[index].field_8_1 !=='' && copyData[index].field_8_1 !== null && column_id == ''"></el-input>
                        </td>
                        <td style="width:6%">
                            <input v-model="data[index].field_8_2" disabled style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                            color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;width: 100%;" ></input>
                        </td>
                 
                    <td style="width:5%">
                        <el-input v-model="data[index].field_9_1" @blur.prevent="getField82($event,index)" 
                        :disabled="copyData[index].field_9_1 !=='' && copyData[index].field_9_1 !== null && column_id == ''"></el-input>
                    </td>
                    <td style="width:6%">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                        color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;width: 100%;" v-model="data[index].field_9_2" disabled></input>
                    </td>
                </tr>
            </template>
            </table>
    <div style="width: 100%;height: 10px;"></div>
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                
                <template v-for="(item,index) in data">
                    <tr>
                        <template v-if="data[index].field_10_1 == null || data[index].field_10_1 == '' || data[index].field_10_2_1 == null || data[index].field_10_2_1 == '' || data[index].field_10_2_2 == null 
                        || data[index].field_10_2_2 == '' || data[index].field_10_3 == null || data[index].field_10_3 == '' || data[index].field_10_4 == null || data[index].field_10_4 == '' || data[index].field_10_5 == null
                        || data[index].field_10_5 == '' || data[index].field_10_6 == '' || data[index].field_10_6 == null">
                            <td style="color: red;">播栽期进度1（7月15日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度1')"></i>
                            </td>
                        </template>
                        <template v-if="data[index].field_10_1 != null && data[index].field_10_1 != '' && data[index].field_10_2_1 != null && data[index].field_10_2_1 != '' && data[index].field_10_2_2 != null 
                        && data[index].field_10_2_2 != '' && data[index].field_10_3 != null && data[index].field_10_3 != '' && data[index].field_10_4 != null && data[index].field_10_4 != '' && data[index].field_10_5 != null
                        && data[index].field_10_5 != '' && data[index].field_10_6 != '' && data[index].field_10_6 != null">
                            <td style="color: green;">
                                播栽期进度1（7月15日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度1')"></i>
                            </td>
                        </template>

                        <template v-if="data[index].field_11_1 == null || data[index].field_11_1 == '' || data[index].field_11_2_1 == null || data[index].field_11_2_1 == '' || data[index].field_11_2_2 == null 
                        || data[index].field_11_2_2 == '' || data[index].field_11_3 == null || data[index].field_11_3 == '' || data[index].field_11_4 == null || data[index].field_11_4 == '' || data[index].field_11_5 == null
                        || data[index].field_11_5 == '' || data[index].field_11_6 == '' || data[index].field_11_6 == null">
                            <td style="color: red;">播栽期进度2（7月25日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度2')"></i>
                            </td>
                        </template>
                        <template v-if="data[index].field_11_1 != null && data[index].field_11_1 != '' && data[index].field_11_2_1 != null && data[index].field_11_2_1 != '' && data[index].field_11_2_2 != null 
                        && data[index].field_11_2_2 != '' && data[index].field_11_3 != null && data[index].field_11_3 != '' && data[index].field_11_4 != null && data[index].field_11_4 != '' && data[index].field_11_5 != null
                        && data[index].field_11_5 != '' && data[index].field_11_6 != '' && data[index].field_11_6 != null">
                            <td style="color: green;">播栽期进度2（7月25日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度2')"></i>
                            </td>
                        </template>

                        <template v-if="data[index].field_12_1 == null || data[index].field_12_1 == '' || data[index].field_12_2_1 == null || data[index].field_12_2_1 == '' || data[index].field_12_2_2 == null 
                        || data[index].field_12_2_2 == '' || data[index].field_12_3 == null || data[index].field_12_3 == '' || data[index].field_12_4 == null || data[index].field_12_4 == '' || data[index].field_12_5 == null
                        || data[index].field_12_5 == '' || data[index].field_12_6 == '' || data[index].field_12_6 == null">
                            <td style="color:red">播栽期进度3（8月5日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度3')"></i>
                            </td>
                        </template>
                        <template v-if="data[index].field_12_1 != null && data[index].field_12_1 != '' && data[index].field_12_2_1 != null && data[index].field_12_2_1 != '' && data[index].field_12_2_2 != null 
                        && data[index].field_12_2_2 != '' && data[index].field_12_3 != null && data[index].field_12_3 != '' && data[index].field_12_4 != null && data[index].field_12_4 != '' && data[index].field_12_5 != null
                        && data[index].field_12_5 != '' && data[index].field_12_6 != '' && data[index].field_12_6 != null">
                            <td style="color: green;">播栽期进度3（8月5日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度2')"></i>
                            </td>
                        </template>

                        <!-- <template v-if="data[index].field_13_1 == null || data[index].field_13_1 == '' || data[index].field_13_2 == null || data[index].field_13_2 == ''  
                        || data[index].field_13_3 == null || data[index].field_13_3 == '' || data[index].field_13_4 == null || data[index].field_13_4 == '' || data[index].field_13_5 == null
                        || data[index].field_13_5 == '' ">
                            <td style="color:red">播栽期进度4（4月25日）
                                <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度4')"></i>
                            </td>
                        </template>
                        <template v-if="data[index].field_13_1 != null && data[index].field_13_1 != '' && data[index].field_13_2 != null && data[index].field_13_2 != '' 
                        && data[index].field_13_3 != null && data[index].field_13_3 != '' && data[index].field_13_4 != null && data[index].field_13_4 != '' && data[index].field_13_5 != null
                        && data[index].field_13_5 != '' ">
                        <td style="color:green">播栽期进度4（4月25日）
                            <i class='el-icon-caret-top' @click="foldOrNot($event,'播栽期进度4')"></i>
                        </td>
                        </template> -->
               
                </tr>
                <template v-if="fold1 == true || fold2 == true || fold3 == true">
                    <tr>
                        <td>日期</td>
                            <td colspan="2">育秧已播种面积（万亩）</td>
                            <td>已栽插面积（万亩）</td>                       
                            <td>直播已播种面积（万亩）</td>
                            <td>小计</td>
                            <td>占比</td>
                    </tr>
                </template>
                <!-- <template v-if="fold4 == true">
                    <tr>
                        <td>日期</td>
                            
                            <td>已栽插面积（万亩）</td>                       
                            <td>直播已播种面积（万亩）</td>
                            <td>小计</td>
                            <td>占比</td>
                    </tr>
                </template> -->
                <template v-if="fold1 == true">
                    <tr>
                        <td>
                            <el-date-picker 
                                v-model="data[index].field_10_1" :disabled="copyData[index].field_10_1 !=='' && copyData[index].field_10_1 !== null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[index].field_10_2_1"  @blur.prevent="getField922($event,index)" 
                            :disabled="copyData[index].field_10_2_1 !=='' && copyData[index].field_10_2_1 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_10_2_2" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_10_3" @blur.prevent="getSumArea('field_10_4','field_10_3')" 
                            :disabled="copyData[index].field_10_3 !=='' && copyData[index].field_10_3 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_10_4" @blur.prevent="getSumArea('field_10_4','field_10_3')" 
                            :disabled="copyData[index].field_10_4 !=='' && copyData[index].field_10_4 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_10_5" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_10_6" disabled></el-input></td>
                    </tr>
                </template>

                <template v-if="fold2 == true">
                    <tr>
                        <td>
                            <el-date-picker
                                v-model="data[index].field_11_1" :disabled="copyData[index].field_11_1 !=='' && copyData[index].field_11_1 !== null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[index].field_11_2_1"  @blur.prevent="getField1022($event,index)" 
                            :disabled="copyData[index].field_11_2_1 !=='' && copyData[index].field_11_2_1 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_11_2_2" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_11_3" @blur.prevent="getSumArea('field_11_4','field_11_3',index)" 
                            :disabled="copyData[index].field_11_3 !=='' && copyData[index].field_11_3 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_11_4" @blur.prevent="getSumArea('field_11_4','field_11_3',index)" 
                            :disabled="copyData[index].field_11_4 !=='' && copyData[index].field_11_4 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_11_5" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_11_6" disabled></el-input></td>
                    </tr>
                </template>
                <template v-if="fold3 == true">
                    <tr>
                        <td>
                            <el-date-picker
                                v-model="data[index].field_12_1" :disabled="copyData[index].field_12_1 !=='' && copyData[index].field_12_1 !== null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[index].field_12_2_1"  @blur.prevent="getField1122($event,index)" 
                            :disabled="copyData[index].field_12_2_1 !=='' && copyData[index].field_12_2_1 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_12_2_2" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_12_3" @blur.prevent="getSumArea('field_12_4','field_12_3',index)" 
                            :disabled="copyData[index].field_12_3 !=='' && copyData[index].field_12_3 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_12_4" @blur.prevent="getSumArea('field_12_4','field_12_3',index)"
                             :disabled="copyData[index].field_12_4 !=='' && copyData[index].field_12_4 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_12_5" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_12_6" disabled></el-input></td> 
                    </tr>
                </template>
                <!-- <template v-if="fold4 == true">
                    <tr>
                        <td>
                            <el-date-picker
                                v-model="data[index].field_13_1" :disabled="copyData[index].field_13_1 !=='' && copyData[index].field_13_1 !== null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[index].field_13_2"  @blur.prevent="getSumArea('field_13_3','field_13_2',index)" 
                            :disabled="copyData[index].field_13_2 !=='' && copyData[index].field_13_2 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_13_3"  @blur.prevent="getSumArea('field_13_3','field_13_2',index)" 
                            :disabled="copyData[index].field_13_3 !=='' && copyData[index].field_13_3 !== null && column_id == ''"></el-input></td>
                        <td><el-input v-model="data[index].field_13_4" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_13_5" disabled></el-input></td>
                    </tr>
                </template> -->

                    <div style="width: 100%;height: 10px;"></div>
                </template>
            </table>


        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    export default{
        data(){
            return{
                test:'',
                data:[],
                copyData:[],
                fieldColIndex:0,
                fieldRowIndex:0,
                tableFlag:'',
                detialDialogVisible:false,
                fold1:false,//播载期1
                fold2:false,//播载期2
                fold3:false,//播载期3
                fold4:false,//播载期4
                column_id :'',
                tableName:'',
                tableId:'',
            }
        },
        methods:{
            foldOrNot(e,tag){
                if(tag == "播栽期进度1"){

                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold1 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold1 = true;
                }
            }
            if(tag == "播栽期进度2"){
         
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold2 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold2 = true;
                }
            }
            if(tag == "播栽期进度3"){
             
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold3 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold3 = true;
                }
            } 
            // if(tag == "播栽期进度4"){
 
            //     if(e.target.className == "el-icon-caret-bottom"){
            //         e.target.className = "el-icon-caret-top";
            //         this.fold4 = false;
            //     }else{
            //         e.target.className = "el-icon-caret-bottom";
            //         this.fold4 = true;
            //     }
            // }   
                
                
            },

            //上报
            getFormList(e){
            this.$confirm("是否上报？").then(_=>{
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
            }).catch(_=>{
            return this.$message.info("您取消了操作！");
            });
               
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

            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTableTwo0302',
                method:'post',
                params:{
                  data:list
                }
              }).then(res=>{
                //console.log(res);
                this.$message.success("操作成功！");
              }).catch(err=>{
                return this.$message.error("数据保存失败！");
              })

            },

            //生成小计
            getSumArea(x1,x2,fieldRowIndex){
                
                if(x1 == 'field_10_4'){
                    this.data[fieldRowIndex].field_10_5 = Number(this.data[fieldRowIndex].field_10_4)+Number(this.data[fieldRowIndex].field_10_3);
                    this.data[fieldRowIndex].field_10_6 = (Math.round((this.data[fieldRowIndex].field_10_5/this.data[fieldRowIndex].field_4)* 100*100)/100).toFixed(2)+"%";
                    // this.data[this.fieldRowIndex][x2] = (Math.round((this.data[this.fieldRowIndex][x1]/this.data[this.fieldRowIndex].field_2)* 100*100)/100).toFixed(2)+"%";
                }
                if(x1 == 'field_11_4'){
                    this.data[fieldRowIndex].field_11_5 = Number(this.data[fieldRowIndex].field_11_4)+Number(this.data[fieldRowIndex].field_11_4);
                    this.data[fieldRowIndex].field_11_6 = (Math.round((this.data[fieldRowIndex].field_11_5/this.data[fieldRowIndex].field_4)* 100*100)/100).toFixed(2)+"%";
                }
                if(x1 == 'field_12_4'){
                    this.data[fieldRowIndex].field_12_5 = Number(this.data[fieldRowIndex].field_12_4)+Number(this.data[fieldRowIndex].field_12_3);
                    this.data[fieldRowIndex].field_12_6 = (Math.round((this.data[fieldRowIndex].field_12_5/this.data[fieldRowIndex].field_4)* 100*100)/100).toFixed(2)+"%";
                }
                // if(x1 == 'field_12_3'){
                //     this.data[fieldRowIndex].field_13_4 = Number(this.data[fieldRowIndex].field_13_3)+Number(this.data[fieldRowIndex].field_13_2);
                //     this.data[fieldRowIndex].field_13_5 = (Math.round((this.data[fieldRowIndex].field_13_4/this.data[fieldRowIndex].field_4)* 100*100)/100).toFixed(2)+"%";
                // }

            },

            //将建档卡2-1中已知内容插入到表3-1中
            getMonitorCardContentInto0301(people){
                axios({
                    url:'/getMonitorCardContentIntoTwo0302',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                }).catch(err=>{
                    return this.$message.error("获取表3-2内容失败！");
                });
            },
            //查询该用户下数据
            getUserAllData0301(people,column_id){
                axios({
                    url:'/getUserAllDataTwo0302',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id,
                        startYear:0,
                        endYear:0
                    }
                }).then(res=>{
                    this.data = res.data;
                    const json=JSON.stringify(res.data);
                    this.copyData = JSON.parse(json);
                    
                }).catch(err=>{
                    return this.$message.error("获取表3-2内容失败！");
                });
            },
            
            //填写详细信息
            fillDetailInfo(e){
              var Index = e.target.parentNode.cellIndex;
              this.fieldColIndex = Index;//把当前点击的字段所在列保存
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
              
              this.fieldRowIndex = this.fieldRowIndex-3;
              var getTdInnerHTML="";
              getTdInnerHTML = e.target.id;
              this.tableFlag = getTdInnerHTML;

              this.detialDialogVisible = true;
            },
            cancelOp(){
                this.detialDialogVisible = false;
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
            //field_6_2
            getField62(e,fieldRowIndex){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_6_2 = Number((this.data[this.fieldRowIndex].field_6_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[fieldRowIndex].field_7_2 = (Math.round((this.data[fieldRowIndex].field_7_1 /this.data[fieldRowIndex].field_6) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField72(e,fieldRowIndex){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_7_2 = Number((this.data[this.fieldRowIndex].field_7_1/this.data[this.fieldRowIndex].field_3).toFixed(4))*100+"%";
                    this.data[fieldRowIndex].field_8_2 = (Math.round((this.data[fieldRowIndex].field_8_1 /this.data[fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField82(e,fieldRowIndex){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_8_2 = Number((this.data[this.fieldRowIndex].field_8_1/this.data[this.fieldRowIndex].field_3).toFixed(4))*100+"%";
                    this.data[fieldRowIndex].field_9_2 = (Math.round((this.data[fieldRowIndex].field_9_1 /this.data[fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField922(e,fieldRowIndex){
                //console.log(fieldRowIndex);
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_9_2_2 = Number((this.data[this.fieldRowIndex].field_9_2_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[fieldRowIndex].field_10_2_2 = (Math.round((this.data[fieldRowIndex].field_10_2_1 /this.data[fieldRowIndex].field_6) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField1022(e,fieldRowIndex){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_9_2_2 = Number((this.data[this.fieldRowIndex].field_9_2_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[fieldRowIndex].field_11_2_2 = (Math.round((this.data[fieldRowIndex].field_11_2_1 /this.data[fieldRowIndex].field_6) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField1122(e,fieldRowIndex){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_9_2_2 = Number((this.data[this.fieldRowIndex].field_9_2_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[fieldRowIndex].field_12_2_2 = (Math.round((this.data[fieldRowIndex].field_12_2_1 /this.data[fieldRowIndex].field_6) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
        }
        if (this.$route.query.column_id == null ||this.$route.query.column_id == undefined ||this.$route.query.column_id == "") {
      this.column_id = '';
    }
            this.getUserAllData0301(people,this.column_id);
        },
       
    }
</script>