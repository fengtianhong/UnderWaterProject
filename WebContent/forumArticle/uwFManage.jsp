<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.articleReport.model.*"%>
<%@ page import="com.member.model.*" %>


<%
	Integer userID = (Integer) session.getAttribute("userID");

	ForumArticleService forumArticleSvc = new ForumArticleService();
	List<ForumArticleVO> list = forumArticleSvc.getAll();
	pageContext.setAttribute("list", list);

	ArticleReportService aRSvc = new ArticleReportService();
	List<ArticleReportVO> rptlist = aRSvc.getAll();
	pageContext.setAttribute("rptlist", rptlist);

%>





<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../share/backend/Bmeta.file" %>
		<meta charset="UTF-8">
		<title>UW - 文章管理</title>
		<style type="text/css">
			.articlestatus {
				border: 1px solid black;
				margin: 0 auto;
			}
			
			tr {
				border: 1px solid black;
			}
			
			td {
				border: 1px solid black;
			}
			
/* 			.readone {
				margin: 0;
				padding: 0;
				border: 1px solid transparent;
				outline: none;
			} */
		</style>

	</head>
	<body>
		<%@ include file="../share/backend/Bheader.file" %>
		
		<table class="articlestatus">
			<tr>
				<td>文章編號</td>
				<td>發文作者</td>
				<td>文章標題</td>
				<td>文章狀態</td>
				<td>查看內文</td>
			</tr>
			<c:forEach var="article" items="${list}">
				<tr>
					<td>${article.articleSN}</td>
					<td>${article.userID}</td>
					<td>
					</td>
					
					<c:if test="${article.articleStatus == true}">
						<td>正常顯示</td>
					</c:if>
					<c:if test="${article.articleStatus == false}">
						<td>正常隱藏</td>
					</c:if>
				<tr>
			</c:forEach>
		</table>
		
		
		
		

		<%@ include file="../share/backend/Bfooter.file" %>
	</body>
		<%@ include file="../share/backend/Bjs.file" %>
	
</html>