<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Password Reset</title>
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
                                <h1>重置密碼成功</h1> 
								<div class="text">您帳號的密碼已經重置，請到email信箱確認密碼。</div>
                            <div>
								<p class="change_link">
									<a href="../index.jsp" class="to_register">回首頁</a>
									
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
	
</script>
</html>