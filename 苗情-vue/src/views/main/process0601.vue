<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻监测点水分管理记录表
            </span>
        </div>
        <el-collapse v-model="activeNames" @change="handleChange($event)">
            <template v-for="(item,index) in reverseTableList" >
                <el-collapse-item  :name="totalData.length-index-1" :title=stepTitle[totalData.length-index-1] >
                    <el-form :inline="true"  label-width="180px">
                        <el-form-item label="日期" >
                            <el-date-picker
                                align="right"
                                type="date"
                                v-model="tab0601DataList[item.date]"
                                :picker-options="pickerOptions"
                                value-format=“yyyy-MM-dd”
                                :disabled="totalData.length-index-1 < tab0601CurrentStep "
                                placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="深度（cm）">
                            <el-input
                                style="width: 220px;"
                                maxlength="6"
                                v-model="tab0601DataList[item.deep]"
                                placeholder="请输入数字"
                                oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                :disabled="totalData.length-index-1 < tab0601CurrentStep ">
                            </el-input>
                        </el-form-item>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab0601CurrentStep && this.tab0601CurrentStep != 8">
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
                tab0601CurrentStep:0,
                tab0601DataList:[],
                tab0601_1:'',
                tab0601_2:'',
                people:'',
                point:'',
                latestData:'',
                // 步骤标题
                stepTitle: [ '时期1（直播秧苗20天/移栽）', '时期2（直播秧苗28天/移栽7天）', '时期3（直播秧苗35天/移栽14天）',
                  '时期4（直播秧苗42天/移栽21天）','时期5（直播秧苗49天/移栽28天）','时期6（直播秧苗56天/移栽35天）',
                  '时期7（直播秧苗66天/移栽42天）','时期8（直播秧苗76天/移栽52天）'],
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

                    this.tab0601DataList.modification_time = date;
                    this.tab0601DataList.in_use = 1;
            
                    if (this.tab0601DataList.field_7_1 != null){
                        this.tab0601DataList.field_7_1 =this.tab0601DataList.field_7_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_8_1 != null){
                        this.tab0601DataList.field_8_1 =this.tab0601DataList.field_8_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_9_1 != null){
                        this.tab0601DataList.field_9_1 =this.tab0601DataList.field_9_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_10_1 != null){
                        this.tab0601DataList.field_10_1 =this.tab0601DataList.field_10_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_11_1 != null){
                        this.tab0601DataList.field_11_1 =this.tab0601DataList.field_11_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_12_1 != null){
                        this.tab0601DataList.field_12_1 =this.tab0601DataList.field_12_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_13_1 != null){
                        this.tab0601DataList.field_13_1 =this.tab0601DataList.field_13_1.toString().replace('“','').replace('”','');
                    }
                    if (this.tab0601DataList.field_14_1 != null){
                        this.tab0601DataList.field_14_1 =this.tab0601DataList.field_14_1.toString().replace('“','').replace('”','');
                    }

                    var list = this.tab0601DataList;

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
                    url:'/getInputDataTable0601',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab0601CurrentStep(this.people,this.point)

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
            gettab0601CurrentStep(people,point){
            axios({
                url:'/getAllData0601',
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
						this.tab0601DataList = [];
						this.totalData =[];
						this.tab0601DataList = res.data[0];
						                  
						this.tab0601CurrentStep=0;
						for(var j = 0 ; j < 8 ; ++j){
						    var start = 7+j;
						    var str = "field_"+start+"_1";
						    var str1 = "field_"+start+"_2";
						    if(this.tab0601DataList[str] !=null && this.tab0601DataList[str] !=''){
						        this.tab0601CurrentStep += 1;
						        this.latestData = this.tab0601DataList[str];
						        var currenrData = {};
						        currenrData.date = str;
						        currenrData.deep = str1;
						        this.totalData.push(currenrData);
						    }else{
						        var nextData = {};
						        nextData.date = str;
						        nextData.deep = str1;
						        this.totalData.push(nextData);
						        break;
						    }
						}
						                 
						if(this.tab0601CurrentStep > this.currentStep){
						    this.currentStep = this.tab0601CurrentStep;
						}
						
						this.maxStep = this.currentStep;
						for(var i = 1 ; i <= this.tab0601CurrentStep ; ++i){
						    var item = ""+i+"";
						    this.activeNames.push(item);
						}
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
                this.gettab0601CurrentStep(this.people,this.point);
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