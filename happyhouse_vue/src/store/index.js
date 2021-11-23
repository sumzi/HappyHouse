import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import noticeStore from "@/store/modules/noticeStore.js";
import userStore from "@/store/modules/userStore.js";
import qnaStore from "@/store/modules/qnaStore.js";
import dealStore from "@/store/modules/dealStore.js";
import interestStore from "@/store/modules/interestStore.js";
import infraStore from "@/store/modules/infraStore.js";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    noticeStore,
    userStore,
    qnaStore,
    dealStore,
    interestStore,
    infraStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
