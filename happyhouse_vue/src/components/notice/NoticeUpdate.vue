<template>
  <div class="d-flex justify-center">
    <div class="col-6">
      <v-card elevation="2" outlined class="pa-6">
        <v-card-title>수정하기</v-card-title>
        <v-card-text>
          <v-text-field
            label="제목"
            color="success"
            v-model="title2"
            ref="title"
          ></v-text-field>
        </v-card-text>
        <v-card-text>
          <v-textarea
            color="success"
            label="내용"
            v-model="content2"
            ref="content"
          ></v-textarea>
        </v-card-text>
        <v-card-actions class="d-flex justify-space-around">
          <v-btn color="primary" outlined rounded text @click="updateNotice">
            수정
          </v-btn>
          <v-btn color="warning" outlined rounded text @click="resetNotice">
            초기화
          </v-btn>
          <v-btn color="success" outlined rounded text @click="moveNoticeList">
            목록
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapGetters } from "vuex";
export default {
  name: "NoticeUpdate",
  computed: {
    ...mapGetters(["notice"]),
  },
  data() {
    return {
      title2: "",
      content2: "",
    };
  },
  created() {
    this.title2 = this.notice.title;
    this.content2 = this.notice.content;
  },
  methods: {
    resetNotice() {
      this.title2 = this.notice.title;
      this.content2 = this.notice.content;
    },
    moveNoticeList() {
      this.$router.push({ name: "NoticeList" });
    },
    updateNotice() {
      let error = true;
      let msg = "";
      !this.title2 &&
        ((msg = "답변을 입력해 주세요"),
        (error = false),
        this.$refs.title.focus());
      error &&
        !this.content2 &&
        ((msg = "내용 입력해주세요"),
        (error = false),
        this.$refs.content.focus());

      if (!error) alert(msg);
      else {
        http
          .put("/notice", {
            no: this.notice.no,
            title: this.title2,
            content: this.content2,
          })
          .then((res) => {
            if (res.data === "success") {
              alert("수정 성공");
            } else {
              alert("수정 실패");
            }
            this.$router.push({ name: "NoticeSearch" });
          });
      }
    },
  },
};
</script>

<style></style>
