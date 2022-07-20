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
                进程<span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{currentStep+1}}</span>
 
        
        </div>
         <div style="width: 170px;height: 1.3px;background-color: #C1FFC1;margin-top: -0px;margin-left: 4%;"></div>
        
          <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)水稻苗情评价动态列表（早稻-移栽）</span></div>
          <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <template v-if="currentStep <= tab2CurrentStep">
                <template v-if="tab2DataList.length > 0">
                <el-form :inline="true" ref="form"  label-width="180px">
                    <template v-if="currentStep == 0">
                        <el-form-item label="全县总面积">
                            <template v-if="currentStep < tab2CurrentStep">
                                <el-input style="width: 220px;" v-model="tab2DataList[0].field_4" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab2CurrentStep">
                                <el-input style="width: 220px;" v-model="tab2DataList[0].field_4"></el-input>
                            </template>
                            
                        </el-form-item>
                        
                    </template>
                    <el-form-item label="日期" >
                        <template v-if="currentStep < tab2CurrentStep">
                            <el-date-picker
                                v-model="tab2DataList[0][tab2_1]"
                                align="right"
                                type="date" readonly
                        >
                        </el-date-picker>
                        </template>
                        <template v-else="currentStep >= tab2CurrentStep">
                            <el-date-picker
                                v-model="tab2DataList[0][tab2_1]"
                                align="right"
                                type="date"
                        >
                        </el-date-picker>
                        </template>
                    </el-form-item>
                    <el-form-item label="一类苗（万亩）">
                        <template v-if="currentStep < tab2CurrentStep">
                            <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_2_1]" readonly></el-input>
                        </template>
                        <template v-else="currentStep >= tab2CurrentStep">
                            <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_2_1]"  @blur="getPercent(tab2_2_1,tab2_2_2)"></el-input>
                        </template>
                    </el-form-item>
                    <el-form-item label="一类苗/全县总面积">
                        <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_2_2]" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="二类苗（万亩）">
                        <template v-if="currentStep < tab2CurrentStep">
                            <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_3_1]" readonly></el-input>
                        </template>
                        <template v-else="currentStep >= tab2CurrentStep">
                            <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_3_1]" @blur="getPercent(tab2_3_1,tab2_3_2)"></el-input>
                        </template>
                    </el-form-item>
                    <el-form-item label="二类苗/全县总面积">
                        <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_3_2]" readonly></el-input>
                    </el-form-item>
                    <el-form-item label="三类苗（万亩）">
                        <template v-if="currentStep < tab2CurrentStep">
                            <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_4_1]" readonly></el-input>
                        </template>
                        <template v-else="currentStep >= tab2CurrentStep">
                            <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_4_1]" @blur="getPercent(tab2_4_1,tab2_4_2)"></el-input>
                        </template>
                    </el-form-item>
                    <el-form-item label="三类苗/全县总面积">
                        <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_4_2]" readonly></el-input>
                    </el-form-item>
                    </el-form>
                </template>
            </template>
          </el-card>
    
        </div>
        <div style="margin-top: 2.3%;float: right;margin-right: 17%;">
            <template v-if="currentStep >= tab2CurrentStep">
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
        tab2CurrentStep:0,//表2   3-3当前进程，最大是7

        tab2DataList:[],//保存表2 - 年度监测县(市、区)水稻苗情评价动态列表(早稻-移栽) 的数据
        tab2_1:'',//表2的字段绑定
        tab2_2_1:'',
        tab2_2_2:'',
        tab2_3_1:'',
        tab2_3_2:'',
        tab2_4_1:'',
        tab2_4_2:'',
        // 步骤标题
      stepTitle: ['进程1','进程2', '进程3', '进程4', '进程5','进程6','进程7'],
      };
    },
    methods: {
        test(){
       
        },
        //上报
        getFormList(e){
            this.$confirm("是否上报？").then(_=>{
                var date = this.getNowTime();
                for(var i = 0 ;i < this.tab2DataList.length ; ++i){
                this.tab2DataList[i].modification_time = date;
                }
                var list = this.tab2DataList;
                var str = '';
                for(var i = 0 ; i < list.length ; ++i){
                    str += JSON.stringify(list[i]) + "#";
                }

                //判断所填的占比是否为100%
                var sum = Number(this.tab2DataList[0][this.tab2_2_1]) + Number(this.tab2DataList[0][this.tab2_3_1]) + Number(this.tab2DataList[0][this.tab2_4_1]);
                // var sum = (Math.round((this.tab2DataList[0][this.tab2_2_2] + this.tab2DataList[0][this.tab2_3_2] + this.tab2DataList[0][this.tab2_4_2]) * 100*100)/100).toFixed(2)+'%';
                if(sum != this.tab2DataList[0].field_4){
                    return this.$message.error("上报失败！请核对类苗填报数据！");
                }else{
                    this.saveInputData(str);
                }                
            }).catch(_=>{
                 return $message.info("您取消了操作！");
            }
            );

            
        },
        //生成保存函数
        saveInputData(list){
            axios({
            url:'/getSituationInputDataTableTwo0303',
            method:'post',
            params:{
                data:list
            }
            }).then(res=>{
            this.$message.success("操作成功！");
            }).catch(err=>{
            return this.$message.error("数据保存失败！");
            })
        },
        //获取占比
        getPercent(field1,field2){
            this.tab2DataList[0][field2] = (Math.round((this.tab2DataList[0][field1]/this.tab2DataList[0].field_4) * 100*100)/100).toFixed(2)+'%';
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
        handleStep(index){
            //console.log(index);
            //如果index > currentStep ，不允许跳转
            if(index > this.maxStep){
                this.currentStep = this.maxStep;
                return this.$message.info("该进程尚未开启！");
            }
            if(index <= this.maxStep){
                //console.log("current:"+this.currentStep);
                this.currentStep = index;
                //修改各表对应的进程下的字段绑定
                if(this.currentStep <= this.tab2CurrentStep){
                    var gap2 = 5 + index;//表3-3 的进程绑定
                    this.tab2_1 = "field_"+gap2+"_1";
                    this.tab2_2_1= "field_" + gap2 + "_2_1";
                    this.tab2_2_2 = "field_"+gap2+"_2_2";
                    this.tab2_3_1= "field_" + gap2 + "_3_1";
                    this.tab2_3_2 = "field_"+gap2+"_3_2";
                    this.tab2_4_1= "field_" + gap2 + "_4_1";
                    this.tab2_4_2 = "field_"+gap2+"_4_2";
                }
                // //console.log(this.tab2_1+":"+this.tab2_2_1+":"+this.tab2_2_2+":"+this.tab2_3_1+":"+this.tab2_3_2+":"+this.tab2_4_1+":"+this.tab2_4_2);
                    // //console.log(this.tab2_1+":"+this.tab2_2_1+":"+this.tab2_2_2+":"+this.tab2_3_1+":"+this.tab2_3_2+":"+this.tab2_4_1+":"+this.tab2_4_2);

            }
           
        },
      //获取表2 - 年度监测县(市、区)水稻苗情评价动态列表(移栽) 的当前进程
      getTab2CurrentStep(people){
        axios({
            url:'/getUserAllDataTwo0303',
            method:'post',
            params:{
                people:people,
                column_id:'',
            }
        }).then(
            res=>{
                // //console.log(res.data);
                this.tab2DataList = res.data;
                if(this.tab2DataList.length > 0){
                    for(var j = 0 ; j < 7 ; ++j){
                        var start = 5+j;
                        var str = "field_"+start+"_1";
                        if(this.tab2DataList[0][str] !=null && this.tab2DataList[0][str] !=''){
                            this.tab2CurrentStep += 1;
                        }else{
                            break;
                        }
                    }

                    if(this.tab2CurrentStep == 7){
                        // this.currentStep = this.tab2CurrentStep;
                        this.tab2CurrentStep = this.tab2CurrentStep - 1;
                        
                    }  
                    var gap = 5 + this.tab2CurrentStep;
                    this.tab2_1 = "field_"+gap+"_1";
                    this.tab2_2_1= "field_" + gap + "_2_1";
                    this.tab2_2_2 = "field_"+gap+"_2_2";
                    this.tab2_3_1= "field_" + gap + "_3_1";
                    this.tab2_3_2 = "field_"+gap+"_3_2";
                    this.tab2_4_1= "field_" + gap + "_4_1";
                    this.tab2_4_2 = "field_"+gap+"_4_2";
                    if(this.tab2CurrentStep > this.currentStep){
                        this.currentStep = this.tab2CurrentStep;
                    }
                    // if(this.tab2CurrentStep == 7){
                    //     this.currentStep = this.tab2CurrentStep-1;
                    //     gap = gap -1;
                    //     this.tab2_1 = "field_"+gap+"_1";
                    //     this.tab2_2_1= "field_" + gap + "_2_1";
                    //     this.tab2_2_2 = "field_"+gap+"_2_2";
                    //     this.tab2_3_1= "field_" + gap + "_3_1";
                    //     this.tab2_3_2 = "field_"+gap+"_3_2";
                    //     this.tab2_4_1= "field_" + gap + "_4_1";
                    //     this.tab2_4_2 = "field_"+gap+"_4_2";
                    // }
                    this.maxStep = this.currentStep;
                }

                // //console.log(this.tab2_1+":"+this.tab2_2_1+":"+this.tab2_2_2+":"+this.tab2_3_1+":"+this.tab2_3_2+":"+this.tab2_4_1+":"+this.tab2_4_2);
                // //console.log("3-2:"+this.tab2CurrentStep);
            }
        ).catch(err=>{
            return this.$message.error("获取苗情动态进程失败！");
        }
        );
      },

    },
    created(){
        var people = localStorage.getItem('ms_username');
        this.getTab2CurrentStep(people);


    },
  };
  </script>
  <style>

  </style>