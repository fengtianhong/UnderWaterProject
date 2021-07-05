<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>ForgetPassword</title>
	<link rel="stylesheet" type="text/css" href="css/demo.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
</head>
<style>
	.text{
		width:440px;
		margin-top:62px;
		margin-bottom:128px;
		margin-left:17px;
	}
</style>
<body>
<div class="container">
		<section>				
			<div id="container_demo" >
				<a class="hiddenanchor" id="toregister"></a>
				<a class="hiddenanchor" id="tologin"></a>
					<div id="wrapper">
                        <div id="login" class="animate form">
                            <form  action="Heisenberg.do" method="post" name="form" > 
                                <h1>忘記密碼</h1> 
                                <div>
                                	<p style="margin-bottom:40px; margin-top:20px;"> 
                                    	<label for="username" class="uname" > 請輸入帳號 </label>
                                    	<input id="username" name="account" required="required" onblur="Email(this.value)" type="text" placeholder="myemail@mail.com"/>
                               		</p>
                               		<p style="color:red;">${errMsg}</p>
                                </div>
								<p class="login button" style="margin-bottom:20px;"> 
									<input type="submit" value="確定送出" /> 
								</p>
                				
                            </form>
                            <div>
								<p class="change_link">
									<a href="../index.jsp" class="to_register">回首頁</a>
									<a href="javascript:history.back()" class="to_register">回上一頁</a>
								</p>
							</div>
                        </div>
						
                    </div>
                </div>  
            </section>
        </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
function Email(strEmail){
	if(strEmail.indexOf('@')!= - 1)
		return true;
	else{
		alert("email格式不正確")
	}
}
</script>
</html>