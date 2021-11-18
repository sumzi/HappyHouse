<template>
  <div class="header-container">
    <div id="logo">
      <router-link :to="{ name: 'Index' }"
        ><img src="@/assets/hh3.png"
      /></router-link>
    </div>

    <!-- 비회원 -->
    <div v-if="user === ''" class="menu-container">
      <ul class="menu">
        <li>
          <router-link :to="{ name: 'Intro' }">해피하우스</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Notice' }">공지사항</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'QnA' }">QnA</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Deal' }">거래 목록보기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Login' }">로그인</router-link>
        </li>
        <li>
          <router-link :to="{}">회원가입</router-link>
        </li>
      </ul>
    </div>
    <!-- 회원 -->
    <div v-if="user" class="menu-container">
      <ul class="menu">
        <li>
          <router-link :to="{ name: 'Intro' }">해피하우스</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Notice' }">공지사항</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'QnA' }">QnA</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'Deal' }">거래 목록보기</router-link>
        </li>
        <li>
          <router-link :to="{ name: 'User' }">회원 정보</router-link>
        </li>
        <li>관리자님</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "HeaderNav",
  data() {
    return {
      user: "",
    };
  },
};
</script>

<style>
.header-container {
  border-bottom: 1px solid lightgray;
  display: flex;
  justify-content: space-between;
}
.menu-container {
  margin-right: 20px;
}
.menu > li {
  display: inline-block;
  margin: 20px 16px;
  font-size: 16px;
}
#logo {
  width: 60px;
  margin: 15px 20px;
}
#logo img {
  width: 100%;
  height: 100%;
}
.menu a {
  font-weight: bold;
  color: #2c3e50;
}
</style>
