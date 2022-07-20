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
                进程<span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{currentStep}}</span>
            </template>
            <!-- <template v-if="currentStep == 9">
                进程<span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{currentStep}}</span>
            </template> -->
            <template v-if="currentStep == 0">
                <span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">移栽期</span>
            </template>
        </div>
         <div style="width: 170px;height: 1.3px;background-color: #C1FFC1;margin-top: -0px;margin-left: 4%;"></div>
        
          <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)中稻生育进度调查表（移栽稻）</span></div>
          <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <!-- <template v-if="currentStep <= tab4CurrentStep"> -->
                <el-form :inline="true" ref="form" :model="form" label-width="180px">
                <template v-if="tab4DataList.length>0">
                    <template v-if="currentStep == 0 ">
                        <el-form-item label="日期" >
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-date-picker
                                    v-model="tab4DataList[0][tab4_1]"
                                    align="right"
                                    type="date" readonly
                                    >
                            </el-date-picker>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-date-picker
                                    v-model="tab4DataList[0][tab4_1]"
                                    align="right"
                                    type="date"
                                    >
                                </el-date-picker>
                            </template>
                            
                        </el-form-item>
                        <el-form-item label="叶龄（叶）">
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_2]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_2]"></el-input>
                            </template>
                        </el-form-item>
                        <el-form-item label="基本苗（万亩）">
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_3]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_3]"></el-input>
                            </template>
                        </el-form-item>
                        <el-form-item label="亩穴数（万）">
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_4]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_4]"></el-input>
                            </template>
                        </el-form-item>
                    </template>
                    <template v-if="currentStep == 2||currentStep == 1||currentStep == 3||currentStep == 4||currentStep == 5||currentStep == 6||currentStep == 7||currentStep == 8 || currentStep == 9">
                        
                        <el-form-item label="日期" >
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-date-picker
                                v-model="tab4DataList[0][tab4_1]"
                                align="right"
                                type="date" readonly
                                >
                                </el-date-picker>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-date-picker
                                v-model="tab4DataList[0][tab4_1]"
                                align="right"
                                type="date"
                                >
                                </el-date-picker>
                            </template>
                        </el-form-item>
                        <el-form-item label="生育进程">
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_2]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_2]"></el-input>
                            </template>
                        </el-form-item>
                        <el-form-item label="叶龄（叶）">
                            <template v-if="currentStep < tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_3]" readonly></el-input>
                            </template>
                            <template v-else="currentStep >= tab4CurrentStep">
                                <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_3]"></el-input>
                            </template>
                        </el-form-item>
                        <template v-if="currentStep == 8 || currentStep == 9">
                            <el-form-item label="穗数(万/亩)">
                                <template v-if="currentStep < tab4CurrentStep">
                                    <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_4]" readonly></el-input>
                                </template>
                                <template v-else="currentStep >= tab4CurrentStep">
                                    <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_4]"></el-input>
                                </template>
                            </el-form-item>
                        </template>
                        <template v-if="currentStep == 2||currentStep == 1||currentStep == 3||currentStep == 4||currentStep == 5||currentStep == 6||currentStep == 7">
                            <el-form-item label="茎蘖数(万/亩)">
                                <template v-if="currentStep < tab4CurrentStep">
                                    <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_4]" readonly></el-input>
                                </template>
                                <template v-else="currentStep >= tab4CurrentStep">
                                    <el-input style="width: 220px;" v-model="tab4DataList[0][tab4_4]"></el-input>
                                </template>
                            </el-form-item>
                        </template>
                    </template>
                </template>
                </el-form>
            <!-- </template> -->
          </el-card>

          <template v-if="currentStep != 0">
          <!-- <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)水稻苗情评价动态列表(移栽)</span></div> -->
          <!-- <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <template v-if="currentStep <= tab2CurrentStep">
                <template v-if="tab2DataList.length > 0">
                <el-form :inline="true" ref="form"  label-width="180px">
                    <el-form-item label="日期" >
                        <el-date-picker
                        v-model="tab2DataList[0][tab2_1]"
                        align="right"
                        type="date"
                        >
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="一类苗（万亩）">
                        <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_2_1]"></el-input>
                    </el-form-item>
                    <el-form-item label="二类苗（万亩）">
                        <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_3_1]"></el-input>
                    </el-form-item>
                    <el-form-item label="三类苗（万亩）">
                        <el-input style="width: 220px;" v-model="tab2DataList[0][tab2_4_1]"></el-input>
                    </el-form-item>
                    </el-form>
                </template>
            </template>
          </el-card> -->
          
          <!-- <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)水稻苗情评价动态列表(直播)</span></div> -->
          <!-- <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <template v-if="currentStep <= tab3CurrentStep">
                <template v-if="tab3DataList.length > 0">
                    <el-form :inline="true" ref="form" label-width="180px">
                    <el-form-item label="日期" >
                        <el-date-picker
                        v-model="tab3DataList[0][tab3_1]"
                        align="right"
                        type="date"
                        placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="一类苗（万亩）">
                        <el-input style="width: 220px;" v-model="tab3DataList[0][tab3_2_1]"></el-input>
                    </el-form-item>
                    <el-form-item label="二类苗（万亩）">
                        <el-input style="width: 220px;" v-model="tab3DataList[0][tab3_3_1]"></el-input>
                    </el-form-item>
                    <el-form-item label="三类苗（万亩）">
                        <el-input style="width: 220px;" v-model="tab3DataList[0][tab3_4_1]"></el-input>
                    </el-form-item>
                    </el-form>
                </template>
            </template>
          </el-card> -->

          <!-- <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)水稻播栽进度调查表</span></div> -->
          <!-- <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <template v-if="currentStep <= tab1CurrentStep">
                <template v-if="tab1DataList.length > 0">
                        <el-form :inline="true" ref="form" label-width="180px">
                            <el-form-item label="日期" >
                                <el-date-picker
                                v-model="tab1DataList[0][tab1_1]"
                                align="right"
                                type="date"
                                placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                            <template v-if="currentStep < 4">
                                <el-form-item label="育秧已播种面积（万亩）">
                                    <el-input style="width: 220px;" v-model="tab1DataList[0][tab1_2_1]"></el-input>
                                </el-form-item>
                                <el-form-item label="已栽插面积（万亩）">
                                    <el-input style="width: 220px;" v-model="tab1DataList[0][tab1_3]"></el-input>
                                </el-form-item>
                                <el-form-item label="直播已播种面积（万亩）">
                                    <el-input style="width: 220px;" v-model="tab1DataList[0][tab1_4]"></el-input>
                                </el-form-item>
                            </template>
                            <template v-if="currentStep == 4">
                                <el-form-item label="已栽插面积（万亩）">
                                    <el-input style="width: 220px;" v-model="tab1DataList[0][tab1_2]"></el-input>
                                </el-form-item>
                                <el-form-item label="直播已播种面积（万亩）">
                                    <el-input style="width: 220px;" v-model="tab1DataList[0][tab1_3]"></el-input>
                                </el-form-item>
                            </template>
                            
                        </el-form>
                </template>
            </template>
           </el-card> -->

           <!-- <div style="margin-left: 1%;margin-top: 15px;margin-bottom: 1%;"><i class="el-icon-s-flag" style="color: #8B2323;"></i><span style="color:#5C5C5C;margin-left: 10px;font-size: 17px;">年度监测县(市、区)中稻生育进度调查表（直播稻）</span></div> -->
          <!-- <el-card style="width: 98%;margin-left: 1%;box-shadow: 0 0 7px #ccffcc;margin-bottom: 10px;">
            <template v-if="currentStep <= tab5CurrentStep">
                <template v-if="tab5DataList.length > 0">
                    <el-form :inline="true" ref="form" :model="form" label-width="180px">
                    <el-form-item label="日期" >
                        <el-date-picker
                        v-model="tab5DataList[0][tab5_1]"
                        align="right"
                        type="date"
                        placeholder="选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="生育进程">
                            <el-input style="width: 220px;"  v-model="tab5DataList[0][tab5_2]"></el-input>
                        </el-form-item>
                        <el-form-item label="叶龄（叶）">
                            <el-input style="width: 220px;"  v-model="tab5DataList[0][tab5_3]"></el-input>
                        </el-form-item>
                    <template v-if="tab5CurrentStep == 0">
                        
                        <el-form-item label="基本苗（万亩）">
                            <el-input style="width: 220px;"  v-model="tab5DataList[0][tab5_4]"></el-input>
                        </el-form-item>
                    </template>
                    <template v-if="tab5CurrentStep != 0">
                        <el-form-item label="茎蘖数(万/亩)">
                            <el-input style="width: 220px;"  v-model="tab5DataList[0][tab5_4]"></el-input>
                        </el-form-item>
                    </template>
                    </el-form>                
                </template>
            </template>
          </el-card> -->
        </template>
        </div>
        <div style="margin-top: 2.3%;float: right;margin-right: 17%;">
            <template v-if="currentStep >= tab4CurrentStep">
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
        currentStep:1,//当前进程数
        maxStep:1,//最大进程数
        tab1CurrentStep:0,//表1-  3-1当前进程,最大是4
        tab2CurrentStep:0,//表2   3-2当前进程，最大是8
        tab3CurrentStep:0,//表3   3-3当前进程，最大是9
        tab4CurrentStep:0,//表4    4-1当前进程，最大是10，进程1之前先判断 ‘移栽期’填过没有
        tab5CurrentStep:0,//表5    4-2当前进程，最大是9
        currentTab:'tab1',//当前所在表格
        tab1DataList:[],//保存表1 - 年度监测县(市、区)水稻播栽进度调查表 的数据
        tab2DataList:[],//保存表2 - 年度监测县(市、区)水稻苗情评价动态列表(移栽) 的数据
        tab3DataList:[],//保存表3 - 年度监测县(市、区)水稻苗情评价动态列表(直播) 的数据
        tab4DataList:[],//保存表4 - 年度监测县(市、区)中稻生育进度调查表（移栽稻） 的数据
        tab5DataList:[],//保存表5 - 年度监测县(市、区)中稻生育进度调查表（直播稻） 的数据
        tab4_1:'',//表4的字段绑定
        tab4_2:'',
        tab4_3:'',
        tab4_4:'',
        tab5_1:'',//表5的字段绑定
        tab5_2:'',
        tab5_3:'',
        tab5_4:'',
        tab3_1:'',//表3的字段绑定
        tab3_2_1:'',
        tab3_2_2:'',
        tab3_3_1:'',
        tab3_3_2:'',
        tab3_4_1:'',
        tab3_4_2:'',
        tab2_1:'',//表2的字段绑定
        tab2_2_1:'',
        tab2_2_2:'',
        tab2_3_1:'',
        tab2_3_2:'',
        tab2_4_1:'',
        tab2_4_2:'',
        tab1_1:'',//表1的字段绑定
        tab1_2_1:'',
        tab1_2_2:'',
        tab1_2:'',
        tab1_3:'',
        tab1_4:'',
        tab1_5:'',
        tab1_6:'',
        // 步骤标题
      stepTitle: ['移栽期', '进程1', '进程2', '进程3','进程4','进程5','进程6','进程7'],
        form: {
            name: '',
            region: '',
            date1: '',
            date2: '',
            delivery: false,
            type: [],
            resource: '',
            desc: ''
          }
      };
    },
    methods: {
        test(){
            //console.log(this.tab1DataList);
            //console.log(this.tab2DataList);
            //console.log(this.tab3DataList);
            //console.log(this.tab4DataList);
            //console.log(this.tab5DataList);
        },
        //上报
        getFormList(e){
            var date = this.getNowTime();
            for(var i = 0 ;i < this.tab4DataList.length ; ++i){
            this.tab4DataList[i].modification_time = date;
            }
            var list = this.tab4DataList;
            var str = '';
            for(var i = 0 ; i < list.length ; ++i){
                str += JSON.stringify(list[i]) + "#";
            }
            //console.log(this.tab4DataList);
            //console.log(str);
            this.saveInputData(str);
        },
        //生成保存函数
        saveInputData(list){
            axios({
            url:'/getSituationInputDataTableTwo0401',
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
            //console.log(this.maxStep);
            //如果index > currentStep ，不允许跳转
            if(index > this.maxStep){
                this.currentStep = this.maxStep;
                return this.$message.info("该进程尚未开启！");
            }
            if(index <= this.maxStep){
                this.currentStep = index;
                //修改各表对应的进程下的字段绑定
                //4-1
                if(this.currentStep <= this.tab4CurrentStep){
                    // var gap = 8 + this.tab4CurrentStep;
                    var gap4 = 8 + index;//表4-1 的进程绑定 OK
                    this.tab4_1 = "field_"+gap4+"_1";
                    this.tab4_2 = "field_"+gap4+"_2";
                    this.tab4_3 = "field_"+gap4+"_3";
                    this.tab4_4 = "field_"+gap4+"_4";
                }

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
                    }
                }

                if(this.currentStep <= this.tab2CurrentStep){
                    var gap2 = 3 + index;//表3-2 的进程绑定
                    this.tab2_1 = "field_"+gap2+"_1";
                    this.tab2_2_1= "field_" + gap2 + "_2_1";
                    this.tab2_2_2 = "field_"+gap2+"_2_2";
                    this.tab2_3_1= "field_" + gap2 + "_3_1";
                    this.tab2_3_2 = "field_"+gap2+"_3_2";
                    this.tab2_4_1= "field_" + gap2 + "_4_1";
                    this.tab2_4_2 = "field_"+gap2+"_4_2";
                }

                if(this.currentStep <= this.tab3CurrentStep){
                    var gap3 = 4 + index;//表3-3 的进程绑定 OK
                    this.tab3_1 = "field_"+gap3+"_1";
                    this.tab3_2_1= "field_" + gap3 + "_2_1";
                    this.tab3_2_2 = "field_"+gap3+"_2_2";
                    this.tab3_3_1= "field_" + gap3 + "_3_1";
                    this.tab3_3_2 = "field_"+gap3+"_3_2";
                    this.tab3_4_1= "field_" + gap3 + "_4_1";
                    this.tab3_4_2 = "field_"+gap3+"_4_2";
                }

                if(this.currentStep <= this.tab5CurrentStep){
                    var gap5 = 8 + index;//表4-2 的进程绑定
                    this.tab5_1 = "field_"+gap5+"_1";
                    this.tab5_2 = "field_"+gap5+"_2";
                    this.tab5_3 = "field_"+gap5+"_3";
                    this.tab5_4 = "field_"+gap5+"_4";
                }
                    //console.log(this.tab2_1+":"+this.tab2_2_1+":"+this.tab2_2_2+":"+this.tab2_3_1+":"+this.tab2_3_2+":"+this.tab2_4_1+":"+this.tab2_4_2);

            }
           
        },
      //获取表1 - 年度监测县(市、区)水稻播栽进度调查表 的当前进程
      getTab1CurrentStep(people){
        axios({
            url:'/getUserAllData0301',
            method:'post',
            params:{
                people:people,
                column_id:'',
            }
        }).then(
            res=>{
                this.tab1DataList = res.data;
                if(this.tab1DataList.length > 0){
                    for(var j = 0 ; j < 4 ; ++j){
                        var start = 9+j;
                        var str = "field_"+start+"_1";
                        if(this.tab1DataList[0][str] !=null && this.tab1DataList[0][str] !=''){
                            this.tab1CurrentStep += 1;
                        }else{
                            break;
                        }
                    }  
                }
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
                //console.log("3-1:"+this.tab1CurrentStep);
            }
        ).catch(err=>{
            return this.$message.error("获取播载进度失败！");
        }
        );
      },
      //获取表2 - 年度监测县(市、区)水稻苗情评价动态列表(移栽) 的当前进程
      getTab2CurrentStep(people){
        axios({
            url:'/getUserAllData0302',
            method:'post',
            params:{
                people:people,
                column_id:'',
            }
        }).then(
            res=>{
                //console.log(res.data);
                this.tab2DataList = res.data;
                if(this.tab2DataList.length > 0){
                    for(var j = 0 ; j < 8 ; ++j){
                        var start = 4+j;
                        var str = "field_"+start+"_1";
                        if(this.tab2DataList[0][str] !=null && this.tab2DataList[0][str] !=''){
                            this.tab2CurrentStep += 1;
                        }else{
                            break;
                        }
                    }  
                }
                var gap = 4 + this.tab2CurrentStep;
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
                this.maxStep = this.currentStep;
                //console.log("3-2:"+this.tab2CurrentStep);
            }
        ).catch(err=>{
            return this.$message.error("获取苗情动态进程失败！");
        }
        );
      },
      //获取表3 - 年度监测县(市、区)水稻苗情评价动态列表(直播) 的当前进程
      getTab3CurrentStep(people){
        axios({
            url:'/getUserAllData0303',
            method:'post',
            params:{
                people:people,
                column_id:'',
            }
        }).then(
            res=>{
                //console.log(res.data);
                this.tab3DataList = res.data;
                if(this.tab3DataList.length > 0){
                    for(var j = 0 ; j < 9 ; ++j){
                        var start = 4+j;
                        var str = "field_"+start+"_1";
                        if(this.tab3DataList[0][str] !=null && this.tab3DataList[0][str] !=''){
                            this.tab3CurrentStep += 1;
                        }else{
                            break;
                        }
                    }  
                }
                var gap = 4 + this.tab3CurrentStep;
                this.tab3_2_1= "field_" + gap + "_2_1";
                this.tab3_2_2 = "field_"+gap+"_2_2";
                this.tab3_3_1= "field_" + gap + "_3_1";
                this.tab3_3_2 = "field_"+gap+"_3_2";
                this.tab3_4_1= "field_" + gap + "_4_1";
                this.tab3_4_2 = "field_"+gap+"_4_2";
                if(this.tab3CurrentStep > this.currentStep){
                    this.currentStep = this.tab3CurrentStep;
                }
                this.maxStep = this.currentStep;
                //console.log("3-3:"+this.tab3CurrentStep);
            }
        ).catch(err=>{
            return this.$message.error("获取苗情动态进程失败！");
        }
        );
      },
      //获取表4 - 年度监测县(市、区)中稻生育进度调查表（移栽稻） 的当前进程
      getTab4CurrentStep(people,point){
        axios({
            url:'/getUserAllDataTwo0401',
            method:'post',
            params:{
                people:people,
                column_id:'',
                monitor_location:point,
            }
        }).then(
            res=>{
                this.tab4DataList = [];
                for(var i = 0 ; i < res.data.length ; ++i){
                    this.tab4DataList.push(res.data[i]);
                }
                this.tab4DataList = res.data;
                if(this.tab4DataList.length > 0){
                    for(var j = 0 ; j < 10 ; ++j){
                        var start = 8+j;
                        var str = "field_"+start+"_1";
                        if(this.tab4DataList[0][str] !=null && this.tab4DataList[0][str] !=''){
                            this.tab4CurrentStep += 1;
                        }else{
                            break;
                        }
                    }  
                }
                var gap = 8 + this.tab4CurrentStep;
                this.tab4_1 = "field_"+gap+"_1";
                this.tab4_2 = "field_"+gap+"_2";
                this.tab4_3 = "field_"+gap+"_3";
                this.tab4_4 = "field_"+gap+"_4";
                if(this.tab4CurrentStep > this.currentStep){
                    this.currentStep = this.tab4CurrentStep;
                }
                if(this.tab4CurrentStep == 10){
                    this.currentStep = this.tab4CurrentStep-1;
                    gap = gap -1;
                    this.tab4_1 = "field_"+gap+"_1";
                    this.tab4_2 = "field_"+gap+"_2";
                    this.tab4_3 = "field_"+gap+"_3";
                    this.tab4_4 = "field_"+gap+"_4";
                }
                this.maxStep = this.currentStep;
                //console.log(this.tab4CurrentStep);
                //console.log(this.tab4_1+":"+this.tab4_2+":"+this.tab4_3+":"+this.tab4_4);
                //console.log("4-1:"+this.tab4CurrentStep);
            }
        ).catch(err=>{
            return this.$message.error("获取中稻进度失败！");
        }
        );
      },

    },
    created(){
        var people = localStorage.getItem('ms_username');
        var point = this.$route.params.point;
        // this.getTab1CurrentStep(people);
        // this.getTab2CurrentStep(people);
        // this.getTab3CurrentStep(people);
        this.getTab4CurrentStep(people,point);
        // this.getTab5CurrentStep(people);

    },
  };
  </script>
  <style>

  </style>