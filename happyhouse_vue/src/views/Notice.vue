<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
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
