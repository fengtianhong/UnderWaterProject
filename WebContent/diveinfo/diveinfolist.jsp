<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	DiveInfoService diveinfoSvc = new DiveInfoService();
	List<DiveInfoVO> list = diveinfoSvc.getAll();
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
	text-align: center;
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
	text-align: center;
}
</style>


<html>
<head>
<%@ include file="../share/backend/Bmeta.file"%>
<title>潛點資訊列表</title>
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
			<th>潛點編號</th>
			<th>潛點名稱<hr>經緯度</th>
			<th>簡介</th>
			<th>詳細介紹</th>
			<th>適合季節</th>
			<th>地區</th>
			<th>照片</th>
			<th>評價人數</th>
			<th>評價總分</th>
			<th>狀態</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="diveinfoVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${diveinfoVO.pointSN}</td>
				<td>${diveinfoVO.pointName}<hr>${diveinfoVO.latitude}<br>${diveinfoVO.longitude}</td>
				<td>${diveinfoVO.view}</td>
				<td>${diveinfoVO.introduction}</td>
				<td>${diveinfoVO.season}</td>
				<td>${diveinfoVO.local}</td>
				<td><div class="show_pic">
						<img
							src="<%=request.getContextPath()%>/diveinfo/ShowPic?pointSN=${diveinfoVO.pointSN}">
					</div></td>
				<td>${diveinfoVO.ratePoint}</td>
				<td>${diveinfoVO.ratePeople}</td>
				<td>${diveinfoVO.status}中<br><br>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/diveinfo/diveinfo.do"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary btn-user" type="submit"
							value="${(diveinfoVO.status=='上架')?'下架':'上架'}"> <input
							type="hidden" name="pointSN" value="${diveinfoVO.pointSN}">
						<input type="hidden" name="action" value="changeStatus">
					</FORM><br>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/diveinfo/diveinfo.do"
						style="margin-bottom: 0px;">
						<input class="btn btn-primary btn-user" type="submit" value="修改">
						<input type="hidden" name="pointSN" value="${diveinfoVO.pointSN}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	<%@ include file="../share/backend/Bfooter.file"%>
	<%@ include file="../share/backend/Bjs.file"%>
</body>
</html>