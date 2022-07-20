<template>
      <div class="totalRange">
        <div class="tableRange">
          <div class="left_table">
            <el-table :data="tableData" highlight-current-row border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'2px',fontSize:'10px'}" row-key="id" stripe>
              <el-table-column align="center" width="100%" label="监测县数据上报与未上报汇总表">
                <el-table-column align="center" prop="county_name" label="监测县" >
                </el-table-column>
                <el-table-column align="center" prop="sumbitNum" label="已上报表个数" >
                </el-table-column>
                <el-table-column align="center" prop="nosumbitNum" label="未上报表个数" >
                </el-table-column>
                <el-table-column align="center" width="200px" label="操作">
                    <template slot-scope="scope">
                      <el-button type="primary" size="mini" @click="showPage(scope.$index,scope.row)" plain>查看详情</el-button>
                    </template>
                </el-table-column>
              </el-table-column>
            </el-table>
          </div>
          <div class="right_table">
            <el-table :data="tableData2" highlight-current-row border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'2px',fontSize:'10px'}" row-key="id" stripe>
              <el-table-column align="center" width="100%" label="监测县数据上报与未上报汇总表">
                <el-table-column align="center" prop="county_name" label="监测县" >
                </el-table-column>
                <el-table-column align="center" prop="sumbitNum" label="已上报表个数" >
                </el-table-column>
                <el-table-column align="center" prop="nosumbitNum" label="未上报表个数" >
                </el-table-column>
                <el-table-column align="center"  fixed="right" width="200px" label="操作">
                  <template slot-scope="scope">
                    <el-button type="primary" size="mini" @click="showPage(scope.$index,scope.row)" plain>查看详情</el-button>
                  </template>
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
      tableData:[],
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
    },
    //表头
    headerStyle({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'18px',padding:'6px'};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'16px',padding:'6px'}
        };
    },
    //获取一季稻数据
    getTableData(){
      const _this=this;
      axios({
        url:'/getSubmitData',
        menthod:'get',
      }).then(res=>{
          for(var i=0;i<18;i++){
            _this.tableData.push(res.data[i]);
          }
          for(var i=18;i<36;i++){
            _this.tableData2.push(res.data[i])
          }
          //_this.tableData=res.data;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //跳转到具体页面
    showPage(index,row){
      console.log(row);
      this.$router.push({path:'/submitDetailPage',query:{CountyName:row.county_name}});

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



