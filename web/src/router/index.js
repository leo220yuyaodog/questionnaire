import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/callback',
    component: () => import('@/views/login/callback'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  {
    component: Layout,
    hidden: true,
    path: '/analysis',
    children: [{
      path: 'index/:id',
      component: () => import('@/views/collectionpage/AnalysisPageView/MainAnalysisList.vue'),
      name: 'analysis',
      meta: {
        title: '答卷分析',
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
  {
    component: Layout,
    path: '/fill',
    hidden: true,
    children: [{
      path: 'index/:id',
      component: () => import('@/views/fillPage/FillIn'),
      name: 'Fill',
      meta: {
        title: '问卷填写',
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  {
    path: '/user',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'User',
        component: () => import('@/views/UserList'),
        meta: {
          title: 'User',
          icon: 'form',
          roles: ['admin']
        }
      }
    ]
  },
  {
    component: Layout,
    path: '/',
    children: [{
      path: 'group',
      component: () => import('@/views/GroupList'),
      name: 'Group',
      meta: {
        roles: ['editor'],
        title: '群组管理',
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
  {
    component: Layout,
    path: '/',
    children: [{
      path: 'tenant',
      component: () => import('@/views/TenantList'),
      name: 'Tenant',
      meta: {
        roles: ['super'],
        title: '租户管理',
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
  {
    component: Layout,
    path: '/',
    children: [{
      path: 'answerer',
      component: () => import('@/views/AnswererList'),
      name: 'answerer',
      meta: {
        roles: ['editor'],
        title: '答者管理',
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
  {
    path: '/bill',
    component: Layout,
    redirect: '/bill',
    name: 'bill',
    children: [
      {
        path: 'index',
        name: 'Table',
        component: () => import('@/views/BillList'),
        meta: { title: '计费管理', icon: 'table', roles: ['editor']}
      }
    ]
  },
  {
    component: Layout,
    hidden: true,
    path: '/create',
    children: [{
      path: 'index/:id',
      component: () => import('@/views/createpage/CreatePageView/Create.vue'),
      name: 'create',
      meta: {
        title: '创建问卷',
        noCache: true,// 不会被 <keep-alive> 缓存
        roles: ['editor']
      }
    }]
  },
  {
    component: Layout,
    path: '/collection',
    children: [{
      path: 'index',
      component: () => import('@/views/collectionpage/MainPageView/Collection.vue'),
      name: 'collection',
      meta: {
        title: '问卷管理',
        noCache: true, // 不会被 <keep-alive> 缓存
        roles: ['editor']
      }
    }]
  },
  {
    component: Layout,
    path: '/questions',
    children: [{
      path: 'index',
      component: () => import('@/views/QuestionList'),
      name: 'Question',
      meta: {
        title: '问题库',
        noCache: true, // 不会被 <keep-alive> 缓存
        roles: ['editor','admin']
      }
    }]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
