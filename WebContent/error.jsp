<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>oops</title>


<!-- 以下兩個link必須載入用於header,footer -->
<link rel="stylesheet" href="share/index.css">
<link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css">

<style>

.show_font{
    text-align: center;
    color: white;
    font-size: 50px;
    padding-top: 100px;
}
}
.main {
	height: 200px;
}
</style>
</head>
<body>
	<jsp:include page="share/navbar_homepage.jsp" flush="true" />
	<div class="show_font">網頁維修中～～～工程師潛水去囉！！</div>
	<jsp:include page="share/footer_homepage.jsp" flush="true" />
</body>
</html>