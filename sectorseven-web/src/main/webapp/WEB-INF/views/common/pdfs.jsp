<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<div id="video-tabs-sec">
      <div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="heading">
						<h3>PDFS</h3>
					</div>
				</div>
			</div>

			<div class="video-gallery">
				<div class="row" id="recdiv"></div>
			</div>
		</div>
    </div>
    <script type="text/javascript">
      var limit = 8;
        var offset = 0;
        $(document).ready(function() {
        	getSeenPdfs();
        });
        $("#loadMore").click(function() {
  		  limit=limit;
  		  offset=offset+8;
  		getSeenPdfs();
         });
        function getSeenPdfs(){
      	  var userId ;
            var userType ;
            var screenType = "${screenType}";
            var subCatId = "${subCatId}";
            if("${subScrType}" == 'inner'){
           	 userId ="${stId}";
           	 userType= 'ROLE_STUDENT';
           }else{
            userId = "${userId}";
          	 userType="${userType}";
           }
            var type='application/pdf';
            $.ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/studt/mediaDetails?screenType="+screenType+"&limit="+limit+"&offset="+offset+"&userId="+userId+"&userType="+userType+"&type="+type+"&mediaId="+subCatId,
                async : false,
                success : function(data) {
                    var table = $('<div class="col-md-12"/>').appendTo($('#recdiv'));
              	  $.each(data.response, function(i, seenPdf) {
                        var tab= $('<div class="gallery-item col-sm-6 col-lg-3"/>').appendTo(table);
                        tab.append($('<a class="vimeo-popup" target="_blank" href=${pageContext.request.contextPath}/studt/mediaType?screenType='+screenType+'&mediaId='+seenPdf.mediaId+'&userId='+userId+'&userType='+userType+'"><img src="../resources/images/pdf.png" alt="North Cascades National Park" ></img><div class="gallery-item-caption"><div><h2>'+seenPdf.title+'</h2></div></div></a>'));
              	  });
              	if(data.response.length>8){
                	htmlCont+='<div class="loadmore-btn"><button id="loadMore" class="btn con-subbtn">LoadMore</button></div>';
              	 }
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
    