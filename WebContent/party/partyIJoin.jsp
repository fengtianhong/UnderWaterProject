<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.partymember.model.PartyMemberVO" %>
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService" />
<jsp:useBean id="partySvc" class="com.party.model.PartyService" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
<%
	Integer userID = (Integer) session.getAttribute("userID");
	pageContext.setAttribute("userID", userID);
	
	List<PartyMemberVO> listAll = partyMemberSvc.findByPartyMember(userID);
	pageContext.setAttribute("listAll", listAll);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>我報名的揪團</title>
	<link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/partyIJoin.css">
	    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<main>
<h4>我報名的揪團</h4>
<c:if test="${empty listAll}">
	<section class="alert">您並沒有參加任何揪團活動喔!</section>
</c:if>

<section class="party">
<%@ include file="page1.file" %>
<c:forEach var="partyMemberVO" items="${listAll}">
	<div class="partyintro">
		<form method="post" action="<%=request.getContextPath()%>/party/party.do">
			<table>
				<tr>
					<td>揪團編號: </td>
					<td>${partyMemberVO.partySN}
						<input type="hidden" name=partySN value="${partyMemberVO.partySN}">
					</td>
				</tr>
				<tr>
					<td>主揪人(之後可做點選連結): </td>
					<td>${memberSvc.getone(partySvc.findByPartySN(partyMemberVO.partySN).partyHost).userName}</td>
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
			<button type="submit" name="action" value="partyIJoinDetail" class="btn btn-outline-info btn-sm">查看揪團詳情</button>
		</form>
	</div>
</c:forEach>
<%@ include file="page2.file" %>
</section>
</main>

<jsp:include page="../share/footer.jsp" flush="true" />

</body>
</html>