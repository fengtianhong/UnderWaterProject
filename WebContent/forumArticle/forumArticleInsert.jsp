<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.member.model.*" %>

<%
	ForumArticleVO forumArticleVO = (ForumArticleVO) request.getAttribute("forumArticleVO");
%>

<!DOCTYPE html>
	<html>
		<head>
			<!-- 以下兩個link必須載入用於header,footer -->
			<link rel="stylesheet" href="../share/index.css">
			<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
		
			<meta charset="UTF-8">
			<title>論壇 - 文章新增頁面</title>
				<style>
					  table#table-1 {
						background-color: paleturquoise;
					    border: 1px solid black;
					    text-align: center;
					  }
					  table#table-1 h4 {
					    color: black;
					    display: block;
					    margin-bottom: 1px;
					  }
					  h4 {
					    color: blue;
					    display: inline;
					  }
					</style>
					
					<style>
					  table {
						width: 65%;
						background-color: white;
						margin-top: 1px;
						margin-bottom: 1px;
						margin: 1px auto;
					  }
					  table, th, td {
					    border: 0px solid #CCCCFF;
					  }
					  th, td {
					    padding: 1px;
					  }
					  
					  table img {
						  max-width: 100%;
						  max-height: 100px;
						}
					  
					  
					</style>
					
					<script type="text/javascript" src="<%=request.getContextPath()%>/party/ckeditor/ckeditor.js"></script>
			

		</head>
		<body>
			<jsp:include page="../share/navbar.jsp" flush="true" />
		
			<table id="table-1">
				<tr>
					<td>
						<h4>新增文章</h4>
					</td>
				</tr>
			</table>

			<%-- 錯誤表列 --%>
<%-- 			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if> --%>
			
			<FORM METHOD="post" ACTION="forumArticle.do" name="form1">
				<table>
					<tr>
						<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
						<!-- <td>發文作者</td> -->
						<td><input type="text" name="article" value="${forumArticleVO.userID}" readonly></td>
						
						<!-- <td><input type="text" name="article" value="2" readonly></td> -->
						
						<td><p>${memberSvc.getone(forumArticleVO.userID).nickName}</p></td>
					</tr>
					<tr>
						<jsp:useBean id="articleTitleOptSvc" scope="page" class="com.articleTitleOpt.model.ArticleTitleOptService"></jsp:useBean>
						<!-- <td>文章分類<font color=red><b>*</b></font></td> -->
						<td>
							<select size="1" name="articleTitleOptSN">
								<c:forEach var="articleTitleOptVO" items="${articleTitleOptSvc.all}">
									<option value="${articleTitleOptVO.articleTitleOptSN}" ${(forumArticleVO.articleTitleOptSN==articleTitleOptVO.articleTitleOptSN)? 'selected':''}> ${articleTitleOptVO.articleTitleOptText}
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<!-- <td>文章標題</td> -->
						<td>
							<input type="text" name="articleTitle" size="60" />
						</td>
					</tr>
					<tr>
						<!-- <td>文章內文</td> -->
						<td>
							
							<textarea class="articleText" name="articleText" style="maxlength=1000"></textarea>
								 <script>
								  CKEDITOR.replace("articleText");
								 </script>
						</td>
					</tr>	
					<tr>
						<td align="center">
							<input type="button" onclick="window.location.href='<%=request.getContextPath()%>/forumArticle/forumArticle.jsp'"
							value="忍痛放棄" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
						
							<input type="hidden" name="action" value="insert">
							<input type="submit" value="送出新增" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
						</td>
					</tr>
				</table>
			</Form>
			

		
		
		
			<jsp:include page="../share/footer.jsp" flush="true" />



		</body>
			
</html>