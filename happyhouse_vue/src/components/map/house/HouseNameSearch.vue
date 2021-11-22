<template>
  <div class="d-flex justify-center mt-4">
    <v-text-field
      v-model="houseName"
      :rules="nameRules"
      label="건물 이름"
      color="success"
    ></v-text-field>
    <v-btn class="" depressed @click="searchAptByName"> 검색 </v-btn>
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

export default {
  name: "HouseNameSearch",
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
    ...mapState(dealStore, ["sidos", "guguns", "dongs"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
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
    ...mapMutations(dealStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;

      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchAptByGugun() {
      // 동도 같이 바꿈.
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) {
        this.getHouseListByGugun(this.gugunCode);
        this.getDong(this.gugunCode);
      }
    },
    searchAptByDong() {
      if (this.dongCode) this.getHouseListByDong(this.dongCode);
    },
    searchAptByName() {
      this.getHouseListByName(this.houseName);
    },
  },
};
</script>

<style scoped></style>
