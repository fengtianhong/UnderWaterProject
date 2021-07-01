<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%
	int userid =(Integer)session.getAttribute("userID");
	System.out.print(userid);
	MemberService MemberSvc = new MemberService();
	MemberVO memberVO = MemberSvc.getone(userid);
	 pageContext.setAttribute("memberVO",memberVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/friendchat.css" type="text/css" />
<style type="text/css">

.offline{
	border:solie 1px red;
	}
</style>
<title>潛水客聊天室-${memberVO.nickName}</title>
</head>
<body onload="connect();" onunload="disconnect();">
	<h3 id="statusOutput" class="statusOutput"></h3>
	<div id="row"></div>
	<div id="messagesArea" class="panel message-area"></div>
	<div class="panel input-area">
		<input id="message" class="text-field" type="text"
			placeholder="Message"
			onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
			type="submit" id="sendMessage" class="button" value="Send"
			onclick="sendMessage();" /> <input type="button" id="connect"
			class="button" value="Connect" onclick="connect();" /> <input
			type="button" id="disconnect" class="button" value="Disconnect"
			onclick="disconnect();" />
	</div>
</body>
<script>
	var MyPoint = "/Friend/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {

				// 				=========================
				// 				var repeat = false;
				// 				var row = document.getElementById("row");
				// 				var receivers = row.childNodes;
				// 				if (row.childNodes.length == 0) {
				// 					row.innerHTML += '<div onclick="HtmlClick(this)" id='
				// 							+ jsonObj.receiver
				// 							+ ' class="column" name="friendName" value='
				// 							+ jsonObj.receiver + ' ><h2>' + jsonObj.receiver
				// 							+ '</h2></div>';
				// 				}
				// 				for (var i = 0; i < row.childNodes.length; i++) {
				// 					if (receivers[i].getAttribute("id") == jsonObj.receiver) {
				// 						repeat = true;
				// 						break;
				// 					}
				// 				}
				// 				if (repeat == false) {
				// 					row.innerHTML += '<div onclick="HtmlClick(this)" id='
				// 							+ jsonObj.receiver
				// 							+ ' class="column" name="friendName" value='
				// 							+ jsonObj.receiver + ' ><h2>' + jsonObj.receiver
				// 							+ '</h2></div>';
				// 				}
				// 				===========================

				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me'
							: li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				if ((statusOutput.textContext == jsonObj.sender)
						|| jsonObj.sender == self) {
					var li = document.createElement('li');
					jsonObj.sender === self ? li.className += 'me'
							: li.className += 'friend';
					li.innerHTML = jsonObj.message;
					console.log(li);
					document.getElementById("area").appendChild(li);
					messagesArea.scrollTop = messagesArea.scrollHeight;
				}
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}

		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;  //當前在線的人
		var row = document.getElementById("row");
		var receivers = row.childNodes;  //顯示在畫面上的人
// 		var repeat = false;
				row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) {
				continue;
			}
// 			for (var j = 0; j < row.childNodes.length; j++) {
// 				if (row.childNodes[j].getAttribute("id") == friends[i]) {
// 					repeat = true;
// 				}
// 			}
// 			if (!repeat) {
				row.innerHTML += '<div id='
						+ friends[i]
						+ ' onclick="HtmlClick(this)" class="column" name="friendName" value='
						+ friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
// 			}
		}
// 		for (var i = 0; i < row.childNodes.length; i++) {
// 			if (row.childNodes[i].getAttribute("id") === self) {
// 				continue;
// 			}
// 		}
// 			var exist = true;
// 			for (var j = 0; j < friends.length; j++) {
// 				if (row.childNodes[i].getAttribute("id") == friends[j]) {
// 					exist = false;
// 				}
// 			}
// 			if (!exist) {
// 				receivers[i].classList.toggle("offline");
// 			} 
		// 		addListener();
	}

	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : friend,
				"message" : ""
			};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}

	//html觸發事件
	function HtmlClick(e) {
		console.log("Htmlclick事件" + e.id);
		var friend = e.id;
		updateFriendName(friend);
		var jsonObj = {
			"type" : "history",
			"sender" : self,
			"receiver" : friend,
			"message" : ""
		};
		webSocket.send(JSON.stringify(jsonObj));
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>