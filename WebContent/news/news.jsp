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
<title>最新消息的啦</title>
<style>

.newsContent {
	margin-top: 100px;
}

.forinclude {
	margin: 0 auto;
	width: 300px;
}

textarea {
	border: none;
	resize: none;
	cursor: pointer;
}

table {
	margin: auto;
	width: 800px;
}
</style>
</head>

<body>
	<jsp:include page="../share/navbar.jsp" flush="true" />
	<div class="newsContent">
		<table>
			<tr>
				<th>新聞標題</th>
				<th>新聞內容</th>
				<th>上架日期</th>
				<th>消息來源</th>
				<th>新聞類型</th>
				<th>詳細內容</th>
			</tr>
			<div class="forinclude">
				<%@ include file="page1frontend.file"%>
			</div>
			<c:forEach var="newsVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${newsVO.title}</td>
					<td><textarea readonly rows="6" cols="40">${newsVO.content}</textarea></td>

					<td><fmt:formatDate value="${newsVO.newsDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${newsVO.newsFrom}</td>
					<td>${newsVO.newsType==0?"潛點":newsVO.newsType==1?"商品":"揪團"}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/news/news.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="詳細資訊"> <input type="hidden"
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
</body>
</html>