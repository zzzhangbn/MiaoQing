<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">
        <el-tab-pane label="品系 1-6" name="1">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                  <router-link to='/tablemain'>
                    <el-button type="info" plain>返回</el-button>
                  </router-link>
                </div>
              </el-row>
            </div>
            <div class="tableStyle">
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height:'30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <el-table-column width="100%" :label="tableLabel" align="center">
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1" label="县（市、区）" />
                  <el-table-column align="center" prop="field_2" label="全县总面积（万亩）" />
                  <el-table-column align="center" label="主推品种">
                    <el-table-column align="center" prop="field_3_1" label="品种1" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_2_1" label="" />
                      <el-table-column align="center" prop="field_3_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_3" label="品种2" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_4_1" label="" />
                      <el-table-column align="center" prop="field_3_4_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_5" label="品种3" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_6_1" label="" />
                      <el-table-column align="center" prop="field_3_6_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_7" label="品种4" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_8_1" label="" />
                      <el-table-column align="center" prop="field_3_8_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_9" label="品种5" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_10_1" label="" />
                      <el-table-column align="center" prop="field_3_10_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_11" label="品种6" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_12_1" label="" />
                      <el-table-column align="center" prop="field_3_12_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table-column>
              </el-table>
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
        </el-tab-pane>
        <el-tab-pane label="品系 7-11" name="2">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button>
                  <router-link to='/tablemain'>
                    <el-button type="info" plain>返回</el-button>
                  </router-link>
                </div>
              </el-row>
            </div>
            <div class="tableStyle">
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" style="width: 100%" :row-style="{height:'30px'}" :cell-style="{padding:'0'}" row-key="id" stripe>
                <el-table-column :label="tableLabel" align="center">
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1" label="县（市、区）" />
                  <el-table-column align="center" prop="field_2" label="全县总面积（万亩）" />
                  <el-table-column align="center" label="主推品种">
                    <el-table-column align="center" prop="field_3_13" label="品种7" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_14_1" label="" />
                      <el-table-column align="center" prop="field_3_14_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_15" label="品种8" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_16_1" label="" />
                      <el-table-column align="center" prop="field_3_16_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_17" label="品种9" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_18_1" label="" />
                      <el-table-column align="center" prop="field_3_18_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_19" label="品种10" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_20_1" label="" />
                      <el-table-column align="center" prop="field_3_20_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_21" label="品种11" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_22_1" label="" />
                      <el-table-column align="center" prop="field_3_22_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table-column>
              </el-table>
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
        </el-tab-pane>
        <el-tab-pane label="品系 12-16" name="3">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button>
                  <router-link to='/tablemain'>
                    <el-button type="info" plain>返回</el-button>
                  </router-link>
                </div>
              </el-row>
            </div>
            <div class="tableStyle">
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" style="width: 100%" :row-style="{height:'30px'}" :cell-style="{padding:'0'}" row-key="id" stripe>
                <el-table-column :label="tableLabel" align="center">
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1" label="县（市、区）" />
                  <el-table-column align="center" prop="field_2"  label="全县总面积（万亩）" />
                  <el-table-column align="center" label="主推品种">
                    <el-table-column align="center" prop="field_3_23" label="品种12" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_24_1" label="" />
                      <el-table-column align="center" prop="field_3_24_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_25" label="品种13" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_26_1" label="" />
                      <el-table-column align="center" prop="field_3_26_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_27" label="品种14" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_28_1" label="" />
                      <el-table-column align="center" prop="field_3_28_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_29" label="品种15" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_30_1" label="" />
                      <el-table-column align="center" prop="field_3_30_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_31" label="品种16" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_32_1" label="" />
                      <el-table-column align="center" prop="field_3_32_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table-column>
              </el-table>
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
        </el-tab-pane>
        <el-tab-pane label="品系 17-20" name="4">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button>
                  <router-link to='/tablemain'>
                    <el-button type="info" plain>返回</el-button>
                  </router-link>
                </div>
              </el-row>
            </div>
            <div class="tableStyle">
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" style="width: 100%" :row-style="{height:'30px'}" :cell-style="{padding:'0'}" row-key="id" stripe>
                <el-table-column :label="tableLabel" align="center">
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1" label="县（市、区）" />
                  <el-table-column align="center" prop="field_2"  label="全县总面积（万亩）" />
                  <el-table-column align="center" label="主推品种">
                    <el-table-column align="center" prop="field_3_33" label="品种17" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_34_1" label="" />
                      <el-table-column align="center" prop="field_3_34_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_35" label="品种18" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_36_1" label="" />
                      <el-table-column align="center" prop="field_3_36_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_37" label="品种19" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_38_1" label="" />
                      <el-table-column align="center" prop="field_3_38_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_3_39" label="品种（品系）20" />
                    <el-table-column align="center" label="面积（万亩）">
                      <el-table-column align="center" prop="field_3_40_1" label="" />
                      <el-table-column align="center" prop="field_3_40_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="小计">
                      <el-table-column align="center" prop="field_3_41_1" label="" />
                      <el-table-column align="center" prop="field_3_41_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table-column>
              </el-table>
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
        </el-tab-pane>
      </el-tabs>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
export default {
  inject: ['reload'],
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
      dataNum:0,//数据个数
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
      this.getTableData(people);
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
    getTableData(people){
      var column_id = '';
      const _this = this;
      axios({
        url:'/getUserAllData',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          flag:1,
        }
      }).then(res=>{
          // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;var num21=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2*1);

              if(totalTableData[i].field_3_2_1 === ''){
                totalTableData[i].field_3_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_3_2_1*1);

              if(totalTableData[i].field_3_4_1 === ''){
                totalTableData[i].field_3_4_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_3_4_1*1);

              if(totalTableData[i].field_3_6_1 === ''){
                totalTableData[i].field_3_6_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_3_6_1*1);

              if(totalTableData[i].field_3_8_1 === ''){
                totalTableData[i].field_3_8_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_3_8_1*1);

              if(totalTableData[i].field_3_10_1 === ''){
                totalTableData[i].field_3_10_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_3_10_1*1);

              if(totalTableData[i].field_3_12_1 === ''){
                totalTableData[i].field_3_12_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_3_12_1*1);

              if(totalTableData[i].field_3_14_1 === ''){
                totalTableData[i].field_3_14_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_3_14_1*1);

              if(totalTableData[i].field_3_16_1 === ''){
                totalTableData[i].field_3_16_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_3_16_1*1);

              if(totalTableData[i].field_3_18_1 === ''){
                totalTableData[i].field_3_18_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_3_18_1*1);

              if(totalTableData[i].field_3_20_1 === ''){
                totalTableData[i].field_3_20_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_3_20_1*1);

              if(totalTableData[i].field_3_22_1 === ''){
                totalTableData[i].field_3_22_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_3_22_1*1);

              if(totalTableData[i].field_3_24_1 === ''){
                totalTableData[i].field_3_24_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_3_24_1*1);

              if(totalTableData[i].field_3_26_1 === ''){
                totalTableData[i].field_3_26_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_3_26_1*1);

              if(totalTableData[i].field_3_28_1 === ''){
                totalTableData[i].field_3_28_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_3_28_1*1);

              if(totalTableData[i].field_3_30_1 === ''){
                totalTableData[i].field_3_30_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_3_30_1*1);

              if(totalTableData[i].field_3_32_1 === ''){
                totalTableData[i].field_3_32_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_3_32_1*1);

              if(totalTableData[i].field_3_34_1 === ''){
                totalTableData[i].field_3_34_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_3_34_1*1);

              if(totalTableData[i].field_3_36_1 === ''){
                totalTableData[i].field_3_36_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_3_36_1*1);

              if(totalTableData[i].field_3_38_1 === ''){
                totalTableData[i].field_3_38_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_3_38_1*1);
              
              if(totalTableData[i].field_3_40_1 === ''){
                totalTableData[i].field_3_40_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_3_40_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;total[19]=num21;
            //求占比
            var totalNum=0;//小计
            for(var i=0; i<total.length; i++){
              totalNum = total[i] + totalNum;
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
            //小计
            if(totalNum === num1){
              total[total.length] = '核对正确！';
              sums[sums.length] = '100%';
            }else{
              total[total.length] = '错误！';
              sums[sums.length] = ((totalNum/num1)*100).toFixed(2)+'%';
            };
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":num1,"field_3_1":'--',"field_3_2_1":total[0],"field_3_2_2":sums[0],
                      "field_3_3":'--',"field_3_4_1":total[1],"field_3_4_2":sums[1],"field_3_5":'--',"field_3_6_1":total[2],"field_3_6_2":sums[2],
                      "field_3_7":'--',"field_3_8_1":total[3],"field_3_8_2":sums[3],"field_3_9":'--',"field_3_10_1":total[4],"field_3_10_2":sums[4],
                      "field_3_11":'--',"field_3_12_1":total[5],"field_3_12_2":sums[5],"field_3_13":'--',"field_3_14_1":total[6],"field_3_14_2":sums[6],
                      "field_3_15":'--',"field_3_16_1":total[7],"field_3_16_2":sums[7],"field_3_17":'--',"field_3_18_1":total[8],"field_3_18_2":sums[8],
                      "field_3_19":'--',"field_3_20_1":total[9],"field_3_20_2":sums[9],"field_3_21":'--',"field_3_22_1":total[10],"field_3_22_2":sums[10],
                      "field_3_23":'--',"field_3_24_1":total[11],"field_3_24_2":sums[11],"field_3_25":'--',"field_3_26_1":total[12],"field_3_26_2":sums[12],
                      "field_3_27":'--',"field_3_28_1":total[13],"field_3_28_2":sums[13],"field_3_29":'--',"field_3_30_1":total[14],"field_3_30_2":sums[14],
                      "field_3_31":'--',"field_3_32_1":total[15],"field_3_32_2":sums[15],"field_3_33":'--',"field_3_34_1":total[16],"field_3_34_2":sums[16],
                      "field_3_35":'--',"field_3_36_1":total[17],"field_3_36_2":sums[17],"field_3_37":'--',"field_3_38_1":total[18],"field_3_38_2":sums[18], 
                      "field_3_39":'--',"field_3_40_1":total[19],"field_3_40_2":sums[19],"field_3_41_1":totalNum,"field_3_41_2":"100.00%","in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          } 
            _this.tableData = totalTableData;
            //console.log(_this.tableData);
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取下拉框内容
    getCountySelection(){
      const _this = this;
      axios({
          url: '/getCounty1',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          //地区数据
          var areaData = [];
          //稻作数据
          var riceData = [];
          ////console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              // if(optionData[i].caption === "地区类型"){
              //   areaData.push({"label":optionData[i].list,"value":optionData[i].list});
              // };
              // if(optionData[i].caption === "稻作类型"){
              //   riceData.push({"label":optionData[i].list,"value":optionData[i].list});
              // };
          };
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;

       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //审核状态
    auditState(row,column){
      if(row.in_use === '3'){
        return "通过";
      }else if(row.in_use === '1'){
        return "未审核";
      }else if(row.in_use === '2'){
        return "已退回";
      }else if(row.in_use === '--'){
        return "--";
      }
    },
    //退还对话框
    reasonDialog(index,row){
      this.dialogVisible=true;
      this.rowRecord = row;
    },
    // 退还记录确定
    returnData(){
      const _this=this;
      this.dialogVisible = false;
      //console.log(this.rowRecord);
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.rowRecord.id;
      var recordPeople = this.rowRecord.people;
      var recordReason = this.reasonForm.reason;
      var returnTableName = this.tableName;
      //console.log(recordTime,recordId,recordPeople,recordReason);
      
      axios({
        url:'/returnRecord',
        menthod:'post',
        params:{returnTableName: returnTableName,
                recordId:recordId,
                recordReason:recordReason,
                recordTime:recordTime,
                recordPeople:recordPeople
                }
      }).then(res=>{
          //console.log(res.data);
           _this.getTableData();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
       this.reload();
       this.reasonForm.reason='';
    },
    //数据记录审核通过
    confirmRecord(row){
      const _this=this;
      var returnTableName = this.tableName;
      var recordId = row.id;
      axios({
        url:'/confirmPass',
        method:'post',
        params:{returnTableName: returnTableName,
                recordId:recordId}
      }).then(res=>{
        //console.log(res.data);
        _this.getTableData();
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
     
      this.reload();
    },
    //数据分析图跳转
    getDataAnalyse(){
      this.$router.replace('/riceAnalysis');
    },
    //重置
    reSet(){
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      var years = this.$route.query.year;
      const _this = this;
      axios({
        url:'/getSeekInfo12',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                 year:years,
        }
      }).then(res=>{
          var totalTableData = res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;var num21=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2*1);

              if(totalTableData[i].field_3_2_1 === ''){
                totalTableData[i].field_3_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_3_2_1*1);

              if(totalTableData[i].field_3_4_1 === ''){
                totalTableData[i].field_3_4_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_3_4_1*1);

              if(totalTableData[i].field_3_6_1 === ''){
                totalTableData[i].field_3_6_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_3_6_1*1);

              if(totalTableData[i].field_3_8_1 === ''){
                totalTableData[i].field_3_8_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_3_8_1*1);

              if(totalTableData[i].field_3_10_1 === ''){
                totalTableData[i].field_3_10_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_3_10_1*1);

              if(totalTableData[i].field_3_12_1 === ''){
                totalTableData[i].field_3_12_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_3_12_1*1);

              if(totalTableData[i].field_3_14_1 === ''){
                totalTableData[i].field_3_14_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_3_14_1*1);

              if(totalTableData[i].field_3_16_1 === ''){
                totalTableData[i].field_3_16_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_3_16_1*1);

              if(totalTableData[i].field_3_18_1 === ''){
                totalTableData[i].field_3_18_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_3_18_1*1);

              if(totalTableData[i].field_3_20_1 === ''){
                totalTableData[i].field_3_20_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_3_20_1*1);

              if(totalTableData[i].field_3_22_1 === ''){
                totalTableData[i].field_3_22_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_3_22_1*1);

              if(totalTableData[i].field_3_24_1 === ''){
                totalTableData[i].field_3_24_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_3_24_1*1);

              if(totalTableData[i].field_3_26_1 === ''){
                totalTableData[i].field_3_26_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_3_26_1*1);

              if(totalTableData[i].field_3_28_1 === ''){
                totalTableData[i].field_3_28_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_3_28_1*1);

              if(totalTableData[i].field_3_30_1 === ''){
                totalTableData[i].field_3_30_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_3_30_1*1);

              if(totalTableData[i].field_3_32_1 === ''){
                totalTableData[i].field_3_32_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_3_32_1*1);

              if(totalTableData[i].field_3_34_1 === ''){
                totalTableData[i].field_3_34_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_3_34_1*1);

              if(totalTableData[i].field_3_36_1 === ''){
                totalTableData[i].field_3_36_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_3_36_1*1);

              if(totalTableData[i].field_3_38_1 === ''){
                totalTableData[i].field_3_38_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_3_38_1*1);
              
              if(totalTableData[i].field_3_40_1 === ''){
                totalTableData[i].field_3_40_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_3_40_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;total[19]=num21;
            //求占比
            var totalNum=0;//小计
            for(var i=0; i<total.length; i++){
              totalNum = total[i] + totalNum;
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
            //小计
            if(totalNum === num1){
              total[total.length] = '核对正确！';
              sums[sums.length] = '100%';
            }else{
              total[total.length] = '错误！';
              sums[sums.length] = ((totalNum/num1)*100).toFixed(2)+'%';
            };
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":num1,"field_3_1":'--',"field_3_2_1":total[0],"field_3_2_2":sums[0],
                      "field_3_3":'--',"field_3_4_1":total[1],"field_3_4_2":sums[1],"field_3_5":'--',"field_3_6_1":total[2],"field_3_6_2":sums[2],
                      "field_3_7":'--',"field_3_8_1":total[3],"field_3_8_2":sums[3],"field_3_9":'--',"field_3_10_1":total[4],"field_3_10_2":sums[4],
                      "field_3_11":'--',"field_3_12_1":total[5],"field_3_12_2":sums[5],"field_3_13":'--',"field_3_14_1":total[6],"field_3_14_2":sums[6],
                      "field_3_15":'--',"field_3_16_1":total[7],"field_3_16_2":sums[7],"field_3_17":'--',"field_3_18_1":total[8],"field_3_18_2":sums[8],
                      "field_3_19":'--',"field_3_20_1":total[9],"field_3_20_2":sums[9],"field_3_21":'--',"field_3_22_1":total[10],"field_3_22_2":sums[10],
                      "field_3_23":'--',"field_3_24_1":total[11],"field_3_24_2":sums[11],"field_3_25":'--',"field_3_26_1":total[12],"field_3_26_2":sums[12],
                      "field_3_27":'--',"field_3_28_1":total[13],"field_3_28_2":sums[13],"field_3_29":'--',"field_3_30_1":total[14],"field_3_30_2":sums[14],
                      "field_3_31":'--',"field_3_32_1":total[15],"field_3_32_2":sums[15],"field_3_33":'--',"field_3_34_1":total[16],"field_3_34_2":sums[16],
                      "field_3_35":'--',"field_3_36_1":total[17],"field_3_36_2":sums[17],"field_3_37":'--',"field_3_38_1":total[18],"field_3_38_2":sums[18], 
                      "field_3_39":'--',"field_3_40_1":total[19],"field_3_40_2":sums[19],"field_3_41_1":totalNum,"field_3_41_2":"100.00%","in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          } 
            _this.tableData = totalTableData;
            //console.log(_this.tableData);
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //分页
    handleCurrentChange(currentPage){
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
    overflow-x:auto;
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



