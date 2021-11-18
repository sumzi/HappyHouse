<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/common.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    $(document).ready(function () {
        $("#updateBtn").click(function () {
        	if (!$("#no").val()) {
                alert("공지번호(no)을 입력하세요!!!!");
                return;
            } else if (!$("#subject").val()) {
                alert("제목을 입력!!!!");
                return;
            } else if (!$("#content").val()) {
                alert("내용를 입력!!!!");
                return;
            } else{
               $("#registForm").attr("action", "${root}/notice/update").submit();
            }
        });
        $("#deleteBtn").click(function () {
        	$("#registForm").attr("action", "${root}/notice/delete").submit();
        });
    });
	</script>
</head>
<body>
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="sky">공지사항 조회</mark>
			</h2>
			<c:if test="${!empty notice}">
				<form id="registForm" class="text-left mb-3" method="post" action="">
					<table class="table table-active table-bordered">
						<tr>
							<th>번호</th>
							<td><input type="text" class="form-control" id="no" name="no" placeholder="번호" value="${notice.no}"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" class="form-control" id="userName" name="userName" placeholder="작성자" value="${notice.userName}"disabled></td>
						</tr>
						<tr>
							<th>작성날짜</th>
							<td><input type="text" class="form-control" id="ndate" name="ndate" placeholder="작성날짜" value="${notice.ndate}" disabled></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" class="form-control" id="subject" name="title" placeholder="제목" value="${notice.title}"></td>
						</tr>
						<tr>
							<th colspan='2'>내용</th>
						</tr>
						<tr>
							<td colspan='2'><input type="text" class="form-control" id="content" name="content" placeholder="내용" value="${notice.content}"></td>
						</tr>
					</table>
					<div class="col-lg-8 mx-auto">
							<button type="button" id="updateBtn"
								class="btn btn-outline-primary">공지수정</button>
							<button type="button" id="deleteBtn"
								class="btn btn-outline-danger">공지삭제</button>
						</div>
				</form>
			</c:if>
			<c:if test="${empty notice}">
				<table class="table table-active text-center">
					<tr class="table-info">
						<td>등록된 상품이 없습니다.</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>



