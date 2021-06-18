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
		<title>查詢標題選項：</title>
	</head>
	<body>
		<table>
			<tr>
				<td>標題選項編號</td>
				<td>標題選項文字</td>
			</tr>

			<tr>
				<td><%=articleTitleOptVO.getArticleTitleOptSN()%></td>
				<td><%=articleTitleOptVO.getArticleTitleOptText()%></td>
			</tr>
		</table>
	</body>
</html>