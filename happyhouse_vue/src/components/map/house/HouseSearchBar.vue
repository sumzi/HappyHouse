<template>
  <div>
    <v-row class="mt-0 mv-0 text-center">
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
      <v-col cols="4">
        <v-select
          v-model="sidoCode"
          :items="sidos"
          @change="gugunList"
        ></v-select>
      </v-col>
      <v-col cols="4">
        <v-select
          v-model="gugunCode"
          :items="guguns"
          @change="searchAptByGugun"
        ></v-select>
      </v-col>
      <v-col cols="4">
        <v-select
          v-model="dongCode"
          :items="dongs"
          @change="searchAptByDong"
        ></v-select>
      </v-col>
    </v-row>
    <v-row class="ma-0 mv-4 text-center">
      <v-text-field
        v-model="houseName"
        :rules="nameRules"
        :counter="20"
        label="건물 이름"
        required
      ></v-text-field>
      <v-btn class="mx-2 mt-6" depressed @click="searchAptByName"> 검색 </v-btn>
    </v-row>
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

<style scoped>
.col,
.v-select,
v-input__control {
  padding: 5px;
}
</style>
