import http from "@/util/http-common.js";
export default {
  namespaced: true,
  state: {
    qnalist: [],
    qna: {},
  },
  mutations: {
    SET_QNA_LIST(state, payload) {
      state.qnalist = payload;
    },
    SET_QNA(state, payload) {
      state.qna = payload;
    },
  },
  actions: {
    getQnAList({ commit }) {
      http
        .get("/qna")
        .then((response) => {
          commit("SET_QNA_LIST", response.data.qnaList);
        })
        .catch(() => {
          alert("큐앤에이목록조회 중 오류 발생!!!!");
        });
    },
    getQnA({ commit }, payload) {
      http
        .get(`/qna/${payload}`)
        .then((response) => {
          commit("SET_QNA", response.data)})
        .catch(() => {
          alert("큐앤에이조회 중 오류 발생!!!!");
        });
    },
  },
  modules: {},
};
