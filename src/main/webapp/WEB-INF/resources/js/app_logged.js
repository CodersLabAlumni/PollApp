$(function () {

    function handleLoginSuccess() {
        var c = Cookies.get('logged_user');
        if (c !== undefined) {
            $('#username-logged').text('Logged as ' + c);
        }
    }

    handleLoginSuccess();

});