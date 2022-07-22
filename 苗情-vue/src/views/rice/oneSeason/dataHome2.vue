<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="双季稻" name="1">
          <div class="tableRange">
            <el-table :data="tableData2" @cell-click="handleCell2" :header-cell-style="{background:'#5e616d',color:'#FFFFFF',fontSize:'14px'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'12px',color:'#000000',cursor:'pointer'}" row-key="id" stripe>
                  <el-table-column align="center" prop="table_01" label="生产概况" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_02" label="监测点建档卡" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_03" label="面上生产调度" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_04" label="监测点生产进度调查" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_05" label="叶片参数自动测定" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_06" label="监测点田管记录">
                  </el-table-column>
                  <el-table-column align="center" prop="table_07" label="测产相关进度监测" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_08" label="生产效益调查" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_09" width="190%" label="气象及自然灾害调查" >
                  </el-table-column>
                  <el-table-column align="center" prop="table_10" label="监测点灌浆速率调查表" >
                  </el-table-column>
            </el-table>
          </div>
          <div class="otherStyle">
            <el-collapse  accordion>
              <el-collapse-item style="">
                <template slot="title">
                  <span style="margin-left:15px;font-size:15px;font-weight:bold"><el-button type="info" plain>表格数据导出Excel</el-button></span>
                </template>
                <div class="checkBox">
                  <el-checkbox style="margin-left:15px" :indeterminate="isIndeterminate2" v-model="checkAll2" @change="handleCheckAllChange2">全选</el-checkbox>
                  <div style="margin:15px 0;margin-left:15px">
                    <el-checkbox-group v-model="checkedCities2" @change="handleCheckedCitiesChange2">
                      <el-checkbox v-for="city2 in cities2" :label="city2" :key="city2" style="width:250px; margin-left: 10px;">{{city2}}</el-checkbox>
                    </el-checkbox-group> 
                  </div>
                </div>
                <div style="margin-top:10px;margin-left:20px">
                  <el-button type="info" @click="getExcel2"> 导出 Excel </el-button>
                </div>
              </el-collapse-item>
            </el-collapse>
            <!-- <el-card class="box-card">
              <div slot="header" class="cardHeader">
                 <span>表格数据导出Excel</span>
              </div>
              <div class="checkBox">
                <el-checkbox :indeterminate="isIndeterminate2" v-model="checkAll2" @change="handleCheckAllChange2">全选</el-checkbox>
                <div style="margin:15px 0;">
                <el-checkbox-group v-model="checkedCities2" @change="handleCheckedCitiesChange2">
                  <el-checkbox v-for="city2 in cities2" :label="city2" :key="city2" style="width:270px; margin-left: 10px;">{{city2}}</el-checkbox>
                </el-checkbox-group> 
                </div>
              </div>
              <div style="margin-top:10px">
                <el-button type="info" @click="getExcel2"> 导出 Excel </el-button>
              </div>
            </el-card> -->
          </div>
        </el-tab-pane>
      </el-tabs>
      <div class="button">
        <el-button type="success" @click="returnPage">返  回</el-button>
      </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
const cityOptions2 = ['水稻栽插概况调查表', '水稻生产主推品种调查表', '水稻监测点汇总表' ,'水稻播栽进度调查表（早稻）' ,'水稻播栽进度调查表（晚稻）' ,'水稻苗情评价动态列表（早稻-移栽稻）' ,
                      '水稻苗情评价动态列表（早稻-直播稻）' ,'水稻苗情评价动态列表（晚稻-移栽稻）' ,'水稻苗情评价动态列表（晚稻-直播稻）' ,'中稻生育进度调查表（移栽稻）' ,'直播稻生育进度调查表（直播稻）' ,
                      '水稻监测点茎蘖数叶面积构成表' ,'水稻监测点苗情生育期统计表' ,'叶片含氮测定仪导出数据' ,'水稻监测点水分管理记录表' ,'水稻监测点化除管理记录表' ,'水稻监测点病虫害防治记录表' ,
                      '水稻监测点施肥管理记录表' ,'水稻监测点化学调节剂使用记录表' ,'水稻双季稻苗情监测预测产表' ,'水稻双季稻收获进度调查（早稻）' ,'水稻双季稻收获进度调查（晚稻）' ,'水稻双季稻测产表' ,
                      '水稻生产成本效益统计表（早稻）' ,'水稻生产成本效益统计表（晚稻）' ,'气象资料列表' ,'抽穗期前、后各15天气象资料' ,'水稻倒伏灾害表' ,'水稻低温冷害表','水稻高温热害表' ,'水稻涝害调查表' ,
                      '水稻监测点洪涝灾害表', '水稻监测点灌浆速率调查表（早稻）', '水稻监测点灌浆速率调查表（晚稻）'];

export default {
  data() { 
    return { 
      activeName:'1', 
      tableData2: [{table_01: '监测县(市、区)水稻栽插概况调查表', table_02: '水稻监测点汇总表',table_03: '监测县(市、区)水稻播栽进度调查表（早稻）', table_04: '监测县(市、区)中稻生育进度调查表（移栽稻）',
                    table_05: '叶片含氮测定仪导出数据',table_06: '水稻监测点水分管理记录表',table_07: '监测县(市、区)水稻双季稻苗情监测预测产表',table_08: '监测县(市、区)水稻生产成本效益统计表（早稻）',
                    table_09: '监测县(市、区)气象资料列表',table_10: '水稻监测点灌浆速率调查表（早稻）'}, 
                  { table_01: '监测县(市、区)水稻生产主推品种调查表', table_03: '监测县(市、区)水稻播栽进度调查表（晚稻）',table_04: '监测县(市、区)直播稻生育进度调查表（直播稻）',
                    table_06: '水稻监测点化除管理记录表',table_07: '监测县(市、区)水稻双季稻收获进度调查（早稻）',table_08: '监测县(市、区)水稻生产成本效益统计表（晚稻）',
                    table_09: '监测县(市、区)抽穗期前、后各15天气象资料',table_10: '水稻监测点灌浆速率调查表（早稻）'},
                  { table_03: '监测县(市、区)水稻苗情评价动态列表（早稻-移栽稻）',table_04: '水稻监测点茎蘖数叶面积构成表', table_06: '水稻监测点病虫害防治记录表',
                    table_07: '监测县(市、区)水稻双季稻收获进度调查（晚稻）',table_09: '监测县(市、区)水稻倒伏灾害表'},
                  { table_03: '监测县(市、区)水稻苗情评价动态列表（早稻-直播稻）',table_04: '水稻监测点苗情生育期统计表',table_06: '水稻监测点施肥管理记录表',
                    table_07: '监测县(市、区)水稻双季稻测产表',table_09: '监测县(市、区)水稻低温冷害表'},
                  { table_03: '监测县(市、区)水稻苗情评价动态列表（晚稻-移栽稻）',table_06: '水稻监测点化学调节剂使用记录表', table_09: '监测县(市、区)水稻高温热害表'},
                  { table_03: '监测县(市、区)水稻苗情评价动态列表（晚稻-直播稻）',table_09: '监测县(市、区)水稻涝害调查表'},
                  { table_09: '水稻监测点洪涝灾害表'},
                  ],
                  
      //暂存参数
      diqu:'',
      xian:'',
      nian:'',
      //双季稻导出选择
      checkAll2: false,
      checkedCities2: [],
      cities2: cityOptions2,
      isIndeterminate2: true,
      //表中数据
      tableDataTwo11: [],
      tableDataTwo12: [],
      tableDataTwo22: [],
      tableDataTwo31: [],
      tableDataTwo32: [],
      tableDataTwo33: [],
      tableDataTwo34: [],
      tableDataTwo35: [],
      tableDataTwo36: [],
      tableDataTwo41: [],
      tableDataTwo42: [],
      tableDataTwo43: [],
      tableDataTwo44: [],
      tableDataTwo51: [],
      tableDataTwo61: [],
      tableDataTwo62: [],
      tableDataTwo63: [],
      tableDataTwo64: [],
      tableDataTwo65: [],
      tableDataTwo71: [],
      tableDataTwo72: [],
      tableDataTwo73: [],
      tableDataTwo74: [],
      tableDataTwo81: [],
      tableDataTwo82: [],
      tableDataTwo91: [],
      tableDataTwo92: [],
      tableDataTwo93: [],
      tableDataTwo94: [],
      tableDataTwo95: [],
      tableDataTwo96: [],
      tableDataTwo97: [],
      tableDataTwo101: [],
      tableDataTwo102: [],
      //本年度县
      currentCounty2:[],
      //年度
      currentYear:'',   //本年度
      previousYear:'',   //上年度 
      //双季稻县
      countys2:[],
      plantWays2:[],

    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.diqu = this.$route.query.area;
      this.xian = this.$route.query.city;
      this.nian = this.$route.query.year;
      this.currentYear = this.$route.query.year;
      this.previousYear = parseInt(this.$route.query.year) - 1;
      ////console.log(this.diqu,this.xian,this.nian);
       //获取表数据
      this.getCountySelection2();
      this.getCurrentCounty2();
      //this.getYear();
      this.getTableDataTwo81();
      this.getTableDataTwo82();
      this.getTableDataTwo11();
      this.getTableDataTwo12();
      this.getTableDataTwo22();
      this.getTableDataTwo31();
      this.getTableDataTwo32();
      this.getTableDataTwo33();
      this.getTableDataTwo34();
      this.getTableDataTwo35();
      this.getTableDataTwo36();
      this.getTableDataTwo41();
      this.getTableDataTwo42();
      this.getTableDataTwo43();
      this.getTableDataTwo44();
      this.getTableDataTwo51();
      this.getTableDataTwo61();
      this.getTableDataTwo62();
      this.getTableDataTwo63();
      this.getTableDataTwo64();
      this.getTableDataTwo65();
      this.getTableDataTwo71();
      this.getTableDataTwo72();
      this.getTableDataTwo73();
      this.getTableDataTwo74();
      this.getTableDataTwo91();
      this.getTableDataTwo92();
      this.getTableDataTwo93();
      this.getTableDataTwo94();
      this.getTableDataTwo95();
      this.getTableDataTwo96();
      this.getTableDataTwo97();
      this.getTableDataTwo101();
      this.getTableDataTwo102();
    }, 
    //切换tabs
    handleClick(tab,event){
      ////console.log(tab,event);
    }, 
    //获取下拉框内容
    getCountySelection2(){
      const _this = this;
      axios({
          url: '/getCounty2',
          method:'get',
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          //栽植数据
          var plantData = [];
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "栽植方式"){
                plantData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          _this.countys2 = countyData;
          _this.plantWays2 = plantData;

       }).catch(err=>{
         //console.log("此处有问题");
         ////return this.$message.error("查询数据失败！");
       });
    },
    //获取当年9-2具体县数据
    getCurrentCounty2(){
      const _this = this;
      axios({
          url: '/getCurrentCounty2',
          method:'post',
          params:{county:this.xian,
                  year:this.nian}
       }).then(res=>{
         ////console.log(res.data);
          _this.currentCounty2 = res.data;
          
       }).catch(err=>{
         //console.log("此处有问题");
         ////return this.$message.error("查询数据失败！");
       });
    },
    //获取年度时间
    getYear(){
      var myDate = new Date();
      this.currentYear = myDate.getFullYear();
      this.previousYear = parseInt(this.currentYear) - 1;
    },
    //双季稻单元格跳转
    handleCell2(row,column,event,cell){
      var areas=this.diqu;
      var citys=this.xian;
      var years=this.nian;
      ////console.log(areas,citys,years);
      ////console.log(row);
      ////console.log(column);
      if(column.label === "生产概况"){
        if(row.table_01 === '监测县(市、区)水稻栽插概况调查表'){
          this.$router.push({path:'/riceTableTwo11',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_01 === '监测县(市、区)水稻生产主推品种调查表'){
          this.$router.push({path:'/riceTableTwo12',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "监测点建档卡"){
        if(row.table_02 === '水稻监测点汇总表'){
          this.$router.push({path:'/riceTableTwo22',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "面上生产调度"){
        if(row.table_03 === '监测县(市、区)水稻播栽进度调查表（早稻）'){
          this.$router.push({path:'/riceTableTwo31',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_03 === '监测县(市、区)水稻播栽进度调查表（晚稻）'){
          this.$router.push({path:'/riceTableTwo32',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_03 === '监测县(市、区)水稻苗情评价动态列表（早稻-移栽稻）'){
          this.$router.push({path:'/riceTableTwo33',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_03 === '监测县(市、区)水稻苗情评价动态列表（早稻-直播稻）'){
          this.$router.push({path:'/riceTableTwo34',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_03 === '监测县(市、区)水稻苗情评价动态列表（晚稻-移栽稻）'){
          this.$router.push({path:'/riceTableTwo35',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_03 === '监测县(市、区)水稻苗情评价动态列表（晚稻-直播稻）'){
          this.$router.push({path:'/riceTableTwo36',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "监测点生产进度调查"){
        if(row.table_04 === '监测县(市、区)中稻生育进度调查表（移栽稻）'){
          this.$router.push({path:'/riceTableTwo41',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_04 === '监测县(市、区)直播稻生育进度调查表（直播稻）'){
          this.$router.push({path:'/riceTableTwo42',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_04 === '水稻监测点茎蘖数叶面积构成表'){
          this.$router.push({path:'/riceTableTwo43',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_04 === '水稻监测点苗情生育期统计表'){
          this.$router.push({path:'/riceTableTwo44',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "叶片参数自动测定"){
        if(row.table_05 === '叶片含氮测定仪导出数据'){
          this.$router.push({path:'/riceTableTwo51',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "监测点田管记录"){
        if(row.table_06 === '水稻监测点水分管理记录表'){
          this.$router.push({path:'/riceTableTwo61',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_06 === '水稻监测点化除管理记录表'){
          this.$router.push({path:'/riceTableTwo62',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_06 === '水稻监测点病虫害防治记录表'){
          this.$router.push({path:'/riceTableTwo63',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_06 === '水稻监测点施肥管理记录表'){
          this.$router.push({path:'/riceTableTwo64',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_06 === '水稻监测点化学调节剂使用记录表'){
          this.$router.push({path:'/riceTableTwo65',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "测产相关进度监测"){
        if(row.table_07 === '监测县(市、区)水稻双季稻苗情监测预测产表'){
          this.$router.push({path:'/riceTableTwo71',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_07 === '监测县(市、区)水稻双季稻收获进度调查（早稻）'){
          this.$router.push({path:'/riceTableTwo72',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_07 === '监测县(市、区)水稻双季稻收获进度调查（晚稻）'){
          this.$router.push({path:'/riceTableTwo73',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_07 === '监测县(市、区)水稻双季稻测产表'){
          this.$router.push({path:'/riceTableTwo74',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "生产效益调查"){
        if(row.table_08 === '监测县(市、区)水稻生产成本效益统计表（早稻）'){
          this.$router.push({path:'/riceTableTwo81',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_08 === '监测县(市、区)水稻生产成本效益统计表（晚稻）'){
          this.$router.push({path:'/riceTableTwo82',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "气象及自然灾害调查"){
        if(row.table_09 === '监测县(市、区)气象资料列表'){
          this.$router.push({path:'/riceTableTwo91',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_09 === '监测县(市、区)抽穗期前、后各15天气象资料'){
          this.$router.push({path:'/riceTableTwo92',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_09 === '监测县(市、区)水稻倒伏灾害表'){
          this.$router.push({path:'/riceTableTwo93',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_09 === '监测县(市、区)水稻低温冷害表'){
          this.$router.push({path:'/riceTableTwo94',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_09 === '监测县(市、区)水稻高温热害表'){
          this.$router.push({path:'/riceTableTwo95',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_09 === '监测县(市、区)水稻涝害调查表'){
          this.$router.push({path:'/riceTableTwo96',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_09 === '水稻监测点洪涝灾害表'){
          this.$router.push({path:'/riceTableTwo97',query:{area:areas,city:citys,year:years}});
        };
      };
      if(column.label === "监测点灌浆速率调查表"){
        if(row.table_10 === '水稻监测点灌浆速率调查表（早稻）'){
          this.$router.push({path:'/riceTableTwo101',query:{area:areas,city:citys,year:years}});
        };
        if(row.table_10 === '水稻监测点灌浆速率调查表（晚稻）'){
          this.$router.push({path:'/riceTableTwo102',query:{area:areas,city:citys,year:years}});
        };
      };
    },
    returnPage(){
        this.$router.push("/riceHome");
    },
    //双季稻导出
    //表名多选框选择
    handleCheckAllChange2(val) {
        this.checkedCities2 = val ? cityOptions2 : [];
        this.isIndeterminate2 = false;
      },
    handleCheckedCitiesChange2(value) {
      let checkedCount2 = value.length;
      this.checkAll2 = checkedCount2 === this.cities2.length;
      this.isIndeterminate2 = checkedCount2 > 0 && checkedCount2 < this.cities2.length;
    },
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
    },
    //获取数据
    getTableDataTwo11(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo11',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData = res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2*1);

              if(totalTableData[i].field_5_1_1 === ''){
                totalTableData[i].field_5_1_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_1_1*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_5_5_1 === ''){
                totalTableData[i].field_5_5_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_5_5_1*1);

              if(totalTableData[i].field_5_6_1 === ''){
                totalTableData[i].field_5_6_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_5_6_1*1);

              if(totalTableData[i].field_5_7_1 === ''){
                totalTableData[i].field_5_7_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_5_7_1*1);

              if(totalTableData[i].field_5_8_1 === ''){
                totalTableData[i].field_5_8_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_5_8_1*1);

              if(totalTableData[i].field_6_1_1 === ''){
                totalTableData[i].field_6_1_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_6_1_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_6_5_1 === ''){
                totalTableData[i].field_6_5_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_6_5_1*1);

              if(totalTableData[i].field_6_6_1 === ''){
                totalTableData[i].field_6_6_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_6_6_1*1);

              if(totalTableData[i].field_6_7_1 === ''){
                totalTableData[i].field_6_7_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_6_7_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;
            //求占比
            var totalNum1=0;//小计
            var totalNum2=0;//小计
            for(var i=0; i<8; i++){
              totalNum1 = total[i] + totalNum1;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            for(var i=8; i<total.length; i++){
              totalNum2 = total[i] + totalNum2;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            ////console.log(total.length,sums.length)
            //小计
            if(totalNum1 === num1){
              total[15] = '核对正确！';
              sums[15] = '100%';
            }else{
              total[15] = '错误！';
              sums[15] = ((totalNum1/num1)*100).toFixed(2)+'%';
            };
            if(totalNum2 === num1){
              total[16] = '核对正确！';
              sums[16] = '100%';
            }else{
              total[16] = '错误！';
              sums[16] = ((totalNum2/num1)*100).toFixed(2)+'%';
            };
            totalNum1 = totalNum1.toFixed(2);
            totalNum2 = totalNum2.toFixed(2);
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":num1,"field_3":'--',"field_4":'--',"field_5_1_1":total[0],"field_5_1_2":sums[0],
                      "field_5_2_1":total[1],"field_5_2_2":sums[1],"field_5_3_1":total[2],"field_5_3_2":sums[2],
                      "field_5_4_1":total[3],"field_5_4_2":sums[3],"field_5_5_1":total[4],"field_5_5_2":sums[4],
                      "field_5_6_1":total[5],"field_5_6_2":sums[5],"field_5_7_1":total[6],"field_5_7_2":sums[6],
                      "field_5_8_1":total[7],"field_5_8_2":sums[7],"field_5_9_1":totalNum1,"field_5_9_2":'--',"field_6_1_1":total[8],"field_6_1_2":sums[8],
                      "field_6_2_1":total[9],"field_6_2_2":sums[9],"field_6_3_1":total[10],"field_6_3_2":sums[10],
                      "field_6_4_1":total[11],"field_6_4_2":sums[11],"field_6_5_1":total[12],"field_6_5_2":sums[12],
                      "field_6_6_1":total[13],"field_6_6_2":sums[13],"field_6_7_1":total[14],"field_6_7_2":sums[14],
                      "field_6_8_1":totalNum2,"field_6_8_2":'--',"in_use":'--'})
           
          }else{
          };
            _this.tableDataTwo11 = totalTableData;
            ////console.log(totalTableData);
       }).catch(err=>{
         //console.log("此处有问题");
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo12(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo12',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
           _this.tableDataTwo12=res.data;
          ////console.log(_this.tableDataTwo12);
       }).catch(err=>{
         //console.log("此处有问题");
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo22(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo22',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
           _this.tableDataTwo22=res.data;
          ////console.log(_this.tableDataTwo22);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo31(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo31',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total1 =[];//存各面积和;无占比
            var total2 =[];//有占比
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_6 === ''){
                totalTableData[i].field_6 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_2_1*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_12_2_1*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_12_4*1);

              if(totalTableData[i].field_13_2 === ''){
                totalTableData[i].field_13_2 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_13_2*1);

              if(totalTableData[i].field_13_3 === ''){
                totalTableData[i].field_13_3 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_13_3*1);
            };
            ////console.log(num1,num2,num6,num14);
            //各品种面积总和
            //无占比
            total1[0]=num2;total1[1]=num7;total1[2]=num8;total1[3]=num10;total1[4]=num11;total1[5]=num13;total1[6]=num14;total1[7]=num15;total1[8]=num16;
            total2[0]=num3;total2[1]=num4;total2[2]=num5;total2[3]=num6;total2[4]=num9;total2[5]=num12;//有占比
            //求占比          
            for(var i=0; i<6; i++){
              if(num1 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(total2[i] === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(i===1 || i===2){
                sums[i] = (Number(total2[i])/Number(num1)*100).toFixed(2)+"%";////总面积占比
              }else if(num2 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total2[i])/Number(num2)*100).toFixed(2)+"%";//秧田面积占比
              };
            };
            //求小计和
            var totalNum=[];
            var totalPer=[];
            totalNum[0] = (Number(total1[1])+Number(total1[2])).toFixed(2);
            totalNum[1] = (Number(total1[3])+Number(total1[4])).toFixed(2);
            totalNum[2]= (Number(total1[5])+Number(total1[6])).toFixed(2);
            totalNum[3]= (Number(total1[7])+Number(total1[8])).toFixed(2);
            for(var i=0; i<4; i++){
              if(num1 === 0){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else if(totalNum[i] === '0.00'){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else{
                totalPer[i] = (Number(totalNum[i])/Number(num1)*100).toFixed(2)+"%";
              }
            }
            //处理无占比数据为0时情况
            for(var i=0; i<9; i++){
              if(total1[i] === 0){
                total1[i] ='--'
              }
            }     
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,"field_5_1":'--',"field_5_2":'--',"field_6":total1[0],
                      "field_7_1":total2[0],"field_7_2":sums[0],"field_8_1":total2[1],"field_8_2":sums[1],"field_9_1":total2[2],"field_9_2":sums[2],
                      "field_10_1":'--',"field_10_2_1":total2[3],"field_10_2_2":sums[3],"field_10_3":total1[1],"field_10_4":total1[2],"field_10_5":totalNum[0],"field_10_6":totalPer[0],
                      "field_11_1":'--',"field_11_2_1":total2[4],"field_11_2_2":sums[4],"field_11_3":total1[3],"field_11_4":total1[4],"field_11_5":totalNum[1],"field_11_6":totalPer[1],
                      "field_12_1":'--',"field_12_2_1":total2[5],"field_12_2_2":sums[5],"field_12_3":total1[5],"field_12_4":total1[6],"field_12_5":totalNum[2],"field_12_6":totalPer[2],
                      "field_13_1":'--',"field_13_2":total1[7],"field_13_3":total1[8],"field_13_4":totalNum[3],"field_13_5":totalPer[3],
                      "in_use":'--'})
          }else{
          };
          _this.tableDataTwo31 = totalTableData;
          ////console.log(totalTableData);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo32(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo32',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          //
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total1 =[];//存各面积和;无占比
            var total2 =[];//有占比
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_6 === ''){
                totalTableData[i].field_6 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_2_1*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_12_2_1*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_12_4*1);
            };
            //各品种面积总和
            //无占比
            total1[0]=num2;total1[1]=num7;total1[2]=num8;total1[3]=num10;total1[4]=num11;total1[5]=num13;total1[6]=num14;total1[7]=num15;total1[8]=num16;
            total2[0]=num3;total2[1]=num4;total2[2]=num5;total2[3]=num6;total2[4]=num9;total2[5]=num12;//有占比
            //求占比          
            for(var i=0; i<6; i++){
              if(num1 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(total2[i] === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(i===1 || i===2){
                sums[i] = (Number(total2[i])/Number(num1)*100).toFixed(2)+"%";////总面积占比
              }else if(num2 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total2[i])/Number(num2)*100).toFixed(2)+"%";//秧田面积占比
              };
            };
            //求小计和
            var totalNum=[];
            var totalPer=[];
            totalNum[0] = (Number(total1[1])+Number(total1[2])).toFixed(2);
            totalNum[1] = (Number(total1[3])+Number(total1[4])).toFixed(2);
            totalNum[2]= (Number(total1[5])+Number(total1[6])).toFixed(2);
            for(var i=0; i<4; i++){
              if(num1 === 0){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else if(totalNum[i] === '0.00'){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else{
                totalPer[i] = (Number(totalNum[i])/Number(num1)*100).toFixed(2)+"%";
              }
            }
            //处理无占比数据为0时情况
            for(var i=0; i<9; i++){
              if(total1[i] === 0){
                total1[i] ='--'
              }
            }     
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,"field_5_1":'--',"field_5_2":'--',"field_6":total1[0],
                      "field_7_1":total2[0],"field_7_2":sums[0],"field_8_1":total2[1],"field_8_2":sums[1],"field_9_1":total2[2],"field_9_2":sums[2],
                      "field_10_1":'--',"field_10_2_1":total2[3],"field_10_2_2":sums[3],"field_10_3":total1[1],"field_10_4":total1[2],"field_10_5":totalNum[0],"field_10_6":totalPer[0],
                      "field_11_1":'--',"field_11_2_1":total2[4],"field_11_2_2":sums[4],"field_11_3":total1[3],"field_11_4":total1[4],"field_11_5":totalNum[1],"field_11_6":totalPer[1],
                      "field_12_1":'--',"field_12_2_1":total2[5],"field_12_2_2":sums[5],"field_12_3":total1[5],"field_12_4":total1[6],"field_12_5":totalNum[2],"field_12_6":totalPer[2],
                      "in_use":'--'})
          }else{
          };
          _this.tableDataTwo32 = totalTableData;
          ////console.log(totalTableData);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo33(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo33',
          method:'post',
          params:{
            county:this.xian,
            areaType:this.diqu,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              ////console.log(num1);
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_7_3_1 === ''){
                totalTableData[i].field_7_3_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_7_3_1*1);

              if(totalTableData[i].field_7_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_7_4_1*1);

              if(totalTableData[i].field_8_2_1 === ''){
                totalTableData[i].field_8_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_2_1*1);

              if(totalTableData[i].field_8_3_1 === ''){
                totalTableData[i].field_8_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_8_3_1*1);

              if(totalTableData[i].field_8_4_1 === ''){
                totalTableData[i].field_8_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_8_4_1*1);

              if(totalTableData[i].field_9_2_1 === ''){
                totalTableData[i].field_9_2_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_9_2_1*1);

              if(totalTableData[i].field_9_3_1 === ''){
                totalTableData[i].field_9_3_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_9_3_1*1);

              if(totalTableData[i].field_9_4_1 === ''){
                totalTableData[i].field_9_4_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_9_4_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3_1 === ''){
                totalTableData[i].field_10_3_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_10_3_1*1);

              if(totalTableData[i].field_10_4_1 === ''){
                totalTableData[i].field_10_4_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_10_4_1*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_11_2_1*1);
              
              if(totalTableData[i].field_11_3_1 === ''){
                totalTableData[i].field_11_3_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_11_3_1*1);

              if(totalTableData[i].field_11_4_1 === ''){
                totalTableData[i].field_11_4_1 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_11_4_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;
            total[19]=num21;total[20]=num22;
            //求占比
            for(var i=0; i<total.length; i++){
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,
                      "field_5_1":'--',"field_5_2_1":total[0],"field_5_2_2":sums[0],"field_5_3_1":total[1],"field_5_3_2":sums[1],"field_5_4_1":total[2],"field_5_4_2":sums[2],
                      "field_6_1":'--',"field_6_2_1":total[3],"field_6_2_2":sums[3],"field_6_3_1":total[4],"field_6_3_2":sums[4],"field_6_4_1":total[5],"field_6_4_2":sums[5],
                      "field_7_1":'--',"field_7_2_1":total[6],"field_7_2_2":sums[6],"field_7_3_1":total[7],"field_7_3_2":sums[7],"field_7_4_1":total[8],"field_7_4_2":sums[8],
                      "field_8_1":'--',"field_8_2_1":total[9],"field_8_2_2":sums[9],"field_8_3_1":total[10],"field_8_3_2":sums[10],"field_8_4_1":total[11],"field_8_4_2":sums[11],
                      "field_9_1":'--',"field_9_2_1":total[12],"field_9_2_2":sums[12],"field_9_3_1":total[13],"field_9_3_2":sums[13],"field_9_4_1":total[14],"field_9_4_2":sums[14],
                      "field_10_1":'--',"field_10_2_1":total[15],"field_10_2_2":sums[15],"field_10_3_1":total[16],"field_10_3_2":sums[16],"field_10_4_1":total[17],"field_10_4_2":sums[17],
                      "field_11_1":'--',"field_11_2_1":total[18],"field_11_2_2":sums[18], "field_11_3_1":total[19],"field_11_3_2":sums[19],"field_11_4_1":total[20],"field_11_4_2":sums[20],
                      "in_use":'--'})
          }else{
          };  
           _this.tableDataTwo33 = totalTableData;
          ////console.log(totalTableData);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo34(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo34',
         method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;

          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              ////console.log(num1);
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_7_3_1 === ''){
                totalTableData[i].field_7_3_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_7_3_1*1);

              if(totalTableData[i].field_7_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_7_4_1*1);

              if(totalTableData[i].field_8_2_1 === ''){
                totalTableData[i].field_8_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_2_1*1);

              if(totalTableData[i].field_8_3_1 === ''){
                totalTableData[i].field_8_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_8_3_1*1);

              if(totalTableData[i].field_8_4_1 === ''){
                totalTableData[i].field_8_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_8_4_1*1);

              if(totalTableData[i].field_9_2_1 === ''){
                totalTableData[i].field_9_2_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_9_2_1*1);

              if(totalTableData[i].field_9_3_1 === ''){
                totalTableData[i].field_9_3_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_9_3_1*1);

              if(totalTableData[i].field_9_4_1 === ''){
                totalTableData[i].field_9_4_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_9_4_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3_1 === ''){
                totalTableData[i].field_10_3_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_10_3_1*1);

              if(totalTableData[i].field_10_4_1 === ''){
                totalTableData[i].field_10_4_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_10_4_1*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_11_2_1*1);
              
              if(totalTableData[i].field_11_3_1 === ''){
                totalTableData[i].field_11_3_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_11_3_1*1);

              if(totalTableData[i].field_11_4_1 === ''){
                totalTableData[i].field_11_4_1 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_11_4_1*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_12_2_1*1);
              
              if(totalTableData[i].field_12_3_1 === ''){
                totalTableData[i].field_12_3_1 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_12_3_1*1);

              if(totalTableData[i].field_12_4_1 === ''){
                totalTableData[i].field_12_4_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_12_4_1*1);

              if(totalTableData[i].field_13_2_1 === ''){
                totalTableData[i].field_13_2_1 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_13_2_1*1);
              
              if(totalTableData[i].field_13_3_1 === ''){
                totalTableData[i].field_13_3_1 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_13_3_1*1);

              if(totalTableData[i].field_13_4_1 === ''){
                totalTableData[i].field_13_4_1 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_13_4_1*1);

              
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;
            total[19]=num21;total[20]=num22;total[21]=num23;total[22]=num24;total[23]=num25;total[24]=num26;total[25]=num27;total[26]=num28;
            //求占比
            for(var i=0; i<total.length; i++){
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,
                      "field_5_1":'--',"field_5_2_1":total[0],"field_5_2_2":sums[0],"field_5_3_1":total[1],"field_5_3_2":sums[1],"field_5_4_1":total[2],"field_5_4_2":sums[2],
                      "field_6_1":'--',"field_6_2_1":total[3],"field_6_2_2":sums[3],"field_6_3_1":total[4],"field_6_3_2":sums[4],"field_6_4_1":total[5],"field_6_4_2":sums[5],
                      "field_7_1":'--',"field_7_2_1":total[6],"field_7_2_2":sums[6],"field_7_3_1":total[7],"field_7_3_2":sums[7],"field_7_4_1":total[8],"field_7_4_2":sums[8],
                      "field_8_1":'--',"field_8_2_1":total[9],"field_8_2_2":sums[9],"field_8_3_1":total[10],"field_8_3_2":sums[10],"field_8_4_1":total[11],"field_8_4_2":sums[11],
                      "field_9_1":'--',"field_9_2_1":total[12],"field_9_2_2":sums[12],"field_9_3_1":total[13],"field_9_3_2":sums[13],"field_9_4_1":total[14],"field_9_4_2":sums[14],
                      "field_10_1":'--',"field_10_2_1":total[15],"field_10_2_2":sums[15],"field_10_3_1":total[16],"field_10_3_2":sums[16],"field_10_4_1":total[17],"field_10_4_2":sums[17],
                      "field_11_1":'--',"field_11_2_1":total[18],"field_11_2_2":sums[18], "field_11_3_1":total[19],"field_11_3_2":sums[19],"field_11_4_1":total[20],"field_11_4_2":sums[20],
                      "field_12_1":'--',"field_12_2_1":total[21],"field_12_2_2":sums[21], "field_12_3_1":total[22],"field_12_3_2":sums[22],"field_12_4_1":total[23],"field_12_4_2":sums[23],
                      "field_13_1":'--',"field_13_2_1":total[24],"field_13_2_2":sums[24], "field_13_3_1":total[25],"field_13_3_2":sums[25],"field_13_4_1":total[26],"field_13_4_2":sums[26],
                      "in_use":'--'})
          }else{
          }; 
           _this.tableDataTwo34 = totalTableData;
          ////console.log(totalTableData);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo35(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo35',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;

          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_3 === ''){
                totalTableData[i].field_3 = 0;
              }
              ////console.log(num1);
              num1 = _this.add(num1*1+totalTableData[i].field_3*1);

              if(totalTableData[i].field_4_2_1 === ''){
                totalTableData[i].field_4_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_4_2_1*1);

              if(totalTableData[i].field_4_3_1 === ''){
                totalTableData[i].field_4_3_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_4_3_1*1);

              if(totalTableData[i].field_4_4_1 === ''){
                totalTableData[i].field_4_4_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_4_4_1*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_7_3_1 === ''){
                totalTableData[i].field_7_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_7_3_1*1);

              if(totalTableData[i].field_7_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_7_4_1*1);

              if(totalTableData[i].field_8_2_1 === ''){
                totalTableData[i].field_8_2_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_8_2_1*1);

              if(totalTableData[i].field_8_3_1 === ''){
                totalTableData[i].field_8_3_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_8_3_1*1);

              if(totalTableData[i].field_8_4_1 === ''){
                totalTableData[i].field_8_4_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_8_4_1*1);

              if(totalTableData[i].field_9_2_1 === ''){
                totalTableData[i].field_9_2_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_9_2_1*1);

              if(totalTableData[i].field_9_3_1 === ''){
                totalTableData[i].field_9_3_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_9_3_1*1);

              if(totalTableData[i].field_9_4_1 === ''){
                totalTableData[i].field_9_4_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_9_4_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_10_2_1*1);
              
              if(totalTableData[i].field_10_3_1 === ''){
                totalTableData[i].field_10_3_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_10_3_1*1);

              if(totalTableData[i].field_10_4_1 === ''){
                totalTableData[i].field_10_4_1 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_10_4_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;
            total[19]=num21;total[20]=num22;
            //求占比
            for(var i=0; i<total.length; i++){
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":num1,
                      "field_4_1":'--',"field_4_2_1":total[0],"field_4_2_2":sums[0],"field_4_3_1":total[1],"field_4_3_2":sums[1],"field_4_4_1":total[2],"field_4_4_2":sums[2],
                      "field_5_1":'--',"field_5_2_1":total[3],"field_5_2_2":sums[3],"field_5_3_1":total[4],"field_5_3_2":sums[4],"field_5_4_1":total[5],"field_5_4_2":sums[5],
                      "field_6_1":'--',"field_6_2_1":total[6],"field_6_2_2":sums[6],"field_6_3_1":total[7],"field_6_3_2":sums[7],"field_6_4_1":total[8],"field_6_4_2":sums[8],
                      "field_7_1":'--',"field_7_2_1":total[9],"field_7_2_2":sums[9],"field_7_3_1":total[10],"field_7_3_2":sums[10],"field_7_4_1":total[11],"field_7_4_2":sums[11],
                      "field_8_1":'--',"field_8_2_1":total[12],"field_8_2_2":sums[12],"field_8_3_1":total[13],"field_8_3_2":sums[13],"field_8_4_1":total[14],"field_8_4_2":sums[14],
                      "field_9_1":'--',"field_9_2_1":total[15],"field_9_2_2":sums[15],"field_9_3_1":total[16],"field_9_3_2":sums[16],"field_9_4_1":total[17],"field_9_4_2":sums[17],
                      "field_10_1":'--',"field_10_2_1":total[18],"field_10_2_2":sums[18], "field_10_3_1":total[19],"field_10_3_2":sums[19],"field_10_4_1":total[20],"field_10_4_2":sums[20],
                      "in_use":'--'}) 
          }else{ 
          };
           _this.tableDataTwo35 = totalTableData;
          ////console.log(totalTableData);

          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo36(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo36',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;

          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              ////console.log(num1);
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_7_3_1 === ''){
                totalTableData[i].field_7_3_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_7_3_1*1);

              if(totalTableData[i].field_7_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_7_4_1*1);

              if(totalTableData[i].field_8_2_1 === ''){
                totalTableData[i].field_8_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_2_1*1);

              if(totalTableData[i].field_8_3_1 === ''){
                totalTableData[i].field_8_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_8_3_1*1);

              if(totalTableData[i].field_8_4_1 === ''){
                totalTableData[i].field_8_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_8_4_1*1);

              if(totalTableData[i].field_9_2_1 === ''){
                totalTableData[i].field_9_2_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_9_2_1*1);

              if(totalTableData[i].field_9_3_1 === ''){
                totalTableData[i].field_9_3_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_9_3_1*1);

              if(totalTableData[i].field_9_4_1 === ''){
                totalTableData[i].field_9_4_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_9_4_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3_1 === ''){
                totalTableData[i].field_10_3_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_10_3_1*1);

              if(totalTableData[i].field_10_4_1 === ''){
                totalTableData[i].field_10_4_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_10_4_1*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_11_2_1*1);
              
              if(totalTableData[i].field_11_3_1 === ''){
                totalTableData[i].field_11_3_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_11_3_1*1);

              if(totalTableData[i].field_11_4_1 === ''){
                totalTableData[i].field_11_4_1 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_11_4_1*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_12_2_1*1);
              
              if(totalTableData[i].field_12_3_1 === ''){
                totalTableData[i].field_12_3_1 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_12_3_1*1);

              if(totalTableData[i].field_12_4_1 === ''){
                totalTableData[i].field_12_4_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_12_4_1*1);

              if(totalTableData[i].field_13_2_1 === ''){
                totalTableData[i].field_13_2_1 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_13_2_1*1);
              
              if(totalTableData[i].field_13_3_1 === ''){
                totalTableData[i].field_13_3_1 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_13_3_1*1);

              if(totalTableData[i].field_13_4_1 === ''){
                totalTableData[i].field_13_4_1 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_13_4_1*1);

              
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;
            total[19]=num21;total[20]=num22;total[21]=num23;total[22]=num24;total[23]=num25;total[24]=num26;total[25]=num27;total[26]=num28;
            //求占比
            for(var i=0; i<total.length; i++){
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,
                      "field_5_1":'--',"field_5_2_1":total[0],"field_5_2_2":sums[0],"field_5_3_1":total[1],"field_5_3_2":sums[1],"field_5_4_1":total[2],"field_5_4_2":sums[2],
                      "field_6_1":'--',"field_6_2_1":total[3],"field_6_2_2":sums[3],"field_6_3_1":total[4],"field_6_3_2":sums[4],"field_6_4_1":total[5],"field_6_4_2":sums[5],
                      "field_7_1":'--',"field_7_2_1":total[6],"field_7_2_2":sums[6],"field_7_3_1":total[7],"field_7_3_2":sums[7],"field_7_4_1":total[8],"field_7_4_2":sums[8],
                      "field_8_1":'--',"field_8_2_1":total[9],"field_8_2_2":sums[9],"field_8_3_1":total[10],"field_8_3_2":sums[10],"field_8_4_1":total[11],"field_8_4_2":sums[11],
                      "field_9_1":'--',"field_9_2_1":total[12],"field_9_2_2":sums[12],"field_9_3_1":total[13],"field_9_3_2":sums[13],"field_9_4_1":total[14],"field_9_4_2":sums[14],
                      "field_10_1":'--',"field_10_2_1":total[15],"field_10_2_2":sums[15],"field_10_3_1":total[16],"field_10_3_2":sums[16],"field_10_4_1":total[17],"field_10_4_2":sums[17],
                      "field_11_1":'--',"field_11_2_1":total[18],"field_11_2_2":sums[18], "field_11_3_1":total[19],"field_11_3_2":sums[19],"field_11_4_1":total[20],"field_11_4_2":sums[20],
                      "field_12_1":'--',"field_12_2_1":total[21],"field_12_2_2":sums[21], "field_12_3_1":total[22],"field_12_3_2":sums[22],"field_12_4_1":total[23],"field_12_4_2":sums[23],
                      "field_13_1":'--',"field_13_2_1":total[24],"field_13_2_2":sums[24], "field_13_3_1":total[25],"field_13_3_2":sums[25],"field_13_4_1":total[26],"field_13_4_2":sums[26],
                      "in_use":'--'}) 
          }else{ 
          }; 
           _this.tableDataTwo36 = totalTableData;
          ////console.log(totalTableData);

          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo41(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo41',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_12_4*1);

              if(totalTableData[i].field_13_3 === ''){
                totalTableData[i].field_13_3 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_13_3*1);

              if(totalTableData[i].field_13_4 === ''){
                totalTableData[i].field_13_4 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_13_4*1);

              if(totalTableData[i].field_14_3 === ''){
                totalTableData[i].field_14_3 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_14_3*1);

              if(totalTableData[i].field_14_4 === ''){
                totalTableData[i].field_14_4 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_14_4*1);

              if(totalTableData[i].field_15_3 === ''){
                totalTableData[i].field_15_3 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_15_3*1);

              if(totalTableData[i].field_15_4 === ''){
                totalTableData[i].field_15_4 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_15_4*1);

            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;total[18]=num19;
            total[19]=num20;total[20]=num21;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',
                                "field_8_1":'--',"field_8_2":sums[0],"field_8_3":sums[1],"field_8_4":sums[2],
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":sums[3],"field_9_4":sums[4],
                                "field_10_1":'--',"field_10_2":'--',"field_10_3":sums[5],"field_10_4":sums[6],
                                "field_11_1":'--',"field_11_2":'--',"field_11_3":sums[7],"field_11_4":sums[8],
                                "field_12_1":'--',"field_12_2":'--',"field_12_3":sums[9],"field_12_4":sums[10],
                                "field_13_1":'--',"field_13_2":'--',"field_13_3":sums[11],"field_13_4":sums[12],
                                "field_14_1":'--',"field_14_2":'--',"field_14_3":sums[13],"field_14_4":sums[14],
                                "field_15_1":'--',"field_15_2":'--',"field_15_3":sums[15],"field_15_4":sums[16],
                                "in_use":'--'}) 
          }else{ 
          }; 
          _this.tableDataTwo41 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo42(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo42',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_12_4*1);

              if(totalTableData[i].field_13_3 === ''){
                totalTableData[i].field_13_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_13_3*1);

              if(totalTableData[i].field_13_4 === ''){
                totalTableData[i].field_13_4 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_13_4*1);

              if(totalTableData[i].field_14_3 === ''){
                totalTableData[i].field_14_3 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_14_3*1);

              if(totalTableData[i].field_14_4 === ''){
                totalTableData[i].field_14_4 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_14_4*1);
            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',
                                "field_8_1":'--',"field_8_2":'--',"field_8_3":sums[0],"field_8_4":sums[1],
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":sums[2],"field_9_4":sums[3],
                                "field_10_1":'--',"field_10_2":'--',"field_10_3":sums[4],"field_10_4":sums[5],
                                "field_11_1":'--',"field_11_2":'--',"field_11_3":sums[6],"field_11_4":sums[7],
                                "field_12_1":'--',"field_12_2":'--',"field_12_3":sums[8],"field_12_4":sums[9],
                                "field_13_1":'--',"field_13_2":'--',"field_13_3":sums[10],"field_13_4":sums[11],
                                "field_14_1":'--',"field_14_2":'--',"field_14_3":sums[12],"field_14_4":sums[13],
                                "in_use":'--'}) 
          }else{ 
          }; 
          _this.tableDataTwo42 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo43(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo43',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_9 === ''){
                totalTableData[i].field_9 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_9*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_10_6 === ''){
                totalTableData[i].field_10_6 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_10_6*1);

              if(totalTableData[i].field_10_7 === ''){
                totalTableData[i].field_10_7 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_7*1);

              if(totalTableData[i].field_10_8 === ''){
                totalTableData[i].field_10_8 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_8*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_2 === ''){
                totalTableData[i].field_12_2 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_12_2*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_12_4*1);

            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            } 
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',"field_8":'--',"field_9":sums[0],
                                "field_10_1":'--',"field_10_2":sums[1],"field_10_3":sums[2],"field_10_4":sums[3],
                                "field_10_5":'--',"field_10_6":sums[4],"field_10_7":sums[5],"field_10_8":sums[6],
                                "field_11_1":'--',"field_11_2":sums[7],"field_11_3":sums[8],"field_11_4":sums[9],"field_11_5":'--',
                                "field_12_1":'--',"field_12_2":sums[10],"field_12_3":sums[11],"field_12_4":sums[12],"field_12_5":'--',                   
                                "in_use":'--'}) 
          }else{ 
          }; 
          _this.tableDataTwo43 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo44(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo44',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_18 === ''){
                totalTableData[i].field_18 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_18*1);
            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;
            ////console.log(total);
            if(total[0] === 0){
              sums[0]='--';
            }else{
              sums[0] = (Number(total[i])/Number(dataLength)).toFixed(2);
            }
            ////console.log(sums);      
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',"field_8":'--',"field_9":'--',"field_10":'--',
                                "field_fennie":'--',"field_11":'--',"field_12":'--',"field_13":'--',"field_14":'--',"field_15":'--',"field_16":'--',"field_17":'--',"field_18":sums[0],               
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo44 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo51(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo51',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
            var num31=0;var num32=0;var num33=0;var num34=0;var num35=0;var num36=0;var num37=0;var num38=0;var num39=0;var num40=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5 === ''){
                totalTableData[i].field_7_5 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_7 === ''){
                totalTableData[i].field_7_7 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_7_7*1);

              if(totalTableData[i].field_7_8 === ''){
                totalTableData[i].field_7_8 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_7_8*1);

              if(totalTableData[i].field_7_9 === ''){
                totalTableData[i].field_7_9 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_7_9*1);

              if(totalTableData[i].field_7_10 === ''){
                totalTableData[i].field_7_10 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_10*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_8_5 === ''){
                totalTableData[i].field_8_5 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_8_5*1);

              if(totalTableData[i].field_8_7 === ''){
                totalTableData[i].field_8_7 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_8_7*1);

              if(totalTableData[i].field_8_8 === ''){
                totalTableData[i].field_8_8 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_8_8*1);

              if(totalTableData[i].field_8_9 === ''){
                totalTableData[i].field_8_9 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_8_9*1);

              if(totalTableData[i].field_8_10 === ''){
                totalTableData[i].field_8_10 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_8_10*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_9_5 === ''){
                totalTableData[i].field_9_5 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_9_5*1);

              if(totalTableData[i].field_9_7 === ''){
                totalTableData[i].field_9_7 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_9_7*1);

              if(totalTableData[i].field_9_8 === ''){
                totalTableData[i].field_9_8 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_9_8*1);

              if(totalTableData[i].field_9_9 === ''){
                totalTableData[i].field_9_9 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_9_9*1);

              if(totalTableData[i].field_9_10 === ''){
                totalTableData[i].field_9_10 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_9_10*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_10_5 === ''){
                totalTableData[i].field_10_5 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_10_5*1);

              if(totalTableData[i].field_10_7 === ''){
                totalTableData[i].field_10_7 = 0;
              }
              num29 = _this.add(num29*1+totalTableData[i].field_10_7*1);

              if(totalTableData[i].field_10_8 === ''){
                totalTableData[i].field_10_8 = 0;
              }
              num30 = _this.add(num30*1+totalTableData[i].field_10_8*1);

              if(totalTableData[i].field_10_9 === ''){
                totalTableData[i].field_10_9 = 0;
              }
              num31 = _this.add(num31*1+totalTableData[i].field_10_9*1);

              if(totalTableData[i].field_10_10 === ''){
                totalTableData[i].field_10_10 = 0;
              }
              num32 = _this.add(num32*1+totalTableData[i].field_10_10*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num33 = _this.add(num33*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num34 = _this.add(num34*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num35 = _this.add(num35*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_11_5 === ''){
                totalTableData[i].field_11_5 = 0;
              }
              num36 = _this.add(num36*1+totalTableData[i].field_11_5*1);

              if(totalTableData[i].field_11_7 === ''){
                totalTableData[i].field_11_7 = 0;
              }
              num37 = _this.add(num37*1+totalTableData[i].field_11_7*1);

              if(totalTableData[i].field_11_8 === ''){
                totalTableData[i].field_11_8 = 0;
              }
              num38 = _this.add(num38*1+totalTableData[i].field_11_8*1);

              if(totalTableData[i].field_11_9 === ''){
                totalTableData[i].field_11_9 = 0;
              }
              num39 = _this.add(num39*1+totalTableData[i].field_11_9*1);

              if(totalTableData[i].field_11_10 === ''){
                totalTableData[i].field_11_10 = 0;
              }
              num40 = _this.add(num40*1+totalTableData[i].field_11_10*1);

            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;total[18]=num19;total[19]=num20;
            total[20]=num21;total[21]=num22;total[22]=num23;total[23]=num24;total[24]=num25;total[25]=num26;total[26]=num27;total[27]=num28;total[28]=num29;total[29]=num30;
            total[30]=num31;total[31]=num32;total[32]=num33;total[33]=num34;total[34]=num35;total[35]=num36;total[36]=num37;total[37]=num38;total[38]=num39;total[39]=num40;
            //////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            //////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":sums[0],"field_7_3":sums[1],"field_7_4":sums[2],"field_7_5":sums[3],
                                "field_7_6":'--',"field_7_7":sums[4],"field_7_8":sums[5],"field_7_9":sums[6],"field_7_10":sums[7], 
                                "field_8_1":'--',"field_8_2":sums[8],"field_8_3":sums[9],"field_8_4":sums[10],"field_8_5":sums[11],
                                "field_8_6":'--',"field_8_7":sums[12],"field_8_8":sums[13],"field_8_9":sums[14],"field_8_10":sums[15],
                                "field_9_1":'--',"field_9_2":sums[16],"field_9_3":sums[17],"field_9_4":sums[18],"field_9_5":sums[19],
                                "field_9_6":'--',"field_9_7":sums[20],"field_9_8":sums[21],"field_9_9":sums[22],"field_9_10":sums[23],
                                "field_10_1":'--',"field_10_2":sums[24],"field_10_3":sums[25],"field_10_4":sums[26],"field_10_5":sums[27],
                                "field_10_6":'--',"field_10_7":sums[28],"field_10_8":sums[29],"field_10_9":sums[30],"field_10_10":sums[31],
                                "field_11_1":'--',"field_11_2":sums[32],"field_11_3":sums[33],"field_11_4":sums[34],"field_11_5":sums[35],
                                "field_11_6":'--',"field_11_7":sums[36],"field_11_8":sums[37],"field_11_9":sums[38],"field_11_10":sums[39],                 
                                "in_use":'--'}) 
          }else{ 
          };
          _this.tableDataTwo51 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    }, 
    getTableDataTwo61(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo61',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
           if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_12_2 === ''){
                totalTableData[i].field_12_2 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_12_2*1);

              if(totalTableData[i].field_13_2 === ''){
                totalTableData[i].field_13_2 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_13_2*1);

             
            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;
             
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":sums[0],"field_8_1":'--',"field_8_2":sums[1],
                                "field_9_1":'--',"field_9_2":sums[2],"field_10_1":'--',"field_10_2":sums[3],
                                "field_11_1":'--',"field_11_2":sums[4],"field_12_1":'--',"field_12_2":sums[5],
                                "field_13_1":'--',"field_13_2":sums[6],
                                "in_use":'--'}) 
          }else{ 
          };  
          _this.tableDataTwo61 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo62(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo62',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_3*1);
            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":sums[0],"field_7_4":'--',"field_7_5":'--',
                                "field_8_1":'--',"field_8_2":'--',"field_8_3":sums[1],"field_8_4":'--',"field_8_5":'--',
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":sums[2],"field_9_4":'--',"field_9_5":'--',
                                "in_use":'--'}) 
          }else{ 
          }  
          _this.tableDataTwo62 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo63(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo63',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_11_4*1);
             
            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":'--',"field_7_4":sums[0],"field_7_5":'--',"field_7_6":'--',
                                "field_8_1":'--',"field_8_2":'--',"field_8_3":'--',"field_8_4":sums[1],"field_8_5":'--',"field_8_6":'--',
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":'--',"field_9_4":sums[2],"field_9_5":'--',"field_9_6":'--',
                                "field_10_1":'--',"field_10_2":'--',"field_10_3":'--',"field_10_4":sums[3],"field_10_5":'--',"field_10_6":'--',
                                "field_11_1":'--',"field_11_2":'--',"field_11_3":'--',"field_11_4":sums[4],"field_11_5":'--',"field_11_6":'--',
                                "in_use":'--'}) 
          }else{ 
          };  
          _this.tableDataTwo63 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo64(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo64',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_11_4*1);

             
            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":sums[0],"field_7_3":sums[1],"field_7_4":sums[2],"field_7_5":'--',"field_7_6":'--',
                                "field_8_1":'--',"field_8_2":sums[3],"field_8_3":sums[4],"field_8_4":sums[5],"field_8_5":'--',"field_8_6":'--',
                                "field_9_1":'--',"field_9_2":sums[6],"field_9_3":sums[7],"field_9_4":sums[8],"field_9_5":'--',"field_9_6":'--',
                                "field_10_1":'--',"field_10_2":sums[9],"field_10_3":sums[10],"field_10_4":sums[11],"field_10_5":'--',"field_10_6":'--',
                                "field_11_1":'--',"field_11_2":sums[12],"field_11_3":sums[13],"field_11_4":sums[14],"field_11_5":'--',"field_11_6":'--',
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo64 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo65(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo65',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
           var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_3*1);
            };
            //各品种面积总和
            ////console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":sums[0],"field_7_4":'--',"field_7_5":'--',
                                "field_8_1":'--',"field_8_2":'--',"field_8_3":sums[1],"field_8_4":'--',"field_8_5":'--',
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":sums[2],"field_9_4":'--',"field_9_5":'--',
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo65 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo71(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo71',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums1 = [];//存平均
            var sums2 = [];//存面积合计
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2*1);

              if(totalTableData[i].field_3_1 === ''){
                totalTableData[i].field_3_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_3_1*1);

              if(totalTableData[i].field_3_2 === ''){
                totalTableData[i].field_3_2 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_3_2*1);

              if(totalTableData[i].field_3_3 === ''){
                totalTableData[i].field_3_3 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_3_3*1);

              if(totalTableData[i].field_3_4 === ''){
                totalTableData[i].field_3_4 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_3_4*1);

              if(totalTableData[i].field_3_5 === ''){
                totalTableData[i].field_3_5 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_3_5*1);

              if(totalTableData[i].field_3_6 === ''){
                totalTableData[i].field_3_6 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_3_6*1);

              if(totalTableData[i].field_4_1 === ''){
                totalTableData[i].field_4_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_4_1*1);

              if(totalTableData[i].field_4_2 === ''){
                totalTableData[i].field_4_2 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_4_2*1);

              if(totalTableData[i].field_4_3 === ''){
                totalTableData[i].field_4_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_4_3*1);

              if(totalTableData[i].field_4_4 === ''){
                totalTableData[i].field_4_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_4_4*1);

              if(totalTableData[i].field_4_5 === ''){
                totalTableData[i].field_4_5 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_4_5*1);

              if(totalTableData[i].field_4_6 === ''){
                totalTableData[i].field_4_6 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_4_6*1);

              if(totalTableData[i].field_5 === ''){
                totalTableData[i].field_5 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_5*1);

              if(totalTableData[i].field_6_1 === ''){
                totalTableData[i].field_6_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_6_1*1);

              if(totalTableData[i].field_6_2 === ''){
                totalTableData[i].field_6_2 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_6_2*1);

              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].field_6_4 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_6_5 === ''){
                totalTableData[i].field_6_5 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_6_5*1);

              if(totalTableData[i].field_6_6 === ''){
                totalTableData[i].field_6_6 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_6_6*1);
              
              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5 === ''){
                totalTableData[i].field_7_5 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_7_6*1);
            };
            //各品种面积总和
            ////console.log('1111');
            sums2[0]=num2;sums2[1]=num8;sums2[2]=num15;sums2[3]=num21;
            total[0]=num3;total[1]=num4;total[2]=num5;total[3]=num6;total[4]=num7;  total[5]=num9;total[6]=num10;total[7]=num11;total[8]=num12;total[9]=num13;
            total[10]=num16;total[11]=num17;total[12]=num18;total[13]=num19;total[14]=num20;   total[15]=num22;total[16]=num23;total[17]=num24;total[18]=num25;total[19]=num26;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(num1===0){
                total[i]='--';
                sums2[i]='--'
              }else if(total[i] === 0){
                sums1[i]='--';
              }else{
                sums1[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums1);
            for(var i=0;i<sums2.length;i++){
              if(sums2[i] === 0){
                sums2[i]='--';
              }
            }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":num1,
                                "field_3_1":sums2[0],"field_3_2":sums1[0],"field_3_3":sums1[1],"field_3_4":sums1[2],"field_3_5":sums1[3],"field_3_6":sums1[4],
                                "field_4_1":sums2[1],"field_4_2":sums1[5],"field_4_3":sums1[6],"field_4_4":sums1[7],"field_4_5":sums1[8],"field_4_6":sums1[9],
                                "field_5":num14,
                                "field_6_1":sums2[2],"field_6_2":sums1[10],"field_6_3":sums1[11],"field_6_4":sums1[12],"field_6_5":sums1[13],"field_6_6":sums1[14],
                                "field_7_1":sums2[3],"field_7_2":sums1[15],"field_7_3":sums1[16],"field_7_4":sums1[17],"field_7_5":sums1[18],"field_7_6":sums1[19],
                                "in_use":'--'})
          }else{ 
          };  
          _this.tableDataTwo71 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo72(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo72',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums1 = [];//存平均和
            var mean = [];//存平均
            var sums2 = [];//存面积合计
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_3 === ''){
                totalTableData[i].field_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_3*1);

              if(totalTableData[i].field_4_2_1 === ''){
                totalTableData[i].field_4_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_4_2_1*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5 === ''){
                totalTableData[i].field_7_5 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_7_6*1);

            };
            //各品种面积总和
            ////console.log('1111');
            sums2[0]=num5;sums2[1]=num6;sums2[2]=num7;sums2[3]=num8;sums2[4]=num9;sums2[5]=num10;
            total[0]=num2;total[1]=num3;total[2]=num4;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<3; i++){
              if(num1===0){
                total[i]='--';
                sums1[i]='--';
                sums2[i]='--';
              }else if(total[i] === 0){
                sums1[i]='--';
                total[i]='--';
              }else{
                sums1[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+'%';
              }
            }
            for(var i=0; i<6;i++){
              if(sums2[i] != '--'){
                if(sums2[i]!=0){
                  mean[i] = (Number(sums2[i])/Number(dataLength)).toFixed(2);
                }else{
                  sums2[i]='--';
                  mean[i]='--';
                }
              }
            }
            ////console.log(sums1);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":num1,
                                "field_4_1":'--',"field_4_2_1":total[0],"field_4_2_2":sums1[0],"field_5_1":'--',"field_5_2_1":total[1],"field_5_2_2":sums1[1],
                                "field_6_1":'--',"field_6_2_1":total[2],"field_6_2_2":sums1[2],
                                "field_7_1":sums2[0],"field_7_2":mean[1],"field_7_3":mean[2],"field_7_4":mean[3],"field_7_5":mean[4],"field_7_6":mean[5],
                                "in_use":'--'})
          }else{ 
          };
          _this.tableDataTwo72 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo73(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo73',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums1 = [];//存平均和
            var mean = [];//存平均
            var sums2 = [];//存面积合计
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_3 === ''){
                totalTableData[i].field_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_3*1);

              if(totalTableData[i].field_4_2_1 === ''){
                totalTableData[i].field_4_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_4_2_1*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_8_5 === ''){
                totalTableData[i].field_8_5 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_8_5*1);

              if(totalTableData[i].field_8_6 === ''){
                totalTableData[i].field_8_6 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_6*1);

            };
            //各品种面积总和
            ////console.log('1111');
            sums2[0]=num6;sums2[1]=num7;sums2[2]=num8;sums2[3]=num9;sums2[4]=num10;sums2[5]=num11;
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<5; i++){
              if(num1===0){
                total[i]='--';
                sums1[i]='--';
                sums2[i]='--';
              }else if(total[i] === 0){
                sums1[i]='--';
                total[i]='--';
              }else{
                sums1[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+'%';
              }
            }
            for(var i=0; i<6;i++){
              if(sums2[i] != '--'){
                if(sums2[i]!=0){
                  mean[i] = (Number(sums2[i])/Number(dataLength)).toFixed(2);
                }else{
                  sums2[i]='--';
                  mean[i]='--';
                }
              }
            }
            ////console.log(sums1);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":num1,
                                "field_4_1":'--',"field_4_2_1":total[0],"field_4_2_2":sums1[0],"field_5_1":'--',"field_5_2_1":total[1],"field_5_2_2":sums1[1],
                                "field_6_1":'--',"field_6_2_1":total[2],"field_6_2_2":sums1[2],"field_7_1":'--',"field_7_2_1":total[3],"field_7_2_2":sums1[3],
                                "field_8_1":sums2[0],"field_8_2":mean[1],"field_8_3":mean[2],"field_8_4":mean[3],"field_8_5":mean[4],"field_8_6":mean[5],
                                "in_use":'--'})
          }else{ 
          }
          _this.tableDataTwo73 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo74(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo74',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums1 = [];//存平均
            var sums2 = [];//存面积合计
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
            var num31=0;var num32=0;var num33=0;var num34=0;var num35=0;var num36=0;var num37=0;var num38=0;var num39=0;var num40=0;
            var num41=0;var num42=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4_1 === ''){
                totalTableData[i].field_4_1 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_4_1*1);

              if(totalTableData[i].field_4_2 === ''){
                totalTableData[i].field_4_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_4_2*1);

              if(totalTableData[i].field_4_3 === ''){
                totalTableData[i].field_4_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_4_3*1);

              if(totalTableData[i].field_4_4 === ''){
                totalTableData[i].field_4_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_4_4*1);

              if(totalTableData[i].field_4_5 === ''){
                totalTableData[i].field_4_5 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_4_5*1);

              if(totalTableData[i].field_4_6 === ''){
                totalTableData[i].field_4_6 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_4_6*1);

              if(totalTableData[i].field_5_1 === ''){
                totalTableData[i].field_5_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_5_1*1);

              if(totalTableData[i].field_5_2 === ''){
                totalTableData[i].field_5_2 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_5_2*1);

              if(totalTableData[i].field_5_3 === ''){
                totalTableData[i].field_5_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_5_3*1);

              if(totalTableData[i].field_5_4 === ''){
                totalTableData[i].field_5_4 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_5_4*1);

              if(totalTableData[i].field_5_5 === ''){
                totalTableData[i].field_5_5 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_5_5*1);

              if(totalTableData[i].field_5_6 === ''){
                totalTableData[i].field_5_6 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_5_6*1);

              if(totalTableData[i].field_6_1 === ''){
                totalTableData[i].field_6_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_6_1*1);

              if(totalTableData[i].field_6_2 === ''){
                totalTableData[i].field_6_2 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_6_2*1);

              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].field_6_4 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_6_5 === ''){
                totalTableData[i].field_6_5 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_6_5*1);

              if(totalTableData[i].field_6_6 === ''){
                totalTableData[i].field_6_6 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_6_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_7_2*1);
              
              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5 === ''){
                totalTableData[i].field_7_5 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_7_6*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_8_5=== ''){
                totalTableData[i].field_8_5 = 0;
              }
              num29 = _this.add(num29*1+totalTableData[i].field_8_5*1);

              if(totalTableData[i].field_8_6 === ''){
                totalTableData[i].field_8_6 = 0;
              }
              num30 = _this.add(num30*1+totalTableData[i].field_8_6*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num31 = _this.add(num31*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num32 = _this.add(num32*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num33 = _this.add(num33*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num34 = _this.add(num34*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_9_5 === ''){
                totalTableData[i].field_9_5 = 0;
              }
              num35 = _this.add(num35*1+totalTableData[i].field_9_5*1);

              if(totalTableData[i].field_9_6 === ''){
                totalTableData[i].field_9_6 = 0;
              }
              num36 = _this.add(num36*1+totalTableData[i].field_9_6*1);

              if(totalTableData[i].field_10_1 === ''){
                totalTableData[i].field_10_1 = 0;
              }
              num37 = _this.add(num37*1+totalTableData[i].field_10_1*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num38 = _this.add(num38*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num39 = _this.add(num39*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num40 = _this.add(num40*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_10_5 === ''){
                totalTableData[i].field_10_5 = 0;
              }
              num41 = _this.add(num41*1+totalTableData[i].field_10_5*1);

              if(totalTableData[i].field_10_6 === ''){
                totalTableData[i].field_10_6 = 0;
              }
              num42 = _this.add(num42*1+totalTableData[i].field_10_6*1);
            };
            //各品种面积总和
            ////console.log('1111');
            sums2[0]=num1;sums2[1]=num7;sums2[2]=num13;sums2[3]=num19;sums2[4]=num25;sums2[5]=num31;sums2[6]=num37;
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num8;total[6]=num9;total[7]=num10;total[8]=num11;total[9]=num12;
            total[10]=num14;total[11]=num15;total[12]=num16;total[13]=num17;total[14]=num18;   total[15]=num20;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
            total[20]=num26;total[21]=num27;total[22]=num28;total[23]=num29;total[24]=num30;   total[25]=num32;total[26]=num33;total[27]=num34;total[28]=num35;total[29]=num36;
            total[30]=num38;total[31]=num39;total[32]=num40;total[33]=num41;total[34]=num42;
            ////console.log(sums2);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(num1===0){
                total[i]='--';
                sums2[i]='--';
                sums1[i]='--';
              }else if(total[i] === 0){
                sums1[i]='--';
              }else{
                sums1[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums1);
            for(var i=0;i<sums2.length;i++){
                if(sums2[i] === 0){
                  sums2[i]='--';
                }
              }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',
                                "field_4_1":sums2[0],"field_4_2":sums1[0],"field_4_3":sums1[1],"field_4_4":sums1[2],"field_4_5":sums1[3],"field_4_6":sums1[4],
                                "field_5_1":sums2[1],"field_5_2":sums1[5],"field_5_3":sums1[6],"field_5_4":sums1[7],"field_5_5":sums1[8],"field_5_6":sums1[9],
                                "field_6_1":sums2[2],"field_6_2":sums1[10],"field_6_3":sums1[11],"field_6_4":sums1[12],"field_6_5":sums1[13],"field_6_6":sums1[14],
                                "field_7_1":sums2[3],"field_7_2":sums1[15],"field_7_3":sums1[16],"field_7_4":sums1[17],"field_7_5":sums1[18],"field_7_6":sums1[19],
                                "field_8_1":sums2[4],"field_8_2":sums1[20],"field_8_3":sums1[21],"field_8_4":sums1[22],"field_8_5":sums1[23],"field_8_6":sums1[24],
                                "field_9_1":sums2[5],"field_9_2":sums1[25],"field_9_3":sums1[26],"field_9_4":sums1[27],"field_9_5":sums1[28],"field_9_6":sums1[29],
                                "field_10_1":sums2[6],"field_10_2":sums1[30],"field_10_3":sums1[31],"field_10_4":sums1[32],"field_10_5":sums1[33],"field_10_6":sums1[34],
                                "in_use":'--'}) 
          }else{ 
          };  
          _this.tableDataTwo74 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo81(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo81',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          var tempData=res.data;
         //处理合计
         if(tempData.length !=0){
            var plantTemp=_this.plantWays2;
            var totalTemp=tempData;
            var sumTemp=[];
            for(var j=0; j<plantTemp.length; j++){
              var sums=[];
              var total=[];
                var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                var num31=0;var num32=0;
              
                for(var i=0; i<totalTemp.length; i++){
                  if(plantTemp[j].value === tempData[i].field_7){
                      if(totalTemp[i].field_6 === ''){
                          totalTemp[i].field_6 = 0;
                        }
                      num1 = _this.add(num1*1+totalTemp[i].field_6*1);

                      if(totalTemp[i].field_8 === ''){
                          totalTemp[i].field_8 = 0;
                        }
                      num2 = _this.add(num2*1+totalTemp[i].field_8*1);

                      if(totalTemp[i].field_9 === ''){
                        totalTemp[i].field_9 = 0;
                      }
                      num3 = _this.add(num3*1+totalTemp[i].field_9*1);

                      if(totalTemp[i].field_10 === ''){
                        totalTemp[i].field_10 = 0;
                      }
                      num4 = _this.add(num4*1+totalTemp[i].field_10*1);

                      if(totalTemp[i].field_11_1_1 === ''){
                        totalTemp[i].field_11_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+totalTemp[i].field_11_1_1*1);

                      if(totalTemp[i].field_11_1_2 === ''){
                        totalTemp[i].field_11_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+totalTemp[i].field_11_1_2*1);

                      if(totalTemp[i].field_11_2_1 === ''){
                        totalTemp[i].field_11_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+totalTemp[i].field_11_2_1*1);

                      if(totalTemp[i].field_11_2_2 === ''){
                        totalTemp[i].field_11_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+totalTemp[i].field_11_2_2*1);

                      if(totalTemp[i].field_11_3_1 === ''){
                        totalTemp[i].field_11_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+totalTemp[i].field_11_3_1*1);

                      if(totalTemp[i].field_11_3_2 === ''){
                        totalTemp[i].field_11_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+totalTemp[i].field_11_3_2*1);

                      if(totalTemp[i].field_11_4_1 === ''){
                        totalTemp[i].field_11_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+totalTemp[i].field_11_4_1*1);

                      if(totalTemp[i].field_11_4_2 === ''){
                        totalTemp[i].field_11_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+totalTemp[i].field_11_4_2*1);

                      if(totalTemp[i].field_11_5_1 === ''){
                        totalTemp[i].field_11_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+totalTemp[i].field_11_5_1*1);

                      if(totalTemp[i].field_11_5_2 === ''){
                        totalTemp[i].field_11_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+totalTemp[i].field_11_5_2*1);

                      if(totalTemp[i].field_11_6_1 === ''){
                        totalTemp[i].field_11_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+totalTemp[i].field_11_6_1*1);

                      if(totalTemp[i].field_11_6_2 === ''){
                        totalTemp[i].field_11_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+totalTemp[i].field_11_6_2*1);

                      if(totalTemp[i].field_12_1 === ''){
                        totalTemp[i].field_12_1 = 0;
                      }
                      num17 = _this.add(num17*1+totalTemp[i].field_12_1*1);

                      if(totalTemp[i].field_12_2_2 === ''){
                        totalTemp[i].field_12_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+totalTemp[i].field_12_2_2*1);

                      if(totalTemp[i].field_12_2_3 === ''){
                        totalTemp[i].field_12_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+totalTemp[i].field_12_2_3*1);

                      if(totalTemp[i].field_12_2_4 === ''){
                        totalTemp[i].field_12_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+totalTemp[i].field_12_2_4*1);
                      
                      if(totalTemp[i].field_12_3 === ''){
                        totalTemp[i].field_12_3 = 0;
                      }
                      num21 = _this.add(num21*1+totalTemp[i].field_12_3*1);

                      if(totalTemp[i].field_12_4 === ''){
                        totalTemp[i].field_12_4 = 0;
                      }
                      num22 = _this.add(num22*1+totalTemp[i].field_12_4*1);

                      if(totalTemp[i].field_12_5 === ''){
                        totalTemp[i].field_12_5 = 0;
                      }
                      num23 = _this.add(num23*1+totalTemp[i].field_12_5*1);

                      if(totalTemp[i].field_12_6 === ''){
                        totalTemp[i].field_12_6 = 0;
                      }
                      num24 = _this.add(num24*1+totalTemp[i].field_12_6*1);

                      if(totalTemp[i].field_12_7 === ''){
                        totalTemp[i].field_12_7 = 0;
                      }
                      num25 = _this.add(num25*1+totalTemp[i].field_12_7*1);

                      if(totalTemp[i].field_12_8 === ''){
                        totalTemp[i].field_12_8 = 0;
                      }
                      num26 = _this.add(num26*1+totalTemp[i].field_12_8*1);

                      if(totalTemp[i].field_12_9 === ''){
                        totalTemp[i].field_12_9 = 0;
                      }
                      num27 = _this.add(num27*1+totalTemp[i].field_12_9*1);

                      if(totalTemp[i].field_12_10 === ''){
                        totalTemp[i].field_12_10 = 0;
                      }
                      num28 = _this.add(num28*1+totalTemp[i].field_12_10*1);

                      if(totalTemp[i].field_13 === ''){
                        totalTemp[i].field_13 = 0;
                      }
                      num29 = _this.add(num29*1+totalTemp[i].field_13*1);

                      if(totalTemp[i].field_14 === ''){
                        totalTemp[i].field_14 = 0;
                      }
                      num30 = _this.add(num30*1+totalTemp[i].field_14*1);

                      if(totalTemp[i].field_15 === ''){
                        totalTemp[i].field_15 = 0;
                      }
                      num31 = _this.add(num31*1+totalTemp[i].field_15*1);

                      if(totalTemp[i].field_16 === ''){
                        totalTemp[i].field_16 = 0;
                      }
                      num32 = _this.add(num32*1+totalTemp[i].field_16*1);
                  };
                };

                if(num1 !=0){//用代表面积来判断栽植方式存在哪些
                    sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                    total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                    total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                    total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30;
                    sumTemp.push({"field_1":"合计","field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":sums[0],"field_7":plantTemp[j].value,"field_8":total[0],"field_9":total[1],"field_10":total[2],
                                  "field_11_1_1":total[3],"field_11_1_2":total[4],"field_11_2_1":total[5],"field_11_2_2":total[6],"field_11_3_1":total[7],"field_11_3_2":total[8],
                                  "field_11_4_1":total[9],"field_11_4_2":total[10],"field_11_5_1":total[11],"field_11_5_2":total[12],"field_11_6_1":sums[1],"field_11_6_2":sums[2],
                                  "field_12_1":total[13],"field_12_2_1":"--","field_12_2_2":total[14],"field_12_2_3":total[15],"field_12_2_4":sums[3],
                                  "field_12_3":total[16],"field_12_4":total[17],"field_12_5":total[18],"field_12_6":total[19],"field_12_7":total[20],"field_12_8":total[21],
                                  "field_12_9":total[22],"field_12_10":sums[4],"field_13":total[23],"field_14":total[24],"field_15":sums[5],"field_16":sums[6],
                                  "in_use":'--'}); 
                };
               
            };
            ////console.log(sumTemp);
         };
         //数据计算处理
         if(tempData.length != 0){
            var countyTemp=_this.countys2;
            ////console.log(countyTemp);
            var secondTemp=[];
            for(var i=0; i<countyTemp.length; i++){           //获取各个县市独立地数据
              var temp=[];
              for(var j=0; j<tempData.length; j++){
                if(countyTemp[i].value === tempData[j].field_1){
                  temp.push(tempData[j]);
                };
              };
              secondTemp.push(temp);
            };
            secondTemp.push(sumTemp);
            ////console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp !=''){
                  var county=thirdTemp[0].field_1;
                  var sums=[];
                  var total=[];
                  var mean=[];//存加权平均
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                  var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                  var num31=0;var num32=0;
                  for(var i=0; i<thirdTemp.length;i++){
                    if(thirdTemp[i].field_6 === ''){
                        thirdTemp[i].field_6 = 0;
                      }
                      num1 = _this.add(num1*1+thirdTemp[i].field_6*1);

                    if(thirdTemp[i].field_8 === ''){
                        thirdTemp[i].field_8 = 0;
                      }
                      num2 = _this.add(num2*1+thirdTemp[i].field_6*thirdTemp[i].field_8);

                      if(thirdTemp[i].field_9 === ''){
                        thirdTemp[i].field_9 = 0;
                      }
                      num3 = _this.add(num3*1+thirdTemp[i].field_9*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_10 === ''){
                        thirdTemp[i].field_10 = 0;
                      }
                      num4 = _this.add(num4*1+thirdTemp[i].field_10*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_1_1 === ''){
                        thirdTemp[i].field_11_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+thirdTemp[i].field_11_1_1*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_1_2 === ''){
                        thirdTemp[i].field_11_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+thirdTemp[i].field_11_1_2*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_2_1 === ''){
                        thirdTemp[i].field_11_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+thirdTemp[i].field_11_2_1*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_2_2 === ''){
                        thirdTemp[i].field_11_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+thirdTemp[i].field_11_2_2*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_3_1 === ''){
                        thirdTemp[i].field_11_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+thirdTemp[i].field_11_3_1*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_3_2 === ''){
                        thirdTemp[i].field_11_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+thirdTemp[i].field_11_3_2*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_4_1 === ''){
                        thirdTemp[i].field_11_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+thirdTemp[i].field_11_4_1*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_4_2 === ''){
                        thirdTemp[i].field_11_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+thirdTemp[i].field_11_4_2*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_5_1 === ''){
                        thirdTemp[i].field_11_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+thirdTemp[i].field_11_5_1*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_5_2 === ''){
                        thirdTemp[i].field_11_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+thirdTemp[i].field_11_5_2*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_11_6_1 === ''){
                        thirdTemp[i].field_11_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+thirdTemp[i].field_11_6_1*1);

                      if(thirdTemp[i].field_11_6_2 === ''){
                        thirdTemp[i].field_11_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+thirdTemp[i].field_11_6_2*1);

                      if(thirdTemp[i].field_12_1 === ''){
                        thirdTemp[i].field_12_1 = 0;
                      }
                      num17 = _this.add(num17*1+thirdTemp[i].field_12_1*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_2_2 === ''){
                        thirdTemp[i].field_12_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+thirdTemp[i].field_12_2_2*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_2_3 === ''){
                        thirdTemp[i].field_12_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+thirdTemp[i].field_12_2_3*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_2_4 === ''){
                        thirdTemp[i].field_12_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+thirdTemp[i].field_12_2_4*1);
                      
                      if(thirdTemp[i].field_12_3 === ''){
                        thirdTemp[i].field_12_3 = 0;
                      }
                      num21 = _this.add(num21*1+thirdTemp[i].field_12_3*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_4 === ''){
                        thirdTemp[i].field_12_4 = 0;
                      }
                      num22 = _this.add(num22*1+thirdTemp[i].field_12_4*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_5 === ''){
                        thirdTemp[i].field_12_5 = 0;
                      }
                      num23 = _this.add(num23*1+thirdTemp[i].field_12_5*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_6 === ''){
                        thirdTemp[i].field_12_6 = 0;
                      }
                      num24 = _this.add(num24*1+thirdTemp[i].field_12_6*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_7 === ''){
                        thirdTemp[i].field_12_7 = 0;
                      }
                      num25 = _this.add(num25*1+thirdTemp[i].field_12_7*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_8 === ''){
                        thirdTemp[i].field_12_8 = 0;
                      }
                      num26 = _this.add(num26*1+thirdTemp[i].field_12_8*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_9 === ''){
                        thirdTemp[i].field_12_9 = 0;
                      }
                      num27 = _this.add(num27*1+thirdTemp[i].field_12_9*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_12_10 === ''){
                        thirdTemp[i].field_12_10 = 0;
                      }
                      num28 = _this.add(num28*1+thirdTemp[i].field_12_10*1);

                      if(thirdTemp[i].field_13 === ''){
                        thirdTemp[i].field_13 = 0;
                      }
                      num29 = _this.add(num29*1+thirdTemp[i].field_13*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_14 === ''){
                        thirdTemp[i].field_14 = 0;
                      }
                      num30 = _this.add(num30*1+thirdTemp[i].field_14*thirdTemp[i].field_6);

                      if(thirdTemp[i].field_15 === ''){
                        thirdTemp[i].field_15 = 0;
                      }
                      num31 = _this.add(num31*1+thirdTemp[i].field_15*1);

                      if(thirdTemp[i].field_16 === ''){
                        thirdTemp[i].field_16 = 0;
                      }
                      num32 = _this.add(num32*1+thirdTemp[i].field_16*1);
                  };
                  sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                  total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                  total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                  total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30; 
                  for(var i=0; i<total.length; i++){
                      if(num1===0){
                        sums[i]=0;
                        mean[i]=0;
                      }else{
                        mean[i] = (Number(total[i])/Number(num1)).toFixed(2);
                      };
                  };
                  ////console.log(total);
                  ////console.log(mean);
                  thirdTemp.push({"field_1":county,"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'小计',"field_8":mean[0],"field_9":mean[1],"field_10":mean[2],
                                  "field_11_1_1":mean[3],"field_11_1_2":mean[4],"field_11_2_1":mean[5],"field_11_2_2":mean[6],"field_11_3_1":mean[7],"field_11_3_2":mean[8],
                                  "field_11_4_1":mean[9],"field_11_4_2":mean[10],"field_11_5_1":mean[11],"field_11_5_2":mean[12],"field_11_6_1":sums[1],"field_11_6_2":sums[2],
                                  "field_12_1":mean[13],"field_12_2_1":"--","field_12_2_2":mean[14],"field_12_2_3":mean[15],"field_12_2_4":sums[3],
                                  "field_12_3":mean[16],"field_12_4":mean[17],"field_12_5":mean[18],"field_12_6":mean[19],"field_12_7":mean[20],"field_12_8":mean[21],
                                  "field_12_9":mean[22],"field_12_10":sums[4],"field_13":mean[23],"field_14":mean[24],"field_15":sums[5],"field_16":sums[6],
                                  "in_use":'--'}); 
                  
                  fourTemp = fourTemp.concat(thirdTemp);
                  
              }
            };
            //fourTemp = fourTemp.concat(sumTemp);
            tempData=fourTemp;
         }else{
         };
          //////console.log(fourTemp);
          _this.tableDataTwo81 = tempData;
          //////console.log(tempData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo82(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo82',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          var tempData=res.data;
         //处理合计
         if(tempData.length !=0){
            var plantTemp=_this.plantWays2;
            var totalTemp=tempData;
            var sumTemp=[];
            for(var j=0; j<plantTemp.length; j++){
              var sums=[];
              var total=[];
                var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                var num31=0;var num32=0;
              
                for(var i=0; i<totalTemp.length; i++){
                  if(plantTemp[j].value === tempData[i].field_6){
                      if(totalTemp[i].field_5 === ''){
                          totalTemp[i].field_5 = 0;
                        }
                      num1 = _this.add(num1*1+totalTemp[i].field_5*1);

                      if(totalTemp[i].field_7 === ''){
                          totalTemp[i].field_7 = 0;
                        }
                      num2 = _this.add(num2*1+totalTemp[i].field_7*1);

                      if(totalTemp[i].field_8 === ''){
                        totalTemp[i].field_8 = 0;
                      }
                      num3 = _this.add(num3*1+totalTemp[i].field_8*1);

                      if(totalTemp[i].field_9 === ''){
                        totalTemp[i].field_9 = 0;
                      }
                      num4 = _this.add(num4*1+totalTemp[i].field_9*1);

                      if(totalTemp[i].field_10_1_1 === ''){
                        totalTemp[i].field_10_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+totalTemp[i].field_10_1_1*1);

                      if(totalTemp[i].field_10_1_2 === ''){
                        totalTemp[i].field_10_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+totalTemp[i].field_10_1_2*1);

                      if(totalTemp[i].field_10_2_1 === ''){
                        totalTemp[i].field_10_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+totalTemp[i].field_10_2_1*1);

                      if(totalTemp[i].field_10_2_2 === ''){
                        totalTemp[i].field_10_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+totalTemp[i].field_10_2_2*1);

                      if(totalTemp[i].field_10_3_1 === ''){
                        totalTemp[i].field_10_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+totalTemp[i].field_10_3_1*1);

                      if(totalTemp[i].field_10_3_2 === ''){
                        totalTemp[i].field_10_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+totalTemp[i].field_10_3_2*1);

                      if(totalTemp[i].field_10_4_1 === ''){
                        totalTemp[i].field_10_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+totalTemp[i].field_10_4_1*1);

                      if(totalTemp[i].field_10_4_2 === ''){
                        totalTemp[i].field_10_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+totalTemp[i].field_10_4_2*1);

                      if(totalTemp[i].field_10_5_1 === ''){
                        totalTemp[i].field_10_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+totalTemp[i].field_10_5_1*1);

                      if(totalTemp[i].field_10_5_2 === ''){
                        totalTemp[i].field_10_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+totalTemp[i].field_10_5_2*1);

                      if(totalTemp[i].field_10_6_1 === ''){
                        totalTemp[i].field_10_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+totalTemp[i].field_10_6_1*1);

                      if(totalTemp[i].field_10_6_2 === ''){
                        totalTemp[i].field_10_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+totalTemp[i].field_10_6_2*1);

                      if(totalTemp[i].field_12_1 === ''){
                        totalTemp[i].field_12_1 = 0;
                      }
                      num17 = _this.add(num17*1+totalTemp[i].field_12_1*1);

                      if(totalTemp[i].field_11_2_2 === ''){
                        totalTemp[i].field_11_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+totalTemp[i].field_11_2_2*1);

                      if(totalTemp[i].field_11_2_3 === ''){
                        totalTemp[i].field_11_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+totalTemp[i].field_11_2_3*1);

                      if(totalTemp[i].field_11_2_4 === ''){
                        totalTemp[i].field_11_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+totalTemp[i].field_11_2_4*1);
                      
                      if(totalTemp[i].field_11_3 === ''){
                        totalTemp[i].field_11_3 = 0;
                      }
                      num21 = _this.add(num21*1+totalTemp[i].field_11_3*1);

                      if(totalTemp[i].field_11_4 === ''){
                        totalTemp[i].field_11_4 = 0;
                      }
                      num22 = _this.add(num22*1+totalTemp[i].field_11_4*1);

                      if(totalTemp[i].field_11_5 === ''){
                        totalTemp[i].field_11_5 = 0;
                      }
                      num23 = _this.add(num23*1+totalTemp[i].field_11_5*1);

                      if(totalTemp[i].field_11_6 === ''){
                        totalTemp[i].field_11_6 = 0;
                      }
                      num24 = _this.add(num24*1+totalTemp[i].field_11_6*1);

                      if(totalTemp[i].field_11_7 === ''){
                        totalTemp[i].field_11_7 = 0;
                      }
                      num25 = _this.add(num25*1+totalTemp[i].field_11_7*1);

                      if(totalTemp[i].field_11_8 === ''){
                        totalTemp[i].field_11_8 = 0;
                      }
                      num26 = _this.add(num26*1+totalTemp[i].field_11_8*1);

                      if(totalTemp[i].field_11_9 === ''){
                        totalTemp[i].field_11_9 = 0;
                      }
                      num27 = _this.add(num27*1+totalTemp[i].field_11_9*1);

                      if(totalTemp[i].field_11_10 === ''){
                        totalTemp[i].field_11_10 = 0;
                      }
                      num28 = _this.add(num28*1+totalTemp[i].field_11_10*1);

                      if(totalTemp[i].field_12 === ''){
                        totalTemp[i].field_12 = 0;
                      }
                      num29 = _this.add(num29*1+totalTemp[i].field_12*1);

                      if(totalTemp[i].field_13 === ''){
                        totalTemp[i].field_13 = 0;
                      }
                      num30 = _this.add(num30*1+totalTemp[i].field_13*1);

                      if(totalTemp[i].field_14 === ''){
                        totalTemp[i].field_14 = 0;
                      }
                      num31 = _this.add(num31*1+totalTemp[i].field_14*1);

                      if(totalTemp[i].field_15 === ''){
                        totalTemp[i].field_15 = 0;
                      }
                      num32 = _this.add(num32*1+totalTemp[i].field_15*1);
                  };
                };

                if(num1 !=0){//用代表面积来判断栽植方式存在哪些
                    sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                    total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                    total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                    total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30;
                    sumTemp.push({"field_1":"合计","field_2":'--',"field_3":'--',"field_4":'--',"field_5":sums[0],"field_6":plantTemp[j].value,"field_7":total[0],"field_8":total[1],"field_9":total[2],
                                  "field_10_1_1":total[3],"field_10_1_2":total[4],"field_10_2_1":total[5],"field_10_2_2":total[6],"field_10_3_1":total[7],"field_10_3_2":total[8],
                                  "field_10_4_1":total[9],"field_10_4_2":total[10],"field_10_5_1":total[11],"field_10_5_2":total[12],"field_10_6_1":sums[1],"field_10_6_2":sums[2],
                                  "field_11_1":total[13],"field_11_2_1":"--","field_11_2_2":total[14],"field_11_2_3":total[15],"field_11_2_4":sums[3],
                                  "field_11_3":total[16],"field_11_4":total[17],"field_11_5":total[18],"field_11_6":total[19],"field_11_7":total[20],"field_11_8":total[21],
                                  "field_11_9":total[22],"field_11_10":sums[4],"field_12":total[23],"field_13":total[24],"field_14":sums[5],"field_15":sums[6],
                                  "in_use":'--'}); 
                };
               
            };
            ////console.log(sumTemp);
         };
         //数据计算处理
         if(tempData.length != 0){
            var countyTemp=_this.countys2;
            ////console.log(countyTemp);
            var secondTemp=[];
            for(var i=0; i<countyTemp.length; i++){           //获取各个县市独立地数据
              var temp=[];
              for(var j=0; j<tempData.length; j++){
                if(countyTemp[i].value === tempData[j].field_1){
                  temp.push(tempData[j]);
                };
              };
              secondTemp.push(temp);
            };
            secondTemp.push(sumTemp);
            ////console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp !=''){
                  var county=thirdTemp[0].field_1;
                  var sums=[];
                  var total=[];
                  var mean=[];//存加权平均
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                  var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                  var num31=0;var num32=0;
                  for(var i=0; i<thirdTemp.length;i++){
                    if(thirdTemp[i].field_5 === ''){
                        thirdTemp[i].field_5 = 0;
                      }
                      num1 = _this.add(num1*1+thirdTemp[i].field_5*1);

                    if(thirdTemp[i].field_7 === ''){
                        thirdTemp[i].field_7 = 0;
                      }
                      num2 = _this.add(num2*1+thirdTemp[i].field_5*thirdTemp[i].field_7);

                      if(thirdTemp[i].field_8 === ''){
                        thirdTemp[i].field_8 = 0;
                      }
                      num3 = _this.add(num3*1+thirdTemp[i].field_8*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_9 === ''){
                        thirdTemp[i].field_9 = 0;
                      }
                      num4 = _this.add(num4*1+thirdTemp[i].field_9*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_1_1 === ''){
                        thirdTemp[i].field_10_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+thirdTemp[i].field_10_1_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_1_2 === ''){
                        thirdTemp[i].field_10_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+thirdTemp[i].field_10_1_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_2_1 === ''){
                        thirdTemp[i].field_10_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+thirdTemp[i].field_10_2_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_2_2 === ''){
                        thirdTemp[i].field_10_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+thirdTemp[i].field_10_2_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_3_1 === ''){
                        thirdTemp[i].field_10_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+thirdTemp[i].field_10_3_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_3_2 === ''){
                        thirdTemp[i].field_10_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+thirdTemp[i].field_10_3_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_4_1 === ''){
                        thirdTemp[i].field_10_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+thirdTemp[i].field_10_4_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_4_2 === ''){
                        thirdTemp[i].field_10_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+thirdTemp[i].field_10_4_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_5_1 === ''){
                        thirdTemp[i].field_10_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+thirdTemp[i].field_10_5_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_5_2 === ''){
                        thirdTemp[i].field_10_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+thirdTemp[i].field_10_5_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_6_1 === ''){
                        thirdTemp[i].field_10_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+thirdTemp[i].field_10_6_1*1);

                      if(thirdTemp[i].field_10_6_2 === ''){
                        thirdTemp[i].field_10_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+thirdTemp[i].field_10_6_2*1);

                      if(thirdTemp[i].field_11_1 === ''){
                        thirdTemp[i].field_11_1 = 0;
                      }
                      num17 = _this.add(num17*1+thirdTemp[i].field_11_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_2 === ''){
                        thirdTemp[i].field_11_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+thirdTemp[i].field_11_2_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_3 === ''){
                        thirdTemp[i].field_11_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+thirdTemp[i].field_11_2_3*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_4 === ''){
                        thirdTemp[i].field_11_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+thirdTemp[i].field_11_2_4*1);
                      
                      if(thirdTemp[i].field_11_3 === ''){
                        thirdTemp[i].field_11_3 = 0;
                      }
                      num21 = _this.add(num21*1+thirdTemp[i].field_11_3*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_4 === ''){
                        thirdTemp[i].field_11_4 = 0;
                      }
                      num22 = _this.add(num22*1+thirdTemp[i].field_11_4*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_5 === ''){
                        thirdTemp[i].field_11_5 = 0;
                      }
                      num23 = _this.add(num23*1+thirdTemp[i].field_11_5*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_6 === ''){
                        thirdTemp[i].field_11_6 = 0;
                      }
                      num24 = _this.add(num24*1+thirdTemp[i].field_11_6*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_7 === ''){
                        thirdTemp[i].field_11_7 = 0;
                      }
                      num25 = _this.add(num25*1+thirdTemp[i].field_11_7*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_8 === ''){
                        thirdTemp[i].field_11_8 = 0;
                      }
                      num26 = _this.add(num26*1+thirdTemp[i].field_11_8*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_9 === ''){
                        thirdTemp[i].field_11_9 = 0;
                      }
                      num27 = _this.add(num27*1+thirdTemp[i].field_11_9*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_10 === ''){
                        thirdTemp[i].field_11_10 = 0;
                      }
                      num28 = _this.add(num28*1+thirdTemp[i].field_11_10*1);

                      if(thirdTemp[i].field_12 === ''){
                        thirdTemp[i].field_12 = 0;
                      }
                      num29 = _this.add(num29*1+thirdTemp[i].field_12*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_13 === ''){
                        thirdTemp[i].field_13 = 0;
                      }
                      num30 = _this.add(num30*1+thirdTemp[i].field_13*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_14 === ''){
                        thirdTemp[i].field_14 = 0;
                      }
                      num31 = _this.add(num31*1+thirdTemp[i].field_14*1);

                      if(thirdTemp[i].field_15 === ''){
                        thirdTemp[i].field_15 = 0;
                      }
                      num32 = _this.add(num32*1+thirdTemp[i].field_15*1);
                  };
                  sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                  total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                  total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                  total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30; 
                  for(var i=0; i<total.length; i++){
                      if(num1===0){
                        sums[i]=0;
                        mean[i]=0;
                      }else{
                        mean[i] = (Number(total[i])/Number(num1)).toFixed(2);
                      };
                  };
                  ////console.log(total);
                  ////console.log(mean);
                  thirdTemp.push({"field_1":county,"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'小计',"field_7":mean[0],"field_8":mean[1],"field_9":mean[2],
                                  "field_10_1_1":mean[3],"field_10_1_2":mean[4],"field_10_2_1":mean[5],"field_10_2_2":mean[6],"field_10_3_1":mean[7],"field_10_3_2":mean[8],
                                  "field_10_4_1":mean[9],"field_10_4_2":mean[10],"field_10_5_1":mean[11],"field_10_5_2":mean[12],"field_10_6_1":sums[1],"field_10_6_2":sums[2],
                                  "field_11_1":mean[13],"field_11_2_1":"--","field_11_2_2":mean[14],"field_11_2_3":mean[15],"field_11_2_4":sums[3],
                                  "field_11_3":mean[16],"field_11_4":mean[17],"field_11_5":mean[18],"field_11_6":mean[19],"field_11_7":mean[20],"field_11_8":mean[21],
                                  "field_11_9":mean[22],"field_11_10":sums[4],"field_12":mean[23],"field_13":mean[24],"field_14":sums[5],"field_15":sums[6],
                                  "in_use":'--'}); 
                  
                  fourTemp = fourTemp.concat(thirdTemp);
                  
              }
            };
            //fourTemp = fourTemp.concat(sumTemp);
            tempData=fourTemp;
         }else{
         };
          _this.tableDataTwo82 = tempData;
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo91(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo91',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          _this.tableDataTwo91=res.data;
          ////console.log(res.data);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo92(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo92',
          method:'post',
          params:{currentYear:this.currentYear,
                  previousYear:this.previousYear,
                  areaType:this.diqu,
                  county:this.xian,
                  year:this.nian}
       }).then(res=>{
         var tempData=res.data;
         var year = _this.currentYear;
         //////console.log(year);
         //处理合计
         if(tempData.length != 0){
           var total = [];
           var mean1 = [];
           var mean2 = [];
           var mean3 = [];
           var sumTemp=[];
           var dataLength1=0;
           var dataLength2=0;
           var yearTemp=tempData;
           var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
           var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
           var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
            var num31=0;var num32=0;var num33=0;var num34=0;var num35=0;var num36=0;var num37=0;var num38=0;var num39=0;var num40=0;
            var num41=0;var num42=0;var num43=0;var num44=0;var num45=0;var num46=0;var num47=0;var num48=0;
           for(var i=0; i<yearTemp.length; i++){
              if(yearTemp[i].field_1_2 == year){
                 dataLength1++;
                if(yearTemp[i].field_2_1 === ''){
                    yearTemp[i].field_2_1=0;
                };
                num1 = _this.add(num1*1+yearTemp[i].field_2_1*1);
                if(yearTemp[i].field_2_2 === ''){
                    yearTemp[i].field_2_2=0;
                };
                num2 = _this.add(num2*1+yearTemp[i].field_2_2*1);
                if(yearTemp[i].field_2_3 === ''){
                    yearTemp[i].field_2_3=0;
                };
                num3 = _this.add(num3*1+yearTemp[i].field_2_3*1);
                if(yearTemp[i].field_2_4 === ''){
                    yearTemp[i].field_2_4=0;
                };
                num4 = _this.add(num4*1+yearTemp[i].field_2_4*1);
                if(yearTemp[i].field_2_5 === ''){
                    yearTemp[i].field_2_5=0;
                };
                num5 = _this.add(num5*1+yearTemp[i].field_2_5*1);
                if(yearTemp[i].field_2_6 === ''){
                    yearTemp[i].field_2_6=0;
                };
                num6 = _this.add(num6*1+yearTemp[i].field_2_6*1);
                if(yearTemp[i].field_3_1 === ''){
                    yearTemp[i].field_3_1=0;
                };
                num7 = _this.add(num7*1+yearTemp[i].field_3_1*1);
                if(yearTemp[i].field_3_2 === ''){
                    yearTemp[i].field_3_2=0;
                };
                num8 = _this.add(num8*1+yearTemp[i].field_3_2*1);
                if(yearTemp[i].field_3_3 === ''){
                    yearTemp[i].field_3_3=0;
                };
                num9 = _this.add(num9*1+yearTemp[i].field_3_3*1);
                if(yearTemp[i].field_3_4 === ''){
                    yearTemp[i].field_3_4=0;
                };
                num10 = _this.add(num10*1+yearTemp[i].field_3_4*1);
                if(yearTemp[i].field_3_5 === ''){
                    yearTemp[i].field_3_5=0;
                };
                num11 = _this.add(num11*1+yearTemp[i].field_3_5*1);
                if(yearTemp[i].field_3_6 === ''){
                    yearTemp[i].field_3_6=0;
                };
                num12 = _this.add(num12*1+yearTemp[i].field_3_6*1);
                if(yearTemp[i].field_4_1 === ''){
                       yearTemp[i].field_4_1=0;
                };
                num13 = _this.add(num13*1+yearTemp[i].field_4_1*1);
                if(yearTemp[i].field_4_2 === ''){
                    yearTemp[i].field_4_2=0;
                };
                num14 = _this.add(num14*1+yearTemp[i].field_4_2*1);
                if(yearTemp[i].field_4_3 === ''){
                    yearTemp[i].field_4_3=0;
                };
                num15 = _this.add(num15*1+yearTemp[i].field_4_3*1);
                if(yearTemp[i].field_4_4 === ''){
                    yearTemp[i].field_4_4=0;
                };
                num16 = _this.add(num16*1+yearTemp[i].field_4_4*1);
                if(yearTemp[i].field_4_5 === ''){
                    yearTemp[i].field_4_5=0;
                };
                num17 = _this.add(num17*1+yearTemp[i].field_4_5*1);
                if(yearTemp[i].field_4_6 === ''){
                    yearTemp[i].field_4_6=0;
                };
                num18 = _this.add(num18*1+yearTemp[i].field_4_6*1);
                if(yearTemp[i].field_5_1 === ''){
                    yearTemp[i].field_5_1=0;
                };
                num19 = _this.add(num19*1+yearTemp[i].field_5_1*1);
                if(yearTemp[i].field_5_2 === ''){
                    yearTemp[i].field_5_2=0;
                };
                num20 = _this.add(num20*1+yearTemp[i].field_5_2*1);
                if(yearTemp[i].field_5_3 === ''){
                    yearTemp[i].field_5_3=0;
                };
                num21 = _this.add(num21*1+yearTemp[i].field_5_3*1);
                if(yearTemp[i].field_5_4 === ''){
                    yearTemp[i].field_5_4=0;
                };
                num22 = _this.add(num22*1+yearTemp[i].field_5_4*1);
                if(yearTemp[i].field_5_5 === ''){
                    yearTemp[i].field_5_5=0;
                };
                num23 = _this.add(num23*1+yearTemp[i].field_5_5*1);
                if(yearTemp[i].field_5_6 === ''){
                    yearTemp[i].field_5_6=0;
                };
                num24 = _this.add(num24*1+yearTemp[i].field_5_6*1);
              }else{
                dataLength2++;
                if(yearTemp[i].field_2_1 === ''){
                    yearTemp[i].field_2_1=0;
                };
                num25 = _this.add(num25*1+yearTemp[i].field_2_1*1);
                if(yearTemp[i].field_2_2 === ''){
                    yearTemp[i].field_2_2=0;
                };
                num26 = _this.add(num26*1+yearTemp[i].field_2_2*1);
                if(yearTemp[i].field_2_3 === ''){
                    yearTemp[i].field_2_3=0;
                };
                num27 = _this.add(num27*1+yearTemp[i].field_2_3*1);
                if(yearTemp[i].field_2_4 === ''){
                    yearTemp[i].field_2_4=0;
                };
                num28 = _this.add(num28*1+yearTemp[i].field_2_4*1);
                if(yearTemp[i].field_2_5 === ''){
                    yearTemp[i].field_2_5=0;
                };
                num29 = _this.add(num29*1+yearTemp[i].field_2_5*1);
                if(yearTemp[i].field_2_6 === ''){
                    yearTemp[i].field_2_6=0;
                };
                num30 = _this.add(num30*1+yearTemp[i].field_2_6*1);
                if(yearTemp[i].field_3_1 === ''){
                    yearTemp[i].field_3_1=0;
                };
                num31 = _this.add(num31*1+yearTemp[i].field_3_1*1);
                if(yearTemp[i].field_3_2 === ''){
                    yearTemp[i].field_3_2=0;
                };
                num32 = _this.add(num32*1+yearTemp[i].field_3_2*1);
                if(yearTemp[i].field_3_3 === ''){
                    yearTemp[i].field_3_3=0;
                };
                num33 = _this.add(num33*1+yearTemp[i].field_3_3*1);
                if(yearTemp[i].field_3_4 === ''){
                    yearTemp[i].field_3_4=0;
                };
                num34 = _this.add(num34*1+yearTemp[i].field_3_4*1);
                if(yearTemp[i].field_3_5 === ''){
                    yearTemp[i].field_3_5=0;
                };
                num35 = _this.add(num35*1+yearTemp[i].field_3_5*1);
                if(yearTemp[i].field_3_6 === ''){
                    yearTemp[i].field_3_6=0;
                };
                num36 = _this.add(num36*1+yearTemp[i].field_3_6*1);
                if(yearTemp[i].field_4_1 === ''){
                       yearTemp[i].field_4_1=0;
                };
                num37= _this.add(num37*1+yearTemp[i].field_4_1*1);
                if(yearTemp[i].field_4_2 === ''){
                    yearTemp[i].field_4_2=0;
                };
                num38 = _this.add(num38*1+yearTemp[i].field_4_2*1);
                if(yearTemp[i].field_4_3 === ''){
                    yearTemp[i].field_4_3=0;
                };
                num39 = _this.add(num39*1+yearTemp[i].field_4_3*1);
                if(yearTemp[i].field_4_4 === ''){
                    yearTemp[i].field_4_4=0;
                };
                num40 = _this.add(num40*1+yearTemp[i].field_4_4*1);
                if(yearTemp[i].field_4_5 === ''){
                    yearTemp[i].field_4_5=0;
                };
                num41 = _this.add(num41*1+yearTemp[i].field_4_5*1);
                if(yearTemp[i].field_4_6 === ''){
                    yearTemp[i].field_4_6=0;
                };
                num42 = _this.add(num42*1+yearTemp[i].field_4_6*1);
                if(yearTemp[i].field_5_1 === ''){
                    yearTemp[i].field_5_1=0;
                };
                num43 = _this.add(num43*1+yearTemp[i].field_5_1*1);
                if(yearTemp[i].field_5_2 === ''){
                    yearTemp[i].field_5_2=0;
                };
                num44 = _this.add(num44*1+yearTemp[i].field_5_2*1);
                if(yearTemp[i].field_5_3 === ''){
                    yearTemp[i].field_5_3=0;
                };
                num45 = _this.add(num45*1+yearTemp[i].field_5_3*1);
                if(yearTemp[i].field_5_4 === ''){
                    yearTemp[i].field_5_4=0;
                };
                num46 = _this.add(num46*1+yearTemp[i].field_5_4*1);
                if(yearTemp[i].field_5_5 === ''){
                    yearTemp[i].field_5_5=0;
                };
                num47 = _this.add(num47*1+yearTemp[i].field_5_5*1);
                if(yearTemp[i].field_5_6 === ''){
                    yearTemp[i].field_5_6=0;
                };
                num48 = _this.add(num48*1+yearTemp[i].field_5_6*1);
              };
           };
           total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
           total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;total[18]=num19;total[19]=num20;
           total[20]=num21;total[21]=num22;total[22]=num23;total[23]=num24;total[24]=num25;total[25]=num26;total[26]=num27;total[27]=num28;total[28]=num29;total[29]=num30;
           total[30]=num31;total[31]=num32;total[32]=num33;total[33]=num34;total[34]=num35;total[35]=num36;total[36]=num37;total[37]=num38;total[38]=num39;total[39]=num40;
           total[40]=num41;total[41]=num42;total[42]=num43;total[43]=num44;total[44]=num45;total[45]=num46;total[46]=num47;total[47]=num48;
           for(var i=0; i<24; i++){
             mean1[i] = (Number(total[i])/Number(dataLength1)).toFixed(2);
           };
           for(var i=24; i<48; i++){
             mean2[i-24] = (Number(total[i])/Number(dataLength2)).toFixed(2);
           };
           for(var i=0; i<24; i++){
             mean3[i] = (_this.add(mean1[i]*1-mean2[i]*1)).toFixed(2);
           }
           sumTemp.push({"field_1_1":"合计","field_1_2":"本年度",
                        "field_2_1":mean1[0],"field_2_2":mean1[1],"field_2_3":mean1[2],"field_2_4":mean1[3],"field_2_5":mean1[4],"field_2_6":mean1[5],
                        "field_3_1":mean1[6],"field_3_2":mean1[7],"field_3_3":mean1[8],"field_3_4":mean1[9],"field_3_5":mean1[10],"field_3_6":mean1[11],
                        "field_4_1":mean1[12],"field_4_2":mean1[13],"field_4_3":mean1[14],"field_4_4":mean1[15],"field_4_5":mean1[16],"field_4_6":mean1[17],
                        "field_5_1":mean1[18],"field_5_2":mean1[19],"field_5_3":mean1[20],"field_5_4":mean1[21],"field_5_5":mean1[22],"field_5_6":mean1[23],
                        "in_use":'--'},
                        {"field_1_1":"合计","field_1_2":"上年度",
                        "field_2_1":mean2[0],"field_2_2":mean2[1],"field_2_3":mean2[2],"field_2_4":mean2[3],"field_2_5":mean2[4],"field_2_6":mean2[5],
                        "field_3_1":mean2[6],"field_3_2":mean2[7],"field_3_3":mean2[8],"field_3_4":mean2[9],"field_3_5":mean2[10],"field_3_6":mean2[11],
                        "field_4_1":mean2[12],"field_4_2":mean2[13],"field_4_3":mean2[14],"field_4_4":mean2[15],"field_4_5":mean2[16],"field_4_6":mean2[17],
                        "field_5_1":mean2[18],"field_5_2":mean2[19],"field_5_3":mean2[20],"field_5_4":mean2[21],"field_5_5":mean2[22],"field_5_6":mean2[23],
                        "in_use":'--'},
                        {"field_1_1":"合计","field_1_2":'较去年增减',
                        "field_2_1":mean3[0],"field_2_2":mean3[1],"field_2_3":mean3[2],"field_2_4":mean3[3],"field_2_5":mean3[4],"field_2_6":mean3[5],
                        "field_3_1":mean3[6],"field_3_2":mean3[7],"field_3_3":mean3[8],"field_3_4":mean3[9],"field_3_5":mean3[10],"field_3_6":mean3[11],
                        "field_4_1":mean3[12],"field_4_2":mean3[13],"field_4_3":mean3[14],"field_4_4":mean3[15],"field_4_5":mean3[16],"field_4_6":mean3[17],
                        "field_5_1":mean3[18],"field_5_2":mean3[19],"field_5_3":mean3[20],"field_5_4":mean3[21],"field_5_5":mean3[22],"field_5_6":mean3[23],
                        "in_use":'--'});
          ////console.log(sumTemp);
         };
         //数据计算处理
         if(tempData.length != 0){
            ////console.log(tempData);
            var secondTemp=[];
            for(var i=0; i<_this.currentCounty2.length; i++){           //获取各个县市独立地数据
              var temp=[];
              for(var j=0; j<tempData.length; j++){
                if(_this.currentCounty2[i] === tempData[j].field_1_1){
                  temp.push(tempData[j]);
                };
              };
              secondTemp.push(temp);
            };
            //secondTemp.push(sumTemp);
            ////console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp.length == 2){
                  var county=thirdTemp[0].field_1_1;
                  var yearTemp=[];
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                  var num21=0;var num22=0;var num23=0;var num24=0;
                  for(var i=0; i<2; i++){     //处理显示本年度和上年度位置信息
                      ////console.log(thirdTemp[i].field_1_2);
                    if(thirdTemp[i].field_1_2 == year){ 
                        thirdTemp[i].field_1_2="本年度";
                        yearTemp[0]=thirdTemp[i];
                    }else{
                        thirdTemp[i].field_1_2="上年度";
                        yearTemp[1]=thirdTemp[i];
                    };
                  };
                  ////console.log(yearTemp);
                  for(var i=0; i<2; i++){
                    if(yearTemp[i].field_2_1 === ''){
                       yearTemp[i].field_2_1=0;
                    };
                    if(yearTemp[i].field_2_2 === ''){
                       yearTemp[i].field_2_2=0;
                    };
                    if(yearTemp[i].field_2_3 === ''){
                       yearTemp[i].field_2_3=0;
                    };
                    if(yearTemp[i].field_2_4 === ''){
                       yearTemp[i].field_2_4=0;
                    };
                    if(yearTemp[i].field_2_5 === ''){
                       yearTemp[i].field_2_5=0;
                    };
                    if(yearTemp[i].field_2_6 === ''){
                       yearTemp[i].field_2_6=0;
                    };
                    if(yearTemp[i].field_3_1 === ''){
                       yearTemp[i].field_3_1=0;
                    };
                    if(yearTemp[i].field_3_2 === ''){
                       yearTemp[i].field_3_2=0;
                    };
                    if(yearTemp[i].field_3_3 === ''){
                       yearTemp[i].field_3_3=0;
                    };
                    if(yearTemp[i].field_3_4 === ''){
                       yearTemp[i].field_3_4=0;
                    };
                    if(yearTemp[i].field_3_5 === ''){
                       yearTemp[i].field_3_5=0;
                    };
                    if(yearTemp[i].field_3_6 === ''){
                       yearTemp[i].field_3_6=0;
                    };
                    if(yearTemp[i].field_4_1 === ''){
                       yearTemp[i].field_4_1=0;
                    };
                    if(yearTemp[i].field_4_2 === ''){
                       yearTemp[i].field_4_2=0;
                    };
                    if(yearTemp[i].field_4_3 === ''){
                       yearTemp[i].field_4_3=0;
                    };
                    if(yearTemp[i].field_4_4 === ''){
                       yearTemp[i].field_4_4=0;
                    };
                    if(yearTemp[i].field_4_5 === ''){
                       yearTemp[i].field_4_5=0;
                    };
                    if(yearTemp[i].field_4_6 === ''){
                       yearTemp[i].field_4_6=0;
                    };
                    if(yearTemp[i].field_5_1 === ''){
                       yearTemp[i].field_5_1=0;
                    };
                    if(yearTemp[i].field_5_2 === ''){
                       yearTemp[i].field_5_2=0;
                    };
                    if(yearTemp[i].field_5_3 === ''){
                       yearTemp[i].field_5_3=0;
                    };
                    if(yearTemp[i].field_5_4 === ''){
                       yearTemp[i].field_5_4=0;
                    };
                    if(yearTemp[i].field_5_5 === ''){
                       yearTemp[i].field_5_5=0;
                    };
                    if(yearTemp[i].field_5_6 === ''){
                       yearTemp[i].field_5_6=0;
                    };
                  }
                  num1 = _this.add(yearTemp[0].field_2_1*1 - yearTemp[1].field_2_1*1);
                  num2 = _this.add(yearTemp[0].field_2_2*1 - yearTemp[1].field_2_2*1);
                  num3 = _this.add(yearTemp[0].field_2_3*1 - yearTemp[1].field_2_3*1);
                  num4 = _this.add(yearTemp[0].field_2_4*1 - yearTemp[1].field_2_4*1);
                  num5 = _this.add(yearTemp[0].field_2_5*1 - yearTemp[1].field_2_5*1);
                  num6 = _this.add(yearTemp[0].field_2_6*1 - yearTemp[1].field_2_6*1);
                  num7 = _this.add(yearTemp[0].field_3_1*1 - yearTemp[1].field_3_1*1);
                  num8 = _this.add(yearTemp[0].field_3_2*1 - yearTemp[1].field_3_2*1);
                  num9 = _this.add(yearTemp[0].field_3_3*1 - yearTemp[1].field_3_3*1);
                  num10 = _this.add(yearTemp[0].field_3_4*1 - yearTemp[1].field_3_4*1);
                  num11 = _this.add(yearTemp[0].field_3_5*1 - yearTemp[1].field_3_5*1);
                  num12 = _this.add(yearTemp[0].field_3_6*1 - yearTemp[1].field_3_6*1);
                  num13 = _this.add(yearTemp[0].field_4_1*1 - yearTemp[1].field_4_1*1);
                  num14 = _this.add(yearTemp[0].field_4_2*1 - yearTemp[1].field_4_2*1);
                  num15 = _this.add(yearTemp[0].field_4_3*1 - yearTemp[1].field_4_3*1);
                  num16 = _this.add(yearTemp[0].field_4_4*1 - yearTemp[1].field_4_4*1);
                  num17 = _this.add(yearTemp[0].field_4_5*1 - yearTemp[1].field_4_5*1);
                  num18 = _this.add(yearTemp[0].field_4_6*1 - yearTemp[1].field_4_6*1);
                  num19 = _this.add(yearTemp[0].field_5_1*1 - yearTemp[1].field_5_1*1);
                  num20 = _this.add(yearTemp[0].field_5_2*1 - yearTemp[1].field_5_2*1);
                  num21 = _this.add(yearTemp[0].field_5_3*1 - yearTemp[1].field_5_3*1);
                  num22 = _this.add(yearTemp[0].field_5_4*1 - yearTemp[1].field_5_4*1);
                  num23 = _this.add(yearTemp[0].field_5_5*1 - yearTemp[1].field_5_5*1);
                  num24 = _this.add(yearTemp[0].field_5_6*1 - yearTemp[1].field_5_6*1);

                  yearTemp.push({"field_1_1":county,"field_1_2":'较去年增减',
                                  "field_2_1":num1,"field_2_2":num2,"field_2_3":num3,"field_2_4":num4,"field_2_5":num5,"field_2_6":num6,
                                  "field_3_1":num7,"field_3_2":num8,"field_3_3":num9,"field_3_4":num10,"field_3_5":num11,"field_3_6":num12,
                                  "field_4_1":num13,"field_4_2":num14,"field_4_3":num15,"field_4_4":num16,"field_4_5":num17,"field_4_6":num18,
                                  "field_5_1":num19,"field_5_2":num20,"field_5_3":num21,"field_5_4":num22,"field_5_5":num23,"field_5_6":num24,
                                  "in_use":'--'}); 
                  fourTemp = fourTemp.concat(yearTemp);
                  
              }
            };
            if(fourTemp.length != 0){
              fourTemp = fourTemp.concat(sumTemp);
            }
            tempData=fourTemp; 
         }else{ 
         }; 
          _this.tableDataTwo92 = tempData;
          //////console.log(tempData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo93(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo93',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_5 === ''){
                totalTableData[i].field_5 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_5*1);

              if(totalTableData[i].field_7 === ''){
                totalTableData[i].field_7 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_7*1);

              if(totalTableData[i].field_9 === ''){
                totalTableData[i].field_9 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9*1);

              if(totalTableData[i].field_10 === ''){
                totalTableData[i].field_10 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_10*1);

            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;
            ////console.log(total);
            for(var i=0; i<4; i++){
              if(num1===0){
                total[i]='--';
                sums[i]='--';
              }
            }
            if(num1 != 0){
              sums[0] = (Number(total[1])/Number(total[0])*100).toFixed(2)+'%';           
              sums[1] = ((Number(total[2])+Number(total[3]))/Number(total[0])*100).toFixed(2)+'%';
            
            }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":total[0],"field_6":'--',"field_7":total[1],"field_8":sums[0],"field_9":total[2],
                                "field_10":total[3],"field_11":sums[1],"field_12":'--',"field_13":'--',                 
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo93 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo94(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo94',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7 === ''){
                totalTableData[i].field_7 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7*1);

              if(totalTableData[i].field_8 === ''){
                totalTableData[i].field_8 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_9_5 === ''){
                totalTableData[i].field_9_5 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_9_5*1);

              if(totalTableData[i].field_12 === ''){
                totalTableData[i].field_12 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_12*1);

              if(totalTableData[i].field_13 === ''){
                totalTableData[i].field_13 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_13*1);

            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;
            ////console.log(total);
             for(var i=0; i<9; i++){
              if(num1===0){
                total[i]='--';
                sums[i]='--';
              }
            }
            if(num1 != 0){
              sums[0] = (Number(total[1])/Number(total[0])*100).toFixed(2)+'%';           
              sums[1] = ((Number(total[7])+Number(total[8]))/Number(total[0])*100).toFixed(2)+'%';
            
            }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":total[0],"field_8":total[1],
                                "field_9_1":total[2],"field_9_2":total[3],"field_9_3":total[4],"field_9_4":total[5],"field_9_5":total[6],
                                "field_10":'--',"field_11":sums[0],"field_12":total[7],"field_13":total[8], "field_14":sums[1],               
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo94 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo95(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo95',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7 === ''){
                totalTableData[i].field_7 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7*1);

              if(totalTableData[i].field_8 === ''){
                totalTableData[i].field_8 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_9_5 === ''){
                totalTableData[i].field_9_5 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_9_5*1);

              if(totalTableData[i].field_12 === ''){
                totalTableData[i].field_12 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_12*1);

              if(totalTableData[i].field_13 === ''){
                totalTableData[i].field_13 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_13*1);
            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;
            ////console.log(total);
             for(var i=0; i<9; i++){
              if(num1===0){
                total[i]='--';
                sums[i]='--';
              }
            }
            if(num1 != 0){
              sums[0] = (Number(total[1])/Number(total[0])*100).toFixed(2)+'%';           
              sums[1] = ((Number(total[7])+Number(total[8]))/Number(total[0])*100).toFixed(2)+'%';
            
            }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":total[0],"field_8":total[1],
                                "field_9_1":total[2],"field_9_2":total[3],"field_9_3":total[4],"field_9_4":total[5],"field_9_5":total[6],
                                "field_10":'--',"field_11":sums[0],"field_12":total[7],"field_13":total[8], "field_14":sums[1],               
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo95 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo96(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo96',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_5_3 === ''){
                totalTableData[i].field_5_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_5_3*1);

              if(totalTableData[i].field_5_4 === ''){
                totalTableData[i].field_5_4 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_4*1);

              if(totalTableData[i].field_5_5 === ''){
                totalTableData[i].field_5_5 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_5*1);

              if(totalTableData[i].field_5_6 === ''){
                totalTableData[i].field_5_6 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_6*1);

              if(totalTableData[i].field_5_7 === ''){
                totalTableData[i].ffield_5_7 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_5_7*1);

              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].ffield_6_4 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_6_5 === ''){
                totalTableData[i].field_6_5 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_6_5*1);

              if(totalTableData[i].ffield_6_6 === ''){
                totalTableData[i].field_6_6 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_6_6*1);

              if(totalTableData[i].field_6_7 === ''){
                totalTableData[i].field_6_7 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_6_7*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5 === ''){
                totalTableData[i].field_7_5 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_7_6*1);

              if(totalTableData[i].field_7_7 === ''){
                totalTableData[i].field_7_7 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_7_7*1);
            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;
            for(var i=0;i<total.length;i++){
              if(total[i]==0){
                total[i]='--';
              }
            }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',
                                "field_5_1":'--',"field_5_2":'--',"field_5_3":total[0],"field_5_4":total[1],"field_5_5":total[2],"field_5_6":total[3],"field_5_7":total[4],
                                "field_6_1":'--',"field_6_2":'--',"field_6_3":total[5],"field_6_4":total[6],"field_6_5":total[7],"field_6_6":total[8],"field_6_7":total[9],
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":total[10],"field_7_4":total[11],"field_7_5":total[12],"field_7_6":total[13],"field_7_7":total[14],
                                
                                "in_use":'--'}) 
          }else{ 
          } 
          _this.tableDataTwo96 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo97(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo97',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
         
           //_this.tableData =res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_9 === ''){
                totalTableData[i].field_9 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_9*1);

              if(totalTableData[i].field_11_1 === ''){
                totalTableData[i].field_11_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_11_1*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_11_2*1);

            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=1; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            ////console.log(sums);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',"field_8":'--',"field_9":total[0],
                                "field_10":'--',"field_11_1":sums[1],"field_11_2":sums[2],"field_12":'--',
                                                 
                                "in_use":'--'}) 
          }else{ 
          }
          _this.tableDataTwo97 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo101(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo101',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;var num11=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_12_2 === ''){
                totalTableData[i].field_12_2 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_12_2*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_12_3*1);
             
            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;total[10]=num11;
             
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            } 
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":sums[0],"field_8_1":'--',"field_8_2":sums[1],"field_8_3":sums[2],
                                "field_9_1":'--',"field_9_2":sums[3],"field_9_3":sums[4],"field_10_1":'--',"field_10_2":sums[5],"field_10_3":sums[6],
                                "field_11_1":'--',"field_11_2":sums[7],"field_11_3":sums[8],"field_12_1":'--',"field_12_2":sums[9],"field_12_3":sums[10],
                                "in_use":'--'}) 
          }else{ 
          }
          _this.tableDataTwo101 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    getTableDataTwo102(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo102',
          method:'post',
          params:{
            areaType:this.diqu,
            county:this.xian,
            year:this.nian
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;var num11=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_12_2 === ''){
                totalTableData[i].field_12_2 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_12_2*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_12_3*1);
             
            };
            //各品种面积总和 
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;total[10]=num11;
            ////console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            } 
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":sums[0],"field_8_1":'--',"field_8_2":sums[1],"field_8_3":sums[2],
                                "field_9_1":'--',"field_9_2":sums[3],"field_9_3":sums[4],"field_10_1":'--',"field_10_2":sums[5],"field_10_3":sums[6],
                                "field_11_1":'--',"field_11_2":sums[7],"field_11_3":sums[8],"field_12_1":'--',"field_12_2":sums[9],"field_12_3":sums[10],
                                
                                "in_use":'--'}) 
          }else{ 
          }
          _this.tableDataTwo102 = totalTableData;
          ////console.log(totalTableData);
          
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    //导出excel
    getExcel2(){
      ////console.log("选择："+this.checkedCities2);
      var tableOption=this.checkedCities2;
      for(var i=0; i<tableOption.length;i++){
        var name=tableOption[i];
        switch(name){
          case "水稻栽插概况调查表":
            this.handleDownloadTwo11();
            break;
          case "水稻生产主推品种调查表":
            this.handleDownloadTwo12();
            break;
          case "水稻监测点汇总表":
            this.handleDownloadTwo22();
            break;
          case "水稻播栽进度调查表（早稻）":
            this.handleDownloadTwo31();
            break;
          case "水稻播栽进度调查表（晚稻）":
            this.handleDownloadTwo33();
            break;
          case "水稻苗情评价动态列表（早稻-移栽稻）":
            this.handleDownloadTwo33();
            break;
          case "水稻苗情评价动态列表（早稻-直播稻）":
            this.handleDownloadTwo34();
            break;
          case "水稻苗情评价动态列表（晚稻-移栽稻）":
            this.handleDownloadTwo35();
            break;
          case "水稻苗情评价动态列表（晚稻-直播稻）":
            this.handleDownloadTwo36();
            break;
          case "中稻生育进度调查表（移栽稻）":
            this.handleDownloadTwo41();
            break;
          case "直播稻生育进度调查表（直播稻）":
            this.handleDownloadTwo42();
            break;
          case "水稻监测点茎蘖数叶面积构成表":
            this.handleDownloadTwo43();
            break;
          case "水稻监测点苗情生育期统计表":
            this.handleDownloadTwo44();
            break;
          case "叶片含氮测定仪导出数据":
            this.handleDownloadTwo51();
            break;
          case "水稻监测点水分管理记录表":
            this.handleDownloadTwo61();
            break;
          case "水稻监测点化除管理记录表":
            this.handleDownloadTwo62();
            break;
          case "水稻监测点病虫害防治记录表":
            this.handleDownloadTwo63();
            break;
          case "水稻监测点施肥管理记录表":
            this.handleDownloadTwo64();
            break;
          case "水稻监测点化学调节剂使用记录表":
            this.handleDownloadTwo65();
            break;
          case "水稻双季稻苗情监测预测产表":
            this.handleDownloadTwo71();
            break;
          case "水稻双季稻收获进度调查（早稻）":
            this.handleDownloadTwo72();
            break;
          case "水稻双季稻收获进度调查（晚稻）":
            this.handleDownloadTwo73();
            break;
          case "水稻双季稻测产表":
            this.handleDownloadTwo74();
            break;
          case "水稻生产成本效益统计表（早稻）":
            this.handleDownloadTwo81();
            break;
          case "水稻生产成本效益统计表（晚稻）":
            this.handleDownloadTwo82();
            break;
          case "气象资料列表":
            this.handleDownloadTwo91();
            break;
          case "抽穗期前、后各15天气象资料":
            this.handleDownloadTwo92();
            break;
          case "水稻倒伏灾害表":
            this.handleDownloadTwo93();
            break;
          case "水稻低温冷害表":
            this.handleDownloadTwo94();
            break;
          case "水稻高温热害表":
            this.handleDownloadTwo95();
            break;
          case "水稻涝害调查表":
            this.handleDownloadTwo96();
            break;
          case "水稻监测点洪涝灾害表":
            this.handleDownloadTwo97();
            break;
          case "水稻监测点灌浆速率调查表（早稻）":
            this.handleDownloadTwo101();
            break;
          case "水稻监测点灌浆速率调查表（晚稻）":
            this.handleDownloadTwo102();
            break;
        }
      }
    },
    handleDownloadTwo11() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻生产栽插概况调查表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '全县总面积（万亩）', '地区类型', '栽植方式（万亩）', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '前茬口类型（万亩）', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '人工移栽', '', '机械插秧', '', '盘育抛栽', '', '无盘旱育抛栽', '', '撒直播(旱)', '', '撒直播(水)', '', '机条播(旱)', '', '机条播(水)', '', '小计', '', '水稻', '', '小麦', '', '油菜', '', '空闲田', '', '绿肥', '', '蔬菜等经作', '', '其它', '', '小计', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_5_1_1', 'field_5_1_2', 'field_5_2_1', 'field_5_2_2', 'field_5_3_1', 'field_5_3_2', 'field_5_4_1', 'field_5_4_2', 'field_5_5_1', 'field_5_5_2', 'field_5_6_1', 'field_5_6_2', 'field_5_7_1', 'field_5_7_2', 'field_5_8_1', 'field_5_8_2', 'field_5_9_1', 'field_5_9_2', 'field_6_1_1', 'field_6_1_2', 'field_6_2_1', 'field_6_2_2', 'field_6_3_1', 'field_6_3_2', 'field_6_4_1', 'field_6_4_2', 'field_6_5_1', 'field_6_5_2', 'field_6_6_1', 'field_6_6_2', 'field_6_7_1', 'field_6_7_2', 'field_6_8_1', 'field_6_8_2'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AK1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:U2",
        "V2:AK2",
        "D3:E3",
        "F3:G3",
        "H3:I3",
        "J3:K3",
        "L3:M3",
        "N3:O3",
        "P3:Q3",
        "R3:S3",
        "T3:U3",
        "V3:W3",
        "X3:Y3",
        "Z3:AA3",
        "AB3:AC3",
        "AD3:AE3",
        "AF3:AG3",
        "AH3:AI3",
        "AJ3:AK3",
      ];
      const list = this.tableDataTwo11;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo12() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻生产主推品种调查表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['县（市、区）', '全县总面积（万亩）', '品种名', '面积（万亩）', '面积占比']] //对应表格三级输出的title
      const filterVal = ['county', 'county_area', 'pin_zhong_name', 'area_data', 'area_percent'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:E1",
      ];
      const list = this.tableDataTwo12;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo22() {
        // this.downloadLoading = true // 加载遮罩层开启
        ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点汇总表"
        const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
        const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                     ['监测点代码', '地址', '', '', '', '', '监测田块', '', '', '', '', '', '', '', '', '', '品种（品系）', '', '', '田间信息', '', '', '', '', '', '', '', '', '', '', '']] //对应表格一级输出的title
        const header = []//三级
        const th2 =  [['', '市', '县（市、区）', '镇（乡）', '村名', '户主', '种植面积（亩）', '地区类型', '土壤类型', '前茬类型', '水管方式', '全氮(g/Kg)', '速效磷(g/Kg)', '速效钾(g/Kg)', '有机质(g/Kg)', '秸秆还田量(kg/亩)', '名称', '稻作类型', '品系类型', '栽植方式', '播期', '移栽秧龄（天）', '移栽叶龄（叶）', '基本苗（万/亩）', '收获日期', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '理论产量(kg/亩)', '实收产量(kg/亩)']] //对应表格三级输出的title
        const filterVal = ['field_1', 'field_2_1', 'field_2_2', 'field_2_3', 'field_2_4', 'field_2_5', 'field_3_1', 'field_3_2', 'field_3_3', 'field_3_4', 'field_3_5', 'field_3_6', 'field_3_7', 'field_3_8', 'field_3_9', 'field_3_10', 'field_4_1', 'field_4_2', 'field_4_3', 'field_5_1', 'field_5_2', 'field_5_3', 'field_5_4', 'field_5_5', 'field_5_6', 'field_5_7', 'field_5_8', 'field_5_9', 'field_5_10', 'field_5_11', 'field_5_12'] // 对应表格tableData输出的数据
        ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
        //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
        const merges = [
          "A1:AE1",
          "A2:A3",
          "B2:F2",
          "G2:P2",
          "Q2:S2",
          "T2:AE2",
        ];
        const list = this.tableDataTwo22;
        ////console.log(list);
        const data = this.formatJson(filterVal, list);
        ////console.log(data);
        export_json_to_excel(th1, th2, header, data, tableName, merges);
        // this.downloadLoading = false // 加载遮罩层结束
      },
    handleDownloadTwo31() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻播栽进度调查表（早稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '' ,'', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '地区类型','稻作类型', '全县总面积（万亩）', '开始播种日期', '', '秧田面积（万亩）', '', '', '折合大田面积（万亩）', '', '', '', '播栽期进度1', '', '', '', '', '', '', '播栽期进度2', '', '', '', '', '', '', '播栽期进度3', '', '', '', '', '', '', '播栽期进度4', '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '','育秧', '直播', '', '其中集中育秧', '', '', '', '其中集中育秧', '', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3','field_4', 'field_5_1', 'field_5_2', 'field_6', 'field_7_1', 'field_7_2', 'field_8_1', 'field_8_2', 'field_9_1', 'field_9_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6', 'field_11_1','field_11_2_1', 'field_11_2_2', 'field_11_3', 'field_11_4', 'field_11_5', 'field_11_6', 'field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_3', 'field_12_4', 'field_12_5', 'field_12_6', 'field_13_1', 'field_13_2', 'field_13_3', 'field_13_4', 'field_13_5'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AM1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:F2",
        "G2:I2",
        "J2:M2",
        "N2:T2",
        "U2:AA2",
        "AB2:AH2",
        "AI2:AM2",
        "H3:I3",
        "J3:K3",
        "L3:M3",
        "O3:P3",
        "V3:W3",
        "AC3:AD3",
      ];
      const list = this.tableDataTwo31;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },  
    handleDownloadTwo32() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻播栽进度调查表（晚稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '地区类型', '稻作类型','全县总面积（万亩）', '开始播种日期', '', '秧田面积（万亩）', '', '', '折合大田面积（万亩）', '', '', '', '播栽期进度1', '', '', '', '', '', '', '播栽期进度2', '', '', '', '', '', '', '播栽期进度3', '', '', '', '', '', '', ]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '育秧', '直播', '', '其中集中育秧', '', '', '', '其中集中育秧', '', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5_1', 'field_5_2', 'field_6', 'field_7_1', 'field_7_2', 'field_8_1', 'field_8_2', 'field_9_1', 'field_9_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6', 'field_11_1','field_11_2_1', 'field_11_2_2', 'field_11_3', 'field_11_4', 'field_11_5', 'field_11_6', 'field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_3', 'field_12_4', 'field_12_5', 'field_12_6'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AH1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:F2",
        "G2:I2",
        "J2:M2",
        "N2:T2",
        "U2:AA2",
        "AB2:AH2",
        "H3:I3",
        "J3:K3",
        "L3:M3",
        "O3:P3",
        "V3:W3",
        "AC3:AD3",
      ];
      const list = this.tableDataTwo32;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo33() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻苗情评价动态列表（早稻-移栽稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      const th1 = [[tableName, '', '', '','','','','','','','', '','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',],
                    ['县（市、区）', '地区类型', '稻作类型', '全县总面积（万亩）','进程1（移栽后7天）','','','','','','', '进程2（移栽后12天）','','','','','','',
                    '进程3（移栽后17天）','','','','','','','进程4（移栽后22天）','','','','','','','进程5（移栽后17天）','','','','','','','进程6（移栽后32天）','','','','','','','进程7（移栽后42天）','','','','','','',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['','', '', '','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）',''
      ,'日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5_1', 'field_5_2_1', 'field_5_2_2', 'field_5_3_1', 'field_5_3_2', 'field_5_4_1', 'field_5_4_2', 'field_6_1', 'field_6_2_1', 'field_6_2_2', 'field_6_3_1', 'field_6_3_2', 'field_6_4_1', 'field_6_4_2',
      'field_7_1', 'field_7_2_1', 'field_7_2_2', 'field_7_3_1', 'field_7_3_2', 'field_7_4_1', 'field_7_4_2','field_8_1', 'field_8_2_1', 'field_8_2_2', 'field_8_3_1', 'field_8_3_2', 'field_8_4_1', 'field_8_4_2',
      'field_9_1', 'field_9_2_1', 'field_9_2_2', 'field_9_3_1', 'field_9_3_2', 'field_9_4_1', 'field_9_4_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3_1', 'field_10_3_2', 'field_10_4_1', 'field_10_4_2',
      'field_11_1', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 'field_11_4_1', 'field_11_4_2'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:BA1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:K2",
        "L2:R2",
        "S2:Y2",
        "Z2:AF2",
        "AG2:AM2",
        "AN2:AT2",
        "AU2:BA2",
        "F3:G3",
        "H3:I3",
        "J3:K3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "T3:U3",
        "V3:W3",
        "X3:Y3",
        "AA3:AB3",
        "AC3:AD3",
        "AE3:AF3",
        "AH3:AI3",
        "AJ3:AK3",
        "AL3:AM3",
        "AO3:AP3",
        "AQ3:AR3",
        "AS3:AT3",
        "AV3:AW3",
        "AX3:AY3",
        "AZ3:BA3",
      ];
      const list = this.tableDataTwo33;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo34() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻苗情评价动态列表（早稻-直播稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '','','','','','','','', '','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',],
                    ['县（市、区）', '地区类型', '稻作类型', '全县总面积（万亩）','进程1（移栽后20天）','','','','','','', '进程2（移栽后28天）','','','','','','',
                    '进程3（移栽后35天）','','','','','','','进程4（移栽后42天）','','','','','','','进程5（移栽后49天）','','','','','','','进程6（移栽后56天）','','','','','','','进程7（移栽后66天）','','','','','','',
                    '进程8（移栽后76天）','','','','','','','进程9（移栽后86天）','','','','','','',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['','', '', '','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）',''
      ,'日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','',
      '日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5_1', 'field_5_2_1', 'field_5_2_2', 'field_5_3_1', 'field_5_3_2', 'field_5_4_1', 'field_5_4_2', 'field_6_1', 'field_6_2_1', 'field_6_2_2', 'field_6_3_1', 'field_6_3_2', 'field_6_4_1', 'field_6_4_2',
      'field_7_1', 'field_7_2_1', 'field_7_2_2', 'field_7_3_1', 'field_7_3_2', 'field_7_4_1', 'field_7_4_2','field_8_1', 'field_8_2_1', 'field_8_2_2', 'field_8_3_1', 'field_8_3_2', 'field_8_4_1', 'field_8_4_2',
      'field_9_1', 'field_9_2_1', 'field_9_2_2', 'field_9_3_1', 'field_9_3_2', 'field_9_4_1', 'field_9_4_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3_1', 'field_10_3_2', 'field_10_4_1', 'field_10_4_2',
      'field_11_1', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 'field_11_4_1', 'field_11_4_2','field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_3_1', 'field_12_3_2', 'field_12_4_1', 'field_12_4_2',
      'field_13_1', 'field_13_2_1', 'field_13_2_2', 'field_13_3_1', 'field_13_3_2', 'field_13_4_1', 'field_13_4_2'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:BO1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:K2",
        "L2:R2",
        "S2:Y2",
        "Z2:AF2",
        "AG2:AM2",
        "AN2:AT2",
        "AU2:BA2",
        "BB2:BH2",
        "BI2:BO2",
        "F3:G3",
        "H3:I3",
        "J3:K3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "T3:U3",
        "V3:W3",
        "X3:Y3",
        "AA3:AB3",
        "AC3:AD3",
        "AE3:AF3",
        "AH3:AI3",
        "AJ3:AK3",
        "AL3:AM3",
        "AO3:AP3",
        "AQ3:AR3",
        "AS3:AT3",
        "AV3:AW3",
        "AX3:AY3",
        "AZ3:BA3",
        "BC3:BD3",
        "BE3:BF3",
        "BG3:BH3",
        "BJ3:BK3",
        "BL3:BM3",
        "BN3:BO3",
      ];
      const list = this.tableDataTwo34;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo35() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻苗情评价动态列表（晚稻-移栽稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '','','','','','','', '','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',],
                    ['县（市、区）', '地区类型', '全县总面积（万亩）','进程1（移栽后7天）','','','','','','', '进程2（移栽后12天）','','','','','','',
                    '进程3（移栽后17天）','','','','','','','进程4（移栽后22天）','','','','','','','进程5（移栽后17天）','','','','','','','进程6（移栽后32天）','','','','','','','进程7（移栽后42天）','','','','','','',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['','',  '','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）',''
      ,'日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4_1', 'field_4_2_1', 'field_4_2_2', 'field_4_3_1', 'field_4_3_2', 'field_4_4_1', 'field_4_4_2','field_5_1', 'field_5_2_1', 'field_5_2_2', 'field_5_3_1', 'field_5_3_2', 'field_5_4_1', 'field_5_4_2', 'field_6_1', 'field_6_2_1', 'field_6_2_2', 'field_6_3_1', 'field_6_3_2', 'field_6_4_1', 'field_6_4_2',
      'field_7_1', 'field_7_2_1', 'field_7_2_2', 'field_7_3_1', 'field_7_3_2', 'field_7_4_1', 'field_7_4_2','field_8_1', 'field_8_2_1', 'field_8_2_2', 'field_8_3_1', 'field_8_3_2', 'field_8_4_1', 'field_8_4_2',
      'field_9_1', 'field_9_2_1', 'field_9_2_2', 'field_9_3_1', 'field_9_3_2', 'field_9_4_1', 'field_9_4_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3_1', 'field_10_3_2', 'field_10_4_1', 'field_10_4_2',
      ] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AZ1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:J2",
        "K2:Q2",
        "R2:X2",
        "Y2:AE2",
        "AF2:AL2",
        "AM2:AS2",
        "AT2:AZ2",
        "E3:F3",
        "G3:H3",
        "I3:J3",
        "L3:M3",
        "N3:O3",
        "P3:Q3",
        "S3:T3",
        "U3:V3",
        "W3:X3",
        "Z3:AA3",
        "AB3:AC3",
        "AD3:AE3",
        "AG3:AH3",
        "AI3:AJ3",
        "AK3:AL3",
        "AN3:AO3",
        "AP3:AQ3",
        "AR3:AS3",
        "AU3:AV3",
        "AW3:AX3",
        "AY3:AZ3",
      ];
      const list = this.tableDataTwo35;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo36() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻苗情评价动态列表（晚稻-直播稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '','','','','','','','', '','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',],
                    ['县（市、区）', '地区类型', '稻作类型', '全县总面积（万亩）','进程1（移栽后20天）','','','','','','', '进程2（移栽后28天）','','','','','','',
                    '进程3（移栽后35天）','','','','','','','进程4（移栽后42天）','','','','','','','进程5（移栽后49天）','','','','','','','进程6（移栽后56天）','','','','','','','进程7（移栽后66天）','','','','','','',
                    '进程8（移栽后76天）','','','','','','','进程9（移栽后86天）','','','','','','',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['','', '', '','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）',''
      ,'日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','',
      '日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5_1', 'field_5_2_1', 'field_5_2_2', 'field_5_3_1', 'field_5_3_2', 'field_5_4_1', 'field_5_4_2', 'field_6_1', 'field_6_2_1', 'field_6_2_2', 'field_6_3_1', 'field_6_3_2', 'field_6_4_1', 'field_6_4_2',
      'field_7_1', 'field_7_2_1', 'field_7_2_2', 'field_7_3_1', 'field_7_3_2', 'field_7_4_1', 'field_7_4_2','field_8_1', 'field_8_2_1', 'field_8_2_2', 'field_8_3_1', 'field_8_3_2', 'field_8_4_1', 'field_8_4_2',
      'field_9_1', 'field_9_2_1', 'field_9_2_2', 'field_9_3_1', 'field_9_3_2', 'field_9_4_1', 'field_9_4_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3_1', 'field_10_3_2', 'field_10_4_1', 'field_10_4_2',
      'field_11_1', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 'field_11_4_1', 'field_11_4_2','field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_3_1', 'field_12_3_2', 'field_12_4_1', 'field_12_4_2',
      'field_13_1', 'field_13_2_1', 'field_13_2_2', 'field_13_3_1', 'field_13_3_2', 'field_13_4_1', 'field_13_4_2'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:BO1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:K2",
        "L2:R2",
        "S2:Y2",
        "Z2:AF2",
        "AG2:AM2",
        "AN2:AT2",
        "AU2:BA2",
        "BB2:BH2",
        "BI2:BO2",
        "F3:G3",
        "H3:I3",
        "J3:K3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "T3:U3",
        "V3:W3",
        "X3:Y3",
        "AA3:AB3",
        "AC3:AD3",
        "AE3:AF3",
        "AH3:AI3",
        "AJ3:AK3",
        "AL3:AM3",
        "AO3:AP3",
        "AQ3:AR3",
        "AS3:AT3",
        "AV3:AW3",
        "AX3:AY3",
        "AZ3:BA3",
        "BC3:BD3",
        "BE3:BF3",
        "BG3:BH3",
        "BJ3:BK3",
        "BL3:BM3",
        "BN3:BO3",
      ];
      const list = this.tableDataTwo36;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo41() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)中稻生育进度调查表（移栽稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '',  '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',''],
                    ['县（市、区）', '监测田块', '地区类型', '稻作类型', '品系类型', '品种名称', '栽培方式', '移栽期', '', '', '','进程1', '', '', '','进程2', '', '', '','进程3', '', '', '','进程4', '', '', '','进程5', '', '', '',
                    '进程6', '', '', '','进程7', '', '', '', ]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', '', 
                     '日期', '叶龄(叶)', '基本苗（万/亩）', '亩穴数（万）',
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）',  ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 'field_7',
                        'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_9_1','field_9_2', 'field_9_3','field_9_4',
                        'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_11_1','field_11_2', 'field_11_3','field_11_4',
                        'field_12_1', 'field_12_2', 'field_12_3', 'field_12_4', 'field_13_1','field_13_2', 'field_13_3','field_13_4',
                        'field_14_1', 'field_14_2', 'field_14_3', 'field_14_4', 'field_15_1','field_15_2', 'field_15_3','field_15_4'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AM1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:G3",
        "H2:K2",
        "L2:O2",
        "P2:S2",
        "T2:W2",
        "X2:AA2",
        "AB2:AE2",
        "AF2:AI2",
        "AJ2:AM2",
      ];
      const list = this.tableDataTwo41;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo42() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)直播稻生育进度调查表（直播稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '',  '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '监测田块', '地区类型', '稻作类型', '品系类型', '品种名称', '栽培方式', '进程1', '', '', '','进程2', '', '', '','进程3', '', '', '','进程4', '', '', '','进程5', '', '', '',
                    '进程6', '', '', '','进程7', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', '',  
                     '日期', '生育进程', '叶龄(叶)', '基本苗（万）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', 
                     '日期', '生育进程', '叶龄(叶)', '茎蘖数（万/亩）', ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 'field_7',
                        'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_9_1','field_9_2', 'field_9_3','field_9_4',
                        'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_11_1','field_11_2', 'field_11_3','field_11_4',
                        'field_12_1', 'field_12_2', 'field_12_3', 'field_12_4', 'field_13_1','field_13_2', 'field_13_3','field_13_4',
                        'field_14_1', 'field_14_2', 'field_14_3', 'field_14_4'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AI1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:G3",
        "H2:K2",
        "L2:O2",
        "P2:S2",
        "T2:W2",
        "X2:AA2",
        "AB2:AE2",
        "AF2:AI2",
      ];
      const list = this.tableDataTwo42;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo43() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点茎蘖数叶面积构成表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '监测田块', '地区类型', '稻作类型', '品系类型', '品种名称', '栽培方式', '目标产量(kg/亩)', '基本苗（万/亩）', '拔节期', '', '', '', '', '', '', '',
                     '孕穗期（幼穗分化1-2期）', '', '', '', '', '孕穗期（幼穗分化3-4期）', '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', '', '', '', '测定日期', '株高(cm)', '总茎蘖数(万/亩)', '平均单茎绿叶数', '倒4与倒3叶色比', '1叶分蘖个数', '2叶分蘖个数', '3叶及以上分蘖个数', 
      '测定日期', '株高(cm)', '总茎蘖数(万/亩)', '平均单茎绿叶数', '倒4与倒3叶色比', '测定日期', '株高(cm)', '总茎蘖数(万/亩)', '平均单茎绿叶数', '倒4与倒3叶色比']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 'field_7', 
                        'field_8', 'field_9', 'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 
                        'field_10_5', 'field_10_6', 'field_10_7', 'field_10_8', 'field_11_1', 'field_11_2', 
                        'field_11_3', 'field_11_4', 'field_11_5', 'field_12_1', 'field_12_2', 'field_12_3', 
                        'field_12_4', 'field_12_5'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AA1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:G3",
        "H2:H3",
        "I2:I3",
        "J2:Q2",
        "R2:V2",
        "W2:AA2",
      ];
      const list = this.tableDataTwo43;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo44() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点苗情生育期统计表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
       const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['县（市、区）', '监测田块', '地区类型', '稻作类型', '品系类型', '品种名称',
                      '栽培方式', '播种期（月/日）', '出苗期（月/日）', '移栽期（月/日）', '分蘖期（月/日）',
                      '够苗期（月/日', '有效分蘗临界叶龄期（月/日）', '拔节期（月/日）', '孕穗期（月/日）', 
                      '抽穗期（月/日）', '齐穗期（月/日）', '成熟期（月/日）', '全生育期/天']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                        'field_7', 'field_8', 'field_9', 'field_10', 'field_fennie','field_11', 'field_12',
                        'field_13', 'field_14', 'field_15', 'field_16', 'field_17', 'field_18'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:S1",
      ];
      const list = this.tableDataTwo44;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo51() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度叶片含氮测定仪导出数据"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', 
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 
                    '', '','', '', '', '', '', '', '','', '', '', '', '', '', '','', '', '', '',],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '够苗期', '', '', '', '', '', '', '', '', '',
                    '拔节期', '', '', '', '', '', '', '', '', '', 
                    '孕穗期1', '', '', '', '', '', '', '', '', '', 
                    '孕穗期2', '', '', '', '', '', '', '', '', '', 
                    '抽穗期', '', '', '', '', '','', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '时间', '叶绿素（SPAD）', '氮含量（mg/g）', '叶面温度（℃）', '叶面湿度（%RH）', '植物名称', '化肥中氮含量（%）', '化肥中氮的利用率（%）', '目标产量', '施肥量',
                    '时间', '叶绿素（SPAD）', '氮含量（mg/g）', '叶面温度（℃）', '叶面湿度（%RH）', '植物名称', '化肥中氮含量（%）', '化肥中氮的利用率（%）', '目标产量', '施肥量',
                    '时间', '叶绿素（SPAD）', '氮含量（mg/g）', '叶面温度（℃）', '叶面湿度（%RH）', '植物名称', '化肥中氮含量（%）', '化肥中氮的利用率（%）', '目标产量', '施肥量',
                    '时间', '叶绿素（SPAD）', '氮含量（mg/g）', '叶面温度（℃）', '叶面湿度（%RH）', '植物名称', '化肥中氮含量（%）', '化肥中氮的利用率（%）', '目标产量', '施肥量',
                    '时间', '叶绿素（SPAD）', '氮含量（mg/g）', '叶面温度（℃）', '叶面湿度（%RH）', '植物名称', '化肥中氮含量（%）', '化肥中氮的利用率（%）', '目标产量', '施肥量',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6',
                        'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6', 'field_7_7', 'field_7_8', 'field_7_9', 'field_7_10',
                        'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 'field_8_6', 'field_8_7', 'field_8_8', 'field_8_9', 'field_8_10',
                        'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5', 'field_9_6', 'field_9_7', 'field_9_8', 'field_9_9', 'field_9_10',
                        'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6', 'field_10_7', 'field_10_8', 'field_10_9', 'field_10_10',
                        'field_11_1', 'field_11_2', 'field_11_3', 'field_11_4', 'field_11_5', 'field_11_6', 'field_11_7', 'field_11_8', 'field_11_9', 'field_11_10',
                         ] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:BD1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:P2",
        "Q2:Z2",
        "AA2:AJ2",
        "AK2:AT2",
        "AU2:BD2"
      ];
      const list = this.tableDataTwo51;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo61() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点水分管理记录表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '',  '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '', ''],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '时期1', '', '时期2', '', '时期3', '', '时期4', '',
                    '时期5', '', '时期6', '', '时期7', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '日期', '深度（cm）', '日期', '深度（cm）', '日期', '深度（cm）', 
                    '日期', '深度（cm）', '日期', '深度（cm）', '日期', '深度（cm）', 
                    '日期', '深度（cm）']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                    'field_7_1', 'field_7_2', 'field_8_1', 'field_8_2',
                    'field_9_1', 'field_9_2','field_10_1', 'field_10_2',
                    'field_11_1', 'field_11_2','field_12_1', 'field_12_2',
                    'field_13_1', 'field_13_2',] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:T1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:H2",
        "I2:J2",
        "K2:L2",
        "M2:N2",
        "O2:P2",
        "Q2:R2",
        "S2:T2"
      ];
      const list = this.tableDataTwo61;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo62() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点化除管理记录表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[this.tableLabel, '', '', '', '', '',  '', '', '', '', '', '', '','', '', '', '', '', '', '', ''],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '化除1-播栽前', '', '', '', '', 
                    '化除2-播栽后或出苗后', '', '', '', '', 
                    '化除3播栽后或出苗后', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '日期', '除草剂名称', '亩用量/kg', '方法', '生育进程',
                    '日期', '除草剂名称', '亩用量/kg', '方法', '生育进程',
                    '日期', '除草剂名称', '亩用量/kg', '方法', '生育进程',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 
                    'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 
                    'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:U1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:K2",
        "L2:P2",
        "Q2:U2"
      ];
      const list = this.tableDataTwo62;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo63() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点病虫害防治记录表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
       const th1 = [[tableName,'', '', '', '', '', '', '', '', '', '','', '', '', '', '', '','', 
                    '', '', '', '', '','', '', '', '', '', '','', '', '', '', '', '','', ],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '病虫害防治1', '', '', '', '','', 
                    '病虫害防治2', '', '', '', '','', 
                    '病虫害防治3', '', '', '', '','', 
                    '病虫害防治4', '', '', '', '','', 
                    '病虫害防治5', '', '', '', '','', ]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '病虫害种类', '使用时间', '用药种类', '用药量(ml/亩)', '用药方法','生育进程',
                    '病虫害种类', '使用时间', '用药种类', '用药量(ml/亩)', '用药方法','生育进程',
                    '病虫害种类', '使用时间', '用药种类', '用药量(ml/亩)', '用药方法','生育进程',
                    '病虫害种类', '使用时间', '用药种类', '用药量(ml/亩)', '用药方法','生育进程',
                    '病虫害种类', '使用时间', '用药种类', '用药量(ml/亩)', '用药方法','生育进程', ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6',
                    'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 'field_8_6',
                    'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5', 'field_9_6',
                    'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6',
                    'field_11_1', 'field_11_2', 'field_11_3', 'field_11_4', 'field_11_5', 'field_11_6',] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AJ1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:L2",
        "M2:R2",
        "S2:X2",
        "Y2:AD2",
        "AE2:AJ2"
      ];
      const list = this.tableDataTwo63;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo64() {
      // this.downloadLoading = true // 加载遮罩层开启 
      var tableName=this.nian + "年度水稻监测点施肥管理记录表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件 
      const th1 = [[tableName,'', '', '', '', '', '', '', '', '', '','', '', '', '', '', '','', 
                    '', '', '', '', '','', '', '', '', '', '','', '', '', '', '', '','', ],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '基肥施肥（以折纯计kg/亩）1', '', '', '', '','', 
                    '分蘖肥施肥（以折纯计kg/亩）2', '', '', '', '','', 
                    '孕穗肥施肥（以折纯计kg/亩）3', '', '', '', '','', 
                    '孕穗肥施肥（以折纯计kg/亩）4', '', '', '', '','', 
                    '抽穗叶面肥施肥（以折纯计kg/亩）5', '', '', '', '','', ]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '时间', 'N', 'P', 'K', '方法','生育进程',
                    '时间', 'N', 'P', 'K', '方法','生育进程',
                    '时间', 'N', 'P', 'K', '方法','生育进程',
                    '时间', 'N', 'P', 'K', '方法','生育进程',
                    '时间', 'N', 'P', 'K', '方法','生育进程', ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6',
                    'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 'field_8_6',
                    'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5', 'field_9_6',
                    'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6',
                    'field_11_1', 'field_11_2', 'field_11_3', 'field_11_4', 'field_11_5', 'field_11_6',] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AJ1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:L2",
        "M2:R2",
        "S2:X2",
        "Y2:AD2",
        "AE2:AJ2"
      ];
      const list = this.tableDataTwo64;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo65() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点化学调节剂使用记录表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '',  '', '', '', '', '', '', '','', '', '', '', '', '', '', ''],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '化调剂1', '', '', '', '', 
                    '化调剂2', '', '', '', '', 
                    '化调剂3', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '使用时间', '化调剂名称', '亩用量/kg', '使用方法', '生育进程',
                    '使用时间', '化调剂名称', '亩用量/kg', '使用方法', '生育进程',
                    '使用时间', '化调剂名称', '亩用量/kg', '使用方法', '生育进程',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 
                    'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 
                    'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:U1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:K2",
        "L2:P2",
        "Q2:U2"
      ];
      const list = this.tableDataTwo65;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo71() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻双季稻苗情监测预测产表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
       const th1 = [[tableName,'',  
                    '', '', '', '', '', '','', '', '', '', '', '',
                    '',
                    '', '', '', '', '', '','', '', '', '', '', ''],
                    ['县（市、区）',  '早稻总面积（万亩）', 
                    '早稻6月20日前已抽穗扬花', '', '', '', '', '',
                    '早稻6月20日-30日抽穗扬花', '', '', '', '', '',
                    '晚稻总面积（万亩', 
                    '晚稻9月10日已抽穗扬花', '', '', '', '', '',
                    '晚稻9月10日以后抽穗扬花', '', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '',  
                    '面积(万亩)', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '八五折理论产量(kg/亩)',
                    '面积(万亩)', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '八五折理论产量(kg/亩)',
                    '',
                    '面积(万亩)', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '八五折理论产量(kg/亩)',
                    '面积(万亩)', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '八五折理论产量(kg/亩)',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 
                    'field_3_1', 'field_3_2', 'field_3_3', 'field_3_4', 'field_3_5', 'field_3_6',
                    'field_4_1', 'field_4_2', 'field_4_3', 'field_4_4', 'field_4_5', 'field_4_6',
                    'field_5',
                    'field_6_1', 'field_6_2', 'field_6_3', 'field_6_4', 'field_6_5', 'field_6_6',
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6',] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AA1",
        "A2:A3",
        "B2:B3",
        "O2:O3",
        "C2:H2",
        "I2:N2",
        "P2:U2",
        "P2:U2",
        "V2:AA2",
      ];
      const list = this.tableDataTwo71;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo72() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻双季稻收获进度调查（早稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '',  '', '', '','', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '地区类型', '总面积（万亩）', 
                    '收获进度1', '', '', '收获进度2', '', '',
                    '收获进度3', '', '', 
                    '单季稻实测产', '', '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '日期', '收获面积（万亩）', '', '日期', '收获面积（万亩）', '', 
                    '日期', '收获面积（万亩）', '',
                    '面积(万亩)', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '八五折理论产量(kg/亩)' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 
                    'field_4_1', 'field_4_2_1', 'field_4_2_2', 'field_5_1', 'field_5_2_1', 'field_5_2_2',
                    'field_6_1', 'field_6_2_1', 'field_6_2_2', 
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:R1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:F2",
        "G2:I2",
        "J2:L2",
        "M2:R2",
        "E3:F3",
        "H3:I3",
        "K3:L3",
      ];
      const list = this.tableDataTwo72;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo73() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻双季稻收获进度调查（晚稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '',  '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '地区类型', '总面积（万亩）', 
                    '收获进度1', '', '', '收获进度2', '', '',
                    '收获进度3', '', '', '收获进度4', '', '',
                    '单季稻实测产', '', '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '日期', '收获面积（万亩）', '', '日期', '收获面积（万亩）', '', 
                     '日期', '收获面积（万亩）', '','日期', '收获面积（万亩）', '',
                    '面积(万亩)', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '八五折理论产量(kg/亩)' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 
                    'field_4_1', 'field_4_2_1', 'field_4_2_2', 'field_5_1', 'field_5_2_1', 'field_5_2_2',
                    'field_6_1', 'field_6_2_1', 'field_6_2_2', 'field_7_1', 'field_7_2_1', 'field_7_2_2',
                    'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 'field_8_6'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:U1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:F2",
        "G2:I2",
        "J2:L2",
        "M2:O2",
        "P2:U2",
        "E3:F3",
        "H3:I3",
        "K3:L3",
        "N3:O3",
      ];
      const list = this.tableDataTwo73;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo74() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻双季稻测产表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '','', '', '','', '', '', '','', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',],
                    ['县（市、区）', '地区类型','稻作类型',
                     '全县面上平均水平', '', '', '', '', '',
                     '人工移栽', '', '', '', '', '',
                     '机械插秧', '', '', '', '', '',
                     '盘育抛秧', '', '', '', '', '',
                     '无盘旱育抛秧', '', '', '', '', '',
                     '撒直播', '', '', '', '', '',
                     '机条播', '', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '','',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2','field_3',
                     'field_4_1', 'field_4_2', 'field_4_3', 'field_4_4', 'field_4_5', 'field_4_6',
                     'field_5_1', 'field_5_2', 'field_5_3', 'field_5_4', 'field_5_5', 'field_5_6',
                     'field_6_1', 'field_6_2', 'field_6_3', 'field_6_4', 'field_6_5', 'field_6_6',
                     'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6',
                     'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 'field_8_6',
                     'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5', 'field_9_6',
                     'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6',] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AS1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:I2",
        "J2:O2",
        "P2:U2",
        "V2:AA2",
        "AB2:AG2",
        "AH2:AM2",
        "AN2:AS2",
      ];
      const list = this.tableDataTwo74;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo81() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻生产成本效益统计表（早稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName,'', '', '','','', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '', '','', '', '', ''],
                    ['县（市、区）', '地区类型', '稻作类型', '品种类型','监测田块',
                     '代表面积（万亩）', '栽植方式', '水稻亩产值（元）', '其中主产品产值（公斤/元）', '副产品产值（公斤/元）',
                    '劳动用工', '', '', '', '', '', '', '', '', '', '', '',
                    '物质费用', '', '', '', '', '', '', '', '', '', '', '', '',
                    '地租（元/亩）', '其他费用（元/亩）', '亩总成本（元）', '亩收益（元）'],
                    ['', '', '', '','','', '', '', '', '',
                    '育秧', '', '耕作', '', '移栽', '', '直播', '', '大田管理', '', '小计', '',
                    '种子（元/亩）', '化肥', '', '', '', '有机肥（元/亩）', '农药（元/亩）', '灌溉（元/亩）', 
                    '机械耕作（元/亩）', '机械插秧/直播（元/亩）', '机械收获（元/亩）', '其他（元/亩）', '小计（元/亩）',
                    '', '', '', ''],
                    ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '','','', '', '', '', '',
                    '数量（个/亩）', '单位用工价格（元/个）', '数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '价格（元/亩）',
                    '', '种类', '施用量（kg/亩）', '价格(元/kg)', '小计（元/亩）', '', '', '', '', '', '', '', '','', '', '', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5','field_6', 'field_7', 'field_8', 'field_9', 'field_10',
                    'field_11_1_1', 'field_11_1_2', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 
                    'field_11_4_1', 'field_11_4_2', 'field_11_5_1', 'field_11_5_2', 'field_11_6_1', 'field_11_6_2',
                    'field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_2_3', 'field_12_2_4', 
                    'field_12_3', 'field_12_4', 'field_12_5','field_12_6', 'field_12_7', 'field_12_8', 'field_12_9', 'field_12_10',
                    'field_13', 'field_14', 'field_15', 'field_16'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AM1",
        "A2:A4",
        "B2:B4",
        "C2:C4",
        "D2:D4",
        "E2:E4",
        "F2:F4",
        "G2:G4",
        "H2:H4",
        "I2:I4",
        "J2:J4",
        "K2:V2",
        "W2:AI2",
        "AJ2:AJ4",
        "AK2:AK4",
        "AL2:AL4",
        "AM2:AM4",
        "K3:L3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "S3:T3",
        "U3:V3",
        "W3:W4",
        "X3:AA3",
        "AB3:AB4",
        "AC3:AC4",
        "AD3:AD4",
        "AE3:AE4",
        "AF3:AF4",
        "AG3:AG4",
        "AH3:AH4",
        "AI3:AI4",
      ];
      const list = this.tableDataTwo81;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo82() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻生产成本效益统计表（晚稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName,'', '', '','','', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '', '','', '', '', ''],
                    ['县（市、区）', '地区类型', '稻作类型', '品种类型','监测田块',
                     '代表面积（万亩）', '栽植方式', '水稻亩产值（元）', '其中主产品产值（公斤/元）', '副产品产值（公斤/元）',
                    '劳动用工', '', '', '', '', '', '', '', '', '', '', '',
                    '物质费用', '', '', '', '', '', '', '', '', '', '', '', '',
                    '地租（元/亩）', '其他费用（元/亩）', '亩总成本（元）', '亩收益（元）'],
                    ['', '', '', '','','', '', '', '', '',
                    '育秧', '', '耕作', '', '移栽', '', '直播', '', '大田管理', '', '小计', '',
                    '种子（元/亩）', '化肥', '', '', '', '有机肥（元/亩）', '农药（元/亩）', '灌溉（元/亩）', 
                    '机械耕作（元/亩）', '机械插秧/直播（元/亩）', '机械收获（元/亩）', '其他（元/亩）', '小计（元/亩）',
                    '', '', '', ''],
                    ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '','','', '', '', '', '',
                    '数量（个/亩）', '单位用工价格（元/个）', '数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '单位用工价格（元/个）','数量（个/亩）', '价格（元/亩）',
                    '', '种类', '施用量（kg/亩）', '价格(元/kg)', '小计（元/亩）', '', '', '', '', '', '', '', '','', '', '', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5','field_6', 'field_7', 'field_8', 'field_9', 'field_10',
                    'field_11_1_1', 'field_11_1_2', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 
                    'field_11_4_1', 'field_11_4_2', 'field_11_5_1', 'field_11_5_2', 'field_11_6_1', 'field_11_6_2',
                    'field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_2_3', 'field_12_2_4', 
                    'field_12_3', 'field_12_4', 'field_12_5','field_12_6', 'field_12_7', 'field_12_8', 'field_12_9', 'field_12_10',
                    'field_13', 'field_14', 'field_15', 'field_16'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AM1",
        "A2:A4",
        "B2:B4",
        "C2:C4",
        "D2:D4",
        "E2:E4",
        "F2:F4",
        "G2:G4",
        "H2:H4",
        "I2:I4",
        "J2:J4",
        "K2:V2",
        "W2:AI2",
        "AJ2:AJ4",
        "AK2:AK4",
        "AL2:AL4",
        "AM2:AM4",
        "K3:L3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "S3:T3",
        "U3:V3",
        "W3:W4",
        "X3:AA3",
        "AB3:AB4",
        "AC3:AC4",
        "AD3:AD4",
        "AE3:AE4",
        "AF3:AF4",
        "AG3:AG4",
        "AH3:AH4",
        "AI3:AI4",
      ];
      const list = this.tableDataTwo82;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo91() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)气象资料列表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',  '', '', '', '', '', '', '', '',  '', '', '', '', '', '', '', '',  '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '1月份', '', '', '', '2月份', '', '', '', 
                    '3月份', '', '', '', '4月份', '', '', '', 
                    '5月份', '', '', '', '6月份', '', '', '', 
                    '7月份', '', '', '', '8月份', '', '', '', 
                    '9月份', '', '', '', '10月份', '', '', '', 
                    '11月份', '', '', '', '12月份', '', '', '',
                    ]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '平均温度', '有效积温', '光照时数', '降雨量', '平均温度', '有效积温', '光照时数', '降雨量', 
                      '平均温度', '有效积温', '光照时数', '降雨量', '平均温度', '有效积温', '光照时数', '降雨量',
                      '平均温度', '有效积温', '光照时数', '降雨量', '平均温度', '有效积温', '光照时数', '降雨量', 
                      '平均温度', '有效积温', '光照时数', '降雨量', '平均温度', '有效积温', '光照时数', '降雨量', 
                      '平均温度', '有效积温', '光照时数', '降雨量', '平均温度', '有效积温', '光照时数', '降雨量', 
                      '平均温度', '有效积温', '光照时数', '降雨量', '平均温度', '有效积温', '光照时数', '降雨量',  ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2_1', 'field_2_2', 'field_2_3', 'field_2_4', 'field_3_1', 'field_3_2', 'field_3_3', 'field_3_4',
                        'field_4_1', 'field_4_2', 'field_4_3', 'field_4_4', 'field_5_1', 'field_5_2', 'field_5_3', 'field_5_4',
                        'field_6_1', 'field_6_2', 'field_6_3', 'field_6_4', 'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4',
                        'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4',
                        'field_10_1', 'field_10_2', 'field_10_3', 'field_10_4', 'field_11_1', 'field_11_2', 'field_11_3', 'field_11_4',
                        'field_12_1', 'field_12_2', 'field_12_3', 'field_12_4', 'field_13_1', 'field_13_2', 'field_13_3', 'field_13_4',] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AW1",
        "A2:A3",
        "B2:E2",
        "F2:I2",
        "J2:M2",
        "N2:Q2",
        "R2:U2",
        "V2:Y2",
        "Z2:AC2",
        "AD2:AG2",
        "AH2:AK2",
        "AL2:AO2",
        "AP2:AS2",
        "AT2:AW2",
      ];
      const list = this.tableDataTwo91;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo92() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)抽穗期前、后各15天气象资料"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName,  '', '','','','','','','','','','','','','','','','','','','','','','','',''],
                    ['县（市、区）', '', '早稻-抽穗期前15天','','','','','','早稻-抽穗期后15天','','','','','',
                    '晚稻-抽穗期前15天','','','','','','晚稻-抽穗期前15天','','','','','']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', 
        '日平均气温（℃）','日最高气温（℃）','降雨量（mm）','日照时数（h）','晴好天气天数（天）','阴雨天气天数（天）',
        '日平均气温（℃）','日最高气温（℃）','降雨量（mm）','日照时数（h）','晴好天气天数（天）','阴雨天气天数（天）',
        '日平均气温（℃）','日最高气温（℃）','降雨量（mm）','日照时数（h）','晴好天气天数（天）','阴雨天气天数（天）',
        '日平均气温（℃）','日最高气温（℃）','降雨量（mm）','日照时数（h）','晴好天气天数（天）','阴雨天气天数（天）']] //对应表格三级输出的title
      const filterVal = ['field_1_1', 'field_1_2', 
        'field_2_1','field_2_2','field_2_3','field_2_4','field_2_5','field_2_6',
        'field_3_1','field_3_2','field_3_3','field_3_4','field_3_5','field_3_6',
        'field_4_1','field_4_2','field_4_3','field_4_4','field_4_5','field_4_6',
        'field_5_1','field_5_2','field_5_3','field_5_4','field_5_5','field_5_6'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:Z1",
        "A2:B3",
        "C2:H2",
        "I2:N2",
        "O2:T2",
        "U2:Z2",
      ];
      const list = this.tableDataTwo92;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo93() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻倒伏灾害表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '','', '', '', '', '', '','', '', '', ''],
                    ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['县（市、区）', '地区类型', '稻作类型','品系类型', '全县种植面积（万亩）', 
                    '倒伏时间（月/日-月/日）', '倒伏面积（万亩）', '占比（%）', '其中：成灾面积（万亩）',
                     '其中：绝收面积（万亩）', '产量损失（%）', '倒伏程度（级别）', '主要品种']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3','field_4', 'field_5', 
                    'field_6', 'field_7', 'field_8', 'field_9',
                    'field_10', 'field_11', 'field_12', 'field_13'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:M1"
      ];
      const list = this.tableDataTwo93;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo94() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻低温冷害表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                  ['县（市、区）', '地区类型', '稻作类型','品系类型', '低温冷害', 
                  '低温冷害发生时间', '全县种植面积（万亩）', '冷害总面积（万亩）', 
                  '冷害级别（万亩）', '', '', '', '',
                  '主要品种名称', '占比（%）', '其中：成灾面积（万亩）', '其中：绝收面积（万亩）', '产量损失（%）']
                  ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '','', '', '', '', '','1级', '2级', '3级', '4级', '5级', '', '', '', '', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3','field_4', 'field_5', 'field_6', 
      'field_7', 'field_8','field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5',
       'field_10', 'field_11', 'field_12', 'field_13', 'field_14'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:R1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:G3",
        "H2:H3",
        "N2:N3",
        "O2:O3",
        "P2:P3",
        "Q2:Q3",
        "R2:R3",
        "I2:M2",

      ];
      const list = this.tableDataTwo94;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo95() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻高温热害表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                  ['县（市、区）', '地区类型', '稻作类型','品系类型', '高温热害', 
                  '高温热害发生时间', '全县种植面积（万亩）', '热害总面积（万亩）', 
                  '热害级别（万亩）', '', '', '', '',
                  '主要品种名称', '占比（%）', '其中：成灾面积（万亩）', '其中：绝收面积（万亩）', '产量损失（%）']
                  ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '','', '', '', '', '','1级', '2级', '3级', '4级', '5级', '', '', '', '', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3','field_4', 'field_5', 'field_6', 
      'field_7', 'field_8','field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5',
       'field_10', 'field_11', 'field_12', 'field_13', 'field_14'] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:R1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:G3",
        "H2:H3",
        "N2:N3",
        "O2:O3",
        "P2:P3",
        "Q2:Q3",
        "R2:R3",
        "I2:M2"
      ];
      const list = this.tableDataTwo95;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo96() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度监测县(市、区)水稻涝害调查表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '','', '', '', '', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '',],
                  ['县（市、区）', '地区类型', '稻作类型','品系类型', 
                  '受灾统计表（万亩）', '', '', '', '', '', '', 
                  '受灾统计表（万亩）', '', '', '', '', '', '',
                  '受灾统计表（万亩）', '', '', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '','', 
                  '填报日期', '生育阶段与灾害症状', '受灾面积', '其中：成灾面积', '其中：绝收面积', '需补改种面积', '已补改种面积', 
                  '填报日期', '生育阶段与灾害症状', '受灾面积', '其中：成灾面积', '其中：绝收面积', '需补改种面积', '已补改种面积', 
                  '填报日期', '生育阶段与灾害症状', '受灾面积', '其中：成灾面积', '其中：绝收面积', '需补改种面积', '已补改种面积', ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3','field_4', 
                  'field_5_1', 'field_5_2', 'field_5_3', 'field_5_4', 'field_5_5', 'field_5_6', 'field_5_7', 
                  'field_6_1', 'field_6_2', 'field_6_3', 'field_6_4', 'field_6_5', 'field_6_6', 'field_6_7', 
                  'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6', 'field_7_7', ] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:Y1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:K2",
        "L2:R2",
        "S2:Y2",
      ];
      const list = this.tableDataTwo96;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo97() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点洪涝灾害表"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '','', '', '', '', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '',],
                  ['县（市、区）', '监测田块', '地区类型','品种名称','栽植方式' ,
                  '淹水日期', '退水日期', '生育阶段', '面积（亩）', '淹水高度（露叶尖、没顶）', '淹水天数', '', '程度']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '','','' , '', '', '', '', '', '', '其中没顶', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3','field_4', 'field_5', 'field_6', 'field_7', 
                  'field_8', 'field_9', 'field_10', 'field_11_1', 'field_11_2', 'field_12' ] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:M1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:G3",
        "H2:H3",
        "I2:I3",
        "J2:J3",
        "K2:L2",
        "M2:M3",
      ];
      const list = this.tableDataTwo97;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo101() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点灌浆速率调查表（早稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[tableName, '', '', '', '','',
                    '', '', '', '', '', '', '', '','', '', '','', '', '','', '', '',],
                    ['县（市、区）', '监测点代码', '稻作类型','地区类型', '品种名称','栽植方式', 
                    '灌浆速率测定1（扬花）', '', 
                    '灌浆速率测定2（扬花+7天）', '', '', 
                    '灌浆速率测定3（扬花+14天）', '', '',
                    '灌浆速率测定4（扬花+21天）', '', '',
                    '灌浆速率测定5（扬花+28天）', '', '',
                    '灌浆速率测定6（扬花+35天）', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '',
                    '日期', '千粒重(克)', 
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5','field_6',
                    'field_7_1', 'field_7_2', 
                    'field_8_1', 'field_8_2', 'field_8_3', 
                    'field_9_1', 'field_9_2', 'field_9_3', 
                    'field_10_1', 'field_10_2', 'field_10_3', 
                    'field_11_1', 'field_11_2', 'field_11_3',
                    'field_12_1', 'field_12_2', 'field_12_3', ] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:W1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:H2",
        "I2:K2",
        "L2:N2",
        "O2:Q2",
        "R2:T2",
        "U2:W2",
      ];
      const list = this.tableDataTwo101;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    handleDownloadTwo102() {
      // this.downloadLoading = true // 加载遮罩层开启
      ////console.log("11111");
      var tableName=this.nian + "年度水稻监测点灌浆速率调查表（晚稻）"
      const {export_json_to_excel} = require('../excel/Export2Excel2'); //引入文件
      ////console.log("2222")
      const th1 = [[this.tableLabel, '', '', '', '','',
                    '', '', '', '', '', '', '', '','', '', '','', '', '','', '', '',],
                    ['县（市、区）', '监测点代码', '稻作类型','地区类型', '品种名称','栽植方式', 
                    '灌浆速率测定1（扬花）', '', 
                    '灌浆速率测定2（扬花+7天）', '', '', 
                    '灌浆速率测定3（扬花+14天）', '', '',
                    '灌浆速率测定4（扬花+21天）', '', '',
                    '灌浆速率测定5（扬花+28天）', '', '',
                    '灌浆速率测定6（扬花+35天）', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '',
                    '日期', '千粒重(克)', 
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',
                    '日期', '千粒重(克)', '灌浆速率（克/千粒•天）',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5','field_6',
                    'field_7_1', 'field_7_2', 
                    'field_8_1', 'field_8_2', 'field_8_3', 
                    'field_9_1', 'field_9_2', 'field_9_3', 
                    'field_10_1', 'field_10_2', 'field_10_3', 
                    'field_11_1', 'field_11_2', 'field_11_3',
                    'field_12_1', 'field_12_2', 'field_12_3', ] // 对应表格tableData输出的数据
      ////console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:W1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:H2",
        "I2:K2",
        "L2:N2",
        "O2:Q2",
        "R2:T2",
        "U2:W2",
      ];
      const list = this.tableDataTwo102;
      ////console.log(list);
      const data = this.formatJson(filterVal, list);
      ////console.log(data);
      export_json_to_excel(th1, th2, header, data, tableName, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    // 将JSON转换成map
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
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
    width:99%;
    margin: 0 auto;
  }
  .searchBox{
    width: 100%;
    height: 50px;
  }
  .tableStyle{
    width: 100%;
    height: 90%;
    margin-top: 5px;
  }
  .totalRange .otherStyle{
    width: 99%;
    height: auto;
    margin: 0 auto;
    margin-top: 10px;
  }
  .cardHeader{
    font-size: 14px;
    font-weight: bold;
    margin: 0px;
    height: 15px;
  }
  .el-table .header-row{
    background: '#FF0000';
    
  }
  .fy{
    text-align: left;
    margin-top: 10px;
  }
</style>



