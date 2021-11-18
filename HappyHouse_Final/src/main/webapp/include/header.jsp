<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<style>
#title {
	text-align: center;
}

#loginInfo {
	text-align: right;
}

#center {
	text-align: center;
}

#menu>li {
	display: inline-block;
	width: 150px;
}

.error {
	color: red;
}

table {
	width: 100%;
	border-collapse: collapse;
}

table td, table th {
	border: 1px solid black;
}
</style>

<h1 id="title">
	<a href="${root }/index.jsp">HappyHouse</a>
</h1>
<div id="loginInfo">
	<c:if test="${empty userInfo }">
		<div id="center">
			<div>
				<form method="post" action="${root }/user/login">
					<input type="text" name="userId" placeholder="아이디"> <input
						type="password" name="userPw" placeholder="비밀번호"> <input
						type="submit" value="login">
				</form>
			</div>
			<div class="text-danger mb-2">${msg}</div>
			<div>
				<ul id="menu">
					<li><a href="${root }/notice/list">공지사항</a></li>
					<li><a href="${root }/user/regist.jsp">회원 가입</a></li>
					<li><a href="${root }/user/find.jsp">비밀번호 찾기</a></li>
				</ul>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty userInfo }">
		<div id="center">
			<div>
				<strong>${userInfo.userName}</strong>님 환영합니다. <a
					href="${root}/user/logout">로그아웃</a>
			</div>
			<div>
				<ul id="menu">
					<li><a href="${root }/notice/list">공지사항</a></li>
					<li><a href="${root }/map">거래 목록보기</a></li>
					<li><a href="${root }/user/list">회원 정보</a></li>
					<li><a href="${root }/mvqna">QnA</a></li>
					
				</ul>
			</div>
		</div>

	</c:if>
</div>
<hr>
<script>
	// request.setAttribute("msg", "id 또는 pass를 확인하세요") 형태로 attribute를 넘겨주면 alert을 확인할 수 있다.
	let msg = "${msg}";
	if (msg) {
		alert(msg);
	}
</script>