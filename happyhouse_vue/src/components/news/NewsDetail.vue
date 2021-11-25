<template>
  <div>
    <div class="d-flex justify-center">
      <div style="width: 800px" class="mb-4 mt-8">
        <v-icon @click="goBack" large> mdi-arrow-left </v-icon>
      </div>
    </div>
    <div class="d-flex justify-center">
      <v-card max-width="800px" class="pa-5 mt-8" elevation="5">
        <div>
          <div v-html="pressLogo"></div>
          <div v-html="title"></div>
          <div class="news_content" v-html="content"></div>
          <v-divider inset></v-divider>
          <div class="text-right mt-2" v-html="byline"></div>
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";

export default {
  name: "NewsDetail",
  data() {
    return {
      pressLogo: String,
      title: String,
      content: String,
      byline: String,
    };
  },
  props: {
    detailLink: String,
  },
  created() {
    // console.log(this.detailLink);
    http
      .get("/news/detail", { params: { url: this.detailLink } })
      .then((response) => {
        // console.log(response);
        this.pressLogo = response.data.pressLogo;
        this.title = response.data.title;
        this.content = response.data.content;
        this.byline = response.data.byline;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
  },
};
</script>

<style>
.end_photo_org {
  position: relative;
  z-index: 1;
  display: block;
  margin-top: 24px;
  text-align: center;
  zoom: 1;
}
.end_photo_org .img_desc {
  display: block;
  padding-bottom: 50px;
  color: #666;
  letter-spacing: -0.3px;
  line-height: 19px;
  font-size: 12px;
}
</style>
