<template>
  <div class="mt-0 text-center" style="display: flex; width: 500px">
    <!-- <v-col class="sm-3">
      <v-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></v-form-input>
    </v-col>
    <v-col class="sm-3" align="left">
      <v-button variant="outline-primary" @click="sendKeyword">검색</v-button>
    </v-col> -->

    <v-select
      solo
      success
      v-model="sidoCode"
      :items="sidos"
      @change="gugunList"
    ></v-select>

    <v-select
      solo
      v-model="gugunCode"
      :items="guguns"
      @change="searchAptByGugun"
    ></v-select>

    <v-select
      solo
      v-model="dongCode"
      :items="dongs"
      @change="searchAptByDong"
    ></v-select>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    dealStore: dealStore
  }
*/
const dealStore = "dealStore";
const commercialStore = "commercialStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      houseName: "",
      nameRules: [(v) => v.length <= 20 || "검색어는 20자 이하여야 합니다."],
    };
  },
  computed: {
    ...mapState(dealStore, [
      "sidos",
      "guguns",
      "dongs",
      "sido",
      "gugun",
      "dong",
    ]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    if (this.sidos.length == 1) {
      this.getSido();
    }
  },
  updated() {
    if (this.sidoCode) this.SET_SIDO(this.sidoCode);
    if (this.gugunCode) this.SET_GUGUN(this.gugunCode);
    if (this.dongCode) this.SET_DONG(this.dongCode);
  },
  mounted() {
    if (this.sido) this.sidoCode = this.sido;
    if (this.gugun) this.gugunCode = this.gugun;
    if (this.dong) this.dongCode = this.dong;
    // this.CLEAR_SIDO_LIST();
    // this.CLEAR_GUGUN_LIST();
    // this.CLEAR_DONG_LIST();
    // this.getSido();
  },
  methods: {
    ...mapActions(dealStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseListByGugun",
      "getHouseListByDong",
      "getHouseListByName",
    ]),
    ...mapActions(commercialStore, [
      "getCommercialByGugun",
      "getCommercialByDong",
    ]),
    ...mapMutations(dealStore, [
      "SET_SIDO",
      "SET_GUGUN",
      "SET_DONG",
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
    ...mapMutations(commercialStore, [
      "CLEAR_ALL_CATE",
      "CLEAR_BG_CATE_LIST",
      "CLEAR_MD_CATE_LIST",
      "CLEAR_SM_CATE_LIST",
      "CLEAR_COMMERCIAL_LIST",
      "CLEAR_DETAIL_COMMERCIAL",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      this.SET_GUGUN(null);
      this.SET_DONG(null);

      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchAptByGugun() {
      // 동도 같이 바꿈.
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      this.SET_DONG(null);

      if (this.gugunCode) {
        this.getHouseListByGugun(this.gugunCode);
        this.getDong(this.gugunCode);
      } else {
        this.SET_GUGUN(null);
        this.CLEAR_HOUSE_LIST();
      }
    },
    searchAptByDong() {
      if (this.dongCode) {
        this.getHouseListByDong(this.dongCode);
        this.getCommercialByDong({ dongcode: this.dongCode });
      } else {
        this.getHouseListByGugun(this.gugunCode);
        this.SET_DONG(null);
      }
    },
    searchAptByName() {
      this.getHouseListByName(this.houseName);
    },
  },
};
</script>

<style scoped>
.col,
.v-select,
v-input__control {
  padding: 1px;
}
</style>
