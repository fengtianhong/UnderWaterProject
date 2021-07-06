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
	 <!-- 自己的fontaweson -->
    <script src="https://kit.fontawesome.com/99b24a5611.js" crossorigin="anonymous"></script>

</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />
<jsp:include page="../share/member/Mheader.jsp" flush="true" />

<div class="option">
	<button type="button" class="btn btn-warning btn-sm" disabled>已報名揪團查詢</button>
	<button type="button" class="btn btn-warning btn-sm" onclick="location.href='<%=request.getContextPath()%>/party/partyIHost.jsp'">主揪活動管理</button>
	<button type="button" class="btn btn-warning btn-sm" onclick="location.href='<%=request.getContextPath()%>/party/party.do?action=party'">回揪團總列表</button>
</div>
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
					<td><i class="fas fa-glass-cheers"></i></td>
					<td>揪團編號: </td>
					<td>${partyMemberVO.partySN}
						<input type="hidden" name=partySN value="${partyMemberVO.partySN}">
					</td>
				</tr>
				<tr>
					<td><i class="far fa-user-circle"></i></td>
					<td>主揪人(之後可做點選連結): </td>
					<td>${memberSvc.getone(partySvc.findByPartySN(partyMemberVO.partySN).partyHost).userName}</td>
				</tr>
				<tr>
					<td><i class="fas fa-volume-up"></i></td>
					<td>揪團主旨: </td>
					<td>${partySvc.findByPartySN(partyMemberVO.partySN).partyTitle}</td>
				</tr>
				<tr>
					<td><i class="fas fa-info-circle"></i></td>
					<td>揪團狀態: </td>
					<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '0'}">
						<td><span class="badge badge-info">熱烈報名中</span></td>
					</c:if>
					<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '1'}">
						<td><span class="badge badge-secondary">已額滿</span></td>
					</c:if>
					<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '2'}">
						<td><span class="badge badge-secondary">已結束</span></td>
					</c:if>
					<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '3'}">
						<td><span class="badge badge-secondary">已取消</span></td>
					</c:if>
					<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '4'}">
						<td><span class="badge badge-info">已成團(仍可報名)</span></td>
					</c:if>
					<c:if test="${partySvc.findByPartySN(partyMemberVO.partySN).status == '5'}">
						<td><span class="badge badge-secondary">已下架</span></td>
					</c:if>
				</tr>
				<tr>
					<td><i class="fas fa-exclamation-circle"></i></td>
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
			<div class="next">
				<input type="hidden" name="partySN" value="${partyMemberVO.partySN}">
				<button type="submit" name="action" value="partyIJoinDetail" class="btn btn-outline-info btn-sm">查看揪團詳情</button>
			</div>
		</form>
	</div>
</c:forEach>
<%@ include file="page2.file" %>
</section>

<jsp:include page="../share/member/Mfooter.html" flush="true" />
<jsp:include page="../share/footer.jsp" flush="true" />
<script>
	$(function(){
		$('a#party').addClass('active');
	})
</script>

</body>
</html>