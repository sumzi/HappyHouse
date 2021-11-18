<template>
  <div>
    <header-nav />
    <v-container>
      <h2>내집찾기</h2>
    </v-container>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "QnA",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
