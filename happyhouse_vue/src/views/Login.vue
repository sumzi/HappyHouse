<template>
  <div>
    <header-nav />
    <v-container class="container">
      <v-card class="login-container">
        <div class="login-header">LOGIN</div>
        <div class="login-input">
          <input type="text" placeholder="Id" v-model="user.userId" />
        </div>
        <div class="login-input">
          <input type="password" placeholder="Password" v-model="user.userPw" />
        </div>
        <div class="login-button" @click="userCheck">SIGN IN</div>
        <div>
          아이디 저장 |
          <router-link :to="{ name: 'Signup' }">회원가입</router-link> |
          비밀번호 찾기
        </div>
      </v-card>
    </v-container>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Login",
  components: {
    HeaderNav,
  },
  data() {
    return {
      user: {
        userId: null,
        userPw: null,
      },
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["userLogin", "getUserInfo"]),
    async userCheck() {
      await this.userLogin(this.user);

      if (this.isLogin) {
        await this.getUserInfo();
        this.$router.push({ name: "Index" });
      }
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
