<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>
<%@ page import="com.articleReport.model.*"%>
<%@ page import="com.member.model.*" %>

<%
	ArticleReportVO articleReportVO = (ArticleReportVO) request.getAttribute("articleReportVO");
	Integer rptSN = (Integer)request.getAttribute("rptSN");
%>


<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../share/backend/Bmeta.file" %>
		<meta charset="UTF-8">
		<title>UW - 檢舉回覆</title>
		<style type="text/css">
			.reply {
				border: 1px solid black;
				margin: 0 auto;
				width: 600px;
			}
			tr {
				border: 1px solid black;
			}
			td{
				border: 1px solid black;
			}
		</style>
		
		
		
	</head>
	<body>
		<%@ include file="../share/backend/Bheader.file" %>
		<form method="post" action="articleReport.do">
			<table class="reply">
				<tr>
					<td>檢舉編號</td>
					<td>${articleReportVO.rptSN}</td>
				</tr>
				<tr>
					<td>文章編號</td>
					<td>${articleReportVO.articleSN}</td>
				</tr>
				<tr>
					<td>檢舉理由</td>
					<td>${articleReportVO.rptReason}</td>
				</tr>
				<tr>
					<td>檢舉回覆</td>
					<td><textarea name="reRptResult" maxlength==1000>${articleReportVO.reRptResult}</textarea></td>
				</tr>
				<tr>
					<td>完成送出</td>
					<td>	
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="rptSN" value="<%=articleReportVO.getRptSN()%>">
						<input type="submit" value="送出處理回覆" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
					</td>
				</tr>
			</table>
		</form>
		<%@ include file="../share/backend/Bfooter.file" %>
	</body>
	<%@ include file="../share/backend/Bjs.file" %>
	
</html>