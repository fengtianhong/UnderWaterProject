<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="diveInfoSvc" class="com.diveinfo.model.DiveInfoService" />
<!-- 需動態帶入(發起揪團)會員資料 -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>發起揪團</title>
	
    <link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/hostParty.css">
     <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<!--     <link rel="stylesheet" href="css/partyDetail.css"> -->
    
</head>
<body>

<jsp:include page="../share/navbar.jsp" flush="true" />

<h4>發起揪團</h4>

<section class="party">
<form method="post" action="<%=request.getContextPath()%>/party/party.do">
	<table>
		<tr>
			<td>主揪人: </td>
			<td><input type="text" name="partyHost" value="1" readonly> (待替換動態帶入)</td>
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
	
	<button type="submit" name="action" value="readyToHost" class="btn btn-outline-info btn-sm">確認發起揪團</button>
	<button type="submit" name="action" value="goBackToList" class="btn btn-outline-info btn-sm">返回(揪團列表)</button>
<!-- 待測試 -->
	<button type="button" onclick="history.back()" class="btn btn-outline-info btn-sm">回上頁(待測試button)</button>
</form>
</section>

<jsp:include page="../share/footer.jsp" flush="true" />