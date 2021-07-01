<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party.model.*"  %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		Integer userID = (Integer) session.getAttribute("userID");
		pageContext.setAttribute("userID", userID);

		PartyService partySvc = new PartyService();
		List<PartyVO> listAll = partySvc.findByPartyHost(userID);
		pageContext.setAttribute("listAll", listAll);
%>
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>我所舉辦的揪團</title>
	<link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/partyIHost.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
	<!-- Favicon-->
<!-- 	<link rel="icon" type="image/x-icon" href="../vendor/favicon.ico" /> -->
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<main>
<h4>我所舉辦的揪團</h4>

<c:if test="${empty listAll}">
	<section class="alert">您並沒有舉辦任何揪團活動喔!</section>
</c:if>

<section class="party">
	<%@ include file="page1.file" %>
		<c:forEach var="partyVO" items="${listAll}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
								<td><span class="badge badge-info">熱烈報名中</span></td>
							</c:if>
							<c:if test="${partyVO.status == '1'}">
								<td><span class="badge badge-secondary">已額滿</span></td>
							</c:if>
							<c:if test="${partyVO.status == '2'}">
								<td><span class="badge badge-secondary">揪團已結束</span></td>
							</c:if>
							<c:if test="${partyVO.status == '3'}">
								<td><span class="badge badge-secondary">已取消</span></td>
							</c:if>
							<c:if test="${partyVO.status == '4'}">
								<td><span class="badge badge-info">已成團(仍可報名)</span></td>
							</c:if>
							<c:if test="${partyVO.status == '5'}">
								<td><span class="badge badge-secondary">已下架</span></td>
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
</main>

<jsp:include page="../share/footer.jsp" flush="true" />

</body>
</html>