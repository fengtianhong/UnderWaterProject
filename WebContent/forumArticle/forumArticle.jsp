<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>

<%
	ForumArticleService forumArticleSvc = new ForumArticleService();
	List<ForumArticleVO> list = forumArticleSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
	<head>
	
		<!-- 以下兩個link必須載入用於header,footer -->
		<link rel="stylesheet" href="../share/index.css">
		<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
		
		<meta charset="UTF-8">
		<title>論壇 - 討論區</title>
		<style>
		
		
		
		</style>
	</head>
	<body>
		<jsp:include page="../share/navbar.jsp" flush="true" />
			<table>
				<tr>
					<th>文章標題</th>
					<th>文章內容</th>
					<th>發表時間</th>
					<th>文章作者</th>
					<th>好評數量</th>
					<th>負評數量</th>
				</tr>
				<%@ include file="page1frontend.file" %>
				<c:forEach var="forumArticleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${forumArticleVO.articleTitle}</td>
						<td>${forumArticleVO.articleText}</td>
						<td><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd"/></td>
						<td>${forumArticleVO.userID}</td>
						<td>${forumArticleVO.rateGCount}</td>
						<td>${forumArticleVO.rateNGCount}</td>
						<td>
							<input type="submit" value="編輯文章">
							<input type="hidden" name="articleSN"  value="${forumArticleVO.articleSN}">
							<input type="hidden" name="action"	value="getOne_For_Update">
						</td>
						
					</tr>
				</c:forEach>
			</table>
		

	
	
	
	
	
	
	
	
	
	
		
	</body>
</html>