<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
      <h2>회원관리</h2>
    </v-container>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "User",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
