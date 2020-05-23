<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="up-comingeve">
		<div class="container-fluid ">
			<div class="slider">
				<div class="col-md-12">
					<div class="heading">
						<h3>Up Coming Events</h3>
					</div>
				</div>
				<div class="container-fluid">
				<div id="upEventdiv">
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="up-comingeve second-event mt-5">
		<div class="container-fluid ">
			<div class="slider">
				<div class="col-md-12">
					<div class="heading">
						<h3>No.of Events</h3>
					</div>
				</div>
				<div class="container-fluid">
                     <div id="eventdiv"></div>
				</div>
			</div>
		</div>
	</section>

	<section class="up-comingeve third-event">
		<div class="container-fluid ">
			<div class="slider">
				<div class="col-md-12">
					<div class="heading">
						<h3>Previous Events</h3>
					</div>
				</div>
				<div class="container-fluid">
					                     <div id="preeventdiv"></div>
				</div>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript">
	
	var breakpoint = {
		// Small screen / phone
		sm : 576,
		// Medium screen / tablet
		md : 768,
		// Large screen / desktop
		lg : 992,
		// Extra large screen / wide desktop
		xl : 1200
	};

		 $(document).ready(function () {
		 var upEvent=0;
		 var event=1;
		 var preEvent=2;

	        getUpcomingEvents(upEvent);
	        getEvents(event);
	        getPreEvents(preEvent);
	        $('#slickup').slick({
	    		autoplay : true,
	    		autoplaySpeed : 1500,
	    		draggable : true,
	    		infinite : true,
	    		dots : false,
	    		arrows : false,
	    		speed : 1000,
	    		mobileFirst : true,
	    		slidesToShow : 1,
	    		responsive : [ {
	    			breakpoint : breakpoint.sm,
	    			settings : {
	    				slidesToShow : 2,
	    			}
	    		}, {
	    			breakpoint : breakpoint.md,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		}, {
	    			breakpoint : breakpoint.lg,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		}, {
	    			breakpoint : breakpoint.xl,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		} ]
	    	});

	    	// slick slider
	    	$('#slick').slick({
	    		autoplay : true,
	    		autoplaySpeed : 1500,
	    		draggable : true,
	    		infinite : true,
	    		dots : false,
	    		arrows : false,
	    		speed : 1000,
	    		mobileFirst : true,
	    		slidesToShow : 1,
	    		responsive : [ {
	    			breakpoint : breakpoint.sm,
	    			settings : {
	    				slidesToShow : 2,
	    			}
	    		}, {
	    			breakpoint : breakpoint.md,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		}, {
	    			breakpoint : breakpoint.lg,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		}, {
	    			breakpoint : breakpoint.xl,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		} ]
	    	});

	    	$('#slickprevious').slick({
	    		autoplay : true,
	    		autoplaySpeed : 1500,
	    		draggable : true,
	    		infinite : true,
	    		dots : false,
	    		arrows : false,
	    		speed : 1000,
	    		mobileFirst : true,
	    		slidesToShow : 1,
	    		responsive : [ {
	    			breakpoint : breakpoint.sm,
	    			settings : {
	    				slidesToShow : 2,
	    			}
	    		}, {
	    			breakpoint : breakpoint.md,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		}, {
	    			breakpoint : breakpoint.lg,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		}, {
	    			breakpoint : breakpoint.xl,
	    			settings : {
	    				slidesToShow : 4,
	    			}
	    		} ]
	    	});



	    });
	    function getUpcomingEvents(upEvent){
	        $.ajax({
	            type : "GET",
	            url : "${pageContext.request.contextPath}/studt/getEvents",
	            data:{
	            	eventId:upEvent
	            },
	            async : false,
	            success : function(data) {
	            	
	            	if(data.response.length==0){
		               	 document.getElementById('upEventdiv').innerHTML='<div class="mtdata">No Data!!</div>'
	            	}
	            	else{
	            		var htmlCont='<div id="slickup">';	
	             	  $.each(data.response, function(i, upEvent) {
	             		 htmlCont+='<div class="slide">'+
					'<div class="card events-card">'+
						'<div class="card-body">'+
							'<div class="container">'+
								'<div class="row">'+
									'<div class="col-sm-3 col-lg-3">'+
										'<div class="float-left event-num">'+
											'<h4>'+upEvent.event_month+'</h4>'+
											'<h4>'+upEvent.event_day+'</h4>'+
										'</div>'+
									'</div>'+
									'<div class="col-sm-9 col-lg-9">'+
										'<div class="event-content">'+
											'<h4>'+upEvent.eventName+'</h4>'+
											'<h4 class="event-time">'+upEvent.eventTime+'</h4>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="col-lg-12">'+
										'<div class="co-creation-btn">'+
											'<form action="${pageContext.request.contextPath}/common/gethubAddress" method="POST">'+
												'<input type="hidden" name="eventId" value="'+upEvent.id+'" /> '+
												'<input type="submit" value="View" class="btn btn-1" />'+
											'</form>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'+
					'</div>';
	             	  });
	             	 htmlCont+='</div>';
	               document.getElementById('upEventdiv').innerHTML=htmlCont;

	            	}

	              },
	            error : function(e) {
	                alert('Error: ' + e);
	            }
	        }).done(function(data) {
	            console.log(data);
	        });
	    }
	    function getEvents(upEvent){
	        $.ajax({
	            type : "GET",
	            url : "${pageContext.request.contextPath}/studt/getEvents",
	            data:{
	            	eventId:upEvent
	            },
	            async : false,
	            success : function(data) {
	            	if(data.response.length==0){
	               	 document.getElementById('eventdiv').innerHTML='<div class="mtdata">No Data!!</div>'

	            	}
	            	else{
	            		var htmlCont='<div id="slick">';
	             	  $.each(data.response, function(i, upEvent) {
	             		 htmlCont+='<div class="slide">'+
					'<div class="card events-card">'+
						'<div class="card-body">'+
							'<div class="container">'+
								'<div class="row">'+
									'<div class="col-sm-3 col-lg-3">'+
										'<div class="float-left event-num">'+
											 '<h4>'+upEvent.event_month+'</h4>'+
											'<h4>'+upEvent.event_day+'</h4>'+
                                     '</div>'+
									'</div>'+
									'<div class="col-sm-9 col-lg-9">'+
										'<div class="event-content">'+
											'<h4>'+upEvent.eventName+'</h4>'+
											'<h4 class="event-time">'+upEvent.eventTime+'</h4>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row">'+
									'<div class="col-lg-12">'+
										'<div class="co-creation-btn">'+
											'<form action="${pageContext.request.contextPath}/common/gethubAddress" method="POST">'+
												'<input type="hidden" name="eventId" value="'+upEvent.id+'" /> '+
												'<input type="submit" value="View" class="btn btn-1" />'+
											'</form>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'+
					'</div>';
	             	  });

	             	 htmlCont+='</div>';
	                 document.getElementById('eventdiv').innerHTML=htmlCont;

	            	}

	              },
	            error : function(e) {
	                alert('Error: ' + e);
	            }
	        }).done(function(data) {
	            console.log(data);
	        });
	    }
	    function getPreEvents(upEvent){
	        $.ajax({
	            type : "GET",
	            url : "${pageContext.request.contextPath}/studt/getEvents",
	            data:{
	            	eventId:upEvent
	            },
	            async : false,
	            success : function(data) {
             if(data.response.length==0){
            	 document.getElementById('preeventdiv').innerHTML='<div class="mtdata">No Data!!</div>'
	            	}
	            	else{
	            	var htmlCont='<div id="slickprevious">';
	             	  $.each(data.response, function(i, upEvent) {
	             		 htmlCont+='<div class="slide">'+
					'<div class="card events-card">'+
						'<div class="card-body">'+
							'<div class="container">'+
								'<div class="row">'+
									'<div class="col-sm-3 col-lg-3">'+
										'<div class="float-left event-num">'+
											'<h4>'+upEvent.event_month+'</h4>'+
											'<h4>'+upEvent.event_day+'</h4>'+
										'</div>'+
									'</div>'+
									'<div class="col-sm-9 col-lg-9">'+
										'<div class="event-content">'+
											'<h4>'+upEvent.eventName+'</h4>'+
											'<h4 class="event-time">'+upEvent.eventTime+'</h4>'+
										'</div>'+
									'</div>'+
								'</div>'+
								'<div class="row mt-3">'+
									'<div class="col-lg-12">'+
										'<div class="co-creation-btn">'+
											'<form action="${pageContext.request.contextPath}/common/gethubAddress" method="POST">'+
												'<input type="hidden" name="eventId" value="'+upEvent.id+'" /> '+
												'<input type="submit" value="View" class="btn btn-1" />'+
											'</form>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'+
					'</div>';
	             	  });

	             	 htmlCont+='</div>';
	                 // $("#preeventdiv").append(htmlContent);
                      document.getElementById('preeventdiv').innerHTML=htmlCont;
	            	}
	              },
	            error : function(e) {
	                alert('Error: ' + e);
	            }
	        }).done(function(data) {
	            console.log(data);
	        });
	    }
</script>