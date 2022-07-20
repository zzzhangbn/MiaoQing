<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">年度监测县(市、区)水稻播栽进度调查表</span>
        </div>
        <el-collapse v-model="activeNames">
            <template v-for="(item,index) in reverseTableList">
                <el-collapse-item  :name="tableList.length-index-1" :title=stepTitle[tableList.length-index-1]>
                    <!-- <template v-if="index != 3"> -->
                        <el-form :inline="true"  label-width="180px">
                            <template v-if="tableList.length-index-1 == 0">
                                <el-form-item label="全县总面积(万亩)">
                                    <el-input v-model="tab1DataList.field_3" 
                                    :disabled="copyData.field_3 != null && copyData.field_3 !=''" style="width: 207px;" @blur="changeAllArea"></el-input>  
                                </el-form-item>
                                <el-form-item label="播种日期-育秧">
                                    <el-date-picker
                                        v-model="tab1DataList.field_4_1"
                                        align="right"
                                        :disabled="copyData.field_4_1 != null && copyData.field_4_1 !=''"
                                        type="date" style="width: 207px;"
                                        placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                                <el-form-item label="播种日期-直播">
                                    <el-date-picker
                                        v-model="tab1DataList.field_4_2"
                                        align="right" 
                                        :disabled="copyData.field_4_2 != null && copyData.field_4_2 !=''"
                                        type="date" style="width: 207px;"
                                        placeholder="选择日期">
                                    </el-date-picker>
                                </el-form-item>
                                <el-form-item label="秧田面积（万亩）">
                                    <el-input v-model="tab1DataList.field_5" style="width: 207px;"
                                    :disabled="copyData.field_5 != null && copyData.field_5 !=''"></el-input>
                                </el-form-item>
                                <el-form-item width="260" label="其中（秧田面积）集中育秧">
                                    <el-input v-model="tab1DataList.field_6_1" 
                                    :disabled="copyData.field_6_1 != null && copyData.field_6_1 !=''" style="width: 207px;"
                                    @blur.prevent="checkFirstFieldInput($event,'field_6_1','field_6_2','1')"></el-input>
                                </el-form-item>
                                <el-form-item width="260" label="其中（秧田面积）集中育秧/秧田面积">
                                        <el-input v-model="tab1DataList.field_6_2" disabled style="width: 207px;"></el-input>
                                </el-form-item>
                                <el-form-item label="折合大田面积（万亩）">
                                    <el-input v-model="tab1DataList.field_7_1" 
                                    :disabled="copyData.field_7_1 != null && copyData.field_7_1 !=''" style="width: 207px;"
                                    @blur.prevent="checkFirstFieldInput($event,'field_7_1','field_7_2','0')"></el-input>
                                </el-form-item>
                                <el-form-item label="折合大田面积（万亩）/总面积">
                                    <el-input v-model="tab1DataList.field_7_2" disabled style="width: 207px;"></el-input>
                                </el-form-item>
                                <el-form-item label="其中（折合大田面积）集中育秧">
                                    <el-input v-model="tab1DataList.field_8_1" 
                                    :disabled="copyData.field_8_1 != null && copyData.field_8_1 !=''" style="width: 207px;"
                                    @blur.prevent="checkFirstFieldInput($event,'field_8_1','field_8_2','0')"></el-input>
                                </el-form-item>
                                <el-form-item  label="其中（折合大田面积）集中育秧/总面积">
                                    <el-input v-model="tab1DataList.field_8_2" disabled style="width: 207px;"></el-input>
                                </el-form-item>
                            </template>
                        <template v-if="tableList.length-index-1 < 4 && tableList.length-index-1 > 0">
                            <el-form-item label="日期" >
                                <el-date-picker
                                    v-model="tab1DataList[item.field1]"
                                    align="right" 
                                    :picker-options="pickerOptions"
                                    :disabled="copyData[item.field1] != null && copyData[item.field1] !=''"
                                    type="date" style="width: 207px;"
                                    placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="育秧已播种面积（万亩）">
                            
                                    <el-input style="width: 207px;" v-model="tab1DataList[item.field21]" @blur.prevent="getField22(item.field21,item.field22)" 
                                    :disabled="copyData[item.field21] != null && copyData[item.field21] !=''"></el-input>
                                
                            </el-form-item>
                            <el-form-item label="育秧已播种面积（万亩）/秧田面积">
                                <el-input style="width: 207px;" v-model="tab1DataList[item.field22]" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="已栽插面积（万亩）">
                                
                                    <el-input style="width: 207px;" v-model="tab1DataList[item.field3]" @blur.prevent="getSumArea(item.field3,item.field4,item.field5,item.field6)" 
                                    :disabled="copyData[item.field3] != null && copyData[item.field3] !=''"></el-input>
                                
                            </el-form-item>
                            <el-form-item label="直播已播种面积（万亩）">
                                
                                    <el-input style="width: 207px;" v-model="tab1DataList[item.field4]" @blur.prevent="getSumArea(item.field3,item.field4,item.field5,item.field6)" 
                                    :disabled="copyData[item.field4] != null && copyData[item.field4] !=''"></el-input>
                                
                            </el-form-item>
                            <el-form-item label="移栽+直播">
                                <el-input style="width: 207px;" v-model="tab1DataList[item.field5]" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="（移栽+直播）/总面积">
                                <el-input style="width: 207px;" v-model="tab1DataList[item.field6]" disabled></el-input>
                            </el-form-item>
                        </template>
                        <template v-if="tableList.length-index-1 == 4">
                            <el-form-item label="日期" >
                                <el-date-picker
                                    v-model="tab1DataList.field_12_1"
                                    align="right" 
                                    :picker-options="pickerOptions"
                                    :disabled="copyData.field_12_1 != null && copyData.field_12_1 !=''"
                                    type="date" style="width: 207px;"
                                    placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="已栽插面积（万亩）">
                                <el-input style="width: 207px;" v-model="tab1DataList.field_12_2" @blur.prevent="getSumArea('field_12_2','field_12_3','field_12_4','field_12_5')" 
                                :disabled="copyData.field_12_2 != null && copyData.field_12_2 !=''"></el-input>
                            </el-form-item>
                            <el-form-item label="直播已播种面积（万亩）">
                                    <el-input style="width: 207px;" v-model="tab1DataList.field_12_3" @blur.prevent="getSumArea('field_12_2','field_12_3','field_12_4','field_12_5')" 
                                    :disabled="copyData.field_12_3 != null && copyData.field_12_3 !=''"></el-input>
                            </el-form-item>
                            <el-form-item label="移栽+直播">
                                <el-input style="width: 207px;" v-model="tab1DataList.field_12_4" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="（移栽+直播）/总面积">
                                <el-input style="width: 207px;" v-model="tab1DataList.field_12_5" disabled></el-input>
                            </el-form-item>
                        </template>    
                        </el-form>
                    <!-- </template> -->
                </el-collapse-item>       
            </template>
          </el-collapse>
          <template v-if="terminateStep == false">
            <el-button @click="getFormList($event)" style="margin-left: 88%;margin-top: 1%;">一键上报</el-button>
        </template>
        <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
          <el-button style="display: inline-block;margin-top: 1%;float: right;">返回</el-button>
        </router-link>
    </div>
  </template>
  <script>
      import axios from 'axios'
      import * as math from 'mathjs'
  export default{
    inject: ['reload'],
      data() {
      return {
        currentStep:0,//当前进程数
        maxStep:0,//最大进程数
        activeNames:[],
        tab1CurrentStep:0,//表1-  3-1当前进程,最大是4
        tab1DataList:{},//保存表1 - 年度监测县(市、区)水稻播栽进度调查表 的数据
        tab1_1:'',//表1的字段绑定
        tab1_2_1:'',
        tab1_2_2:'',
        tab1_2:'',
        tab1_3:'',
        tab1_4:'',
        tab1_5:'',
        tab1_6:'',
        listArray:[],
        people:'',
        point:'',
        tableList:[],
        copyData:{},
        terminateStep:false,
        latestData:'',
      pickerOptions: {
              disabledDate: (time) => {
                  return time.getTime() < new Date(this.latestData).getTime();
              }
          },
        // 步骤标题
      stepTitle: ['进度准备','播栽期进度1（5月20日）', '播栽期进度2（5月30日）', '播栽期进度3（6月10日）', '播栽期进度4（6月20日）'],
      };
    },
    methods: {
        //改变全县总面积
        changeAllArea(){
            this.tab1DataList.field_5 = '' 
            this.tab1DataList.field_6_1 = '' 
            this.tab1DataList.field_6_2 = ''
            this.tab1DataList.field_7_1 = '' 
            this.tab1DataList.field_7_2 = ''
            this.tab1DataList.field_8_1 = ''
            this.tab1DataList.field_8_2 = ''
        },
        //处理小数点
        add(value) {
            const precision = 14
            return Number(math.format(value, precision))
        },
        //检查input框的输入是否符合要求
        checkFirstFieldInput(e,field1,field2,flag){
            var strValue = e.target.value;
            if(field2 == 'sum'){
                this.tab1DataList.field_5 = '';
                this.tab1DataList.field_6_1 = '';
                this.tab1DataList.field_6_2 = '';
                this.tab1DataList.field_7_1 = '';
                this.tab1DataList.field_7_2 = '';
                this.tab1DataList.field_8_1 = '';
                this.tab1DataList.field_8_2 = '';
            }
          if(!isNaN(strValue)){ 
              if(this.tab1DataList[field1] != null && this.tab1DataList[field1] != ''){
                  if(flag == '0'){//计算总面积占比
                    this.tab1DataList[field2] =(Math.round((this.tab1DataList[field1]/this.tab1DataList.field_3) * 100*100)/100).toFixed(2) +'%';
                  }
                  if(flag == '1'){//计算秧田面积
                    this.tab1DataList[field2] =(Math.round((this.tab1DataList[field1]/this.tab1DataList.field_5) * 100*100)/100).toFixed(2)+'%';
                  }
              }
        }else{
          // e.target.parentNode.nextSibling.innerHTML = "X";
          e.target.value = "";
          alert("请输入数字！");
        }
        },
        //日期格式设置
        dateFormat:function(row,column){
            var date = row[column.property];
            if(date == undefined){return ''};
            return moment(date).format("YYYY-MM-DD");
            },
        //上报
        getFormList(e){
            this.$confirm('是否上报？').then(_ => {
　　        var date = this.getNowTime();
            for(var i = 0 ;i < this.tab1DataList.length ; ++i){
            this.tab1DataList[i].modification_time = date;
            }
            var list = this.tab1DataList;
            var Mstr = '';
            Mstr += JSON.stringify(list) + "#";
            var start = 8+this.tab1CurrentStep;
            var str = "field_"+start+"_1";
            var str1 = "field_"+start+"_2_1";
            var str2 = "field_"+start+"_2_2";
            var str3 = "field_"+start+"_3";
            var str4 = "field_"+start+"_4";
            var str5 = "field_"+start+"_5";
            var str6 = "field_"+start+"_6";
            var str7 = "field_"+start+"_2";
            //判断是否所有空都填过了
            if(this.tab1CurrentStep == 0){
                if(this.tab1DataList.field_3!='' && this.tab1DataList.field_3!=null && this.tab1DataList.field_4_1 != null && this.tab1DataList.field_4_1 != '' 
                && this.tab1DataList.field_4_2 != ''&& this.tab1DataList.field_4_2 != null && this.tab1DataList.field_5 != '' && this.tab1DataList.field_5 != null 
                && this.tab1DataList.field_6_1 != '' && this.tab1DataList.field_6_1 != null && this.tab1DataList.field_6_2 != '' && this.tab1DataList.field_6_2 != null&&this.tab1DataList.field_7_1 != ''
                &&this.tab1DataList.field_7_1 != null && this.tab1DataList.field_7_2 != ''&& this.tab1DataList.field_7_2 != null
                && this.tab1DataList.field_8_1 != ''&& this.tab1DataList.field_8_1 != null && this.tab1DataList.field_8_2 != ''&& this.tab1DataList.field_8_2 != null){
                    this.saveInputData(Mstr);
                }else{
                    this.$message.warning("您还有数据未填完！");
                }
            }
            if(this.tab1CurrentStep >0 && this.tab1CurrentStep <4){

                if(this.tab1DataList[str] != null && this.tab1DataList[str]!='' && this.tab1DataList[str1]!=null && 
                this.tab1DataList[str1]!='' && this.tab1DataList[str2]!=null
                && this.tab1DataList[str2]!=''&& this.tab1DataList[str3]!=null&& this.tab1DataList[str3]!=''&&
                this.tab1DataList[str4] != null && this.tab1DataList[str4]!='' && this.tab1DataList[str5]!=null && 
                this.tab1DataList[str5]!='' && this.tab1DataList[str6]!=null
                && this.tab1DataList[str6]!=''){
                    this.saveInputData(Mstr);
                 }else{
                    this.$message.warning("您还有数据未填完！");
                }
            }
            if(this.tab1CurrentStep == 4){
                if(this.tab1DataList[str] != null && this.tab1DataList[str]!='' &&  this.tab1DataList[str3]!=null&& this.tab1DataList[str3]!=''&&
                this.tab1DataList[str4] != null && this.tab1DataList[str4]!='' && this.tab1DataList[str5]!=null && 
                this.tab1DataList[str5]!='' && this.tab1DataList[str7]!=null
                && this.tab1DataList[str7]!=''){
                    this.saveInputData(Mstr);
                 }else{
                    this.$message.warning("您还有数据未填完！");
                }
            }
            }).catch(_ => {
                return this.$message.info("您取消了操作！");
            });
        },
        //生成保存函数
        saveInputData(list){
            axios({
            url:'/getSituationInputDataTable5',
            method:'post',
            params:{
                data:list
            }
            }).then(res=>{
            this.$message.success("操作成功！");
            var people = localStorage.getItem('ms_username');
            this.getTab1CurrentStep(people);
            }).catch(err=>{
            return this.$message.error("数据保存失败！");
            })
        },
        //获取育秧的占比
        getField22(field1,field2){
            var strValue = this.tab1DataList[field1];
          if(!isNaN(strValue)){ 
            this.tab1DataList[field2] = (Math.round((this.tab1DataList[field1]/this.tab1DataList.field_5) * 100*100)/100).toFixed(2)+'%';
        }else{
            this.tab1DataList[field1] = "";
            this.tab1DataList[field2] = "";
          alert("请输入数字！");
        }
        },
        //获得直播+移栽的总面积 同时获取该值与总面积的比例
        getSumArea(field1,field2,field3,field4){
            var strValue1 = this.tab1DataList[field1];
            var strValue2 = this.tab1DataList[field2];
            if(!isNaN(strValue1)&& !isNaN(strValue2)){
                this.tab1DataList[field3] = this.add(Number(this.tab1DataList[field1])+Number(this.tab1DataList[field2]));
                this.tab1DataList[field4] = (Math.round((this.tab1DataList[field3]/this.tab1DataList.field_3) * 100*100)/100).toFixed(2) +'%';
            }else{
                this.tab1DataList[field1] = "";
                this.tab1DataList[field2] = "";
                this.tab1DataList[field3] ="";
                this.tab1DataList[field4]  = "";
                alert("请输入数字！");
            }
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
      //获取表1 - 年度监测县(市、区)水稻播栽进度调查表 的当前进程
      getTab1CurrentStep(people){
        axios({
            url:'/getUserAllData0301',
            method:'post',
            params:{
                people:people,
                column_id:'',
                startYear:0,
                endYear:0
            }
        }).then(
            res=>{
                this.tableList = [];
                this.tab1DataList = res.data[0];
                this.tab1CurrentStep = 0;
                if(res.data.length > 0){
                    var obj = {};
                    obj.field1 = str;
                    obj.field21 = str1;
                    obj.field22 = str2;
                    obj.field3 = str3;
                    obj.field4 = str4;
                    obj.field5 = str5;
                    obj.field6 = str6;
                    this.tableList.push(obj);
                    if(this.tab1DataList.field_3 != null && this.tab1DataList.field_3 != ''){
                        this.tab1CurrentStep += 1;
                    }
                    if(this.tab1DataList.field_3 == null || this.tab1DataList.field_3 == ''){
                        this.tab1CurrentStep = 0;
                    }
                    for(var j = 0 ; j < 4 ; ++j){
                            if(this.tab1DataList.field_3 == null || this.tab1DataList.field_3 == ''){
                                this.tab1CurrentStep = 0;
                                break;
                            }
                            var start = 9+j;
                            var str = "field_"+start+"_1";
                            var str1 = "field_"+start+"_2_1";
                            var str2 = "field_"+start+"_2_2";
                            var str3 = "field_"+start+"_3";
                            var str4 = "field_"+start+"_4";
                            var str5 = "field_"+start+"_5";
                            var str6 = "field_"+start+"_6";
                            
                            if(this.tab1DataList[str] !=null && this.tab1DataList[str] !=''){
                                this.tab1CurrentStep += 1;
                                this.latestData = this.tab1DataList[str];
                                var obj = {};
                                obj.field1 = str;
                                obj.field21 = str1;
                                obj.field22 = str2;
                                obj.field3 = str3;
                                obj.field4 = str4;
                                obj.field5 = str5;
                                obj.field6 = str6;
                                this.tableList.push(obj);
                            }
                            if(this.tab1DataList[str] ==null || this.tab1DataList[str] ==''){
                                var obj = {};
                                obj.field1 = str;
                                obj.field21 = str1;
                                obj.field22 = str2;
                                obj.field3 = str3;
                                obj.field4 = str4;
                                obj.field5 = str5;
                                obj.field6 = str6;
                                this.tableList.push(obj);
                                break;
                            }                         
                    }
                    this.activeNames.push(this.tab1CurrentStep);
                    const json = JSON.stringify(res.data[0]);
                    this.copyData = JSON.parse(json);
                    if(this.tab1DataList.field_12_1 != null && this.tab1DataList.field_12_1 != ""){
                        this.terminateStep = true;
                    }
                }

                   
            }
        ).catch(err=>{
            return this.$message.error(err);
        }
        );
      },
        //获取 稻作类型 下拉框内容
        getSelectContent6(){ 
            axios({
                url:'/getSelectContent',
                method:'post',
                params:{
                fieldName:"稻作类型",
                riceType:'double'
                }
            }).then(
                res=>{
                this.listArray = res.data;
                var select = document.getElementById("select_6");
                for(var i = 0 ; i < this.listArray.length ; ++i){
                    select.options[i+1] = new Option(this.listArray[i].list);
                }
                
                }
            ).catch(err=>{
                return this.$message.error("获取下拉框内容失败！");
            });
        },

    },
    created(){
        var people = localStorage.getItem('ms_username');

        this.getTab1CurrentStep(people);
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