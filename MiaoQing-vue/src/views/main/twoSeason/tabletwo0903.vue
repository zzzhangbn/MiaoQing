<template>
    <div class="demo-image__placeholder">
        <!-- <Child v-show="false"  @tableCode="tableCode"></Child>  -->
        <template v-if="column_id == ''|| column_id == null">
            <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
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
                    <td colspan="14" style="background-color: #6E8B3D;color: #FFFFFF;">监测县(市、区)水稻倒伏灾害表</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 7%;" >县（市、区）</td>
                    <td style="width: 7%;">地区类型</td>
                    <td style="width: 7%;">稻作类型</td>
                    <td style="width: 7%;">品系类型</td>
                    <td style="width: 7%;">全县种植面积（万亩）</td>
                    <td style="width: 15%;">倒伏时间（月/日-月/日）</td>
                    <td style="width: 7%;">倒伏面积（万亩）</td>
                    <td style="width: 7%;">占比（%）</td>
                    <td style="width: 7%;">其中：成灾面积（万亩）</td>
                    <td style="width: 7%;">其中：绝收面积（万亩）</td>
                    <td style="width: 7%;">产量损失（%）</td>
                    <td style="width: 7%;">倒伏程度（级别）</td>
                    <td style="width: 7%;">主要品种</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td><el-input v-model="data[index].field_1" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_2" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_3" disabled></el-input></td>
                        <td><el-input v-model="data[index].field_4" disabled></el-input></td>
                        <!--<td>-->
                            <!--<el-select v-model="data[index].field_3" placeholder="请选择" @focus="brandKeyChange1">-->
                                <!--<el-option-->
                                        <!--v-for="item in listArray1"-->
                                        <!--:key="item.id"-->
                                        <!--:label="item.list"-->
                                        <!--:value="item.list">-->
                                <!--</el-option>-->
                            <!--</el-select>-->
                        <!--</td>-->
                        <!--<td>-->
                            <!--<el-select v-model="data[index].field_4" placeholder="请选择" @focus="brandKeyChange1">-->
                                <!--<el-option-->
                                        <!--v-for="item in listArray2"-->
                                        <!--:key="item.id"-->
                                        <!--:label="item.list"-->
                                        <!--:value="item.list">-->
                                <!--</el-option>-->
                            <!--</el-select>-->
                        <!--</td>-->

                        <td><el-input v-model="data[index].field_5" ></el-input></td>
                        <td><el-date-picker
                                value-format=“yyyy-MM-dd”
                                v-model="data[index].field_6"
                                type="daterange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker></td>
                        <td><el-input v-model="data[index].field_7" ></el-input></td>
                        <td>
                            <template v-if="data[index].field_5!=null && data[index].field_7!=null">
                                <span class="span">{{Number((data[index].field_7/data[index].field_5)*100).toFixed(2)}}%</span>
                            </template>
                            <template v-if="data[index].field_5==null || data[index].field_7==null">
                                <span>暂无</span>
                            </template>
                        </td>
                        <td><el-input v-model="data[index].field_9" ></el-input></td>
                        <td><el-input v-model="data[index].field_10" ></el-input></td>
                        <td>
                            <template v-if="data[index].field_9!=null && data[index].field_10!=null">
                                <span class="span">{{Number(((data[index].field_9*0.3+data[index].field_10*0.8)*100)/data[index].field_5).toFixed(2)}}%</span>
                            </template>
                            <template v-if="data[index].field_5==null || data[index].field_7==null">
                                <span>暂无</span>
                            </template>
                        </td>
                        <td><el-input v-model="data[index].field_12" ></el-input></td>
                        <td><el-input v-model="data[index].field_13" ></el-input></td>
                    </tr>
                </template>
            </table>
        </div>
    </div>

</template>
<script src="../../../public/js/Vue.min.js"></script>
<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                tableName:'',
                column_id:'',
                tableId:'',
                autoList:[], //存储自动获取内容
                // temList:'',
                listArray1:[],
                listArray2:[],//存储下拉框列表
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
                //this.getHeaderList();
                //this.getTableData();
            },
            //获取表数据
            getTableData(people,column_id){
                const _this = this;
                axios({
                    url: '/getAllDataTwo0903',
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
            //获取稻作类型下拉框内容
            getSelectContent1(){
                axios({
                    url:'/getSelectContent',
                    method:'get',
                    params:{
                        fieldName:"稻作类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray1 = res.data;
                        //console.log(this.listArray1);
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取品系类型下拉框内容
            getSelectContent2(){
                axios({
                    url:'/getSelectContent',
                    method:'get',
                    params:{
                        fieldName:"品系类型",
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
                var spans = document.getElementsByClassName("span");
                this.data[0].field_8 = spans[0].innerHTML;
                this.data[0].field_11 = spans[1].innerHTML;
                if (this.data[0].field_6 != null){
                    this.data[0].field_6 = this.data[0].field_6.toString().replace('“','').replace('”','').replace('“','').replace('”','').replace(',','至');
                }
                var list = [];
                for(var i = 0 ; i < this.data.length ; ++i){
                    list.push(this.data[i]);
                }
                var str = JSON.stringify(list[0]);
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
                    url:'/getInputDataTableTwo0903',
                    method:'post',
                    params:{
                        data:str
                    }
                }).then(res=>{
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
                this.fieldRowIndex = this.fieldRowIndex-1;
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