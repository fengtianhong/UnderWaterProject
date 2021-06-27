<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.qa.model.*"%>

<%  // 抓取新增失敗時回傳的VO
	QaVO qaVO = (QaVO) request.getAttribute("qaVO");
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file" %>
<title>Add QA</title>
<style>
	.container{
		margin: 0 auto;  
 		width: 1200px; 
		display: flex;
	}
	tr td{
		padding: 5px;
		padding-right: 20px;
	}
	.input1{
		width: 420px;
	}
	#editor{
		width: 420px;
		height: 100px;
	}
	.submit_btn{
		padding-top: 20px;
		padding-left: 50%;
		margin-bottom: 20px;
	}

</style>
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>

<div class="container">

<div class="col-lg-7">
<form method="post" action="qa.do">
<table>
	<h1 class="h3 mb-2 text-gray-800">新增問與答</h1>
	<p class="mb-4">針對使用者可能提出的問題進行解答，務必詳述</p>
	<tr>
		<td>問題</td>	
		<td><input type="TEXT" name="question" class="input1" value="<%=(qaVO==null)?"":qaVO.getQuestion()%>" /></td>
	</tr>
	<tr>
		<td>解答</td>
		<td><textarea id="editor" name="answer" class="input1">${qaVO.answer}</textarea></td>
	</tr>
	<tr>
		<td>選單分類</td>
		<td><input type="TEXT" name="menu" class="input1" value="${qaVO.menu}" /></td>
	</tr>
	<tr>
		<td>選單子分類</td>
		<td><input type="TEXT" name="submenu" class="input1" value="${qaVO.submenu}" /></td>
	</tr>
	<tr>
		<td>系統分類</td>
		<td>
			<select name="system" class="input1" >
				<option value="0" ${(qaVO.system==0)? 'selected':'' }>測試
				<option value="1" ${(qaVO.system==1)? 'selected':'' }>潛水地圖
				<option value="2" ${(qaVO.system==2)? 'selected':'' }>潛水團 (揪團/套裝行程)
				<option value="3" ${(qaVO.system==3)? 'selected':'' }>商城
				<option value="4" ${(qaVO.system==4)? 'selected':'' }>潛水分享
				<option value="5" ${(qaVO.system==5)? 'selected':'' }>會員相關
				<option value="6" ${(qaVO.system==6)? 'selected':'' }>其他
			</select>
		</td>
	</tr>
	<tr>
		<td>為熱門問題</td>
		<td>
			<div>
			    <input type="checkbox" name="popularQuestion" value="true" ${(qaVO.popularQuestion == false)?"":"checked"}>
			    <label for="popularQuestion"></label>
		  	</div>
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input class="btn btn-primary btn-user" type="submit" value="新增">
<input class="btn btn-primary btn-user" type ="button" onclick="window.location.href='<%=request.getContextPath()%>/qa/backendList.jsp'" value="上一頁">
</form>
</div>


<div class="col-lg-4">
<%-- 錯誤表列 --%>
 <div class="mb-4">
<div class="card border-left-warning shadow h-100 py-2">
	<div class="card-body">
		<div class="row no-gutters align-items-center">
			<div class="col mr-2">
			
						<div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
							Error Messages</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800"></div>
						
						<div class="card-body" style="color:LightCoral">
                            <c:forEach var="message" items="${errMsg}">
								- ${message}<br>
							</c:forEach>
                        </div>
			</div>
		<div class="col-auto">
		<i class="fas fa-comments fa-2x text-gray-300"></i>
	</div></div></div></div></div>


</div>

<%-- 成功Alert --%>
<c:if test="${not empty Msg}">
	<script>alert("${Msg}");</script>
</c:if>


<%@ include file="../share/backend/Bfooter.file" %>
</body>
<%@ include file="../share/backend/Bjs.file" %>
</html>