<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/9
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="static/htm/head.html"></jsp:include>
    <title>信息录入 - 学生信息管理系统</title>
</head>
<body>
<jsp:include page="static/htm/navbar.jsp">
  <jsp:param name="w" value="1"></jsp:param>
</jsp:include>
<jsp:include page="static/htm/banner.jsp">
  <jsp:param name="title" value="信息录入"></jsp:param>
  <jsp:param name="content" value="学生信息录入，可以进行修改。"></jsp:param>
</jsp:include>
<jsp:include page="static/htm/import.jsp"></jsp:include>

<jsp:include page="static/htm/footer.html"></jsp:include>
</body>
</html>
