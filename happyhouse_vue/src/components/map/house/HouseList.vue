<template>
  <div
    v-if="houses && houses.length != 0"
    class="mt-3 overflow-y-auto"
    style="height: 100%"
  >
    <house-list-row
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </div>
  <div v-else class="mt-3">
    <v-row>
      <v-col><v-alert show>주택 목록이 없습니다.</v-alert></v-col>
    </v-row>
  </div>
</template>

<script>
import HouseListRow from "@/components/map/house/HouseListRow.vue";
import { mapState } from "vuex";

const dealStore = "dealStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(dealStore, ["houses", "avgPrice"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style></style>
