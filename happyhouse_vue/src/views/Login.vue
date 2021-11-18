<template>
  <div>
    <header-nav />
    <v-container class="container">
      <v-card class="login-container">
        <div class="login-header">LOGIN</div>
        <div class="login-input">
          <input type="text" placeholder="Id" v-model="userId" />
        </div>
        <div class="login-input">
          <input type="password" placeholder="Password" v-model="userPw" />
        </div>
        <div class="login-button" @click="login">SIGN IN</div>
        <div>아이디 저장 | 회원가입 | 비밀번호 찾기</div>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  components: {
    HeaderNav,
  },
  data() {
    return {
      userId: "",
      userPw: "",
    };
  },
  methods: {
    login() {
      this.$store.dispatch("loginUser", {
        userId: this.userId,
        userPw: this.userPw,
      });
    },
  },
};
</script>

<style>
.container {
  margin-top: 80px;
  display: flex;
  justify-content: center;
  /* border: 1px solid red; */
}
.login-container {
  width: 370px;
  height: 500px;
  text-align: center;
}
.login-header {
  padding: 60px;
  font-size: 22px;
  font-weight: bold;
  color: #019689;
}
.login-input {
  margin-bottom: 20px;
}
.login-input input {
  border: 2px solid #019689;
  border-radius: 5px;
  padding: 10px 15px;
  width: 300px;
  font-size: 18px;
  color: #019689;
  outline: none;
  width: 300px;
}
.login-button {
  background-color: #019689;
  color: white;
  padding: 15px;
  width: 300px;
  margin: 20px 35px;
  border-radius: 30px;
}
</style>
