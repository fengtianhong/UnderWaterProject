<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<html>
<head>
<title><%=newsVO.getTitle()%></title>
</head>
<body>
	<table>
		<tr>
			<td>標題:</td>
			<td><input type="TEXT" name="title" size="45"
				value="<%=newsVO.getTitle()%>" /></td>
		</tr>
		<tr>
			<td>上架日期:</td>
			<td><input name="newsDate" id="f_date1" type="text"></td>
		</tr>
		<tr>
			<td>內容:</td>
			<td><input type="TEXT" name="content" size="45"
				value="<%=newsVO.getContent()%>" /></td>
		</tr>

		<tr>
			<td>消息來源:</td>
			<td><input type="TEXT" name="newsFrom" size="45"
				value="<%=newsVO.getNewsFrom()%>" /></td>
		</tr>

		<tr>
			<td>圖片:</td>

			<td><div class="show_pic">
					<img
						src="<%=request.getContextPath()%>/news/ShowPic?newsSN=${newsVO.newsSN}">
				</div></td>

		</tr>




	</table>
</body>
</html>