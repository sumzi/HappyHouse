<template>
  <li class="item" @click="selectHouse">
    <div
      style="border: 1px solid lightgray"
      class="pa-2 mr-3 mt-3 d-flex justify-space-between"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{ 'mouse-over-bgcolor': isColor }"
    >
      <div class="markerbg" :class="'marker_' + (num + 1)"></div>
      <div class="content">
        <h5>{{ house.aptName }}</h5>
        <div>
          {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
          {{ house.jibun }}
        </div>
      </div>
      <div>
        <div v-if="this.houselist.includes(house.aptCode)" @click.stop="unlike">
          💗
        </div>
        <div v-else @click.stop="like">🤍</div>
      </div>
    </div>
  </li>
</template>

<script>
import { mapState, mapActions } from "vuex";

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
  computed: {
    ...mapState("interestStore", ["houselist"]),
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapActions("interestStore", [
      "getInterestHouse",
      "likeHouse",
      "unlikeHouse",
    ]),
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
    like() {
      this.likeHouse({
        userId: this.userInfo.userId,
        aptCode: this.house.aptCode,
      });
    },
    unlike() {
      this.unlikeHouse({
        userId: this.userInfo.userId,
        aptCode: this.house.aptCode,
      });
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
