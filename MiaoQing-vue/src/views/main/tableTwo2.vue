<template>
    <div class="demo-image__placeholder">
      <template v-if="column_id != ''">
        <router-link to='/traceBackData'>
          <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
         </router-link>
      </template>
      <template v-if="column_id == ''|| column_id == null">
        <router-link to='/tablemain'>
              <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
          </router-link>
      </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上 报</el-button>
        <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 2.4%;margin-bottom: 1%;" @click="getFormList('0')">暂存</el-button> -->
                <div id="containner_div" style="width: 100%;height: 70%;">
   
                  <table id="sum_tab" style="width: 100%;border: 1px solid white;line-height: 40px;text-align: center;" border="1" cellspacing="0">
                     <tr >
                      <td colspan="3" style="background-color: #6E8B3D;color: #FFFFFF;">2021年度监测县(市、区)水稻生产主推品种调查表</td>
                    </tr>                   
                    

                    <tr style="background-color: #CDBE70;color: green;">
                      <td style="width: 20%;">县（市、区）</td>
                      <td style="width: 20%;">全县总面积（万亩）</td>
                      <td style="width: 20%;">主推品种</td>
                    </tr>
                    <template v-for="(i,index) in data">
                    <tr>
                      <td>
                        <!-- <template v-if="data[index].field_1 == null || data[index].field_1 == ''"> -->
                        <el-input v-model="data[index].field_1"></el-input>
                      <!-- </template> -->
                        <!-- <template v-if="data[index].field_1 != null && data[index].field_1 != ''">
                          <el-input v-model="data[index].field_1" disabled></el-input></template> -->
                      </td>
                      <td>
                        <!-- <template v-if="data[index].field_2 == null || data[index].field_2 == ''"> -->
                        <el-input v-model="data[index].field_2" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      <!-- </template> -->
                        <!-- <template v-if="data[index].field_2 != null && data[index].field_2 != ''">
                          <el-input disabled v-model="data[index].field_2" @blur.prevent="checkFirstFieldInput($event)"></el-input></template> -->
                      </td>
                      <td style="text-align: center;">
                        <span  onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                      </td>
                    </tr>
                  </template>
                  </table>
                </div>
              <!-- <el-button @click="getNewRecord" style="margin-top: 10px;margin-left: 9px;">新增</el-button> -->
  
              <!--字段详情-->
              <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="90%" >
                <el-row style="margin-bottom: 0.3%;">
                  <el-col>
                    <!-- 请输入启用的品系数目：<el-input-number v-model="PinXiNum" @change="handlePinXiNumChange" :min="1" :max="20" label="描述文字"></el-input-number> -->
                    <!-- <template v-if="rawCheckPinXi.length == 0"> -->
                    <el-checkbox style="margin-left: 2.4%;" :indeterminate="isIndeterminate" v-model="checkPinXiAll" @change="handleCheckAllChange">全选</el-checkbox>
                    <el-checkbox-group  v-model="checkPinXi" @change="handleCheckedPinXiChange">
                      <el-checkbox class="el-checkbox-width" v-for="i in PinXis" :label="i" >{{i}}</el-checkbox>
                    </el-checkbox-group>
                  <!-- </template> -->
                  </el-col>
                </el-row>
              <table style="width: 100%;border: 1px solid white;" border="1" cellspacing="0">
                <!-- <template v-if="this.data[0].field_1 != ''"> -->
                <tr style="line-height: 40px;">
                  <template v-if="getChoosePinXi('大白菜1')">
                  <td style="width: 7.1%;">大白菜1</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜2')">
                  <td style="width: 7.1%;">大白菜2</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜3')">
                  <td style="width: 7.1%;">大白菜3</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜4')">
                  <td style="width: 7.1%;">大白菜4</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜5')">
                  <td style="width: 7.1%;">大白菜5</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜6')">
                  <td style="width: 7.1%;">大白菜6</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜7')">
                  <td style="width: 7.1%;">大白菜7</td>
                  <td style="width: 7.1%;">面积（万亩）</td></template>
                </tr>
                <tr>
                  <template v-if="getChoosePinXi('大白菜1')">
                    <td style="width: 7.1%;">
                      <el-input v-model="data[fieldRowIndex].field_3_1" style="width: 100%;"></el-input>
                    </td>
                    <td style="width: 7.1%;">
                      <tr>
                        <td style="width:60%">
                          <el-input v-model="data[fieldRowIndex].field_3_2_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                        </td>
                        <td style="width:47%">
                          <template v-if="data[fieldRowIndex].field_3_2_1 != ''">
                            <span class="span" id="span1">{{Number((data[fieldRowIndex].field_3_2_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                          </template>
                          <template v-if="data[fieldRowIndex].field_3_2_1 == ''">
                            <span id="span1">暂无</span>
                          </template>
                        </td>
                      </tr>
                    </td>
                </template>
                <template v-if="getChoosePinXi('大白菜2')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_3" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_4_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_4_1 != ''">
                          <span class="span" id="span2">{{Number((data[fieldRowIndex].field_3_4_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_4_1 == ''">
                          <span id="span2">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                  </template>
                  <template v-if="getChoosePinXi('大白菜3')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_5" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_6_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_6_1 != ''">
                          <span class="span" id="span3">{{Number((data[fieldRowIndex].field_3_6_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_6_1 == ''">
                          <span id="span3">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                  </template>
                  <template v-if="getChoosePinXi('大白菜4')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_7" style="width: 100%;"></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_8_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_8_1 != ''">
                          <span class="span" id="span4">{{Number((data[fieldRowIndex].field_3_8_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_8_1 == ''">
                          <span id="span4">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                  </template>
                  <template v-if="getChoosePinXi('大白菜5')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_9" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_10_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_10_1 != ''">
                          <span class="span" id="span5">{{Number((data[fieldRowIndex].field_3_10_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_10_1 == ''">
                          <span id="span5">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                </template>
                <template v-if="getChoosePinXi('大白菜6')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_11" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_12_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_12_1 != ''">
                          <span class="span" id="span6">{{Number((data[fieldRowIndex].field_3_12_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_12_1 == ''">
                          <span id="span6">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                </template>
                <template v-if="getChoosePinXi('大白菜7')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_13" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_14_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_14_1 != ''">
                          <span class="span" id="span7">{{Number((data[fieldRowIndex].field_3_14_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_14_1 == ''">
                          <span id="span7">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                </template>
                </tr>
                <tr style="line-height: 40px;">
                  <template v-if="getChoosePinXi('大白菜8')">
                  <td>大白菜8</td>
                  <td>面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜9')">
                  <td>大白菜9</td>
                  <td>面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜10')">
                  <td>大白菜10</td>
                  <td>面积（万亩）</td></template>
                  <!-- <template v-if="getChoosePinXi('大白菜11')">
                  <td>大白菜11</td>
                  <td>面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜12')">
                  <td>大白菜12</td>
                  <td>面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜13')">
                  <td>大白菜13</td>
                  <td>面积（万亩）</td></template>
                  <template v-if="getChoosePinXi('大白菜14')">
                  <td>大白菜14</td>
                  <td>面积（万亩）</td></template> -->
                </tr>
                <tr>
                  <template v-if="getChoosePinXi('大白菜8')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_15" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_16_1" style="width: 100%" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_16_1 != ''">
                          <span class="span" id="span8">{{Number((data[fieldRowIndex].field_3_16_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_16_1 == ''">
                          <span id="span8">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                </template>
                <template v-if="getChoosePinXi('大白菜9')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_17" style="width: 100%;" ></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_18_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_18_1 != ''">
                          <span class="span" id="span9">{{Number((data[fieldRowIndex].field_3_18_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_18_1 == ''">
                          <span id="span9">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td></template>
                  <template v-if="getChoosePinXi('大白菜10')">
                  <td style="width: 7.1%;">
                    <el-input v-model="data[fieldRowIndex].field_3_19" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                  </td>
                  <td style="width: 7.1%;">
                    <tr>
                      <td style="width:60%">
                        <el-input v-model="data[fieldRowIndex].field_3_20_1" style="width: 100%;" @blur.prevent="checkFirstFieldInput($event)"></el-input>
                      </td>
                      <td style="width:47%">
                        <template v-if="data[fieldRowIndex].field_3_20_1 != ''">
                          <span class="span" id="span10">{{Number((data[fieldRowIndex].field_3_20_1/data[fieldRowIndex].field_2)*100).toFixed(2)}}%</span>
                        </template>
                        <template v-if="data[fieldRowIndex].field_3_20_1 == ''">
                          <span id="span10">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td></template>

                </tr>
                <tr>
                  <td style="width: 7.9%;">
                    <tr>
                      <td style="width:70%">
                        <template v-if="checkSumInput() == false">
                          <span id="editResult" class="span" style="color: red;text-align: center;line-height: 40px;">填写有误！</span>
                        </template>

                        <template v-if="checkSumInput() == true">
                          <span id="editResult" class="span" style="color: green;text-align: center;line-height: 40px;">核对正确！</span>
                        </template>

                        <template v-if="checkSumInput() == 'N'">
                          <span id="editResult" style="line-height: 40px;">暂无</span>
                        </template>
                      
                      </td>
                      <td style="width:70%">
                        <template v-if="checkSumAudit() != 'N'">
                          <span id="editPercent" class="span">{{checkSumAudit()}}</span>
                        </template>
                        <template v-if="checkSumAudit() == 'N'">
                          <span id="editPercent">暂无</span>
                        </template>
                      </td>
                    </tr>
                  </td>
                </tr>
              </table>

            <span slot="footer" class="dialog-footer">
              <el-button @click="cancelOp">取 消</el-button>
              <el-button type="primary" @click="getInputInfo">确 定</el-button>
            </span>
          </el-dialog>
      </div>
</template>
<script>
    import axios from 'axios'
    const pinXiOptions1 = [{name:'大白菜1',firstInput:'field_3_1',secondInput:'field_3_2_1',spanContent:'span1'},{name:'大白菜2',firstInput:'field_3_3',secondInput:'field_3_4_1',spanContent:'span2'},{name:'大白菜3',firstInput:'field_3_5',secondInput:'field_3_6_1',spanContent:'span3'},
                          {name:'大白菜4',firstInput:'field_3_7',secondInput:'field_3_8_1',spanContent:'span4'},{name:'大白菜5',firstInput:'field_3_9',secondInput:'field_3_10_1',spanContent:'span5'},{name:'大白菜6',firstInput:'field_3_11',secondInput:'field_3_12_1',spanContent:'span6'},
                          {name:'大白菜7',firstInput:'field_3_13',secondInput:'field_3_14_1',spanContent:'span7'},{name:'大白菜8',firstInput:'field_3_15',secondInput:'field_3_16_1',spanContent:'span8'},{name:'大白菜9',firstInput:'field_3_17',secondInput:'field_3_18_1',spanContent:'span9'},
                          {name:'大白菜10',firstInput:'field_3_19',secondInput:'field_3_20_1',spanContent:'span10'},
                        //   {name:'大白菜11',firstInput:'field_3_21',secondInput:'field_3_22_1',spanContent:'span11'},{name:'大白菜12',firstInput:'field_3_23',secondInput:'field_3_24_1',spanContent:'span12'},
                        //   {name:'大白菜13',firstInput:'field_3_25',secondInput:'field_3_26_1',spanContent:'span13'},{name:'大白菜14',firstInput:'field_3_27',secondInput:'field_3_28_1',spanContent:'span14'},{name:'大白菜15',firstInput:'field_3_29',secondInput:'field_3_30_1',spanContent:'span15'},
                        //   {name:'大白菜16',firstInput:'field_3_31',secondInput:'field_3_32_1',spanContent:'span16'},{name:'大白菜17',firstInput:'field_3_33',secondInput:'field_3_34_1',spanContent:'span17'},{name:'大白菜18',firstInput:'field_3_35',secondInput:'field_3_36_1',spanContent:'span18'},
                        //   {name:'大白菜19',firstInput:'field_3_37',secondInput:'field_3_38_1',spanContent:'span19'},{name:'大白菜20',firstInput:'field_3_39',secondInput:'field_3_40_1',spanContent:'span20'}
                        ];
    const pinXiOptions=['大白菜1','大白菜2','大白菜3','大白菜4','大白菜5','大白菜6','大白菜7','大白菜8','大白菜9','大白菜10'
    //,    '大白菜11','大白菜12','大白菜13','大白菜14','大白菜15','大白菜16','大白菜17','大白菜18','大白菜19','大白菜20'
];
export default{
    data(){
        return{
            temList:[],//存储所有表头数据
            headerList:[],//一级表头
            fieldRowIndex:0,//存储二级字段所在行位置
            tableFlag:'',//保存当前点击的字段名

            secondList:[],//存储二级表头内容
            detialDialogVisible:false,
            firstFieldValue:'巢湖市',//当前监测点所在的县（市、区）
            field_3:{//主推品种 的数据列表
              
            },
            checkPinXiAll:false,//是否全选品系
            checkPinXi:[],//已经选中的品系
            rawCheckPinXi:[],//初始的已选中品系
            PinXis:pinXiOptions,
            isIndeterminate:true,
            // PinXiNum:1,//数字选择器的默认数字
            data:[],
            temData:[],
            tableName:'',
            column_id:'',
        }
    },
    methods:{
      handleCheckAllChange(val) {
        //console.log(val);
        this.checkPinXi = val ? pinXiOptions : ['大白菜1'];
        // this.PinXiNum = this.checkPinXi.length;
        // //console.log(this.PinXiNum);
        this.isIndeterminate = false;
      },
      handleCheckedPinXiChange(value){
        //console.log(value);
        let checkedCount = value.length;
        // this.PinXiNum = checkedCount;
        // //console.log(this.PinXiNum);
        this.checkPinXiAll = checkedCount === this.PinXis.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.PinXis.length;
      },

      getChoosePinXi(PinXiName){
        for(var i = 0 ; i < this.checkPinXi.length ; ++i){
          if(this.checkPinXi[i] == PinXiName){
            return true;
          }
        }
        return false;
      },

        //确保该输入框输入为数字
        checkFirstFieldInput(e){
          var strValue = e.target.value;

          if(!isNaN(strValue)){
            
        }else{
          // e.target.parentNode.nextSibling.innerHTML = "X";
          e.target.value = "";
          //console.log(e);
          alert("请输入数字！");   
        }
        },

        //判断用户输入的数据是否合格
        checkSumInput(){
          var sum = 0;
          if(this.checkPinXi.length == 0){
            return "N";
          }
          for(var i = 0 ; i < this.checkPinXi.length ; ++i){
            var name = this.checkPinXi[i];
            for(var j in pinXiOptions1){
              if(pinXiOptions1[j].name == name){
                var field1 = pinXiOptions1[j].firstInput;
                var field2 = pinXiOptions1[j].secondInput;
                if(this.data[this.fieldRowIndex][field1] == null || this.data[this.fieldRowIndex][field2] == null){
                    return "N";//还有空没填完
                }
              }
            }
          }
          //所有都填完了
          //判断填的对不对
          for(var i = 0 ; i < this.checkPinXi.length ; ++i){
            var name = this.checkPinXi[i];
            for(var j in pinXiOptions1){
              if(pinXiOptions1[j].name == name){
                var field1 = pinXiOptions1[j].firstInput;
                var field2 = pinXiOptions1[j].secondInput;
                sum += Number(this.data[this.fieldRowIndex][field2]);
              }
            }
          }
          if(sum == Number(this.data[this.fieldRowIndex].field_2)){
            return true;
          }else{
            return false;
          }
        },
        //判断用户输入的数据跟总面积的占比
        checkSumAudit(){
          var sum = 0;
          for(var i = 0 ; i < this.checkPinXi.length ; ++i){
            var name = this.checkPinXi[i];
            for(var j in pinXiOptions1){
              if(pinXiOptions1[j].name == name){
                var field1 = pinXiOptions1[j].firstInput;
                var field2 = pinXiOptions1[j].secondInput;
                if(this.data[this.fieldRowIndex][field1] == null || this.data[this.fieldRowIndex][field2] == null){
                    return "N";//还有空没填完
                }
              }
            }
          }
          //所有数据都填完
          //判断填的对不对
          for(var i = 0 ; i < this.checkPinXi.length ; ++i){
            var name = this.checkPinXi[i];
            for(var j in pinXiOptions1){
              if(pinXiOptions1[j].name == name){
                var field1 = pinXiOptions1[j].firstInput;
                var field2 = pinXiOptions1[j].secondInput;
                sum += Number(this.data[this.fieldRowIndex][field2]);
              }
            }
          }
          return ((sum/Number(this.data[this.fieldRowIndex].field_2)).toFixed(2))*100+"%"

        },

        handleClose(done) {
            var inputs = document.getElementsByClassName("secondFieldDetail");
            if(inputs.length < this.secondList.length){
                this.$confirm('还未录入完成，确认退出？')
                .then(_ => {
                    //不保存当前数据

                    done();
                })
                .catch(_ => {});          
            }
            else if(inputs.length == this.secondList.length){
            this.$confirm('录入数据是否保存？')
                .then(_ => {
                    //保存当前数据

                    done();
                })
                .catch(_ => {}); 
            }else{
            this.$confirm('确认关闭？')
                .then(_ => {
                    //不保存当前数据

                    done();
                })
                .catch(_ => {}); 
            }
      },

      //品系选择器数字改变时
      // handlePinXiNumChange(){
      //   // //console.log(this.PinXiNum);

      // },
      //保存
      getFormList(flag){
              var date = this.getNowTime();
              for(var i = 0 ;i < this.data.length ; ++i){
                this.data[i].modification_time = date;
              }

              
              var span1 = document.getElementById("span1");
              if(span1!=null){this.data[this.fieldRowIndex].field_3_2_2 = span1.innerHTML;}
              

              var span2 = document.getElementById("span2");
              if(span2 != null){this.data[this.fieldRowIndex].field_3_4_2 = span2.innerHTML;}
              

              var span3 = document.getElementById("span3");
              if(span3 != null){this.data[this.fieldRowIndex].field_3_6_2 = span3.innerHTML;}
              

              var span4 = document.getElementById("span4");
              if(span4 != null){this.data[this.fieldRowIndex].field_3_8_2 = span4.innerHTML;}
              

              var span5 = document.getElementById("span5");
              if(span5!= null){this.data[this.fieldRowIndex].field_3_10_2 = span5.innerHTML;}
              

              var span6 = document.getElementById("span6");
              if(span6!=null){this.data[this.fieldRowIndex].field_3_12_2 = span6.innerHTML;}
              

              var span7 = document.getElementById("span7");
              if(span7!=null){ this.data[this.fieldRowIndex].field_3_14_2 = span7.innerHTML;}
             

              var span8 = document.getElementById("span8");
              if(span8!=null){this.data[this.fieldRowIndex].field_3_16_2 = span8.innerHTML;}
              

              var span9 = document.getElementById("span9");
              if(span9!=null){this.data[this.fieldRowIndex].field_3_18_2 = span9.innerHTML;}
              

              var span10 = document.getElementById("span10");
              if(span10!=null){ this.data[this.fieldRowIndex].field_3_20_2 = span10.innerHTML;}
              

              var span21 = document.getElementById("editResult");
              if(span21!=null){this.data[this.fieldRowIndex].field_3_21_1 = span21.innerHTML;}
              
              var span22 = document.getElementById("editPercent");
              if(span22!=null){this.data[this.fieldRowIndex].field_3_21_2 = span22.innerHTML;}
              
            
              var list = this.data;
              var str = '';
              for(var i = 0 ; i < list.length ; ++i){
                  str += JSON.stringify(list[i]) + "#";
              }
              
              this.saveInputData(str);
              if(this.column_id != '' && this.tableName != ''){
                this.fixDataBackState(this.tableName,this.column_id);
              }
      },


      
        //填写详细信息
         fillDetailInfo(e){
           this.temData = [];
              var Index = e.target.parentNode.cellIndex;
              // //console.log(Index);
              this.fieldColIndex = Index;//把当前点击的字段所在列保存
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
              this.fieldRowIndex = this.fieldRowIndex-2;
    

              //根据temData中的数据自动生成对话框中的多选框品系数目
              this.checkPinXi = [];
              this.rawCheckPinXi = [];
              for(var i = 0 ; i < pinXiOptions1.length ; ++i){
                // this.checkPinXi = [];
                var name = pinXiOptions1[i].name;
                var firstInput = pinXiOptions1[i].firstInput;
                var secondInput = pinXiOptions1[i].secondInput;
                if(this.data[this.fieldRowIndex][firstInput]!=null && this.data[this.fieldRowIndex][firstInput]!=''){
                    this.checkPinXi.push(name);
                    this.rawCheckPinXi.push(name);
                    // //console.log(this.temData[0][firstInput]);
                }
              }
              //console.log(this.checkPinXi);
              var getTdInnerHTML="";    
              getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
              this.tableFlag = getTdInnerHTML;
              this.detialDialogVisible = true;
            },

            //暂存按钮
            getInputInfo(){
            //   var span1 = document.getElementById("span1");
            //   if(span1 != null){
            //     this.data[0].field_3_2_2 = span1.innerHTML;
            //   }
              
            //   var span2 = document.getElementById("span2");
            //   if(span2 != null){this.data[0].field_3_4_2 = span2.innerHTML;}
              
            //   var span3 = document.getElementById("span3");
            //   if(span3 != null){this.data[0].field_3_6_2 = span3.innerHTML;}
              
            //   var span4 = document.getElementById("span4");
            //   if(span4!=null){this.data[0].field_3_8_2 = span4.innerHTML;}
              
            //   var span5 = document.getElementById("span5");
            //   if(span5!=null){this.data[0].field_3_10_2 = span5.innerHTML;}
              
            //   var span6 = document.getElementById("span6");
            //   if(span6 != null){this.data[0].field_3_12_2 = span6.innerHTML;}
              
            //   var span7 = document.getElementById("span7");
            //   if(span7!=null){this.data[0].field_3_14_2 = span7.innerHTML;}
              
            //   var span8 = document.getElementById("span8");
            //   if(span8 != null){this.data[0].field_3_16_2 = span8.innerHTML;}
              
            //   var span9 = document.getElementById("span9");
            //   if(span9!=null){this.data[0].field_3_18_2 = span9.innerHTML;}
              
            //   var span10 = document.getElementById("span10");
            //   if(span10){this.data[0].field_3_20_2 = span10.innerHTML;}
              
            //   var span11 = document.getElementById("span11");
            //   if(span11!=null){this.data[0].field_3_22_2 = span11.innerHTML;}
              
            //   var span12 = document.getElementById("span12");
            //   if(span12!=null){this.data[0].field_3_24_2 = span12.innerHTML;}
              
            //   var span13 = document.getElementById("span13");
            //   if(span13!=null){this.data[0].field_3_26_2 = span13.innerHTML;}
              
            //   var span14 = document.getElementById("span14");
            //   if(span14!=null){this.data[0].field_3_28_2 = span14.innerHTML;}
              
            //   var span15 = document.getElementById("span15");
            //   if(span15!=null){this.data[0].field_3_30_2 = span15.innerHTML;}
              
            //   var span16 = document.getElementById("span16");
            //   if(span16!=null){this.data[0].field_3_32_2 = span16.innerHTML;}
              
            //   var span17 = document.getElementById("span17");
            //   if(span17!=null){this.data[0].field_3_34_2 = span17.innerHTML;}
              
            //   var span18 = document.getElementById("span18");
            //   if(span18!=null){this.data[0].field_3_36_2 = span18.innerHTML;}
              
            //   var span19 = document.getElementById("span19");
            //   if(span19!=null){this.data[0].field_3_38_2 = span19.innerHTML;}
              
            //   var span20 = document.getElementById("span20");
            //   if(span20!=null){this.data[0].field_3_40_2 = span20.innerHTML;}
              

            //   var edit = document.getElementById("editResult");
            //   if(edit!=null){this.data[0].field_3_41_1 = edit.innerHTML;}
              
            //   var editPercent = document.getElementById("editPercent");
            //   if(editPercent!=null){ this.data[0].field_3_41_2 = editPercent.innerHTML;}

                //console.log(this.data);
                this.detialDialogVisible = false;
            },
        checkFirstFieldInput(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){
                    
                }else{
                // e.target.parentNode.nextSibling.innerHTML = "X";
                e.target.value = "";
                alert("请输入数字！");
                }
        },
        //取消按钮
        cancelOp(){

            this.detialDialogVisible = false;
        },

         // 获取当前时间
         getNowTime: function () {
              let dateTime
              let yy = new Date().getFullYear()
              let mm = new Date().getMonth() + 1
              let dd = new Date().getDate()
              let hh = new Date().getHours()
              let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes()
                :
                new Date().getMinutes()
              let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds()
                :
                new Date().getSeconds()
                if(hh < 10){
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
                }else{
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                }
              return dateTime
            },

            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTableTwo2',
                method:'post',
                params:{
                  data:list
                }
              }).then(res=>{
                //console.log(res);
                this.$message.success("操作成功！");
              }).catch(err=>{
                return this.$message.error("数据保存失败！");
              })

            },

            //查询该用户下的所有数据
            getUserAllData(people,column_id){
              axios({
                url:'/getUserAllDataTwo',
                method:'post',
                params:{
                  people:people,
                  column_id:column_id
                }
              }).then(res=>{
                // //console.log(res.data);
                this.data = res.data;
                //console.log(this.data);
              }).catch(err=>{
                return this.$message.error();}
              );
            },

        
    },
    created(){
        var people = localStorage.getItem('ms_username');
        if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
          this.column_id = this.$route.query.column_id;
          this.tableName = this.$route.query.record_table;
        }
        this.getUserAllData(people,this.column_id);
    },
    mounted(){

    },
    watch:{
      // 'data.field_2':'clearRespondingInput'//一旦改变field_2的输入值，对话框中相对应的输入值全部清空
    },
}
</script>
<style scoped>
  .el-checkbox-width{
  width: 11%;
  height: 25px;
}
.el-checkbox{
  margin-left: 2.4%;
}
.el-dialog {
    top: 5%;
    left: 2%;
}
</style>