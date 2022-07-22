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
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;" @click="getFormList('1')">上 报</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr>
                    <td colspan="16" style="background-color: #6E8B3D;color: #FFFFFF;">2021年度监测县(市、区)水稻播栽进度调查表（晚稻）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td rowspan="2">县（市、区）</td>
                    <td rowspan="2">地区类型</td>
                    <td rowspan="2">稻作类型</td>
                    <td rowspan="2" style="width: 6%;">全县总面积（万亩）</td>
                    <td colspan="2" style="width: 10%;">播种日期</td>
                    <td  colspan="3">秧田面积（万亩）</td>
                    <td  colspan="4">折合大田面积（万亩）</td>
                    <td rowspan="2">播栽期进度1（5月20日）</td>
                    <td rowspan="2">播栽期进度2（5月30日）</td>
                    <td rowspan="2">播栽期进度3（6月10日）</td>
                    <!-- <td rowspan="2">播栽期进度4（6月20日）</td> -->
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td >育秧</td>
                    <td >直播</td>
                    <!-- <td style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">日期</td> -->
                    <td style="text-align: center;width: 4%;" ><span style="width: 70%;height: 7%;"></span></td>
                    <td style="text-align: center;" colspan="2">其中集中育秧</td>
                    <td colspan="2" style="width: 6%;text-align: center;"><span style="width: 70%;"></span></td>
                    <td style="text-align: center;" colspan="2">其中集中育秧</td>
                </tr>
                <template v-for="(item,index) in data">
                <tr>
                    <td>
                        <el-input v-model="data[index].field_1" disabled></el-input>
                    </td>
                    <td style="width: 7%;">
                        <el-input v-model="data[index].field_2" style="width: 100%;" disabled></el-input>
                    </td>
                    <td>
                        <el-input v-model="data[index].field_3" ></el-input>
                    </td>
                    <td>
                        <el-input v-model="data[index].field_4" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                    </td>
                    <td style="width:3%">
               
                        <input type="date" v-model="data[index].field_5_1" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                        color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;outline: 0;width: 100%;"></input>
                    </td>
                    <td style="width:3%">
                       
                        <input type="date" v-model="data[index].field_5_2" style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                        color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;outline: 0;width: 100%;"></input>
                    </td>

                    <td style="width:5%">
                        <el-input v-model="data[index].field_6"></el-input>
                    </td>

                    
                        <td style="width:5%">
                            <el-input v-model="data[index].field_7_1" @blur.prevent="getField62($event)"></el-input>
                        </td>
                        <td style="width:6%">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                            color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;width: 100%;" v-model="data[index].field_7_2" disabled ></input>
                        </td>
                    
                    
                        <td style="width:4%">
                            <el-input v-model="data[index].field_8_1" @blur.prevent="getField72($event)"></el-input>
                        </td>
                        <td style="width:6%">
                            <input v-model="data[index].field_8_2" disabled style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                            color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;width: 100%;"></input>
                        </td>
                 
                    <td style="width:5%">
                        <el-input v-model="data[index].field_9_1" @blur.prevent="getField82($event)"></el-input>
                    </td>
                    <td style="width:6%">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;box-sizing: border-box;
                        color: #606266;display: inline-block;font-size: inherit;height: 40px;line-height: 40px;width: 100%;" v-model="data[index].field_9_2" disabled></input>
                    </td>
                    

                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" id="播载期进度1">填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" id="播载期进度2">填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" id="播载期进度3">填写</span>
                    </td>
                    <!-- <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" id="播载期进度4">填写</span>
                    </td> -->
                </tr>
            </template>
            </table>


            <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="90%">
                <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                    <template v-if="tableFlag == '播载期进度1' || tableFlag == '播载期进度2' || tableFlag == '播载期进度3'">
                    <tr style="background-color: #CDBE70;color: green;">
                        <td>日期</td>
                            <td colspan="2">育秧已播种面积（万亩）</td>
                            <td>已栽插面积（万亩）</td>                       
                            <td>直播已播种面积（万亩）</td>
                        <td>小计</td>
                        <td>占比</td>
                    </tr>
                    </template>
                    <!-- <tr>  -->
                      <template v-if="tableFlag == '播载期进度1'">
                          <tr>
                            <td>
                                <el-date-picker
                                    v-model="data[fieldRowIndex].field_10_1"
                                    type="date"
                                    placeholder="选择日期">
                                    </el-date-picker>
                            </td>
                            <td><el-input v-model="data[fieldRowIndex].field_10_2_1"  @blur.prevent="getField922($event)"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_10_2_2" disabled></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_10_3" @blur.prevent="getSumArea('field_10_4','field_10_3')"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_10_4" @blur.prevent="getSumArea('field_10_4','field_10_3')"></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_10_5" disabled></el-input></td>
                            <td><el-input v-model="data[fieldRowIndex].field_10_6" disabled></el-input></td>
                        </tr>
                        </template>
                            
                    <template v-if="tableFlag == '播载期进度2'">
                        <tr>
                        <td>
                            <el-date-picker
                                v-model="data[fieldRowIndex].field_11_1"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[fieldRowIndex].field_11_2_1"  @blur.prevent="getField1022($event)"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_11_2_2" disabled></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_11_3" @blur.prevent="getSumArea('field_11_4','field_11_3')"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_11_4" @blur.prevent="getSumArea('field_11_4','field_11_3')"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_11_5" disabled></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_11_6" disabled></el-input></td></tr>
                    </template>
                    <template v-if="tableFlag == '播载期进度3'">
                        <tr>
                        <td>
                            <el-date-picker
                                v-model="data[fieldRowIndex].field_12_1"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[fieldRowIndex].field_12_2_1"  @blur.prevent="getField1122($event)"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_12_2_2" disabled></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_12_3" @blur.prevent="getSumArea('field_12_4','field_12_3')"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_12_4" @blur.prevent="getSumArea('field_12_4','field_12_3')"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_12_5" disabled></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_12_6" disabled></el-input></td></tr>
                    </template>
                    <template v-if="tableFlag == '播载期进度4'">
                        <tr>
                        <td>
                            <el-date-picker
                                v-model="data[fieldRowIndex].field_13_1"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                        </td>
                        <td><el-input v-model="data[fieldRowIndex].field_13_2"  @blur.prevent="getSumArea('field_13_3','field_13_2')"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_13_3"  @blur.prevent="getSumArea('field_13_3','field_13_2')"></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_13_4" disabled></el-input></td>
                        <td><el-input v-model="data[fieldRowIndex].field_13_5" disabled></el-input></td>
                  </tr>
                    </template>
                    <!-- </tr> -->
                </table>
          <span slot="footer" class="dialog-footer">
            <el-button @click="cancelOp">关 闭</el-button>
            <el-button type="primary" @click="detialDialogVisible = false">确 定</el-button>
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
                test:'',
                data:[],
                fieldColIndex:0,
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
            getSumArea(x1,x2){
                if(x1 == 'field_9_4'){
                    this.data[this.fieldRowIndex].field_9_5 = Number(this.data[this.fieldRowIndex].field_9_4)+Number(this.data[this.fieldRowIndex].field_9_3);
                    this.data[this.fieldRowIndex].field_9_6 = (Math.round((this.data[this.fieldRowIndex].field_9_5/this.data[this.fieldRowIndex].field_3)* 100*100)/100).toFixed(2)+"%";
                    // this.data[this.fieldRowIndex][x2] = (Math.round((this.data[this.fieldRowIndex][x1]/this.data[this.fieldRowIndex].field_2)* 100*100)/100).toFixed(2)+"%";
                }
                if(x1 == 'field_10_4'){
                    this.data[this.fieldRowIndex].field_10_5 = Number(this.data[this.fieldRowIndex].field_10_4)+Number(this.data[this.fieldRowIndex].field_10_3);
                    this.data[this.fieldRowIndex].field_10_6 = (Math.round((this.data[this.fieldRowIndex].field_10_5/this.data[this.fieldRowIndex].field_3)* 100*100)/100).toFixed(2)+"%";
                }
                if(x1 == 'field_11_4'){
                    this.data[this.fieldRowIndex].field_11_5 = Number(this.data[this.fieldRowIndex].field_11_4)+Number(this.data[this.fieldRowIndex].field_11_3);
                    this.data[this.fieldRowIndex].field_11_6 = (Math.round((this.data[this.fieldRowIndex].field_11_5/this.data[this.fieldRowIndex].field_3)* 100*100)/100).toFixed(2)+"%";
                }
                if(x1 == 'field_12_3'){
                    this.data[this.fieldRowIndex].field_12_4 = Number(this.data[this.fieldRowIndex].field_12_3)+Number(this.data[this.fieldRowIndex].field_12_2);
                    this.data[this.fieldRowIndex].field_12_5 = (Math.round((this.data[this.fieldRowIndex].field_12_4/this.data[this.fieldRowIndex].field_3)* 100*100)/100).toFixed(2)+"%";
                }

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
                    //console.log(res);
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
                        column_id:column_id
                    }
                }).then(res=>{
                    this.data = res.data;
                    
                    //console.log(this.data);
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
              //console.log(this.fieldRowIndex);
              var getTdInnerHTML="";
              getTdInnerHTML = e.target.id;
              this.tableFlag = getTdInnerHTML;
              //console.log(this.tableFlag);
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
            getField62(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_6_2 = Number((this.data[this.fieldRowIndex].field_6_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[this.fieldRowIndex].field_6_2 = (Math.round((this.data[this.fieldRowIndex].field_6_1 /this.data[this.fieldRowIndex].field_5) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField72(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_7_2 = Number((this.data[this.fieldRowIndex].field_7_1/this.data[this.fieldRowIndex].field_3).toFixed(4))*100+"%";
                    this.data[this.fieldRowIndex].field_7_2 = (Math.round((this.data[this.fieldRowIndex].field_7_1 /this.data[this.fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField82(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_8_2 = Number((this.data[this.fieldRowIndex].field_8_1/this.data[this.fieldRowIndex].field_3).toFixed(4))*100+"%";
                    this.data[this.fieldRowIndex].field_8_2 = (Math.round((this.data[this.fieldRowIndex].field_8_1 /this.data[this.fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField922(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_9_2_2 = Number((this.data[this.fieldRowIndex].field_9_2_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[this.fieldRowIndex].field_9_2_2 = (Math.round((this.data[this.fieldRowIndex].field_9_2_1 /this.data[this.fieldRowIndex].field_5) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField1022(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_9_2_2 = Number((this.data[this.fieldRowIndex].field_9_2_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[this.fieldRowIndex].field_10_2_2 = (Math.round((this.data[this.fieldRowIndex].field_10_2_1 /this.data[this.fieldRowIndex].field_5) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
            getField1122(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){ 
                    // this.data[this.fieldRowIndex].field_9_2_2 = Number((this.data[this.fieldRowIndex].field_9_2_1/this.data[this.fieldRowIndex].field_5).toFixed(4))*100+"%";
                    this.data[this.fieldRowIndex].field_11_2_2 = (Math.round((this.data[this.fieldRowIndex].field_11_2_1 /this.data[this.fieldRowIndex].field_5) * 100*100)/100).toFixed(2) + '%';
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            // this.getMonitorCardContentInto0301(people);
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            this.getUserAllData0301(people,this.column_id);
        },
       
    }
</script>