$(function () {
  var baseUrl = 'http://localhost:5000/'
  var factories = $('ul.factories')

  test();

  function test() {
    factories.append('<li> test </li>');
  }

  renderList();

  function renderList() {
    $.getJSON({
      url: baseUrl + 'factory/list/'
    }).done(function (data) {
      console.log(data);
      factories.empty();
      data.forEach(function (factory) {
        factories.append('<li>' + factory.supervisor.firstName + '</li>');
      })
    }).fail(function () {
      console.log("error");
    });
  }

  var pollCreate = $('#pollCreate');
  var pollForm = $('#pollForm');

  pollCreate.on('click', function () {
    pollForm.toggle();
  })

  jQuery(function ($) {
      var data1 = [12, 10, 20, 40, 30, 60, 50];
      var data2 = [70, 12, 8, 10];


      $("#chart1").shieldChart({
          exportOptions: {
              image: false,
              print: false
          },
          axisY: {
              title: {
                  text: "Break-Down for selected quarter"
              }
          },
          dataSeries: [{
              seriesType: "bar",
              data: data1
          }]
      });

      $("#chart2").shieldChart({
          exportOptions: {
              image: false,
              print: false
          },
          axisY: {
              title: {
                  text: "Break-Down for selected quarter"
              }
          },
          dataSeries: [{
              seriesType: "bar",
              data: data2
          }]
      });
  });

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
