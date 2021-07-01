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
					<li class="nav-item"><a class="nav-link">會員中心</a></li>
					<li class="nav-item"><a class="nav-link">購物車?</a></li>
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

</body>
</html>