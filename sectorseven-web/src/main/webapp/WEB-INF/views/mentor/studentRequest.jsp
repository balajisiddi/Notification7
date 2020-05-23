<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">


	<div class="request-page">
		<div class="activity-heading">
			<h2>Request Page</h2>
		</div>
		<!-- partial:index.partial.html -->
		<input type="hidden" value="${userId}" name="userId" id="userId" /> <input
			type="hidden" value="${userType}" name="userType" id="userType" />
		<div id="reqstudentdiv"></div>
		<!-- partial -->
	</div>
	<script type="text/javascript">
  var limit=10;
  var offset=0;
  $(document).ready(function(){
	  getAllStudentsRequests();
  });
  var userId = document.getElementById('userId').value;
  var userType = document.getElementById('userType').value;
  function getAllStudentsRequests(){
	    $.ajax({
	        type : "GET",
	        url : "${pageContext.request.contextPath}/studt/getAllList?type=reqStudents&userId="+userId+"&userType="+userType+"&limit="+limit+"&offset="+offset,
	        async : false,
	        success : function(data) {
	      	  var table =$('<ol class="activity-stream"/>').appendTo($('#reqstudentdiv'));
	      	if(data.response.length==0){
            	table.append('No Data');
            }
	      	  $.each(data.response, function(i, student) {
	      		var tab=$('<li/>').appendTo(table);
	      		tab.append($('<img src="${pageContext.request.contextPath}/studt/getImageUrl?type=askStudent&userId='+student.id+'&imgPath='+student.imgPath+'&imgName='+student.imgName+'" class="icon">'+
	      			'	<p>'+student.name+'</p>'+
	      		  '      <button type="button" name="button"  value="Accepted"  onclick="acceptOrRejectStudent('+student.id+',this.value)" class="left-btn">Accept</button>'+
	      		 '       <button type="button" name="button"  value="Rejected"  onclick="acceptOrRejectStudent('+student.id+',this.value)" class="right-btn">Decline</button>'));
	                 });
	      	  
	        },
	        error : function(e) {
	            alert('Error: ' + e);
	        }
	    }).done(function(data) {
	        console.log(data);
	    });
	}
  function openPopup(val){
      const swalWithBootstrapButtons = Swal.mixin({
      	 customClass: {
      		    confirmButton: 'btn btn-primary con-subbtn'
      		  },
      	buttonsStyling: false,
      	allowOutsideClick: false
      	})

      	swalWithBootstrapButtons.fire({
      	  title:"Status",	
	      html:"<div>"+val+"</div>",
      	  confirmButtonText: 'OK',
      	  reverseButtons: true
      	}).then(function(result) {
      		if (result.value) {
      			location.href = "${pageContext.request.contextPath}/common/studentFootPrints?screenType=reqStudents";
               }
      	})
  } 
  function acceptOrRejectStudent(stId,type){
	  if(type.localeCompare("Accepted")===0){
		  acceptance='Accepted';
	  }
	  if(type.localeCompare("Rejected")===0){
		  acceptance='Rejected';
	  }
	      $.ajax({
	        type : "POST",
	        url : "${pageContext.request.contextPath}/studt/acceptStudent?mentorId="+userId+"&studentId="+stId+"&acceptance="+acceptance,
	        async : false,
	        success : function(data) {
	        	openPopup("Accepted Successfully");
	      	  //location.reload();
// 		         location.href = "${pageContext.request.contextPath}/common/studentFootPrints?screenType=reqStudents";
	      	  },
	        error : function(e) {
	            alert('Error: ' + e);
	        }
	    }).done(function(data) {
	        console.log(data);
	    });  
	}
	</script>
	</script>
</div>