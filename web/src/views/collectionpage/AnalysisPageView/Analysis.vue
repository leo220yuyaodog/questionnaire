<template>
  <div>
    <div class="outlineData">
      <MainAnalysisList> </MainAnalysisList>
    </div>

    <el-divider></el-divider>
    <div class="questionList"></div>
  </div>
</template>

<script>
import MainAnalysisList from './MainAnalysisList';

export default {
  name: 'AnalysisPage',
  components: { MainAnalysisList },
};
</script>

<style scoped></style>
