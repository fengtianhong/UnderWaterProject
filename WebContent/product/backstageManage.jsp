<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<%@ include file="../share/backend/Bmeta.file" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">   
<title>後台商品管理頁面</title>

</head>

<body>
<%@ include file="../share/backend/Bheader.file" %>

	<div class="container">
		<h3>商品管理功能</h3>
		
		<ul>
	  		<li><a href='bk_insertProduct.jsp'>商品新增</a></li>
		</ul>
			
		<ul>
	  		<li><a href='bk_updateProduct_select.jsp'>商品修改</a></li>
		</ul>
	 </div>

<%@ include file="../share/backend/Bfooter.file" %>
</body>
<%@ include file="../share/backend/Bjs.file" %>

</html>