<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.partymember.model.*" %>
<jsp:useBean id="diveInfoSvc" class="com.diveinfo.model.DiveInfoService" />
<jsp:useBean id="partyMemberSvc" class="com.partymember.model.PartyMemberService" />
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>我舉辦的揪團 修改內容審核報名資格</title>
</head>
<body>

<h2>我舉辦的揪團: ${partyVO.partySN}</h2>
<h2>修改內容 / 審核報名資格</h2>

<form method="post" action="<%=request.getContextPath()%>/party/party.do">
	<table>
		<tr>
			<td class="partySN">揪團編號： </td>
			<td><input type="text" name="partySN" value="${partyVO.partySN}" readonly></td>
		</tr>
		<tr>
			<td class="partyHost">主揪人： </td>
			<td><input type="text" name="partyHost" value="${partyVO.partyHost}" readonly></td>
		</tr>
		<tr>
			<td class="partyTitle">揪團主旨: </td>
			<td><input type="text" name="partyTitle" value="${partyVO.partyTitle}"></td>
		</tr>
		<tr>
			<td class="date">活動日期: </td>
			<td>
				<input type="date" name="startDate" value="${partyVO.startDate}"> 至  <input type="date" name="endDate" value="${partyVO.endDate}">
			</td>
		</tr>
		<tr>
			<td class="regDate">報名開放日期: </td>
			<td><input type="date" name="regDate" value="${partyVO.regDate}"></td>
		</tr>
		<tr>
			<td class="closeDate">報名截止日期: </td>
			<td><input type="date" name="closeDate" value="${partyVO.closeDate}"></td>
		</tr>
		<tr>
			<td class="partyLocation">揪團潛點: </td>
			<td>
				<select size="" name="partyLocation">
				<c:forEach var="diveInfoVO" items="${diveInfoSvc.getAll()}">
					<option value="${diveInfoVO.pointSN}" ${diveInfoVO.pointSN == partyVO.partyLocation? "selected" : ""}>${diveInfoVO.pointName}
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="partyMinSize">最低成團人數(待修改): </td>
			<td><input type="number" min="${partyMemberSvc.findByPartySN(partyVO.partySN).size()}" max="20" name="partyMinSize" value="${partyVO.partyMinSize}"></td>
		</tr>
		<tr>
			<td class="sizenow">目前已通過審核人數(待修改): </td>
			<td>${partyMemberSvc.findByPartySN(partyVO.partySN).size()}</td>
		</tr>
		<tr>
			<td class="status">揪團狀態: </td>
			<c:if test="${partyVO.status == 0}">
				<td>
					<select size="" name="status">
						<option value="0" selected>熱烈報名中
						<option value="2">結束
						<option value="3">取消
					</select>
				</td>
			</c:if>
			<c:if test="${partyVO.status == 1}">
				<td>
					<select size="" name="status">
						<option value="1" selected>已額滿
						<option value="4">已成團(仍可報名)
					</select>
				</td>
			</c:if>
			<c:if test="${partyVO.status == 2}">
				<td>
					<select size="" name="status">
						<option value="2" selected>已結束
					</select>
				</td>
			</c:if>
			<c:if test="${partyVO.status == 3}">
				<td>
					<select size="" name="status">
						<option value="3" selected>已取消
					</select>
				</td>
			</c:if>
			<c:if test="${partyVO.status == 4}">
				<td>
					<select size="" name="status">
						<option value="1" ${partyVO.status == "1"? "selected": ""}>額滿
						<option value="3" ${partyVO.status == "3"? "selected": ""}>取消
						<option value="4" selected>已成團(仍可報名)
					</select>
				</td>
			</c:if>
			<c:if test="${partyVO.status == 5}">
				<td>
					<select size="" name="status">
						<option value="5" selected>此揪團已被下架
					</select>
				</td>
			</c:if>
		</tr>
		<tr>
			<td class="partyDetail">詳細內容: (CKEditor還沒測) </td>
			<td><textarea name="partyDetail" maxlength=100>${partyVO.partyDetail}</textarea></td>
		</tr>
	</table>
	
	<button type="submit" name="action" value="submitUpdate">確認修改</button>
	<button type="submit" name="action" value="goBackToPartyIHost">返回前頁(待做)</button>
<!-- 待更新goBack功能 -->
</form>

<h2>報名狀況</h2>
<c:if test="${empty partyMembers}">
	<div style="color:red">目前尚無會員報名喔!</div>
</c:if>

<c:if test="${not empty partyMembers}">
	<table>
		<tr>
			<td>報名序號</td>
			<td>報名會員</td>
			<td>性別</td>
			<td>E-mail</td>
			<td>手機</td>
			<td>出生年月日</td>
			<td>身份證字號</td>
			<td>最高證照</td>
			<td>證照圖片</td>
			<td>報名時間(待修)</td>
			<td>其他備註</td>
			<td>報名狀態</td>
			<td>確認後不可修改</td>
		</tr>
	<c:forEach var="partyMember" items="${partyMembers}">
		<form method="post" action="<%=request.getContextPath()%>/party/party.do">
			<tr>
				<td>
					${partyMember.partyMemberSN}
					<input type="hidden" name="partyMemberSN" value="${partyMember.partyMemberSN}">
					<input type="hidden" name="partySN" value="${partyVO.partySN}">
				</td>
				<td>${memberSvc.getone(partyMember.partyMember).userName}</td>
				<td>${partyMember.gender == 0? "男" : "女"}</td>
				<td>${partyMember.email}</td>
				<td>${partyMember.phone}</td>
				<td>${partyMember.birthDate}</td>
				<td>${partyMember.personID}</td>
				<c:if test="${partyMember.certification == 'A1'}">
					<td>PADI OW</td>
				</c:if>
				<c:if test="${partyMember.certification == 'A2'}">
					<td>PADI AOW</td>
				</c:if>
				<c:if test="${partyMember.certification == 'B1'}">
					<td>SSI OW</td>
				</c:if>
				<c:if test="${partyMember.certification == 'B2'}">
					<td>SSI AOW</td>
				</c:if>
				<c:if test="${partyMember.certification == null}">
					<td>無</td>
				</c:if>
				<td><button>(待做)</button></td>
				<td>${partyMember.appliedTime}</td>
				<td>${partyMember.comment}</td>
				<c:if test="${partyMember.status == '0'}">
					<td>
						<select size="" name="status">
								<option value="0" selected>待審核
								<option value="1">審核通過
								<option value="2">審核不通過
						</select>
					</td>
					<td><button type="submit" name="action" value="updatePartyMemberStatus">確認</button></td>
				</c:if>
				<c:if test="${partyMember.status == '1'}">
					<td>審核通過</td>
					<td>
						<button disabled>已確認</button>
					</td>
				</c:if>
				<c:if test="${partyMember.status == '2'}">
					<td>審核不通過</td>
					<td>
						<button disabled>已確認</button>
					</td>
				</c:if>
			</tr>
		</form>
	</c:forEach>
	</table>
</c:if>

</body>
</html>