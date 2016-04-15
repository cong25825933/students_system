<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/8
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="static/htm/head.html"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="static/htm/navbar.jsp"></jsp:include>
<jsp:include page="static/htm/banner.jsp">
  <jsp:param name="title" value="注册"></jsp:param>
  <jsp:param name="content" value="注册用户，注册后能够录入本人的信息，查看本人信息。"></jsp:param>
</jsp:include>
<jsp:include page="static/htm/reg.html"></jsp:include>
<jsp:include page="static/htm/footer.html"></jsp:include>
</body>
</html>
