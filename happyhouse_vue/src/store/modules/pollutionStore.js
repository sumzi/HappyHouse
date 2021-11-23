import http from "@/util/http-common.js";

export default {
  namespaced: true,
  state: {
    pollutions: [],
    pollution: null,
  },

  getters: {},

  mutations: {
    SET_POLLUTION_LIST: (state, pollutions) => {
      //   console.log(houses);
      state.pollutions = pollutions;
    },
    SET_DETAIL_POLLUTION: (state, pollution) => {
      state.pollution = pollution;
    },
    CLEAR_POLLUTION_LIST: (state) => {
      //   console.log(houses);
      state.pollutions = [];
    },
    CLEAR_DETAIL_POLLUTION: (state) => {
      state.pollution = null;
    },
  },

  actions: {
    getPollutionByGugun: ({ commit }, gugunCode) => {
      commit("CLEAR_POLLUTION_LIST");
      commit("CLEAR_DETAIL_POLLUTION");
      const params = {
        gugun: gugunCode,
      };
      http
        .get(`/map/pollution/gugun`, { params: params })
        .then((reponse) => {
          commit("SET_POLLUTION_LIST", reponse.data.pollutionList);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getPollutionByDong: ({ commit }, dongCode) => {
      commit("CLEAR_POLLUTION_LIST");
      commit("CLEAR_DETAIL_POLLUTION");
      const params = {
        dong: dongCode,
      };

      http
        .get(`/map/pollution/dong`, { params: params })
        .then((reponse) => {
          commit("SET_POLLUTION_LIST", reponse.data.pollutionList);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    detailPollution: ({ commit }, pollution) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_POLLUTION", pollution);
    },
    detailPollutionClear: ({ commit }) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("CLEAR_DETAIL_POLLUTION");
    },
  },
};
