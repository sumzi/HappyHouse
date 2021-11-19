import http from "@/util/http-common";
export default {
  state: {
    user: {},
  },
  getters: {
    user(state) {
      return state.user;
    },
  },
  mutations: {
    LOGIN_USER(state, payload) {
      console.log(payload);
      state.user = payload;
    },
  },
  actions: {
    loginUser({ commit }, payload) {
      http
        .post("/user/login", {
          userId: payload.userId,
          userPw: payload.userPw,
        })
        .then((response) => commit("LOGIN_USER", response))
        .catch((error) => console.log(error));
    },
  },
};
