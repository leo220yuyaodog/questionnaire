<template>
  <div>
    <el-card shadow="hover" @click.native="dialogOutlineDataVisible = true">
      <div class="card-inner">
        <div class="card-title">
          {{ title }}
        </div>
        <div class="card-state">
          {{ translateLabel[status] }}
        </div>
        <div class="card-time">
          {{ createTime }}
        </div>
      </div>
    </el-card>
    <el-dialog :title="title" :visible.sync="dialogOutlineDataVisible">
      <div>
        问卷链接为：
        <el-link
          class="copy-link"
          target="_blank"
          :data-clipboard-text="servername+'/fill/index/'+this.id"
          data-clipboard-action="copy"
          type="primary"
          @click="copy"
        >
          {{ title }}-点击复制
        </el-link>
      </div>
      <div class="description-box">
        <div>
          <b>问卷描述：</b>{{ description }}
        </div>
        <div>
          <b>问卷状态：</b>{{ translateLabel[status] }}
        </div>
        <div>
          <b>填写人数：</b>{{ fillCount }}
        </div>
        <div>
          <b>发布时间：</b>{{ createTime }}
        </div>
        <div>
          <b>截止时间：</b>{{ endTime }}
        </div>
      </div>

      <el-dialog
        width="30%"
        title="确认删除？"
        :visible.sync="deleteVisible"
        append-to-body
        center
      >
        <span slot="footer" class="dialog-footer">
          <el-button @click="deleteVisible=false">取 消</el-button>
          <el-button type="danger" @click="deleteQuestionnaire">确认删除</el-button>
        </span>

      </el-dialog>
      <el-button type="danger" icon="el-icon-delete" @click="deleteVisible = true">删 除</el-button>

      <el-button v-if="status==='collecting'" icon="el-icon-close" @click="closeForm">关 停</el-button>

      <el-button v-if="status==='collecting'||status==='closed'" icon="el-icon-search" @click="gotoFillIn">预 览
      </el-button>

      <el-button
        v-if="status==='collecting'||status==='closed'"
        type="primary"
        icon="el-icon-s-data"
        @click="gotoAnalysis"
      >分 析
      </el-button>
      <el-button v-if="status==='editing'" type="primary" icon="el-icon-edit" @click="gotoEdit">编 辑</el-button>

    </el-dialog>
  </div>
</template>

<script>
import Clipboard from "clipboard"
import * as config from "@/config"
import { ServerUrl } from "@/config"
export default {
  name: "QuestionnaireCard",
  props: {
    id: Number,
    title: String,
    status: String,
    createTime: String,
    endTime: String,
    fillCount: Number,
    description: String
  },
  data: function() {
    return {
      servername: config.ServerUrl,
      dialogOutlineDataVisible: false,
      deleteVisible: false,
      translateLabel: {
        "collecting": "收集中",
        "editing": "编辑中",
        "closed": "已关闭"
      }
    }
  },
  methods: {
    gotoAnalysis() {
      const success = this.$router.push("/analysis/index/" + this.id)
      if (!success) {
        console.log("analysis error")
      }
    },
    gotoEdit() {
      const success = this.$router.push("/create/index/" + this.id)
      if (!success) {
        console.log("analysis error")
      }
    },

    deleteQuestionnaire() {
      this.deleteVisible = false
      this.dialogOutlineDataVisible = false
      this.axios.get(ServerUrl + "/api/deleteQuestionnaire", { params: { questionnaireId: this.id }}).then((response) => {
        console.log(response)
        this.$emit("deleteQuestionnaire")
      })
    },
    copy() {
      const clipboard = new Clipboard(".copy-link")
      clipboard.on("success", e => {
        console.log("复制成功")
        this.$message({ message: "复制成功", duration: 1000 })
        // 释放内存
        clipboard.destroy()
      })
      clipboard.on("error", e => {
        // 不支持复制
        console.log("该浏览器不支持自动复制")
        // 释放内存
        clipboard.destroy()
      })
    },

    gotoFillIn() {
      const routeData = this.$router.resolve("/fill/index/" + this.id)
      window.open(routeData.href, "_blank")
    },
    closeForm() {
      this.axios.post(ServerUrl + "/api/closeQuestionnaire?questionnaireId=" + this.id).then(() => {
        this.$message({ message: "已成功关闭", duration: 1000 })
      }).catch(() => {
        this.$message.error({ message: "error！关闭失败！", duration: 1000 })
      })
    }
  }
}
</script>

<style scoped>
    .description-box {
        text-align: left;
        font-size: 14px;
        line-height: 35px;
        margin-left: 10%;
        margin-bottom: 15px;
        margin-top: 15px;
    }

    .el-card {
        width: 150px;
        height: 200px;
        margin: 10px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-width: 2px;
        -webkit-user-select: none;
        cursor: pointer;
    }

    .card-inner {
        background-color: rgba(128, 128, 128, 0.1);
        width: 140px;
        height: 190px;
        position: relative;
    }

    .card-title {
        padding-top: 25px;
        font-size: 16px;
        font-weight: bold;
        color: black;
        overflow: hidden;
        width: 75%;
        margin: auto;
    }

    .card-time {
        position: absolute;
        font-size: 10px;
        color: black;
        bottom: 15px;
        width: 100%;
    }

    .card-state {
        position: absolute;
        font-size: 14px;
        color: black;
        bottom: 40px;
        width: 100%;
    }

</style>
