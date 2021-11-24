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
    <detail-pre-view id="previewPopup" v-show="isDetailView"></detail-pre-view>
  </div>
</template>

<script>
import HouseMap from "@/components/map/HouseMap.vue";
import HouseList from "@/components/map/house/HouseList.vue";
import HouseNameSearch from "@/components/map/house/HouseNameSearch.vue";
import DetailPreView from "@/components/map/house/DetailPreView.vue";
import { mapState } from "vuex";

export default {
  name: "HouseComp",
  components: {
    HouseMap,
    HouseList,
    HouseNameSearch,
    DetailPreView,
  },

  computed: {
    ...mapState("dealStore", ["isDetailView"]),
  },
  watch: {
    isDetailView() {
      console.log("작동");
    },
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
#previewPopup {
  position: absolute;
  bottom: 0;
  right: 0;
  z-index: 1000;
  width: 400px;
  height: 600px;
  background-color: white;
}
</style>
