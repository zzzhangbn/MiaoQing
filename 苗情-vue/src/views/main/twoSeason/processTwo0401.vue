<template>
    <div style="width: 98%;height: 90%;margin-left: 1.5%;">
      <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;margin-bottom: 1%;">年度监测县(市、区)双季稻生育进度调查表（移栽稻）</span>
        <el-collapse v-model="activeNames" @change="handleChange($event)">
            <template v-for="(item,index) in reverseTableList">
                <el-collapse-item  :name="tableList.length-index-1" :title=stepTitle[tableList.length-index-1]>
                    <template v-if="tableList.length-index-1 == 0">
                        <el-form :inline="true"  label-width="180px">
                            <el-form-item label="日期" >
                                <el-date-picker  v-model="tab4DataList[item.field1]" :picker-options="pickerOptions" align="right" type="date"
                                :disabled="copyData[item.field1] != null && copyData[item.field1] != ''"></el-date-picker>
                            </el-form-item>
                            <el-form-item  label="叶龄（叶）">
                                <el-input :disabled="copyData[item.field2] != null && copyData[item.field2] != ''" v-model="tab4DataList[item.field2]"  style="width: 220px" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="基本苗（万亩）">
                                <el-input :disabled="copyData[item.field3] != null && copyData[item.field3] != ''" v-model="tab4DataList[item.field3]" style="width: 220px" placeholder="请输入数字（小数点后两位）"   oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="亩穴数（万）">
                                <el-input :disabled="copyData[item.field4] != null && copyData[item.field4] != ''" style="width: 220px"v-model="tab4DataList[item.field4]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                        </el-form>
                    </template>
                    <template v-if="tableList.length-index-1 > 0 && tableList.length-index-1 < 8">
                        <el-form :inline="true"  label-width="180px">
                            <el-form-item label="日期" >
                                <el-date-picker  v-model="tab4DataList[item.field1]" :picker-options="pickerOptions" align="right" type="date"
                                :disabled="copyData[item.field1] != null && copyData[item.field1] != ''"></el-date-picker>
                            </el-form-item>
                            <el-form-item  label="生育进程">
                                <el-autocomplete :disabled="copyData[item.field2] != null && copyData[item.field2] != ''" v-model="tab4DataList[item.field2]"  style="width: 220px"
                                :fetch-suggestions="querySearchProcess"></el-autocomplete>
                            </el-form-item>
                            <el-form-item  label="叶龄（叶）">
                                <el-input :disabled="copyData[item.field3] != null && copyData[item.field3] != ''" v-model="tab4DataList[item.field3]" style="width: 220px" placeholder="请输入数字（小数点后两位）"   oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item  label="茎蘖数(万/亩)">
                                <el-input :disabled="copyData[item.field4] != null && copyData[item.field4] != ''" style="width: 220px"v-model="tab4DataList[item.field4]" placeholder="请输入数字（小数点后两位）" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-collapse-item>       
            </template>
          </el-collapse>
          <div style="width: 98%;height: 50px;">
            <template v-if="terminateStep == false">
                <el-button @click="getFormList($event)" style="margin-left: 88%;margin-top: 1%;">一键上报</el-button>
            </template>
            <router-link :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
              <el-button style="display: inline-block;margin-top: 1%;float: right;">返回</el-button>
            </router-link>
        </div>
    </div>
</template>
<script>
import axios from 'axios'
    export default {
      data() {
        return {
          activeNames: [],
          terminateStep:false,
          tab4CurrentStep:0,
          people:'',
          point:'',
          tab4DataList:{},
          latestData:'',
          pickerOptions: {
              disabledDate: (time) => {
                  return time.getTime() < new Date(this.latestData).getTime();
              }
          },
          stepTitle: ["移栽期","进程1（移栽后7天）","进程2（移栽后12天）","进程3（移栽后17天）","进程4（移栽后22天）","进程5（移栽后27天）","进程6（移栽后37天）","进程7（移栽后47天）"],
          copyData:[],
          tableList:[],
          processName: [{ "value": "播种期" },{ "value": "出苗期" },{ "value": "移栽期" },
              { "value": "够苗期" },{ "value": "有效分蘗临界叶龄期" },{ "value": "拔节期" },
              { "value": "孕穗期" },{ "value": "抽穗期" },{ "value": "齐穗期" },
              { "value": "成熟期" },{ "value": "全生育期" }],
        };
      },
      methods: {
        handleChange(e) {
       
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
    getFormList(e) {
      this.$confirm("是否上报？").then(_=>{
        var date = this.getNowTime();
        for (var i = 0; i < this.tab4DataList.length; ++i) {
          this.tab4DataList[i].modification_time = date;
        }
        var list = this.tab4DataList;
        var str = "";
        str += JSON.stringify(list) + "#";
        var index = 8+this.tab4CurrentStep;
        var field1 = "field_"+index+"_1";
        var field2 = "field_"+index+"_2";
        var field3 = "field_"+index+"_3";
        var field4 = "field_"+index+"_4";
        if(this.tab4DataList[field1] == null || this.tab4DataList[field1] == '' || this.tab4DataList[field2] == null || this.tab4DataList[field2] == '' || this.tab4DataList[field3] == null || this.tab4DataList[field3] == ''
        || this.tab4DataList[field4] == null || this.tab4DataList[field4] == ''){
            return this.$message.warning("您还有数据未填！");
        } 
        this.saveInputData(str);
        }).catch(_=>{
          return this.$message.info("您取消了操作！");
        });
    },
    //判断是否该填的框都填过了
    checkUserInput(){
       
    },
      //生成保存函数
      saveInputData(list) {
      axios({
        url: "/getSituationInputDataTableTwo0401",
        method: "post",
        params: {
          data: list,
        },
      })
        .then((res) => {
          this.$message.success("操作成功！");
          this.getTab4CurrentStep(this.people, this.point);
        })
        .catch((err) => {
          return this.$message.error("数据保存失败！");
        });
    },      
    // 获取当前时间
    getNowTime: function () {
      let dateTime;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth() + 1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf =
        new Date().getMinutes() < 10
          ? "0" + new Date().getMinutes()
          : new Date().getMinutes();
      let ss =
        new Date().getSeconds() < 10
          ? "0" + new Date().getSeconds()
          : new Date().getSeconds();
      if (hh < 10) {
        dateTime =
          yy + "-" + mm + "-" + dd + " " + "0" + hh + ":" + mf + ":" + ss;
      } else {
        dateTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
      }
      return dateTime;
    },
    //获取表4 - 年度监测县(市、区)中稻生育进度调查表（移栽稻） 的当前进程
    getTab4CurrentStep(people, point) {
      axios({
        url: "/getUserAllDataTwo0401",
        method: "post",
        params: {
          people: people,
          column_id: "",
          monitor_location: point,
          startYear:0,
          endYear:0
        },
      }).then((res) => {
          this.tab4DataList = {};
          this.tab4CurrentStep = 0;
          this.tableList = [];
          this.tab4DataList = res.data[0];
          if(res.data.length > 0){
             for (var j = 0; j < 8; ++j) {
              var start = 8 + j;
              var str = "field_" + start + "_1";
              var str1 = "field_"+start+"_2";
              var str2 = "field_" + start + "_3";
              var str3 = "field_"+start+"_4";
              if (this.tab4DataList[str] != null &&this.tab4DataList[str] != "") { 
                    this.tab4CurrentStep += 1;
                    this.latestData = this.tab4DataList[str];
                    //console.log(this.latestData);
                    var obj = {};
                    obj.field1 = str;
                    obj.field2 = str1;
                    obj.field3 = str2;
                    obj.field4 = str3;
                    this.tableList.push(obj);
              } else {
                    var obj = {};
                    obj.field1 = str;
                    obj.field2 = str1;
                    obj.field3 = str2;
                    obj.field4 = str3;
                    this.tableList.push(obj);
                break;
              }
            }
            this.activeNames.push(this.tab4CurrentStep);
            const json = JSON.stringify(res.data[0]);
            this.copyData = JSON.parse(json);
            if(this.tab4DataList.field_15_1 != null && this.tab4DataList.field_15_1 != ""){
                this.terminateStep = true;
            }           
          }
          
        })
        .catch((err) => {
           
          return this.$message.error("获取中稻进度失败！");
        });
    },
      },
      created() {
        this.people = localStorage.getItem("ms_username");
        this.point = this.$route.query.point;
        this.getTab4CurrentStep(this.people, this.point);
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
    }
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