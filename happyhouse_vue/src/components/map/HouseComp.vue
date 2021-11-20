<template>
  <div>
    <v-container class="mt-3 text-center">
      <h3 class="underline-orange">
        <v-icon icon="house-fill"></v-icon> House Service
      </h3>
      <v-row style="height: 100px">
        <v-col>
          <house-search-bar></house-search-bar>
        </v-col>
      </v-row>
      <v-row style="height: 70vh">
        <v-col cols="3" align="left" style="height: 100%">
          <house-list />
        </v-col>
        <v-col cols="9">
          <house-map />
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import HouseMap from "@/components/map/HouseMap.vue";
import HouseList from "@/components/map/house/HouseList.vue";
import HouseSearchBar from "@/components/map/house/HouseSearchBar.vue";
export default {
  name: "HouseComp",
  components: {
    HouseMap,
    HouseList,
    HouseSearchBar,
  },
};
</script>

<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
