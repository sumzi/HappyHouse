<template>
  <v-container>
    <div class="col-6" style="border: 1px solid red">
      <v-simple-table>
        <template v-slot:default>
          <tr>
            <td class="text-center">번호</td>
            <td>
              <v-card elevation="2">
                {{ notice.no }}
              </v-card>
            </td>
          </tr>
          <tr>
            <td>작성자</td>
            <td>
              <v-card elevation="2">
                {{ notice.userId }}
              </v-card>
            </td>
          </tr>
          <tr>
            <td>등록일</td>
            <td>
              <v-card elevation="2">
                {{ notice.ndate }}
              </v-card>
            </td>
          </tr>
          <tr>
            <td>제목</td>
            <td>
              <v-card elevation="2">
                {{ notice.title }}
              </v-card>
            </td>
          </tr>
          <tr>
            <td>내용</td>
            <td>
              <v-card elevation="2">
                {{ notice.content }}
              </v-card>
            </td>
          </tr>
        </template>
      </v-simple-table>
      <div>
        <v-btn @click="updateNotice">수정</v-btn>
        <v-btn @click="deleteNotice">삭제</v-btn>
        <v-btn @click="moveNoticeList">목록</v-btn>
      </div>
    </div>
  </v-container>
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
tr {
  border: 1px solid blud;
  height: 50px;
}
</style>
