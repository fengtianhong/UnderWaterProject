<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>

<%  
		QaService qaSvc = new QaService();
		List<QaVO> list = qaSvc.getAll();
		pageContext.setAttribute("list", list);

%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../share/backend/Bmeta.file" %>
<title>List All QA</title>
<style>
	.container{
	margin: 0 auto;  
 	width: 1200px; 
 	}
    table{
    width:100%;
    table-layout: fixed;
   }
  .tbl-header{
    background-color: #4e73df; 
/*     background-color: rgba(255,255,255,0.3);  */
   }
  .tbl-content{
    height:400px;
    overflow-x:auto;
    margin-top: 0px;
    border: 1px solid rgba(20,24,78,0.3);
    background-color: rgba(255,255,255,0.3); 
/*     border: 1px solid rgba(255,255,255,0.3);   */
  }
  th{
    padding: 10px 10px;
    text-align: left;
    font-weight: 500;
    color: white;
    text-transform: uppercase;
  }
  td{
    padding: 8px;
    text-align: left;
    vertical-align:middle;
    font-weight: 300;
    color: gray;
    border-bottom: solid 1px rgba(20,24,78,0.3); 
  }
 
  section{
    margin-top: 30px;
  }

/*   button { */
/*     color: #fff; */
/*   } */
  /* for custom scrollbar for webkit browser*/
  
  ::-webkit-scrollbar {
      width: 6px;
  } 
  ::-webkit-scrollbar-track {
      -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
  } 
  ::-webkit-scrollbar-thumb {
      -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
  }

</style>

</head>
<body>
<%@ include file="../share/backend/Bheader.file" %>

<div class="container">

	<h1 class="h3 mb-2 text-gray-800">Q A 列表</h1>
			<span>請選擇系統分類</span>
			<select name="system" class="system" onchange="filter(this.value)">
				<option value="6" ${(qaVO.system==6)? 'selected':'' }>全部
				<option value="0" ${(qaVO.system==0)? 'selected':'' }>0: 其他
				<option value="1" ${(qaVO.system==1)? 'selected':'' }>1: 潛水地圖
				<option value="2" ${(qaVO.system==2)? 'selected':'' }>2: 潛水團 (揪團/套裝行程)
				<option value="3" ${(qaVO.system==3)? 'selected':'' }>3: 商城
				<option value="4" ${(qaVO.system==4)? 'selected':'' }>4: 潛水分享
				<option value="5" ${(qaVO.system==5)? 'selected':'' }>5: 會員相關
			</select>
			
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
                <th>Action</th>
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
				<td class="forFilter">${qaVO.system}</td>
				<td width="20%">${qaVO.question}</td>
				<td width="30%">${qaVO.answer}</td>
				<td>${qaVO.clicks}</td>
				<td>
					<form method="post" action="qa.do">
						<input type="hidden" name="questionSN" value="${qaVO.questionSN}">
						<input type="hidden" name="action" value="getOne_ForUpdate">
                        <button class="btn btn-primary btn-user" type="submit" class="custom-btn btn-2">Update</button>
					</FORM>
				</td>
				<td>
					
					<form method="post" action="qa.do">
						<input type="hidden" name="questionSN" value="${qaVO.questionSN}">
						<input type="hidden" name="action" value="delete">
                        <button class="btn btn-primary btn-user" type="submit" class="custom-btn btn-2">Delete</button>
					</FORM>
				</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
        </div>
      </section>
      
 </div>    
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
//     $(".filter").on("click", function() {
//     	var system = ($(".system").val()).trim();
//     	var tdListUser = $("table>tbody>tr").find("td:eq(0)");

// 	console.log(tdListUser);
// 		tdListUser.each(function(index, el) {
// 			if (system == el.innerText) {
// 				console.log(el);
// 				$(el).closest('tr').show();
// 			} else {
// 				$(el).closest('tr').hide();
// 			}
// 		})

// 	})
	// 	搜索列表
	function filter(value) {
    console.log(value);
		switch(value) {
			case "0":
				$(".forFilter").each(function(i, e) {
                    if($(e).text()=="0") {
                        $(e).closest("tr").show();
                    }else{
                        $(e).closest("tr").hide();
                    }
				})
				break;
			case "1":
				$(".forFilter").each(function(i, e) {
                    if($(e).text()=="1") {
                        $(e).closest("tr").show();
                    }else{
                        $(e).closest("tr").hide();
                    }
				})
				break;
			case "2":
                 $(".forFilter").each(function(i, e) {
                    if($(e).text()=="2") {
                        $(e).closest("tr").show();
                    }else{
                        $(e).closest("tr").hide();
                    }
				})
				break;
			case "3":
                $(".forFilter").each(function(i, e) {
                    if($(e).text()=="3") {
                        $(e).closest("tr").show();
                    }else{
                        $(e).closest("tr").hide();
                    }
				})
				break;
			case "4":
                $(".forFilter").each(function(i, e) {
                    if($(e).text()=="4") {
                        $(e).closest("tr").show();
                    }else{
                        $(e).closest("tr").hide();
                    }
				})
				break;
			case "5":
                $(".forFilter").each(function(i, e) {
                    if($(e).text()=="5") {
                        $(e).closest("tr").show();
                    }else{
                        $(e).closest("tr").hide();
                    }
				})
				break;
			default:
                $(".forFilter").closest("tr").show();
				break;
		}
	}
</script>
</html>