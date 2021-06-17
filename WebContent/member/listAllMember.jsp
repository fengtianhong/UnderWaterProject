<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.member.model.*" %>
<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台會員查詢</title>
</head>
<body>
	<table>
		<tr>
			<th>會員編號</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>暱稱</th>
			<th>名字</th>
			<th>性別</th>
			<th>生日</th>
			<th>行動電話</th>
			<th>證照</th>
			<th>證照照片</th>
			<th>身分證字號</th>
			<th>地址</th>
			<th>創立時間</th>
			<th>帳號狀態</th>
			<th>更新時間</th>
			<th>被評價人數</th>
			<th>被評價總分</th>
		</tr>
		
	</table>
</body>
</html>