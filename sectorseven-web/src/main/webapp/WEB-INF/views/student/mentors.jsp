<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<section class="mentor-page">
		<div class="container-fluid">
		<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12">
								<div class="activity-heading">
									<h2>Mentors</h2>
								</div>
							</div>
						</div>
			<div class="row">
				<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
					<div class="form-group has-search">
						<span class="fa fa-search form-control-feedback">
							<!--  -->
						</span> 
						<input type="text" class="form-control form-rounded"
							placeholder="Search mentors" id="mentorName" onkeyup="getMentors()" />
					</div>
				</div>
			</div>
			<div id="mentordiv"></div>
		</div>
	</section>
	<script type="text/javascript">
  var limit = 10;
  var offset = 0;
  $(document).ready(function() {
	  getMentors();
	  function moreMentors(){
		  limit=limit+10;
		  offset=offset+10;
		  getMentors();
	  }
  });
  
   function getMentors(){
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getAllList",
          async : false,
          data:{
        	  limit:limit,
        	  offset:offset,
        	  userId:"${userId}",
        	  userType:"${userType}",
        	  type:"mentors"
          },
          success : function(data) {
              var htmlCont ='<div class="row" >';
        	  if(data.response.length === 0){
        		  htmlCont+='No data!!';
				}
        	  $.each(data.response, function(i, mentor) {
					 var input = document.getElementById("mentorName");
					 var filter = input.value.toUpperCase();
					 if (mentor.name.toUpperCase().indexOf(filter) > -1) {
						 htmlCont+='<div class="col-sm-6 col-md-6 col-lg-3 col-xl-3">'+
						 '<div class="bgsuccess"><div class="text-center">'+
						 '<img src="${pageContext.request.contextPath}/studt/userImage?userId='+mentor.id+'&userType=ROLE_MENTOR&imgPath='+mentor.imgPath+'&imgName='+mentor.imgName+'"/>'+
						 '<h4>'+mentor.name+'</h4><p>'+mentor.expertize+'</p>'+
						 '<div class="mentor-viewmore-btn btn-view">'+
                          '<form action="${pageContext.request.contextPath}/student/mentorDetails" method="POST">'+
                        ' <input type="hidden" value="'+mentor.id+'" name="mentorId" id="mentorId"/>'+
                         '<button class="btn btn big-btn">Read More</button>'+
                       '</form>'+
                       '</div></div></div></div>';
					 }
              });
                htmlCont+='</div>';
                if(data.response.length>8){
                	htmlCont+='<div class="loadmore-btn"><button onclick="moreMentors()" class="btn btn-primary con-subbtn">LoadMore</button></div>';
              	 } 
              document.getElementById('mentordiv').innerHTML=htmlCont;        	  
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
