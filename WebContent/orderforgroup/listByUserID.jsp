<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforgroup.model.*"%>

<%
	
	// Integer userID = (Integer) session.getAttribute("userID");	// userID 存在session
	OrderForGroupService orderForGroupSvc = new OrderForGroupService();
	List<OrderForGroupVO> list = orderForGroupSvc.getOrderByUserID(1);	// UserID先寫死
	pageContext.setAttribute("list", list);	// WHY
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List All for back</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/orderforgroup/css/listByUserID.css">

</head>
<body>
    <div class="container">
        <h2>套裝行程訂單</h2>
        <ul class="responsive-table">
          <li class="table-header">
            <div class="col col-1">Order SN</div>
            <div class="col col-2">Customer Name</div>
            <div class="col col-3">Group Tour</div>
            <div class="col col-4">Amount Due</div>
            <div class="col col-5">Payment Status</div>
            <div class="col col-6">Modified</div>
          </li>

	<%@ include file="page1.file" %>
    <c:forEach var="orderForGroupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">          
          <li class="table-row">
            <div class="col col-1" data-label="Order SN">${orderForGroupVO.orderSN}</div>
  			<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>          
            <div class="col col-2" data-label="Customer Name">${memberSvc.getone(orderForGroupVO.userID).userName}</div>
            
            <jsp:useBean id="groupTourSvc" scope="page" class="com.grouptour.model.GroupTourService"></jsp:useBean>
            <div class="col col-3" data-label="Group Tour">${orderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(orderForGroupVO.groupTourSN).tourName}</div>
            
            <div class="col col-4" data-label="Amount Due">${orderForGroupVO.totalPrice}</div>
            <div class="col col-5" data-label="Payment Status">Pending(NY)</div>
            <div class="col col-6" data-label="Modified">
                <form method="post" action="orderforgroup.do">
                    <input type="hidden" name="orderSN" value="${orderForGroupVO.orderSN}">
                    <input type="hidden" name="action" value="getOne_ForUpdate">
                    <input type="submit" value="UPDATE">
                </FORM>
            </div>
          </li>
	</c:forEach>
	<div class='pageNumber'><%@ include file="page2.file" %></div>

    
        </ul>
      </div>

</body>
</html>