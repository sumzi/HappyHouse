import Vue from "vue";
import VueRouter from "vue-router";
import Index from "../views/Index.vue";
import Notice from "../views/Notice.vue";
import Deal from "../views/Deal.vue";
import User from "../views/User.vue";
import QnA from "../views/QnA.vue";
import Login from "../views/Login.vue";
import Intro from "../views/Intro.vue";

import Signup from "../views/Signup.vue";

import NoticeList from "../components/notice/NoticeList.vue";
import NoticeRegist from "../components/notice/NoticeRegist.vue";
import NoticeSearch from "../components/notice/NoticeSearch.vue";
import NoticeUpdate from "../components/notice/NoticeUpdate.vue";

import UserList from "../components/user/UserList.vue";

import QnAList from "../components/qna/QnAList.vue";
import QnARegist from "../components/qna/QnARegist.vue";
import QnASearch from "../components/qna/QnASearch.vue";
import QnAUpdate from "../components/qna/QnAUpdate.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Index",
    component: Index,
  },
  {
    path: "/intro",
    name: "Intro",
    component: Intro,
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "search/:no",
        name: "NoticeSearch",
        component: NoticeSearch,
      },
      {
        path: "regist",
        name: "NoticeRegist",
        component: NoticeRegist,
      },
      {
        path: "update/:no",
        name: "NoticeUpdate",
        component: NoticeUpdate,
      },
    ],
  },
  {
    path: "/deal",
    name: "Deal",
    component: Deal,
  },
  {
    path: "/user",
    name: "User",
    component: User,
    redirect: "/user/list",
    children: [
      {
        path: "list",
        name: "UserList",
        component: UserList,
      },
    ],
  },
  {
    path: "/qna",
    name: "QnA",
    component: QnA,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnAList",
        component: QnAList,
      },
      {
        path: "search/:no",
        name: "QnASearch",
        component: QnASearch,
      },
      {
        path: "regist",
        name: "QnARegist",
        component: QnARegist,
      },
      {
        path: "update/:no",
        name: "QnAUpdate",
        component: QnAUpdate,
      },
    ],
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
