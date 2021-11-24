<template>
  <div>
    <header-nav />
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "News",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
