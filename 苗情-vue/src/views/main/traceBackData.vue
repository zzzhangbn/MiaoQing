<template>
        <div>
            <router-link to='/main'>
                <el-button type="info" plain style="margin-left: 1%;">返回</el-button>
              </router-link>
            <el-table
                :cell-style="rowStyle"
                :header-cell-style="tableHeaderColor"
                :data="tableData"
                style="width: 98%;text-align: center;margin-left: 1%;margin-top: 1%;"
                >
                <el-table-column
                prop="second_name"
                label="表名"
                style="word-break:break-all; word-wrap:break-all;"
                >
                </el-table-column>
                <el-table-column
                prop="back_time"
                label="退回时间" >
                </el-table-column>
                <el-table-column
                prop="reason"
                style="word-break:break-all; word-wrap:break-all;"
                label="退回原因">
                </el-table-column>
                <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-tooltip class="item" effect="dark" content="修改数据" placement="right-start">
                        <template v-if="scope.row.record_table != 'crop_01_01_table_01_02'">
                            <el-button  icon="el-icon-edit-outline" circle @click.native.prevent="fixData(scope.row.record_table,scope.row.record_id)"></el-button>
                        </template>
                        <template v-if="scope.row.record_table == 'crop_01_01_table_01_02'">
                            <el-button  icon="el-icon-edit-outline" circle @click.native.prevent="fixData(scope.row.record_table,scope.row.record_county)"></el-button>
                        </template>
                    </el-tooltip>
                </template>
                </el-table-column>
            </el-table>
            <div class="page-bar">
                <ul>
                <li v-if="cur>1"><a v-on:click="pageClick('last')">上一页</a></li>
                <li v-if="cur==1"><a class="banclick">上一页</a></li>
                <li v-for="index in indexs" v-bind:class="{ 'active': cur == index}">
                <a v-on:click="btnClick(index)">{{ index }}</a>
                </li>
                <li v-if="cur!=all"><a v-on:click="pageClick('next')">下一页</a></li>
                <li v-if="cur == all"><a class="banclick">下一页</a></li>
                <li><a>共<i>{{all}}</i>页</a></li>
                </ul>
            </div>

        </div>
</template>
<script>
import axios from 'axios'
import moment from 'moment'
    export default{
        data(){
            return{
                tableData:[],
                cur:0,//当前页码
                all:0,//总页数
                totalPage:0,//当前条数
                sumData:[],
            }
        },
        methods:{
            //请求数据
                //分页
                btnClick: function(data){//页码点击事件
                    // //console.log(data);
                    var list = [];
                if(data != this.cur){
                    var i = data * 5 -1;
                    i = i - 4;
                    for(var j = 0; j < 5 ; ++j){
                        if(i < this.sumData.length){
                            list.push(this.sumData[i]);
                            ++i;
                        }
                        
                    }
                    this.tableData = list;
                    this.cur = data;
                }
                },
                pageClick: function(flag){
                if(flag == 'next'){
                    this.cur = this.cur + 1;
                    var list =[];
                    var i = this.cur * 5 -1;
                    i = i - 4;
                    for(var j = 0; j < 5 ; ++j){
                        if(i < this.sumData.length){
                            list.push(this.sumData[i]);
                            ++i;
                        }
                    }
                    this.tableData = list;
                }
                if(flag == 'last'){
                    var list = [];
                    this.cur = this.cur -1;
                    var i = this.cur * 5 -1;
                    i = i - 4;
                    for(var j = 0; j < 5 ; ++j){
                        list.push(this.sumData[i]);
                        ++i;
                    }
                    this.tableData = list;
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
                    // this.tableData = res.data;
                   
                    this.all = Math.ceil(res.data.length/5);
                    this.sumData = res.data;
                    //console.log(this.sumData);
                    var list = [];
                    if(this.sumData.length <= 5){
                        this.tableData = this.sumData;
                        this.totalPage = res.data.length;
                        this.cur = 1; 
                    }
                    if(this.sumData.length > 5){
                        for(var i = 0 ; i < 5 ; ++i){
                        list.push(res.data[i]);
                    }
                     this.tableData = list;
                    this.totalPage = res.data.length;
                    this.cur = 1; 
                    }
                    
                }).catch(err=>{
                    return this.$message.error("退还数据获取失败！");
                })
            },
            //日期格式设置
            dateFormat:function(row,column){
            var date = row[column.property];
            if(date == undefined){return ''};
            return moment(date).format("YYYY-MM-DD");
            },
            //表格样式
            rowStyle(){
	            return 'text-align:center;'
	        },
            tableHeaderColor(){
                return 'background-color:#F0F8FF;font-color:#000000;font-weight:bolder;text-align:center;'
            },
            //修改数据
            fixData(name,id){
             
                var str = '/'+name;
                console.log(str);
                 //指定跳转地址
                this.$router.push({path:str,query:{column_id:id,record_table:name}});
            },

        },
        created(){
            var people = localStorage.getItem('ms_username');
            this.getDataBackListInfo(people);
        },
        computed: {
            //分页
            indexs: function(){
            var left = 1;
            var right = this.all;
            var ar = [];
            if(this.all>= 5){
            if(this.cur > 3 && this.cur < this.all-2){
            left = this.cur - 2
            right = this.cur + 2
            }else{
            if(this.cur<=3){
            left = 1
            right = 5
            }else{
            right = this.all
            left = this.all -4
            }
            }
            }
            while (left <= right){
            ar.push(left)
            left ++
            }
            return ar
            }
            }
    }
</script>
<style>
    .page-bar{
margin:40px auto;
margin-left: 40%;
}
ul,li{
margin: 0px;
padding: 0px;
}
li{
list-style: none
}
.page-bar li:first-child>a {
margin-left: 0px
}
.page-bar a{
border: 1px solid #ddd;
text-decoration: none;
position: relative;
float: left;
padding: 6px 12px;
margin-left: -1px;
line-height: 1.42857143;
color: #5D6062;
cursor: pointer;
margin-right: 20px;
}
.page-bar a:hover{
background-color: #eee;
}
.page-bar a.banclick{
cursor:not-allowed;
}
.page-bar .active a{
color: #fff;
cursor: default;
background-color: #E96463;
border-color: #E96463;
}
.page-bar i{
font-style:normal;
color: #d44950;
margin: 0px 4px;
font-size: 12px;
}
</style>