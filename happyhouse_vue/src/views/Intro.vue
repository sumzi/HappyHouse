<template>
  <div>
    <header-nav />
    <div class="d-flex justify-center">
      <div class="col-6">
        <v-img src="@/assets/intro.jpg"> </v-img>
        <p>
          <strong>Happy House</strong>는 국토교통부 공공기관이 생성 또는
          취득하여 관리하고 있는 전국 아파트 실거래 매매 데이터를 제공하는
          웹사이트입니다. 해피 하우스에서는 쉽고 편리하게 사이트를 이용할 수
          있도록 지도, 아파트정보, 오픈 API, 시각화 등 다양한 방식으로 제공하고
          있으며, 누구라도 쉽고 편리한 검색을 통해 원하는 아파트 실거래 매매
          정보를 빠르고 정확하게 찾을 수 있습니다.
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderNav from "../components/layout/HeaderNav.vue";
export default {
  name: "QnA",
  components: {
    HeaderNav,
  },
};
</script>

<style></style>
