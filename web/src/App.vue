<template>
    <div id="app">
        <div v-if="this.$route.name==='fillin'">
            <router-view></router-view>
        </div>
        <div v-else style="height: 100%">
            <el-backtop></el-backtop>
            <el-container>
                <el-header>
                    <Header></Header>
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
                <el-footer>
                    <Footer></Footer>
                </el-footer>
            </el-container>
        </div>
    </div>
</template>

<script>
    import Header from "./components/Header";
    import Footer from "./components/Footer";

    export default {
        components: {Footer, Header}
    }
</script>
