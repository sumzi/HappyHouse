<template>
  <li class="item" @click="selectHouse">
    <div
      style="border: 1px solid lightgray"
      class="pa-2 mr-3 mt-3 d-flex justify-space-between"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{ 'mouse-over-bgcolor': isColor }"
    >
      <div class="markerbg">
        <img
          src="https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3e0.png"
          alt="아파트"
          srcset=""
          width="30px"
          height="30px"
        />
      </div>
      <div class="content">
        <h5>{{ house.aptName }}</h5>
        <div>
          {{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}
          {{ house.jibun }}
        </div>
      </div>
      <div>
        <div v-if="this.houselist.includes(house.aptCode)" @click.stop="unlike">
          💗
        </div>
        <div v-else @click.stop="like">🤍</div>
      </div>
    </div>
  </li>
</template>

<script>
import { mapState, mapActions } from "vuex";

const dealStore = "dealStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
    num: Number,
  },
  computed: {
    ...mapState("interestStore", ["houselist"]),
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapActions("interestStore", [
      "getInterestHouse",
      "likeHouse",
      "unlikeHouse",
    ]),
    ...mapActions(dealStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
      this.$router.push({ name: "HouseDetail" });
    },
    colorChange(flag) {
      this.isColor = flag;
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
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
.option {
  text-align: center;
}
.option p {
  margin: 10px 0;
}
.option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  /* border-bottom: 1px solid #888; */
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .content {
  margin-top: 4px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .content {
  padding: 10px 0 10px 55px;
}
#placesList .content .gray {
  color: #8a8a8a;
}
#placesList .content .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .content .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 20px 0 0 20px;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
</style>
