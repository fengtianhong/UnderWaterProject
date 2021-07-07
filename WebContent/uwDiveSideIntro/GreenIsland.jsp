<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>uwDiveSideIntro - 台灣各地潛點介紹</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        
		<style type="text/css">
			body {
				background-image: url(../uwDiveSideIntro/img/iStock_42073526_SMALL_1024.jpeg);
				background-repeat: no-repeat;
				background-size:cover;
			}
		</style>

    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">台灣各地潛點介紹</div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/Northcoast.jsp">北海岸潛場</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/NEcoast.jsp">東北角潛場</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/Huadong.jsp">花東潛場</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/Kenting.jsp">墾丁潛場</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/Penghu.jsp">澎湖潛場</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/GreenIsland.jsp">綠島潛場</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/uwDiveSideIntro/Lanyu.jsp">蘭嶼潛場</a>
                </div>
                 <div class="sidebar-heading border-bottom bg-light">潛水社群討論區</div>
                 <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="<%=request.getContextPath()%>/forumArticle/forumArticle.jsp">討論區</a>
                </div>
            </div>
            <div class="listpic">
                <img class="showpic1" src="<%=request.getContextPath()%>/uwDiveSideIntro/img/uw04.jpeg">
            </div>

            <div class="introlist">
                <div class="listitem">
                    <h4>潛場範圍</h4>
                    <h5>綠島位於台東東方海上，與台東相距 30公里，與蘭嶼相距 75公里</h5>
                </div>
                <div class="listitem">
                    <h4>附近潛點</h4>
                    <h5>
                        中寮港海域<br>
                        石朗區<br>
                        龜彎<br>
                        海參坪
                    </h5>
                </div>
                <div class="listitem">
                    <h4>海洋地質</h4>
                    <h5>海底火山噴發熔岩冷凝而成的火山島嶼<br>
                        長年受風化及海蝕作用形成曲折多變的海岸景觀，又海域黑潮主流經過而珊瑚礁遍佈</h5>
                </div>
                <div class="listitem">
                    <h4>全年氣候</h4>
                    <h5>
                        終年可潛水，水溫約為25~28度，冬季20度左右 潛水時可隨著季節變化而調整到背風面</h5>
                </div>
                <div class="listitem">
                    <h4>海洋生態</h4>
                    <h5>石珊瑚、鹿角珊瑚、穗珊瑚、軟珊瑚、紅扇珊瑚等、豆丁海馬、鸚哥魚、小丑魚...等</h5>
                </div>
            </div>
            
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
