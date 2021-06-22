<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");

	if ("post".equals(request.getMethod())) {
		System.out.println("2");
		Cookie accountCookie = new Cookie("account", request.getParameter("account"));
		Cookie visittimesCookie = new Cookie("visiTimes", "0");

		response.addCookie(accountCookie);
		response.addCookie(visittimesCookie);

		response.sendRedirect(request.getContextPath() + "/cookie.jsp");

		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Under Water 在水之下 註冊</title>
</head>
<body>
	<div align="center">
		<form action="login.do" method="post" name="form" onsubmit="return checkform(this)">
			<table>
				<tr>
					<td>
						<input type="hidden" value="<%=session.getAttribute("randomString")%>"  name="test" id="randomstring" onblur="VerificationCode(this.value)"/>
					</td>
				</tr>
				<tr>
					<td>帳號：<input type="text" name="account" onblur="Email(this.value)" size="40"></td>
					
					
				
				</tr>
				<tr>
					<td>密碼：<input type="password" name="pwd" size="20"></td>
				</tr>
				<tr>
					<td>驗證碼：<input type="text" name="checknum" id="checknum" onblur="Manual_VerificationCode(this.value)"/>
						<img src="<%=request.getContextPath()%>/member/IdentityServlet"
						id="identity" onload="btn.disabled=false;" /> <input
						type="button" value=" 換個圖片 " id="btn" onclick="reloadImage()">
						
					</td>
					<td>${msg}</td>
				</tr>
				<tr align="center">
					<td><input type="submit" value="登入" /> <input type="reset" /></td>
				</tr>
			</table>
		</form>
		<div>
			<div id="wrap1">
				<a href="register.jsp">註冊</a>
			</div>
			<div id="wrap2">
				<a href="getaccount.jsp">/忘記密碼</a>
			</div>
		</div>
	</div>

</body>
<style>

#wrap1,#wrap2{
	display : inline-block;
}
</style>
<script>
	
	function checkform(form){
		if(form.account.value == ""){
			alert("帳號不得空白");
			return false;
		}else if(form.pwd.value == ""){
			alert("密碼不能空白");
			return false;
		}else{
			return true;
		}
	}
	
	
	alert(document.getElementsByName("test").item(0).value); 
	
		
	function Email(strEmail){
		if(strEmail.indexOf('@')!= - 1)
			return true;
		else{
			alert("email格式不正確")
		}
	}
	
	function reloadImage() {
		document.getElementById('btn').disabled = true;
		document.getElementById('identity').src = 'IdentityServlet?ts='
				+ new Date().getTime();
	}
</script>
</html>