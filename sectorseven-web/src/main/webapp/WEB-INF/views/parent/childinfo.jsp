<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<!--Menu End-->
	<div class="interest-sec child-information">
		<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<div class="activity-heading">
					<h2>Child Info</h2>
				</div>
			</div>
		</div>
			<div class="row">
				<input type="hidden" value="${stId}" name="stId" id="stId" /> <input
					type="hidden" value="${userId}" name="userId" id="userId" />

				<div class="col-sm-6 col-md-7 col-lg-7 col-xl-7">
					<div id="selectdiv"></div>
					<div class="row">
					<div class="col-md-12 col-sm-12">
						<div id="childFlag">
						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
								<div id="chartContainer" style="height: 300px; width: 100%;"></div>
							</div>
							</div>
						</div>
						<div id="msg" class="mtdata"></div>
						</div>

					</div>
					<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
					<div id="actFlag">
						<div class="interest-active">
							<div id="activi"></div>
						</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-5 col-lg-5 col-xl-5">
             	<div id="studFlag">
					<section id="tabs" class="project-tab">
						<div class="container">
							<div class="row">
								<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
									<nav>
										<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
											<a class="nav-item nav-link active" id="nav-home-tab"
												data-toggle="tab" href="#nav-home" role="tab"
												aria-controls="nav-home" aria-selected="true">Student</a> <a
												class="nav-item nav-link" id="nav-profile-tab"
												data-toggle="tab" href="#nav-profile" role="tab"
												aria-controls="nav-profile" aria-selected="false">Interest</a>

										</div>
									</nav>
									<div class="tab-content" id="nav-tabContent">
										<div class="tab-pane fade show active" id="nav-home"
											role="tabpanel" aria-labelledby="nav-home-tab">
											<div id="childDetail"></div>
										</div>
										<div class="tab-pane fade" id="nav-profile" role="tabpanel"
											aria-labelledby="nav-profile-tab">
											<div id="interests"></div>
										</div>
									</div>
								</div>
							</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
  $(document).ready(function() {
	  getMyChilds();
	    });
  function getNewVal(item){
	  getAllPieChartData(item.value);
  	  getMyInterests(item.value);
  	  getChildInfo(item.value);
  	  getActivity(item.value);
     
  }
  function getActivity(vall){
	  var activityHtml='<form action="${pageContext.request.contextPath}/common/activitylog" method="POST">'+
	    ' <input type="hidden" value="inner" name="scType" id="scType"/>'+
	     '  <input type="hidden" value="'+vall+'" name="stId" id="stId"/>'+
	      '   <button class="btn btn-info">Activity</button>'+
	    ' </form>';
		   document.getElementById('activi').innerHTML=activityHtml;
  }
  function getMyChilds(){
	  var userId = document.getElementById('userId').value;
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getChilds?&parentId="+userId,
          async : false,
          success : function(data) {
        	 var  htmlContent='<div class="row"><div class="col-sm-12 col-md-12 col-lg-12 col-xl-12"><select class="form-control custom-select" onchange="getNewVal(this);">';
   		   $.each(data.response, function(j, child) {
        	    htmlContent+='<option value="'+child.childId+'">'+child.name+'</option>';
   		   });
   		getAllPieChartData(data.response[0].childId);
     	 getMyInterests(data.response[0].childId);
     	  getChildInfo(data.response[0].childId);
      	  getActivity(data.response[0].childId);

   		htmlContent+='</select></div><div>';
   		$('#selectdiv').append(htmlContent);
	          },
          error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
  }
  function getAllPieChartData(childId){
	  var dataPoints = [];
	  var chart = new CanvasJS.Chart("chartContainer", {
	  	animationEnabled: true,
	  	title: {
	  		text: "Daily Activities"
	  	},
	  	data: [{
	  		type: "pie",
	  		startAngle: 240,
	        click: onClick,
	  		yValueFormatString: "##0.00\"%\"",
	  		indexLabel: "{label} - {y}",
	  		dataPoints: dataPoints
	  	}]
	  });
	  $.getJSON("${pageContext.request.contextPath}/studt/getPieChartSubcategories?userId="+childId+"&userType=ROLE_STUDENT", function(data) {  
		  if(data.response.length==0){
  	        $("#childFlag").css("display", "none");
  	        $("#actFlag").css("display", "none");
	        $("#studFlag").css("display", "none");
  	        document.getElementById('msg').innerHTML="This User Still does not Logged In";
		  }
		  else{
			  $("#msg").css("display", "none");
  	        $("#childFlag").css("display", "block");
  	        $("#actFlag").css("display", "block");
	        $("#studFlag").css("display", "block");
		  $.each(data.response, function(key, value){
		        dataPoints.push({label: value.name, y: value.data,subCatId:value.id,stId:childId,scrType:'inner'});
		    });
		    chart.render();
		  }
		});
	  }
  function onClick(e) {
	  //  window.open(e.dataPoint.link,'_blank');
	  var htmlContent='<form id="percentageForm" action="${pageContext.request.contextPath}/common/percentages" method="POST">'+
      '<input type="hidden" value="'+e.dataPoint.subCatId+'" name="subCatId" id="subCatId"/>'+
       ' <input type="hidden" value="'+e.dataPoint.scrType+'" name="scrType" id="scrType"/>'+
       ' <input type="hidden" value="'+e.dataPoint.stId+'" name="stId" id="stId"/>'+
       '   <input type="hidden" value="inner" name="subScrType" id="subScrType"/>' +
        '  <button class="btn btn-info">Activity</button>'+
     ' </form>';
     
     document.body.innerHTML=htmlContent;
     
	 document.getElementById("percentageForm").submit();
	  };
  function getMyInterests(childId){
	  var stId = document.getElementById('stId').value;
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getInitialInterests?&userId="+childId+"&userType=ROLE_STUDENT",
          async : false,
          success : function(data) {
        	  var htmlContt='<div class="row">';
    		   $.each(data.response, function(j, careers) {
    		       htmlContt+='<div class="col-sm-6 col-md-6 col-lg-6"><div class="bgsuccess interest"><div class="inreset-img icon"><img src="${pageContext.request.contextPath}/studt/iconImage?iconId='+careers.id+'&iconScreen=category&imgPath='+careers.categoryImagPath+'&imgName='+careers.categoryImagName+'" alt=""></div><h4>'+careers.categoryName+'</h4></div></div>';
    		   });  
    		   htmlContt+='</div>';
    		   document.getElementById('interests').innerHTML=htmlContt
	          },
          error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
  }
  function getChildInfo(childId){
	  var stId = document.getElementById('stId').value;
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/studentProfileForAcceptAndReject?&userId="+childId+"&userType=ROLE_STUDENT",
          async : false,
          success : function(data) {
        	  var htmlContent='<table class="table table-responsive" cellspacing="0"><tbody>';
        	  htmlContent+='<tr><td>Name</td><td>'+data.response.name+'</td></tr><tr><td>Class</td><td>'+data.response.class+'</td></tr></tbody></table>';
        	  //$('#childDetail').append(htmlContent);
   		   document.getElementById('childDetail').innerHTML=htmlContent;
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