<template>
  <v-card id="popup" class="text-center">
    <div v-if="house">
      <h2>집 정보 Preview</h2>
      <div>이름: {{ house.aptName }}</div>
      <div>동: {{ house.dongName }}</div>
      <div>건축 연도: {{ house.buildYear }}</div>
      <div>
        최근 거래금액:
        {{ (parseInt(house.recentPrice.replace(",", "")) * 10000) | price }} 원
      </div>
      <div>
        검색 결과 평균거래금액: {{ (house.avgPrice * 10000) | price }} 원
      </div>
    </div>
    <div v-else-if="commercial">
      <h2>상권 정보 Preview</h2>
      <div>이름: {{ commercial.shopname }}</div>
      <div>비고: {{ commercial.localname }}</div>
      <div>
        카테고리: {{ commercial.codename1 }}>{{ commercial.codename2 }}>{{
          commercial.codename3
        }}>{{ commercial.codename4 }}
      </div>
      <div>주소: {{ commercial.address }}</div>
    </div>
    <div v-else-if="pollution">
      <h2>녹지 정보 Preview</h2>
      <div>이름: {{ pollution.name }}</div>
      <div>위치: {{ pollution.sidoName }} {{ pollution.gugunName }}</div>
    </div>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
const dealStore = "dealStore";
const commercialStore = "commercialStore";
const pollutionStore = "pollutionStore";
export default {
  name: "DetailPreView",
  computed: {
    ...mapState(dealStore, ["house"]),
    ...mapState(commercialStore, ["commercial"]),
    ...mapState(pollutionStore, ["pollution"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style></style>
