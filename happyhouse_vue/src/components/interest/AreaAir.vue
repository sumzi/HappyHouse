<template>
  <v-card width="300px" elevation="2" class="pa-5 text-center">
    <h2>
      <strong>{{ address.gugun }}</strong
      >의 미세먼지
    </h2>
    <h4 class="mt-9">
      {{ grade[air.pm10Grade].state }}
    </h4>
    <h1 class="ma-3">{{ grade[air.pm10Grade].icon }}</h1>
    <h3>{{ air.pm10Value }} ㎍/㎥</h3>
    <h5>{{ air.dataTime }}</h5>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "AreaAir",
  data() {
    return {
      grade: [
        "",
        { icon: "😍", state: "좋음", color: "lightblue" },
        { icon: "🙂", state: "보통", color: "lightgreen" },
        { icon: "🙁", state: "나쁨", color: "lightorange" },
        { icon: "😭", state: "매우나쁨", color: "lightred" },
      ],
    };
  },
  computed: {
    ...mapState("interestStore", ["address", "air"]),
  },
  created() {},
};
</script>

<style></style>
