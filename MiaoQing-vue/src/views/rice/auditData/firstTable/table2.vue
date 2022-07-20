<template>
  <div class="dashWrap">
    <el-table :data="tableData" border style="width: 100%" row-key="id" stripe>
      <el-table-column :label="tableLabel" align="center">
        <el-table-column align="center" fixed type="index"  label="序号" />
        <el-table-column align="center" type="index"  label="序号" />
        <!-- <el-table-column v-for="(item, index) in tableHeader" sortable :key="index" align="center" :prop='item.prop' :label="item.label">

          <el-table-column v-for="(childItem, indexChild) in item.children"   :key="index+'-'+indexChild" align="center" :prop="childItem.prop" :label="childItem.childLabel">
          </el-table-column>
        </el-table-column> -->
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
      tableLabel:'2021年度监测县(市、区)水稻生产主推品种调查表',
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
      // this.getTableData();
      // this.getSummaries();

    },
    /**
     * @description 获取表头列表
     * @method getHeaderList
     */
    getHeaderList(){
       var table = 'crop_01_01_table_01_02';
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
          }
          // for(var i=0; i<header.length;){
          //   if(header[i].column_id == header[i+1].father_id){
          //     var childData = [];
          //     var count = 0;
          //     var temp = header[i].column_id;
          //     //console.log(i);
          //     //console.log(temp);
          //     for(var j=i; j<(header.length-i); j++){
          //       if(header[j+1].father_id == temp){
          //         childData.push({"childLabel":header[j+1].column, "prop":header[j+1].column_id});
          //         count++;
          //       }
          //     }
          //     ////console.log(count);
          //     headerData.push({"label":header[i].column, "prop":header[i].column_id, "children":childData});
          //     //console.log(headerData);
          //     i=i+count+1;
          //     //console.log(i);
          //   }else{
          //     headerData.push({"label":header[i].column, "prop":header[i].column_id})
          //     i++;
          //   }
              
          // };
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
          url: '/getTableData',
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
     * @description 表头合并列
     * @method headerSpan
     */
      //隐藏表头
      handerMethod({ row, column, rowIndex, columnIndex}) {
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
    setColSpan:function(){
       // //console.log(document.getElementsByClassName("el-table__header"))
      // 获取表头的所有单元格
      var x = document.getElementsByClassName("el-table__header")[0].rows[0].cells
      // 将第二列表头单元格的colSpan设为2
      x[1].colSpan = 2
      // 将第三列表头单元格的display设为none
      x[2].style.display = 'none'
    },
    changeTableSort(){
    },
  }
};
</script>

<style scoped>
</style>



