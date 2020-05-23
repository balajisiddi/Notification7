<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	 <section class="library-main howtoget">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div id="back"></div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 vertical-tabs bd-example bd-example-tabs">
                        <div class="row">
                            <div class="col-sm-8 col-lg-9">
                                <div class="tab-content" id="pills-tabContent">
                                    <div class="container" id="htgt">
                                        <div class="row mt-3">
                                            <div class="col-lg-12">
                                                <div class="heading">
                                                    <h2 style="color:#e85a2a">How To Get There?</h2>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                                        aria-labelledby="pills-home-tab">
                                        <div class="container">
                                            <div class="row ">
                                                <div class="col-lg-12">
                                                    <div class="right-blogs">
                                                        <p class="pt-3" id="howTOGetThere"> ${hgt.howToGetThere}
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                                        aria-labelledby="pills-profile-tab">
                                        <div class="container">
                                            <div class="row mt-4" id="basicEligibilities">
                                                <div class="col-sm-12 col-lg-4">
                                                    <div class="left-img">
                                                        <img src="../resources/images/course-img/School.PNG" alt="am-i-the" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-12 col-lg-8">
                                                    <div class="right-blogs">
                                                        <div class="row mb-2">
                                                            <div class="col-md-12 mt-2">
                                                                <h5>Basic Eligibility</h5>
                                                            <ul id="basicEligibility"><!--  --></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mt-4" id="ugCourses">
                                                <div class="col-md-4">
                                                    <div class="left-img">
                                                        <img src="../resources/images/course-img/college1.png" alt="am-i-the" />
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="right-blogs">
                                                        <div class="row mb-2">
                                                            <div class="col-md-12 mt-2">
                                                                <h5>List Of UG Courses:</h5>
                                                            <ul id="ugCourse"><!--  --></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mt-4" id="diplomaCourses">
                                                <div class="col-md-4">
                                                    <div class="left-img">
                                                        <img src="../resources/images/course-img/college2.png" alt="am-i-the" />
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="right-blogs">
                                                        <div class="row mb-2">
                                                            <div class="col-md-12 mt-2">
                                                                <h5>Diploma Courses</h5>
                                                            <ul id="diplomaCourse"><!--  --></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mt-4" id="pgCourses">
                                                <div class="col-md-4">
                                                    <div class="left-img">
                                                        <img src="../resources/images/course-img/college3.png" alt="am-i-the" />
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="right-blogs">
                                                        <div class="row mb-2">
                                                            <div class="col-md-12 mt-2">
                                                                <h5>Post-Graduate Courses</h5>
                                                            <ul id="pgCourse"><!--  --></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row mt-4" id="doctoralCourses">
                                                <div class="col-md-4">
                                                    <div class="left-img">
                                                        <img src="../resources/images/course-img/college4.png" alt="am-i-the" />
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="right-blogs">
                                                        <div class="row mb-2">
                                                            <div class="col-md-12 mt-2">
                                                                <h5>Doctoral Courses</h5>
                                                            <ul id="doctoralCourse"><!--  --></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                               
                                            </div>
                                            <div class="row mt-4" id="certificationCourses">
                                                <div class="col-md-4">
                                                    <div class="left-img">
                                                        <img src="../resources/images/course-img/college5.png" alt="am-i-the" />
                                                    </div>
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="right-blogs">
                                                        <div class="row mb-2">
                                                            <div class="col-md-12 mt-2">
                                                                <h5>Certification Courses</h5>
                                                            <ul id="certificationCourse"><!--  --></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                   
                                    <div class="tab-pane fade" id="pills-entrance" role="tabpanel"
                                        aria-labelledby="pills-entrance-tab">
                                        <div class="container">
                                            <div class="row" id="graduationLevelExams">
                                                <div class="col-md-12">
                                                    <div class="right-blogs pl-4">
                                                        <h5>Graduate-level Entrance Exams</h5>
                                                            <ul id="graduationLevel"><!--  --></ul>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" id="postGraduationLevelExams">
                                                <div class="col-md-12">
                                                    <div class="right-blogs pl-4">
                                                        <h5>Postgraduate level</h5>
                                                            <ul id="postGraduationLevel"><!--  --></ul>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" id="diplomaLevelExams">
                                                <div class="col-md-12">
                                                    <div class="right-blogs pl-4">
                                                        <h5>Diploma level: </h5>
                                                            <ul id="diplomaLevel"><!--  --></ul>
                                                    </div>
                                                </div>
                                            </div>
                                             <div class="row" id="doctoralLevelExams">
                                                <div class="col-md-12">
                                                    <div class="right-blogs pl-4">
                                                        <h5>Doctoral level: </h5>
                                                           <ul id="doctoralLevel"><!--  --></ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-dropdown1" role="tabpanel"
                                        aria-labelledby="pills-dropdown1-tab">
                                       <div class="container">
                                           <div class="row">
												<div class="col-lg-12">
													<div class="activity-heading text-center">
														<h5>India</h5>
													</div>
												</div>
											</div>
                                           <div id="accordionIndia" class="accordion">
												<div class="row">
													<div class="col-md-12">
														<div class="right-blogs pl-4">
															<div class="card" id="cardInstitutionsIndia">
															</div>
														</div>
													</div>
												</div>
											</div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-dropdown2" role="tabpanel"
                                        aria-labelledby="nav-dropdown2-tab">
                                        <div class="container">
                                           <div class="row">
												<div class="col-lg-12">
													<div class="heading text-center">
														<h5>Abroad</h5>
													</div>
												</div>
											</div>
                                            <div id="accordionAbrod" class="accordion">
												<div class="row">
													<div class="col-md-12">
														<div class="right-blogs pl-4">
															<div class="card mb-0" id="cardInstitutionsAbroad">
															</div>
														</div>
													</div>
												</div>
											</div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-dropdown3" role="tabpanel"
                                        aria-labelledby="nav-dropdown3-tab" id="indianScholorShips">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="right-blogs pl-4">
                                                        <h5>Indian Scholorships</h5>
                                                            <ul id="indianScholorShip"><!--  --></ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-dropdown4" role="tabpanel"
                                        aria-labelledby="nav-dropdown4-tab" id="abroadScholorShips">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="right-blogs pl-4">
                                                        <h5>Abroad Scholorships</h5>
                                                            <ul id="abroadScholorShip"><!--  --></ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="pills-person" role="tabpanel"
                                        aria-labelledby="pills-person-tab">
                                        <div class="container" id="successfulPersonalities">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4 col-lg-3">
                                <div class="stickyNav__frame">
                                    <div class="stickyNav is-fixed" >
                                        <div class="stickyNav__inner">
                                            <ul class="nav nav-pills mb-3 stickyNav__links accordion" role="tablist"
                                                id="accordion">
                                                <li class="nav-item stickyNav__link">
                                                    <a class="nav-link active" id="pills-home-tab" data-toggle="pill"
                                                        href="#pills-home" role="tab" aria-controls="pills-home"
                                                        aria-expanded="true">Home</a>
                                                </li>
                                                <li class="nav-item stickyNav__link">
                                                    <a class="nav-link" id="pills-profile-tab" data-toggle="pill"
                                                        href="#pills-profile" role="tab" aria-controls="pills-profile"
                                                        aria-expanded="true">Courses</a>
                                                </li>
                                                <li class="nav-item stickyNav__link">
                                                    <a class="nav-link" id="pills-entrance-tab" data-toggle="pill"
                                                        href="#pills-entrance" role="tab" aria-controls="pills-entrance"
                                                        aria-expanded="true">Entrance Exam</a>
                                                </li>
                                                <li class="nav-item stickyNav__link dropdown dropleft float-right">
                                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
                                                        role="button" aria-haspopup="true"
                                                        aria-expanded="false">Institutions</a>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item" id="pills-dropdown1-tab"
                                                            href="#pills-dropdown1" role="tab" data-toggle="pill"
                                                            aria-controls="pills-dropdown1">India</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item" id="pills-dropdown2-tab"
                                                            href="#pills-dropdown2" role="tab" data-toggle="pill"
                                                            aria-controls="pills-dropdown2">Abroad</a>
                                                    </div>
                                                </li>
                                                <li class="nav-item stickyNav__link dropdown dropleft float-right">
                                                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
                                                        role="button" aria-haspopup="true"
                                                        aria-expanded="false">Scholarships</a>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item" id="pills-dropdown3-tab"
                                                            href="#pills-dropdown3" role="tab" data-toggle="pill"
                                                            aria-controls="pills-dropdown3">India</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a class="dropdown-item" id="pills-dropdown4-tab"
                                                            href="#pills-dropdown4" role="tab" data-toggle="pill"
                                                            aria-controls="pills-dropdown4">Abroad</a>
                                                    </div>

                                                </li>

                                                <li class="nav-item stickyNav__link">
                                                    <a class="nav-link" id="pills-person-tab" data-toggle="pill"
                                                        href="#pills-person" role="tab" aria-controls="pills-person"
                                                        aria-expanded="true">Successful Personalities</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </section>
 </div>
    <script>
        $(function () {
            $("#tabs").tabs();
        });
    </script>
    <script type="text/javascript">
    
    $("#htgt").ready(function(){
    	$.ajax({
    		type:"GET",
    		url:"${pageContext.request.contextPath}/studt/getHGTHome",
    		data: {
    			subcategoryId:"${subCatId}",
    		},
    		success: function(data){
    			console.log(data);
    			if(Object.keys(data.response.hgtHome).length === 0){
    				$('#howTOGetThere').html('<div  class="mtdata">No Data!</div>');
    			}
    			$('#howTOGetThere').append(data.response.hgtHome);
    		},
    		error: function(){
    			console.log("error occured");
    		}
    	});
    });
    
    //for Courses
    function getCoursesData(url, professionId, displayId, appendId){
    	$.ajax({
    		type:"GET",
    		url:"${pageContext.request.contextPath}/studt/"+url,
    		data: {
    			subcategoryId:"${subCatId}",
    			professionId: professionId
    		},
    		success: function(data){
    			console.log(data);
    			if(data.response.courses){
    				var courses= new Array();
        			courses=data.response.courses.split("@@@");
        			$('#'+appendId).empty();
    				$.each(courses, function(index, course){
        				var ellei=$('<li class="pt-3"/>');
        				$('#'+appendId).append(ellei.append(course));
    				});
    			}
    			else{
    				$('#'+displayId).hide();
    			}
    		},
    		error: function(){
    			console.log("error occured");
    		}
    	});
	}
    $("#pills-profile-tab").on('click', function(){
    	//for basic eligibility
    	getCoursesData("getHGTCourses", 1, "basicEligibilities", "basicEligibility" );
    	
    	//for UG Courses
    	getCoursesData("getHGTCourses", 2, "ugCourses", "ugCourse" );
    	
    	//for pg Courses
    	getCoursesData("getHGTCourses", 3, "pgCourses", "pgCourse" );
    	
    	//for doctoralCourses
    	getCoursesData("getHGTCourses", 4, "doctoralCourses", "doctoralCourse" );
    	
    	//for diplomaCourses
    	getCoursesData("getHGTCourses", 5, "diplomaCourses", "diplomaCourse" );
    	
    	//for certificationCourses
    	getCoursesData("getHGTCourses", 6, "certificationCourses", "certificationCourse" );
    });
	//for EntranceExams
	$("#pills-entrance-tab").on('click', function(){
		$.ajax({
    		type:"GET",
    		url:"${pageContext.request.contextPath}/studt/getHGTExams",
    		data: {
    			subcategoryId:"${subCatId}"
    		},
    		success: function(data){
    			console.log(data);
    			if(data.response.exam1==null && data.response.exam2==null && data.response.exam3==null && data.response.exam4==null){
    				$("#pills-entrance").html('<div  class="mtdata">No Data!</div>');
    			}
    			var eExams1= new Array();
    			var eExams2= new Array();
    			var eExams3= new Array();
    			var eExams4= new Array();
    			$('#graduationLevel').empty();
    			$('#postGraduationLevel').empty();
    			$('#diplomaLevel').empty();
    			$('#doctoralLevel').empty();
    			if(data.response.exam1){
    				eExams1=data.response.exam1.split("@@@");    	
    				$.each(eExams1, function(index, exams1){
           				var ellei=$('<li class="pt-3"/>');
           				$('#graduationLevel').append(ellei.append(exams1));
       				});
    			}
    			else{
    				$('#graduationLevelExams').css("display", "none");
    			}
    			if(data.response.exam2){
    				eExams2=data.response.exam2.split("@@@");   
    				$.each(eExams2, function(index, exams2){
           				var ellei=$('<li class="pt-3"/>');
           				$('#postGraduationLevel').append(ellei.append(exams2));
       				});
    			}
    			else{
    				$('#postGraduationLevelExams').css("display", "none");
    			}
    			if(data.response.exam3){
    				eExams3=data.response.exam3.split("@@@");  	
    				$.each(eExams3, function(index, exams3){
           				var ellei=$('<li class="pt-3"/>');
           				$('#diplomaLevel').append(ellei.append(exams3));
       				});
    			}
    			else{
    				$('#diplomaLevelExams').css("display", "none");
    			}
    			if(data.response.exam4){
    				eExams4=data.response.exam4.split("@@@"); 	
    				$.each(eExams4, function(index, exams4){
           				var ellei=$('<li class="pt-3"/>');
           				$('#doctoralLevel').append(ellei.append(exams4));
       				});
    			}
    			else{
    				$('#doctoralLevelExams').css("display", "none");
    			}
    			
    		},
    		error: function(){
    			console.log("error occured");
    		}
    	});
	});
	//for Scholorships
    function getScholorshipsData(countryId, displayId, appendId){
    	$.ajax({
    		type:"GET",
    		url:"${pageContext.request.contextPath}/studt/getHGTScholorships",
    		data: {
    			subcategoryId:"${subCatId}",
    			country: countryId
    		},
    		success: function(data){
    			console.log(data);
    			if(Object.keys(data.response).length === 0){
    				$('#'+appendId).html('<div  class="mtdata">No Data!</div>');
    			}
    			else{
        			$('#'+appendId).empty();
    				$.each(data.response, function(index, scholorships){
        				var ellei=$('<li class="pt-3"/>');
        				$('#'+appendId).append(ellei.append(scholorships.name));
    				});
    			}
    		},
    		error: function(){
    			console.log("error occured");
    		}
    	});
	}
    $("#pills-dropdown3-tab").on('click', function(){
    	///for Indian Scholorships
    	getScholorshipsData("India", "indianScholorShips", "indianScholorShip" );
    });
    
    $("#pills-dropdown4-tab").on('click', function(){
    	///for abroad Scholorships
    	getScholorshipsData("Abroad", "abroadScholorShips", "abroadScholorShip" );
    });
    
  	//for SuccessfulPersonalities
    $("#pills-person-tab").on('click', function(){
    	$.ajax({
    		type:"GET",
    		url:"${pageContext.request.contextPath}/studt/getHGTSuccessPersons",
    		data: {
    			subcategoryId:"${subCatId}",
    		},
    		success: function(data){
    			console.log(data);
    			if(Object.keys(data.response).length === 0){
    				$('#successfulPersonalities').html('<div  class="mtdata">No Data!</div>');
    			}
    			else{
        			$('#successfulPersonalities').empty();
    				$.each(data.response, function(index, person){
    					var row=$('<div class="row">');
        				var column=$('<div class="col-md-12">');
        				var right_blocks=$('<div class="right-blogs pl-4"/>');
        				var pea= $('<p/>');
        				var bea= $('<b/>');
        				var descPea= $('<p/>');
        				var heshaar=$('<hr/>');
        				$('#successfulPersonalities').append(row.append(column.append(right_blocks.append(pea.append(bea.append(person.name))).append(descPea.append(person.description).append(heshaar)))));
    				});
    			}
    		},
    		error: function(){
    			console.log("error occured");
    		}
    	});
    });
  	
	//for institutions
	function getInstitutionsAndCourses(country, appendId, accordion ){
		$.ajax({
    		type:"GET",
    		url:"${pageContext.request.contextPath}/studt/getHGTInstitutes",
    		data: {
    			subcategoryId: "${subCatId}",
    			country: country
    		},
    		success: function(data){
    			console.log(data);
    			if(Object.keys(data.response).length === 0){
    				$('#'+appendId).html('<div  class="mtdata">No Data!</div>');
    			}
    			else{
        			$('#'+appendId).empty();
    				$.each(data.response, function(index, institutes){
    					var heshref="#"+institutes.id;
    					var collapseId= institutes.id;
    					var accordionId= "#"+accordion;
    					var card_header=$('<div class="card-header collapsed" data-toggle="collapse" />').attr("href", heshref);
        				var anchor=$('<a class="card-title"/>').append(institutes.name);
						var collapse= $('<div class="card-body collapse india-sec"/>').attr("id", collapseId).attr("data-parent", accordionId);
        				$('#'+appendId).append(card_header.append(anchor)).append(collapse);
    					$.each(institutes.result, function(cindex, course){
    						var formAction= course.courseUrl;
    						var form= $('<form method="get" target="_blank" />').attr("action", formAction);
    						var button= $('<button type="submit" class="card-header"/>').append(course.courseName);
    						$(collapse).append(form.append(button));
        				});
    				});
    			}
    		},
    		error: function(){
    			console.log("error occured");
    		}
    	});
	}
	$("#pills-dropdown1-tab").on('click', function(){
		getInstitutionsAndCourses("India", "cardInstitutionsIndia", "accordionIndia");
	});
	
	$("#pills-dropdown2-tab").on('click', function(){
		getInstitutionsAndCourses("Abroad", "cardInstitutionsAbroad", "accordionAbrod");
	});
	
        $('.dropdown-toggle').click(function () {
            $(this).next('.dropdownprofile').toggle(400);
        });

        $(document).click(function (e) {
            var target = e.target;
            if (!$(target).is('.dropdown-toggle') && !$(target).parents().is('.dropdown-toggle')) {
                $('.dropdownprofile').hide();
            }
        });
        document.getElementById('back').innerHTML='<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
        ' <input type="hidden" value="${subCatId}" name="subCatId" id="subCatId"/>'+
        '  <button class="btn btn-info2">'+
         '<div class="round">'+
          '   <i class="fa fa-arrow-left" aria-hidden="true"></i>'+
         '</div>'+
    ' </button></form>';

    </script>