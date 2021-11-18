<template>
  <div>
    <header-nav />
    <div class="">
      <h1>QnA</h1>
      <div class="">
        <router-view />
      </div>
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
