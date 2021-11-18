<template>
  <div class="mt-4 col-8">
    <h5>search</h5>
    <table class="table table-bordered">
      <tr>
        <td>번호</td>
        <td>
          {{ qna.no }}
        </td>
      </tr>
      <tr>
        <td>작성자</td>
        <td>{{ qna.userId }}</td>
      </tr>
      <tr>
        <td>등록일</td>
        <td>{{ qna.ndate }}</td>
      </tr>
      <tr>
        <td>제목</td>
        <td>{{ qna.title }}</td>
      </tr>
      <tr>
        <td>내용</td>
        <td>{{ qna.content }}</td>
      </tr>
      <tr>
        <td>답변</td>
        <td>{{ qna.reply }}</td>
      </tr>
    </table>
    <div>
      <button class="btn btn-primary" @click="updateQnA">답변등록</button>
      <button class="btn btn-primary" @click="deleteQnA">삭제</button>
      <button class="btn btn-primary" @click="moveQnAList">목록</button>
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
