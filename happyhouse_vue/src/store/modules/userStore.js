import http from "@/util/http-common";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    userInfo: null,
  },
  mutations: {
    USER_LOGIN(state, isLogin) {
      state.isLogin = isLogin;
    },
    SET_USER_INFO(state, userInfo) {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userLogin({ commit }, payload) {
      await http
        .post("/user/login", payload)
        .then((response) => {
          if (response.data.message === "success") {
            commit("USER_LOGIN", true);
            sessionStorage.setItem("userId", response.data.user.userId);
          } else {
            commit("USER_LOGIN", false);
          }
        })
        .catch((error) => console.log(error));
    },
    getUserInfo({ commit }, id) {
      http
        .get(`/user/info/${id}`)
        .then((response) => {
          if (response.data.message === "success") {
            console.log(response)
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        })
        .catch((error) => console.log(error));
    },
  },
};
