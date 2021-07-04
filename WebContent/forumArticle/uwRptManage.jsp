<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.articleReport.model.*"%>
<%@ page import="com.member.model.*" %>

<%
	Integer userID = (Integer) session.getAttribute("userID");
	Integer rptSN = (Integer)request.getAttribute("rptSN");

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
		<%@ include file="../share/backend/Bmeta.file" %>
		<meta charset="UTF-8">
		<title>UW - 檢舉管理</title>
		<style type="text/css">
			.rptlist {
				margin-left: 30px;
			}
		
			table {
				border: 1px solid black;
				width: 1100px;
			}
			
			tr {
				border: 1px solid black;
			}
			
			td {
				border: 1px solid black;
			}
			
			
		</style>
		
		
		
	</head>
	<body>
	<%@ include file="../share/backend/Bheader.file" %>
		<div class="rptlist">
			<h4>文章檢舉列表</h4>
			<table>
				<tr>
					<td>檢舉編號</td>
					<td>檢舉userID</td>
					<td>檢舉文章</td>
					<td>檢舉理由</td>
					<td>檢舉狀態</td>
					<td>處理回覆</td>
				</tr>
				<c:forEach var="rpt" items="${rptlist}">
					<tr>
						<td>${rpt.rptSN}</td>
						<td>${rpt.userID}</td>
						<td>${rpt.articleSN}</td>
						<td>${rpt.rptReason}</td>
						
						<c:if test="${rpt.rptStatus == true}">
							<td><font color="green">已處理</font></td>
						</c:if>
						<c:if test="${rpt.rptStatus == false}">
							<td><font color="red">未處理</font></td>
						</c:if>
						
						
						<td>${rpt.reRptResult}</td>
						
						<td>
							<form method="post" action="articleReport.do">
								<input type="submit" value="檢舉處理">
								<input type="hidden" name="rptSN" value="${rpt.rptSN}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
							<h1>有接到參數了</h1>
						
						
						</td>
						
						
					</tr>
				</c:forEach>
			</table>
		
		
		
		</div>
	
	
	
		
	
	
	
	
	<%@ include file="../share/backend/Bfooter.file" %>
	</body>
	<%@ include file="../share/backend/Bjs.file" %>
	
</html>