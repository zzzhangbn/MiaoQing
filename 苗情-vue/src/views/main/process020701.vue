<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻双季稻苗情监测预测产表
            </span>
        </div>
        <el-collapse v-model="activeNames" >
            <template v-for="(item,index) in reverseTableList" >
                <el-collapse-item  :name="totalData.length-index-1" :title=stepTitle[totalData.length-index-1] >
                    <el-form :inline="true"  label-width="180px">
                        <template v-if="totalData.length-index-1 == 0">
                            <el-form-item label="早稻总面积(万亩)" >
                                <el-input
                                        style="width: 220px;"
                                        placeholder="请输入数字且不超过8位"
                                        maxlength="8"
                                        oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                        v-model="tab020701DataList[item.str]"
                                        :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                >
                                </el-input>
                            </el-form-item>
                        </template>
                        <template v-if="totalData.length-index-1 == 2">
                            <el-form-item label="晚稻总面积(万亩)" >
                                <el-input
                                        style="width: 220px;"
                                        placeholder="请输入数字且不超过8位"
                                        maxlength="8"
                                        oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                        v-model="tab020701DataList[item.str]"
                                        :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                >
                                </el-input>
                            </el-form-item>
                        </template>
                        <el-form-item label="面积(万亩)" >
                            <el-input
                                    style="width: 220px;"
                                    maxlength="8"
                                    placeholder="请输入数字且不超过8位"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    v-model="tab020701DataList[item.str1]"
                                    :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="亩有效穗(万)" >
                            <el-input
                                    style="width: 220px;"
                                    placeholder="请输入数字且不超过8位"
                                    maxlength="8"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    v-model="tab020701DataList[item.str2]"
                                    :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="穗总粒数(个)" >
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    placeholder="请输入数字且不超过6位"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    v-model="tab020701DataList[item.str3]"
                                    :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="结实率(％)" >
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    placeholder="请输入数字且不超过6位"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    v-model="tab020701DataList[item.str4]"
                                    :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="千粒重(g)" >
                            <el-input
                                    style="width: 220px;"
                                    maxlength="6"
                                    placeholder="请输入数字且不超过6位"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    v-model="tab020701DataList[item.str5]"
                                    :disabled="totalData.length-index-1 < tab020701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="八五折理论产量(kg/亩)" >
                            <el-input style="width: 220px;"
                                      v-model="tab020701DataList[item.str6]"
                                      :disabled="true"
                                      placeholder="自动填写所得"
                            >
                            </el-input>
                        </el-form-item>
                    </el-form>
                </el-collapse-item>
            </template>
        </el-collapse>
        <div style="width: 100%;height: 100px;margin-top: 25px;">
            <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
                <template v-if="currentStep >= tab020701CurrentStep && this.tab020701CurrentStep != 4">
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
                totalData:[],
                activeNames: [],
                currentStep:0,//当前进程数
                maxStep:0,//最大进程数
                tab020701CurrentStep:0,//表0701
                tab020701DataList:[],//保存表0701 - 年度水稻监测点茎蘖数叶面积构成表 的数据
                tab020701_0:'',
                tab020701_1:'',
                tab020701_2:'',
                tab020701_3:'',
                tab020701_4:'',
                tab020701_5:'',
                tab020701_6:'',
                people:'',
                // 步骤标题
                stepTitle: [ '早稻6月20日前已抽穗扬花', '早稻6月20日~30日抽穗扬花', '晚稻9月10日前已抽穗扬花',
                    '晚稻9月10日以后抽穗扬花'],
            };

        },
        methods: {
            test(){
                //console.log(this.tab020701DataList);
            },
            //上报
            getFormList(e){
                // if (this.tab020701DataList[0][this.tab020701_1] == '' || this.tab020701DataList[0][this.tab020701_2] == ''|| this.tab020701DataList[0][this.tab020701_3] == ''
                //     || this.tab020701DataList[0][this.tab020701_4] == ''|| this.tab020701DataList[0][this.tab020701_5] == ''
                //     || this.tab020701DataList[0][this.tab020701_1] == null || this.tab020701DataList[0][this.tab020701_2] == null|| this.tab020701DataList[0][this.tab020701_3] == null
                //     || this.tab020701DataList[0][this.tab020701_4] == null|| this.tab020701DataList[0][this.tab020701_5] == null) {
                //     this.$alert('数据不能为空!', '错误提示', {
                //         confirmButtonText: '确定',
                //         callback: action => {
                //             /*this.$message({
                //                 type: 'info',
                //                 message: `action: ${ action }`
                //             });*/
                //         }
                //     });
                // }else {
                this.$confirm("是否上报？").then(_ => {
                    var date = this.getNowTime();

                    this.tab020701DataList.in_use = 1;
                    this.tab020701DataList.modification_time = date;

                    var list = this.tab020701DataList;
                    var str = '';
                    str = JSON.stringify(list);
                    this.saveInputData(str);
                }).catch(_ => {
                    return this.$message.info("您取消了操作！");
                });
                //}
            },
            //生成保存函数
            saveInputData(list){
                axios({
                    url:'/getInputDataTableTwo0701',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab020701CurrentStep(this.people);
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
                    if(this.currentStep <= this.tab020701CurrentStep){
                        var gap5 = 3 + index;
                        var gap4 = 2 + index;
                        this.tab020701_0 = "field_"+gap4;
                        this.tab020701_1 = "field_"+gap5+"_1";
                        this.tab020701_2 = "field_"+gap5+"_2";
                        this.tab020701_3 = "field_"+gap5+"_3";
                        this.tab020701_4 = "field_"+gap5+"_4";
                        this.tab020701_5 = "field_"+gap5+"_5";
                        this.tab020701_6 = "field_"+gap5+"_6";
                    }
                }
            },
            getToatl(field_2,field_3,field_4,field_5,field_6){
                if(this.tab020701DataList[field_2]!=null || this.tab020701DataList[field_3]!=null || this.tab020701DataList[field_4]!=null || this.tab020701DataList[field_5]!=null
                    || this.tab020701DataList[field_2]!='' || this.tab020701DataList[field_3]!='' || this.tab020701DataList[field_4]!='' || this.tab020701DataList[field_5]!=''){
                    this.tab020701DataList[field_6] = (Math.round((this.tab020701DataList[field_2]*this.tab020701DataList[field_3]*this.tab020701DataList[field_4]
                        *this.tab020701DataList[field_5]))*0.0001*0.85).toFixed(2);
                }
            },

            //获取表7-1 - 年度监测县(市、区)中稻生育进度调查表（直播稻） 的当前进程
            gettab020701CurrentStep(people){
                axios({
                    url:'/getAllDataTwo0701',
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
							this.tab020701DataList = [];
							this.totalData =[];
							this.tab020701DataList = res.data[0];
							//console.log(this.tab020701DataList);
							this.tab020701CurrentStep=0;
							for(var j = 0 ; j < 4 ; ++j){
							    var index_0701 = 0;
							    if (j < 2){
							        index_0701 = j;
							    } else {
							        index_0701 = j + 1;
							    }
							    var start = 3 + index_0701;
							    var start0 = 2 + index_0701;
							    var str = "field_"+start0;
							    var str1 = "field_"+start+"_1";
							    var str2 = "field_"+start+"_2";
							    var str3 = "field_"+start+"_3";
							    var str4 = "field_"+start+"_4";
							    var str5 = "field_"+start+"_5";
							    var str6 = "field_"+start+"_6";
							    if(this.tab020701DataList[str1] !=null && this.tab020701DataList[str1] !=''){
							        this.tab020701CurrentStep += 1;
							        this.latestData = this.tab020701DataList[str];
							        var currenrData = {};
							        currenrData.str = str;
							        currenrData.str1 = str1;
							        currenrData.str2 = str2;
							        currenrData.str3 = str3;
							        currenrData.str4 = str4;
							        currenrData.str5 = str5;
							        currenrData.str6 = str6;
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
							        this.totalData.push(nextData);
							        break;
							    }
							}
							//console.log(this.totalData);
							//console.log(this.tab020701CurrentStep);
							
							if(this.tab020701CurrentStep > this.currentStep){
							    this.currentStep = this.tab020701CurrentStep;
							}
							
							this.maxStep = this.currentStep;
							for(var i = 1 ; i <= this.tab020701CurrentStep ; ++i){
							    var item = ""+i+"";
							    this.activeNames.push(item);
							}
						}
                        
                    }
                ).catch(err=>{
                        return this.$message.error("获取数据失败！");
                    }
                );
            },
        },
        created(){
            this.people = localStorage.getItem('ms_username');
            this.gettab020701CurrentStep(this.people);

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