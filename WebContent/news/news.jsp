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

#detail {
	background-color: #017f9d;
	color: #fff;
	border-radius: 10px;
	cursor: pointer;
	transition-duration: 0.4s;
	border: 2px #017f9d solid;
}

#detail:hover {
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
	width:800px;
	margin:0 auto;
	background-color: #eee;
	margin-top: 50px;
	border: 2px #ccc solid;
	border-radius: 10px;
}
.filterType{
	margin-top:50px;
}
.forinclude {
	margin: 0 auto;
	width: 190px;
	margin-bottom: 10px;
}

textarea {
	border: none;
	resize: none;
	cursor: pointer;
}

tr, td {
	padding: 10px 2px;
	text-align:center; 
}

table {
	margin: auto;
	width: 700px;
}
</style>
</head>

<body>
	<jsp:include page="../share/navbar.jsp" flush="true" />
	<select id="filterType" name="newsType" onchange="ShowType(this.value)">
		<option value="all">全部</option>
		<option value="divepoint">潛點</option>
		<option value="product">商品</option>
		<option value="party">揪團</option>
	</select>
	<div class="newsContent">
		<table>
			<tr>
				<th>新聞標題</th>
				<th>新聞內容</th>
				<th>上架日期</th>
				<th>新聞類型</th>
				<th>詳細內容</th>
			</tr>
			<div class="forinclude">
				<%@ include file="page1frontend.file"%>
			</div>
			<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr
					class=${newsVO.newsType==0?"divepoint":newsVO.newsType==1?"product":"party"}>
					<td>${newsVO.title.substring(0,10)}....</td>
					<td>${newsVO.content.substring(0,10)}....</td>

					<td><fmt:formatDate value="${newsVO.newsDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${newsVO.newsType==0?"潛點":newsVO.newsType==1?"商品":"揪團"}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/news/news.do"
							style="margin-bottom: 0px;">
							<input id="detail" type="submit" value="詳細資訊"> <input type="hidden"
								name="newsSN" value="${newsVO.newsSN}"> <input
								type="hidden" name="action" value="getOne_For_Show">
						</FORM>
					</td>

				</tr>

			</c:forEach>
		</table>
	</div>
	<%@ include file="page2frontend.file"%>
	<jsp:include page="../share/footer.jsp" flush="true" />
	<script type="text/javascript">
		function ShowType(filterType) {

			// 		$(".product")
			// 		$(".party")

			// 		 var Str=document.getElementById(filterType).value;
			// 		 var divepoint = document.querySelectorAll(".divepoint");
			// 		 var product= document.querySelectorAll(".product");
			// 		 var party= document.querySelectorAll(".party");
			switch (filterType) {
			case 'divepoint':
				$(".divepoint").show();
				$(".product").hide();
				$(".party").hide();
				console.log(filterType);
				break;
			case 'product':
				$(".divepoint").hide();
				$(".product").show();
				$(".party").hide();
				console.log(filterType);
				break;
			case 'party':
				$(".divepoint").hide();
				$(".product").hide();
				$(".party").show();
				console.log(filterType);
				break;
			default:
				$(".divepoint").show();
				$(".product").show();
				$(".party").show();
				console.log(filterType);
			}

		}
	</script>
</body>
</html>