<%@ page import="model.User" %>
<%@ page import="model.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/4/9
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%User me = (User) session.getAttribute("username");
  UserInfo myinfo = new UserInfo();
  myinfo.setUsername(me.getUsername());
  boolean selected = myinfo.select();
%>
<style>
  #vld-tooltip {
    position: absolute;
    z-index: 1000;
    padding: 5px 10px;
    background: #F37B1D;
    min-width: 150px;
    color: #fff;
    transition: all 0.15s;
    box-shadow: 0 0 5px rgba(0,0,0,.15);
    display: none;
  }

  .file-item {
    float: left;
    position: relative;
    margin: 0 20px 20px 0;
    padding: 4px;
  }
  .uploader-list {

    overflow: hidden;
  }
  #vld-tooltip:before {
    position: absolute;
    top: -8px;
    left: 50%;
    width: 0;
    height: 0;
    margin-left: -8px;
    content: "";
    border-width: 0 8px 8px;
    border-color: transparent transparent #F37B1D;
    border-style: none inset solid;
  }
</style>
<script src="static/js/webuploader.html5only.min.js"></script>
<div class="am-container" style="margin-top: 20px;">

  <div class="am-panel am-panel-default">
    <div class="am-panel-hd">
      <h4 class="am-panel-title" data-am-collapse="{parent: '#accordion', target: '#do-not-say-1'}">
        基本信息(必填)
      </h4>
    </div>
    <div id="do-not-say-1" class="am-panel-collapse am-collapse am-in">
      <div class="am-panel-bd">
        <form class="am-form" id="form-with-tooltip">
          <fieldset>
            <div class="am-form-group">
              <label for="id">学号：</label>
              <input type="text" id="id"
                     pattern="^\d{8}\d*$" data-foolish-msg="至少8位的数字！"
                     placeholder="你的学号(至少8位)" required value="<%=selected?myinfo.getId():""%>"/>
            </div>
            <div class="am-form-group">
              <label for="name">姓名：</label>
              <input type="text" id="name" placeholder="你的姓名" value="<%=selected?myinfo.getName():""%>" data-foolish-msg="你的姓名忘了吗？"  required />
            </div>
            <div class="am-form-group">
              <label>性别：</label>
              <label class="am-radio-inline">
                <input type="radio" <%=!selected?"checked":""%> <%=selected&&myinfo.getSex().equals("m")?"checked":""%> value="m" name="sex" data-am-ucheck> 男
              </label>
              <label class="am-radio-inline">
                <input type="radio" name="sex" <%=selected&&myinfo.getSex().equals("w")?"checked":""%> data-am-ucheck value="w"> 女
              </label>
            </div>
            <div class="am-form-group">
              <label for="birth">生日：</label>
              <div class="am-input-group am-datepicker-date" data-am-datepicker="{format: 'yyyy-mm-dd',viewMode:years}">
                <input type="text" id="birth" value="<%=selected?myinfo.getBirth():""%>" class="am-form-field" data-foolish-msg="你的生日忘了吗？" required placeholder="生日" data-am-datepicker readonly>
                <%--<span class="am-input-group-btn am-datepicker-add-on">--%>
                  <%--<button class="am-btn am-btn-default" type="button"><span class="am-icon-calendar"></span> </button>--%>
                <%--</span>--%>
              </div>
            </div>
            <div class="am-form-group">
              <label for="photo">照片：</label>
              <div id="fileList" class="uploader-list">
                <%if(myinfo.getPhoto()!=null){%>
                <img src="photoShow" class="am-thumbnail" />
                <%}%>
              </div>
              <div class="am-form-group am-form-file">
                 <i class="am-icon-cloud-upload"></i> 选择要上传的文件
                 <input type="file" id="photo">
              </div>
            </div>

            <button class="am-btn am-btn-secondary" id="submit" type="submit">提交</button>
            <button class="am-btn am-margin am-margin-horizontal-sm am-btn-sm am-btn-danger" id="reset" type="button">清空</button>
          </fieldset>
        </form>
      </div>
    </div>
  </div>

  <div class="am-panel am-panel-default">
    <div class="am-panel-hd">
      <h4 class="am-panel-title" data-am-collapse="{parent: '#accordion', target: '#do-not-say-2'}">
        个人简历(可选)
      </h4>
    </div>
    <div id="do-not-say-2" class="am-panel-collapse am-collapse am-in">
      <div class="am-panel-bd">
        <div class="am-form-group">
          <div id="resumeList" class="uploader-list">
          </div>
          <div class="am-form-group am-form-file">
            <i class="am-icon-cloud-upload"></i> 选择要上传的简历(Word)
            <input type="file" id="resume">
          </div>
        </div>
        <%if(myinfo.getResume()!=null){%>
        <a class="am-btn am-btn-default" href="downloadResume" download="<%=me.getUsername()+"_resume"%>" >查看简历</a>
        <%}%>
      </div>
    </div>
</div>
<script>
  $(function() {
    $('#reset').click(function () {
      $.alertModal('确定全部清空吗？(包括照片与简历)', function () {
        $('#reset').prop('disabled',true);
        $.post(AJAX_URL,{act:'clear'}, function (d) {
          $('#reset').prop('disabled',false);
          d = JSON.parse(d);
          if(d.status==-1)
            $('#submit').infoBeforeShow(d.msg)
          else
            location.reload();
        })
      });
    })
    
    $('#resume').on('change', function () {
      var _t = $(this);
      var file = this.files[0];
      if(!file) return;
      console.log(file.type);//application/pdf  application/vnd.openxmlformats-officedocument.wordprocessingml.document  application/msword
      //if(!this.files[0].type || this.files[0].type) return;
      var fr = new FileReader();
      fr.onload = function () {
        var fileNames = '';
        fileNames += '<span class="am-badge">' + file.name + '</span> ';
        $('#resumeList').html(fileNames).append(LOADICON);
        _t.parent().hide();
        $.post('resumeUp',fr.result, function (d) {
          _t.parent().show();
          $('#resumeList').children(':last').remove();
          d = JSON.parse(d);
          if(d.status==1){
            _t.parent().infoBeforeShow(d.msg);
          }else{
            _t.parent().alertBeforeShow(d.msg);
          }
        });
      }
      fr.readAsDataURL(this.files[0]);
    });
    
    $('#photo').on('change', function (e) {
      var _t = $('#photo');
      var file = $(this)[0].files[0];
      if(!file) return;
      if(!file.type || !/image\/.*/i.test(file.type))
        $(this).parent().alertBeforeShow('选择照片！');
      else{
        var fr = new FileReader();
        fr.onload = function (e) {
          var img = '<img src="'+fr.result+'" style="width:100px;height:100px;margin-bottom:20px;" class="am-thumbnail" />';
          $('#realImg').remove();
          var imgx = '<img id="realImg" src="'+fr.result+'" style="display:none;" />';
          var a = '<a title="点击有惊喜" href="javascript:;" role="modal-btn">'+
                    img + imgx
                  '</a>';
          $('#fileList').html(a).append(LOADICON);
          $('#realImg').on('load', function () {
            $('[role=modal-btn]').click(function () {
              $.noBorderModal('照片',$(imgx).show().prop('outerHTML'),_t.width(),_t.height());
            });
          });
          _t.parent().hide();
          $.ajax({
            method:'post',
            url:'photoUp',
            data:fr.result,
            xhr: function() {
              var xhr = $.ajaxSettings.xhr();
              xhr.upload.onprogress = function(e) {
                console.log(Math.floor(e.loaded / e.total *100) + '%');
              };
              return xhr;
            },
            success:function (d) {
              _t.parent().show();
              $('#fileList').children(':last').remove();
              d = JSON.parse(d);
              if (d.status == 1) {
                _t.parent().infoBeforeShow(d.msg);
              } else {
                _t.parent().alertBeforeShow(d.msg);
              }
            }
          });
        };
        fr.readAsDataURL(file);
      }
    });

    var $form = $('#form-with-tooltip');
    var $tooltip = $('<div id="vld-tooltip">提示信息！</div>');
    $tooltip.appendTo(document.body);
    $form.validator({
      submit: function() {
        var formValidity = this.isFormValid();
        if(formValidity){
          $.post(AJAX_URL,{act:'import',id:$('#id').val().trim(),name:$('#name').val().trim(),sex:$('[name=sex]:checked').val(),birth:$('#birth').val()},
                  function (d) {
                    d = JSON.parse(d);
                    if(d.status==1)
                      $('#submit').infoBeforeShow(d.msg);
                    else
                      $('#submit').alertBeforeShow(d.msg);
                  }
          )
        }
        return false;
      }
    });
    var validator = $form.data('amui.validator');
    $form.on('focusin focusout', '.am-form-error input', function(e) {
      if (e.type === 'focusin') {
        var $this = $(this);
        var offset = $this.offset();
        var msg = $this.data('foolishMsg') || validator.getValidationMessage($this.data('validity'));

        $tooltip.text(msg).show().css({
          left: offset.left + 10,
          top: offset.top + $(this).outerHeight() + 10
        });
      } else {
        $tooltip.hide();
      }
    });

  });

</script>