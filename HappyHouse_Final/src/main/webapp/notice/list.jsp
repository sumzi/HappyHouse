<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
mark.sky {
	background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}


</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#registBtn").click(function () {
            location.href = "${root}/notice/regist.jsp";
        });
	});
</script>
</head>

<body>

	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="sky">공지사항</mark>
			</h2>
			<c:if test="${!empty notices}">
				<table class="table table-active text-left">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="notice" items="${notices}">
							<tr class="table-info">
								<td>${notice.no}</td>
								<td><a href="${root}/notice/search?no=${notice.no}">${notice.title}</a></td>
								<td>${notice.ndate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${empty notices}">
				<table class="table table-active text-center">
					<tr class="table-info">
						<td>작성한 글이 없습니다.</td>
					</tr>
				</table>
			</c:if>
			<div class="text-center">
                    <button type="button" id="registBtn" class="btn btn-outline-primary">공지 등록</button>
             </div>
		</div>
	</div>
</body>
</html>