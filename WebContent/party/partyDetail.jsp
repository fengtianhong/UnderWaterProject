<%@ page import="com.party.model.PartyVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.partymember.model.*"%>
<%@ page import="com.party.model.*"%>
<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>揪團詳細內容</title>
</head>
<body>

<h2>揪團詳細內容</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<input type="hidden" name="partySN" value="${partyVO.partySN}">
		<input type="button" onclick="history.back()" value="回上頁">
		<button type="submit" name="action" value="goRegister">報名去(待處理若會員已報名過的判斷)</button>
	</form>
	
	<table>
	<tr>
		<td class="partyHost">揪團編號： </td>
		<td>${partyVO.partySN}</td>
<%-- 		<td>${partyVO.partySN == null? "partyVO1.getPartySN": "partyVO.partySN"}</td> --%>
	</tr>
	<tr>
		<td class="partyHost">主揪人： </td>
		<td>${partyVO.partyHost}</td>
	</tr>
	<tr>
		<td class="partyTitle">揪團主旨: </td>
		<td>${partyVO.partyTitle}</td>
	</tr>
	<tr>
		<td class="date">活動日期: </td>
		<td>${partyVO.startDate}至 ${partyVO.endDate}</td>
	</tr>
	<tr>
		<td class="closeDate">報名截止日期: </td>
		<td>${partyVO.closeDate}</td>
	</tr>
	<tr>
		<td class="partyLocation">揪團潛點: </td>
		<td>${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</td>
	</tr>
	<tr>
		<td class="size">最低成團人數: </td>
		<td>${partyVO.partyMinSize}</td>
	</tr>
	<tr>
		<td class="sizenow">目前報名人數: </td>
		<td>${partyMemberList.size()}</td>
	</tr>
	<tr>
		<td class="partyDetail">詳細內容: </td>
		<td>(CKEditor還沒測)${partyVO.partyDetail}</td>
	</tr>
	</table>
	
<!-- 如果是返回上頁來的(有資料)	 -->
<%-- 		<h2>hello~~${partySN}</h2> --%>

</body>
</html>