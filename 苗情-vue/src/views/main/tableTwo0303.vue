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
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上 报</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr >
                    <td colspan="60" style="background-color: #6E8B3D;color: #FFFFFF;">2021年度监测县(市、区)水稻苗情评价动态列表（早稻-移栽）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td >县（市、区）</td>
                    <td>地区类型</td>
                    <td>稻作类型</td>
                    <td>全县总面积（万亩）</td>
                    <td >进程1（移栽后7天）</td>
                    <td >进程2（移栽后14天）</td>
                    <td >进程3（移栽后21天）</td>
                    <td >进程4（移栽后28天）</td>
                    <td >进程5（移栽后35天）</td>
                    <td >进程6（移栽后42天）</td>
                    <td >进程7（移栽后52天）</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td>
                            <el-input v-model="data[index].field_1"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_2"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_3"></el-input>
                        </td>
                        <td>
                            <el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data[index].field_4"></el-input>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)">填写</span>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)">填写</span>
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

            <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="90%">
                <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                    <tr style="background-color: #CDBE70;color: green;">
                        <td>日期</td>
                        <td colspan="2">一类苗
                            （万亩）</td>
                        <td colspan="2">二类苗
                            （万亩）</td>
                        <td colspan="2">三类苗
                            （万亩）</td>
                    </tr>
                    <template v-if="tableFlag == '进程1（移栽后7天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_5_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_5_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_5_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_5_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_5_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_5_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_5_4_2"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程2（移栽后12天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_6_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_6_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_6_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_6_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_6_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_6_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_6_4_2"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程3（移栽后17天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_7_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_7_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_7_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_7_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_7_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_7_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_7_4_2"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程4（移栽后22天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_8_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_8_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_8_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_8_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_8_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_8_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_8_4_2"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程5（移栽后27天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_9_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_9_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_9_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_9_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_9_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_9_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_9_4_2"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程6（移栽后32天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_10_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_10_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_10_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_10_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_10_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_10_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_10_4_2"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="tableFlag == '进程7（移栽后42天）'">
                        <tr> 
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_11_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_11_2_1"  @blur.prevent="checkFirstFieldInput421($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_11_2_2" ></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_11_3_1" @blur.prevent="checkFirstFieldInput431($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_11_3_2"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_11_4_1" @blur.prevent="checkFirstFieldInput441($event)"></el-input>
                            </td>
                            <td>
                                <el-input disabled v-model="data[fieldRowIndex].field_11_4_2"></el-input>
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
                fieldRowIndex:0,
                detialDialogVisible:false,
                tableFlag:'',
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
            cancelOp(){
                this.detialDialogVisible = false;
            },
            //查询该用户下数据
            getUserAllData0302(people,column_id){
                axios({
                    url:'/getUserAllDataTwo0303',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id
                    }
                }).then(res=>{
                    this.data = res.data;
                    //console.log(this.data);
                }).catch(err=>{
                    return this.$message.error("获取表3-3内容失败！");
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
            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTableTwo0303',
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
            //将建档卡2-1中已知内容插入到表3-2中
            getMonitorCardContentInto0302(people){
                axios({
                    url:'/getMonitorCardContentIntoTwo0303',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    //console.log(res);
                }).catch(err=>{
                    return this.$message.error("获取表3-3内容失败！");
                });
            },
            //填写详细信息
            fillDetailInfo(e){
              var Index = e.target.parentNode.cellIndex;
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
             this.fieldRowIndex = this.fieldRowIndex-2; 
            
              var getTdInnerHTML="";
              getTdInnerHTML = getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
              //console.log(getTdInnerHTML);
              this.tableFlag = getTdInnerHTML;
              this.detialDialogVisible = true;
            },
            checkFirstFieldInput421(e){
                //console.log(e);
                if(this.tableFlag == '进程1（移栽后7天）'){
                    this.data[this.fieldRowIndex].field_5_2_2 = (Math.round((this.data[this.fieldRowIndex].field_5_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                
                if(this.tableFlag == '进程2（移栽后12天）'){
                    this.data[this.fieldRowIndex].field_6_2_2 = (Math.round((this.data[this.fieldRowIndex].field_6_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程3（移栽后17天）'){
                    this.data[this.fieldRowIndex].field_7_2_2 = (Math.round((this.data[this.fieldRowIndex].field_7_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程4（移栽后22天）'){
                    this.data[this.fieldRowIndex].field_8_2_2 = (Math.round((this.data[this.fieldRowIndex].field_8_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程5（移栽后27天）'){
                    this.data[this.fieldRowIndex].field_9_2_2 = (Math.round((this.data[this.fieldRowIndex].field_9_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程6（移栽后32天）'){
                    this.data[this.fieldRowIndex].field_10_2_2 = (Math.round((this.data[this.fieldRowIndex].field_10_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程7（移栽后42天）'){
                    this.data[this.fieldRowIndex].field_11_2_2 = (Math.round((this.data[this.fieldRowIndex].field_11_2_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
              
            },
            checkFirstFieldInput431(){
                if(this.tableFlag == '进程1（移栽后7天）'){
                    this.data[this.fieldRowIndex].field_5_3_2 = (Math.round((this.data[this.fieldRowIndex].field_5_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                
                if(this.tableFlag == '进程2（移栽后12天）'){
                    this.data[this.fieldRowIndex].field_6_3_2 = (Math.round((this.data[this.fieldRowIndex].field_6_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程3（移栽后17天）'){
                    this.data[this.fieldRowIndex].field_7_3_2 = (Math.round((this.data[this.fieldRowIndex].field_7_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程4（移栽后22天）'){
                    this.data[this.fieldRowIndex].field_8_3_2 = (Math.round((this.data[this.fieldRowIndex].field_8_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程5（移栽后27天）'){
                    this.data[this.fieldRowIndex].field_9_3_2 = (Math.round((this.data[this.fieldRowIndex].field_9_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程6（移栽后32天）'){
                    this.data[this.fieldRowIndex].field_10_3_2 = (Math.round((this.data[this.fieldRowIndex].field_10_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程7（移栽后42天）'){
                    this.data[this.fieldRowIndex].field_11_3_2 = (Math.round((this.data[this.fieldRowIndex].field_11_3_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
               
            },
            checkFirstFieldInput441(){
                if(this.tableFlag == '进程1（移栽后7天）'){
                    this.data[this.fieldRowIndex].field_5_4_2 = (Math.round((this.data[this.fieldRowIndex].field_5_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                
                if(this.tableFlag == '进程2（移栽后12天）'){
                    this.data[this.fieldRowIndex].field_6_4_2 = (Math.round((this.data[this.fieldRowIndex].field_6_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程3（移栽后17天）'){
                    this.data[this.fieldRowIndex].field_7_4_2 = (Math.round((this.data[this.fieldRowIndex].field_7_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程4（移栽后22天）'){
                    this.data[this.fieldRowIndex].field_8_4_2 = (Math.round((this.data[this.fieldRowIndex].field_8_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程5（移栽后27天）'){
                    this.data[this.fieldRowIndex].field_9_4_2 = (Math.round((this.data[this.fieldRowIndex].field_9_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程6（移栽后32天）'){
                    this.data[this.fieldRowIndex].field_10_4_2 = (Math.round((this.data[this.fieldRowIndex].field_10_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                if(this.tableFlag == '进程7（移栽后42天）'){
                    this.data[this.fieldRowIndex].field_11_4_2 = (Math.round((this.data[this.fieldRowIndex].field_11_4_1 /this.data[this.fieldRowIndex].field_4) * 100*100)/100).toFixed(2) + '%';
                }
                
            },
            close(){
                this.detialDialogVisible = false;
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            // this.getMonitorCardContentInto0302(people);
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            this.getUserAllData0302(people,this.column_id);
        },
    }
</script>