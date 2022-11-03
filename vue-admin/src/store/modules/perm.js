import { asyncRoutes, constantRoutes } from '../../router';

function hasPermission(role, route) {
  if (route.meta && route.meta.role) {
    return route.meta.role.indexOf(role) >= 0
  } else {
    return true
  }
}

const state = {
  routers: constantRoutes,
  addRouters: []
}

const mutations = {
  SET_ROUTERS: (state, routers) => {
    state.addRouters = routers;
    state.routers = constantRoutes.concat(routers);
  }
}

const actions = {
  GenerateRoutes({ commit }, role) {
    return new Promise(resolve => {
      const accessedRouters = asyncRoutes.filter(v => {
        if (hasPermission(role, v)) {
          if (v.children && v.children.length > 0) {
            v.children = v.children.filter(child => {
              if (hasPermission(role, child)) {
                return child
              }
              return false;
            });
            return v
          } else {
            return v
          }
        }
        return false;
      });
      commit('SET_ROUTERS', accessedRouters);
      resolve();
    })
  }
}


export default {
  namespaced: true,
  state,
  mutations,
  actions
}

