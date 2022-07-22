<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻监测点水分管理记录表
            </span>
        </div>
        <el-collapse v-model="activeNames" @change="handleChange($event)">
            <template v-for="(item,index) in totalData" >
                <el-collapse-item  :name="index" :title=stepTitle[index] >
                    <el-form :inline="true"  label-width="180px">
                        <template v-if="index == 0">
                            <el-form-item label="总面积(万亩)" >
                                <el-input
                                        style="width: 220px;"
                                        placeholder="请输入数字且不超过8位"
                                        maxlength="8"
                                        oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                        replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                        v-model="tab0701DataList[item.str]"
                                        :disabled="index < tab0701CurrentStep "
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
                                    v-model="tab0701DataList[item.str1]"
                                    :disabled="index < tab0701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="亩有效穗(万)" >
                            <el-input
                                    style="width: 220px;"
                                    placeholder="请输入数字且不超过6位"
                                    maxlength="6"
                                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                                    v-model="tab0701DataList[item.str2]"
                                    :disabled="index < tab0701CurrentStep "
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
                                    v-model="tab0701DataList[item.str3]"
                                    :disabled="index < tab0701CurrentStep "
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
                                    v-model="tab0701DataList[item.str4]"
                                    :disabled="index < tab0701CurrentStep "
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
                                    v-model="tab0701DataList[item.str5]"
                                    :disabled="index < tab0701CurrentStep "
                                    @blur="getToatl(item.str2,item.str3,item.str4,item.str5,item.str6)">
                            </el-input>
                        </el-form-item>
                        <el-form-item label="理论产量(kg/亩)" >
                            <el-input style="width: 220px;"
                                      v-model="tab0701DataList[item.str6]"
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
                <template v-if="currentStep >= tab0701CurrentStep && this.tab0701CurrentStep != 6">
                    <el-button  @click="getFormList($event)">一键上报</el-button>
                </template>
                <router-link  :to="{name:'/tablemain',params: {activeNum: 1}}">
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
              tab0701CurrentStep:0,//表0701
              tab0701DataList:[],//保存表0701 - 年度水稻监测点茎蘖数叶面积构成表 的数据
              tab0701_0:'',
              tab0701_1:'',
              tab0701_2:'',
              tab0701_3:'',
              tab0701_4:'',
              tab0701_5:'',
              tab0701_6:'',
              people:'',
              // 步骤标题
              stepTitle: [ '7月31日前抽穗扬花的中稻', '8月1-10日抽穗扬花的中稻', '8月11-15日抽穗扬花的中稻',
              '在8月16日-20日抽穗扬花的中稻','在8月21-31日抽穗扬花的中稻','在9月上旬抽穗扬花的中稻'],
          };

    },
    methods: {
        test(){
            console.log(this.tab0701DataList);
        },
        //上报
        getFormList(e){
            // if (this.tab0701DataList[0][this.tab0701_1] == '' || this.tab0701DataList[0][this.tab0701_2] == ''|| this.tab0701DataList[0][this.tab0701_3] == ''
            //     || this.tab0701DataList[0][this.tab0701_4] == ''|| this.tab0701DataList[0][this.tab0701_5] == ''
            //     || this.tab0701DataList[0][this.tab0701_1] == null || this.tab0701DataList[0][this.tab0701_2] == null|| this.tab0701DataList[0][this.tab0701_3] == null
            //     || this.tab0701DataList[0][this.tab0701_4] == null|| this.tab0701DataList[0][this.tab0701_5] == null) {
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

                    this.tab0701DataList.in_use = 1;
                    this.tab0701DataList.modification_time = date;

                    var list = this.tab0701DataList;
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
                url:'/getInputDataTable0701',
                method:'post',
                params:{
                    data:list
                }
            }).then(res=>{
                this.$message.success("操作成功！");
                this.getTab0701CurrentStep(this.people);
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
                if(this.currentStep <= this.tab0701CurrentStep){
                    var gap5 = 4 + index;//表4-2 的进程绑定
                    var gap4 = 3 + index;
                    this.tab0701_0 = "field_"+gap4;
                    this.tab0701_1 = "field_"+gap5+"_1";
                    this.tab0701_2 = "field_"+gap5+"_2";
                    this.tab0701_3 = "field_"+gap5+"_3";
                    this.tab0701_4 = "field_"+gap5+"_4";
                    this.tab0701_5 = "field_"+gap5+"_5";
                    this.tab0701_6 = "field_"+gap5+"_6";
                }
            }
        },
        getToatl(field_2,field_3,field_4,field_5,field_6){
            if(this.tab0701DataList[field_2]!=null || this.tab0701DataList[field_3]!=null || this.tab0701DataList[field_4]!=null || this.tab0701DataList[field_5]!=null
            || this.tab0701DataList[field_2]!='' || this.tab0701DataList[field_3]!='' || this.tab0701DataList[field_4]!='' || this.tab0701DataList[field_5]!=''){
            this.tab0701DataList[field_6] = (Math.round((this.tab0701DataList[field_2]*this.tab0701DataList[field_3]*this.tab0701DataList[field_4]
                *this.tab0701DataList[field_5]))*0.000001*0.85).toFixed(2);
            }
        },

      //获取表7-1 - 年度监测县(市、区)中稻生育进度调查表（直播稻） 的当前进程
      getTab0701CurrentStep(people){
        axios({
            url:'/getAllData0701',
            method:'get',
            params:{
                people:people,
                column_id: "",
                beginYear:0,
                endYear:0
            }
        }).then(
            res=>{
                this.tab0701DataList = [];
                this.totalData =[];
                this.tab0701DataList = res.data[0];
                console.log(this.tab0701DataList);
                this.tab0701CurrentStep=0;
                for(var j = 0 ; j < 6 ; ++j){
                    var start = 4+j;
                    var start0 = 3+j;
                    var str = "field_"+start0;
                    var str1 = "field_"+start+"_1";
                    var str2 = "field_"+start+"_2";
                    var str3 = "field_"+start+"_3";
                    var str4 = "field_"+start+"_4";
                    var str5 = "field_"+start+"_5";
                    var str6 = "field_"+start+"_6";
                    if(this.tab0701DataList[str1] !=null && this.tab0701DataList[str1] !=''){
                        this.tab0701CurrentStep += 1;
                        this.latestData = this.tab0701DataList[str];
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
                console.log(this.totalData);
                if(this.tab0701CurrentStep > this.currentStep){
                    this.currentStep = this.tab0701CurrentStep;
                }

                this.maxStep = this.currentStep;
                for(var i = 1 ; i <= this.tab0701CurrentStep ; ++i){
                    var item = ""+i+"";
                    this.activeNames.push(item);
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
        this.getTab0701CurrentStep(this.people);

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