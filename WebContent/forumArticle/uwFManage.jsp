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
				<td><td>
			</tr>
		
		</table>
		
		
		
		

		<%@ include file="../share/backend/Bfooter.file" %>
	</body>
		<%@ include file="../share/backend/Bjs.file" %>
	
</html>