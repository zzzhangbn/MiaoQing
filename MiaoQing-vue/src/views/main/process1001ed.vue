<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">
                年度水稻监测点灌浆速率调查表
            </span>
        </div>
        <el-collapse v-model="activeNames" @change="handleChange($event)">
            <template v-for="(item,index) in totalData" >
                <el-collapse-item  :name="index" :title=stepTitle[index] >
                    <el-form :inline="true"  label-width="180px">
                        <el-form-item label="日期" >
                            <el-date-picker
                                    v-model="tab1001DataList[item.date]"
                                    align="right"
                                    type="date"
                                    :picker-options="pickerOptions"
                                    value-format=“yyyy-MM-dd”
                                    :disabled="index < tab1001CurrentStep "
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
                                      v-model="tab1001DataList[item.weight]"
                                      :disabled="index < tab1001CurrentStep "
                                      @blur="getToatl(item.date,item.weight,item.v)">
                            </el-input>
                        </el-form-item>
                        <template v-if="index != 0">
                            <el-form-item label="灌浆速率（克/千粒•天）" >
                                <el-input style="width: 220px;"
                                          placeholder="自动生成"
                                          v-model="tab1001DataList[item.v]"
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
                <template v-if="currentStep >= tab1001CurrentStep && this.tab1001CurrentStep != 6">
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
          currentStep:0,//当前进程数
          maxStep:0,//最大进程数
          tab1001CurrentStep:0,
          tab1001DataList:[],
          tab1001_1:'',
          tab1001_2:'',
          tab1001_3:'',
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
            console.log(this.tab1001DataList);
        },
        //上报
        getFormList(e){
            this.$confirm("是否上报？").then(_ => {
                var date = this.getNowTime();
                this.tab1001DataList.in_use = 1;
                this.tab1001DataList.modification_time = date;
                if (this.tab1001DataList.field_6_1 != null) {
                    this.tab1001DataList.field_6_1 = this.tab1001DataList.field_6_1.toString().replace('“', '').replace('”', '');
                }
                if (this.tab1001DataList.field_7_1 != null) {
                    this.tab1001DataList.field_7_1 = this.tab1001DataList.field_7_1.toString().replace('“', '').replace('”', '');
                }
                if (this.tab1001DataList.field_8_1 != null) {
                    this.tab1001DataList.field_8_1 = this.tab1001DataList.field_8_1.toString().replace('“', '').replace('”', '');
                }
                if (this.tab1001DataList.field_9_1 != null) {
                    this.tab1001DataList.field_9_1 = this.tab1001DataList.field_9_1.toString().replace('“', '').replace('”', '');
                }
                if (this.tab1001DataList.field_10_1 != null) {
                    this.tab1001DataList.field_10_1 = this.tab1001DataList.field_10_1.toString().replace('“', '').replace('”', '');
                }
                if (this.tab1001DataList.field_11_1 != null) {
                    this.tab1001DataList.field_11_1 = this.tab1001DataList.field_11_1.toString().replace('“', '').replace('”', '');
                }

                var list = this.tab1001DataList;
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
                url:'/getInputDataTable1001',
                method:'post',
                params:{
                    data:list
                }
            }).then(res=>{
                this.$message.success("操作成功！");
                this.gettab1001CurrentStep(this.people,this.point);
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
                if(this.currentStep <= this.tab1001CurrentStep){
                    var gap5 = 6 + index;
                    this.tab1001_1 = "field_"+gap5+"_1";
                    this.tab1001_2 = "field_"+gap5+"_2";
                    this.tab1001_3 = "field_"+gap5+"_3";
                }
            }
        },
    
        gettab1001CurrentStep(people,point){
            axios({
                url:'/getAllData1001',
                method:'get',
                params:{
                    people:people,
                    point:point,
                    column_id: ""
                }
            }).then(
            res=>{
                this.tab1001DataList = [];
                this.totalData =[];
                this.tab1001DataList = res.data[0];
                console.log(this.tab1001DataList);
                this.tab1001CurrentStep=0;
                for(var j = 0 ; j < 6 ; ++j){
                    var start = 6+j;
                    var str = "field_"+start+"_1";
                    var str1 = "field_"+start+"_2";
                    var str2 = "field_"+start+"_3";
                    if(this.tab1001DataList[str] !=null && this.tab1001DataList[str] !=''){
                        this.tab1001CurrentStep += 1;
                        this.latestData = this.tab1001DataList[str];
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
                console.log(this.totalData);
                if(this.tab1001CurrentStep > this.currentStep){
                    this.currentStep = this.tab1001CurrentStep;
                }

                this.maxStep = this.currentStep;

                for(var i = 1 ; i <= this.tab1001CurrentStep ; ++i){
                    var item = ""+i+"";
                    this.activeNames.push(item);
                }
                console.log(this.activeNames);

            }
            ).catch(err=>{
                return this.$message.error("获取数据失败！");
            }
            );
        },
        getToatl(tab1001_1,tab1001_2,tab1001_3) {
            console.log(tab1001_1);
            console.log(tab1001_2);
            console.log(tab1001_3);
            var start =this.currentStep+5;
            var data = "field_"+start+"_1";
            var weight = "field_"+start+"_2";
            if(this.tab1001DataList[tab1001_2] != null && this.tab1001DataList[weight] != null && this.tab1001DataList[data]
            && this.tab1001DataList[tab1001_1] != null){
                var startDate = Date.parse(this.tab1001DataList[data]);
                var endDate = Date.parse(this.tab1001DataList[tab1001_1].toString().replace('“', '').replace('”', ''));
                var days = (endDate - startDate) / (1 * 24 * 60 * 60 * 1000);
                this.tab1001DataList[tab1001_3]=((this.tab1001DataList[tab1001_2]-this.tab1001DataList[weight])/days).toFixed(2);
            }
        }
    },
    created(){
        this.people = localStorage.getItem('ms_username');
        this.point = this.$route.query.point;
        this.gettab1001CurrentStep(this.people,this.point);
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