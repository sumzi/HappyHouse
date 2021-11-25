<template>
  <v-card id="popup" class="text-center">
    <div v-if="house">
      <v-img
        src="https://images.unsplash.com/photo-1549517045-bc93de075e53?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80"
        height="100px"
        dark
      >
        <v-card-title class="white--text pl-2 pt-2">
          <div class="text-h5 pl-12 pt-12 font-weight-black">
            {{ house.aptName }}
          </div>
        </v-card-title>
      </v-img>

      <v-list two-line>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-domain </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ house.dongName }}</v-list-item-title>
            <v-list-item-subtitle>동</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider inset></v-divider>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-wrench </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ house.buildYear }}</v-list-item-title>
            <v-list-item-subtitle>건축 연도</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider inset></v-divider>

        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-map-marker </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ house.lat }}</v-list-item-title>
            <v-list-item-subtitle>위도</v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-content>
            <v-list-item-title>{{ house.lng }}</v-list-item-title>
            <v-list-item-subtitle>경도</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider inset></v-divider>

        <v-list-item>
          <v-list-item-icon class="pa-2">
            <v-icon color="indigo"> mdi-cash-fast </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title
              >{{
                (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
              }}
              원</v-list-item-title
            >
            <v-list-item-subtitle>최근 거래가</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action></v-list-item-action>

          <v-list-item-content>
            <v-list-item-title
              >{{ (avgPrice * 10000) | price }} 원</v-list-item-title
            >
            <v-list-item-subtitle>검색 결과 평균거래금액</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </div>
    <div v-else-if="commercial">
      <v-img
        src="https://images.unsplash.com/photo-1504297050568-910d24c426d3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
        height="100px"
        dark
      >
        <v-card-title class="white--text pl-2 pt-2">
          <div class="text-h5 pl-12 pt-12 font-weight-black">
            {{ commercial.shopname }}
            {{
              commercial.localname + commercial.localname.length > 0 ? "점" : ""
            }}
          </div>
        </v-card-title>
      </v-img>

      <v-list two-line>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-domain </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title
              >{{ commercial.codename1 }}>{{ commercial.codename2 }}>{{
                commercial.codename3
              }}</v-list-item-title
            >
            <v-list-item-subtitle>카테고리</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action></v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>{{ commercial.codename4 }}</v-list-item-title>
            <v-list-item-subtitle>세부분류</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider inset></v-divider>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-post </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ commercial.postcode }}</v-list-item-title>
            <v-list-item-subtitle>우편번호</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider inset></v-divider>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-note-text-outline </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ commercial.address }}</v-list-item-title>
            <v-list-item-subtitle>주소</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider inset></v-divider>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-map-marker </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ commercial.lat }}</v-list-item-title>
            <v-list-item-subtitle>위도</v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-content>
            <v-list-item-title>{{ commercial.lng }}</v-list-item-title>
            <v-list-item-subtitle>경도</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </div>
    <div v-else-if="pollution">
      <v-img
        src="https://images.unsplash.com/photo-1420593248178-d88870618ca0?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"
        height="300px"
        dark
      >
        <v-card-title class="white--text pl-1 pt-2" style="margin-top: 180px">
          <div class="text-h5 pl-12 pt-12 font-weight-black">
            {{ pollution.name }}
          </div>
        </v-card-title>
      </v-img>

      <v-list two-line>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-note-text-outline </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title
              >{{ pollution.sidoName }}
              {{ pollution.gugunName }}</v-list-item-title
            >
            <v-list-item-subtitle>주소</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider inset></v-divider>
        <v-list-item>
          <v-list-item-icon>
            <v-icon color="indigo"> mdi-map-marker </v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ pollution.lat }}</v-list-item-title>
            <v-list-item-subtitle>위도</v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-content>
            <v-list-item-title>{{ pollution.lng }}</v-list-item-title>
            <v-list-item-subtitle>경도</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list>
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
    ...mapState(dealStore, ["house", "avgPrice"]),
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
