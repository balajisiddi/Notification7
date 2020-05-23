<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<div class="main mb-5">
		<section class="activity-log">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-12 offset-lg-1 col-lg-10">
						<div class="activity-heading">
							<h2>Activity Log</h2>
						</div>
					</div>
					<div class="col-sm-12 offset-lg-1 col-lg-10">
						<div class="bgsuccess cbe">
							<div id="tracking">
								<div id="somediv"></div>
									<div id="ndata"></div>
									<div id="morediv"></div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<script type="text/javascript">
  var limit = 10;
  var offset = 0;
  $(document).ready(function() {
	  getActivities();
	  $("#mydiv").click(function() {
		  limit=limit;
		  offset=offset+10;
		  getActivities();
       });
  });
   function getActivities(){
	   var userId;
	   var userType;
	   if("${scType}"=='main'){
		    userId = "${userId}";
		      userType = "${userType}";
	   }
	   if("${scType}"=='inner'){
		    userId = "${stId}"
		    userType = 'ROLE_STUDENT';
	   }
	   var scrType='activity';
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getAllActivities",
          async : false,
          data:{
        	  limit:limit,
        	  offset:offset,
        	  userId:userId,
        	  userType:userType
          },
          success : function(data) {
              if(data.response.length==0){
            	  document.getElementById('ndata').innerHTML='<div class="mtdata">No Data!</div>';
              }
              else if(data.response.length>0){
            	  var table = $('<div class="tracking-list"/>').appendTo($('#somediv'));
              
        	  $.each(data.response, function(i, activity) {
                  var tab=$('<div class="tracking-item row"/>').appendTo(table);
                  tab.append($('<div class="tracking-date col-sm-3 col-lg-3"/>').text(activity.activityDate));
                 // tab.append($('<div class="tracking-icon status-intransit"></div>'));
                  if(activity.action=='Video'){
                      var htmlContt='<div class="tracking-icon status-intransit col-sm-1  col-lg-1"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8">';
                      if(activity.videoType=='Private'){
                    	  htmlContt+= '<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">';
                        }
                      if(activity.videoType=='Youtube'){
                    	  htmlContt+='<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">';
                        }
                      htmlContt+= ' <input type="hidden" value="'+activity.mediaId+'" name="mediaId" id="mediaId"/>'+
                             ' <input type="hidden" value="'+activity.screen+'" name="screenType" id="screenType"/>'+
                              ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                              ' <input type="hidden" value="activity" name="scrType" id="scrType"/>'+
                              ' <input type="hidden" value="Video" name="action" id="action"/>'+
                              ' <input type="hidden" value="'+activity.videoType+'" name="videoType" id="videoType"/>'+
                              ' <input type="hidden" value="'+activity.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                              ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>';
                              if(activity.subcategory!=null){
                           	   htmlContt+= '<input type="hidden" value="'+activity.subcategory.id+'" name="subCatId" id="subCatId"/>';
                             }
                              htmlContt+= '<button class="">'+activity.message+'</button></form></div>';
                        	
                              tab.append($(htmlContt));
                     
                  }
                  if(activity.action=='Pdf' ){
                	  var htmlContt='<div class="tracking-icon status-intransit col-sm-1 col-lg-1"><i class="fa fa-file-pdf-o" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8">'+
                     		 ' <form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">'+
                   			 ' <input type="hidden" value="'+activity.mediaId+'" name="mediaId" id="mediaId"/>'+
                               ' <input type="hidden" value="'+activity.screen+'" name="screenType" id="screenType"/>'+
                               ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                               ' <input type="hidden" value="Pdf" name="action" id="action"/>'+
                               ' <input type="hidden" value="'+activity.videoType+'" name="videoType" id="videoType"/>'+
                               ' <input type="hidden" value="'+activity.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                               ' <input type="hidden" value="application/pdf" name="mediaType" id="mediaType"/>';
                	   if(activity.subcategory!=null){
                      	   htmlContt+= '<input type="hidden" value="'+activity.subcategory.id+'" name="subCatId" id="subCatId"/>';
                        }
                       	  htmlContt+= '<button class="">'+activity.message+'</button></form>';
                        '</div>';
                	  tab.append($(htmlContt));

                  }
                  if(activity.action=='Subscribed' ){
                      tab.append($('<div class="tracking-icon status-intransit col-sm-1 col-lg-1"><i class="fa fa-bell" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8"><form action="${pageContext.request.contextPath}/common/subCategory" method="POST"><input type="hidden" value="'+activity.subcategory.id+'" name="subCatId" id="subCatId"/><button>'+activity.message+'</button></form></div>'));
                  }
                  if(activity.action=='UnSubscribed'){
                      tab.append($('<div class="tracking-icon status-intransit col-sm-1 col-lg-1"><i class="fa fa-bell-slash" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8"><form action="${pageContext.request.contextPath}/common/subCategory" method="POST"><input type="hidden" value="'+activity.subcategory.id+'" name="subCatId" id="subCatId"/><button>'+activity.message+'</button></form></div>'));
                  }
                  if(activity.action=='Before_Published' ){
                      tab.append($('<div class="tracking-icon status-intransit col-sm-1 col-lg-1"><i class="fa fa-upload" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8">'+activity.message+'</div>'));
                  }
                  if( activity.action=='ChangePassword' ){
                      tab.append($('<div class="tracking-icon status-intransit col-sm-1 col-lg-1"><i class="fa fa-lock" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8">'+activity.message+'</div>'));
                  }
                  if(activity.action=='Update'){
                      tab.append($('<div class="tracking-icon status-intransit col-sm-1 col-lg-1"><i class="fa fa-wrench" aria-hidden="true"><!--  --></i></div><div class="tracking-content col-sm-8 col-lg-8">'+activity.message+'</div>'));
                  }
                 
              });
              }
        	  if(data.response.length==10){
        		  document.getElementById('morediv').innerHTML='<div class="loadmoreBtn">'+
          				'<button id="mydiv" class="btn ">Load More</button>'+
          				'</div>';
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