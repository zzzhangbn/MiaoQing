<template>
  <div class="main">
    <div class="item" @click="toModel('农情分析')">
      <span>
        <font-awesome-icon icon="seedling"></font-awesome-icon>
      </span>
      <h2>农情分析</h2>
    </div>
    <div class="item" @click="toModel('病虫害资料库')">
      <span>
        <font-awesome-icon icon="heart"></font-awesome-icon>
      </span>
      <h2>病虫害资料库</h2>
    </div>
    <div class="item" @click="toModel('账户分配')">
      <span>
        <font-awesome-icon icon="hotdog" />
      </span>
      <h2>账户分配</h2>
    </div>
    <div class="item" @click="toModel('苗情监控大屏')">
      <span>
        <font-awesome-icon icon="eye" />
      </span>
      <h2>苗情监控大屏</h2>
    </div>
    <div class="item" @click="toModel('环境监控系统')">
      <span>
        <font-awesome-icon icon="egg" />
      </span>
      <h2>环境监控系统</h2>
    </div>
    <div class="item" @click="toModel('用户中心')">
      <span> 
        <font-awesome-icon icon="cloud-meatball" /> 
      </span>
      <h2>用户中心</h2>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created() {
    sessionStorage.setItem("path", "/index");
  },
  methods: {
    toModel(info) {
      switch (info) {
        case "农情分析":
          this.$router.replace("/riceAnalysis");
          sessionStorage.setItem("years",2022)
          break;
        case "账户分配":
          this.$router.replace("/accountAllocation");
          break;
        case "病虫害资料库":
          this.$router.replace("/pestDatabase");
          break;
        case "环境监控系统":
          this.$router.replace("/weathershow");
          break;
        case "苗情监控大屏":
          window.open("http://139.196.208.205:8207/");
          break;
        case "用户中心":
          this.$router.replace("/userData");
          break;
      }
    },
  },
};
</script>

<style scoped>
.main {
  width: 75%;
  height: 500px;
  margin: 50px auto;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
.main .item {
  width: 28%;
  height: 40%;
}
.main .item:hover span {
  color: orange;
  transition: 0.7s;
}
.main .item:hover {
  cursor: pointer;
  transition: 1s;
  box-shadow: 0px 0px 16px 5px #888888;
}
.main .item span {
  width: 50px;
  font-size: 40px;
  color: green;
  display: block;
  /* margin: 50px 0 0 132px; */
  margin: 50px auto;
}
.main .item h2 {
  width: 100%;
  text-align: center;
  font-size: 26px;
  margin: 10px 0 0 0;
  letter-spacing: 5px;
  font-weight: bold;
}
</style>