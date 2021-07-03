<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.articleReport.model.*"%>



<%
	Integer userID = (Integer) session.getAttribute("userID");

	ForumArticleService forumArticleSvc = new ForumArticleService();
	List<ForumArticleVO> list = forumArticleSvc.getAll();
	pageContext.setAttribute("list", list);

	ArticleReportService aRSvc = new ArticleReportService();
	List<ArticleReportVO> rptlist = aRSvc.getAll();
	pageContext.setAttribute("rptlist", rptlist);
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
<%-- 		<jsp:include page="../share/navbar.jsp" flush="true" />
		<jsp:include page="../share/member/Mheader.jsp" flush="true" /> --%>
	
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
								<c:if test="${article.articleStatus == true}">
									<td>正常顯示</td>
								</c:if>
								<c:if test="${article.articleStatus == false}">
									<td>不正常</td>
								</c:if>								
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
			<div>
				<table>
					<tr>
						<td>檢舉編號</td>
						<td>文章編號</td>	
						<td>處理狀態</td>
						<td>檢舉原因</td>
						<td>處理回覆</td>
					</tr>
					<c:forEach var="rpt" items="${rptlist}">
						<c:if test="${userID == rpt.userID}">		
							<tr>
								<td>${rpt.rptSN}</td>
								<td>${rpt.articleSN}</td>					
								<c:if test="${rpt.rptStatus == false}">
									<td>待處理</td>
								</c:if>
								<c:if test="${rpt.rptStatus == true}">
									<td>已處理</td>
								</c:if>						
								<td>${rpt.rptReason}</td>	
								<td>${rpt.reRptResult}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		<%-- <jsp:include page="../share/member/Mfooter.html" flush="true" /> 
		<jsp:include page="../share/footer.jsp" flush="true" />	 --%>
	</body>
</html>