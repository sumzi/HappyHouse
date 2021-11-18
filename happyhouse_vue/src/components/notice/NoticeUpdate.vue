<template>
  <div class="mt-4 col-8">
    <table class="table table-bordered">
      <tr>
        <td>번호</td>
        <td>
          <input
            type="text"
            class="form-control"
            id="no"
            name="no"
            v-model="notice.no"
            ref="no"
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
            v-model="notice.userId"
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
            v-model="notice.ndate"
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
            v-model="notice.title"
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
            v-model="notice.content"
            ref="content"
          />
        </td>
      </tr>
    </table>
    <div>
      <button class="btn btn-primary" @click="updateNotice">수정</button>
      <button class="btn btn-primary" @click="moveNoticeList">목록</button>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  name: "NoticeUpdate",
  data() {
    return {
      notice: {
        no: "",
        title: "",
        content: "",
        ndata: "",
        userId: "",
      },
    };
  },
  created() {
    http
      .get(`/notice/${this.$route.params.no}`)
      .then((res) => {
        this.notice = res.data;
      })
      .catch(() => alert("공지 조회 중 오류"));
  },
  methods: {
    moveNoticeList() {
      this.$router.push({ name: "NoticeList" });
    },
    updateNotice() {
      let error = true;
      let msg = "";
      !this.notice.title &&
        ((msg = "답변을 입력해 주세요"),
        (error = false),
        this.$refs.title.focus());
      error &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"),
        (error = false),
        this.$refs.content.focus());

      if (!error) alert(msg);
      else {
        http.put("/notice", this.notice).then((res) => {
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
