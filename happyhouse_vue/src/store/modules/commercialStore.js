import http from "@/util/http-common.js";

export default {
  namespaced: true,
  state: {
    bgCate: [{ value: null, text: "대분류" }],
    mdCate: [{ value: null, text: "중분류" }],
    smCate: [{ value: null, text: "소분류" }],
    commercials: [],
    commercial: null,
  },

  getters: {},

  mutations: {
    SET_BG_CATE_LIST: (state, cates) => {
      cates.forEach((cate) => {
        state.bgCate.push({ value: cate.code1, text: cate.codename1 });
      });
    },
    SET_MD_CATE_LIST: (state, cates) => {
      cates.forEach((cate) => {
        state.mdCate.push({ value: cate.code2, text: cate.codename2 });
      });
    },
    SET_SM_CATE_LIST: (state, cates) => {
      cates.forEach((cate) => {
        state.smCate.push({ value: cate.code3, text: cate.codename3 });
      });
    },
    CLEAR_ALL_CATE: (state) => {
      state.bgCate = [{ value: null, text: "대분류" }];
      state.mdCate = [{ value: null, text: "중분류" }];
      state.smCate = [{ value: null, text: "소분류" }];
    },
    CLEAR_BG_CATE_LIST: (state) => {
      state.bgCate = [{ value: null, text: "대분류" }];
    },
    CLEAR_MD_CATE_LIST: (state) => {
      state.mdCate = [{ value: null, text: "중분류" }];
    },
    CLEAR_SM_CATE_LIST: (state) => {
      state.smCate = [{ value: null, text: "소분류" }];
    },
    SET_COMMERCIAL_LIST: (state, commercials) => {
      //   console.log(houses);
      state.commercials = commercials;
    },
    SET_DETAIL_COMMERCIAL: (state, commercial) => {
      state.commercial = commercial;
    },
    CLEAR_COMMERCIAL_LIST: (state) => {
      //   console.log(houses);
      state.commercials = [];
    },
    CLEAR_DETAIL_COMMERCIAL: (state) => {
      state.commercial = null;
    },
  },

  actions: {
    clearCate: ({ commit }) => {
      commit("CLEAR_MD_CATE_LIST");
      commit("CLEAR_SM_CATE_LIST");
      commit("CLEAR_COMMERCIAL_LIST");
      commit("CLEAR_DETAIL_COMMERCIAL");
    },
    getBgCate: ({ commit }) => {
      http
        .get(`/map/code1`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_BG_CATE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getMdCate: ({ commit }, code1) => {
      commit("CLEAR_MD_CATE_LIST");
      commit("CLEAR_SM_CATE_LIST");
      const params = {
        code1,
      };
      http
        .get(`/map/code2`, { params: params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_MD_CATE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getSmCate: ({ commit }, code2) => {
      commit("CLEAR_SM_CATE_LIST");
      const params = {
        code2,
      };
      http
        .get(`/map/code3`, { params: params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_SM_CATE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    //수정해야할 것들

    getCommercialByGugun: ({ commit }, param) => {
      commit("CLEAR_COMMERCIAL_LIST");
      commit("CLEAR_DETAIL_COMMERCIAL");
      let data;
      switch (param.type) {
        case 1:
          data = {
            guguncode: param.guguncode,
            code1: param.code,
          };
          break;
        case 2:
          data = {
            guguncode: param.guguncode,
            code2: param.code,
          };
          break;
        case 3:
          data = {
            guguncode: param.guguncode,
            code3: param.code,
          };
          break;
        //너무 많이 반환됨.
        // default:
        //   data = {
        //     guguncode: gugun,
        //   };
        //   break;
      }
      http
        .post(`/map/commercial/gugun`, data)
        .then((reponse) => {
          commit("SET_COMMERCIAL_LIST", reponse.data.commercialList);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getCommercialByDong: ({ commit }, param) => {
      commit("CLEAR_COMMERCIAL_LIST");
      commit("CLEAR_DETAIL_COMMERCIAL");
      let data;
      switch (param.type) {
        case 1:
          data = {
            dongcode: param.dongcode,
            code1: param.code,
          };
          break;
        case 2:
          data = {
            dongcode: param.dongcode,
            code2: param.code,
          };
          break;
        case 3:
          data = {
            dongcode: param.dongcode,
            code3: param.code,
          };
          break;
        default:
          data = {
            dongcode: param.dongcode,
          };
          break;
      }

      http
        .post(`/map/commercial/dong`, data)
        .then((reponse) => {
          commit("SET_COMMERCIAL_LIST", reponse.data.commercialList);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    detailCommercial: ({ commit }, commercial) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_COMMERCIAL", commercial);
    },
    detailCommercialClear: ({ commit }) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("CLEAR_DETAIL_COMMERCIAL");
    },
  },
};
