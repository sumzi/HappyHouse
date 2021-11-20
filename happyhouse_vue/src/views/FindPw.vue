<template>
  <div>
    <header-nav />
    <div class="d-flex justify-center findpw-container">
      <v-card width="350px" height="300px" elevation="5" class="pa-8">
        <v-card-title>비밀번호 찾기</v-card-title>
        <v-card-text>
          <v-text-field
            v-model="email"
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
export default {
  name: "FindPw",
  components: {
    HeaderNav,
  },
  data() {
    return {
      email: "",
    };
  },
  methods: {
    findPw() {},
  },
};
</script>

<style>
.findpw-container {
  margin: 100px 0;
}
</style>
