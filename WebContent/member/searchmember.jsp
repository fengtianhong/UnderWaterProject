<%@page import="com.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
// 	MemberService memberSvc = new MemberService();
// 	List<MemberVO> list = (List<MemberVO>)session.getAttribute("listBySearch");
// 	pageContext.setAttribute("list", list);
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("list");
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<!-- 	<link href="css/app.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="../share/index.css">
	<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<title>Search Member</title>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />
<jsp:include page="../share/member/Mheader.jsp" flush="true"/>
<!-- <div class="wrapper"> -->
<!-- 		<div class="main"> -->
<!-- 			<main class="content"> -->
<!-- 				<div class="container-fluid p-0"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-3 col-xl-2"> -->

<!-- 							<div class="card"> -->
<!-- 								<div class="list-group list-group-flush" role="tablist"> -->
<!-- 									<a class="list-group-item list-group-item-action" href="personinfo.jsp" role="tab"> 會員資訊 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="personinfochange.jsp" role="tab"> 資訊變更</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="personchangepwd.jsp" role="tab"> 密碼變更</a>  -->
<!-- 									<a class="list-group-item list-group-item-action active" href="#" role="tab"> 尋找會員 </a>  -->
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
										
										<form action="MemberInfoServlet.do" method="post">
											<div class="card-header">
												<p style="text-align:right; color:red;">
													<c:if test="${not empty errorMsgs}">
														<section>查無此條件資料，請重新查詢！</section>
													</c:if>
												</p>
												<input type="text" class="card-title mb-0" name="keyword"  placeholder="搜尋帳號、暱稱、姓名"> 
												<button type="submit">搜尋</button>
												<input type="hidden" name="action" value="search">
											</div>
										</form>
										
<!-- 									</div> -->
<!-- 								</div> -->
								<table style="border:solid; text-align:center; ">
									<tr>
										<th>帳號</th>
										<th>暱稱</th>
										<th>姓名</th>
										<th>操作</th>
									</tr>
									<c:if test="${list!=null}">
									<%@ include file="page1.file" %> 
									<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										
										<tr>
											<td>${memberVO.account}</td>
											<td>${memberVO.nickName}</td>
											<td>${memberVO.userName}</td>
											<td>
			 									<form method="post" action="<%=request.getContextPath()%>/member/MemberInfoServlet.do" style="margin-bottom: 0px;">
			     									<input type="submit" value="查看">
			     									<input type="hidden" name="userID"  value="${memberVO.userID}">
			     									<input type="hidden" name="action"	value="memberDetail">
			    								</FORM>
											</td>
										</tr>
									</c:forEach>
							
									<%@ include file="page2.file" %>	
									</c:if>
							</table>					
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				</main> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<script src="js/vendor.js"></script> -->
<jsp:include page="../share/member/Mfooter.html" flush="true"/>    
<jsp:include page="../share/footer.jsp" flush="true" />
	<script src="js/app.js"></script>

</body>
<style>
	th,td{
		border:1px solid black;
	}
	.content{
	    width: 1000px;
    	margin: 0 auto;
	}
</style>
</html>