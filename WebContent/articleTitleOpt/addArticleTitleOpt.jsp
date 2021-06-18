<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.articleTitleOpt.model.*"%>

<%
	ArticleTitleOptVO articleTitleOptVO = (ArticleTitleOptVO) request.getAttribute("articleTitleOptVO");
%>

<%-- <%= articleTitleOptVO == null%>> --%>

<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<title>新增標題選項 (addArticleTitleOpt.jsp)</title>
		
		<style>
        table#addtitle{
            background-color: wheat;
            border: 2px solid black;
            margin-left: auto;
            margin-right: auto;
            margin-top: 50px;
        }

		table#addpg {
				background-color: #97CBFF;
				border: 2px solid black;
				text-align: center;
				margin: 0 auto;
			}

        td {
            text-align: center;
        }
    </style>
</head>
<body>
	<table id="addpg">
			<tr>
				<td>
					<h3>新增文章選項，以四個中文字為限</h3>
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
	
<FORM METHOD="post" ACTION="articleTitleOpt.do" name="form1" enctype="multipart/form-data">
    <table id = "addtitle">
        <tr>
            <td>請在此輸入要新增的標題選項文字</td>
        </tr>
        <tr>
            <td>
            	<input type="text" name="TitleText" size="45" 
            			value="<%= (articleTitleOptVO==null)? "新增標題" : articleTitleOptVO.getArticleTitleOptText()%>" />
            </td>
        	<td>
        		<input type="hidden" name="action" value="insert">
            	<input type="submit" value="送出新增">
            </td>
        </tr>
	 </table>
        	
	</FORM>
 
</body>

</html>