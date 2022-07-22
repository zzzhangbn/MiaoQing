<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">
            <el-row>            
              <el-col :span="6">
                <el-button  type="info" plain @click="getDataAnalyse">数据分析</el-button>
                <el-button  type="info" plain @click="printTable">打印</el-button>
              </el-col>
            </el-row>
          </div>
          <div class="tableStyle">
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" style="width: 100%" :row-style="{height: '30px'}" :cell-style="{padding:'0'}" row-key="id" stripe>
              <el-table-column :label="tableLabel" align="center">
                <el-table-column align="center" fixed type="index"  label="序号" />
                <el-table-column align="center" prop="field_1" label="县（市、区）" />
                <el-table-column align="center" prop="field_2" width="100%" label="全县总面积（万亩）" />
                <el-table-column align="center" prop="field_3" label="地区类型" />
                <el-table-column align="center" prop="field_4" label="稻作类型" />
                <el-table-column align="center" label="栽植方式（万亩）">
                  <el-table-column align="center" label="人工移栽">
                    <el-table-column align="center" prop="field_5_1_1" label="" />
                    <el-table-column align="center" prop="field_5_1_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="机械插秧">
                    <el-table-column align="center" prop="field_5_2_1" label="" />
                    <el-table-column align="center" prop="field_5_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="盘育抛栽">
                    <el-table-column align="center" prop="field_5_3_1" label="" />
                    <el-table-column align="center" prop="field_5_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="无盘旱育抛栽">
                    <el-table-column align="center" prop="field_5_4_1" label="" />
                    <el-table-column align="center" prop="field_5_4_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="撒直播（旱）">
                    <el-table-column align="center" prop="field_5_5_1" label="" />
                    <el-table-column align="center" prop="field_5_5_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="撒直播（水）">
                    <el-table-column align="center" prop="field_5_6_1" label="" />
                    <el-table-column align="center" prop="field_5_6_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="机条播（旱）">
                    <el-table-column align="center" prop="field_5_7_1" label="" />
                    <el-table-column align="center" prop="field_5_7_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="机条播（水）">
                    <el-table-column align="center" prop="field_5_8_1" label="" />
                    <el-table-column align="center" prop="field_5_8_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="小计">
                    <el-table-column align="center" prop="field_5_9_1" width="100%" label="" />
                    <el-table-column align="center" prop="field_5_9_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="前茬口类型（万亩）">
                  <el-table-column align="center" label="水稻">
                    <el-table-column align="center" prop="field_6_1_1" label="" />
                    <el-table-column align="center" prop="field_6_1_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="小麦">
                    <el-table-column align="center" prop="field_6_2_1" label="" />
                    <el-table-column align="center" prop="field_6_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="油菜">
                    <el-table-column align="center" prop="field_6_3_1" label="" />
                    <el-table-column align="center" prop="field_6_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="空闲田">
                    <el-table-column align="center" prop="field_6_4_1" label="" />
                    <el-table-column align="center" prop="field_6_4_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="绿肥">
                    <el-table-column align="center" prop="field_6_5_1" label="" />
                    <el-table-column align="center" prop="field_6_5_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="蔬菜等经作">
                    <el-table-column align="center" prop="field_6_6_1" label="" />
                    <el-table-column align="center" prop="field_6_6_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="其他">
                    <el-table-column align="center" prop="field_6_7_1" label="" />
                    <el-table-column align="center" prop="field_6_7_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="小计">
                    <el-table-column align="center" prop="field_6_8_1" width="100%" label="" />
                    <el-table-column align="center" prop="field_6_8_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" width="100" prop="in_use" :formatter="auditState" label="审核状态">
                </el-table-column>
                <el-table-column align="center" width="150px" label="操作">
                  <template slot-scope="scope">                 
                      <!-- <el-button size="mini" @click="returnData(scope.$index,scope.row)"  v-if="scope.row.in_use === '1'">退还</el-button>                     -->
                       <span width='50px'><el-button type="danger" plain size="mini" @click="reasonDialog(scope.$index,scope.row)"  v-if="scope.row.in_use === '1'">退还</el-button> </span>
                       <span width='50px' margin-left='20px'> <el-button type="info" plain size="mini" @click="confirmRecord(scope.row)"  v-if="scope.row.in_use === '1'">通过</el-button> </span>
                     
                  </template>
                </el-table-column>
              </el-table-column>
            </el-table>
          </div>
        </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import printJs from 'print-js'
export default {
  inject: ['reload'],
  data() {
    return {        
      activeName:'1',     
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产栽插概况调查表',
      tableName:'crop_01_01_table_01_01',
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
      //打印
      list:[],
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      //this.getHeaderList();
      this.getTableData();
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
    }, 
    //获取时间
    //获取系统时间
     
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 3){ 
           return {display:"none"};
        }else{
            // return {background:'#eee',color:'#606266'};
            return{height:'0'}
        };
           
    },
    //获取表数据
    getTableData(){
       const _this = this;
       axios({
          url: '/getRiceTable11',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
          _this.tableData=res.data;
          var totalTableData = res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = Number(totalTableData[i].field_2) + Number(num1);

              if(totalTableData[i].field_5_1_1 === ''){
                totalTableData[i].field_5_1_1 = 0;
              }
              num2 = Number(totalTableData[i].field_5_1_1) + Number(num2);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num3 = Number(totalTableData[i].field_5_2_1) + Number(num3);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num4 = Number(totalTableData[i].field_5_3_1) + Number(num4);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num5 = Number(totalTableData[i].field_5_4_1) + Number(num5);

              if(totalTableData[i].field_5_5_1 === ''){
                totalTableData[i].field_5_5_1 = 0;
              }
              num6 = Number(totalTableData[i].field_5_5_1) + Number(num6);

              if(totalTableData[i].field_5_6_1 === ''){
                totalTableData[i].field_5_6_1 = 0;
              }
              num7 = Number(totalTableData[i].field_5_6_1) + Number(num7);

              if(totalTableData[i].field_5_7_1 === ''){
                totalTableData[i].field_5_7_1 = 0;
              }
              num8 = Number(totalTableData[i].field_5_7_1) + Number(num8);

              if(totalTableData[i].field_5_8_1 === ''){
                totalTableData[i].field_5_8_1 = 0;
              }
              num9 = Number(totalTableData[i].field_5_8_1) + Number(num9);

              if(totalTableData[i].field_6_1_1 === ''){
                totalTableData[i].field_6_1_1 = 0;
              }
              num10 = Number(totalTableData[i].field_6_1_1) + Number(num10);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num11 = Number(totalTableData[i].field_6_2_1) + Number(num11);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num12 = Number(totalTableData[i].field_6_3_1) + Number(num12);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num13 = Number(totalTableData[i].field_6_4_1) + Number(num13);

              if(totalTableData[i].field_6_5_1 === ''){
                totalTableData[i].field_6_5_1 = 0;
              }
              num14 = Number(totalTableData[i].field_6_5_1) + Number(num14);

              if(totalTableData[i].field_6_6_1 === ''){
                totalTableData[i].field_6_6_1 = 0;
              }
              num15 = Number(totalTableData[i].field_6_6_1) + Number(num15);

              if(totalTableData[i].field_6_7_1 === ''){
                totalTableData[i].field_6_7_1 = 0;
              }
              num16 = Number(totalTableData[i].field_6_7_1) + Number(num16);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;
            //求占比
            var totalNum1=0;//小计
            var totalNum2=0;//小计
            for(var i=0; i<8; i++){
              totalNum1 = total[i] + totalNum1;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            for(var i=8; i<total.length; i++){
              totalNum2 = total[i] + totalNum2;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //console.log(total.length,sums.length)
            //小计
            if(totalNum1 === num1){
              total[15] = '核对正确！';
              sums[15] = '100%';
            }else{
              total[15] = '错误！';
              sums[15] = ((totalNum1/num1)*100).toFixed(2)+'%';
            };
            if(totalNum2 === num1){
              total[16] = '核对正确！';
              sums[16] = '100%';
            }else{
              total[16] = '错误！';
              sums[16] = ((totalNum2/num1)*100).toFixed(2)+'%';
            };
            //辅助数组
            totalTableData.push({"field_1":'总计',"field_2":num1,"field_3":'--',"field_4":'--',"field_5_1_1":total[0],"field_5_1_2":sums[0],
                      "field_5_2_1":total[1],"field_5_2_2":sums[1],"field_5_3_1":total[2],"field_5_3_2":sums[2],
                      "field_5_4_1":total[3],"field_5_4_2":sums[3],"field_5_5_1":total[4],"field_5_5_2":sums[4],
                      "field_5_6_1":total[5],"field_5_6_2":sums[5],"field_5_7_1":total[6],"field_5_7_2":sums[6],
                      "field_5_8_1":total[7],"field_5_8_2":sums[7],"field_5_9_1":total[15],"field_5_9_2":sums[15],"field_6_1_1":total[8],"field_6_1_2":sums[8],
                      "field_6_2_1":total[9],"field_6_2_2":sums[9],"field_6_3_1":total[10],"field_6_3_2":sums[10],
                      "field_6_4_1":total[11],"field_6_4_2":sums[11],"field_6_5_1":total[12],"field_6_5_2":sums[12],
                      "field_6_6_1":total[13],"field_6_6_2":sums[13],"field_6_7_1":total[14],"field_6_7_2":sums[14],
                      "field_6_8_1":total[16],"field_6_8_2":sums[16],"in_use":'--'})
            _this.dataNum = totalTableData.length-1;
          }else{
            _this.dataNum = 0;
          }
            _this.tableData = totalTableData;
            //console.log(_this.tableData);
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //打印
    async printTable(){
      this.list = this.tableData || [];
      let data=[];
      for(let i=0; i<this.list.length;i++){
        data.push({
          "field1":this.list[i].field_1,
          "field2":this.list[i].field_2,
          "field3":this.list[i].field_3,
        })
      printJs({
        printable:data,
        properties:[
          {
            field:"field1",
            displayName:"第一个字段",
            columnSize:1
          },
          {
            field:"field2",
            displayName:"第二个字段",
            columnSize:1
          },
          {
            field:"field3",
            displayName:"第三个字段",
            columnSize:1
          },
        ],
        type:'json',
        header:'标题',
        gridStyle:'border: 2px solid #3971A5;',
        gridHeaderStyle:'color:red; border: 2px solid #3971A5;'
      })
    }

    }
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
    width:1525px;
    height: auto;
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
    height: 400px;
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
</style>



