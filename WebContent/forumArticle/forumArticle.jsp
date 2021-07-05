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
				width: 60%;
				font-family:"Microsoft JhengHei", "Apple LiGothic Medium", Arial;
				margin-top: 10px;
				margin: 0 auto;
			}
			
			.newpost{
				position: relative;
			    left: 1073px;
			    top: -10px;
			}
			
			
		</style>
	</head>
	<body>

		<jsp:include page="../share/navbar.jsp" flush="true" />
		<%-- <c:if test="userID != null"> --%>
			<button class="newpost" type="button" onclick="location.href='<%=request.getContextPath()%>/forumArticle/forumArticleInsert.jsp'">新增文章</button>
		<%-- </c:if> --%>
		<%@ include file="page1frontend.file" %>
		
		<c:forEach var="forumArticleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			
			<div class="post-heading">
				<div id="css_table">
					<div class="css_tr">
							<div class="col-xs-2 col-md-1">
								<form method="post" action="forumArticle.do" class="btn-div">
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="hidden" name="articleSN" value="${forumArticleVO.articleSN}">
									<input class="btn btn-success" type="submit" value="${forumArticleVO.articleTitle}">
								</form>
							</div>
						<span class="css_td" style="text-align: center;"><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd HH:mm:ss "/></span>
						
						<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
						<span class="css_td" style="text-align: center;">作者：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
						
						
					</div>

				</div>
            </div>
		</c:forEach>
		
		<%@ include file="page2frontend.file" %>
		<jsp:include page="../share/footer.jsp" flush="true" />

	</body>
</html>