<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>



<div class="container-fluid mt-1 mb-5">
<!-- 중앙 컨텐츠 영역-->
<div class="col-lg-9">


	<!-- Google Map start -->

	<div id="map" style="width: 80%; height: 80%; margin: auto;" class="mb-3"></div>
	<script defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDiRWTJMPa9V7u_7xr8SDs2EnF1JIVqk9w&callback=initMap"></script>
	<script>
		var multi = { lat: 37.5012743, lng: 127.039585 };
		var map;
		var markers = [];
		var infoWindow;

		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center: multi, zoom: 15
			});

			infoWindow = new google.maps.InfoWindow;

			const myposition = "img/my_position.png";


			//멀티캠퍼스  
			var multimarker = {
				coords: multi,
				iconImage: myposition,
				content: '멀티캠퍼스(역삼)'
			};
			addMarker(multimarker);
			infoWindow.setPosition(multi);
			infoWindow.setContent('멀티캠퍼스.');
			infoWindow.open(map);


		}


		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			infoWindow.setPosition(pos);
			infoWindow.setContent(browserHasGeolocation ?
				'Error: Geolocation 서비스 실패.' :
				'Error: Geolocation을 지원하지 않는 브라우저.');
			infoWindow.open(map);
		}

		function addMarker(props) {
			const marker = new google.maps.Marker({
				position: new google.maps.LatLng(parseFloat(props.coords.lat), parseFloat(props.coords.lng)),
				map: map
			});
			map.setCenter(marker.getPosition());
			map.setZoom(15);

			if (props.iconImage) {
				marker.setIcon(props.iconImage);
			}

			if (props.content) {
				infoWindow = new google.maps.InfoWindow({
					content: props.content
				});

			}

			marker.addListener('click', function () {
				map.setZoom(17);
				map.setCenter(marker.getPosition());
				bounceMarker(marker);
			});
			markers.push(marker);
			setMapOnAll(map);
		}

		function bounceMarker(marker) {
			if (marker.getAnimation() !== null) {
				marker.setAnimation(null);
			} else {
				marker.setAnimation(google.maps.Animation.BOUNCE);
			}
		}

		function deleteMarkers() {
			clearMarkers();
			markers = [];
		}

		function clearMarkers() {
			setMapOnAll(null);
		}

		function setMapOnAll(map) {
			for (let i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}
	</script>
	<!-- Google Map end -->

</div>

</body>
</html>