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
<title>UnderWater-聊天室</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css" type="text/css" />
</head>

<body onload="connect();" onunload="disconnect();">
	<h1>UnderWater-聊天室</h1>
	
	<textarea id="messagesArea" class="panel message-area" readonly></textarea>
	<div class="panel input-area">
		<input id="userName" class="text-field" type="text" placeholder="User name" value="${memberVO.nickName}" readonly/> 
		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> 
		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" /> 
		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" />
		<h3 id="statusOutput" class="statusOutput"></h3>
	</div>
</body>

<script>
	var MyPoint = "/TogetherWS/james";//與TogetherWS.java中的userName做對應
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);//產生物件的同時，就根據參數的URL去連結serverEndPoint了

		webSocket.onopen = function(event) {
			updateStatus("上線中");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var timer = new Date();
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = timer.toLocaleTimeString() +" " +jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("離線中");
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

	function sendMessage() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"userName" : userName,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
</script>
</html>