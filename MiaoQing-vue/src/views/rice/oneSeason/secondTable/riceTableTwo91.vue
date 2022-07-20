<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col :span="3">
                <template>                 
                  <el-select v-model="county" clearable  style="width:150px; margin-left: 20px;" placeholder="县（市、区）">
                    <el-option
                      v-for="item in countys"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>                 
                </template>
              </el-col>
            <el-col :span="4">
              <el-button type="info" @click="reSet">重置</el-button>
              <el-button type="info" @click="searchInfo">查询</el-button>
            </el-col>
            <el-col :span="3">
              <!-- <el-button  type="info" plain @click="downloadAndPrint">下载打印</el-button> -->
                    <el-button  type="info" plain @click="handleDownload">全部数据导出Excel</el-button>
            </el-col>
            </el-row>
          </div>
          <div class="tableStyle">
            <el-row>
              <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:10px">
                <span>{{tableLabel}}</span>
              </div>
            </el-row>
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle"  :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
               
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" fixed prop="field_1" width="105%" label="县（市、区）" />
                <el-table-column align="center" label="1月份">
                  <el-table-column align="center" prop="field_2_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_2_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_2_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_2_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="2月份">
                  <el-table-column align="center" prop="field_3_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_3_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_3_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_3_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="3月份">
                  <el-table-column align="center" prop="field_4_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_4_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_4_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_4_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="4月份">
                  <el-table-column align="center" prop="field_5_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_5_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_5_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_5_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="5月份">
                  <el-table-column align="center" prop="field_6_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_6_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_6_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_6_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="6月份">
                  <el-table-column align="center" prop="field_7_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_7_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_7_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_7_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="7月份">
                  <el-table-column align="center" prop="field_8_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_8_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_8_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_8_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="8月份">
                  <el-table-column align="center" prop="field_9_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_9_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_9_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_9_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="9月份">
                  <el-table-column align="center" prop="field_10_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_10_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_10_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_10_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="10月份">
                  <el-table-column align="center" prop="field_11_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_11_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_11_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_11_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="11月份">
                  <el-table-column align="center" prop="field_12_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_12_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_12_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_12_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                <el-table-column align="center" label="12月份">
                  <el-table-column align="center" prop="field_13_1" width="130%" label="平均温度（℃）" />
                  <el-table-column align="center" prop="field_13_2" width="130%" label="有效积温（℃）" />
                  <el-table-column align="center" prop="field_13_3" width="130%" label="光照时数（h）" />
                  <el-table-column align="center" prop="field_13_4" width="130%" label="降雨量（mm）" />
                </el-table-column>
                
                 
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
      activeName:'1',      
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'',
      tableName:'crop_01_02_table_09_01',
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
      //暂存参数
      areas:'',
      citys:'',
      years:'',
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.areas = this.$route.query.area;
      this.citys = this.$route.query.city;
      this.years = this.$route.query.year;
      this.tableLabel= this.years+"年度监测县(市、区)气象资料表";
      //console.log(this.areas,this.citys,this.years);
      //this.getHeaderList();
      this.getTableData();
      this.getCountySelection();
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
    }, 
    //获取时间
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
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex==0 || rowIndex==1){
            return{height:'0',padding:'2px',fontSize:'7px',background:'#6E8B3D',color:'#FFFFFF'};
        };
    },
    //表头颜色
    tableRowClassName({row,rowIndex}){
        if(rowIndex === 6){
          return '.header-row';
        }
        return '';
    },
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
    },
    //获取表数据
    getTableData(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo91',
          method:'post',
          params:{
            areaType:this.areas,
            county:this.citys,
            year:this.years
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          _this.dataNum = totalTableData.length;
          _this.tableData = totalTableData;
          //console.log(_this.tableData);
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //获取下拉框内容
    getCountySelection(){
      const _this = this;
      axios({
          url: '/getCounty2',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          ////console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          _this.countys = countyData;

       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //重置
    reSet(){
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      var years = this.$route.query.year;
      const _this = this;
      axios({
        url:'/getSeekInfoTwo91',
        method:'post',
        params:{county:this.county,
                year:this.years,
        }
      }).then(res=>{
         //_this.tableData=res.data;
          var totalTableData=res.data;
          _this.dataNum = totalTableData.length;
          _this.tableData = totalTableData;
          //console.log(_this.tableData);
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
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
    //导出excel
    // 导出Excel点击方法
    handleDownload() {
      // this.downloadLoading = true // 加载遮罩层开启
      //console.log("11111");
      const {export_json_to_excel} = require('../../excel/Export2Excel2'); //引入文件
      //console.log("2222")
      const th1 = [[this.tableLabel, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',  '', '', '', '', '', '', '', '',  '', '', '', '', '', '', '', '',  '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
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
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
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
      const list = this.tableData;
      //console.log(list);
      const data = this.formatJson(filterVal, list);
      //console.log(data);
      export_json_to_excel(th1, th2, header, data, this.tableLabel, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    // 将JSON转换成map
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    returnPage(){
      var diqu = this.areas;
      var xian = this.citys;
      var nian = this.years;
      //console.log(diqu,xian,nian)
      if(xian ==''){
        this.$router.push({path:'/dataHome',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='宣州区') || (xian=='庐江县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='怀宁县') || (xian=='枞阳县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='贵池区')|| (xian=='南陵县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else{
        this.$router.push({path:'/dataHome1',query:{area:diqu,city:xian,year:nian}});
      };
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



