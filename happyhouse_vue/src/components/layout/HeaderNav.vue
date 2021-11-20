<template>
  <div class="d-flex justify-space-between header-container">
    <div id="logo">
      <router-link :to="{ name: 'Index' }"
        ><img src="@/assets/hh3.png"
      /></router-link>
    </div>
    <!-- 회원 -->
    <div v-if="userInfo" class="menu-container">
      <ul class="menu">
        <li>
          <router-link :to="{ name: 'Intro' }">해피하우스</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Deal' }">집찾기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Notice' }">공지사항</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'QnA' }">문의하기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'User' }">회원 정보</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'User' }"
            >{{ userInfo.userName }}님</router-link
          >
        </li>
      </ul>
    </div>
    <!-- 비회원 -->
    <div v-else class="menu-container">
      <ul class="menu">
        <li>
          <router-link :to="{ name: 'Intro' }">해피하우스</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Deal' }">집찾기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Notice' }">공지사항</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'QnA' }">문의하기</router-link>
        </li>

        <li>
          <router-link :to="{ name: 'Login' }">로그인</router-link>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  name: "HeaderNav",
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations("userStore", ["SET_USER_INFO", "USER_LOGIN"]),
    userLogout() {
      this.USER_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("userId");
      if (this.$route.path != "/") this.$router.push({ name: "Index" });
    },
  },
};
</script>

<style>
#logo {
  width: 60px;
  margin: 15px 20px;
}
#logo img {
  width: 100%;
  height: 100%;
}
.header-container {
  border-bottom: 1px solid lightgray;
}
.menu {
  height: 100%;
}
.menu-container {
  margin-right: 20px;
}
.menu > li {
  display: inline-block;
  margin: 25px 16px;
  font-size: 16px;
}

.menu a {
  font-weight: bold;
  color: red;
}
</style>
