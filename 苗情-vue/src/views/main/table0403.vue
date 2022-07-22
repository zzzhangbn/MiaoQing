<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
              <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
             </router-link>
          </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr >
                    <td colspan="12" style="background-color: #6E8B3D;color: #FFFFFF;">年度水稻监测点茎蘖数叶面积构成表</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 4%;">县（市、区）</td>
                    <td style="width: 4%;">监测田块</td>
                    <td style="width: 4%;">地区类型</td>
                    <td style="width: 4%;">稻作类型</td>
                    <td style="width: 4%;">品系类型</td>
                    <td style="width: 4%;">品种名称</td>
                    <td>栽培方式</td>
                    <td>目标产量(kg/亩)</td>
                    <td>基本苗（万/亩）</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td style="width: 6%;">
                            <el-input v-model="data[index].field_1" readonly></el-input>
                        </td>
                        <td style="width: 8.5%;">
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
                        <td style="width: 5%;">
                            <el-input v-model="data[index].field_8" :disabled="data[index].field_8 != null && data[index].field_8 != '' && column_id == '' "></el-input>
                        </td>
                        <td style="width: 5%;">
                            <el-input v-model="data[index].field_9" readonly></el-input>
                        </td>
                    </tr>
                </template>
            </table>
            <div style="width: 100%;height: 10px;"></div>
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <template v-for="(item,index) in data">
                    <tr>
                        <template v-if="data[index].field_10_1 == null || data[index].field_10_1 == '' || data[index].field_10_2 == null || data[index].field_10_2 == '' || data[index].field_10_3 == null
                        || data[index].field_10_3 == '' || data[index].field_10_4 == null || data[index].field_10_4 == '' || data[index].field_10_5 == '' || data[index].field_10_5 == null || data[index].field_10_6 == null
                        || data[index].field_10_6 == '' || data[index].field_10_7 == null || data[index].field_10_7 == '' || data[index].field_10_8 == null || data[index].field_10_8 == ''">
                            <td style="color: red;">拔节期（第一节间定长后）<i class='el-icon-caret-top' @click="foldOrNot($event,'拔节期')"></i></td>
                        </template>
                        <template v-if="data[index].field_10_1 != null && data[index].field_10_1 != '' && data[index].field_10_2 != null && data[index].field_10_2 != '' && data[index].field_10_3 != null
                        && data[index].field_10_3 != '' && data[index].field_10_4 != null && data[index].field_10_4 != '' && data[index].field_10_5 != '' && data[index].field_10_5 != null && data[index].field_10_6 != null
                        && data[index].field_10_6 != '' && data[index].field_10_7 != null && data[index].field_10_7 != '' && data[index].field_10_8 != null && data[index].field_10_8 != ''">
                            <td style="color: green;">拔节期（第一节间定长后）<i class='el-icon-caret-top' @click="foldOrNot($event,'拔节期')"></i></td>
                        </template>
                        <template v-if="data[index].field_11_1 == null || data[index].field_11_1 == '' || data[index].field_11_2 == null || data[index].field_11_2 == '' || data[index].field_11_3 == null
                        || data[index].field_11_3 == '' || data[index].field_11_4 == null || data[index].field_11_4 == '' || data[index].field_11_5 == '' || data[index].field_11_5 == null ">
                            <td style="color: red;">孕穗期（幼穗分化1-2期）<i class='el-icon-caret-top' @click="foldOrNot($event,'孕穗期1')"></i></td>
                        </template>
                        <template v-if="data[index].field_11_1 != null && data[index].field_11_1 != '' && data[index].field_11_2 != null && data[index].field_11_2 != '' && data[index].field_11_3 != null
                        && data[index].field_11_3 != '' && data[index].field_11_4 != null && data[index].field_11_4 != '' && data[index].field_11_5 != '' && data[index].field_11_5 != null">
                            <td style="color: green;">孕穗期（幼穗分化1-2期）<i class='el-icon-caret-top' @click="foldOrNot($event,'孕穗期1')"></i></td>
                        </template>
                        <template v-if="data[index].field_12_1 == null || data[index].field_12_1 == '' || data[index].field_12_2 == null || data[index].field_12_2 == '' || data[index].field_12_3 == null
                        || data[index].field_12_3 == '' || data[index].field_12_4 == null || data[index].field_12_4 == '' || data[index].field_12_5 == '' || data[index].field_12_5 == null">
                            <td style="color: red;">孕穗期（幼穗分化3-4期）<i class='el-icon-caret-top' @click="foldOrNot($event,'孕穗期2')"></i></td>
                        </template>
                        <template v-if="data[index].field_12_1 != null && data[index].field_12_1 != '' && data[index].field_12_2 != null && data[index].field_12_2 != '' && data[index].field_12_3 != null
                        && data[index].field_12_3 != '' && data[index].field_12_4 != null && data[index].field_12_4 != '' && data[index].field_12_5 != '' && data[index].field_11_5 != null">
                            <td style="color: green;">孕穗期（幼穗分化3-4期）<i class='el-icon-caret-top' @click="foldOrNot($event,'孕穗期2')"></i></td>
                        </template>
                        
                    </tr>
                    <template v-if="fold1 == true">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>测定日期</td>
                            <td>株高(cm)</td>
                            <td>总茎蘖数(万/亩)</td>
                            <td>平均单茎绿叶数</td>
                            <td>倒4与倒3叶色比(大于、等于、小于)</td>
                            <td>1叶分蘖个数</td>
                            <td>2叶分蘖个数</td>
                            <td>3叶及以上分蘖个数</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                v-model="data[index].field_10_1" :disabled="data[index].field_10_1 != null && data[index].field_10_1 != '' && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_2" :disabled="data[index].field_10_2 != null && data[index].field_10_2 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_3" :disabled="data[index].field_10_3 != null && data[index].field_10_3 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_4" :disabled="data[index].field_10_4 != null && data[index].field_10_4 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_5" :disabled="data[index].field_10_5 != null && data[index].field_10_5 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_6" :disabled="data[index].field_10_6 != null && data[index].field_10_6 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_7" :disabled="data[index].field_10_7 != null && data[index].field_10_7 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_10_8" :disabled="data[index].field_10_8 != null && data[index].field_10_8 != '' && column_id == ''"></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold2 == true">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>测定日期</td>
                            <td>株高(cm)</td>
                            <td>总茎蘖数(万/亩)</td>
                            <td>平均单茎绿叶数</td>
                            <td>倒4与倒3叶色比(大于、等于、小于)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                v-model="data[index].field_11_1" :disabled="data[index].field_11_1 != null && data[index].field_11_1 != '' && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_11_2" :disabled="data[index].field_11_2 != null && data[index].field_11_2 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_11_3" :disabled="data[index].field_11_3 != null && data[index].field_11_3 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_11_4" :disabled="data[index].field_11_4 != null && data[index].field_11_4 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[index].field_11_5" :disabled="data[index].field_11_5 != null && data[index].field_11_5 != '' && column_id == ''"></el-input>
                            </td>

                        </tr>
                    </template>
                    <template v-if="fold3 == true">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>测定日期</td>
                            <td>株高(cm)</td>
                            <td>总茎蘖数(万/亩)</td>
                            <td>平均单茎绿叶数</td>
                            <td>倒4与倒3叶色比(大于、等于、小于)</td>
                        </tr>
                        <tr>
                            <td>
                                <el-date-picker
                                v-model="data[fieldRowIndex].field_12_1" :disabled="data[index].field_12_1 != null && data[index].field_12_1 != '' && column_id == ''"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_12_2" :disabled="data[index].field_12_2 != null && data[index].field_12_2 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_12_3" :disabled="data[index].field_12_3 != null && data[index].field_12_3 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_12_4" :disabled="data[index].field_12_4 != null && data[index].field_12_4 != '' && column_id == ''"></el-input>
                            </td>
                            <td>
                                <el-input v-model="data[fieldRowIndex].field_12_5" :disabled="data[index].field_12_5 != null && data[index].field_12_5 != '' && column_id == ''"></el-input>
                            </td>

                        </tr>
                    </template>
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
                fold1:false,
                fold2:false,
                fold3:false,
                column_id:'',
                tableName:'',
                monitor_location:'',
            }
        },
        methods:{
            foldOrNot(e,tag){
                if(tag == "拔节期"){
                    
                    if(e.target.className == "el-icon-caret-bottom"){
                        e.target.className = "el-icon-caret-top";
                        this.fold1 = false;
                    }else{
                        e.target.className = "el-icon-caret-bottom";
                        this.fold1 = true;
                    }
                }
                if(tag == "孕穗期1"){
                    
                    if(e.target.className == "el-icon-caret-bottom"){
                        e.target.className = "el-icon-caret-top";
                        this.fold2 = false;
                    }else{
                        e.target.className = "el-icon-caret-bottom";
                        this.fold2 = true;
                    }
                }
                if(tag == "孕穗期2"){
                    
                    if(e.target.className == "el-icon-caret-bottom"){
                        e.target.className = "el-icon-caret-top";
                        this.fold3 = false;
                    }else{
                        e.target.className = "el-icon-caret-bottom";
                        this.fold3 = true;
                    }
                }
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
                url:'/getSituationInputDataTable0403',
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
            getMonitorCardContentInto0403(people){
                axios({
                    url:'/getMonitorCardContentInto0403',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    
                    //console.log(res.data);
                }).catch(err=>{
                    return this.$message.error("更新表4-3数据失败！");
                });
            },
            //读取表4-3相应的内容
            getUserAllData0403(people,column_id,monitor_location){
                axios({
                    url:'/getUserAllData0403',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id,
                        monitor_location:monitor_location,
                        startYear:0,
                        endYear:0,
                    }
                }).then(res=>{
                    this.data = res.data;
                    const json = JSON.stringify(res.data);
                    this.copyData = JSON.parse(json);
                    
                }).catch(err=>{
                    return this.$message.error("获取表4-3数据失败！");
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
                this.detialDialogVisible = false;
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            // this.getMonitorCardContentInto0403(people);
            this.getUserAllData0403(people,this.column_id,this.monitor_location);
        }
    }
</script>