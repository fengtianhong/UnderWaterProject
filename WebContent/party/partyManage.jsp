<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party.model.*" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PartyService partySvc = new PartyService();
	List<PartyVO> listAll = partySvc.getAll();
	pageContext.setAttribute("listAll", listAll);
%>
<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>揪團後台管理總表</title>
</head>
<body>

<h2>揪團後台管理總表</h2>

<form method="post" action="<%=request.getContextPath()%>/party/party.do">
	<b>輸入揪團編號：</b> 
	<input type="text" name="partySN" placeholder="查詢全部"> 
	<button type="submit" name="action" value="findByPartySN">送出</button>
	<button type="reset">清空</button>
</form>

	<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			<div style="color:red">${message}</div>
		</c:forEach>
	</c:if>
	
	<table>
		<tr>
			<th>揪團編號</th>
			<th>主揪</th>
			<th>揪團主旨</th>
			<th>報名截止日期</th>
			<th>揪團潛點</th>
			<th>揪團狀態</th>
			<th>點選修改內容</th>
		</tr>
		
<!-- ======================== 原始清單 =========================== -->
		<c:if test="${!not empty findByPartySNLike}">
		<c:forEach var="partyVO" items="${listAll}">
		
		<tr>
			<td class="partySN">${partyVO.partySN}</td>
			<td class="partyHost">${partyVO.partyHost}</td>
			<td class="partyTitle">${partyVO.partyTitle}</td>
			<td class="date">${partyVO.closeDate}</td>
			<td class="partyLocation">${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</td>
			<c:if test="${partyVO.status == '0'}">s
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
				<td class="status">活動已下架</td>
			</c:if>
		
			<td>
				<button onclick="location.href='<%=request.getContextPath()%>/party/party.do?action=updateParty&&partySN=${partyVO.partySN}'">修改</button>
			</td>
		</tr>
		</c:forEach>
		</c:if>
	
<!-- 	========================= 搜尋清單   ========================= -->
	
		<c:forEach var="partyVO" items="${findByPartySNLike}">
		<tr>
			<td class="partySN">${partyVO.partySN}</td>
			<td class="partyHost">${partyVO.partyHost}</td>
			<td class="partyTitle">${partyVO.partyTitle}</td>
			<td class="date">${partyVO.closeDate}</td>
			<td class="partyLocation">${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</td>
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
				<td class="status">活動已下架</td>
			</c:if>
			<td>
				<button onclick="location.href='<%=request.getContextPath()%>/party/party.do?action=updateParty&&partySN=${partyVO.partySN}'">修改</button>
			</td>
		</tr>
		</c:forEach>

	</table>


</body>
</html>