<template>
  <div>
    <header-nav />
    <div class="d-flex justify-center findpw-container">
      <v-card width="350px" elevation="5" class="pa-8">
        <v-card-title>비밀번호 찾기</v-card-title>
        <v-card-text>
          <v-text-field
            v-model="user.userId"
            height="50px"
            color="success"
            placeholder="아이디를 입력해주세요"
            label="id"
          >
          </v-text-field>
          <v-text-field
            v-model="user.userName"
            height="50px"
            color="success"
            placeholder="이름을 입력해주세요"
            label="name"
          >
          </v-text-field>
          <v-text-field
            v-model="user.userEmail"
            height="50px"
            color="success"
            placeholder="이메일을 입력해주세요"
            label="email"
          >
          </v-text-field>
        </v-card-text>
        <v-card-actions>
          <v-btn
            @click="findPw"
            height="50px"
            width="100%"
            color="#019689"
            rounded
            class="white--text"
            >찾기</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
import http from "@/util/http-common.js";
export default {
  name: "FindPw",
  components: {
    HeaderNav,
  },
  data() {
    return {
      user: {
        userId: "",
        userName: "",
        userEmail: "",
      },
    };
  },
  methods: {
    findPw() {
      http.post("user/find", this.user).then((response) => {
        if (response.data.message === "success") {
          alert("비밀번호는 " + response.data.pass + " 입니다.");
          this.$router.push({ name: "Login" });
        }
      });
    },
  },
};
</script>

<style scoped>
.findpw-container {
  margin: 80px 0;
}
</style>
