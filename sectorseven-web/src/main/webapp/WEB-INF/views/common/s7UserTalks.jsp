<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="mentor-page student-details">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12">
					<div class="activity-heading">
						<c:if test="${talkType== 'ROLE_STUDENT'}">
							<h2>Student Talks</h2>
						</c:if>
						<c:if test="${talkType=='ROLE_MENTOR'}">
							<h2>Mentor Talks</h2>
						</c:if>
						<c:if test="${talkType=='ROLE_SCHOOL_TEACHER'}">
							<h2>Teacher Talks</h2>
						</c:if>
						<c:if test="${talkType=='ROLE_PARENT'}">
							<h2>Parent Talks</h2>
						</c:if>
					</div>
				</div>
			</div>
				<div class="row" id="talksRow"><!--  --></div>
		</div>
	</section>
	
	<script type="text/javascript">
	//To get UserTsalks
	$("#talksRow").ready(function(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/gets7TalksList",
			data: {
				userType:"${talkType}"
			},
			success: function(data){
				console.log(data);
				if(Object.keys(data.response).length === 0){
					$('#talksRow').text("No Talks Available!!");
				}
				 $.each(data.response, function(index, s7Talks) {
					 var imagePath="${pageContext.request.contextPath}/studt/userImage?userId="+s7Talks.userId+"&userType=${talkType}&imgPath="+s7Talks.imgPath+"&imgName="+s7Talks.imgName;
					 var column=$('<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3"/>');
					 var bgsuccess= $('<div class="bgsuccess"/>');
					 var text_center= $('<div class="text-center"/>');
					 var image= $('<img/>').attr("src", imagePath);
					 var heshFour= $('<h4/>').append(s7Talks.name);
					 var subject= $('<span/>').append('<strong>Subject: </strong>');
					 var sub_value=$('<span/>').text(s7Talks.subject);
					 var pea= $('<p/>');
					 var student_viewmore_btn= $('<div class="student-viewmore-btn btn-view"/>');
					 var viewMoreForm= $('<form	action="${pageContext.request.contextPath}/common/s7TalksDetails" method="post"><input type="hidden" name="s7TalksUserId" value="'+s7Talks.userId+'"/><input type="hidden" name="s7TalksUserType" value="${talkType}"/><input type="hidden" name="mediaType" value="video/mp4"/><input type="hidden" name="latest" value="0"/><input type="hidden" name="limit" value="12"/><input type="hidden" name="offset" value="0"/></form>');
					 var button= $('<button type="submit" class="btn big-btn">Read More</button>');
					 $('#talksRow').append(column.append(bgsuccess.append(text_center.append(image).append(heshFour).append(subject).append(sub_value).append(pea).append(student_viewmore_btn.append(viewMoreForm.append(button))))));
	             });
			},
			error: function(){
				console.log("error occured");
			}
		});
	});
	</script>
</div>