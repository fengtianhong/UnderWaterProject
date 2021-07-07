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
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>揪團列表</title>
    <link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/partyList.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
     <!-- 自己的fontaweson -->
    <script src="https://kit.fontawesome.com/99b24a5611.js" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<main>
<h4>揪團列表</h4>
	<form method="post" action="<%=request.getContextPath()%>/party/party.do">
		<section class="search">
			<span>搜尋揪團編號或主旨：</span> 
			<input type="text" name="keyword" placeholder="查詢全部" maxlength="6" size="10"> 
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
			<button type="button" class="btn btn-outline-light btn-sm" onclick="location.href='<%=request.getContextPath()%>/party/HostParty.jsp'">發起揪團</button>
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
			<div class="partyintro">
				<table>
					<tr>
						<td><i class="fas fa-glass-cheers"></i></td>
						<td>揪團編號 </td>
						<td><sup>PartyNo.</sup>${partyVO.partySN}</td>
					</tr>
					<tr>
						<td><i class="far fa-user-circle"></i></td>
						<td>主揪人</td>
						<td>${memberSvc.getone(partyVO.partyHost).userName}(${memberSvc.getone(partyVO.partyHost).nickName})</td>
					</tr>
					<tr>
						<td><i class="fas fa-volume-up"></i></td>
						<td>揪團主旨</td>
						<td>${partyVO.partyTitle}</td>
					</tr>
					<tr>
						<td><i class="fas fa-calendar-alt"></i></td>
						<td>活動日期</td>
						<td>${partyVO.startDate} 至 ${partyVO.endDate}</td>
					</tr>
					<tr>
						<td><i class="fas fa-exclamation-circle"></i></td>
						<td>報名截止</td>
						<td>${partyVO.closeDate}</td>
					</tr>
					<tr>
						<td><i class="far fa-compass"></i></td>
						<td>揪團潛點</td>
						<td>${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</td>
					</tr>
					<tr>
						<td><i class="fas fa-users"></i></td>
						<td>最低人數</td>
						<td>${partyVO.partyMinSize}</td>
					</tr>
				</table>
				<c:if test="${partyVO.status == '0'}">
					<form method="post" action="<%=request.getContextPath()%>/party/party.do">
						<div class="status">
							<span class="badge badge-info">熱烈報名中</span>
							<input type="hidden" name="partySN" value="${partyVO.partySN}">
							<input type="hidden" name="listBySearch" value="${listBySearch}">
							<button type="submit" name="action" value="partyDetail" class="btn btn-outline-info btn-sm">詳細資訊</button>
							<button type="submit" name="action" value="goRegister" class="btn btn-outline-info btn-sm">立即報名</button>
						</div>
					</form>
				</c:if>
				<c:if test="${partyVO.status == '1'}">
						<div class="status">
							<span class="badge badge-secondary">揪團已額滿</span>
						</div>
				</c:if>
				<c:if test="${partyVO.status == '2'}">
					<div class="status">
						<span class="badge badge-secondary">揪團已結束</span>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '3'}">
					<div class="status">
						<span class="badge badge-secondary">揪團已取消</span>
					</div>
				</c:if>
				<c:if test="${partyVO.status == '4'}">
					<form method="post" action="<%=request.getContextPath()%>/party/party.do">
						<div class="status">
							<span class="badge badge-info">已成團(仍可報名)</span>
							<input type="hidden" name="partySN" value="${partyVO.partySN}">
							<input type="hidden" name="listBySearch" value="${listBySearch}">
							<button type="submit" name="action" value="partyDetail" class="btn btn-outline-info btn-sm">詳細資訊</button>
							<button type="submit" name="action" value="goRegister" class="btn btn-outline-info btn-sm">立即報名</button>
						</div>
					</form>
				</c:if>
				<c:if test="${partyVO.status == '5'}">
					<div class="status">
						<span class="badge badge-secondary">揪團已下架</span>
					</div>
				</c:if>
			</div>
		</c:forEach>
	<%@ include file="page2.file" %>
	</section>
</main>

<jsp:include page="../share/footer.jsp" flush="true" />

</body>
</html>