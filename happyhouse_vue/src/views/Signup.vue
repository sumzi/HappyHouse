<template>
  <div>
    <header-nav />
    <v-container class="container">
      <h2>회원 가입</h2>
    </v-container>

    <div class="d-flex justify-center">
      <div class="">
        <v-card elevation="2" outlined class="pa-6" width="450px">
          <v-card-title>가입하기</v-card-title>
          <v-card-text>
            <v-text-field
              label="아이디"
              color="success"
              v-model="id"
              ref="id"
              :counter="10"
              :rules="idRules"
              required
            ></v-text-field>
            <v-text-field
              label="이름"
              color="success"
              v-model="name"
              ref="name"
              :counter="10"
              :rules="nameRules"
              required
            ></v-text-field>
            <v-text-field
              label="비밀번호"
              color="success"
              v-model="pw1"
              ref="pw1"
              :counter="12"
              :rules="pwRules"
              required
              :type="show1 ? 'text' : 'password'"
              :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              @click:append="show1 = !show1"
            ></v-text-field>
            <v-text-field
              label="비밀번호 확인"
              color="success"
              v-model="pw2"
              ref="pw2"
              :counter="12"
              :rules="pw2Rules"
              :type="show2 ? 'text' : 'password'"
              :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              @click:append="show2 = !show2"
              required
            ></v-text-field>
            <v-text-field
              label="이메일"
              color="success"
              v-model="email"
              ref="email"
              :rules="emailRules"
              required
            ></v-text-field>
            <v-text-field
              label="전화번호"
              color="success"
              v-model="phone"
              ref="phone"
              required
            ></v-text-field>
            <v-checkbox
              label="개인정보 수집 및 이용에 동의합니다."
              color="success"
              v-model="checkbox"
            ></v-checkbox>
          </v-card-text>
          <v-card-actions class="d-flex justify-space-around">
            <v-btn color="primary" outlined rounded text @click="registUser">
              가입
            </v-btn>
            <v-btn color="warning" outlined rounded text @click="resetUser">
              초기화
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>
    </div>
  </div>
</template>
<script>
import http from "@/util/http-common.js";
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "Signup",
  components: {
    HeaderNav,
  },
  data() {
    return {
      id: "",
      pw1: "",
      pw2: "",
      name: "",
      email: "",
      phone: "",
      show1: false,
      show2: false,
      checkbox: false,
      idRules: [
        (v) => !!v || "Id is required",
        (v) => v.length <= 10 || "Id must be less than 10 characters",
      ],
      nameRules: [
        (v) => !!v || "Name is required",
        (v) => v.length <= 10 || "Name must be less than 10 characters",
      ],
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ],
      pwRules: [
        (v) => !!v || "Password is required",
        (v) => v.length <= 10 || "Password must be less than 12 characters",
      ],
      pw2Rules: [
        (v) => !!v || "Password is required",
        () => this.pw1 === this.pw2 || "Password must match",
      ],
    };
  },
  watch: {
    phone(val) {
      if (val !== null) {
        this.phone = val.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3");
      }
    },
  },
  methods: {
    registUser() {
      let error = true;
      let msg = "";
      !this.id &&
        ((msg = "아이디를 입력해 주세요"),
        (error = false),
        this.$refs.id.focus());
      error &&
        !this.name &&
        ((msg = "이름을 입력해 주세요"),
        (error = false),
        this.$refs.name.focus());
      error &&
        !this.pw1 &&
        ((msg = "비밀번호을 입력해 주세요"),
        (error = false),
        this.$refs.pw1.focus());
      error &&
        !this.pw2 &&
        ((msg = "비밀번호을 입력해 주세요"),
        (error = false),
        this.$refs.pw2.focus());
      error &&
        this.pw1 !== this.pw2 &&
        ((msg = "비밀번호가 일치하지 않습니다"),
        (error = false),
        this.$refs.pw2.focus());
      error &&
        !this.email &&
        ((msg = "이메일을 입력해 주세요"),
        (error = false),
        this.$refs.email.focus());
      error &&
        !this.phone &&
        ((msg = "전화번호을 입력해 주세요"),
        (error = false),
        this.$refs.phone.focus());
      error &&
        !this.checkbox &&
        ((msg = "가입동의를 확인해주세요"), (error = false));

      if (!error) {
        alert(msg);
      } else {
        http
          .post("/user", {
            userId: this.id,
            userPw: this.pw1,
            userName: this.name,
            userEmail: this.email,
            userPhone: this.phone,
          })
          .then((res) => {
            console.log(res);
            this.$router.push({ name: "Login" });
            // if (res.data === "success") {
            //   alert("회원가입 성공");

            // } else {
            //   alert("회원가입 실패");
            // }
          })
          .catch((error) => {
            alert(error);
          });
      }
    },
    resetUser() {
      this.id = "";
      this.pw1 = "";
      this.pw2 = "";
      this.name = "";
      this.email = "";
      this.phone = "";
      this.check = false;
    },
  },
};
</script>

<style></style>
