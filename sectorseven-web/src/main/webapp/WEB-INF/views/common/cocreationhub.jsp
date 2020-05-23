<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	    <section class="co-creation">
        <div class="container-fluid">
        <div class="row">
				<div class="col-sm-12 col-lg-12">
					<div class="activity-heading mt-2 mb-4">
						<h2>E-nnovation Hub</h2>
					</div>
				</div>
			</div>
              <div id="hubsdiv"></div>
        </div>
    </section>
    <script>
    $(document).ready(function () {
        getAllHubs();
    });
    function getAllHubs(){
        $.ajax({
            type : "GET",
            url : "${pageContext.request.contextPath}/studt/getAllHubs",
            data:{
            	userId:"${userId}",
            	userType:"${userType}"
            },
            async : false,
            success : function(data) {
               var htmlContent='<div class="row">';
             	  $.each(data.response, function(i, hub) {
             	  htmlContent+=' <div class="col-lg-3 col-md-3 col-sm-12"><form action="${pageContext.request.contextPath}/common/gethubMap" method="POST"><button>'+
                     '<div class="bgsuccess" id="bgsuccess">'+
                    '<input type="hidden" value="'+hub.id+'" name="hubId" id="hubId"/>'+
                    ' <h4>'+hub.name+'</h4>'+
                    '<p>'+hub.hubZone+'</p>'+
                  ' </div>'+
              '</button></form> </div>';
             	  });
             	  htmlContent+='</div>';
                  $("#hubsdiv").append(htmlContent);

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