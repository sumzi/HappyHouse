<template>
  <div class="d-flex justify-center">
    <div class="col-6">
      <v-card elevation="2" outlined class="pa-6">
        <v-card-title> {{ notice.title }}</v-card-title>
        <v-card-subtitle class="d-flex justify-space-between"
          ><p>{{ notice.userId }}</p>
          <p>{{ notice.ndate }}</p></v-card-subtitle
        >
        <v-card-text class="mb-15">{{ notice.content }}</v-card-text>
      </v-card>
      <!-- 관리자만 보이도록 -->
      <v-card-actions class="d-flex justify-space-around mt-6">
        <v-btn color="primary" outlined rounded text @click="updateNotice"
          >수정</v-btn
        >
        <v-btn color="error" outlined rounded text @click="deleteNotice"
          >삭제</v-btn
        >
        <v-btn color="success" outlined rounded text @click="moveNoticeList"
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
    ...mapGetters(["notice"]),
  },
  created() {
    this.$store.dispatch("getNotice", this.$route.params.no);
  },
  methods: {
    moveNoticeList() {
      this.$router.push({ name: "NoticeList" });
    },
    updateNotice() {
      this.$router.push({ name: "NoticeUpdate", params: this.notice.no });
    },
    deleteNotice() {
      http.delete(`/notice/${this.notice.no}`).then((response) => {
        if (response.data === "success") {
          alert("삭제 성공");
          this.$router.push({ name: "NoticeList" });
        } else {
          alert("삭제 실패!!!");
        }
      });
    },
  },
};
</script>

<style>
.btn-container {
  display: flex;
}
</style>
