<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>

<%  
	if(request.getAttribute("list") == null) {
		QaService qaSvc = new QaService();
		List<QaVO> list = qaSvc.getAll();
		pageContext.setAttribute("list", list);	// WHY
	}

%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file" %>
<title>List All QA</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/qa/css/backendListAll.css">
</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>


	<h2>Q & A 後台管理 - 全部列表</h2>
	<h3>Q A 列表 </h3>
			<span>請選擇系統分類</span>
			<select name="system" class="system">
				<option value="0" ${(qaVO.system==0)? 'selected':'' }>測試
				<option value="1" ${(qaVO.system==1)? 'selected':'' }>潛水地圖
				<option value="2" ${(qaVO.system==2)? 'selected':'' }>潛水團 (揪團/套裝行程)
				<option value="3" ${(qaVO.system==3)? 'selected':'' }>商城
				<option value="4" ${(qaVO.system==4)? 'selected':'' }>潛水分享
				<option value="5" ${(qaVO.system==5)? 'selected':'' }>會員相關
				<option value="6" ${(qaVO.system==6)? 'selected':'' }>其他
			</select>
			<button type="button" class="filter">Search</button>
			<button type="button" onclick="window.location.href='<%=request.getContextPath()%>/qa/backendList.jsp'">Refresh</button>
			
			
		<section>
   		<div class="main">
        <div class="tbl-header">
          <table cellpadding="0" cellspacing="0" border="0" >
            <thead>
              <tr>
                <th>System</th>
                <th width="20%">question</th>
                <th width="30%">answer</th>
                <th>clicks</th>
                <th>menu & submenu</th>
                <th>Action</th>
              </tr>
            </thead>
          </table>
        </div>
        <div class="tbl-content">
          <table cellpadding="0" cellspacing="0" border="0" id="qa_content">	
            <tbody>
            <c:forEach var="qaVO" items="${list}">
                
              <tr>
				<td>${qaVO.system}</td>
				<td width="20%">${qaVO.question}</td>
				<td width="30%">${qaVO.answer}</td>
				<td>${qaVO.clicks}</td>
				<td>
					<form method="post" action="qa.do">
						<input type="hidden" name="questionSN" value="${qaVO.questionSN}">
						<input type="hidden" name="action" value="getOne_ForUpdate">
                        <button type="submit" class="custom-btn btn-2">Update</button>
					</FORM>
				</td>
				<td>
					
					<form method="post" action="qa.do">
						<input type="hidden" name="questionSN" value="${qaVO.questionSN}">
						<input type="hidden" name="action" value="delete">
                        <button type="submit" class="custom-btn btn-2">Delete</button>
					</FORM>
				</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        </div>
      </section>
     
 <%-- 成功Alert --%>
<c:if test="${not empty Msg}">
	<script>alert("${Msg}");</script>
</c:if>
<c:if test="${not empty errMsg}">
	<script>alert("${Msg}");</script>
</c:if>

<%@ include file="../share/backend/Bfooter.file" %>
</body>
<%@ include file="../share/backend/Bjs.file" %>
<script>

	// 滾輪視窗
    // '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
    $(window).on("load resize ", function() {
    var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
    $('.tbl-header').css({'padding-right':scrollWidth});
    }).resize();
    
	
    // filter getValue by id 
    $(".filter").on("click", function() {
    	var system = ($(".system").val()).trim();
    	var tdListUser = $("table>tbody>tr").find("td:eq(0)");

	console.log(tdListUser);
		tdListUser.each(function(index, el) {
			if (system == el.innerText) {
				console.log(el);
				$(el).closest('tr').show();
			} else {
				$(el).closest('tr').hide();
			}
		})

	})
</script>
</html>