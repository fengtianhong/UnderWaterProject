<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<%
	// 抓取新增失敗時回傳的VO
	MemberVO memberVO = (MemberVO) request.getAttribute("MemberVO");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
    </head>
<body>
	<div class="container">
		<section>				
			<div id="container_demo" >
				<a class="hiddenanchor" id="toregister"></a>
				<a class="hiddenanchor" id="tologin"></a>
					<div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="login.do" method="post" name="form" > 
                                <h1>登 入</h1> 
								<p><input type="hidden" name="test" id="randomstring" value="<%=session.getAttribute("randomString")%>" onblur="VerificationCode(this.value)"><p>
                                <p> 
                                    <label for="username" class="uname"> 帳號 </label>
                                    <input id="username" name="account" required="required" onblur="Email(this.value)" type="text" placeholder="myemail@mail.com"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd"> 密碼 </label>
                                    <input id="password" name="pwd" required="required" type="password" placeholder="password" /> 
                                </p>
								<div>驗證碼：
									<input type="text" name="checknum" id="checknum" onblur="Manual_VerificationCode(this.value)"/>
									<img src="<%=request.getContextPath()%>/member/IdentityServlet" id="identity" onload="btn.disabled=false;" /> 
									<input type="button" value=" 換個圖片 " id="btn" onclick="reloadImage()">
									<div id="wrap-inline">
										<p style="color:red;">${msg}</p>
                            			<p class="login button"> <input type="submit" value="登入" /> </p>
									</div>
								</div>
                				<p class="change_link">還沒成為會員 ?
									<a href="register.jsp" class="to_register">註冊</a>
									<a href="forgetpassword.jsp" class="to_register">忘記密碼</a>
								</p>
                            </form>
<!--                         </div> -->

<!--                       	<div id="register" class="animate form"> -->
<!--                             <form  autocomplete="on" action="member.do" onsubmit="checkform(this)" method="post">  -->
<!--                                 <h1> 會員註冊 </h1>  -->
<%--                                 <div style="text-align:center;"><c:if test="${msg!=null}"> ${msg}</c:if></div> --%>
<!--                                 <p>  -->
<!--                                     <label for="Emailsignup" class="youmail" >帳號:</label> -->
<%--                                     <input id="Emailsignup" name="account" required="required" type="email" value="<%=(memberVO == null) ? "" : memberVO.getAccount()%>"/>  --%>
<!--                                 </p> -->
<!-- 								<p> -->
<%-- 									<input type="button" name="btn_account" class="btn_checkaccount" value="檢查帳號可用性" size="30" id="btn_account" onclick="CheckAccountUse()"><p>${used} --%>
<!-- 								</p> -->
<!--                                 <p>  -->
<!--                                     <label for="Passwordsignup" class="youpasswd" >密碼: </label> -->
<%--                                     <input id="Passwordsignup" name="pwd" required="required" type="password" value="<%=(memberVO == null) ? "" : memberVO.getPwd()%>"/> --%>
<!--                                 </p> -->
<!--                                 <p>  -->
<!--                                     <label for="Passwordsignup_confirm" class="youpasswd">再次輸入密碼 </label> -->
<!--                                     <input id="Passwordsignup_confirm" name="repwd" required="required" type="password"/> -->
<!--                                 </p> -->
<!-- 								 <p>  -->
<!--                                     <label for="Name">姓名:</label> -->
<%--                                     <input id="Name" name="userName" required="required" type="text" value="<%=(memberVO == null) ? "" : memberVO.getUserName()%>" />  --%>
<!--                                 </p> -->
<!-- 								<p>  -->
<!--                                     <label for="Nickname">暱稱:</label> -->
<%--                                     <input id="Nickname" name="nickName" required="required" type="text" value="<%=(memberVO == null) ? "" : memberVO.getNickName()%>" />  --%>
<!--                                 </p> -->
<!-- 								<p>  -->
<!--                                     <label for="Personid" >身分證字號:</label> -->
<%--                                     <input id="Personid" name="personID"  type="text" value="<%=(memberVO == null) ? "" : memberVO.getPersonID()%>" />  --%>
<!--                                 </p> -->
<!-- 								<p>  -->
<!--                                     <label for="sex">性別:</label> -->
<%--                                     <input id="sex" type="radio" name="gender" value="0" required="required"  placeholder="必填"  ${memberVO.equals("0") ? 'selected' : ''}>男  --%>
<%-- 				    				<input 	    type="radio" name="gender" value="1"  ${memberVO.equals("1") ? 'selected' : ''}>女 --%>
<!--                                 </p> -->
<!-- 								<p>  -->
<!--                                     <label for="Birthdate">生日:</label> -->
<%--                                     <input id="Birthdate" name="birthDate" type="date" value="<%=(memberVO == null) ? "" : memberVO.getBirthDate()%>" />  --%>
<!--                                 </p> -->
<!-- 								<p>  -->
<!--                                     <label for="Telephone">電話:</label> -->
<%--                                     <input id="Telephone" name="phone"  type="text" value="<%=(memberVO == null) ? "" : memberVO.getPhone()%>" />  --%>
<!--                                 </p>			 -->
<!-- 								<p>  -->
<!--                                     <label for="Address">地址:</label> -->
<%--                                     <input id="Address" name="address"  type="text" value="<%=(memberVO == null) ? "" : memberVO.getAddress()%>" />  --%>
<!--                                 </p> -->
<!-- 								<p>  -->
<!--                                     <label for="Certification">潛水證照:</label> -->
<!--                                     <select name="certification" size="2"> -->
<%-- 										<option value="0" ${(memberVO.certification==null)? 'selected':'' }>無 --%>
							
<%-- 										<option value="1" ${(memberVO.certification==1)? 'selected':'' }>PADI --%>
<!-- 										OW / SSI OW -->
<%-- 										<option value="2" ${(memberVO.certification==2)? 'selected':'' }>PADI --%>
<!-- 										AOW / SSI AOW -->
<!-- 									</select> -->
<!--                                 </p> -->
<!-- 								<p>  -->
<!-- 								<div> -->
<!--                                     <label for="Certificationpic">證照照片:</label> -->
<!--                                     <input id="Certificationpic" name="certificationPic" type="file" accept="image/*" />  -->
<!--                                 </div> -->
<!--                                 <div> -->
<!--                                     <label for="PersonPhoto">個人照片:</label> -->
<!--                                     <input id="PersonPhoto" name="personPhoto" type="file" accept="image/*" />  -->
<!--                                 </div>	 -->
<!-- 								<p>  -->
<!--                                     <label for="CertificationPic">驗證碼:</label> -->
<!--                                     <input type="text" name="checknum" id="checknum" onblur="Manual_VerificationCode(this.value)"/> -->
<%-- 									<img src="<%=request.getContextPath()%>/member/IdentityServlet" id="identitye" onload="btn.disabled=false;" />  --%>
<!-- 									<input type="button" value=" 換個圖片 " id="btn" onclick="reloadImage()"> -->
<!--                                 </p> -->
<!--                                 <p class="signin button">  -->
<!-- 									<input type="submit" value="註冊"/>  -->
<!-- 								</p> -->
<!--                                 <p class="change_link"> 已經是會員 ? -->
<!-- 									<a href="register.jsp" class="to_register"> 會員登入 </a> -->
<!-- 								</p> -->
<!--                             </form> -->
                        </div>
						
                    </div>
                </div>  
            </section>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	
	
	
	
	function checkform(form){
		
		if($("#Passwordsignup_confirm").val() != $("#Passwordsignup").val()){
			alert("兩次輸入密碼不正確");
		}
// 		if(form.account.value == ""){
// 			alert("帳號不得空白");
// 			return false;
// 		}else if(form.pwd.value == ""){
// 			alert("密碼不能空白");
// 			return false;
// 		}else(form.pwd.value != form.repwd.value ) {
// 			alert("兩次輸入密碼不正確");
// 			return false;
// 		}else{
// 			return true;
// 		}
	}	

		function Email(strEmail){
		if(strEmail.indexOf('@')!= - 1)
			return true;
		else{
			alert("email格式不正確")
		}
	}

		function reloadImage() {
// 			$('#identity').each(function(i,e){
// 				alert($(e));
// 				$(e).attr('src','IdentityServlet?ts=' + new Date().getTime());
// 			})
// 			document.getElementById('btn').disabled = true;
			document.getElementById('identity').src = 'IdentityServlet?ts='
					+ new Date().getTime();
			document.getElementById('identitye').src = 'IdentityServlet?ts='
					+ new Date().getTime();
		}
	</script>
	
</html>