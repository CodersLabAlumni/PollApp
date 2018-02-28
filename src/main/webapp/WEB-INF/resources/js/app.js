$(function () {
    var ajax = new Ajax();
    var formUtil = new FormUtil();
    var ongoingPolls = $('#ongoingPolls');
    var closedPolls = $('#closedPolls');
    var closedPollsCategories = $('#closedPollsCategories');
    var openPollsCategories = $('#openPollsCategories');
    var showPolls = $('#showPolls');
    var pollForm = $('#pollForm');
    var answers = $('#answers');
    var categories = $('#all-categories');
    var selectedCategories = $('#selected-categories');
    var showPollsAddress = "/closed";

    function renderClosedList(endpoint) {
        closedPolls.empty();
        ajax.ajaxGetCallback(endpoint, function (response) {
            response.forEach(function (elem) {
                var poll = elem.poll;
                var pollData = elem.pollNumberData;
                var charContent = [];
                ajax.ajaxGetCallback('/polls/' + poll.id + '/answers', function (response) {
                    response.forEach(function (elem) {
                        var answer = elem.answer;
                        var answerData = elem.answerNumberData;
                        charContent.push({
                            y: answerData.percent,
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
                            legendText: pollData.totalAnswers + " answers",
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
            response.forEach(function (elem) {
                var poll = elem.poll;
                var pollAnswers = '';
                ajax.ajaxGetCallback('/polls/' + poll.id + '/answers', function (response) {
                    response.forEach(function (elem) {
                        var answer = elem.answer;
                        pollAnswers += '<div class="form-check">' +
                            '<label class="form-check-label">' +
                            '<input type="radio" class="form-check-input" name="optionsRadios" value="' + answer.id + '" checked="">' +
                            answer.content +
                            '</label>' +
                            '</div>'
                    });

                    var getClock = setInterval(function(){
                        var now = new Date().getTime();
	                    var closed = poll.closed;
	                    var distance = closed - now;
	                    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
	                    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
	                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
	                    var clock = days + 'd ' + hours + 'h ' + minutes + 'm ' + seconds + 's ';
	                    document.getElementById("clock" + poll.id).innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
                    }, 1000);
                    
                    ongoingPolls.append('<div class="text-white bg-secondary mb-3" style="max-width: 40rem;"><div class="card-header">' +
                        poll.question + '<br\>' + 
                        '</div><div class="card-body">' +
                        '<fieldset class="form-group">' +
                        pollAnswers + 
                        '</fieldset>' +
                    	'</div><div class="card-header">'+
                    	'<div>Time left:</div>' +
                    	'<div id="clock' + poll.id +'"></div>' +
                    	'</div></div>');
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

    function renderCategoriesToSelect() {
        ajax.ajaxGetCallback('/categories', function (response) {
            response.forEach(function (category) {
                categories.append('<li data-id="' + category.id + '" class="col-xs-3 list-group-item"> ' + category.name + '</li>')
            })
        });
    }

    function renderAnswers() {
        var answer = answers.children().last();
        answers.empty();
        answers.append(answer);
        answer.clone().appendTo(answers);
    }
    
    showPolls.on('click', function (e) {
    	showPollsAddress = $(e.target).data('address');
        renderClosedList('/polls/' + showPollsAddress);
    });

    closedPollsCategories.on('click', function (e) {
        var categoryId = $(e.target).data('category');
        renderClosedList('/categories/' + categoryId + '/'+ showPollsAddress);
    });

    openPollsCategories.on('click', function (e) {
        var categoryId = $(e.target).data("category");
        renderOpenedList('/categories/' + categoryId + '/ongoing');
    });

    pollForm.on('submit', function (e) {
        e.preventDefault();
        var poll = formUtil.createObjectFromForm($('#poll'));
        var answers = formUtil.createObjectListFromForm($('#answers'));
        ajax.ajaxPostCallback("/polls", poll, function (response) {
            answers.forEach(function (answer) {
                ajax.ajaxPost("/polls/" + response.id + "/answers", answer)
            });
            $('#selected-categories').children().each(function (index, category) {
                ajax.ajaxPost("/polls/" + response.id + "/categories/" + $(category).data('id'))
            });
            categories.empty();
            selectedCategories.empty();
            renderCategoriesToSelect();
            renderAnswers();
        });
        this.reset();
    });

    $('#pollCreate').on('click', function () {
        pollForm.toggle('hidden');
        categories.empty();
        selectedCategories.empty();
        renderCategoriesToSelect();
    });

    categories.on('click', 'li', function (e) {
        selectedCategories.append(e.target);
    });

    selectedCategories.on('click', 'li', function (e) {
        categories.append(e.target);
    });

    $('.add-answer').on('click', function () {
        answers.children().last().clone().appendTo(answers).val('');
    });

    $('.remove-answer').on('click', function () {
        if (answers.children().length > 2) {
            answers.children().last().remove();
        }
    });

    ongoingPolls.on('click', '.form-check-input', function (e) {
        var userData = {};
        ajax.ajaxPostCallback('/data', userData, function (data) {
            ajax.ajaxPost('/answers/' + e.target.value + '/data', data);
        });
        $(this).parents('.text-white').fadeOut();
    });

    renderCategoriesList();
    renderOpenedList('/polls/ongoing');
    renderClosedList('/polls/closed');

});