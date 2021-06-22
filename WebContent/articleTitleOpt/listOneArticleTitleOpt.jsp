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
		
		<style>
			table#listallpg {
				background-color: #97CBFF;
				border: 2px solid black;
				text-align: center;
				margin: 0 auto;
			}
		</style>
		
	</head>
	<body>
	
		<table id="listOnepg">
			<tr>
				<td>
					<h3>查詢所有文章選項標題，列表如下</h3>
					<h4><a href="articleTitleOptSelect.jsp">回標題選項查詢首頁</a></h4>
				</td>
			</tr>
		</table>
		<table id="getone">
			<tr>
				<th>標題選項編號</th>
				<th>標題選項文字</th>
			</tr>

			<tr>
				<td><%=articleTitleOptVO.getArticleTitleOptSN()%></td>
				<td><%=articleTitleOptVO.getArticleTitleOptText()%></td>
			</tr>
		</table>
	</body>
</html>