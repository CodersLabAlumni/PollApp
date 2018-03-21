$(function () {

    var ajax = new Ajax();

    function handleLoginSuccess() {
        var cookie = Cookies.get('logged_user');
        if (cookie !== undefined) {
            $('#username-logged').text(cookie);
        }
    }

    function commentPanel() {
        return '<div class="container comment-add">' +
            '<button>Add comment</button></div>'
    }

    $('.comment-add').on('click', 'button', function (e) {
        e.preventDefault();
    });

    $('#closedPolls').children().each(function (index, elem) {
        $(elem).append(commentPanel());
    });

    handleLoginSuccess();

});