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
		<title>論壇 - 文章編輯</title>
		
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
						<h4>編輯文章</h4>
					</td>
				</tr>
			</table>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
				
		<FORM METHOD="post" ACTION="forumArticle.do" name="form1">
			<table>
				<tr>
					<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					<td><input type="text" name="article" value="${forumArticleVO.userID}" readonly></td>
					<td><p>${memberSvc.getone(forumArticleVO.userID).nickName}</p></td>
				</tr>
				<tr>
					<td>
						<input type="text" name="articleTitle" size="60" />
					</td>
				</tr>
				<tr>
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
					
						<input type="hidden" name="action" value="userUpdate">
						<input type="submit" value="完成編輯" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
					</td>	
				</tr>
			</table>
		</FORM>
		
		<jsp:include page="../share/footer.jsp" flush="true" />
		
		
	</body>
</html>