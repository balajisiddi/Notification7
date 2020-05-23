<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="student-talks seven-sigmas-detail">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12 col-lg-12 ">
					<div class="heading">
						<h3>Media</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<div class="tab">
						<div class="tab-content seven-sigma">
							<div class="row">
								<div class="seven-sigma-option-bg">
									<div class="col-sm-6 col-lg-6">
										<a href="#" id="videos"
											class="btn-sm animated-button one active">Video</a>
									</div>
									<div class="col-sm-6 col-lg-6">
										<a href="#" id="pdfs" class="btn-sm animated-button three">Pdf</a>
									</div>
								</div>
								<%-- <input type="hidden" value="${userId}" name="userId" id="userId" />
								<input type="hidden" value="${userType}" name="userType"
									id="userType" /> <input type="hidden" value="${screenType}"
									name="screenType" id="screenType" /> <input type="hidden"
									value="${sigmaId}" name="sigmaId" id="sigmaId" /> --%>

							</div>
							
								<div id="sigmaDetaildiv" class="row">
								</div>
							
							<div class="row mt-3">
								<div class="loadmoreBtn">
									<div id="loadMore"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
</section>
<script type="text/javascript">
        var limit = 12;
        var offset = 0;
        $(document).ready(function() {
            var type='video/mp4';
        	getSigmaDetails(type);
        });
        $("#pdfs").click(function() {
        	console.log("pdfs clicked success");
        	console.log("${screenType}");
        	console.log("${userId}");
        	console.log("${userType}");
        	console.log("${sigmaId}");
        	  limit = 12;
             offset = 0;
          type='application/pdf';
  		  getSigmaDetails(type);
         });
        $("#videos").click(function() {
        	console.log("videos clicked success");
        	console.log("${screenType}");
        	console.log("${userId}");
        	console.log("${userType}");
        	console.log("${sigmaId}");
        	  limit = 12;
              offset = 0;
              type='video/mp4';
    		  getSigmaDetails(type);
           });
        function callLoadMoreVideos(){
        	  limit=limit;
     		  offset=offset+12;
     		  type='video/mp4';
     		  getSigmaDetails(type);
        } 
        function callLoadMorePdfs(){
       	     limit=limit;
    		  offset=offset+12;
    		  type='application/pdf';
    		  getSigmaDetails(type);
       } 
      
        function getSigmaDetails(type){
          var scrType = 'sigma';
            $.ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/studt/mediaDetails",
               data:{
            	   screenType:"${screenType}",
            	   limit:limit,
            	   offset:offset,
            	   userId:"${userId}",
            	   userType:"${userType}",
            	   type:type,
            	   mediaId:"${sigmaId}"
               },
                async : false,
                success : function(data) {
                	console.log(data);
                	if(data.response.length==0){
                  	  document.getElementById("sigmaDetaildiv").innerHTML = '<div class="mtdata">No Data!!</div>';
                	}
                	else if(data.response.length>0){
              	var  htmlContent='<div class="video-gallery videos">';
            	  $.each(data.response, function(i, sigma) {
                      if(type=='video/mp4'){
                       	   htmlContent+='<div class="gallery-item col-sm-6 col-lg-3">';
                       	 if(sigma.videoType=='Private')
                    	   {
                     		htmlContent+='<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">';                                 
                    	   }
                       	if(sigma.videoType=='Youtube')
                 	   {
                  		htmlContent+='<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">';                                 
                 	   }
                        	htmlContent+= ' <input type="hidden" value="'+sigma.mediaId+'" name="mediaId" id="mediaId"/>'+
                            ' <input type="hidden" value="${screenType}" name="screenType" id="screenType"/>'+
                            ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                            ' <input type="hidden" value="'+sigma.videoType+'" name="videoType" id="videoType"/>'+
                            ' <input type="hidden" value="'+sigma.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                            ' <input type="hidden" value="Video" name="action" id="action"/>'+
                            ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                            '<button class=""> <img src="../resources/images/bg.jpg" alt="North Cascades National Park" >'+
                       	  ' </img><div class="gallery-item-caption"><div><h2>'+sigma.title.substr(0,25)+'</h2></div></div></button></form>'; 
                       	  
                      }
                        if(type=='application/pdf'){
                        	  htmlContent+='<div class="gallery-item col-sm-6 col-lg-3">';
                            	 if(sigma.videoType=='Private')
                         	   {
                          		htmlContent+='<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">';                                 
                         	   }
                            	if(sigma.videoType=='Youtube')
                      	   {
                       		htmlContent+='<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">';                                 
                      	   }
                             	htmlContent+= ' <input type="hidden" value="'+sigma.mediaId+'" name="mediaId" id="mediaId"/>'+
                                 ' <input type="hidden" value="${screenType}" name="screenType" id="screenType"/>'+
                                 ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                 ' <input type="hidden" value="'+sigma.videoType+'" name="videoType" id="videoType"/>'+
                                 ' <input type="hidden" value="'+sigma.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                                 ' <input type="hidden" value="Pdf" name="action" id="action"/>'+
                                 ' <input type="hidden" value="application/pdf" name="mediaType" id="mediaType"/>'+
                                 '<button class=""> <img src="../resources/images/pdf.png" alt="North Cascades National Park" >'+
                            	  ' </img><div class="gallery-item-caption"><div><h2>'+sigma.title.substr(0,25)+'</h2></div></div></button></form>'; 
                   	   //htmlContent+='<div class="gallery-item  col-sm-6 col-lg-3"><a class="vimeo-popup" target="_blank" href="https://www.youtube.com/watch?v='+sigma.youtubeUrl+'"><img src="../resources/images/pdf.png" alt="North Cascades National Park" ></img><div class="gallery-item-caption"><div><h2>'+sigma.title+'</h2></div></div></a>';
                      }  
                	  htmlContent+='</div>';

                      });
            	  htmlContent+='</div>';
            	  if(offset==0){
                	  document.getElementById("sigmaDetaildiv").innerHTML = htmlContent;
            	  }
            	  if(offset>0){
                      $("#sigmaDetaildiv").append(htmlContent);
            	  }
            	  var loadMorehtml='';
            	  if(data.response.length==12){
            		  if(type=='video/mp4'){
                		  loadMorehtml+='<button onclick="callLoadMoreVideos()" class="btn ">Load More</button>' 
                	  }
            		  else if(type=='application/pdf'){
                		  loadMorehtml+='<button onclick="callLoadMorePdfs()" class="btn ">Load More</button>' 
                	  }  
            	  }
            	  
            	  document.getElementById("loadMore").innerHTML = loadMorehtml;
                	}

                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            }).done(function(data) {
                console.log(data);
            });
        }
        
        
        $('#videos').on('click', function(event){
        	event.preventDefault()
        	$(this).addClass('active');
        	$('#pdfs').removeClass('active');
        });
        $('#pdfs').on('click', function(event){
        	event.preventDefault()
        	$(this).addClass('active');
        	$('#videos').removeClass('active');
        });
    </script>
</div>