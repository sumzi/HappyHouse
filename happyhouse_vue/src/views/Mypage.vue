<template>
  <div>
    <header-nav />
    <div class="d-flex justify-center mypage-container">
      <v-card width="400px" class="pa-8">
        <v-card-title class="pb-10">마이페이지</v-card-title>

        <v-card-text> 아이디 </v-card-text>
        <v-card-text> 이름 </v-card-text>
        <v-card-text> 비밀번호 </v-card-text>
        <v-card-text> 이메일 </v-card-text>
        <v-card-text> 전화번호 </v-card-text>
        <v-card-actions class="d-flex justify-space-around">
          <v-btn outlined rounded color="success">회원수정</v-btn>
          <v-btn outlined rounded color="error">회원탈퇴</v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Mypage",
  components: {
    HeaderNav,
  },
};
</script>

<style>
.mypage-container {
  margin: 80px 0;
}
</style>
