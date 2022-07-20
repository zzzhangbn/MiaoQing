<template>
    <div class="demo-image__placeholder">
        <!-- <Child v-show="false"  @tableCode="tableCode"></Child>  -->
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <!-- <span style="font-size: 20px;color:gray;margin-left: 41%;">{{tableId}}</span> -->
        <div class ="table-s" id="containner_div" style="width: 100%;height: 70%;">

            <table  id="sum_tab"  style="width: 100%;overflow-x: auto;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr style="background-color: #6E8B3D;color:white;">
                    <td colspan="14">监测县(市、区)气象资料列表</td>
                </tr>
                <tr >
                    <td style="width: 15%;" >县（市、区）</td>
                    <td style="width: 7%;">年份</td>
                    <td style="width: 7%;">早稻--抽穗期前15天的</td>
                    <td style="width: 7%;">早稻--抽穗期后15天的</td>
                    <td style="width: 7%;">晚稻--抽穗期前15天的</td>
                    <td style="width: 7%;">晚稻--抽穗期后15天的</td>

                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td><el-input v-model="data[index].field_1_1" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_1_2" ></el-input></td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
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
        <!--<el-button @click="getNewRecord" style="margin-top: 10px;margin-left: 9px;">新增</el-button>-->

        <!--字段详情-->
        <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="60%" :before-close="handleClose">
            <table style="width: 90%;border: 1px solid lightgray;line-height: 40px;" border="1" cellspacing="0">
                <template v-if="tableFlag == '早稻--抽穗期前15天的'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td style="width: 15%;">平均温度</td>
                        <td style="width: 15%;">日最高气温</td>
                        <td style="width: 15%;">降雨量</td>
                        <td style="width: 15%;">日照时数</td>
                        <td style="width: 15%;">晴好天气天数</td>
                        <td style="width: 15%;">阴雨天气天数</td>
                    </tr>
                    <tr>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_2_1" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_2_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_2_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_2_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_2_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_2_6" @blur.prevent="checkFirstFieldInput($event)" /></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '早稻--抽穗期后15天的'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td style="width: 15%;">平均温度</td>
                        <td style="width: 15%;">日最高气温</td>
                        <td style="width: 15%;">降雨量</td>
                        <td style="width: 15%;">日照时数</td>
                        <td style="width: 15%;">晴好天气天数</td>
                        <td style="width: 15%;">阴雨天气天数</td>
                    </tr>
                    <tr>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_3_1" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_3_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_3_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_3_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_3_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_3_6" @blur.prevent="checkFirstFieldInput($event)" /></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '晚稻--抽穗期前15天的'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td style="width: 15%;">平均温度</td>
                        <td style="width: 15%;">日最高气温</td>
                        <td style="width: 15%;">降雨量</td>
                        <td style="width: 15%;">日照时数</td>
                        <td style="width: 15%;">晴好天气天数</td>
                        <td style="width: 15%;">阴雨天气天数</td>
                    </tr>
                    <tr>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_1" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_6" @blur.prevent="checkFirstFieldInput($event)" /></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '晚稻--抽穗期后15天的'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td style="width: 15%;">平均温度</td>
                        <td style="width: 15%;">日最高气温</td>
                        <td style="width: 15%;">降雨量</td>
                        <td style="width: 15%;">日照时数</td>
                        <td style="width: 15%;">晴好天气天数</td>
                        <td style="width: 15%;">阴雨天气天数</td>
                    </tr>
                    <tr>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_1" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td ><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_6" @blur.prevent="checkFirstFieldInput($event)" /></td>
                    </tr>
                </template>
            </table>
            <span slot="footer" class="dialog-footer">
            <el-button @click="cancelOp">关 闭</el-button>
            <!--<el-button type="primary" @click="getInputInfo">暂 存</el-button>-->
                <!-- <el-button @click="clearAllInputs(tableFlag)">清空</el-button> -->
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
                tableName:'',
                tableId:'',
                autoList:[], //存储自动获取内容
                // temList:'',
                listArray:[],//存储下拉框列表
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

        methods:{

            //获取表数据
            getTableData(people,year,point){
                const _this = this;
                axios({
                    //url: '/getAllData0901/202119966486260',
                    url: '/getAllDataTwo0902',
                    method:'get',
                    params:{
                        account:people,
                        yaer:year,
                        point:point
                    }
                }).then(res=>{
                    //console.log(res);
                    _this.data=res.data;
                }).catch(err=>{
                    return this.$message.error("查询数据失败！");
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
                var date = this.getNowTime();
                //this.data.modification_time = date;
                for(var i = 0 ;i < this.data.length ; ++i){
                    this.data[i].modification_time = date;
                    this.data[i].in_use = flag;
                }


                var list = [];
                for(var i = 0 ; i < this.data.length ; ++i){
                    list.push(this.data[i]);
                }

                //console.log(this.date);
                //console.log(typeof this.date);

                //console.log(list);
                //console.log(typeof list);
                //console.log(list[0]);
                var str = JSON.stringify(list[0]);
                //console.log(str)

                //console.log(str)
                //console.log(typeof str)

                this.data = list;
                this.saveInputData(str);
            },

            //生成保存函数
            saveInputData(str){
                axios({
                    url:'/getInputDataTableTwo0902',
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

                    dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss

                return dateTime
            },

        },
        created(){
            var people = localStorage.getItem('ms_username');
            //console.log(people)
            var time = new Date();
            var year = time.getFullYear().toString();
            //console.log(year);
            var point = this.$route.params.point;
            this.getTableData(people,year,point);


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