<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleTitleOpt.model.*"%>

<% 
	ArticleTitleOptService articleTitleOptSvc = new ArticleTitleOptService();
	List<ArticleTitleOptVO> list = articleTitleOptSvc.getAll();
	pageContext.setAttribute("list", list);
%>
	

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../share/backend/Bmeta.file" %>
		<meta charset="UTF-8">
		<title>文章選項標題列表</title>
		
		<style>
			table#listallpg {
				background-color: #97CBFF;
				border: 2px solid black;
				text-align: center;
				
			}
		
			table#tableform {
				border: 2px solid black;
				text-align: center;
				margin-top: 5px;
			}
			
			table#tableform, tr, td {
				border: 1px solid black;
				padding: 3px;
				text-align: center;
			}
		
			.infoarea {
				margin-left: 200px;
			}
		
		
		</style>
	</head>

	<body>
	
	
	<%@ include file="../share/backend/Bheader.file" %>
		<div class="infoarea">
			<table id="listallpg">
				<tr>
					<td>
						<h3>查詢所有文章選項標題，列表如下</h3>
						<h4><a href="articleTitleOptSelect.jsp">回標題選項查詢首頁</a></h4>
					</td>
				</tr>
			</table>
	
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<table id=tableform>
				<tr>
					<td>標題選項編號</td>
					<td>標題選項內容</td>
					<td>修改標題選項</td>
				</tr>
				<%@ include file="page1.file" %>
				<c:forEach var="articleTitleOptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td>${articleTitleOptVO.articleTitleOptSN}</td>
						<td>${articleTitleOptVO.articleTitleOptText}</td>
						<td>
							<form method="post" action="articleTitleOpt.do" style="margin-bottom: 0px;">
								<input type="submit" value="修改">
								<input type="hidden" name="articleTitleOptSN" value="${articleTitleOptVO.articleTitleOptSN}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
						</td>
					</tr>
				</c:forEach>	
			</table>
			<%@ include file="page2.file" %>
		</div>
	<%@ include file="../share/backend/Bfooter.file" %>
	</body>
	<%@ include file="../share/backend/Bjs.file" %>
</html>