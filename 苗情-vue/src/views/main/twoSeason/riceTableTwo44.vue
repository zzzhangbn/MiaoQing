<template>
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">
            <el-row>
              <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                <router-link :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
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
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
              <!-- <el-table-column :label="tableLabel" align="center"> -->
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="110%" label="县（市、区）" fixed/>
                <el-table-column align="center" prop="field_2"  label="监测地块" fixed/>
                <el-table-column align="center" prop="field_3"  label="地区类型" fixed/>
                <el-table-column align="center" prop="field_4"  label="稻作类型" fixed/>
                <el-table-column align="center" prop="field_5"  label="品系类型" fixed/>
                <el-table-column align="center" prop="field_6"  label="品种名称" fixed/>
                <el-table-column align="center" prop="field_7"  label="栽培方式" fixed/>
                <el-table-column align="center" prop="field_8" label="播种期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_9" label="出苗期（月/日）" :formatter="dateFormat"/>
                <!-- <el-table-column align="center" prop="field_10" label="移栽期（月/日）" :formatter="dateFormat"/> -->
                <el-table-column align="center" prop="field_10" label="移栽期（月/日）" fixed/>
                <el-table-column align="center" prop="field_fennie" label="分蘖期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_11" label="够苗期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_12"  label="有效分蘗临界叶龄期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_13" label="拔节期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_14" label="孕穗期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_15" label="抽穗期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_16" label="齐穗期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_17" label="成熟期（月/日）" :formatter="dateFormat"/>
                <el-table-column align="center" prop="field_18" label="全生育期（日）" />
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
import moment from 'moment'
export default {
  inject: ['reload'],
  data() {
    return {          
      activeName:'1',   
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'水稻监测点苗情生育期统计表',
      tableName:'crop_01_02_table_04_04',
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
      startYear:2019,
      endYear:2031,
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
      var people = localStorage.getItem('ms_username');
      var point = this.$route.query.point;
      this.getTableData(people,point);
    }, 
    //切换tabs
    handleClick(tab,event){
     
    }, 
    dateFormat:function(row,column){

var date = row[column.property];

if(date == undefined){return ''};

return moment(date).format("YYYY-MM-DD")

},
    //获取时间
    //获取系统时间
    getMyTime(){
      var myDate = new Date();
    
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
    
      this.systemTime = myTime;
    },  
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
  
         if(rowIndex === 2){ 
           return {display:"none"};
        };
        // if(rowIndex === 0){
        //   return{background:'#6E8B3D',color:'#FFFFFF',}
        // }
        if(rowIndex==1 || rowIndex==0){
            // return {background:'#eee',color:'#606266'};
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
    getTableData(people,point){
      var column_id = '';
      const _this = this;
      axios({
        url:'/getUserAllDataTwo0404',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          monitor_location:point,
          startYear:this.startYear,
          endYear:this.endYear
        }
      }).then(res=>{
          this.tableData=res.data;
          this.dataNum = this.tableData.length;
          console.log(this.tableData);
          // console.log(this.tableData.length);
          var i=0;
          for(i; i<this.tableData.length; i++){
            console.log(i)
            console.log(this.tableData[i].field_7)
            if(this.tableData[i].field_7=="撒直播（水）"){
              this.tableData[i].field_10="--"
            }
          }
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },

    //数据分析图跳转
    getDataAnalyse(){
      //console.log("11111")
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



