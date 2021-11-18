<template>
  <div>
    <header-nav />
    <div class="container">
      <h1>거래, 지도</h1>
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
