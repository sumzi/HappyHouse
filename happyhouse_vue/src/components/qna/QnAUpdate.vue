<template>
  <div class="mt-4 col-8">
    <h5>update</h5>
    <table class="table table-bordered">
      <tr>
        <td>번호</td>
        <td>
          <input
            type="text"
            class="form-control"
            id="no"
            name="no"
            v-model="qna.no"
            ref="no"
            disabled
          />
        </td>
      </tr>
      <tr>
        <td>작성자</td>
        <td>
          <input
            type="text"
            class="form-control"
            id="userId"
            name="userId"
            v-model="qna.userId"
            ref="userId"
            disabled
          />
        </td>
      </tr>
      <tr>
        <td>등록일</td>
        <td>
          <input
            type="text"
            class="form-control"
            id="ndata"
            name="ndata"
            v-model="qna.ndate"
            ref="ndata"
            disabled
          />
        </td>
      </tr>
      <tr>
        <td>제목</td>
        <td>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            v-model="qna.title"
            ref="title"
            disabled
          />
        </td>
      </tr>
      <tr>
        <td>내용</td>
        <td>
          <textarea
            type="text"
            class="form-control"
            id="content"
            name="content"
            v-model="qna.content"
            ref="content"
            disabled
          />
        </td>
      </tr>
      <tr>
        <td>답변</td>
        <td>
          <textarea
            type="text"
            class="form-control"
            id="reply"
            name="reply"
            v-model="reply"
            ref="reply"
          />
        </td>
      </tr>
    </table>
    <div>
      <button class="btn btn-primary" @click="updateAnswer">답변완료</button>
      <button class="btn btn-primary" @click="moveQnAList">목록</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapGetters } from "vuex";
export default {
  name: "QnAUpdate",
  data() {
    return {
      reply: "",
    };
  },
  computed: {
    ...mapGetters(["qna"]),
  },
  methods: {
    moveQnAList() {
      this.$router.push({ name: "QnAList" });
    },
    updateAnswer() {
      let error = true;
      let msg = "";
      !this.reply &&
        ((msg = "답변을 입력해 주세요"),
        (error = false),
        this.$refs.reply.focus());

      if (!error) {
        alert(msg);
      } else {
        http
          .put("/qna/reply", {
            reply: this.reply,
            no: this.qna.no,
          })
          .then((res) => {
            if (res.data === "success") {
              alert("답변 등록 성공");
              this.$router.push({ name: "QnAList" });
            } else {
              alert("등록 실패!!!");
            }
          });
      }
    },
  },
};
</script>

<style></style>
