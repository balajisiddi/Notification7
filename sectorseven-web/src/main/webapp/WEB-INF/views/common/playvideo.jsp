<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<div class="play-video">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12 col-lg-8 student-view">
					<div class="gallery-item">
						<div id="player"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
      var tag = document.createElement('script');

      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      var player;
      function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
          height: '390',
          width: '640',
          videoId: '${youtubeUrl}',
          events: {
            'onReady': onPlayerReady
          }
        });
      }

      function onPlayerReady(event) {
//     	  alert("zdc"+"${scrType}");
      	  if("${scrType}"=='recomm' || "${scrType}"=='subCategory' || "${scrType}"=='sigma' ||  "${scrType}"=='contribution'){
             {
    		  event.target.playVideo();
    	        setTimeout(saveActivity, 5000);
    		  }
      	  }
      
      }
      function saveActivity() {
//     	  alert("45 seconds completed");
    	  var userId= "${userId}";
    	  var userType= "${userType}";
    	  var activity = {
    	  screen: "${screenType}",
          action: "${action}",
          mediaId: "${mediaId}",
          videoType: "${videoType}",
          youtubeUrl: "${youtubeUrl}",
          mediaType: "${mediaType}",
          subcategory: {
        	  id:"${subCatId}"
          }
    	  }
    	  $.ajax({
    			type:"POST",
    			url:"${pageContext.request.contextPath}/studt/saveActivity?userId="+userId+"&userType="+userType,
    			data: JSON.stringify(activity),
    			contentType: "application/json",
    			success: function(data){
    				console.log(data);
    			},
    			error: function(){
    				console.log("error occured");
    			}
    		});
     }
    </script>
</div>
