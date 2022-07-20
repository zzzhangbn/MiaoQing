<template>
  
  <div class="totalRange">
      <div class="tableRange">
        <div class="searchBox">            
          <el-row>
              <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <YearPicker v-model="startYear"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker v-model="endYear"/>
                  <el-button   style="margin-right: 20px" @click="getDataComparison()">查询数据</el-button>
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                  <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
                    <el-button type="info" plain>返回</el-button>
                  </router-link>
                </div>
          </el-row>
        </div>
        <div class="tableStyle">
          <el-row>
              <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:10px">
                <span>{{tableLabel}}</span>
              </div>
            </el-row>
          <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" style="width: 100%" :row-style="{height: '30px'}" :cell-style="{padding:'0'}" row-key="id" stripe>
            <!-- <el-table-column :label="tableLabel" align="center"> -->
              <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
              <el-table-column align="center" prop="field_1" width="100%" label="县（市、区）" fixed/>
              <el-table-column align="center" prop="field_2" width="100%" label="地区类型" fixed/>
              <el-table-column align="center" prop="field_3" width="100%" label="稻作类型" fixed/>
              <el-table-column align="center" prop="field_4" width="100%" label="品种类型" />
              <el-table-column align="center" prop="field_5" width="100%" label="监测田块" />
              <el-table-column align="center" prop="field_6" width="140%" label="代表面积（万亩）" />
              <el-table-column align="center" prop="field_7" width="100%" label="栽植方式" />
              <el-table-column align="center" prop="field_8" width="140%" label="水稻亩产值（元）" />
              <el-table-column align="center" prop="field_9" width="200%" label="其中主产品产值（公斤/元）" />
              <el-table-column align="center" prop="field_10" width="170%" label="副产品产值（公斤/元）" />
              <el-table-column align="center" label="劳动用工">
                <el-table-column align="center" label="育秧" >
                  <el-table-column align="center" prop="field_11_1_1" width="130%" label="数量（个/亩）" />
                  <el-table-column align="center" prop="field_11_1_2" width="130%" label="单位用工价格（元/个）" />
                </el-table-column>
                <el-table-column align="center" label="耕作" >
                  <el-table-column align="center" prop="field_11_2_1" width="130%" label="数量（个/亩）" />
                  <el-table-column align="center" prop="field_11_2_2" width="130%" label="单位用工价格（元/个）" />
                </el-table-column>
                <el-table-column align="center" label="移栽" >
                  <el-table-column align="center" prop="field_11_3_1" width="130%" label="数量（个/亩）" />
                  <el-table-column align="center" prop="field_11_3_2" width="130%" label="单位用工价格（元/个）" />
                </el-table-column>
                <el-table-column align="center" label="直播" >
                  <el-table-column align="center" prop="field_11_4_1" width="130%" label="数量（个/亩）" />
                  <el-table-column align="center" prop="field_11_4_2" width="130%" label="单位用工价格（元/个）" />
                </el-table-column>
                <el-table-column align="center" label="大田管理" >
                  <el-table-column align="center" prop="field_11_5_1" width="130%" label="数量（个/亩）" />
                  <el-table-column align="center" prop="field_11_5_2" width="130%" label="单位用工价格（元/个）" />
                </el-table-column>
                <el-table-column align="center" label="小计" >
                  <el-table-column align="center" prop="field_11_6_1" width="130%" label="数量（个/亩）" />
                  <el-table-column align="center" prop="field_11_6_2" width="130%" label="价格（元/亩）" />
                </el-table-column>
              </el-table-column>
              <el-table-column align="center" label="物质费用">
                <el-table-column align="center" prop="field_12_1" width="130%" label="种子（元/亩）" />
                <el-table-column align="center" label="化肥">
                  <el-table-column align="center" prop="field_12_2_1" width="120%" label="种类" />
                  <el-table-column align="center" prop="field_12_2_2" width="130%" label="施用量（kg/亩）" />
                  <el-table-column align="center" prop="field_12_2_3" width="120%" label="价格（元/kg）" />
                  <el-table-column align="center" prop="field_12_2_4" width="120%" label="小计（元/亩）" />
                </el-table-column>
                  <el-table-column align="center" prop="field_12_3" width="130%" label="有机肥（元/亩）" />
                  <el-table-column align="center" prop="field_12_4" width="120%" label="农药（元/亩）" />
                  <el-table-column align="center" prop="field_12_5" width="120%" label="灌溉（元/亩）" />
                  <el-table-column align="center" prop="field_12_6" width="140%" label="机械耕作（元/亩）" />
                  <el-table-column align="center" prop="field_12_7" width="180%" label="机械插秧/直播（元/亩）" />
                  <el-table-column align="center" prop="field_12_8" width="150%" label="机械收获（元/亩）" />
                  <el-table-column align="center" prop="field_12_9" width="120%" label="其他（元/亩）" />
                  <el-table-column align="center" prop="field_12_10" width="120%" label="小计（元/亩）" />
              </el-table-column>
              <el-table-column align="center" prop="field_13" width="120%" label="地租（元/亩）" />
              <el-table-column align="center" prop="field_14" width="140%" label="其他费用（元/亩）" />
              <el-table-column align="center" prop="field_15" width="120%" label="亩总成本（元）" />
              <el-table-column align="center" prop="field_16" width="110%" label="亩收益（元）" />
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
          <el-dialog title="退回原因:" :visible.sync="dialogVisible" width="30%">
            <el-form :model='reasonForm'>
              <el-form-item label='' label-width="10px">
                <el-input v-model="reasonForm.reason" auto-complete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="returnData">确 定</el-button>
            </div>
          </el-dialog>
        </div>
      </div>
   </div>

</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
import YearPicker from './YearPicker'
import echarts from 'echarts'
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
    column_id :'',
    //表名
    tableLabel:'监测县(市、区)水稻生产成本效益统计表',
    tableName:'crop_01_01_table_08_01',
    //数据有误输入原因
    reasonForm: {reason:''},
    //县的option
    countys:[],
    county: '',
    //地区类型option
    areaTypes:[],
    areaType: '',
    //稻作类型option
    riceTypes:[],
    riceType: '',
    //品系类型option
    categorys:[],
    category: '',
    //栽植方式option
    plantWays:[],
    plantWay: '',
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
    startYear:2019,//开始年份
    endYear:2022//结束年份
  };
},
created() {
  this.initData();
  sessionStorage.removeItem("path");
  sessionStorage.setItem("path","/dataHome1");
},
methods: {
  //初始化数据
  initData() {
    this.people = localStorage.getItem('ms_username');
    this.point = this.$route.query.point;
    this.getTableData(this.people,this.point,this.startYear,this.endYear);
  },
  //获取数据对比
  getDataComparison(title){
    if(this.startYear != '' && this.endYear != ''){
      this.getTableData(this.people,this.point,this.startYear,this.endYear);
    }
  },
  //改变开始年份
  changeYear1(){
    if(this.startYear != '' && this.endYear !=''){
      if(this.startYear > this.endYear){
        this.startYear = '';
        this.$message.warning("该年份不可选择！");
      }
    }
    // //console.log(this.startYear);
  } ,
  //改变结束年份
  changeYear2(){
    var date = new Date();
    var currentYear = date .getFullYear();
    if(this.endYear > currentYear){
      this.endYear = currentYear;
      return this.$message.warning("不可选择该年份！");
    }
    if(this.startYear > this.endYear){
      this.startYear = '';
      return this.$message.warning("开始年份不可大于结束年份！");
    }
  },
  //切换tabs
  handleClick(tab,event){
    //console.log(tab,event);
  }, 

  //表头跨列隐藏
  headerStyle({row,column,rowIndex,columnIndex}){
   // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
       if(rowIndex === 3){ 
         return {display:"none"};
      };
      if(rowIndex === 0){
        return{background:'#6E8B3D',color:'#FFFFFF',}
      }
      if(rowIndex==1 || rowIndex==2){
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
  //处理小数点
  add(value) {
      const precision = 14
      return Number(math.format(value, precision))
  },
  //获取表数据
  getTableData(people,point,startYear,endYear){
    const _this = this;
    var column_id = '';
    axios({
      url:'/getUserAllData0801',
      method:'post',
      params:{
          people:people,
          point:point,
          column_id:column_id,
          startYear:startYear,
          endYear:endYear
      }
    }).then(res=>{
      this.tableData=res.data;
      this.dataNum = this.tableData.length;   
      console.log(res.data)    
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
}
};
</script>

<style scoped>
/* .dashWrap{
    flex:1;
    width:1525px;
    overflow-y:auto;
    display:flex;
    flex-direction: column;
} */
.totalRange{
  width:100%;
  height: 100%;
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



