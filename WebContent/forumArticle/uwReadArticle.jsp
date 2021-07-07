<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.forumComment.model.*" %>

<%
	Integer articleSN = (Integer)request.getAttribute("articleSN");
	Integer userID = (Integer)session.getAttribute("userID");
	Integer cmtSN = (Integer)request.getAttribute("cmtSN");


	ForumCommentService forumCommentSvc = new ForumCommentService();
	List<ForumCommentVO> list = forumCommentSvc.getOneForumComment(articleSN);
	pageContext.setAttribute("list", list);
	
	ForumArticleService faSvc = new ForumArticleService();
	ForumArticleVO forumArticleVO = faSvc.getOneForumArticle(articleSN);
	pageContext.setAttribute("forumArticleVO", forumArticleVO);
%>


<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../share/backend/Bmeta.file" %>
		<meta charset="UTF-8">
		<title>UW - 查看檢舉</title>
	</head>
	<body>
		<%@ include file="../share/backend/Bheader.file" %>
			<!-- 文章標頭區塊 -->
			<div id="bar" style="margin-left: 20px; margin-top: 20px; box-shadow: 0 1px; padding-bottom:5px;">
				<jsp:useBean id="memberSvc" scope="session" class="com.member.model.MemberService" />
				<span class="css_td">作者：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
				<span class="css_td" style=""><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd HH:mm:ss "/></span>
			</div>
			<!-- 文章顯示內文區塊 -->
			<div id="text" style="margin-left: 20px; margin-top: 20px; margin-bottom: 60px;"><h5>${forumArticleVO.articleText}</h5></div>








	
	
		<%@ include file="../share/backend/Bfooter.file" %>
	</body>
		<%@ include file="../share/backend/Bjs.file" %>
</html>