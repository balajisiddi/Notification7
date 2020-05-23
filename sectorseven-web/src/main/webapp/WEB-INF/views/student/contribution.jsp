<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
       <section class="contribution-bg">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12">
                    <div class="bgsuccess cbe">
                        <div class="row">
                        <div class="alert-success">${msg}</div>
                            <div class="col-12 col-sm-6 col-md-6">
                                <div class="content">
                                    <h2>Contribution </h2>
                                    <p>All of us are on a mission to Make India Great Again. All of us are born with
                                        infinite potential and endless curiosity. We have the innate urge to do our bit
                                        for the social good. Two thirds of India is under the age of 35. Every one of
                                        them needs guidance and mentoring. We have students, parents, teachers, mentors
                                        and policy makers on this platform. Time has arrived for every one of us to pay
                                        back to the society. The world of Students need contribution in terms of
                                        presentations, audios, videos from every one of the stakeholders. Every student
                                        can bring out his uniqueness through his/her contribution and let all other
                                        students know the same exapnd their knowledge horizons. All teachers can reach
                                        millions of students across the globe with their unique insights on different
                                        subjects and learnings. Parents and mentors can guide the vast number of
                                        students with their expertise in their specialised subject matter. Policy
                                        holders can contribute about administrative and public policy insights which
                                        will be useful to every other stakeholder. All the contributions will be
                                        reviewed and uploaded under different heads in the library.</p>

                                </div>
                            </div>
                            <div class="col-12 col-sm-6 col-md-6">
                                <form:form action="${pageContext.request.contextPath}/student/contribution" enctype="multipart/form-data" commandName="contributionObj" method="post">
                                   <div class="row">
										<div class="col-sm-12 col-md-6 col-lg-6">
											<select id="country" class="form-control">
												<option>--Select Category--</option>
												<option>USA</option>
												<option>AUSTRALIA</option>
												<option>FRANCE</option>
											</select>
										</div>
										<div class="col-sm-12 col-md-6 col-lg-6">
											<select id="city" class="form-control">
												<!--  -->
											</select>
										</div>
									</div>
                                    <div class="form-group mt-3">
                                        <textarea class="form-control form-rounded" rows="6" placeholder="Message"></textarea>
                                    </div>
                                    <div class="form-group">
                                    <input type="file"> 
                                    </div>
                                    <button type="submit" value="submit" class="btn btn-primary con-subbtn">Submit <i
                                            class="fa fa-sign-in" aria-hidden="true"><!--  --></i></button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
       </div>
       <script>
	$(document).ready(
			function() {
				// Initializing arrays with city names.
				var USA = [ {
					display : "Washington, D.C.",
					value : "WashingtonDC"
				}, {
					display : "Alaska",
					value : "Alaska"
				}, {
					display : "New York",
					value : "New-York"
				}, {
					display : "Florida",
					value : "Florida"
				}, {
					display : "Hawaii",
					value : "Hawaii"
				}, {
					display : "California",
					value : "California"
				} ];
				var AUSTRALIA = [ {
					display : "Canberra",
					value : "Canberra"
				}, {
					display : "Sydney",
					value : "Sydney"
				}, {
					display : "Melbourne",
					value : "Melbourne"
				}, {
					display : "Perth",
					value : "Perth"
				}, {
					display : "Gold Coast ",
					value : "Gold-Coast"
				} ];
				var FRANCE = [ {
					display : "Paris",
					value : "Paris"
				}, {
					display : "Avignon",
					value : "Avignon"
				}, {
					display : "Strasbourg",
					value : "Strasbourg"
				}, {
					display : "Nice",
					value : "Nice"
				} ];
				// Function executes on change of first select option field.
				$("#country").change(function() {
					var select = $("#country option:selected").val();
					$("#city").css("display", "block");
					switch (select) {
					case "USA":
						city(USA);
						break;
					case "AUSTRALIA":
						city(AUSTRALIA);
						break;
					case "FRANCE":
						city(FRANCE);
						break;
					default:
						$("#city").empty();
						$("#city").css("display", "none");
						$("#city").append("<option>--Select City--</option>");
						break;
					}
				});
				// Function To List out Cities in Second Select tags
				function city(arr) {
					$("#city").empty(); //To reset cities
					$("#city").append("<option>--Select--</option>");
					$(arr).each(
							function(i) { //to list cities
								$("#city").append(
										"<option value=" + arr[i].value + ">"
												+ arr[i].display + "</option>")
							});
				}
			});
</script>