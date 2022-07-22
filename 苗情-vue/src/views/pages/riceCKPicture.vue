<template>
  <!-- 监测点图片资料库 -->
  <div style="height: 500px; width: 80%; margin: 0 auto">
    <el-card>
      <el-alert title="监测点图片信息" type="info" center show-icon> </el-alert>

      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="县名：">
          <el-input v-model="form.country" placeholder="肥东县"></el-input>
        </el-form-item>
        <el-form-item label="监测点代号：">
          <el-input v-model="form.code" placeholder="001"></el-input>
        </el-form-item>
        <el-form-item label="苗情描述：">
          <el-input v-model="form.description" maxlength="150" placeholder="苗情涨势良好，无明显病虫害，水文正常" show-word-limit type="textarea" />
          <!-- <el-input v-model="form.description"></el-input> -->
        </el-form-item>
        <el-form-item label="苗情图片：">
          <el-upload class="upload-demo" action="/uploadCKPic" :file-list="form.pro_picList"
            list-type="picture" :http-request="picUpload">
            <el-button size="small" type="primary">上传图片</el-button>
            <div slot="tip" class="el-upload__tip">
              只能上传jpg/png文件
            </div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">确认添加</el-button>
          <el-button @click="cancelUpload">撤销/清空</el-button>
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
          description: "",
          country: "",
          code: "",
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
        // alert(this.description)
        if (this.form.lastname != this.form.country && this.form.country != "") {
          this.form.lastname = this.form.country;
          axios({
            url: "/addCKPicInfor",
            method: "post",
            params: {
              description: this.form.description,
              country: this.form.country,
              code: this.form.code,
              picUrl: this.form.picUrl
            },
          }).then((res) => {
            alert("监测点图片数据库添加成功！");
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
          url: '/uploadCKPic',
          data: formData,
          method: 'post',
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          alert(res.data.msg)
          console.log(res);
          this.form.pro_picList[0] = res.data;
          this.form.picUrl = res.data.url;
        }).catch(res => {
          alert(res.data.msg);
        })
      },

      cancelUpload() {
        this.form = {}

      }

    },
  };
</script>

<style>
</style>