<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.news.model.*"%>

<%
	NewsService newsSvc = new NewsService();
	List<NewsVO> list = newsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	/*     text-align: center; */
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

th {
	white-space: nowrap;
}

h4 {
	color: blue;
	display: inline;
}

table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

.show_pic {
	width: 200px;
}

img {
	width: 100%;
}

th, td {
	padding: 5px;
	/*     text-align: center; */
}

textarea {
	color: #858796;
	border: none;
	resize: none !important;
	cursor: pointer;
}
</style>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file"%>
<title>最新消息表單</title>
</head>
<body>
	<%@ include file="../share/backend/Bheader.file"%>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>新聞編號</th>
			<th>新聞標題</th>
			<th>新聞內容</th>
			<th>照片</th>
			<th>上架日期</th>
			<th>消息來源<br>新聞類型</th>
			<th></th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${newsVO.newsSN}</td>
				<td>${newsVO.title}</td>
				<td><textarea rows="6" cols="40" readonly>${newsVO.content}</textarea></td>
				<td><div class="show_pic">
						<img
							src="<%=request.getContextPath()%>/news/ShowPic?newsSN=${newsVO.newsSN}">
					</div></td>
				<td><fmt:formatDate value="${newsVO.newsDate}"
						pattern="yyyy-MM-dd" /></td>
				<td>${newsVO.newsFrom}<br>${newsVO.newsType==0?"潛點":newsVO.newsType==1?"商品":"揪團"}</td>
					<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/news/news.do"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary btn-user" type="submit" value="修改"> <input type="hidden"
							name="newsSN" value="${newsVO.newsSN}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/news/news.do"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary btn-user" type="submit" value="刪除"> <input type="hidden"
							name="newsSN" value="${newsVO.newsSN}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	<%@ include file="../share/backend/Bfooter.file"%>
	<%@ include file="../share/backend/Bjs.file"%>
</body>
</html>