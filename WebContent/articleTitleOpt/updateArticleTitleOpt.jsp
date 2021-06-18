<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleTitleOpt.model.*"%>

<%
	ArticleTitleOptVO articleTitleOptVO = (ArticleTitleOptVO) request.getAttribute("articleTitleOptVO");
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>更新文章標題選項</title>
		<style>
			 table#update-1 {
			 	border: 1px solid black;
			 	text-align: center;
			 }
			 table#update-2{
			 	margin-top: 3px;
			 	
			 }
			 
		</style>

	</head>
	<body>
		<table id="update-1">
			<tr>
				<td>請於下方更新文章標題選項，確認無誤後送出
				<h4><a href="articleTitleOptSelect.jsp">回標題選項查詢首頁</a></h4></td>
			</tr>
		</table>
		<h3>資料修改:</h3>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<form method="post" action="articleTitleOpt.do" name="form1" enctype="multipart/form-data">
			<table id="update-2">
				<tr>
					<td>標題選項編號:<font color=red><b>*</b></font></td>
					<td><%=articleTitleOptVO.getArticleTitleOptSN() %></td>
				</tr>
				<tr>
					<td>標題選項文字：</td>
					<td><input type="text" name="articleTitleOptText" size="45" value="<%=articleTitleOptVO.getArticleTitleOptText()%>"></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="empno" value="<%=articleTitleOptVO.getArticleTitleOptSN()%>">
			<input type="submit" value="送出修改">
		</form>
	</body>
</html>