<template>
        <div>
            <div class="begin">
                <el-card class="box-card" style="border: 1px solid #dbdbdb"  >
                    <div class="item" style="text-align: center">
                        <h1>
                            苗情数据录入主界面
                        </h1>
                        <p style="margin-top: 20px">
                            欢迎来到苗情数据录入主界面
                        </p>
                    </div>
                </el-card>
            </div>
            <div class="content">
                <el-row style="margin-top: 0px">
                    <el-col :span="1" offset=3 >
                        <div  class="vertical-text">
                            模块·功能
                        </div>
                    </el-col>
                    <el-col :span="4" offset=2 style="margin-top: 10px">
                        <el-card :body-style="{ padding: '0px' }" shadow="hover" >
                            <img alt="100x200" style="height: 200px;width: 100%"  src="../../assets/crop.jpg" @click="go"/>
                            <div style="padding: 14px;border: 1px solid #63807d;" @click="go">
                                <span class="fontstyle">数据录入</span>
                                <div class="bottom clearfix">
                                    <time class="time">负责苗情数据的录入</time>
                                    <!--<el-button type="text" class="button">查看详情</el-button>-->
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    
                    <el-col :span="4" offset=5 style="margin-top: 10px">
                        <el-card :body-style="{ padding: '0px' }">
                            <router-link  to="/traceBackData" class="router-link-active"><img alt="100x200" style="height: 200px;width: 100%"  src="../../assets/crop2.jpg" /></router-link>
                            <div style="padding: 14px;border: 1px solid #ffc303;">
                                <el-badge :value="unProcessedRecord" class="item">
                                <router-link  to="/traceBackData" class="router-link-active"><span class="fontstyle"> 数据修正</span></router-link>
                                <div class="bottom clearfix">
                                    <time class="time">修改填报有误的数据</time>
                                    <!--<el-button type="text" class="button">查看详情</el-button>-->
                                </div>
                                </el-badge>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </div>
</template>
<script type="text/javascript">
import axios from 'axios'
    export default {
        data() {
            return {
                activeName: 'first',
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                visible: false,
                unProcessedRecord:0,
                flag:'',
                area:''
            };
        },
        methods: {
            handleClick(tab, event) {
                //console.log(tab, event);
            },
            go(){
                if (this.area == 'crop_01_01'){
                    //console.log(this.area);
                    this.$router.push('/tablemain');
                } else{
                    this.$router.push('/tableTwoMain')
                }

            },

            //获取退还所有数据
            getDataBackListInfo(people){
                axios({
                    url:'/getDataBackListInfo',
                    method:'post',
                    params:{
                        people:people,
                        // page:index,
                        // limit:'10',
                        // state:0
                    }
            }).then(res=>{
                this.unProcessedRecord = res.data.length;
                
            }).catch(err=>{
                return this.$message.error("退还数据获取失败！");
            })
            },
            //获取县
            getAreaInfo(people){
                //console.log(people)
                axios({
                    url:'/getPerson',
                    method:'get',
                    params:{
                        people:people,
                    }
                }).then(res=>{
                        //console.log(res.data)
                        this.area = res.data[0].sub_crop;
                        //console.log(this.area )
                    }
                ).catch(err=>{
                    return this.$message.error("获取信息失败！");
                });
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            this.getDataBackListInfo(people);
            this.getAreaInfo(people);
        },
    };
</script>
<style>
    .time {
        font-size: 13px;
        color: #999;
    }
    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }
    .button {
        padding: 0;
        float: right;
    }
    .image {
        width: 100%;
        display: block;
    }
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 18px;
        opacity: 0.75;
        line-height: 300px;
        margin: 0;
    }
    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
    .el-card h1{
        font-family: "隶书";
        font-size: 50px;
    }
    .el-card .fontstyle{
        font-family: "隶书";
        font-size: 32px;
    }
    .el-card .fontstyle:hover{
        text-decoration: none;
    }
    .el-card img{
        opacity:0.8;
    }
    .vertical-text{
        -webkit-writing-mode: vertical-rl;
        writing-mode: vertical-rl;
        font-size: 40px;
        font-family: 微软雅黑;
        color: #333333;
        margin-top: 90px;
    }
    p{
        font-size: 20px;
    }
    .el-card img:hover{
        transform:scale(1.02,1.02)
    }
    .el-header{
        border-bottom: 1px solid #dbdbdb;
        position: relative;
        backgroundImage: "";
        backgroundRepeat: "no-repeat",
    }
    .el-main{
        /*height: calc(100vh - 60px);*/
        overflow-y: auto;
    }
    .content{
        width: 97%;
        margin: 30px auto;
        background: white;
    ;
    }
    .router-link-active {
        text-decoration: none;
    }
    .router-link-active:hover {
        text-decoration: none;
    }
    a{
        text-decoration: none;
    }
    .box-card {
        background: rgba(63, 117, 1, 0.6);
    }
</style>
