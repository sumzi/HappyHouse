<template>
  <div class="d-flex justify-space-between header-container">
    <div id="logo">
      <router-link :to="{ name: 'Index' }"
        ><img src="@/assets/hh3.png"
      /></router-link>
    </div>
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
        <li v-if="userInfo.role === 'admin'">
          <router-link :to="{ name: 'User' }">회원관리</router-link>
        </li>
        <li>
          <v-menu open-on-hover bottom offset-y :nudge-width="100">
            <template v-slot:activator="{ on, attrs }">
              <div color="primary" dark v-bind="attrs" v-on="on">
                <v-icon class="mr-1"> mdi-account-circle </v-icon>
                <router-link :to="{ name: 'Mypage' }" v-bind="attrs" v-on="on"
                  >{{ userInfo.userName }}님</router-link
                >
              </div>
            </template>
            <v-list>
              <router-link :to="{ name: 'Mypage' }"
                ><v-list-item link>마이페이지</v-list-item></router-link
              >
              <router-link :to="{ name: 'InterestHouse' }"
                ><v-list-item link>관심 찜 목록</v-list-item></router-link
              >
              <router-link :to="{ name: 'InterestArea' }"
                ><v-list-item link>관심 지역</v-list-item></router-link
              >
              <v-list-item link @click="userLogout">로그아웃</v-list-item>
            </v-list>
          </v-menu>
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
          <router-link :to="{ name: 'Signup' }">회원가입</router-link>
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
      this.SET_USER_INFO(null);
      this.USER_LOGIN(false);
      sessionStorage.removeItem("userId");
      if (this.$route.path != "/") this.$router.push({ name: "Index" });
    },
  },
};
</script>

<style scoped>
#logo img {
  height: 60px;
  margin: 10px 20px;
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
  margin: 30px 16px;
  font-size: 16px;
}
.menu li a {
  font-weight: bold;
  color: gray;
}
.menu li a:hover {
  font-weight: bold;
  color: black;
}
</style>
