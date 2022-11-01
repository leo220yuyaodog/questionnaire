import {getInfo, login, logout} from '@/api/user'
import { getToken, setToken, removeToken, setUser, getUser, removeUser} from '@/utils/auth'
import { resetRouter } from '@/router'
import { CasdoorSdk, ServerUrl } from '@/config'
import { Message } from 'element-ui'
import router from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  signin({ commit }) {
    return new Promise((resolve, reject) => {
      CasdoorSdk.signin(ServerUrl).then((res) => {
        if (res.msg === 'ok') {
          Message('Logged in successfully')
          const { data } = res
          commit('SET_TOKEN', data.data1)
          console.log(data.data1)
          setToken(data.data1)
          setUser(data.data2)
          resolve()
          router.push({
            path: '/'
          })
        } else {
          Message('Logged in failed')
          router.push({
            path: '/'
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
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response

        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        const { name, avatar } = data

        commit('SET_NAME', name)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        removeUser()
        resetRouter()
        commit('RESET_STATE')
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
      commit('RESET_STATE')
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

