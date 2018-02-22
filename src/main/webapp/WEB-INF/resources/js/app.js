$(function () {
    var ajax = new Ajax();
    var formUtil = new FormUtil();
    var ongoingPolls = $('#ongoingPolls');
    var closedPolls = $('#closedPolls');
    var closedPollsCategories = $('#closedPollsCategories');
    var openPollsCategories = $('#openPollsCategories');
    var pollForm = $('#pollForm');
    var answers = $('#answers');

    function renderClosedList(endpoint) {
        closedPolls.empty();
        ajax.ajaxGetCallback(endpoint, function (response) {
            response.forEach(function (poll) {
                var charContent = [];
                ajax.ajaxGetCallback('/polls/' + poll.id + '/answers', function (response) {
                    response.forEach(function (answer) {
                        charContent.push({
                            y: answer.id,
                            label: answer.content
                        })
                    });
                    closedPolls.append('<div class="card border-danger mb-3" style="max-width: 40rem;">' +
                        '<div class="card-header">' +
                        poll.question +
                        '</div>' +
                        '<div class="card-body">' +
                        '<div id="chartContainer' + poll.id + '" style="height: 370px; width: 100%;"></div>' +
                        '</div></div>');
                    var chart = new CanvasJS.Chart("chartContainer" + poll.id, {
                        animationEnabled: true,
                        theme: "light2", // "light1", "light2", "dark1", "dark2"
                        title: {
                            text: ""
                        },
                        axisY: {
                            title: "%"
                        },
                        data: [{
                            type: "column",
                            showInLegend: true,
                            legendMarkerColor: "grey",
                            legendText: "100 answers",
                            dataPoints: charContent
                        }]
                    });
                    chart.render();
                });
            })
        });
    }

    function renderOpenedList(endpoint) {
        ongoingPolls.empty();
        ajax.ajaxGetCallback(endpoint, function (response) {
            response.forEach(function (poll) {
                var pollAnswers = '';
                ajax.ajaxGetCallback('/polls/' + poll.id + '/answers', function (response) {
                    response.forEach(function (answer) {
                        pollAnswers += '<div class="form-check">' +
                            '<label class="form-check-label">' +
                            '<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1" checked="">' +
                            answer.content +
                            '</label>' +
                            '</div>'
                    });
                    ongoingPolls.append('<div class="text-white bg-secondary mb-3" style="max-width: 40rem;"><div class="card-header">' +
                        poll.question +
                        '</div><div class="card-body">' +
                        '<fieldset class="form-group">' +
                        pollAnswers +
                        '</fieldset></div></div>');
                });
            })
        });
    }

    function renderCategoriesList() {
        ajax.ajaxGetCallback('/categories', function (response) {
            response.forEach(function (category) {
                openPollsCategories.append('<a class="dropdown-item category-open" data-category="' + category.id + '" href="#">' + category.name + '</a>');
                closedPollsCategories.append('<a class="dropdown-item category-closed" data-category="' + category.id + '" href="#">' + category.name + '</a>');
            })
        });
    }

    closedPollsCategories.on('click', function (e) {
        var categoryId = $(e.target).data('category');
        renderClosedList('/categories/' + categoryId + '/polls');
    });

    openPollsCategories.on('click', function (e) {
        var categoryId = $(e.target).data("category");
        renderOpenedList('/categories/' + categoryId + '/polls'); //TODO once backend disctinction between closed and opened polls is developed, attach it
    });

    pollForm.on('submit', function (e) {
        e.preventDefault();
        var poll = formUtil.createObjectFromForm($('#poll'));
        var answers = formUtil.createObjectListFromForm($('#answers'));
        var categories = $('#select-categories').val();
        ajax.ajaxPostCallback("/polls", poll, function (response) {
            answers.forEach(function (answer) {
                ajax.ajaxPost("/polls/" + response.id + "/answers", answer)
            });
            categories.forEach(function (category) {
                ajax.ajaxPost("/polls/" + response.id + "/categories/" + category)
            })
        });
        this.reset();
    });

    $('#pollCreate').on('click', function () {
        pollForm.toggle('hidden');
        var categoryMenu = $('#select-categories');
        categoryMenu.empty();
        ajax.ajaxGetCallback('/categories', function (response) {
            response.forEach(function (category) {
                categoryMenu.append('<option value="' + category.id + '"> ' + category.name + '</option>')
            })
        });
    });

    $('.add-answer').on('click', function () {
        answers.children().last().clone().appendTo(answers).val('');
    });

    $('.remove-answer').on('click', function () {
        answers.children().last().remove();
    });

    ongoingPolls.on('click', '.form-check-input', function () {
        $(this).parents('.text-white').fadeOut();
    });

    renderCategoriesList();
    renderOpenedList('/polls/ongoing');
    renderClosedList('/polls/closed');

});