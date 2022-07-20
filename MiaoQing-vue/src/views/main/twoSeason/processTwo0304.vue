<template>
    <div style="margin-left: 1%;">
        <div style="width: 98%;height: 50px;">
            <span style="font-size: 20px;color: rgb(0, 102, 102);margin-right: 48%;">年度监测县(市、区)水稻苗情评价动态列表（早稻）-（直播稻）</span>
        
        </div>
        <el-collapse v-model="activeNames">
            <template v-for="(item,index) in reverseTableList">
                <el-collapse-item  :name="tableList.length-index-1" :title=stepTitle[tableList.length-index-1]>
                        <el-form :inline="true"  label-width="180px">
                            <template v-if="tableList.length-index-1 == 0">
                                <el-form-item label="全县总面积（万亩）" >
                                <el-input  v-model="tab3DataList.field_4" align="right"
                                :disabled="copyData.field_4 != null && copyData.field_4 != ''" placeholder="请输入数字（小数点后四位）"   oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                            </el-form-item>
                            <el-form-item label="稻作类型">
                                <!-- <el-input v-model="tab1DataList.field_3" 
                                :disabled="copyData.field_3 != null && copyData.field_3 !=''" style="width: 207px;" @blur="changeAllArea"></el-input>   -->
                                <template v-if="copyData.field_3 != null && copyData.field_3 != '' && column_id == ''">
                                    <el-input v-model="tab3DataList.field_3" readonly></el-input>
                                     
                                  </template> 
                                  <template v-if="copyData.field_3 == null || copyData.field_3 == '' || column_id != '' ">
                                      <el-select v-model="tab3DataList.field_3" placeholder="请选择" @focus="brandKeyChange1">
                                            <el-option
                                                v-for="item in daoZuoTypeData"
                                                :key="item.id"
                                                :label="item.list"
                                                :value="item.list">
                                            </el-option>
                                      </el-select>
                                  </template>
                            </el-form-item>
                            </template>
                            <el-form-item label="日期" >
                                <el-date-picker  v-model="tab3DataList[item.field1]" :picker-options="pickerOptions" align="right" type="date"
                                :disabled="copyData[item.field1] != null && copyData[item.field1] != ''"></el-date-picker>
                            </el-form-item>
                            <el-form-item label="一类苗（万亩）">
                                    <el-input style="width: 220px;" v-model="tab3DataList[item.field2]" @blur="getPercent(item.field2,item.field3)"
                                    :disabled="copyData[item.field2] != null && copyData[item.field2] != ''" placeholder="请输入数字（小数点后四位）"   oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                              
                            </el-form-item>
                            <el-form-item label="一类苗/全县总面积">
                                <el-input style="width: 220px;" v-model="tab3DataList[item.field3]" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="二类苗（万亩）">
                               
                                
                                    <el-input style="width: 220px;" v-model="tab3DataList[item.field4]" @blur="getPercent(item.field4,item.field5)"
                                    :disabled="copyData[item.field4] != null && copyData[item.field4] != ''" placeholder="请输入数字（小数点后四位）"   oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                                
                            </el-form-item>
                            <el-form-item label="二类苗/全县总面积">
                                <el-input style="width: 220px;" v-model="tab3DataList[item.field5]" disabled></el-input>
                            </el-form-item>
                            <el-form-item label="三类苗（万亩）">
                                
                             
                                    <el-input style="width: 220px;" v-model="tab3DataList[item.field6]" @blur="getPercent(item.field6,item.field7)"
                                    :disabled="copyData[item.field6] != null && copyData[item.field6] != ''" placeholder="请输入数字（小数点后四位）"   oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                                
                            </el-form-item>
                            <el-form-item label="三类苗/全县总面积">
                                <el-input style="width: 220px;" v-model="tab3DataList[item.field7]" disabled></el-input>
                            </el-form-item>
                        </el-form>
                  
                </el-collapse-item>       
            </template>
          </el-collapse>
          <template v-if="terminateStep == false">
            <el-button @click="getFormList($event)" style="margin-left: 88%;margin-top: 1%;">一键上报</el-button>
        </template>
        <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
          <el-button style="display: inline-block;margin-top: 1%;float: right;">返回</el-button>
        </router-link>
    </div>
  </template>
  <script>
    import axios from 'axios'
    import * as math from 'mathjs'
  export default{
      data() {
      return {
        currentStep:0,//当前进程数
        maxStep:0,//最大进程数
        tab3CurrentStep:0,//表3   3-3当前进程，最大是9
        tab3DataList:[],//保存表3 - 年度监测县(市、区)水稻苗情评价动态列表(直播) 的数据
        tab3_1:'',//表3的字段绑定
        tab3_2_1:'',
        tab3_2_2:'',
        tab3_3_1:'',
        tab3_3_2:'',
        tab3_4_1:'',
        tab3_4_2:'',
        column_id:'',
        activeNames:[],
        tableList:[],
        copyData:[],
        terminateStep:false,
        latestData:'',
        daoZuoTypeData:[],
        pickerOptions: {
              disabledDate: (time) => {
                  return time.getTime() < new Date(this.latestData).getTime();
              }
          },
        // 步骤标题
        stepTitle: ['进程1（大田直播后20天）','进程2（大田直播后28天）', '进程3（直播后35天）', '进程4（大田直播后42天）', '进程5（大田直播后49天）','进程6（大田直播后56天）','进程7（大田直播后66天）','进程8（大田直播后76天）','进程9（大田直播后86天）'],

      };
    },
    methods: {
        clearInput(){//改变全县总面积，清空所有输入框
            var start = 5+this.tab3CurrentStep;
            var str = "field_"+start+"_1";
            var str1 = "field_"+start+"_2_1";
            var str2 = "field_" + start + "_2_2";
            var str3 = "field_"+start+"_3_1";
            var str4 = "field_" + start + "_3_2";
            var str5 = "field_"+start+"_4_1";
            var str6 = "field_" + start + "_4_2";
            this.tab3DataList[str] = '';
            this.tab3DataList[str1]='';
            this.tab3DataList[str2]='';
            this.tab3DataList[str3]='';
            this.tab3DataList[str4]='';
            this.tab3DataList[str5]='';
            this.tab3DataList[str6]='';
        },
        //获取稻作类型数据
        brandKeyChange1:function(){
          axios({
              url:'/getSelectContent',
              method:'post',
              params:{
                  fieldName:"稻作类型",
                  riceType:'double'
              },
          }).then(res=>{
              this.daoZuoTypeData = res.data;
          }).catch(err=>{
              return this.$message.error("获取稻作类型失败！");
          });
          },
         //处理小数点
        add(value) {
            const precision = 14
            return Number(math.format(value, precision))
        },
        //上报
        getFormList(e){
                this.$confirm("是否上报？").then(_=>{
                    var date = this.getNowTime();
                for(var i = 0 ;i < this.tab3DataList.length ; ++i){
                    this.tab3DataList[i].modification_time = date;
                }
                var list = this.tab3DataList;
                var Mstr = '';
                Mstr += JSON.stringify(list) + "#";
                var start = 5+this.tab3CurrentStep;
                var str = "field_"+start+"_1";
                var str1 = "field_"+start+"_2_1";
                var str2 = "field_" + start + "_2_2";
                var str3 = "field_"+start+"_3_1";
                var str4 = "field_" + start + "_3_2";
                var str5 = "field_"+start+"_4_1";
                var str6 = "field_" + start + "_4_2";
                //判断所有数据是否填完
                if(this.tab3DataList[str]!=null && this.tab3DataList[str]!='' && this.tab3DataList[str1]!=null
                && this.tab3DataList[str1]!=''&& this.tab3DataList[str3]!=null && this.tab3DataList[str3]!=''&& this.tab3DataList[str5]!=null&& this.tab3DataList[str5]!=''){
                    //判断所填的占比是否为100%
                    var sum =this.add(this.tab3DataList[str1]*1 + this.tab3DataList[str3]*1 + this.tab3DataList[str5]*1);
                    if(sum != this.tab3DataList.field_4){
                        return this.$message.error("上报失败！请核对类苗填报数据！");
                    }else{
                        this.saveInputData(Mstr);
                    } 
                }else{
                    this.$message.warnning("您还有数据未填完！");
                }
            
        }).catch(_=>{
          return this.$message.info("您取消了操作！");
        });          
        },
        //生成保存函数
        saveInputData(list){
            axios({
            url:'/getSituationInputDataTableTwo0304',
            method:'post',
            params:{
                data:list
            }
            }).then(res=>{
            this.$message.success("操作成功！");
            var people = localStorage.getItem('ms_username');
            this.getTab3CurrentStep(people);
            }).catch(err=>{
            return this.$message.error("数据保存失败！");
            })
        },
        //获取占比
        getPercent(field1,field2){
            this.tab3DataList[field2] = (Math.round((this.tab3DataList[field1]/this.tab3DataList.field_4) * 100*100)/100).toFixed(2)+'%';
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
      //获取表3 - 年度监测县(市、区)水稻苗情评价动态列表(直播) 的当前进程
      getTab3CurrentStep(people){
        axios({
            url:'/getUserAllDataTwo0304',
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
                this.tab3DataList = res.data[0];
                this.tab3CurrentStep = 0;
                if(res.data.length > 0){
                    for(var j = 0 ; j < 9 ; ++j){
                        var start = 5+j;
                        var str = "field_"+start+"_1";
                        var str1 = "field_"+start+"_2_1";
                        var str2 = "field_" + start + "_2_2";
                        var str3 = "field_"+start+"_3_1";
                        var str4 = "field_" + start + "_3_2";
                        var str5 = "field_"+start+"_4_1";
                        var str6 = "field_" + start + "_4_2";
                        if(this.tab3DataList[str] !=null && this.tab3DataList[str] !=''){
                            this.tab3CurrentStep += 1;
                            this.latestData = this.tab3DataList[str];
                            var obj = {};
                            obj.field1 = str;
                            obj.field2 = str1;
                            obj.field3 = str2;
                            obj.field4 = str3;
                            obj.field5 = str4;
                            obj.field6 = str5;
                            obj.field7 = str6;
                            this.tableList.push(obj);
                        }else{
                            var obj = {};
                            obj.field1 = str;
                            obj.field2 = str1;
                            obj.field3 = str2;
                            obj.field4 = str3;
                            obj.field5 = str4;
                            obj.field6 = str5;
                            obj.field7 = str6;
                            this.tableList.push(obj);
                            break;
                        }
                    }  
                    this.activeNames.push(this.tab3CurrentStep);
                    const json = JSON.stringify(res.data[0]);
                    this.copyData = JSON.parse(json);
                if(this.tab3DataList.field_13_4_1 != null && this.tab3DataList.field_13_4_1 != ""){
                    this.terminateStep = true;
                }  
                }

                
            }
        ).catch(err=>{
            return this.$message.error("获取苗情动态进程失败！");
        }
        );
      },

    },
    created(){
        var people = localStorage.getItem('ms_username');
        this.getTab3CurrentStep(people);
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