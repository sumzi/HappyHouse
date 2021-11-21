import http from "@/util/http-common.js";
export default {
  namespaced: true,
  state: {
    houselist: [],
  },
  mutations: {
    SET_INTEREST_HOUSE_LIST(state,payload){
      state.houselist = payload;
    },
  },
  actions: {
    async getInterestHouse({ commit }, id) {
      const likelist = await http.get(`/house/${id}`).then((res) => {
        if (res.data.message === "success") {
          return res.data.interestHouseList;
        }
      });
      const requests = likelist.map(async (house) => {
        return await http
          .get(`/map/apt/code?aptCode=${house.aptCode}`)
          .then((response) => response.data.houseList);
      });
      const response = await Promise.all(requests);
      commit("SET_INTEREST_HOUSE_LIST", response.flat());
    },
  },
};
