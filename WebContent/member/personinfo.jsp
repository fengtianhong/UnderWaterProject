<%@page import="com.member.model.MemberService"%>
<%@page import="com.member.model.MemberVO"%>
<%@ page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.grouptour.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	
	Integer userID = Integer.parseInt(session.getAttribute("userID").toString());
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.getone(userID);
	pageContext.setAttribute("memberVO", memberVO);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<!-- 	<link href="css/app.css" rel="stylesheet"> -->
	<title>UNDER WATER 會員中心</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<style>
	.content{
	    width: 1000px;
    	margin: 0 auto;
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />
<jsp:include page="../share/member/Mheader.jsp" flush="true"/>
<!-- 	<div class="wrapper"> -->
<!-- 		<div class="main"> -->
<!-- 			<main class="content"> -->
<!-- 				<div class="container-fluid p-0"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-3 col-xl-2"> -->

<!-- 							<div class="card"> -->
<!-- 								<div class="list-group list-group-flush" role="tablist"> -->
<!-- 									<a class="list-group-item list-group-item-action active" href="personinfo.jsp" role="tab"> 會員資訊 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="personinfochange.jsp" role="tab"> 資訊變更</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="personchangepwd.jsp" role="tab"> 密碼變更</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="searchmember.jsp" role="tab"> 尋找會員 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						<div class="col-md-9 col-xl-10"> -->
<!-- 							<div class="tab-content"> -->
<!-- 								<div class="tab-pane fade show active" id="account"	role="tabpanel"> -->
<!-- 									<div class="card"> -->
										
										<div class="card-body">
											<div class="text-center" style="margin-bottom:30px">
												<img alt="Charles Hall" src="GetImagepersonPhoto.do?userid=${memberVO.userID}" class="rounded-circle img-responsive mt-2" width="128" height="128">
											</div>
												<div>
													<div>
														<div class="form-group">
															<label style="margin-left:240px;">
																帳號:<a>${memberVO.account}</a>
															</label>
														</div>
														<div class="form-group">
															<label style="margin-left:240px;">暱稱:
																<a> ${memberVO.nickName}</a>
															</label>
														</div>
														<div  class="form-group">
															<label style="margin-left:240px;">姓名:
																<a> ${memberVO.userName}</a>
															</label>
														</div>
														<div  class="form-group">
															<label style="margin-left:240px;">性別:
																<a>
																	<c:if test="${memberVO.gender==0}">男</c:if>
	        														<c:if test="${memberVO.gender==1}">女</c:if>
																</a>
															</label>
														</div>
														<div  class="form-group">
															<label style="margin-left:240px;">會員生日:
																<a> ${memberVO.birthDate}</a>
															</label>
														</div>
														<div  class="form-group">
															<label style="margin-left:240px;">證照:
	       														<c:if test="${memberVO.certification==0}">未持有</c:if>
	        													<c:if test="${memberVO.certification==1}">PADI OW / SSI OW</c:if>
	        													<c:if test="${memberVO.certification==2}">PADI AOW / SSI AOW</c:if>
															</label>
														</div>
													</div>
													
												</div>
										</div>
<!-- 									</div> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				</main> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
<jsp:include page="../share/member/Mfooter.html" flush="true"/>    
<jsp:include page="../share/footer.jsp" flush="true" />
<script>
//cancel

$(function () {
	
	$('#personinfo').addClass('active');
})

</script>
</body>
</html>