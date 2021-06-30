<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderforgroup.model.*"%>

<%  
// 抓取新增失敗時回傳的VO
OrderForGroupVO orderForGroupVO = (OrderForGroupVO) request.getAttribute("orderForGroupVO");
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file" %>
<title>Update Order For Group 後台修改訂單</title>
<style>
	.container{
		margin: 0 auto;  
 		width: 1200px; 
		display: flex;
	}
	img{
		width: 100%;
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
        <h1 class="h3 mb-2 text-gray-800">套裝行程訂單修改</h1>
        <p class="mb-4">訂單資訊請勿隨意變更</p>


<form method="post" action="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do">
<table>
	<tr>
		<td>訂購人userID</td>	
		<td>${orderForGroupVO.userID}</td>
	</tr>
	<tr>
		<td>訂購商品(套裝行程)</td>	
		<td>${orderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).tourName}</td>
	</tr>
	<tr>
		<td>總金額</td>	
		<td><input type="TEXT" name="phone" size="45" value="${orderForGroupVO.totalPrice}" /></td>
	</tr>
	<tr>
		<td>購買日期</td>	
		<td><input type="TEXT" class="date" name="purchaseDate" size="45" value="${orderForGroupVO.purchaseDate}" /></td>
	</tr>
	<tr>
		<td>連絡電話</td>	
		<td><input type="TEXT" name="phone" size="45" value="${orderForGroupVO.phone}" /></td>
	</tr>
	<tr>
		<td>身分證字號</td>	
		<td><input type="TEXT" name="personID" size="45" value="${orderForGroupVO.userID}" /></td>
	</tr>
	<tr>
		<td>出生日期</td>	
		<td><input type="TEXT" class="date" name="birthdate" size="45" value="${orderForGroupVO.birthdate}" /></td>
	</tr>
</table>
	<input type="hidden" name="action" value="update">
	<input class="btn btn-primary btn-user" type="submit" value="修改">
</form>
</div><%-- col-lg-7 end --%>

<div class="col-lg-4">
  <!-- Err msg -->
    <c:if test="${not empty errMsg}">
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
        </div></div></div></div>
                
    </div></c:if></div><%-- col-lg-4 end --%>	
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>

	      window.addEventListener("DOMContentLoaded", function(){
	    	  
	// dateTimePicker
	$.datetimepicker.setLocale('zh');
     var today = new Date();
     $('.date').datetimepicker({
	       timepicker:false,
	       format:'Y-m-d',
		   beforeShowDay: function(date) {
       	  if (  date.getYear() <  today.getYear() || 
		       (date.getYear() == today.getYear() && date.getMonth() <  today.getMonth()) || 
		       (date.getYear() == today.getYear() && date.getMonth() == today.getMonth() && date.getDate() < today.getDate())
	          ) {
	               return [false, ""]
	          }
	          return [true, ""];
    	}});


        
      });

</script>
</html>