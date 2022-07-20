<template>
    <div class="demo-image__placeholder">
        <!-- <Child v-show="false"  @tableCode="tableCode"></Child>  -->
        <template v-if="column_id == ''|| column_id == null">
            <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
                <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
                <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>

        <el-row>
          <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:5px">
            <span>{{tableLabel}}</span>
          </div>
        </el-row>
        <!-- <span style="font-size: 20px;color:gray;margin-left: 41%;">{{tableId}}</span> -->
        <el-table :data="data" :header-cell-style="headerStyle">
            <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed />
            <el-table-column align="center"  width="105%" label="地区类型">
                <template v-if="data[fieldRowIndex].field_2 != null && data[fieldRowIndex].field_2 != '' && column_id == ''">
                    <el-input v-model="data[fieldRowIndex].field_2" readonly></el-input>
                </template> 
                        <template v-if="data[fieldRowIndex].field_2 == null || data[fieldRowIndex].field_2 == '' || column_id != '' ">
                            <el-select v-model="data[fieldRowIndex].field_2" placeholder="请选择" @focus="getSelectContent1">
                                <el-option
                                    v-for="item in listArray1"
                                    :key="item.id"
                                    :label="item.list"
                                    :value="item.list">
                                    </el-option>
                            </el-select>
                        </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="稻作类型" >
                <el-input v-model="data[fieldRowIndex].field_3" disabled></el-input>            
            </el-table-column>
            <el-table-column align="center"  width="105%" label="品系类型">
                <el-input v-model="data[fieldRowIndex].field_4" disabled></el-input>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="受灾统计表1（万亩）">
                <el-table-column align="center"  label="填报日期" >
                    <el-date-picker
                                value-format=“yyyy-MM-dd”
                                v-model="data[fieldRowIndex].field_5_1"
                                type="date"
                                placeholder="选择日期" style="width: 100%">
                    </el-date-picker>  
                </el-table-column>
                <el-table-column align="center"  label="生育阶段与灾害症状" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_2"  />
                </el-table-column>
                <el-table-column align="center"  label="受灾面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_3" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="其中：成灾面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_4" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="其中：绝收面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_5" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="需补改种面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_6" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="已补改种面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_7" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="受灾统计表2（万亩）">
                <el-table-column align="center"  label="填报日期" > 
                    <el-date-picker
                                value-format=“yyyy-MM-dd”
                                v-model="data[fieldRowIndex].field_6_1"
                                type="date"
                                placeholder="选择日期" style="width: 100%">
                    </el-date-picker>       
                </el-table-column>
                <el-table-column align="center"  label="生育阶段与灾害症状" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_2" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="受灾面积" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_3" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="其中：成灾面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_4" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="其中：绝收面积" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_5" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="需补改种面积" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_6" @blur.prevent="checkFirstFieldInput($event)" /> 
                </el-table-column>
                <el-table-column align="center"  label="已补改种面积" >
                    <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_7" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="受灾统计表3（万亩）">
                <el-table-column align="center"  label="填报日期" > 
                   <el-date-picker
                                value-format=“yyyy-MM-dd”
                                v-model="data[fieldRowIndex].field_7_1"
                                type="date"
                                placeholder="选择日期" style="width: 100%">
                        </el-date-picker> 
                    </el-table-column>
                <el-table-column align="center"  label="生育阶段与灾害症状" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_2" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="受灾面积" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_3" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="其中：成灾面积" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_4" @blur.prevent="checkFirstFieldInput($event)" /> 
                </el-table-column>
                <el-table-column align="center"  label="其中：绝收面积" >
                   <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_5" @blur.prevent="checkFirstFieldInput($event)" />
                </el-table-column>
                <el-table-column align="center"  label="需补改种面积" >
                  <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_6" @blur.prevent="checkFirstFieldInput($event)" />  
                </el-table-column>
                <el-table-column align="center"  label="已补改种面积" >
                <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_7" @blur.prevent="checkFirstFieldInput($event)" /> 
                </el-table-column>
            </el-table-column>
        </el-table>
        

    </div>

</template>
<script src="../../../public/js/Vue.min.js"></script>
<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                tableLabel:'',
                tableName:'',
                column_id:'',
                tableId:'',
                autoList:[], //存储自动获取内容
                // temList:'',
                listArray1:[],//存储地区类型下拉框列表
                listArray2:[],//存储稻作类型下拉框列表
                listArray3:[],//存储品系类型下拉框列表
                selectedID:'',//存储选择了哪一个下拉框
                sumDataList:[],//保存所有表头的输入
                dataList:[],//保存二级表头的输入
                headerList:[],//一级表头
                secondList:[],//存储二级表头内容
                secondHeaderList:[],//存储有跨列的字段名
                isOpen:false,//控制表格的前段和后端展开
                tdWidth:'',//一级表的宽度
                tdSecondWidth:'',//二级表的宽度
                tdSecondFieldWidth:'',//二级表头的输入框宽度
                data:[],
                detialDialogVisible:false,
                tableFlag:'',//保存当前点击的字段名
                fieldColIndex:0,//存储二级字段所在列位置
                fieldRowIndex:0,//存储二级字段所在行位置
                testValue:0,
                getField_1_1:'',//第一行的‘县（市、区）’
                fieldName:'',//下拉框所属的字段名称
            }
        },

        mounted() {
            // this.initData();
            // this.getTableData();
            // this.getSelectContent1();
            // this.getSelectContent2();
        }
    ,
        methods:{
            //初始化数据
            initData() {
                this.tableLabel= "监测县(市、区)水稻涝害调查表";
                //this.getHeaderList();
                //this.getTableData();
            },
            //获取表数据
            getTableData(people,column_id){
                const _this = this;
                axios({
                    url: '/getAllData0906',
                    method:'get',
                    params:{
                        people:people,
                        column_id:column_id,
                        beginYear:0,
                        endYear:0,
                    }
                }).then(res=>{
                    console.log(res);
                    _this.data=res.data;
                }).catch(err=>{
                    return this.$message.error("查询数据失败！");
                });
            },
            headerStyle({row,column,rowIndex,columnIndex}){
                //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
                if(rowIndex === 2||3){ 
                // return {display:"none"};
                return{height:'0',padding:'2px',fontSize:'7px',background:'#6E8B3D',color:'#FFFFFF',}
                }
                if(rowIndex === 0){
                return{height:'0',padding:'2px',fontSize:'7px',background:'#6E8B3D',color:'#FFFFFF',}
                }
                if(rowIndex==1 ){
                    // return {background:'#eee',color:'#606266'};
                    return{height:'0',padding:'1px',fontSize:'5px',background:'#6E8B3D',color:'#FFFFFF'};
                };
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
            //获取地区类型下拉框内容
            getSelectContent1(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"地区类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray1 = res.data;
                        //console.log(this.listArray1);
                    }
                ).catch(err=>{
                    return this.$message.error("获取地区类型失败！");
                });
            },

            //获取稻作类型下拉框内容
            getSelectContent2(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"稻作类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray2 = res.data;
                        //console.log(this.listArray2);
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取品系类型下拉框内容
            getSelectContent3(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"品系类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray3 = res.data;
                        // console.log(this.listArray3);
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
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
                if(inputs.length < this.secondList.length){
                    this.$confirm('还未录入完成，确认退出？')
                        .then(_ => {
                            //不保存当前数据
                            // this.clearAllInputs();
                            done();
                        })
                        .catch(_ => {});
                }
                else if(inputs.length == this.secondList.length){
                    this.$confirm('录入数据是否保存？')
                        .then(_ => {
                            //保存当前数据
                            this.dataList = [];
                            var tem = "";
                            tem += this.fieldRowIndex+"_"+this.fieldColIndex;
                            localStorage.setItem(tem,this.dataList);
                            // this.clearAllInputs();
                            done();
                        })
                        .catch(_ => {});
                }else{
                    this.$confirm('确认关闭？')
                        .then(_ => {
                            //不保存当前数据
                            // this.clearAllInputs();
                            done();
                        })
                        .catch(_ => {});
                }

            },

            //取消按钮
            cancelOp(){
                this.detialDialogVisible = false;
            },


            //保存
            getFormList(flag){
                this.$confirm("是否上报？").then(_=>{
                var date = this.getNowTime();
                //this.data.modification_time = date;
                for(var i = 0 ;i < this.data.length ; ++i){
                    this.data[i].modification_time = date;
                    this.data[i].in_use = flag;
                }
                if (this.data[0].field_5_1 != null){
                    this.data[0].field_5_1 = this.data[0].field_5_1.toString().replace('“','').replace('”','');
                }
                if (this.data[0].field_6_1 != null){
                    this.data[0].field_6_1 = this.data[0].field_6_1.toString().replace('“','').replace('”','');
                }
                if (this.data[0].field_7_1 != null){
                    this.data[0].field_7_1 = this.data[0].field_7_1.toString().replace('“','').replace('”','');
                }
                var list = [];
                for(var i = 0 ; i < this.data.length ; ++i){
                    list.push(this.data[i]);
                }
                var str = JSON.stringify(list[0]);
                var str = '';
                for(var i = 0 ; i < list.length ; ++i){
                    str += JSON.stringify(list[i]) ;
                }
                this.data = list;
                this.saveInputData(str);
                if(this.column_id != '' && this.tableName != ''){
                    this.fixDataBackState(this.tableName,this.column_id);
          }  
        }).catch(_=>{
          return this.$message.info("您取消了操作！");
        });
                
            },

            //生成保存函数
            saveInputData(str){
                axios({
                    url:'/getInputDataTable0906',
                    method:'post',
                    // headers: {
                    //     'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
                    // },
                    params:{
                        data:str
                    }
                }).then(res=>{
                    // //console.log(res);
                    this.$message.success("操作成功！");
                }).catch(err=>{
                    return this.$message.error("数据保存失败！");
                })

            },

            //填写详细信息
            fillDetailInfo(e){
                var temData = localStorage.getItem('1_4');
                var Index = e.target.parentNode.cellIndex;
                this.fieldColIndex = Index;//把当前点击的字段所在列保存
                this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
                this.fieldRowIndex = this.fieldRowIndex-2;
                //console.log(this.fieldRowIndex);
                var getTdInnerHTML="";
                getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
                this.tableFlag = getTdInnerHTML;

                // this.secondChooseField(getTdInnerHTML);
                this.secondList = [];
                for(var i = 0 ; i < this.headerList.length ; ++i){
                    if(this.headerList[i].column_name == getTdInnerHTML){
                        this.secondList = this.headerList[i].children;
                    }
                }
                //console.log(this.headerList);
                //console.log(this.secondHeaderList);
                //console.log(this.secondList);
                this.tdSecondWidth = 100/this.secondHeaderList.length;
                this.tdSecondFieldWidth = 100/this.secondList.length;
                this.detialDialogVisible = true;
            },

            // 获取当前时间
            getNowTime: function () {
                let dateTime
                let yy = new Date().getFullYear()
                let mm = new Date().getMonth() + 1 < 10 ? '0'+ (new Date().getMonth() + 1) :new Date().getMonth() + 1
                let dd = new Date().getDate() < 10 ? '0'+ new Date().getDate():new Date().getDate()
                let hh = new Date().getHours()  < 10 ? '0'+ new Date().getHours() :new Date().getHours()
                let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes():new Date().getMinutes()
                let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds():new Date().getSeconds()
                // if(hh < 10){
                //     dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
                // }else{
                    dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                // }
                return dateTime
            },

        },
        created(){
            this.initData();
            var people = localStorage.getItem('ms_username');
            //console.log(people);
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            this.getTableData(people,this.column_id);

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