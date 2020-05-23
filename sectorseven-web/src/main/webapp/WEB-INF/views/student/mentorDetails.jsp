<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<div class="main">
      <section class="mentor-bg mentor-profile-page">
      <div id="mentor"></div>
    </section>
  </div>
  <script type="text/javascript">
  $(document).ready(function() {
	  getMentorDetails();
	 
  });
  function getMentorDetails(){
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/studt/mentorDetails",
			data:{
				mentorId:"${mentorId}"
			},
			success: function(data){
				 getRequestEnable();
				var htmlConn='<div class="container-fluid">'+
	            '<div class="row">'+
	             '   <div class="col-sm-12 col-lg-10 student-view">'+
	              '  <div class="row">'+
					'			<div class="col-sm-12 col-md-12 col-lg-12">'+
					'				<div class="activity-heading">'+
					'					<h2>Mentor Details</h2>'+
					'				</div>'+
					'			</div>'+
					'		</div>'+
	                 '   <div class="bgsuccess cbe">'+
	                  '      <div class="row">'+
	                   '         <div class="col-sm-6 col-lg-4">'+
	                    '            <div class="metor-img">'+
	                     '               <img src="${pageContext.request.contextPath}/studt/userImage?userId='+data.response.id+'&userType='+data.response.authority+'&imgPath='+data.response.imgPath+'&imgName='+data.response.imgName+'" alt=""/>'+
	                      '          </div>'+
	                       '     </div>'+
	                        '    <div class="col-sm-6 col-lg-8">'+
	                         '       <div class="about-mentor">'+
	                          '          <h3>'+data.response.firstName+''+data.response.lastName+'</h3>'+
	                           '         <h6>'+data.response.occupation+'</h6>'+
	                            '        <h3>About</h3>'+
	                             '       <p>'+data.response.description+'</p>'+
	                              '  </div>'+
	                           ' </div>'+
	                        '</div>'+
	                    '</div>'+
	               ' </div>'+
	           ' </div>'+
	            '<div class="row social-buttons">'+
	            '    <div class="col-sm-6 col-lg-6">'+
	             '       <div class="icons">'+
	              '          <ul>'+
	               '             <li>'+
	                '                <a href="'+data.response.linkedUrl+'" target="_blank">'+
	                 '                   <i class="fa fa-linkedin-square" style="color:#0e76a8"><!--  --></i>'+
	                  '              </a>'+
	                   '         </li>'+
	                    '        <li>'+
	                     '           <a href="'+data.response.instagramUrl+'" target="_blank">'+
	                      '              <i class="fa fa-instagram" style="color:#3f729b"><!--  --></i>'+
	                       '         </a>'+
	                        '    </li>'+
	                         '   <li>'+
	                          '      <a href="'+data.response.twitterUrl+'" target="_blank">'+
	                           '         <i class="fa fa-twitter-square" style="color: #00acee"><!--  --></i>'+
	                            '    </a>'+
	                           ' </li>'+
	                        '</ul>'+
	                    '</div>'+
	                '</div>'+
	               ' <div class="col-sm-6 col-lg-6">'+
	                '    <div class="button-group">'+
	                 '<div  id="req"></div>'+
	                   ' </div>'+
	                '</div>'+
	           ' </div>'+
	        '</div>';
	              document.getElementById('mentor').innerHTML=htmlConn;      	  

			},
			error: function(){
				console.log("error occured");
			}
		});
	
}
   function getRequestEnable(){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/studt/getRequestEnable",
			data:{
				mentorId:"${mentorId}",
				studentId:"${userId}"
			},
			success: function(data){
				var html='<div class="botton">';
				if(data.response.request=='Submitted'){
                	  html+='<button disabled class="con-subbtn">Submitted</button>';
                	  
                  }
				if(data.response.request=='Accepted'){
              	  html+='<button disabled class=" con-subbtn">Following</button>';
              	  
                }
				if(data.response.request=='Rejected'){
	              	  html+='<button class="con-subbtn" onclick="openPopup1();">SeeStatus</button>';
	              	  
	                }
				if(data.response.request=='ReqNotRaised'){
	              	  html+='<button class="con-subbtn" onclick="requestToMentor()">Request</button>';
	              	  
	                }
                  html+= ' </div>';
	              document.getElementById('req').innerHTML=html;      	  

			},
			error: function(){
				console.log("error occured");
			}
		});
	
  }
   function requestToMentor(){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/studt/requestMentor",
			data:{
				studentId:"${userId}",
				mentorId:"${mentorId}"
			},
			success: function(data){
				openPopup(data.response);
			},
			error: function(){
				console.log("error occured");
			}
		});
	
 }
    function openPopup(val){
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
       		getRequestEnable();
       	})
   } 
   function openPopup1(){
       const swalWithBootstrapButtons = Swal.mixin({
       	 customClass: {
       		    confirmButton: 'btn con-subbtn'
       		  },
       	buttonsStyling: false,
       	allowOutsideClick: false
       	})

       	swalWithBootstrapButtons.fire({
       	  title: 'Please Connect With Other Mentors',
	      html:"<div>Your Request Cannot be processed with this mentor now</div>",
       	  confirmButtonText: 'OK',
       	  reverseButtons: true
       	}).then(function(result) {
       	  
       	})
   }
  </script>
</div>