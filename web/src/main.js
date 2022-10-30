import { createApp } from 'vue';

import App from './App.vue';
import router from './router';
import store from './store';

import '@/assets/main.css';
import '@/style/index.less';

const config = {
  serverUrl: 'https://bit.windsparkle.top/',
  clientId: 'd523344d9ee6890afacf',
  organizationName: 'Star',
  appName: 'Star-Survey',
  redirectPath: '/',
};

createApp(App).use(router).use(store).mount('#app');
