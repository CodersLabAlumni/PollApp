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
        var textNode = $(e.target).siblings('.comment-text');
        var text = textNode.val();
        var pollId = $(e.target).parent().siblings('.toggle-comments').find('a').data('poll');
        var comment = {content: text};
        ajax.ajaxPost('/polls/' + pollId + '/comments', comment);
        textNode.val('');
    });

    closedPolls.on('click', '.toggle-comments', function (e) {
        var commentAddNode = $(e.target).parent().siblings('.comment-add');
        if (commentAddNode.length === 0) {
            $(e.target).parent().siblings('.pager-comments').after(renderAddCommentForm());
        }
        commentAddNode.toggle('hidden');
    });

    handleLoginSuccess();

});