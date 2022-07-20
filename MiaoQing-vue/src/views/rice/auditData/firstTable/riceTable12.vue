<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="tableStyle">
            <el-row>
              <div style="text-align:center;font-size:16px;color:black;margin-bottom:10px">
                <span>{{tableLabel}}</span>
              </div>
            </el-row>
            <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height:'30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <el-table-column align="center" prop="county" label="县（市、区）" />
                <el-table-column align="center" prop="county_area" label="全县总面积（万亩）" />
                <el-table-column align="center" prop="pin_zhong_name" label="品种名" />
                <el-table-column align="center" prop="area_data" label="面积（万亩）" />
                <el-table-column align="center" prop="area_percent" label="面积占比" />
              </el-table>
          </div>
          
        </div>
     </div>
  
</template>


<script>
import axios from 'axios'
export default {
  name: 'riceTable12',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {    
      // 表格组件 列表数据对象
      //表中分页数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产主推品种调查表',
      tableName:'crop_01_01_table_01_02',
      //接收父组件传过来参数
      parentMessage:this.parent_msg,
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
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
      if(rowIndex === 2){ 
           return {display:"none"};
        }else{
            // return {background:'#eee',color:'#606266'};
            return{background:'#CDBE70',color:'green',fontSize:'8px',height:'0',padding:'1px',}
        };
    },
    //获取表数据
    getTableData(){
       const _this = this;
       //console.log(this.parentMessage);
       var auditCounty=this.parentMessage;
       axios({
          url: '/getAssist12',
          method:'post',
          params:{auditCounty:auditCounty}
       }).then(res=>{
         //console.log(res.data);
         _this.tableData=res.data;
        //  var temp=res.data;
        //  var tempData=[];
        //  var temp1='';
        //  for(var i=0;i<temp.length;i++){
        //    if(temp[i].id == auditId){
        //      temp1 = temp[i];
        //    }
        //  }
        //  tempData[0] = temp1;
        //  _this.tableData=tempData;
          //console.log(_this.tableData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },

  }
};
</script>

<style scoped>
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



