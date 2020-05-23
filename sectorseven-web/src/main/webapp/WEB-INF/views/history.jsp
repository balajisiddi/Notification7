<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<div class="interest-sec">
		<div class="container-fluid">
			<div class="row mt-3">
				<div class="col-sm-12 col-lg-12">
					<div class="activity-heading pl-3">
						<h2>${subCatName}</h2>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-12 col-lg-6">
					<div class="interests">
						<div class="row">
							<div class="col-xs-12 col-sm-4 col-lg-5">
								<canvas id="myCanvas" height="200"></canvas>
							</div>
							<div class="col-xs-12 col-sm-4 col-lg-2">
								<div class="line"></div>
							</div>
							<div class="col-xs-12 col-sm-4 col-lg-5">
								<div class="bgsuccess video">
									<%--   <input type="hidden" name="userId" id="userId" value="${userId}">
                 <input type="hidden" name="userType" id="userType" value="${userType}">
                 <input type="hidden" name="subCatId" id="subCatId" value="${subCatId}">
                 <input type="hidden" name="stId" id="stId" value="${stId}">
                  <input type="hidden" value="${scrType}" name="scrType" id="scrType"/> --%>

									<form action="${pageContext.request.contextPath}/common/videos"
										method="POST">
										<input type="hidden" value="stats" name="screenType"
											id="screenType" /> <input type="hidden" value="${subCatId}"
											name="subCatId" id="subCatId" /> <input type="hidden"
											value="${stId}" name="stId" id="stId" /> <input type="hidden"
											value="stats" name="scrType" id="scrType" /> <input
											type="hidden" value="${subScrType}" name="subScrType" id="subScrType" />
										<button class="btn btn-info1">
											<div class="rounded-circle icovideo">
												<i class="fa fa-video-camera" aria-hidden="true"></i>No. Of
												Videos
											</div>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-6">
					<div class="interests pdfrow">
						<div class="row">
							<div class="col-xs-12 col-sm-4 col-lg-5">
								<canvas id="myinterest" height="200"></canvas>
							</div>
							<div class="col-xs-12 col-sm-4 col-lg-2">
								<div class="line"></div>
								<div class="line1"></div>
							</div>
							<div class="col-xs-12 col-sm-4 col-lg-5">
								<div class="bgsuccess">
									<form action="${pageContext.request.contextPath}/common/pdfs"
										method="POST">
										<input type="hidden" value="stats" name="screenType"
											id="screenType" /> <input type="hidden" value="${subCatId}"
											name="subCatId" id="subCatId" /> <input type="hidden"
											value="${stId}" name="stId" id="stId" /> <input type="hidden"
											value="stats" name="scrType" id="scrType" /> <input
											type="hidden" value="${subScrType}" name="subScrType"
											id="subScrType" />
										<button class="btn btn-info1">
											<div class="rounded-circle icovideo">
												<i class="fa fa-file-pdf-o" aria-hidden="true"></i>No. Of
												Pdfs
											</div>
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
  $(document).ready(function(){
	  getPieChartPercentagesVideos();
	  getPieChartPercentagesPdfs();
  });
	   function getPieChartPercentagesVideos(){
		     var canvas = document.getElementById('myCanvas');
			  var context = canvas.getContext('2d');
		      var al = 0;
			  var start = 4.72;
			  var cw = context.canvas.width / 2;
			  var ch = context.canvas.height / 2;
			  var diff;
         function progressBar(){
		   diff = (al / 100) * Math.PI * 2;
		    context.clearRect(0, 0, 400, 200);
		    context.beginPath();
		    context.arc(cw, ch, 50, 0, 2 * Math.PI, false);
		    context.fillStyle = '#FFF';
		    context.fill();
		    context.strokeStyle = '#e7f2ba';
		    context.stroke();
		    context.fillStyle = '#000';
  		       var userId;
		      var userType ;
		      var subCatId = "${subCatId}";
		      if("${subScrType}" == 'inner'){
	            	 userId = "${stId}";
	            	 userType= 'ROLE_STUDENT';
	            }else {
	             userId = "${userId}";
	           	 userType= "${userType}";
	            }
			  $.ajax({
		          type : "GET",
		          url : "${pageContext.request.contextPath}/studt/getSeenPercentages?userId="+userId+"&userType="+userType+"&subCatId="+subCatId,
		          async : false,
		          success : function(data) {
		        	  if(data.response.videos>=0.0){
		        		  context.strokeStyle = '#00bfd8';
				  		    context.textAlign = 'center';
				  		    context.lineWidth = 15;
				  		    context.font = '10pt Verdana';
				  		    context.beginPath();
				  		    context.arc(cw, ch, 50, start, diff + start, false);
				  		    context.stroke();
				  		    context.fillText(al + '%', cw + 2, ch + 6);
				        		  
		        	  }
		        	  if (al >= data.response.videos) {
		    		      clearTimeout(bar);
		    		    }
		          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
		   		    al++;
            }
		    var bar = setInterval(progressBar, 50);

			
		  }
	   function getPieChartPercentagesPdfs(){
		     var canvas = document.getElementById('myinterest');
			  var context = canvas.getContext('2d');
		      var al = 0;
			  var start = 4.72;
			  var cw = context.canvas.width / 2;
			  var ch = context.canvas.height / 2;
			  var diff;
       function progressBar(){
		   diff = (al / 100) * Math.PI * 2;
		    context.clearRect(0, 0, 400, 200);
		    context.beginPath();
		    context.arc(cw, ch, 50, 0, 2 * Math.PI, false);
		    context.fillStyle = '#FFF';
		    context.fill();
		    context.strokeStyle = '#e7f2ba';
		    context.stroke();
		    context.fillStyle = '#000';
		      var userId;
		      var userType ;
		      var subCatId = "${subCatId}";
		      if("${subScrType}" == 'inner'){
	            	 userId = "${stId}";
	            	 userType= 'ROLE_STUDENT';
	            }else {
	             userId = "${userId}";
	           	 userType= "${userType}";
	            }
			  $.ajax({
		          type : "GET",
		          url : "${pageContext.request.contextPath}/studt/getSeenPercentages?userId="+userId+"&userType="+userType+"&subCatId="+subCatId,
		          async : false,
		          success : function(data) {
		        	  if(data.response.pdfs>=0){
		        		  context.strokeStyle = '#ff4343';
				  		    context.textAlign = 'center';
				  		    context.lineWidth = 15;
				  		    context.font = '10pt Verdana';
				  		    context.beginPath();
				  		    context.arc(cw, ch, 50, start, diff + start, false);
				  		    context.stroke();
				  		    context.fillText(al + '%', cw + 2, ch + 6);
				        		  
		        	  }
		        	  if (al >= data.response.pdfs) {
		    		      clearTimeout(bar);
		    		    }
		          },
		          error : function(e) {
		              alert('Error: ' + e);
		          }
		      }).done(function(data) {
		          console.log(data);
		      });
		   		    al++;
          }
		    var bar = setInterval(progressBar, 50);

			
		  }
	   
  </script>
	<!--   <script type="text/javascript">
      var limit = 8;
        var offset = 0;
        $(document).ready(function() {
        	getSeenHistory();
        });
        $("#loadMore").click(function() {
  		  limit=limit;
  		  offset=offset+8;
  		getSeenHistory();
         });
        function getSeenHistory(){
      	  var userId = "${userId}";
            var userType = "${userType}";
            var screenType = "${screenType}";
            var subCatId = "${subCatId}";

            $.ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/studt/mediaDetails?screenType="+screenType+"&limit="+limit+"&offset="+offset+"&userId="+userId+"&userType="+userType+"&mediaId="+subCatId,
                async : false,
                success : function(data) {
                    var table = $('<div class="col-md-12"/>').appendTo($('#recdiv'));
              	  $.each(data.response, function(i, rec) {
                        var tab= $('<div class="gallery-item col-sm-6 col-lg-3"/>').appendTo(table);
                        tab.append($('<a class="vimeo-popup" target="_blank" href="https://www.youtube.com/watch?v='+rec.youtubeUrl+'"><img src="'+rec.thumbnailUrl+'" alt="North Cascades National Park" ></img><div class="gallery-item-caption"><div><h2>'+rec.title+'</h2></div></div></a>'));
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
    </script> -->

</div>