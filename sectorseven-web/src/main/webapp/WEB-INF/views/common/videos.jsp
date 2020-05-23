<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<div class="col-md-12">
				<div class="heading">
					<h3>Video</h3>
				</div>
				</div>
			</div>
			<div class="video-gallery">
			<div class="row" id="recdiv">
			</div>
			</div>
			<div class="row" id="divMore"> 
			
			</div>
		</div>
	</div>
	<script type="text/javascript">
      var limit = 12;
        var offset = 0;
        $(document).ready(function() {
        	getVideos();
        });
        function loadMore(){
        	  limit=limit;
      		  offset=offset+12;
      		   getVideos();
        }
  		function getVideos(){
      	    var userId ;
            var userType ;
            var screenType = "${screenType}";
            var scrType = "${scrType}";
            var subCatId = "${subCatId}";
            var type='video/mp4';
            if("${subScrType}" == 'inner'){
            	 userId = "${stId}";
            	 userType= 'ROLE_STUDENT';
            }else{
             userId = "${userId}";
           	 userType="${userType}";
            }
            $.ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/studt/mediaDetails?screenType="+screenType+"&limit="+limit+"&offset="+offset+"&userId="+userId+"&userType="+userType+"&type="+type+"&mediaId="+subCatId,
                async : false,
                success : function(data) {
                	if(data.response.length == 0){
                		document.getElementById('recdiv').innerHTML='No Data!!';
                	}
                	else{
                		 var table = $('#recdiv');
                     	  $.each(data.response, function(i, rec) {
                                var tab= $('<div class="gallery-item col-sm-6 col-lg-3"/>').appendTo(table);
                                if(rec.videoType=='Private'){
                               	  tab.append($('<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">'+
                               			 ' <input type="hidden" value="'+rec.mediaId+'" name="mediaId" id="mediaId"/>'+
                                           ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                           ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                           ' <input type="hidden" value="Video" name="action" id="action"/>'+
                                           ' <input type="hidden" value="'+rec.videoType+'" name="videoType" id="videoType"/>'+
                                           ' <input type="hidden" value="'+rec.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                                           ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                                           ' <input type="hidden" value="'+rec.subCatId+'" name="subCatId" id="subCatId"/>'+
                                  '<button class=""><img src="../resources/images/bg.jpg" alt="North Cascades National Park"></img></button>'+
                                  '<div class="gallery-item-caption">'+
                                      '</div>'+
                                 ' </form>')); 
                               	   }
                            	   if(rec.videoType=='Youtube'){
                            	  tab.append($('<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">'+
                            			 ' <input type="hidden" value="'+rec.mediaId+'" name="mediaId" id="mediaId"/>'+
                                        ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                        ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                        ' <input type="hidden" value="Video" name="action" id="action"/>'+
                                        ' <input type="hidden" value="'+rec.videoType+'" name="videoType" id="videoType"/>'+
                                        ' <input type="hidden" value="'+rec.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                                        ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                                        ' <input type="hidden" value="'+rec.subCatId+'" name="subCatId" id="subCatId"/>'+
                               '<button class=""><img src="../resources/images/bg.jpg" alt="North Cascades National Park"></img>'+
                               '<div class="gallery-item-caption">'+
                               '<h2>'+rec.title+'....</h2>')); 
                               if(rec.description != null || rec.description != ""){
                               	tab.append($('<p>'+rec.description+'</p>'));
                               }
                               tab.append($('</div></button>'+
                              ' </form>')); 
                            	   }
                             

                     	  });
                    	  if(data.response.length==12){
                     	 var htmlCo='<div class="col-md-12">'+
         				'<div  id="loadMore"class="loadmoreBtn">'+
         					'<button onclick="loadMore()" class="btn">Load More</button>'+
         				'</div>'+
         				'</div>';
                    	  }
         				document.getElementById('divMore').innerHTML=htmlCo;

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
