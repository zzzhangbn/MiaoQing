<template>
    <div class="login-wrap">
        <div style="width:35%;margin:0 auto;">
            <h3 class="ms-title">用户注册</h3>
            <el-card>
                <el-form :model="registerForm" :rules="registerRules" label-position="left" label-width="100px">
                    <el-form-item label="姓名" prop="name">
                        <el-input v-model="registerForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="registerForm.password"></el-input>
                    </el-form-item>
                    <el-form-item label="密码确认" prop="newPwd">
                        <el-input type="password" v-model="registerForm.newPwd"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                        <el-input v-model="registerForm.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="用户类型" prop="access">
                        <el-select v-model="registerForm.access" placeholder="请选择人员类型">
                            <el-option label="平台管理员" value="1"></el-option>
                            <el-option label="苗情监测员" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="监测作物" prop="crop" v-if="registerForm.access=='2'">
                        <el-select v-model="registerForm.crop" placeholder="请选择监测区域" @change="changeCrop">
                            <el-option label="水稻" value="crop_01"></el-option>
                            <el-option label="小麦" value="crop_02"></el-option>
                            <el-option label="玉米" value="crop_03"></el-option>
                            <el-option label="油菜" value="crop_04"></el-option>
                            <el-option label="大豆" value="crop_05"></el-option>
                            <el-option label="棉花" value="crop_06"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="二级作物分类" prop="subCropList" v-if="registerForm.access=='2'&&subCropList.length!==0">
                        <el-checkbox-group v-model="registerForm.subCropList">
                            <el-checkbox :label="item.code" name="type" v-for="(item,index) in subCropList" :key="index">{{item.name}}</el-checkbox>
                        </el-checkbox-group>
                        
                    </el-form-item>
                    <el-form-item label="监测地块" prop="plot" v-if="registerForm.access=='2'">
                        <el-select v-model="registerForm.plot" placeholder="请选择监测区域">
                            <el-option label="沿江地区" value="1"></el-option>
                            <el-option label="沿淮地区" value="2"></el-option>
                            <el-option label="江淮地区" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <el-button @click="register" style="margin-left:100px">立即创建</el-button>
            </el-card>
            

        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('请再次输入密码'));
            } else if (value !== this.registerForm.password) {
            callback(new Error('两次输入密码不一致!'));
            } else {
            callback();
            }
        };
        return {
            //注册表单数据
            registerForm:{
                name:'',
                password:'',
                newPwd:'',
                phone:'',
                crop:'',
                subCropList:[],
                plot:'',
                access:''
            },
            //注册表单规则
            registerRules:{
                name:[
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { min: 1, max: 8, message: '长度在 1 到 8 个字符', trigger: 'blur' }
                ],
                password:[
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' } 
                ],
                newPwd:[
                    {validator:validatePass2,trigger:'blur'}
                ],
                phone:[
                    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                ]

            },
            //二级作物类别
            subCropList:[]
        }
    },
    methods: {
        //用户的注册功能
        register(){
            this.registerForm.subCropList.forEach((item,index) => {
                item = this.crop+(index+1);
                
            });
            this.registerForm.crop = this.registerForm.crop+','+this.registerForm.subCropList.toString();
            this.registerForm.plot = this.registerForm.plot.toString();
            axios({
                url:'/register',
                method:'post',
                data:this.registerForm
            }).then(res=>{
                if(res.data.token===200){
                    this.el-alert("请耐心等待管理员审核");
                    this.$router.push({path:'/login'});
                }
            })
        },
        //根据一级作物类别选择二级作物类别
        changeCrop(value){
            axios({
                url:'/getSubCropList',
                method:'post',
                params:{cropId:value}
            }).then(res=>{
                
                this.subCropList = res.data;
            })
        }
    }
}
</script>
<style  scoped>
    .login-wrap{
        width:100%;
        height:100%;
        /*background:cornflowerblue;*/
        background:steelblue;
    }
    .ms-title{
        width:100%;
        text-align: center;
        font-size:30px;
        color: #fff;
    }
</style>
