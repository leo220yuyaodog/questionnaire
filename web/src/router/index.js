import { createRouter, createWebHistory } from 'vue-router';

const routers = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/auth/Login.vue'),
  },
  {
    path: '/collection',
    name: 'collection',
    component: () => import('@/views/collectionpage/MainPageView/Collection.vue'),
  },
  {
    path: '/create/:id',
    name: 'create',
    component: () => import('@/views/createpage/CreatePageView/Create.vue'),
  },
  {
    path: '/help',
    name: 'help',
    component: () => import('@/views/helppage/Help.vue'),
  },
  {
    path: '/analysis/:id',
    name: 'analysis',
    component: () => import('@/views/collectionpage/AnalysisPageView/Analysis.vue'),
  },
  {
    path: '/fillin/:id',
    name: 'fillin',
    component: () => import('@/views/fillinpage/FillInPageView/FillIn.vue'),
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routers,
});

export default router;
