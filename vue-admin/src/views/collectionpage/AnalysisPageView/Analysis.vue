<template>
  <div>
    <div class="outlineData">
      <MainAnalysisList />
    </div>

    <el-divider />
    <div class="questionList" />
  </div>
</template>

<script>
import MainAnalysisList from "./MainAnalysisList"

export default {
  name: "Analysis",
  components: { MainAnalysisList }
}
</script>

<style scoped>

</style>
