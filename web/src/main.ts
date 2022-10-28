import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './registerServiceWorker'
import router from './router'
import store from './store'
import '@/App.css'

Vue.config.productionTip = false
Vue.prototype.servername = 'http://39.107.66.30'
// 'http://kforquestionnaire.xyz'
Vue.use(ElementUI)

axios.defaults.baseURL = '/'

Vue.use(VueAxios, axios)

const config = {
    serverUrl: "https://bit.windsparkle.top/",
    clientId: "d523344d9ee6890afacf",
    organizationName: "Star",
    appName: "Star-Survey",
    redirectPath: "/",
};

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')




