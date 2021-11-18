<template>
  <div class="mt-4 col-8">
    <table class="table table-bordered">
      <tr>
        <td>제목</td>
        <td>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            v-model="title"
            ref="title"
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
            v-model="content"
            ref="content"
          />
        </td>
      </tr>
    </table>
    <div>
      <button class="btn btn-primary" @click="registQnA">등록</button>
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
