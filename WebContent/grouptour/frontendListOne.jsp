<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouptour.model.*"%>

<%  // 抓取新增失敗時回傳的VO
	GroupTourVO groupTourVO = (GroupTourVO) request.getAttribute("groupTourVO");
%>
<%= groupTourVO==null %><%-- 確認有沒有抓到用(可刪) --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>