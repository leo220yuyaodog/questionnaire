<template>
  <el-main>
    <el-form
      ref="searchForm"
      :model="parms"
      label-width="80px"
      :inline="true"
      size="small"
    >
      <el-form-item label="问题">
        <el-input v-model="parms.id" />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" @click="">搜索</el-button>
        <el-button type="primary" icon="el-icon-plus" @click="addBtn">新增问题</el-button>
      </el-form-item>
      <el-upload
        action=""
        accept=".xls,.xlsx"
        :auto-upload="false"
        :on-change="inputFile"
        :show-file-list="false"
      >
        <el-button size="small" type="primary" icon="el-icon-folder-add" style="margin-bottom: 5px">点击上传</el-button>
      </el-upload>
    </el-form>

    <el-table
      :height="tableHeight"
      :data="filPersons"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="选项">
              <span>{{ props.row.answerer }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="问题"
        prop="id"
      />
      <el-table-column
        label="类型"
        prop="name"
      />
      <el-table-column
        label="标签"
        prop="desc"
      />
      <el-table-column align="center" width="180" label="操作">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            type="primary"
            size="small"
            @click="editBtn(scope.row)"
          >编辑
          </el-button>
          <el-button
            icon="el-icon-delete"
            type="danger"
            size="small"
            @click="deleteBtn(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      :current-page.sync="parms.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="parms.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="parms.total"
      background
      @size-change="sizeChange"
      @current-change="currentChange"
    />

    <!-- 新增弹框 -->
    <sys-dialog
      :title="addDialog.title"
      :height="addDialog.height"
      :width="addDialog.width"
      :visible="addDialog.visible"
      @onClose="onClose"
      @onConfirm="onConfirm"
    >
      <template slot="content">
        <el-form
          ref="addForm"
          :model="addModel"
          label-width="80px"
          :inline="true"
          size="small"
        >
          <el-form-item prop="phone" label="问题">
            <el-input v-model="addModel.id" />
          </el-form-item>
          <el-form-item prop="email" label="类型">
            <el-input v-model="addModel.name" />
          </el-form-item>
          <el-form-item prop="username" label="标签">
            <el-input v-model="addModel.desc" />
          </el-form-item>
          <el-form-item prop="password" label="选项">
            <el-input v-model="addModel.answerer" />
          </el-form-item>
        </el-form>
      </template>
    </sys-dialog>
  </el-main>
</template>

<style>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

<script>
import { nanoid } from "nanoid"
import SysDialog from "@/components/system/SysDialog"
import { MessageBox, Message } from "element-ui"
import xlsx from "xlsx"

export default {
  components: {
    SysDialog
  },
  data() {
    return {
      tableHeight: 0,
      rules: {
        id: [
          {
            trigger: "change",
            message: "请填写问题名称",
            required: true
          }
        ],
        name: [
          {
            trigger: "change",
            message: "请填写问题介绍",
            required: true
          }
        ],
        desc: [
          {
            trigger: "change",
            message: "请填写问题标签",
            required: true
          }
        ],
        answerer: [
          {
            trigger: "change",
            message: "请填写选项",
            required: true
          }
        ]
      },
      // tableGroup: [],
      tableGroup: [{
        unique: nanoid(),
        id: "请写出常用数据库操作关键字",
        name: "填空题",
        desc: "数据库",
        answerer: ""
      }, {
        unique: nanoid(),
        id: "请选择数据库中表示选择的关键字",
        name: "单选题",
        desc: "数据库",
        answerer: "A. SELECT B.GROUP C.BY D.DELETE"
      }],
      parms: {
        id: "",
        currentPage: 1,
        pageSize: 10,
        total: 2
      },
      // 新增表单绑定的数据域
      addModel: {
        unique: "",
        id: "",
        name: "",
        desc: "",
        answerer: "",
        editType: "" // 0：新增 1：编辑
      },
      addDialog: {
        title: "",
        height: 180,
        width: 650,
        visible: false
      }
    }
  },
  computed: {
    // eslint-disable-next-line vue/return-in-computed-property
    filPersons() {
      return this.tableGroup.filter((p) => {
        return p.id.indexOf(this.parms.id) !== -1
      })
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 200
    })
  },
  methods: {
    readFile(file) {
      return new Promise(resolve => {
        const reader = new FileReader()
        reader.readAsBinaryString(file)
        reader.onload = ev => {
          resolve(ev.target.result)
        }
      })
    },
    async inputFile(e) {
      console.log(e)
      this.inputFileName = e.name
      const file = e.raw
      if (!file) return
      // console.log(file)
      const data = await this.readFile(file)
      // console.log(data) //解析出的二进制文件
      let eleData = xlsx.read(data, { type: "binary" })
      // console.log(eleData)
      const eleDataSheet = eleData.Sheets[eleData.SheetNames[0]]
      eleData = xlsx.utils.sheet_to_json(eleDataSheet) // 将解析出的数据转换为json格式（xlsx自带的方法）
      console.log(eleData)
      eleData.forEach((row) => {
        const obj = {
          unique: nanoid(),
          id: row.id,
          name: row.name,
          desc: row.desc,
          answerer: row.answerer,
          editType: "0" // 0：新增 1：编辑
        }
        this.filPersons.push(obj)
        this.parms.total++
      })
    },
    currentChange(val) {
      console.log(val)
      this.parms.currentPage = val
      // 重新获取列表
      this.getList()
    },
    // 页容量改变时触发
    sizeChange(val) {
      console.log(val)
      this.parms.pageSize = val
      this.parms.currentPage = 1
      this.getList()
    },
    editBtn(row) {
      console.log(row)
      // 表单的清空
      this.resetForm("addForm", this.addModel)
      // 设置弹框属性
      this.addDialog.title = "编辑问题"
      this.addDialog.visible = true
      // 设置要编辑的数据回显
      // 把当前要编辑的数据设置到表单绑定的数据域
      this.objCoppy(row, this.addModel)
      console.log(this.addModel)
      // 设置为编辑
      this.addModel.editType = "1"
      console.log("编辑成功")
    },
    resetForm(formName, obj) {
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
      }
      // 清空表单数据域
      Object.keys(obj).forEach(key => {
        obj[key] = ""
      })
    },
    myconfirm(text) {
      return new Promise((resolve, reject) => {
        MessageBox.confirm(text, "系统提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          resolve(true)
        }).catch(() => {
          Message.warning("已取消")
          reject(false)
        })
      }).catch(() => {

      })
    },

    objCoppy(obj1, obj2) {
      Object.keys(obj2).forEach(key => {
        obj2[key] = obj1[key]
      })
    },
    addBtn() {
      // // 表单的清空
      this.resetForm("addForm", this.addModel)
      this.addDialog.title = "新增问题"
      this.addDialog.visible = true
      this.addModel.editType = "0"
      this.parms.total++
      console.log("添加成功")
    },
    async deleteBtn(row) {
      console.log(row)
      // 信息确认提示
      const confirm = await this.myconfirm("确定删除该数据吗?")
      console.log(confirm)
      if (confirm) {
        this.tableGroup = this.tableGroup.filter(group => group.id !== row.id)
      }
      this.parms.total--
      console.log("删除成功")
    },
    onConfirm() {
      if (this.addModel.editType === "0") {
        // 新增
        const obj = {
          unique: nanoid(),
          id: this.addModel.id,
          name: this.addModel.name,
          desc: this.addModel.desc,
          answerer: this.addModel.answerer
        }
        this.filPersons.push(obj)
      } else {
        this.filPersons.forEach((group) => {
          if (group.unique === this.addModel.unique) {
            group.id = this.addModel.id
            group.name = this.addModel.name
            group.desc = this.addModel.desc
            group.answerer = this.addModel.answerer
          }
        })
        console.log("成功")
      }
      this.addDialog.visible = false
    },
    onClose() {
      this.addDialog.visible = false
    }
  }
}
</script>
