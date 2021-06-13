<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleTitleOpt.model.*"%>

<%
	ArticleTitleOptService articleTitleOptSvc = new ArticleTitleOptService();
	List<ArticleTitleOptVO> list = articleTitleOptSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
	<head>
		<meta charset="UTF-8">
		<title>發文標題選項管理</title>
		<style>
		
		
		</style>
	</head>

	<body>

	</body>
</html>