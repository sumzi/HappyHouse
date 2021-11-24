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

<style>
.mouse-over-bgcolor {
  background-color: lightblue;
}
.news_item {
  padding: 18px 0 20px;
  border-bottom: 1px solid #eee;
  overflow: hidden;
  position: relative;
}
.news_item > dt.photo {
  position: relative;
  float: left;
  margin-top: 2px;
  margin-right: 17px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px;
  font-weight: bold;

  text-align: -webkit-match-parent;
}
.news_item > dt:not(.photo),
.news_item > dd {
  flex-wrap: wrap;
}

.news_item > dt:not(.photo) {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 15px !important;
  font-weight: bold;
  font-family: "Helvetica Neue", "Apple SD Gothic Neo", "Malgun Gothic",
    "맑은 고딕", Dotum, "돋움", sans-serif;
  align-self: center;
  font-size: 1rem;
  margin-bottom: 2px;
  line-height: 1.2;
  color: #000;
}
.news_item > dd {
  white-space: normal;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  display: -webkit-box;
}
</style>
