<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouptour.model.*"%>
<%@ page import="com.diveinfo.model.*"%>


<%
	GroupTourService groupTourSvc = new GroupTourService();
	List<GroupTourVO> list = groupTourSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file" %>
<title>Backend List for Group Tour</title>
<style>
	.container{
		margin: 0 auto;  
 		width: 1000px; 
/* 		display: flex; */
	}
	.card-header{
		padding: 0.7rem 1.25rem 0.5rem !important;
	}
	.text-xs{
		margin-bottom: 0rem !important;
	}
	.card-body{
		padding: 0.5rem 1.25rem !important;
	}
	.title{
		border-bottom: 2px solid #000;
	}
	div.button{
		position: absolute;
    	bottom: 50px;
    	right: 5%;
	}
	#filter{
		position: absolute;
    	right: 14%;
    	top: 90px;
	}
	

</style>
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>
<div class="container">

<!-- <div>	 -->
<h1 class="h3 mb-2 text-gray-800">套裝行程列表</h1>

<select id="filter" class="system" onchange="filter(this.value)">
	<option value="0">ALL
	<option value="1">北部
	<option value="2">南部
	<option value="3">離島
</select>
<!-- </div> -->
<hr>

		<c:forEach var="groupTourVO" items="${list}">
		<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
		<div class="card shadow mb-4 ">
		<input class="forFilter" type="hidden" value="${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).local}">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">${groupTourVO.tourName}</h6>
				<p class="text-xs">${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).pointName}</p>
			</div>
			<div class="card-body">
			<p>行程時間: ${groupTourVO.startTime} ~ ${groupTourVO.endTime}<br>
				報名時間: ${groupTourVO.regTime} ~ ${groupTourVO.closeTime}<br>
				報名人數: ${groupTourVO.attendNumber} / ${groupTourVO.limitNumder}</p>
<!-- 				<p class="text-xs">.text-xs</p> -->
<!-- 				<p class="text-lg mb-0">.text-lg</p> -->
				<div class="button">
					<form method="post" action="grouptour.do">
						<input type="hidden" name="groupTourSN" value="${groupTourVO.groupTourSN}"> 
						<input type="hidden" name="action" value="getOne_ForUpdate"> 
						<input  class="btn btn-primary btn-user" type="submit" value="更新">
					</form>
				</div>
			</div>
		</div>
		</c:forEach>
		
</div>
<!-- 成功新增Msg -->
	<c:if test="${not empty Msg}">
		<script>alert("${Msg}");</script>
	</c:if>

<%@ include file="../share/backend/Bfooter.file" %>
</body>
<%@ include file="../share/backend/Bjs.file" %>
<script>

// 	// 搜索列表
	function filter(value) {
    console.log(value);
		switch(value) {
			case "1":
				$(".forFilter").each(function(i, e) {
                    if($(e).val()=="北部") {
                        $(e).closest("div").show();
                    }else{
                        $(e).closest("div").hide();
                    }
				})
				break;
			case "2":
                 $(".forFilter").each(function(i, e) {
                    if($(e).val()=="南部") {
                        $(e).closest("div").show();
                    }else{
                        $(e).closest("div").hide();
                    }
				})
				break;
			case "3":
                $(".forFilter").each(function(i, e) {
                    if($(e).val()=="離島") {
                        $(e).closest("div").show();
                    }else{
                        $(e).closest("div").hide();
                    }
				})
				break;
			default:
                $(".forFilter").closest("div").show();
				break;
		}
	}

</script>
</html>