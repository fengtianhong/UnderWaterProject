<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.articleTitleOpt.model.*"%>

<%
	ArticleTitleOptVO articleTitleOptVO = (ArticleTitleOptVO) request.getAttribute("articleTitleoptVO");
%>

<%-- <%= articleTitleOptVO == null%>> --%>

<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<title>後台管理 - 文章標題 (addArticleTitleOpt.jsp)</title>
		
		<style>
        table#addtitle{
            background-color: wheat;
            border: 2px solid black;
            margin-left: auto;
            margin-right: auto;
            margin-top: 50px;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	
<FORM METHOD="post" ACTION="articleTitleOpt.do" name="form1">
    <table id = "addtitle">
        <tr>
            <td>請在此輸入要新增的標題文字</td>
        </tr>
        <tr>
            <td><input type="text" name="TitleText" size="45" 
            			value="<%= (articleTitleOptVO==null)? "新增標題" : articleTitleOptVO.getArticleTitleOptText() %>" /></td>
        </tr>
        <tr>
        	<td><input type="hidden" name="action" value="insert"></td>
            <td><input type="submit" name="submit" value="送出新增"></td>
        </tr>
    </table>
  </FORM>
</body>

</html>