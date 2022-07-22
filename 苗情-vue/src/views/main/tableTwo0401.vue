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
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr >
                    <td colspan="15" style="background-color: #6E8B3D;color: #FFFFFF;">2021年度监测县(市、区)中稻生育进度调查表（移栽稻）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 4%;">县（市、区）</td>
                    <td style="width: 4%;">监测田块</td>
                    <td style="width: 4%;">地区类型</td>
                    <td style="width: 4%;">稻作类型</td>
                    <td style="width: 4%;">品系类型</td>
                    <td style="width: 4%;">品种名称</td>
                    <td>栽培方式</td>
                    <td>移栽期</td>
                    <td>进程1（移栽后7天）</td>
                    <td>进程2（移栽后12天）</td>
                    <td>进程3（移栽后17天）</td>
                    <td>进程4（移栽后22天）</td>
                    <td>进程5（移栽后27天）</td>
                    <td>进程6（移栽后37天）</td>
                    <td>进程7（移栽后47天）</td>
                    <!-- <td>进程8（移栽后62）</td>
                    <td>孕穗中期进程9（移栽后72天）</td> -->
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td style="width: 6%;">
                            <el-input v-model="data[index].field_1"></el-input>
                        </td>
                        <td style="width: 8.5%;">
                            <el-input v-model="data[index].field_2"></el-input>
                        </td>
                        <td style="width: 7.6%;">
                            <el-input v-model="data[index].field_3"></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_4"></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_5"></el-input>
                        </td>
                        <td style="width: 6%;">
                            <el-input v-model="data[index].field_6"></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_7"></el-input>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <!-- <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td style="width: 5%;">
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td> -->
                    </tr>
                </template>
            </table>

            <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="90%">
                <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                    <template v-if="tableFlag == '移栽期'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>叶龄(叶)</td>
                            <td>基本苗（万/亩</td>
                            <td>亩穴数（万）</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_8_1" style="width: 100%;"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_8_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_8_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_8_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程1（移栽后7天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_9_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_9_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_9_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_9_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程2（移栽后12天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_10_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_10_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_10_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_10_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程3（移栽后17天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_11_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_11_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_11_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_11_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程4（移栽后22天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_12_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_12_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_12_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_12_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程5（移栽后27天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_13_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_13_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_13_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_13_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程6（移栽后37天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_14_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_14_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_14_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_14_4"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程7（移栽后47天）'">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[fieldRowIndex].field_15_1"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_15_2"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_15_3"></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[fieldRowIndex].field_15_4"></el-input>
                            </td>
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
                data:[],
                daoZuoTypeData:[],//稻作类型
                pinXiTypeData:[],//品系类型
                zaiZhiTypeData:[],//栽植方式
                fieldRowIndex:0,
                tableFlag:'',
                detialDialogVisible:false,
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
            //确定地区类型
            getMonitorCardContentInto0401(people){
                axios({
                    url:'/getMonitorCardContentInto0401',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    
                    //console.log(res.data);
                }).catch(err=>{
                    return this.$message.error("插入表4-1数据失败！");
                });
            },

            cancelOp(){
                this.detialDialogVisible = false;
            },
            close(){
                //console.log(this.data);
                this.detialDialogVisible = false;
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
            //填写详细信息
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
            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTableTwo0401',
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
            //读取表4-1相应的内容
            getUserAllData0401(people,column_id){
                axios({
                    url:'/getUserAllDataTwo0401',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id
                    }
                }).then(res=>{
                    this.data = res.data;
                    
                }).catch(err=>{
                    return this.$message.error("获取表4-1数据失败！");
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
            //获取z栽植方式数据
            brandKeyChange3:function(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"栽植方式",
                        riceType:'one'
                    },
                }).then(res=>{
                    this.zaiZhiTypeData = res.data;
                }).catch(err=>{
                    return this.$message.error("获取栽植方式失败！");
                });
                },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            // this.getMonitorCardContentInto0401(people);
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            this.getUserAllData0401(people,this.column_id);
        }
    }
</script>