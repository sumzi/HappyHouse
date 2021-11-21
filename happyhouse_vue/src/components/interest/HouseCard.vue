<template>
  <v-card class="ma-8 pa-3" elevation="3" width="400px">
    <v-card-title>
      <span>{{ aptName }}</span></v-card-title
    >
    <v-card-text>{{ sidoName }} {{ gugunName }} {{ dongName }}</v-card-text>
    <v-card-text>가격 : {{ recentPrice }}</v-card-text>
    <v-card-actions>
      <v-icon large left @click="unlike"> mdi-heart </v-icon>
    </v-card-actions>
  </v-card>
</template>

<script>
import http from "@/util/http-common.js";
import { mapState } from "vuex";
export default {
  name: "HouseCard",
  props: {
    aptCode: String,
    aptName: String,
    sidoName: String,
    gugunName: String,
    dongName: String,
    recentPrice: String,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    unlike() {
      http
        .delete("/house/unlike", {
          data: { aptCode: this.aptCode, userId: this.userInfo.userId },
        })
        .then((response) => {
          if (response.data.message === "success") {
            location.reload();
          }
        });
    },
  },
};
</script>

<style></style>
