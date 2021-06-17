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
	height: 100%
}

.show_pic{
	width:400px
}
img {
	
	width: 100%;
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
			status: ${diveinfoVO.status},
			lat: ${diveinfoVO.latitude},
			lng: ${diveinfoVO.longitude},
			content:'<h2>${diveinfoVO.pointName}</h2>'+
		    '<span>${diveinfoVO.view}</span><br/>'+
		    '<div class="show_pic"><img src="<%=request.getContextPath()%>/diveinfo/ShowPic?pointSN=${diveinfoVO.pointSN}"><br></div>'+
			((${diveinfoVO.pic==null})?'<i>找不到圖片</i>':'<i>圖片取自網路</i>')+
			'<br><span>${diveinfoVO.introduction}</span><br>'+
			'<span>適合季節:${diveinfoVO.season}</sapn><br>'+
			'<span>評價平均分數:'+(isNaN(${diveinfoVO.ratePoint/diveinfoVO.ratePeople})?0:${diveinfoVO.ratePoint/diveinfoVO.ratePeople})+'</span>'
	
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
				if (position[i].status!=0){addMarker(i);}
				
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