<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
              <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
             </router-link>
          </template>
          <template v-if="point != ''">
            <template v-if="column_id == ''|| column_id == null">
        <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
              <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
          </router-link>
      </template>
          </template>
          
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;overflow-x : auto;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;overflow-x: auto ;" border="1" cellspacing="0">
                <tr >
                    <td colspan="18" style="background-color: #6E8B3D;color: #FFFFFF;">年度水稻监测点苗情生育期统计表</td>
                </tr>
                <!-- <tr><td colspan="18" style="text-align: right;">单位：(月/日)、天</td></tr> -->
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 5%;">县（市、区）</td>
                    <td style="width: 4%;">监测田块</td>
                    <td style="width: 4%;">地区类型</td>
                    <td style="width: 4%;">稻作类型</td>
                    <td style="width: 4%;">品系类型</td>
                    <td style="width: 4%;">品种名称</td>
                    <td>栽培方式</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td style="width: 5%;">
                            <el-input v-model="data[index].field_1" readonly></el-input>
                        </td>
                        <td style="width: 4%;">
                            <el-input v-model="data[index].field_2" readonly></el-input>
                        </td>
                        <td style="width: 7.6%;">
                            <el-input v-model="data[index].field_3" readonly></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_4" readonly></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_5" readonly></el-input>
                        </td>
                        <td style="width: 6%;">
                            <el-input v-model="data[index].field_6" readonly></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_7" readonly></el-input>
                        </td>
                    </tr>
                </template>
            </table>
            <!-- <div style="width: 100%;height: 1px;background-color: grey;margin-top: 1%;margin-bottom: 1%;"></div> -->
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;overflow-x: auto;margin-top: 1%;" border="1" cellspacing="0">
                <tr style="width: 100%;height: 1px;background-color: grey;margin-top: 1%;margin-bottom: 1%;"><td colspan="11"></td></tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td>播种期(月/日)</td>
                        <td>出苗期(月/日)</td>
                        <td>移栽期(月/日)</td>
                        <td>分蘖期(月/日)</td>
                        <td>够苗期(月/日)</td>
                        <td>有效分蘗临界叶龄期(月/日)</td>
                        <td>拔节期(月/日)</td>
                        <td>孕穗期(月/日)</td>
                        <td>抽穗期(月/日)</td>
                        <td>齐穗期(月/日)</td>
                        <td>成熟期(月/日)</td>
                        <td>全生育期(天)</td>
                    </tr>
                    <template v-for="(item,index) in data">
                        <tr>
                            <td style="width: 4%;">
                                <el-date-picker
                                    v-model="data[index].field_8" :disabled="copyData[index].field_8 != '' && copyData[index].field_8 != null && column_id == '' "
                                    type="date"
                                    placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_9" :disabled="copyData[index].field_9 != '' && copyData[index].field_9 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_10" 
                                :disabled="copyData[index].field_10 != '' && copyData[index].field_10 != null && column_id == '' || copyData[index].field_7 == '撒直播（水）'"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_fennie" :disabled="copyData[index].field_fennie != '' && copyData[index].field_fennie != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_11" :disabled="copyData[index].field_11 != '' && copyData[index].field_11 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_12" :disabled="copyData[index].field_12 != '' && copyData[index].field_12 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_13" :disabled="copyData[index].field_13 != '' && copyData[index].field_13 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_14" :disabled="copyData[index].field_14 != '' && copyData[index].field_14 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_15" :disabled="copyData[index].field_15 != '' && copyData[index].field_15 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_16" :disabled="copyData[index].field_16 != '' && copyData[index].field_16 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-date-picker
                                v-model="data[index].field_17" :disabled="copyData[index].field_17 != '' && copyData[index].field_17 != null && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td style="width: 5%;">
                                <el-input v-model="data[index].field_18" :disabled="copyData[index].field_18 != '' && copyData[index].field_18 != null && column_id == ''"></el-input>
                            </td>
                        </tr>
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
                data:[],
                daoZuoTypeData:[],//稻作类型
                pinXiTypeData:[],//品系类型
                zaiZhiTypeData:[],//栽植方式
                fieldRowIndex:0,
                copyData:[],
                detialDialogVisible:false,
                tableFlag:'',
                column_id:'',
                tableName:'',
                point:'',
            }
        },
        methods:{
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
                    console.log("测试的是多少");
                    this.saveInputData(str);
                    console.log("1212313");
                    if(this.column_id != '' && this.tableName != ''){
                        this.fixDataBackState(this.tableName,this.column_id);
                    }
                }).catch(_=>{
                return this.$message.info("您取消了操作！");
                });
                
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
                url:'/getSituationInputDataTable0404',
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
            //确定地区类型
            getMonitorCardContentInto0404(people){
                axios({
                    url:'/getMonitorCardContentInto0404',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    
                    //console.log(res.data);
                }).catch(err=>{
                    return this.$message.error("更新表4-4数据失败！");
                });
            },
            //读取表4-4相应的内容
            getUserAllData0404(people,column_id,point){
                axios({
                    url:'/getUserAllData0404',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id,
                        point:point,
                        startYear:0,
                        endYear:0,
                    }
                }).then(res=>{
                    this.data = res.data;
                    const json = JSON.stringify(res.data);
                    this.copyData = JSON.parse(json);
                    
                }).catch(err=>{
                    return this.$message.error("获取表4-4数据失败！");
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
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            var point = this.$route.query.point;
            this.point = point;
            if(this.point == null || this.point == '' || this.point == undefined){
                this.point = '';
            }
            this.getUserAllData0404(people,this.column_id,this.point);
        }
    }
</script>
<style >
.el-input--prefix .el-input__inner {
    padding-left: 26px;
}
.el-input--suffix .el-input__inner {
    padding-right: 0px;
}
.el-input{
    padding-right: 0px;
}
.el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 130px;
}
</style>