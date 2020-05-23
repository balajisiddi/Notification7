<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
     <section class="student-talks">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-4 col-lg-3 col-xl-3">
          <div class="bgsuccess">
            <div class="text-center mb-3">
              <img src="../img/parakala.jpg" alt="">
            </div>
            <h4>${student.firstName} ${student.lastName}</h4>
            <p>${student.studentClass}</p>
            <h4 class="text-left">About</h4>
            <p class="text-justify">${student.description}</p>
          </div>
        </div>
        <div class="col-sm-8 col-lg-9 col-xl-9">
          <div class="row" id="tabs">
            <div class="col-sm-12 col-lg-12 col-xl-12">
              <div class="row tab-content seven-sigma student-media">
                <div class="seven-sigma-option-bg student-talk-option">
                  <div class="container">
                    <ul class="row" role="tablist">
                      <li class="col-sm-6 col-lg-6 col-xl-6"><a href="#tabs-1" class="active" data-toggle="tab"
                          role="tab" aria-controls="audio">Recent Talks</a></li>
                      <li class="col-sm-6 col-lg-6 col-xl-6"><a href="#tabs-2" data-toggle="tab" role="tab"
                          aria-controls="video">All Talks</a>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="video-gallery tab-pane active" id="tabs-1" role="tabpanel">
                  <div id="tabsone">
                    <div class="tab-content">
                      <div class="seven-sigma-option-bg videos">
                        <div class="container">
                          <ul class="row" role="tablist">
						<button onclick="initialTalkDetails1('video/mp4')">VIDEO</button>
						<button onclick="initialTalkDetails1('application/pdf')">PDF</button>
                          </ul>
                        </div>
                      </div>
                      <div class="video-gallery active" id="video">
                        <div class="container">
                          <div id="talkVideoDiv"></div>
                          <input type="hidden" value="${userId}" name="userId" id="userId"/>
                           <input type="hidden" value="${userType}" name="userType" id="userType"/>
                           <span id="loadMore">LoadMore</span>
                           
                        </div>
                      </div>
                      <div class="video-gallery " id="pdf">
                        <div class="container">
                          <div class="row">
                            <div id="talkPdfDiv"></div>
                          <input type="hidden" value="${userId}" name="userId" id="userId"/>
                           <input type="hidden" value="${userType}" name="userType" id="userType"/>
                           <span id="loadMore">LoadMore</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="video-gallery tab-pane" id="tabs-2">
                  <div id="tabstwo">
                    <div class="tab-content">
                      <div class="seven-sigma-option-bg videos">
                        <div class="container">
                          <ul class="row" role="tablist">
                             <form class="form-horizontal needs-validation" method="POST" novalidate="true" role="form" 
						action="${pageContext.request.contextPath}/common/getTalksDetails">
						<input type="hidden" name="type" value="video/mp4"/>
						<input type="hidden" name="latest" value="1"/>
						<input type="hidden" name="limit" value="10"/>
						<input type="hidden" name="offset" value="0"/>
						<button type="submit" class="btn-sm animated-button">VIDEO</button>
						</form>
						 <form class="form-horizontal needs-validation" method="POST" novalidate="true" role="form" 
						action="${pageContext.request.contextPath}/common/getTalksDetails">
						<input type="hidden" name="type" value="application/pdf"/>
				        <input type="hidden" name="latest" value="1"/>
						<input type="hidden" name="limit" value="10"/>
						<input type="hidden" name="offset" value="0"/>
						<button type="submit" class="btn-sm animated-button">Pdf</button>
						</form>
                          </ul>
                        </div>
                      </div>
                      <div class="video-gallery active" id="video">
                        <div class="container">
                          <div class="row">
                           <c:forEach var="contributionDoc" items="${contributionDocs}">
                             <c:if test="${contributionDoc.type=='video/mp4'}">
                            <div class="col-sm-6 col-md-6 col-lg-3 col-xl-3">
                              <div class="gallery-item">
                                <img src="../resources/images/bg.jpg" alt="North Cascades National Park" />
                                <div class="gallery-item-caption">
                                  <!-- <div>
                                  <h2>North Cascades</h2>
                                  <p>The mountains are calling</p>
                                </div> -->
                                  <a class="vimeo-popup" href="${pageContext.request.contextPath}/studt/mediaType?mediaId=${contributionDoc.id}&screenType=contribution"></a>
                                </div>
                              </div>
                            </div>
                              </c:if>
                            </c:forEach>
                          </div>
                        </div>
                      </div>
                                          <div class="video-gallery " id="pdf">
                        <div class="container">
                          <div class="row">
                           <c:forEach var="contributionDoc" items="${contributionDocs}">
                             <c:if test="${contributionDoc.type=='application/pdf'}">
                            <div class="col-sm-6 col-md-6 col-lg-3 col-xl-3">
                              <div class="gallery-item">
                                <img src="../resources/images/pdf.png" alt="North Cascades National Park" />
                                <div class="gallery-item-caption">
                                  <!-- <div>
                                  <h2>North Cascades</h2>
                                  <p>The mountains are calling</p>
                                </div> -->
                                  <a class="vimeo-popup" href="${pageContext.request.contextPath}/studt/mediaType?mediaId=${contributionDoc.id}&screenType=contribution"></a>
                                </div>
                              </div>
                            </div>
                            </c:if>
                            </c:forEach>
                        </div>
                      </div>
                    </div>
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
	<script type="text/javascript">
  var limit = 10;
  var offset = 0;
  $(document).ready(function() {
	  initialTalkDetails('video/mp4');
	  $("#loadMore").click(function() {
		  limit=limit+10;
		  offset=offset+1;
		  initialTalkDetails();
       });
  });
  function initialTalkDetails1(type){
	  if(type=='video/mp4'){
		  $('#talkPdfDiv').load('');
	  }
	  if(type=='application/pdf'){
		  $('#talkVideoDiv').load('');
	  }
	  initialTalkDetails(type);
  }
   function initialTalkDetails(type){
	  var userId = document.getElementById('userId').value;
      var userType = document.getElementById('userType').value;
      var latest=0;
      var mediaType=type;
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/gets7TalksDetails?limit="+limit+"&offset="+offset+"&s7TalksUserId="+userId+"&s7TalksUserType="+userType+"&mediaType="+mediaType+"&latest="+latest,
          async : false,
          success : function(data) {
              var table = $('<div class="row"/>').appendTo($('#talkVideoDiv'));
              var table1 = $('<div class="row"/>').appendTo($('#talkPdfDiv'));

        	  $.each(data.response, function(i, talk) {
        		  if(type=='video/mp4'){
        			  $('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>').appendTo(table)
                      .append($('<div class="gallery-item"><img src="../resources/images/bg.jpg" alt="North Cascades National Park" /><div class="gallery-item-caption"><a class="vimeo-popup" target=”_blank” href="${pageContext.request.contextPath}/studt/mediaType?screenType=contribution&mediaId='+talk.mediaId+'"</div></div>'));
        		  }
        		  if(type=='application/pdf'){
        			  $('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>').appendTo(table1)
                      .append($('<div class="gallery-item"><img src="../resources/images/pdf.png" alt="North Cascades National Park" /><div class="gallery-item-caption"><a class="vimeo-popup" target=”_blank” href="${pageContext.request.contextPath}/studt/mediaType?screenType=contribution&mediaId='+talk.mediaId+'"</div></div>'));
        		  }
                                });
        	  
            //  alert('Success: ' + data);
          },
          error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
  }
</script>
	<script type="text/javascript">
    $(document).ready(function () {
      $("#tabs").tabs({ selected: 1 });
      $("#tabsone").tabs({ selected: 1 });
      $("#tabstwo").tabs({ selected: 1 });
    });

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
    // If you are using Turbolinks use only
    //$(document).on('turbolinks:load', displayList);

    $(document).on('page:load', displayList);
    $(document).ready(displayList);

    $('.dropdown-toggle').click(function () {
      $(this).next('.dropdownprofile').toggle(400);
    });

    $(document).click(function (e) {
      var target = e.target;
      if (!$(target).is('.dropdown-toggle') && !$(target).parents().is('.dropdown-toggle')) {
        $('.dropdownprofile').hide();
      }
    });

    $(window).scroll(function (event) {
      function footer() {
        var scroll = $(window).scrollTop();
        if (scroll > 50) {
          $(".footer-nav").fadeIn("slow").addClass("show");
        }
        else {
          $(".footer-nav").fadeOut("slow").removeClass("show");
        }
      }
      footer();
    });
  </script>
  
  