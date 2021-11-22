import http from "@/util/http-common.js";
export default {
  namespaced: true,
  state: {
    houselist: [],
  },
  mutations: {
    SET_INTEREST_HOUSE_LIST(state, payload) {
      state.houselist = payload;
    },
    LIKE_INTEREST_HOUSE(state, payload) {
      state.houselist.push(payload);
    },
    UNLIKE_INTEREST_HOUSE(state, payload) {
      state.houselist = state.houselist.filter((house) => house !== payload);
    },
  },
  actions: {
    getInterestHouse({ commit }, id) {
      // const likelist = await http.get(`/house/${id}`).then((res) => {
      //   if (res.data.message === "success") {
      //     return res.data.interestHouseList;
      //   }
      // });
      // const requests = likelist.map(async (house) => {
      //   return await http
      //     .get(`/map/apt/code?aptCode=${house.aptCode}`)
      //     .then((response) => response.data.houseList);
      // });
      // const response = await Promise.all(requests);
      // commit("SET_INTEREST_HOUSE_LIST", response.flat());
      http.get(`house/${id}`).then((response) => {
        if (response.data.message === "success") {
          commit("SET_INTEREST_HOUSE_LIST", response.data.interestHouseList);
        }
      });
    },
    likeHouse({ commit }, data) {
      http.post("house/like", data).then((response) => {
        if (response.data.message === "success") {
          commit("LIKE_INTEREST_HOUSE", data.aptCode);
        }
      });
    },
    unlikeHouse({ commit }, payload) {
      http.delete("house/unlike", { data: payload }).then((response) => {
        if (response.data.message === "success") {
          commit("UNLIKE_INTEREST_HOUSE", payload.aptCode);
        }
      });
    },
  },
};
