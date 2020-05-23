<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="student-list">
		<div class="list img-list container-fluid">
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<div class="activity-heading">
						<h2>Student-List</h2>
					</div>
				</div>
			</div>
			<div id="assigndiv"></div>
		</div>
	</section>
<script type="text/javascript">
var limit=10;
var offset=0;
$(document).ready(function(){
	  getAllStudentsUnderTeacher();
});
function getAllStudentsUnderTeacher(){
    $.ajax({
        type : "GET",
        url : "${pageContext.request.contextPath}/studt/getAllList",
        async : false,
        data:{
        	userId:"${userId}",
        	userType:"${userType}",
        	type:"teacherStudents",
        	limit:limit,
        	offset:offset
        },
        success : function(data) {
      	  var table =$('<div class="row"/>').appendTo($('#assigndiv'));
      	  if(data.response.length==0){
      		table.append("<div class='mtdata'>Still no students were assigned</div>"); 
      	  }
      	  $.each(data.response, function(i, student) {
        		var tab=$('<div class="col-sm-6 col-lg-4 col-xl-4">').appendTo(table);
        		tab.append($('<div class="bgsuccess">'+
				'<div class="row">'+
        '<div class="col-sm-4 col-lg-4 col-xl-4">'+
						'<div class="how-img">'+
							'<img src="${pageContext.request.contextPath}/studt/getImageUrl?type=teacherStudents&userId='+student.id+'&imgPath='+student.imgPath+'&imgName='+student.imgName+'" class="rounded-circle img-fluid" alt="" ></img>'+
						'</div>'+
					'</div>'+
					'<div class="col-sm-8 col-lg-8 col-xl-8">'+
						'<div class="student-details">'+
							'<h5>'+student.name+'</h5>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="row">'+
					'<div class="library-viewmore-btn btn-view">'+
					'<form action="${pageContext.request.contextPath}/common/studentDetails" method="POST">'+
	                        ' <input type="hidden" value="'+student.id+'" name="stId" id="stId"/>'+
	                         '<button class="btn btn big-btn">View More</button></form>'+
					'</div>'+
				'</div>'+
			'</div>'+
		'</div>'));
      	         });
      	  
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