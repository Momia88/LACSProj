var randomScalingFactor = function() {
	return (Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100);
};
var randomColorFactor = function() {
	return Math.round(Math.random() * 255);
};

var brightAvg = function(list) {
	var sum = 0;
	for (var i = 0; i < list.length; i++) {
		sum += list[i];
	}
	var avg = sum / list.length;
	return parseInt(avg);
};

Date.prototype.dateDiff = function(interval, objDate) {
	var dtEnd = new Date(objDate);
	if (isNaN(dtEnd))
		return null;
	switch (interval) {
	case "s":
		return parseInt((dtEnd - this) / 1000);
	case "n":
		return parseInt((dtEnd - this) / 60000);
	case "h":
		return parseInt((dtEnd - this) / 3600000);
	case "d":
		return parseInt((dtEnd - this) / 86400000);
	case "w":
		return parseInt((dtEnd - this) / (86400000 * 7));
	case "m":
		return (dtEnd.getMonth() + 1) + ((dtEnd.getFullYear() - this.getFullYear()) * 12) - (this.getMonth() + 1);
	case "y":
		return dtEnd.getFullYear() - this.getFullYear();
	}
};

$('#sumitBtn').click(function drawBarChart() {
	var mType = "DHD850";
	var cType = "brightness";
	var sTime = document.getElementById("startdatepicker").value;
	var eTime = document.getElementById("enddatepicker").value;

	var titleStr = "DHD850 " + sTime + " - " + eTime;
	var s = new Date(sTime);
	var e = new Date(eTime);
	if (s.dateDiff("m", e) > 12 || s.dateDiff("m", e) == null) {
		alert("日期錯誤或超過12個月!");
		return;
	}
	jsonData = $.ajax({
		// url : 'http://192.168.3.88:8080/LACSProj/api/v1/service/chroma',
		url : 'http://localhost:8080/LACSProj/api/v1/service/chroma',
		type : "POST",
		data : {
			modelType : mType,
			chartType : cType,
			startTime : sTime,
			endTime : eTime
		},
		dataType : 'json',
		success : function(chartJsonData) {
			// Split timestamp and data into
			// separate arrays
			var barLebels = chartJsonData.xLabels;
			var barValues = chartJsonData.yValues;

			// bar color
			var r = 0;
			var g = 0;
			var b = 0;
			var backColor = [];
			var borderColor = [];
			for (var i = 0; i < barLebels.length; i++) {
				r = randomColorFactor();
				g = randomColorFactor();
				b = randomColorFactor();
				backColor.push('rgba(' + r + "," + g + "," + b + ",0.5)");
				borderColor.push('rgba(' + r + "," + g + "," + b + ",1)");
			}

			// insert html value
			var maxValue = chartJsonData.maxValue;
			var minValue = chartJsonData.minValue;
			var avgValue = chartJsonData.avgValue;
			var stdValue = chartJsonData.stdValue;

			document.getElementById('maxlight').innerHTML = maxValue;
			document.getElementById('minlight').innerHTML = minValue;
			document.getElementById('average').innerHTML = avgValue;
			document.getElementById('standard').innerHTML = 0;

			// Chart Setting
			var avgIndex = (avgValue - minValue) / (maxValue - minValue) * 10 + 2;

			var bubbleData = {
				datasets : [{
					label : 'First Dataset',
					data : [{
						x : 0.2938,
						y : 0.3130,
						r : 2
					}, {
						x : 0.2931,
						y : 0.3123,
						r : 2
					}, {
						x : 0.2999,
						y : 0.3220,
						r : 2
					}, {
						x : 0.2946,
						y : 0.3129,
						r : 2
					}, {
						x : 0.2904,
						y : 0.3066,
						r : 2
					}, {
						x : 0.2904,
						y : 0.3092,
						r : 2
					}, {
						x : 0.2944,
						y : 0.3190,
						r : 2
					}, {
						x : 0.2941,
						y : 0.3173,
						r : 2
					}, {
						x : 0.2905,
						y : 0.3097,
						r : 2
					}, {
						x : 0.2939,
						y : 0.3206,
						r : 2
					}, {
						x : 0.2904,
						y : 0.3113,
						r : 2
					}, {
						x : 0.2965,
						y : 0.3214,
						r : 2
					}, {
						x : 0.2908,
						y : 0.3111,
						r : 2
					}, {
						x : 0.2926,
						y : 0.3152,
						r : 2
					}, {
						x : 0.2926,
						y : 0.3129,
						r : 2
					}, {
						x : 0.2976,
						y : 0.3245,
						r : 2
					}],
					backgroundColor : "#FF6384",
					hoverBackgroundColor : "#FF6384",
				}],
			};

			var options = {
				responsive : false,
				title : {
					display : true,
					text : titleStr
				},
				elements : {
					points : {
						borderWidth : 1,
						borderColor : 'rgb(0, 0, 0)'
					}
				},
				scales : {
					xAxes : [{
						ticks : {
							suggestedMin : 0.27,
							suggestedMax : 0.32,
						}
					}],
					yAxes : [{

						ticks : {
							suggestedMin : 0.29,
							suggestedMax : 0.34,
						}
					}]
				},
			};

			var config = {
				type : 'bubble',
				data : bubbleData,
				options : options
			};

			// show average line
			var originalLineDraw = Chart.controllers.bar.prototype.draw;
			Chart.helpers.extend(Chart.controllers.bar.prototype, {
				draw : function() {
					originalLineDraw.apply(this, arguments);

					var chart = this.chart;
					var ctx = chart.chart.ctx;

					var index = chart.config.data.lineAtIndex;
					if (index) {
						var xaxis = chart.scales['x-axis-0'];
						var yaxis = chart.scales['y-axis-0'];
						ctx.fillStyle = "#0000FF";
						ctx.textAlign = "center";
						ctx.textBaseline = "bottom";
						ctx.save();
						ctx.beginPath();
						ctx.moveTo(xaxis.getPixelForValue(undefined, index), yaxis.top);
						ctx.strokeStyle = '#FF0000';
						ctx.lineTo(xaxis.getPixelForValue(undefined, index), yaxis.bottom);
						ctx.stroke();
						ctx.restore();
						ctx.fillText(avgValue, xaxis.getPixelForValue(undefined, index), yaxis.top);

					}
				}
			});

			var ctx = document.getElementById("myChart");
			var myChart = new Chart(ctx, config);

		},

		error : function() {
			alert("ERROR!!!");
		}
	});
});

