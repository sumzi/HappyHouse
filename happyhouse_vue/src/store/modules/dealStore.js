import http from "@/util/http-common.js";

export default {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도" }],
    guguns: [{ value: null, text: "구/군" }],
    dongs: [{ value: null, text: "동" }],
    sido: null,
    gugun: null,
    dong: null,
    houses: [],
    house: null,
    avgPrice: 0,
    housedeals: [],
    isDetailView: false,
    prices: [],
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_SIDO: (state, sido) => {
      state.sido = sido;
    },
    SET_GUGUN: (state, gugun) => {
      state.gugun = gugun;
    },
    SET_DONG: (state, dong) => {
      state.dong = dong;
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      //   console.log(houses);
      state.houses = houses;
    },
    CLEAR_HOUSE_LIST: (state) => {
      //   console.log(houses);
      state.houses = [];
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    CLEAR_DETAIL_HOUSE: (state) => {
      state.house = null;
    },
    SET_AVG_PRICE: (state, avgPrice) => {
      state.avgPrice = avgPrice;
    },
    SET_HOUSE_DEAL: (state, payload) => {
      state.housedeals = payload;
    },
    SET_DETAIL_VIEW_FLAG: (state, flag) => {
      state.isDetailView = flag;
    },
    SET_PRICE_LIST: (state, payload) => {
      state.prices = payload;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      http
        .get(`/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      http
        .get(`/map/gugun`, { params: params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      http
        .get(`/map/dong`, { params: params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseListByGugun: ({ commit }, gugunCode) => {
      // // vue cli enviroment variables 검색
      // //.env.local file 생성.
      // // 반드시 VUE_APP으로 시작해야 한다.
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      // //   const SERVICE_KEY =
      // //     "9Xo0vlglWcOBGUDxH8PPbuKnlBwbWU6aO7%2Bk3FV4baF9GXok1yxIEF%2BIwr2%2B%2F%2F4oVLT8bekKU%2Bk9ztkJO0wsBw%3D%3D";
      // const SERVICE_URL =
      //   "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const params = {
        gugun: gugunCode,
      };
      http
        .get(`/map/apt/gugun`, { params })
        .then((reponse) => {
          commit("SET_HOUSE_LIST", reponse.data.houseList);
          commit("SET_AVG_PRICE", reponse.data.avgPrice);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getHouseListByDong: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      http
        .get(`/map/apt/dong`, { params })
        .then((reponse) => {
          commit("SET_HOUSE_LIST", reponse.data.houseList);
          commit("SET_AVG_PRICE", reponse.data.avgPrice);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getHouseListByName: ({ commit }, houseName) => {
      const params = {
        aptName: houseName,
      };
      http
        .get(`/map/apt/name`, { params })
        .then((reponse) => {
          commit("SET_HOUSE_LIST", reponse.data.houseList);
          commit("SET_AVG_PRICE", reponse.data.avgPrice);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPrices({ commit }, payload) {
      let datas = payload.map((deal) => {
        let price = deal.dealAmount.trim().split(",").join("");
        return parseInt(price);
      });
      commit("SET_PRICE_LIST", datas);
    },
    getHouseDeal: (store, payload) => {
      http
        .get(`/map/apt/deal?aptCode=${payload}`)
        .then((response) => {
          // this.getPrices(response.data.aptDealList);//
          store.dispatch("getPrices", response.data.aptDealList);
          store.commit("SET_HOUSE_DEAL", response.data.aptDealList);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailViewFlag: ({ commit }, flag) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_VIEW_FLAG", flag);
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    detailHouseClear: ({ commit }) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("CLEAR_DETAIL_HOUSE");
    },
  },
};
