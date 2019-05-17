// ajax 请求参数
var ajaxSettings = function(opt) {
    var url = opt.url;
    var href = location.href;
    // 判断是否跨域请求
    var requestType = 'jsonp';
    if (url.indexOf(location.host) > -1)
        requestType = 'json';
    requestType = opt.dataType || requestType;
    // 是否异步请求
    var async = (opt.async === undefined ? true : opt.async);
    return {
        url: url,
        async: async,
        type: opt.type || 'get',
        dataType: requestType,
        cache: false,
        data: opt.data,
        success: function(data, textStatus, xhr) {
            /*
            *如果dataType是json，怎判断返回数据是否为json格式，如果不是进行转换
            * 成功数据通用格式
            *   {
            *       "code": 200,
            *       "data": [], 
            *       "success": true // 成功
            *   }
            *   失败返回的数据
            *   {
            *       "code": 200, 
            *       "info": 'error', 
            *       "success": false // 失败
            *   }
             */
            if((requestType === 'json' || requestType === "jsonp") && typeof(data) === "string") {
                data = JSON.parse(data);
            }
            if(data.success) {
                opt.success(data);
            }

            if(opt.error) {
                opt.error(data);
            }

        },
        error: function(xhr, status, handler) {
            if (opt.error)
                opt.error();
        }
    };
};