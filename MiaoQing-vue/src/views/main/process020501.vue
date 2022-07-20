<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                叶片含氮测定仪导出数据
            </span>
        </div>
        <el-collapse v-model="activeNames" @change="handleChange($event)">
            <template v-for="(item,index) in reverseTableList" >
                <el-collapse-item  :name="totalData.length-index-1" :title=stepTitle[totalData.length-index-1] >
                    <el-form :inline="true"  label-width="180px">
                        <el-form-item label="时间" >
                            <el-date-picker
                                    align="right"
                                    type="date"
                                    v-model="tab0501DataList[item.date1]"
                                    :picker-options="pickerOptions"
                                    value-format=“yyyy-MM-dd”
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep "
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="叶绿素（SPAD）">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date2]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="氮含量（mg/g）">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date3]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="叶面温度（℃）">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date4]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="index < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="叶面湿度（%RH）">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date5]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="植物名称">
                            <el-input
                                    style="width: 220px;"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep "
                                    v-model="tab0501DataList[item.date6]"
                            >
                            </el-input>
                        </el-form-item>
                        <el-form-item label="化肥中氮含量（%）">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date7]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="化肥中氮的利用率（%）">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date8]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="目标产量">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date9]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="施肥量">
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    v-model="tab0501DataList[item.date10]"
                                    placeholder="请输入数字"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    :disabled="totalData.length-index-1 < tab0501CurrentStep ">
                            </el-input>
                        </el-form-item>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab0501CurrentStep && this.tab0501CurrentStep != 5">
                    <el-button  @click="getFormList($event)">一键上报</el-button>
                </template>
                <router-link  :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
                    <el-button >返回</el-button>
                </router-link>
            </div>
        </div>
        <!--</div>-->
    </div>
</template>
<script>
    import axios from 'axios'
    export default{
        inject:['reload'],
        data() {
            return {
                activeNames: [],
                currentStep:0,//当前进程数
                maxStep:0,//最大进程数
                tab0501CurrentStep:0,
                tab0501DataList:[],
                tab0501_1:'',
                tab0501_2:'',
                people:'',
                point:'',
                latestData:'',
                // 步骤标题
                stepTitle: [ '够苗期', '拔节期', '孕穗期1',
                    '孕穗期2','抽穗期'],
                pickerOptions: {
                    disabledDate: (time) => {
                        return time.getTime() < new Date(this.latestData).getTime();
                    }
                },
                totalData:[],
            }

        },
        methods: {


            querySearchType4(queryString, cb) {
                var processName = this.processName;
                var results = queryString ? processName.filter(this.createFilter(queryString)) : processName;
                cb(results);
            },
            createFilter(queryString) {
                return (ways) => {
                    return (ways.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
                };
            },
            //上报
            getFormList(e){
                this.$confirm("是否上报？").then(_=>{
                    var date = this.getNowTime();

                    this.tab0501DataList.modification_time = date;
                    this.tab0501DataList.in_use = 1;

                    if (this.tab0501DataList.field_7_1 != null){
                        this.tab0501DataList.field_7_1 =this.tab0501DataList.field_7_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0501DataList.field_8_1 != null){
                        this.tab0501DataList.field_8_1 =this.tab0501DataList.field_8_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0501DataList.field_9_1 != null){
                        this.tab0501DataList.field_9_1 =this.tab0501DataList.field_9_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0501DataList.field_10_1 != null){
                        this.tab0501DataList.field_10_1 =this.tab0501DataList.field_10_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0501DataList.field_11_1 != null){
                        this.tab0501DataList.field_11_1 =this.tab0501DataList.field_11_1.toString().replace('“','').replace('”','');
                    }


                    var list = this.tab0501DataList;

                    var str = '';
                    str = JSON.stringify(list);
                    this.saveInputData(str);
                }).catch(_=>{
                    return this.$message.info("您取消了操作！");
                });
            },
            //生成保存函数
            saveInputData(list){
                axios({
                    url:'/getInputDataTableTwo0501',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab0501CurrentStep(this.people,this.point)

                }).catch(err=>{
                    return this.$message.error("数据保存失败！");
                })
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
            //获取表6-1 - 年度监测县(市、区)中稻生育进度调查表（直播稻） 的当前进程
            gettab0501CurrentStep(people,point){
                axios({
                    url:'/getAllDataTwo0501',
                    method:'get',
                    params:{
                        people:people,
                        point:point,
                        column_id: "",
                        startYear:0,
                        endYear:0
                    }
                }).then(
                    res=>{
                        this.tab0501DataList = [];
                        this.totalData =[];
                        this.tab0501DataList = res.data[0];

                        this.tab0501CurrentStep=0;
                        for(var j = 0 ; j < 5 ; ++j){
                            var start = 7+j;
                            var str = "field_"+start+"_1";
                            var str1 = "field_"+start+"_2";
                            var str2 = "field_"+start+"_3";
                            var str3 = "field_"+start+"_4";
                            var str4 = "field_"+start+"_5";
                            var str5 = "field_"+start+"_6";
                            var str6 = "field_"+start+"_7";
                            var str7 = "field_"+start+"_8";
                            var str8 = "field_"+start+"_9";
                            var str9 = "field_"+start+"_10";
                            if(this.tab0501DataList[str] !=null && this.tab0501DataList[str] !=''){
                                this.tab0501CurrentStep += 1;
                                this.latestData = this.tab0501DataList[str];
                                var currenrData = {};
                                currenrData.date1 = str;
                                currenrData.date2 = str1;
                                currenrData.date3 = str2;
                                currenrData.date4 = str3;
                                currenrData.date5 = str4;
                                currenrData.date6 = str5;
                                currenrData.date7 = str6;
                                currenrData.date8 = str7;
                                currenrData.date9 = str8;
                                currenrData.date10 = str9;
                                this.totalData.push(currenrData);
                            }else{
                                var nextData = {};
                                nextData.date1 = str;
                                nextData.date2 = str1;
                                nextData.date3 = str2;
                                nextData.date4 = str3;
                                nextData.date5 = str4;
                                nextData.date6 = str5;
                                nextData.date7 = str6;
                                nextData.date8 = str7;
                                nextData.date9 = str8;
                                nextData.date10 = str9;
                                this.totalData.push(nextData);
                                break;
                            }
                        }

                        if(this.tab0501CurrentStep > this.currentStep){
                            this.currentStep = this.tab0501CurrentStep;
                        }

                        this.maxStep = this.currentStep;
                        for(var i = 1 ; i <= this.tab0501CurrentStep ; ++i){
                            var item = ""+i+"";
                            this.activeNames.push(item);
                        }
                    }
                ).catch(err=>{
                    return this.$message.error("获取数据失败！");
                });
            },
        },

        created(){
            this.people = localStorage.getItem('ms_username');
            this.point = this.$route.query.point;
            this.gettab0501CurrentStep(this.people,this.point);
        },

        computed: {
            // 反转数组totalData
            reverseTableList() {
            // 设置所有折叠面展开
            const open = [];
            for (var i = 0; i < this.totalData.length; i++) {
                open.push(i)
            }
            this.activeNames = open;

            return this.totalData.reverse();
            }
        },
    };
</script>
<style>
    input:disabled,textarea:disabled{
        opacity: 1;
        -webkit-text-fill-color: black;
    }
    .el-collapse-item__header {
        font-size: 20px;
        color: rgb(0, 102, 102);
    }
</style>