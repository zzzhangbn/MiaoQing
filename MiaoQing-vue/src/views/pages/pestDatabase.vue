<template>
  <!-- 病虫害资料库 -->
  <div style="height: 500px; width: 80%; margin: 0 auto">
    <el-card>
      <el-alert title="病虫害资料库" type="info" center show-icon> </el-alert>

      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="病虫害名称：">
          <el-input v-model="form.pestName" placeholder = "稻瘟病"></el-input>
        </el-form-item>
        <el-form-item label="表现症状：">
          <el-input v-model="form.performance" maxlength="150" placeholder="秧苗变褐，卷缩枯死，病苗表面常生绿色霉层" show-word-limit type="textarea" />

          <!-- <el-input v-model="form.performance"></el-input> -->
        </el-form-item>
        <el-form-item label="建议用药：">
          <el-input v-model="form.drugName" placeholder="三环唑、春雷霉素、稻瘟酰胺、稻瘟灵"></el-input>
        </el-form-item>
        <el-form-item label="图片添加：">
          <el-upload class="upload-demo" action="/uploadPestPic" :file-list="form.pro_picList"
            list-type="picture" :http-request="picUpload">
            <el-button size="small" type="primary">上传图片</el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件
            </div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认添加</el-button>
          <el-button @click = "cancelUpload">撤销/清空</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import axios from "axios";
  export default {
    data() {
      return {
        form: {
          performance: "",
          pestName: "",
          drugName: "",
          imageUrl: "",
          pro_picList: [],
          picUrl: '',
          lastname: '',
        },
      };
    },
    created() {},
    methods: {
      //通过作物类型获取监测点数据
      onSubmit() {
        // alert(this.performance)
        if (this.form.lastname != this.form.pestName && this.form.pestName != "" ) {
          this.form.lastname = this.form.pestName;
          axios({
            url: "/addPestInfor",
            method: "post",
            params: {
              performance: this.form.performance,
              pestName: this.form.pestName,
              drugName: this.form.drugName,
              picUrl: this.form.picUrl
            },
          }).then((res) => {
            alert("病虫害数据库添加成功！");
            var value = res.data;
          });
        } else {
          if (this.form.lastname != '') {
            alert("重复录入！")
          } else {
            alert("请填写完整信息！");
          }
        }

      },

      //文件的上传
      picUpload(content) {
        const formData = new FormData()
        formData.append('file', content.file)
        formData.append('overwrite', false)
        axios({
          url: '/uploadPestPic',
          data: formData,
          method: 'post',
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          alert(res.data.msg)
          console.log(res);
          this.form.pro_picList[0]=res.data;
          this.form.picUrl = res.data.url;
        }).catch(res => {
          alert(res.data.msg);
        })
      },
    
      cancelUpload(){
        this.form = {}

      }
    
    },
  };
</script>

<style>
</style>