<template>
  <v-container
    v-if="house"
    class="lighten-5 pa-16"
    style="width: auto; height: 100%"
  >
    <v-row class="mb-1">
      <v-col
        ><h3>{{ house.아파트 }}</h3></v-col
      >
    </v-row>
    <v-row>
      <v-col cols="4"
        ><v-img :src="require('@/assets/apt.png')" fluid-grow></v-img
      ></v-col>
      <v-col cols="8">
        <br />
        <v-row>
          <v-col>
            <v-alert show outlined color="success"
              >아파트번호 : {{ house.aptCode }}</v-alert
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-alert show outlined color="success"
              >아파트 이름 : {{ house.aptName }}
            </v-alert>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-alert show outlined color="success"
              >법정동 : {{ house.dongName }}
            </v-alert>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-alert show outlined color="success"
              >건축 연도 : {{ house.buildYear }}년</v-alert
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-alert show outlined text color="deep-orange"
              >최근 거래금액 :
              {{
                (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
              }}원</v-alert
            >
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-alert show outlined text color="deep-orange"
              >검색결과 평균 거래금액 :
              {{ (avgPrice * 10000) | price }}원</v-alert
            >
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

const dealStore = "dealStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(dealStore, ["house", "avgPrice"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
div.col {
  padding: 0 10px 0 10px;
}
.v-alert {
  margin: 2px;
  padding: 10px;
  width: fit-content;
}
</style>
