<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 	Integer userID = Integer.parseInt(session.getAttribute("userID").toString());
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.getone(userID);
	
	pageContext.setAttribute("memberVO", memberVO);
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<!-- 	<link href="css/app.css" rel="stylesheet"> -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<title>Under Water 會員</title>
<link rel="stylesheet" href="../share/index.css">
<link rel="stylesheet" href="../vendors/bootstrap/css/bootstrap.min.css">
<style>
	.content{
	    width: 1000px;
    	margin: 0 auto;
	}

</style>
</head>
<body>
<jsp:include page="../share/navbar.jsp" flush="true" />
<jsp:include page="../share/member/Mheader.jsp" flush="true"/>
<!-- 	<div class="wrapper"> -->
<!-- 		<div class="main"> -->
<!-- 			<main class="content"> -->
<!-- 				<div class="container-fluid p-0"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-3 col-xl-2"> -->

<!-- 							<div class="card"> -->
<!-- 								<div class="list-group list-group-flush" role="tablist"> -->
<!-- 									<a class="list-group-item list-group-item-action" href="personinfo.jsp" role="tab"> 會員資訊 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action active" href="personinfochange.jsp" role="tab"> 資訊變更</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="personchangepwd.jsp" role="tab"> 密碼變更</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="searchmember.jsp" role="tab"> 尋找會員 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增</a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a>  -->
<!-- 									<a class="list-group-item list-group-item-action" href="#" role="tab"> 待新增 </a> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 						<div class="col-md-9 col-xl-10"> -->
<!-- 							<div class="tab-content"> -->
<!-- 								<div class="tab-pane fade show active" id="account" -->
<!-- 									role="tabpanel"> -->

<!-- 									<div class="card"> -->
										
										<div class="card-body" style="margin-top:25px;">
											<c:if test="${not empty successMsgs}">
													<font style="color:red"></font>
													<c:forEach var="message" items="${successMsgs}">
														<a style="color:red">${message}</a>
													</c:forEach>
												</c:if>
											<form action="PersonChangeServlet.do" method="post" enctype="multipart/form-data">
												<div class="row">
													<div class="col-md-8">
														<div class="form-group" style="margin-left:70px;">
															<label for="account">帳號:
																<a style="margin-left:25px"   id="account">${memberVO.account}</a>
																<input type="hidden" name="account" value="${memberVO.account}">
															</label> 
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="nickname">暱稱:
																<input type="text" style="margin-left:25px" size="20" name="nickName" id="nickname" value="${memberVO.nickName}">
															</label>
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="username">姓名:
																<input type="text" style="margin-left:25px" name="userName" size="20" id="username" value="${memberVO.userName}">
															</label> 
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="gender">性別:
																<input name="gender" type="radio" value="0"${(memberVO.gender=="0")? 'checked' : ''} style="margin-left:25px">男
																<input name="gender" type="radio" value="1"${(memberVO.gender=="1")? 'checked' : ''} style="margin-left:25px">女
															</label> 
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="birthdate">生日:
																<input name="birthdate" type="text" value="${memberVO.birthDate}" class="birthdate" style="margin-left:25px">
															</label> 
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="phone">連絡電話:
																<input type="text" style="margin-left:-3px" size="20" id="phone" value="${memberVO.phone}">
															</label>
														</div>
														<div class="form-group" style="margin-left:70px;">
															<div>證照:
																<input type="radio" name="certification" value="0"${(memberVO.certification=="0")? 'checked' : ''} style="margin-left:25px">未持有
																<input type="radio" name="certification" value="1"${(memberVO.certification=="1")? 'checked' : ''}>PADI OW / SSI OW
																<input type="radio" name="certification" value="2"${(memberVO.certification=="2")? 'checked' : ''}>PADI AOW / SSI AOW
															</div> 
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="personid">身分證字號:
																<input type="text" name="personID" id="personid" size="18" value="${memberVO.personID}">
															</label> 
															
														</div>
														<div class="form-group" style="margin-left:70px;">
															<label for="address">通訊地址:
																<input type="text" id="address" name="address" size="19" value="${memberVO.address}" style="margin-left:3px">
															</label> 
															
														</div>
													</div>
													<div class="col-md-4">
														<div class="text-center">
															<img id="showimgperson" src="GetImagepersonPhoto.do?userid=${memberVO.userID}" class="rounded-circle img-responsive mt-2" width="128" height="128" />
															<div class="mt-2">
																<input type="file" name="personPhoto" onchange="showImgperson(this)" value="上傳照片"/>
															</div>
														</div>
														<div class="mt-2"></div>
														<div class="text-center">
															<img id="showimg" src="GetImage.do?userid=${memberVO.userID}" style="" width="128" height="128"/>
															<div class="mt-2">
																<input type="file" name="certificationPic" onchange="showImg(this)" value="上傳證照"/>
																
															</div>
														</div>
													</div>
													<input type="hidden" name="action" value="update_info">
													<input type="hidden" name="userID" value="${memberVO.userID}">
												</div>
												<button type="submit" class="btn btn-primary" style="margin-left:140px">送出</button>
											</form>
											<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
										</div>
<!-- 									</div> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				</main> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
<jsp:include page="../share/member/Mfooter.html" flush="true"/>    
<jsp:include page="../share/footer.jsp" flush="true" />
<!-- <script src="js/vendor.js"></script> -->
<!-- <script src="js/app.js"></script> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->

<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
$('#personinfochange').addClass('active');

$.datetimepicker.setLocale('zh');
var today = new Date();
$('.birthdate').datetimepicker({
	theme: '',          //theme: 'dark',
    timepicker: false,   //timepicker: false,預設true
//     step: 60,            //step: 60 (這是timepicker的預設間隔60分鐘)
       format: 'Y-m-d',
//	       value: new Date(),
    //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
    //startDate:	        '2017/07/10',  // 起始日
    //minDate:           '-1970-01-01', // 去除今日(不含)之前
    maxDate:           '+1970-01-01'  // 去除今日(不含)之後
 });


function showImg(thisimg) {
	var file = thisimg.files[0];
	if(window.FileReader) {
		var fr = new FileReader();
		
		var showimg = document.getElementById('showimg');
		fr.onloadend = function(e) {
		showimg.src = e.target.result;
	};
	fr.readAsDataURL(file);
	showimg.style.display = 'block';
	}
}

function showImgperson(thisimg) {
	var file = thisimg.files[0];
	if(window.FileReader) {
		var fr = new FileReader();
		
		var showimg = document.getElementById('showimgperson');
		fr.onloadend = function(e) {
		showimgperson.src = e.target.result;
	};
	fr.readAsDataURL(file);
	showimgperson.style.display = 'block';
	}
}
</script>


</body>
</html>