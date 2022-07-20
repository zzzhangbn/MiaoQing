<template>
    <div class="demo-image__placeholder">
        <!-- <Child v-show="false"  @tableCode="tableCode"></Child>  -->
        <template v-if="column_id == ''|| column_id == null">
            <router-link :to="{name:'/TableMainTwo',params: {activeNum: 1}}">
                <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
                <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <!-- <span style="font-size: 20px;color:gray;margin-left: 41%;">{{tableId}}</span> -->
        <div class ="table-s" id="containner_div" style="width: 100%;height: 70%;">

            <table  id="sum_tab"  style="width: 100%;overflow-x: auto;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr style="background-color: #6E8B3D;color:white;">
                    <td colspan="14">2021年度监测县(市、区)水稻双季稻收获进度调查（早稻）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 10%;" >县（市、区）</td>
                    <td style="width: 10%;">地区类型</td>
                    <td style="width: 10%;">总面积（万亩）</td>
                    <td style="width: 10%;">收获进度1（7月15日）</td>
                    <td style="width: 10%;">收获进度2（7月25日）</td>
                    <td style="width: 10%;">收获进度3（8月5日）</td>
                    <td style="width: 10%;">早稻实测产</td>
                </tr>
                <template v-for="(item,index) in data">
            <tr>
                <td><el-input v-model="data[index].field_1" disabled></el-input></td>
                <td><el-input v-model="data[index].field_2" disabled></el-input></td>
                <td><el-input v-model="data[index].field_3" ></el-input></td>
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
        <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="50%" :before-close="handleClose">
            <table style="width: 90%;border: 1px solid lightgray;line-height: 40px;" border="1" cellspacing="0">
                <template v-if="tableFlag == '收获进度1（7月15日）'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td colspan="2">收获面积（万亩）</td>
                    </tr>
                    <tr>
                        <td style="width: 50%;"><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_4_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td style="width: 25%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_4_2_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 25%;">
                            <template v-if="data[fieldRowIndex].field_4_2_1!=null && data[fieldRowIndex].field_4_2_1!=''">
                                <span class="span">{{Number((data.field_4_2_1/data.field_3)*100).toFixed(2)}}%</span>
                            </template>
                            <template v-if="data[fieldRowIndex].field_4_2_1==null || data[fieldRowIndex].field_4_2_1==''">
                                <span style="text-align: center;display:block;">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '收获进度2（7月25日）'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td colspan="2">收获面积（万亩）</td>
                    </tr>
                    <tr>
                        <td style="width: 50%;"><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_5_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td style="width: 25%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_5_2_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 25%;">
                            <template v-if="data[fieldRowIndex].field_4_2_1!=null && data[fieldRowIndex].field_5_2_1!=''">
                                <span class="span">{{Number((data.field_5_2_1/data.field_3)*100).toFixed(2)}}%</span>
                            </template>
                            <template v-if="data[fieldRowIndex].field_5_2_1==null || data[fieldRowIndex].field_5_2_1==''">
                                <span style="text-align: center;display:block;">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '收获进度3（8月5日）'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td colspan="2">收获面积（万亩）</td>
                    </tr>
                    <tr>
                        <td style="width: 50%;"><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_6_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td style="width: 25%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_6_2_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 25%;">
                            <template v-if="data[fieldRowIndex].field_6_2_1!=null && data[fieldRowIndex].field_6_2_1!=''">
                                <span class="span">{{Number((data.field_6_2_1/data.field_3)*100).toFixed(2)}}%</span>
                            </template>
                            <template v-if="data[fieldRowIndex].field_6_2_1==null || data[fieldRowIndex].field_6_2_1==''">
                                <span style="text-align: center;display:block;">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '早稻实测产'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积(万亩)</td>
                        <td >亩有效穗(万)</td>
                        <td>穗总粒数(个)</td>
                        <td>结实率(％)</td>
                        <td>千粒重(g)</td>
                        <td>八五折理论产量(kg/亩)</td>
                    </tr>
                    <tr>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_1" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
                        <td style="width: 20%">
                            <template v-if=" data[fieldRowIndex].field_7_2!='' && data[fieldRowIndex].field_7_3!='' && data[fieldRowIndex].field_7_4!='' && data[fieldRowIndex].field_7_5!='' ">
                                <span class="span">{{Number(data[fieldRowIndex].field_7_2) * Number(data[fieldRowIndex].field_7_3) * Number(data[fieldRowIndex].field_7_4) * Number(data[fieldRowIndex].field_7_5) * 0.0001}}</span>
                            </template>
                            <template v-if=" data[fieldRowIndex].field_7_2=='' || data[fieldRowIndex].field_7_3=='' || data[fieldRowIndex].field_7_4=='' || data[fieldRowIndex].field_7_5=='' ">
                                <span style="text-align: center;display:block;">暂无</span>
                            </template>
                        </td>
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
                column_id:'',
            }
        },

        mounted() {
            // this.initData();
            // this.getTableData();
        },

        methods:{
            //初始化数据
            initData() {
                //this.getHeaderList();
                this.getTableData();
            },
            //获取表数据
            getTableData(people,column_id){
                const _this = this;
                axios({
                    url: '/getAllDataTwo0702',
                    method:'get',
                    params:{
                        people:people,
                        column_id:column_id,
                        beginYear:0,
                        endYear:0,
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

            //关闭按钮
            cancelOp(){
                //console.log(this.tableFlag)
                if(this.tableFlag == '收获进度1（7月15日）'){
                    var spans = document.getElementsByClassName("span");
                    this.data.field_4_2_2 = spans[0].innerHTML;
                    //console.log(this.data.field_4_1);
                    //console.log(typeof this.data.field_4_1);
                }
                if(this.tableFlag == '收获进度2（7月25日）'){
                    var spans = document.getElementsByClassName("span");
                    this.data.field_5_2_2 = spans[0].innerHTML;
                    //console.log(this.data.field_5_2_2);
                    //console.log(this.data.field_5_1);
                }
                if(this.tableFlag == '收获进度3（8月5日）'){
                    var spans = document.getElementsByClassName("span");
                    this.data.field_6_2_2 = spans[0].innerHTML;
                    //console.log(this.data.field_6_2_2);
                    //console.log(this.data.field_6_1);
                }
                if(this.tableFlag == '早稻实测产'){
                    var spans = document.getElementsByClassName("span");
                    this.data.field_7_6 = spans[0].innerHTML;
                    //console.log(this.data.field_10_6);
                }
                this.detialDialogVisible = false;
            },


            //保存
            getFormList(flag){
                var date = this.getNowTime();
                for(var i = 0 ;i < this.data.length ; ++i){
                    this.data[i].modification_time = date;
                    this.data[i].in_use = flag;
                    if (this.data[i].field_4_1 != null){
                        this.data[i].field_4_1 = this.data[i].field_4_1.replace('“','').replace('”','')
                    }
                    if (this.data[i].field_5_1 != null){
                        this.data[i].field_5_1 = this.data[i].field_5_1.replace('“','').replace('”','')
                    }
                    if (this.data[i].field_6_1 != null){
                        this.data[i].field_6_1 = this.data[i].field_6_1.replace('“','').replace('”','')
                    }
                }



                var list = [];
                for(var i = 0 ; i < this.data.length ; ++i){
                    list.push(this.data[i]);
                }
                var str = JSON.stringify(list[0]);
                //console.log(str)
                var str = '';
                for(var i = 0 ; i < list.length ; ++i){
                    str += JSON.stringify(list[i]) ;
                }

                this.data = list;
                this.saveInputData(str);
                if(this.column_id != '' && this.tableName != ''){
                    this.fixDataBackState(this.tableName,this.column_id);
                }
            },

            //生成保存函数
            saveInputData(str){
                axios({
                    url:'/getInputDataTableTwo0702',
                    method:'post',
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
            var people = localStorage.getItem('ms_username');
            //console.log(people)
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