<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.party.model.*"%>
<%@ page import="java.util.*"%>

<%
		PartyService partySvc = new PartyService();
		List<PartyVO> listAll = partySvc.getAll();
		pageContext.setAttribute("listAll", listAll);
%>
<!-- 需補: 等DiveInfoService(下方還有) -->


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>揪團列表</title>

<style>
.partyShort {
	background-color: lightgrey;
	width: 500px;
	margin: 10px auto;
}
</style>
</head>

<body>

	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<b>輸入關鍵字：</b> 
		<input type="text" name="keyword" placeholder="查詢全部"> 
		<b>選擇潛點區域：</b> 
	<!--   需補: 等DiveInfoService -->
		<select size="" name="pointSN">
			<option value="2">查詢全部
			<c:forEach var="partyVO" items="${listAll}">
				<option value="${partyVO.partyLocation}">${partyVO.partyLocation}
			</c:forEach>
		</select>
		<b>選擇最低需求人數：</b> 
		<select size="" name="partyMinSize">
			<option value="0">查詢全部
			<c:forEach var="number" begin="1" end="10">
				<option value="${number}">${number}
			</c:forEach>
		</select> 
		<input type="hidden" name="action" value="getAllBy">
		<input type="submit" value="送出">
		<input type="reset" value="清空">
	</form>
	
	<c:if test="${listBySearch.size() == 0}">
		<font style="color:red">查無此條件資料，請重新查詢！</font>
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
	<h3>揪團列表</h3>
	
	<c:forEach var="partyVO" items="${listBySearch}">
		<div class="partyShort">
		<form method="post" action="<%=request.getContextPath()%>/party/party.do">
			<div class="partyHost">揪團編號： ${partyVO.partySN}</div>
			<div class="partyHost">主揪人： ${partyVO.partyHost}</div>
			<div class="partyTitle">揪團主旨: ${partyVO.partyTitle}</div>
			<div class="date">活動日期: ${partyVO.startDate}至 ${partyVO.endDate}</div>
			<div class="partyLocation">揪團潛點(待替換): ${partyVO.partyLocation}</div>
			<!--   需補: 等DiveInfoService -->
			<%-- 		<div class="location">${DiveInfoSvc.findByPrimaryKey(partyVO.partyLocation()).getPointName}</div> --%>
			<div class="size">${partyVO.partyMinSize}</div>
				<c:if test="${partyVO.status == '0'}">
					<div class="status">
						<button type="button">熱烈報名中</button>	
							<input type="hidden" name="partySN" value="${partyVO.partySN}">
							<input type="hidden" name="listBySearch" value="${listBySearch}">
							<button type="submit" name="action" value="partyDetail">詳細資訊</button>
							<button type="submit" name="action" value="goRegister">立即報名</button>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '1'}">
					<div class="status">已額滿</div>
				</c:if>
				<c:if test="${partyVO.status == '2'}">
					<div class="status">已結束</div>
				</c:if>
				<c:if test="${partyVO.status == '3'}">
					<div class="status">已取消</div>
				</c:if>
				<c:if test="${partyVO.status == '4'}">
					<div class="status">已成團(仍可報名)</div>
<!-- 			這邊也要改 -->
					<buttom>報名(待改)</buttom>
				</c:if>
				<c:if test="${partyVO.status == '5'}">
					<div class="status">活動已下架</div>
				</c:if>
		</form>
		</div>
	</c:forEach>


		<!-- ======================== 原始清單 =========================== -->
		
	
	<c:if test="${!not empty listBySearch}">
		<c:forEach var="partyVO" items="${listAll}">
			<div class="partyShort">
			<form method="post" action="<%=request.getContextPath()%>/party/party.do">
				<div class="partySN">揪團編號: ${partyVO.partySN}</div>
				<div class="partyHost">主揪人: ${partyVO.partyHost}</div>
				<div class="partyTitle">揪團主旨: ${partyVO.partyTitle}</div>
				<div class="date">活動日期: ${partyVO.startDate} 至 ${partyVO.endDate}</div>
				<div class="partyLocation">揪團潛點(待替換): ${partyVO.partyLocation}</div>
				<!--   需補: 等DiveInfoService -->
				<%-- 		<div class="location">${DiveInfoSvc.findByPrimaryKey(partyVO.partyLocation()).getPointName}</div> --%>
				<div class="size">最低成團人數: ${partyVO.partyMinSize}</div>
				<c:if test="${partyVO.status == '0'}">
					<div class="status">
						<button type="button">熱烈報名中</button>	
							<input type="hidden" name="partySN" value="${partyVO.partySN}">
							<input type="hidden" name="action" value="partyDetail">
							<input type="submit" value="詳細資訊">
<%-- 						<form method="post" action="<%=request.getContextPath()%>/party/party.do"> --%>
<!-- 							<input type="hidden" name="action" value="partyRegister"> -->
<!-- 							<input type="submit" value="立即報名"> -->
<!-- 						</form> -->
					</div>
				</c:if>
				<c:if test="${partyVO.status == '1'}">
					<div class="status">已額滿</div>
				</c:if>
				<c:if test="${partyVO.status == '2'}">
					<div class="status">已結束</div>
				</c:if>
				<c:if test="${partyVO.status == '3'}">
					<div class="status">已取消</div>
				</c:if>
				<c:if test="${partyVO.status == '4'}">
					<div class="status">已成團(仍可報名)</div>
	<!-- 			這邊也要改 -->
					<buttom>報名(待改)</buttom>
				</c:if>
				<c:if test="${partyVO.status == '5'}">
					<div class="status">活動已下架</div>
				</c:if>
			</form>
			</div>
		</c:forEach>
	</c:if>
	
</body>
</html>