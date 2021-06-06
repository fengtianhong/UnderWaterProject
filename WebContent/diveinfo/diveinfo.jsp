<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
</style>
<body>
	<div id="map-canvas"></div>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9aDE1YUHyODX3w3Wcv7Kttdtf9eyxhBw&v=3.exp"></script>
	<script>
	 	var a=-1;
		var markers = [];
		var position = [ {
			label : 'myhome',
			lat : 23.847150167622253,
			lng : 121.49852176037554,
			label : "我家底家啦"
		}, {
			label : 'yushan',

			lat : 23.470747286685828,
			lng : 120.9578841031984,
			label : "玉山在這裡"
		}, {
			label : 'tibame',
			lat : 25.05747075024689,
			lng : 121.54703497305502,
			label : "提拔一下"
		} ];
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
				addMarker(i);
			}
			;

		}
		function addMarker(e) {
			markers[e] = new google.maps.Marker({
				position : {
					lat : position[e].lat,
					lng : position[e].lng
				},
				map : map
				                ,icon: {
				                    url: 'images/flag.png',
				                    // url:"https://img.lovepik.com/element/45004/4317.png_860.png",
				                    scaledSize: new google.maps.Size(30, 50)
				                }
				                , Animation: google.maps.Animation.BOUNCE
			});
				            var show = new google.maps.InfoWindow({
				                content: position[e].label
				            });
				            markers[e].addListener('click', function () {
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
				            google.maps.event.addListener(show, 'closeclick', function () {
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