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

<!-- 以下兩個link必須載入用於header,footer -->
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">



<meta charset="UTF-8">
<title>最新消息</title>
<style>
tr:nth-child(even) {
	background: #eee
}

tr:nth-child(odd) {
	background: #FFF
}

.detail {
	background-color: #017f9d;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	transition-duration: 0.4s;
	border: 2px #017f9d solid;
	display: none;
}

.page2btn {
	background-color: #017f9d;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	transition-duration: 0.4s;
	border: 2px #017f9d solid;
}

.page2btn:hover {
	color: #017f9d;
	background-color: #fff;
	border: 2px #017f9d solid;
}

.detail:hover {
	color: #017f9d;
	background-color: #fff;
	border: 2px #017f9d solid;
}

body {
	font-family: Nunito, -apple-system, BlinkMacSystemFont, "Segoe UI",
		Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
		"Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji"
}

.newsContent {
	width: 800px;
	margin: 0 auto;
	background-color: #eee;
	margin-top: 50px;
	border: 2px #ccc solid;
	border-radius: 10px;
	padding-bottom: 70px;
}

.show_pic {
	height: 150px;
	width: 200px;
	overflow: hidden;
}

.show_pic>img {
	cursor: pointer;
	transition-duration: 0.5s;
	height: 100%;
	width: 100%;
	transition-duration: 0.5s;
}

.show_pic>img:hover {
	transform: scale(1.2);
}

#filterType {
	margin-right: 50px;
	margin-top: 5px;
	float: right;
}

.forincludeTop {
	margin: 0 auto;
	width: 200px;
	margin-bottom: 10px;
}

.forincludeBottom {
	float: right;
	margin: 0 auto;
	width: 180px;
	margin-right: 20px;
}

#newsTitle {
	font-size: 28px;
}

textarea {
	border: none;
	resize: none;
	cursor: pointer;
}

.inside {
	text-align: left;
}

tr, td {
	padding: 10px 2px;
	text-align: center;
}

table {
	margin: auto;
	width: 700px;
}
</style>
</head>

<body>
	<jsp:include page="../share/navbar.jsp" flush="true" />

	<div class="newsContent">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/news/news.do" id="showType">
			<select id="filterType" name="newsType"
				onchange="ShowType(this.value)">
				<option value="all" ${(newsTypeS=='all')?"selected":""}>全部</option>
				<option value="divepoint" ${(newsTypeS=='divepoint')?"selected":""}>潛點</option>
				<option value="product" ${(newsTypeS=='product')?"selected":""}>商品</option>
				<option value="party" ${(newsTypeS=='party')?"selected":""}>揪團</option>
			</select>
			<input type="hidden" name="action" value="showType">
		</FORM>
		<table>
			<tr>
				<th colspan="10">最新消息</th>
			</tr>
			<div class="forincludeTop">
				<%@ include file="page1frontend.file"%>
			</div>
			<c:forEach var="newsVO" items="${(listNew==null)?list:listNew}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

				<tr
					class=${newsVO.newsType==0?"divepoint":newsVO.newsType==1?"product":"party"}>
					<td>
					<td><div class="show_pic">
							<img onclick="goDetail(${newsVO.newsSN})"
								src="<%=request.getContextPath()%>/news/ShowPic?newsSN=${newsVO.newsSN}">
						</div></td>

					<td class="inside">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/news/news.do"
							id="${newsVO.newsSN}">
							<a href="javascript:void(0)" onclick="goDetail(${newsVO.newsSN})"
								id="newsTitle">${newsVO.title}</a>
							<%-- 							<a href="<%=request.getContextPath()%>/news/news.do?newsSN=${newsVO.newsSN}&action=getOne_For_Show" --%>
							<%-- 								id="newsTitle">${newsVO.title}</a> --%>

							<br>${newsVO.content.substring(0,50)}....<br>
							<fmt:formatDate value="${newsVO.newsDate}" pattern="yyyy-MM-dd" />
							<br>${newsVO.newsType==0?"潛點":newsVO.newsType==1?"商品":"揪團"}

							<input class="detail" type="submit" value="詳細資訊"> <input
								type="hidden" name="newsSN" value="${newsVO.newsSN}"> <input
								type="hidden" name="action" value="getOne_For_Show">
						</FORM>
					</td>


				</tr>

			</c:forEach>
		</table>
		<div class="forincludeBottom">
			<%@ include file="page2frontend.file"%>
		</div>
	</div>

	<jsp:include page="../share/footer.jsp" flush="true" />
	<script type="text/javascript">
		function goDetail(newsSN) {
			document.getElementById(newsSN).submit();
		}
		function ShowType(filterType) {
			document.getElementById("showType").submit();
		}
	</script>
</body>
</html>