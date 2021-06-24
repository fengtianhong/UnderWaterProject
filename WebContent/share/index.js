$(function () {
    $(window).scroll(function () {
      var scrollVal = $(this).scrollTop();
      $("span.howDive").text((scrollVal/20).toFixed(1)+ "M");
    //   $("span.howDive").text(scrollVal+ "M");

      if(scrollVal >88){
        $("nav.navbar.fixed-top.navbar-expand-md.navbar-light a").css({
            "color": "white",
            "transition": "0.5s",
            "cursor": "pointer"
        });  
      }else{
        $("nav.navbar.fixed-top.navbar-expand-md.navbar-light a").css({
            "color": "#b0b0b0",
            "transition": "0.5s",
            "cursor" :"pointer"
        });
      }
      $(".divePosition").css({
        "margin-top": (scrollVal/1.3)-130
      });
      var pos = $(this).scrollTop();  //卷軸位置
      var wh = $(window).height();  //視窗高度(底下)
      var dh = $(document).innerHeight()  //html文件高度
      var percentage = (pos/(dh-wh));
      

      $(".divePosition").css({
        "bottom": wh*0.7*(1-percentage) 
      });
    });
  });