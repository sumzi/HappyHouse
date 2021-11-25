<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8"> <h2>관심 지역</h2></v-container>

    <v-dialog v-model="dialog" width="700">
      <template v-slot:activator="{ on, attrs }">
        <div class="d-flex justify-center">
          <v-card
            rounded="pill"
            color="green lighten-2"
            class="text-center pa-5"
            style="width: 400px; font-size: 24px"
            v-bind="attrs"
            v-on="on"
          >
            <div v-if="interestAreaUser">
              <strong>
                {{ address.sido }} {{ address.gugun }} {{ address.dong }}
              </strong>
            </div>
            <div v-else><strong> 등록해주세요 </strong></div>
          </v-card>
        </div>
      </template>
      <v-card height="200px" class="pa-5">
        <div class="d-flex justtify-center">
          <v-card-text>
            <v-select
              success
              solo
              rounded
              label="시도"
              v-model="sidoCode"
              :items="sidoList"
              @change="changeSido"
            ></v-select>
          </v-card-text>
          <v-card-text>
            <v-select
              success
              solo
              rounded
              label="구군"
              v-model="gugunCode"
              :items="gugunList"
              @change="changeGugun"
            ></v-select>
          </v-card-text>
          <v-card-text>
            <v-select
              success
              solo
              rounded
              label="동"
              v-model="dongCode"
              :items="dongList"
              @change="changeDong"
            ></v-select>
          </v-card-text>
        </div>
        <v-card-actions class="d-flex justify-center">
          <v-btn
            outlined
            color="primary"
            rounded
            large
            @click="insertArea"
            v-if="this.interestAreaUser === null"
            >등록</v-btn
          >
          <v-btn
            outlined
            color="success"
            rounded
            large
            @click="updateArea"
            v-if="this.interestAreaUser !== null"
            >수정</v-btn
          >
          <v-btn
            outlined
            color="error"
            rounded
            large
            @click="deleteArea"
            v-if="this.interestAreaUser !== null"
            >삭제</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>

    <router-view />
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import { mapState, mapActions, mapGetters } from "vuex";
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "InterestHouse",
  components: {
    HeaderNav,
  },
  data() {
    return {
      sidoList: [],
      gugunList: [],
      dongList: [],
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      dialog: false,
    };
  },
  computed: {
    ...mapState("interestStore", [
      "houselist",
      "interestArea",
      "interestAreaUser",
      "address",
    ]),
    ...mapState("userStore", ["userInfo"]),
    ...mapGetters("infraStore", ["getInfraAll"]),
  },
  methods: {
    ...mapActions("interestStore", [
      "getInterestHouse",
      "getInterestAreaAddress",
      "insertInterestArea",
      "updateInterestArea",
      "deleteInterestArea",
    ]),
    ...mapActions("infraStore", ["getInfraList"]),
    changeSido() {
      http.get(`/map/gugun?sido=${this.sidoCode}`).then((response) => {
        this.gugunList = response.data.map((gugun) => ({
          value: gugun.gugunCode,
          text: gugun.gugunName,
        }));
      });
    },
    changeGugun() {
      http.get(`/map/dong?gugun=${this.gugunCode}`).then((response) => {
        this.dongList = response.data.map((dong) => ({
          value: dong.dongCode,
          text: dong.dongName,
        }));
      });
    },
    changeDong() {},
    async insertArea() {
      if (this.check()) {
        await this.insertInterestArea({
          userId: this.userInfo.userId,
          dongCode: this.dongCode,
        });
        await this.getInterestAreaAddress(this.interestAreaUser);
        this.dialog = false;
      }
    },
    async updateArea() {
      if (this.check()) {
        await this.updateInterestArea({
          userId: this.userInfo.userId,
          dongCode: this.dongCode,
        });
        await this.getInterestAreaAddress(this.interestAreaUser);
        this.dialog = false;
      }
    },
    deleteArea() {
      this.deleteInterestArea(this.userInfo.userId);
      this.dialog = false;
    },
    check() {
      if (this.sidoCode === null) {
        return alert("시도를 선택해주세요");
      } else if (this.gugunCode === null) {
        return alert("구군을 선택해주세요");
      } else if (this.dongCode === null) {
        return alert("동을 선택해주세요");
      } else {
        return true;
      }
    },
  },
  created() {
    if (this.interestAreaUser)
      this.getInterestAreaAddress(this.interestAreaUser);
    http.get("/map/sido").then((response) => {
      this.sidoList = response.data.map((sido) => ({
        value: sido.sidoCode,
        text: sido.sidoName,
      }));
    });
  },
};
</script>

<style></style>
