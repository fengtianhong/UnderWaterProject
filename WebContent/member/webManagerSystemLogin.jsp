<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Management System Login</title>
<link rel="stylesheet" href="css/bootstrap.css">
</head>

<body>
	<form action="WebManagerSystemLogin.do" method="post" onsubmit="return checkform(this)">
		<div class="container">
			<div class="loginBox col-md-offset-4 col-md-3">
				<div style="text-align: center;">
					<h3>後台管理系统</h3>
				</div>
				<div>
					<input type="text" class="form-control" name="account"
						placeholder="輸入帳號" onblur="account(this.value)">
				</div>
				<div style="margin-top: 20px;">
					<input type="password" class="form-control" name="pwd"
						placeholder="輸入密碼"  onblur="pwd(this.value)">
				</div>
				<div style="margin-top: 5px;">
					<p style="height: 10px;" >${msg}</p>
				</div>
				<div style="margin-top: 10px; float: right;">
					<button class="btn btn-primary" id="loginBtn">登入</button>
				</div>
			</div>
		</div>
	</form>

</body>
<style>
html, body {
	height: 100%;
	width: 100%;
}

.loginBox {
	border: solid 3px;
	margin-top: 20%;
}

h3 {
	color: black;
}

.form-group {
	float: left;
}
</style>
<script>
	
	function checkform(form){
		if(form.account.value == ""){
			alert("帳號不得空白");
			return false;
		}else if(form.pwd.value == ""){
			alert("密碼不得空白");
			return false;
		}else{
			return true;
		}
	}
</script>
</html>