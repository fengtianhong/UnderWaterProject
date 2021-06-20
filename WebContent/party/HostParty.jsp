<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="diveInfoSvc" class="com.diveinfo.model.DiveInfoService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>發起揪團</title>
</head>
<body>

<h2>發起揪團</h2>

<form method="post" action="<%=request.getContextPath()%>/party/party.do">
	<table>
		<tr>
			<td>主揪人: </td>
			<td><input type="text" name="partyHost" value="5" readonly> (待替換動態帶入)</td>
		</tr>
		<tr>
			<td class="partyTitle">揪團主旨: </td>
			<td><input type="text" name="partyTitle" maxlength="20"></td>
		</tr>
		<tr>
			<td class="date">活動日期: </td>
			<td>
				<input type="date" name="startDate" > 至  <input type="date" name="endDate">
			</td>
		</tr>
		<tr>
			<td class="regDate">報名開放日期: </td>
			<td><input type="date" name="regDate"></td>
		</tr>
		<tr>
			<td class="closeDate">報名截止日期: </td>
			<td><input type="date" name="closeDate"></td>
		</tr>
		<tr>
			<td class="partyLocation">揪團潛點: </td>
			<td>
				<select size="" name="partyLocation">
				<c:forEach var="diveInfoVO" items="${diveInfoSvc.getAll()}">
					<option value="${diveInfoVO.pointSN}">${diveInfoVO.pointName}
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="partyMinSize">最低成團人數: </td>
			<td><input type="number" min="1" max="20" name="partyMinSize"></td>
		</tr>
		<tr>
			<td class="partyDetail">詳細內容(CKEditor還沒測): </td>
			<td><textarea name="partyDetail" maxlength=1000></textarea></td>
		</tr>
	</table>
	
	<button type="submit" name="action" value="readyToHost">確認發起揪團</button>
	<button type="submit" name="action" value="goBackToList">返回(揪團列表)</button> 
	
</form>

</body>
</html>