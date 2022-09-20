<template>
  <div>
    <div class="header">
      <span class="icon">
        <font-awesome-icon icon="leaf"></font-awesome-icon>
      </span>
      <h2>安徽省苗情监测平台</h2>
    </div>
    <div class="main">
      <el-dialog :visible.sync="dialogFormVisible" width="500px">
        <p style="width:100%;font-size:20px;text-align: center;margin:0 0 20px 0;">用户登录</p>
        <el-form :model="loginForm" :rules="rules" label-width="90px" class="demo-ruleForm" label-position="left">
            <el-form-item prop="account" label="用户账号">
                <el-input v-model.trim="loginForm.account" placeholder="请输入账户" width="150" @blur="addItem()"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="账号密码">
                <el-input :type="flagType" placeholder="请输入密码" width="150" v-model.trim="loginForm.password" autocomplete="off" @keyup.enter.native="submitForm('ruleForm')">
                  <i slot="suffix" :class="[this.flag?'el-icon-circle-check':'el-icon-circle-close']" @click="getFlag()" />
                </el-input>
            </el-form-item>
            <el-form-item v-if="isshow==true" prop="ValidCode" label="验证码" >
                <div style="display: flex">
                  <el-input v-model.trim="loginForm.ValidCode" width="150" placeholder="请输入验证码" ></el-input>
                  <ValidCode @input="createValidCode" />
                </div>
            </el-form-item>
            <!-- <el-form-item label="作物子类别">
                <el-radio-group v-model="loginForm.subCrop">
                    <el-radio label="单季稻"></el-radio>
                    <el-radio label="双季稻"></el-radio>
                </el-radio-group>
            </el-form-item> -->
            <div class="login-btn" style="width:100%">
                <el-button type="primary" @click="login" style="width:60px;height:35px;margin:20px 0 0 200px;">登录</el-button>
            </div>
            <div>
              <el-button type="text" @click="changeMessge">短信验证码登录</el-button>
            </div>            
          </el-form>
      </el-dialog>
      <el-dialog :visible.sync="chooseWay" width="500px">
        <p style="width:100%;font-size:20px;text-align: center;margin:0 0 20px 0;">用户短信验证码登录</p>
        <el-form :model="phoneLogin" :rules="phoneRules"  ref="phoneLogin" label-width="90px" class="demo-phoneRuleForm" label-position="left">
          <el-form-item prop="phone" label="手机号码">
                <el-input v-model.trim="phoneLogin.phone" placeholder="输入手机号码" width="150"></el-input>
          </el-form-item>
          <el-form-item  prop="code" label="验证码">
              <el-input v-model="phoneLogin.code" placeholder="请输入验证码">
              <el-button v-if="sending" slot="append" @click="getCode">获取验证码</el-button>
              <el-button v-else slot="append" :disabled="disabled">{{second}}秒后获取</el-button>
              </el-input>
          </el-form-item>
          <div class="phoneLogin-btn" style="width:100%">
                <el-button type="primary" @click="pLogin" style="width:60px; height:35px; margin:20px 0 0 200px;">登录</el-button>
          </div>
        </el-form>
      </el-dialog>
      <div class="item" @click="moveToLogin('crop_01')">
        <span>
          <font-awesome-icon icon="seedling"></font-awesome-icon>
        </span>
        <h2>水稻模块</h2>
      </div>
      <div class="item" @click="moveToLogin('crop_02')">
        <span>
          <font-awesome-icon icon="spa"></font-awesome-icon>
        </span>
        <h2>小麦模块</h2>
      </div>
      <div class="item" @click="moveToLogin('crop_03')">
        <span>
          <font-awesome-icon icon="hotdog" />
        </span>
        <h2>玉米模块</h2>
      </div>
      <div class="item" @click="moveToLogin('crop_04')">
        <span>
          <font-awesome-icon icon="cloud-meatball" />
        </span>
        <h2>油菜模块</h2>
      </div>
      <div class="item" @click="moveToLogin('crop_05')">
        <span>
          <font-awesome-icon icon="egg" />
        </span>
        <h2>大豆模块</h2>
      </div>
      <div class="item" @click="moveToLogin('crop_06')">
        <span>
          <font-awesome-icon icon="cloud-meatball" />
        </span>
        <h2>棉花模块</h2>
      </div>
    </div>
  </div>
</template>

<script>
import {Message} from 'element-ui'
import $ from 'jquery'
import  axios  from 'axios'
import  ValidCode from "@/components/ValidCode";
export default {
  components: {
    ValidCode
  },
  data() {
    const validate_mobile = (rule, value, callback) => {
        let reg = /^[1]([3-9])[0-9]{9}$/;
        if (!value) {
          callback("请输入手机号");
        } else if (!reg.test(value)) {
          callback("请输入正确的手机号");
        } else {
          callback();
        }
      };
    return {
      isshow: false,
      flag: false,
      flagType:'password',
      chooseWay:false,
      dialogFormVisible:false,
      validCode: '',
      //returnCitySN: '',
      //登录表单
      loginForm: {
          account: '',
          password: '',
          ValidCode: '',
          cip: '',
          subCrop:[]
      },
      //短信验证码表单
      phoneLogin: {
          phone: '',
          code:'',
      },
      sending: true,    //是否发送验证码
      disabled: false,  //是否禁发验证码
      second:59,        //倒计时间
      //登录表单的规则
      rules: {
          account: [
              { required: true, message: '请输入用户账号', trigger: 'blur' },
          ],
          password: [
              { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          ValidCode: [
              { required: true, message: '请输入验证码', trigger: 'blur' }
          ],
      },
      //短信验证码表单规则
      phoneRules: {
          phone: [
              { required: true, validator: validate_mobile, trigger:'blur'}
          ],
          code: [
              { required: true, message: '请输入短信验证码', trigger: 'blur'}
          ],
      },
    }
  },
  mounted(){
    this.getIp();
  },

  methods: {
    changeMessge(){
      this.dialogFormVisible=false;
      this.chooseWay=true;
    },
    //获取ip
    //http://api.qingyunke.com/api.php?key=free&appid=0&msg=你好
    //http://pv.sohu.com/cityjson?ie=utf-8
    getIp(){
    var that = this;
    $.getScript('http://pv.sohu.com/cityjson?ie=utf-8')
		.done(function (script, textStatus,jqxhr) { 
        that.loginForm.cip=returnCitySN.cip;
        //获得returnCitySN;
        //that.$message.success("初始化成功");
        //console.log(script);
        console.log('访问成功');
		})
		.fail(function () {
        //that.$message.error("初始化失败");
		    console.log('访问错误');
		});
    },
    addItem(){
      if (Number(this.getCookie(this.loginForm.account)) >= 3 ){
        this.isshow=true;
      } else {
        this.isshow=false;
      }
    },
    //选择类别跳转到登录页面
    async moveToLogin(msg) {
      //将用户选择的类别存到vuex中
      sessionStorage.setItem("checkedCrop",msg);
      this.dialogFormVisible = true;
      // this.$router.push({ path: "/login" });
    },
    //
    //验证用户账号是否为数字格式
    isNumber(value) {//验证是否为数字
        var patrn = /^(-)?\d+(\.\d+)?$/;
        if (patrn.exec(value) == null || value == "") {
            return false
        } else {
            return true
        }
    },
    createValidCode(data) {
      this.validCode = data
    },
    getFlag(){
      this.flag = !this.flag
      this.flagType = this.flag ? 'text' : 'password' //text为显示密码；password为隐藏密码
    },
    //倒计时
    timeDown() {
      let result = setInterval(()=>{
      --this.second;
      if(this.second < 0) {
        clearInterval(result);
        this.sending  = true;
        this.disabled = false;
        this.second = 59;
        }   
      }, 1000);
    },
    
    //获取验证码
    getCode(){
      if(!this.sending){
        return;
      }
      if (this.phoneLogin.phone != null && this.phoneLogin.phone != '') {
          axios({
            url: '/phonelogin',
            method: 'post',
            params: {
              phone: this.phoneLogin.phone,
            }
          }).then((res)=>{
            //api接口封装请求
            if (res.data.token== 500){
              this.sending  = false;
              this.disabled = true;
              this.timeDown();
            }else {
              this.$message.error(res.data.msg);
            }
            
          })
        } else{
          this.$message.error("请输入正确的手机号")
        }
    },
    pLogin(){
      axios({
        url:'/pLogin',
        method:'post',
        data: this.phoneLogin
      }).then((res)=>{
        switch (res.data.token) {
                case 404:
                    this.$message({message:"请重新输入",type:'error'});
                    break;
                case 200:
                    // 账户密码正确；判断用户的类别
                    //判断用户的作物类别是否和选择的匹配
                    //获取到存在vuex中的作物类别
                    //获取用户真实的作物类别,两者进行匹配，如果匹配得到返回index，匹配不到则返回-1
                    this.setCookie(this.loginForm.account, 0, 1);
                    localStorage.setItem('crop',res.data.person.crop);
                    localStorage.setItem('area',res.data.person.area);
                    sessionStorage.setItem("ms_username",res.data.person.account);
                    sessionStorage.setItem("access",res.data.person.access);
                    var cropCategory = sessionStorage.getItem("checkedCrop");
                    var index = res.data.person.crop.indexOf(cropCategory);
                    if(index===-1){
                        this.$message({message:"你选择的作物类别有误，请重新选择！",type:'warning'});
                        this.dialogFormVisible = false;
                        break;
                    }
                    if(res.data.person.access==="2"){
                        //苗情监测员
                        this.$router.replace('/main');
                    }else if(res.data.person.access==="1"){
                        this.$router.replace('/');
                    }
                    break;
                }
                
      })
    },
    login() {
        //this.getIp();
        //console.log(this.loginForm.cip);
        //判断用户输入的是否为数字
        if(!this.isNumber(this.loginForm.account)){
            this.$message('你输入的账号格式不对，请重新输入！');
            return;
        }
        if (this.isshow==true){
          if (!this.loginForm.ValidCode) {
            this.$message.error("请填写验证码");
            return;
          }
          if(this.loginForm.ValidCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误");
            return;
          }
        }
        //将用户数据存到缓存里
        localStorage.setItem('ms_username',this.loginForm.account);
        axios({
            url: '/login',
            method: 'post',
            data: this.loginForm
        }).then((res) => {
            //判断返回的token，
            switch (res.data.token) {
                case 404:
                    //账户不存在，提醒用户
                    this.$message({message:"你输入的账户不存在，请重新输入",type:'error'});
                    break;
                case 500:
                    //账户存在，但密码错误,且累计错误小于3次
                    this.$message({message:"你输入的账户或密码错误，请重新输入",type:'error'});
                    this.setCookie(this.loginForm.account, Number(this.getCookie(this.loginForm.account)) + 1, 1);
                    if (Number(this.getCookie(this.loginForm.account)) == 3 ){
                        this.isshow=true;
                    } 
                    break;
                case 501:
                    //账号已被封禁
                    this.$message({message:"该账号密码错误次数过多，已封号。请联系管理员解封",type:'error'});
                    break;
                case 200:
                    // 账户密码正确；判断用户的类别
                    //判断用户的作物类别是否和选择的匹配
                    //获取到存在vuex中的作物类别
                    //获取用户真实的作物类别,两者进行匹配，如果匹配得到返回index，匹配不到则返回-1
                    this.setCookie(this.loginForm.account, 0, 1);
                    localStorage.setItem('crop',res.data.person.crop);
                    localStorage.setItem('area',res.data.person.area);
                    sessionStorage.setItem("ms_username",res.data.person.account);
                    sessionStorage.setItem("access",res.data.person.access);
                    var cropCategory = sessionStorage.getItem("checkedCrop");
                    var index = res.data.person.crop.indexOf(cropCategory);
                    if(index===-1){
                        this.$message({message:"你选择的作物类别有误，请重新选择！",type:'warning'});
                        this.dialogFormVisible = false;
                        break;
                    }
                    if(res.data.person.access==="2"){
                        //苗情监测员
                        this.$router.replace('/main');
                    }else if(res.data.person.access==="1"){
                        this.$router.replace('/');
                    }
                    break;
            }
            
        })
    },
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}

.header {
  width: 40%;
  height: 80px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow:none;
}
.icon {
  color: green;
  font-size: 32px;
}
.header h2 {
  font-size: 32px;
  font-weight: bold;
  letter-spacing: 5px;
}
.main {
  width: 70%;
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
  width: 40px;
  font-size: 40px;
  color: green;
  display: block;
  /* margin: 50px 0 0 115px; */
  margin: 20px auto;
}
.main .item h2 {
  width: 100%;
  text-align: center;
  font-size: 26px;
  margin: 20px 0 0 0;
  letter-spacing: 5px;
  font-weight: bold;
  
}
.el-dialog {
  height: 400px !important;
  overflow: auto;
}
</style>
