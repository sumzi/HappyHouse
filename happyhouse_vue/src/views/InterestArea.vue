<template>
  <div>
    <header-nav />
    <v-container class="text-center mt-8">
      <h2>관심 지역</h2>
    </v-container>
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
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("interestStore", ["houselist"]),
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
