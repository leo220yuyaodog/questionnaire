import Vue from "vue"
import Router from "vue-router"

Vue.use(Router)

/* Layout */
import Layout from "@/layout"
import School from "../components/Student"

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
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },

  {
    path: "/404",
    component: () => import("@/views/404"),
    hidden: true
  },

  {
    path: "/callback",
    component: () => import("@/views/login/callback"),
    hidden: true
  },

  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    children: [{
      path: "dashboard",
      name: "Dashboard",
      component: () => import("@/views/dashboard/index"),
      meta: { title: "Dashboard", icon: "dashboard" }
    }]
  },

  {
    path: "/example",
    component: Layout,
    redirect: "/example/table",
    name: "Example",
    meta: { title: "Example", icon: "el-icon-s-help" },
    children: [
      {
        path: "table",
        name: "Table",
        component: () => import("@/views/table/index"),
        meta: { title: "Table", icon: "table" }
      },
      {
        path: "tree",
        name: "Tree",
        component: () => import("@/views/tree/index"),
        meta: { title: "Tree", icon: "tree" }
      }
    ]
  },

  {
    path: "/form",
    component: Layout,
    children: [
      {
        path: "index",
        name: "Form",
        component: () => import("@/views/form/index"),
        meta: { title: "Form", icon: "form" }
      }
    ]
  },
  {
    component: Layout,
    hidden: true,
    path: "/create",
    children: [{
      path: "index/:id",
      component: () => import("@/views/createpage/CreatePageView/Create.vue"),
      name: "create",
      meta: {
        title: "create",
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },

  {
    component: Layout,
    path: "/collection",
    children: [{
      path: "index",
      component: () => import("@/views/collectionpage/MainPageView/Collection.vue"),
      name: "collection",
      meta: {
        title: "collection",
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
  {
    component: Layout,
    path: "/analysis",
    children: [{
      path: "index",
      component: () => import("@/views/collectionpage/AnalysisPageView/Analysis.vue"),
      name: "analysis",
      meta: {
        title: "analysis",
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  },
  {
    component: Layout,
    path: "/fill",
    children: [{
      path: "index",
      component: () => import("@/views/fillinpage/FillInPageView/FillIn.vue"),
      name: "Fill",
      meta: {
        title: "Fill",
        noCache: true // 不会被 <keep-alive> 缓存
      }
    }]
  }

  // 因为我们后面还要加动态的，所以这里应该不能加，反正加了不行
  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]

export const asyncRoutes = [
  {
    path: "/permission",
    component: Layout,
    name: "权限测试",
    meta: { role: ["super_admin", "tenant_admin", "user", "answerer"] },
    children: [
      {
        path: "index",
        component: School,
        name: "权限测试页",
        meta: { title: "PERM", icon: "form", role: ["super_admin", "tenant_admin", "user", "answerer"] }
      }]
  },
  { path: "*", redirect: "/404", hidden: true }
]

// 实例化vue的时候只挂载constantRouter
const createRouter = () => new Router({
  mode: "history", // require service support
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
