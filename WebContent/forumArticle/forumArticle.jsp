<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.member.model.*" %>

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
			#css_table {
				display:table;
				background-color: paleturquoise;
				border: 2px dashed white;
				width: 100%;
				font-family:"Microsoft JhengHei", "Apple LiGothic Medium", Arial;
				margin-top: 10px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="../share/navbar.jsp" flush="true" />
		<%@ include file="page1frontend.file" %>
		<c:forEach var="forumArticleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
			<div class="post-heading">
				<div id="css_table">
					<div class="css_tr">
						<div class="css_td" style="text-align: center;"><h3>${forumArticleVO.articleTitle}</h3></div>
						<div class="css_td"><h6>${forumArticleVO.articleText}</h6></div>
						<span class="css_td" style="text-align: center;"><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd"/></span>
						<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
						<span class="css_td" style="text-align: center;">作者：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
						<span class="css_td" style="text-align: center;">文章好評：${forumArticleVO.rateGCount}</span>
						<span class="css_td" style="text-align: center;">文章負評：${forumArticleVO.rateNGCount}</span>
					</div>
				</div>
            </div>
		</c:forEach>
		<jsp:include page="../share/footer.jsp" flush="true" />
	</body>
</html>