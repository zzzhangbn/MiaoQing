<template>
    <div style="margin-left: 1%;">
        <div style="width: 100%;height: 200px;margin-top: 25px;">
            <el-steps  :active="currentStep" finish-status="success">
                <el-step
                        v-for="(item,index) of stepTitle"
                        :key="index"
                        :title="item"
                        onmouseover="this.style.cursor='pointer'"
                        @click.native="handleStep(index)"
                />
            </el-steps>
        </div>
        <div style="width: 90%;border: #C4C4C4 1.3px solid;margin-top: 10px;">
            <div style="background-color: white;width: 370px;height: 30px;margin-top: -1px;border-width: 0 1px 1px 1px;border-style: solid;border-color: #C4C4C4;;margin-left: 4%;text-align: center;line-height: 20px;font-family: YouYuan;color: #006666;font-size: 25px;">
                <template v-if="currentStep >= 6">
                    <span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{stepTitle[currentStep-1]}}</span>
                </template>
                <template v-else="currentStep < 6">
                    <span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{stepTitle[currentStep]}}</span>
                </template>
            </div>
            <div style="width: 170px;height: 1.3px;background-color: #C1FFC1;margin-top: -0px;margin-left: 4%;"></div>
            <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;">
                <i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">
                年度监测县(市、区)水稻单季稻测产表</span></div>
            <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
                <template v-if="tab0704DataList.length > 0">
                    <template v-i></template>
                    <el-form :inline="true" ref="form"  label-width="180px">
                        <template v-if="currentStep == 0">
                            <el-form-item label="总面积(万亩)" >
                                <el-input style="width: 220px;"
                                          v-model="tab0704DataList[0][tab0704_1]"
                                          :disabled="currentStep < tab0704CurrentStep || currentStep == 8"
                                >
                                </el-input>
                            </el-form-item>
                        </template>
                        <template v-if="currentStep != 0">
                            <el-form-item label="面积(万亩)" >
                                <el-input style="width: 220px;"
                                          v-model="tab0704DataList[0][tab0704_1]"
                                          :disabled="currentStep < tab0704CurrentStep || currentStep == 8"
                                >
                                </el-input>
                            </el-form-item>
                        </template>
                        <el-form-item label="亩有效穗(万)" >
                            <el-input style="width: 220px;"
                                      v-model="tab0704DataList[0][tab0704_2]"
                                      :disabled="currentStep < tab0704CurrentStep || currentStep == 8"
                                      @blur="getToatl(tab0704_2,tab0704_3,tab0704_4,tab0704_5,tab0704_6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="穗总粒数(个)" >
                            <el-input style="width: 220px;"
                                      v-model="tab0704DataList[0][tab0704_3]"
                                      :disabled="currentStep < tab0704CurrentStep || currentStep == 8"
                                      @blur="getToatl(tab0704_2,tab0704_3,tab0704_4,tab0704_5,tab0704_6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="结实率(％)" >
                            <el-input style="width: 220px;"
                                      v-model="tab0704DataList[0][tab0704_4]"
                                      :disabled="currentStep < tab0704CurrentStep || currentStep == 8"
                                      @blur="getToatl(tab0704_2,tab0704_3,tab0704_4,tab0704_5,tab0704_6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="千粒重(g)" >
                            <el-input style="width: 220px;"
                                      v-model="tab0704DataList[0][tab0704_5]"
                                      :disabled="currentStep < tab0704CurrentStep || currentStep == 8"
                                      @blur="getToatl(tab0704_2,tab0704_3,tab0704_4,tab0704_5,tab0704_6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="八五折理论产量(kg/亩)" >
                            <el-input style="width: 220px;"
                                      v-model="tab0704DataList[0][tab0704_6]"
                                      :disabled="true"
                                      placeholder="自动计算"
                            >
                            </el-input>
                        </el-form-item>
                    </el-form>
                </template>
            </el-card>
        </div>
        <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
            <template v-if="currentStep >= tab0704CurrentStep">
                <el-button  @click="getFormList($event)">一键上报</el-button>
            </template>
            <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
                <el-button >返回</el-button>
            </router-link>
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
                tab0704CurrentStep:0,
                tab0704DataList:[],
                tab0704_1:'',
                tab0704_2:'',
                tab0704_3:'',
                tab0704_4:'',
                tab0704_5:'',
                tab0704_6:'',
                people:'',
                latestData:'',
                pickerOptions: {
                    disabledDate: (time) => {
                        return time.getTime() < new Date(this.latestData).getTime();
                    }
                },
                // 步骤标题
                stepTitle: [ '全县面上平均水平', '人工移栽', '机械插秧','盘育抛秧', '无盘旱育抛秧','撒直播','机条播'],
            };
        },
        methods: {
            test(){
                //console.log(this.tab0704DataList);
            },
            //上报
            getFormList(e){
                if (this.tab0704DataList[0][this.tab0704_1] == '' || this.tab0704DataList[0][this.tab0704_2] == ''|| this.tab0704DataList[0][this.tab0704_3] == ''
                    || this.tab0704DataList[0][this.tab0704_4] == ''|| this.tab0704DataList[0][this.tab0704_5] == ''
                    || this.tab0704DataList[0][this.tab0704_1] == null || this.tab0704DataList[0][this.tab0704_2] == null|| this.tab0704DataList[0][this.tab0704_3] == null
                    || this.tab0704DataList[0][this.tab0704_4] == null|| this.tab0704DataList[0][this.tab0704_5] == null) {
                    this.$alert('数据不能为空!', '错误提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                            /*this.$message({
                                type: 'info',
                                message: `action: ${ action }`
                            });*/
                        }
                    });
                }else {
                    this.$confirm("是否上报？").then(_ => {
                        var date = this.getNowTime();
                        for (var i = 0; i < this.tab0704DataList.length; ++i) {
                            this.tab0704DataList[i].modification_time = date;
                        }
                        var list = this.tab0704DataList;
                        //console.log(list);
                        var str = '';
                        for (var i = 0; i < list.length; ++i) {
                            this.tab0704DataList[i].in_use = 1;
                            str += JSON.stringify(list[i]);
                        }
                        this.saveInputData(str);
                    }).catch(_ => {
                        return this.$message.info("您取消了操作！");
                    });
                }
            },
            //生成保存函数
            saveInputData(list){
                axios({
                    url:'/getInputDataTableTwo0704',
                    method:'post',
                    params:{
                        data:list
                    }
                }).then(res=>{
                    this.$message.success("操作成功！");
                    this.gettab0704CurrentStep(people);
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
                    if(this.currentStep <= this.tab0704CurrentStep){
                        var gap5 = 4 + index;
                        this.tab0704_1 = "field_"+gap5+"_1";
                        this.tab0704_2 = "field_"+gap5+"_2";
                        this.tab0704_3 = "field_"+gap5+"_3";
                        this.tab0704_4 = "field_"+gap5+"_4";
                        this.tab0704_5 = "field_"+gap5+"_5";
                        this.tab0704_6 = "field_"+gap5+"_6";
                    }
                }
            },
            getToatl(field_2,field_3,field_4,field_5,field_6){
                if(this.tab0704DataList[0][field_2]!=null || this.tab0704DataList[0][field_3]!=null || this.tab0704DataList[0][field_4]!=null || this.tab0704DataList[0][field_5]!=null
                    || this.tab0704DataList[0][field_2]!='' || this.tab0704DataList[0][field_3]!='' || this.tab0704DataList[0][field_4]!='' || this.tab0704DataList[0][field_5]!=''){

                    this.tab0704DataList[0][field_6] = (Math.round((this.tab0704DataList[0][field_2]*this.tab0704DataList[0][field_3]*this.tab0704DataList[0][field_4]
                        *this.tab0704DataList[0][field_5]))*0.0001*0.85).toFixed(2);
                }
            },

            //获取表7-1 - 年度监测县(市、区)中稻生育进度调查表（直播稻） 的当前进程
            gettab0704CurrentStep(people){
                axios({
                    url:'/getAllData0704',
                    method:'get',
                    params:{
                        people:people,
                        column_id: "",
                        beginYear:0,
                        endYear:0
                    }
                }).then(
                    res=>{
                        this.tab0704DataList = res.data;
                        this.tab0704CurrentStep = 0;
                        //console.log(this.tab0704DataList);
                        if(this.tab0704DataList.length > 0){
                            for(var j = 0 ; j < 7 ; ++j){
                                var start = 4+j;
                                var str = "field_"+start+"_1";
                                if(this.tab0704DataList[0][str] !=null && this.tab0704DataList[0][str] !=''){
                                    this.tab0704CurrentStep += 1;
                                    this.latestData = this.tab0704DataList[0][str];
                                }else{
                                    break;
                                }
                            }
                            //console.log(this.tab0704CurrentStep);
                        }
                        var gap = 4 + this.tab0704CurrentStep;
                        this.tab0704_1 = "field_"+gap+"_1";
                        this.tab0704_2 = "field_"+gap+"_2";
                        this.tab0704_3 = "field_"+gap+"_3";
                        this.tab0704_4 = "field_"+gap+"_4";
                        this.tab0704_5 = "field_"+gap+"_5";
                        this.tab0704_6 = "field_"+gap+"_6";

                        if(this.tab0704CurrentStep > this.currentStep){
                            this.currentStep = this.tab0704CurrentStep;
                        }
                        if(this.currentStep == 6){
                            gap = gap -1;
                            this.tab0704_1 = "field_"+gap+"_1";
                            this.tab0704_2 = "field_"+gap+"_2";
                            this.tab0704_3 = "field_"+gap+"_3";
                            this.tab0704_4 = "field_"+gap+"_4";
                            this.tab0704_5 = "field_"+gap+"_5";
                            this.tab0704_6 = "field_"+gap+"_6";
                        }

                        this.maxStep = this.currentStep;
                        //console.log(this.currentStep);
                        //console.log(this.maxStep);
                        // //console.log(this.tab0403_1+':'+this.tab0403_2+":"+this.tab0403_3+":");

                    }
                ).catch(err=>{
                        return this.$message.error("获取数据失败！");
                    }
                );
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            this.gettab0704CurrentStep(people);

        },
    };
</script>
<style>
    input:disabled,textarea:disabled{
        opacity: 1;
        -webkit-text-fill-color: black;
    }
</style>