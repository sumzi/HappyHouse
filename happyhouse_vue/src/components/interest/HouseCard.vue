<template>
  <v-card class="ma-8 pa-3" elevation="3" width="400px">
    {{ aptCode }}
    <v-card-title>
      <span>{{ house.aptName }}</span></v-card-title
    >
    <v-card-text
      >{{ house.sidoName }} {{ house.gugunName }}
      {{ house.dongName }}</v-card-text
    >
    <v-card-text>가격 : {{ house.recentPrice }}</v-card-text>
    <v-card-actions>
      <v-icon large left @click="unlike"> mdi-heart </v-icon>
    </v-card-actions>
  </v-card>
</template>

<script>
import http from "@/util/http-common.js";
import { mapState, mapActions } from "vuex";
export default {
  name: "HouseCard",
  props: {
    aptCode: String,
  },
  data() {
    return {
      house: {},
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  created() {
    http
      .get(`/map/apt/code?aptCode=${this.aptCode}`)
      .then((response) => (this.house = response.data.houseList[0]));
  },
  methods: {
    ...mapActions("interestStore", ["getInterestHouse"]),
    unlike() {
      http
        .delete("/house/unlike", {
          data: { aptCode: this.aptCode, userId: this.userInfo.userId },
        })
        .then((response) => {
          if (response.data.message === "success") {
            this.getInterestHouse(this.userInfo.userId);
          }
        });
    },
  },
};
</script>

<style></style>
