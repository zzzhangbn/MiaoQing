<template>

  <div class="totalRange">
    <div class="tableRange">
      <div class="searchBox">
        <el-row>
          <el-col >
            <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
              <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
              <!--<YearPicker  v-model="beginYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>-->
              <!--<el-button   style="margin-right: 20px" @click="getDataComparison('')">查询对比</el-button>-->
              <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
                <el-button type="info" plain>返回</el-button>
              </router-link>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="tableStyle">

        <el-row>
          <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:10px">
            <span>{{tableLabel}}</span>
          </div>
        </el-row>

        <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
          <!-- <el-table-column :label="tableLabel" align="center"> -->
          <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
          <el-table-column align="center" width="100%" label="县（市、区）" >
            <el-table-column align="center" width="100%" prop="field_1_1" label="" />
            <el-table-column align="center" width="100%" prop="field_1_2" label="" />
          </el-table-column>
          <el-table-column align="center" label="抽穗期前15天">
            <el-table-column align="center" prop="field_2_1"  label="日平均气温（℃）" />
            <el-table-column align="center" prop="field_2_2"  label="日最高气温（℃）" />
            <el-table-column align="center" prop="field_2_3"  label="降雨量（mm）" />
            <el-table-column align="center" prop="field_2_4"  label="日照时数（h）" />
            <el-table-column align="center" prop="field_2_5"  label="晴好天气天数（天）" />
            <el-table-column align="center" prop="field_2_6"  label="阴雨天气天数（天）" />
          </el-table-column>
          <el-table-column align="center" label="抽穗期后15天">
            <el-table-column align="center" prop="field_3_1"  label="日平均气温（℃）" />
            <el-table-column align="center" prop="field_3_2"  label="日最高气温（℃）" />
            <el-table-column align="center" prop="field_3_3"  label="降雨量（mm）" />
            <el-table-column align="center" prop="field_3_4"  label="日照时数（h）" />
            <el-table-column align="center" prop="field_3_5"  label="晴好天气天数（天）" />
            <el-table-column align="center" prop="field_3_6"  label="阴雨天气天数（天）" />
          </el-table-column>
          <!-- </el-table-column> -->
        </el-table>
        <el-pagination class="fy"
                       layout="prev,pager, next, total"
                       @current-change="handleCurrentChange"
                       :current-page="currentPage"
                       :page-size="pagesize"
                       :total="dataNum"
                       background
        >
        </el-pagination>

      </div>
    </div>

  </div>

</template>


<script>
  import axios from 'axios'
  import * as math from 'mathjs'
  import YearPicker from './YearPicker'
  export default {
    inject: ['reload'],
    components: {
      YearPicker
    },
    data() {
      return {
        activeName:'1',
        // 表格组件 列表数据对象
        //表中数据
        tableData: [],
        //表名
        tableLabel:'监测县(市、区)抽穗期前后气象表',
        tableName:'crop_01_01_table_09_02',
        //数据有误输入原因
        reasonForm: {reason:''},
        //县的option
        countys:[],
        county: '',
        //系统时间
        systemTime:'',
        //退回原因对话框
        dialogVisible:false,
        //暂存退回记录
        rowRecord:[],
        //分页
        pagesize:15,  //每页的数据条数
        currentPage:1,//默认开始页面
        dataNum:0,
        //年度
        currentYear:'',   //本年度
        previousYear:'',   //上年度
        //暂存参数
        areas:'',
        citys:'',
        years:'',
        //本年度县
        currentCounty:[],
        beginYear:'2020',
        endYear:'2022',
        column_id:'',
        point:'',
        people:'',
      };
    },
    created() {
      this.initData();
    },
    methods: {
      //初始化数据
      initData() {
        this.people = localStorage.getItem('ms_username');
        this.getTableData(this.people,this.beginYear,this.endYear);
      },
      //获取数据对比
      getDataComparison(title){
        if(this.beginYear != '' && this.endYear != ''){
          this.getTableData(this.people,this.beginYear,this.endYear);
        }
      },
      //改变开始年份
      changeYear1(){
        if(this.beginYear != '' && this.endYear !=''){
          if(this.beginYear > this.endYear){
            this.beginYear = '';
            this.$message.warning("该年份不可选择！");
          }
        }
        // console.log(this.beginYear);
      } ,
      //改变结束年份
      changeYear2(){
        var date = new Date();
        var currentYear = date .getFullYear();
        if(this.endYear > currentYear){
          this.endYear = currentYear;
          return this.$message.warning("不可选择该年份！");
        }
        if(this.beginYear > this.endYear){
          this.beginYear = '';
          return this.$message.warning("开始年份不可大于结束年份！");
        }
      },
      //切换tabs
      handleClick(tab,event){
        console.log(tab,event);
      },
      //获取年度时间
      getYear(){
        var myDate = new Date();
        // this.currentYear = myDate.getFullYear();
        // this.previousYear = parseInt(this.currentYear) - 1;

      },

      //获取系统时间
      getMyTime(){
        var myDate = new Date();
        //console.log(myDate);
        var sep1 = "-";
        var sep2 = ":";
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var hour = myDate.getHours();
        var minute = myDate.getMinutes();
        var second = myDate.getSeconds();
        if (month >= 1 && month <= 9) {
          month = "0" + month;
        };
        if (day >= 0 && day <= 9) {
          day = "0" + day;
        };
        if(hour >= 0 && hour <= 9) {
          hour = "0" + hour;
        };
        if(minute >= 0 && minute <= 9) {
          minute = "0" + minute;
        };
        if(second >= 0 && second <= 9) {
          second = "0" + second;
        };
        var myTime = myDate.getFullYear() + sep1 + month + sep1 + day
                + " " + hour + sep2 + minute + sep2 + second;
        //console.log(myTime);
        this.systemTime = myTime;
      },
      //表头跨列隐藏
      headerStyle({row,column,rowIndex,columnIndex}){
        // console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex === 2){
          return {display:"none"};
        };
        if(rowIndex==1 || rowIndex === 0){
          return{height:'0',padding:'1px',fontSize:'5px',background:'#6E8B3D',color:'#FFFFFF'};
        };
      },
      //表头颜色
      tableRowClassName({row,rowIndex}){
        if(rowIndex === 6){
          return '.header-row';
        }
        return '';
      },
      //获取下拉框内容
      getCountySelection(){
        const _this = this;
        axios({
          url: '/getCounty1',
          method:'get',
        }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          //console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
            if(optionData[i].caption === "县（市、区）"){
              countyData.push({"label":optionData[i].list,"value":optionData[i].list});
            };
          };
          _this.countys = countyData;
          //_this.getTableData();
        }).catch(err=>{
          return this.$message.error("查询数据失败！");
        });
      },
      //处理小数点
      add(value) {
        const precision = 14
        return Number(math.format(value, precision))
      },
      //获取当年具体县数据
      /*getCurrentCounty(){
        const _this = this;
        axios({
          url: '/getCurrentCounty',
          method:'post',
          params:{county:this.citys,
            year:this.years}
        }).then(res=>{
          console.log(res.data);
          _this.currentCounty = res.data;

        }).catch(err=>{
          return this.$message.error("查询数据失败！");
        });
      },*/
      //获取表数据
      getTableData(people,beginYear,endYear){
        const _this = this;
        console.log(beginYear)
        console.log(endYear)
        axios({
          url:'/getAllData0902',
          method:'get',
          params:{
            people:people,
            column_id:'',
            year:'1',
            beginYear:beginYear,
            endYear:endYear
          }
        }).then(res=>{
          if(res.data.length > 0){
          _this.currentCounty.push(res.data[0].field_1_1);
          console.log(res.data)
          //console.log(_this.currentCounty);
          var tempData=res.data;
          for(var i=0; i<tempData.length; i++){
            tempData[i].field_1_2 = tempData[i].field_1_2+"年";
          }
          //console.log(res.data);
          var year = _this.endYear;
          //处理合计
          if(tempData.length != 0){
            _this.dataNum = tempData.length;
          }else{
            _this.dataNum = 0;
          };
          _this.tableData = tempData;
          // console.log(_this.tableData);
          }
        }).catch(err=>{
          return this.$message.error("查询数据失败！");
        });
      },
      //分页
      handleCurrentChange:function(currentPage){
        this.currentPage = currentPage;
        //console.log(this.currentPage);
      },
      //分页后序号连续处理
      countMethod(index){
        return (this.currentPage-1)*this.pagesize + index +1;
      },
      // 将JSON转换成map
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]))
      },
    }
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
  .otherStyle{
    width: 100%;
    height: 70px;
    margin-top: 260px;
  }
  .el-table .header-row{
    background: '#FF0000';

  }
  .fy{
    text-align: left;
    margin-top: 10px;
  }
</style>







