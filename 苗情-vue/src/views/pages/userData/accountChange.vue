<template>
    <div class="all">
        <el-card>
        <el-breadcrumb style="font-size:18px;margin:20px 0 20px 0px;">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>账号管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-button  type="success"  @click="accountIN=true" style="margin:0px 0 20px 0px; float:right;">绑定账号</el-button>
        <el-table 
            :data="myAccount.filter(data => !search || data.account.toLowerCase().includes(search.toLowerCase()))" 
            border  
            height="400" 
            id="excel">
        <el-table-column
            label="序号"
            type="index"
            align="center"
            width="200px">
        </el-table-column>
        <el-table-column
            label="管理账号"
            prop="account"
            align="center">
        </el-table-column>
        <el-table-column
            label="绑定时间"
            prop="time"
            align="center">
        </el-table-column>
        <el-table-column 
            align="center"
            >
            <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              placeholder="输入关键字搜索"/>
          </template>
            <template slot-scope="scope">
            <el-button
                size="mini"
                @click="loginQuick(scope.row)">登录绑定账号</el-button>
            <el-button
                size="mini"
                @click="deleteChoice(scope.row)">解除绑定</el-button>
          </template>
        </el-table-column>
        </el-table>
        <el-dialog
            :visible.sync="accountIN"
            width="500px"
            center>
            <p style="width:100%;font-size:20px;text-align: center;margin:0 0 20px 0;">账户信息</p>
            <el-form :model="accountInFrom">
                <el-form-item prop="accountIn" label="绑定账号">
                    <el-input v-model="accountInFrom.account"></el-input>
                </el-form-item>
                <el-form-item prop="passwordIn" label="绑定账号密码">
                    <el-input  type="password" v-model="accountInFrom.password"></el-input>
                </el-form-item>
                <el-button @click="accountInFromClear" style="margin:20px 0 0 290px;">取 消</el-button>
                <el-button type="primary" @click="loginIn">确 定</el-button> 
            </el-form>
        </el-dialog>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios'
import FileSaver from 'file-saver'
import XLSX from 'xlsx'
export default {
    data() {
        return {
            accountIN:false,
            search:'',
            myAccount: [],
            activeIndex:sessionStorage.getItem('activeIndex')==null?sessionStorage.getItem('activeIndex'):'/index',
            username:sessionStorage.getItem("ms_username"),
            loginForm: {
                account: '',
                password: '',
                time:'',
            },
            accountInFrom:{
                account:'',
                password:'',
            },
        }
    },
    mounted(){
        this.getAccount();
    },
    methods: {
        //获取信息
        getAccount() {
            axios({
                url:'/accountChange',
                method: 'post',
                params:{
                    name: this.username,
                }
            }).then((res)=>{
                this.myAccount=res.data.account;
            })
        },
        // 账号解除绑定
        deleteChoice(row){
            this.$confirm("确定解除绑定账号:"+row.account+"?").then(()=>{
                this.deleterw(row);
            })
        },
        deleterw(row){
            const name=row.name;
            const account=row.account;
            axios({
                url:'deleteAccount',
                method:'post',
                params:{
                    name:name,
                    account:account,
                },
            }).then((res)=>{
                this.$message.info(res.data.msg);
                this.getAccount();
            })
        },
        // 登录绑定账号
        loginQuick(row){
            this.loginForm.account=row.account;
            localStorage.setItem('ms_username',this.loginForm.account);
            axios({
                url:'/loginQuick',
                method:'post',
                data:this.loginForm,
            }).then((res)=>{
                    this.setCookie(this.loginForm.account, 0, 1);
                    localStorage.setItem('crop',res.data.person.crop);
                    localStorage.setItem('area',res.data.person.area);
                    sessionStorage.setItem("ms_username",res.data.person.account);
                    sessionStorage.setItem("access",res.data.person.access);
                    var cropCategory = sessionStorage.getItem("checkedCrop");
                    var index = res.data.person.crop.indexOf(cropCategory);
                    if(res.data.person.access==="2"){
                        //苗情监测员
                        this.$router.replace('/main');
                    }else if(res.data.person.access==="1"){
                        this.$router.replace('/');
                    }
            })
        },
        //添加绑定账号
        loginIn(){
            axios({
                url:'/loginIn',
                method:'post',
                params:{
                    name:this.username,
                },
                data:this.accountInFrom,
            }).then((res)=>{
                switch (res.data.token) {
                    case 404:
                        this.$message.error("账号有误，请重新输入");
                        break;
                    case 200:
                        this.$message.success("绑定成功");
                        this.accountInFromClear();
                        this.getAccount();
                        break;
                    case 401:
                    this.$message.info("账号已存在，请勿重复绑定");
                        break;
                    case 500:
                    this.$message.error("账号密码错误,请重新输入");
                        break;
                }
            })
        },
        //绑定账号输入栏清空
        accountInFromClear(){
            this.accountInFrom.account='';
            this.accountInFrom.password='';
            this.accountIN=false;
        }
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