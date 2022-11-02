<template>
  <div>
    <el-checkbox
      v-model="checkAll"
      :indeterminate="isIndeterminate"
      @change="handleCheckAllChange"
    >全选
    </el-checkbox>
    <el-checkbox-group v-model="checkedList" @change="handleCheckedCitiesChange">
      <el-checkbox v-for="item in checkOptions" :key="item" :label="item">{{ translateLabel[item] }}</el-checkbox>
    </el-checkbox-group>
  </div>
</template>
<script>
const options = ['collecting', 'editing', 'closed']
export default {
  data() {
    return {
      checkAll: false,
      checkedList: ['collecting', 'editing', 'closed'],
      translateLabel: {
        'collecting': '收集中',
        'editing': '编辑中',
        'closed': '已关闭'
      },
      checkOptions: options,
      isIndeterminate: true
    }
  },
  updated() {
    console.log(this.checkedList)
    this.$emit('changeShow', this.checkedList)
  },
  methods: {
    handleCheckAllChange(val) {
      this.checkedList = val ? options : []
      this.isIndeterminate = false
    },
    handleCheckedCitiesChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.checkOptions.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.checkOptions.length
    }
  }
}
</script>
<style scoped>

</style>
