<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orderforgroup.model.*"%>

<%	
	OderForGroupService oderForGroupSvc = new OderForGroupService();
	List<OderForGroupVO> list = oderForGroupSvc.getAll();
	pageContext.setAttribute("list", list);	// WHY

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List All for back</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/orderforgroup/css/listAllForBackend.css">

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
    <c:forEach var="oderForGroupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">          
          <li class="table-row">
            <div class="col col-1" data-label="Order SN">${oderForGroupVO.orderSN}</div>
  			<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>          
            <div class="col col-2" data-label="Customer Name">${memberSvc.getone(oderForGroupVO.userID).userName}</div>
            
            <jsp:useBean id="groupTourSvc" scope="page" class="com.grouptour.model.GroupTourService"></jsp:useBean>
            <div class="col col-3" data-label="Group Tour">${oderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(oderForGroupVO.groupTourSN).tourName}</div>
            
            <div class="col col-4" data-label="Amount Due">${oderForGroupVO.totalPrice}</div>
            <div class="col col-5" data-label="Payment Status">Pending(NY)</div>
            <div class="col col-6" data-label="Modified">
                <form method="post" action="orderforgroup.do">
                    <input type="hidden" name="orderSN" value="${oderForGroupVO.orderSN}">
                    <input type="hidden" name="action" value="getOne_ForUpdate">
                    <input type="submit" value="UPDATE">
                </FORM>
            </div>
          </li>
	</c:forEach><%@ include file="page2.file" %>

    
        </ul>
      </div>

</body>
</html>