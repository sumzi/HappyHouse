<template>
  <v-app>
    <router-view />
    <Footer></Footer>
    <v-fab-transition>
      <v-btn
        color="success"
        class="ma-3"
        bottom
        right
        fixed
        fab
        v-show="btnShow"
        @click="$vuetify.goTo('#top')"
        ><v-icon large>mdi-chevron-up</v-icon></v-btn
      >
    </v-fab-transition>
  </v-app>
</template>

<script>
import Footer from "./components/layout/Footer.vue";
export default {
  components: {
    Footer,
  },
  data() {
    return {
      btnShow: false,
    };
  },
  methods: {
    hanleScroll() {
      this.btnShow = window.scrollY > 400;
    },
  },
  beforeMount() {
    window.addEventListener("scroll", this.hanleScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.hanleScroll);
  },
};
</script>
<style>
* {
  box-sizing: border-box;
  text-decoration: none;
}
</style>
