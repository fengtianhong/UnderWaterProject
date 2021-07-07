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
                <img class="showpic1" src="<%=request.getContextPath()%>/uwDiveSideIntro/img/uw05.jpeg">
            </div>

            <div class="introlist">
                <div class="listitem">
                    <h4>潛場範圍</h4>
                    <h5>台灣第二大附屬島嶼<br>
                        位於距台灣本島65公里海面上</h5>
                </div>
                <div class="listitem">
                    <h4>附近潛點</h4>
                    <h5>
                        新舊開元港、五孔洞、朗島、雙獅岩
                    </h5>
                </div>
                <div class="listitem">
                    <h4>海洋地質</h4>
                    <h5>珊瑚礁隆起環繞於四周，海底屬峭壁地形，夏季也受颱風侵襲而使海岸形成奇形怪狀的海蝕崖</h5>
                </div>
                <div class="listitem">
                    <h4>全年氣候</h4>
                    <h5>
                        台灣北部海域夏季的平均水溫約為24度，
                        冬季約為20度，
                        能見度約 3~8 公尺。<br>
                        四月至十月較適合潛水，
                        十一月至三月因東北季風影響海況較差不適合潛水，
                        尤其風浪大時海況會變得很惡劣</h5>
                </div>
                <div class="listitem">
                    <h4>海洋生態</h4>
                    <h5>蘭嶼污染少且海流強勁海水清澈，珊瑚礁及魚類繁茂與美麗堪稱全台之冠</h5>
                </div>
            </div>
            
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
