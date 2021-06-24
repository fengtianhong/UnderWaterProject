<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.partymember.model.PartyMemberVO" %>
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService" />
<jsp:useBean id="partySvc" class="com.party.model.PartyService" />

<!-- 待改寫動態會員 -->
<%
	List<PartyMemberVO> listAll = partyMemberSvc.findByPartyMember(2);
	pageContext.setAttribute("listAll", listAll);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我報名的揪團(待改寫動態會員)</title>
</head>
<body>

<h2>我報名的揪團(待改寫動態會員)</h2>

<c:if test="${empty listAll}">
	<div style="color:red">您並沒有參加任何揪團活動喔!</div>
</c:if>

<c:forEach var="partyMemberVO" items="${listAll}">
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<table>
			<tr>
				<td>揪團編號: </td>
				<td>${partyMemberVO.partySN}</td>
			</tr>
			<tr>
				<td>主揪人(之後可刪除): </td>
				<td>${partySvc.findByPartySN(partyMemberVO.partySN).partyHost}</td>
			</tr>
			<tr>
				<td>揪團主旨: </td>
				<td>${partySvc.findByPartySN(partyMemberVO.partySN).partyTitle}</td>
			</tr>
			<tr>
				<td>揪團狀態: </td>
				<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '0'}">
					<td class="status">熱烈報名中</td>
				</c:if>
				<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '1'}">
					<td class="status">已額滿</td>
				</c:if>
				<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '2'}">
					<td class="status">已結束</td>
				</c:if>
				<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '3'}">
					<td class="status">已取消</td>
				</c:if>
				<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '4'}">
					<td class="status">已成團(仍可報名)</td>
				</c:if>
				<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '5'}">
					<td class="status">已下架</td>
				</c:if>
			</tr>
			<tr>
				<td>報名狀態: </td>
				<c:if test="${partyMemberVO.status == '0'}">
					<td>尚待審核確認</td>
				</c:if>
				<c:if test="${partyMemberVO.status == '1'}">
					<td>報名成功</td>
				</c:if>
				<c:if test="${partyMemberVO.status == '2'}">
					<td>報名失敗</td>
				</c:if>
			</tr>
		</table>
		<input type="hidden" name="partySN" value="${partyMemberVO.partySN}">
		<button type="submit" name="action" value="partyDetail">查看揪團詳情</button>
	</form>
</c:forEach>


</body>
</html>