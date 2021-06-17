<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
</head>
<body>
		<form action="login.do" method="get">
			<table>
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
		<a href = "register.jsp">註冊</a>

</body>
</html>