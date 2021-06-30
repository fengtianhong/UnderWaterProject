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
				width: 75%;
				font-family:"Microsoft JhengHei", "Apple LiGothic Medium", Arial;
				margin-top: 10px;
				margin: 0 auto;
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
						<div class="css_td"><h5>${forumArticleVO.articleText}</h5></div>
							<div class="col-xs-2 col-md-1">
								<form method="post" action="forumArticle.do" class="btn-div">
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="hidden" name="articleSN" value="${forumArticleVO.articleSN}">
									<input class="btn btn-success" type="submit" value="(...閱讀更多)">
								</form>
							</div>
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