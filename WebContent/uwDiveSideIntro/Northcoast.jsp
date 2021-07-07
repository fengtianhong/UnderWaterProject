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
                <img class="showpic1" src="<%=request.getContextPath()%>/uwDiveSideIntro/img/nc02.jpeg">
            </div>

            <div class="introlist">
                <div class="listitem">
                    <h4>潛場範圍</h4>
                    <h5>位於淡水河口以北的沙崙海水浴場，至基隆和平島附近的海岸</h5>
                </div>
                <div class="listitem">
                    <h4>附近潛點</h4>
                    <h5>
                        石門洞<br>
                        國聖埔至野柳隧道口<br>
                        野柳岬風景區
                    </h5>
                </div>
                <div class="listitem">
                    <h4>海洋地質</h4>
                    <h5>以火山岩及沉積岩為主的海岸，<br>而珊瑚礁極少</h5>
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
                    <h5>以多剌河魨、隆頭魚科的魚種最多</h5>
                </div>
            </div>
            
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
    