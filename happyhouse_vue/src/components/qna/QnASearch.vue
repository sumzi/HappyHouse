<template>
  <div class="d-flex justify-center">
    <div class="col-6">
      <v-card elevation="2" outlined class="pa-6">
        <v-card-title> Q. {{ qna.title }}</v-card-title>
        <v-card-subtitle class="d-flex justify-space-between"
          ><p>{{ qna.userId }}</p>
          <p>{{ qna.ndate }}</p></v-card-subtitle
        >
        <v-card-text class="">{{ qna.content }}</v-card-text>
      </v-card>
      <v-card
        class="mt-5 pa-6"
        elevation="2"
        outlined
        v-if="qna.reply !== null"
      >
        <v-card-title>A.</v-card-title>
        <v-card-text class="pb-5">{{ qna.reply }}</v-card-text>
      </v-card>
      <!-- 관리자만 보이도록 -->
      <v-card-actions class="d-flex justify-space-around mt-6">
        <v-btn color="primary" outlined rounded text @click="updateQnA"
          >답변</v-btn
        >
        <v-btn color="error" outlined rounded text @click="deleteQnA"
          >삭제</v-btn
        >
        <v-btn color="success" outlined rounded text @click="moveQnAList"
          >목록</v-btn
        >
      </v-card-actions>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["qna"]),
  },
  created() {
    this.$store.dispatch("getQnA", this.$route.params.no);
  },
  methods: {
    moveQnAList() {
      this.$router.push({ name: "QnAList" });
    },
    updateQnA() {
      this.$router.push({ name: "QnAUpdate", params: this.qna.no });
    },
    deleteQnA() {
      http.delete(`/qna/${this.qna.no}`).then((response) => {
        if (response.data === "success") {
          alert("삭제 성공");
          this.$router.push({ name: "QnAList" });
        } else {
          alert("삭제 실패!!!");
        }
      });
    },
  },
};
</script>

<style></style>
