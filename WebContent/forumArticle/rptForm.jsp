<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleReport.model.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.member.model.*" %>

<%
	ArticleReportVO aRVO = (ArticleReportVO) request.getAttribute("aRVO");
	Integer userID = (Integer) session.getAttribute("userID");
	Integer articleSN = (Integer) session.getAttribute("articleSN");
%>



<!DOCTYPE html>
<html>
	<head>
		<!-- 以下兩個link必須載入用於header,footer -->
		<link rel="stylesheet" href="../share/index.css">
		<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
		
		<meta charset="UTF-8">
		<title>論壇 - 文章檢舉頁</title>
		<style>
			 .rptbox {
				margin: 0 auto; 
				margin-top: 10px;
				width: 50%;
				background-color: paleturquoise;
				text-align: center;
				border: 2px sloid white;
			}
		</style>

	</head>
	<body>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>	
	
	
		<jsp:include page="../share/navbar.jsp" flush="true" />
		
		<div class="rptbox">
			<table>
				<tr>
					<td><h5>
						請簡述檢舉該文章原因，<br>
						檢舉處理回覆可在個人頁面查詢取得資訊。<br>
						若查詢狀態為待處理，請耐心等候，將儘速處理。
					</h5></td>
				</tr>
				<tr>
					<td class="rptbox">
						<form method="post" action="articleReport.do" name="rptform">
						<textarea name="rptReason" rows="5" cols="65"></textarea><br>
			
						<input type="button" onclick="window.location.href='<%=request.getContextPath()%>/forumArticle/forumArticle.jsp'"
						value="回文章列表" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
						
						<input type="hidden" name="action" value="insertrpt">
						<input type="hidden" name="articleSN" value="${articleSN}">
						<input type="submit" value="送出檢舉" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">

						</form>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="../share/footer.jsp" flush="true" />
	</body>
</html>