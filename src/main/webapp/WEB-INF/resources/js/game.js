$(function() {

  // var gamePollList;
  // var gameQuestion = $('#gameQuestion');
  // var gameAnswers = $('#gameAnswers');
  // var gameClock = $('#gameClock');
  // var wrongAnswers = $('#wrongAnswers');
  // var correctAnswers = $('#correctAnswers');
  // var startGame = $('#startGame');
  // var closeGame = $('#closeGame');
  // var nextGame = $('#nextGame');
  // var gameBoard = $('#gameBoard');
  // var noGameView = $('#noGameView');
  // var afterGameView = $('#afterGameView')
  // var previousGamePoll = $('#previousGamePoll');
  // var randomPoll;
  // var previousRandomPoll;
  // var correctAnswersScore = 0;
  // var wrongAnswersScore = 0;
  // var pollList = [];
  //
  //
  //
  // function getGamePolls() {
  //   $.getJSON({
  //     url: 'polls/game'
  //   }).done(function(data) {
  //     console.log(data);
  //     // list.empty();
  //     data.forEach(function(poll) {
  //       console.log(poll);
  //       pollList.push(poll);
  //     })
  //   });
  // }
  //
  // // function getGamePolls(endpoint) {
  // //   ajax.ajaxGetCallback(endpoint, function(response) {
  // //     var totalPages = response.totalPages;
  // //     if (totalPages <= 0) {
  // //       totalPages = 1;
  // //     }
  // //     $pagination.twbsPagination('destroy');
  // //     $pagination.twbsPagination($.extend({}, defaultOpts, {
  // //       totalPages: totalPages,
  // //       onPageClick: function(evt, page) {
  // //         closedPolls.empty();
  // //         var param = '?page=' + (page - 1);
  // //         if (endpoint.includes('?')) {
  // //           param = '&page=' + (page - 1);
  // //         }
  // //         ajax.ajaxGetCallback(endpoint + param, function(response) {
  // //           var data = response.content;
  // //           data.forEach(function(elem) {
  // //             var poll = elem.poll;
  // //
  // //           })
  // //         });
  // //       }
  // //     }));
  // //   });
  // // }
  //
  // getGamePolls('/polls/game');
  //
  // function renderGame() {
  //   gamePollList = pollList.slice();
  //   shuffleArray(gamePollList);
  //   console.log(gamePollList);
  //   var gameClockTimer = 10;
  //   renderGameQuestion();
  //   var gameTimer = setInterval(function() {
  //     gameClock.empty();
  //     gameClock.append(gameClockTimer + ' s');
  //     if (gameClockTimer > 0) {
  //       gameClockTimer -= 1;
  //     } else {
  //       gameClock.empty();
  //       gameClock.append("TIME'S UP");
  //       gameQuestion.empty();
  //       gameQuestion.append("GAME OVER");
  //       gameAnswers.empty();
  //       $('#pollsResults').toggle('hidden');
  //       clearInterval(gameTimer);
  //       previousGamePoll.empty();
  //       $('#scoreMessage').empty();
  //       $('#scoreMessage').append("Your score is " + correctAnswersScore + " correct answers and " + wrongAnswersScore + " wrong answers. <br>You think you can do better?");
  //     }
  //
  //   }, 1000);
  // }
  //
  // function shuffleArray(array) {
  //   for (var i = array.length - 1; i > 0; i--) {
  //     var j = Math.floor(Math.random() * (i + 1));
  //     var temp = array[i];
  //     array[i] = array[j];
  //     array[j] = temp;
  //   }
  // }
  // // console.log(pollList);
  // function renderGameQuestion() {
  //   if (randomPoll != null) {
  //     previousRandomPoll = randomPoll;
  //   }
  //   console.log(gamePollList);
  //   console.log(gamePollList.length);
  //   console.log(gamePollList.length > 0);
  //   if (gamePollList.length > 0) {
  //     randomPoll = gamePollList.pop(randomPoll);
  //     gameQuestion.empty();
  //     gameQuestion.append(randomPoll.question);
  //     var randomPollAnswers = '';
  //     ajax.ajaxGetCallback('/polls/' + randomPoll.id + '/answers', function(response) {
  //       response.forEach(function(elem) {
  //         var answer = elem.answer;
  //         randomPollAnswers += '<div class="form-check">' +
  //           '<label class="form-check-label">' +
  //           '<input type="radio" class="form-check-input" name="optionsRadios" value="' + answer.id + '" checked="">' +
  //           answer.content +
  //           '</label>' +
  //           '</div>'
  //       });
  //       gameAnswers.empty();
  //       gameAnswers.append(randomPollAnswers);
  //     })
  //   } else {
  //     gameAnswers.empty();
  //     gameAnswers.append("no more questions left");
  //   }
  // }
  //
  // gameAnswers.on('click', '.form-check-input', function(e) {
  //   renderGameQuestion();
  //   correctAnswersScore += 1;
  //   correctAnswers.empty();
  //   correctAnswers.append(correctAnswersScore);
  //   wrongAnswers.empty();
  //   wrongAnswers.append(wrongAnswersScore);
  //   previousGamePoll.empty();
  //
  //
  //   if (previousRandomPoll != null) {
  //     var charContent = [];
  //     ajax.ajaxGetCallback('/polls/' + previousRandomPoll.id + '/answers', function(response) {
  //       response.forEach(function(elem) {
  //         var answer = elem.answer;
  //         var answerData = elem.answerNumberData;
  //         charContent.push({
  //           y: answerData.percent,
  //           label: answer.content
  //         })
  //       });
  //       previousGamePoll.append('<p>Answers from previous poll:</p><div class="card border-danger mb-3" style="max-width: 100%;">' +
  //         '<div class="card-header">' +
  //         previousRandomPoll.question +
  //         '</div>' +
  //         '<div class="card-body">' +
  //         '<div id="chartContainer' + previousRandomPoll.id + '" style="height: 370px; width: 100%;"></div>' +
  //         '</div></div>');
  //       var chart = new CanvasJS.Chart("chartContainer" + previousRandomPoll.id, {
  //         animationEnabled: true,
  //         theme: "light2", // "light1", "light2", "dark1", "dark2"
  //         title: {
  //           text: ""
  //         },
  //         axisY: {
  //           title: "%"
  //         },
  //         data: [{
  //           type: "column",
  //           showInLegend: true,
  //           legendMarkerColor: "grey",
  //           // legendText: pollData.totalAnswers + " answers",
  //           dataPoints: charContent
  //         }]
  //       });
  //       chart.render();
  //     });
  //   }
  //
  //
  // })
  //
  // startGame.on('click', function(e) {
  //   toggleGameView();
  //   $('#pollsResults').toggle('hidden');
  //   renderGame();
  // })
  //
  // closeGame.on('click', function(e) {
  //   toggleGameView();
  //   resetGame();
  // })
  //
  // nextGame.on('click', function() {
  //   $('#pollsResults').toggle('hidden');
  //   resetGame();
  //   renderGame();
  // })
  //
  // function toggleGameView() {
  //   gameBoard.toggle('hidden');
  //   noGameView.toggle('hidden');
  //   afterGameView.toggle('hidden');
  //   // closeGame.toggle('hidden');
  //   // startGame.toggle('hidden');
  //   // nextGame.toggle('hidden');
  // }
  //
  // function resetGame() {
  //   correctAnswersScore = 0;
  //   wrongAnswersScore = 0;
  //   correctAnswers.empty();
  //   correctAnswers.append(correctAnswersScore);
  //   wrongAnswers.empty();
  //   wrongAnswers.append(wrongAnswersScore);
  //   gameClock.empty();
  // }
  //
  // $('#guideButton').on('click', function() {
  //   $('.guide').toggle('hidden');
  // })




})
