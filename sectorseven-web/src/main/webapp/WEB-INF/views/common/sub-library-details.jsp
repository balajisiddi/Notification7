<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">

	<section class="library-main init">
		<div class="container-fluid">
			<div class="col-sm-12 col-lg-12">
				<div id="back"></div>
			</div>
		</div>
		<div class="container library-blogs">
			<c:if test="${not empty msg && msg=='Subscribed'}">
				<div class="row">
					<div class="col-sm-12 col-lg-12">
						<div class="alert alert-success">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Success!</strong> ${msg}
						</div>
					</div>
				</div>
			</c:if>

			<c:if test="${not empty msg && msg=='UnSubscribed'}">
				<div class="row">
					<div class="col-sm-12 col-lg-12">
						<div class="alert alert-danger">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							<strong>Success!</strong> ${msg}
						</div>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<div class="heading">
						<h2>${careeSubCat.subCategoryName}</h2>
						<span class="quotee"></span>
					</div>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-sm-12 col-lg-4">
					<div class="left-img">
						<img
							src=""
							alt="library-img" id="landingImage" />
					</div>
				</div>
				<div class="col-sm-12 col-lg-8">
					<div class="right-blogs">
						<div class="row mb-2">
							<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4 mt-2">
								<form
									action="${pageContext.request.contextPath}/common/demandAndSupply"
									method="POST">
									<input type="hidden" value="${subCatId}" name="subCatId"
										id="subCatId" />
									<div class="card shadow">
										<div class="card-body">
											<button class="btn btn-info2">
												<h5>Demand & Supply</h5>
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4 mt-2">
								<form
									action="${pageContext.request.contextPath}/common/amITheOne"
									method="POST">
									<input type="hidden" value="${subCatId}" name="subCatId"
										id="subCatId" />
									<div class="card shadow">
										<div class="card-body">
											<button class="btn btn-info2">
												<h5>Am I The One</h5>
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4 mt-2">
								<form
									action="${pageContext.request.contextPath}/common/skilAndRes"
									method="POST">
									<input type="hidden" value="${subCatId}" name="subCatId"
										id="subCatId" />
									<div class="card shadow">
										<div class="card-body">
											<button class="btn btn-info2">
												<h5>Skills&Responsibilities</h5>
											</button>
										</div>
									</div>
								</form>
							</div>

						</div>
						<div class="row mb-2">
							<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4 mt-2">
								<form class="form-horizontal needs-validation" method="POST"
									novalidate="true" role="form"
									action="${pageContext.request.contextPath}/common/media">
									<input type="hidden" name="subcatId" value="${subCatId}" />
									<button type="submit" class="text-dark">
										<div class="card shadow">
											<div class="card-body">
												<h5>Media</h5>
											</div>
										</div>
										</a>
								</form>
							</div>
							<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4 mt-2">
								<form action="${pageContext.request.contextPath}/common/HGT"
									method="POST">
									<input type="hidden" value="${subCatId}" name="subCatId"
										id="subCatId" />
									<div class="card shadow">
										<div class="card-body">
											<button class="btn btn-info2">
												<h5>How To Get There</h5>
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-sm-6 col-md-4 col-lg-4 col-xl-4 mt-2">
								<div id="subscribeDiv"></div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="row mt-4 mb-4">
				<div class="col-md-12 ">
					<div id="desc"></div>
				</div>
			</div>
		</div>
	</section>
</div>
<script>
        $(document).ready(function () {
        	getdetails();
        	getSubscribeOrNot();
            });
        function getdetails(){
        	$.ajax({
    			type:"GET",
    			url:"${pageContext.request.contextPath}/studt/getCareerMain",
    			data: {
    				subcategoryId:"${subCatId}",
    			},
    			success: function(data){
    				var landingSrc="${pageContext.request.contextPath}/studt/getCareerMainImage?subcategoryId="+data.response.id+"&imgPath=/landingImages/"+data.response.id+"&imgName="+data.response.imgName+"";
    				$('.quotee').append('<p>'+data.response.quote+'</p>');
    				$('#landingImage').attr("src", landingSrc);
    				document.getElementById("desc").innerHTML = '<span class="show-read-more more">'+data.response.description+'</span>';
    				getTotalText();

    			},
    			error: function(){
    				console.log("error occured");
    			}
    	        });
        }
        
        function getTotalText(){
   		 $('.show-read-more').ready(function() {
	        	 var maxLength = 300;
	 	    	 var myStr = $('#desc').children('.show-read-more').text();
	 	        if($.trim(myStr).length > maxLength){
	 	        var newStr = myStr.substring(0, maxLength);
	 	        var removedStr = myStr.substring(maxLength, $.trim(myStr).length);
	 	        $('#desc').children('.show-read-more').empty().html(newStr);
	 	        $('#desc').children('.show-read-more').append(' <a href="javascript:void(0);" class="read-more">read more...</a>');
	 	        $('#desc').children('.show-read-more').append('<span class="more-text">' + removedStr + '</span>');
	 	        }
	 	    $(".read-more").click(function(){
	 	        $(this).siblings(".more-text").contents().unwrap();
	 	        $(this).remove();
	 	    });
	        });  
   	 }
        function getSubscribeOrNot(){
    		$.ajax({
    			type:"GET",
    			url:"${pageContext.request.contextPath}/studt/getSubscribeOrNot",
    			data:{
    				userId:"${userId}",
    				userType:"${userType}",
    				subCatId:"${subCatId}"
    			},
    			success: function(data){
    				var html='<div class="subscrib-btn">';
    				if(!data.response.subscribed){
                  	  html+='<button  class="btn-primary con-subbtn" onclick="subscribecareer()">Subscribe</button>';
                  	  
                    }
    			  html+='</div>';
                 document.getElementById('subscribeDiv').innerHTML=html;      	  

    			},
    			error: function(){
    				console.log("error occured");
    			}
    		});
    	
      }
        function subscribecareer(){
    		$.ajax({
    			type:"POST",
    			url:"${pageContext.request.contextPath}/studt/subscribeCareer",
    			data:{
    				userId:"${userId}",
    				userType:"${userType}",
    				subCatId:"${subCatId}",
    				subscribe:"Subscribed"
    			},
    			success: function(data){
    				openPopup();
//                 	location.reload();
					$("#subscribediv").empty();
					//getAllSubscribedCareers();
					//getSubscribeOrNot();
    			},
    			error: function(){
    				console.log("error occured");
    			}
    		});
    	
      }
        function openPopup(){
            const swalWithBootstrapButtons = Swal.mixin({
            	 customClass: {
            		    confirmButton: 'btn btn-primary con-subbtn'
            		  },
            	buttonsStyling: false,
            	allowOutsideClick: false
            	})
            	swalWithBootstrapButtons.fire({
            	  title: 'Success!',
     	      html:"<div>You have successfully Subscribed!!</div>",
            	  confirmButtonText: 'OK',
            	  reverseButtons: true
            	}).then(function(result){
            		getSubscribeOrNot();
            	})
        }
        document.getElementById('back').innerHTML='<form action="${pageContext.request.contextPath}/common/subCategories" method="POST">'+
        ' <input type="hidden" value="${careeSubCat.careerCategory.id}" name="catId" id="catId"/>'+
        '  <button class="btn btn-info2">'+
         '<div class="round">'+
          '   <i class="fa fa-arrow-left" aria-hidden="true"></i>'+
         '</div>'+
    ' </button></form>';
    </script>

