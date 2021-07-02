<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouptour.model.*"%>
<%@ page import="com.diveinfo.model.*"%>

<%
	List<GroupTourVO> listNew = (List<GroupTourVO>) request.getAttribute("listNew");
	if(listNew ==null) {
		GroupTourService groupTourSvc = new GroupTourService();
		List<GroupTourVO> list = groupTourSvc.getFrontendAll();
		pageContext.setAttribute("list", list);
	}else{
		pageContext.setAttribute("list", listNew);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">   
<title>套裝行程列表</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">	
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/grouptour/css/frontendListAll.css"> --%>

<style>

	.container{
		margin: 0 auto;  
 		width: 1200px; 
/* 		display: flex; */
	}
 	.item{ 
 		margin: 18px; 
 		border: 1px solid white;
		background-color: #eee;
 		padding: 5px; 
 		border-radius: 20px;
 		box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.4);
 		
 	} 
 	.item:hover{
 		opacity: 0.99;
 		transform: scale(1.01);
 		box-shadow: 0px 0px 9px 0px rgba(0,0,0,0.7);
 	}
	.picture{
		border: 1px solid white;
		height: 180px;
		overflow: hidden;
		margin: 8px;
		margin-top: 15px;
 		border-radius: 50% 20% / 10% 40%; 
	}
	.list-group-image{
		width: 257px;
		height: 200px;
		/*圖片撐滿 */
		object-fit: cover;		
    	object-position: center;
	}
	.caption{
		padding: 15px;
		
	}
	.pointName{
		color: DarkSlateGray;
	}
	
	.btn-success{
		float: right;
	}
	.inner-text{
		padding-left: 9px;
	}
	.detail{
		margin-top: 7px;
	}
/* 上方搜尋欄 */
	.top{
	    margin-bottom: 20px;
    	margin-top: 30px;
		background-color: snow;
		border-radius: 10px;
		box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.4);
		padding: 10px;
		opacity: .9;
	}
	.filter{
		padding: 20px;
		display: inline;
	}
	.page2{
		margin-top: 50px;
		text-align: center;
	}
	.attend_btn{
    	position: relative;
    	bottom: 45px;
    	left: 96px;
    	cursor: default !important;
	}
	
		
</style>
</head>

<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

	<div class="row justify-content-center">
	<div class="top">
    <form  method="post" action="GroupTourFilter.do">
	 	<div class="filter">
		<select id="filter" name="location" class="location" onchange="filter(this.value)">
			<option value="0">ALL
			<option value="北部">北部
			<option value="南部">南部
			<option value="離島">離島
		</select> 
		</div> 
	    <div class="filter"><input type="search" name="keyword" class="keyword-filter" placeholder="請輸入關鍵字"></div> 
<!-- 		 <div class="filter"><input name="dateStart" type="date" class="date-filter"></div> -->
<!-- 		 <div class="filter"><input name="dateEnd" type="date" class="date-filter"></div> -->
		 
		<input type="hidden" name="action" value="filter">
		<input class="btn btn-primary btn-user" type ="submit" value="送出">
		<input class="btn btn-primary btn-user" type="reset" value="清除">
	 	<c:if test="${msg!=null}"> ${msg}</c:if>
	</form> 
    </div> 
    </div>
    
    
    
    <div class="container">
        
        <div id="products" class="row justify-content-center">

<%@ include file="page1.file"%>	
            <c:forEach var="groupTourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
            <div class="item col-lg-3 col-md-3 col-sm-3">
            <input class="forFilter" type="hidden" value="${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).local}">
                <div class="thumbnail">
                
                <div class="picture">
                		<img class="list-group-image" src="GetImage.do?id=${groupTourVO.groupTourSN}" />
                </div>
                <div class="caption">
                        <h6><b>${groupTourVO.tourName}</b></h6>

                        <jsp:useBean id="diveInfoSvc" scope="page" class="com.diveinfo.model.DiveInfoService"></jsp:useBean>
                        <small class="pointName">
                            ${diveInfoSvc.getOneDiveInfo(groupTourVO.pointSN).pointName}</small>
                            
                        
                        <p class="detail">
                            <i class="fas fa-calendar-day"></i><span class="inner-text">${groupTourVO.startTime} ~ ${groupTourVO.endTime}</span>
                            <br>
                        	<i class="fas fa-child"></i><span class="inner-text">${groupTourVO.attendNumber} / ${groupTourVO.limitNumder}</span>
                        </p>
                            
                        <div class="row">
                            <div class="col-xs-12 col-md-6">
                                <p class="lead">
                                    $ ${groupTourVO.price}</p>
                         </div>
                            <div class="col-xs-12 col-md-6">
                                <form method="post" action="grouptour.do" class="btn-div">
                                <input type="hidden" name="action" value="getOne_ForDisplay">
                                <input type="hidden" name="groupTourSN" value="${groupTourVO.groupTourSN}">
                                <input class="btn btn-success" type="submit" value="More">
                                </form>
                            </div>
                        </div>
                        <c:if test="${groupTourVO.attendNumber >= groupTourVO.limitNumder}">
                        		<input class="btn btn-danger attend_btn" style="color: white; background-color: LightCoral; border: LightCoral;" type="button" value="已額滿" >
                        </c:if>
                </div>
                </div>
            </div>
            </c:forEach>


        </div>
    </div>
    
 
<div class="page2"><%@ include file="page2.file"%></div>
<jsp:include page="../share/footer.jsp" flush="true" />
<script src="https://kit.fontawesome.com/d3e24e4d81.js" crossorigin="anonymous"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        
</body>

<script>
// 	// 關鍵字搜索
// 		function filter(value) {
//     console.log(value);
// 		switch(value) {
// 			case "1":
// 				$(".forFilter").each(function(i, e) {
//                     if($(e).val()=="北部") {
//                         $(e).closest("div").show();
//                     }else{
//                         $(e).closest("div").hide();
//                     }
// 				})
// 				break;
// 			case "2":
//                  $(".forFilter").each(function(i, e) {
//                     if($(e).val()=="南部") {
//                         $(e).closest("div").show();
//                     }else{
//                         $(e).closest("div").hide();
//                     }
// 				})
// 				break;
// 			case "3":
//                 $(".forFilter").each(function(i, e) {
//                     if($(e).val()=="離島") {
//                         $(e).closest("div").show();
//                     }else{
//                         $(e).closest("div").hide();
//                     }
// 				})
// 				break;
// 			default:
//                 $(".forFilter").closest("div").show();
// 				break;
// 		}
// 	}
</script>

</html>