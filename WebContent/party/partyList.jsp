<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.party.model.*"%>
<%@ page import="java.util.*"%>
<%
		PartyService partySvc = new PartyService();
		List<PartyVO> listAll = partySvc.getAll();
		
		if (session.getAttribute("listBySearch") != null) {
			listAll = (List<PartyVO>) session.getAttribute("listBySearch");
		}
		
		pageContext.setAttribute("listAll", listAll);
%>

<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService" />

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>揪團列表</title>
	
    <link rel="stylesheet" href="../share/index.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/partyList.css">
</head>

<body>

<jsp:include page="../share/navbar.jsp" flush="true" />

<h4>揪團列表</h4>
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<section class="search">
			<span>關鍵字：</span> 
			<input type="text" name="keyword" placeholder="查詢全部" maxlength="5" size="10"> 
			<span>潛點區域：</span >
			<select size="" name="pointSN">
				<option value="2">查詢全部
				<c:forEach var="diveInfoVO" items="${diveInfoSvc.getAll()}">
					<option value="${diveInfoVO.pointSN}">${diveInfoVO.pointName}
				</c:forEach>
			</select>
			<span>最低人數：</span> 
			<select size="" name="partyMinSize">
				<option value="0">查詢全部
				<c:forEach var="number" begin="2" end="10">
					<option value="${number}">${number}
				</c:forEach>
			</select> 
			<button type="submit" class="btn btn-outline-light btn-sm" name="action" value="getAllBy">送出</button>
			<button type="reset" class="btn btn-outline-light btn-sm">清空</button>
			<button class="btn btn-outline-light btn-sm" onclick="location.href='<%=request.getContextPath()%>/party/HostParty.jsp'">發起揪團</button>
		</section>
	</form>
	
	<c:if test="${not empty errorMsgs}">
		<section class="alert">查無此條件資料，請重新查詢！</section>
	</c:if>
	
		<%-- <form method="post" action="<%=request.getContextPath()%>/party/party.do"> --%>
		<!-- 	<b>選擇揪團月份：</b> -->
		<!-- 	<select size="" name=""> -->
		<%-- 		<c:forEach var="number" begin="1" end="10"> --%>
		<%-- 			<option value="number">${number} --%>
		<%-- 		</c:forEach> --%>
		<!-- 	</select> -->
		<!--     <input type="hidden" name="action" value="getAllByDate"> -->
		<!--     <input type="submit" value="送出"> -->
		<!-- </form> -->

	<section class="party">
	<%@ include file="page1.file" %>
		<c:forEach var="partyVO" items="${listAll}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr><td>
			<div class="partyintro">
			<form method="post" action="<%=request.getContextPath()%>/party/party.do">
				<div class="partySN">揪團編號: <sup>PartyNo.</sup>${partyVO.partySN}</div>
				<div class="partyHost">主揪人: ${partyVO.partyHost}</div>
				<div class="partyTitle">揪團主旨: ${partyVO.partyTitle}</div>
				<div class="date">活動日期: ${partyVO.startDate} 至 ${partyVO.endDate}</div>
				<div class="partyLocation">揪團潛點: ${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</div>
				<div class="size">最低成團人數: ${partyVO.partyMinSize}</div>
				<c:if test="${partyVO.status == '0'}">
					<div class="status">
						<span class="badge badge-info">熱烈報名中</span>
						<input type="hidden" name="partySN" value="${partyVO.partySN}">
						<input type="hidden" name="listBySearch" value="${listBySearch}">
						<button type="submit" name="action" value="partyDetail" class="btn btn-outline-info btn-sm">詳細資訊</button>
						<button type="submit" name="action" value="goRegister" class="btn btn-outline-info btn-sm">立即報名</button>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '1'}">
					<div class="status">
						<span class="badge badge-secondary">活動已額滿</span>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '2'}">
					<div class="status">
						<span class="badge badge-secondary">活動已結束</span>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '3'}">
					<div class="status">
						<span class="badge badge-secondary">活動已取消</span>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '4'}">
					<div class="status">
						<span class="badge badge-info">已成團(仍可報名)</span>
						<input type="hidden" name="partySN" value="${partyVO.partySN}">
						<input type="hidden" name="listBySearch" value="${listBySearch}">
						<button type="submit" name="action" value="partyDetail" class="btn btn-outline-info btn-sm">詳細資訊</button>
						<button type="submit" name="action" value="goRegister" class="btn btn-outline-info btn-sm">立即報名</button>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '5'}">
					<div class="status">
						<span class="badge badge-secondary">活動已下架</span>
					</div>
				</c:if>
			</form>
			</div>
			</td></tr>
		</c:forEach>
	<%@ include file="page2.file" %>
	</section>

<jsp:include page="../share/footer.jsp" flush="true" />
