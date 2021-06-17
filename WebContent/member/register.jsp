<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "com.member.model.*"%>


<%
	// 抓取新增失敗時回傳的VO
	MemberVO memberVO = (MemberVO) request.getAttribute("MemberVO");
%>
<%=memberVO == null%><%-- 確認有沒有抓到用(可刪) --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊</title>
<style>
.ckeditor {
	width: 45%;
}
</style>
</head>
<body>

	<form method="post" action="member.do" enctype="multipart/form-data">
		<table>
			<tr>
				<td>帳號:</td>
				<td><input type="email" name="account" size="45"
					value="<%=(memberVO == null) ? "" : memberVO.getAccount()%>" /></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="password" name="pwd"
					value="<%=(memberVO == null) ? "" : memberVO.getPwd()%>" /></td>
			</tr>
			<tr>
				<td>再次確認:</td>

			</tr>
			<tr>
				<td>姓名:</td>
				<td><input type="TEXT" name="userName"
					value="<%=(memberVO == null) ? "" : memberVO.getUserName()%>" /></td>
			</tr>
			<tr>
				<td>暱稱:</td>
				<td><input type="TEXT" name="nickName"
					value="<%=(memberVO == null) ? "" : memberVO.getNickName()%>" /></td>
			</tr>
			<tr>
				<td>身分證字號</td>
				<td><input type="TEXT" name="personID"
					value="<%=(memberVO == null) ? "" : memberVO.getPersonID()%>" /></td>
			</tr>
			<tr>
				<td>性別:</td>
				<td>
					<input type="radio" name="gender" value="男"
						${memberVO.equals("男") ? 'selected' : ''}>男
					<input type="radio" name="gender" value="男"
						${memberVO.equals("女") ? 'selected' : ''}>女
					
				</td>
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
						<option value="" ${(memberVO.limitNumder==null)? 'selected':'' }>無
						<option value="A1" ${(memberVO.limitNumder==1)? 'selected':'' }>PADI
							OW
						<option value="A2" ${(memberVO.limitNumder==2)? 'selected':'' }>PADI
							AOW
						<option value="B1" ${(memberVO.limitNumder==3)? 'selected':'' }>SSI
							OW
						<option value="B2" ${(memberVO.limitNumder==4)? 'selected':'' }>SSI
							AOW
				</select></td>
			</tr>

			<tr>
				<td>驗證碼:</td>

			</tr>

			<tr>
				<td>淺水證照</td>
				<td><input type="file" name="certificationPic" accept="image/*">
					<div class="picture"></div></td>

			</tr>
		</table>


		<input type="hidden" name="action" value="insert"> <input
			type="submit" value="重填"><input type="submit" value="註冊">
	</form>
	<a href = "login.jsp"></a>
</body>



<style>
</style>
<script>
	
</script>
</html>