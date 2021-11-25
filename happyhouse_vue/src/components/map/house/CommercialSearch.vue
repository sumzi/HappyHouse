<template>
  <div id="comm_filter" class="text-center" v-show="this.gugun">
    <v-select
      class="cumm_select"
      solo
      success
      v-model="code1"
      :items="bgCate"
      @change="searchByBgCate"
    ></v-select>

    <v-select
      class="cumm_select"
      solo
      v-model="code2"
      :items="mdCate"
      @change="searchByMdCate"
    ></v-select>

    <v-select
      class="cumm_select"
      solo
      v-model="code3"
      :items="smCate"
      @change="searchBySmCate"
    ></v-select>
  </div>
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
  watch: {
    sido() {
      this.code1 = null;
      this.code2 = null;
      this.code3 = null;
      this.clearCate();
    },
    gugun() {
      this.code1 = null;
      this.code2 = null;
      this.code3 = null;
      this.clearCate();
    },
    dong() {
      this.code1 = null;
      this.code2 = null;
      this.code3 = null;
      this.clearCate();
    },
  },
  updated() {
    console.log(this.gugun + "현재구군");
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
      "clearCate",
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
      this.code2 = null;
      this.code3 = null;
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
      this.code3 = null;
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

<style>
#comm_filter {
  display: flex;
  width: 400px;
  height: 50px;
}
.cumm_select.v-text-field.v-text-field--enclosed .v-text-field__details,
.cumm_select.v-text-field.v-text-field--enclosed
  > .v-input__control
  > .v-input__slot {
  padding: 2px;
  border-radius: 0px;
  font-size: 13px;
  margin: 0;
  max-height: 35px;
  min-height: 35px !important;
  align-items: center !important;
}
</style>
