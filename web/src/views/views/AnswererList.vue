<template>
  <el-main>
    <el-form
      ref="searchForm"
      :model="parms"
      label-width="80px"
      :inline="true"
      size="small"
    >
      <el-form-item label="答者列表">
        <el-input v-model="parms.id" />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" @click="searchBtn">搜索</el-button>
        <el-button type="primary" icon="el-icon-plus" @click="addBtn">新增答者</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :height="tableHeight"
      :data="filPersons"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column
        label="账户"
        prop="id"
      />
      <el-table-column
        label="电话"
        prop="phone"
      />
      <el-table-column
        label="邮箱"
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
          <el-form-item prop="id" label="账户">
            <el-input v-model="addModel.id" />
          </el-form-item>
          <el-form-item prop="phone" label="电话">
            <el-input v-model="addModel.phone" />
          </el-form-item>
          <el-form-item prop="email" label="邮箱">
            <el-input v-model="addModel.desc" />
          </el-form-item>
          <el-form-item prop="answerer" label="密码">
            <el-input v-model="addModel.answerer" />
          </el-form-item>
        </el-form>
      </template>
    </sys-dialog>
  </el-main>
</template>

<style>

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

<script>
import { nanoid } from 'nanoid'
import SysDialog from '@/components/system/SysDialog.vue'
import { Message, MessageBox } from 'element-ui'

export default {
  components: {
    SysDialog
  },
  data() {
    return {
      tableHeight: 0,
      rules: {
        phone: [
          {
            trigger: 'change',
            message: '请填写手机',
            required: true
          }
        ],
        email: [
          {
            trigger: 'change',
            message: '请填写电话',
            required: true
          }
        ],
        username: [
          {
            trigger: 'change',
            message: '请填写邮箱',
            required: true
          }
        ],
        password: [
          {
            trigger: 'change',
            message: '请填写密码',
            required: true
          }
        ]
      },
      tableGroup: [{
        unique: nanoid(),
        id: 'aa',
        phone: '18276562331',
        desc: '163@qq.com'
      }, {
        unique: nanoid(),
        id: 'qq',
        phone: '18342421414',
        desc: '178@qq.com'
      }, {
        unique: nanoid(),
        id: 'dd',
        phone: '18726332111',
        desc: '166@qq.com'
      }, {
        unique: nanoid(),
        id: 'answerer',
        phone: '18912318872',
        desc: '136@qq.com'
      }
      ],
      parms: {
        id: '',
        currentPage: 1,
        pageSize: 10,
        total: 4
      },
      // 新增表单绑定的数据域
      addModel: {
        unique: '',
        id: '',
        phone: '',
        desc: '',
        answerer: '',
        editType: '' // 0：新增 1：编辑
      },
      addDialog: {
        title: '',
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
    resetForm(formName, obj) {
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields()
      }
      // 清空表单数据域
      Object.keys(obj).forEach(key => {
        obj[key] = ''
      })
    },
    myconfirm(text) {
      return new Promise((resolve, reject) => {
        MessageBox.confirm(text, '系统提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          resolve(true)
        }).catch(() => {
          Message.warning('已取消')
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
    searchBtn() {
      // this.getList()
    },
    currentChange(val) {
      console.log(val)
      this.parms.currentPage = val
      // 重新获取列表
      // this.getList()
    },
    sizeChange(val) {
      console.log(val)
      this.parms.pageSize = val
      this.parms.currentPage = 1
    },
    editBtn(row) {
      console.log(row)
      // 表单的清空
      this.resetForm('addForm', this.addModel)
      // 设置弹框属性
      this.addDialog.title = '编辑答者'
      this.addDialog.visible = true
      this.objCoppy(row, this.addModel)
      console.log(this.addModel)
      this.addModel.editType = '1'
      console.log('编辑成功')
    },
    addBtn() {
      // // 表单的清空
      console.log(this.addModel)
      this.resetForm('addForm', this.addModel)
      this.addDialog.title = '新增答者'
      this.addDialog.visible = true
      this.addModel.editType = '0'
      this.parms.total++
      console.log('添加成功')
    },
    async deleteBtn(row) {
      console.log(row)
      // 信息确认提示
      const confirm = await this.myconfirm('确定删除该答者吗?')
      console.log(confirm)
      if (confirm) {
        this.tableGroup = this.tableGroup.filter(group => group.id !== row.id)
      }
      this.parms.total--
      console.log('删除成功')
    },
    onConfirm() {
      if (this.addModel.editType === '0') {
        // 新增
        const obj = {
          unique: nanoid(),
          id: this.addModel.id,
          phone: this.addModel.phone,
          desc: this.addModel.desc
        }
        this.filPersons.push(obj)
      } else {
        this.filPersons.forEach((group) => {
          if (group.unique === this.addModel.unique) {
            group.id = this.addModel.id
            group.phone = this.addModel.phone
            group.desc = this.addModel.desc
          }
        })
        console.log('成功')
      }
      this.addDialog.visible = false
    },
    onClose() {
      this.addDialog.visible = false
    }
  }
}
</script>
