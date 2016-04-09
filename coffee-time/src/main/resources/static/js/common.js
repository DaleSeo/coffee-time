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