<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
              <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <template v-if="column_id == ''|| column_id == null">
            <!-- <router-link to='/tablemain'> -->
			<router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
              <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交1</el-button> -->
        <div id="containner_div" style="width: 100%;height: 70%;overflow-x : auto;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;overflow-x: auto ;" border="1" cellspacing="0">
                <tr >
                    <td colspan="17" style="background-color: #6E8B3D;color: #FFFFFF;">年度监测县(市、区)水稻生产成本效益统计表</td>
                </tr>
                    <tr  style="background-color: #CDBE70;color: green;">
                        <td >县（市、区）</td>
                        <td>地区类型</td>
                        <td>稻作类型</td>
                        <td>品种类型</td>
                        <td style="width: 7.5%;">监测田块</td>
                        <td style="width: 5%;">代表面积（万亩）</td>
                        <td>栽植方式</td>
                        <td>水稻亩产值（元）</td>
                        <td>其中主产品产值（公斤/元）</td>
                        <td>副产品产值（公斤/元）</td>
                        <td style="width: 4%;">劳动用工</td>
                        <td style="width: 4%;">物质费用</td>
                        <td>地租（元/亩）</td>
                        <td>其他费用（元/亩）</td>
                        <td>亩总成本（元）</td>
                        <td>亩收益（元）</td>
                        <td>提交表项</td>
                    </tr>
                   <template v-for="(item,index) in data"> 
                    <tr>
                        <td>
                            <el-input v-model="data[index].field_1" :disabled="copyData[index].field_1 != null && copyData[index].field_1 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_2" :disabled="copyData[index].field_2 != null && copyData[index].field_2 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <!-- <el-input v-model="data[index].field_3" :disabled="copyData[index].field_3 != null && copyData[index].field_3 != '' && column_id == ''"></el-input> -->
                            <template v-if="copyData[index].field_3 != null && copyData[index].field_3 != '' && column_id == ''">
                                <el-input v-model="data[index].field_3" readonly></el-input>
                              </template> 
                              <template v-if="copyData[index].field_3 == null || copyData[index].field_3 == '' || column_id != '' ">
                                  <el-select v-model="data[index].field_3" placeholder="请选择" @focus="brandKeyChange1">
                                        <el-option
                                            v-for="item in daoZuoTypeData"
                                            :key="item.id"
                                            :label="item.list"
                                            :value="item.list">
                                        </el-option>
                                  </el-select>
                              </template>
                        </td>
                        <td>
                            <!-- <el-input v-model="data[index].field_4" :disabled="copyData[index].field_4 != null && copyData[index].field_4 != '' && column_id == ''"></el-input> -->
                            <template v-if="copyData[index].field_4 != null && copyData[index].field_4 != '' && column_id == ''">
                                <el-input v-model="data[index].field_4" readonly></el-input>
                              </template> 
                              <template v-if="copyData[index].field_4 == null || copyData[index].field_4 == '' || column_id != '' ">
                                  <el-select v-model="data[index].field_4" placeholder="请选择" @focus="brandKeyChange2">
                                        <el-option
                                            v-for="item in pinXiTypeData"
                                            :key="item.id"
                                            :label="item.list"
                                            :value="item.list">
                                        </el-option>
                                  </el-select>
                              </template>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_5" :disabled="copyData[index].field_5 != null && copyData[index].field_5 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_6" :disabled="copyData[index].field_6 != null && copyData[index].field_6 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_7" :disabled="copyData[index].field_7 != null && copyData[index].field_7 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_8" @blur.prevent="checkAllLiYun($event)" 
                            :disabled="copyData[index].field_8 != null && copyData[index].field_8 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_9" :disabled="copyData[index].field_9 != null && copyData[index].field_9 != '' && column_id == ''"></el-input></td>
                        <td>
                            <el-input v-model="data[index].field_10" :disabled="copyData[index].field_10 != null && copyData[index].field_10 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <!-- <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span> -->
                            <span :class="{tianxie: !tianxie_1_show[index], tianxie_full: tianxie_1_show[index]}" onmouseover="this.style.cursor='pointer'" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <!-- <span :class="{tianxie: !tianxie_2_show, tianxie_full: tianxie_2_show}" onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span> -->
                            <span :class="{tianxie: !tianxie_2_show[index], tianxie_full: tianxie_2_show[index]}" onmouseover="this.style.cursor='pointer'" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_13" @blur.prevent="checkAllCost($event)" :disabled="copyData[index].field_13 != null && copyData[index].field_13 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_14" @blur.prevent="checkAllCost($event)" :disabled="copyData[index].field_14 != null && copyData[index].field_14 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_15" disabled></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_16" disabled></el-input>
                        </td>
                        <td>
                            <el-button style="display: inline-block; float: right;margin-top: 1%;" :disabled="copyData[index].in_use != '0'" @click="getFormList(index)">提交</el-button>
                        </td>
                        <td>
                            <el-input v-if='show' v-model="data[index].in_use" disabled></el-input>
                        </td>
                    </tr>

                </template>
            </table>

            <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="90%">
                <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                    <template v-if="tableFlag == '劳动用工'">
                        <tr  style="background-color: #CDBE70;color: green;">
                            <td colspan="2">育秧</td>
                            <td colspan="2">耕作</td>
                            <td colspan="2">移栽</td>
                            <td colspan="2">直播</td>
                            <td colspan="2">大田管理</td>
                            <td colspan="2">小计</td>
                        </tr>
                        <tr  style="background-color: #CDBE70;color: green;">
                            <td>数量（个/亩）</td>
                            <td>单位用工价格（元/个）</td>
                            <td>数量（个/亩）</td>
                            <td>单位用工价格（元/个）</td>
                            <td>数量（个/亩）</td>
                            <td>单位用工价格（元/个）</td>
                            <td>数量（个/亩）</td>
                            <td>单位用工价格（元/个）</td>
                            <td>数量（个/亩）</td>
                            <td>单位用工价格（元/个）</td>
                            <td>数量（个/亩）</td>
                            <td>价格（元/亩）</td>
                        </tr>
                        <tr>
                            <template v-if="data[fieldRowIndex].field_7.indexOf('撒直播') != -1 || data[fieldRowIndex].field_7.indexOf('机条播') != -1">
                               <td><el-input v-model="data[fieldRowIndex].field_11_1_1"  disabled></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_1_2" disabled></el-input></td>                             
                            </template>
                            <template v-if="data[fieldRowIndex].field_7.indexOf('撒直播') == -1 && data[fieldRowIndex].field_7.indexOf('机条播') == -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_1_1" @blur.prevent="checkFirstFieldInput($event)" 
                                    :disabled="copyData[fieldRowIndex].field_11_1_1 != null && copyData[fieldRowIndex].field_11_1_1 != '' && column_id == ''"></el-input></td>
                                 <td><el-input v-model="data[fieldRowIndex].field_11_1_2"  @blur.prevent="checkSecondFieldInput($event)"
                                    :disabled="copyData[fieldRowIndex].field_11_1_2 != null && copyData[fieldRowIndex].field_11_1_2 != '' && column_id == ''"></el-input></td>                             
                             </template>

                            <td><el-input v-model="data[fieldRowIndex].field_11_2_1" @blur.prevent="checkFirstFieldInput($event)"
                                :disabled="copyData[fieldRowIndex].field_11_2_1 != null && copyData[fieldRowIndex].field_11_2_1 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_11_2_2" @blur.prevent="checkSecondFieldInput($event)"
                                :disabled="copyData[fieldRowIndex].field_11_2_2 != null && copyData[fieldRowIndex].field_11_2_2 != '' && column_id == ''"></el-input></td>

                            <template v-if="data[fieldRowIndex].field_7.indexOf('撒直播') != -1 || data[fieldRowIndex].field_7.indexOf('机条播') != -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_1" disabled></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_2" disabled></el-input></td>
                            </template>
                            <template v-if="data[fieldRowIndex].field_7.indexOf('撒直播') == -1 && data[fieldRowIndex].field_7.indexOf('机条播') == -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_1" @blur.prevent="checkFirstFieldInput($event)"
                                    :disabled="copyData[fieldRowIndex].field_11_3_1 != null && copyData[fieldRowIndex].field_11_3_1 != '' && column_id == ''"></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_2" @blur.prevent="checkSecondFieldInput($event)"
                                    :disabled="copyData[fieldRowIndex].field_11_3_2 != null && copyData[fieldRowIndex].field_11_3_2 != '' && column_id == ''"></el-input></td>
                            </template>

                            <template v-if="data[fieldRowIndex].field_7.indexOf('人工移栽') != -1 || data[fieldRowIndex].field_7.indexOf('机械插秧') != -1 || data[fieldRowIndex].field_7.indexOf('抛秧') != -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_1" disabled></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_2" disabled></el-input></td>
                            </template>
                            <template v-if="data[fieldRowIndex].field_7.indexOf('人工移栽') == -1 && data[fieldRowIndex].field_7.indexOf('机械插秧') == -1 && data[fieldRowIndex].field_7.indexOf('抛秧') == -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_1" @blur.prevent="checkFirstFieldInput($event)"
                                    :disabled="copyData[fieldRowIndex].field_11_4_1 != null && copyData[fieldRowIndex].field_11_4_1 != '' && column_id == ''"></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_2" @blur.prevent="checkSecondFieldInput($event)"
                                    :disabled="copyData[fieldRowIndex].field_11_4_2 != null && copyData[fieldRowIndex].field_11_4_2 != '' && column_id == ''"></el-input></td>
                            </template>

                            <td><el-input v-model="data[fieldRowIndex].field_11_5_1" @blur.prevent="checkFirstFieldInput($event)"
                                :disabled="copyData[fieldRowIndex].field_11_5_1 != null && copyData[fieldRowIndex].field_11_5_1 != '' && column_id == ''"> </el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_11_5_2" @blur.prevent="checkSecondFieldInput($event)"
                                :disabled="copyData[fieldRowIndex].field_11_5_2 != null && copyData[fieldRowIndex].field_11_5_2 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_11_6_1" @blur.prevent="checkFirstFieldInput($event)" disabled></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_11_6_2" @blur.prevent="checkSecondFieldInput($event)" disabled></el-input></td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '物质费用'">
                        <tr  style="background-color: #CDBE70;color: green;">
                            <td rowspan="2">种子（元/亩）</td>
                            <td colspan="4">化肥</td>
                            <td rowspan="2">有机肥（元/亩）</td>
                            <td rowspan="2">农药（元/亩）</td>
                            <td rowspan="2">灌溉（元/亩）</td>
                            <td rowspan="2">机械耕作（元/亩）</td>
                            <td rowspan="2">机械插秧/直播
                                （元/亩）</td>
                            <td rowspan="2">机械收获（元/亩）</td>
                            <td rowspan="2">其他（元/亩）</td>
                            <td rowspan="2">小计（元/亩）</td>
                        </tr>
                        <tr   style="background-color: #CDBE70;color: green;">
                            <td>种类</td>
                            <td>施用量（kg/亩）</td>
                            <td>价格(元/kg)</td>
                            <td>小计（元/亩）</td>
                        </tr>
                        <tr>
                            <td><el-input v-model="data[fieldRowIndex].field_12_1" @blur.prevent="checkWuZhiCost($event)" 
                                :disabled="copyData[fieldRowIndex].field_12_1 != null && copyData[fieldRowIndex].field_12_1 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_1" 
                                :disabled="copyData[fieldRowIndex].field_12_2_1 != null && copyData[fieldRowIndex].field_12_2_1 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_2" @blur.prevent="checkHuaFeiAudit($event)" 
                                :disabled="copyData[fieldRowIndex].field_12_2_2 != null && copyData[fieldRowIndex].field_12_2_2 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_3" @blur.prevent="checkHuaFeiAudit($event)"
                                :disabled="copyData[fieldRowIndex].field_12_2_3 != null && copyData[fieldRowIndex].field_12_2_3 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_4" disabled></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_3"   @blur.prevent="checkWuZhiCost($event)" 
                                :disabled="copyData[fieldRowIndex].field_12_3 != null && copyData[fieldRowIndex].field_12_3 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_4"  @blur.prevent="checkWuZhiCost($event)"
                                :disabled="copyData[fieldRowIndex].field_12_4 != null && copyData[fieldRowIndex].field_12_4 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_5" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="copyData[fieldRowIndex].field_12_5 != null && copyData[fieldRowIndex].field_12_5 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_6" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="copyData[fieldRowIndex].field_12_6 != null && copyData[fieldRowIndex].field_12_6 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_7" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="copyData[fieldRowIndex].field_12_7 != null && copyData[fieldRowIndex].field_12_7 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_8" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="copyData[fieldRowIndex].field_12_8 != null && copyData[fieldRowIndex].field_12_8 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_9" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="copyData[fieldRowIndex].field_12_9 != null && copyData[fieldRowIndex].field_12_9 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_10" disabled></el-input></td>
                        </tr>

                    </template>
                </table>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelOp">关 闭</el-button>
                    <el-button type="primary" @click="close">确 定</el-button>
                  </span>
            </el-dialog>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
import Vue from 'vue';
    export default{
        data(){
            return{
                daoZuoTypeData:[],//稻作类型
                data:[],
                pinXiTypeData:[],
                zaiZhiTypeData:[],//栽植方式
                fieldRowIndex:0,
                tableFlag:'',
                detialDialogVisible:false,
                tableName:'',
                column_id:'',
                copyData:[],
                show: false, //隐藏列
                tianxie_1_show: [false],
                tianxie_2_show:[false],

            }
        },
        methods:{
            //逐条上报
            getFormList(e){
                    console.log(this.data[e]);
                this.$confirm("是否上报？").then(_=>{
                    if(this.data[e].in_use != 1){
                        var date = this.getNowTime();
                        this.data[e].modification_time = date;
                        this.data[e].in_use = 1;
                        console.log(this.data[e]);
                        var str = '';

                        str += JSON.stringify(this.data[e]);
                        this.saveInputData(str);

                        if(this.column_id != '' && this.tableName != ''){
                            this.fixDataBackState(this.tableName,this.column_id);
                        }
                    }else{
                        return this.$message.error("数据不可保存！");
                    }
                    
                }).catch(_=>{
                    return this.$message.info("您取消了操作！");
                });
            },

            //上报
            // getFormList(e){
            //     console.log("e",e);
            //     console.log("data",this.data);
            //     this.$confirm("是否上报？").then(_=>{
            //         var date = this.getNowTime();
            //   for(var i = 0 ;i < this.data.length ; ++i){
            //     this.data[i].modification_time = date;
            //   }
            //     var list = this.data;
            //   var str = '';
            //   for(var i = 0 ; i < list.length ; ++i){
            //       str += JSON.stringify(list[i]) + "#";
            //   }
            //   this.saveInputData(str);
            //   if(this.column_id != '' && this.tableName != ''){
            //     this.fixDataBackState(this.tableName,this.column_id);
            //   }
            //     }).catch(_=>{
            //     return this.$message.info("您取消了操作！");
            //     });
            // },

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
                url:'/getSituationInputDataTable0801',
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
            //获取品系类型数据
            brandKeyChange2:function(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"品系类型",
                        riceType:'one'
                    },
                }).then(res=>{
                    this.pinXiTypeData = res.data;
                }).catch(err=>{
                    return this.$message.error("获取品系类型失败！");
                });
            },
            //获取当前账号下所有的监测点信息
            getCurrentAccountMonitorInfo(people){
                axios({
                    url:'/getCurrentAccountMonitorInfo',
                    method:'post',
                    params:{
                        account:people
                    }
                }).then(res=>{
                    console.log(res.data);
                }).catch(err=>{
                    return this.$message.error("获取所有监测点信息失败！");
                });
            },
            //数量求和
            checkFirstFieldInput(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                   this.data[this.fieldRowIndex].field_11_6_1 = Number(this.data[this.fieldRowIndex].field_11_1_1) + Number(this.data[this.fieldRowIndex].field_11_2_1) 
                   + Number(this.data[this.fieldRowIndex].field_11_3_1) + Number(this.data[this.fieldRowIndex].field_11_4_1) + Number(this.data[this.fieldRowIndex].field_11_5_1);
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            //价格求和
            checkSecondFieldInput(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    this.data[this.fieldRowIndex].field_11_6_2 = Number(this.data[this.fieldRowIndex].field_11_1_1)*Number(this.data[this.fieldRowIndex].field_11_1_2)+
                                                                Number(this.data[this.fieldRowIndex].field_11_2_1)*Number(this.data[this.fieldRowIndex].field_11_2_2)+
                                                                Number(this.data[this.fieldRowIndex].field_11_3_1)*Number(this.data[this.fieldRowIndex].field_11_3_2)+
                                                                Number(this.data[this.fieldRowIndex].field_11_4_1)*Number(this.data[this.fieldRowIndex].field_11_4_2)+
                                                                Number(this.data[this.fieldRowIndex].field_11_5_1)*Number(this.data[this.fieldRowIndex].field_11_5_2);
                    this.checkAllCost();
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            //化肥价格小计
            checkHuaFeiAudit(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                   this.data[this.fieldRowIndex].field_12_2_4 = Number(this.data[this.fieldRowIndex].field_12_2_2)*Number(this.data[this.fieldRowIndex].field_12_2_3);
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            //自动求和物质费用价格
            checkWuZhiCost(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                   this.data[this.fieldRowIndex].field_12_10 = Number(this.data[this.fieldRowIndex].field_12_1)+Number(this.data[this.fieldRowIndex].field_12_3)+Number(this.data[this.fieldRowIndex].field_12_4)
                   +Number(this.data[this.fieldRowIndex].field_12_5)+Number(this.data[this.fieldRowIndex].field_12_6)+Number(this.data[this.fieldRowIndex].field_12_7)+Number(this.data[this.fieldRowIndex].field_12_8)
                   +Number(this.data[this.fieldRowIndex].field_12_9)+Number(this.data[this.fieldRowIndex].field_12_2_4);
                   this.checkAllCost();
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            //求亩总成本
            checkAllCost(e){
                    this.data[this.fieldRowIndex].field_15 = Number(this.data[this.fieldRowIndex].field_11_6_2)+Number(this.data[this.fieldRowIndex].field_12_10)+Number(this.data[this.fieldRowIndex].field_13)+Number(this.data[this.fieldRowIndex].field_14);
                    this.checkAllLiYun();

            },
            //求亩总收益
            checkAllLiYun(e){
                this.data[this.fieldRowIndex].field_16 = this.data[this.fieldRowIndex].field_8-this.data[this.fieldRowIndex].field_15;
            },
            //读取表8-1相应的内容
            getUserAllData0801(people,point,column_id){
                axios({
                    url:'/getUserAllData0801',
                    method:'post',
                    params:{
                        people:people,
                        point:point,
                        column_id:column_id,
                        startYear:0,
                        endYear:0,
                        
                    }
                }).then(res=>{
                    this.data = res.data;
                    if(this.data.length > 0){
                        const json = JSON.stringify(res.data);
                        this.copyData = JSON.parse(json);
                      
                    }
                    //console.log(this.data);
                }).catch(err=>{
                    return this.$message.error("获取表8-1数据失败！");
                });
            },
         //获取稻作类型数据
         brandKeyChange1:function(){
          axios({
              url:'/getSelectContent',
              method:'post',
              params:{
                  fieldName:"稻作类型",
                  riceType:'one'
              },
          }).then(res=>{

              this.daoZuoTypeData = res.data;

          }).catch(err=>{
              return this.$message.error("获取稻作类型失败！");
          });
          },
            fillDetailInfo(e){
                var Index = e.target.parentNode.cellIndex;
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
             this.fieldRowIndex = this.fieldRowIndex-2; 
                //console.log(this.fieldRowIndex);
              var getTdInnerHTML="";
              getTdInnerHTML = getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
  
              this.tableFlag = getTdInnerHTML;
              //console.log(this.tableFlag);
              this.detialDialogVisible = true;
            },
            cancelOp(){
                this.detialDialogVisible = false;
            },
            close(){
                //console.log(this.data);
                this.detialDialogVisible = false;

                // console.log(this.data[this.fieldRowIndex]);

                // 判断【劳动用工】输入框是否填写，来改变【填写】按钮颜色
                if(this.data[this.fieldRowIndex].field_7 == "人工移栽"){
                    console.log(this.data[this.fieldRowIndex].field_7);

                    if(this.data[this.fieldRowIndex].field_11_1_1 != null &&
                    this.data[this.fieldRowIndex].field_11_1_2 != null &&
                    this.data[this.fieldRowIndex].field_11_2_1 != null && 
                    this.data[this.fieldRowIndex].field_11_2_2 != null && 
                    this.data[this.fieldRowIndex].field_11_3_1 != null && 
                    this.data[this.fieldRowIndex].field_11_3_2 != null &&
                    this.data[this.fieldRowIndex].field_11_5_1 != null &&
                    this.data[this.fieldRowIndex].field_11_5_2 != null ){
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,true);
                    }else {
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,false);
                    };

                }else if(this.data[this.fieldRowIndex].field_7 == "机械插秧"){
                    console.log(this.data[this.fieldRowIndex].field_7);

                    if(this.data[this.fieldRowIndex].field_11_1_1 != null &&
                    this.data[this.fieldRowIndex].field_11_1_2 != null &&
                    this.data[this.fieldRowIndex].field_11_2_1 != null && 
                    this.data[this.fieldRowIndex].field_11_2_2 != null && 
                    this.data[this.fieldRowIndex].field_11_3_1 != null && 
                    this.data[this.fieldRowIndex].field_11_3_2 != null &&
                    this.data[this.fieldRowIndex].field_11_5_1 != null &&
                    this.data[this.fieldRowIndex].field_11_5_2 != null ){
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,true);
                    }else {
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,false);
                    };

                }else if(this.data[this.fieldRowIndex].field_7 == "抛秧"){
                    console.log(this.data[this.fieldRowIndex].field_7);

                    if(this.data[this.fieldRowIndex].field_11_1_1 != null &&
                    this.data[this.fieldRowIndex].field_11_1_2 != null &&
                    this.data[this.fieldRowIndex].field_11_2_1 != null && 
                    this.data[this.fieldRowIndex].field_11_2_2 != null && 
                    this.data[this.fieldRowIndex].field_11_3_1 != null && 
                    this.data[this.fieldRowIndex].field_11_3_2 != null &&
                    this.data[this.fieldRowIndex].field_11_5_1 != null &&
                    this.data[this.fieldRowIndex].field_11_5_2 != null ){
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,true);
                    }else {
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,false);
                    };

                }else if(this.data[this.fieldRowIndex].field_7 == "撒直播"){
                    console.log(this.data[this.fieldRowIndex].field_7);

                    if(this.data[this.fieldRowIndex].field_11_2_1 != null && 
                    this.data[this.fieldRowIndex].field_11_2_2 != null && 
                    this.data[this.fieldRowIndex].field_11_4_1 != null && 
                    this.data[this.fieldRowIndex].field_11_4_2 != null &&
                    this.data[this.fieldRowIndex].field_11_5_1 != null &&
                    this.data[this.fieldRowIndex].field_11_5_2 != null ){
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,true);
                    }else {
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,false);
                    };

                }else if(this.data[this.fieldRowIndex].field_7 == "机条播"){
                    console.log(this.data[this.fieldRowIndex].field_7);

                    if(this.data[this.fieldRowIndex].field_11_2_1 != null && 
                    this.data[this.fieldRowIndex].field_11_2_2 != null && 
                    this.data[this.fieldRowIndex].field_11_4_1 != null && 
                    this.data[this.fieldRowIndex].field_11_4_2 != null &&
                    this.data[this.fieldRowIndex].field_11_5_1 != null &&
                    this.data[this.fieldRowIndex].field_11_5_2 != null ){
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,true);
                    }else {
                        Vue.set(this.tianxie_1_show,this.fieldRowIndex,false);
                    };
                }

                // 判断【物质费用】输入框是否填写，来改变【填写】按钮颜色
                if(this.data[this.fieldRowIndex].field_12_1 != null &&
                 this.data[this.fieldRowIndex].field_12_2_1 != null &&
                 this.data[this.fieldRowIndex].field_12_2_2 != null && 
                 this.data[this.fieldRowIndex].field_12_2_3 != null && 
                 this.data[this.fieldRowIndex].field_12_2_4 != null && 
                 this.data[this.fieldRowIndex].field_12_3 != null &&
                 this.data[this.fieldRowIndex].field_12_4 != null &&
                 this.data[this.fieldRowIndex].field_12_5 != null &&
                 this.data[this.fieldRowIndex].field_12_6 !=null &&
                 this.data[this.fieldRowIndex].field_12_7 != null &&
                 this.data[this.fieldRowIndex].field_12_8 != null &&
                 this.data[this.fieldRowIndex].field_12_9 != null ){
                    //  this.tianxie_2_show[this.fieldRowIndex] = true;
                     Vue.set(this.tianxie_2_show,this.fieldRowIndex,true);

                }else {
                    //  this.tianxie_2_show[this.fieldRowIndex] = false;
                     Vue.set(this.tianxie_2_show,this.fieldRowIndex,false);

                };
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            var point = this.$route.query.point;
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
        // this.getCurrentAccountMonitorInfo(people);
            this.getUserAllData0801(people,point,this.column_id);
        },

    }
</script>

<style scoped>
    .tianxie {
        color: black

    }

    .tianxie_full {
        color: green

    }
</style>