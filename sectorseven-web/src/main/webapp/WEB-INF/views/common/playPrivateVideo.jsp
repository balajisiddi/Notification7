<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<div class="play-video">
		<div class="container-fluid">
			<div class="row">
             <div class="col-sm-12 col-lg-12">
                    <div class="gallery-item " id="goBack">
                       <input type="hidden" value="${screenType}" name="screenType" id="screenType"/>
                    	<iframe src="${pageContext.request.contextPath}/studt/mediaType?mediaId=${mediaId}&amp;screenType=${screenType}" id="pdfViews" ></iframe>
                      <div id="player"></div>
                    </div>
                </div>
            </div>
		</div>
	</div>
	<script type="text/javascript">
    $('#pdfViews').on('load', function(){
    	checkPdf();
  	  if("${scrType}"=='recomm' || "${scrType}"=='subCategory' || "${scrType}"=='sigma' ||  "${scrType}"=='contribution'){
//       	setTimeout(saveActivity, 5000);
  	  }
    
    });
      function saveActivity() {
    	  var userId= "${userId}";
    	  var userType= "${userType}";
    	  var activity = {
    	  screen: "${screenType}",
          action: "${action}",
          mediaId: "${mediaId}",
          videoType: "${videoType}",
          youtubeUrl: "${youtubeUrl}",
          mediaType: "${mediaType}",
          subcategory: {
        	  id:"${subCatId}"
          }
    	  }
    	  $.ajax({
    			type:"POST",
    			url:"${pageContext.request.contextPath}/studt/saveActivity?userId="+userId+"&userType="+userType,
    			data: JSON.stringify(activity),
    			contentType: "application/json",
    			success: function(data){
    				console.log(data);
    			},
    			error: function(){
    				console.log("error occured");
    			}
    		});
     }
      function checkPdf() {
    	  $.ajax({
    			type:"GET",
    			url:"${pageContext.request.contextPath}/studt/mediaType",
    			data: {
    				  screenType: "${screenType}",
    		          mediaId: "${mediaId}",
    			},
    			success: function(data){
    				if(data.statusCode==202){
    					$("#goBack").html("goback");
    					openPopup("Problem opening file!!");
    				}
    				else{
    					setTimeout(saveActivity, 5000);
    				}
    			},
    			error: function(){
    				console.log("error occured");
    			}
    		});
     }
      function openPopup(val){
	       const swalWithBootstrapButtons = Swal.mixin({
	       	 customClass: {
	       		    confirmButton: 'btn btn-primary con-subbtn'
	       		  },
	       	buttonsStyling: false,
	       	allowOutsideClick: false
	       	})

	       	swalWithBootstrapButtons.fire({
	       	  title:"Status",	
		      html:"<div>"+val+"</div>",
	       	  confirmButtonText: 'OK',
	       	  reverseButtons: true
	       	}).then(function(result) {
	       		if (result.value) {
		       			location.href = "${pageContext.request.contextPath}/common/sigmas";
	                }
	       	})
	   } 
    </script>
</div>
