<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻监测点灌浆速率调查表(晚稻)
            </span>
        </div>
        <el-collapse v-model="activeNames" @change="handleChange($event)">
            <template v-for="(item,index) in reverseTableList" >
                <el-collapse-item  :name="totalData.length-index-1" :title=stepTitle[totalData.length-index-1] >
                    <el-form :inline="true"  label-width="180px">
                        <el-form-item label="日期" >
                            <el-date-picker
                                    v-model="tab1002DataList[item.date]"
                                    align="right"
                                    type="date"
                                    :picker-options="pickerOptions"
                                    value-format=“yyyy-MM-dd”
                                    :disabled="totalData.length-index-1 < tab1002CurrentStep "
                                    placeholder="选择日期"
                                    @blur="getToatl(item.date,item.weight,item.v)">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="千粒重(克)" >
                            <el-input style="width: 220px;"
                                      maxlength="6"
                                      placeholder="请输入数字且不超过6位"
                                      oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                      v-model="tab1002DataList[item.weight]"
                                      :disabled="totalData.length-index-1 < tab1002CurrentStep "
                                      @blur="getToatl(item.date,item.weight,item.v)">
                            </el-input>
                        </el-form-item>
                        <template v-if="totalData.length-index-1 != 0">
                            <el-form-item label="灌浆速率（克/千粒•天）" >
                                <el-input style="width: 220px;"
                                          placeholder="自动生成"
                                          v-model="tab1002DataList[item.v]"
                                          :disabled="true"
                                          @blur="getToatl(item.date,item.weight,item.v)">
                                </el-input>
                            </el-form-item>
                        </template>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab1002CurrentStep && this.tab1002CurrentStep != 6">
                    <el-button  @click="getFormList($event)">一键上报</el-button>
                </template>
                <router-link  :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
                    <el-button >返回</el-button>
                </router-link>
            </div>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    export default{
        data() {
            return {
                currentStep:0,//当前进程数
                maxStep:0,//最大进程数
                tab1002CurrentStep:0,
                tab1002DataList:[],
                tab1002_1:'',
                tab1002_2:'',
                tab1002_3:'',
                // 步骤标题
                stepTitle: [ '灌浆速率测定1（扬花）', '灌浆速率测定2（扬花+7天）', '灌浆速率测定3（扬花+14天）','灌浆速率测定4（扬花+21天）', '灌浆速率测定5（扬花+28天）','灌浆速率测定6（扬花+35天）'],
                pickerOptions: {
                    disabledDate: (time) => {
                        return time.getTime() < new Date(this.latestData).getTime();
                    }
                },
                totalData:[],
                activeNames: []
            };
        },
        methods: {
            test(){
                //console.log(this.tab1002DataList);
            },
            //上报
            getFormList(e){
                this.$confirm("是否上报？").then(_ => {
                    var date = this.getNowTime();
                    this.tab1002DataList.in_use = 1;
                    this.tab1002DataList.modification_time = date;
                    if (this.tab1002DataList.field_12_1 != null) {
                        this.tab1002DataList.field_12_1 = this.tab1002DataList.field_12_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab1002DataList.field_7_1 != null) {
                        this.tab1002DataList.field_7_1 = this.tab1002DataList.field_7_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab1002DataList.field_8_1 != null) {
                        this.tab1002DataList.field_8_1 = this.tab1002DataList.field_8_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab1002DataList.field_9_1 != null) {
                        this.tab1002DataList.field_9_1 = this.tab1002DataList.field_9_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab1002DataList.field_10_1 != null) {
                        this.tab1002DataList.field_10_1 = this.tab1002DataList.field_10_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab1002DataList.field_11_1 != null) {
                        this.tab1002DataList.field_11_1 = this.tab1002DataList.field_11_1.toString().replace('“', '').replace('”', '');
                    }

                    var list = this.tab1002DataList;
                    var str = '';
                    str = JSON.stringify(list);
                    this.saveInputData(str);
                }).catch(
                    _ => {
                        return this.$message.info("您取消了操作！");
                    }
                );

            },
            //生成保存函数
            saveInputData(list){
                axios({
                    url:'/getInputDataTableTwo1002',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab1002CurrentStep(this.people,this.point);
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
            handleStep(index){
                // index += 1;
                //如果index > currentStep ，不允许跳转
                if(index > this.maxStep){
                    this.currentStep = this.maxStep;
                    return this.$message.info("该进程尚未开启！");
                }
                if(index <= this.maxStep){
                    this.currentStep = index;
                    //修改各表对应的进程下的字段绑定
                    if(this.currentStep <= this.tab1002CurrentStep){
                        var gap5 = 6 + index;
                        this.tab1002_1 = "field_"+gap5+"_1";
                        this.tab1002_2 = "field_"+gap5+"_2";
                        this.tab1002_3 = "field_"+gap5+"_3";
                    }
                }
            },

            gettab1002CurrentStep(people,point){
                axios({
                    url:'/getAllDataTwo1002',
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
							this.tab1002DataList = [];
							    this.totalData =[];
							    this.tab1002DataList = res.data[0];
							    //console.log(this.tab1002DataList);
							    this.tab1002CurrentStep=0;
							    for(var j = 0 ; j < 6 ; ++j){
							        var start = 7+j;
							        var str = "field_"+start+"_1";
							        var str1 = "field_"+start+"_2";
							        var str2 = "field_"+start+"_3";
							        if(this.tab1002DataList[str] !=null && this.tab1002DataList[str] !=''){
							            this.tab1002CurrentStep += 1;
							            this.latestData = this.tab1002DataList[str];
							            var currenrData = {};
							            currenrData.date = str;
							            currenrData.weight = str1;
							            currenrData.v = str2;
							            this.totalData.push(currenrData);
							        }else{
							            var nextData = {};
							            nextData.date = str;
							            nextData.weight = str1;
							            nextData.v = str2;
							            this.totalData.push(nextData);
							            break;
							        }
							    }
							    //console.log(this.totalData);
							    if(this.tab1002CurrentStep > this.currentStep){
							        this.currentStep = this.tab1002CurrentStep;
							    }
							
							    this.maxStep = this.currentStep;
							
							    for(var i = 1 ; i <= this.tab1002CurrentStep ; ++i){
							        var item = ""+i+"";
							        this.activeNames.push(item);
							    }
							    //console.log(this.activeNames);
							
							}
						}
                        
                ).catch(err=>{
                        return this.$message.error("获取数据失败！");
                    }
                );
            },
            getToatl(tab1002_1,tab1002_2,tab1002_3) {
                //console.log(tab1002_1);
                //console.log(tab1002_2);
                //console.log(tab1002_3);
                var start =this.currentStep+6;
                var data = "field_"+start+"_1";
                var weight = "field_"+start+"_2";
                if(this.tab1002DataList[tab1002_2] != null && this.tab1002DataList[weight] != null && this.tab1002DataList[data]
                    && this.tab1002DataList[tab1002_1] != null){
                    var startDate = Date.parse(this.tab1002DataList[data]);
                    var endDate = Date.parse(this.tab1002DataList[tab1002_1].toString().replace('“', '').replace('”', ''));
                    var days = (endDate - startDate) / (1 * 24 * 60 * 60 * 1000);
                    this.tab1002DataList[tab1002_3]=((this.tab1002DataList[tab1002_2]-this.tab1002DataList[weight])/days).toFixed(2);
                }
            }
        },
        created(){
            this.people = localStorage.getItem('ms_username');
            this.point = this.$route.query.point;
            this.gettab1002CurrentStep(this.people,this.point);
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