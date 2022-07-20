<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">年度水稻监测点茎蘖数叶面积构成表</span>
            
        </div>
        <el-collapse v-model="activeNames">
            <template v-for="(item,index) in reverseTableList">
                <el-collapse-item  :name="tableList.length-index-1" :title=stepTitle[tableList.length-index-1]>
                    <template v-if="tableList.length-index-1 == 0">
                        <el-form :inline="true"  label-width="180px">
                            <el-form-item label="测定日期" >
                                <el-date-picker  v-model="tab0403DataList[item.field1]" :picker-options="pickerOptions" align="right" type="date"
                                :disabled="copyData[item.field1] != null && copyData[item.field1] != ''"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="目标产量（kg/亩）" >
                                <el-input  v-model="tab0403DataList.field_8"  
                                :disabled="copyData.field_8 != null && copyData.field_8 != ''" style="width: 220px"
                                placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item label="基本苗（万/亩）" >
                                <el-input  v-model="tab0403DataList.field_9"  
                                :disabled="copyData.field_9 != null && copyData.field_9 != ''" style="width: 220px"
                                placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="株高（cm）">
                                <el-input :disabled="copyData[item.field2] != null && copyData[item.field2] != ''" v-model="tab0403DataList[item.field2]"  style="width: 220px"
                                 placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                 replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="总茎蘖数(万/亩)">
                                <el-input :disabled="copyData[item.field3] != null && copyData[item.field3] != ''" v-model="tab0403DataList[item.field3]" style="width: 220px" placeholder="请输入数字（小数点后两位）"  oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="平均单茎绿叶数">
                                <el-input :disabled="copyData[item.field4] != null && copyData[item.field4] != ''" style="width: 220px"v-model="tab0403DataList[item.field4]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="倒4与倒3叶色比">
                                <el-select :disabled="copyData[item.field5] != null && copyData[item.field5] != ''" style="width: 220px" v-model="tab0403DataList[item.field5]">
                                    <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.label"/>
                                </el-select>
                            </el-form-item>
                            <el-form-item  label="1叶分蘖个数">
                                <el-input :disabled="copyData[item.field6] != null && copyData[item.field6] != ''" style="width: 220px"v-model="tab0403DataList[item.field6]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="2叶分蘖个数">
                                <el-input :disabled="copyData[item.field7] != null && copyData[item.field7] != ''" style="width: 220px"v-model="tab0403DataList[item.field7]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item label="3叶及以上分蘖个数">
                                <el-input :disabled="copyData[item.field8] != null && copyData[item.field8] != ''" style="width: 220px"v-model="tab0403DataList[item.field8]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                        </el-form>
                    </template>
                    <template v-if="tableList.length-index-1 > 0">
                        <el-form :inline="true"  label-width="180px">
                            <el-form-item label="测定日期" >
                                <el-date-picker  v-model="tab0403DataList[item.field1]" :picker-options="pickerOptions" align="right" type="date"
                                :disabled="copyData[item.field1] != null && copyData[item.field1] != ''"></el-date-picker>
                            </el-form-item>
                            <el-form-item  label="株高（cm）">
                                <el-input :disabled="copyData[item.field2] != null && copyData[item.field2] != ''" v-model="tab0403DataList[item.field2]"  style="width: 220px"
                                 placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                 replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="总茎蘖数(万/亩)">
                                <el-input :disabled="copyData[item.field3] != null && copyData[item.field3] != ''" v-model="tab0403DataList[item.field3]" style="width: 220px" placeholder="请输入数字（小数点后两位）"  oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="平均单茎绿叶数">
                                <el-input :disabled="copyData[item.field4] != null && copyData[item.field4] != ''" style="width: 220px"v-model="tab0403DataList[item.field4]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="倒4与倒3叶色比">
                                <el-select :disabled="copyData[item.field5] != null && copyData[item.field5] != ''" style="width: 220px" v-model="tab0403DataList[item.field5]">
                                    <el-option
                                    v-for="item in options"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.label"/>
                                </el-select>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-collapse-item>       
            </template>
          </el-collapse>
          <template v-if="terminateStep == false">
            <el-button @click="getFormList($event)" style="margin-left: 88%;margin-top: 1%;">一键上报</el-button>
        </template>
        <router-link :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
          <el-button style="display: inline-block;margin-top: 1%;float: right;">返回</el-button>
        </router-link>
    </div>
  </template>
  <script>
      import axios from 'axios'
  export default{
      data() {
      return {
        currentStep:0,//当前进程数
        maxStep:0,//最大进程数
        tab0403CurrentStep:0,//表0403    
        tab0403DataList:{},//保存表0403 - 年度水稻监测点茎蘖数叶面积构成表 的数据
        tab0403_1:'',//表0403的字段绑定
        tab0403_2:'',
        tab0403_3:'',
        tab0403_4:'',
        tab0403_5:'',
        tab0403_6:'',
        tab0403_7:'',
        tab0403_8:'',
        tab0403_9:'field_8',//目标产量
        options:[
        {
          value: '1',
          label: '大于'
        }, 
        {
          value: '2',
          label: '等于'
        },
        {
            value:'3',
            label:'小于'
        }
      ],
        // 步骤标题
      stepTitle: [ '拔节期（第一节间定长后）', '孕穗期（幼穗分化1-2期）', '孕穗期（幼穗分化3-4期）'],
      people:'',
      point:'',
      latestData:'',
      tableList:[],
      copyData:[],
      terminateStep:false,
      activeNames:[],
      pickerOptions: {
              disabledDate: (time) => {
                  return time.getTime() < new Date(this.latestData).getTime();
              }
          },
      };
    },
    methods: {
        //上报
        getFormList(e){
                    this.$confirm("是否上报？").then(_=>{
                        var date = this.getNowTime();
                        for(var i = 0 ;i < this.tab0403DataList.length ; ++i){
                        this.tab0403DataList[i].modification_time = date;
                        }
                        var list = this.tab0403DataList;
                    
                        var str = '';
                        str += JSON.stringify(list) + "#";
                        
                        //判断是否全都填完
                        var start = 10+this.tab0403CurrentStep;
                        var str8 = "field_"+start+"_1";
                        var str1 = "field_"+start+"_2";
                        var str2 = "field_"+start+"_3";
                        var str3 = "field_"+start+"_4";
                        var str4 = "field_"+start+"_5";
                        var str5 = "field_"+start+"_6";
                        var str6 = "field_"+start+"_7";
                        var str7 = "field_"+start+"_8";
                        if(this.tab0403CurrentStep == 0){
                            if(this.tab0403DataList[str8]!=null &&  this.tab0403DataList[str8]!='' &&this.tab0403DataList[str1]!=null&&
                            this.tab0403DataList[str1] != '' && this.tab0403DataList[str2]!=null&& this.tab0403DataList[str2]!='' && this.tab0403DataList[str3]!=''&&
                            this.tab0403DataList[str3]!=null && this.tab0403DataList[str4]!=null && 
                            this.tab0403DataList[str4]!='' &&this.tab0403DataList[str5]!=null&&
                            this.tab0403DataList[str5] != '' && this.tab0403DataList[str6]!=null&& this.tab0403DataList[str6]!='' && this.tab0403DataList[str7]!=''&&
                            this.tab0403DataList[str7]!=null&&this.tab0403DataList.field_8 != null && this.tab0403DataList.field_8 != '' && this.tab0403DataList.field_9 != null && this.tab0403DataList.field_9!= ''){
                                this.saveInputData(str);
                            }else{
                                this.$message.warning("您还有数据没填！");
                            }
                        }
                        if(this.tab0403CurrentStep > 0){
                            if(this.tab0403DataList[str8]!=null &&  this.tab0403DataList[str8]!='' &&this.tab0403DataList[str1]!=null&&
                            this.tab0403DataList[str1] != '' && this.tab0403DataList[str2]!=null&& this.tab0403DataList[str2]!='' && this.tab0403DataList[str3]!=''&&
                            this.tab0403DataList[str3]!=null && this.tab0403DataList[str4]!=null && 
                            this.tab0403DataList[str4]!=''){
                                this.saveInputData(str);
                            
                            }else{
                                this.$message.warning("您还有数据没填！");
                            }
                        }
            
        }).catch(_=>{
          return this.$message.info("您取消了操作！");
        });
           
        },
        //生成保存函数
        saveInputData(list){
            axios({
            url:'/getSituationInputDataTableTwo0403',
            method:'post',
            params:{
                data:list
            }
            }).then(res=>{
            this.$message.success("操作成功！");
            this.getTab0403CurrentStep(this.people,this.point);
            }).catch(err=>{
                return this.$message.error("数据保存失败！");
            })
        },
        // 获取当前时间
        getNowTime: function () {
              let dateTime
              let yy = new Date().getFullYear()
              let mm = new Date().getMonth() + 1
              let dd = new Date().getDate()
              let hh = new Date().getHours()
              let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes()
                :
                new Date().getMinutes()
              let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds()
                :
                new Date().getSeconds()
                if(hh < 10){
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
                }else{
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                }
              return dateTime
            },

      //获取表4-3 - 年度水稻监测点茎蘖数叶面积构成表 的当前进程
      getTab0403CurrentStep(people,point){
        axios({
            url:'/getUserAllDataTwo0403',
            method:'post',
            params:{
                people:people,
                column_id:'',
                monitor_location:point,
                startYear:0,
                endYear:0
            }
        }).then(
            res=>{
                this.tableList = [];
                this.tab0403DataList = res.data[0];
                this.tab0403CurrentStep = 0;
                if(res.data.length > 0){
                    for(var j = 0 ; j < 3 ; ++j){
                        var start = 10+j;
                        var str = "field_"+start+"_1";
                        var str1 = "field_"+start+"_2";
                        var str2 = "field_"+start+"_3";
                        var str3 = "field_"+start+"_4";
                        var str4 = "field_"+start+"_5";
                        var str5 = "field_"+start+"_6";
                        var str6 = "field_"+start+"_7";
                        var str7 = "field_"+start+"_8";
                        if(this.tab0403DataList[str] !=null && this.tab0403DataList[str] !=''){
                            this.tab0403CurrentStep += 1;
                            this.latestData = this.tab0403DataList[str];
                            if(j == 0){
                                var obj = {};
                                obj.field1 = str;
                                obj.field2 = str1;
                                obj.field3 = str2;
                                obj.field4 = str3;
                                obj.field5 = str4;
                                obj.field6 = str5;
                                obj.field7 = str6;
                                obj.field8 = str7;
                                this.tableList.push(obj);
                            }else{
                                var obj = {};
                                obj.field1 = str;
                                obj.field2 = str1;
                                obj.field3 = str2;
                                obj.field4 = str3;
                                obj.field5 = str4;
                                this.tableList.push(obj);
                            }
                        }else{
                                var obj = {};
                                obj.field1 = str;
                                obj.field2 = str1;
                                obj.field3 = str2;
                                obj.field4 = str3;
                                obj.field5 = str4;
                                obj.field6 = str5;
                                obj.field7 = str6;
                                obj.field8 = str7;
                                this.tableList.push(obj);
                            break;
                        }
                    }  
                    this.activeNames.push(this.tab0403CurrentStep);
                    const json = JSON.stringify(res.data[0]);
                    this.copyData = JSON.parse(json);
                    if(this.tab0403DataList.field_12_1 != null && this.tab0403DataList.field_12_1 != ""){
                        this.terminateStep = true;
                    } 
                }
            }
        ).catch(err=>{
            return this.$message.error("获取监测点茎蘖数叶面积构成数据失败！");
        }
        );
      },
    },
    created(){
        this.people = localStorage.getItem('ms_username');
        this.point = this.$route.query.point;
        this.getTab0403CurrentStep(this.people,this.point);

    },
    computed: {
        // 反转数组tableList
        reverseTableList() {
          // 设置所有折叠面展开
          const open = [];
          for (var i = 0; i < this.tableList.length; i++) {
            open.push(i)
          }
          this.activeNames = open;

          return this.tableList.reverse();
        }
      },
  };
  </script>
  <style>
      .el-collapse-item__header{
          font-size: 20px;
          color: rgb(0, 102, 102);
      }
      input:disabled,textarea:disabled{
        opacity: 0.7;
        -webkit-text-fill-color: black;
        background-color: honeydew;
        cursor: pointer;

    }
  </style>