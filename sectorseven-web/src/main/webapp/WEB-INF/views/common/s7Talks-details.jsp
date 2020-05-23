<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
     <section class="student-talks" id="s7TalksSection">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-4 col-lg-3 col-xl-3">
          <div class="bgsuccess">
            <div class="text-center mb-3">
              <img src="../img/parakala.jpg" alt=""/>
            </div>
            <div id="profile">
          <!--  <h4>G. Kiran Kumar</h4>
        	    <p>9th Standard</p>
                   <h4 class="text-left">About</h4>
                   <p class="text-justify">This program is free software;</p> -->
               </div>
                                                 <input type="hidden" value="${s7TalksUserType}" name="s7TalksUserType" id="s7TalksUserType"/>
                                                  
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
<!-- Recent Talks -->                
                <div class="video-gallery tab-pane active" id="tabs-1" role="tabpanel">
                  <div id="tabsone">
                    <div class="tab-content">
                      <div class="seven-sigma-option-bg videos">
                        <div class="container">
                          <ul class="row" role="tablist">
                            <li class="col-sm-6 col-md-6 col-lg-6 col-xl-6"><a href="#videoRecent" class="active"
                                data-toggle="tab" role="tab" aria-controls="audio">Video</a></li>
                            <li class="col-sm-6 col-md-6 col-lg-6 col-xl-6"><a href="#pdfRecent" data-toggle="tab" role="tab"
                                aria-controls="pdf">Pdf</a>
                            </li>
                          </ul>
                        </div>
                      </div>
<!-- Recent Talks Video -->                      
                      <div class="video-gallery tab-pane active" id="videoRecent">
                        <div class="container">
                          <div class="row" id="videosRecent"><!--  --></div>
                         	 <div class="activity-heading mt-2 mb-4 pl-3">
                         	 <input type="hidden" id="recentVideoLoadMoreOffset" name="offset" value="" />
								 <h5 id="recentVideoLoadMore">Load More</h5>
								</div>
                        </div>
                      </div>
<!-- Recent talks pdfs -->
                      <div class="video-gallery tab-pane" id="pdfRecent">
                        <div class="container">
                          <div class="row" id="pdfsRecent"><!--  --></div>
                          <div class="activity-heading mt-2 mb-4 pl-3">
                          <input type="hidden" id="recentPdfLoadMoreOffset" name="offset" value="" />
								 <h5 id="recentPdfLoadMore">Load More</h5>
								</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
<!-- All Talks -->
                <div class="video-gallery tab-pane" id="tabs-2">
                  <div id="tabstwo">
                    <div class="tab-content">
                      <div class="seven-sigma-option-bg videos">
                        <div class="container">
                          <ul class="row" role="tablist">
                            <li class="col-sm-6 col-md-6 col-lg-6 col-xl-6"><a href="#videoAll" class="active"
                                data-toggle="tab" role="tab" aria-controls="audio">Video</a></li>
                            <li class="col-sm-6 col-md-6 col-lg-6 col-xl-6"><a href="#pdfAll" data-toggle="tab" role="tab"
                                aria-controls="pdf">Pdf</a>
                            </li>
                          </ul>
                        </div>
                      </div>
<!-- All Talks Video -->                      
                      <div class="video-gallery tab-pane active" id="videoAll">
                        <div class="container">
                          <div class="row" id="videosAll"><!--  --></div>
                          <div class="activity-heading mt-2 mb-4 pl-3">
                          <input type="hidden" id="allVideoLoadMoreOffset" name="offset" value="" />
								 <h5 id="allVideoLoadMore">Load More</h5>
								</div>
                        </div>
                      </div>
<!-- All Talkjs Pdfs -->
                      <div class="video-gallery tab-pane" id="pdfAll">
                        <div class="container">
                          <div class="row" id="pdfsAll"><!--  --></div>
                          <div class="activity-heading mt-2 mb-4 pl-3">
                          <input type="hidden" id="allPdfLoadMoreOffset" name="offset" value="" />
								 <h5 id="allPdfLoadMore">Load More</h5>
								</div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div id="loader-wrapper" class="row">
													<div id="loader"></div>
												</div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <script type="text/javascript">
	$("#loader-wrapper").hide();
    $(document).ready(function () {
    	getprofile();
      $("#tabs").tabs({ selected: 1 });
      $("#tabsone").tabs({ selected: 1 });
      $("#tabstwo").tabs({ selected: 1 });
    });
    function getprofile(){
        var userType = document.getElementById('s7TalksUserType').value;
 	  $.ajax({
           type : "GET",
           url : "${pageContext.request.contextPath}/studt/gets7TalksProfile",
           async : false,
           data: {
        	   s7TalksUserId:"${s7TalksUserId}",
        	   s7TalksUserType:userType
			},
           success : function(data) {
        	   var htmlCont='<h4>'+data.response.name+'</h4>' ;
        	   if(userType=='ROLE_STUDENT'){
            	   htmlCont+='<p>'+data.response.stClass+'</p>';
        	   }
        	   if(userType=='ROLE_MENTOR' || userType=='ROLE_SCHOOL_TEACHER'){
            	   htmlCont+='<p>'+data.response.expertize+'</p>';
        	   }
        	   
        	   htmlCont+='<h4 class="text-left">About</h4>' +
                   ' <p class="text-justify">'+data.response.description+'</p>';
               $('#profile').append(htmlCont);
           },
           error : function(e) {
               alert('Error: ' + e);
           }
       }).done(function(data) {
           console.log(data);
       });
    }
    function gets7TalksMedia(mediaType, latestId, loadMoreId, noDataId, imageUrl){
    	var screenType='contribution';
    	var scrType='contribution';
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/gets7TalksDetails",
			data: {
				s7TalksUserId:"${s7TalksUserId}",
				s7TalksUserType:"${s7TalksUserType}",
				mediaType: mediaType,
				latest: latestId,
				limit:"${limit}",
				offset:"${offset}"
			},
			
			success: function(data){
				console.log(data);
				$('#recentVideoLoadMoreOffset').val(12);
				$('#recentPdfLoadMoreOffset').val(12);
				$('#allVideoLoadMoreOffset').val(12);
				$('#allPdfLoadMoreOffset').val(12);
				if(Object.keys(data.response).length === 0 || Object.keys(data.response).length > 11){
    				console.log(Object.keys(data.response).length);
					$('#'+loadMoreId).show();
				}
				else{
    				$('#'+loadMoreId).hide();
    			}
				if(Object.keys(data.response).length === 0){
					$('#'+noDataId).html('<div  class="mtdata">No Data!</div>');
					$('#'+loadMoreId).hide();
				}
				 $.each(data.response, function(index, s7Talks) {
					 if(mediaType=='video/mp4'){
      					var imagePath= imageUrl;
     					var anchor;
              			var column= $('<div class="col-sm-6 col-lg-3 col-xl-3"/>');
      					anchor=$('<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">'+
                    			 ' <input type="hidden" value="'+s7Talks.mediaId+'" name="mediaId" id="mediaId"/>'+
                                ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                '<input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                                ' <input type="hidden" value="Video" name="action" id="action"/>'+
                                ' <input type="hidden" value="'+s7Talks.videoType+'" name="videoType" id="videoType"/>'+
                                ' <input type="hidden" value="'+s7Talks.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>');
      					  var gallery_item= $('<div class="gallery-item"/>');
      	         			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
      	         			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
      	        			var gallery_item_button= $('<button class=""/>');
      	         			var relativ_div= $('<div/></form>');
      	         			var heshtwo= $('<h2/>').append(s7Talks.title);
      	        			$('#'+noDataId).append(column.append(anchor.append(gallery_item_button.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo)))))));
      		            
      				}
					 if(mediaType=='application/pdf'){
      					var imagePath= imageUrl;
     					var anchor;
              			var column= $('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
      					anchor=$('<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">'+
                    			 ' <input type="hidden" value="'+s7Talks.mediaId+'" name="mediaId" id="mediaId"/>'+
                                ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                '<input type="hidden" value="application/pdf" name="mediaType" id="mediaType"/>'+
                                ' <input type="hidden" value="Pdf" name="action" id="action"/>'+
                                ' <input type="hidden" value="'+s7Talks.videoType+'" name="videoType" id="videoType"/>'+
                                ' <input type="hidden" value="'+s7Talks.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>');
      					  var gallery_item= $('<div class="gallery-item"/>');
      	         			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
      	         			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
      	        			var gallery_item_button= $('<button class=""/>');
      	         			var relativ_div= $('<div/></form>');
      	         			var heshtwo= $('<h2/>').append(s7Talks.title);
      	        			$('#'+noDataId).append(column.append(anchor.append(gallery_item_button.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo)))))));
      		            
      				}
                 });
				 
			},
			error: function(){
				console.log("error occured");
			}
		});
	}
 	 //To get 4 types of user talks on load
	$("#s7TalksSection").ready(function(){
	
		gets7TalksMedia("video/mp4", 0, "recentVideoLoadMore", "videosRecent", "../resources/images/bg.jpg");
		gets7TalksMedia("application/pdf", 0, "recentPdfLoadMore", "pdfsRecent", "../resources/images/pdf.png");
		gets7TalksMedia("video/mp4", 1, "allVideoLoadMore", "videosAll", "../resources/images/bg.jpg");
		gets7TalksMedia("application/pdf", 1, "allPdfLoadMore", "pdfsAll", "../resources/images/pdf.png");
	});
 	 
 	 //for loadmore media
	function loadMores7TalksMedia(offsetId, loadMoreId, mediaType, latestId, noDataId, imageUrl){
		var offset=0;
		offset=parseInt($('#'+offsetId).val());
		var screenType='contribution';
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/gets7TalksDetails",
			data: {
				s7TalksUserId:"${s7TalksUserId}",
				s7TalksUserType:"${s7TalksUserType}",
				mediaType: mediaType,
				latest: latestId,
				limit:"${limit}",
				offset:offset
			},
			
			success: function(data){
				$('#'+offsetId).val(parseInt($('#'+offsetId).val())+12);
				if(Object.keys(data.response).length === 0 || Object.keys(data.response).length > 11){
    				console.log(Object.keys(data.response).length);
					$('#'+loadMoreId).show();
				}
				else{
    				$('#'+loadMoreId).hide();
    			}
				if(Object.keys(data.response).length === 0){
					$('#'+noDataId).html('<div  class="mtdata">No Data!</div>');
					$('#'+loadMoreId).hide();
				}
				 $.each(data.response, function(index, s7Talks) {
					var imagePath= imageUrl;
					var anchor;
         			var column= $('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
         			  var hshref="${pageContext.request.contextPath}/common/playprivateVideo?screenType=contribution&amp;mediaId="+s7Talks.mediaId+"";
                      var anchor=$('<a class="vimeo-popup" target="_blank"></a>').attr("href", hshref);
         			var gallery_item= $('<div class="gallery-item"/>');
         			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
         			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
         			var relativ_div= $('<div/>');
         			var heshtwo= $('<h2/>').append(s7Talks.title);
        			$('#'+noDataId).append(column.append(anchor.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo))))));
	             });
			},
			error: function(){
				console.log("error occured");
			}
		});
	}
 	 //LoadMore RecentVideos
	$('#recentVideoLoadMore').on('click', function(){
    	$('#loader-wrapper').show();
    	$('#recentVideoLoadMore').hide();
    	setTimeout(
    			  function() 
    			  {
    				  $('#loader-wrapper').hide();
    				  loadMores7TalksMedia("recentVideoLoadMoreOffset", "recentVideoLoadMore", "video/mp4", 0, "videosRecent", "../resources/images/bg.jpg");
    			  }, 2000);
    });
 	 
	//LoadMore RecentPdfs
	$('#recentPdfLoadMore').on('click', function(){
    	$('#loader-wrapper').show();
    	$('#recentPdfLoadMore').hide();
    	setTimeout(
    			  function() 
    			  {
    				  $('#loader-wrapper').hide();
    				  loadMores7TalksMedia("recentPdfLoadMoreOffset", "recentPdfLoadMore", "application/pdf", 0, "pdfsRecent", "../resources/images/pdf.png");
    			  }, 2000);
    });
 	 
	//LoadMore allVideos
	$('#allVideoLoadMore').on('click', function(){
    	$('#loader-wrapper').show();
    	$('#allVideoLoadMore').hide();
    	setTimeout(
    			  function() 
    			  {
    				  $('#loader-wrapper').hide();
    				  loadMores7TalksMedia("allVideoLoadMoreOffset", "allVideoLoadMore", "video/mp4", 1, "videosAll", "../resources/images/bg.jpg");
    			  }, 2000);
    });
	
	//LoadMore allPdfs
	$('#allPdfLoadMore').on('click', function(){
    	$('#loader-wrapper').show();
    	$('#allPdfLoadMore').hide();
    	setTimeout(
    			  function() 
    			  {
    				  $('#loader-wrapper').hide();
    				  loadMores7TalksMedia("allPdfLoadMoreOffset", "allPdfLoadMore", "application/pdf", 1, "pdfsAll", "../resources/images/pdf.png");
    			  }, 2000);
    });
	
  </script>
	</div>
