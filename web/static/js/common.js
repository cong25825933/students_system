/**
 * Created by Yc on 2016/4/8.
 */
AJAX_URL="ajaxDo";
LOADICON='<i class="am-icon-spinner am-icon-spin"></i>';

$(function(){
    $.fn.datetimepicker.dates['zh-CN'] = {
        days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
        months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        today: "今天",
        suffix: [],
        meridiem: ["上午", "下午"],
        rtl: false
    };

    $.alertModal = function (content,onok,oncancel) {
        var t = $('#my-alertModal');
        t.find('.am-modal-bd').html(content);
        t.modal({
            onConfirm: onok,
            // closeOnConfirm: false,
            onCancel: oncancel
        });
    }

    $.fn.alertBeforeShow = function (html) {
        var alert = $('#myAlert');
        if(alert.length==0) {
            var t = '<div class="am-alert am-alert-danger" id="myAlert" data-am-alert="">' +
                '<button type="button" class="am-close">×</button>' +
                '<p>' + html + '</p>' +
                '</div>';
            $(this).before($(t).show('normal'));
        }else{
            var alertclone = alert.clone();
            alert.remove();
            alert.find('p').html(html);
            $(this).before(alertclone)
        }
    };
    $.fn.infoBeforeShow = function (html) {
        var alert = $('#myInfo');
        if(alert.length==0) {
            var t = '<div class="am-alert am-alert-secondary" id="myInfo" data-am-alert="">' +
                '<button type="button" class="am-close">×</button>' +
                '<p>' + html + '</p>' +
                '</div>';
            $(this).before($(t).show('normal'));
        }else{
            var alertclone = alert.clone();
            alert.remove();
            alertclone.find('p').html(html)
            $(this).before(alertclone)
        }
    };
    $.infoModal = function (head,html) {
        var modal = $('#my-infoModal');
        modal.find('.am-modal-hd span').text(head);
        modal.find('.am-modal-bd').html(html);
        modal.modal();
    }
    $.noBorderModal = function (head,html,w,h) {
        var modal = $('#my-noBorderModal');
        modal.html(html)
        modal.modal({width:w,height:h});
        $(document).keydown(function (e) {
            if(e.keyCode == 27) {
                modal.modal('close');
                $(this).off('keydown');
            }
        })
    }

});

