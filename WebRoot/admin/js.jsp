<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!-- dialog -->
<jsp:include page="dialog.jsp"/>


<!-- jQuery 3 -->
<script src="<%=basePath%>res/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=basePath%>res/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script
        src="<%=basePath%>res/datatables.net/js/jquery.dataTables.min.js"></script>
<script
        src="<%=basePath%>res/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script
        src="<%=basePath%>res/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=basePath%>res/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=basePath%>res/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath%>res/dist/js/demo.js"></script>
<!--holder-->
<script src="<%=basePath%>res/holder/holder.js"></script>

<script type="text/javascript">

    //边栏菜单链接
    function aClick(href) {
        window.location = href;
    }
    //弹出删除框
    function delDialogOpen(id, title, srcUrl) {
        //删除具体信息的描述
        $('#delTitle').text(title);
        //传递参数到dialog框的删除按钮，里面的自定义属性objectId里面
        $('#delBtn').data('objectId', id);

        //传递参数到dialog框的删除按钮，里面的自定义属性srcUrl里面
        //目的是告知到底去那个servlet
        $('#delBtn').data('srcUrl', srcUrl);
        //dialog打开
        $('#delDialog').modal('show');

    }

    //删除执行
    function delAction() {
        //删除dialog消失
        $('#delDialog').modal('hide');
        //遮罩板跳出来
        $('#m1').modal('show');
        //取出要删除的对象的ID
        var id = $('#delBtn').data('objectId');
        var srcUrl = $('#delBtn').data('srcUrl');
        var url = '<%=basePath%>admin/' + srcUrl;
        //请求后端，开始删除
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'post',
            data: {
                method: 'del',
                id: id
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log('请求错误.....');
                console.log('no');
                //遮罩层关闭
                $('#m1').modal('hide');
                //设置错误消息
                $('#mess').text('请求错误.....');
                $('#m2').modal('show');

            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                console.log(textStatus);
                if (data.state == 1) {
                    window.location = '<%=basePath%>admin/' + srcUrl + '?method=findbyall';
                }
                else {
                    //遮罩层关闭
                    $('#m1').modal('hide');
                    //设置错误消息
                    $('#mess').text(data.mess);
                    $('#m2').modal('show');
                }
            }
        });


    }


    function getTime() {

        var weekData = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];

        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;

        var day = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        var seconds = date.getSeconds();
        var week = weekData[date.getDay()];
        return year+'年'+month+'月'+day+'日'+' '+hours+':'+minutes+':'+seconds+' '+week;

    }


    //渲染完毕后执行
    $(function () {



        //计时器
       window.setInterval(function () {
           $('#timeLi').text(getTime());
       },500);






    });


</script>



