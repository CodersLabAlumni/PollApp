$(function() {

  var ajax = new Ajax();
  var formUtil = new FormUtil();
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
  var gamePoints = 0;




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
        gameAnswers.empty();
        endGame();
      }

    }, 1000);
  }

  function endGame() {
    gameQuestion.empty();
    gameQuestion.append("GAME OVER");
    gameAnswers.append('<form method="post" id="gameScoreForm"><fieldset>' +
    '<div class="form-group"><div id="score">' +
    '<label for="exampleInputEmail1">Enter name</label>' +
    '<input type="text" class="form-control" name="name" id="name"" aria-describedby="scoreName" placeholder="Enter name">' +
    '</div></div>' +
    '<button type="submit" class="btn btn-primary">Submit Score</button>' +
    '</fieldset></form>');
    $('#pollsResults').toggle('hidden');
    clearInterval(gameTimer);
    previousGamePoll.empty();
    $('#scoreMessage').empty();
    $('#scoreMessage').append("Your score is " + correctAnswersScore +
    " correct answers and " + wrongAnswersScore +
    " wrong answers. <br>You think you can do better?");
    gamePoints = (correctAnswersScore * 100) - (wrongAnswersScore * 100) + gameClockTimer * 5;
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
      gameClock.empty();
      gameClock.append(gameClockTimer);
      gameAnswers.empty();
      gameAnswers.append("You have finished ahead of time, with " + gameClockTimer + " s left to spare");
      endGame();
    }
  }

  gameAnswers.on('click', '.form-check-input', function(e) {
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

  gameAnswers.on('submit', function(e) {
    e.preventDefault();
    var gameScore = formUtil.createObjectFromForm($('#score'));
    gameScore.score = gamePoints;
    ajax.ajaxPostCallback("/gameScores", gameScore, function(response) {
    });
    gameAnswers.empty();
    gameAnswers.append('Your score has been saved and score board updated');
    renderScoreBoard('/gameScores');
  })

  function renderScoreBoard(endpoint) {
    var rank = 1;
    $('#scoreBoard').empty();
    ajax.ajaxGetCallback(endpoint, function (response) {
      response.forEach(function(nameScore) {
        $('#scoreBoard').append('<tr><th scope="row"> ' + rank + '</th>' +
      '<td>' + nameScore.name + '</td>' +
      '<td>' + nameScore.score + '</td></tr>')
      rank++;
      })
    })
  }

  renderScoreBoard('/gameScores');

})
