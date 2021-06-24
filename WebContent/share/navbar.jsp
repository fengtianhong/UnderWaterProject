<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="container">
	<!-- navbar navbar-expand-lg navbar-light bg-light -->
	<nav class="navbar fixed-top navbar-expand-md navbar-light">
		<div class="container"><a class="navbar-brand">UnderWater</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle naviation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link">潛點地圖</a></li>
					<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">潛水團</a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="#">套裝行程</a>
                              <a class="dropdown-item" href="http://localhost:8081/UnderWaterProject/party/party.do?action=party">揪團Go!</a>
                            </div>
                        </li>
                        <li class="nav-item"><a class="nav-link">商城</a></li>
                        <li class="nav-item"><a class="nav-link">論壇</a></li>
                        <li class="nav-item"><a class="nav-link">會員中心</a></li>
                        <li class="nav-item"><a class="nav-link">購物車?</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 背景圖 -->
 	<img id="wave" src="../img/wave.svg" alt="">
    <img id="depthBar" src="../img/depth-bar.png" alt="">
    <div class="divePosition">
    	<img id="diveMan" src="../img/diveManWhite.svg" alt="">
        <span class="howDive"></span>
    </div>
</div>

<main class="main">