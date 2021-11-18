<template>
  <div class="container">
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8">
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
      </div>
    </div>
    <div>
      <button class="btn btn-primary" @click="registNotice">등록</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "NoticeRegist",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
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
            userId: "ssafy",
          })
          .then((res) => {
            if (res.data === "success") {
              alert("등록 성공");
              this.$router.push({ name: "NoticeList" });
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
