<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.news.model.*"%>
<%
	NewsVO newsVO = (NewsVO) request.getAttribute("newsVO");
%>
<html>
<head>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">

<style type="text/css">
input {
	background-color: #017f9d;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	transition-duration: 0.4s;
	border: 2px #017f9d solid;
	float: right;
	margin-top: 5px;
	margin-right: 20px;
}

input:hover {
	color: #017f9d;
	background-color: #fff;
	border: 2px #017f9d solid;
}

.show_pic {
	width: 400px;
}

.show_pic>img {
	width: 100%;
}

.title {
	white-space:nowrap; 
}

tr, td {
	color: #999;
}

#contener {
	background-color: #eee;
	border-radius: 10px;
	margin: 0px auto;
	width: 500px;
	border: 2px #ccc solid;
	padding: 10px 10px 50px;
	margin: 0px auto;
}

textarea {
	color: #999;
	width: 400px;
	cursor: auto;
	resize: none;
}
</style>
<title><%=newsVO.getTitle()%></title>
</head>
<body>
	<jsp:include page="../share/navbar.jsp" flush="true" />
	<div id="contener">
		<table>
			<tr>
				<td class="title">標題:</td>
				<td><%=newsVO.getTitle()%></td>
			</tr>
			<tr>
				<td class="title">上架日期:</td>
				<td><%=newsVO.getNewsDate()%></td>
			</tr>
			<tr>
				<td class="title">內容:</td>
				<td><textarea readonly rows="6" cols="40"><%=newsVO.getContent()%></textarea></td>
			</tr>



			<tr>
				<td class="title">圖片:</td>

				<td><div class="show_pic">
						<img
							src="<%=request.getContextPath()%>/news/ShowPic?newsSN=${newsVO.newsSN}">
					</div></td>

			</tr>

			<tr>
				<td class="title">消息來源:</td>
				<td><%=newsVO.getNewsFrom()%></td>
			</tr>


		</table>
		<input  type="button"
			onclick="window.location.href='<%=request.getContextPath()%>/news/news.jsp'"
			value="回上一頁">
	</div>


	<jsp:include page="../share/footer.jsp" flush="true" />

</body>
</html>