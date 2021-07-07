<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.articleReport.model.*"%>
<%@ page import="com.member.model.*" %>


<%
	Integer userID = (Integer) session.getAttribute("userID");
	Integer articleSN = (Integer)request.getAttribute("articleSN");

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
			.rtpArticle {
				border: 1px solid black;
				margin: 0 auto;
				width: 600px;
			}
			
			tr {
				border: 1px solid black;
			}
			
			td {
				border: 1px solid black;
			}
			.articleHidden {
				margin: 0 auto;
				width: 600px;
				text-align: center;
			}
		</style>

	</head>
	<body>
		<%@ include file="../share/backend/Bheader.file" %>
			<div class="articleHidden"><h3>文章下架</h3></div>
			<table class="rtpArticle">
				<tr>
					<td>文章編號</td>
					<td>查看內文</td>
				</tr>
				<c:forEach var="forumArticleVO" items="${list}">
					<tr>
						<td>
							${forumArticleVO.articleSN}
						</td>
						<td>
							<form method="post" action="forumArticle.do" class="btn-div">
								<input type="hidden" name="action" value="getOne_For_mDisplay">
								<input type="hidden" name="articleSN" value="${forumArticleVO.articleSN}">
								<input class="btn btn-success" type="submit" value="${forumArticleVO.articleTitle}">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		

		<%@ include file="../share/backend/Bfooter.file" %>
	</body>
		<%@ include file="../share/backend/Bjs.file" %>
	
</html>