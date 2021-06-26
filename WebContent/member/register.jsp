<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
	// 抓取新增失敗時回傳的VO
	MemberVO memberVO = (MemberVO) request.getAttribute("MemberVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊</title>
<style>
.contain {
	width: 70%;
}
</style>
</head>
<body>

	<div  class="contain" align="center">
		<div>
			<c:if test="${not empty errorMsgs}">
				<font style="color=red">以下欄位請輸入正確資料:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs }">
						<li style="color=red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<form  method="post" action="member.do"
			enctype="multipart/form-data" onsubmit="return checkform(this)">
			<table style="border-collapse:separate; border-spacing:0px 50px;">
				<tr>
					<td>帳號:</td>
					<td ><input placeholder="必填" type="email" name="account" size="45"
						value="<%=(memberVO == null) ? "" : memberVO.getAccount()%>" /></td>
					<td><input type="submit" name="btn_account" value="檢查帳號可用性" size="30" id="btn_account"><p>${used}</td>
				</tr>
				<tr>
					<td>密碼:</td>
					<td><input placeholder="必填" type="password" name="pwd"
						value="<%=(memberVO == null) ? "" : memberVO.getPwd()%>" /></td>
				</tr>
				<tr>
					<td>再次確認密碼:</td>
					<td><input placeholder="必填" type="password" name="repwd" id="repwd" /></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input placeholder="必填" type="TEXT" name="userName"
						value="<%=(memberVO == null) ? "" : memberVO.getUserName()%>" /></td>
				</tr>
				<tr>
					<td>暱稱:</td>
					<td><input placeholder="必填" type="TEXT" name="nickName"
						value="<%=(memberVO == null) ? "" : memberVO.getNickName()%>" /></td>
				</tr>
				<tr>
					<td>身分證字號:</td>
					<td><input type="TEXT" name="personID"
						value="<%=(memberVO == null) ? "" : memberVO.getPersonID()%>" /></td>
				</tr>
				<tr>
					<td>性別:</td>
					<td><input placeholder="必填" type="radio" name="gender" value="男"
						${memberVO.equals("男") ? 'selected' : ''}>男 <input
						type="radio" name="gender" value="男"
						${memberVO.equals("女") ? 'selected' : ''}>女</td>
				</tr>

				<tr>
					<td>生日:</td>
					<td><input type="Date" name="birthDate"
						value="<%=(memberVO == null) ? "" : memberVO.getBirthDate()%>" /></td>
				</tr>

				<tr>
					<td>電話:</td>
					<td><input type="TEXT" name="phone"
						value="<%=(memberVO == null) ? "" : memberVO.getPhone()%>" /></td>
				</tr>

				<tr>
					<td>地址:</td>
					<td><input type="TEXT" name=address
						value="<%=(memberVO == null) ? "" : memberVO.getAddress()%>" /></td>
				</tr>

				<tr>
					<td>淺水證照:</td>
					<td><select name="certification" size="2">
							<option value="0" ${(memberVO.limitNumder==null)? 'selected':'' }>無
							
							<option value="1" ${(memberVO.limitNumder==1)? 'selected':'' }>PADI
								OW / SSI OW
							<option value="2" ${(memberVO.limitNumder==2)? 'selected':'' }>PADI
								AOW / SSI AOW
					</select></td>
				</tr>

				<tr>
					<td>
						驗證碼：<input type="text" name="checknum" id="checknum" onblur="Manual_VerificationCode(this.value)"/>
						<img src="<%=request.getContextPath()%>/member/IdentityServlet"
						id="identity" onload="btn.disabled=false;" /> <input
						type="button" value=" 換個圖片 " id="btn" onclick="reloadImage()">
					</td>

				</tr>

				<tr>
					<td>淺水證照</td>
					<td><input type="file" name="certificationPic"
						accept="image/*">
						<div class="picture"></div></td>

				</tr>
			</table>


			<input type="hidden" name="action" value="insert"> 
			<input
				type="reset" value="重填"><input type="submit" value="註冊">
		</form>
		<a href="login.jsp"></a>
	</div>
</body>
<style>



</style>
<script>

function checkform(form){
	if(form.account.value == ""){
		alert("帳號不能空白");
		return false;
	}else if(form.pwd.value == ""){
		alert("密碼不能空白");
		return false;
	}else if(form.pwd.value != form.repwd.value){
		alert("兩次密碼不一致");
		return false;
	}else{
		return true;
	}
}
</script>
</html>