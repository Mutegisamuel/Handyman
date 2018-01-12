var nexusratio = 0.5628415300546448;
//43.4512445,-80.498576

$(document).ready(function(){
	//Resize();
});

function Resize(){
	var h = $(document).height();
	console.log(h);
	if(h > 512){
		$("#nexus6p").css('height', 512);
		$("#nexus6p").css('width', 512 * nexusratio);
	}
	else{
		var w = h * nexusratio;
		$("#nexus6p").css('height', h);
		$("#nexus6p").css('width', w);
	}
}

$(".menu").on("click", function(e){
	$(this).toggleClass("on");
});


$(".menu-item").on("click", function(e){
	$(".profile-bottom .profile-container").removeClass("open");
	var c = $(this).attr('menu-data');
	console.log(c);
		$("."+c).addClass('open');
});
