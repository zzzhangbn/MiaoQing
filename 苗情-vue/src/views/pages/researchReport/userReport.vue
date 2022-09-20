<template>
  <div class="all">
      <el-breadcrumb style="font-size:18px;margin:20px 0px 10px 0px;">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>登录日志</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="filter-container" style="margin:0px 0px 10px 0px;">
        
        <el-input placeholder="查询登录账号" v-model.trim="FromData.account" style="margin-right: 5px;width:150px" class="filter-container"></el-input>
        
        <el-input placeholder="查询用户ip" v-model.trim="FromData.loginip" style="margin-right: 5px;width: 150px;" class="filter-container"></el-input>
        
        <el-input placeholder="查询登录归属地" v-model.trim="FromData.loginname" style="margin-right: 5px;width: 150px;" class="filter-container"></el-input>
        
        <el-input placeholder="查询用户登录状态" v-model.trim="FromData.status" style="margin-right: 5px;width: 150px;" class="filter-container"></el-input>
        <el-date-picker
          style="margin-right: 29px"
          v-model="dates"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="截至日期"
          value-format="yyyy-MM-dd">
        </el-date-picker>
        <el-button  style="" icon="el-icon-search" type="success" @click="getlivestockInfoBy(1)">查询</el-button>
        <el-button  style="" type="primary" @click="excelVisible=true">导出为excel</el-button>
      </div>
      <!-- 展示表格 -->
      <el-dialog
        title="选择导出页面"
        :visible.sync="excelVisible"
        :before-close="handleClose"
      >
        <el-radio v-model="radio" label="1">导出当前页</el-radio>
        <el-radio v-model="radio" label="2">导出所有页</el-radio>
        <span slot="footer" class="dialog-footer">
          <el-button @click="excelVisible=false">取 消</el-button>
          <el-button type="primary" @click="exportExcelChice">确 定</el-button>
        </span>
      </el-dialog>
      <el-card>
      <el-table :data="dataList" border style="width: 100%" height="400" id="excel">
        <el-table-column
          label="登录账号"
          prop="account"
          align="center">
        </el-table-column>
        <el-table-column
          label="登录时间"
          prop="logintime"
          align="center">
        </el-table-column>
        <el-table-column
          label="登录ip"
          prop="loginip"
          align="center">
        </el-table-column>
        <el-table-column
          label="登录归属地"
          prop="loginname"
          align="center">
        </el-table-column>
        <el-table-column
          label="登录状态"
          prop="status"
          :formatter="statusFormatter"
          align="center">
        </el-table-column>
      </el-table>
      </el-card>
      <!--分页组件-->
      <div class="block" style="margin-top:15px" align="right">
            <el-pagination
            	@size-change="handleSizeChange"
            	@current-change="handleCurrentChange"
              background
            	:current-page="currentPage"
            	:page-size="pageSize"
            	layout="total, prev, pager, next, jumper"
            	:total="total">
			      </el-pagination>
      </div>
  </div>
  
</template>

<script>
import axios from 'axios'
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
export default {
    data() {
      return {
        radio: '1',
        excelVisible:false,
        dataList: [],//当前页要展示的列表数据
        dates: '',
        time1:'',
        time2:'',
        FromData:{
            account:'',
            logintime:'',
            loginip:'',
            loginname:'',
            status:'',
        },
        currentPage: 1, // 当前页码
        total: '', // 总条数
        pageSize: 15 // 每页的数据条数
        }
      },
    mounted(){
      this.getlivestockInfo(1);
    },
    methods: {
      //每页条数改变时触发 选择一页显示多少行
			handleSizeChange(val) {
				  //console.log(`每页 ${val} 条`);
				  this.currentPage = 1;
				  this.pageSize = val;
			},
      //定义改变页面事件
      handleCurrentChange(val) {
				    //console.log(`当前页: ${val}`);
				    this.currentPage = val;
					  this.getlivestockInfo(val);
				},
      //查询事件
      getlivestockInfo(num1){
          var that = this;
					axios({
            url:"/getAllRecord",
            method: 'post',
            params:{
            currentpage: num1,
            pagesize: this.pageSize,
            time1: this.time1,
            time2: this.time2,
            },
            data: this.FromData
          }).then((res) => {  // 请求成功
						console.log('请求成功');
						that.dataList=res.data.data;
						that.currentPage=num1;
						that.pageSize=res.data.pagesize;
						that.total=res.data.total;
						//console.log(res.data);
					}).catch(error => {  // 请求失败
						console.log('请求失败');
						console.log(error);
					})
				},
      statusFormatter(row, column){
				  const status = row.status
              if (status == 0) {
                return '失败'
              } else if (status == 1) {
                return '成功'
              } 
        },
      //验证用户账号是否为数字格式
      isNumber(value) {//验证是否为数字
          var patrn = /^(-)?\d+(\.\d+)?$/;
          if (patrn.exec(value) == null || value == "") {
              return false
          } else {
              return true
          }
      },
      isCharacters(value) {//验证是否为汉字
          var patrn = /^([\u4e00-\u9fa5]+(\s?))+$/;
          if (patrn.exec(value) == null || value == "") {
              return false;
          } else {
              return true;
          }
      },
      //条件查询事件
      getlivestockInfoBy(num1){
          var that=this;
          //判断用户输入账号信息是否为数字
          if(that.FromData.account && !that.isNumber(that.FromData.account)){
              that.$message.error('你输入的账号格式应为连续数字，请重新输入！');
              return;
          }
          //判断用户输入IP信息是否为数字
          if(that.FromData.loginip && !that.isNumber(that.FromData.loginip)){
              that.$message.error('你输入的IP格式应为连续数字，请重新输入！');
              return;
          }
          //判断用户输入状态信息是否为数字
          if(that.FromData.status != 0 && that.FromData.status != 1){
              that.$message.error('登录状态值只能为0或1，请重新输入！');
              return;
          }
          //判断用户输入的地址是否位汉字
          if(that.FromData.loginname && !that.isCharacters(that.FromData.loginname)){
              that.$message.error('你输入的地址格式应为汉字，请重新输入！');
              return;
          }
          // 防止没有选时间导致变量未初始化传递空
          if(this.dates){
            this.time1=this.dates[0];
            this.time2=this.dates[1];
          }
          
					axios({
            url:"/getAllRecord",
            method: 'post',
            params:{
            currentpage: num1,
            pagesize: this.pageSize,
            time1: this.time1,
            time2: this.time2,
            },
            data: that.FromData
          }).then((res) => {  // 请求成功
						console.log('请求成功');
						that.dataList=res.data.data;
						that.currentPage=num1;
						that.pageSize=res.data.pagesize;
						that.total=res.data.total;
					}).catch(error => {  // 请求失败
						console.log('请求失败');
						console.log(error);
            that.$message.error('查询失败');
					})
				},

      // 将数据导出位excel格式
      exportExcelAll(){
        this.pageSize=this.total;
        this.getlivestockInfoBy(1);
        this.$confirm("确定导出为excel").then(()=> {
          
          let wb = XLSX.utils.table_to_book(document.querySelector('#excel'));
          let wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' });
          try {
              FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '管理日志.xlsx');
              
          } catch (e)
          {
              // if (typeof console !== 'undefined')
                  //console.log(e, wbout)
          }
          this.pageSize=15;
          this.getlivestockInfoBy(1);
          return wbout;
        }).catch(()=>{
          this.pageSize=15;
          this.getlivestockInfoBy(1);
      })
      },
      exportExcelOne(){
        this.$confirm("确定导出为excel").then(()=> {
          
          let wb = XLSX.utils.table_to_book(document.querySelector('#excel'));
          let wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' });
          try {
              FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '管理日志.xlsx');
              
          } catch (e)
          {
              // if (typeof console !== 'undefined')
                  //console.log(e, wbout)
          }
          return wbout;
        }).catch(()=>{
      })
      },
      exportExcelChice(){
        if(this.radio==='1'){
          this.exportExcelOne();
        } else{
          this.exportExcelAll();
        }
        this.excelVisible=false;
      },
    },
};
</script>

<style scoped>
.all {
  width: 80%;
  margin: 0 auto;
  height:700px;
}
</style>