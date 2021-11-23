<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
      <h2>관심 찜 목록</h2>
    </v-container>
    <v-container style="width: 65%" class="">
      <v-layout text-xs-center row wrap v-if="houselist.length > 0">
        <v-flex v-for="house in houselist" :key="house">
          <house-card v-bind="{ aptCode: house }" />
        </v-flex>
      </v-layout>
      <div v-else class="text-center" style="height: 400px">
        등록한 찜이 없습니다.
      </div>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HeaderNav from "../components/layout/HeaderNav.vue";
import HouseCard from "../components/interest/HouseCard.vue";
export default {
  name: "InterestHouse",
  components: {
    HeaderNav,
    HouseCard,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("interestStore", ["houselist"]),
  },
  methods: {
    ...mapActions("interestStore", ["getInterestHouse"]),
  },
};
</script>

<style></style>
