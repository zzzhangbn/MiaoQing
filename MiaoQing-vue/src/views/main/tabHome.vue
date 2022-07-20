<template>
    <div>
      <!--        //获取表编号-->

        <!-- <el-button @click="back" style="margin-left: 86%;margin-top: 1%;display: inline-block;">返回</el-button> -->

        <!-- 🚀 component 标签已经代表当前这个是一个组件 🚀 -->
    <!-- 🚀 只需要加载computed里计算出来的组件即可 🚀 -->
        <component v-bind:is="currentBizComponent"></component>
    </div>
</template>

<script>
    import axios from 'axios'
    import table1 from './table1.vue'
    // import Child from '../home/index' //导入其他组件之后，需要在components中注册

export default{ 
  // components: {Child},
  components: { table1},
    data(){
        return{
            tableName:'',//表名
            temList:[],
            headerList:[],//存储表头
            secondHeaderList:[],//存储二级表头
        }
    },
    created(){
        this.tableName = this.$route.query.name;
        //先写死
        this.tableName = 'crop_01_01_table_01_01';
        //console.log(this.tableName);
        this.getTableHeader();

    },
    methods:{
        getTableHeader(){
            //console.log();
            axios({
                url:'/getTableHeader',
                method:'post',
                params:{
                    headerName:this.tableName
                }
            }).then(res=>{
                this.temList = res.data;
            }).catch(err=>{
                return this.$message.error("获取表头数据失败！");
            });
        },

        back(){
        this.$router.go(-1);//返回上一层
    },

    },
    mounted(){
        this.tableName = this.$route.query.name;
        //先写死
        this.tableName = 'crop_01_01_table_01_01';
        //console.log(this.tableName);
    },
    computed: {
        // 业务类型
        condition:function(){
          // 当前页面数据 bizDoc
          if(this.tableName == 'crop_01_01_table_01_01'){
            return table1;
          }
        },
      // 🚀 当前应该加载的组件
        currentBizComponent: function () {
          return this.condition
        }
      }

}

</script>

<style>
  .component{
    height: 100%;
  }
</style>