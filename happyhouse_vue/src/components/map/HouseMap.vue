<template>
  <div style="position: relative; width: auto; height: 100%">
    <div class="map_wrap" style="position: relative; width: auto; height: 100%">
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      >
        <span id="map_search">
          <div id="filters" style="display: flex">
            <house-address-search></house-address-search>

            <commercial-search class="ml-5 mt-3"></commercial-search>
            <pollution-search class="ml-5"></pollution-search>
          </div>
        </span>
        <div id="menu_wrap" class="bg_white">
          <ul id="placesList"></ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import HouseAddressSearch from "@/components/map/house/HouseAddressSearch.vue";
import CommercialSearch from "@/components/map/house/CommercialSearch.vue";
import PollutionSearch from "@/components/map/house/PollutionSearch.vue";
const dealStore = "dealStore";
const commercialStore = "commercialStore";
const pollutionStore = "pollutionStore";

// Q	음식
// F	생활서비스
// D	소매
// S	의료
// R	학문/교육
// N	관광/여가/오락
// L	부동산
// O	숙박
// P	스포츠
const code1Cate = {
  F: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f9fa.png",
  D: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3ea.png",
  S: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3e5.png",
  R: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3eb.png",
  N: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3d6.png",
  L: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f5fa.png",
  O: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3e8.png",
  P: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/26bd.png",
};
// Q09	유흥주점
// Q12	커피점/카페
// Q07	패스트푸드
// Q03	일식/수산물
// Q04	분식
// Q01	한식
// Q06	양식
// Q02	중식
// Q10	별식/퓨전요리
// Q05	닭/오리요리
// Q14	기타음식업
// Q13	음식배달서비스
// Q08	제과제빵떡케익
// Q15	부페
const code2FoodCate = {
  Q09: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f37a.png",
  Q12: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/2615.png",
  Q07: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f35f.png",
  Q03: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f980.png",
  Q04: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f362.png",
  Q01: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f371.png",
  Q06: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f969.png",
  Q02: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f963.png",
  Q10: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f372.png",
  Q05: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f357.png",
  Q14: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f37d.png",
  Q13: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f6f5.png",
  Q08: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f35e.png",
  Q15: "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f37d.png",
};

export default {
  name: "HouseMap",
  components: {
    HouseAddressSearch,
    CommercialSearch,
    PollutionSearch,
  },
  data() {
    return {
      map: null,
      markers: [],
      pollutionMarkers: [],
      commercailMarkers: [],
      infowindow: null,
      customOverlay: null,
      clusterer: null,
      commercialClusterer: null,
    };
  },
  computed: {
    ...mapState(dealStore, ["houses", "house", "dong"]),
    ...mapState(commercialStore, ["commercials"]),
    ...mapState(pollutionStore, ["pollutions"]),
  },
  watch: {
    house() {
      // // console.log(this.house);
      if (this.house) {
        // console.log(this.house + "동작");
        var moveLatLon = new kakao.maps.LatLng(this.house.lat, this.house.lng);
        this.map.setLevel(2, { anchor: moveLatLon, animate: true });

        this.map.panTo(moveLatLon);
      }
    },
    houses() {
      this.removeMarker();
      if (this.houses.length != 0) this.displayMarkers(this.houses);
    },
    commercials() {
      this.commercialClusterer.clear();
      this.commercailMarkers = [];

      if (this.commercials.length != 0)
        this.displayCommercialMarkers(this.commercials);
    },
    pollutions() {
      if (this.pollutions.length == 0) {
        this.setPollutionMarkers(null);
        this.pollutionMarkers = [];
      } else {
        this.displayPollutionMarkers(this.pollutions);
      }
    },
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      // kakao.maps.load(this.initMap);
      this.initMap();
    } else {
      this.addKakaoMapScript();
    }
    if (this.houses.length != 0) {
      setTimeout(this.displayMarkers, 500, this.houses);
    }
    if (this.dong) {
      setTimeout(this.displayCommercialMarkers, 500, this.commercials);
    }
  },
  methods: {
    ...mapMutations(dealStore, ["SET_HOUSE_LIST"]),
    ...mapActions(dealStore, [
      "detailViewFlag",
      "detailHouse",
      "detailHouseClear",
    ]),
    ...mapActions(commercialStore, [
      "detailCommercial",
      "detailCommercialClear",
    ]),
    ...mapActions(pollutionStore, ["detailPollution", "detailPollutionClear"]),

    async addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=clusterer";
      document.head.appendChild(script);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      this.map = new kakao.maps.Map(container, options);
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 4, // 클러스터 할 최소 지도 레벨
        // disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      });
      this.commercialClusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 4, // 클러스터 할 최소 지도 레벨
        // disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      });
      this.pollutionClusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 4, // 클러스터 할 최소 지도 레벨
        // disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      });
      // this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarkers(places) {
      // console.log(this.map + "MAP");
      // var listEl = document.getElementById("placesList"),
      //   menuEl = document.getElementById("menu_wrap"),
      //   fragment = new DocumentFragment(),
      var bounds = new kakao.maps.LatLngBounds();

      //검색 결과 목록에 추가된 항목들을 제거합니다
      // this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다

      for (var i = 0; i < places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
        var marker = this.addMarker(
          placePosition,
          "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f3e0.png"
        );
        this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
        // var itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);
        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, place, temp) {
          kakao.maps.event.addListener(marker, "click", function () {
            temp.detailCommercialClear();
            temp.detailPollutionClear();
            temp.detailHouse(place);
            temp.detailViewFlag(true);
            // console.log("집: " + place);
          });
          // itemEl.onmouseover = function () {
          //   temp.displayInfowindow(marker, title, place);
          // };

          // itemEl.onmouseout = function () {
          //   temp.customOverlay.setMap(null);
          // };
        })(marker, places[i], this);
        // fragment.appendChild(itemEl);
      }
      let temp = this;
      kakao.maps.event.addListener(this.map, "click", function () {
        temp.detailViewFlag(false);
        temp.detailHouseClear();
      });
      // listEl.appendChild(fragment);
      // menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.clusterer.addMarkers(this.markers);
      this.map.setBounds(bounds);
    },

    displayCommercialMarkers(places) {
      // var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
        var marker = this.addMarker(
          placePosition,
          places[i].code1 === "Q"
            ? code2FoodCate[places[i].code2]
            : code1Cate[places[i].code1]
        );
        this.commercailMarkers.push(marker); // 배열에 생성된 마커를 추가합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        // bounds.extend(placePosition);
        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, place, temp) {
          kakao.maps.event.addListener(marker, "click", function () {
            temp.detailHouseClear();
            temp.detailPollutionClear();
            temp.detailCommercial(place);
            temp.detailViewFlag(true);
            // console.log("상권: " + place);
          });
        })(marker, places[i], this);
      }
      let temp = this;
      kakao.maps.event.addListener(this.map, "click", function () {
        temp.detailViewFlag(false);
        temp.detailCommercialClear();
      });

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.commercialClusterer.addMarkers(this.commercailMarkers);
      // this.map.setBounds(bounds);
    },
    displayPollutionMarkers(places) {
      for (var i = 0; i < places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
        var marker = this.addMarker(
          placePosition,
          "https://cdnjs.cloudflare.com/ajax/libs/twemoji/13.1.0/72x72/1f332.png"
        );
        this.pollutionMarkers.push(marker); // 배열에 생성된 마커를 추가합니다

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, place, temp) {
          kakao.maps.event.addListener(marker, "click", function () {
            temp.detailHouseClear();
            temp.detailCommercialClear();
            temp.detailPollution(place);
            temp.detailViewFlag(true);
          });
        })(marker, places[i], this);
      }
      let temp = this;
      kakao.maps.event.addListener(this.map, "click", function () {
        temp.detailViewFlag(false);
        temp.detailPollutionClear();
      });

      //맵에 환경 마커(클러스터러 아님) 추가
      this.setPollutionMarkers(this.map);
    },
    addMarker(position, imageSrc) {
      var imageSize = new kakao.maps.Size(30, 30), // 마커 이미지의 크기
        imgOptions = {
          // spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          // spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(15, 15), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      // marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      // this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      // for (var i = 0; i < this.markers.length; i++) {
      //   this.markers[i].setMap(null);
      // }
      this.clusterer.clear();
      this.markers = [];
    },
    setPollutionMarkers(map) {
      for (var i = 0; i < this.pollutionMarkers.length; i++) {
        this.pollutionMarkers[i].setMap(map);
      }
    },
    getListItem(index, place) {
      var el = document.createElement("li");
      var itemStr = `
        <span class="markerbg marker_${index + 1}"></span>
        <div class=""><h5>${place.aptName}</h5>
          <span>${place.sidoName} ${place.gugunName} ${place.dongName} ${
        place.jibun
      }</span>
        </div>
      `;
      el.innerHTML = itemStr;
      el.className = "item";

      return el;
    },
    displayInfowindow(marker, title, place) {
      // // console.log(title);
      var content = `
        <div class="overlaybox">
          <div class="boxtitle">${title}</div>
          <div class="first"><img src='@/assets/apt.png' style="width:247px; height:136px;" alt=""></div>
          <ul>
            <li class="up">
              <span class="title">건축년도</span>
              <span class="count">${place.buildYear}</span>
            </li>
            <li>
              <span class="title">주소</span>
              <span class="count">${place.sidoName} ${place.gugunName} ${place.dongName} ${place.jibun}</span>
            </li>
            <li>
              <span class="title">최신거래금액</span>
              <span class="count">${place.recentPrice}</span>
            </li>
            <li>
              <span class="last" id="recenthistor" data-toggle="modal" data-target="#myModal">아파트정보 update</span>
            </li>
          </ul>
        </div>
      `;
      var position = new kakao.maps.LatLng(
        marker.getPosition().getLat() + 0.00033,
        marker.getPosition().getLng() - 0.00003
      );
      this.customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        xAnchor: 0.3,
        yAnchor: 0.91,
      });
      this.customOverlay.setMap(this.map);
    },
    //검색 결과 목록의 자식 Element를 제거하는 함수
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },
  },
};
</script>

<style>
.overlaybox {
  position: relative;
  width: 360px;
  height: 500px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png")
    no-repeat;
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .boxtitle {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png")
    no-repeat right 120px center;
  margin-bottom: 8px;
}
.overlaybox .first {
  position: relative;
  width: 247px;
  height: 136px;
  margin-bottom: 8px;
}
.first .text {
  color: #fff;
  font-weight: bold;
}
.first .triangle {
  position: absolute;
  width: 48px;
  height: 48px;
  top: 0;
  left: 0;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png")
    no-repeat;
  padding: 6px;
  font-size: 18px;
}
.first .movietitle {
  position: absolute;
  width: 100%;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  padding: 7px 15px;
  font-size: 14px;
}
.overlaybox ul {
  width: 247px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: #2b2d36;
  padding: 5px 10px;
  color: #aaabaf;
  line-height: 1;
}
.overlaybox li span {
  display: inline-block;
}
.overlaybox li .number {
  font-size: 16px;
  font-weight: bold;
}
.overlaybox li .title {
  font-size: 13px !important;
}
.overlaybox li .last {
  font-size: 12px;
  margin-right: 15px;
}
.overlaybox ul .arrow {
  position: absolute;
  margin-top: 8px;
  right: 25px;
  width: 5px;
  height: 3px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png")
    no-repeat;
}
.overlaybox li .up {
  background-position: 0 -0px;
}
.overlaybox li .down {
  background-position: 0 -60px;
}
.overlaybox li .count {
  position: absolute;
  margin-top: 5px;
  right: 15px;
  font-size: 10px;
}
.overlaybox li:hover {
  color: #fff;
  background: #d24545;
}
.overlaybox li:hover .up {
  background-position: 0 0px;
}
.overlaybox li:hover .down {
  background-position: 0 -20px;
}

#map_search {
  position: absolute;
  top: 10px;
  left: 30px;
  width: 500vw;
  z-index: 1000;
}
</style>
