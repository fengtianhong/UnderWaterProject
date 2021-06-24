<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setAttribute("userID", 1);	// 先寫死
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
html, body {
	font: 20px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
	width: 90%;
	height: 90%;
	background: #eeeeda;
}

</style>
<title>Customer Service Chat</title>
</head>
<body>
		<h3>客服前台 & 後台按鈕</h3>
		<hr>

		<!-- for manager 後台管理訊息-->
		<FORM method="post" action="<%=request.getContextPath()%>/customerreply/customerservice.do">
			<!-- 後台管理者身分驗證(Filter? Param = userID????) -->
			<input type="hidden" name="userID" value="${userID}"> 
			<input type="hidden" name="action" value="CSmanager">
			<input type="submit" value="訊息回復">
		</FORM>
		
		<hr>
		
		<!-- for member user when "轉真人客服" or 點擊聊天室-->
		<FORM id="CSform" method="post" action="<%=request.getContextPath()%>/customerreply/customerservice.do">
			<!-- 若未登入則呼叫登入頁面(Filter?) -->
			<input id="userID" type="hidden" name="userID" value="${userID}"> 
			<input type="hidden" name="action" value="CSchat">
			<input type="submit" value="轉真人客服" onclick="checkLogin">
		
		</FORM>
</body>
<script>

	// for user login check
	var inputUserID = document.getElementById("userID");
	function checkLogin() {
		var userID = inputUserID.value.trim();
		if (userID === "") {
			alert("Plz login");
			// 跳轉login?
			return;
		} else {
			document.getElementById("CSform").submit();
		}
	}
</script>

</html>