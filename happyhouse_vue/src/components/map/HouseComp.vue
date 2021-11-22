<template>
  <div>
    <div
      class="mt-0"
      style="position: relative; height: 100%; width: 100%; display: flex"
    >
      <div style="height: 90vh; width: 100%; display: flex">
        <div align="left" style="width: 400px">
          <div style="width: 300px; margin-left: 50px">
            <house-name-search></house-name-search>
          </div>
          <house-list />
        </div>
        <div style="height: 100%; width: 100%">
          <house-map />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HouseMap from "@/components/map/HouseMap.vue";
import HouseList from "@/components/map/house/HouseList.vue";
import HouseNameSearch from "@/components/map/house/HouseNameSearch.vue";

export default {
  name: "HouseComp",
  components: {
    HouseMap,
    HouseList,
    HouseNameSearch,
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
