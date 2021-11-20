<template>
  <div>
    <header-nav />
    <div style="height: calc(100% - 100px)">
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Deal",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
