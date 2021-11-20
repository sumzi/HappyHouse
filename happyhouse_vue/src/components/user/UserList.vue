<template>
  <div class="d-flex justify-center">
    <div class="col-10">
      <v-dialog v-model="dialog" width="500">
        <template v-slot:activator="{ on, attrs }">
          <div class="d-flex justify-end">
            <v-btn outlined rounded color="success" v-bind="attrs" v-on="on">
              회원추가
            </v-btn>
          </div>
        </template>

        <v-card class="pa-5">
          <v-card-title>회원 추가</v-card-title>
          <v-card-text>
            <v-text-field
              label="아이디"
              color="success"
              v-model="user.userId"
            ></v-text-field>
            <v-text-field
              label="비밀번호"
              color="success"
              v-model="user.userPw"
            ></v-text-field>
            <v-text-field
              label="이름"
              color="success"
              v-model="user.userName"
            ></v-text-field>
            <v-text-field
              label="이메일"
              color="success"
              v-model="user.userEmail"
            ></v-text-field>
            <v-text-field
              label="전화번호"
              color="success"
              v-model="user.userPhone"
            ></v-text-field>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="success" text @click="insertUser"> 등록 </v-btn>
            <v-btn color="primary" text @click="dialog = false"> 취소 </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th style="width: 15%">아이디</th>
              <th style="width: 15%">이름</th>
              <th style="width: 15%">이메일</th>
              <th style="width: 15%">전화번호</th>
              <th style="width: 15%">등록일</th>
              <th style="width: 15%">권한</th>
              <th style="width: 5%">수정</th>
              <th style="width: 5%">삭제</th>
            </tr>
          </thead>
          <tbody>
            <user-list-row
              v-for="(user, index) in userlist"
              :key="index"
              v-bind="user"
            >
            </user-list-row>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </div>
</template>

<script>
import UserListRow from "./UserListRow.vue";
import http from "@/util/http-common.js";
export default {
  name: "UserList",
  data() {
    return {
      userlist: [],
      dialog: false,
      user: {
        userId: "",
        userPw: "",
        userName: "",
        userEmail: "",
        userPhone: "",
      },
    };
  },
  components: {
    UserListRow,
  },
  created() {
    http.post("/user/list").then((response) => {
      if (response.data.message === "success") {
        this.userlist = response.data.userList;
      }
    });
  },
  methods: {
    insertUser() {
      http
        .post("http://localhost:9200/user", this.user)
        .then((res) => {
          console.log(res);
          if (res.data.message === "success") {
            alert("회원등록 성공");
          } else {
            alert("회원등록 실패");
          }
          this.dialog = false;
          location.reload();
        })
        .catch((error) => {
          alert(error);
          this.dialog = false;
        });
    },
  },
};
</script>

<style></style>
