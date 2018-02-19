var Ajax = function () {
};

Ajax.prototype.ajaxGet = function (endpoint) {
    return $.getJSON({
        url: $(location).attr('origin') + endpoint
    })
};

Ajax.prototype.ajaxPost = function(endpoint, data) {
    return $.post({
        headers: {
            'Content-Type': 'application/json'
        },
        url: $(location).attr('origin') + endpoint,
        data: JSON.stringify(data)
    })
};

Ajax.prototype.ajaxDelete = function(endpoint) {
    return $.ajax({
        url: $(location).attr('origin') + endpoint,
        type: 'DELETE'
    })
};

Ajax.prototype.ajaxPut = function(endpoint, data) {
    return $.ajax({
        headers: {
            'Content-Type': 'application/json'
        },
        url: $(location).attr('origin') + endpoint,
        type: 'PUT',
        data: JSON.stringify(data)
    })
};

var FormUtil = function () {
};

FormUtil.prototype.createObjectFromForm = function(form) {
        var object = {};
        $(form).find('input[type!=submit]').each(function (index, elem) {
            object[elem.name] = elem.value
        });
        return object;
};


