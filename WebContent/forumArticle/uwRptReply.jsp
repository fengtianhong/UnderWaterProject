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

		</style>
		
		
		
	</head>
	<body>
		<%@ include file="../share/backend/Bheader.file" %>
			<table>
				<tr>
					<td>檢舉編號<td>
					<td>${articleReportVO.rptSN}</td>
				</tr>
				<tr>
					<td>檢舉理由<td>
					<td>${articleReportVO.rptReason}</td>
				</tr>
				<tr>
					<td>檢舉回覆<td>
					<td><textarea name="reRptResult" maxlength==1000></textarea></td>
				</tr>
<%-- 				<tr>
					<td>處理狀態<td>
					<td>
						<jsp:useBean id="articleReportSvc" scope="page" class="com.articleReport.model.ArticleReportService"></jsp:useBean>
						<select size="1" name="rptStatus">
							<c:forEach var="articleReportVO" items="${articleReportSvc.all}">
								<option value="${articleReportVO.rptStatus == 0}" ${articleReportVO.rptStatus}? 'selected':''"></option>
							</c:forEach>
						</select>
					</td>
				</tr> --%>
				<tr>
					<td>送出<td>
					<td>
						
							<input type="hidden" name="action" value="update">
							<%-- <input type="hidden" name="rptSN" value="<%=articleReportVO.getRptSN()%>"> --%>
							<input type="submit" value="送出修改" style="border-radius: 7px; margin-bottom: 20px; margin-left: 20px;">
						
					</td>
				</tr>
			
			
			
			</table>
		
		
		
		
		
		
		

		<%@ include file="../share/backend/Bfooter.file" %>
	</body>
	<%@ include file="../share/backend/Bjs.file" %>
	
</html>