<template>
  
    <div class="totalRange">
      <div class="tableRange">
        <div class="tableRange_left">
          <el-card class="box-card">
            <div slot="header" class="notice">
              <span>工作进度</span>
            </div>
            <div class="textAudit">
              <div class="text">
                <span>当前未审核数据：</span>
                <span v-if="this.schedule != 0">
                  <span>一季稻共</span>
                  <span style="color:#ff0033">{{schedule}}</span>
                  <span>条；</span>
                </span>
                <span v-if="this.schedule2 != 0">
                  <span>双季稻共</span>
                  <span style="color:#ff0033">{{schedule2}}</span>
                  <span>条</span>
                </span>
                <span v-if="this.schedule == 0 && this.schedule2 ==0">暂无数据审核</span>
                <!-- <span>当前监测点申请：</span>
                <span v-if="this.applyPoint != 0">
                  <span>一季稻共</span>
                  <span style="color:#ff0033">{{applyPoint}}</span>
                  <span>个；</span>
                </span>
                <span v-if="this.applyPoint2 != 0">
                  <span>双季稻共</span>
                  <span style="color:#ff0033">{{applyPoint2}}</span>
                  <span>个</span>
                </span>
                <span v-if="this.applyPoint == 0 && this.applyPoint2 ==0">暂无监测点申请</span> -->
              </div>
              <div class="auditButtom">
                <el-button type="info" size="medium"  icon="el-icon-search" @click="auditData"> 数 据 审 核</el-button>
                <!-- <el-button type="info" size="medium"  icon="el-icon-search" @click="auditPoint">监测点审核</el-button> -->
              </div>
            </div>
            <div class="textAudit1">
              <div class="text1">
                <span>当前未审核数据：</span>
                <span v-if="this.schedule != 0">
                  <span>一季稻共</span>
                  <span style="color:#ff0033">{{schedule}}</span>
                  <span>条；</span>
                </span>
                <span v-if="this.schedule2 != 0">
                  <span>双季稻共</span>
                  <span style="color:#ff0033">{{schedule2}}</span>
                  <span>条</span>
                </span>
                <span v-if="this.schedule == 0 && this.schedule2 ==0">暂无数据审核</span>
              </div>
              <div class="auditButtom1">
                <el-button type="info" size="medium"  icon="el-icon-search" @click="auditData"> 数 据 审 核</el-button>
              </div>
            </div>
            <div class="textAudit2">
              <div class="text2">
                <span>当前数据未上报表个数：</span>
                <span v-if="this.submitData != 0">
                  <span>一季稻共</span>
                  <span style="color:#ff0033">{{submitData}}</span>
                  <span>个；</span>
                </span>
                <span v-if="this.submitData2 != 0">
                  <span>双季稻共</span>
                  <span style="color:#ff0033">{{submitData2}}</span>
                  <span>个</span>
                </span>
                <span v-if="this.submitData == 0 && this.submitData2 ==0">暂无未上报数据</span>
              </div>
              <div class="auditButtom2">
                <el-button type="info" size="medium"  icon="el-icon-search" @click="reportNotice"> 未上报查看</el-button>
              </div>
            </div>
            <div class="notice-table">
              <!-- <span>县(市、区)未审核数目</span> -->
              <div class="notice-table1">
                <el-table :data="cityData1" :header-cell-style="{background:'#5e616d',color:'#FFFFFF',fontSize:'13px'}" :row-style="{height: '30px'}" :cell-style="cellStyle"  row-key="id" stripe>
                    <el-table-column align="center" prop="cityName" label="县(市、区)">
                    </el-table-column>
                    <el-table-column align="center" prop="cityNumber" width="120%" label="未审核数（条）">
                    </el-table-column>
                </el-table>
              </div>
              <div class="notice-table2">
                <el-table :data="cityData2" :header-cell-style="{background:'#5e616d',color:'#FFFFFF',fontSize:'13px'}" :row-style="{height: '30px'}" :cell-style="cellStyle2" row-key="id" stripe>
                    <el-table-column align="center" prop="cityName2" label="县(市、区)">
                    </el-table-column>
                    <el-table-column align="center" prop="cityNumber2" width="120%" label="未审核数（条）">
                    </el-table-column>
                </el-table>
              </div>
              
              <div class="notice-table3">
                <el-table :data="cityData3" :header-cell-style="{background:'#5e616d',color:'#FFFFFF',fontSize:'13px'}" :row-style="{height: '30px'}" :cell-style="cellStyle3" row-key="id" stripe>
                    <el-table-column align="center" prop="cityName3" label="县(市、区)">
                    </el-table-column>
                    <el-table-column align="center" prop="cityNumber3" width="120%" label="未审核数（条）">
                    </el-table-column>
                </el-table>
              </div>
            </div>
          </el-card>

        </div>
        <div class="tableRange_right">
          <div class="right_top" >
            <div class="top_right">
              <el-card shadow="hover" class="box-card">
                <div class="card1">
                  <el-select v-model="areaType"  clearable  placeholder="地区类型" class="select1" style="width:120px;height:50px">
                    <el-option
                      v-for="item in areaTypes"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>

                  <el-select v-model="county" clearable  style="width:130px; margin-left: 30px;height:50px" placeholder="县（市、区）" class="select2">
                    <el-option
                      v-for="item in countys"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                  <el-select v-model="year" clearable  style="width:150px; margin-left: 30px;height:50px" placeholder="请选择查看年度" class="select3">
                    <el-option
                      v-for="item in years"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </div>
                <div class="card2">
                  <el-button type="info" style="width:150px;height:40px" icon="el-icon-search" @click="lookData">数据查看</el-button>
                </div>
                <div class="card3">
                  <span style="color:red">注：</span><span>默认数据为本年度所有地区、所有县数据</span>
                </div>
              </el-card>
            </div>
          </div>
          <div class="right_bottom">
            <el-row style="background-color:rgb(230, 225, 225);width: 98%;margin-left:9px">
              <span >生育期概况</span>
            </el-row>
            <div class="process">
              <el-tabs v-model="activeName" @tab-click="handleClick">
                  <el-tab-pane label="一季稻生育期" name="1">
                      <div class="table_content">
                          <div id="crop_01_01" class="process_content"></div>
                      </div>
                  </el-tab-pane>
                  <el-tab-pane label="双季稻生育期" name="2">
                      <div class="table_content">
                          <div id="crop_01_02" class="process_content"></div>
                      </div>
                  </el-tab-pane>
              </el-tabs>
            </div>
          </div>
        </div>
      </div>
      <div class="button">
        <el-button type="success" @click="returnPage">返  回</el-button>
      </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
export default {
  inject: ['reload'],
  data() { 
    return { 
      //季稻选择
      activeName:'1',
      //县的option
      countys:[],
      county: '',
      //地区类型option
      areaTypes:[],
      areaType: '',
      //年份
      years:[{"label":"2021年","value":"2021"},
            {"label":"2020年","value":"2020"},
            {"label":"2019年","value":"2019"},
            {"label":"2018年","value":"2018"},
            {"label":"2017年","value":"2017"},],
      year:'',
      //未审核数目
      oneData:[],
      twoData:[],
      oneCounty:[],
      twoCounty:[],
      //一季稻未审核条数
      schedule:0,
      //双季稻未审核条数
      schedule2:0,
      //申请监测点数
      applyPoint:0,
      applyPoint2:0,
      //未上报数据的县个数
      submitData:0,
      submitData2:0,
      //未审核数据县数
      cityData1:[],
      cityData2:[],
      cityData3:[],
    };
  },
  created() {
    this.initData();
    
  },
  mounted(){
    this.crop_01_01Analysis();
    
  },
  methods: {
    //初始化数据
    initData() {
      this.getAllAuditData();
      this.getAllAuditData2();
      this.getTableData1();
      this.getTableData2();
      this.getCountySelection();
      //this.getNoAuditData();
      this.getPoint();
      this.getPoint2();
      this.getReportData();
      //this.getReportData2();
      this.county='';
      this.areaType='';
      this.year='';
    }, 

    getAllAuditData(){
      axios({
          url: '/getAllAuditData',
          method:'get',
       }).then(res=>{   
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getAllAuditData2(){
      axios({
          url: '/getAllAuditData2',
          method:'get',
       }).then(res=>{   
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取一季表数据
    getTableData1(){
       const _this = this;
       axios({
          url: '/getNoAuditAllData',
          method:'get',
          params:{}
       }).then(res=>{
          var temp=res.data;
          _this.oneData=temp;
          var num1=temp.length;
          _this.schedule=num1;
          ////console.log(_this.oneData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取双季稻数据
    getTableData2(){
       const _this = this;
       axios({
          url: '/getNoAuditAllData2',
          method:'get',
       }).then(res=>{
           var temp=res.data;
           _this.twoData=temp;
           var num1=temp.length;
          _this.schedule2=num1;
          ////console.log(_this.twoData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取下拉框内容
    getCountySelection(){
      const _this = this;
      axios({
          url: '/getCounty1',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          //地区数据
          var areaData = [];
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
                _this.oneCounty[i] = optionData[i].list;
              };
              if(optionData[i].caption === "地区类型"){
                areaData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.getCountySelection1();
          ////console.log(_this.countys);

       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取完整稻作类型下拉框内容
    getCountySelection1(){
      const _this = this;
      axios({
          url: '/getCounty2',
          method:'get',
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                _this.countys.push({"label":optionData[i].list,"value":optionData[i].list});
                _this.twoCounty[i]=optionData[i].list;
              };
          };
          ////console.log(_this.twoCounty);
          _this.getNoAuditData();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //数据查看
    lookData(){
      var myDate = new Date();
      var myYear= myDate.getFullYear();
      var citys=this.county;
      var areas=this.areaType;
      var years='';
      if(this.year==null || this.year==""){
        years = myYear;
      }else{
        years = this.year;
      }
      ////console.log(areas,citys,years);
      if(areas ==''){
        if(citys ==''){
          this.$router.push({path:'/dataHome',query:{area:this.areaType,city:this.county,year:years}});
        }else if((citys=='宣州区') || (citys=='庐江县')){
          this.$router.push({path:'/dataHome2',query:{area:this.areaType,city:this.county,year:years}});
        }else if((citys=='怀宁县') || (citys=='枞阳县')){
          this.$router.push({path:'/dataHome2',query:{area:this.areaType,city:this.county,year:years}});
        }else if((citys=='贵池区')|| (citys=='南陵县')){
          this.$router.push({path:'/dataHome2',query:{area:this.areaType,city:this.county,year:years}});
        }else{
          this.$router.push({path:'/dataHome1',query:{area:this.areaType,city:this.county,year:years}});
        };
      };
      if(areas != ''){
        if(citys ==''){
          this.$router.push({path:'/dataHome',query:{area:this.areaType,city:this.county,year:years}});
        }else if((citys=='宣州区') || (citys=='庐江县')){
          this.$router.push({path:'/dataHome2',query:{area:this.areaType,city:this.county,year:years}});
        }else if((citys=='怀宁县') || (citys=='枞阳县')){
          this.$router.push({path:'/dataHome2',query:{area:this.areaType,city:this.county,year:years}});
        }else if((citys=='贵池区')|| (citys=='南陵县')){
          this.$router.push({path:'/dataHome2',query:{area:this.areaType,city:this.county,year:years}});
        }else{
          this.$router.push({path:'/dataHome1',query:{area:this.areaType,city:this.county,year:years}});
        };
      };
    },
    //跳转数据审核页面
    auditData(){
      this.$router.push('/audit');
    },
    //跳转数据未上报的县页面
    reportNotice(){
      this.$router.push('/submitHome');
    },
    //获取一季稻每个县对应多少条未审核数据
    getNoAuditData(){
       var oneTemp=[];
       var twoTemp=[];

       for(var i=0;i<30;i++){
         var num = 0;
         for(var j=0; j<this.oneData.length;j++){
           if(this.oneCounty[i] === this.oneData[j].county){
             num = parseInt(num)+1;
           }
         }
         oneTemp[i]=num;
       };
       for(var i=0;i<6;i++){
         var num = 0;
         for(var j=0; j<this.twoData.length;j++){
           if(this.twoCounty[i] == this.twoData[j].county){
             num++;
           }
         }
         twoTemp[i]=num;
       };
       ////console.log(oneTemp);
       ////console.log(twoTemp);
       for(var i=0;i<12;i++){
         this.cityData1.push({"cityName":this.oneCounty[i],"cityNumber":oneTemp[i]});
       };
       for(var i=12;i<24;i++){
         this.cityData2.push({"cityName2":this.oneCounty[i],"cityNumber2":oneTemp[i]});
       };
       for(var i=24;i<30;i++){
         this.cityData3.push({"cityName3":this.oneCounty[i],"cityNumber3":oneTemp[i]});
       };
       for(var i=0;i<6;i++){
         this.cityData3.push({"cityName3":this.twoCounty[i],"cityNumber3":twoTemp[i]});
       };
    },
    //跳转到监测点审核页面
    auditPoint(){
      this.$router.push('/auditPoint');
    },
    //一季稻监测点
    getPoint(){
      const _this = this;
       axios({
          url: '/gePointData',
          method:'get',
       }).then(res=>{
           var temp=res.data;
           _this.applyPoint=temp.length;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //双季稻监测点
    getPoint2(){
      const _this = this;
       axios({
          url: '/gePointData2',
          method:'get',
       }).then(res=>{
           var temp=res.data;
           _this.applyPoint2=temp.length;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //未上报县
    getReportData(){
      const _this=this;
      axios({
        url:'/getNoReportData',
        menthod:'get',
      }).then(res=>{
          //console.log(res.data);
          var temp=res.data;
          _this.submitData=res.data.length;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //进程
    crop_01_01Analysis(){
      const _this = this;
       axios({
          url: '/getPeriod',
          method:'get',
       }).then(res=>{
            var list = [];
            for(var i = 0 ; i < res.data.length ; ++i){
                list.push(res.data[i]);
            };
           var data1 = list;
            ////console.log(data1);
            let barechart = this.$echarts.init(document.getElementById("crop_01_01"));
            var option;
            option = {
              title:{
                text:'生育期：监测点个数'
              },
              tooltip: {
                        trigger: 'axis',
                        axisPointer:{type:'shadow'},
                        formatter:function(params){
                          ////console.log(params);
                          var res="";
                          var pointcode="";
                          var name=params[0].seriesName;
                          var value=params[0].value;
                          var pointName=params[0].data.pointName;
                          ////console.log(pointName);
                          if(pointName.length!=0){
                            for(var i=0; i<pointName.length;i++){
                              if(i%3==0 && i!=0){
                                pointcode = pointcode+"<br/>"
                              }
                              pointcode =pointcode +"&nbsp;&nbsp"+pointName[i];
                              
                            };
                            var point=pointcode;
                          }else{
                            var point="";
                          }
                          ////console.log(point);
                          res = "<span>"+name+"</span>:"+value+"<br/>"
                                +point;
                          return res;
                        },
                        },
              toolbox: {
                        show: true,
                        feature: {
                            // dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            //saveAsImage: {show: true}
                        }
                    },
              grid:{
                left:'3%',
                right:'20%',
                bottom:'1%',
                containLabel:true
              },
              calculable: true,
              xAxis: [
                      {
                          type: 'category',
                          axisLabel : {//坐标轴刻度标签的相关设置。
                              interval:0,
                              rotate:"45"
                          },
                          data: ['播种期', '出苗期', '移栽期', '够苗期', '有效分蘗临界叶龄期', '拔节期', '孕穗期', '抽穗期', '齐穗期', '成熟期']
                      }
                  ],
              yAxis: [
                  {
                      type: 'value'
                  }
              ],
              series: [
                  {
                      name: '监测点个数',
                      type: 'bar',
                      data:data1,
                      // data: [{'pointName':['肥东县_001','肥东县_002','肥东县_003','肥西县_001','肥西县_002','肥西县_003','肥西县_004','肥西县_005','望江县_001','望江县_002','望江县_003','望江县_004','休宁县_001','休宁县_002'],'value':'14'},
                      //         {'pointName':['全椒县_001','全椒县_002','全椒县_003','全椒县_004','全椒县_005'],'value':'5'},
                      //         {'pointName':['东至县_001','东至县_002','东至县_003'],'value':'3'},
                      //         {'pointName':['定远县_001','定远县_002'],'value':'2'},
                      //         {'pointName':['无为市_001','无为市_002'],'value':'2'},
                      //         {'pointName':['来安县_001','来安县_002'],'value':'2'},
                      //         {'pointName':['定远县_001'],'value':'1'},
                      //         {'pointName':['巢湖市_001'],'value':'1'},
                      //         {'pointName':[],'value':'0'},
                      //         {'pointName':[],'value':'0'}],
                      barWidth:25,
                      markPoint: {
                                  data: [
                                      {type: 'max', name: '最大值'},
                                      // {type: 'min', name: '最小值'}
                                  ]
                      },
                  },
              ]
            };
            barechart.setOption(option);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
      
          
    },
    crop_01_02Analysis(){
      const _this = this;
       axios({
          url: '/getPeriod2',
          method:'get',
       }).then(res=>{
            var list = [];
            for(var i = 0 ; i < res.data.length ; ++i){
                list.push(res.data[i]);
            };
           var data2=list;
           let barechart = this.$echarts.init(document.getElementById("crop_01_02"));
            var option;
            option = {
                title:{
                  text:'生育期监测点个数'
                },
                tooltip: {
                              trigger: 'axis',
                              axisPointer:{type:'shadow'},
                              formatter:function(params){
                                ////console.log(params);
                                var res="";
                                var pointcode="";
                                var name=params[0].seriesName;
                                var value=params[0].value;
                                var pointName=params[0].data.pointName;
                                ////console.log(pointName);
                                if(pointName.length!=0){
                                  for(var i=0; i<pointName.length;i++){
                                    if(i%3==0 && i!=0){
                                      pointcode = pointcode+"<br/>"
                                    }
                                    pointcode =pointcode +"&nbsp;&nbsp"+pointName[i];
                                    
                                  };
                                  var point=pointcode;
                                }else{
                                  var point="";
                                }
                                ////console.log(point);
                                res = "<span>"+name+"</span>:"+value+"<br/>"
                                      +point;
                                return res;
                              },
                          },
                toolbox: {
                          show: true,
                          feature: {
                              //dataView: {show: true, readOnly: false},
                              magicType: {show: true, type: ['line', 'bar']},
                              restore: {show: true},
                              //saveAsImage: {show: true}
                          }
                      },
                grid:{
                  left:'3%',
                  right:'20%',
                  bottom:'1%',
                  containLabel:true
                },
                calculable: true,
                xAxis: [
                        { 
                            type: 'category',
                            axisLabel : {//坐标轴刻度标签的相关设置。
                                  interval:0,
                                  rotate:"45"
                              },
                            data: ['播种期', '出苗期', '移栽期', '够苗期', '有效分蘗临界叶龄期', '拔节期', '孕穗期', '抽穗期', '齐穗期', '成熟期']
                        }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: '监测点个数',
                        type: 'bar',
                        data:data2,
                        barWidth:25,
                        markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        //{type: 'min', name: '最小值'}
                                    ]
                        },
                    },
                ]
            };
            barechart.setOption(option);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
        
        
    },
    handleClick(tab, event) {
            switch(tab.label){
                case '一季稻生育期':
                    this.crop_01_01Analysis();
                    break;
                case '双季稻生育期':
                    this.crop_01_02Analysis();
                    break;
                
            }
    },
    //返回
    returnPage(){
        this.$router.push("/index");
    },
    //数据高亮显示
    cellStyle({row,column,rowIndex,columnIndex}){
      ////console.log(row,column,rowIndex,columnIndex);
      if(row.cityNumber!=0){
        return {padding:'0',fontSize:'10px',color:'#ff0033'}
      }else{
        return {padding:'0',fontSize:'10px',color:'#000000'}
      }
    },
    cellStyle2({row,column,rowIndex,columnIndex}){
      ////console.log(row,column,rowIndex,columnIndex);
      if(row.cityNumber2!=0){
        return {padding:'0',fontSize:'10px',color:'#ff0033'}
      }else{
        return {padding:'0',fontSize:'10px',color:'#000000'}
      }
    },
    cellStyle3({row,column,rowIndex,columnIndex}){
      ////console.log(row,column,rowIndex,columnIndex);
      if(row.cityNumber3!=0){
        return {padding:'0',fontSize:'10px',color:'#ff0033'}
      }else{
        return {padding:'0',fontSize:'10px',color:'#000000'}
      }
    },
     
      

  },
};
</script>

<style scoped>
  .totalRange{
    width:100%;
    height: 100%;
  }
  .button{
    position: absolute;right: 60px;top: 105px;
    color:black;
    font-weight: 600;
    font-size: 14px;
  }
  .totalRange .tableRange{
    width:85%;
    margin: 0 auto;
  }
  .tableRange .tableRange_left{
    width: 60%;
    height: 700px;
    float: left;
    margin-top: 30px;
     /* margin-left: 30px;  */
    background: rgb(247, 244, 246);
  }
  .tableRange .tableRange_right{
    width: 40%;
    height: 700px;
    float: right;
    margin-top: 30px;
    
  }
  .tableRange_right .right_top{
    width: 100%;
    height: 36%;
    float: left;
    /* margin-top: 20px;
    margin-left: 20px; */
  }
  /* .tableRange_right .right_top .top_left{
    width:40%;
    height: 100%;
    float: left;
    background: rgb(255, 254, 253);
  } */
  .tableRange_right .right_top .top_right{
    width:99%;
    height: 100%;
    float: right;
    /* margin-left: 20px; */
    background: rgb(255, 255, 255);
  }
  .tableRange_right .right_bottom{
    width: 99%;
    height: 60%;
    text-align: center;
    font-size: 20px;
    float: left;
    margin-top: 25px;
    margin-left: 5px;
    /* margin-left: 10px;  */
    background: rgb(255, 255, 255);
  }
  .right_bottom .process{
     width: 98%;
     height: 93%;
     margin-left: 9px;
     margin-top: 0px;
     background: rgb(230, 225, 225);
  }
  .box-card {
    width: 98%;
    background: rgb(230, 225, 225);
    height: 100%;
    margin-left: 8px;
  }
  .box-card .card1{
    width: 99%;
    height: 50%;
    margin-top: 20px;
    margin-left: 20px;
  }
  .box-card .card2{
    width: 99%;
    height: 50%;
    margin-top: 20px;
    margin-left: 20px;
  }
  .box-card .card3{
    width: 99%;
    height: 25px;
    margin-top: 55px;
    margin-left: 10px;
    font-size: 15px;
    text-align: left;
  }
  .notice{
    text-align: center;
    font-size: 30px;
    font-weight: bold;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .textAudit{
    height: 30px;
    width: 100%;
    margin-bottom: 20px;
  }
  .textAudit .text {
    font-size: 20px;
    text-align: center;
    height: 25px;
    width: 80%;
    float: left;
  }
  .textAudit .auditButtom{
    height: 20px;
    width: 20%;
    float: right;
  }
  .textAudit1{
    height: 30px;
    width: 100%;
    margin-bottom: 20px;
  }
  .textAudit1 .text1 {
    font-size: 20px;
    text-align: center;
    height: 25px;
    width: 80%;
    float: left;
  }
  .textAudit1 .auditButtom1{
    height: 20px;
    width: 20%;
    float: right;
  }
   .textAudit2{
    height: 30px;
    width: 100%;
    margin-bottom: 20px;
  }
  .textAudit2 .text2 {
    font-size: 20px;
    text-align: center;
    height: 25px;
    width: 80%;
    float: left;
  }
  .textAudit2 .auditButtom2{
    height: 20px;
    width: 20%;
    float: right;
  }
  .textNotice{
    margin-top: 20px;
    height: 35px;
    width: 100%;
    margin-bottom: 5px;
  }
  .textNotice .text2 {
    margin-top: 10px;
    font-size: 15px;
    text-align: left;
    height: 20px;
    width: 80%;
    float: left;
  }
 .notice-table{
    margin-top: 30px;
    text-align: center;
    height: 95%;
    width: 100%;
    background: rgb(255, 255, 255);
  }
   .notice-table1{
    margin-right: 0.02%;
    width: 33.3%;
    float: left;
  }
   .notice-table2{
    margin-right: 0.03%;
    width: 33.3%;
    float: left;
  }
  .notice-table3{
    margin-left: 0.03%;
    width: 33.3%;
    float: right;
  }
  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .process .table_content{
    width: 95%;
    height: 95%;
}
.table_content .process_content{
  width: 600px;
  height: 300px;
}
</style>



