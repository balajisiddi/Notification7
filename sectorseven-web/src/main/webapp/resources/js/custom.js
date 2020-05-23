//for child info and interests after login
$(document).ready(function(){
	$("#check5").hide();
	$('.chnov2').hide();
	 var childid = $('#chldinfo').children(":selected").attr("id");
	 $('#ch'+childid).show();
	$('input[type=checkbox]').on('change', function(evt){
		var choosenid = $(this).attr('id');
		if($('input[type=checkbox]:checked').length <= 5){
		$("#choosen"+choosenid).toggle();
		}
		if($('input[type=checkbox]:checked').length < 5){
			$("#check5").hide();	
		}
		else{
			$("#check5").show();
		}
		 if($('input[name="interest"]:checked').length > 5){
			 alert("sd")
			 $(this).prop('checked', false);
			 alert("You are allowed to choose 5 interests only!!");
		 }
	 });
	$("#chldinfo").change(function() {
		$('.chnov2').hide();
		  var childid = $(this).children(":selected").attr("id");
		  	$('#ch'+childid).show();
 		});
	
	//for sub interest selection
	function displayList() {
        $.fn.reverse = [].reverse;
        var trigger = $("#trigger"),
            mainTarget = $(".my-nav"),
            targetItem = $('.my-nav__item'),
            html = $("html");

        trigger.on("click", function (event) {
            mainTarget.toggleClass("reveal unreveal");

            targetItem.reverse().each(function (i, el) {
                setTimeout(function () {
                    $(el).toggleClass("visible");
                }, i * 68);
            })

            html.on("click", function () {
                targetItem.removeClass("visible");
                mainTarget.removeClass("reveal");
            });

            event.preventDefault();
            event.stopPropagation();
        });
    }
	$('#interestsId').load('${pageContext.request.contextPath}/studt/getUserInterests',{
		userId:"${userId}",
		userType:"${userType}",
	},function getUserInterests(){
		alert("sd"+"${userId}");
		$.ajax({
			type:"GET",
			url:"",
			data: {
				userId:"${userId}",
				userType:"${userType}",

			},
			success: function(data){
	          alert("as"+JSON.stringify(data));
			},
			error: function(){
				console.log("error occured");
			}
		});
	});
});

