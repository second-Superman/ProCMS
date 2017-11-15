<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'login.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <jsp:include page="link.jsp"></jsp:include>
</head>

<body class="hold-transition login-page">


<div class="login-box">
    <div class="login-logo">
        <a href="#"><b>HZJ</b>后台管理系统V1.0</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">在此输入你的登录信息</p>


        <div class="form-group has-feedback">
            <input type="text" id="username" class="form-control"
                   placeholder="账户名称"> <span
                class="fa fa-user form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input type="password" id="password" class="form-control"
                   placeholder="账户密码"> <span
                class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>

        <div class="row">
            <div class="col-xs-6">
                <div class="form-group has-feedback">
                    <input style="height: 50px" type="text" id="kaptcha"
                           class="form-control" placeholder="输入验证码">
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-6">
                <img id="imgKaptcha" onclick="setKaptcha()">
            </div>
            <!-- /.col -->
        </div>
        <div class="row">
            <div class="col-xs-8">
                <div class="checkbox icheck">
                    <label> <input type="checkbox"> 记住我
                    </label>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-4">
                <button type="button" onclick="loginAjax()" class="btn btn-primary btn-block btn-flat">登录</button>
            </div>
            <!-- /.col -->
        </div>

        <!-- /.social-auth-links -->
        <a href="#">返回首页面</a><br>
        <p class="text-center">技术支持：2307800400</p>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->


<!--B3-Modal-遮罩-->
<div id="m1" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <p>正在登陆.............</p>
            </div>
        </div>
    </div>
</div>

<!--错误提出弹出框框-->
<div id="m2" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">信息提示框</h4>
            </div>
            <div class="modal-body">
                <p id="mess"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>

<jsp:include page="js.jsp"></jsp:include>

<script>


    //ajax请求后端
    //ajax:浏览器异步请求服务器，XMLHttpRequest（XHR）
    //<%=basePath%>LoginServletJSON.action
    // var url=<%=basePath%>LoginServletJSON.action;
    function loginAjax() {
        console.log('dddddddddddddddddd');
        $('#m1').modal('show');
        var a = $('#username').val();
        var b = $('#password').val();
        var c = $('#kaptcha').val();
        var url = '<%=basePath%>LoginServletJSON.action';
        var method = "post";
        //开始请求
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'post',
            data: {
                username: a,
                password: b,
                kaptcha: c
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('请求错误.....');
                console.log('no');
                //遮罩层关闭
                $('#m1').modal('hide');
                //重置验证码
                setKaptcha();
                //设置错误消息
                $('#mess').text('请求错误.....');
                $('#m2').modal('show');

            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                console.log(textStatus);
                if (data.state == 1) {
                    console.log('okokoko');
                    window.location = '<%=basePath%>admin/NewTypeServlet.action?method=findbyall';
                    $('#m1').modal('hide');
                }
                else {
                    console.log('no');
                    //遮罩层关闭
                    $('#m1').modal('hide');
                    //重置验证码
                    setKaptcha();
                    //设置错误消息
                    $('#mess').text(data.mess);
                    $('#m2').modal('show');
                }
            }
        });


    }


    function setKaptcha() {

        $('#imgKaptcha').attr('src', '<%=basePath%>KaptchaServlet.action?' + Math.floor(Math.random() * 100));

    }


    $(function () {
        //设置遮罩层
        $('#m1').modal({
            keyboard: false,
            show: false
        });
        //信息提示框
        $('#m2').modal({
            keyboard: false,
            show: false
        });


        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });


        setKaptcha();


    });
</script>
