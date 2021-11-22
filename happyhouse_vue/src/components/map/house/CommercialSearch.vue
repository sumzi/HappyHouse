<template>
  <v-row class="mt-0 text-center">
    <v-col cols="4">
      <v-select
        solo
        success
        v-model="code1"
        :items="bgCate"
        @change="searchByBgCate"
      ></v-select>
    </v-col>
    <v-col cols="4">
      <v-select
        solo
        v-model="code2"
        :items="mdCate"
        @change="searchByMdCate"
      ></v-select>
    </v-col>
    <v-col cols="4">
      <v-select
        solo
        v-model="code3"
        :items="smCate"
        @change="searchBySmCate"
      ></v-select>
    </v-col>
  </v-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const commercialStore = "commercialStore";
const dealStore = "dealStore";

export default {
  name: "CommercialSearch",
  data() {
    return {
      code1: null,
      code2: null,
      code3: null,
    };
  },
  computed: {
    ...mapState(commercialStore, ["bgCate", "mdCate", "smCate"]),
    ...mapState(dealStore, ["sido", "gugun", "dong"]),
  },
  created() {},
  updated() {
    this.getBgCate();
  },
  watch: {
    sido() {
      this.code1 = null;
      this.code2 = null;
      this.code3 = null;
    },
    gugun() {
      this.code1 = null;
      this.code2 = null;
      this.code3 = null;
      this.CLEAR_ALL_CATE();
    },
    dong() {
      this.code1 = null;
      this.code2 = null;
      this.code3 = null;
      this.CLEAR_ALL_CATE();
    },
  },
  mounted() {
    if (this.bgCate.length == 1) this.getBgCate();
  },
  methods: {
    ...mapActions(commercialStore, [
      "getBgCate",
      "getMdCate",
      "getSmCate",
      "getHouseListByGugun",
      "getHouseListByDong",
      "getHouseListByName",
    ]),
    ...mapActions(commercialStore, [
      "getCommercialByGugun",
      "getCommercialByDong",
    ]),
    ...mapMutations(commercialStore, [
      "CLEAR_ALL_CATE",
      "CLEAR_BG_CATE_LIST",
      "CLEAR_MD_CATE_LIST",
      "CLEAR_SM_CATE_LIST",
    ]),

    // sidoList() {
    //   this.getSido();
    // },
    searchByBgCate() {
      if (this.code1) {
        if (this.dong) {
          this.getCommercialByDong({
            dongcode: this.dong,
            code: this.code1,
            type: 1,
          });
        } else if (this.gugun) {
          this.getCommercialByGugun({
            guguncode: this.gugun,
            code: this.code1,
            type: 1,
          });
        }
      } else {
        if (this.dong) {
          this.getCommercialByDong({
            dongcode: this.dong,
          });
        }
      }
      this.getMdCate(this.code1);
    },
    searchByMdCate() {
      if (this.code2) {
        if (this.dong) {
          this.getCommercialByDong({
            dongcode: this.dong,
            code: this.code2,
            type: 2,
          });
        } else if (this.gugun) {
          this.getCommercialByGugun({
            guguncode: this.gugun,
            code: this.code2,
            type: 2,
          });
        }
      } else {
        if (this.dong && this.code1) {
          this.getCommercialByDong({
            dongcode: this.dong,
            code: this.code1,
            type: 1,
          });
        }
      }
      this.getSmCate(this.code2);
    },
    searchBySmCate() {
      if (this.code3) {
        if (this.dong) {
          this.getCommercialByDong({
            dongcode: this.dong,
            code: this.code3,
            type: 3,
          });
        } else if (this.gugun) {
          this.getCommercialByGugun({
            guguncode: this.gugun,
            code: this.code3,
            type: 3,
          });
        }
      }
    },
  },
};
</script>

<style scoped>
.col,
.v-select,
v-input__control {
  padding: 3px;
}
</style>
