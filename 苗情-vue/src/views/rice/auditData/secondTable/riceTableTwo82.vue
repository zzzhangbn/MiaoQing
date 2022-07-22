<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="tableStyle">
            <el-row>
              <div style="text-align:center;font-size:16px;color:black;margin-bottom:10px">
                <span>{{tableLabel}}</span>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" prop="field_1"  label="县（市、区）" />
                    <el-table-column align="center" prop="field_2"  label="地区类型" />
                    <el-table-column align="center" prop="field_3"  label="品种类型" />
                    <el-table-column align="center" prop="field_4"  label="监测田块" />
                    <el-table-column align="center" prop="field_5"  label="代表面积（万亩）" />
                    <el-table-column align="center" prop="field_6"  label="栽植方式" />
                    <el-table-column align="center" prop="field_7"  label="水稻亩产值（元）" />
                    <el-table-column align="center" prop="field_8"  label="其中主产品产值（公斤/元）" />
                    <el-table-column align="center" prop="field_9" label="副产品产值（公斤/元）" />
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:45px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="劳动用工">
                      <el-table-column align="center" label="育秧" >
                        <el-table-column align="center" prop="field_10_1_1"  label="数量（个/亩）" />
                        <el-table-column align="center" prop="field_10_1_2"  label="价格（元/亩）" />
                      </el-table-column>
                      <el-table-column align="center" label="耕作" >
                        <el-table-column align="center" prop="field_10_2_1"  label="数量（个/亩）" />
                        <el-table-column align="center" prop="field_10_2_2"  label="价格（元/亩）" />
                      </el-table-column>
                      <el-table-column align="center" label="移栽" >
                        <el-table-column align="center" prop="field_10_3_1"  label="数量（个/亩）" />
                        <el-table-column align="center" prop="field_10_3_2"  label="价格（元/亩）" />
                      </el-table-column>
                      <el-table-column align="center" label="直播" >
                        <el-table-column align="center" prop="field_10_4_1"  label="数量（个/亩）" />
                        <el-table-column align="center" prop="field_10_4_2"  label="价格（元/亩）" />
                      </el-table-column>
                      <el-table-column align="center" label="大田管理" >
                        <el-table-column align="center" prop="field_10_5_1"  label="数量（个/亩）" />
                        <el-table-column align="center" prop="field_10_5_2"  label="价格（元/亩）" />
                      </el-table-column>
                      <el-table-column align="center" label="小计" >
                        <el-table-column align="center" prop="field_10_6_1"  label="数量（个/亩）" />
                        <el-table-column align="center" prop="field_10_6_2"  label="价格（元/亩）" />
                      </el-table-column>
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:45px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="物质费用">
                      <el-table-column align="center" prop="field_11_1"  label="种子（元/亩）" />
                      <el-table-column align="center" label="化肥">
                        <el-table-column align="center" prop="field_11_2_1"  label="种类" />
                        <el-table-column align="center" prop="field_11_2_2"  label="施用量（kg/亩）" />
                        <el-table-column align="center" prop="field_11_2_3"  label="价格（元/kg）" />
                        <el-table-column align="center" prop="field_11_2_4"  label="小计（元/亩）" />
                      </el-table-column>
                        <el-table-column align="center" prop="field_11_3"  label="有机肥（元/亩）" />
                        <el-table-column align="center" prop="field_11_4"  label="农药（元/亩）" />
                        <el-table-column align="center" prop="field_11_5"  label="灌溉（元/亩）" />
                        <el-table-column align="center" prop="field_11_6"  label="机械耕作（元/亩）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:45px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="物质费用">
                        <el-table-column align="center" prop="field_11_7"  label="机械插秧/直播（元/亩）" />
                        <el-table-column align="center" prop="field_11_8"  label="机械收获（元/亩）" />
                        <el-table-column align="center" prop="field_11_9"  label="其他（元/亩）" />
                        <el-table-column align="center" prop="field_11_10"  label="小计（元/亩）" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_12"  label="地租（元/亩）" />
                    <el-table-column align="center" prop="field_13"  label="其他费用（元/亩）" />
                    <el-table-column align="center" prop="field_14"  label="亩总成本（元）" />
                    <el-table-column align="center" prop="field_15"  label="亩收益（元）" />
                </el-table>
              </div>
            </el-row>
          </div>
        </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
export default {
  name: 'riceTableTwo82',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {           
      activeName:'1',      
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产成本效益统计表（晚稻）',
      tableName:'crop_01_02_table_08_02',
      //退回原因对话框
      dialogVisible:false,
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
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        return{background:'#CDBE70',color:'green',fontSize:'8px',height:'0',padding:'1px',}
        
    },
    //获取表数据
    getTableData(){
       const _this = this;
       var auditId=this.parentMessage;
       axios({
          url: '/getAuditRiceTableTwo82',
          method:'get',
       }).then(res=>{
         var temp=res.data;
         var tempData=[];
         var temp1='';
         for(var i=0;i<temp.length;i++){
           if(temp[i].id == auditId){
             temp1 = temp[i];
           }
         }
         tempData[0] = temp1;
          _this.tableData=tempData;
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



