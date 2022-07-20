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
        <div style="width: 90%;border: #C4C4C4 1.3px solid;margin-top: 30px;">
         <div style="background-color: white;width: 370px;height: 30px;margin-top: -1px;border-width: 0 1px 1px 1px;border-style: solid;border-color: #C4C4C4;;margin-left: 4%;text-align: center;line-height: 20px;font-family: YouYuan;color: #006666;font-size: 25px;">
                <template v-if="currentStep >= 3">
                    <span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{stepTitle[currentStep-1]}}</span>
                </template>
                <template v-else="currentStep < 3">
                    <span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{stepTitle[currentStep]}}</span>
                </template>
                
        </div>
         <div style="width: 170px;height: 1.3px;background-color: #C1FFC1;margin-top: -0px;margin-left: 4%;"></div>

           <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;">
            <i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">
                年度水稻监测点茎蘖数叶面积构成表</span></div>
          <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
                <template v-if="tab0403DataList.length > 0">
                    <template v-i></template>
                    <el-form :inline="true" ref="form"  label-width="180px">
                        
                    <el-form-item label="测定日期" >
                        <template v-if="currentStep < tab0403CurrentStep || currentStep == 3">
                             <el-date-picker
                                v-model="tab0403DataList[0][tab0403_1]"
                                align="right"
                                type="date" readonly
                                placeholder="选择日期">
                        </el-date-picker>
                        </template>
                        <template v-else="currentStep >= tab0403CurrentStep">
                            <el-date-picker
                               v-model="tab0403DataList[0][tab0403_1]"
                               align="right"
                               type="date" 
                               placeholder="选择日期">
                       </el-date-picker>
                       </template>
                    </el-form-item>
                    <el-form-item label="株高(cm)">
                        <template v-if="currentStep < tab0403CurrentStep || currentStep == 3">
                            <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_2]" readonly></el-input>
                        </template>
                        <template v-else="currentStep >= tab0403CurrentStep">
                            <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_2]"></el-input>
                        </template>
                        </el-form-item>
                        <el-form-item label="总茎蘖数(万/亩)">
                            <template v-if="currentStep < tab0403CurrentStep || currentStep == 3">
                                <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_3]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab0403CurrentStep">
                                <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_3]"></el-input>
                            </template>
                        </el-form-item>
                 
                        <el-form-item label="平均单茎绿叶数">
                            <template v-if="currentStep < tab0403CurrentStep || currentStep == 3">
                                <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_4]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab0403CurrentStep">
                                <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_4]"></el-input>
                            </template>
                        </el-form-item>
                        <el-form-item label="倒4与倒3叶色比">
                            <template v-if="currentStep < tab0403CurrentStep || currentStep == 3">
                                <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_5]" readonly></el-input> 
                            </template>
                            <template v-else="currentStep >= tab0403CurrentStep">
                                <!-- <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_5]"></el-input> -->
                                <el-select v-model="tab0403DataList[0][tab0403_5]" style="width: 220px;">
                                    <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.label">
                                    </el-option>
                                </el-select>
                            </template>
                        </el-form-item>
                        <template v-if="currentStep == 0">
                            <el-form-item label="1叶分蘖个数">
                                <template v-if="currentStep < tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_6]" readonly></el-input>
                                </template>
                                <template v-else="currentStep >= tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_6]"></el-input>
                                </template>
                            </el-form-item>
                            <el-form-item label="2叶分蘖个数">
                                <template v-if="currentStep < tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_7]" readonly></el-input>
                                </template>
                                <template v-else="currentStep >= tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_7]"></el-input>
                                </template>
                            </el-form-item>
                            <el-form-item label="3叶及以上分蘖个数">
                                <template v-if="currentStep < tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_8]" readonly></el-input>
                                </template>
                                <template v-else="currentStep >= tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_8]"></el-input>
                                </template>
                            </el-form-item>
                            <el-form-item label="目标产量(kg/亩)">
                                <template v-if="currentStep < tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_9]" readonly></el-input>
                                </template>
                                <template v-else="currentStep >= tab0403CurrentStep">
                                    <el-input style="width: 220px;"  v-model="tab0403DataList[0][tab0403_9]"></el-input>
                                </template>
                            </el-form-item>
                        </template>
                    </el-form>                
                </template>
          </el-card>
        
        </div>
        <div style="margin-top: 2.3%;float: right;margin-right: 10%;">
            <template v-if="currentStep >= tab0403CurrentStep">
                <el-button  @click="getFormList($event)">一键上报</el-button>
            </template>
            <router-link to='/tablemain'>
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
        tab0403CurrentStep:0,//表0403    
        tab0403DataList:[],//保存表0403 - 年度水稻监测点茎蘖数叶面积构成表 的数据
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

      };
    },
    methods: {
        test(){
            //console.log(this.tab0403DataList);
        },
        //上报
        getFormList(e){
            var date = this.getNowTime();
            for(var i = 0 ;i < this.tab0403DataList.length ; ++i){
            this.tab0403DataList[i].modification_time = date;
            }
            var list = this.tab0403DataList;
            var str = '';
            for(var i = 0 ; i < list.length ; ++i){
                str += JSON.stringify(list[i]) + "#";
            }
            this.saveInputData(str);
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
                if(this.currentStep <= this.tab0403CurrentStep){
                    var gap5 = 10 + index;//表4-2 的进程绑定
                    this.tab0403_1 = "field_"+gap5+"_1";
                    this.tab0403_2 = "field_"+gap5+"_2";
                    this.tab0403_3 = "field_"+gap5+"_3";
                    this.tab0403_4 = "field_"+gap5+"_4";
                    this.tab0403_5 = "field_"+gap5+"_5";
                    this.tab0403_6 = "field_"+gap5+"_6";
                    this.tab0403_7 = "field_"+gap5+"_7";
                    this.tab0403_8 = "field_"+gap5+"_8";
                }

            }
           
        },

      //获取表4-3 - 年度监测县(市、区)中稻生育进度调查表（直播稻） 的当前进程
      getTab0403CurrentStep(people,point){
        axios({
            url:'/getUserAllDataTwo0403',
            method:'post',
            params:{
                people:people,
                column_id:'',
                monitor_location:point,
            }
        }).then(
            res=>{
                this.tab0403DataList = res.data;
                if(this.tab0403DataList.length > 0){
                    for(var j = 0 ; j < 3 ; ++j){
                        var start = 10+j;
                        var str = "field_"+start+"_1";
                        if(this.tab0403DataList[0][str] !=null && this.tab0403DataList[0][str] !=''){
                            this.tab0403CurrentStep += 1;
                        }else{
                            break;
                        }
                    }  
                }
               
                var gap = 10 + this.tab0403CurrentStep;
                this.tab0403_1 = "field_"+gap+"_1";
                this.tab0403_2 = "field_"+gap+"_2";
                this.tab0403_3 = "field_"+gap+"_3";
                this.tab0403_4 = "field_"+gap+"_4";
                this.tab0403_5 = "field_"+gap+"_5";
                this.tab0403_6 = "field_"+gap+"_6";
                this.tab0403_7 = "field_"+gap+"_7";
                this.tab0403_8 = "field_"+gap+"_8";
                
                if(this.tab0403CurrentStep > this.currentStep){
                    this.currentStep = this.tab0403CurrentStep;
                }
                // if(this.currentStep == 3){
                //     this.currentStep = this.currentStep - 1;
                //     this.tab0403CurrentStep = this.tab0403CurrentStep -1;
                // }
                if(this.currentStep == 3){
                    gap = gap -1;
                    this.tab0403_1 = "field_"+gap+"_1";
                    this.tab0403_2 = "field_"+gap+"_2";
                    this.tab0403_3 = "field_"+gap+"_3";
                    this.tab0403_4 = "field_"+gap+"_4";
                    this.tab0403_5 = "field_"+gap+"_5";
                    this.tab0403_6 = "field_"+gap+"_6";
                    this.tab0403_7 = "field_"+gap+"_7";
                    this.tab0403_8 = "field_"+gap+"_8";
                }
   
                this.maxStep = this.currentStep;
                // //console.log(this.tab0403_1+':'+this.tab0403_2+":"+this.tab0403_3+":");
   
            }
        ).catch(err=>{
            return this.$message.error("获取监测点茎蘖数叶面积构成数据失败！");
        }
        );
      },
    },
    created(){
        var people = localStorage.getItem('ms_username');
        var point = this.$route.params.point;
        this.getTab0403CurrentStep(people,point);

    },
  };
  </script>
  <style>

  </style>