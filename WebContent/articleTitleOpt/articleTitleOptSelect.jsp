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
			div#titlepg {
				border: 2px solid;
				margin-left: auto;
				margin-right: auto;
				text-align: center;
				width: 400px;
				line-height: 20px;
			}
			div#linkbutton {
				border: 2px solid;
				margin-right: auto;
				margin-left: auto;
				margin-top: 3px;
				padding: 5px;
				width: 390px;
				line-height: 10px;
				text-align: center;
			}
		
		</style>
	</head>

	<body>
		<div id="titlepg">
			<h2>發文標題選項管理頁</h2>
			<h4>此頁提供查詢功能，若需新增或更新請點下方連結前往</h4>
		</div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<div id="linkbutton">
		<input type="button" value="前往新增標題選項" onclick="location.href='addArticleTitleOpt.jsp'">
		<input type="button" value="前往更新標題選項" onclick="location.href='updateArticleTitleOpt.jsp'">
		</div>

		<div>
			<h3>發文選項查詢</h3>
			<ul>
				<li><a href="listAllArticleTitleOpt.jsp">查詢全部標題選項</a></li>
				<li>
					<FORM METHOD="post" ACTION="articleTitleOpt.do" enctype="multipart/form-data">
						<b>輸入發文標題選項編號(如：31)</b>
						<input type="text" name="articleTitleOptSN">
						<input type="hidden" name="action" value="getOne_For_display">
						<input type="submit" value="sent">
					</FORM>
				</li>
			</ul>
		</div>

	</body>
</html>