<template>
  <div class="d-flex justify-center">
    <div class="col-6">
      <v-card elevation="2" outlined class="pa-6">
        <v-card-title>등록하기</v-card-title>
        <v-card-text>
          <v-text-field
            label="제목"
            color="success"
            v-model="title"
            ref="title"
          ></v-text-field>
        </v-card-text>
        <v-card-text>
          <v-textarea
            color="success"
            label="내용"
            v-model="content"
            ref="content"
          ></v-textarea>
        </v-card-text>
        <v-card-actions class="d-flex justify-space-around">
          <v-btn color="primary" outlined rounded text @click="registNotice">
            등록
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
import { mapState } from "vuex";
import http from "@/util/http-common";
export default {
  name: "NoticeRegist",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    resetNotice() {
      this.title = "";
      this.content = "";
    },
    moveNoticeList() {
      this.$router.push({ name: "NoticeList" });
    },
    registNotice() {
      let error = true;
      let msg = "";
      !this.title &&
        ((msg = "제목을 입력해 주세요"),
        (error = false),
        this.$refs.title.focus());
      error &&
        !this.content &&
        ((msg = "내용을 입력해 주세요"),
        (error = false),
        this.$refs.content.focus());
      if (!error) {
        alert(msg);
      } else {
        http
          .post("/notice", {
            title: this.title,
            content: this.content,
            userId: this.userInfo.userId,
          })
          .then((res) => {
            if (res.data === "success") {
              alert("등록 되었습니다.");
              this.$router.push({ name: "NoticeList" });
            } else {
              alert("등록에 실패했습니다.");
            }
          })
          .catch((error) => {
            alert(error);
          });
      }
    },
  },
};
</script>

<style></style>
