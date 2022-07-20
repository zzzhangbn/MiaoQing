<template>
  <div id="app">
    <!--<img alt="Vue logo" src="./assets/logo.png">-->
    <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <!--<home></home>-->
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import Home from './components/layout/Home.vue'
export default {
  name: 'app',
  components: {
    // HelloWorld
     Home
  },
  provide(){
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      isRouterAlive:true
    }
  },
  mounted:function(){
    // window.addEventListener('beforeunload',e=>{
    //   var storage = window.localStorage;
    //   storage.clear();
    // })
  },
  methods:{
    reload(){
      this.isRouterAlive = false;
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    }
  }
}
</script>

<style>
#app{
  width:100%;
  height:100%;
  overflow: auto;
}
</style>
