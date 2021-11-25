<template>
  <div>
    <v-container>
      <div class="d-flex justify-center">
        <div
          class="mb-4 mt-8 d-flex justify-space-between"
          style="width: 800px"
        >
          <v-icon @click="goBack" large> mdi-arrow-left </v-icon>
          <h2>{{ this.house.aptName }}</h2>
          <v-icon
            @click="unlike"
            large
            color="red"
            v-if="likelist.includes(this.house.aptCode)"
          >
            mdi-heart
          </v-icon>
          <v-icon @click="like" large v-else> mdi-heart-outline </v-icon>
        </div>
      </div>

      <div class="d-flex justify-center">
        <div id="map" style="width: 400px; height: 400px"></div>

        <div style="width: 400px" class="ml-4">
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th colspan="2">상세정보</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>시도</td>
                  <td>
                    {{ house.sidoName }}
                  </td>
                </tr>
                <tr>
                  <td>구군</td>
                  <td>
                    {{ house.gugunName }}
                  </td>
                </tr>
                <tr>
                  <td>동</td>
                  <td>
                    {{ house.dongName }}
                  </td>
                </tr>
                <tr>
                  <td>건축연도</td>
                  <td>{{ house.buildYear }}</td>
                </tr>
                <tr>
                  <td>지번</td>
                  <td>{{ house.jibun }}</td>
                </tr>
                <tr>
                  <td>최근거래금액</td>
                  <td>{{ house.recentPrice }} 만원</td>
                </tr>
                <tr>
                  <td>지역 평균거래금액</td>
                  <td>{{ avgPrice | price }} 만원</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </div>
      </div>
      <div class="d-flex justify-center">
        <v-card class="mt-10 pa-5" width="800px" elevation="2">
          <v-card-title>최근 거래 목록</v-card-title>
          <v-card-text v-if="housedeals.length > 1">
            <v-sheet color="grey lighten-4">
              <v-sparkline
                :value="prices"
                :gradient="['#ffd200', '#1feaea']"
                height="100"
                padding="24"
                stroke-linecap="round"
                smooth
                color="black"
              >
                <template v-slot:label="item"> {{ item.value }}만원 </template>
              </v-sparkline>
            </v-sheet>
          </v-card-text>
          <v-card-text>
            <v-simple-table>
              <template v-slot:default>
                <thead>
                  <tr>
                    <th>년</th>
                    <th>월</th>
                    <th>일</th>
                    <th>거래 금액</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="deal in housedeals" :key="deal.no">
                    <td>{{ deal.dealYear }}</td>
                    <td>{{ deal.dealMonth }}</td>
                    <td>{{ deal.dealDay }}</td>
                    <td>{{ deal.dealAmount }} 만원</td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-card-text>
        </v-card>
      </div>
      <div class="d-flex justify-center">
        <v-card class="mt-10 pa-5" width="800px" elevation="2">
          <v-card-title>주변 기반 시설</v-card-title>
          <v-card-text>
            <v-tabs color="success" v-model="tab">
              <v-tab v-for="item in items" :key="item">{{ item }}</v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
              <v-tab-item v-for="(item, index) in items" :key="index">
                <v-card flat>
                  <v-card-text>
                    <v-simple-table>
                      <thead></thead>
                      <tbody>
                        <tr v-for="data in getInfraAll[index]" :key="data.id">
                          <td>
                            <strong>{{ data.distance }}</strong> m 거리에
                            <v-tooltip right>
                              <template v-slot:activator="{ on, attrs }">
                                <strong v-bind="attrs" v-on="on">{{
                                  data.place_name
                                }}</strong>
                              </template>
                              <span>{{ data.address_name }}</span>
                            </v-tooltip>
                          </td>
                        </tr>
                      </tbody>
                    </v-simple-table>
                  </v-card-text>
                </v-card>
              </v-tab-item>
            </v-tabs-items>
            <router-view />
          </v-card-text>
        </v-card>
      </div>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";

const dealStore = "dealStore";
const infraStore = "infraStore";
const interestStore = "interestStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      tab: null,
      items: ["학교", "편의점", "지하철", "병원", "카페", "문화시설"],
      map: null,
      markers: [],
      infowindow: null,
      customOverlay: null,
    };
  },
  computed: {
    ...mapState(dealStore, ["house", "avgPrice", "housedeals", "prices"]),
    ...mapGetters(infraStore, ["getInfraAll"]),
    ...mapState(interestStore, ["likelist"]),
    ...mapState("userStore", ["userInfo"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    ...mapActions("interestStore", [
      "getInterestHouse",
      "likeHouse",
      "unlikeHouse",
    ]),
    ...mapActions(infraStore, ["getInfraList"]),
    ...mapActions(dealStore, ["getHouseDeal"]),
    goBack() {
      this.$router.go(-1);
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
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.house.lat, this.house.lng), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      this.map = new kakao.maps.Map(container, options);

      var imageSize = new kakao.maps.Size(50, 50), // 마커 이미지의 크기
        imgOptions = {
          // spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          // spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(25, 25), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3e0.png",
          imageSize,
          imgOptions
        );
      var markerPosition = new kakao.maps.LatLng(
        this.house.lat,
        this.house.lng
      );

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
        image: markerImage,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
      this.map.setDraggable(false);
      this.map.setZoomable(false);
    },
  },
  created() {
    this.getInfraList({ x: this.house.lng, y: this.house.lat });
    this.getHouseDeal(this.house.aptCode);
    if (window.kakao && window.kakao.maps) {
      // kakao.maps.load(this.initMap);
      this.initMap();
    } else {
      this.addKakaoMapScript();
    }
    setTimeout(this.initMap, 200);
  },
};
</script>

<style scoped>
a {
  color: black;
}
</style>
