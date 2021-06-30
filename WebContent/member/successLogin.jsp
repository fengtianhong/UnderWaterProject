<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	歡迎回來:${account}<br>
	<input type="button" value="回上一頁" onclick="history.back()">
	<form action="LogoutServlet.do" method="post">
		<button >登出</button>
	</form>
</body>
</html>