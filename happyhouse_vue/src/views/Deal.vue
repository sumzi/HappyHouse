<template>
  <div>
    <header-nav />
    <v-container>
      <h2>내집찾기</h2>
    </v-container>
    <house-map></house-map>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
import HouseMap from "../components/map/HouseMap.vue";
export default {
  name: "QnA",
  components: {
    HeaderNav,
    HouseMap,
  },
};
</script>

<style></style>
