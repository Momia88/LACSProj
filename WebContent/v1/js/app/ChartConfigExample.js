/**
 * 獲取一個新的chart配置項
 *
 * @param color rgba顏色
 * @param type 圖表類型:line,bar,radar,polarArea,pie,doughnut
 * @param title 顯示圖表的標題
 * @param label 圖表的標籤，鼠標移到圖表某個數據點時顯示的提示文字
 * @param categories 一般是X軸的內容
 * @param data 一般是Y軸的數據
 * @returns 返回圖表的配置參數
 */
function getNewConfig(color, type, title, label, categories, data) {
	var backgroundColor = color;
	var chartOptions = {
		responsive : true,
		legend : {
			display : false,
			position : 'bottom'
		},
		hover : {
			mode : 'label'
		},
		scales : {
			xAxes : [{
				display : true,
				scaleLabel : {
					display : false,
					labelString : 'Month'
				}
			}],
			yAxes : [{
				display : true,
				scaleLabel : {
					display : false,
					labelString : 'Value'
				}
			}]
		},
		title : {
			display : true,
			text : title
		}
	};

	var dataset = {
		label : label,
		data : data,
		fill : false,
		borderDash : [5, 5],
		borderColor : backgroundColor,
		backgroundColor : backgroundColor,
		pointBorderColor : backgroundColor,
		pointBackgroundColor : backgroundColor,
		pointBorderWidth : 1
	};

	var chartData = {
		labels : categories,
		datasets : [dataset]
	};

	var config = {
		type : type,
		data : chartData,
		options : chartOptions,

	};

	config.data = data;
	return config;
}