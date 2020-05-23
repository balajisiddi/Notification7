 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
 <div id="tabs">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 col-lg-12 col-xl-12">
                    <div class="trending-careers">
                        <div class="container">
                            <ul class="row" role="tablist">
                                <li class="col-sm-4 col-md-6 col-lg-6 col-xl-6">
                                    <a href="#tabs-1">Trending</a>
                                </li>
                                <li class="col-sm-4 col-md-6 col-lg-6 col-xl-6">
                                    <a href="#tabs-2">Subscribe</a>
                                </li>
                            </ul>
                        </div>
                        <div class="container">

                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <div class="container">
                                    
                                    <div  id="trendingdiv"></div>
                                </div>
                            </div>
                            <div class="subscribe-sec tab-pane" id="tabs-2">
                                <div class="container">
                                        <div id="subscribediv"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $("#tabs").tabs({ selected: 1 });
            getAllTrendingCareers();
            getAllSubscribedCareers();
        });
        function getAllTrendingCareers(){
           $.ajax({
               type : "GET",
               url : "${pageContext.request.contextPath}/studt/getAllTrendingCareers",
               async : false,
               success : function(data) {
                   if(data.response.length==0){
                       document.getElementById('trendingdiv').innerHTML='<div class="mtdata">No Data!!</div>';
                   }
                   else{
                		var  htmlContent='<div class="row">';
                        htmlContent+='<div class="col-md-6 col-lg-6 col-xl-6">';
             	  $.each(data.response, function(i, career) {
             	  htmlContent+='<div class="row"><div class="col-md-12 col-lg-12 col-xl-12">'+
             		 ' <form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
                     ' <input type="hidden" value="'+career.id+'" name="subCatId" id="subCatId"/>'+
                        '  <button class="btn btn-info2"><div class="card">'+
             	  '<div class="card-horizontal"><div class="img-square-wrapper icon">'+
             	  '<img class="" src="${pageContext.request.contextPath}/studt/iconImage?iconId='+career.id+'&iconScreen=subCategory&imgPath='+career.imgPath+'&imgName='+career.imgName+'"></div>'+
             	  '<div class="card-body"><h4 class="card-title">'+career.name+
             	  '</h4></div></div></div></button></form></div></div>';
             	  });
             	 htmlContent+='</div><div class="col-md-6 col-lg-6 col-xl-6"><div class="trending-img"><img src="../resources/images/subscribe.png" alt="trending-img" /></div></div></div>';
                // $("#trendingdiv").append(htmlContent);
                document.getElementById('trendingdiv').innerHTML=htmlContent;
                   }
               },
               error : function(e) {
                   alert('Error: ' + e);
               }
           }).done(function(data) {
               console.log(data);
           });
       }
        function getAllSubscribedCareers(){
            $.ajax({
                type : "GET",
                url : "${pageContext.request.contextPath}/studt/getAllSubscribeCareers",
                async : false,
                data:{
                	userId:"${userId}",
                	userType:"${userType}"
                },
                success : function(data) {
                   if(data.response.length==0){
                       document.getElementById('subscribediv').innerHTML='<div class="mtdata">No Data!!</div>!!';
                   }
                   else{
                	    var htmlContent='<div class="row"><div class="col-md-6 col-lg-6 col-xl-6"><div class="trending-img"><img src="../resources/images/trending.png" alt="trending-img" /></div></div>';
                        htmlContent+='<div class="col-md-6 col-lg-6 col-xl-6">';
              	  $.each(data.response, function(i, career) {
              	  htmlContent+='<div class="row"><div class="col-md-12 col-lg-12 col-xl-12"><div class="card">'+
              	  '<div class="card-horizontal"><div class="img-square-wrapper icon">'+
              	  '<img class="" src="${pageContext.request.contextPath}/studt/iconImage?iconId='+career.id+'&iconScreen=subCategory&imgPath='+career.imgPath+'&imgName='+career.imgName+'"></div>'+
              	  '<div class="card-body"><h4 class="card-title">'+career.name+
              	  '</h4> <div class="radius-icon"><button class="btn btn-xs btn-primary id="unSubscribe" onClick="subscribeCareer('+career.id+')" ">UnSubscribe</button></div></div></div></div></div></div>';
              	  });
              	 htmlContent+='</div>';
              //   $("#subscribediv").append(htmlContent);
                 document.getElementById('subscribediv').innerHTML=htmlContent;

                   }

                },
                error : function(e) {
                    alert('Error: ' + e);
                }
            }).done(function(data) {
                console.log(data);
            });
        }
        function subscribeCareer(subCatId){
            var subscribe='UnSubscribed';
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/studt/subscribeCareer",
                async : false,
                data:{
                	userId:"${userId}",
                	userType:"${userType}",
                	subCatId:subCatId,
                	subscribe:subscribe
                },
                success : function(data) {
                	//location.reload();
                	openPopup();
                  },
                error : function(e) {
                    alert('Error: ' + e);
                }
            }).done(function(data) {
                console.log(data);
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
     	      html:"<div>You have successfully UnSubscribed!!</div>",
            	  confirmButtonText: 'OK',
            	  reverseButtons: true
            	}).then(function(result) {
           		if (result.value) {
           			getAllSubscribedCareers();      
                  }
         	})
        }
    </script>
    </div>