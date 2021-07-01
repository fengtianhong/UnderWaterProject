<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Integer userID = (Integer) session.getAttribute("userID");
	pageContext.setAttribute("userID", userID);
%>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>報名資訊</title>
    <link rel="stylesheet" href="../share/index.css">
    <link rel="stylesheet" href="css/partyRegister.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<main>
<h4>報名揪團頁面</h4>

<section class="party">
<form action="<%=request.getContextPath()%>/party/party.do" method="post" enctype="multipart/form-data">
	<table>
	<tr>
		<td>欲報名揪團編號: </td> 
		<td>
			${partyVO.partySN}
			<input type="hidden" name="partySN" value="${partyVO.partySN}">
		</td>
	</tr>
	<tr>
		<td>會員: </td>
		<td>
			<input type="text" name="partyMemberName" value="${memberSvc.getone(userID).userName}(${memberSvc.getone(userID).nickName})" readonly>
			<input type="hidden" name="partyMember" value="${userID}" readonly>
		</td>
	</tr>
	<tr>
		<td>性別: </td> 
		<td>
			<select name="gender">
				<option value="0" ${memberSvc.getone(userID).gender == '0'? "selected":""}>男
				<option value="1" ${memberSvc.getone(userID).gender == '1'? "selected":""}>女
			</select>
		</td>
	</tr>
	<tr>
		<td>E-mail: </td>
		<td><input type="text" name="email" value="${memberSvc.getone(userID).account}" maxlength="50"></td>
	</tr>
	<tr>
		<td>手機號碼: </td>
		<td><input type="text" name="phone" value="${memberSvc.getone(userID).phone}" maxlength="10"></td>
	</tr>
	<tr>
		<td>出生年月日(投保用): </td>
		<td><input type="date" name="birthDate" value="${memberSvc.getone(userID).birthDate}"></td>
	</tr>
	<tr>
		<td>身份證字號(投保用): </td>
		<td><input type="text" name="personID" value="${memberSvc.getone(userID).personID}" maxlength="10"></td>
	</tr>
	<tr>
		<td>最高證照: </td>
		<td>
			<select size="" name="certification">
				<option value="0" ${memberSvc.getone(userID).certification == '0'? "selected":""} ${memberSvc.getone(userID).certification == null? "selected":""}>無相關證照
				<option value="1" ${memberSvc.getone(userID).certification == '1'? "selected":""}>PADI OW / SSI OW
				<option value="2" ${memberSvc.getone(userID).certification == '2'? "selected":""}>PADI AOW / SSI AOW
			</select>
		</td>
	</tr>
	<tr>
		<td>請上傳證照翻拍檔案: </td>
		<td>
			<input type="file" id="p_file" name="certificationPic">
			<div id="displayArea">預覽圖</div>
		</td>
	</tr>
	<tr>
		<td>其他備註</td>
		<td><textarea style="width:450px;height:150px;" placeholder="其他備註" name="comment" maxlength="100"></textarea></td>
	</tr>
	</table>
	
	<div class="page">
		<input type="button" onclick="history.back()" class="btn btn-outline-info btn-sm" value="回上頁">
		<button type="submit" name="action" value="submitRegistration" class="btn btn-outline-info btn-sm">提交報名表</button>
	</div>
<!-- 看goBack可否直接動態 -->

</form>
</section>

</main>
<jsp:include page="../share/footer.jsp" flush="true" />

<script>
	document.querySelector('#p_file').addEventListener('change', function() {
		let file = this.files[0];
		let reader = new FileReader();
		reader.readAsDataURL(file);
		reader.addEventListener('load', function() {
			document.querySelector('#displayArea').innerText = "";
        	let html = `<img id="display" src="" style="max-width:200px">`;
    	    document.querySelector('#displayArea').insertAdjacentHTML('beforeend', html);
			document.querySelector('#display').setAttribute('src', reader.result);
		})
	});
		
</script>

</body>
</html>