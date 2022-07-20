<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻监测点化除管理记录表
            </span>
        </div>
        <el-collapse v-model="activeNames" >
            <template v-for="(item,index) in reverseTableList" >
                <el-collapse-item  :name="totalData.length-index-1" :title=stepTitle[totalData.length-index-1] >
                    <el-form :inline="true"  label-width="180px">
                        <el-form-item label="日期" >
                            <el-date-picker
                                    align="right"
                                    type="date"
                                    v-model="tab020602DataList[item.date]"
                                    :picker-options="pickerOptions"
                                    value-format=“yyyy-MM-dd”
                                    :disabled="totalData.length-index-1 < tab020602CurrentStep "
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="除草剂名称" >
                            <el-autocomplete style="width: 220px;"
                                             class="inline-input"
                                             :fetch-suggestions="querySearchName"
                                             v-model="tab020602DataList[item.herbicide]"
                                             :disabled="totalData.length-index-1 < tab020602CurrentStep ">
                            </el-autocomplete>
                        </el-form-item>
                        <el-form-item label="亩用量/kg" >
                            <el-input style="width: 220px;"
                                      maxlength="6"
                                      placeholder="请输入数字且不超过6位"
                                      oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                      v-model="tab020602DataList[item.quantity]"
                                      :disabled="totalData.length-index-1 < tab020602CurrentStep ">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="方法" >
                            <el-autocomplete
                                    style="width: 220px;"
                                    class="inline-input"
                                    :fetch-suggestions="querySearchWay"
                                    v-model="tab020602DataList[item.way]"
                                    :disabled="totalData.length-index-1 < tab020602CurrentStep ">
                            </el-autocomplete>
                        </el-form-item>
                        <el-form-item label="生育进程" >
                            <el-autocomplete
                                    style="width: 220px;"
                                    class="inline-input"
                                    :fetch-suggestions="querySearchProcess"
                                    v-model="tab020602DataList[item.process]"
                                    :disabled="totalData.length-index-1 < tab020602CurrentStep ">
                            </el-autocomplete>
                        </el-form-item>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab020602CurrentStep && this.tab020602CurrentStep != 3">
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
                tab020602CurrentStep:0,
                tab020602DataList:[],
                tab020602_1:'',
                tab020602_2:'',
                people:'',
                point:'',
                latestData:'',
                // 步骤标题
                stepTitle: [ '化除1-播栽前', '化除2-播栽后或出苗后','化除3播栽后或出苗后'],
                pickerOptions: {
                    disabledDate: (time) => {
                        return time.getTime() < new Date(this.latestData).getTime();
                    }
                },
                totalData:[],
                ways: [{ "value": "撒施" },{ "value": "喷雾" },{ "value": "喷施" },
                    { "value": "拌种" },{ "value": "飞防" }],
                huaNames: [{ "value": "乙草胺" },{ "value": "丙草氨" },{ "value": "灭草松" }],
                processName: [{ "value": "播种期" },{ "value": "出苗期" },{ "value": "移栽期" },
                    { "value": "够苗期" },{ "value": "有效分蘗临界叶龄期" },{ "value": "拔节期" },
                    { "value": "孕穗期" },{ "value": "抽穗期" },{ "value": "齐穗期" },
                    { "value": "成熟期" },{ "value": "全生育期" }],
            }
        },
        methods: {
            querySearchWay(queryString, cb) {
                var ways = this.ways;
                var results = queryString ? ways.filter(this.createFilter(queryString)) : ways;
                cb(results);
            },
            querySearchName(queryString, cb) {
                var huaNames = this.huaNames;
                var results = queryString ? huaNames.filter(this.createFilter(queryString)) : huaNames;
                cb(results);
            },
            querySearchProcess(queryString, cb) {
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

                    this.tab020602DataList.modification_time = date;
                    this.tab020602DataList.in_use = 1;

                    if (this.tab020602DataList.field_7_1 != null){
                        this.tab020602DataList.field_7_1 =this.tab020602DataList.field_7_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab020602DataList.field_8_1 != null){
                        this.tab020602DataList.field_8_1 =this.tab020602DataList.field_8_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab020602DataList.field_9_1 != null){
                        this.tab020602DataList.field_9_1 =this.tab020602DataList.field_9_1.toString().replace('“','').replace('”','');
                    }

                    var list = this.tab020602DataList;
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
                    url:'/getInputDataTableTwo0602',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab020602CurrentStep(this.people,this.point)

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
            gettab020602CurrentStep(people,point){
                axios({
                    url:'/getAllDataTwo0602',
                    method:'get',
                    params:{
                        people:people,
                        point:point,
                        column_id: "",
                        beginYear:0,
                        endYear:0
                    }
                }).then(
                    res=>{
						if(res.data.length > 0){
							this.tab020602DataList = [];
							this.totalData =[];
							this.tab020602DataList = res.data[0];
							//console.log(this.totalData);
							this.tab020602CurrentStep=0;
							for(var j = 0 ; j < 3 ; ++j){
							    var start = 7+j;
							    var str = "field_"+start+"_1";
							    var str1 = "field_"+start+"_2";
							    if(this.tab020602DataList[str] !=null && this.tab020602DataList[str] !=''){
							        this.tab020602CurrentStep += 1;
							        this.latestData = this.tab020602DataList[str];
							        var currenrData = {};
							        currenrData.date = "field_"+start+"_1";
							        currenrData.herbicide = "field_"+start+"_2";
							        currenrData.quantity = "field_"+start+"_3";
							        currenrData.way = "field_"+start+"_4";
							        currenrData.process = "field_"+start+"_5";
							        this.totalData.push(currenrData);
							    }else{
							        var nextData = {};
							        nextData.date = "field_"+start+"_1";
							        nextData.herbicide = "field_"+start+"_2";
							        nextData.quantity = "field_"+start+"_3";
							        nextData.way = "field_"+start+"_4";
							        nextData.process = "field_"+start+"_5";
							        this.totalData.push(nextData);
							        break;
							    }
							}
							//console.log(this.totalData);
							if(this.tab020602CurrentStep > this.currentStep){
							    this.currentStep = this.tab020602CurrentStep;
							}
							
							//console.log(this.tab020602CurrentStep);
							
							this.maxStep = this.currentStep;
							for(var i = 1 ; i <= this.tab020602CurrentStep ; ++i){
							    var item = ""+i+"";
							    this.activeNames.push(item);
							}
						}
                       
                    }).catch(err=>{
                    return this.$message.error("获取数据失败！");
                });
            },
        },

        created(){
            this.people = localStorage.getItem('ms_username');
            this.point = this.$route.query.point;
            this.gettab020602CurrentStep(this.people,this.point);
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