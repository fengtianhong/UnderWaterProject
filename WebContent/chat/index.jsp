<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.member.model.*"%>
<%
	int userid = (Integer) session.getAttribute("userID");
	System.out.print(userid);
	MemberService MemberSvc = new MemberService();
	MemberVO memberVO = MemberSvc.getone(userid);
	pageContext.setAttribute("memberVO", memberVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
html, body {
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif,
		Microsoft JhengHei;
	width: 90%;
	height: 90%;
	background: linear-gradient(330deg, rgba(16, 24, 42, 1) 0%,
		rgba(51, 161, 175, 1) 100%);
}

#userName {
	position: absolute;
	top: 50%;
	left: 50%;
	height: 30px;
	width: 250px;
	margin: -50px 121px 0 -130px;
}

#outPopUp {
	position: absolute;
	width: 500px;
	height: 300px;
	z-index: 15;
	top: 50%;
	left: 50%;
	margin: -200px 250px 0 -250px;
}

.button {
	background-color: #0078ae;
	color: #ffffff;
	border-radius: 5px;
	position: absolute;
	width: 100px;
	height: 40px;
	top: 50%;
	left: 50%;
	top: 50%;
	left: 50%;
	margin: 20px 200px 0 -50px;
}
</style>
<title>聊天室</title>
</head>
<body>
	<div id="outPopUp">
		<h1 align="center" style="color: white;">水平線下聊天室</h1>
		<h2 align="center" style="color: white;">${memberVO.nickName}</h2>

		<form id="myForm" action="<%=request.getContextPath()%>/chat/chat.do"
			method="POST">
			<input id="userName" name="userName" value="${memberVO.nickName}"
				class="text-field" type="hidden" placeholder="Input user name" /> <input
				type="button" value="大家來聊天" class="button"
				onclick="location.href='<%=request.getContextPath()%>/chat/together.jsp'">
			<input type="submit" id="send" class="button"  style="margin-top: 70px" value="找好友聊天"
				onclick="sendName();" />
		</form>
	</div>
</body>
<script>
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

	function sendName() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		} else {
			document.getElementById("myForm").submit();
		}
	}
</script>

</html>