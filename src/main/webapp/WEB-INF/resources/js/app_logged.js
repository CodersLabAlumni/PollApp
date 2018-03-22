$(function () {

    function handleLoginSuccess() {
        var cookie = Cookies.get('logged_user');
        if (cookie !== undefined) {
            $('#username-logged').text(cookie);
        }
    }

    handleLoginSuccess();

});