$(document).ready(function() {
	var turnCounter = 1;
	// System names
	var systems = ["sys1", "sys2", "sys3", "sys4", "sys5", "sys6", "sys7"];
	$("#ssdDiv").hide();

	// When clicking the button, create another row of input data fields
	$("#next_turn").click(function() {
		$("#turn_counter").append("<td>Turn" + turnCounter + "</td>");
		var sysCounter = 1;
		systems.forEach(function(i) {
			// For each row that has class that match items in the system list, append a new cell with the new turn number.
			$("." + i).append("<td class='turn" + turnCounter + "'><input class='sys" + sysCounter + "_input' id='sys"+ sysCounter + "_turn" + turnCounter +"'></td>");
			sysCounter = sysCounter + 1;
		});
		turnCounter++;
	});
	
	$("#show_ssd").click(function() {
		$("#ssdDiv").animate({width: "toggle"});
	});
	
	$(".sys1").mouseover(function() {
		$("#system1").css( {opacity: 1});
	}).mouseout(function() {
		$("#system1").css( {opacity: 0.3});
	});

	$(".sys2").mouseover(function() {
		$("#system2").css( {opacity: 1});
	}).mouseout(function() {
		$("#system2").css( {opacity: 0.3});
	});

	$(".sys3").mouseover(function() {
		$("#system3").css( {opacity: 1});
	}).mouseout(function() {
		$("#system3").css( {opacity: 0.3});
	});

	$(".sys4").mouseover(function() {
		$("#system4").css( {opacity: 1});
	}).mouseout(function() {
		$("#system4").css( {opacity: 0.3});
	});

	$("thead").css("font-weight", "bold");
	
});