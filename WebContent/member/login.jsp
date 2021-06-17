<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
<script>
	
</script>
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

			<tr>
				<td>驗證碼：<input type="text" name="checknum" id="checknum">
					<img
					src="http://www.goldenjade.com.tw/captchaCheck/check/showrandimg.php"
					id="rand-img"> <input type="button" value=" 換個圖片 "
					id="reload-img">
				</td>

			</tr>
		</table>
	</form>
	<div>
		<div>
			<a href="register.jsp">註冊</a>
		</div>
		<div>
			<a href="getaccount.jsp">忘記密碼</a>
		</div>
	</div>


</body>

</html>