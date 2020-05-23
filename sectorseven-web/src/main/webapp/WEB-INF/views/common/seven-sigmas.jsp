<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="sevensigmas">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<div class="activity-heading mt-2 mb-4 pl-3">
						<h2>Seven Sigmas</h2>
					</div>
				</div>
			</div>
			
			<div id="sigmadiv"></div>
			
		</div>
	</section>
	<script>
$(document).ready(function(){
 /*    $(".cbeimg1").hide();
    $(".ppimg1").hide();
    $(".ccimg1").hide();
    $(".inoimg1").hide();
    $(".dcimg1").hide();
    $(".peimg1").hide();
    $(".tlimg1").hide();
    $(".cbe").mouseover(function(){
      $(".cbeimg").toggle();
      $(".cbeimg1").toggle();
    });
    $(".cbe").mouseout(function(){
      $(".cbeimg").toggle();
      $(".cbeimg1").toggle();
    });
    $(".pp").mouseover(function(){
      $(".ppimg").toggle();
      $(".ppimg1").toggle();
    });
    $(".pp").mouseout(function(){
      $(".ppimg").toggle();
      $(".ppimg1").toggle();
    });
    $(".cc").mouseover(function(){
      $(".ccimg").toggle();
      $(".ccimg1").toggle();
    });
    $(".cc").mouseout(function(){
      $(".ccimg").toggle();
      $(".ccimg1").toggle();
    });
    $(".tl").mouseover(function(){
      $(".tlimg").toggle();
      $(".tlimg1").toggle();
    });
    $(".tl").mouseout(function(){
      $(".tlimg").toggle();
      $(".tlimg1").toggle();
    });
    $(".dc").mouseover(function(){
      $(".dcimg").toggle();
      $(".dcimg1").toggle();
    });
    $(".dc").mouseout(function(){
      $(".dcimg").toggle();
      $(".dcimg1").toggle();
    });
    $(".pe").mouseover(function(){
      $(".peimg").toggle();
      $(".peimg1").toggle();
    });
    $(".pe").mouseout(function(){
      $(".peimg").toggle();
      $(".peimg1").toggle();
    });
    $(".ino").mouseover(function(){
      $(".inoimg").toggle();
      $(".inoimg1").toggle();
    });
    $(".ino").mouseout(function(){
      $(".inoimg").toggle();
      $(".inoimg1").toggle();
    }); */
    getAllSigmas();
  });
function getAllSigmas(){
    $.ajax({
        type : "GET",
        url : "${pageContext.request.contextPath}/studt/allSigmas",
        async : false,
        success : function(data) {
      	  var table = $('<div class="row">').appendTo($('#sigmadiv'));
      	  $.each(data.response, function(i, sigma) {
                var tab= $('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3">').appendTo(table);
                tab.append($('<div class="bgsuccess cbe"><div class="rounded-circle icope"><img src="${pageContext.request.contextPath}/studt/iconImage?iconId='+sigma.id+'&iconScreen=sigma&imgPath='+sigma.sigmaIconPath+'&imgName='+sigma.sigmaIconName+'"alt="" class="cbeimg"></div><h4>'+sigma.sigmaName+'</h4><p>'+sigma.sigmaDesc+'</p><div class="mentor-viewmore-btn btn-view">'+
                '<form action="${pageContext.request.contextPath}/common/sigmasDetails" method="POST">'+
                ' <input type="hidden" value="'+sigma.id+'" name="sigmaId" id="sigmaId"/>'+
                 '<button class="btn big-btn">View More</button>'+
                 '</form>'+
                '</div></div></div>'));
      		  });
           // alert('Success: ' + data);
        },
        error : function(e) {
            alert('Error: ' + e);
        }
    }).done(function(data) {
        console.log(data);
    });
}
</script>
</div>
