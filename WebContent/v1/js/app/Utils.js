/**
 * 
 */

var randomColorFactor = function() {
	return Math.round(Math.random() * 255);
};
var randomColor = function() {
    return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',.7)';
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
