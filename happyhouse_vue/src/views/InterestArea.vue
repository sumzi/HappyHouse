<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
      <h2>관심 지역</h2>
    </v-container>
    <v-container style="width: 65%" class=""> </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "InterestHouse",
  components: {
    HeaderNav,
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
