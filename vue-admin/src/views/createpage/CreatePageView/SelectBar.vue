<template>
  <div class="select-menu">
    <el-row>
      <el-col class="select-item" @click.native="addNewQuestion('single_check')">
        <i class="el-icon-circle-check" />
        <p>单选</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('multi_check')">
        <i class="el-icon-edit-outline" />
        <p>多选</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('single_line_text')">
        <i class="el-icon-document-remove" />
        <p>单行文本</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('multi_line_text')">
        <i class="el-icon-document" />
        <p>多行文本</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('number')">
        <i class="el-icon-s-data" />
        <p>数字</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('grade')">
        <i class="el-icon-star-off" />
        <p>评分</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('date')">
        <i class="el-icon-date" />
        <p>日期</p>
      </el-col>
      <el-col class="select-item" @click.native="addNewQuestion('text_description')">
        <i class="el-icon-warning-outline" />
        <p>文本描述</p>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "SelectBar",
  methods: {
    addNewQuestion(type) {
      this.$emit("addNewQuestion", type)
    }
  }
}
</script>

<style scoped>
    .select-menu {
      margin: 20px 30px 30px 20px;
      width: 200px;
      background-color: white;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    }

    .select-item {
      width: 100px;
      padding-top: 15px;
      border-width: 1px;
      border-style: solid;
      border-color: rgba(0, 0, 0, 0.05);
      transition: all ease 300ms;
      cursor: pointer;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-content: center;
      align-items: center;
    }

    .select-item:hover {
        width: 100px;
        box-shadow: 0 2px 10px 2px rgba(0, 0, 0, 0.1);
    }

</style>
