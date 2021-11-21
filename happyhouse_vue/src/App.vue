<template>
  <v-app>
    <router-view />
    <Footer></Footer>
  </v-app>
</template>

<script>
import Footer from "./components/layout/Footer.vue";
export default {
  components: {
    Footer,
  },
};
</script>
<style>
* {
  box-sizing: border-box;
  text-decoration: none;
}
</style>
