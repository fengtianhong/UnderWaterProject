<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forumArticle.model.*"%>

<%
	ForumArticleVO forumArticleVO = (ForumArticleVO) request.getAttribute("forumArticleVO");
%>





<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>論壇 - 文章新增頁面</title>
				<style>
					  table#table-1 {
						background-color: #CCCCFF;
					    border: 2px solid black;
					    text-align: center;
					  }
					  table#table-1 h4 {
					    color: red;
					    display: block;
					    margin-bottom: 1px;
					  }
					  h4 {
					    color: blue;
					    display: inline;
					  }
					</style>
					
					<style>
					  table {
						width: 800px;
						background-color: white;
						margin-top: 1px;
						margin-bottom: 1px;
					  }
					  table, th, td {
					    border: 0px solid #CCCCFF;
					  }
					  th, td {
					    padding: 1px;
					  }
					</style>
		
			 <script src="https://cdn.tiny.cloud/1/sr0j1s2qucugfgs93xtofdv2nwy0g18a4ypu93btxfpet2nz/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

		</head>
		<body>
			<table id="table-1">
				<tr>
					<td><h3>文章新增</h3><p>(內文只能是中、英文字母、數字和_)</p></td>
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
			
			<FORM METHOD="post" ACTION="forumArticle.do" name="form1"  enctype="multipart/form-data">
				<table>
					<tr>
						<jsp:useBean id="articleTitleOptSvc" scope="page" class="com.articleTitleOpt.model.ArticleTitleOptService"></jsp:useBean>
						<td>文章分類<font color=red><b>*</b></font></td>
						<td>
							<select size="1" name="articleTitleOptSN">
								<c:forEach var="articleTitleOptVO" items="${articleTitleOptSvc.all}">
									<option value="${articleTitleOptVO.articleTitleOptSN}" ${(forumArticleVO.articleTitleOptSN==articleTitleOptVO.articleTitleOptSN)? 'selected':''}> ${articleTitleOptVO.articleTitleOptText}
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>文章標題</td>
						<td>
							<input type="text" name="articleTitcle" size="45" value="<%= (forumArticleVO==null)? "" : forumArticleVO.getArticleTitle()%>" />
						</td>
					</tr>
					<tr>
						<td>文章內文</td>
						<td>
							<textarea></textarea>
						</td>
					</tr>	
				</table>
				<br>
				<input type="hidden" name="action" value="insert">
				<input type="submit" value="送出新增">
			</Form>
			
			
		<script>
		    tinymce.init({
		      selector: 'textarea',
		      plugins: 'a11ychecker advcode casechange formatpainter linkchecker autolink lists checklist media mediaembed pageembed permanentpen powerpaste table advtable tinycomments tinymcespellchecker',
		      toolbar: 'a11ycheck addcomment showcomments casechange checklist code formatpainter pageembed permanentpen table',
		      toolbar_mode: 'floating',
		      tinycomments_mode: 'embedded',
		      tinycomments_author: 'Author name',
		   });
		</script>
		
		
		
		
		</body>
			
</html>