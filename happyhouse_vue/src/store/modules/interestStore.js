import http from "@/util/http-common.js";

export default {
  namespaced: true,
  state: {
    likelist: [],
    houseRank: [],
    interestArea: "",
    interestAreaUser: "",
    interestAreaList: [],
    address: {},
  },
  mutations: {
    SET_INTEREST_HOUSE_LIST(state, payload) {
      state.likelist = payload;
    },
    LIKE_INTEREST_HOUSE(state, payload) {
      state.likelist.push(payload);
    },
    UNLIKE_INTEREST_HOUSE(state, payload) {
      state.likelist = state.likelist.filter((house) => house !== payload);
    },
    SET_INTEREAT_HOUSE_RANK_LIST(state, payload) {
      state.houseRank = payload;
    },
    SET_INTEREST_AREA_RANK(state, payload) {
      state.interestArea = payload;
    },
    SET_INTEREST_AREA_USER(state, payload) {
      state.interestAreaUser = payload;
    },
    SET_INTEREST_AREA_ADDRESS(state, payload) {
      state.address = {
        sido: payload.sidoName,
        gugun: payload.gugunName,
        dong: payload.dongName,
      };
    },
    SET_INTEREST_AREA_LIST(state, payload) {
      state.interestAreaList = payload;
    },
  },
  actions: {
    getInterestHouse({ commit }, id) {
      http.get(`interest/house/search/${id}`).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_HOUSE_LIST", response.data.interestHouseList);
        }
      });
    },
    likeHouse({ commit }, data) {
      http.post("interest/house/like", data).then((response) => {
        if (response.data.message === "success") {
          commit("LIKE_INTEREST_HOUSE", data.aptCode);
        }
      });
    },
    unlikeHouse({ commit }, payload) {
      http
        .delete("interest/house/unlike", { data: payload })
        .then((response) => {
          if (response.data.message === "success") {
            commit("UNLIKE_INTEREST_HOUSE", payload.aptCode);
          }
        });
    },
    getHouseRank({ commit }) {
      http.get("interest/house/rank").then((response) => {
        if (response.data.message === "success") {
          commit(
            "SET_INTEREAT_HOUSE_RANK_LIST",
            response.data.interestHouseRank
          );
        }
      });
    },
    async getInterestArea({ commit }) {
      await http.get("interest/area/rank").then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_AREA_RANK", response.data.interestAreaRank);
        }
      });
    },
    getInterestAreaUser({ commit }, userId) {
      http.get(`interest/area/search/${userId}`).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_AREA_USER", response.data.interestAreaDongCode);
        }
      });
    },
    async getInterestAreaAddress({ commit }, dongCode) {
      await http.get(`interest/area/code/${dongCode}`).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_AREA_ADDRESS", response.data.address);
        }
      });
    },
    async insertInterestArea({ commit }, payload) {
      await http.post("interest/area/insert", payload).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_AREA_USER", payload.dongCode);
        }
      });
    },
    async updateInterestArea({ commit }, payload) {
      await http.put("interest/area/update", payload).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_AREA_USER", payload.dongCode);
        }
      });
    },
    deleteInterestArea({ commit }, userId) {
      http.delete(`interest/area/delete/${userId}`).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_AREA_USER", null);
          commit("SET_INTEREST_AREA_ADDRESS", {
            sidoCode: null,
            gugunCode: null,
            dongCode: null,
          });
        }
      });
    },
    getInterestAreaList({ commit }, dongCode) {
      http
        .get(`/map/apt/dong?dong=${dongCode}`)
        .then((response) => {
          commit("SET_INTEREST_AREA_LIST", response.data.houseList);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
