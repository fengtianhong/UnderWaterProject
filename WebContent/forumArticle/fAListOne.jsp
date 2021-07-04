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



<%=articleSN %>
<%=list == null %>
<%=userID %> 
<%=cmtSN %>



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
				width: 1000px;
				margin: 0 auto;
				border: 2px white double;
				margin-bottom: 10px;
			}
			
			.red {
				position: relative;
			    top: 24px;
			    left: 750px;
			}
			
		</style>
		
		
	</head>
	<body>
		<jsp:include page="../share/navbar.jsp" flush="true" />
			<div id="showbox">
				<div id="infobox" style="display:flex; align-items: ;" >
					<span id="title" style="margin-left: 20px; margin-top: 20px; font-size: 24px; font-style: italic; font-weight: bold;">
							${forumArticleVO.articleTitle}</span>
						<span class="red">
							<c:if test="${forumArticleVO.userID != userID }">
								<form method="post" action="articleReport.do">
									<input type="submit" value="檢舉">
									<input type="hidden" name="articleSN" value="<%=articleSN%>">
									<input type="hidden" name="action" value="insert">
								</form>
							</c:if>
						</span>	
 						<span class="red">
							<c:if test="${forumArticleVO.userID == userID }">
								<form method="post" action="forumArticle.do">
									<input type="submit" value="編輯">
									<input type="hidden" name="articleSN" value="<%=articleSN%>">
									<input type="hidden" name="action" value="getOne_For_Update">
								</form>
							</c:if>
						</span>	
						<span class="red">
							<c:if test="${forumArticleVO.userID == userID }">
								<form method="post" action="forumArticle.do">
									<input type="submit" value="刪除">
									<input type="hidden" name="articleSN" value="<%=articleSN%>">
									<input type="hidden" name="action" value="hiddenAtricle">
								</form>
							</c:if>
						</span>

				</div>
				
				
				<hr>
				<div id="bar" style="margin-left: 20px; margin-top: 20px; box-shadow: 0 1px; padding-bottom:5px;">
					<jsp:useBean id="memberSvc" scope="session" class="com.member.model.MemberService" />
					<span class="css_td">作者：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
					<span class="css_td" style=""><fmt:formatDate value="${forumArticleVO.publishedDate}" pattern="yyyy-MM-dd HH:mm:ss "/></span>
					<span class="css_td">文章好評：${forumArticleVO.rateGCount}</span>
					<span class="css_td">文章負評：${forumArticleVO.rateNGCount}</span>
				</div>
				
				<div id="text" style="margin-left: 20px; margin-top: 20px; margin-bottom: 60px;"><h5>${forumArticleVO.articleText}</h5></div>
				
				<%@ include file="page1frontend.file" %>
				<c:forEach var="forumCommentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

					<div class="commentShowbox" style="margin-left: 20px">
						<span class="css_td" style="text-align: center;">From：${memberSvc.getone(forumArticleVO.userID).nickName}</span>
						<span class="css_td" style="text-align: center;">在<fmt:formatDate value="${forumCommentVO.cmtDate}" pattern="yyyy-MM-dd HH:mm:ss"/>寫下：</span>
						<div class="cmtarea" style="margin-left: 20px;"><p>${forumCommentVO.cmtText}</p></div>
							<div>
							
								<c:if test="${userID == forumCommentVO.userID}">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumComment/forumComment.do" name="form1">
										<input type="hidden" name="action" value="delete">
										<input type="hidden" name="cmtSN" value="${forumCommentVO.cmtSN}">
										<input type="hidden" name="articleSN" value="${forumCommentVO.articleSN}">
										<input type="submit" value="刪除">
									</FORM>
								</c:if>
								
								<c:if test="${userID == forumCommentVO.userID}">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumComment/forumComment.do" name="form2">
										<input type="hidden" name="action" value="update">
										<input type="hidden" name="cmtSN" value="${forumCommentVO.cmtSN}">
										<input type="hidden" name="articleSN" value="${forumCommentVO.articleSN}">
										<input type="hidden" name="userID" value="${forumCommentVO.userID}">
										<textarea name="cmtText" rows="4" cols="52">${forumCommentVO.getCmtText()}</textarea>
										<input type="submit" value="編輯">
									</FORM>
								</c:if>
							
							</div>
					</div>
					
					
				</c:forEach>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumComment/forumComment.do" name="form1">
					<div id="cmtArea">
						<div>來自${memberSvc.getone(forumArticleVO.userID).nickName}的留言</div>
						<div>
							<textarea name="cmtText" rows="4" cols="50"></textarea>
							<input type="hidden" name="action" value="insert">
							<input type="hidden" name="articleSN" value="<%=articleSN%>">
							<input type="submit" value="新增留言" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
						</div>
					</div>
				</FORM>
					<input type="button" onclick="window.location.href='<%=request.getContextPath()%>/forumArticle/forumArticle.jsp'"
							value="回文章列表" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
			</div>	
				
		<%@ include file="page2frontend.file" %>
		<jsp:include page="../share/footer.jsp" flush="true" />
	</body>
</html>