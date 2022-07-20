<template>
    <div class="login-wrap">
        <div class="ms-title">账户密码修改</div>
        <div class="ms-login">
            <el-form :model="loginForm" :rules="rules" label-width="100px" class="demo-ruleForm" label-position="left">
                <el-form-item prop="account" label="用户账号">
                    <el-input v-model="loginForm.account" placeholder="account"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="原有密码">
                    <el-input type="password" placeholder="password" v-model="loginForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="新密码">
                    <el-input type="password" placeholder="newPassword" v-model="loginForm.newPassword" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="changePwd">确定修改</el-button>
                </div>
            </el-form>
            <!-- <el-button type="mini" style="float:right;margin-top:20px;" @click="dialogFormVisible=true">忘记密码</el-button> -->
        </div>

    </div>
</template>
<script>
import {Message} from 'element-ui'
// import axios from '@/config/http.js'
import axios from 'axios'
export default {
    data: function(){
        return {
            //登录表单
            loginForm: {
                account: '',
                password: '',
                newPassword:''
            },
            //登录表单的规则
            rules: {
                account: [
                    { required: true, message: '请输入用户账号', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ],
            },
            dialogFormVisible:false,
        }
    },
    methods: {
        //验证用户账号是否为数字格式
        isNumber(value) {//验证是否为数字
            var patrn = /^(-)?\d+(\.\d+)?$/;
            if (patrn.exec(value) == null || value == "") {
                return false
            } else {
                return true
            }
        },
        changePwd(){
            axios({
                url:'/changePwd',
                method:'post',
                data:this.loginForm
            }).then(res=>{
                var data = res.data;
                if(data.status=='200'){
                    this.$message({message:'密码修改完成，请重新登录！',type:'success'});
                    sessionStorage.removeItem('ms_username');
                    this.$router.replace('/login');
                }else{
                    this.$message({message:'你输入的账户或者密码有误，请重新输入！',type:'warning'});  
                }
            })
        }
    }
}
</script>
<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
        /*background:cornflowerblue;*/
        background:steelblue;
    }
    .ms-title{
        position: absolute;
        top:50%;
        width:100%;
        margin-top: -230px;
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:350px;
        height:250px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
        border:1px solid #ccc;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
    .remember{
        margin:3px 0 10px 0;
    }
</style>
