<%@	page import="com.member.model.MemberVO"%>
<%@	page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>ChangPwd</title>
<!-- 	<link href="css/app.css" rel="stylesheet"> -->
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
<!-- 									<a class="list-group-item list-group-item-action" href="personinfo.jsp" role="tab"> 會員資訊 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="personinfochange.jsp" role="tab"> 資訊變更 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action active" href="personchangepwd.jsp" role="tab"> 密碼變更</a>  -->
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
<!-- 								<div id="password" role="tabpanel"> -->
<!-- 									<div class="card"> -->
										<div class="card-body">

												<c:if test="${not empty successMsgs}">
													<font style="color:red"></font>
													<c:forEach var="message" items="${successMsgs}">
														<a style="color:red">${message}</a>
													</c:forEach>
												</c:if>

											<form action="PersonChangeServlet.do" method="post">
												<div class="form-group">
													<label for="inputPasswordCurrent">原始密碼</label>
													<input type="password" name="pwd" class="form-control" id="inputPasswordCurrent" style="width:300px">
												</div>
												<div class="form-group">
													<label for="inputPasswordNew">新密碼</label>
													<input type="password" name="newpwd1" class="form-control" id="inputPasswordNew" style="width:300px">
												</div>
												<div class="form-group">
													<label for="inputPasswordNew2">再次確認密碼</label>
													<input type="password" name="newpwd2" class="form-control" id="inputPasswordNew2" style="width:300px">
												</div>
												<input type="hidden" name="userID" value="${memberVO.userID}">
												<input type="hidden" name="action" value="update_pwd">
												<button type="submit" class="btn btn-primary" style="margin-left:100px;">送出</button>
											</form>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
										</div>
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</main> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
<!-- 	<script src="js/vendor.js"></script> -->
<!-- 	<script src="js/app.js"></script> -->
<jsp:include page="../share/member/Mfooter.html" flush="true"/>    
<jsp:include page="../share/footer.jsp" flush="true" />
<script>
//cancel

$(function () {
	
	$('#personchangepwd').addClass('active');
})

</script>	
</body>
</html>