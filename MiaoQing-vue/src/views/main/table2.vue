<template>
  <div class="demo-image__placeholder">
    <template v-if="column_id != ''&& column_id != null">
      <router-link to="/traceBackData">
        <el-button
          style="
            display: inline-block;
            float: right;
            margin-top: 1%;
            margin-right: 1.4%;
            margin-bottom: 1%;
          "
          >返回</el-button
        >
      </router-link>
    </template>
    <template v-if="column_id == '' || column_id == null">
      <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
        <el-button
          style="
            display: inline-block;
            margin-left: 10px;
            float: right;
            margin-top: 1%;
            margin-right: 1.4%;
            margin-bottom: 1%;
          "
          >返回</el-button
        >
      </router-link>
    </template>
    <el-button
      style="display: inline-block;float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上 报</el-button
    >
    <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 2.4%;margin-bottom: 1%;" @click="getFormList('0')">暂存</el-button> -->
    <div id="containner_div" style="width: 100%; height: 70%">
      <table
        id="sum_tab"
        style="
          width: 100%;
          border: 1px solid #d1d1d1;
          line-height: 40px;
          text-align: center;
        "
        border="1"
        cellspacing="0"
      >
        <tr>
          <td colspan="3" style="background-color: #6e8b3d; color: #ffffff">
            年度监测县(市、区)水稻生产主推品种调查表
          </td>
        </tr>

        <tr style="background-color: #cdbe70; color: green">
          <td style="width: 20%">县（市、区）</td>
          <td style="width: 20%">全县总面积（万亩）</td>
          <td style="width: 20%">主推品种</td>
        </tr>
        <template v-for="(i, index) in data">
          <tr>
            <td>
              <el-input v-model="data[0].county" readonly></el-input>
            </td>
            <td>
              <template
                v-if="copyData[0].county_area == null || copyData[0].county_area == '' || column_id != ''">
                <el-input
                  v-model="data[0].county_area"
                  @blur.prevent="checkFirstFieldInput($event)"
                ></el-input>
              </template>
              <template v-else="copyData[0].county_area != null && copyData[0].county_area != '' && column_id == ''">
                <el-input v-model="data[0].county_area" readonly></el-input>
              </template>
            </td>
            <td style="text-align: center">
              <span  onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)">填写</span>
            </td>
          </tr>
        </template>
      </table>
    </div>

    <!--对话框打开-->
    <el-dialog
      :title="tableFlag"
      :visible.sync="detialDialogVisible"
      width="80%"
    >
      <div style="height: auto; width: 100%">
        <div style="width: 100%; height: 50px">
          <span style="margin-right: 10px; color: #4e5f45">常用品种:</span>
          <el-select
              v-model="selectedPinXi"
              filterable
              remote
              allow-create
              reserve-keyword
              placeholder="请输入关键词"
              :remote-method="remoteMethod"
              :loading="loading">
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>



           <!-- <el-select
            v-model="selectedType"
            placeholder="品种概称"
            @change="selectDaoZuoType"
            style="margin-right: 10px; width: 10%"
          > 
            <el-option
              v-for="item in existDaoZuoList"
              :label="item.name"
              :key="item.id"
              :value="item.name"
            >
            </el-option>
          </el-select>
          <el-select
            v-model="selectedPinXi"
            placeholder="品种名"
            style="margin-right: 10px; width: 10%"
          >
            <el-option
              v-for="item in existPinXiList"
              :label="item.name"
              :key="item.id"
              :value="item.name"
            >
            </el-option>
          </el-select>  -->

                        

          <!-- <template v-if="existTableList.length== 0 || column_id != ''">
            <el-button @click="addSelectedPinXi">添加</el-button>
          </template>
          <template v-if="existTableList.length> 0 && column_id == ''">
            <el-button disabled>添加</el-button>
          </template> -->
          <el-button v-if="column_id == ''">添加</el-button>
          <el-button v-else @click="addSelectedPinXi">添加</el-button>
          <!-- <template v-if="existTableList.length > 0 && column_id == ''">
             <el-button disabled>新增品系</el-button>
          </template>
          <template v-if="existTableList.length== 0 || column_id != ''">
            <el-button @click="addNewPinXi">新增品系</el-button>
          </template> -->
         
        </div>
        <div style="width: 100%">
          <template v-if="addNewPinXiFlag == true">
            <div style="width: 100%; height: 50px; margin-left: 6%">
              <el-select
                v-model="NewSelectedType"
                placeholder="稻作类型"
                @change="selectDaoZuoType"
                style="margin-right: 10px; display: inline-block; width: 10%"
              >
                <el-option
                  v-for="item in existDaoZuoList"
                  :label="item.name"
                  :key="item.id"
                  :value="item.name"
                >
                </el-option>
              </el-select>
              <el-input v-model="newPinxi" style="display: inline-block; width: 220px" placeholder="品系名"></el-input>
             
                <el-button style="display: inline-block; margin-left: 11px"  @click="confirmAdd" >增加</el-button>
              
             
            </div>
          </template>

          <table
            id="myTable"
            style="
              width: 100%;
              border: 0px solid;
              line-height: 30px;
              text-align: center; ;
            "
            border="1"
            cellspacing="0"
          >
            <tr style="background-color: #cdbe70; color: green; height: 45px">
              <td style="width: 16%">品种名</td>
              <td style="width: 20%">面积（万亩）</td>
              <td style="width: 10%">占比</td>
              <td style="width: 6%">操作</td>
            </tr>
            <template v-for="i in tableList">
              <tr>
                <td style="color: green">{{ i.name }}</td>
                <td>
                  <template v-if="existTableList.length > 0 && column_id == ''">
                    <el-input v-model="i.firstInput" readonly></el-input>
                  </template>
                  <template v-if="existTableList.length == 0 || column_id != ''">
                    <el-input v-model="i.firstInput" @blur.prevent="fromValueGetPercent($event)"></el-input>
                  </template>
                </td>
                <td>
                  <el-input v-model="i.secondPercent" readonly></el-input>
                </td>
                <td>
                  <template v-if="existTableList.length > 0 && column_id == ''">
                    <el-button icon="el-icon-delete" circle size="mini" disabled></el-button>
                  </template>
                  <template v-if="existTableList.length == 0 || column_id != ''">
                    <el-button icon="el-icon-delete" circle size="mini" @click="deleteFromTabList($event)"></el-button>
                  </template> 
                </td>
              </tr>
            </template>
          </table>
          <el-button
            @click="dialogDisappear"
            style="margin-top: 10px; margin-left: 85%"
            type="primary"
            >确定</el-button
          >
          <el-button
            @click="detialDialogVisible = false"
            style="margin-top: 10px"
            >取消</el-button
          >
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      addNewPinXiFlag: false, //新增品系按钮是否被点击
      NewSelectedType: "", //新增品系所属的稻作类型
      newPinxi: "", //新增的品系
      temList: [], //存储所有表头数据
      headerList: [], //一级表头
      fieldRowIndex: 0, //存储二级字段所在行位置
      tableFlag: "", //保存当前点击的字段名
      copyData: [],
      secondList: [], //存储二级表头内容
      states: [],
      options: [],
      loading: false,
      detialDialogVisible: false,
      firstFieldValue: "巢湖市", //当前监测点所在的县（市、区）
      field_3: {
        //主推品种 的数据列表
      },
      tableList: [], //已经选中的内容
      checkPinXiAll: false, //是否全选品系
      checkPinXi: [], //已经选中的品系
      rawCheckPinXi: [], //初始的已选中品系
      // PinXis: pinXiOptions,
      isIndeterminate: true,
      selectedType: "", //选中的稻作类型
      selectedPinXi: "", //选中的品系
      existPinXiList: [],
      existDaoZuoList: [
        {
          name: "稻作类型1",
          id: "1",
          subs: [
            {
              name: "大白菜1",
              id: "1-1",
            },
            {
              name: "大白菜2",
              id: "1-2",
            },
            {
              name: "大白菜3",
              id: "1-3",
            },
            {
              name: "大白菜4",
              id: "1-4",
            },
          ],
        },
        {
          name: "稻作类型2",
          id: 2,
          subs: [
            {
              name: "大白菜5",
              id: "2-1",
            },
            {
              name: "大白菜6",
              id: "2-2",
            },
            {
              name: "大白菜7",
              id: "2-3",
            },
            {
              name: "大白菜8",
              id: "2-4",
            },
          ],
        },
        {
          name: "稻作类型3",
          id: 3,
          subs: [
            {
              name: "大白菜9",
              id: "3-1",
            },
            {
              name: "大白菜10",
              id: "3-2",
            },
            {
              name: "大白菜11",
              id: "3-3",
            },
          ],
        },
      ],
      // PinXiNum:1,//数字选择器的默认数字
      data: [],
      temData: [],
      column_id: '',
      input1: "",
      secondInput: "",
      thirdInput: "",
      tableName: "",
      pinZhongList:[],
      varieties:'',//品系名
      existTableList:[],//数据库中已经填报了品种数据
      
    };
  },
  methods: {
    dialogDisappear() {
      this.detialDialogVisible = false;
    },
    //选择品种分类
    selectDaoZuoType() {
      this.varieties = this.selectedType;
     
      this.getPinZhongInfo(this.varieties);
      // for (var i = 0; i < this.existDaoZuoList.length; ++i) {
      //   if (this.selectedType == this.existDaoZuoList[i].name) {
      //     this.existPinXiList = this.existDaoZuoList[i].subs;
      //   }
      // }
    },
    remoteMethod(query) {
                console.log(this.options)
                if (query !== '') {
                    this.loading = true;
                    axios({
                        url:'/getAllVarieties',
                        method:'get',
                    }).then((response) => {
                        console.log(response);
                        this.states = response.data;
                        console.log(this.states);
                    });
                    setTimeout(() => {
                        this.loading = false;
                        // this.options = this.list.filter(item => {
                        this.options = this.listSet.filter(item => {
                            return item.label.toLowerCase()
                                .indexOf(query.toLowerCase()) > -1;
                        });
                        // console.log(this.options);
                    }, 1000);
                } else {
                    this.options = [];
                }
            },
    //新增品系
    addNewPinXi() {
      if (this.addNewPinXiFlag == true) {
        this.addNewPinXiFlag = false;
      } else {
        this.addNewPinXiFlag = true;
      }
    },
    //增加品系
    confirmAdd() {
     this.$confirm("增加新的品系？").then(_=>{
        var varieties = this.NewSelectedType;
        var variety_name = this.newPinxi;
        axios({
          url:'/addNewPinXiInfo',
          method:'post',
          params:{
            varieties:varieties,
            variety_name:variety_name
          }
        }).then(res=>{
          this.$message.success("品种增加成功！");
        }).catch(_=>{
          return this.$message.error("新增品系失败！");
        });
     }).catch(_=>{
       return this.$message.info("您取消了操作！");
     });

    },
    //删除该品系
    deleteFromTabList(e) {
      if (e.target.nodeName == "I") {
        var i = e.target.parentNode.parentNode.parentNode.rowIndex;
        var tabLen = document.getElementById("myTable").rows.length;
          var tab = document.getElementById("myTable");
        document.getElementById("myTable").deleteRow(i);
      }
      if (e.target.nodeName == "BUTTON") {

        var i = e.target.parentNode.parentNode.rowIndex;
        var tabLen = document.getElementById("myTable").rows.length;
          var tab = document.getElementById("myTable");
        document.getElementById("myTable").deleteRow(i);
      }
    },
    //添加品系至表格中
    addSelectedPinXi() {
      if (this.selectedPinXi != "") {

        // 判断选中品系是否已有，是则禁止添加
        for(var i in this.existTableList){
          if(this.existTableList[i].name == this.selectedPinXi){
            // console.log("存在品系",this.selectedPinXi);
            return this.$message.error("已存在品系",this.selectedPinXi);
          }
        }

        var obj = new Object();
        obj.name = this.selectedPinXi;
        obj.firstInput = "";
        obj.secondPercent = "";
        this.tableList.push(obj);
      }
      this.selectedPinXi = "";
    },

    getChoosePinXi(PinXiName) {
      for (var i = 0; i < this.checkPinXi.length; ++i) {
        if (this.checkPinXi[i] == PinXiName) {
          return true;
        }
      }
      return false;
    },

    //确保该输入框输入为数字
    checkFirstFieldInput(e) {
      var strValue = e.target.value;

      if (!isNaN(strValue)) {
      } else {
        // e.target.parentNode.nextSibling.innerHTML = "X";
        e.target.value = "";
        //console.log(e);
        alert("请输入数字！");
      }
    },


    //保存
    getFormList() {
      this.$confirm("是否上报？")
        .then((_) => {
          var date = this.getNowTime();
          var people = localStorage.getItem("ms_username");
          for (var i = 0; i < this.data.length; ++i) {
            this.data[i].modification_time = date;
          }
          var tabLen = document.getElementById("myTable").rows.length;
          var tab = document.getElementById("myTable");
          var sum = [];
          for(var i = 0 ; i < tabLen ; ++i){
            // //console.log(tab.rows[i]);
            var pinxiName_ = tab.rows[i].firstChild.innerHTML;
            var obj = new Object();
            for(var j = 0 ; j < this.tableList.length ; ++j){
              if(this.tableList[j].name == pinxiName_){
                obj.county = this.data[0].county;
                obj.county_area = this.data[0].county_area;
                obj.pin_zhong_name = pinxiName_;
                obj.area_data = this.tableList[j].firstInput;
                if(this.column_id == '' || this.column_id == null){
                  obj.area_percent = tab.rows[i].cells[2].innerHTML;
                }
                if(this.column_id != '' && this.column_id != null){
                  obj.area_percent = this.tableList[j].secondPercent;
                }
                obj.modification_time = date;
                obj.people = people;
                obj.column_id = this.column_id;
                sum.push(obj);
              }
            }
          }

          var list = sum;
          var str = "";
          for (var i = 0; i < list.length; ++i) {
            str += JSON.stringify(list[i]) + "#";
          }
          //console.log(sum);
          this.saveInputData(str);
          if (this.column_id != "" && this.tableName != "") {
            this.fixDataBackState(this.tableName, this.column_id);
          }
        })
        .catch(err => {
            if(err=='TypeError: Cannot read properties of null (reading \'rows\')'){
              return this.$message.warning("操作失误！请检查主推品种填写是否有误!");
            }
          return this.$message.info("您取消了操作！");
        });
    },
    //修改数据退还部分状态
    fixDataBackState(tableName, id) {
      axios({
        url: "/fixDataBackState",
        method: "post",
        params: {
          tableName: tableName,
          tableId: id,
        },
      })
        .then()
        .catch((_) => {
          return this.$message.error("数据修改失败！");
        });
    },
    //根据输入值确定占比
    fromValueGetPercent(e) {
      //console.log(e);
      var cur = Number(e.target.value);
      //console.log(this.data[0].county_area);
      e.target.parentNode.parentNode.nextElementSibling.innerHTML = (Math.round((cur / this.data[0].county_area) *100 *100) / 100).toFixed(2) + "%";
    },

    //填写详细信息
    fillDetailInfo(e) {
      this.temData = [];
      var Index = e.target.parentNode.cellIndex;
      // //console.log(Index);
      this.fieldColIndex = Index; //把当前点击的字段所在列保存
      this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex; //把当前点击字段所在行保存
      this.fieldRowIndex = this.fieldRowIndex - 2;

      //根据temData中的数据自动生成对话框中的多选框品系数目
      this.checkPinXi = [];
      this.rawCheckPinXi = [];
      // for (var i = 0; i < pinXiOptions1.length; ++i) {
      //   // this.checkPinXi = [];
      //   var name = pinXiOptions1[i].name;
      //   var firstInput = pinXiOptions1[i].firstInput;
      //   var secondInput = pinXiOptions1[i].secondInput;
      //   if (
      //     this.data[this.fieldRowIndex][firstInput] != null &&
      //     this.data[this.fieldRowIndex][firstInput] != ""
      //   ) {
      //     this.checkPinXi.push(name);
      //     this.rawCheckPinXi.push(name);
      //     // //console.log(this.temData[0][firstInput]);
      //   }
      // }
      //console.log(this.checkPinXi);
      var getTdInnerHTML = "";
      getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index]
          .innerHTML;
      this.tableFlag = getTdInnerHTML;
      this.detialDialogVisible = true;
    },

    checkFirstFieldInput(e) {
      var strValue = e.target.value;
      if (!isNaN(strValue)) {
      } else {
        // e.target.parentNode.nextSibling.innerHTML = "X";
        e.target.value = "";
        alert("请输入数字！");
      }
    },

    // 获取当前时间
    getNowTime: function () {
      let dateTime;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth() + 1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf =
        new Date().getMinutes() < 10
          ? "0" + new Date().getMinutes()
          : new Date().getMinutes();
      let ss =
        new Date().getSeconds() < 10
          ? "0" + new Date().getSeconds()
          : new Date().getSeconds();
      if (hh < 10) {
        dateTime =
          yy + "-" + mm + "-" + dd + " " + "0" + hh + ":" + mf + ":" + ss;
      } else {
        dateTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
      }
      return dateTime;
    },

    //生成保存函数
    saveInputData(list) {
      axios({
        url: "/getSituationInputDataTable2",
        method: "post",
        params: {
          data: list,
        },
      })
        .then((res) => {
          //console.log(res);
          this.$message.success("操作成功！");
        })
        .catch((err) => {
          return this.$message.error("数据保存失败！");
        });
    },

    //查询该用户下的所有数据
    getUserAllData(people, column_id) {
      axios({
        url: "/getUserAllData",
        method: "post",
        params: {
          people: people,
          column_id: column_id,
          flag:0
        },
      })
        .then((res) => {
          this.data.push(res.data[0]);
          if (this.data.length > 0) {
            const json = JSON.stringify(res.data);
            this.copyData = JSON.parse(json);
          }
          this.tableList = [];
          for(var i = 0 ; i < res.data.length ; ++i){
            var obj = new Object();
            if(res.data[i].pin_zhong_name != null && res.data[i].pin_zhong_name != ''){
              obj.name = res.data[i].pin_zhong_name;
              obj.firstInput = res.data[i].area_data;
              obj.secondPercent = res.data[i].area_percent;
            }
            if(obj.name != null && obj.name != ''){
              this.tableList.push(obj);
            }        
          }
          var tem = this.tableList;
          const json1 = JSON.stringify(tem);
          this.existTableList = JSON.parse(json1);
        })
        .catch((err) => {
          return this.$message.error("获取数据失败！");
        });
    },
    //获取品种信息
    getPinZhongInfo(varieties){
      axios({
        url:'/getPinZhongInfo',
        method:'post',
        params:{
          varieties:varieties
        }
      }).then(res=>{
        if(this.varieties == '' || this.varieties == null){//刚打开页面时
          this.pinZhongList = res.data;
          this.existDaoZuoList = [];
          for(var i = 0 ; i < this.pinZhongList.length ; ++i){
            var obj = new Object();
            obj.name = this.pinZhongList[i].varieties;
            this.existDaoZuoList.push(obj);
          }
        }
        if(this.varieties != '' && this.varieties != null){
          this.existPinXiList = [];
          for(var i = 0 ; i < res.data.length ; ++i){
            var obj = new Object();
            obj.name = res.data[i].variety_name;
            this.existPinXiList.push(obj);
          }
        }

      }).catch(_=>{
        return this.$message.error("获取品种信息失败！");
      });
    },
  },
  created() {
    var people = localStorage.getItem("ms_username");
    if (this.$route.query.column_id != null &&this.$route.query.column_id != undefined &&this.$route.query.column_id != "") {
      this.column_id = this.$route.query.column_id;
      this.tableName = this.$route.query.record_table;
    }
    if (this.$route.query.column_id == null ||this.$route.query.column_id == undefined ||this.$route.query.column_id == "") {
      this.column_id = '';
    } //在其他表也加这一句
    this.getUserAllData(people, this.column_id);
    this.getPinZhongInfo(this.varieties);
  },
  mounted() {},
  computed: {
            listSet() {
                return this.states.map(item => {
                    return { value: `${item.variety_name}`, label: `${item.variety_name}` };
                });
            }
        },
  watch: {
    // 'data.field_2':'clearRespondingInput'//一旦改变field_2的输入值，对话框中相对应的输入值全部清空
  },
};
</script>
<style scoped>
.el-checkbox-width {
  width: 11%;
  height: 25px;
}
.el-checkbox {
  margin-left: 2.4%;
}
.el-dialog {
  top: 5%;
  left: 2%;
  height: auto;
  overflow: hidden;
}
</style>