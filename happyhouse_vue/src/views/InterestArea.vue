<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
      <h2>관심 지역</h2>
    </v-container>

    <div class="d-flex justify-center">
      <div style="width: 600px">
        <!--관심 지역이 없을때  -->
        <div>
          <house-address-search />
        </div>
      </div>
    </div>

    <router-view />
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
import HeaderNav from "../components/layout/HeaderNav.vue";
import HouseAddressSearch from "../components/map/house/HouseAddressSearch.vue";
export default {
  name: "InterestHouse",
  components: {
    HeaderNav,
    HouseAddressSearch,
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
  },
  mounted() {
    console.log(this.getInfraAll);
  },
};
</script>

<style></style>
