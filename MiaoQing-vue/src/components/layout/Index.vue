<template>
  <div class="total">
    <div class="header">
      <span class="icon">
        <font-awesome-icon icon="leaf"></font-awesome-icon>
      </span>
      <h2 >安徽省苗情监测平台</h2>
      <div class="account">
        <div class="bg bg-blur"></div>
        <div class="myAccount">
          <span style="width:70%;height:40px;line-height:40px">{{ms_username}}</span>
          <span style="border-left:2px solid #ffffff;width:35%;height:40px;line-height:40px;text-align:center;cursor:pointer" @click="loginOut">退出</span>
        </div>
      </div>
    </div>
    <div class="navs">
      <el-menu :default-active="activeIndex" active-text-color="orange" :router="true" text-color="#fff" background-color="green" mode="horizontal">
          <el-menu-item index="/index">首页</el-menu-item>
          <el-submenu index="/riceAnalysis">
              <template slot="title">农情分析</template>
                  <el-submenu index="2-4" id="nav_item">
                    <template slot="title" style="font-size:18px !important;">水稻</template>
                    <el-menu-item index="/riceAnalysis" style="font-size:18px;">单季稻</el-menu-item>
                    <el-menu-item index="/doubleRiceAnalysis" style="font-size:18px;">双季稻</el-menu-item>
                  </el-submenu>
          </el-submenu>
          <el-menu-item index="/riceHome">苗情数据</el-menu-item>
          <el-menu-item index="/researchReport">研究报告</el-menu-item>
          <el-menu-item index="" @click="toBigScreen">苗情监控大屏</el-menu-item>
          <el-menu-item index="/weathershow">环境监控系统</el-menu-item>
          <el-menu-item index="7">决策支持系统</el-menu-item>
      </el-menu>
    </div>
    <router-view></router-view>
    <div class="footer">
      <div>© 安徽省农业技术推广总站 版权所有</div>
      <div class="box1">技术支持单位：<a href="http://elab.ulenp.com/aau/" target="_blank">安徽省农业大学-信息与计算机学院</a></div>
      <div>地址：合肥市蜀山区长江西路130号安徽农业大学经计楼信息与计算机学院 邮编:230036</div>
      <!-- <div class="logo"></div> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex:sessionStorage.getItem('activeIndex')==null?sessionStorage.getItem('activeIndex'):'/index',
      ms_username:sessionStorage.getItem("ms_username"),
    };
  },
  methods: {
    loginOut(){
      localStorage.removeItem("ms_username");
      sessionStorage.removeItem("ms_username");
      sessionStorage.removeItem("checkedCrop");
      this.$router.push({path:'/login'});
    },
    
    getAllAuditData(){
      axios({
          url: '/getAllAuditData',
          method:'get',
       }).then(res=>{   
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getAllAuditData2(){
      axios({
          url: '/getAllAuditData2',
          method:'get',
       }).then(res=>{   
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getAllData(){
      this.getAllAuditData();
      this.getAllAuditData2();
    },
    toBigScreen(){
      // window.location.href="http://139.196.208.205:8207";   
      window.open('http://139.196.208.205:8207');
    }
  },
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
.total{
  width: 100%;
  height: auto;
}
.total .header {
  width: 100%;
  height: 120px;
  /* margin: 0 auto; */
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: none !important;
  position: relative;
}
.header .account {
  width:25%;
  height: 100%;
  position: absolute !important;
  top:0;
  right: 0;
}
.bg {
  width: 100%;
  height: 100%;
  background-image: url("../../assets/img/2.jpg");
  background-size: cover;
}
.bg-blur {
  float: left;
  width: 100%;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  -webkit-filter: blur(1px);
  -moz-filter: blur(1px);
  -o-filter: blur(1px);
  -ms-filter: blur(1px);
  filter: blur(1.2px);
}
.account .myAccount {
  width: 80%;
  height: 30%;
  line-height: 30%;
  color: orange;
  font-size: 18px;
  font-weight: 700;
  position: absolute;
  top: 40%;
  left: 5%;
  display: flex;
  justify-content: space-between;
}
.navs {
  width: 100%;
  height: 80px;
  background: green;
}
.navs .el-menu {
  width: 75%;
  height: 100%;
  line-height: 100%;
  padding-top: 0;
  margin: 0 auto;
}
.navs .el-menu-item{
  width: 140px;
  height: 80px;
  text-align: center;
  line-height: 80px;
  font-size: 20px;
  font-weight: 700;
}
.navs .el-submenu  {
  width: 130px;
  height: 80px;
  text-align: center;
  line-height: 80px ;
  font-size: 18px;
}
.el-submenu__title{
    width: 140px;
    height: 80px !important;
    text-align: center !important;
    line-height: 80px !important;
    font-size: 18px !important;
    font-weight: 700;
}
.navs .el-menu--collapse .el-menu .el-submenu, .el-menu--popup{
  min-width: 140px;
}
#nav_item{
  height: 36px !important;
  line-height: 36px !important;
  text-align: center;
}
.icon {
  color: green;
  font-size: 32px;
}
.header h2 {
  font-size: 36px;
  font-weight: bold;
  letter-spacing: 2px;
  margin-left:20px;
}
.footer {
  width: 100%;
  height: 250px;
  background: #343a40 ;
  color: white;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
}
.footer div{
  font-size: 16px;
  margin-top:20px ;
}
.footer .logo{
  width: 30%;
  height: 80%;
  border: 1px solid gray;
  position: absolute;
  top: 0;
  left: 0;
}
.footer a{
  font-size: 16px;
  text-decoration: none;
  color: white;
}
</style>
