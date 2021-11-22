<template>
  <div>
    <v-checkbox v-model="activeCheck" label="환경 정보 보기"></v-checkbox>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

const dealStore = "dealStore";
const pollutionStore = "pollutionStore";

export default {
  name: "PollutionSearch",
  data() {
    return {
      activeCheck: false,
    };
  },
  computed: {
    ...mapState(dealStore, ["sido", "gugun", "dong"]),
    ...mapState(pollutionStore, ["pollutions", "pollution"]),
  },
  created() {},
  updated() {
    if (this.activeCheck) {
      if (this.dong) {
        this.getPollutionByDong(this.dong);
      } else if (this.gugun) {
        this.getPollutionByGugun(this.gugun);
      }
    } else {
      this.CLEAR_POLLUTION_LIST();
      this.CLEAR_DETAIL_POLLUTION();
    }
  },
  watch: {
    sido() {
      this.CLEAR_POLLUTION_LIST();
      this.CLEAR_DETAIL_POLLUTION();
      this.activeCheck = false;
    },
    gugun() {
      this.CLEAR_POLLUTION_LIST();
      this.CLEAR_DETAIL_POLLUTION();
      this.activeCheck = false;
    },
    dong() {
      this.CLEAR_POLLUTION_LIST();
      this.CLEAR_DETAIL_POLLUTION();
      this.activeCheck = false;
    },
  },
  mounted() {
    if (this.bgCate.length == 1) this.getBgCate();
  },
  methods: {
    ...mapMutations(pollutionStore, [
      "CLEAR_POLLUTION_LIST",
      "CLEAR_DETAIL_POLLUTION",
    ]),
    ...mapActions(pollutionStore, [
      "getPollutionByGugun",
      "getPollutionByDong",
    ]),
  },
};
</script>

<style scoped></style>
