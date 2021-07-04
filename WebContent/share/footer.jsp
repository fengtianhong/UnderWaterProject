<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- <aside class="aside"> -->
<!-- 	廣告欄位<br>(暫放 W150 x H300) -->
<!-- </aside> -->

<!-- </main> -->

<div id="footerGroup">
	<img id="ad" src="../img/ad.png" alt="">
    <img id="about" src="../img/about.png" alt="">
    <img id="QA" src="../img/QA.svg" alt="" style="cursor:pointer" onclick="window.location.href='<%=request.getContextPath()%>/qa/QA.jsp;'">
    <img id="footer" src="../img/footer.png" alt="">
</div>

	<a class="btn btn-primary btn-sm text-uppercase chat" href="<%=request.getContextPath()%>/chat/index.jsp"  target="_blank"><i class="far fa-comment-dots"></i></a>
<%-- 	<a target="_blank" class="btn btn-primary btn-sm text-uppercase customer-service" href="<%=request.getContextPath()%>/customerreply/CSchat.jsp"><i class="fas fa-headset"></i></a> --%>
	
		<!-- 客服 -->
	<button target="_blank" class="btn btn-primary btn-sm text-uppercase customer-service" onclick="connect();"><i class="fas fa-headset"></i></button>
	<div class="bot-container" onload="connect();" onunload="disconnect();">
		<h5 id="statusOutput" class="statusOutput">客服1號</h5>
		<div id="messagesArea" class="panel message-area" >
		<c:if test="${userID==null}"><ul id="area"><li class="friend">請先登入才能使用客服喔 : )</li></ul></c:if>
		</div>
		<div class="panel input-area">
			<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
			<button type="submit" id="sendMessage" class="button fish" value="Send" onclick="sendMessage();"><i class="fas fa-fish"></i></button>
		</div>
	</div>
	<!-- 客服 -->
	<a class="btn btn-primary btn-sm text-uppercase arrow-up" href="#"><i class="fas fa-arrow-up fa-lg"></i></a>

<!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
<script src="../vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="../vendors/popper/popper.min.js"></script>
<script src="../vendors/bootstrap/js/bootstrap.min.js"></script>
<script src="../js/index.js"></script>

<script>
	$('a.list-group-item-action').mouseover(function(){
		let that = $(this);
		that.siblings().removeClass('active');
		that.addClass('active');
	});
	var MyPoint = "/CustomerServiceWS/${userID}";	// java EL，可以改成 roomID 跟 session 等，變成發送給特定對象(一對一的聊天室)
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

//		var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userID}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				getHistoryMsg();
//					getHistoryMsg(jsonObj);
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
//					getHistoryMsg(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	function sendMessage() {
		var inputMessage = document.getElementById("message");
//			var friend = statusOutput.textContent;	// "客服1號" > "receiver" : "Manager"
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
//			} else if (friend === "") {
//				alert("Choose a friend");
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

	//有好友上線或離線就更新列表
//		function refreshFriendList(jsonObj) {
//			var friends = jsonObj.users;
//			var row = document.getElementById("row");
//			row.innerHTML = '';
//			for (var i = 0; i < friends.length; i++) {
//				if (friends[i] === self) { continue; }
//				row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
//			}
//			getHistoryMsg();
//		}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息 > 
//		function addListener() {
//			var container = document.getElementById("row");
//			container.addEventListener("click", function(e) {
//				var friend = e.srcElement.textContent;	// 當前事件源(物件)
//				updateFriendName(friend);
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
	}

	$(".customer-service").on("click", function() {
		$(".bot-container").toggleClass("-on");
	})


</script>
