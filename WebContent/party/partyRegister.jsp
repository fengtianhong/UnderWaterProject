<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<c:forEach var="msg" items="${errorMsgs}">
	<section class="msg">${msg}</section>
</c:forEach>

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
			${memberSvc.getone(userID).userName}(${memberSvc.getone(userID).nickName})
			<input type="hidden" name="partyMember" value="${userID}" readonly>
		</td>
	</tr>
	<tr>
		<td>性別: </td> 
		<td>
			${memberSvc.getone(userID).gender == '0'? "男":"女"}
			<input type="hidden" name="gender" value="${memberSvc.getone(userID).gender}">
		</td>
	</tr>
	<tr>
		<td>E-mail: </td>
<%-- 		<td><input type="email" name="email" value="${partyMemberVO.account}" maxlength="50"></td> --%>
		<td><input type="email" name="email" value="${partyMemberVO == null? memberSvc.getone(userID).account : partyMemberVO.email}" maxlength="50"></td>
<%-- 		<td><input type="email" name="email" value="${partyMemberVO.email}" maxlength="50"></td> --%>
	</tr>
	<tr>
		<td>手機號碼: </td>
		<td><input type="tel" pattern="[0][9][0-9]{8}" name="phone" value="${partyMemberVO == null? memberSvc.getone(userID).phone : partyMemberVO.phone}" maxlength="10"></td>
	</tr>
	<tr>
		<td>出生年月日(選填): </td>
		<td><input type="date" name="birthDate" value="${partyMemberVO == null? memberSvc.getone(userID).birthDate : partyMemberVO.birthDate}"></td>
	</tr>
	<tr>
		<td>身份證字號(選填): </td>
		<td><input type="text" name="personID" value="${partyMemberVO == null? memberSvc.getone(userID).personID : partyMemberVO.personID}" maxlength="10"></td>
	</tr>
	<tr>
		<td>最高證照: </td>
		<td>
			<c:if test="${partyMemberVO == null}">
				<select size="" name="certification">
					<option value="0" ${memberSvc.getone(userID).certification == '0'? "selected":""}>無相關證照
					<option value="1" ${memberSvc.getone(userID).certification == '1'? "selected":""}>PADI OW / SSI OW
					<option value="2" ${memberSvc.getone(userID).certification == '2'? "selected":""}>PADI AOW / SSI AOW
				</select>
			</c:if>
			<c:if test="${partyMemberVO != null}">
				<select size="" name="certification">
					<option value="0" ${partyMemberVO.certification == '0'? "selected":""}>無相關證照
					<option value="1" ${partyMemberVO.certification == '1'? "selected":""}>PADI OW / SSI OW
					<option value="2" ${partyMemberVO.certification == '2'? "selected":""}>PADI AOW / SSI AOW
				</select>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>請上傳證照翻拍檔案<br>(最大2MB): </td>
		<td>
			<input type="file" id="p_file" name="certificationPic">
			<div id="displayArea">預覽圖</div>
			<c:if test="${partyMemberVO != null}">
			</c:if>
		</td>
	</tr>
	<tr>
		<td>其他備註</td>
		<td><textarea style="width:450px;height:150px;" placeholder="其他備註" name="comment" maxlength="100">${partyMemberVO == null? "" : partyMemberVO.comment}</textarea></td>
	</tr>
	</table>
	
	<div class="page">
		<input type="button" onclick="history.back()" class="btn btn-outline-info btn-sm" id="goBack" value="回上頁">
		<button type="submit" name="action" value="submitRegistration" id="submitRegistration" class="btn btn-outline-info btn-sm">提交報名表</button>
	</div>

</form>
</section>

</main>
<jsp:include page="../share/footer.jsp" flush="true" />

<script>

	// 確認接受平台規範
	$(function() {
		let msg = ``;
		if (!confirm("提醒您, 本網站僅提供會員一個自由發起揪團與參加揪團的交流平台。   請會員務必小心詐騙，並謹慎提供個人資料。接受本平台條款才可報名揪團。")) {
			window.history.back();
		}
		
		// 若session已有圖檔 取出來放
		if (sessionStorage.getItem('dataIn') != null) {
			var dataOut = JSON.parse(sessionStorage.getItem("dataIn"));
			document.querySelector('#displayArea').innerText = "";
        	let html = `<img id="display" src="" style="max-width:200px">`;
    	    document.querySelector('#displayArea').insertAdjacentHTML('beforeend', html);
			$('#display').attr('src', dataOut.pic_base64);
		};
	});
	
	var dataIn;
	// 證照圖片上傳處理
	document.querySelector('#p_file').addEventListener('change', function() {
		let file = this.files[0];
		let reader = new FileReader();
		reader.readAsDataURL(file);
		reader.addEventListener('load', function() {
			document.querySelector('#displayArea').innerText = "";
        	let html = `<img id="display" src="" style="max-width:200px">`;
    	    document.querySelector('#displayArea').insertAdjacentHTML('beforeend', html);
			document.querySelector('#display').setAttribute('src', reader.result);
			
			var pic_base64 = reader.result;
			
			dataIn = {
					pic_base64: pic_base64
			};
			console.log(dataIn);
// 			console.log(dataIn.pic_base64);
		});
		
		// 提交報名後 暫存證照圖檔
		$('#submitRegistration').on('click', function() {
				console.log('aa');
				sessionStorage.setItem('dataIn', JSON.stringify(dataIn));
				console.log('bb');
		})
		
		$('#goBack').on('click', function() {
			sessionStorage.removeItem("dataIn");
		})
			
	});
</script>

</body>
</html>