$(function () {

    var ajax = new Ajax();
    var closedPolls = $('#closedPolls');

    function handleLoginSuccess() {
        var cookie = Cookies.get('logged_user');
        if (cookie !== undefined) {
            $('#username-logged').text(cookie);
        }
    }

    function renderAddCommentForm() {
        return '<div class="container comment-add text-center">' +
            '<textarea class="comment-text form-control" placeholder="Insert text..."></textarea><br>' +
            '<a class="comment-add-btn btn">Add comment</a>' +
            '</div>'
    }

    closedPolls.on('click', '.comment-add-btn', function (e) {
        e.preventDefault();
        $(e.target).parent().find('.text-success').remove();
        $(e.target).parent().find('.text-danger').remove();
        var textNode = $(e.target).siblings('.comment-text');
        var text = textNode.val();
        var pollId = $(e.target).parent().siblings('.toggle-comments').find('a').data('poll');
        var comment = {content: text};
        ajax.ajaxPostCallback('/polls/' + pollId + '/comments', comment, function (response) {
            if (response.status === 'OK') {
                $(e.target).before('<p class="text-success">' + response.successMsg + '</p>');
                textNode.val('');
            } else {
                $.each(response.errors, function (key, value) {
                    $(e.target).before('<p class="text-danger">' + value + '</p>');
                });
            }
        });

    });

    closedPolls.on('click', '.toggle-comments', function (e) {
        $(e.target).parent().find('.text-success').remove();
        $(e.target).parent().find('.text-danger').remove();
        var commentAddNode = $(e.target).parent().siblings('.comment-add');
        if (commentAddNode.length === 0) {
            $(e.target).parent().siblings('.pager-comments').after(renderAddCommentForm());
        }
        commentAddNode.toggle('hidden');
    });

    handleLoginSuccess();

});