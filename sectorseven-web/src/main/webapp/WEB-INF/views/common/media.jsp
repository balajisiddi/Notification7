<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	      <div class="lightpage library-main">
        <div class="sexytabs">
            <div class="contents lib-inner-page">
                <div class="section custom-full1">
                    <section class="student-talks media-detail">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-sm-12 col-lg-12">
					                <form action="${pageContext.request.contextPath}/common/subCategory" method="POST">
				<input type="hidden" value="${subCatId}" name="subCatId" id="subCatId"/>
				<button class="btn btn-info2">
						<div class="round">
							<i class="fa fa-arrow-left" aria-hidden="true">
								<!--  -->
							</i>
						</div>
					</button>
					</form>
				                 </div>
                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-sm-12 col-lg-12">
                                    <div class="activity-heading mt-2 mb-4 pl-3">
                                        <h2>Media</h2>
                                    </div>
                                </div>
                            </div>
                            <div id="tabs" id="mediaReady">
                                <div class="row">
                                    <div class="col-sm-12 col-lg-12">
                                        <div class="tab-content seven-sigma">
                                            <div class="seven-sigma-option-bg">
                                               <div class="container">
													<div class="row">
														<div class="col-lg-6">
															<form class="form-horizontal needs-validation">
																	<input type="hidden" id="videoOffset" name="offset" value="" />
																<button class="btn-sm animated-button active" id="videoMedia">VIDEO</button>
															</form>
														</div>
														<div class="col-lg-6">
															<form class="form-horizontal needs-validation">
																<input type="hidden" id="pdfOffset" name="offset" value="" />
																<button class="btn-sm animated-button" id="pdfMedia">PDF</button>
															</form>
														</div>
													</div>
												</div>
                                            </div>
											<div class="video-gallery active" role="tabpanel">
                                                <div class="row" id="videoData">
                                                <!--  -->
                                                </div>
                                                <div id="loader-wrapper" class="row">
													<div id="loader"></div>
												</div>
                                                 <div class="activity-heading mt-2 mb-4 pl-3">
													<h5 id="videoloadMore">Load More</h5>
												</div>
												 
											</div>
											<div class="video-gallery pdf-gallery" role="tabpanel">
                                                <div class="row" id="pdfsData">
                                                <!--  -->
                                                </div>
                                                <div class="activity-heading mt-2 mb-4 pl-3">
													<h5 id="pdfloadMore">Load More</h5>
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

            </div>
        </div>
    </div>
	  </div>
	

    <script type="text/javascript">
    //for Videos
    function getVideos(){
    	var screenType="subCategory";
    	var scrType="subCategory";
    	var subScrType="subCategory";

        	$.ajax({
        		type:"GET",
        		url:"${pageContext.request.contextPath}/studt/mediaDetails",
        		data: {
        			userId: 0,
        			userType:"ROLE_DEVELOPER",
        			mediaId:"${subCatId}",
        			type: "video/mp4",
        			screenType: "subCategory",
        			limit: 12,
        			offset: 0
        		},
        		success: function(data){
        			$('#videoOffset').val(12);
        			$('#videoData').empty();
        			if(data.response.length > 11){
    					$('#videoloadMore').show();
    				}
        			
        			else{
        				$('#videoloadMore').hide();
        			}
        			if(data.response.length === 0){
        				$('#videoloadMore').hide();
    					$('#videoData').html('<div  class="mtdata">No Data!</div>');
    				}
        			$.each(data.response, function(index, videos){
        				var imagePath="../resources/images/bg.jpg";
            			var column=$('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
            			var anchor=$('<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">'+
                     			 ' <input type="hidden" value="'+videos.mediaId+'" name="mediaId" id="mediaId"/>'+
                                 ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                 ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                 ' <input type="hidden" value="'+subScrType+'" name="subScrType" id="subScrType"/>'+
                                 ' <input type="hidden" value="Video" name="action" id="action"/>'+
                                 ' <input type="hidden" value="'+videos.videoType+'" name="videoType" id="videoType"/>'+
                                 ' <input type="hidden" value="'+videos.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                                 ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                                 ' <input type="hidden" value="'+videos.subCatId+'" name="subCatId" id="subCatId"/>');
            			var gallery_item= $('<div class="gallery-item "/>');
            			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
            			var gallery_item_button= $('<button class=""/>');
            			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
            			var relativ_div= $('<div/></form>');
            			var heshtwo= $('<h2/>').append(videos.title.substr(0,25));
            			$('#videoData').append(column.append(anchor.append(gallery_item_button.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo)))))));
            		
        			});
        		},
        		error: function(){
        			console.log("error occured");
        		}
        	});
    }
    
  //for load more Videos
    function loadMoreVideos(){ 
    	var screenType="subCategory";
    	var scrType="subCategory";
    	var subScrType="subCategory";

	  var offset=parseInt($('#videoOffset').val());
        	$.ajax({
        		type:"GET",
        		url:"${pageContext.request.contextPath}/studt/mediaDetails",
        		data: {
        			userId: 0,
        			userType:"ROLE_DEVELOPER",
        			mediaId:"${subCatId}",
        			type: "video/mp4",
        			screenType: "subCategory",
        			limit: 12,
        			offset: offset
        			
        		},
        		success: function(data){
        			$('#videoOffset').val(parseInt($('#videoOffset').val())+12);
        			console.log(data);
//         			$('#videoData').empty();
					console.log("hooo"+Object.keys(data.response).length);
        			if(Object.keys(data.response).length === 0 || Object.keys(data.response).length > 11){
    					$('#videoloadMore').show();
    				}
        			else{
        				$('#videoloadMore').hide();
        			}
        			if(Object.keys(data.response).length === 0){
        				$('#videoloadMore').hide();
    					$('#videoData').html('<div  class="mtdata">No Data!</div>');
    				}
        			$.each(data.response, function(index, videos){
        				var imagePath="../resources/images/bg.jpg";
            			var column=$('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
            			var anchor=$('<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">'+
                    			 ' <input type="hidden" value="'+videos.mediaId+'" name="mediaId" id="mediaId"/>'+
                                ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                ' <input type="hidden" value="'+subScrType+'" name="subScrType" id="subScrType"/>'+
                                ' <input type="hidden" value="Video" name="action" id="action"/>'+
                                ' <input type="hidden" value="'+videos.videoType+'" name="videoType" id="videoType"/>'+
                                ' <input type="hidden" value="'+videos.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                                ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                                ' <input type="hidden" value="'+videos.subCatId+'" name="subCatId" id="subCatId"/>');
            			var gallery_item= $('<div class="gallery-item "/>');
            			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
            			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
            			var gallery_item_button= $('<button class=""/>');
            			var relativ_div= $('<div/>');
            			var heshtwo= $('<h2/>').append(videos.title.substr(0,25));
            			$('#videoData').append(column.append(anchor.append(gallery_item_button.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo)))))));
            		
        			});
        		},
        		error: function(){
        			console.log("error occured");
        		}
        	});
    }
    
  	//for PDFs
    function getPDFs(){
    	var screenType="subCategory";
    	var scrType="subCategory";
    	var subScrType="subCategory";

        	$.ajax({
        		type:"GET",
        		url:"${pageContext.request.contextPath}/studt/mediaDetails",
        		data: {
        			userId: 0,
        			userType:"ROLE_DEVELOPER",
        			mediaId:"${subCatId}",
        			type: "application/pdf",
        			screenType: "subCategory",
        			limit: 12,
        			offset: 0
        		},
        		success: function(data){
        			$('#pdfOffset').val(12);
        			console.log(data);
        			$('#pdfsData').empty();
        			console.log(Object.keys(data.response).length);
        			if(Object.keys(data.response).length === 0 || Object.keys(data.response).length > 11){
    					$('#pdfloadMore').show();
    				}
        			if(Object.keys(data.response).length === 0){
        				$('#pdfloadMore').hide();        	
    					$('#pdfsData').html('<div  class="mtdata">No Data!</div>');
    				}
        			$.each(data.response, function(index, videos){
        				var imagePath="../resources/images/pdf.png";
            			var column=$('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
            			var anchor=$('<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">'+
                   			 ' <input type="hidden" value="'+videos.mediaId+'" name="mediaId" id="mediaId"/>'+
                               ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                               ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                               ' <input type="hidden" value="'+subScrType+'" name="subScrType" id="subScrType"/>'+
                               ' <input type="hidden" value="Pdf" name="action" id="action"/>'+
                               ' <input type="hidden" value="'+videos.videoType+'" name="videoType" id="videoType"/>'+
                               ' <input type="hidden" value="'+videos.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                               ' <input type="hidden" value="application/pdf" name="mediaType" id="mediaType"/>'+
                               ' <input type="hidden" value="'+videos.subCatId+'" name="subCatId" id="subCatId"/>');
            			var gallery_item= $('<div class="gallery-item "/>');
            			var gallery_item_button= $('<button class=""/>');
            			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
            			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
            			var relativ_div= $('<div/>');
            			var heshtwo= $('<h2/>').append(videos.title.substr(0,25));
            			$('#pdfsData').append(column.append(anchor.append(gallery_item_button.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo)))))));
            		
        			});
        		},
        		error: function(){
        			console.log("error occured");
        		}
        	});
    }
  	
  //for load more PDFs
    function loadMorePDFs(){
    	var screenType="subCategory";
    	var scrType="subCategory";
    	var subScrType="subCategory";

    	var offset=parseInt($('#pdfOffset').val());
        	$.ajax({
        		type:"GET",
        		url:"${pageContext.request.contextPath}/studt/mediaDetails",
        		data: {
        			userId: 0,
        			userType:"ROLE_DEVELOPER",
        			mediaId:"${subCatId}",
        			type: "application/pdf",
        			screenType: "subCategory",
        			limit: 12,
        			offset: offset
        		},
        		success: function(data){
        			console.log(data);
//         			$('#pdfsData').empty();
					$('#pdfOffset').val(parseInt($('#pdfOffset').val())+12);
        			console.log(Object.keys(data.response).length);
        			if(Object.keys(data.response).length === 0 || Object.keys(data.response).length > 11){
    					$('#pdfloadMore').show();
    				}
        			else{
        				$('#pdfloadMore').hide();
        			}
        			if(Object.keys(data.response).length === 0){
        				$('#pdfloadMore').hide();        	
    					$('#pdfsData').html('<div  class="mtdata">No Data!</div>');
    				}
        			$.each(data.response, function(index, videos){
        				var imagePath="../resources/images/pdf.png";
            			var column=$('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
            			var anchor=$('<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">'+
                      			 ' <input type="hidden" value="'+videos.mediaId+'" name="mediaId" id="mediaId"/>'+
                                  ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                                  ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                                  ' <input type="hidden" value="'+subScrType+'" name="subScrType" id="subScrType"/>'+
                                  ' <input type="hidden" value="Video" name="action" id="action"/>'+
                                  ' <input type="hidden" value="'+videos.videoType+'" name="videoType" id="videoType"/>'+
                                  ' <input type="hidden" value="'+videos.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                                  ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                                  ' <input type="hidden" value="'+videos.subCatId+'" name="subCatId" id="subCatId"/>');
            			var gallery_item= $('<div class="gallery-item "/>');
            			var gallery_item_button= $('<button class=""/>');
            			var image= $('<img alt="North Cascades National Park" />').attr("src", imagePath);
            			var gallery_item_caption= $('<div class="gallery-item-caption"/>');
            			var relativ_div= $('<div/>');
            			var heshtwo= $('<h2/>').append(videos.title.substr(0,25));
            			$('#pdfsData').append(column.append(anchor.append(gallery_item_button.append(gallery_item.append(image).append(gallery_item_caption.append(relativ_div.append(heshtwo)))))));
            		
        			});
        		},
        		error: function(){
        			console.log("error occured");
        		}
        	});
    }
    $('#mediaReady').ready(function(){
    	$('#loader-wrapper').hide();
    	$('#pdfsData').hide();
    	$('#pdfloadMore').hide();
    	$('#videoData').show();
    	getVideos();
    });
    $('#videoMedia').on('click', function(event){
    	event.preventDefault()
    	$('#videoData').show();
    	$('#pdfloadMore').hide();
    	$('#pdfsData').hide();
    	$(this).addClass('active');
    	$('#pdfMedia').removeClass('active');
    	getVideos();
    });
    $('#pdfMedia').on('click', function(event){
    	event.preventDefault()
    	$(this).addClass('active');
    	$('#videoMedia').removeClass('active');
    	$('#videoData').hide();
    	$('#videoloadMore').hide();
    	$('#pdfsData').show();
    	getPDFs();
    });
    $('#videoloadMore').on('click', function(event){
    	$('#loader-wrapper').show();
    	$('#videoloadMore').hide();
    	setTimeout(
    			  function() 
    			  {
    				  $('#loader-wrapper').hide();
    				  loadMoreVideos();
    			  }, 2000);
    });
    $('#pdfloadMore').on('click', function(event){
    	loadMorePDFs();
   });
        $(document).ready(function () {
            $("#tabs").tabs({ selected: 1 });
        });
        document.getElementById('back').innerHTML='<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
        ' <input type="hidden" value="${subCatId}" name="subCatId" id="subCatId"/>'+
        '  <button class="btn btn-info2">'+
         '<div class="round">'+
          '   <i class="fa fa-arrow-left" aria-hidden="true"></i>'+
         '</div>'+
    ' </button></form>';
          </script>