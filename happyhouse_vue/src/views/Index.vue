<template>
  <div id="app">
    <ul id="menu" v-if="userInfo">
      <li><router-link :to="{ name: 'Intro' }">해피하우스</router-link></li>
      <li><router-link :to="{ name: 'Deal' }">집찾기</router-link></li>
      <li><router-link :to="{ name: 'Notice' }">공지사항</router-link></li>
      <li><router-link :to="{ name: 'QnA' }">문의하기</router-link></li>
      <li v-if="userInfo.role === 'admin'">
        <router-link :to="{ name: 'User' }">회원관리</router-link>
      </li>
      <li>
        <router-link :to="{ name: 'Mypage' }"
          ><v-icon class="mr-1" color="white"> mdi-account-circle </v-icon
          >{{ userInfo.userName }} 님</router-link
        >
      </li>
    </ul>

    <ul id="menu" v-else>
      <li><router-link :to="{ name: 'Intro' }">해피하우스</router-link></li>
      <li><router-link :to="{ name: 'Deal' }">집찾기</router-link></li>
      <li><router-link :to="{ name: 'Notice' }">공지사항</router-link></li>
      <li><router-link :to="{ name: 'QnA' }">문의하기</router-link></li>
      <li><router-link :to="{ name: 'Signup' }">회원가입</router-link></li>
      <li><router-link :to="{ name: 'Login' }">로그인</router-link></li>
    </ul>

    <full-page :options="options" id="fullpage">
      <div class="section">
        <v-img :src="bgImage" lazy-src="@/assets/house2.jpg" height="100vh">
          <div id="index">
            <p id="title">Happy House</p>
            <p id="sub-title">당신의 행복한 집을 찾아보세요!</p>
            <div class="d-flex justify-center">
              <div class="col-4">
                <v-text-field
                  height="60px"
                  color="success"
                  prepend-inner-icon="mdi-map-marker"
                  label="건물명으로 검색하기"
                  solo
                  rounded
                  v-model="aptName"
                  @keyup.enter="searchApt"
                ></v-text-field>
              </div>
            </div>
          </div>
        </v-img>
      </div>
      <div class="section">
        <div style="height: 60%; z-index: 10">
          <v-carousel
            cycle
            hide-delimiter-background
            show-arrows-on-hover
            style="height: 100%"
          >
            <v-carousel-item v-for="(slide, i) in slides" :key="i">
              <v-sheet :color="colors[i]" height="100%">
                <v-row class="fill-height" align="center" justify="center">
                  <div class="text-h2">{{ slide }} Slide</div>
                </v-row>
              </v-sheet>
            </v-carousel-item>
          </v-carousel>
        </div>
        <div
          style="z-index: 10; height: 40%"
          class="d-flex justify-space-around"
        >
          <v-card width="50%" elevation="5" class="ma-10 mt-0 pa-3">
            <v-card-title class="d-flex justify-space-between">
              <div>공지사항</div>
              <div>
                <router-link :to="{ name: 'NoticeList' }">➕</router-link>
              </div>
            </v-card-title>
            <v-card-text>
              <v-simple-table
                ><thead>
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="notice in noticeList"
                    :key="notice.no"
                    @click="selectNotice(notice.no)"
                  >
                    <td>{{ notice.no }}</td>
                    <td>{{ notice.title }}</td>
                    <td>{{ notice.userId }}</td>
                    <td>{{ notice.ndate }}</td>
                  </tr>
                </tbody>
              </v-simple-table>
            </v-card-text>
          </v-card>
          <v-card
            width="50%"
            elevation="5"
            class="ma-10 mt-0 pa-3 overflow-y-auto"
          >
            <v-card-title class="d-flex justify-space-between">
              <div>Q & A</div>
              <div><router-link :to="{ name: 'QnAList' }">➕</router-link></div>
            </v-card-title>
            <v-card-text overline>
              <v-simple-table
                ><thead>
                  <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="qna in qnaList"
                    :key="qna.no"
                    @click="selectQnA(qna.no)"
                  >
                    <td>{{ qna.no }}</td>
                    <td>{{ qna.title }}</td>
                    <td>{{ qna.userId }}</td>
                    <td>{{ qna.ndate }}</td>
                  </tr>
                </tbody>
              </v-simple-table>
            </v-card-text>
          </v-card>
        </div>
      </div>
      <div class="section"></div>
    </full-page>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import http from "@/util/http-common.js";
export default {
  name: "app",
  components: {},
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      colors: [
        "indigo",
        "warning",
        "pink darken-2",
        "red lighten-1",
        "deep-purple accent-4",
      ],
      slides: ["First", "Second", "Third"],
      bgImage: "",
      options: {
        licenseKey: "YOUR_KEY_HERE",
        afterLoad: this.afterLoad,
        scrollOverflow: true,
        scrollBar: false,
        menu: "#menu",
        navigation: true,
        anchors: ["page1", "page2", "page3"],
        sectionsColor: ["#fff", "lightgray", "#fff"],
      },
      aptName: "",
      noticeList: [],
      qnaList: [],
    };
  },
  created() {
    axios
      .get("https://api.unsplash.com/photos/random", {
        params: {
          client_id: "Ll9bKn7J8fhbQdfWONFwKpHmi0RwmTJNXwa3CYlU6S4",
          query: "house",
          count: 1,
        },
      })
      .then((res) => (this.bgImage = res.data[0].urls.full));
    http.get("/qna?spp=3").then((response) => {
      this.qnaList = response.data.qnaList;
    });
    http.get("/notice?spp=3").then((response) => {
      this.noticeList = response.data.noticeList;
    });
  },
  methods: {
    searchApt() {
      if (this.aptName.trim() !== "")
        this.$router.push({
          name: "HouseComp",
          params: { aptName: this.aptName },
        });
    },
    selectNotice(no) {
      this.$router.push({ name: "NoticeSearch", params: { no: no } });
    },
    selectQnA(no) {
      this.$router.push({ name: "QnASearch", params: { no: no } });
    },
  },
};
</script>

<style scoped>
.input-container {
  position: absolute;
  top: 45%;
  left: 30%;
  right: 30%;
}
.sub-text {
  margin: 20px;
  font-size: 18px;
  font-weight: 100;
}
#index {
  position: absolute;
  top: 25%;
  right: 0;
  left: 0;
  z-index: 70;
  text-align: center;
  color: #fff;
}
#title {
  font-size: 4.5em;
  font-weight: bold;
  text-shadow: 2px 2px 4px black;
}
#sub-title {
  text-shadow: 1px 1px 2px black;
}
#menu {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 70;
  -webkit-font-smoothing: antialiased;
  -moz-font-smoothing: antialiased;
  letter-spacing: 1px;
  font-size: 1.1em;
  font-weight: 400;
  text-shadow: 0px 0px 3px black;
}
#menu li {
  display: inline-block;
  margin: 10px 15px;
  position: relative;
}
#menu a:hover {
  font-weight: bold;
}
#menu a {
  color: #fff;
  /* padding: 0 1.1em 1.1em 1.1em; */
}
#menu li.active a:after {
  content: "";
  margin: 0 1.1em 0 1.1em;
  background: #fff;
  display: block;
  position: absolute;
  bottom: -6px;
  left: 0;
  right: 0;
  display: block;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  text-decoration: none;
}
</style>
