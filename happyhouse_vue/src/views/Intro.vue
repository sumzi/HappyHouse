<template>
  <div>
    <header-nav />
    <div class="container">
      <h1>해피 하우스</h1>
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
