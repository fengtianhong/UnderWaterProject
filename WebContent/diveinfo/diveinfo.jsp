<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.diveinfo.model.*"%>

<%
	DiveInfoService diveinfoSvc = new DiveInfoService();
	List<DiveInfoVO> list = diveinfoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>潛點資訊</title>
</head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<style>
.arrow-up {
	position: fixed;
	right: 60px;
	bottom: 20px;
}

/* customer service  */
.customer-service {
	position: fixed;
	right: 60px;
    bottom: 58px;
    font-size: 17px !important;
}
.chat{
	position: fixed;
	right: 60px;
	bottom: 100px;
    font-size: 17px !important;
}

.bot-container{
	transition-duration: 0.4s;
    position: fixed;
    bottom: 50px;
    right: 80px;
    height: 0;
    width: 0;
    border-radius: 20px;
    overflow: hidden;
}
.-on{
	display: block;
    transform: scale(1, 1);
    height: 500px;
    width: 300px;
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


</style>
<style type="text/css">
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map_canvas {
	height: 700px;
	width: 700px;
}

#map-canvas {
	height: 100%;
}
.dropdown-menu{
background-color:transparent;
}
.show_pic {
	height: 250px;
}

img {
	height: 100%;
}

nav.navbar.fixed-top.navbar-expand-md.navbar-light a {
	color: white;
}
</style>



<body>
	<c:if test="${not empty errorMsgs}">
		<script>alert("請修正以下錯誤:");
			<c:forEach var="message" items="${errorMsgs}">
				alert("${message}");
			</c:forEach>
		</script>
	</c:if>
	<nav class="navbar fixed-top navbar-expand-md navbar-light">
		<div class="container">
			<a href="<%=request.getContextPath()%>" class="navbar-brand">UnderWater</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="true"
				aria-label="Toggle naviation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/diveinfo/diveinfo.jsp">潛點地圖</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">潛水團</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/grouptour/frontendListAll.jsp">套裝行程</a>
							<a class="dropdown-item"
								href="<%=request.getContextPath()%>/party/party.do?action=party">揪團Go!</a>
						</div></li>
					<li class="nav-item"><a class="nav-link">商城</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/forumArticle/forumArticle.jsp">論壇</a></li>
 <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/personinfo.jsp">會員中心</a></li>
                        <li class="nav-item"><a class="nav-link"  target="_blank"  href="<%=request.getContextPath()%>/chat/index.jsp">聊天</a></li>
					<c:if test="${userID==null}">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/member/login.jsp">登入</a></li>
					</c:if>
					<c:if test="${userID!=null}">
						<form id="logout"
							action="<%=request.getContextPath()%>/member/LogoutServlet.do"
							method="post">
							<li class="nav-item"><a class="nav-link" href="#"
								onclick='document.getElementById("logout").submit();'>登出</a></li>
						</form>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<div id="map-canvas"></div>
	
<!-- 	FOOTER開始 -->
	<a class="btn btn-primary btn-sm text-uppercase chat" href="<%=request.getContextPath()%>/chat/index.jsp"  target="_blank"><i class="far fa-comment-dots"></i></a>
	
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
<!-- 	FOOTER結束 -->
	
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9aDE1YUHyODX3w3Wcv7Kttdtf9eyxhBw&v=3.exp"></script>
	<script>
		var a = -1;
		var total = 0;
		var markers = [];
		var position=[];
		<c:forEach var="diveinfoVO" items="${list}">
		position.push({
			status: "${diveinfoVO.status}",
			lat: ${diveinfoVO.latitude},
			lng: ${diveinfoVO.longitude},
			content:'<h2>${diveinfoVO.pointName}</h2>'+
		    '<span>${diveinfoVO.view}</span><hr>'+
		    '<div class="show_pic"><img src="<%=request.getContextPath()%>/diveinfo/ShowPic?pointSN=${diveinfoVO.pointSN}"><br></div>'+
			((${diveinfoVO.pic==null})?'<i>找不到圖片</i>':'<i></i><hr>')+
			'<span>適合季節 : ${diveinfoVO.season}</sapn><hr>'+
			'<span>潛點推薦分數 : '+(isNaN(${diveinfoVO.ratePoint/diveinfoVO.ratePeople})?0:${diveinfoVO.ratePoint/diveinfoVO.ratePeople})+'顆星</span>'
			+'<hr><span>${diveinfoVO.introduction}</span>'
	
		});
		</c:forEach>
		var map;

		function initialize() {

			map = new google.maps.Map(document.getElementById('map-canvas'), {
				center : {
					lat : 23.470747286685828,
					lng : 120.9578841031984
				},
				zoom : 7,
				mapTypeControl : false,
				fullscreenControl : false,
				rotateControl : false,
				scaleControl : true,
				streetViewControl : false,
				zoomControl : true,
				mapTypeId : "hybrid"
			});
			for (var i = 0; i < position.length; i++) {
				if (position[i].status=="上架"){addMarker(i);}
				
			}
			;

		}
		function addMarker(e) {
			markers[e] = new google.maps.Marker({
				position : {
					lat : position[e].lat,
					lng : position[e].lng
				},
				map : map,
				icon : {
					url : 'images/flag.png',
					// url:"https://img.lovepik.com/element/45004/4317.png_860.png",
					scaledSize : new google.maps.Size(30, 50)
				},
				Animation : google.maps.Animation.BOUNCE
			});
			var show = new google.maps.InfoWindow({
				content : position[e].content
			});
			markers[e].addListener('click', function() {
				a = a * -1;
				if (a > 0) {
					show.open(map, markers[e]);
				} else {
					show.close();
				}
				if (markers[e].getAnimation() == null) {
					markers[e].setAnimation(google.maps.Animation.BOUNCE);
				} else {
					markers[e].setAnimation(null);
				}
			});
			google.maps.event.addListener(show, 'closeclick', function() {
				a = a * -1;
				if (markers[e].getAnimation() == null) {
					markers[e].setAnimation(google.maps.Animation.BOUNCE);
				} else {
					markers[e].setAnimation(null);
				}
			});
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
<script src="../vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="../vendors/popper/popper.min.js"></script>
<script src="../vendors/bootstrap/js/bootstrap.min.js"></script>



<!-- 我是分割線。底下聊天用 -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
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
				}
				
			};
	
			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}
	
		function sendMessage() {
			var inputMessage = document.getElementById("message");
			var message = inputMessage.value.trim();
	
			if (message === "") {
				alert("Input a message");
				inputMessage.focus();
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : "Manager",		
					"message" : message
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
	
		function getHistoryMsg() {		// 改成init()抓取歷史訊息
				var jsonObj = {
						"type" : "history",
						"sender" : self,
						"receiver" : "Manager",		
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


</body>
</html>