<template>
  <div id="app">
    <ul id="menu">
      <li><router-link :to="{ name: 'Intro' }">해피하우스</router-link></li>
      <li><router-link :to="{ name: 'Deal' }">집찾기</router-link></li>
      <li><router-link :to="{ name: 'Notice' }">공지사항</router-link></li>
      <li><router-link :to="{ name: 'QnA' }">문의하기</router-link></li>
      <li><router-link :to="{ name: 'Login' }">로그인</router-link></li>
    </ul>

    <full-page :options="options" id="fullpage">
      <div class="section">
        <v-img :src="bgImage" lazy-src="@/assets/house2.jpg" height="100vh">
          <div id="index">
            <p id="title">Happy House</p>
            <p id="sub-title">당신의 행복한 집을 찾아보세요!</p>
            <div class="d-flex justify-center mt-5">
              <div class="col-4">
                <v-text-field
                  label="내 집 검색하기"
                  solo
                  rounded
                ></v-text-field>
              </div>
            </div>
          </div>
        </v-img>
      </div>
      <div class="section"></div>
      <div class="section"></div>
    </full-page>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "app",
  data() {
    return {
      bgImage: "",
      options: {
        licenseKey: "YOUR_KEY_HERE",
        afterLoad: this.afterLoad,
        scrollOverflow: true,
        scrollBar: false,
        menu: "#menu",
        navigation: true,
        anchors: ["page1", "page2", "page3"],
        sectionsColor: [
          "#fff",
          "#e9f4ed",
          "#fff",
          "#fec401",
          "#1bcee6",
          "#ee1a59",
          "#2c3e4f",
          "#ba5be9",
          "#b4b8ab",
        ],
      },
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
  },
  methods: {},
};
</script>

<style>
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
  margin: 10px 0px;
  position: relative;
}
#menu a:hover {
  color: darkgreen;
  font-weight: bold;
  text-shadow: none;
}
#menu a {
  color: #fff;
  padding: 0 1.1em 1.1em 1.1em;
}
#menu li.active a:after {
  content: "";
  margin: 0 1.1em 0 1.1em;
  height: 2px;
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
