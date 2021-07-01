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
		max-width: 900px;
		width: 100%;
	}
	.top{
		DISPLAY: FLEX;
    	justify-content: center;
	}
	.content{
		background-color: snow;
		border-radius: 10px;
		box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.4);
		opacity: .9;
		padding: 60px;
		margin-top: 20px;
	}
	.question{
		color: #343a40;
		text-decoration:none !important;
	}
	.question h6{
		color: gray !important;
	}
	.answer{
		font-weight: 300;
    	color: black;
	}
	.pageNumber{
		padding-top: 57px;
	    position: absolute;
	    left: 50%;
	    transform: translate(-50%,-50%);
	}
	.gap{
		display: inline-grid;
	    width: 50px;
	    text-align: center;
	}
	.select{
		text-decoration:none !important;
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<div class="main-container">

<div class="content top">
				
				<a href="<%=request.getContextPath()%>/qa/QA.jsp" class="select" role="button">所有問題</a>
				<div class="gap">|</div>
				<a href="qaSelect.do?action=getPopular" class="select" role="button">熱門問題</a>
				<div class="gap">|</div>
				<a href="qaSelect.do?action=getPoint" class="select" role="button">潛水地圖</a>
				<div class="gap">|</div>
				<a href="qaSelect.do?action=getGroup" class="select" role="button">潛水團</a>
				<div class="gap">|</div>
				<a href="qaSelect.do?action=getMall" class="select" role="button">商城</a>
				<div class="gap">|</div>
				<a href="qaSelect.do?action=getForum" class="select" role="button">潛水分享</a>
				<div class="gap">|</div>
				<a href="qaSelect.do?action=getOthers" class="select" role="button">其他</a>
<!-- 				<div class="gap">|</div>	 -->
</div>
<div class="content">
	
	<h1 class="h3 mb-2 text-gray-800">${(type==null)?"所有問題":type}</h1>
	<hr>

	<%@ include file="page1.file" %>
    <c:forEach var="qaVO" items="${(listNew==null)?list:listNew}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">          
         <div class="card shadow mb-4">
           <!-- Card Header - Accordion -->
           <a href="#collapse${qaVO.questionSN}" class="d-block card-header py-2 collapsed question" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="collapse${orderForGroupVO.orderSN}">
               <h6 class="m-0 font-weight-bold">Q. ${qaVO.question}</h6>
           </a>
           <!-- Card Content - Collapse -->
           <div class="collapse" id="collapse${qaVO.questionSN}" style="">
              <div class="card-body">
              	      
                <div class="answer">
               		A. ${qaVO.answer}
                </div> 
                                       
              </div>
            </div>
            </div>
	</c:forEach>
	<div class='pageNumber'><%@ include file="page2.file" %></div>


<!-- content end --></div>
<!-- main-container-end --></div>

<jsp:include page="../share/footer.jsp" flush="true" />
</body>
</html>