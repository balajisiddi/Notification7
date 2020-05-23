<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	    <section class="library-main Skills" id="skillsAndRespons">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12 col-lg-12">
                   <div id="back"></div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row mt-3">
                <div class="col-sm-12 col-lg-12">
                    <div class="activity-heading">
                        <h2>Skills & Responsibilities</h2>
                    </div>
                </div>
            </div>
            <div class="row vertical-tabs" id="tabs">
                <div class="col-sm-8 col-lg-9">
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1" role="tabpanel">
                            <div class="sv-tab-panel" id="skills">
                            <c:forEach var="skill" items="${skills}">
										<p><i class="fa fa-circle" aria-hidden="true"> <!--  -->
										</i> ${skill.skill}</p>
									</c:forEach>
                             	
                            </div>
                        </div>
                        <div class="tab-pane" id="tab2" role="tabpanel">
                            <div class="sv-tab-panel" id="responsibility">
                             <c:forEach var="responsibility" items="${responsibilities}">
										<p><i class="fa fa-circle" aria-hidden="true"> <!--  -->
										</i> ${responsibility.roles}</p>
									</c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4 col-lg-3">
                    <div class="stickyNav__frame">
                        <div class="is-fixed">
                            <div class="stickyNav__inner">
                                <ul class="stickyNav__links" role="tablist">
                                    <li class="stickyNavskill stickyNav__link">
                                        <a class="moss active" href="#tab1" data-toggle="tab" role="tab"
                                            aria-controls="home">Skills</a>
                                    </li>
                                    <li class="stickyNavre stickyNav__link">
                                        <a class="moss" id="responsibilities" href="#tab2" data-toggle="tab" role="tab"
                                            aria-controls="courses">Responsibilities</a>
                                    </li>

                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>
</div>
<script type="text/javascript">
$("#skillsAndRespons").ready(function(){
	$.ajax({
		type:"GET",
		url:"${pageContext.request.contextPath}/studt/getSRASkills",
		data: {
			subcategoryId:"${subCatId}",
		},
		success: function(data){
			console.log(data);
			if(Object.keys(data.response).length === 0){
				$('#skills').text("No Data!!");
			}
			 $.each(data.response, function(index, skill) {
				 var pea= $('<p/>');
				 var ei= $('<i class="fa fa-circle" aria-hidden="true"/>');
				 $('#skills').append(pea.append(ei).append(skill.skill));
             });
		},
		error: function(){
			console.log("error occured");
		}
	});
});

$("#responsibilities").on('click', function(){
	$.ajax({
		type:"GET",
		url:"${pageContext.request.contextPath}/studt/getSRAResponsibilities",
		data: {
			subcategoryId:"${subCatId}",
		},
		success: function(data){
			console.log(data);
			if(Object.keys(data.response).length === 0){
				$('#responsibility').text("No Data!!");
			}
			 $('#responsibility').empty();
			 $.each(data.response, function(index, role) {
				 var pea= $('<p/>');
				 var ellei= $('<i class="fa fa-circle" aria-hidden="true"/>');
				 $('#responsibility').append(pea.append(ellei).append(role.role));
             });
		},
		error: function(){
			console.log("error occured");
		}
	});
});


	$(function() {
		$("#tabs").tabs({
			selected : 1
		});
	});
	function displayList() {
		$.fn.reverse = [].reverse;
		var trigger = $("#trigger"), mainTarget = $(".my-nav"), targetItem = $('.my-nav__item'), html = $("html");
		trigger.on("click", function(event) {
			mainTarget.toggleClass("reveal unreveal");
			targetItem.reverse().each(function(i, el) {
				setTimeout(function() {
					$(el).toggleClass("visible");
				}, i * 68);
			})
			html.on("click", function() {
				targetItem.removeClass("visible");
				mainTarget.removeClass("reveal");
			});
			event.preventDefault();
			event.stopPropagation();
		});
	}
	// If you are using Turbolinks use only
	//$(document).on('turbolinks:load', displayList);
	$(document).on('page:load', displayList);
	$(document).ready(displayList);
	 document.getElementById('back').innerHTML='<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
     ' <input type="hidden" value="${subCatId}" name="subCatId" id="subCatId"/>'+
     '  <button class="btn btn-info2">'+
      '<div class="round">'+
       '   <i class="fa fa-arrow-left" aria-hidden="true"></i>'+
      '</div>'+
 ' </button></form>';
</script>