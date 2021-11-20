import http from "@/util/http-common";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    userinfo(state) {
      return state.user;
    },
  },
  mutations: {
    USER_LOGIN(state, isLogin) {
      state.isLogin = isLogin;
    },
    USER_LOGIN_ERROR(state, isLoginError) {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO(state, userInfo) {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    userLogin({ commit }, payload) {
      http
        .post("/user/login", payload)
        .then((response) => {
          if (response.data.message === "success") {
            commit("USER_LOGIN", true);
            commit("USER_LOGIN_ERROR", false);
            sessionStorage.setItem("userId", response.data.user.userId);
          } else {
            commit("USER_LOGIN", false);
            commit("USER_LOGIN_ERROR", true);
          }
        })
        .catch((error) => console.log(error));
    },
    getUserInfo({ commit }) {
      const userId = sessionStorage.getItem("userId");
      http
        .get(`/user/info/${userId}`)
        .then((response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        })
        .catch((error) => console.log(error));
    },
  },
};
