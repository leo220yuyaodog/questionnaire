import Vuex from 'vuex';

const vuex = new Vuex.Store({
  state: {
    login: false,
    username: '',
  },
  mutations: {
    login(state, username) {
      state.login = true;
      state.username = username;
    },
    logout(state) {
      state.login = false;
      state.username = '';
    },
  },
  actions: {},
  modules: {},
});

export default vuex;
