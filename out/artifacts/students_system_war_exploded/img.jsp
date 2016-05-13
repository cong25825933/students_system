<%@ page import="model.RandomImg" %>
<%@ page import="model.UserInfo" %>
<%@ page import="java.io.OutputStream" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/8
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jepg" language="java" %>
<%
  String v = request.getParameter("v");
  UserInfo userInfo = new UserInfo();
  userInfo.setUsername(v);
  if(userInfo.selectPhoto()){
    OutputStream os =response.getOutputStream();
    byte[] buf = new byte[1024*1024];
    int l = -1;
    while ((l=userInfo.getPhoto().read(buf))!=-1){
      os.write(buf,0,l);
    }
    os.flush();
    os.close();
  }else{
    return;
  }
%>
