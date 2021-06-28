<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party.model.*"  %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		PartyService partySvc = new PartyService();
		Integer partyHost = 1;
		List<PartyVO> listAll = partySvc.findByPartyHost(partyHost);
		pageContext.setAttribute("listAll", listAll);
%>
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService"/>
<!-- 待動態帶入會員編號 -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>我所舉辦的揪團(待改寫動態帶入會員編號)</title>
	<link rel="stylesheet" href="../share/index.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/partyIHost.css">
</head>

<body>

<jsp:include page="../share/navbar.jsp" flush="true" />

<h4>我所舉辦的揪團(待改寫動態帶入會員編號)</h4>

<section class="party">
<c:if test="${empty listAll}">
	<div style="color:red">您並沒有舉辦任何揪團活動喔!</div>
</c:if>

<%@ include file="page1.file" %>
<c:forEach var="partyVO" items="${listAll}">
	<div class="partyintro">
		<form method="post" action="<%=request.getContextPath()%>/party/party.do">
			<table>
				<tr>
					<td>揪團編號: </td>
					<td>${partyVO.partySN}</td>
				</tr>
				<tr>
					<td>主揪人(之後可刪除): </td>
					<td>${partyVO.partyHost}</td>
				</tr>
				<tr>
					<td>揪團主旨: </td>
					<td>${partyVO.partyTitle}</td>
				</tr>
				<tr>
					<td>揪團狀態: </td>
					<c:if test="${partyVO.status == '0'}">
						<td class="status">熱烈報名中</td>
					</c:if>
					<c:if test="${partyVO.status == '1'}">
						<td class="status">已額滿</td>
					</c:if>
					<c:if test="${partyVO.status == '2'}">
						<td class="status">已結束</td>
					</c:if>
					<c:if test="${partyVO.status == '3'}">
						<td class="status">已取消</td>
					</c:if>
					<c:if test="${partyVO.status == '4'}">
						<td class="status">已成團(仍可報名)</td>
					</c:if>
					<c:if test="${partyVO.status == '5'}">
						<td class="status">已下架</td>
					</c:if>
				</tr>
				<tr>
					<td>最低成團人數: </td>
					<td>${partyVO.partyMinSize}</td>
				</tr>
				<tr>
					<td>已接受人數: </td>
					<td>${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, "1").size()}</td>
				</tr>
				<tr>
					<td>未審核人數: </td>
					<td>${partyMemberSvc.findByPartySNAndStatus(partyVO.partySN, "0").size()}</td>
				</tr>
			</table>
			<div class="next">
				<input type="hidden" name="partySN" value="${partyVO.partySN}">
				<button type="submit" name="action" value="partyIHostDetail" class="btn btn-outline-info btn-sm">查看詳情 / 修改內容 / 審核報名資格</button>
			</div>
		</form>
	</div>
</c:forEach>
<%@ include file="page2.file" %>
</section>

<jsp:include page="../share/footer.jsp" flush="true" />