<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/5/6
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" import="com.baidu.ueditor.ActionEnter" %>
<html>
<head>
    <title></title>
</head>
<body>
<br>
<%--<%@include file="include.txt"%><br>--%>
<jsp:include page="include.txt"></jsp:include>
<%=new String(request.getParameter("password").toString().getBytes("ISO-8859-1"),"utf-8")%>
<jsp:useBean id="name" scope="request" class="q.User" />
<jsp:setProperty name="name" property="*" />
<%!int v = 0;%>
<%=3+2%>
<%="所谓"%>

<jsp:include page="forq3.jsp"></jsp:include>

<%="所谓"%>

<%--<jsp:forward page="fileReader.html"></jsp:forward>--%>
<%
  request.setCharacterEncoding("utf-8");
  response.getWriter().print(request.getParameter("v"));
  v++;
  out.print("v= "+this.v+"<br>");
  out.print((this==page)+"<br>");
  out.print(request.getServletPath()+"<br>");
  out.print(request.getServerName()+"<br>");
//  response.sendRedirect("http://baidu.com");
  if(pageContext.getServletContext()==application)
    out.print(1222);

%>

<jsp:getProperty name="name" property="loginName" />
<jsp:getProperty name="name" property="password" />
</body>
</html>

