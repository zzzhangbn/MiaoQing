<template>
  
    <div class="totalRange">
      <div class="tableRange">
            <div class="searchBox">
            </div>
            <div class="tableStyle">
              <!-- <el-table :data="pageData" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height:'30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <el-table-column width="100%" :label="tableLabel" align="center">
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="county" label="县（市、区）" />
                  <el-table-column align="center" prop="county_area" label="全县总面积（万亩）" />
                  <el-table-column align="center" prop="pin_zhong_name" label="品种名" />
                  <el-table-column align="center" prop="area_data" label="面积（万亩）" />
                  <el-table-column align="center" prop="area_percent" label="面积占比" />
                </el-table-column>
              </el-table> -->
              <table id="sum_tab" style=" width: 100%;  border: 1px solid #d1d1d1; line-height: 40px; text-align: center; " border="1" cellspacing="0">
              <tr>
                <td colspan="5" style="background-color:  #6E8B3D; color: #ffffff">
                  年度监测县(市、区)水稻生产主推品种调查表
                </td>
              </tr>
              <tr style="background-color: #6E8B3D; color: green">
                <td style="width: 20%">县（市、区）</td>
                <td style="width: 20%">全县总面积（万亩）</td>
                <td style="width: 20%">品种名</td>
                <td style="width: 20%">栽种面积</td>
                <td style="width: 20%">占比</td>
              </tr>
              <template v-for="(i, index) in tableData">
                <tr>
                  <td>
                    <el-input v-model="tableData[index].county" readonly style="height: 100%;"></el-input>
                  </td>
                  <td>
                      <el-input v-model="tableData[index].county_area" readonly></el-input>
                  </td>
                  <td style="text-align: center">
                    <el-input v-model="tableData[index].pin_zhong_name" readonly></el-input>
                  </td>
                  <td style="text-align: center">
                    <el-input v-model="tableData[index].area_data" readonly></el-input>
                  </td>
                  <td style="text-align: center">
                    <el-input v-model="tableData[index].area_percent" readonly></el-input>
                  </td>
                </tr>
              </template>
            </table>
              <el-pagination class="fy"
                      layout="prev, pager, next, total"
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
        <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
          <el-button type="info" plain>返回</el-button>
        </router-link>
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
      //表中总数据
      TotalData:[],
      //表中分页数据
      tableData: [],
      //表名
      tableLabel:'监测县(市、区)水稻生产主推品种调查表',
      tableName:'crop_01_01_table_01_02',
      //县的option
      countys:[],
      county: '',
      //地区类型option
      areaTypes:[],
      areaType: '',
      //稻作类型option
      riceTypes:[],
      riceType: '',
      //系统时间
      systemTime:'',
      //退回原因对话框
      dialogVisible:false,
      //暂存退回记录
      rowRecord:[],
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,//数据个数
      //分页后当前页面数据
      pageData:[],
      //暂存参数
      areas:'',
      citys:'',
      years:'',
      temSeries:[],
      option:{},
      open: false,
      startYear:'2019',
      endYear:'2022',
      field1Row:0,
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
    // this.areas = this.$route.query.area;
    // this.citys = this.$route.query.city;
    // this.years = this.$route.query.year;
      var people = localStorage.getItem('ms_username');
      this.getTableData(people,this.startYear,this.endYear);

    },

    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
        if(rowIndex === 3){ 
           return {display:"none"};
        };
        if(rowIndex === 0){
          return{background:'#6E8B3D',color:'#FFFFFF',}
        }
        if(rowIndex==1 || rowIndex==2){
            return{height:'0',padding:'1px',fontSize:'5px',background:'#CDBE70',color:'green'};
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
    getTableData(people,startYear,endYear){
      var column_id='';
      if(startYear == ''){
        startYear = 0;
      }
      if(endYear == ''){
        endYear = 0;
      }
       const _this = this;
       axios({
          url: '/getUserAllData',
          method:'post',
          params:{
            // county:this.citys,
            // areaType:this.areas,
            // year:this.years
            people:people,
            column_id:column_id,
            flag:1,
           }
       }).then(res=>{
           this.tableData=res.data;
           this.dataNum = this.tableData.length;
           this.field1Row = this.tableData.length;
            //console.log(_this.tableData);
          
           
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },


    //分页
    handleCurrentChange(currentPage){
      this.currentPage = currentPage;
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
    overflow-x:auto;
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



