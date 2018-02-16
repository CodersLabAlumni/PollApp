$(function () {
  // var baseUrl = 'http://localhost:5000/'
  // var factories = $('ul.factories')
  //
  // test();
  //
  // function test() {
  //   factories.append('<li> test </li>');
  // }
  //
  // renderList();
  //
  // function renderList() {
  //   $.getJSON({
  //     url: baseUrl + 'factory/list/'
  //   }).done(function (data) {
  //     console.log(data);
  //     factories.empty();
  //     data.forEach(function (factory) {
  //       factories.append('<li>' + factory.supervisor.firstName + '</li>');
  //     })
  //   }).fail(function () {
  //     console.log("error");
  //   });
  // }

  var pollCreate = $('#pollCreate');
  var pollForm = $('#pollForm');

  pollCreate.on('click', function () {
    pollForm.toggle();
  })

  var button = $('.form-check-input');

  button.on('click', function () {
    console.log($(this).parents('.text-white'));
    $(this).parents('.text-white').fadeOut();
  })


      var chart = new CanvasJS.Chart("chartContainer1", {
	animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
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
		dataPoints: [
			{ y: 10, label: "Philosopher's Stone" },
			{ y: 7,  label: "Chamber of Secrets" },
			{ y: 13,  label: "Prisoner of Azkaban" },
			{ y: 15,  label: "Goblet of Fire" },
			{ y: 20,  label: "Order of the Phoenix" },
			{ y: 25, label: "Half-Blood Prince" },
			{ y: 30,  label: "Deathly Hallows" }
		]
	}]
});
chart.render();

var chart = new CanvasJS.Chart("chartContainer2", {
animationEnabled: true,
theme: "light2", // "light1", "light2", "dark1", "dark2"
title:{
text: ""
},
axisY: {
title: "%"
},
data: [{
type: "column",
showInLegend: true,
legendMarkerColor: "grey",
legendText: "138 answers",
dataPoints: [
{ y: 50, label: "YES" },
{ y: 4,  label: "NO" },
{ y: 20,  label: "Don't know, don't care" },
{ y: 26,  label: "Who is Donald Trump" }
]
}]
});
chart.render();

var chart = new CanvasJS.Chart("chartContainer3", {
animationEnabled: true,
theme: "light2", // "light1", "light2", "dark1", "dark2"
title:{
text: ""
},
axisY: {
title: "%"
},
data: [{
type: "column",
showInLegend: true,
legendMarkerColor: "grey",
legendText: "20 answers",
dataPoints: [
{ y: 80, label: "Venezuela" },
{ y: 70,  label: "Saudi" },
{ y: 60,  label: "Canada" },
{ y: 50,  label: "Iran" },
{ y: 40,  label: "Iraq" },
{ y: 30, label: "Kuwait" },
{ y: 20,  label: "UAE" },
{ y: 10,  label: "Russia" }
]
}]
});
chart.render();

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
