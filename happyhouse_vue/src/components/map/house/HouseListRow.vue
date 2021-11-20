<template>
  <li class="item">
    <v-row
      class="m-2"
      @click="selectHouse"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{ 'mouse-over-bgcolor': isColor }"
    >
      <span class="markerbg" :class="'marker_' + (num + 1)"></span>
      <div class="content">
        <h5>{{ house.aptName }}</h5>
        <span
          >{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
          {{ house.jibun }}</span
        >
      </div>
    </v-row>
  </li>
</template>

<script>
import { mapActions } from "vuex";

const dealStore = "dealStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
    num: Number,
  },

  methods: {
    ...mapActions(dealStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
      this.$router.push({ name: "HouseDetail" });
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
