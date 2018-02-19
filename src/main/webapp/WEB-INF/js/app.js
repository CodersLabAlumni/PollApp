$(function() {
  var baseUrl = 'http://localhost:5000/'
  var ongoingPolls = $('#ongoingPolls');
  var closedPolls = $('#closedPolls');
  var form = $('#pollForm');

  //
  // test();
  //
  // function test() {
  //   factories.append('<li> test </li>');
  // }
  //
  renderCategoriesList()
  renderOpenedList();
  renderClosedList();

  function renderClosedList() {
    $.ajax({
      url: baseUrl + 'polls/closed/'
    }).done(function(data) {
      console.log(data);
      closedPolls.empty();
      data.forEach(function(poll) {
        var pollAnswers = '';
        poll.answers.forEach(function(answer) {
          pollAnswers += '<div class="form-check">' +
            '<label class="form-check-label">' +
            '<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1" checked="">' +
            answer.content +
            '</label>' +
            '</div>'
        })
        closedPolls.append('<div class="text-white bg-secondary mb-3" style="max-width: 40rem;"><div class="card-header">' +
          poll.question +
          '</div><div class="card-body">' +
          '<fieldset class="form-group">' +
          pollAnswers +
          '</fieldset></div></div>');
      })
    }).fail(function(e) {
      console.log("error");
      console.log(e);
    });
  }

  function renderOpenedList() {
    $.ajax({
      url: baseUrl + 'polls/ongoing/'
    }).done(function(data) {
      console.log(data);
      ongoingPolls.empty();
      data.forEach(function(poll) {
        var pollAnswers = '';
        poll.answers.forEach(function(answer) {
          pollAnswers += '<div class="form-check">' +
            '<label class="form-check-label">' +
            '<input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1" checked="">' +
            answer.content +
            '</label>' +
            '</div>'
        })
        ongoingPolls.append('<div class="card border-danger mb-3" style="max-width: 40rem;">' +
          '<div class="card-header">' +
          poll.question +
          '</div>' +
          '<div class="card-body">' +
          '<div id="chartContainer' + poll.id + '" style="height: 370px; width: 100%;"></div>' +
          '</div></div>');
        var charContent = [];
        poll.answers.forEach(function(answer) {
          charContent.push({
            y: answer.id,
            label: answer.content
          })
        })
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
      })
    }).fail(function(e) {
      console.log("error");
      console.log(e);
    });
  }

  // renderCategoriesList()

  var closedPollsCategories = $('#closedPollsCategories');
  var openPollsCategories = $('#openPollsCategories');

  function renderCategoriesList() {
    $.ajax({
      url: baseUrl + 'categories/'
    }).done(function(data) {
      data.forEach(function(category) {
        openPollsCategories.append('<a class="dropdown-item category-choice" href="#">' + category.name + '</a>');
        closedPollsCategories.append('<a class="dropdown-item category-choice" data-category="' + category.id + '" href="#">' + category.name + '</a>');
      })
    }).fail(function(e) {
      console.log("error");
      console.log(e);
    });
  }

  var category = $('.dropdown-menu');

  category.on('mouseover', ".category-choice", function() {
    console.log(this);
    console.log($(this).data("category"));
    // console.log($(this).parents('.text-white'));
    // $(this).parents('.text-white').fadeOut();
  })

  form.on('submit', function(e) {

    //process form
    var data = {};
    $(this).find('input[type=text]').each(function(index, elem) {
      data[elem.name] = elem.value
    });

    console.log(data);

    $.post({
      headers: {
        'Content-Type': 'application/json'
      },
      url: baseUrl + 'polls/create',
      data: JSON.stringify(data),
      dataType: 'json' //potencjalnie to mozna wyrzucic
    }).done(function(res) {
      console.log(res);
      renderList();
    }).fail(function(xhr, status, error) {
      console.log(xhr, status, error);
    })

    this.reset();
    e.preventDefault();
  });

  var pollCreate = $('#pollCreate');
  var pollForm = $('#pollForm');

  pollCreate.on('click', function() {
    pollForm.toggle();
  })

  var button = $('.form-check-input');

  closedPolls.on('click', '.form-check-input', function() {
    console.log(this);
    $(this).parents('.text-white').fadeOut();
  })



  // var chart = new CanvasJS.Chart("chartContainer1", {
  //   animationEnabled: true,
  //   theme: "light2", // "light1", "light2", "dark1", "dark2"
  //   title: {
  //     text: ""
  //   },
  //   axisY: {
  //     title: "%"
  //   },
  //   data: [{
  //     type: "column",
  //     showInLegend: true,
  //     legendMarkerColor: "grey",
  //     legendText: "100 answers",
  //     dataPoints: [{
  //         y: 10,
  //         label: "Philosopher's Stone"
  //       },
  //       {
  //         y: 7,
  //         label: "Chamber of Secrets"
  //       },
  //       {
  //         y: 13,
  //         label: "Prisoner of Azkaban"
  //       },
  //       {
  //         y: 15,
  //         label: "Goblet of Fire"
  //       },
  //       {
  //         y: 20,
  //         label: "Order of the Phoenix"
  //       },
  //       {
  //         y: 25,
  //         label: "Half-Blood Prince"
  //       },
  //       {
  //         y: 30,
  //         label: "Deathly Hallows"
  //       }
  //     ]
  //   }]
  // });
  // chart.render();
  //
  // var chart = new CanvasJS.Chart("chartContainer2", {
  //   animationEnabled: true,
  //   theme: "light2", // "light1", "light2", "dark1", "dark2"
  //   title: {
  //     text: ""
  //   },
  //   axisY: {
  //     title: "%"
  //   },
  //   data: [{
  //     type: "column",
  //     showInLegend: true,
  //     legendMarkerColor: "grey",
  //     legendText: "138 answers",
  //     dataPoints: [{
  //         y: 50,
  //         label: "YES"
  //       },
  //       {
  //         y: 4,
  //         label: "NO"
  //       },
  //       {
  //         y: 20,
  //         label: "Don't know, don't care"
  //       },
  //       {
  //         y: 26,
  //         label: "Who is Donald Trump"
  //       }
  //     ]
  //   }]
  // });
  // chart.render();
  //
  // var chart = new CanvasJS.Chart("chartContainer3", {
  //   animationEnabled: true,
  //   theme: "light2", // "light1", "light2", "dark1", "dark2"
  //   title: {
  //     text: ""
  //   },
  //   axisY: {
  //     title: "%"
  //   },
  //   data: [{
  //     type: "column",
  //     showInLegend: true,
  //     legendMarkerColor: "grey",
  //     legendText: "20 answers",
  //     dataPoints: [{
  //         y: 80,
  //         label: "Venezuela"
  //       },
  //       {
  //         y: 70,
  //         label: "Saudi"
  //       },
  //       {
  //         y: 60,
  //         label: "Canada"
  //       },
  //       {
  //         y: 50,
  //         label: "Iran"
  //       },
  //       {
  //         y: 40,
  //         label: "Iraq"
  //       },
  //       {
  //         y: 30,
  //         label: "Kuwait"
  //       },
  //       {
  //         y: 20,
  //         label: "UAE"
  //       },
  //       {
  //         y: 10,
  //         label: "Russia"
  //       }
  //     ]
  //   }]
  // });
  // chart.render();

  // function renderList() {
  //   $.ajax({
  //     url: baseUrl + 'factory/list/',
  //     type: 'GET',
  //     dataType: 'json'
  //   }).done(function (data) {
  //     console.log(data);
  //     factories.empty();
  //     data.forEach(function (factory) {
  //       factories.append('<li>' + factory.name + '</li>');
  //     })
  //   }).fail(function () {
  //     console.log("error");
  //   });
  // }

})
