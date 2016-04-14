$.ajaxSetup({
    contentType: "application/json; charset=UTF-8"
});

function toJson(form) {
    var json = {};
    $(form).serializeArray().map(function(item) {
        json[item.name] = item.value;
    });
    return JSON.stringify(json);
}

$.put = function(url, data, success, dataType){
    return $.ajax({
        type: 'PUT',
        url: url,
        data: data,
        success: success,
        dataType: dataType
    });
}

$.delete = function(url, data, success, dataType){
    return $.ajax({
        type: 'DELETE',
        url: url,
        data: data,
        success: success,
        dataType: dataType
    });
}