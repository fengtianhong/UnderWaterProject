<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.member.model.*" %>
<%@ page import="com.forumComment.model.*" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="../share/index.css">
		<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
		<meta charset="UTF-8">
		<title>論壇 - 文章內容</title>
		
		<style type="text/css">
			#showbox {
				width: 75%;
				margin: 0 auto;
			}
		
			＃comment {
				width: 75%;
				margin: 0 auto;
			}
		
		</style>
		
		
	</head>
	<body>
		<jsp:include page="../share/navbar.jsp" flush="true" />
			<div id="showbox">
				<div id="bar">
					<span class="css_td" style="text-align: center;"><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd"/></span>		
					<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					<span class="css_td" style="text-align: center;">作者：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
					<span class="css_td" style="text-align: center;">文章好評：${forumArticleVO.rateGCount}</span>
					<span class="css_td" style="text-align: center;">文章負評：${forumArticleVO.rateNGCount}</span>
				</div>
				<div id="title"><h3>${forumArticleVO.articleTitle}</h3></div>
				<div id="text"><h5>${forumArticleVO.articleText}</h5></div>
			</div>

		<div id="comment">
			<h>想對這則文章發表留言評論嗎？</h6>
			<div id="commentShowbox">
				<div id="comment"><h4></h4></div>
			</div>
		</div>
		
		
		<jsp:include page="../share/footer.jsp" flush="true" />
	</body>
</html>