<template>
<div>
    <el-breadcrumb style="font-size:18px;margin:20px 0px 10px 0px;">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户信息</el-breadcrumb-item>
    </el-breadcrumb>
    <div style="margin:0px 0px 0px 200px">
    <div class="head-line" style="margin:20px 0px 20px 130px">
        <h2>用户信息</h2>
    </div>
    <el-avatar 
    :src="imageUrl"
    >
    </el-avatar>

    <div class="user">
        <el-form :model="userdataFrom" label-width="80px"> 
            <el-form-item label="账号">
                <el-input v-model="userdataFrom.account" class="user" style="width: 200px" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="姓名">
                <el-input v-model="userdataFrom.name" class="user" style="width: 200px" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="userdataFrom.phone" class="user" style="width: 200px" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="作物类型">
                <el-input v-model="userdataFrom.crop" class="user" style="width: 200px"  :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="上传图片">
                <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8899/UpdateImage"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload"
                    :data="{name:ms_username}">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
        </el-form>
    </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'
export default{
    data(){
        return{
            imageUrl: '',
            ms_username:sessionStorage.getItem("ms_username"),
            userdataFrom: {
                account: '',
                name: '',
                phone: '',
                crop: '',
            },
        }
    },
    mounted(){
        this.getImage(),
        this.getaccount()
    },
    methods:{
        getImage(){
            var that = this;
            axios({
                url:'/getImage',
                method: 'get',
                params: {
                    name : this.userdataFrom.account,
                }
            }).then((res)=>{
                that.imageUrl=res.data;
            })
        },
        getaccount(){
            this.userdataFrom.account=this.ms_username;
            var that = this;
            axios({
                url: '/Userdata',
                method: 'post',
                data: this.userdataFrom,
            }).then((res)=>{
                //console.log(res.data);
                that.userdataFrom.name=res.data.name;
                that.userdataFrom.phone=res.data.phone;
                switch(res.data.crop){
                    case 'crop_01':
                        that.userdataFrom.crop = '水稻';
                        break;
                    case 'crop_02':
                        that.userdataFrom.crop = '小麦';
                        break;
                    case 'crop_03':
                        that.userdataFrom.crop = '玉米';
                        break;
                    case 'crop_04':
                        that.userdataFrom.crop = '油菜';
                        break;
                    case 'crop_05':
                        that.userdataFrom.crop = '大豆';
                        break;
                    case 'crop_06':
                        that.userdataFrom.crop = '棉花';
                        break;
                }
            })
        },
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            console.log(file);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        }  
    },
};


</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
