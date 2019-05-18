$(function () {
    var token = store.get('token');
    if (token == null) {
        window.location.href = '/bb/login.html'
    }
    initInfo();
});
var token;
var companyUserInfo;

/**
 * 初始化信息
 */
function initInfo() {
    token = store.get('token');
    companyUserInfo = store.get('companyUserInfo');

    $('.user-name').html(companyUserInfo.userInfoOut.userName);
    $('.account-expires').html('过期时间:' + companyUserInfo.userInfoOut.startTime + ' ~ ' + companyUserInfo.userInfoOut.endTime);
    initRule();

    $('.face').attr('src', companyUserInfo.userInfoOut.photoUrl);
}

/**
 * 获取考勤规则
 */
function getRule() {
    $.ajax({
        url: BASE_API + '/rule/info',
        type: 'POST',
        data: {
            userId: companyUserInfo.userInfoOut.userId,
            companyId: companyUserInfo.companyInfoOut.companyId,
            token: token
        },
        success: function (res) {
            if (res.status == '0000') {
                var ruleInfo = res.data.listSecOfcSignRuleOutParam;
                store.set('ruleInfo', ruleInfo);
                var divHtml = '<div class="col-xs-12">\n';

                for (var i = 0; i < ruleInfo.length; i++) {
                    var ruleId = ruleInfo[i].ruleId
                    var name = ruleInfo[i].ruleName;
                    divHtml += '            <label>' + name + '</label>\n' +
                        '<div><label> <font color="orange">距离范围:' + ruleInfo[i].signRange + ' 米</font></label></div>' +
                        '<div><label> <font color="orange">时间范围:' + ruleInfo[i].signStartTime + ' ~ ' + ruleInfo[i].signEndTime + '</font></label></div>' +
                        '            <ul>\n';
                    for (var j = 0; j < ruleInfo[i].signRuleOutList.length; j++) {
                        var signRule = ruleInfo[i].signRuleOutList[j];
                        divHtml += '<li><input type="radio" name="address" ruleId="' + ruleInfo[i].ruleId + '" value="' + signRule.lat + ',' + signRule.lng + '" data-labelauty="' + signRule.signAddress + '"></li>';
                    }

                    store.set('rule-' + ruleId, ruleInfo[i]);
                }
                divHtml += '</ul></div>';

                $('.rule-div').html(divHtml);

                $(':input').labelauty();
            } else {
                toastr.error(res.message);
            }
        }
    })
}

function initRule() {
    getRule();
}

toastr.options.positionClass = 'toast-bottom-right';

/**
 * 签到
 */
$('.sign_btn').click(function () {
    var address = $('input[name=address]:checked').val();
    var ruleId = $('input[name=address]:checked').attr('ruleId');
    console.log(address);
    console.log(ruleId);
    console.log(companyUserInfo.companyInfoOut.companyId);
    console.log(companyUserInfo.userInfoOut.userId);
    if (confirm("是否要签到?")) {
        $.ajax({
            url: BASE_API + '/sign',
            type: 'POST',
            data: {
                companyId: companyUserInfo.companyInfoOut.companyId,
                userId: companyUserInfo.userInfoOut.userId,
                ruleId: ruleId,
                token: token,
                location: address
            },
            success: function (res) {
                if (res.status == '0000') {
                    toastr.success(res.message);
                } else {
                    toastr.error(res.message);
                }
            }
        })
    } else {
        return;
    }

})

/**
 * 签退
 */
$('.signout_btn').click(function () {
    var account = $('#account').val();
    var password = $('#password').val();
    var address = $('input[name=address]:checked').val();

    if (confirm("是否要签退?")) {
        $.ajax({
            url: BASE_API + '/sign/out',
            type: 'POST',
            data: {
                account: account,
                password: password,
                address: address
            },
            success: function (res) {
                if (res.status == '0000') {
                    toastr.success(res.message);
                } else {
                    toastr.error(res.message);
                }
            }
        })
    } else {
        return;
    }

})