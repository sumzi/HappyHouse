<template>
  <div class="d-flex justify-center">
    <div class="col-8">
      <!-- 관리자만 보이게 해야한다. -->
      <div class="d-flex justify-end">
        <v-btn outlined rounded color="success" @click="moveNoticeRegist"
          >공지등록</v-btn
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
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="(notice, index) in noticelist"
              :key="index"
              @click="selectRow(notice.no)"
            >
              <td>{{ notice.no }}</td>
              <td>{{ notice.title }}</td>
              <td>{{ notice.userId }}</td>
              <td>{{ notice.ndate }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>

      <div class="text-center mt-10">
        <v-pagination
          v-model="page"
          :length="5"
          color="#019689"
          prev-icon="mdi-menu-left"
          next-icon="mdi-menu-right"
        ></v-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "NoticeList",
  computed: {
    ...mapGetters(["noticelist"]),
  },
  created() {
    this.$store.dispatch("getNoticeList");
  },
  methods: {
    moveNoticeRegist() {
      this.$router.push({ name: "NoticeRegist" });
    },
    selectRow(no) {
      this.$router.push({ name: "NoticeSearch", params: { no: no } });
    },
  },
};
</script>

<style></style>
