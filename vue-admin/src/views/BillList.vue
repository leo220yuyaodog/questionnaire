<template>
  <el-main>
    <el-form
      :model="parms"
      ref="searchForm"
      label-width="80px"
      :inline="true"
      size="small"
    >
      <el-form-item label="租户名">
        <el-input v-model="parms.tenant"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" @click="searchBtn">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :height="tableHeight"
      :data="filPersons"
      border
      style="width: 100%"
      stripe
    >
      <el-table-column
        fixed
        prop="tenant"
        label="租户"
        width="180">
      </el-table-column>
      <el-table-column
        prop="group"
        label="群组费用"
        width="180">
      </el-table-column>
      <el-table-column
        prop="questionnaire"
        label="问卷费用"
        width="180">
      </el-table-column>
      <el-table-column
        prop="answer"
        label="答卷费用"
        width="180">
      </el-table-column>
      <el-table-column
        prop="total"
        label="总计"
        width="280">
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="sizeChange"
      @current-change="currentChange"
      :current-page.sync="parms.currentPage"
      :page-sizes="[10, 20, 40, 80, 100]"
      :page-size="parms.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="parms.total"
      background
    >
    </el-pagination>
  </el-main>
</template>

<script>

export default {
  data() {
    return {
      tableHeight: 0,
      tableBill: [{
        tenant: '张一',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }, {
        tenant: '张二',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }, {
        tenant: '张三',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }, {
        tenant: '张四',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }, {
        tenant: '张五',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }, {
        tenant: '张六',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }, {
        tenant: '张七',
        group: '100',
        questionnaire: '100',
        answer: '100',
        total: '300'
      }],
      parms: {
        tenant: '',
        currentPage: 1,
        pageSize: 10,
        total: 7
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 200
    })
  },
  methods: {
    searchBtn() {
      this.getList()
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
    }
  },
  computed: {
    // eslint-disable-next-line vue/return-in-computed-property
    filPersons() {
      return this.tableBill.filter((p) => {
        return p.tenant.indexOf(this.parms.tenant) !== -1
      })
    }
  }
}
</script>
