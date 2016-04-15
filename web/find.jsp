<%@ page import="model.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/9
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="static/htm/head.html"></jsp:include>
  <title>信息搜索 - 学生信息管理系统</title>
</head>
<body>
<jsp:include page="static/htm/navbar.jsp"></jsp:include>
<jsp:include page="static/htm/banner.jsp">
  <jsp:param name="title" value="信息搜索"></jsp:param>
  <jsp:param name="content" value="按照学号/用户搜索唯一信息。"></jsp:param>
</jsp:include>
<%
  String v = request.getParameter("v");
  String t = request.getParameter("t");
  UserInfo info = new UserInfo();
  if(t.equals("id")) {
    info.setId(v);
    info.selectById();
  }else{
    info.setUsername(v);
    info.select();
  }
  boolean find = info.getName()!=null;
%>
<div class="am-container" style="margin-top: 40px">
  <h3>搜索 <%=v%> 的结果</h3>
  <div class="am-tabs">
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <li class="<%=t.equals("id")?"am-active":""%>"><a href="?t=id&v=<%=v%>">学号</a></li>
      <li class="<%=t.equals("user")?"am-active":""%>"><a href="?t=user&v=<%=v%>">用户</a></li>
    </ul>
    <div class="am-tabs-bd">
      <div class="am-tab-panel am-fade am-in <%=t.equals("id")?"am-active":""%>">
      </div>
      <div class="am-tab-panel am-fade <%=t.equals("user")?"am-active":""%>">
      </div>
    </div>
  </div>
<%if(!find){%>
  <h4>未找到</h4>
<%}else{%>
<div class="am-panel am-panel-default">
  <div class="am-panel-hd">
    <h4 class="am-panel-title" data-am-collapse="{parent: '#accordion', target: '#do-not-say-1'}">
      基本信息
    </h4>
  </div>
  <div id="do-not-say-1" class="am-panel-collapse am-collapse am-in">
    <div class="am-panel-bd">
      <form class="am-form">
        <fieldset>
          <div class="am-form-group">
            <label for="id">学号：</label>
            <input type="text" id="id" data-foolish-msg="至少8位的数字！" value="<%=info.getId()%>">
          </div>
          <div class="am-form-group">
            <label for="name">姓名：</label>
            <input type="text" id="name" placeholder="你的姓名" value="<%=info.getName()%>" >
          </div>
          <div class="am-form-group">
            <label>性别：</label>
            <label class="am-radio-inline">
              <input type="radio" <%=info.getSex().equals("m")?"checked":""%> value="m" name="sex" data-am-ucheck="" class="am-ucheck-radio"><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 男
            </label>
            <label class="am-radio-inline">
              <input type="radio" <%=info.getSex().equals("w")?"checked":""%> name="sex" data-am-ucheck="" value="w" class="am-ucheck-radio"><span class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 女
            </label>
          </div>
          <div class="am-form-group">
            <label for="birth">生日：</label>
            <div class="am-input-group">
              <input type="text" id="birth" value="<%=info.getBirth()%>" class="am-form-field"  placeholder="生日">
            </div>
          </div>
          <div class="am-form-group">
            <label>照片：</label>
            <div id="fileList" class="uploader-list">
              <img src="img.jsp?v=<%=info.getUsername()%>" class="am-thumbnail">
            </div>
          </div>
          <%if(info.getResume()!=null){%>
          <div class="am-form-group">
            <label>简历：</label>
            <a class="am-btn am-btn-default" href="resume.jsp?v=<%=info.getUsername()%>" download="<%=info.getUsername()%>_resume">查看简历</a>
          </div>
          <%}%>
        </fieldset>
      </form>
    </div>
  </div>
</div>
</div>
<%}%>
</body>
</html>
