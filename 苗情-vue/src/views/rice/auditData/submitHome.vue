<template>
      <div class="totalRange">
        <el-tabs v-model="activeName">
          <el-tab-pane label="单季稻" name="1">
            <div class="tableRange">
              <el-table ref="table" :data="tableData" highlight-current-row border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" row-key="id" stripe>
                    <el-table-column align="center" width="80%" label="数据未上报表">
                      <el-table-column align="center" fixed type="index" width="80%"  label="序号" >
                      </el-table-column>
                      <el-table-column align="center" prop="table_name" label="表名" >
                      </el-table-column>
                      <el-table-column align="center" prop="number" label="未上报县个数" >
                      </el-table-column>
                      <el-table-column align="center" label="上报状态">
                        <template slot-scope="status">
                          <span v-if="status.row.number!=0">未上报</span>
                        </template>
                      </el-table-column>
                      <el-table-column align="center" width="200px" label="操作">
                          <template slot-scope="scope">
                            <el-button type="text" @click="toogleExpand(scope.row)">查看详情</el-button>
                          </template>
                      </el-table-column>
                      <el-table-column type="expand" width="1">
                         <template slot-scope="props">
                            <el-form label-position="left"  class="demo-table-expand">
                              <el-form-item v-if="props.row.number != 0" label="需上报县：">
                                <span style="width:100%">
                                  <!-- <span>{{ props.row.county_name}}</span> -->
                                  <span v-for="item in props.row.county_name">{{item}}、</span>
                                  <!-- <span v-for="(item,index) in props.row.county_name">
                                    <span v-if="index != props.row.county_name.length">{{item}}、</span>
                                    <span v-else>{{item}}</span>
                                    </span>-->
                                </span> 
                              </el-form-item>
                            </el-form>
                          </template>
                      </el-table-column>
                    </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
          <el-tab-pane label="双季稻" name="2">
            <div class="tableRange">
              <el-table ref="table2" :data="tableData2" highlight-current-row border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" row-key="id" stripe>
                    <el-table-column align="center" width="80%" label="数据未上报表">
                      <el-table-column align="center" fixed type="index" width="80%"  label="序号" >
                      </el-table-column>
                      <el-table-column align="center" prop="table_name" label="表名" >
                      </el-table-column>
                      <el-table-column align="center" prop="number" label="未上报县个数" >
                      </el-table-column>
                      <el-table-column align="center" label="上报状态">
                        <template slot-scope="status">
                          <span v-if="status.row.number!=0">未上报</span>
                        </template>
                      </el-table-column>
                      <el-table-column align="center" width="200px" label="操作">
                          <template slot-scope="scope">
                            <el-button type="text" @click="toogleExpand2(scope.row)">查看详情</el-button>
                          </template>
                      </el-table-column>
                      <el-table-column type="expand" width="1">
                         <template slot-scope="props">
                            <el-form label-position="left"  class="demo-table-expand">
                              <el-form-item v-if="props.row.number != 0" label="需上报县：">
                                <span style="width:100%">
                                  <!-- <span>{{ props.row.county_name}}</span> -->
                                  <span v-for="item in props.row.county_name">{{item}}、</span>
                                  <!-- <span v-for="(item,index) in props.row.county_name">
                                    <span v-if="index != props.row.county_name.length">{{item}}、</span>
                                    <span v-else>{{item}}</span>
                                    </span>-->
                                </span> 
                              </el-form-item>
                            </el-form>
                          </template>
                      </el-table-column>
                    </el-table-column>
              </el-table>
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
export default {
  data() { 
    return { 
      activeName:'1',
      tableData:[],
      //tableData2:[],
      //系统时间
      systemTime:'',
      tableData2:[],
      
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.getTableData();
      this.getTableData2();
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
    //表头
    headerStyle({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'19px'};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'16px'}
        };
    },
    //获取一季稻数据
    getTableData(){
      const _this=this;
      axios({
        url:'/getNoReportData',
        menthod:'get',
      }).then(res=>{
          //console.log(res.data);
          _this.tableData=res.data;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取双季稻数据
    getTableData2(){
      const _this=this;
      axios({
        url:'/getNoReportData2',
        menthod:'get',
      }).then(res=>{
          //console.log(res.data);
          _this.tableData2=res.data;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //一季稻点击行展开，查看详情
    toogleExpand(row) {
      //console.log(row);
      let $table = this.$refs.table;
      this.tableData.map((item) => {
          if (row.id != item.id) {
          $table.toggleRowExpansion(item, false)
        }
      })
      $table.toggleRowExpansion(row)
    },
    //双季稻点击行展开，查看详情
    toogleExpand2(row) {
      let $table = this.$refs.table2;
      this.tableData2.map((item) => {
          if (row.id != item.id) {
          $table.toggleRowExpansion(item, false)
        }
      })
      $table.toggleRowExpansion(row)
    },
    
    //返回
    returnPage(){
        this.$router.push("/riceHome");
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
    width:80%;
    margin: 0 auto;
  }
  .el-button--danger{
    border: 1px solid #000000;
    padding:2px;
    width: 70px;
    height: 30px;
  }
  .el-tabs_item{
    font-size: 18px !important;
  
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #69cc42;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 99%;
  }
</style>



