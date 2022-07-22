<template>

  <div class="totalRange">
    <div class="tableRange">
      <div class="searchBox">
        <el-row>
          <el-col >
            <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
              <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
              <YearPicker  v-model="startYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
              <el-button   style="margin-right: 20px" @click="getDataComparison('时期1-4')">查询对比</el-button>
              <router-link :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
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
        <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle"  :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>

          <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
          <el-table-column align="center" prop="field_1" width="100%" label="县（市、区）" fixed/>
          <el-table-column align="center" prop="field_2" width="100%" label="监测点代码" fixed/>
          <el-table-column align="center" prop="field_3" width="100%" label="地区类型" fixed/>
          <el-table-column align="center" prop="field_4" width="100%" label="稻作类型" fixed/>
          <el-table-column align="center" prop="field_5" width="100%" label="品种名称" fixed/>
          <el-table-column align="center" prop="field_6" width="100%" label="栽植方式" />
          <el-table-column align="center" label="够苗期">
            <el-table-column align="center" prop="field_7_1" width="100%" label="时间" />
            <el-table-column align="center" prop="field_7_2" width="140%" label="叶绿素（SPAD）" />
            <el-table-column align="center" prop="field_7_3" width="140%" label="氮含量（mg/g）" />
            <el-table-column align="center" prop="field_7_4" width="120%" label="叶面温度（℃）" />
            <el-table-column align="center" prop="field_7_5" width="140%" label="叶面湿度（%RH）" />
            <el-table-column align="center" prop="field_7_6" width="140%" label="植物名称" />
            <el-table-column align="center" prop="field_7_7" width="160%" label="化肥中氮含量（%）" />
            <el-table-column align="center" prop="field_7_8" width="180%" label="化肥中氮的利用率（%）" />
            <el-table-column align="center" prop="field_7_9" width="100%" label="目标产量" />
            <el-table-column align="center" prop="field_7_10" width="100%" label="施肥量" />
          </el-table-column>
          <el-table-column align="center" label="拔节期">
            <el-table-column align="center" prop="field_8_1" width="100%" label="时间" />
            <el-table-column align="center" prop="field_8_2" width="140%" label="叶绿素（SPAD）" />
            <el-table-column align="center" prop="field_8_3" width="140%" label="氮含量（mg/g）" />
            <el-table-column align="center" prop="field_8_4" width="120%" label="叶面温度（℃）" />
            <el-table-column align="center" prop="field_8_5" width="140%" label="叶面湿度（%RH）" />
            <el-table-column align="center" prop="field_8_6" width="140%" label="植物名称" />
            <el-table-column align="center" prop="field_8_7" width="160%" label="化肥中氮含量（%）" />
            <el-table-column align="center" prop="field_8_8" width="180%" label="化肥中氮的利用率（%）" />
            <el-table-column align="center" prop="field_8_9" width="100%" label="目标产量" />
            <el-table-column align="center" prop="field_8_10" width="100%" label="施肥量" />
          </el-table-column>
          <el-table-column align="center" label="抽穗期">
            <el-table-column align="center" prop="field_9_1" width="100%" label="时间" />
            <el-table-column align="center" prop="field_9_2" width="140%" label="叶绿素（SPAD）" />
            <el-table-column align="center" prop="field_9_3" width="140%" label="氮含量（mg/g）" />
            <el-table-column align="center" prop="field_9_4" width="120%" label="叶面温度（℃）" />
            <el-table-column align="center" prop="field_9_5" width="140%" label="叶面湿度（%RH）" />
            <el-table-column align="center" prop="field_9_6" width="140%" label="植物名称" />
            <el-table-column align="center" prop="field_9_7" width="160%" label="化肥中氮含量（%）" />
            <el-table-column align="center" prop="field_9_8" width="180%" label="化肥中氮的利用率（%）" />
            <el-table-column align="center" prop="field_9_9" width="100%" label="目标产量" />
            <el-table-column align="center" prop="field_9_10" width="100%" label="施肥量" />
          </el-table-column>
          <el-table-column align="center" label="孕穗期1">
            <el-table-column align="center" prop="field_10_1" width="100%" label="时间" />
            <el-table-column align="center" prop="field_10_2" width="140%" label="叶绿素（SPAD）" />
            <el-table-column align="center" prop="field_10_3" width="140%" label="氮含量（mg/g）" />
            <el-table-column align="center" prop="field_10_4" width="120%" label="叶面温度（℃）" />
            <el-table-column align="center" prop="field_10_5" width="140%" label="叶面湿度（%RH）" />
            <el-table-column align="center" prop="field_10_6" width="140%" label="植物名称" />
            <el-table-column align="center" prop="field_10_7" width="160%" label="化肥中氮含量（%）" />
            <el-table-column align="center" prop="field_10_8" width="180%" label="化肥中氮的利用率（%）" />
            <el-table-column align="center" prop="field_10_9" width="100%" label="目标产量" />
            <el-table-column align="center" prop="field_10_10" width="100%" label="施肥量" />
          </el-table-column>
          <el-table-column align="center" label="孕穗期2">
            <el-table-column align="center" prop="field_11_1" width="100%" label="时间" />
            <el-table-column align="center" prop="field_11_2" width="140%" label="叶绿素（SPAD）" />
            <el-table-column align="center" prop="field_11_3" width="140%" label="氮含量（mg/g）" />
            <el-table-column align="center" prop="field_11_4" width="120%" label="叶面温度（℃）" />
            <el-table-column align="center" prop="field_11_5" width="140%" label="叶面湿度（%RH）" />
            <el-table-column align="center" prop="field_11_6" width="140%" label="植物名称" />
            <el-table-column align="center" prop="field_11_7" width="160%" label="化肥中氮含量（%）" />
            <el-table-column align="center" prop="field_11_8" width="180%" label="化肥中氮的利用率（%）" />
            <el-table-column align="center" prop="field_11_9" width="100%" label="目标产量" />
            <el-table-column align="center" prop="field_11_10" width="100%" label="施肥量" />
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
        tableLabel:'叶片含氮测定仪导出数据',
        tableName:'crop_01_02_table_05_01',
        //数据有误输入原因
        reasonForm: {reason:''},
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
        startYear:'2021',
        endYear:'2022',
        column_id:'',
        point:'',
        people:'',
        temSeries:[],
        option:{},
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
        //console.log(this.point);
        this.getTableData(this.people,this.point,this.startYear,this.endYear);
      },
      //获取数据对比
      getDataComparison(title){
        if(this.startYear != '' && this.endYear != ''){
          this.getTableData(this.people,this.point,this.startYear,this.endYear);
        }

        // this.getTableData(people,this.startYear,this.endYear,title);

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
      getTableData(people,point,startYear,endYear,title){
        const _this = this;
        //console.log(point)
        if(startYear == ''){
          startYear = 0;
        }
        if(endYear == ''){
          endYear = 0;
        }
        axios({
          url:'/getAllDataTwo0501',
          method:'get',
          params:{
            people:people,
            point:point,
            column_id:'',
            startYear:startYear,
            endYear:endYear
          }
        }).then(res=>{

          // _this.tableData=res.data;
          var totalTableData=res.data;
          this.dataNum=res.data.length;
          console.log(totalTableData)
          _this.tableData = totalTableData;
          //console.log(_this.tableData);
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



