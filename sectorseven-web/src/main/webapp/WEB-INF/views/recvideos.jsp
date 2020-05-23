<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mg-content" id="mg-content"
xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">

   <!--events page card design -->
  <section class="events">
    <div class="container-fluid">
      <div class="row events-row">
        <div class="col-sm-12 col-md-8 col-lg-8 col-xl-8">
          <div class="chartbg">
            <div id="chartContainer" class="btn" style="height: 300px; width: 100%;"></div>
          </div>
        </div>
        <div class="col-sm-12 col-md-4 col-lg-4 col-xl-4">
          <div class="events-card home">
            <form action="${pageContext.request.contextPath}/common/getEvents" method="POST">
             <button>
              <div class="card">
                <div class="card-body">
                  <div class="event icon">
                    <img src="../resources/images/events.png" alt="events" />
                  </div>
                  <div class="content">
                    <h4>Events</h4>
                  </div>
                </div>
              </div>
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
    </div>
  </section>
  <!-- End events page card design -->
<!--  main Section --> 
 <section class="main-sction recommended">
           <div id="recommdiv"></div>
  </section>

<section class="main-sction">
    
            <div id="trendingdiv"></div>
    
  </section>
 </div>
  <script type="text/javascript">
  $(document).ready(function() {
	  getAllPieChartData();
	  getHomeRecVideos();
	  getAllTrendingCareers();
  });
  function getAllPieChartData(){
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
	  $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getPieChartSubcategories",
          async : false,
          data:{
        	  userId:"${userId}",
        	  userType:"${userType}"
          },
          success : function(data) {
        	  $.each(data.response, function(key, value){
  		        dataPoints.push({label: value.name, y: value.data,scrType:'recomm',subCatId:+value.id});
  		    });
  		    chart.render();
          },
          error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
		  }

  function onClick(e) {
	  var htmlContent='<form id="percentageForm" action="${pageContext.request.contextPath}/common/percentages" method="POST">'+
      '<input type="hidden" value="'+e.dataPoint.scrType+'" name="scrType" id="scrType"/>'+
       ' <input type="hidden" value="'+e.dataPoint.subCatId+'" name="subCatId" id="subCatId"/>'+
       ' <input type="hidden" value="'+e.dataPoint.label+'" name="subCatName" id="subCatName"/>'+
        '  <button class="btn btn-info">Activity</button>'+
     ' </form>';
     
     document.body.innerHTML=htmlContent;
     
	 document.getElementById("percentageForm").submit();
	 
  };
  function getHomeRecVideos(){
	  var screenType='recomm';
	  var scrType='recomm';
	  var subScrType='main';
      $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/recommendedVideos",
          async : false,
          data:{
        	  userId:"${userId}",
        	  userType:"${userType}"
          },
          success : function(data) {
        	  if(data.response.length==0){
            	  document.getElementById('recommdiv').innerHTML='<div>No Data!!</div>';
        	  }
        	  if(data.response.length>0){
        	 var htmlCont='<div class="container-fluid ">'+
        		 '<div class="row">'+
             '<div class="col-sm-6 col-lg-6">'+
                '<div class="heading float-left">'+
                  '<h2>Recommended Videos</h2></div></div>'+
              '<div class="col-sm-6 col-lg-6">'+
                '<div class="more-btn mt-3 mb-3 float-right">'+
                  '<form action="${pageContext.request.contextPath}/common/videos" method="POST">'+
                                  '<input type="hidden" value="recomm" name="screenType" id="screenType"/>'+
                                  '<input type="hidden" value="recomm" name="scrType" id="scrType"/>'+
                                  '<input type="hidden" value="main" name="subScrType" id="subScrType"/>'+
                                  '<button class="btn ">'+
                                  '<div class="round">'+
                                  '<i class="fa fa-angle-right" aria-hidden="true"><!--  --></i>'+
                                  '</div>'+
                                  '</button>'+
                                  '</form>'+
                 '</div></div></div><div class="row">';
        	  $.each(data.response, function(i, rec) {
        		  htmlCont+='<div class="col-xl-2 col-lg-2 col-md-6 col-sm-12 col-12">';
              	   if(rec.videoType=='Private'){
              		 htmlCont+='<div class="card"><div class="card-body">'+
                           '  <form action="${pageContext.request.contextPath}/common/privateVORP" method="POST">'+
                            ' <input type="hidden" value="'+rec.mediaId+'" name="mediaId" id="mediaId"/>'+
                            ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                            ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                            ' <input type="hidden" value="'+subScrType+'" name="subScrType" id="subScrType"/>'+
                            ' <input type="hidden" value="Video" name="action" id="action"/>'+
                            ' <input type="hidden" value="'+rec.videoType+'" name="videoType" id="videoType"/>'+
                            ' <input type="hidden" value="" name="youtubeUrl" id="youtubeUrl"/>'+
                            ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                            ' <input type="hidden" value="'+rec.subCatId+'" name="subCatId" id="subCatId"/>'+
                             '<div class="video-popup"><img class="img-responsive" src="'+rec.thumbnaiUrl+'"/></div></form>'+
              				'</div></div>';
              	   }
              	 else if(rec.videoType=='Youtube')
             	   {
              		htmlCont+='<div class="card"><div class="card-body">'+
              				'  <form action="${pageContext.request.contextPath}/common/playVideo" method="POST">'+
                            ' <input type="hidden" value="'+rec.mediaId+'" name="mediaId" id="mediaId"/>'+
                            ' <input type="hidden" value="'+screenType+'" name="screenType" id="screenType"/>'+
                            ' <input type="hidden" value="'+scrType+'" name="scrType" id="scrType"/>'+
                            ' <input type="hidden" value="'+subScrType+'" name="subScrType" id="subScrType"/>'+
                            ' <input type="hidden" value="Video" name="action" id="action"/>'+
                            ' <input type="hidden" value="'+rec.videoType+'" name="videoType" id="videoType"/>'+
                            ' <input type="hidden" value="'+rec.youtubeUrl+'" name="youtubeUrl" id="youtubeUrl"/>'+
                            ' <input type="hidden" value="video/mp4" name="mediaType" id="mediaType"/>'+
                            ' <input type="hidden" value="'+rec.subCatId+'" name="subCatId" id="subCatId"/>'+
                             '<button class="btn"><div class="video-popup">'+
                             '<img class="img-responsive" src="'+rec.thumbnaiUrl+'"/></div><button></form></div></div>';

             	   }
              	 htmlCont+='</div>';
        	  });
        	  htmlCont+='</div></div>';
        	  document.getElementById('recommdiv').innerHTML=htmlCont;
        	  }
        	  
             // alert('Success: ' + data);
          },
          error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
  }
  function getAllTrendingCareers(){
      $.ajax({
          type : "GET",
          url : "${pageContext.request.contextPath}/studt/getAllTrendingCareers",
          async : false,
          success : function(data) {
        	  if(data.response.length==0){
            	  document.getElementById('trendingdiv').innerHTML='';

        	  }
        	  if(data.response.length>0){
        	 var htmlContt='<div class="container-fluid ">'+
              '<div class="row">'+
                '<div class="col-sm-6 col-lg-6">'+
                  '<div class="heading">'+
                    '<h2>Trending Careers</h2></div></div>'+
                '<div class="col-sm-6 col-lg-6">'+
                 ' <div class="more-btn mt-3 mb-3 float-right">'+
                    '<a href="${pageContext.request.contextPath}/common/trending" class="previous">'+
                      '<div class="round">'+
                      '<i class="fa fa-angle-right" aria-hidden="true"><!--  --></i>'+
                    '</div></a></div></div></div>'+
              '<div class="row trending-career-sec">';
        	  $.each(data.response, function(i, career) {
        		  if(i<6){
        			  htmlContt+='<div class="col-xl-2 col-lg-2 col-md-6 col-sm-12"><div class="card trending-card"><div class="card-body">'+
                		  ' <form action="${pageContext.request.contextPath}/common/subCategory" method="POST">'+
                          ' <input type="hidden" value="'+career.id+'" name="subCatId" id="subCatId"/>'+
                             '  <button class="btn btn-info2">'+
                             ' <div class="biology icon">' +
                             ' <img src="${pageContext.request.contextPath}/studt/iconImage?iconId='+career.id+'&iconScreen=subCategory&imgPath='+career.subcategoryImgPath+'&imgName='+career.subcategoryImgName+'" alt="award" /> ' +
                             ' </div> <div class=""><h4>'+career.name+'</h4></div></button></form></div></div></div>';
        		  }
        		  });
        	  htmlContt+='</div>'
        	  document.getElementById('trendingdiv').innerHTML=htmlContt;

        	  }
          },
          error : function(e) {
              alert('Error: ' + e);
          }
      }).done(function(data) {
          console.log(data);
      });
  }
  </script>