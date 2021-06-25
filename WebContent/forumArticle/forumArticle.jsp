<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>

<%
	ForumArticleService forumArticleSvc = new ForumArticleService();
	List<ForumArticleVO> list = forumArticleSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>論壇 - 討論區</title>
		<style>
		
		
		
		</style>
	</head>
	<body>
		<diV>
			
		
		</diV>
	
	
	
	
	
	
	
	
	
	
		
	</body>
</html>