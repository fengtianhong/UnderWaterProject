<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouptour.model.*"%>
<%@ page import="com.diveinfo.model.*"%>


<%
	GroupTourService groupTourSvc = new GroupTourService();
	List<GroupTourVO> list = groupTourSvc.getAll();
	pageContext.setAttribute("list", list);	// WHY
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Backend List for Group Tour</title>
<style>
	table{
	  border: solid 1px black;
	  border-collapse: collapse;
	  border-radius: 20%;
	  width: 80%;
	}
	.tourlist{
		margin: 20px;
		
		weight: 60%;
	}
	.title{
		border-bottom: 2px solid #000;
	}

</style>
</head>
<body>

	<h3>建立新行程 <a href='addGT.jsp'> +</a></h3>
	
	<h3>套裝行程列表</h3>
	<input type="search" class="light-table-filter" data-table="order-table" placeholder="請輸入關鍵字">
	<c:forEach var="groupTourVO" items="${list}">
		<table class="tourlist order-table">
			<tr>
				<td class="tour">
					<div class="title">${groupTourVO.tourName}</div>
					<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
					<div class="pointname">${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).pointName}</div>
					
					<div class="tourTime">行程時間: ${groupTourVO.startTime} ~ ${groupTourVO.endTime}</div>
					<div class="regTime">報名時間: ${groupTourVO.regTime} ~ ${groupTourVO.closeTime}</div>
					<div class="number">報名人數: ${groupTourVO.attendNumber} / ${groupTourVO.limitNumder}</div>
				</td>
				<td class="button">
					<form method="post" action="grouptour.do">
						<input type="hidden" name="groupTourSN" value="${groupTourVO.groupTourSN}"> 
						<input type="hidden" name="action" value="getOne_ForUpdate"> 
						<input type="submit" value="修改">
					</form>
				</td>
			</tr>
		</table>
	</c:forEach>
	<c:if test="${not empty Msg}">
		<script>alert("${Msg}");</script>
	</c:if>

</body>
<script>
	// 輕量關鍵字搜索列表
	!function(e){"use strict";var a,n,t=(a=Array.prototype,{init:function(){var t=e.getElementsByClassName("light-table-filter");a.forEach.call(t,function(t){t.oninput=o})}});function o(t){n=t.target;t=e.getElementsByClassName(n.getAttribute("data-table"));a.forEach.call(t,function(t){a.forEach.call(t.tBodies,function(t){a.forEach.call(t.rows,i)})})}function i(t){var e=t.textContent.toLowerCase(),a=n.value.toLowerCase();t.style.display=-1===e.indexOf(a)?"none":"table-row"}e.addEventListener("readystatechange",function(){"complete"===e.readyState&&t.init()})}(document);
	
</script>
</html>