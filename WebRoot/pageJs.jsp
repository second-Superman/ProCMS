<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script src="<%=basePath%>res/jquery/dist/jquery.js"></script>
<script src="<%=basePath%>res/bootstrap/dist/js/bootstrap.js"></script>
<script src="<%=basePath%>res/STAR-ZERO-jquery-ellipsis/dist/jquery.ellipsis.js"></script>
<script>

    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }




    $(function () {

        $('.title').ellipsis({
            row: 2
        });
        
         $('.title-sidebar').ellipsis({
            row: 1
        });

        //设置nav中的active

        var  navTag=getUrlParam("newsTypeId");

        if(navTag==null){
            navTag=-1;
        }
        console.log(navTag);

        $('.nav>li').each(function(i){

            if($(this).data('tag')==navTag){
                $(this).addClass("my-active");
                return;
            }

        });

    });

</script>