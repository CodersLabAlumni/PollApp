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
    var $pagination = $('.pagination-polls');
    var categoryId = 0;
    var showPollsAddress = "";
    var pollSortProperty = "created";
    var pollSortDirection = "desc";
    var registerForm = $('#register-form');
    var loginForm = $('#login-form');

    var defaultOpts = {
        totalPages: 10,
        visiblePages: 10,
        startPage: 1
    };
    $pagination.twbsPagination(defaultOpts);

    function renderClosedList(endpoint) {
        ajax.ajaxGetCallback(endpoint, function (response) {
            var totalPages = response.totalPages;
            if (totalPages <= 0) {
                totalPages = 1;
            }
            $pagination.twbsPagination('destroy');
            $pagination.twbsPagination($.extend({}, defaultOpts, {
                totalPages: totalPages,
                onPageClick: function (evt, page) {
                    closedPolls.empty();
                    var param = '?page=' + (page - 1);
                    if (endpoint.includes('?')) {
                        param = '&page=' + (page - 1);
                    }
                    ajax.ajaxGetCallback(endpoint + param, function (response) {
                        var data = response.content;
                        data.forEach(function (elem) {
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
            }));
        });
    }

    function renderOpenedList(endpoint) {
        ongoingPolls.empty();
        ajax.ajaxGetCallback(endpoint, function (response) {
            var content = response.content;
            content.forEach(function (elem) {
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

                    ongoingPolls.append('<div class="text-white bg-secondary mb-3" style="max-width: 40rem;"><div class="card-header">' +
                        poll.question + '<br\>' +
                        '</div><div class="card-body">' +
                        '<fieldset class="form-group">' +
                        pollAnswers +
                        '</fieldset>' +
                        '</div><div class="card-header">' +
                        '<div>Time left:</div>' +
                        '<div id="clock' + poll.id + '"></div>' +
                        '</div></div>');

                    var getClock = setInterval(function () {
                        var now = new Date().getTime();
                        var closed = poll.closed;
                        var distance = closed - now;
                        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                        var clock = days + 'd ' + hours + 'h ' + minutes + 'm ' + seconds + 's ';
                        if (document.getElementById("clock" + poll.id) !== null) {
                            document.getElementById("clock" + poll.id).innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
                        }
                    }, 1000);
        });
      })
    });
  }

  function renderCategoriesList() {
    openPollsCategories.append('<a class="dropdown-item category-open" data-category="' + "0" + '" href="#">' + "All" + '</a>');
    closedPollsCategories.append('<a class="dropdown-item category-closed" data-category="' + "0" + '" href="#">' + "All" + '</a>');
    ajax.ajaxGetCallback('/categories', function(response) {
      response.forEach(function(category) {
        openPollsCategories.append('<a class="dropdown-item category-open" data-category="' + category.id + '" href="#">' + category.name + '</a>');
        closedPollsCategories.append('<a class="dropdown-item category-closed" data-category="' + category.id + '" href="#">' + category.name + '</a>');
      })
    });
  }

  function renderCategoriesToSelect() {
    ajax.ajaxGetCallback('/categories', function(response) {
      response.forEach(function(category) {
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

  showPolls.on('click', function(e) {
    showPollsAddress = $(e.target).data('address');
    renderClosedList('/categories/' + categoryId + '/polls/' + showPollsAddress + '?sort=' + pollSortProperty + '&dir=' + pollSortDirection);
    $('#showPollsButton').html($(e.target).html() + ' POLLS');
  });

  closedPollsCategories.on('click', function(e) {
    categoryId = $(e.target).data('category');
    var categoryName = $(e.target).html();
    renderClosedList('/categories/' + categoryId + '/polls/' + showPollsAddress + '?sort=' + pollSortProperty + '&dir=' + pollSortDirection);
    $('#closedPollsCategoryButton').html('CATEGORY ' + categoryName);
  });

  $('#showPollsSort').on('click', function(e) {
    pollSortProperty = $(e.target).data('sort');
    pollSortDirection = $(e.target).data('direction');
    var sortName = $(e.target).html();
    renderClosedList('/categories/' + categoryId + '/polls/' + showPollsAddress + '?sort=' + pollSortProperty + '&dir=' + pollSortDirection);
    $('#showPollsSortButton').html(sortName);
  });

  openPollsCategories.on('click', function(e) {
    categoryId = $(e.target).data("category");
    var categoryName = $(e.target).html();
    renderOpenedList('/categories/' + categoryId + '/polls/available');
    $('#ongoingPollsCategoryButton').html('CATEGORY ' + categoryName);
  });

  pollForm.on('submit', function(e) {
    e.preventDefault();
    var poll = formUtil.createObjectFromForm($('#poll'));
    var answers = formUtil.createObjectListFromForm($('#answers'));
    var days = $('#days').children().first().val();
    var hours = $('#hours').children().first().val();
    ajax.ajaxPostCallback("/polls", poll, function(response) {
      answers.forEach(function(answer) {
        ajax.ajaxPost("/polls/" + response.poll.id + "/answers", answer)
      });
      $('#selected-categories').children().each(function(index, category) {
        ajax.ajaxPost("/polls/" + response.poll.id + "/categories/" + $(category).data('id'))
      });
      ajax.ajaxPost("/polls/" + response.poll.id + "/closed/0" + days + "/0" + hours);
      categories.empty();
      selectedCategories.empty();
      renderCategoriesToSelect();
      renderAnswers();
    });
    this.reset();
  });

  $('#pollCreate').on('click', function() {
    pollForm.toggle('hidden');
    categories.empty();
    selectedCategories.empty();
    renderCategoriesToSelect();
  });

  categories.on('click', 'li', function(e) {
    selectedCategories.append(e.target);
  });

  selectedCategories.on('click', 'li', function(e) {
    categories.append(e.target);
  });

  $('.add-answer').on('click', function() {
    answers.children().last().clone().appendTo(answers).val('');
  });

  $('.remove-answer').on('click', function() {
    if (answers.children().length > 2) {
      answers.children().last().remove();
    }
  });

  ongoingPolls.on('click', '.form-check-input', function(e) {
    ajax.ajaxPost('/answers/' + e.target.value + '/data');
    $(this).parents('.text-white').fadeOut();
  });

  renderCategoriesList();
  renderOpenedList('/categories/' + 0 + '/polls/available');
  renderClosedList('/categories/' + 0 + '/polls/closed');


    $('#register').on('click', function () {
        registerForm.toggle('hidden');
        registerForm.trigger('reset');
        registerForm.find('.text-success').remove();
        registerForm.find('.text-danger').remove();
    });

    registerForm.on('submit', function (e) {
        e.preventDefault();
        registerForm.find('.text-success').remove();
        registerForm.find('.text-danger').remove();
        var account = formUtil.createObjectFromForm($('#register-form'));
        ajax.ajaxPostCallback("/accounts", account, function (response) {
            if (response.status === 'OK') {
                registerForm.append('<p class="text-success">' + response.successMsg + '</p>');
            } else {
                $.each(response.errors, function (key, value) {
                    registerForm.find('#' + key).parent().append('<p class="text-danger">' + value + '</p>');
                });
            }
        });
        this.reset();
    });

    $('#login').on('click', function () {
        removeLoginError();
        loginForm.submit();
    });

    $(window).on('beforeunload', function () {
        removeLoginError();
    });

    function handleLoginError() {
        if (Cookies.get('logged_error') !== undefined) {
            $('#login-error').text('Invalid credentials');
        }
    }

    function removeLoginError() {
        $('#login-error').remove();
        Cookies.remove('logged_error');
    }

    renderCategoriesList();
    renderOpenedList('/categories/' + 0 + '/polls/available');
    renderClosedList('/categories/' + 0 + '/polls');
    handleLoginError();

    //GAME JS -----------------------------------------------------------
    var gamePollList;
    var gameQuestion = $('#gameQuestion');
    var gameAnswers = $('#gameAnswers');
    var gameClock = $('#gameClock');
    var wrongAnswers = $('#wrongAnswers');
    var correctAnswers = $('#correctAnswers');
    var startGame = $('#startGame');
    var closeGame = $('#closeGame');
    var nextGame = $('#nextGame');
    var gameBoard = $('#gameBoard');
    var noGameView = $('#noGameView');
    var afterGameView = $('#afterGameView')
    var previousGamePoll = $('#previousGamePoll');
    var randomPoll;
    var previousRandomPoll;
    var correctAnswersScore = 0;
    var wrongAnswersScore = 0;
    var pollList = [];
    var gameTimer;
    var gameClockTimer;




    function getGamePolls(endpoint) {
      ajax.ajaxGetCallback(endpoint, function(response) {
        response.forEach(function(poll) {
          pollList.push(poll);
        })
      });
    }

    getGamePolls('/polls/game');

    function renderGame() {
      gamePollList = pollList.slice();
      shuffleArray(gamePollList);
      gameClockTimer = 10;
      renderGameQuestion();
      gameTimer = setInterval(function() {
        gameClock.empty();
        gameClock.append(gameClockTimer + ' s');
        if (gameClockTimer > 0) {
          gameClockTimer -= 1;
        } else {
          gameClock.empty();
          gameClock.append("TIME'S UP");
          gameQuestion.empty();
          gameQuestion.append("GAME OVER");
          gameAnswers.empty();
          $('#pollsResults').toggle('hidden');
          clearInterval(gameTimer);
          previousGamePoll.empty();
          $('#scoreMessage').empty();
          $('#scoreMessage').append("Your score is " + correctAnswersScore + " correct answers and " + wrongAnswersScore + " wrong answers. <br>You think you can do better?");
        }

      }, 1000);
    }

    function shuffleArray(array) {
      for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }

    function renderGameQuestion() {
      if (randomPoll != null) {
        previousRandomPoll = randomPoll;
      }
      if (gamePollList.length > 0) {
        randomPoll = gamePollList.pop(randomPoll);
        gameQuestion.empty();
        gameQuestion.append(randomPoll.question);
        var randomPollAnswers = '';
        ajax.ajaxGetCallback('/polls/' + randomPoll.id + '/answers', function(response) {
          response.forEach(function(elem) {
            var answer = elem.answer;
            var answerData = elem.answerNumberData;
            randomPollAnswers += '<div class="form-check">' +
              '<label class="form-check-label">' +
              '<input type="radio" class="form-check-input" name="optionsRadios" value="' + answer.id + '" max= ' + answerData.top + ' checked="">' +
              answer.content +
              '</label>' +
              '</div>'
          });
          gameAnswers.empty();
          gameAnswers.append(randomPollAnswers);
        })
      } else {
        gameQuestion.empty();
        gameQuestion.append("GAME OVER");
        gameAnswers.empty();
        gameAnswers.append("You have finished ahead of time, with " + gameClockTimer + " s left to spare");
        clearInterval(gameTimer);
      }
    }

    gameAnswers.on('click', '.form-check-input', function(e) {
      console.log(e.target.max);
      if (e.target.max === 'true') {
        correctAnswersScore += 1;
        correctAnswers.empty();
        correctAnswers.append(correctAnswersScore);
      } else {
        wrongAnswersScore += 1;
        wrongAnswers.empty();
        wrongAnswers.append(wrongAnswersScore);
      }
      renderGameQuestion();
      previousGamePoll.empty();


      if (previousRandomPoll != null) {
        var charContent = [];
        ajax.ajaxGetCallback('/polls/' + previousRandomPoll.id + '/answers', function(response) {
          response.forEach(function(elem) {
            var answer = elem.answer;
            var answerData = elem.answerNumberData;
            charContent.push({
              y: answerData.percent,
              label: answer.content
            })
          });
          previousGamePoll.append('<p>Answers from previous poll:</p><div class="card border-danger mb-3" style="max-width: 100%;">' +
            '<div class="card-header">' +
            previousRandomPoll.question +
            '</div>' +
            '<div class="card-body">' +
            '<div id="chartContainer' + previousRandomPoll.id + '" style="height: 370px; width: 100%;"></div>' +
            '</div></div>');
          var chart = new CanvasJS.Chart("chartContainer" + previousRandomPoll.id, {
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
              // legendText: pollData.totalAnswers + " answers",
              dataPoints: charContent
            }]
          });
          chart.render();
        });
      }


    })

    startGame.on('click', function(e) {
      toggleGameView();
      $('#pollsResults').toggle('hidden');
      renderGame();
    })

    closeGame.on('click', function(e) {
      toggleGameView();
      resetGame();
    })

    nextGame.on('click', function() {
      $('#pollsResults').toggle('hidden');
      resetGame();
      renderGame();
    })

    function toggleGameView() {
      gameBoard.toggle('hidden');
      noGameView.toggle('hidden');
      afterGameView.toggle('hidden');
    }

    function resetGame() {
      correctAnswersScore = 0;
      wrongAnswersScore = 0;
      correctAnswers.empty();
      correctAnswers.append(correctAnswersScore);
      wrongAnswers.empty();
      wrongAnswers.append(wrongAnswersScore);
      gameClock.empty();
    }

    $('#guideButton').on('click', function() {
      $('.guide').toggle('hidden');
    })

});
