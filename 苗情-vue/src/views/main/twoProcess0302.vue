<template>
    <div style="margin-left: 1%;">
        <div style="width: 100%;margin-top: 25px;">
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
        <div style="width: 89%;border: #C4C4C4 1.3px solid;margin-top: 30px;">
         <div style="background-color: white;width: 170px;height: 30px;margin-top: -1px;border-width: 0 1px 1px 1px;border-style: solid;border-color: #C4C4C4;;margin-left: 4%;text-align: center;line-height: 20px;font-family: YouYuan;color: #006666;font-size: 25px;">
            <template v-if="currentStep != 0">
                播载期进度<span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{currentStep}}</span>
            </template>
            <template v-if="currentStep == 0">
                <span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">进度准备</span>
            </template>
 
        
        </div>
         <div style="width: 170px;height: 1.3px;background-color: #C1FFC1;margin-top: -0px;margin-left: 4%;"></div>

          <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)水稻播栽进度调查表</span></div>
          <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <template v-if="currentStep <= tab1CurrentStep">
                <template v-if="tab1DataList.length > 0">
                        <el-form :inline="true" ref="form" label-width="180px">
                            <template v-if="currentStep == 0">
                                <el-form-item label="全县总面积(万亩)">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_4" readonly style="width: 207px;"></el-input>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_4" style="width: 207px;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                                    </template>
                                </el-form-item>
                                <el-form-item label="播种日期-育秧">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <!-- <el-input v-model="tab1DataList[0].field_5_1" :formatter="dateFormat" readonly></el-input> -->
                                        <el-date-picker
                                            v-model="tab1DataList[0].field_5_1" readonly
                                            align="right"
                                            type="date" style="width: 207px;"
                                            placeholder="选择日期">
                                        </el-date-picker>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <el-date-picker
                                            v-model="tab1DataList[0].field_5_1" :formatter="dateFormat"
                                            align="right"
                                            type="date" style="width: 207px;"
                                            placeholder="选择日期">
                                        </el-date-picker>
                                    </template>
                                </el-form-item>
                                <el-form-item label="播种日期-直播">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <el-date-picker
                                            v-model="tab1DataList[0].field_5_2"
                                            align="right" readonly
                                            type="date" style="width: 207px;"
                                            placeholder="选择日期">
                                        </el-date-picker>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <!-- <el-input v-model="tab1DataList[0].field_4_2" ></el-input> -->
                                        <el-date-picker
                                            v-model="tab1DataList[0].field_5_2" :formatter="dateFormat"
                                            align="right"
                                            type="date" style="width: 207px;"
                                            placeholder="选择日期">
                                        </el-date-picker>
                                    </template>
                                </el-form-item>
                                <el-form-item label="秧田面积（万亩）">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_6" readonly style="width: 207px;"></el-input>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_6" style="width: 207px;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                                    </template>
                                </el-form-item>
                                <el-form-item width="260" label="其中（秧田面积）集中育秧">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_7_1" readonly style="width: 207px;"></el-input>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_7_1" style="width: 207px;" @blur.prevent="checkFirstFieldInput($event,'field_7_1','field_7_2','1')"></el-input>
                                    </template>
                                </el-form-item>
                                <el-form-item width="260" label="其中（秧田面积）集中育秧/秧田面积">
                                        <el-input v-model="tab1DataList[0].field_7_2" readonly style="width: 207px;"></el-input>
                                </el-form-item>
                                <el-form-item label="折合大田面积（万亩）">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_8_1" readonly style="width: 207px;"></el-input>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_8_1" style="width: 207px;" @blur.prevent="checkFirstFieldInput($event,'field_8_1','field_8_2','0')"></el-input>
                                    </template>
                                </el-form-item>
                                <el-form-item label="折合大田面积（万亩）/总面积">
                                    <el-input v-model="tab1DataList[0].field_8_2" readonly style="width: 207px;"></el-input>
                                </el-form-item>
                                <el-form-item label="其中（折合大田面积）集中育秧">
                                    <template v-if="currentStep < tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_9_1" readonly style="width: 207px;"></el-input>
                                    </template>
                                    <template v-else="currentStep >= tab1CurrentStep">
                                        <el-input v-model="tab1DataList[0].field_9_1" style="width: 207px;" @blur.prevent="checkFirstFieldInput($event,'field_9_1','field_9_2','0')"></el-input>
                                    </template>
                                </el-form-item>
                                <el-form-item  label="其中（折合大田面积）集中育秧/总面积">
                                    <el-input v-model="tab1DataList[0].field_9_2" readonly style="width: 207px;"></el-input>
                                </el-form-item>
                            </template>
                            <template v-if="currentStep > 0">
                                    <el-form-item label="日期" >
                                        <template v-if="currentStep < tab1CurrentStep">
                                            <el-date-picker
                                                v-model="tab1DataList[0][tab1_1]"
                                                align="right" readonly
                                                type="date" style="width: 207px;"
                                                placeholder="选择日期">
                                            </el-date-picker>
                                        </template>
                                        <template v-else="currentStep >= tab1CurrentStep">
                                            <el-date-picker
                                                v-model="tab1DataList[0][tab1_1]"
                                                align="right"
                                                type="date" style="width: 207px;"
                                                placeholder="选择日期">
                                            </el-date-picker>
                                        </template>
                                </el-form-item>
          
                                    <el-form-item label="育秧已播种面积（万亩）">
                                        <template v-if="currentStep < tab1CurrentStep">
                                            <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_2_1]" @blur.prevent="getField22(tab1_2_1,tab1_2_2)" readonly></el-input>
                                        </template>
                                        <template v-else="currentStep >= tab1CurrentStep">
                                            <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_2_1]" @blur.prevent="getField22(tab1_2_1,tab1_2_2)"></el-input>
                                        </template>
                                    </el-form-item>
                                    <el-form-item label="育秧已播种面积（万亩）/秧田面积">
                                        <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_2_2]" readonly></el-input>
                                    </el-form-item>
                                    <el-form-item label="已栽插面积（万亩）">
                                        <template v-if="currentStep < tab1CurrentStep">
                                            <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_3]" @blur.prevent="getSumArea(tab1_3,tab1_4,tab1_5,tab1_6)" readonly></el-input>
                                        </template>
                                        <template v-else="currentStep >= tab1CurrentStep">
                                            <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_3]" @blur.prevent="getSumArea(tab1_3,tab1_4,tab1_5,tab1_6)"></el-input>
                                        </template>
                                    </el-form-item>
                                    <el-form-item label="直播已播种面积（万亩）">
                                        <template v-if="currentStep < tab1CurrentStep">
                                            <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_4]" @blur.prevent="getSumArea(tab1_3,tab1_4,tab1_5,tab1_6)" readonly></el-input>
                                        </template>
                                        <template v-else="currentStep >= tab1CurrentStep">
                                            <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_4]" @blur.prevent="getSumArea(tab1_3,tab1_4,tab1_5,tab1_6)" ></el-input>
                                        </template>
                                    </el-form-item>
                                    <el-form-item label="移栽+直播">
                                        <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_5]" readonly></el-input>
                                    </el-form-item>
                                    <el-form-item label="（移栽+直播）/总面积">
                                        <el-input style="width: 207px;" v-model="tab1DataList[0][tab1_6]" readonly></el-input>
                                    </el-form-item>
               
                            </template>
                           
                            
                        </el-form>
                </template>
            </template>
           </el-card>
    
        </div>
        <div style="margin-top: 20px;float: right;margin-right: 17%;">
            <template v-if="currentStep >= tab1CurrentStep">
                <el-button  @click="getFormList($event)">一键上报</el-button>
                
            </template>
            <router-link to='/traceBackData'>
                <el-button style="display: inline-block; margin-left: 10px;">返回</el-button>
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
        tab1CurrentStep:0,//表1-  3-1当前进程,最大是4

        tab1DataList:[],//保存表1 - 年度监测县(市、区)水稻播栽进度调查表 的数据

        tab1_1:'',//表1的字段绑定
        tab1_2_1:'',
        tab1_2_2:'',
        tab1_2:'',
        tab1_3:'',
        tab1_4:'',
        tab1_5:'',
        tab1_6:'',
        // 步骤标题
      stepTitle: ['进度准备','播载期进度1', '播载期进度2', '播载期进度3'],

      };
    },
    methods: {
        test(){
       
        },
        //检查input框的输入是否符合要求
        checkFirstFieldInput(e,field1,field2,flag){
            var strValue = e.target.value;
          if(!isNaN(strValue)){ 
              if(this.tab1DataList[0][field1] != null && this.tab1DataList[0][field1] != ''){
                  if(flag == '0'){//计算总面积占比
                    this.tab1DataList[0][field2] = (Math.round((this.tab1DataList[0][field1]/this.tab1DataList[0].field_4) * 100*100)/100).toFixed(2)+'%';
                  }
                  if(flag == '1'){//计算秧田面积
                    this.tab1DataList[0][field2] = (Math.round((this.tab1DataList[0][field1]/this.tab1DataList[0].field_6) * 100*100)/100).toFixed(2)+'%';
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
            var str = '';
            for(var i = 0 ; i < list.length ; ++i){
                str += JSON.stringify(list[i]) + "#";
            }
            //console.log(this.tab1DataList);
            //console.log(this.tab1_1+":"+this.tab1_2_1+":"+this.tab1_2_2);
            this.saveInputData(str);
            }).catch(_ => {
                return this.$message.info("您取消了操作！");
            });
            
        },
        //生成保存函数
        saveInputData(list){
            axios({
            url:'/getSituationInputDataTableTwo0302',
            method:'post',
            params:{
                data:list
            }
            }).then(res=>{
            //console.log(res);
            this.$message.success("操作成功！");
            }).catch(err=>{
            return this.$message.error("数据保存失败！");
            })
        },
        //获取育秧的占比
        getField22(field1,field2){
            //console.log(this.currentStep);
            //console.log(field1 +":"+field2);
            //console.log(this.tab1DataList[0][field1]);
            var strValue = this.tab1DataList[0][field1];
          if(!isNaN(strValue)){ 
            this.tab1DataList[0][field2] = (Math.round((this.tab1DataList[0][field1]/this.tab1DataList[0].field_6) * 100*100)/100).toFixed(2)+'%';
        }else{
            this.tab1DataList[0][field1] = "";
            this.tab1DataList[0][field2] = "";
          alert("请输入数字！");
        }
        },
        //获得直播+移栽的总面积 同时获取该值与总面积的比例
        getSumArea(field1,field2,field3,field4){
            //console.log(field1+":"+field2);
            var strValue1 = this.tab1DataList[0][field1];
            var strValue2 = this.tab1DataList[0][field2];
            if(!isNaN(strValue1)&& !isNaN(strValue2)){
                this.tab1DataList[0][field3] = Number(this.tab1DataList[0][field1])+Number(this.tab1DataList[0][field2]);
                this.tab1DataList[0][field4] = (Math.round((this.tab1DataList[0][field3]/this.tab1DataList[0].field_4) * 100*100)/100).toFixed(2)+'%';
            }else{
                this.tab1DataList[0][field1] = "";
                this.tab1DataList[0][field2] = "";
                this.tab1DataList[0][field3] ="";
                this.tab1DataList[0][field4]  = "";
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
        //日期格式设置
        dateFormat:function(row,column){
            var date = row[column.property];
            if(date == undefined){return ''};
            return moment(date).format("YYYY-MM-DD");
            },
        handleStep(index){
            //如果index > currentStep ，不允许跳转
            if(index > this.maxStep){
                this.currentStep = this.maxStep;
                return this.$message.info("该进程尚未开启！");
            }
            if(index <= this.maxStep){
                this.currentStep = index;
                //修改各表对应的进程下的字段绑定
                if(this.currentStep <= this.tab1CurrentStep){
                    if(this.currentStep < 4){
                       var gap1 = 9 + index;//表3-1 的进程绑定 OK
                        this.tab1_1 = "field_"+gap1+"_1";
                        this.tab1_2_1 = "field_"+ gap1 + "_2_1";
                        this.tab1_2_2 = "field_"+ gap1 + "_2_2"; //占比
                        this.tab1_3 = "field_"+gap1+"_3";
                        this.tab1_4 = "field_"+gap1+"_4";
                        this.tab1_5 = "field_"+gap1+"_5";
                        this.tab1_6 = "field_"+gap1+"_6";                     
                    }
                    if(this.currentStep == 4){
                        var gap1 = 9 + index;//表3-1 的进程绑定 OK
                        this.tab1_1 = "field_"+gap1+"_1";
                        this.tab1_2 = "field_"+gap1+"_2";
                        this.tab1_3 = "field_"+gap1+"_3";
                        this.tab1_4 = "field_"+gap1+"_4";
                        this.tab1_5 = "field_"+gap1+"_5";
                    }
                }
                //console.log(this.tab1_1+":"+this.tab1_2_1+":"+this.tab1_2_2+":"+this.tab1_3+":"+this.tab1_4+":"+this.tab1_5);

            }
           
        },
      //获取表1 - 年度监测县(市、区)水稻播栽进度调查表 的当前进程
      getTab1CurrentStep(people){
        axios({
            url:'/getUserAllDataTwo0302',
            method:'post',
            params:{
                people:people,
                column_id:'',
            }
        }).then(
            res=>{
                this.tab1DataList = res.data;
                //console.log(this.tab1DataList);
                if(this.tab1DataList.length > 0){
                    for(var j = 0 ; j < 3 ; ++j){
                        var start = 9+j;
                        var str = "field_"+start+"_1";
                        if(this.tab1DataList[0][str] !=null && this.tab1DataList[0][str] !=''){
                            this.tab1CurrentStep += 1;
                        }else{
                            break;
                        }
                    }
                    //console.log(this.tab1CurrentStep+":3-2");

                    //判断进程准备的数据是否已经填报
                    if(this.tab1DataList[0].field_4.length == 0|| (this.tab1DataList[0].field_5_1 == null || this.tab1DataList[0].field_5_1 == '') || (this.tab1DataList[0].field_5_2 == null || this.tab1DataList[0].field_5_2 == '')  || (this.tab1DataList[0].field_8_1 == null || this.tab1DataList[0].field_8_1 == '')|| (this.tab1DataList[0].field_6 == null || this.tab1DataList[0].field_6=='')){
                        //进程准备没填
                        // //console.log(this.tab1DataList[0].field_4.length+":"+this.tab1DataList[0].field_5_1.length+":"+this.tab1DataList[0].field_5_2.length+":"+this.tab1DataList[0].field_8_1.length+":"+this.tab1DataList[0].field_6.length);
                        this.tab1CurrentStep = 0;
                    }
                    // if(this.tab1CurrentStep > 3){
                    //     this.tab1CurrentStep = 3;
                    // }
                        var gap = 9 + this.tab1CurrentStep;
                        this.tab1_1 = "field_"+gap+"_1";
                        this.tab1_2_1 = "field_"+ gap + "_2_1";
                        this.tab1_2_2 = "field_"+ gap + "_2_2"; //占比
                        this.tab1_3 = "field_"+gap+"_3";
                        this.tab1_4 = "field_"+gap+"_4";
                        this.tab1_5 = "field_"+gap+"_5";
                        this.tab1_6 = "field_"+gap+"_6";   

                    if(this.tab1CurrentStep > this.currentStep){
                        this.currentStep = this.tab1CurrentStep;
                    }
                    this.maxStep = this.currentStep;
                }
                //console.log(this.tab1CurrentStep+":3-2");
                //console.log(this.currentStep);
            }
        ).catch(err=>{
            return this.$message.error("获取播载进度失败！");
        }
        );
      },


    },
    created(){
        var people = localStorage.getItem('ms_username');
        this.getTab1CurrentStep(people);
        // this.getTab2CurrentStep(people);
        // this.getTab3CurrentStep(people);
        // this.getTab4CurrentStep(people);
        // this.getTab5CurrentStep(people);

    },
  };
  </script>
  <style>

  </style>