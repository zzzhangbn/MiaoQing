<template>
  <!-- 账户分配组件 -->
  <div style="height:500px;width: 80%; margin: 0 auto">
    <el-card>
      <el-button type="primary" @click="openDialog">账号分配</el-button>
      <el-button type="success" @click="exportExcel">导出为excel</el-button>
      <el-dialog title="账户信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form :model="form">
          <div>
            <el-form-item label="账号" label-width="120px">
              <el-input autocomplete="off" placeholder="账号自动生成，无需填写" disabled></el-input>
            </el-form-item>
            <el-form-item label="县(市，区)" label-width="120px">
              <el-select v-model="form.area" placeholder="请选择管理县市" @change="changeArea">
                <el-option  v-for="item in areas" :key="item.id" :label="item.list" :value="item.list"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="作物类型" label-width="120px">
              <el-input  v-model="crop" disabled></el-input>
            </el-form-item>
            <el-form-item label="二级作物类型" label-width="120px" v-if="showSubCrop">
              <el-input  v-model="sub_crop" disabled width="100px"></el-input>
            </el-form-item>
          </div>
          <div v-for="(item,index) in form.people" :key="index">
            <el-card style="position:relative;">
              <el-form-item :label="`联系人姓名${(index+1)}`" label-width="100px">
                <el-input placeholder="请输入联系人姓名" v-model="item.name" style="width:60%;"></el-input>
              </el-form-item>
              <el-form-item label="联系方式" label-width="100px" 
              :prop="`people.${index}.phone`" 
              :rules="rules.phone">
                <el-input placeholder="请输入手机号" v-model="item.phone" style="width:60%;"></el-input>
              </el-form-item>
              <el-button icon="el-icon-remove-outline" circle style="position:absolute;right:0;top:0;" v-if="index!=0" @click="delPeople(index)"></el-button>
            </el-card>
          </div>
          <el-button type="mini" style="width:100px;margin-left:220px;" @click="addPeople">新增联系人</el-button>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="accountAllocation">确 定</el-button>
        </div>
      </el-dialog>
      <el-table :data="tableData" border style="width: 100%" height="400" id="excel">
        <el-table-column
          type="index"
          label="序号"
          width="50"
          align="center"
        ></el-table-column>
        <el-table-column prop="account" label="账号" width="180" align="center">
        </el-table-column>
        <el-table-column prop="name" label="联系人姓名" align="center"> </el-table-column>
        <el-table-column prop="phone" label="联系人手机号码" align="center"> </el-table-column>
        <el-table-column prop="sub_crop" label="二级作物类型" align="center"> </el-table-column>
        <el-table-column prop="area" label="县市区" align="center"> </el-table-column>
        <!-- <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="accountDeprecation(scope.row)" type="text" size="small">账户弃用</el-button>
          </template>
        </el-table-column> -->
      </el-table>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'
import { re } from 'mathjs';
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
export default {
  data() {
    //检查手机格式
    var checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        this.phoneFlag = reg.test(value);
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error('请输入正确的手机号'));
        }
      }
    };
    return {
      areas:[],
      dialogFormVisible: false,
      //账户分配信息填写表格
      form: {
        area:'',
        crop:'',
        people:[{name:'',phone:''}],
      },
      //表格数据
      tableData:[],
      //二级分类
      showSubCrop:false,
      //作物类别的显示
      crop:'',
      sub_crop:'',
      //表单规则
      rules:{
        phone:[
          {validator: checkPhone, trigger: 'blur'}
        ]
      },
      phoneFlag:false,
    };
  },
  created(){
    this.getAccounts();
    this.translateCrop();
  },
  methods:{
    //苗情监测员分配
    accountAllocation(){
      var crop = localStorage.getItem("crop")
      this.$confirm("提交确定？").then(res=>{
          if(this.form.area==''||this.form.people[0].name==''||this.phoneFlag==false){
            this.$message({message:'请将信息填完整！',type:'error'});
          }else{
            var crop = localStorage.getItem("crop");
            this.form.crop = crop;
            var data = new FormData;
            // 将form表单中的值都赋值给FormData传递给后台
            for(let key in this.form){
              if(key==='people'){
                data.append(key,JSON.stringify(this.form[key]));
              }else{
                data.append(key,this.form[key]);
              }
            }
            axios({
            url:'/accountAllocation',
            data:data,
            method:'post'
            }).then(res=>{
              var msg = res.data.msg;
              var token = res.data.token;
              if(token==='200'){
                this.$message({message:msg,type:'success'});
                window.setTimeout(location.reload(),2000);
              }else{
                this.$message({message:msg,type:'warning'});
              }
            })
          }
      })
    },
    //根据作物类型查询所有的苗情监测员账号
    getAccounts(){
      var crop = localStorage.getItem("crop")
      axios({
        url:'/getAccounts',
        method:'post',
        params:{crop:crop}
      }).then(res=>{
        var tempData = res.data;
        console.log(tempData);
        //作物类型的转化
        tempData.forEach(item => {
          //将字符串转化为数组
          var crops =item.crop.split(',');
          for(let i=0;i<crops.length;i++){
            switch(crops[i]){
              case 'crop_01':
                crops[i] = '水稻';
                break;
              case 'crop_02':
                crops[i] = '小麦';
                break;
              case 'crop_03':
                crops[i] = '玉米';
                break;
              case 'crop_04':
                crops[i] = '油菜';
                break;
              case 'crop_05':
                crops[i] = '大豆';
                break;
              case 'crop_06':
                crops[i] = '棉花';
                break;
            }
          }
          item.crop = crops.toString();
          switch(item.sub_crop){
            case "crop_01_01":
              item.sub_crop = '单季稻';
              break;
            case "crop_01_02":
              item.sub_crop = '双季稻';
              break;
          }
        });
        this.tableData = res.data;
      })
    },
    //账户弃用
    accountDeprecation(row){
      axios({
        url:'/accountDeprecation',
        params:{account:row.account},
        method:'post'
      }).then(res=>{
        if(res.status ===200){
          this.$message('操作成功');
        }
        setTimeout(location.reload(),1000);
      })
    },
    //获取到所有的县市信息
    getallAreaCode(){
      axios({
        url:'/getAllAreaCode',
        method:'get'
      }).then(res=>{
        let arr = res.data;
        this.areas = arr;
      })
    },
    //账户分配表单打开
    openDialog(){
      this.dialogFormVisible = true;
      this.getallAreaCode();
    },
    //作物类别转化为文字
    translateCrop(){
      var crop = localStorage.getItem("crop");
      switch(crop){
              case 'crop_01':
                this.crop = '水稻';
                break;
              case 'crop_02':
                this.crop = '小麦';
                break;
              case 'crop_03':
                this.crop = '玉米';
                break;
              case 'crop_04':
                this.crop = '油菜';
                break;
              case 'crop_05':
                this.crop = '大豆';
                break;
              case 'crop_06':
                this.crop = '棉花';
                break;
      }
    },
    //根据选择的县市判断是否展示二级作物类别
    changeArea(val){
      this.areas.forEach(item => {
          this.showSubCrop = true;
          if(val===item.list){
            if(item.rice_type=='one'){
              this.sub_crop = '单季稻'
            }else if(item.rice_type=='double'){
              this.sub_crop = '双季稻'
            }
          }
      });
    },
    //判断字符串中是否含有非法字符
    hasLetter(str) {
      var flag = true;
      var len = str.length;
      for (var i in str) {
          var item = str[i];
          if(item==','||item==='，'||item=='-'||item=='{'||item=='}'||item=='.'){
            flag = true;
          };
          var asc = str.charCodeAt(i);
          if ((asc >= 65 && asc <= 90 || asc >= 97 && asc <= 122)) {
            flag = true;
          }
      }
      //判断输入的格式是否存在问题
      for(let i=0;i<10;i++){
        if(len==11+i*12){
          flag = false;
        }
      }
      return flag;
   },
   //新增联系人栏
   addPeople(){
     this.form.people.push({'name':'','phone':''});
   },
   delPeople(val){
     if(this.form.people.length>1){
       this.form.people.pop();
       this.$message({message:'删除成功',type:'warning'});
     }else{
       this.$message({message:'至少存在一个联系人',type:'warning'});
     }
   },
   //人员名单导出为excel
   exportExcel () {
     this.$confirm("确定导出为excel").then(()=>{
        /* generate workbook object from table */
        let wb = XLSX.utils.table_to_book(document.querySelector('#excel'));
        /* get binary string as output */
        let wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' });
        try {
            FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), '人员名单.xlsx');
        } catch (e)
        {
            // if (typeof console !== 'undefined')
                //console.log(e, wbout)
        }
        return wbout;
     }).catch(()=>{
     })
      
 },


    
  }
};
</script>

<style>
</style>