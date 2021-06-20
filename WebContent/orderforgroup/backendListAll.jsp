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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>List All Order for back</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/orderforgroup/css/backendListAll.css">
</head>
<body>

    <section>
   		<div class="main">
        <h1>All Orders For Group Tour</h1>
        
        <div class="tbl-header">
          <table cellpadding="0" cellspacing="0" border="0">
            <thead>
              <tr>
                <th>Order SN</th>
                <th>Customer Name</th>
                <th width="30%">Group Tour</th>
                <th>Amount Due</th>
                <th>Payment Status</th>
                <th>Action</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <c:forEach var="oderForGroupVO" items="${list}">
                
              <tr>
				<td>${oderForGroupVO.orderSN}</td>
				<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"></jsp:useBean>          
				<td>${memberSvc.getone(oderForGroupVO.userID).userName}</td>
           		<jsp:useBean id="groupTourSvc" scope="page" class="com.grouptour.model.GroupTourService"></jsp:useBean>
				<td width="30%">${oderForGroupVO.groupTourSN} - ${groupTourSvc.getOne(oderForGroupVO.groupTourSN).tourName}</td>
				<td>$ ${oderForGroupVO.totalPrice}</td>
				<td>Pending(NY)</td>
				<td>
					<form method="post" action="orderforgroup.do">
						<input type="hidden" name="orderSN" value="${oderForGroupVO.orderSN}">
						<input type="hidden" name="action" value="getOne_ForUpdate">
                        <button type="submit" class="custom-btn btn-2">Update</button>
					</FORM>
				</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        </div>
      </section>
      
      
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script>
    // '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
    $(window).on("load resize ", function() {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({'padding-right':scrollWidth});
    }).resize();
</script>
</body>
</html>