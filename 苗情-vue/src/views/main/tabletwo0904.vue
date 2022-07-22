<template>
    <div class="demo-image__placeholder">
        <el-row>
          <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:5px">
            <span>{{tableLabel}}</span>
          </div>
        </el-row>
        
        <el-row>
            <template v-if="column_id == ''|| column_id == null">
                <router-link :to="{name:'/tableTwoMain',params: {activeNum: 3}}">
                    <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
                </router-link>
            </template>
            <template v-if="column_id != ''">
                <router-link to='/traceBackData'>
                    <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
                </router-link>
            </template>
            <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button> -->
        </el-row>

        <el-table :data="data" :header-cell-style="headerStyle">
            <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed />
                <!-- <el-input v-model="data[fieldRowIndex].field_1" disabled></el-input> -->
            <el-table-column align="center"  width="105%" label="地区类型">
                <template slot-scope="scope">
                    <template v-if="data[fieldRowIndex=scope.$index].field_2 != null && data[fieldRowIndex=scope.$index].field_2 != '' && column_id == ''">
                        <el-input v-model="data[fieldRowIndex=scope.$index].field_2" readonly></el-input>
                    </template> 
                    <template v-if="data[fieldRowIndex=scope.$index].field_2 == null || data[fieldRowIndex=scope.$index].field_2 == '' || column_id != '' ">
                        <el-select v-model="data[fieldRowIndex=scope.$index].field_2" placeholder="请选择" @focus="getSelectContent1">
                                <el-option
                                    v-for="item in listArray1"
                                    :key="item.id"
                                    :label="item.list"
                                    :value="item.list">
                                </el-option>
                        </el-select>
                    </template>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="稻作类型">
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_3" disabled></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="品系类型">
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_4" disabled></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="高温冷害">
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_5" ></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="高温冷害发生时间" >
                <template slot-scope="scope">
                    <el-date-picker
                        value-format=“yyyy-MM-dd”
                        v-model="data[fieldRowIndex=scope.$index].field_6"
                        type="date"
                        placeholder="选择日期" style="width: 100%">
                    </el-date-picker>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="全县种植面积（万亩）">
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_7" ></el-input>                    
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="冷害总面积（万亩）" >
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_8" ></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="冷害级别（万亩）">
                <el-table-column align="center"   label="一级">
                    <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_9_1" @blur.prevent="checkFirstFieldInput($event)" />      
                    </template>
                </el-table-column>
                <el-table-column align="center"   label="二级">
                    <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_9_2" @blur.prevent="checkFirstFieldInput($event)" />
                    </template>
                </el-table-column>
                <el-table-column align="center"   label="三级">
                    <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_9_3" @blur.prevent="checkFirstFieldInput($event)" />
                    </template>
                </el-table-column>
                <el-table-column align="center"   label="四级">
                    <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_9_4" @blur.prevent="checkFirstFieldInput($event)" />
                    </template>
                </el-table-column>
                <el-table-column align="center"   label="五级">
                    <template slot-scope="scope">
                        <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex=scope.$index].field_9_5" @blur.prevent="checkFirstFieldInput($event)" />
                    </template>
                </el-table-column>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="主要品种名称">
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_10" ></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="占比（%）">
                <template slot-scope="scope">
                    <template v-if="data[fieldRowIndex=scope.$index].field_7!=null && data[fieldRowIndex=scope.$index].field_8!=null">
                        <span class="span">{{Number((data[fieldRowIndex=scope.$index].field_8/data[fieldRowIndex=scope.$index].field_7)*100).toFixed(2)}}%</span>
                    </template>
                    <template v-if="data[fieldRowIndex=scope.$index].field_7==null || data[fieldRowIndex=scope.$index].field_8==null">
                        <span class="span">暂无</span>
                    </template>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="其中：成灾面积（万亩）">
                <template slot-scope="scope">
                <el-input v-model="data[fieldRowIndex=scope.$index].field_12" ></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="其中：绝收面积（万亩）">
                <template slot-scope="scope">
                    <el-input v-model="data[fieldRowIndex=scope.$index].field_13" ></el-input>
                </template>
            </el-table-column>
            <el-table-column align="center"  width="105%" label="产量损失（%）">
                <template slot-scope="scope">
                    <template v-if="data[fieldRowIndex=scope.$index].field_12!=null && data[fieldRowIndex=scope.$index].field_13!=null">
                        <span class="span">{{Number(((data[fieldRowIndex=scope.$index].field_12*0.3+data[fieldRowIndex=scope.$index].field_13*0.8)*100)/data[fieldRowIndex=scope.$index].field_7).toFixed(2)}}%</span>
                    </template>
                    <template v-if="data[fieldRowIndex=scope.$index].field_12==null || data[fieldRowIndex=scope.$index].field_13==null">
                        <span class="span">暂无</span>
                    </template>
                </template>
            </el-table-column>
            
            <el-table-column align="center"  fixed="right" width="252px" label="操作">
                <template slot-scope="scope">
                    <!-- 当in_use非0，非5时，不可点击暂存 -->
                    <template v-if="data[fieldRowIndex=scope.$index].in_use == '0' || data[fieldRowIndex=scope.$index].in_use == '5'">
                        <el-button @click="getTemporaryFormList(fieldRowIndex=scope.$index)">暂存</el-button>
                    </template>
                
                    <el-button @click="getFormList(fieldRowIndex=scope.$index)">上报</el-button>
                    <el-button>撤回</el-button>
                    <!-- <template slot-scope="scope">
                        <el-button type="danger" size="mini" v-if="scope.row.in_use == 3" @click="backRevision(scope.$index,scope.row)" >返回</el-button>
                    </template> -->
                </template>
            </el-table-column>  
        </el-table>

    </div>

</template>
<script src="../../../public/js/Vue.min.js">
</script>
<script>
import axios from "axios";
export default {
  data() {
    return {
      tableLabel: "",
      tableName: "",
      column_id: "",
      tableId: "",
      autoList: [], //存储自动获取内容
      // temList:'',
      listArray1: [],
      listArray2: [], //存储下拉框列表
      listArea: [], //存地区类型
      selectedID: "", //存储选择了哪一个下拉框
      sumDataList: [], //保存所有表头的输入
      dataList: [], //保存二级表头的输入
      headerList: [], //一级表头
      secondList: [], //存储二级表头内容
      secondHeaderList: [], //存储有跨列的字段名
      isOpen: false, //控制表格的前段和后端展开
      tdWidth: "", //一级表的宽度
      tdSecondWidth: "", //二级表的宽度
      tdSecondFieldWidth: "", //二级表头的输入框宽度
      data: [],
      detialDialogVisible: false,
      tableFlag: "", //保存当前点击的字段名
      fieldColIndex: 0, //存储二级字段所在列位置
      fieldRowIndex: 0, //存储二级字段所在行位置
      testValue: 0,
      getField_1_1: "", //第一行的‘县（市、区）’
      fieldName: "", //下拉框所属的字段名称
    };
  },

  mounted() {
    // this.initData();
    // this.getTableData();
    // this.getSelectContent1();
    // this.getSelectContent2();
  },
  methods: {
    //初始化数据
    initData() {
      this.tableLabel = "监测县(市、区)水稻低温冷害表";
      //this.getHeaderList();
      //this.getTableData();
    },
    //获取表数据
    getTableData(people, column_id) {
      const _this = this;
      axios({
        url: "/getAllDataTwo0904",
        method: "get",
        params: {
          people: people,
          column_id: column_id,
          beginYear: 0,
          endYear: 0,
        },
      })
        .then((res) => {
          console.log(res);
          _this.data = res.data;
        })
        .catch((err) => {
          return this.$message.error("查询数据失败！");
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
    //获取地区类型下拉框内容
    getSelectContentArea() {
      axios({
        url: "/getSelectContent",
        method: "post",
        params: {
          fieldName: "地区类型",
          riceType: "one",
        },
      })
        .then((res) => {
          this.listArea = res.data;
          //console.log(this.listArray1);
        })
        .catch((err) => {
          return this.$message.error("获取地区类型失败！");
        });
    },
    //获取稻作类型下拉框内容
    getSelectContent1() {
      axios({
        url: "/getSelectContent",
        method: "get",
        params: {
          fieldName: "稻作类型",
          riceType: "one",
        },
      })
        .then((res) => {
          this.listArray1 = res.data;
          //console.log(this.listArray1);
        })
        .catch((err) => {
          return this.$message.error("获取下拉框内容失败！");
        });
    },

    headerStyle({ row, column, rowIndex, columnIndex }) {
      //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
      if (rowIndex === 2 || 3) {
        // return {display:"none"};
        return {
          height: "0",
          padding: "2px",
          fontSize: "7px",
          background: "#6E8B3D",
          color: "#FFFFFF",
        };
      }
      if (rowIndex === 0) {
        return {
          height: "0",
          padding: "2px",
          fontSize: "7px",
          background: "#6E8B3D",
          color: "#FFFFFF",
        };
      }
      if (rowIndex == 1) {
        // return {background:'#eee',color:'#606266'};
        return {
          height: "0",
          padding: "1px",
          fontSize: "5px",
          background: "#6E8B3D",
          color: "#FFFFFF",
        };
      }
    },

    //获取品系类型下拉框内容
    getSelectContent2() {
      axios({
        url: "/getSelectContent",
        method: "get",
        params: {
          fieldName: "品系类型",
          riceType: "one",
        },
      })
        .then((res) => {
          this.listArray2 = res.data;
          //console.log(this.listArray2);
        })
        .catch((err) => {
          return this.$message.error("获取下拉框内容失败！");
        });
    },

    //检查input框的输入是否符合要求
    checkFirstFieldInput(e) {
      var strValue = e.target.value;
      if (!isNaN(strValue)) {
      } else {
        // e.target.parentNode.nextSibling.innerHTML = "X";
        e.target.value = "";
        alert("请输入数字！");
      }
    },

    handleClose(done) {
      var inputs = document.getElementsByClassName("secondFieldDetail");
      if (inputs.length < this.secondList.length) {
        this.$confirm("还未录入完成，确认退出？")
          .then((_) => {
            //不保存当前数据
            // this.clearAllInputs();
            done();
          })
          .catch((_) => {});
      } else if (inputs.length == this.secondList.length) {
        this.$confirm("录入数据是否保存？")
          .then((_) => {
            //保存当前数据
            this.dataList = [];
            var tem = "";
            tem += this.fieldRowIndex + "_" + this.fieldColIndex;
            localStorage.setItem(tem, this.dataList);
            // this.clearAllInputs();
            done();
          })
          .catch((_) => {});
      } else {
        this.$confirm("确认关闭？")
          .then((_) => {
            //不保存当前数据
            // this.clearAllInputs();
            done();
          })
          .catch((_) => {});
      }
    },

    //取消按钮
    cancelOp() {
      this.detialDialogVisible = false;
    },

    //暂存数据
    getTemporaryFormList(fieldRowIndex) {
      this.$confirm("是否暂存？")
        .then((_) => {
          var date = this.getNowTime();
          
          this.data[fieldRowIndex].modification_time = date;
          this.data[fieldRowIndex].in_use = 5;

          var spans = document.getElementsByClassName("span");
          if (spans[0].innerHTML != null) {
            this.data[fieldRowIndex].field_11 = spans[0].innerHTML;
          }

          if (spans[1].innerHTML != null) {
            this.data[fieldRowIndex].field_14 = spans[1].innerHTML;
          }
          if (this.data[fieldRowIndex].field_6 != null) {
            this.data[fieldRowIndex].field_6 = this.data[0].field_6
              .toString()
              .replace("“", "")
              .replace("”", "");
          }
          
          this.saveInputData(this.data[fieldRowIndex]);

          if (this.column_id != "" && this.tableName != "") {
            this.fixDataBackState(this.tableName, this.column_id);
          }
        })
        .catch((_) => {
          return this.$$message.info("您取消了操作！");
        });
    },

    //保存
    getFormList(fieldRowIndex) {
      this.$confirm("是否上报？")
        .then((_) => {
          var date = this.getNowTime();

          this.data[fieldRowIndex].modification_time = date;
          this.data[fieldRowIndex].in_use = 1;

          var spans = document.getElementsByClassName("span");
          if (spans[0].innerHTML != null) {
            this.data[fieldRowIndex].field_11 = spans[0].innerHTML;
          }

          if (spans[1].innerHTML != null) {
            this.data[fieldRowIndex].field_14 = spans[1].innerHTML;
          }
          if (this.data[fieldRowIndex].field_6 != null) {
            this.data[fieldRowIndex].field_6 = this.data[0].field_6
              .toString()
              .replace("“", "")
              .replace("”", "");
          }

          this.saveInputData(this.data[fieldRowIndex]);

          if (this.column_id != "" && this.tableName != "") {
            this.fixDataBackState(this.tableName, this.column_id);
          }
        })
        .catch((_) => {
          return this.$message.info("您取消了操作！");
        });
    },

    //生成保存函数
    saveInputData(str) {
      axios({
        url: "/getInputDataTableTwo0904",
        method: "post",
        params: {
          data: str,
        },
      })
        .then((res) => {
          this.$message.success("操作成功！");
        })
        .catch((err) => {
          return this.$message.error("数据保存失败！");
        });
    },

    //填写详细信息
    fillDetailInfo(e) {
      var temData = localStorage.getItem("1_4");
      var Index = e.target.parentNode.cellIndex;
      this.fieldColIndex = Index; //把当前点击的字段所在列保存
      this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex; //把当前点击字段所在行保存
      this.fieldRowIndex = this.fieldRowIndex - 2;
      //console.log(this.fieldRowIndex);
      var getTdInnerHTML = "";
      getTdInnerHTML =
        e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index]
          .innerHTML;
      this.tableFlag = getTdInnerHTML;

      // this.secondChooseField(getTdInnerHTML);
      this.secondList = [];
      for (var i = 0; i < this.headerList.length; ++i) {
        if (this.headerList[i].column_name == getTdInnerHTML) {
          this.secondList = this.headerList[i].children;
        }
      }
      //console.log(this.headerList);
      //console.log(this.secondHeaderList);
      //console.log(this.secondList);
      this.tdSecondWidth = 100 / this.secondHeaderList.length;
      this.tdSecondFieldWidth = 100 / this.secondList.length;
      this.detialDialogVisible = true;
    },

    // 获取当前时间
    getNowTime: function() {
      let dateTime;
      let yy = new Date().getFullYear();
      let mm =
        new Date().getMonth() + 1 < 10
          ? "0" + (new Date().getMonth() + 1)
          : new Date().getMonth() + 1;
      let dd =
        new Date().getDate() < 10
          ? "0" + new Date().getDate()
          : new Date().getDate();
      let hh =
        new Date().getHours() < 10
          ? "0" + new Date().getHours()
          : new Date().getHours();
      let mf =
        new Date().getMinutes() < 10
          ? "0" + new Date().getMinutes()
          : new Date().getMinutes();
      let ss =
        new Date().getSeconds() < 10
          ? "0" + new Date().getSeconds()
          : new Date().getSeconds();
      // if(hh < 10){
      //     dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
      // }else{
      dateTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
      // }
      return dateTime;
    },
  },
  created() {
    this.initData();
    var people = localStorage.getItem("ms_username");
    //console.log(people)
    if (
      this.$route.query.column_id != null &&
      this.$route.query.column_id != undefined &&
      this.$route.query.column_id != ""
    ) {
      this.column_id = this.$route.query.column_id;
      this.tableName = this.$route.query.record_table;
    }
    this.getTableData(people, this.column_id);
  },
};
</script>
<!-- <script>Vue.config.productionTip=false</script> -->
<style>
.demo-image__placeholder {
}
.el-dialog {
  top: 15%;
  left: 4%;
}
table tr,
table tr td {
  border: 1px solid lightgray;
}
</style>
