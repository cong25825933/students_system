<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/8
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="static/htm/head.html"></jsp:include>
    <title>个人信息查看 - 学生信息管理系统</title>
</head>
<body>
<jsp:include page="static/htm/navbar.jsp">
  <jsp:param name="w" value="2"></jsp:param>
</jsp:include>
<jsp:include page="static/htm/banner.jsp">
  <jsp:param name="title" value="个人信息查看"></jsp:param>
  <jsp:param name="content" value="查看个人详细的录入信息"></jsp:param>
</jsp:include>
<jsp:include page="static/htm/info.jsp"></jsp:include>
<jsp:include page="static/htm/footer.html"></jsp:include>
</body>
</html>
