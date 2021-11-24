<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
      <h2>관심 지역</h2>
    </v-container>

    <v-dialog v-model="dialog" width="500">
      <template
        v-slot:activator="{ on, attrs }"
        v-if="interestAreaUser === null"
      >
        <div>
          <v-btn rounded color="success" v-bind="attrs" v-on="on"> 등록 </v-btn>
        </div>
      </template>
      <template v-slot:activator="{ on, attrs }" v-else>
        <div>
          해당 지역을 알려준다
          <v-btn rounded color="success" v-bind="attrs" v-on="on"> 수정 </v-btn>
          <v-btn rounded color="success"> 삭제</v-btn>
        </div>
      </template>
      <v-card> 주소 등록하는 공간 </v-card>
    </v-dialog>

    <router-view />
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "InterestHouse",
  components: {
    HeaderNav,
  },
  data() {
    return {
      dialog: false,
    };
  },
  computed: {
    ...mapState("dealStore", [
      "sidos",
      "guguns",
      "dongs",
      "sido",
      "gugun",
      "dong",
    ]),
    ...mapState("interestStore", [
      "houselist",
      "interestArea",
      "interestAreaUser",
    ]),
    ...mapGetters("infraStore", ["getInfraAll"]),
  },
  methods: {
    ...mapActions("interestStore", ["getInterestHouse"]),
    ...mapActions("infraStore", ["getInfraList"]),
  },
  created() {
    this.getInfraList({ y: 37.5743822, x: 126.9688505 });
    console.log(this.interestAreaUser);
  },
};
</script>

<style></style>
