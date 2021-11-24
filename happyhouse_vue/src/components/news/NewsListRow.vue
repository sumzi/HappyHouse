<template>
  <!-- <v-list-item v-else :key="item.title">
            <v-list-item-avatar>
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title v-html="item.title"></v-list-item-title>
              <v-list-item-subtitle
                v-html="item.subtitle"
              ></v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item> -->
  <v-list-item
    style="border: 1px solid lightgray"
    class="pa-2 mr-3 mt-3 d-flex justify-space-between"
    @mouseover="mouseOnOut(true)"
    @mouseout="mouseOnOut(false)"
    @click="selectNews"
    :class="{ 'mouse-over-bgcolor': isColor }"
    v-html="article"
  ></v-list-item>
</template>

<script>
export default {
  name: "NewsListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    article: String,
    num: Number,
    detailLink: String,
  },
  computed: {},
  methods: {
    selectNews() {
      // // console.log("listRow : ", this.house);
      // // this.$store.dispatch("getHouse", this.house);
      // this.detailHouse(this.house);
      // this.$router.push({ name: "NewsDetail" });
      console.log("기사 클릭됨");
      console.log(this.detailLink);
      this.$router.push({
        name: "NewsDetail",
        params: { detailLink: this.detailLink },
      });
    },
    mouseOnOut(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
