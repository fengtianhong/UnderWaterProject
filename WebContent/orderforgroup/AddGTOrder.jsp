<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orderforgroup.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.grouptour.model.*"%>

<%  
	// 抓取新增失敗時回傳的VO
	OrderForGroupVO orderForGroupVO = (OrderForGroupVO) request.getAttribute("orderForGroupVO");
	
	// userID groupTourSN 應該會放在Session
// 	Integer userID = (Integer) session.getAttribute("userID");
// 	pageContext.setAttribute("userID", userID);
// 	MemberService memSvc = new MemberService();
// 	pageContext.setAttribute("memberVO", memSvc.getone(userID));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add order for group</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<style>
	.main-container{
		margin: 0 auto;
		width: 900px;
	}
	.bar{
		height: 100px;
	}
	.row{
		margin-bottom: 20px;
	}
	.picture{
		width: 120px;
		height: 120px;
	}
	.list-group-image{
		width: 120px;
		height: 120px;
		/*圖片撐滿 */
		object-fit: cover;		
    	object-position: center;
	}
	table{
		text-align: start;
	    border-collapse:collapse;
	    width: 100%;
	}
/* 	td,td{ */
/* 	    padding-right: 50px; */
/* 	} */
	.price{
	    display: inline;
	    position: relative;
	    right: -36%;
    	bottom: 10px;
	    color: firebrick;
	    font-weight: 500;
	}
	.total{
		padding-right: 20px;
	}
	label{
		padding: 10px;
	}
	.confirm_btn{
		position: absolute;
	    right: 8%;
	    bottom: 9%;
	}
	.pay_bar{
		height: 120px;
	    margin: 0 auto;
	    padding-bottom: 24px;
	}
	

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />

<div class="bar"></div>
<div class="main-container">

<!------------ 商品資訊 ---------------->
<div class="row product">
<img class="pay_bar" src="<%=request.getContextPath()%>/orderforgroup/img/pay_bar.svg">


<div class="col-lg-12 product">
    <!-- Roitation Utilities -->
	<div class="card">
    	<div class="card-header py-3">
        	<h6 class="m-0 font-weight-bold text-secondary">step 1. 商品資訊</h6>
        </div>
        <div class="card-body text-center">
			<table>
			<tr>
				<td>
					<div class="picture">
					<img class="list-group-image" src="<%=request.getContextPath()%>/grouptour/GetImage.do?id=${groupTourVO.groupTourSN}" />
					</div>
				</td>
				<td>
					<div class="tourName">${groupTourVO.tourName}</div>
            		<div class="time">${groupTourVO.startTime} - ${groupTourVO.endTime}</div>
				</td>
				<td>
					<div class="pricedetail">$ ${groupTourVO.price}</div>
				</td>
			</tr>
			</table>

			<hr>
            <div class="price">
	            <span class="total">總金額</span>
	            <span>$ ${groupTourVO.price}</span>
            </div>
        </div>
     </div>
</div>

</div>


<!------------ 付款資訊 ---------------->
<div class="row pay">
<div class="col-lg-12 product">
    <!-- Roitation Utilities -->
	<div class="card">
    	<div class="card-header py-3">
        	<h6 class="m-0 font-weight-bold text-secondary">step 2. 付款方式</h6>
        </div>
        <div class="card-body text-center">
            <div>
            	<label>請選擇付款方式 : </label>
				<label><input type="radio" name="payment" value="in" disabled>貨到付款</label>
				<label><input type="radio" name="payment" value="credit" checked>信用卡付款</label>
				<br><small style="color:red;">套裝行程商品無法貨到付款，請見諒。</small>
            </div>
        </div>
     </div>
</div>
</div>

<!------------ 個人資訊 ---------------->
<jsp:useBean id="memberSvc" class="com.member.model.MemberService" />
<div class="row info">
<div class="col-lg-12 product">
    <!-- Roitation Utilities -->
	<div class="card">
    	<div class="card-header py-3">
        	<h6 class="m-0 font-weight-bold text-secondary">step 3. 個人資訊</h6>
        </div>
        <div class="card-body text-center">
<!-- FROM -->
		<form method="post" action="<%=request.getContextPath()%>/orderforgroup/orderforgroup.do">
		<table class="form">
			<tr>
				<td width="30%">訂購人</td>	
				<td><input type="TEXT" size="45" value="${memberSvc.getone(userID).userName}" required />
				</td>
			</tr>	
			<tr>
				<td>連絡電話</td>	
				<td><input type="TEXT" name="phone" size="45" value="${memberSvc.getone(userID).phone}" placeholder="請輸入手機號碼" required />
				</td>
			</tr>
			<tr>
				<td>身分證字號</td>	
				<td><input type="TEXT" name="personID" size="45" value="${memberSvc.getone(userID).personID}" required />
				</td>
			</tr>
			<tr>
				<td>出生日期</td>	
				<td><input type="TEXT" class="date" name="birthdate" size="45" value="${memberSvc.getone(userID).birthDate}" required />
				</td>
			</tr>
		</table>
		<input type="hidden" name="userID" value="${userID}">
		<input type="hidden" name="groupTourSN" value="${groupTourVO.groupTourSN}">
		<input type="hidden" name="totalPrice" value="${groupTourVO.price}">
		<input type="hidden" name="action" value="insert">
		<input class="confirm_btn btn btn-success" type="submit" value="確認">
		</form>
<!-- FROM -->
        </div>
     </div>
</div>

</div>

<!-- main-container-end --></div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errMsg}">
	<script>
	alert('請修正以下錯誤: ${errMsg}');
	
	</script>
</c:if>
<jsp:include page="../share/footer.jsp" flush="true" />
</body>
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
	       });

        
      });

</script>

</html>