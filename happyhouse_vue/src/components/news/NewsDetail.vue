<template>
  <div>
    <div v-html="pressLogo"></div>
    <div v-html="title"></div>
    <div v-html="content"></div>
    <div v-html="byline"></div>
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
    console.log(this.detailLink);
    http
      .get("/news/detail", { params: { url: this.detailLink } })
      .then((response) => {
        console.log(response);
        this.pressLogo = response.data.pressLogo;
        this.title = response.data.title;
        this.content = response.data.content;
        this.byline = response.data.byline;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {},
};
</script>

<style scoped></style>
