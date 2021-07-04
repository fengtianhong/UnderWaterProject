<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setAttribute("userID", 3);	// 先寫死
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- <link rel="stylesheet" href="./css/friendchat.css" type="text/css" /> -->
<style type="text/css">

.bot-container{
	display:none;
    position: fixed;
    bottom: 50px;
    right: 80px;
	height: 500px;
	width: 300px;
	border-radius: 20px;
	overflow: hidden;
}
.-on{
	display: block;
}
.statusOutput {
	background: steelblue;
	color: white;
	margin: 0;
	width: 100%;
	height: 8%;
	padding-top: 10px;
    padding-left: 22px;
}
.message-area {
    font-size: 14px;
	padding: 5px;
	height: 78%;
	width: 100%;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
	background-color: #efefef;;
}
.panel {
	float: right;
}
.input-area {
	height: 15%;
	background: white;
	width: 100%;
}
/* 輸入訊息的input */
.input-area input {
	margin: 1.2em 0em 0.5em 1.1em;
}
.text-field {
	padding: 0.2em;
	width: 70%;
	height: 21px;
}
#message {
	min-width: 50%;
	max-width: 70%;
}
.fish {
   font-size: 28px;
    border: none;
    background-color: transparent;
    position: relative;
/*     left: 71px; */
    top: 6px;
}
/* 聊天訊息 */
.message-area > ul{
  list-style: none;
  margin: 0;
  padding: 0;
}

.message-area > ul > li {
    box-shadow: rgb(0 0 0 / 10%) 0px 20px 25px -5px, 
    			rgb(0 0 0 / 4%) 0px 10px 10px -5px;
    display: inline-block;
    clear: both;
    padding: 10px;
    border-radius: 31px;
    margin-bottom: 5px;
    font-family: Helvetica, Arial, sans-serif;
}


.friend{
	background: white;
	float: left;
}
.me{
	float: right;
	background: slategray;
	color: #fff;
}

.friend + .me{
  border-bottom-right-radius: 5px;
}

.me + .me{
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.me:last-of-type {
  border-bottom-right-radius: 30px;
}


html, body {
	background: gray;
}
</style>
<title>USER 聊天室 (待改一對Manager)</title>
</head>
<body>
	<button target="_blank" class="btn btn-primary btn-sm text-uppercase customer-service" onclick="connect();"><i class="fas fa-headset"></i></button>

	<div class="bot-container" onload="connect();" onunload="disconnect();">
		
		<h3 id="statusOutput" class="statusOutput">客服1號</h3>
		<div id="messagesArea" class="panel message-area" ></div>
		<div class="panel input-area">
			<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
			<button type="submit" id="sendMessage" class="button fish" value="Send" onclick="sendMessage();"><i class="fas fa-fish"></i></button>
		</div>
	</div>
</body>
<script src="https://kit.fontawesome.com/d3e24e4d81.js" crossorigin="anonymous"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script>
	var MyPoint = "/CustomerServiceWS/${userID}";	// java EL，可以改成 roomID 跟 session 等，變成發送給特定對象(一對一的聊天室)
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

// 	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userID}';
	var webSocket;

	function connect() {
		// create a websocket
		console.log(endPointURL);
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
				getHistoryMsg();
// 				getHistoryMsg(jsonObj);
			} else if ("history" === jsonObj.type) {
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
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				getHistoryMsg();
// 				getHistoryMsg(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
// 		var friend = statusOutput.textContent;	// "客服1號" > "receiver" : "Manager"
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
// 		} else if (friend === "") {
// 			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : "Manager",		////////////NOT SURE
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
// 有好友上線或離線就更新列表
// 	function refreshFriendList(jsonObj) {
// 		var friends = jsonObj.users;
// 		var row = document.getElementById("row");
// 		row.innerHTML = '';
// 		for (var i = 0; i < friends.length; i++) {
// 			if (friends[i] === self) { continue; }
// 			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
// 		}
// 		getHistoryMsg();
// 	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息 > 
// 	function addListener() {
// 		var container = document.getElementById("row");
// 		container.addEventListener("click", function(e) {
// 			var friend = e.srcElement.textContent;	// 當前事件源(物件)
// 			updateFriendName(friend);
	function getHistoryMsg() {		// 改成init()抓取歷史訊息
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : "Manager",		////////////NOT SURE
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
	
	$(".customer-service").on("click", function() {
		$(".bot-container").toggleClass("-on");
	})
	
	
	
// 	function updateFriendName(name) {
// 		statusOutput.innerHTML = name;
// 	}
</script>
</html>