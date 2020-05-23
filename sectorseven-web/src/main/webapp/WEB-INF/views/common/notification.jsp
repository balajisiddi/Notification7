<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<div class="main mb-5">
   <section class="activity-log notifications">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12 col-lg-10 student-view">
                    <div class="row">
                        <div class="col-sm-12 col-lg-12">
                            <div class="activity-heading">
                                <h2>All Notifications</h2>
                            </div>
                        </div>
                        <div class="col-sm-12 col-lg-12">
                         <div class="bgsuccessnot cbe">
                             <div id="notification">
                             </div>
                            <div id="ndata"></div>
                             <input type="hidden" id="notificationOffset" name="offset" value="0" />
                             <div id="morediv">Load More</div>
                             
                           </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
  </div>
   <script type="text/javascript">
   $('#morediv').hide();
  var userId="${userId}";
  var userType="${userType}";
  $(document).ready(function() {
	  var userId="${userId}";
	  var userType="${userType}";
		  limit=limit;
		  offset=offset+10;
		  getAllNotifications(10,0);
	   });
   function getAllNotifications(flimit, foffset){
	   var scrType='notification';
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getAllNotifications",
          data:{
        	  userId:"${userId}",
        	  userType:"${userType}",
        	  limit: flimit,
        	  offset: foffset
          },
          success : function(data) {
        	  $('#notificationOffset').val(parseInt($('#notificationOffset').val())+10);
  			$('#videoData').empty();
        	  if(data.response.length>9){
        		  $('#morediv').show();
        	  }
        	  else{
        		  $('#morediv').hide();
        	  }
              if(data.response.length==0 && 5>55){
            	  document.getElementById('ndata').innerHTML='<div  class="mtdata">No Data!</div>';
              }else if(data.response.length>0){
            	  var table = $('<ul/>').appendTo($('#notification'));
        	  $.each(data.response, function(i, notification) {
                  var tab=$('<li class="notification-box notification-page"/>').appendTo(table);
                  console.log(notification.action);
                  if (notification.action == "careerMain") {
                      tab.append($('<form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
                      ' <input type="hidden" value="'+notification.subCatId+'" name="subCatId" id="subCatId"/>'+
                      '  <button class="">'+
                    	' <div class="row"><div class="col-sm-1 col-lg-1"><i class="fa fa-video-camera" aria-hidden="true"><!--  --></i></div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></button></form>'));
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
                      tab.append($('<a href="javascript:openPopup();"openPopup()"><input type="hidden" value="'+notification.student.firstName+'" id="name"/><input type="hidden" value="'+notification.student.studentClass+'" id="class"/><input type="hidden" value="'+notification.student.id+'" id="stId"/><input type="hidden" value="'+notification.student.section+'" id="section"/><div class="row"><div class="col-sm-1 col-lg-1"><i class="fa fa-paper-plane" aria-hidden="true"><!--  --></i></div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p></div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span></small></div></div></a>'));
                  }
                  else if (notification.action == "contribution") {
                	  tab.append($('<form action="${pageContext.request.contextPath}/common/s7TalksDetails" method="post">'+
                	  '<input type="hidden" name="s7TalksUserType" value="${userType}"/>'+
                	  '<input type="hidden" name="s7TalksUserId" value="${userId}"/>'+
                	  '<input type="hidden" name="mediaType" value="video/mp4"/>'+
                	  '<input type="hidden" name="latest" value="0"/>'+
                	  '<input type="hidden" name="limit" value="12"/>'+
                	  '<input type="hidden" name="offset" value="0"/>'+
                      ' <button class=""><div class="row"><div class="col-sm-1 col-lg-1"> '+
                       '<i class="fas fa-hand-holding-heart" aria-hidden="true"><!--  --></i> '+
                       '</div><div class="col-sm-8 col-lg-8"><div><p>'+notification.message+'</p>'+
                       '</div></div><div class="col-sm-3 col-lg-3"><small><span>'+notification.displayDate+'</span> '+
                       ' </small></div></div></button></form>'));
                  }
        	  }); 
              }
        	  if(data.response.length==10){
        		  document.getElementById('morediv').innerHTML='<div class="loadmoreBtn">'+
    				'<div id="customLoadMore" class="btn">Load More</div>'+
    				'</div>';
              }
          },
    	 error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
  }
   
   $('#morediv').on('click', function(event){
	   var offset=$('#notificationOffset').val();
	   console.log(offset);
	   getAllNotifications(10, offset);
  });
   
   function openPopup(){
	        const swalWithBootstrapButtons = Swal.mixin({
	        	 customClass: {
	        		    confirmButton: 'btn con-subbtn',
	        		    cancelButton: 'btn con-subbtn'
	        		  },
	        	  buttonsStyling: false
	        	})

	        	swalWithBootstrapButtons.fire({
	        	  title: 'Student Details',
		          html:"<div><b>StudentName</b><p>"+document.getElementById('name').value+"</p><b>StudentClass</b><p>"+document.getElementById('class').value+"</p><b>Section</b><p>"+document.getElementById('section').value+"</p></div>",
	        	  showCancelButton: true,
	        	  confirmButtonText: 'Accept',
	        	  cancelButtonText: 'Reject',
	        	  reverseButtons: true
	        	}).then(function(result) {
	        	  if (result.value) {
	            	acceptOrRejectStudent(document.getElementById('stId').value,'Accepted');

	        	  } else if (result.dismiss === Swal.DismissReason.cancel) {
	            	acceptOrRejectStudent(document.getElementById('stId').value,'Rejected');

	        	  } 
	        	  else{
	        		  swalWithBootstrapButtons.fire(
	    	        	      'Cancelled',
	    	        	      'You have Cancelled',
	    	        	      'error'
	    	        	    )	        	  }
	        	})
	    }
   function acceptOrRejectStudent(stId,type){
		  if(type.localeCompare("Accepted")===0){
			  acceptance='Accepted';
		  }
		  if(type.localeCompare("Rejected")===0){
			  acceptance='Rejected';
		  }
		  var userId = "${userId}";

		      $.ajax({
		        type : "POST",
		        url : "${pageContext.request.contextPath}/studt/acceptStudent?mentorId="+userId+"&studentId="+stId+"&acceptance="+acceptance,
		        async : false,
		        success : function(data) {
		        	openPopup1(data.response);
		        	},
		        error : function(e) {
		            alert('Error: ' + e);
		        }
		    }).done(function(data) {
		        console.log(data);
		    });  
		}
   function openPopup1(val){
       const swalWithBootstrapButtons = Swal.mixin({
       	 customClass: {
       		    confirmButton: 'btn con-subbtn'
       		  },
       	buttonsStyling: false,
       	allowOutsideClick: false
       	})

       	swalWithBootstrapButtons.fire({
       	  title: 'Status',
	      html:"<div>"+val+"</div>",
       	  confirmButtonText: 'OK',
       	  reverseButtons: true
       	}).then(function(result) {
       		if (result.value) {
                  location.reload();      
                }
       	})
   } 
   function openRejectPopup(val){
       const swalWithBootstrapButtons = Swal.mixin({
       	 customClass: {
       		    confirmButton: 'btn  con-subbtn'
       		  },
       	buttonsStyling: false,
       	allowOutsideClick: false
       	})

       	swalWithBootstrapButtons.fire({
       	  title: 'Please Connect With Other Mentors',
	      html:"<div>Your request cannot be processed with this mentor now.</div>",
       	  confirmButtonText: 'OK',
       	  reverseButtons: true
       	}).then(function(result) {
       		if (result.value) {
       			window.location.href="${pageContext.request.contextPath}/student/getAllMentors";
                }
       	})
   } 
</script>
	</div>