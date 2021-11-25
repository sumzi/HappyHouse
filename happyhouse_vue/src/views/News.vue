<template>
  <div>
    <header-nav id="top" />
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
