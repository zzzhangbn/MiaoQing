<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度监测县(市、区)水稻双季稻收获进度调查（晚稻）
            </span>
        </div>
        <el-collapse v-model="activeNames" >
            <template v-for="(item,index) in reverseTableList" >
                <el-collapse-item  :name="totalData.length-index-1" :title=stepTitle[totalData.length-index-1] >
                    <el-form :inline="true"  label-width="180px">
                        <template v-if="totalData.length-index-1 == 0" >
                            <el-form-item label="总面积（万亩）" >
                                <el-input style="width: 220px;"
                                          placeholder="请输入数字且不超过8位"
                                          maxlength="8"
                                          oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                          v-model="tab020703DataList[item.str]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep ">
                                </el-input>
                            </el-form-item>
                        </template>
                        <template v-if="totalData.length-index-1 != 4" >
                            <el-form-item label="日期" >
                                <el-date-picker
                                        v-model="tab020703DataList[item.str1]"
                                        align="right"
                                        type="date"
                                        :picker-options="pickerOptions"
                                        value-format=“yyyy-MM-dd”
                                        :disabled="totalData.length-index-1 < tab020703CurrentStep"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="收获面积（万亩）)" >
                                <el-input style="width: 220px;"
                                          placeholder="请输入数字且不超过8位"
                                          maxlength="8"
                                          oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                          v-model="tab020703DataList[item.str21]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep"
                                          @blur="getSum(item.str21,item.str22)">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="占比" >
                                <el-input style="width: 220px;"
                                          v-model="tab020703DataList[item.str22]"
                                          :disabled="true"
                                          @blur="getSum(item.str21,item.str22)">
                                </el-input>
                            </el-form-item>
                        </template>
                        <template v-if="totalData.length-index-1 == 4">
                            <el-form-item label="面积(万亩)" >
                                <el-input style="width: 220px;"
                                          placeholder="请输入数字且不超过8位"
                                          maxlength="8"
                                          oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                          v-model="tab020703DataList[item.str1]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="亩有效穗(万)" >
                                <el-input style="width: 220px;"
                                          placeholder="请输入数字且不超过6位"
                                          maxlength="6"
                                          oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                          v-model="tab020703DataList[item.str2]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep"
                                          @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)"
                                >
                                </el-input>
                            </el-form-item>
                            <el-form-item label="穗总粒数(个)" >
                                <el-input style="width: 220px;"
                                          placeholder="请输入数字且不超过6位"
                                          maxlength="6"
                                          oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                          v-model="tab020703DataList[item.str3]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep"
                                          @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="结实率(％)" >
                                <el-input style="width: 220px;"
                                          placeholder="请输入数字且不超过6位"
                                          maxlength="6"
                                          oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                          v-model="tab020703DataList[item.str4]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep"
                                          @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="千粒重(g)" >
                                <el-input style="width: 220px;"
                                          v-model="tab020703DataList[item.str5]"
                                          :disabled="totalData.length-index-1 < tab020703CurrentStep"
                                          @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="八五折理论产量(kg/亩)" >
                                <el-input style="width: 220px;"
                                          v-model="tab020703DataList[item.str6]"
                                          :disabled="true"
                                          placeholder="自动计算">
                                </el-input>
                            </el-form-item>
                        </template>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab020703CurrentStep && this.tab020703CurrentStep != 5">
                    <el-button  @click="getFormList($event)">一键上报</el-button>
                </template>
                <router-link  :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
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
                tab020703CurrentStep:0,
                tab020703DataList:[],
                tab020703_0:'',
                tab020703_1:'',
                tab020703_2_1:'',
                tab020703_2_2:'',
                tab020703_2:'',
                tab020703_3:'',
                tab020703_4:'',
                tab020703_5:'',
                tab020703_6:'',
                people:'',
                latestData:'',
                pickerOptions: {
                    disabledDate: (time) => {
                        return time.getTime() < new Date(this.latestData).getTime();
                    }
                },
                totalData:[],
                activeNames: [],
                // 步骤标题
                stepTitle: [ '收获进度1', '收获进度2', '收获进度3','收获进度4','晚稻实测产']
            };
        },
        methods:{
            test(){
                //console.log(this.tab020703DataList);
            },
            //上报
            getFormList(e){
                this.$confirm("是否上报？").then(_ => {
                    //console.log(this.tab020703DataList);
                    var date = this.getNowTime();
                    this.tab020703DataList.in_use = 1;
                    this.tab020703DataList.modification_time = date;
                    if (this.tab020703DataList.field_4_1 != null) {
                        this.tab020703DataList.field_4_1 = this.tab020703DataList.field_4_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab020703DataList.field_5_1 != null) {
                        this.tab020703DataList.field_5_1 = this.tab020703DataList.field_5_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab020703DataList.field_6_1 != null) {
                        this.tab020703DataList.field_6_1 = this.tab020703DataList.field_6_1.toString().replace('“', '').replace('”', '');
                    }
                    if (this.tab020703DataList.field_7_1 != null) {
                        this.tab020703DataList.field_7_1 = this.tab020703DataList.field_7_1.toString().replace('“', '').replace('”', '');
                    }
                   

                    var list = this.tab020703DataList;
                    var str = '';
                    str = JSON.stringify(list);
                    this.saveInputData(str);
                }).catch(_=> {
                    return this.$message.info("您取消了操作！");
                });
            },
            //生成保存函数
            saveInputData(list){
                axios({
                    url:'/getInputDataTableTwo0703',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab020703CurrentStep(this.people);
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
                    if(this.currentStep <= this.tab020703CurrentStep){
                        var gap5 = 4 + index;
                        var gap4 = 3 + index;
                        this.tab020703_0 = "field_"+gap4;
                        this.tab020703_1 = "field_"+gap5+"_1";
                        this.tab020703_2 = "field_"+gap5+"_2";
                        this.tab020703_2_1 = "field_"+gap5+"_2_1";
                        this.tab020703_2_2 = "field_"+gap5+"_2_2";
                        this.tab020703_3 = "field_"+gap5+"_3";
                        this.tab020703_4 = "field_"+gap5+"_4";
                        this.tab020703_5 = "field_"+gap5+"_5";
                        this.tab020703_6 = "field_"+gap5+"_6";
                        //console.log(this.tab020703_0);
                        //console.log(this.tab020703_1);
                        //console.log(this.tab020703_2);
                        //console.log(this.tab020703_3);
                        //console.log(this.tab020703_4);
                        //console.log(this.tab020703_5);
                        //console.log(this.tab020703_6);
                    }
                }
            },
            getToatl(field_2,field_3,field_4,field_5,field_6){
                if(this.tab020703DataList[field_2]!=null && this.tab020703DataList[field_3]!=null && this.tab020703DataList[field_4]!=null && this.tab020703DataList[field_5]!=null
                    && this.tab020703DataList[field_2]!='' && this.tab020703DataList[field_3]!='' && this.tab020703DataList[field_4]!='' && this.tab020703DataList[field_5]!=''){

                    this.tab020703DataList[field_6] = (Math.round((this.tab020703DataList[field_2]*this.tab020703DataList[field_3]*this.tab020703DataList[field_4]
                        *this.tab020703DataList[field_5]))*0.0001*0.85).toFixed(2);
                }
            },
            getSum(field_1,field_2){
                //console.log(this.tab020703DataList[field_1]);
                this.tab020703DataList[field_2] = (Math.round((this.tab020703DataList[field_1]/this.tab020703DataList.field_3) * 100*100)/100).toFixed(2)+'%';
            },

            //获取表7-2
            gettab020703CurrentStep(people){
                axios({
                    url:'/getAllDataTwo0703',
                    method:'get',
                    params:{
                        people:people,
                        column_id: "",
                        beginYear:0,
                        endYear:0
                    }
                }).then(
                    res=>{
						if(res.data.length > 0){
							this.tab020703DataList = [];
							this.totalData =[];
							this.tab020703DataList = res.data[0];
							//console.log(this.tab020703DataList);
							this.tab020703CurrentStep=0;
							for(var j = 0 ; j < 5 ; ++j){
							    var start = 4+j;
							    var start0 = 3+j;
							    var str = "field_"+start0;
							    var str1 = "field_"+start+"_1";
							    var str2 = "field_"+start+"_2";
							    var str21 = "field_"+start+"_2_1";
							    var str22 = "field_"+start+"_2_2";
							    var str3 = "field_"+start+"_3";
							    var str4 = "field_"+start+"_4";
							    var str5 = "field_"+start+"_5";
							    var str6 = "field_"+start+"_6";
							    if(this.tab020703DataList[str1] !=null && this.tab020703DataList[str1] !=''){
							        this.tab020703CurrentStep += 1;
							        this.latestData = this.tab020703DataList[str1];
							        var currenrData = {};
							        currenrData.str = str;
							        currenrData.str1 = str1;
							        currenrData.str2 = str2;
							        currenrData.str3 = str3;
							        currenrData.str4 = str4;
							        currenrData.str5 = str5;
							        currenrData.str6 = str6;
							        currenrData.str21 = str21;
							        currenrData.str22 = str22;
							        this.totalData.push(currenrData);
							    }else{
							        var nextData = {};
							        nextData.str = str;
							        nextData.str1 = str1;
							        nextData.str2 = str2;
							        nextData.str3 = str3;
							        nextData.str4 = str4;
							        nextData.str5 = str5;
							        nextData.str6 = str6;
							        nextData.str21 = str21;
							        nextData.str22 = str22;
							        this.totalData.push(nextData);
							        break;
							    }
							}
							//console.log(this.totalData);
							if(this.tab020703CurrentStep > this.currentStep){
							    this.currentStep = this.tab020703CurrentStep;
							}
							
							this.maxStep = this.currentStep;
							for(var i = 1 ; i <= this.tab020703CurrentStep ; ++i){
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
            this.gettab020703CurrentStep(this.people);
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