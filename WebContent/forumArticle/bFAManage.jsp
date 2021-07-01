<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>

<%
	ForumArticleService forumArticleSvc = new ForumArticleService();
	List<ForumArticleVO> list = forumArticleSvc.getAll();
	pageContext.setAttribute("list", list);
	
	Integer userID = (Integer) session.getAttribute("userID");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>論壇 - 後台</title>
		<style>
			div {
				border: 1px black solid;
				text-align: center;
			}
			table {
				border: 1px black solid;
				text-align: center;
			}
			tr {
				border: 1px black solid;
			}
			
			td {
				border: 1px black solid;
			}
		</style>
	</head>
	<body>
	
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
	    		<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div id="articleManage"><h3>個人文章管理頁面</h3></div>
			<div>
				<table>
					<tr>
						<td>文章編號</td>
						<td>標題選項</td>
						<td>文章標題</td>
						<td>發布日期</td>
						<td>文章狀態</td>
						<td>文章編輯</td>
						<td>刪除文章</td>		
					</tr>
					<c:forEach var="article" items="${list}">
						<c:if test="${userID == article.userID}">
							<tr>
								<td>${article.articleSN}</td>
								<td>${article.articleTitleOptSN}</td>
								<td>${article.articleTitle}</td>
								<td>${article.publishedDate}</td>
								<td>${article.articleStatus}</td>
								<td>
									<form method="post" action="forumArticle.do">
										<input type="submit" value="修改">
										<input type="hidden" name="articleSN" value="${article.articleSN}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>
								</td>
								<td>
									<form method="post" action="forumArticle.do">
										<input type="submit" value="刪除">
										<input type="hidden" name="articleSN" value="${article.articleSN}">
										<input type="hidden" name="action" value="deleteArticle">
									</form>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
	</body>
</html>