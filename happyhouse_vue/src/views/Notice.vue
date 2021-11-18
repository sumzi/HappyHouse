<template>
  <div>
    <header-nav />
    <v-container>
      <h1>공지사항</h1>
    </v-container>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Notice",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
