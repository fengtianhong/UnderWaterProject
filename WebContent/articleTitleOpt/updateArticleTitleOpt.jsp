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
	</head>
	<body>
		<table id="update-1">
			<tr>
				<td>請於下方更新文章標題選項，確認無誤後送出</td>
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
			<table>
				<tr>
					<td>標題選項編號:<font color=red><b>*</b></font></td>
					<td><%=articleTitleOptVO.getArticleTitleOptText() %></td>
				</tr>
			</table>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="empno" value="<%=articleTitleOptVO.getArticleTitleOptSN()%>">
			<input type="submit" value="送出修改">
		</form>
	</body>
</html>