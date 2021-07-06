<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"/>
<jsp:useBean id="partyMemberSvc" scope="page" class="com.partymember.model.PartyMemberService" />


<!DOCTYPE html>
<html>
<head>
	<%@ include file="../share/backend/Bmeta.file" %>
	<title>揪團後台修改</title>
	<!-- CKEditor -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/party/ckeditor/ckeditor.js"></script>
	<style>
		section.msg {
			color: red;
			margin-bottom: 5px;
		}
		
	</style>
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>

<h2>揪團後台修改</h2>
<c:forEach var="msg" items="${errorMsgs}">
	<section class="msg">${msg}</section>
</c:forEach>
<form method="post" action="<%=request.getContextPath()%>/party/party.do">
	<table>
		<tr>
			<td class="partySN">揪團編號： </td>
			<td><input type="text" name="partySN" value="${partyVO.partySN}" readonly></td>
		</tr>
		<tr>
			<td class="partyHost">主揪人： </td>
			<td><input type="text" name="partyHost" value="${partyVO.partyHost}" readonly></td>
		</tr>
		<tr>
			<td class="partyTitle">揪團主旨: </td>
			<td><input type="text" name="partyTitle" value="${partyVO.partyTitle}"></td>
		</tr>
		<tr>
			<td class="date">活動日期: </td>
			<td>
				<input type="date" name="startDate" value="${partyVO.startDate}"> 至  <input type="date" name="endDate" value="${partyVO.endDate}">
			</td>
		</tr>
		<tr>
			<td class="regDate">報名開放日期: </td>
			<td><input type="date" name="regDate" value="${partyVO.regDate}"></td>
		</tr>
		<tr>
			<td class="closeDate">報名截止日期: </td>
			<td><input type="date" name="closeDate" value="${partyVO.closeDate}"></td>
		</tr>
		<tr>
			<td class="partyLocation">揪團潛點: </td>
			<td>
				<select size="" name="partyLocation">
				<c:forEach var="diveInfoVO" items="${diveInfoSvc.getAll()}">
					<option value="${diveInfoVO.pointSN}" ${diveInfoVO.pointSN == partyVO.partyLocation ? "selected" : ""}>${diveInfoVO.pointName}
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="partyMinSize">最低成團人數: </td>
			<td><input type="number" min="${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, '1').size()}" max="20" name="partyMinSize" value="${partyVO.partyMinSize}"></td>
		</tr>
		<tr>
			<td class="sizenow">目前已通過審核人數: </td>
			<td>${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, "1").size()}</td>
		</tr>
		<tr>
			<td class="status">揪團狀態: </td>
			<td>
				<select size="" name="status">
					<c:if test="${partyVO.status == '0'}">
						<option value="0" selected}>熱烈報名中
						<option value="2">結束(不再接受報名)
						<option value="3">取消(取消揪團)
						<option value="5">下架此揪團(違反規定)
					</c:if>
					<c:if test="${partyVO.status == '1'}">
						<option value="1" selected>已額滿
						<option value="3">取消(取消揪團)
						<option value="4">已成團(仍可報名)
						<option value="5">下架此揪團(違反規定)
					</c:if>
					<c:if test="${partyVO.status == '2'}">
						<option value="2" selected>已結束
						<option value="5">下架此揪團(違反規定)
					</c:if>
					<c:if test="${partyVO.status == '3'}">
						<option value="3" selected>已取消
						<option value="5">下架此揪團(違反規定)
					</c:if>
					<c:if test="${partyVO.status == '4'}">
						<option value="1">額滿(不接受報名)
						<option value="3">取消(取消揪團)
						<option value="4" selected>已成團(仍可報名)
						<option value="5">下架此揪團(違反規定)
					</c:if>
					<c:if test="${partyVO.status == '5'}">
						<option value="5" selected>已下架
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td class="partyDetail">詳細內容: </td>
		</tr>
	</table>
	<textarea name="partyDetail" maxlength=1000><c:if test="${partyVO != null}">${partyVO.partyDetail}</c:if></textarea>
	<script>
		CKEDITOR.replace("partyDetail");
	</script>
	
	<button type="button" onclick="location.href='<%=request.getContextPath()%>/party/partyManage.jsp'" class="btn btn-primary btn-sm">回上頁(放棄修改)</button>
	<button type="submit" name="action" value="submitUpdate" class="btn btn-primary btn-sm">確認修改</button>
</form>

<%@ include file="../share/backend/Bfooter.file" %>
<%@ include file="../share/backend/Bjs.file" %>

<script>
// HostParty.jsp也有(不完整)
	// 設定活動 開放報名時間最小值: 當下
	$('input[name="regDate"]').attr('min', new Date().toISOString().split("T")[0]);
	
	// 設定活動 開始時間 : 當下隔天
	var today = new Date();
	var minDay = today.getDate() + 1;
	var minMonth = today.getMonth() + 1;
	var minYear = today.getFullYear();
//不完整  還差day==30 or 31 的判斷QQ (month+1的話記得加if)
	if (minDay < 10) {
		minDay ="0" + minDay;
	}
	if (minMonth < 10) {
		minMonth = "0" + minMonth;
	}
	var minStartDate = minYear + "-" + minMonth + "-" + minDay;
	$('input[name="startDate"]').attr('min', minStartDate);

	var maxDay = today.getDate();
	var maxYear = today.getFullYear() + 1 ;
	if (maxDay < 10) {
		maxDay = "0" + maxDay;
	}
	var maxStartDate = maxYear + "-" + minMonth + "-" + maxDay;
	$('input[name="startDate"]').attr('max', maxStartDate);
	
	// 設定活動 結束時間
	$('input[name="endDate"]').on('click', function(){
		var startDate = $('input[name="startDate"]').val();
		$('input[name="endDate"]').attr('min', startDate);
	});

	// 設定活動 結束報名時間: 在活動開始前一天
	$('input[name="closeDate"]').on('click', function(){
		var startDate = $('input[name="startDate"]').val();
		var array1 = startDate.split('-');
		var year = array1[0];
		var month = array1[1];
		var day = array1[2] - 1;
		if (day < 10) {
			day ="0" + day;
		}
		console.log(day);
//不完整  還差day==0的判斷QQ (month-1的話記得加if)
		$('input[name="closeDate"]').attr('max', year + "-" + month + "-" + day);
		$('input[name="closeDate"]').attr('min', new Date().toISOString().split("T")[0]);
	});
</script>
	
</body>
</html>