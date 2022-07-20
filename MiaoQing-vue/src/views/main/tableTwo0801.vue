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
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;overflow-x : auto;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;overflow-x: auto ;" border="1" cellspacing="0">
                <tr >
                    <td colspan="16" style="background-color: #6E8B3D;color: #FFFFFF;">2021年度监测县(市、区)水稻生产成本效益统计表(早稻)</td>
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
                    </tr>
                   <template v-for="(item,index) in data"> 
                    <tr>
                        <td>
                            <el-input v-model="data[index].field_1" :disabled="data[index].field_1 != null && data[index].field_1 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_2" :disabled="data[index].field_2 != null && data[index].field_2 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_3" :disabled="data[index].field_3 != null && data[index].field_3 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_4" :disabled="data[index].field_4 != null && data[index].field_4 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_5" :disabled="data[index].field_5 != null && data[index].field_5 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_6" :disabled="data[index].field_6 != null && data[index].field_6 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_7" :disabled="data[index].field_7 != null && data[index].field_7 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_8" @blur.prevent="checkAllLiYun($event)" 
                            :disabled="data[index].field_8 != null && data[index].field_8 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_9" :disabled="data[index].field_9 != null && data[index].field_9 != '' && column_id == ''"></el-input></td>
                        <td>
                            <el-input v-model="data[index].field_10" :disabled="data[index].field_10 != null && data[index].field_10 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_13" @blur.prevent="checkAllCost($event)" :disabled="data[index].field_13 != null && data[index].field_13 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_14" @blur.prevent="checkAllCost($event)" :disabled="data[index].field_14 != null && data[index].field_14 != '' && column_id == ''"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_15" disabled></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_16" disabled></el-input>
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
                                    :disabled="data[fieldRowIndex].field_11_1_1 != null && data[fieldRowIndex].field_11_1_1 != '' && column_id == ''"></el-input></td>
                                 <td><el-input v-model="data[fieldRowIndex].field_11_1_2"  @blur.prevent="checkSecondFieldInput($event)"
                                    :disabled="data[fieldRowIndex].field_11_1_2 != null && data[fieldRowIndex].field_11_1_2 != '' && column_id == ''"></el-input></td>                             
                             </template>

                            <td><el-input v-model="data[fieldRowIndex].field_11_2_1" @blur.prevent="checkFirstFieldInput($event)"
                                :disabled="data[fieldRowIndex].field_11_2_1 != null && data[fieldRowIndex].field_11_2_1 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_11_2_2" @blur.prevent="checkSecondFieldInput($event)"
                                :disabled="data[fieldRowIndex].field_11_2_2 != null && data[fieldRowIndex].field_11_2_2 != '' && column_id == ''"></el-input></td>

                            <template v-if="data[fieldRowIndex].field_7.indexOf('撒直播') != -1 || data[fieldRowIndex].field_7.indexOf('机条播') != -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_1" disabled></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_2" disabled></el-input></td>
                            </template>
                            <template v-if="data[fieldRowIndex].field_7.indexOf('撒直播') == -1 && data[fieldRowIndex].field_7.indexOf('机条播') == -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_1" @blur.prevent="checkFirstFieldInput($event)"
                                    :disabled="data[fieldRowIndex].field_11_3_1 != null && data[fieldRowIndex].field_11_3_1 != '' && column_id == ''"></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_3_2" @blur.prevent="checkSecondFieldInput($event)"
                                    :disabled="data[fieldRowIndex].field_11_3_2 != null && data[fieldRowIndex].field_11_3_2 != '' && column_id == ''"></el-input></td>
                            </template>

                            <template v-if="data[fieldRowIndex].field_7.indexOf('人工移栽') != -1 || data[fieldRowIndex].field_7.indexOf('机械插秧') != -1 || data[fieldRowIndex].field_7.indexOf('抛秧') != -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_1" disabled></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_2" disabled></el-input></td>
                            </template>
                            <template v-if="data[fieldRowIndex].field_7.indexOf('人工移栽') == -1 && data[fieldRowIndex].field_7.indexOf('机械插秧') == -1 && data[fieldRowIndex].field_7.indexOf('抛秧') == -1">
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_1" @blur.prevent="checkFirstFieldInput($event)"
                                    :disabled="data[fieldRowIndex].field_11_4_1 != null && data[fieldRowIndex].field_11_4_1 != '' && column_id == ''"></el-input></td>
                                <td><el-input v-model="data[fieldRowIndex].field_11_4_2" @blur.prevent="checkSecondFieldInput($event)"
                                    :disabled="data[fieldRowIndex].field_11_4_2 != null && data[fieldRowIndex].field_11_4_2 != '' && column_id == ''"></el-input></td>
                            </template>

                            <td><el-input v-model="data[fieldRowIndex].field_11_5_1" @blur.prevent="checkFirstFieldInput($event)"
                                :disabled="data[fieldRowIndex].field_11_5_1 != null && data[fieldRowIndex].field_11_5_1 != '' && column_id == ''"> </el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_11_5_2" @blur.prevent="checkSecondFieldInput($event)"
                                :disabled="data[fieldRowIndex].field_11_5_2 != null && data[fieldRowIndex].field_11_5_2 != '' && column_id == ''"></el-input></td>
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
                                :disabled="data[fieldRowIndex].field_12_1 != null && data[fieldRowIndex].field_12_1 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_1" 
                                :disabled="data[fieldRowIndex].field_12_2_1 != null && data[fieldRowIndex].field_12_2_1 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_2" @blur.prevent="checkHuaFeiAudit($event)" 
                                :disabled="data[fieldRowIndex].field_12_2_2 != null && data[fieldRowIndex].field_12_2_2 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_3" @blur.prevent="checkHuaFeiAudit($event)"
                                :disabled="data[fieldRowIndex].field_12_2_3 != null && data[fieldRowIndex].field_12_2_3 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_2_4" disabled></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_3"   @blur.prevent="checkWuZhiCost($event)" 
                                :disabled="data[fieldRowIndex].field_12_3 != null && data[fieldRowIndex].field_12_3 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_4"  @blur.prevent="checkWuZhiCost($event)"
                                :disabled="data[fieldRowIndex].field_12_4 != null && data[fieldRowIndex].field_12_4 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_5" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="data[fieldRowIndex].field_12_5 != null && data[fieldRowIndex].field_12_5 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_6" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="data[fieldRowIndex].field_12_6 != null && data[fieldRowIndex].field_12_6 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_7" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="data[fieldRowIndex].field_12_7 != null && data[fieldRowIndex].field_12_7 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_8" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="data[fieldRowIndex].field_12_8 != null && data[fieldRowIndex].field_12_8 != '' && column_id == ''"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_12_9" @blur.prevent="checkWuZhiCost($event)"
                                :disabled="data[fieldRowIndex].field_12_9 != null && data[fieldRowIndex].field_12_9 != '' && column_id == ''"></el-input></td>
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
    export default{
        data(){
            return{
                daoZuoTypeData:[],//稻作类型
                data:[],
                zaiZhiTypeData:[],//栽植方式
                fieldRowIndex:0,
                tableFlag:'',
                detialDialogVisible:false,
                tableName:'',
                column_id:'',
            }
        },
        methods:{
            //上报
            getFormList(e){
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
                url:'/getSituationInputDataTableTwo0801',
                method:'post',
                params:{
                  data:list
                }
              }).then(res=>{
                console.log(res);
                this.$message.success("操作成功！");
              }).catch(err=>{
                return this.$message.error("数据保存失败！");
              })

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
                    url:'/getUserAllDataTwo0801',
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

                    console.log(this.data);
                }).catch(err=>{
                    return this.$message.error("获取表8-1数据失败！");
                });
            },
            fillDetailInfo(e){
                var Index = e.target.parentNode.cellIndex;
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
             this.fieldRowIndex = this.fieldRowIndex-2; 
                console.log(this.fieldRowIndex);
              var getTdInnerHTML="";
              getTdInnerHTML = getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
  
              this.tableFlag = getTdInnerHTML;
              console.log(this.tableFlag);
              this.detialDialogVisible = true;
            },
            cancelOp(){
                this.detialDialogVisible = false;
            },
            close(){
                console.log(this.data);
                this.detialDialogVisible = false;
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            var point = this.$route.query.point;
            console.log(point)
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
          this.column_id = this.$route.query.column_id;
          this.tableName = this.$route.query.record_table;
        }
            this.getUserAllData0801(people,point,this.column_id);
        },
    }
</script>