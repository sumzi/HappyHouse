<template>
  <v-carousel
    :continuous="false"
    hide-delimiter-background
    show-arrows-on-hover
    hide-delimiters
    delimiter-icon="mdi-minus"
    height="200px"
    :cycle="false"
  >
    <v-carousel-item v-for="(house, i) in interestAreaList" :key="i">
      <v-sheet height="100%" tile>
        <v-row class="fill-height" align="center" justify="center">
          <div>
            <v-icon
              style="font-size: 70px; margin-right: 30px"
              @click="moveDetail(house)"
              >mdi-home-circle</v-icon
            >
          </div>
          <div>
            <div>{{ house.aptName }}</div>
            <div>
              {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
            </div>
            <div>거래금액: {{ house.recentPrice }} 만원</div>
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "AreaRank",
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("interestStore", [
      "interestAreaUser",
      "interestArea",
      "interestAreaList",
    ]),
  },
  methods: {
    ...mapActions("dealStore", ["detailHouse"]),
    async moveDetail(house) {
      await this.detailHouse(house);
      this.$router.push({ name: "HouseDetail" });
    },
  },
};
</script>

<style></style>
