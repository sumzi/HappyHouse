<template>
  <v-card-text
    class="pa-4 mb-3 white text-no-wrap rounded-pill"
    @click="moveDetail"
  >
    {{ index }}. <strong>{{ house.aptName }}</strong>
  </v-card-text>
</template>

<script>
import http from "@/util/http-common.js";
import { mapActions } from "vuex";
export default {
  props: {
    aptCode: String,
    index: Number,
  },
  data() {
    return {
      house: {},
    };
  },
  created() {
    http
      .get(`/map/apt/code?aptCode=${this.aptCode}`)
      .then((response) => (this.house = response.data.houseList[0]));
  },
  methods: {
    ...mapActions("dealStore", ["detailHouse"]),
    moveDetail() {
      this.detailHouse(this.house);
      this.$router.push({ name: "HouseDetail" });
    },
  },
};
</script>

<style></style>
