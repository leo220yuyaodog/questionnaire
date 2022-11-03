<!--用来测试路由的-->
<template>
  <div class="demo">
    <div>
      <h2>学生名称{{ name }}</h2>
      <h2>学生年龄{{ age }}</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "School",
  data() {
    return {
      name: 'szy',
      age: 20,
    };
  },
};

</script>

<style>
.demo {
  background-color:cornflowerblue;
}
</style>
