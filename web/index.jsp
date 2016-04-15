<%@ page import="model.Resume" %>
<%@ page import="model.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/8
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%if(session.getAttribute("username")!=null)response.sendRedirect("/i");%>
<html>
  <head>
    <jsp:include page="static/htm/head.html"></jsp:include>
    <title>登录 - 学生信息管理系统</title>
  </head>
  <body>
    <jsp:include page="static/htm/navbar.jsp">
      <jsp:param name="w" value="0"></jsp:param>
    </jsp:include>
    <jsp:include page="static/htm/banner.jsp">
      <jsp:param name="title" value="登录"></jsp:param>
      <jsp:param name="content" value="登录用户，游客可以先进行注册，或查看学生信息。"></jsp:param>
    </jsp:include>

    <jsp:include page="static/htm/login.jsp"></jsp:include>
    <jsp:include page="static/htm/footer.html"></jsp:include>
  </body>
</html>
