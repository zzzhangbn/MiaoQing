<template>
  <div class="dashWrap">
    <!-- <el-input v-model="input" placeholder="请输入内容"></el-input>   -->
    <el-table :data="tableData" show-summary :summary-method="getSummaries" border style="width: 100%" :header-cell-style="headerMethod" row-key="id" stripe>
      <el-table-column :label="tableLabel" align="center">
        <el-table-column align="center" fixed type="index"  label="序号"/>
        <el-table-column v-for="(item, index) in tableHeader" sortable :key="index" align="center" :prop='item.prop' :label="item.label">

          <el-table-column v-for="(childItem, indexChild) in item.children"   :key="index+'-'+indexChild" align="center" :prop="childItem.prop" :label="childItem.childLabel">
            <!-- <template>  
              <span >111</span>
            </template> -->
          </el-table-column>
        </el-table-column>
        <!-- <el-table-column align="center"  prop="date" label="日期" width="100" />
        <el-table-column align="center"  prop="user_id" label="使用者" /> -->
      </el-table-column>
    </el-table>
    <el-row>
      <el-button  type="info" plain @click="getDataAnalyse">数据分析</el-button>
    </el-row>
  </div>
</template>


<script>
import axios from 'axios'
export default {
  data() {
    return {      
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县（市、区）水稻栽插概况调查表',
      //表头数据
      tableHeader:[],
      // tableHeader:[
      //   {label:'县（市、区）',prop:'field_1'},
      //   {label:'土地肥力状况',prop:'field_2',children:[
      //     {childLabel:'二级标签1',prop:'two1'},
      //     {childLabel:'二级标签2',prop:'two2'},
      //     {childLabel:'二级标签3',prop:'two3'}
      //   ]},
      //   {label:'111级表头',prop:'two',children:[
      //     {childLabel:'二级标签1',prop:'two1'},
      //     {childLabel:'二级标签2',prop:'two2'},
      //     {childLabel:'二级标签3',prop:'two3'}
      //   ]},
      // ],
      input1:'',
    };
  },
  created() {
    this.initData();
    this.$nextTick(function(){
      this.setColSpan();
    })
    
  },
  methods: {
    
     /* @method initData
     * @description 初始化数据
     */
    initData() {
      this.getHeaderList();
      this.getTableData();
      this.getSummaries();

    },
    /**
     * @description 获取表头列表
     * @method getHeaderList
     */
    getHeaderList(){
       var table = 'crop_01_01_table_01_01';
       var header = [];
       const _this = this;
       axios({
          url: '/getTableHeader',
          method:'get',
          params:{
            tableName:table
          }
       }).then(res=>{
          header=res.data;
          //console.log(header);
          var headerData = [];

          for(var i=0; i<header.length; ++i){
            if(header[i].father_id == '0'){
                headerData.push({"label":header[i].column_name, "prop":header[i].column_id});
                
            }
          }
          //console.log(headerData);
          for(var i =0; i<headerData.length; ++i){
            var childData = [];
            for(var j=0; j<header.length; ++j){
              if(header[j].father_id == headerData[i].prop){
                childData.push({"childLabel":header[j].column_name, "prop":header[j].column_id});
              }
            }
            headerData[i]["children"]=childData;
          };
          
          //console.log(headerData);
          this.tableHeader = headerData;
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
       
    },
    /**
     * @description 获取表数据
     * @method getTableData
     */
    getTableData(){
       var table = 'crop_01_01_table_01_02';
       const _this = this;
       axios({
          url: '/getRiceTable1',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
          _this.tableData=res.data;
          //console.log(_this.tableData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    /**
     * @description 合计行
     * @method Summaries
     */
    getSummaries(param) {
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
          if(index === 0){
            sums[index] = '';
            return;
          }
          if (index === 1) {
            sums[index] = '合计';
            return;
          }
          const values = data.map(item => Number(item[column.property]));
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
            sums[index] += '';
          } else {
            sums[index] = '--';
          }
        });

        return sums;
    },
    /**
     * @description 跳转数据分析图
     * @method getDataAnalyse
     */
    getDataAnalyse(){
      //console.log("11111")
    },
    /**
     * @description 表头合并列
     * @method headerSpan
     */
      //隐藏表头
      //:header-cell-style="{background:'#DDDDDD',color:'#606266'}"
      headerMethod({ row, column, rowIndex, columnIndex}) {
        //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex===2 && columnIndex===0) {
          //console.log("111")
          this.$nextTick(()=> {
            if(document.getElementsByClassName(column.id).length!==0) {
              document.getElementsByClassName(column.id)[0].setAttribute('rowSpan',2);
              //console.log("222");
              ////console.log(column.id)
              return false
            }
          })
        }
        if(rowIndex=== 2 && columnIndex===1 ) {
          //console.log("333")
          return {display:'none'}
        }

      },
    

    // arraySpanMethod ({ row, column, rowIndex, columnIndex }) {
    // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
    // if (rowIndex === 0) {
    //   if (columnIndex === 5) {
    //     //console.log(row, column, rowIndex, columnIndex );
    //     return {
    //       rowspan: 1,
    //       colsoan: 2,
    //     }
    //   }
    // }
    // },
    // setColSpan:function(){
    //    // //console.log(document.getElementsByClassName("el-table__header"))
    //   // 获取表头的所有单元格
    //   var x = document.getElementsByClassName("el-table__header")[0].rows[0].cells
    //   // 将第二列表头单元格的colSpan设为2
    //   x[1].colSpan = 2
    //   // 将第三列表头单元格的display设为none
    //   x[2].style.display = 'none'
    // },
    changeTableSort(){
    },
  }
};
</script>

<style>
.dashWrap{
    flex:1;
    width:1525px;
    overflow-y:auto;
    display:flex;
    flex-direction: column;
};
.el-button--info:hover {
  background: #FFFF00;
}
</style>



