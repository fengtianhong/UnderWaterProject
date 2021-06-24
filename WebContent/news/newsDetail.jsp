<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<html>
<head>
<style type="text/css">
.show_pic {
	width: 400px;
}

img {
	width: 100%;
}
 textarea
{
     resize: none;
     cursor: pointer;
}
</style>
<title><%=newsVO.getTitle()%></title>
</head>
<body>
	<table>
		<tr>
			<td>標題:</td>
			<td><%=newsVO.getTitle()%></td>
		</tr>
		<tr>
			<td>上架日期:</td>
			<td><%=newsVO.getNewsDate()%></td>
		</tr>
		<tr>
			<td>內容:</td>
			<td><textarea readonly rows="6" cols="40"><%=newsVO.getContent()%></textarea></td>
		</tr>

		<tr>
			<td>消息來源:</td>
			<td><%=newsVO.getNewsFrom()%></td>
		</tr>

		<tr>
			<td>圖片:</td>

			<td><div class="show_pic">
					<img
						src="<%=request.getContextPath()%>/news/ShowPic?newsSN=${newsVO.newsSN}">
				</div></td>

		</tr>




	</table>
	<button onclick="goback()">回上一頁</button>
	<script>
		function goback() {
			history.go(-1);
		}
	</script>
</body>
</html>