<template>
  <div class="mt-4 col-8">
    <table class="table table-bordered table-hover" style="table-layout: fixed">
      <colgroup>
        <col style="width: 10%" />
        <col style="width: 40%" />
        <col style="width: 15%" />
        <col style="width: 20%" />
        <col style="width: 15%" />
      </colgroup>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>등록일</th>
          <th>답변상태</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(qna, index) in qnalist"
          :key="index"
          @click="selectRow(qna.no)"
        >
          <td>{{ qna.no }}</td>
          <td>{{ qna.title }}</td>
          <td>{{ qna.userId }}</td>
          <td>{{ qna.ndate }}</td>
          <td>{{ qna.reply === null ? "답변대기중" : "답변완료" }}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <router-link class="btn btn-primary" :to="{ name: 'QnARegist' }"
        >등록</router-link
      >
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "QnAList",
  computed: {
    ...mapGetters(["qnalist"]),
  },
  created() {
    this.$store.dispatch("getQnAList");
  },
  methods: {
    selectRow(no) {
      this.$router.push({ name: "QnASearch", params: { no: no } });
    },
    // insertQnA() {
    //   this.$router.push();
    // },
  },
};
</script>

<style></style>
