<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%-- <c:if test="${empty userInfo}">
	<script type="text/javascript">
		alert("로그인 후 이용 가능한 페이지입니다.");
		location.href = "${root}/index.jsp";
	</script>
</c:if>
<c:if test="${!empty userInfo}"> --%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

	
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/apt.css">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        mark.sky {
            background: linear-gradient(to top, #54fff9 20%, transparent 30%);
        }
    </style>

    <script src="./js/deal.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
    
<style>
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}
</style>
</head>
<body>
<div class="container">
<%--     <c:if test="${!empty list}"> --%>
    <section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
					<div class="text-left mb-2">
						시도 : 
						<select id="sido">
							<option value="0">선택</option>
						</select>
						구군 : 
						<select id="gugun">
							<option value="0">선택</option>
						</select>
						읍면동 : 
						<select id="dong">
							<option value="0">선택</option>
						</select>
						<button type="button" id="aptSearchBtn" class="btn btn-primary mr-auto">검색</button>
					</div>
					<div class="text-left mb-2">
	                	<label for="inputDong" class="control-label">건물명으로 검색 : </label>
	                       <input
							type="text" name="houseName" id="houseName" class="bd-control"
							placeholder="아파트 이름" autofocus />
						<button id="nameSearchBtn" type="button" class="btn btn-primary mr-auto">찾기</button>
					</div>
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="150">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>	
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th id="yearBtn">건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
			
				<div class="map_wrap" style="position:relative;width:100%;height:500px;">
					<div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
					<div id="menu_wrap" class="bg_white">
						<div id="menu_top" style="font-size:15px">평균가격:
							<span id="avg_price" style="font-size:15px"></span>
						</div>
						<hr>
				        <ul id="placesList"></ul>
				    </div>
				</div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7867997446d568836572c7e6bd73135d&libraries=services"></script>
				<script type="text/javascript" src="deal/js/map.js"></script>
				<script type="text/javascript">
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){					
					$.get(root + "/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
				$(document).on("change", "#sido", function() {
					$.get(root + "/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get(root + "/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				
				function aptTable(color, vo){
					return `
					<tr class="\${color}">
					<td>\${vo.aptCode}</td>
					<td>\${vo.aptName}</td>
					<td>\${vo.sidoName} \${vo.gugunName} \${vo.dongName} \${vo.jibun}</td>
					<td>\${vo.buildYear}</td>
					<td>\${vo.recentPrice}</td>
					</tr>
					`;
				}
				
				function setResult(data, status){
					$("tbody").empty();
					console.log(data);
					$("#avg_price").text(data.avgPrice.toLocaleString()+"만원");
					$.each(data.houseList, function(index, vo) {
						console.log(index);
						let str = aptTable(colorArr[index%3], vo);
						$("tbody").append(str);
					});
					displayMarkers(data.houseList);
				}
				
				/* $(document).on("change", "#dong", function() { */
				$(document).on("click", "#aptSearchBtn", function() {
					$("#yearBtn").text("건축연도");
					if($("#sido").val() != "0" && $("#gugun").val() != "0" && $("#dong").val() == "0"){
						$.get(root + "/map/apt/gugun"
								,{gugun: $("#gugun").val()}
								, setResult
								, "json"
						);
					}else{
						$.get(root + "/map/apt/dong"
								,{dong: $("#dong").val()}
								,setResult
								, "json"
						);
					}
				});
				
				/* 신축순으로 정렬하기 */
				$(document).on("click", "#yearBtn", function() {
					$("#yearBtn").text("신축순");
					if($("#sido").val() != "0" && $("#gugun").val() != "0" && $("#dong").val() == "0"){
						$.get(root + "/map/apt/gugun/year"
								,{gugun: $("#gugun").val()}
								,function(data, status){
									$("tbody").empty();
									console.log(data);
									$.each(data, function(index, vo) {
										let str = aptTable(colorArr[index%3], vo);
										$("tbody").append(str);
									});
									console.log(data);
									displayMarkers(data);
								}
								, "json"
						);
					}else{
						$.get(root + "/map/apt/dong/year"
								,{dong: $("#dong").val()}
								,function(data, status){
									$("tbody").empty();
									console.log(data);
									$.each(data, function(index, vo) {
										console.log(index);
										let str = aptTable(colorArr[index%3], vo);
										$("tbody").append(str);
									});
									console.log(data);
									displayMarkers(data);
								}
								, "json"
						);
					}
				});
				/* 조금 더 저 수준 함수 사용해보기  */
				$(document).on("click", "#nameSearchBtn", function() {
					$("#yearBtn").text("건축연도");
					var houseName = $("#houseName").val();
			    	if(houseName == ""){
			    		return;
			    	}
			    	$("#tbody").empty();
			    	$.get(root + "/map/apt/name"
							,{aptName: houseName}
							,setResult
							, "json"
					);
			    	/* $.ajax({
						url: root +"/map/apt/name",
						data: {aptName: houseName},
						dataType:'json',
						success: function(data, status){
							$.each(data, function (index, vo) {
								let str = `
									<tr class="\${colorArr[index%3]}">
									<td>\${vo.aptCode}</td>
									<td>\${vo.aptName}</td>
									<td>\${vo.sidoName} \${vo.gugunName} \${vo.dongName} \${vo.jibun}</td>
									<td>\${vo.buildYear}</td>
									<td>\${vo.recentPrice}</td>
									</tr>
								`;
								$("tbody").append(str);
					        });
							console.log(data);
							displayMarker(data);
						}
					});    	 */
			    	/* if (marker) marker.setMap(null);	//마커제거 */
			    });
				/* 아파트 신축순  */
				$(document).on("click", "#yearBtn", function() {
					$("#yearBtn").text("신축순");
					var houseName = $("#houseName").val();
			    	if(houseName == ""){
			    		return;
			    	}
			    	$("#tbody").empty();
			    	$.get(root + "/map/apt/name/year"
							,{aptName: houseName}
							,function(data, status){
								$("tbody").empty();
								console.log(data);
								$.each(data, function(index, vo) {
									console.log(index);
									let str = aptTable(colorArr[index%3], vo);
									$("tbody").append(str);
								});
								console.log(data);
								displayMarkers(data);
							}
							, "json"
					);
			    });
				/* 
				$(document).on("click", "#aptSearchBtn", function() {
					var param = {
							serviceKey:'K7Wn8ITiQ1up51wuD3nvRKU24dRAJTj6ERIB+jk2XoEkMdgrQXLHY7D+g2/JDKtT+WwXLzaJEYvKTC72QbL1PA==',
							pageNo:encodeURIComponent('1'),
							numOfRows:encodeURIComponent('10'),
							LAWD_CD:encodeURIComponent($("#gugun").val()),
							DEAL_YMD:encodeURIComponent('202110')
					};
					$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
							,param
							,function(data, status){
								var items = $(data).find('item');
								var jsonArray = new Array();
								items.each(function() {
									var jsonObj	= new Object();
									jsonObj.aptCode = $(this).find('일련번호').text();
									jsonObj.aptName = $(this).find('아파트').text();
									jsonObj.dongCode = $(this).find('법정동읍면동코드').text();
									//jsonObj.dongName = $(this).find('').text();
									//jsonObj.sidoName = $(this).find('').text();
									//jsonObj.gugunName = $(this).find('').text();
									jsonObj.buildYear = $(this).find('건축년도').text();
									jsonObj.jibun = $(this).find('지번').text();
									jsonObj.recentPirce = $(this).find('거래금액').text();
										
									jsonObj = JSON.stringify(jsonObj);
									//String 형태로 파싱한 객체를 다시 json으로 변환
									jsonArray.push(JSON.parse(jsonObj));
								});
								console.log(jsonArray);
								displayMarkers(jsonArray);
							}
							, "xml"
					); */
					/* var xhr = new XMLHttpRequest();
					var url = 'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
					var queryParams = '?' + encodeURIComponent('serviceKey') + '='+encodeURIComponent(' API KEY ');
					queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); 
					queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); 
					queryParams += '&' + encodeURIComponent('LAWD_CD') + '=' + encodeURIComponent($("#gugun").val()); 
					queryParams += '&' + encodeURIComponent('DEAL_YMD') + '=' + encodeURIComponent('202110'); 
					xhr.open('GET', url + queryParams);
					xhr.onreadystatechange = function () {
					    if (this.readyState == 4) {
					    	console.log(this.responseXML);
					        alert('Status: '+this.status+'nHeaders: '+JSON.stringify(this.getAllResponseHeaders())+'nBody: '+this.responseText);
					    }
					};

					xhr.send(''); 
				});
				*/
				</script>
				</div>
			</div>
		</section>
<%-- 	</c:if> --%>
    <footer>

    </footer>
	</div>
</body>

</html>
<%-- </c:if> --%>