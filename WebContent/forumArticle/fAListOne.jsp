<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.forumComment.model.*" %>

<%
	ForumCommentService forumCommentSvc = new ForumCommentService();
	List<ForumCommentVO> list = forumCommentSvc.getAll();
	pageContext.setAttribute("list", list);

%>


<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../share/index.css">
		<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
		<meta charset="UTF-8">
		<title>論壇 - 文章內容</title>
		
		<style type="text/css">
			#showbox {
				background-color: paleturquoise;
				width: 75%;
				margin: 0 auto;
				border: 2px white double;
				margin-bottom: 10px;
			}
		</style>
		
		
	</head>
	<body>
		<jsp:include page="../share/navbar.jsp" flush="true" />
			<div id="showbox">
				<div id="bar" style="margin-left: 20px; margin-top: 20px; box-shadow: 0 1px; padding-bottom:5px;">
					<span class="css_td" style="text-align: center;"><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd HH:mm:ss "/></span>		
					<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					<span class="css_td" style="text-align: center;">作者：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
					<span class="css_td" style="text-align: center;">文章好評：${forumArticleVO.rateGCount}</span>
					<span class="css_td" style="text-align: center;">文章負評：${forumArticleVO.rateNGCount}</span>
				</div>
				<div id="title" style="margin-left: 20px; margin-top: 20px;"><h3>${forumArticleVO.articleTitle}</h3></div>
				<div id="text" style="margin-left: 20px; margin-top: 20px;"><h5>${forumArticleVO.articleText}</h5></div>
				
				<%@ include file="page1frontend.file" %>
				<c:forEach var="forumCommentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
					<div class="commentShowbox" style="margin-left: 20px">
						<span class="css_td" style="text-align: center;">From：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
						<span class="css_td" style="text-align: center;">在<fmt:formatDate value="${forumCommentVO.cmtDate}" pattern="yyyy-MM-dd HH:mm:ss "/>寫下：</span>
						<div class="cmtarea" style="margin-left: 20px;"><p>${forumCommentVO.cmtText}</p></div>
					</div>
					
				</c:forEach>
				
					<input type="button" onclick="window.location.href='<%=request.getContextPath()%>/forumArticle/forumArticle.jsp'"
							value="回文章列表" style="border-radius: 7px; margin-bottom: 20px;">
			</div>	
				

		<jsp:include page="../share/footer.jsp" flush="true" />
	</body>
</html>