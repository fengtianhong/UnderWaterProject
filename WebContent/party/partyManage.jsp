<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party.model.*" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PartyService partySvc = new PartyService();
	List<PartyVO> listAll = partySvc.getAll();
	
	if (session.getAttribute("findByPartySNLike") != null) {
		listAll = (List<PartyVO>) session.getAttribute("findByPartySNLike");
	}
	
	pageContext.setAttribute("listAll", listAll);
%>
<jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService" />


<!DOCTYPE html>
<html>
<head>
	<%@ include file="../share/backend/Bmeta.file" %>
	<title>揪團後台管理總表</title>
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>

<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">揪團管理</h1>
    <form method="post" action="<%=request.getContextPath()%>/party/party.do" class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
	    <div class="input-group">
	        <input type="text" name="partySN" class="form-control bg-light border-0 small" placeholder="輸入揪團編號" aria-label="Search" aria-describedby="basic-addon2">
	        <div class="input-group-append">
	            <button class="btn btn-primary" type="submit" name="action" value="findByPartySN">
	                <i class="fas fa-search fa-sm"></i>
	            </button>
	        </div>
	    </div>
	</form>
	
	<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			<div style="color:red">${message}</div>
		</c:forEach>
	</c:if>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">揪團列表</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>揪團編號</th>
                            <th>主揪會員編號</th>
                            <th>揪團主旨</th>
                            <th>報名截止日期</th>
                            <th>揪團潛點</th>
                            <th>揪團狀態</th>
                            <th>修改</th>
                        </tr>
                    </thead>
                    <c:forEach var="partyVO" items="${listAll}">
                    <tbody>
                        <tr>
                            <td class="partySN">${partyVO.partySN}</td>
							<td class="partyHost">${partyVO.partyHost}</td>
							<td class="partyTitle">${partyVO.partyTitle}</td>
							<td class="date">${partyVO.closeDate}</td>
							<td class="partyLocation">${diveInfoSvc.getOneDiveInfo(partyVO.partyLocation).pointName}</td>
                            <c:if test="${partyVO.status == '0'}">
								<td class="status">熱烈報名中</td>
							</c:if>
							<c:if test="${partyVO.status == '1'}">
								<td class="status">已額滿</td>
							</c:if>
							<c:if test="${partyVO.status == '2'}">
								<td class="status">已結束</td>
							</c:if>
							<c:if test="${partyVO.status == '3'}">
								<td class="status">已取消</td>
							</c:if>
							<c:if test="${partyVO.status == '4'}">
								<td class="status">已成團(仍可報名)</td>
							</c:if>
							<c:if test="${partyVO.status == '5'}">
								<td class="status">活動已下架</td>
							</c:if>
							<td>
								<button onclick="location.href='<%=request.getContextPath()%>/party/party.do?action=updateParty&&partySN=${partyVO.partySN}'" class="btn btn-primary btn-sm">修改</button>
							</td>
                        </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- /.container-fluid -->

<%@ include file="../share/backend/Bfooter.file" %>
<%@ include file="../share/backend/Bjs.file" %>

</body>
</html>