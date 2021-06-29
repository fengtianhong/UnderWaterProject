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
					onclick="window.location.href='<%=request.getContextPath()%>/diveinfo/diveinfo.jsp;'" class="fa-stack fa-4x"> <i
						class="fas fa-circle fa-stack-2x text-primary"></i> <i
						class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
					</span>
					<h4 style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/diveinfo/diveinfo.jsp;'" class="my-3">潛水地圖</h4>
					<p style="cursor:pointer"
					onclick="window.location.href='<%=request.getContextPath()%>/diveinfo/diveinfo.jsp;'" class="text-muted">是否在為了下一次的冒險目的籌畫呢？給您地圖式的搜尋，快速找到心動的潛水點。</p>

				</div>

				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <i
						class="fas fa-circle fa-stack-2x text-primary"></i> <i
						class="fas fa-laptop fa-stack-1x fa-inverse"></i>
					</span>
					<h4 class="my-3">論壇</h4>
					<p class="text-muted">眾多潛水愛好者發表的潛點分享、潛點心得，就算還沒親自去過，也能先一窺究竟。</p>
				</div>
				<div class="col-md-4"
					onclick="window.location.href='<%=request.getContextPath()%>/grouptour/frontendListAll.jsp;'">
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
				<div class="col-lg-4 col-sm-6 mb-4">
					<!-- Portfolio item 1-->
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
							<div class="portfolio-caption-heading">潛水衣</div>
							<div class="portfolio-caption-subheading text-muted">穿膩了一身黑嗎</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-sm-6 mb-4">
					<!-- Portfolio item 2-->
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
							<div class="portfolio-caption-heading">潛水手錶</div>
							<div class="portfolio-caption-subheading text-muted">各大品牌任妳挑</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-sm-6 mb-4">
					<!-- Portfolio item 3-->
					<div class="portfolio-item">
						<a class="portfolio-link" data-bs-toggle="modal"
							href="#portfolioModal3">
							<div class="portfolio-hover">
								<div class="portfolio-hover-content">
									<i class="fas fa-plus fa-3x"></i>
								</div>
							</div> <img class="img-fluid" src="img/portfolio/3.jpg" alt="..." />
						</a>
						<div class="portfolio-caption">
							<div class="portfolio-caption-heading">活動商品</div>
							<div class="portfolio-caption-subheading text-muted">主打明星都在這</div>
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
						<div class="timeline-body">
							<p class="text-muted"><%=partyList.get(0).getPartyDetail()%></p>
						</div>
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
						<div class="timeline-body">
							<p class="text-muted"><%=partyList.get(1).getPartyDetail()%></p>
						</div>
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
						<img class="mx-auto rounded-circle" src="img/team/feng.jpg" alt="..." />
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
						<h4>來張大頭照</h4>
						<p class="text-muted">Lead Marketer</p>
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
									src="/img/portfolio/suit.jpg" alt="..." />
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
									src="../assets/img/portfolio/watch.jpg" alt="..." />
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
									src="../assets/img/portfolio/3.jpg" alt="..." />
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

</body>
</html>