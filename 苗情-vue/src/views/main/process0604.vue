<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻监测点施肥管理记录表
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
                                v-model="tab0604DataList[item.date]"
                                :picker-options="pickerOptions"
                                value-format=“yyyy-MM-dd”
                                :disabled="totalData.length-index-1 < tab0604CurrentStep "
                                placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="N" >
                            <el-input style="width: 220px;"
                                      maxlength="5"
                                      show-word-limit
                                      placeholder="请输入数字"
                                      oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                      v-model="tab0604DataList[item.N]"
                                      :disabled="totalData.length-index-1 < tab0604CurrentStep">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="P" >
                            <el-input style="width: 220px;"
                                      placeholder="请输入数字"
                                      type="text"
                                      maxlength="5"
                                      show-word-limit
                                      oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                      v-model="tab0604DataList[item.P]"
                                      :disabled="totalData.length-index-1 < tab0604CurrentStep">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="K" >
                            <el-input style="width: 220px;"
                                      maxlength="5"
                                      placeholder="请输入数字"
                                      oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                      v-model="tab0604DataList[item.K]"
                                      :disabled="totalData.length-index-1 < tab0604CurrentStep">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="方法" >
                            <el-autocomplete
                                    style="width: 220px;"
                                    class="inline-input"
                                    v-model="tab0604DataList[item.way]"
                                    :disabled="totalData.length-index-1 < tab0604CurrentStep"
                                    :fetch-suggestions="querySearch"
                                    placeholder="请输入内容"
                            ></el-autocomplete>
                        </el-form-item>
                        <el-form-item label="生育进程" >
                            <el-autocomplete
                                    style="width: 220px;"
                                    class="inline-input"
                                    :fetch-suggestions="querySearchProcess"
                                    v-model="tab0604DataList[item.process]"
                                    :disabled="totalData.length-index-1 < tab0604CurrentStep">
                            </el-autocomplete>
                        </el-form-item>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab0604CurrentStep && this.tab0604CurrentStep != 5">
                    <el-button  @click="getFormList($event)">一键上报</el-button>
                </template>
                <router-link  :to="{name:'/tablemain',params: {activeNum: 1}}">
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
                tab0604CurrentStep:0,
                tab0604DataList:[],
                tab0604_1:'',
                tab0604_2:'',
                people:'',
                point:'',
                latestData:'',
                // 步骤标题
                stepTitle: [ '基肥施肥（以折纯计kg/亩）1','分蘖肥施肥（以折纯计kg/亩）2',
                    '孕穗肥施肥（以折纯计kg/亩）3','孕穗肥施肥（以折纯计kg/亩）4','抽穗叶面肥施肥（以折纯计kg/亩）4'
                ],
                pickerOptions: {
                    disabledDate: (time) => {
                        return time.getTime() < new Date(this.latestData).getTime();
                    }
                },
                totalData:[],
                ways: [{ "value": "撒施" },{ "value": "喷雾" },{ "value": "喷施" },
                    { "value": "拌种" },{ "value": "飞防" }],
                processName: [{ "value": "播种期" },{ "value": "出苗期" },{ "value": "移栽期" },
                    { "value": "够苗期" },{ "value": "有效分蘗临界叶龄期" },{ "value": "拔节期" },
                    { "value": "孕穗期" },{ "value": "抽穗期" },{ "value": "齐穗期" },
                    { "value": "成熟期" },{ "value": "全生育期" }],
            }
        },
        methods: {
            querySearch(queryString, cb) {
                var ways = this.ways;
           
                var results = queryString ? ways.filter(this.createFilter(queryString)) : ways;
                // 调用 callback 返回建议列表的数据
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

                    this.tab0604DataList.modification_time = date;
                    this.tab0604DataList.in_use = 1;

                    if (this.tab0604DataList.field_7_1 != null){
                        this.tab0604DataList.field_7_1 =this.tab0604DataList.field_7_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0604DataList.field_8_1 != null){
                        this.tab0604DataList.field_8_1 =this.tab0604DataList.field_8_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0604DataList.field_9_1 != null){
                        this.tab0604DataList.field_9_1 =this.tab0604DataList.field_9_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0604DataList.field_10_1 != null){
                        this.tab0604DataList.field_10_1 =this.tab0604DataList.field_10_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0604DataList.field_11_1 != null){
                        this.tab0604DataList.field_11_1 =this.tab0604DataList.field_11_1.toString().replace('“','').replace('”','');
                    }
                    var list = this.tab0604DataList;
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
                    url:'/getInputDataTable0604',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab0604CurrentStep(this.people,this.point)

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
            gettab0604CurrentStep(people,point){
                axios({
                    url:'/getAllData0604',
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
						this.tab0604DataList = [];
						this.totalData =[];
						this.tab0604DataList = res.data[0];
						              
						this.tab0604CurrentStep=0;
						for(var j = 0 ; j < 5 ; ++j){
						    var start = 7+j;
						    var str = "field_"+start+"_1";
						    if(this.tab0604DataList[str] !=null && this.tab0604DataList[str] !=''){
						        this.tab0604CurrentStep += 1;
						        this.latestData = this.tab0604DataList[str];
						        var currenrData = {};
						        currenrData.date = "field_"+start+"_1";
						        currenrData.N = "field_"+start+"_2";
						        currenrData.P = "field_"+start+"_3";
						        currenrData.K = "field_"+start+"_4";
						        currenrData.way = "field_"+start+"_5";
						        currenrData.process = "field_"+start+"_6";
						        this.totalData.push(currenrData);
						    }else{
						        var nextData = {};
						        nextData.date = "field_"+start+"_1";
						        nextData.N = "field_"+start+"_2";
						        nextData.P = "field_"+start+"_3";
						        nextData.K = "field_"+start+"_4";
						        nextData.way = "field_"+start+"_5";
						        nextData.process = "field_"+start+"_6";
						        this.totalData.push(nextData);
						        break;
						    }
						}
						       
						if(this.tab0604CurrentStep > this.currentStep){
						    this.currentStep = this.tab0604CurrentStep;
						}
						
						             
						
						this.maxStep = this.currentStep;
						for(var i = 1 ; i <= this.tab0604CurrentStep ; ++i){
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
            this.gettab0604CurrentStep(this.people,this.point);
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