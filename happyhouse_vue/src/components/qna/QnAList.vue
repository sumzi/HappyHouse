<template>
  <div class="d-flex justify-center">
    <div class="col-8">
      <div class="d-flex justify-end">
        <v-btn outlined rounded color="success" @click="registQnA"
          >문의하기</v-btn
        >
      </div>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>등록일</th>
              <th>조회수</th>
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
              <td>{{ qna.hitCount }}</td>
              <td v-if="qna.reply === null" style="color: red">답변대기중</td>
              <td v-else style="color: blue">답변완료</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>

      <!-- <div class="text-center mt-10">
        <v-pagination
          v-model="page"
          :length="5"
          color="#019689"
          prev-icon="mdi-menu-left"
          next-icon="mdi-menu-right"
        ></v-pagination>
      </div> -->
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "QnAList",
  computed: {
    ...mapState("qnaStore", ["qnalist"]),
  },
  created() {
    this.getQnAList();
  },
  methods: {
    ...mapActions("qnaStore", ["getQnAList"]),
    selectRow(no) {
      this.$router.push({ name: "QnASearch", params: { no: no } });
    },
    registQnA() {
      this.$router.push({ name: "QnARegist" });
    },
  },
};
</script>

<style></style>
