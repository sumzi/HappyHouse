const API_URL = "https://dapi.kakao.com/v2/local/search/category.json";
// const API_KEY = process.env.VUE_APP_REST_API_KEY;
const API_KEY = "1be3b0f9811d489a771b152f95698f78";
import axios from "axios";
export default {
  namespaced: true,
  state: {
    schools: [],
    conveniences: [],
    traffics: [],
    medicals: [],
    cafes: [],
    cultures: [],
    infraAll: [],
    totalIdx: 0,
  },
  getters: {
    getShools(state) {
      return state.shcools;
    },
    getConveniences(state) {
      return state.conveniences;
    },
    getTraffics(state) {
      return state.traffics;
    },
    getMedicals(state) {
      return state.medicals;
    },
    getCafes(state) {
      return state.cafes;
    },
    getCultures(state) {
      return state.cultures;
    },
    getInfraAll(state) {
      return [
        state.schools,
        state.conveniences,
        state.traffics,
        state.medicals,
        state.cafes,
        state.cultures,
      ];
    },
    getTotalIdx(state) {
      return state.totalIdx;
    },
  },
  mutations: {
    SET_SCHOOL_LIST(state, payload) {
      state.schools = payload;
    },
    SET_CONVENIENCE_LIST(state, payload) {
      state.conveniences = payload;
    },
    SET_TRAFFIC_LIST(state, payload) {
      state.traffics = payload;
    },
    SET_MEDICAL_LIST(state, payload) {
      state.medicals = payload;
    },
    SET_CAFE_LIST(state, payload) {
      state.cafes = payload;
    },
    SET_CULTURE_LIST(state, payload) {
      state.cultures = payload;
    },
    INITIDX(state) {
      state.totalIdx = 0;
    },
  },
  actions: {
    getInfraList(store, payload) {
      store.commit("INITIDX");
      store.dispatch("getSchoolList", payload);
      store.dispatch("getConvenienceList", payload);
      store.dispatch("getTrafficList", payload);
      store.dispatch("getMedicalList", payload);
      store.dispatch("getCafeList", payload);
      store.dispatch("getCultureList", payload);
    },
    getSchoolList({ commit }, payload) {
      axios
        .get(API_URL, {
          headers: { Authorization: `KakaoAK ${API_KEY}` },
          params: {
            category_group_code: "SC4",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            size: 5,
            sort: "distance",
          },
        })
        .then((response) => {
          commit("SET_SCHOOL_LIST", response.data.documents);
        });
    },
    getConvenienceList({ commit }, payload) {
      axios
        .get(API_URL, {
          headers: { Authorization: `KakaoAK ${API_KEY}` },
          params: {
            category_group_code: "CS2",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            size: 5,
            sort: "distance",
          },
        })
        .then((response) => {
          commit("SET_CONVENIENCE_LIST", response.data.documents);
        });
    },
    getTrafficList({ commit }, payload) {
      axios
        .get(API_URL, {
          headers: { Authorization: `KakaoAK ${API_KEY}` },
          params: {
            category_group_code: "SW8",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            size: 5,
            sort: "distance",
          },
        })
        .then((response) => {
          commit("SET_TRAFFIC_LIST", response.data.documents);
        });
    },
    getMedicalList({ commit }, payload) {
      axios
        .get(API_URL, {
          headers: { Authorization: `KakaoAK ${API_KEY}` },
          params: {
            category_group_code: "HP8",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            size: 5,
            sort: "distance",
          },
        })
        .then((response) => {
          commit("SET_MEDICAL_LIST", response.data.documents);
        });
    },
    getCafeList({ commit }, payload) {
      axios
        .get(API_URL, {
          headers: { Authorization: `KakaoAK ${API_KEY}` },
          params: {
            category_group_code: "CE7",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            size: 5,
            sort: "distance",
          },
        })
        .then((response) => {
          commit("SET_CAFE_LIST", response.data.documents);
        });
    },
    getCultureList({ commit }, payload) {
      axios
        .get(API_URL, {
          headers: { Authorization: `KakaoAK ${API_KEY}` },
          params: {
            category_group_code: "CT1",
            x: payload.x,
            y: payload.y,
            radius: 1000,
            size: 5,
            sort: "distance",
          },
        })
        .then((response) => {
          commit("SET_CULTURE_LIST", response.data.documents);
        });
    },
  },
};
