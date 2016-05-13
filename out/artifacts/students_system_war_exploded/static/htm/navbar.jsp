<%@ page import="model.User" %>
<%
    String w = request.getParameter("w");
    String[] ss = new String[4];
    if(w!=null)
        ss[Integer.parseInt(w)] = "am-active";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="am-topbar-inverse">
    <div class="am-container">
        <h1 class="am-topbar-brand">
            <a href="#">学生信息管理系统</a>
        </h1>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <%--<li class="<%=ss[0]%>"><a href="i">首页</a></li>--%>
            <li class="<%=ss[1]%>"><a href="import">信息录入</a></li>
            <%--<li class="<%=ss[2]%>"><a href="infoshow">信息查看</a></li>--%>
            <%--<li class="<%=ss[3]%>"><a href="#">随便看看</a></li>--%>
        </ul>
        <%if(session.getAttribute("username")==null){%>
        <div class="am-topbar-right">
            <a class="am-btn am-btn-primary am-topbar-btn am-btn-sm" href="reg" data-am-dropdown-toggle>注册</a>
        </div><%}%>

        <div class="am-topbar-right">
            <%if(session.getAttribute("username")!=null){%>
            <form method="get" action="find" class="am-topbar-form am-topbar-left am-form-inline" role="search">
                <div class="am-input-group">
                    <input type="text" name="v" class="am-form-field am-input-sm" placeholder="搜索学生信息 学号/用户">
                    <input type="hidden" name="t" value="id">
                <span class="am-input-group-btn">
                    <button class="am-btn am-btn-primary am-btn-group-sm" type="submit"><span class="am-icon-search"></span></button>
                </span>
                </div>
            </form>
            <label class="am-vertical-align-middle" style="margin: 0px 20px 0px"><%=((User)session.getAttribute("username")).getUsername()%></label>
            <a class="am-btn am-btn-danger am-topbar-btn am-btn-sm" id="exit">退出</a>
            <%}%>
            <%if(session.getAttribute("username")==null){%>
            <a class="am-btn am-btn-secondary am-topbar-btn am-btn-sm" href="i">登录</a>
            <%}%>
        </div>
    </div>
    </div>
</header>
    <script>
        $('#exit').click(function () {
            $.alertModal("确认退出吗？", function () {
                $.post(AJAX_URL,{act:'exit'}, function (d) {
                    location.href="i";
                })
            });
        })
    </script>
    <div class="am-modal am-modal-confirm" tabindex="-1" id="my-alertModal">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">提示</div>
            <div class="am-modal-bd"></div>
            <div class="am-modal-footer">
                <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                <span class="am-modal-btn" data-am-modal-confirm>确定</span>
            </div>
        </div>
    </div>
    <div class="am-modal am-modal-no-btn" tabindex="-1" id="my-infoModal">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">
                <span></span>
                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
            </div>
            <div class="am-modal-bd">

            </div>
        </div>
    </div>

    <div class="am-modal am-modal-no-btn" tabindex="-1" id="my-noBorderModal">
        <div class="am-modal-dialog">
            <%--<div class="am-modal-bd">--%>

            <%--</div>--%>
        </div>
    </div>
</div>