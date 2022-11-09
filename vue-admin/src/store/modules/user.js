import { login, logout } from "@/api/user"
import { getToken, setToken, removeToken, setUser, getUser, removeUser } from "@/utils/auth"

import { resetRouter } from "@/router"
import { CasdoorSdk, ServerUrl } from "@/config"
import { Message } from "element-ui"
import router from "@/router"

const getDefaultState = () => {
  return {
    token: getToken(),
    id: "",
    tenantId: "",
    name: "",
    password: "",
    phoneNum: "",
    role: "",
    email: "",
    avatar: ""
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_USER: (state, user) => {
    state.id = user.id
    state.tenantId = user.owner
    state.username = user.name
    state.password = user.password
    state.phoneNum = user.phone
    state.role = user.role
    state.email = user.email
    state.avatar = user.avatar
  },

  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_ROLE: (state, role) => {
    state.role = role
  }

  // SET_NAME: (state, name) => {
  //   state.name = name
  // },
  // SET_AVATAR: (state, avatar) => {
  //   state.avatar = avatar
  // }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit("SET_TOKEN", data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 我们的 login 方法
  signin({ commit }) {
    return new Promise((resolve, reject) => {
      CasdoorSdk.signin(ServerUrl).then((res) => {
        if (res.msg === "ok") {
          Message("Logged in successfully")
          const { data } = res
          commit("SET_TOKEN", data.data1)
          // console.log(data.data1)
          setToken(data.data1)
          // 3. 设置 user 到 store（方便后面假装从后端读）
          commit("SET_USER", data.data2)
          // （3.1设置 user 到 cookies，之后假装从后端读取，其实读的是 cookies）
          setUser(data.data2)
          // 4. 路由
          resolve()
          router.push({
            path: "/"
          })
        } else {
          Message("Logged in failed")
          router.push({
            path: "/"
          })
        }
      }).catch((err) => {
        reject(err)
      })
    })
  },

  // // get user info  Casdoor 登录
  // getInfo({ commit, state }) {
  //   return new Promise((resolve, reject) => {
  //     const data = JSON.parse(getUser())
  //     if (!data) {
  //       return reject('Verification failed, please Login again.')
  //     }
  //     console.log(data.name)
  //     commit('SET_NAME', data.name)
  //     commit('SET_AVATAR', data.avatar)
  //     resolve(data)
  //   })
  // },

  // get user info
  // 假装从后端读取，其实是从 cookies 读
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      const user = JSON.parse(getUser())
      if (!user) {
        return reject("Verification failed, please Login again.")
      }
      // 默认user
      commit("SET_ROLE", "user")
      // 管理用户
      if (user.isAdmin === 1) {
        commit("SET_ROLE", "tenant_admin")
      }
      // 管理租户
      if (user.isGlobalAdmin === 1) {
        commit("SET_ROLE", "super_admin")
      }

      commit("SET_USER", user)
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        removeUser()
        resetRouter()
        commit("RESET_STATE")
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      removeUser()
      commit("RESET_STATE")
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

