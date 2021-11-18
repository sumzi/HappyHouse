import Vue from "vue";
import Vuex from "vuex";

import notice from "./modules/noticeStore.js";
import user from "./modules/userStore.js";
import qna from "./modules/qnaStore.js";
import deal from "./modules/dealStore.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    notice: notice,
    user: user,
    qna: qna,
    deal: deal,
  },
});
