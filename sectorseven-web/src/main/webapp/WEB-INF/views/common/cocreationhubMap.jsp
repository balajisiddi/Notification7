<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">

    <section class="up-comingeve">
        <div class="container-fluid ">
                                                  <div id="hubMapdiv"></div>
        </div>
    </section>
        <script>
    $(document).ready(function () {
    	getAllHubDetails();
    });
    function getAllHubDetails(){
  /*   	var userId = document.getElementById('userId').value;
        var userType = document.getElementById('userType').value;
        var hubId = document.getElementById('hubId').value;
   */  
   $.ajax({
            type : "GET",
            url : "${pageContext.request.contextPath}/studt/gethubDetails",
            async : false,
            data:{
            	hubId:"${hubId}"
            },
            success : function(data) {
             	 var htmlContent='<div class="row">';
              	  htmlContent+='<div class="col-sm-12 col-md-12 col-lg-12">'+
                 '<div class="map">'+
                 '<iframe  src="https://maps.google.com/maps?q='+data.response.latitude+','+data.response.longitude+'+&hl=es;z=14&amp;output=embed"width="100%" height="750" style="border:0;" allowfullscreen=""></iframe>'+
                 ' </div>'+
                 ' </div>';
             	  htmlContent+='</div>';
                  $("#hubMapdiv").append(htmlContent); 
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
	
