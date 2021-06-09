<%@page import="com.party.model.PartyVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.partymember.model.*"%>
<%@ page import="com.party.model.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<input type="hidden" name="action" value="goBack">
		<input type="submit" value="回上頁">
	</form>
	
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<input type="hidden" name="action" value="goRegister">
		<input type="hidden" name="partySN" value="${partyVO.partySN}">
		<input type="submit" value="報名去">
	</form>
	
	<div class="partyShort">
		<div class="partyHost">揪團編號： ${partyVO.partySN}</div>
		<div class="partyHost">主揪人： ${partyVO.partyHost}</div>
		<div class="partyTitle">揪團主旨: ${partyVO.partyTitle}</div>
		<div class="date">活動日期: ${partyVO.startDate}至 ${partyVO.endDate}</div>
		<div class="closeDate">報名截止日期: ${partyVO.closeDate}</div>
		<div class="partyLocation">揪團潛點(待替換): ${partyVO.partyLocation}</div>
		<!--   需補: 等DiveInfoService -->
		<%-- 		<div class="location">${DiveInfoSvc.findByPrimaryKey(partyVO.partyLocation()).getPointName}</div> --%>
		<div class="size">最低成團人數: ${partyVO.partyMinSize}</div>
		<div class="sizenow">目前報名人數: ${partyMemberList.size()}</div>
		<div class="partyDetail">詳細內容: ${partyVO.partyDetail}</div>
		<div>${test}</div>
	</div>

</body>
</html>