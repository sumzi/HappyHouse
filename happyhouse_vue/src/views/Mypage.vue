<template>
  <div>
    <header-nav />
    <v-container class="text-center">
      <h2>마이페이지</h2>
    </v-container>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Mypage",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
