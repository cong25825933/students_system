<%@ page import="util.CookieUtil" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <br>
    <br>
    <%String name = CookieUtil.findCookie("NAME",request);%>
    <div class="am-form">
      <div class="am-form-group am-form-icon">
        <i class="am-icon-user am-icon-fw"></i>
        <input type="text" id="user" value="<%=URLDecoder.decode(name,"utf-8")%>" class="am-form-field" placeholder="你的大名">
      </div>
      <div class="am-form-group am-form-icon">
        <i class="am-icon-lock am-icon-fw"></i>
        <input type="password" id="pwd" class="am-form-field" placeholder="你的密码" value="<%=URLDecoder.decode(CookieUtil.findCookie("PWD",request),"utf-8")%>">
      </div>
      <label for="remember-me" class="am-radio-inline">
        <input id="remember-me" type="checkbox" data-am-ucheck <%if(!name.isEmpty()){%>checked<%}%>>
        记住密码
      </label>
      <br>
      <div class="am-cf">
        <button id="submit" class="am-btn am-btn-primary am-fl">登 录</button>
      </div>
    </div>
  </div>
</div>
<script>
  $('#submit').click(function(){
    var _t = $(this).prop('disabled',true);
    var user = $('#user').val().trim(),pwd = $('#pwd').val().trim(),rem = $('#remember-me').prop('checked');
    $.post(AJAX_URL,{act:'login',user:user,pwd:pwd,rem:rem}, function (d) {
      _t.prop('disabled',false);
      d = JSON.parse(d);
      if(d.status==-1){
        _t.alertBeforeShow(d.msg);
      }else{
        location.href = 'import';
      }
    })
  });
</script>