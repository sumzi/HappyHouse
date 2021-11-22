<template>
  <v-card width="300px" elevation="5" class="pa-5 text-center">
    <h2>"강남구"의 미세먼지</h2>
    <h3>{{ item.pm10Value }} ㎍/㎥</h3>
    <h1>{{ grade[item.pm10Grade].icon }}</h1>
    <div>{{ grade[item.pm10Grade].state }}</div>
    <h5>{{ item.dataTime }}</h5>
  </v-card>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  name: "AreaAir",
  data() {
    return {
      item: {},
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
    ...mapState("userStore", ["userInfo", "isLogin"]),
  },
  created() {
    const area = "강남구";
    if (this.isLogin) {
      axios
        .get(
          `http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=${area}&dataTerm=month&pageNo=1&numOfRows=1&returnType=json&serviceKey=K7Wn8ITiQ1up51wuD3nvRKU24dRAJTj6ERIB%2Bjk2XoEkMdgrQXLHY7D%2Bg2%2FJDKtT%2BWwXLzaJEYvKTC72QbL1PA%3D%3D`
        )
        .then((response) => {
          this.item = response.data.response.body.items[0];
          console.log(this.item);
        });
    }
  },
};
</script>

<style></style>
