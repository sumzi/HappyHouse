<template>
  <div>
    <header-nav />
    <v-container>
      <h2>공지사항</h2>
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
