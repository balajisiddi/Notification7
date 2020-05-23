<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-light bg-light" id="footer"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util" version="2.0">
	<spring:url var="cc_logo1"
		value="../resources/images/ennovation-logo.png" />
	<section id="header">
		<div class="nav">
			<div class="row">
				<div class="col-sm-4 col-lg-4">
					<div class="nav-header">
						<a href="${pageContext.request.contextPath}/common/dashboard">               
						<img src="../resources/images/ennovation-logo.png" alt="logo" class="logo"/>
						</a>
					</div>
				</div>
				<div class="col-sm-8 col-lg-8">
					<div class="nav-links">
						<div class="row">
							<div class="col-sm-4 col-lg-8"><!--  --></div>
							<div class="col-sm-4 col-lg-4">
								<ul>
								<c:if test="${headerFlag == true}">
									<li class="dropdown"><a class="nav-link text-dark"
										href="#" id="navbarDropdown" role="button"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="fa fa-bell"><!--  --></i>
									</a>
										<ul class="dropdown-menu">
											<li class="head text-white">
												<div class="row">
													<div class="col-lg-12 col-sm-12 text-center">
														<span>Notifications </span>
													</div>
												</div>
											</li>
											<div id="notificationdiv" class="style-2"></div>
											<li class="text-center footer-view" id="viewAllId"><a href="${pageContext.request.contextPath}/common/notifications">View All</a></li>
										</ul></li>
										</c:if>
									<li>
										<div class="profile-img">
											<a href="${pageContext.request.contextPath}/common/profile"> <img
												src="${pageContext.request.contextPath}/studt/userImage?userId=${userId}&amp;userType=${userType}&amp;imgPath=${user.studentImgPath}&amp;imgName=${user.studentImgName}" alt="no_image" />
											</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
		<script type="text/javascript">
  var limit = 5;
  var offset = 0;
  var userId="${userId}";
  var userType="${userType}";
  $(document).ready(function() {
	  if("${headerFlag}"){
	  getAllNotifications();
	  }
	   });
   function getAllNotifications(){
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getAllNotifications",
          async : false,
          data:{
        	  userId:"${userId}",
        	  userType:"${userType}",
        	  limit: 5,
        	  offset: 0
          },
          success : function(data) {
        	  if(data.response.length==0){
        		  $('<div class="mtdata" id="assigndiv">No Notifications</div>').appendTo('#notificationdiv');
        		  $('#viewAllId').hide();
        	  }
              var table = $('<div/>').appendTo($('#notificationdiv'));
              $.each(data.response, function(i, notification) {
                  var tab=$('<li class="notification-box"/>').appendTo(table);
                  if (notification.action == "careerMain") {
                      tab.append($('<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
                      ' <button class=""><input type="hidden" value="'+notification.subCatId+'" name="subCatId" id="subCatId"/>'+
                    	' <div class="row"><div class="col-sm-1 col-lg-1"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i>'+
                    	'</div><div class="col-sm-8 col-lg-8"><p>'+notification.message+'</p></div><div class="col-sm-3 col-lg-3"><small>'+
                    	'<span>'+notification.displayDate+'</span></small></div></div></button></form>'));
                  } else if (notification.action == "Video" ) {
                	  var htmlContt='<div>';
                	  if(notification.videoType=='Private'){
                    	  htmlContt+='<form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">';
                        }
                      if(notification.videoType=='Youtube'){
                    	  htmlContt+='<form action="${pageContext.request.contextPath}/common/playVideo" method="POST">';
                        }
                      htmlContt+= ' <input type="hidden" value="'+notification.mediaId+'" name="mediaId" id="mediaId"/>'+
                      ' <input type="hidden" value="'+notification.screen+'" name="screenType" id="screenType"/>'+
                       ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                       ' <input type="hidden" value="notification" name="scrType" id="scrType"/>'+
                       ' <input type="hidden" value="Video" name="action" id="action"/>'+
                       ' <input type="hidden" value="'+notification.videoType+'" name="videoType" id="videoType"/>'+
                       ' <input type="hidden" value="'+notification.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                       ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>';
                       if(notification.subcategory!=null){
                    	   htmlContt+= '<input type="hidden" value="'+notification.subcategory.id+'" name="subCatId" id="subCatId"/>';
                      }
                       htmlContt+= '<button class=""><div class="row"><div class="col-sm-1 col-lg-1"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i>'+
                       '</div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p></div></div>'+
                       '<div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></button></form></div>';
                      tab.append($(htmlContt));
                  } else if (notification.action == "Pdf") {
                      tab.append($('<form  action="${pageContext.request.contextPath}/studt/mediaType"><button class="">'+
                              ' <input type="hidden" value="'+notification.screen+'" name="screenType" id="screenType"/>'+
                              ' <input type="hidden" value="'+notification.mediaId+'" name="mediaId" id="mediaId"/>'+
                              ' <input type="hidden" value="'+userId+'" name="userId" id="userId"/>'+
                              ' <input type="hidden" value="'+userType+'" name="userType" id="userType"/>'+
                              ' <div class="row"><div class="col-sm-1 col-lg-1">' +
                      ' <i class="fa fa-file-pdf-o" aria-hidden="true"><!--  --></i>' +
                      ' </div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p></div></div>' +
                      ' <div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> ' +
                      '  </small></div></div></button></form>'));
                  } else if (notification.action == "Accepted" && userType == "ROLE_STUDENT") {
                      tab.append($('<form action="${pageContext.request.contextPath}/student/mentorDetails" method="POST"><input type="hidden" value="'+notification.mentor.id+'" name="mentorId" id="mentorId"/><button class="">'+
                      '<div class="row"><div class="col-sm-1 col-lg-1"><i class="fa fa-check" aria-hidden="true"><!--  --></i></div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></button></form>'));
                  }
                  else if (notification.action == "Rejected" && userType == "ROLE_STUDENT") {
                      tab.append($('<a href="javascript:openRejectPopup()"><div class="row"><div class="col-sm-1 col-lg-1"><i class="fa fa-ban" aria-hidden="true"><!--  --></i></div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></a>'));
                  }
                  else if (notification.action == "Request" && userType == "ROLE_MENTOR") {
                      tab.append($('<a href="javascript:openPopup();"openPopup()"><input type="hidden" value="'+notification.student.firstName+'" id="name"/><input type="hidden" value="'+notification.student.studentClass+'" id="class"/><input type="hidden" value="'+notification.student.id+'" id="stId"/><input type="hidden" value="'+notification.student.section+'" id="section"/><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-paper-plane" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></a>'));
                  }
                  else if (notification.action == "contribution") {
                	  tab.append($('<form	action="${pageContext.request.contextPath}/common/s7TalksDetails" method="post">'+
                	  '<input type="hidden" name="s7TalksUserType" value="${userType}"/>'+
                	  '<input type="hidden" name="s7TalksUserId" value="${userId}"/>'+
                	  '<input type="hidden" name="mediaType" value="video/mp4"/>'+
                	  '<input type="hidden" name="latest" value="0"/>'+
                	  '<input type="hidden" name="limit" value="12"/>'+
                	  '<input type="hidden" name="offset" value="0"/>'+
                      ' <button class=""><div class="row"><div class="col-sm-1 col-lg-1 text-center"> '+
                       '<i class="fa fa-hand-holding-heart" ><!--  --></i> '+
                       '</div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p> '+
                       '</div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> '+
                       ' </small></div></div></button></form>'));
                  }
        	  }); 
        	/*   $.each(data.response, function(i, notification) {
                  var tab=$('<li class="notification-box"/>').appendTo(table);
                  if (notification.action == "careerMain") {
                      tab.append($('<a href="${pageContext.request.contextPath}/common/subCategoryDetailForMain/'+notification.subcategory.id+'"><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> </small></div></div></a>'));
                  } else if (notification.action == "Video" ) {
                      tab.append($('<a href="${pageContext.request.contextPath}/common/playVideo?youtubeId='+notification.youtubeUrl+'"><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></a>'));
                  } else if (notification.action == "Pdf") {
                      tab.append($('<a target="_blank" href="${pageContext.request.contextPath}/studt/mediaType?screenType='+notification.screen+'&mediaId='+notification.mediaId+'&userId='+userId+'&userType='+userType+'"><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> 15:00</small></div></div></a>'));
                  } else if (notification.action == "Accepted") {
                      tab.append($('<a href=""><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> </small></div></div></a>'));
                  }
                  else if (notification.action == "Rejected") {
                      tab.append($('<a href=""><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> </small></div></div></a>'));
                  }
                  else if (notification.action == "Request") {
                      tab.append($('<a href=""><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> </small></div></div></a>'));
                  }
                  else if (notification.action == "contribution") {
                      tab.append($('<form	action="${pageContext.request.contextPath}/common/s7TalksDetails" method="post">'+
                	  '<input type="hidden" name="s7TalksUserType" value="${userType}"/>'+
                	  '<input type="hidden" name="s7TalksUserId" value="${userId}"/>'+
                	  '<input type="hidden" name="mediaType" value="video/mp4"/>'+
                	  '<input type="hidden" name="latest" value="0"/>'+
                	  '<input type="hidden" name="limit" value="12"/>'+
                	  '<input type="hidden" name="offset" value="0"/>'+
                	  '<button class=""><div class="row"><div class="col-sm-2 col-lg-2 text-center"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-7 col-lg-7"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> </small></div></div></button></form>'));
                  }
                  }); */
        	  
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

</nav>

