<template>
  <tr>
    <td>{{ user.userId }}</td>
    <td><v-text-field v-model="user.userName"></v-text-field></td>
    <td><v-text-field v-model="user.userEmail"></v-text-field></td>
    <td><v-text-field v-model="user.userPhone"></v-text-field></td>
    <td>{{ user.joinDate }}</td>
    <td>
      <v-select :items="roles" v-model="user.role"></v-select>
    </td>
    <td>
      <v-btn small outlined rounded color="primary" @click="updateUser"
        >수정</v-btn
      >
    </td>
    <td>
      <v-btn small outlined rounded color="error" @click="deleteUser"
        >삭제</v-btn
      >
    </td>
  </tr>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name: "UserListRow",
  props: {
    userId: String,
    userName: String,
    userEmail: String,
    userPhone: String,
    joinDate: String,
    role: String,
  },
  data() {
    return {
      roles: ["admin", "nomal"],
      user: {
        userId: this.userId,
        userName: this.userName,
        userEmail: this.userEmail,
        userPhone: this.userPhone,
        joinDate: this.joinDate,
        role: this.role,
      },
    };
  },
  methods: {
    updateUser() {
      http.put("/user", this.user).then((response) => {
        if (response.data.message === "success") {
          alert("수정 성공");
        } else {
          alert("수정 실패");
        }
        location.reload();
      });
    },
    deleteUser() {
      http
        .delete("/user/delete", { data: { userId: this.user.userId } })
        .then((response) => {
          if (response.data.message === "success") {
            alert("회원삭제성공");
          } else {
            alert("회원삭제실패");
          }
          location.reload();
        })
        .catch((error) => console.log(error));
    },
  },
};
</script>

<style></style>
