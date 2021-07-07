<%@ page import="com.party.model.PartyVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.partymember.model.*"%>
<%@ page import="com.party.model.*"%>
<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>揪團詳細內容</title>
    <link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/partyDetail.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
    <!-- 自己的fontaweson -->
    <script src="https://kit.fontawesome.com/99b24a5611.js" crossorigin="anonymous"></script>

</head>
<body>

<jsp:include page="../share/navbar.jsp" flush="true" />
<main>
<h4>揪團詳細內容</h4>
<c:if test="${not empty errorMsgs}">
	<c:forEach var="error" items="${errorMsgs}">
		<section class="alert">${error}</section>
	</c:forEach>
</c:if>
<section class="party">
	<table>
	<tr>
		<td><i class="fas fa-glass-cheers"></i></td>
		<td class="partyHost">揪團編號 </td>
		<td><sup>PartyNo.</sup>${partyVO.partySN}</td>
	</tr>
	<tr>
		<td><i class="far fa-user-circle"></i></td>
		<td class="partyHost">主揪人 </td>
		<td>${memberSvc.getone(partyVO.partyHost).userName}(${memberSvc.getone(partyVO.partyHost).nickName})</td>
	</tr>
	<tr>
		<td><i class="fas fa-volume-up"></i></td>
		<td class="partyTitle">揪團主旨 </td>
		<td>${partyVO.partyTitle}</td>
	</tr>
	<tr>
		<td><i class="fas fa-calendar-alt"></i></td>
		<td class="date">活動日期 </td>
		<td>${partyVO.startDate}至 ${partyVO.endDate}</td>
	</tr>
	<tr>
		<td><i class="fas fa-exclamation-circle"></i></td>
		<td class="closeDate">報名截止日期 </td>
		<td>${partyVO.closeDate}</td>
	</tr>
	<tr>
		<td><i class="far fa-compass"></i></td>
		<td class="partyLocation">揪團潛點 </td>
		<td>${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</td>
	</tr>
	<tr>
		<td><i class="fas fa-users"></i></td>
		<td class="size">最低成團人數 </td>
		<td>${partyVO.partyMinSize}</td>
	</tr>
	<tr>
		<td><i class="far fa-check-circle"></i></td>
		<td class="sizenow">通過審核人數 </td>
		<td>${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, "1").size()}</td>
	</tr>
	<tr>
		<td><i class="fas fa-info-circle"></i></td>
		<td>詳細內容 </td>
		<td class=""></td>
	</tr>
	</table>
	
	<div class="partyDetail">${partyVO.partyDetail}</div>

	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<input type="hidden" name="partySN" value="${partyVO.partySN}">
		<input type="button" onclick="history.back()" value="回上頁" class="btn btn-outline-info btn-sm">
		<button type="submit" name="action" value="goRegister" class="btn btn-outline-info btn-sm">報名去</button>
	</form>
</section>
</main>

<jsp:include page="../share/footer.jsp" flush="true" />