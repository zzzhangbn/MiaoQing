<template>
  
    <div class="totalRange">
          <div class="tableRange">
            <div class="searchBox">            
              <el-row>
                <el-col :span="6">
                  <template>
                    <el-select v-model="city" clearable  style="width:160px; margin-left: 20px;" placeholder="市">
                      <el-option
                        v-for="item in citys"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                    <el-select v-model="county" clearable  style="width:160px; margin-left: 20px;" placeholder="县（市、区）">
                      <el-option
                        v-for="item in countys"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-col>
              <el-col :span="4">
                <el-button type="info" @click="reSet">重置</el-button>
                <el-button type="info" @click="searchInfo">查询</el-button>
              </el-col>
              <el-col :span="3">
                <!-- <el-button  type="info" plain @click="downloadAndPrint">下载打印</el-button> -->
                    <el-button  type="info" plain @click="handleDownload">全部数据导出Excel</el-button>
              </el-col>
              </el-row>
            </div>
            <div class="tableStyle">
              <el-row>
                <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:10px">
                  <span>{{tableLabel}}</span>
                </div>
              </el-row>
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-style="{height:'30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="100%" label="监测点代码" fixed/>
                <el-table-column align="center" label="地址">
                  <el-table-column align="center" prop="field_2_1" width="100%" label="市" />
                  <el-table-column align="center" prop="field_2_2" width="120%" label="县（市、区）"/>
                  <el-table-column align="center" prop="field_2_3" width="100%" label="镇（乡）" />
                  <el-table-column align="center" prop="field_2_4" width="100%" label="村名"/>
                  <el-table-column align="center" prop="field_2_5" width="100%" label="户主" />
                </el-table-column>
                <el-table-column align="center" label="监测田块">
                  <el-table-column align="center" prop="field_3_1" width="120%" label="种植面积（亩）" />
                  <el-table-column align="center" prop="field_3_2" width="100%" label="地区类型"/>
                  <el-table-column align="center" prop="field_3_3" width="100%" label="土壤类型" />
                  <el-table-column align="center" prop="field_3_4" width="100%" label="前茬类型"/>
                  <el-table-column align="center" prop="field_3_5" width="120%" label="水管方式" />
                  <el-table-column align="center" prop="field_3_6" width="100%" label="全氮(g/Kg)" />
                  <el-table-column align="center" prop="field_3_7" width="120%" label="速效磷(g/Kg)"/>
                  <el-table-column align="center" prop="field_3_8" width="120%" label="速效钾(g/Kg)" />
                  <el-table-column align="center" prop="field_3_9" width="120%" label="有机质(g/Kg)"/>
                  <el-table-column align="center" prop="field_3_10" width="150%" label="秸秆还田量(kg/亩)" />
                </el-table-column>
                <el-table-column align="center" label="品种（品系）">
                  <el-table-column align="center" prop="field_4_1" width="100%" label="名称" />
                  <el-table-column align="center" prop="field_4_2" width="100%" label="稻作类型"/>
                  <el-table-column align="center" prop="field_4_3" width="100%" label="品系类型" />
                </el-table-column>
                <el-table-column align="center" label="田间信息">
                  <el-table-column align="center" prop="field_5_1" width="100%" label="栽植方式" />
                  <el-table-column align="center" prop="field_5_2" width="100%" label="播期"/>
                  <el-table-column align="center" prop="field_5_3" width="120%" label="移栽秧龄（天）" />
                  <el-table-column align="center" prop="field_5_4" width="120%" label="移栽叶龄（叶）"/>
                  <el-table-column align="center" prop="field_5_5" width="130%" label="基本苗（万/亩）" />
                  <el-table-column align="center" prop="field_5_6" width="100%" label="收获日期" />
                  <el-table-column align="center" prop="field_5_7" width="120%" label="亩有效穗(万)"/>
                  <el-table-column align="center" prop="field_5_8" width="120%" label="穗总粒数(个)" />
                  <el-table-column align="center" prop="field_5_9" width="100%" label="结实率(％)"/>
                  <el-table-column align="center" prop="field_5_10" width="100%" label="千粒重(g)" />
                  <el-table-column align="center" prop="field_5_11" width="130%" label="理论产量(kg/亩)"/>
                  <el-table-column align="center" prop="field_5_12" width="130%" label="实收产量(kg/亩)" />
                </el-table-column>
                <el-table-column align="center"  fixed="right" width="120px" label="操作" v-if="years == currentyear">
                  <template slot-scope="scope">
                    <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认删除?</p>
                      <div style="text-align: right; margin: 10px">
                        <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                        <el-button type="primary" size="mini" @click="deletePoint(scope.$index,scope.row)">确 定</el-button>
                      </div>
                      <el-button slot="reference" size="mini"   type="danger" style="margin-right:10px;">删  除</el-button>
                    </el-popover>
                  </template>
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
        
      <div class="button">
        <el-button type="success" @click="returnPage">返  回</el-button>
      </div>
     </div>
  
</template>


<script>
import axios from 'axios'
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
      tableLabel:'',
      tableName:'crop_01_02_table_02_02',
      //县的option
      countys:[],
      county: '',
      //市option
      citys:[],
      city: '',
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
      //暂存参数
      currentyear:'',
      areas:'',
      chengshis:'',
      years:'',
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
    var myDate = new Date();
    this.currentyear = myDate.getFullYear();
      this.areas = this.$route.query.area;
      this.chengshis = this.$route.query.city;
      this.years = this.$route.query.year;
      this.tableLabel= this.years+"年度水稻监测点汇总表";
      //this.getHeaderList();
      this.getTableData();
      this.getCountySelection();
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
    //获取表数据
    getTableData(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo22',
          method:'post',
          params:{
            areaType:this.areas,
            county:this.chengshis,
            year:this.years
           }
       }).then(res=>{
           _this.tableData=res.data;
           _this.dataNum = _this.tableData.length;
            //console.log(_this.tableData);
          
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
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
          var cityData = [];
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "市"){
                cityData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              // if(optionData[i].caption === "稻作类型"){
              //   riceData.push({"label":optionData[i].list,"value":optionData[i].list});
              // };
          };
          _this.countys = countyData;
          _this.citys = cityData;
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //重置
    reSet(){
      this.city='';
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      const _this = this;
      var years = this.$route.query.year;
      axios({
        url:'/getSeekInfoTwo22',
        method:'post',
        params:{county:this.county,
                city:this.city,
                year:this.years,
        }
      }).then(res=>{
            _this.tableData = res.data;
            _this.dataNum = _this.tableData.length;
            //console.log(_this.tableData);
          
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
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
    //导出excel
     // 导出Excel点击方法
      handleDownload() {
        // this.downloadLoading = true // 加载遮罩层开启
        //console.log("11111");
        const {export_json_to_excel} = require('../../excel/Export2Excel2'); //引入文件
        const th1 = [[this.tableLabel, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                     ['监测点代码', '地址', '', '', '', '', '监测田块', '', '', '', '', '', '', '', '', '', '品种（品系）', '', '', '田间信息', '', '', '', '', '', '', '', '', '', '', '']] //对应表格一级输出的title
        const header = []//三级
        const th2 =  [['', '市', '县（市、区）', '镇（乡）', '村名', '户主', '种植面积（亩）', '地区类型', '土壤类型', '前茬类型', '水管方式', '全氮(g/Kg)', '速效磷(g/Kg)', '速效钾(g/Kg)', '有机质(g/Kg)', '秸秆还田量(kg/亩)', '名称', '稻作类型', '品系类型', '栽植方式', '播期', '移栽秧龄（天）', '移栽叶龄（叶）', '基本苗（万/亩）', '收获日期', '亩有效穗(万)', '穗总粒数(个)', '结实率(％)', '千粒重(g)', '理论产量(kg/亩)', '实收产量(kg/亩)']] //对应表格三级输出的title
        const filterVal = ['field_1', 'field_2_1', 'field_2_2', 'field_2_3', 'field_2_4', 'field_2_5', 'field_3_1', 'field_3_2', 'field_3_3', 'field_3_4', 'field_3_5', 'field_3_6', 'field_3_7', 'field_3_8', 'field_3_9', 'field_3_10', 'field_4_1', 'field_4_2', 'field_4_3', 'field_5_1', 'field_5_2', 'field_5_3', 'field_5_4', 'field_5_5', 'field_5_6', 'field_5_7', 'field_5_8', 'field_5_9', 'field_5_10', 'field_5_11', 'field_5_12'] // 对应表格tableData输出的数据
        //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
        //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
        const merges = [
          "A1:AE1",
          "A2:A3",
          "B2:F2",
          "G2:P2",
          "Q2:S2",
          "T2:AE2",
        ];
        const list = this.tableData;
        //console.log(list);
        const data = this.formatJson(filterVal, list);
        //console.log(data);
        export_json_to_excel(th1, th2, header, data, this.tableLabel, merges);
        // this.downloadLoading = false // 加载遮罩层结束
      },
      // 将JSON转换成map
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]))
      },
    //返回
    returnPage(){
      var diqu = this.areas;
      var xian = this.chengshis;
      var nian = this.years;
      //console.log(diqu,xian,nian)
      if(xian ==''){
        this.$router.push({path:'/dataHome',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='宣州区') || (xian=='庐江县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='怀宁县') || (xian=='枞阳县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='贵池区')|| (xian=='南陵县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else{
        this.$router.push({path:'/dataHome1',query:{area:diqu,city:xian,year:nian}});
      };
    },
    //删除
    //关闭popover
    closePopover(index){
      this.$refs[`popover-${index}`].doClose();
    },
    deletePoint(index,row){
      const _this = this;
      axios({
        url:'/deletePoint2',
        method:'post',
        params:{pointInfo:row}
      }).then(res=>{
        this.getTableData();
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
      this.$refs[`popover-${index}`].doClose();
    }


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



