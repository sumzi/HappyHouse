<template>
  <div class="d-flex justify-center">
    <div class="col-6">
      <v-card elevation="2" outlined class="pa-6">
        <v-card-title>Q. {{ qna.title }}</v-card-title>
        <v-card-text>
          {{ qna.content }}
        </v-card-text>
        <v-card-text>
          <v-textarea
            color="success"
            label="답변"
            v-model="reply2"
            ref="reply"
          ></v-textarea>
        </v-card-text>
        <v-card-actions class="d-flex justify-space-around">
          <v-btn color="primary" outlined rounded text @click="updateAnswer">
            답변
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
import { mapState } from "vuex";
export default {
  name: "QnAUpdate",
  data() {
    return {
      reply2: "",
    };
  },
  created() {
    this.reply2 = this.qna.reply;
  },
  computed: {
    ...mapState("qnaStore", ["qna"]),
  },
  methods: {
    resetQnA() {
      this.reply2 = this.qna.reply;
    },
    moveQnAList() {
      this.$router.push({ name: "QnAList" });
    },
    updateAnswer() {
      let error = true;
      let msg = "";
      !this.reply2 &&
        ((msg = "답변을 입력해 주세요"),
        (error = false),
        this.$refs.reply.focus());

      if (!error) {
        alert(msg);
      } else {
        http
          .put("/qna/reply", {
            reply: this.reply2,
            no: this.qna.no,
          })
          .then((res) => {
            if (res.data === "success") {
              alert("답변 등록이 되었습니다.");
              this.$router.push({ name: "QnAList" });
            } else {
              alert("답변 등록에 실패했습니다.");
            }
          });
      }
    },
  },
};
</script>

<style></style>
