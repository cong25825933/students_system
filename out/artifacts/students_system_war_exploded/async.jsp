<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/10
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%
  out.print("Done<br/>");
  if(request.isAsyncStarted()){
    request.getAsyncContext().complete();
  }
%>