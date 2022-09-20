import Vue from 'vue'
import App from './App.vue'
import ElementUI from  'element-ui';
import router from './router/router.js'
import store from './store/index.js'
import 'element-ui/lib/theme-chalk/index.css';
import '../public/css/main.css'
import echarts from 'echarts'
Vue.config.productionTip = false;


//axios
import axios from 'axios'
import VueAxios from 'vue-axios'
axios.defaults.baseURL = 'http://localhost:8899/'
//axios.defaults.baseURL='http://pv.sohu.com'
// axios.defaults.baseURL = 'http://139.196.208.205:8889/' 
Vue.use(VueAxios, axios)

//vuex
import Vuex from 'vuex'
Vue.use(Vuex)

//font
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
Vue.component('font-awesome-icon', FontAwesomeIcon);
import {fas} from '@fortawesome/free-solid-svg-icons'
library.add(fas)


//引入地区选择器
import VDistpicker from 'v-distpicker'
Vue.component('v-distpicker', VDistpicker);


//打印
import Print from '@/plugs/print';
Vue.use(Print); //注册

// //富文本编辑器
// import '../public/static/UE/ueditor.config.js'
// import '../public/static/UE/ueditor.all.min.js'
// import '../public/static/UE/lang/zh-cn/zh-cn.js'
// // import '../public/static/UE/ueditor.parse.js'
// //可引入，也可不引入
// import '../public/static/UE/themes/default/css/ueditor.css'

Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;

var items =[];

router.beforeEach((to,from,next)=>{
    let username = sessionStorage.getItem('ms_username');
    let access = sessionStorage.getItem("access");
    if (to.matched.some(record => record.meta.requiresAuth)) {
      //用户未登陆时  
      if(username===null){
          next({path:'/login'});
          return;
      }else{
        next();
      }
      //console.log(access);
      if(to.meta.title!==access){
        next({path:'/401'});
      }else{
        next();
      }
    } else {
      next() // 确保一定要调用 next()
    }

});


export default {
    items
}

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

//Cookie
Vue.prototype.setCookie = function(c_name,value,expiredays) {
  var exdate=new Date()
  exdate.setDate(exdate.getDate()+expiredays)
  document.cookie=c_name+ "=" +escape(value)+
    ((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
};

Vue.prototype.getCookie=function(c_name) {
  if (document.cookie.length>0)
  {
    var  c_start=document.cookie.indexOf(c_name + "=")
    if (c_start!=-1)
    {
      c_start=c_start + c_name.length+1
     var c_end=document.cookie.indexOf(";",c_start)
      if (c_end==-1) c_end=document.cookie.length
      return unescape(document.cookie.substring(c_start,c_end))
    }
  }
  return ""
};