<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

<div id="container">
	<!-- navbar navbar-expand-lg navbar-light bg-light -->
	<nav class="navbar fixed-top navbar-expand-md navbar-light">
		<div class="container"><a href="<%=request.getContextPath()%>" class="navbar-brand">UnderWater</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle naviation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/diveinfo/diveinfo.jsp">潛點地圖</a></li>
					<li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">潛水團</a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                              <a class="dropdown-item" href="<%=request.getContextPath()%>/grouptour/frontendListAll.jsp">套裝行程</a>
                              <a class="dropdown-item" href="<%=request.getContextPath()%>/party/party.do?action=party">揪團Go!</a>
                            </div>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/product/ft_listAllProduct.jsp">商城</a></li>
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/uwDiveSideIntro/Northcoast.jsp">潛點分享討論</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/personinfo.jsp">會員中心</a></li>
						<li class="nav-item"><a class="nav-link"  target="_blank"  href="<%=request.getContextPath()%>/chat/index.jsp">聊天</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/shoppingCar/Cart.jsp">購物車</a></li>
                        <c:if test="${userID==null}">
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/login.jsp">登入</a></li>
                        </c:if>
                        <c:if test="${userID!=null}">
                        	<form id="logout" action="<%=request.getContextPath()%>/member/LogoutServlet.do" method="post">
					        <li class="nav-item">
					        <a class="nav-link" href="#" onclick='document.getElementById("logout").submit();'>${memberSvc.getone(userID).userName}|登出</a>
					        </li>
					     	</form>
                        </c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 背景圖 -->
 	<img id="wave" src="img/wave.svg" alt="">
    <img id="depthBar" src="img/depth-bar.png" alt="">
    <div class="divePosition">
    	<img id="diveMan" src="img/diveManWhite.svg" alt="">
        <span class="howDive"></span>
    </div>
</div>

<main class="main">