<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
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
        $(document).ready(function () {
            $("#deleteBtn").click(function () {
                 $("#updateform").attr("action", "${root}/user/delete").submit();
            });
            $("#updateBtn").click(function () {
            	if (!$("#userPw").val()) {
                	alert("비밀번호입력");
                    return;
                } else if (!$("#userEmail").val()) {
                	alert("이메일입력");
                    return;
                } else if (!$("#userPhone").val()) {
                	alert("전화번호입력");
                    return;
                }else if (!$("#userName").val()) {
                	alert("이름입력");
                    return;
                }else {
                    $("#updateform").attr("action", "${root}/user/update").submit();
                }
            });
        });
   </script>
</head>
<body>
	<div class="container text-center mt-3">
		<div class="col-lg-8 mx-auto">
			<h2 class="p-3 mb-3 shadow bg-light">
				<mark class="sky">회원 정보</mark>
			</h2>
			<form id="updateform" class="text-left mb-3" method="post" action="">
				<input type="hidden" name="action" value="update">

				<div class="form-group">
					<label for="id">아이디:</label> <input type="text"
						class="form-control" id="userId" name="userId" placeholder="아이디" value="${user.userId}" readonly>
				</div>
				<div class="form-group">
                    <label for="pass">비밀번호:</label>
                    <input type="text" class="form-control" id="userPw" name="userPw" placeholder="비밀번호" value="${user.userPw}">
                </div>
                <div class="form-group">
                    <label for="email">이메일:</label>
                    <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="이메일" value="${user.userEmail}">
                </div>
                <div class="form-group">
                    <label for="phone">전화번호:</label>
                    <input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="전화번호" value="${user.userPhone}">
                </div>
                <div class="form-group">
                    <label for="name">이름:</label>
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="이름" value="${user.userName}">
                </div>
				<div class="text-center">
					<button type="button" id="updateBtn"
						class="btn btn-outline-primary">회원수정</button>
					<button type="button" id="deleteBtn" class="btn btn-outline-danger">회원삭제</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>