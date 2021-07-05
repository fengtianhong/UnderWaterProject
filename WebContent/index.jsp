<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.news.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	NewsService newsSvc = new NewsService();
	List<NewsVO> list = newsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UNDERWATER</title>
<!-- Bootstrap 的 CSS -->
<link rel="stylesheet" href="vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/homepage.css">

<!-- 首頁套版資源 -->
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="vendor/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />

<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/homepage2.css" rel="stylesheet" />

</head>
<body id="page-top">

	<jsp:include page="/share/navbar_homepage.jsp" flush="true" />





	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-subheading">Welcome To UNDERWATER</div>
			<div class="masthead-heading text-uppercase">潛水者的天堂</div>
			<div class="words">
				<c:forEach var="newsVO" items="${list}" begin="0" end="5">
					<a href="<%=request.getContextPath()%>/news/news.jsp">${newsVO.title}</a>
				</c:forEach>
			</div>
			<a class="btn btn-primary btn-xl text-uppercase" href="#services">瞭解更多</a>
		</div>
	</header>

	<!-- Services-->
	<section class="page-section" id="services">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Services</h2>
				<h3 class="section-subheading text-muted">我們提供喜愛潛水的您</h3>
			</div>
			<div class="row text-center">

				<div  class="col-md-4" >

					<span style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/diveinfo/diveinfo.jsp'" class="fa-stack fa-4x"> <i
						class="fas fa-circle fa-stack-2x text-primary"></i> <i
						class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
					</span>
					<h4 style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/diveinfo/diveinfo.jsp'" class="my-3">潛水地圖</h4>
					<p style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/diveinfo/diveinfo.jsp'" class="text-muted">是否在為了下一次的冒險目的籌畫呢？給您地圖式的搜尋，快速找到心動的潛水點。</p>

				</div>

				<div class="col-md-4" style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/forumArticle/forumArticle.jsp'">
					<span class="fa-stack fa-4x"> <i
						class="fas fa-circle fa-stack-2x text-primary"></i> <i
						class="fas fa-laptop fa-stack-1x fa-inverse"></i>
					</span>
					<h4 class="my-3">論壇</h4>
					<p class="text-muted">眾多潛水愛好者發表的潛點分享、潛點心得，就算還沒親自去過，也能先一窺究竟。</p>
				</div>
				<div class="col-md-4" style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/grouptour/frontendListAll.jsp'">
					<span class="fa-stack fa-4x"> <i
						class="fas fa-circle fa-stack-2x text-primary"></i> <i
						class="fas fa-lock fa-stack-1x fa-inverse"></i>
					</span>
					<h4 class="my-3" style="cursor: pointer;">套裝行程</h4>
					<p class="text-muted" style="cursor: pointer;">還在探索潛水這個新領域嗎？讓我們充滿熱情的團隊來帶領您一起加入潛水的大家庭！</p>
				</div>
			</div>
			<div class="text-center">
				<a class="btn btn-primary text-uppercase" href="#portfolio">配備商品</a>
			</div>
		</div>
	</section>


	<!-- Portfolio Grid-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Portfolio</h2>
				<h3 class="section-subheading text-muted">最完整也最品質放心的潛水相關裝備</h3>
			</div>
			
			<div class="row">
				<div class="col-lg-4 col-sm-6 mb-4" onclick="window.location.href='<%=request.getContextPath()%>/product/ft_listAllProduct.jsp;'">
<!-- 					Portfolio item 1 -->
					<div class="portfolio-item">
						<a class="portfolio-link" data-bs-toggle="modal"
							href="#portfolioModal1">
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<i class="fas fa-plus fa-3x"></i>
								</div>
							</div> <img class="img-fluid" src="img/portfolio/suit.jpg" alt="..." />
						</a>
						<div class="portfolio-caption">
							<div class="portfolio-caption-heading">血拚好市</div>
							<div class="portfolio-caption-subheading text-muted">多種商品任您挑選</div>
						</div>
					</div>
				</div>			
				
				<div class="col-lg-4 col-sm-6 mb-4" onclick="window.location.href='<%=request.getContextPath()%>/product/ft_listPrimeProduct.jsp;'">
					<!-- Portfolio item 2 -->
					<div class="portfolio-item">
						<a class="portfolio-link" data-bs-toggle="modal"
							href="#portfolioModal2">
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<i class="fas fa-plus fa-3x"></i>
								</div>
							</div> <img class="img-fluid" src="img/portfolio/watch.jpg" alt="..." />
						</a>
						<div class="portfolio-caption">
							<div class="portfolio-caption-heading">暢銷專區</div>
							<div class="portfolio-caption-subheading text-muted">主打明星通通在這</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-4 col-sm-6 mb-4" onclick="window.location.href='<%=request.getContextPath()%>/product/ft_listDiscountProduct.jsp;'">
					<!-- Portfolio item 3-->
					<div class="portfolio-item">
						<a class="portfolio-link" data-bs-toggle="modal"
							href="#portfolioModal3">
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<i class="fas fa-plus fa-3x"></i>
								</div>
							</div> <img class="img-fluid" src="img/portfolio/BCD.jpg" alt="..." />
						</a>
						<div class="portfolio-caption">
							<div class="portfolio-caption-heading">優惠專區</div>
							<div class="portfolio-caption-subheading text-muted">百大品牌天天最低價</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- About-->
	<%@ page import="com.party.model.PartyService"%>
	<%@ page import="com.party.model.PartyVO"%>
	<%@ page import="java.util.*"%>
	<%
		PartyService partySvc = new PartyService();
		List<PartyVO> partyList = partySvc.getAll();
	%>
	<section class="page-section" id="about">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">揪團自由行</h2>
				<h3 class="section-subheading text-muted">出船就差幾個人嗎？想認識新夥伴？揪團揪起來！</h3>
			</div>
			<ul class="timeline">
				<li>
					<div class="timeline-image">
						<img class="rounded-circle img-fluid" src="img/about/party1.jpg"
							alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>
								揪團編號
								<%=partyList.get(0).getPartySN()%></h4>
							<h4 class="subheading"><%=partyList.get(0).getPartyTitle()%></h4>
						</div>
<!-- 						<div class="timeline-body"> -->
<%-- 							<p class="text-muted"><%=partyList.get(0).getPartyDetail()%></p> --%>
<!-- 						</div> -->
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<img class="rounded-circle img-fluid" src="img/about/party2.jpg"
							alt="..." />
					</div>
					<div class="timeline-panel">
						<div class="timeline-heading">
							<h4>
								揪團編號
								<%=partyList.get(1).getPartySN()%></h4>
							<h4 class="subheading"><%=partyList.get(1).getPartyTitle()%></h4>
						</div>
<!-- 						<div class="timeline-body"> -->
<%-- 							<p class="text-muted"><%=partyList.get(1).getPartyDetail()%></p> --%>
<!-- 						</div> -->
					</div>
				</li>
				<li class="timeline-inverted">
					<div class="timeline-image">
						<a href="party/party.do?action=party"><h4 style="color: white">
								更多揪團<br>就等你來
							</h4></a>
					</div>
				</li>
			</ul>
		</div>
	</section>

	<!-- Team-->
	<section class="page-section bg-light" id="team">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">Our Amazing Team</h2>
				<h3 class="section-subheading text-muted">TFA101 第5組 UNDERWATER
					在水之下</h3>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<div class="team-member">
						<img class="mx-auto rounded-circle" src="img/team/1.jpg" alt="..." />
						<h4>Tim Feng</h4>
						<p class="text-muted">潛點地圖、最新消息</p>
						<a class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="team-member">
						<img class="mx-auto rounded-circle" src="img/team/2.jpg" alt="..." />
						<h4>Huang</h4>
						<p class="text-muted">發起揪團 | 報名 | 審核資格</p>
						<a class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="team-member">
						<img class="mx-auto rounded-circle" src="img/team/3.jpg" alt="..." />
						<h4>了嗎XD</h4>
						<p class="text-muted">Lead Developer</p>
						<a class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="team-member">
						<img class="mx-auto rounded-circle" src="img/team/1.jpg" alt="..." />
						<h4>放六個人</h4>
						<p class="text-muted">Lead Developer</p>
						<a class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="team-member">
						<img class="mx-auto rounded-circle" src="img/team/2.jpg" alt="..." />
						<h4>很剛好</h4>
						<p class="text-muted">Lead Developer</p>
						<a class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="team-member">
						<img class="mx-auto rounded-circle" src="img/team/3.jpg" alt="..." />
						<h4>的感覺!</h4>
						<p class="text-muted">Lead Developer</p>
						<a class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-twitter"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-facebook-f"></i></a> <a
							class="btn btn-dark btn-social mx-2" href="#!"><i
							class="fab fa-linkedin-in"></i></a>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 mx-auto text-center">
					<p class="large text-muted">Lorem ipsum dolor sit amet,
						consectetur adipisicing elit. Aut eaque, laboriosam veritatis,
						quos non quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
				</div>
			</div>
		</div>
	</section>


	<!-- Portfolio Modals-->
	<!-- Portfolio item 1 modal popup-->
	<div class="portfolio-modal modal fade" id="portfolioModal1"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-bs-dismiss="modal">
					<img src="../assets/img/close-icon.svg" alt="Close modal" />
				</div>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="modal-body">
								<!-- Project details-->
								<h2 class="text-uppercase">Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid d-block mx-auto"
									src="img/portfolio/suit.jpg" alt="..." />
								<p>Use this area to describe your project. Lorem ipsum dolor
									sit amet, consectetur adipisicing elit. Est blanditiis dolorem
									culpa incidunt minus dignissimos deserunt repellat aperiam
									quasi sunt officia expedita beatae cupiditate, maiores
									repudiandae, nostrum, reiciendis facere nemo!</p>
								<ul class="list-inline">
									<li><strong>Client:</strong> Threads</li>
									<li><strong>Category:</strong> Illustration</li>
								</ul>
								<button class="btn btn-primary btn-xl text-uppercase"
									data-bs-dismiss="modal" type="button">
									<i class="fas fa-times me-1"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Portfolio item 2 modal popup-->
	<div class="portfolio-modal modal fade" id="portfolioModal2"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-bs-dismiss="modal">
					<img src="../assets/img/close-icon.svg" alt="Close modal" />
				</div>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="modal-body">
								<!-- Project details-->
								<h2 class="text-uppercase">Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid d-block mx-auto"
									src="img/portfolio/watch.jpg" alt="..." />
								<p>Use this area to describe your project. Lorem ipsum dolor
									sit amet, consectetur adipisicing elit. Est blanditiis dolorem
									culpa incidunt minus dignissimos deserunt repellat aperiam
									quasi sunt officia expedita beatae cupiditate, maiores
									repudiandae, nostrum, reiciendis facere nemo!</p>
								<ul class="list-inline">
									<li><strong>Client:</strong> Explore</li>
									<li><strong>Category:</strong> Graphic Design</li>
								</ul>
								<button class="btn btn-primary btn-xl text-uppercase"
									data-bs-dismiss="modal" type="button">
									<i class="fas fa-times me-1"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Portfolio item 3 modal popup-->
	<div class="portfolio-modal modal fade" id="portfolioModal3"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="close-modal" data-bs-dismiss="modal">
					<img src="../assets/img/close-icon.svg" alt="Close modal" />
				</div>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<div class="modal-body">
								<!-- Project details-->
								<h2 class="text-uppercase">Project Name</h2>
								<p class="item-intro text-muted">Lorem ipsum dolor sit amet
									consectetur.</p>
								<img class="img-fluid d-block mx-auto"
									src="img/portfolio/BCD.jpg" alt="..." />
								<p>Use this area to describe your project. Lorem ipsum dolor
									sit amet, consectetur adipisicing elit. Est blanditiis dolorem
									culpa incidunt minus dignissimos deserunt repellat aperiam
									quasi sunt officia expedita beatae cupiditate, maiores
									repudiandae, nostrum, reiciendis facere nemo!</p>
								<ul class="list-inline">
									<li><strong>Client:</strong> Finish</li>
									<li><strong>Category:</strong> Identity</li>
								</ul>
								<button class="btn btn-primary btn-xl text-uppercase"
									data-bs-dismiss="modal" type="button">
									<i class="fas fa-times me-1"></i> Close Project
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="/share/footer_homepage.jsp" flush="true" />
	<a class="btn btn-primary btn-sm text-uppercase chat" target="_blank"  href="<%=request.getContextPath()%>/chat/index.jsp"><i class="far fa-comment-dots" ></i></a>
	
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
	<a class="btn btn-primary btn-sm text-uppercase arrow-up" href="#"><i
		class="fas fa-arrow-up fa-lg"></i></a>

	<!-- 首頁套版資源 -->
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/js/homepage.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
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

</body>
</html>