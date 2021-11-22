<template>
  <div style="position: relative; width: auto; height: 100%">
    <div class="map_wrap" style="position: relative; width: auto; height: 100%">
      <div
        id="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      >
        <span style="position: absolute; top: 0; left: 0; z-index: 1000"
          >안녕하세요</span
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
const dealStore = "dealStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markers: [],
      infowindow: null,
      customOverlay: null,
    };
  },
  computed: {
    ...mapState(dealStore, ["houses"]),
  },
  watch: {
    houses() {
      this.displayMarkers(this.houses);
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      // kakao.maps.load(this.initMap);
      console.log("새로고침?");
      this.initMap();
    } else {
      this.addKakaoMapScript();
    }
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      console.log("새로고침? kakao after");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
      console.log(script);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      this.map = new kakao.maps.Map(container, options);
      // this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.displayMarkers(this.houses);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarkers(places) {
      console.log(this.map + "MAP");
      // var listEl = document.getElementById("placesList"),
      //   menuEl = document.getElementById("menu_wrap"),
      //   fragment = new DocumentFragment(),
      var bounds = new kakao.maps.LatLngBounds();

      //검색 결과 목록에 추가된 항목들을 제거합니다
      // this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      for (var i = 0; i < places.length; i++) {
        var placePosition = new kakao.maps.LatLng(places[i].lat, places[i].lng);
        var marker = this.addMarker(placePosition, i);
        // var itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);
        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, title, code, place, temp) {
          kakao.maps.event.addListener(marker, "click", function () {
            temp.displayInfowindow(marker, title, place);
            console.log(title + " " + code);
          });
          console.log("여기까진 잘 됨" + temp.map);
          kakao.maps.event.addListener(temp.map, "click", function () {
            console.log(temp.customOverlay);
            temp.customOverlay.setMap(null);
          });

          // itemEl.onmouseover = function () {
          //   temp.displayInfowindow(marker, title, place);
          // };

          // itemEl.onmouseout = function () {
          //   temp.customOverlay.setMap(null);
          // };
        })(marker, places[i].aptName, places[i].aptCode, places[i], this);

        // fragment.appendChild(itemEl);
      }
      // listEl.appendChild(fragment);
      // menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.map.setBounds(bounds);
    },
    addMarker(position) {
      var marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
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
      console.log(title);
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

<style></style>
