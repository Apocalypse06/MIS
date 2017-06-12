/**
 * Created by Daniel on 2017/3/4.
 */
successCode = 10000

function showTips(content) {
    $('#tips-content').html(content)
    $('#tips').modal()
}


function getToken() {
    var token = $.cookie('token');
    if (typeof (token) == 'undefined')
        window.location.href = "/index.html"
    else
        return $.cookie('token');
}

function getTokenArray() {
    var dataArray = [];
    addData(dataArray, 'token', getToken())
    return dataArray;
}

function setToken(token) {
    $.cookie('token', token);
}

function addData(dataArray, name, value) {
    dataArray.push({"name": name, "value": value})
}

function addToken(dataArray) {
    addData(dataArray, 'token', getToken())
}

function parseDom(nodelist) {
    var objE = document.createElement("div");
    objE.innerHTML = nodelist;
    return objE.childNodes;
}

function retCallBack(res, callback) {
    if (res.retCode == successCode) {
        callback(res.retContent)
    } else {
        showTips(res.retMess);
    }
}

function lateRefresh(href) {
    setTimeout(function () {
        window.location.href = href
    }, 1500)
}

function customAjax(url, data, successCallback, errorCallback) {
    $.ajax({
        url: url,
        data: data,
        dataType: 'json',
        type: 'POST',
        success: function (res) {
            retCallBack(res, successCallback)
        },
        error: function (res) {
            if (typeof (errorCallback) == 'undefined') {
                showTips("error")
            } else(
                errorCallback(res)
            )
        }
    })
}

function isToday(time) {
    var date = new Date();
    var fileds = time.split("-")
    var year = fileds[0];
    var month = fileds[1] - 1
    var day = fileds[2]
    return date.getFullYear() == year && date.getMonth() == month && date.getDate() == day

}

/**
 * 按照传入的键值，提取出datas数组里的对应值
 * 如datas=[{'date':'1970-01-01','value'=1},{'date':'1970-01-02','value'=2}]
 * 调用getArray(datas,'date')将返回datas中的date域构成的数组['1970-01-01','1970-01-02']
 * @param datas
 * @returns {Array}
 */
function getArray(datas) {
    var array = [];
    if (arguments.length < 2) {
        alert("wrong size");
        return null;
    }
    for (var i = 0; i < datas.length; i++) {
        var tem;
        if (arguments.length >= 3) {
            tem = [];
            for (var j = 1; j < arguments.length; j++) {
                tem.push(datas[i][arguments[j]]);
            }
        } else {
            tem = datas[i][arguments[1]];
        }
        array[i] = tem;
    }
    return array;
}