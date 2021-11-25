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
      <v-card-actions class="d-flex justify-space-around mt-6">
        <v-btn
          color="primary"
          outlined
          rounded
          text
          @click="updateNotice"
          v-if="userInfo && userInfo.role === 'admin'"
          >수정</v-btn
        >
        <v-btn
          color="error"
          outlined
          rounded
          text
          @click="deleteNotice"
          v-if="userInfo && userInfo.role === 'admin'"
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
import { mapState, mapActions } from "vuex";
export default {
  name: "NoticeSearch",
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("noticeStore", ["notice"]),
  },
  created() {
    this.getNotice(this.$route.params.no);
    console.log(this.notice);
  },
  methods: {
    ...mapActions("noticeStore", ["getNotice"]),
    moveNoticeList() {
      this.$router.push({ name: "NoticeList" });
    },
    updateNotice() {
      this.$router.push({
        name: "NoticeUpdate",
        params: { no: this.notice.no },
      });
    },
    deleteNotice() {
      http.delete(`/notice/${this.notice.no}`).then((response) => {
        if (response.data === "success") {
          alert("삭제되었습니다.");
          this.$router.push({ name: "NoticeList" });
        } else {
          alert("삭제에 실패했습니다.");
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
