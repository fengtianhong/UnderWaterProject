<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>

<%  
	QaService qaService = new QaService();
	List<QaVO> list = qaService.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q & A</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<style>
	.main-container{
		margin: 0 auto;
		width: 900px;
		/* 		display: flex; */
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<div class="main-container">


	<%@ include file="page1.file" %>
    <c:forEach var="qaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">          
         <div class="card shadow mb-4">
           <!-- Card Header - Accordion -->
           <a href="#collapse${qaVO.questionSN}" class="d-block card-header py-3 collapsed header_orderSN" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="collapse${orderForGroupVO.orderSN}">
               <h6 class="m-0 font-weight-bold text-primary">${qaVO.question}</h6>
           </a>
           <!-- Card Content - Collapse -->
           <div class="collapse" id="collapse${qaVO.questionSN}" style="">
              <div class="card-body">
              	      
                <div>
               		${qaVO.answer}
                </div> 
                <hr>聯繫客服 
                                       
              </div>
            </div>
            </div>
	</c:forEach>
	<div class='pageNumber'><%@ include file="page2.file" %></div>




<!-- main-container-end --></div>

<jsp:include page="../share/footer.jsp" flush="true" />
</body>
</html>