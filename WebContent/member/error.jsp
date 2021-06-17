<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login.do" method="get">
		<table>
			<tr >
				<td>帳號或密碼輸入錯誤 請重新輸入</td>
			</tr>
			<tr>
				<td>請輸入使用者名稱：<input type="text" name="account" size="20"></td>
			</tr>
			<tr>
				<td>請輸入密碼：<input type="password" name="pwd" size="20"></td>
			</tr>
			<tr>
				<td><input type="submit" value="登入" /> <input type="reset" /></td>
			</tr>
		</table>
	</form>
	<a href = "register.jsp">註冊</a><a href = "getaccount.jsp">忘記密碼</a>
	
</body>
</html>