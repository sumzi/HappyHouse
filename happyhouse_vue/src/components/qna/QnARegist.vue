<template>
  <div class="d-flex justify-center">
    <div class="col-6">
      <v-card elevation="2" outlined class="pa-6">
        <v-card-title>문의하기</v-card-title>
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
          <v-btn color="primary" outlined rounded text @click="registQnA">
            문의
          </v-btn>
          <v-btn color="warning" outlined rounded text @click="resetQnA">
            초기화
          </v-btn>
          <v-btn color="success" outlined rounded text @click="moveQnAList">
            목록
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "QnARegist",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    moveQnAList() {
      this.$router.push({ name: "QnAList" });
    },
    resetQnA() {
      this.title = "";
      this.content = "";
    },
    registQnA() {
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
          .post("/qna", {
            title: this.title,
            content: this.content,
            userId: "ssafy",
          })
          .then((res) => {
            if (res.data === "success") {
              alert("등록 성공");
              this.$router.push({ name: "QnAList" });
            } else {
              alert("등록 실패!!!");
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
