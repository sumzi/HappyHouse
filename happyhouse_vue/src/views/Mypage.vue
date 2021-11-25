<template>
  <div>
    <header-nav />
    <div class="d-flex justify-center mypage-container">
      <v-card width="400px" class="pa-8">
        <v-card-title class="pb-10">마이페이지</v-card-title>
        <v-card-text>
          <v-text-field
            label="id"
            v-model="user.userId"
            disabled
          ></v-text-field>
          <v-text-field label="name" v-model="user.userName"></v-text-field>
          <v-text-field label="email" v-model="user.userEmail"></v-text-field>
          <v-text-field label="phone" v-model="user.userPhone"></v-text-field>
          <v-text-field label="password" v-model="user.userPw"></v-text-field>
        </v-card-text>

        <v-card-actions class="d-flex justify-space-around">
          <v-btn outlined rounded color="success" @click="updateUser"
            >회원수정</v-btn
          >
          <v-btn outlined rounded color="error" @click="deleteUser"
            >회원탈퇴</v-btn
          >
        </v-card-actions>
      </v-card>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import http from "@/util/http-common.js";
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Mypage",
  components: {
    HeaderNav,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      user: {
        userId: "",
        userPw: "",
        userName: "",
        userEmail: "",
        userPhone: "",
        role: "nomal",
      },
    };
  },
  filters: {
    // phone(val){
    // }
  },
  created() {
    this.user.userId = this.userInfo.userId;
    this.user.userName = this.userInfo.userName;
    this.user.userEmail = this.userInfo.userEmail;
    this.user.userPhone = this.userInfo.userPhone;
  },
  methods: {
    ...mapMutations("userStore", ["SET_USER_INFO", "USER_LOGIN"]),
    updateUser() {
      http.put("/user", this.user).then((response) => {
        if (response.data.message === "success") {
          alert("회원수정 되었습니다.");
          this.SET_USER_INFO(this.user);
        } else {
          alert("회원수정에 실패했습니다.");
        }
      });
    },
    deleteUser() {
      http.delete("user/delete", { data: this.userInfo }).then((response) => {
        if (response.data.message === "success") {
          this.SET_USER_INFO(null);
          this.USER_LOGIN(false);
          sessionStorage.removeItem("userId");
          alert("탈퇴되었습니다.");
          if (this.$route.path != "/") this.$router.push({ name: "Index" });
        }
      });
    },
  },
};
</script>

<style>
.mypage-container {
  margin: 80px 0;
}
</style>
