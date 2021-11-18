<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 조회</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${root}/css/common.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#registBtn").click(function () {
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
               $("#registForm").attr("action", "${root}/notice/insert").submit();
            }
        });
    });
	</script>
</head>
<body>
    <div class="container text-center mt-3">
        <div class="col-lg-8 mx-auto">
             <h2 class="p-3 mb-3 shadow bg-light"><mark class="sky">공지사항 등록</mark></h2>
            <form id="registForm" class="text-left mb-3" method="post" action="">
                <div class="form-group">
                    <label for="isbn">공지 번호:</label>
                    <input type="text" class="form-control" id="no" name="no" placeholder="번호">
                </div>
                <div class="form-group">
                    <label for="title">제목:</label>
                    <input type="text" class="form-control" id="subject" name="title" placeholder="제목">
                </div>
                <div class="form-group">
                	<label for="author">내용:</label> 
                    <input type="text" class="form-control" id="content" name="content" placeholder="내용">
                </div>
                <input type="hidden" id="userName" name="userName" value="${userInfo.userName}">            
                <div class="text-center">
                    <button type="button" id="registBtn" class="btn btn-outline-primary">공지등록</button>
                    <button type="reset" class="btn btn-outline-danger">초기화</button>
                </div>
            </form>
		  </form>
		</div>
    </div>
</body>
</html>