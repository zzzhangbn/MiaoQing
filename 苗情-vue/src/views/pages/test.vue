<template>
    <div>
        <el-button @click="addlistVisible = 'true'">打开</el-button>
        <el-dialog
            title="添加"
            :visible.sync="addlistVisible"
            width="920px"
        >
            <div>
            <el-form :model="form">
                <div>
                    <el-form-item :label-width="formLabelWidth">
                        <el-select
                        v-model="form.addschool"
                        filterable
                        remote
                        style="width: 230px"
                        reserve-keyword
                        placeholder="请输入关键词"
                        :remote-method="remoteMethod2"
                        :loading="loading"
                        @focus="repeatList"
                        clearable="true"
                        @change="handleSelectBranchCom2"
                        >
                        <el-option
                            v-for="item in addschoolname"
                            :key="item.value"
                            :label="item.label"
                            :value="item"
                        >
                        </el-option>
                        </el-select>
                        <el-input
                        style="width: 180px"
                        v-model="form.addzy"
                        placeholder="请输入专业"
                        autocomplete="off"
                        ></el-input>
                        <el-input
                        style="width: 150px"
                        v-model="form.addnumber"
                        placeholder="请输入安置人数"
                        autocomplete="off"
                        ></el-input>
                        <el-date-picker
                        v-model="form.addazdate"
                        type="month"
                        placeholder="选择安置日期"
                        format="yyyy 年 MM 月"
                        value-format="yyyy-MM-dd"
                        >
                        </el-date-picker>
                    </el-form-item>
                </div>
                <div v-for="(ddd, index) in counter">
                    <el-form-item :label-width="formLabelWidth">
                        <el-input
                        style="width: 230px"
                        v-model="addequallyname"
                        :disabled="true"
                        placeholder="请输入学校"
                        autocomplete="off"
                        ></el-input>
                        <!--这里v-model绑定要 ddd.zyname   才能确保动态添加的所绑定的数值不一样-->
                        <el-input
                        style="width: 180px"
                        v-model="ddd.zyName"
                        placeholder="请输入专业"
                        autocomplete="off"
                        ></el-input>
                        <el-input
                        style="width: 150px"
                        v-model="ddd.azCount"
                        placeholder="请输入安置人数"
                        autocomplete="off"
                        ></el-input>
                        <el-date-picker
                        v-model="ddd.azDate"
                        type="month"
                        placeholder="选择安置日期"
                        format="yyyy 年 MM 月"
                        value-format="yyyy-MM-dd"
                        >
                        </el-date-picker>
                    </el-form-item>
                </div>
                <div class="ujm">
                    <el-button class="addstyle" type="info" @click="addinput"
                        >新增信息</el-button
                    >
                </div>
            </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
            <!--<el-button @click="addkkk">看一下数据</el-button>-->
            <el-button @click="addlistVisible = false">取 消</el-button>
            <el-button type="primary" @click="determineadd">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return {
            addlistVisible:true,
            //添加要循环的为空
            counter:[],
            form:[]
        }
    },
    //写方法
    methods:{
        //现在是动态创建input里所需要的值，既然是添加多条 就是要push添加对象
        addinput() {
                this.counter.push({ 'zyName': '', 'azCount': '', 'azDate': '' })
        },
    }
};
</script>

<style>
</style>