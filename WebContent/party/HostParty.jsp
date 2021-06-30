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
    
    <!-- CKEditor -->
<!-- 	<script src="//cdn.ckeditor.com/4.16.1/full/ckeditor.js"></script> -->
<!-- 	<script src="//cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/party/ckeditor/ckeditor.js"></script>
    
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
				<input type="date" name="startDate"> 至  <input type="date" name="endDate">
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
<!-- 		<tr> -->
<!-- 			<td class="partyDetail">詳細內容(CKEditor還沒測): </td> -->
<!-- 			<td><textarea name="partyDetail" maxlength=1000></textarea></td> -->
<!-- 		</tr> -->
		<tr>
			<td>揪團詳細內容</td>
			<td></td>
		</tr>
	</table>
	<textarea name="partyDetail" maxlength=1000></textarea>
	<script>
		CKEDITOR.replace("partyDetail");
	</script>
	
	<button type="submit" name="action" value="readyToHost" class="btn btn-outline-info btn-sm">確認發起揪團</button>
	<button type="submit" name="action" value="goBackToList" class="btn btn-outline-info btn-sm">返回(揪團列表)</button>
<!-- 待測試 -->
	<button type="button" onclick="history.back()" class="btn btn-outline-info btn-sm">回上頁(待測試button)</button>
</form>
</section>

<jsp:include page="../share/footer.jsp" flush="true" />

<script>
	$(function(){
		// 設定活動 開放報名時間: 當下
		$('input[name="regDate"]').attr('min', new Date().toISOString().split("T")[0]);
		
		// 設定活動 開始時間 : 當下隔天
		var today = new Date();
		var minDay = today.getDate() + 1;
		var minMonth = today.getMonth() + 1;
		var minYear = today.getFullYear();
		
		if (minDay < 10) {
			minDay ="0" + minDay;
		}
		if (minMonth < 10) {
			minMonth = "0" + minMonth;
		}
		var minStartDate = minYear + "-" + minMonth + "-" + minDay;
		$('input[name="startDate"]').attr('min', minStartDate);

		var maxDay = today.getDate();
		var maxYear = today.getFullYear() + 1 ;
		if (maxDay < 10) {
			maxDay = "0" + maxDay;
		}
		var maxStartDate = maxYear + "-" + minMonth + "-" + maxDay;
		$('input[name="startDate"]').attr('max', maxStartDate);
		
	});
	
	// 設定活動 結束時間
	$('input[name="endDate"]').on('click', function(){
		var startDate = $('input[name="startDate"]').val();
		$('input[name="endDate"]').attr('min', startDate);
	})

	// 設定活動 結束報名時間
	$('input[name="closeDate"]').on('click', function(){
		var startDate = $('input[name="startDate"]').val();
		var array1 = startDate.split('-');
		var year = array1[0];
		var month = array1[1];
		var day = array1[2] - 1;
		if (day < 10) {
			day ="0" + day;
		}
		console.log(day);
//不完整  還差day==0的判斷QQ (month-1的話記得加if)
		$('input[name="closeDate"]').attr('max', year + "-" + month + "-" + day);
		$('input[name="closeDate"]').attr('min', new Date().toISOString().split("T")[0]);
	})
		
</script>