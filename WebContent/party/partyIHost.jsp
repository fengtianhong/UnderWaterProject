<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ page import="com.party.model.*"  %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		PartyService partySvc = new PartyService();
		Integer partyHost = 1;
		List<PartyVO> listAll = partySvc.findByPartyHost(partyHost);
		pageContext.setAttribute("listAll", listAll);
%>
<!-- 待動態帶入會員編號 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>我所舉辦的揪團(待改寫動態帶入會員編號)</title>

<style>
.partyShort {
	background-color: lightgrey;
	width: 500px;
	margin: 10px auto;
}
</style>
</head>
<body>

<h2>我所舉辦的揪團(待改寫動態帶入會員編號)</h2>

<c:if test="${empty listAll}">
	<div style="color:red">您並沒有舉辦任何揪團活動喔!</div>
</c:if>

<c:forEach var="partyVO" items="${listAll}">
	<div class="partyShort">
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
					<td>待審核人數(待完善): </td>
					<td>partyMemberSvc.xxxxxx</td>
				</tr>
			</table>
			<input type="hidden" name="partySN" value="${partyVO.partySN}">
			<button type="submit" name="action" value="partyIHostDetail">查看詳情 / 修改內容 / 審核報名資格</button>
		</form>
	</div>
</c:forEach>

</body>
</html>