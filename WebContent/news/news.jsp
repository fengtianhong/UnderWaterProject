<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.news.model.*"%>
<%@ page import="java.util.*"%>
<%
	NewsService newsSvc = new NewsService();
	List<NewsVO> list = newsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>最新消息的啦</title>
<%@ include file="../util/meta.file"%>
<style>
.newsContent{
 margin-top:100px; 
height: 800px;
}
.forinclude{
	margin:0 auto;
	width:300px;
}
table {
	margin: auto;
	width: 800px;
}
</style>
</head>

<body>
	<%@ include file="../util/header.file"%>
	<%@ include file="../util/sidebar.file" %>
	<div class="newsContent">
	<table>
		<tr>
			<th>新聞標題</th>
			<th>新聞內容</th>
			<th>上架日期</th>
			<th>消息來源</th>
			<th>新聞類型</th>
		</tr>
		<div class="forinclude">
		<%@ include file="page1frontend.file"%>
		</div>
		<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${newsVO.title}</td>
				<td>${newsVO.content}</td>

				<td><fmt:formatDate value="${newsVO.newsDate}"
						pattern="yyyy-MM-dd" /></td>
				<td>${newsVO.newsFrom}</td>
				<td>${newsVO.newsType==0?"潛點":newsVO.newsType==1?"商品":"揪團"}</td>


			</tr>
		</c:forEach>
	</table>
	</div>
	<%@ include file="page2frontend.file"%>
	<%@ include file="../util/footer.file" %>
</body>
</html>