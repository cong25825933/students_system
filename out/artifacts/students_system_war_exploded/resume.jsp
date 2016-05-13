<%@ page import="java.io.OutputStream" %>
<%@ page import="model.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/9
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" %>
<%
  OutputStream os = response.getOutputStream();
  UserInfo userInfo = new UserInfo();
  userInfo.setUsername(request.getParameter("v"));
  if(userInfo.selectResume()){
    response.setContentType(userInfo.getResume_type());
    byte[] buf = new byte[1024*1024];
    int v = -1;
    while ((v=userInfo.getResume().read(buf))!=-1){
      os.write(buf,0,v);
    }
    os.flush();
    os.close();
  }else{
    return;
  }
%>
