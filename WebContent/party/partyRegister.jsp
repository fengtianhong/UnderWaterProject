<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>報名資訊</title>
    <link rel="stylesheet" href="../share/index.css">
    <!-- Bootstrap 的 CSS -->
    <link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/partyRegister.css">
</head>

<body>

<jsp:include page="../share/navbar.jsp" flush="true" />

<h4>報名揪團頁面</h4>

<section class="party">
<form action="<%=request.getContextPath()%>/party/party.do" method="post" enctype="multipart/form-data">
	<div>*****需改成動態帶入預設值*****</div>
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
		<td><input type="text" name="partyMember" placeholder="待改" readonly></td>
	</tr>
	<tr>
		<td>性別: </td> 
		<td>
			<select size="" name="gender">
				<option value="0" selected>男
				<option value="1">女
<!-- 需確認Member選項	<option value="2">其他 -->
			</select>
		</td>
	</tr>
	<tr>
		<td>E-mail: </td>
		<td><input type="text" name="email" placeholder="待補" maxlength="50"></td>
	</tr>
	<tr>
		<td>手機號碼: </td>
		<td><input type="text" name="phone" placeholder="待補" maxlength="10"></td>
	</tr>
	<tr>
		<td>出生年月日(投保用): </td>
		<td><input type="date" name="birthDate" placeholder="待補"></td>
	</tr>
	<tr>
		<td>身份證字號(投保用): </td>
		<td><input type="text" name="personID" placeholder="待補" maxlength="10"></td>
	</tr>
	<tr>
		<td>最高證照: </td>
		<td>
			<select size="" name="certification">
				<option value="0">無相關證照(待確認是否有代碼)
				<option value="1">PADI OW / SSI OW
				<option value="2">PADI AOW / SSI AOW
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
		<td><textarea style="width:300px;height:100px;" placeholder="其他備註" name="comment" maxlength="100"></textarea></td>
	</tr>
	</table>
	
	<div class="page">
		<input type="button" onclick="history.back()" class="btn btn-outline-info btn-sm" value="回上頁">
		<button type="submit" name="action" value="submitRegistration" class="btn btn-outline-info btn-sm">提交報名表(未完成)</button>
	</div>
<!-- 看goBack可否直接動態 -->

</form>

</section>

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