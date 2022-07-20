<template>
    <div>
      <el-card class="box-card">
        <el-table
          :data="personTable"
          border
          style="width: 100%"
          highlight-current-row
          >
          <el-table-column
            fixed
            prop="name"
            label="人员姓名"
            width="80"
            center
            >
          </el-table-column>
          <el-table-column
            fixed
            prop="email"
            label="人员邮箱"
            >
          </el-table-column>
          <el-table-column
            fixed
            prop="crop"
            label="作物类型"
            >
          </el-table-column>
          <el-table-column
            fixed
            prop="plot"
            label="区域范围"
            >
          </el-table-column>
          <el-table-column
            fixed
            prop="access"
            label="用户类型"
            >
          </el-table-column>
          <el-table-column
            fixed
            prop="audit"
            label="审核状态"
            width="100"
            >
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="shenpi(scope.row)" v-if="scope.row.audit==='1'">审核</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog
          title="审批页面"
          :visible.sync="dialogVisible"
          width="30%"
          >
          <el-select v-model="optionValue" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitShenpi">确 定</el-button>
          </span>
        </el-dialog>

      </el-card>
    </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      //人员审核名单表格
      personTable:[],
      //审批弹框打开
      dialogVisible:false,
      //审核的选项
      options:[
        {
          value: '2',
          label: '审核通过'
        }, 
        {
          value: '3',
          label: '审核不通过'
        }
      ],
      optionValue:'',
      //选择的审批的用户数据
      tempPerson:{}



    }
  },
  mounted(){
    this.getAllPerson();
  },
  methods:{
    //获取到所有的人员信息
    getAllPerson(){
      axios({
        url:'/getAllPerson',
        method:'post'
      }).then(res=>{
        res.data.forEach(element => {
          if(element.access==="2"){
            element.access="苗情检测员"
          }else if(element.access==="1"){
            element.access="平台管理员"
          }
        });
        this.personTable = res.data;
      })
    },
    //人员状态的审批
    shenpi(row){
      this.dialogVisible = true;
      this.tempPerson = row;
    },
    //审批信息的提交
    submitShenpi(){
      axios({
        url:'/shenpi',
        method:'post',
        params:{
          person:this.tempPerson,
          shenpiValue:this.optionValue
        }
      }).then(res=>{
        this.el-alert(res.data.msg);
        window.onload();
      })
    }
    
  }

}
</script>

<style>

</style>