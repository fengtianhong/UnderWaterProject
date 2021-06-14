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
	

    <table id = "addtitle">
        <tr>
            <td>請在此輸入要新增的標題文字</td>
        </tr>
        <tr>
            <td><input type="text" name="TitleText" size="45" placeholder="以中文四字為限。"></td>
        </tr>
        <tr>
            <td><input type="button" name="submit" value="送出新增"></td>
        </tr>
    </table>
</body>

</html>