<%@ page import="util.StringUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/8
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>

<div class="am-banner" style="margin-top: 10px;">
  <div class="am-container">
  <div class="am-scrollspy-init am-scrollspy-inview am-animation-scale-up" data-am-scrollspy="{animation: 'scale-up', repeat: false}">
    <h1><%=(request.getParameter("title"))%></h1>
    <p><%=(request.getParameter("content"))%></p>
  </div>
  </div>
</div>
