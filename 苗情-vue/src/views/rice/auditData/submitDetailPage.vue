<template>
      <div class="totalRange">
        <div class="tableRange">
          <el-row>
            <div style="text-align:left;font-size:20px;font-weight:bolder;color:black;margin-bottom:15px">
              <span>{{currentCountyName}}:</span>
            </div>
          </el-row>
          <div class="left_table">
            <el-table :data="tableData.slice(0,15)" highlight-current-row border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'2px',fontSize:'10px'}" row-key="id" stripe>
              <el-table-column align="center" width="100%" label="监测县数据已上报表">
                <el-table-column align="center" prop="sumbitTable">
                </el-table-column>
                <el-table-column align="center" prop="sumbitTable2">
                </el-table-column>
              </el-table-column>
            </el-table>
          </div>
          <div class="right_table">
            <el-table :data="tableData2.slice(0,15)" highlight-current-row border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'2px',fontSize:'10px'}" row-key="id" stripe>
              <el-table-column align="center" width="100%" label="监测县数据未上报表">
                <el-table-column align="center" prop="nosumbitTable">
                </el-table-column>
                <el-table-column align="center" prop="nosumbitTable2">
                </el-table-column>
              </el-table-column>
            </el-table>
          </div>
        </div>
        <div class="button">
          <el-button type="success" @click="returnPage">返  回</el-button>
        </div>
      </div>
  
</template>


<script>
import axios from 'axios'
export default {
  data() { 
    return { 
      tableData:[{"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',},
      {"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',},
      {"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',},
      {"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',},
      {"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',},
      {"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',},
      {"sumbitTable":'年度监测县(市、区)水稻播栽进度调查表',"sumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',}],
      tableData2:[{"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',},
      {"nosumbitTable2":'年度监测县(市、区)水稻播栽进度调查表',"nosumbitTable":'年度监测县(市、区)水稻播栽进度调查表',}],
      currentCountyName:'',
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.currentCountyName = this.$route.query.CountyName;
      console.log(this.currentCountyName)
      this.getTableData();
    },
    //表头
    headerStyle({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'18px',padding:'6px'};
        }
        if(rowIndex === 1){ 
           return {display:"none"};
        };
        // else{
        //     return{background:'#CDBE70',color:'green',fontSize:'16px',padding:'6px'}
        // };
    },
    //获取一季稻数据
    getTableData(){
      const _this=this;
      axios({
        url:'/getSubmitInfo',
        menthod:'get',
        params:{countyName:this.currentCountyName}
      }).then(res=>{
        var data1 = res.data[0];
        var data2 = res.data[1];
        console.log(data1);
        console.log(data2);
        _this.tableData = data1;
        _this.tableData2 = data2;
          // for(var i=0;i<18;i++){
          //   _this.tableData.push(res.data[i]);
          // }
          // for(var i=18;i<36;i++){
          //   _this.tableData2.push(res.data[i])
          // }
          //_this.tableData=res.data;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //返回
    returnPage(){
      this.$router.push("/submitHome");
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
  .tableRange .left_table{
    margin-right: 0.02%;
    width: 48%;
    float: left;
  }
  .tableRange .right_table{
    margin-left: 0.02%;
    width: 48%;
    float: right;
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
</style>



