<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="mg-content" id="mg-content"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:util="urn:jsptagdir:/WEB-INF/tags/util"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	 <section class="contribution-bg event-address">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-10 col-lg-10 student-view">
                    <div class="bgsuccess cbe">
                        <div class="row">
                            <div class="col-sm-12 col-lg-12 col-xl-12">
                                <div class="event-content">
                                    <div class="event-num">
                                        <h4>${hubAddress.event_month}</h4>
                                        <h4>${hubAddress.event_day}</h4>
                                    </div>
                                    <div>
                                        <h2>${hubAddress.eventName}</h2>
                                    </div>
                                </div>
                                <div class="event-detail-sec">
                                <div class="details">
                                    <h3>Details</h3>
                                </div>
                                <div class="event-date">
                                    <span><i class="fa fa-clock-o"></i></span>
                                    <span>${hubAddress.event_month} ${hubAddress.event_day}, ${hubAddress.eventTime}</span>
                                </div>
                                <div class="location">
                                <form  action="${pageContext.request.contextPath}/common/gethubMap" method="POST">
                                   <input type="hidden" name="hubId" value="${hubAddress.hubZone.id}" /> 
                                   <button>
                                    <div>
                                        <i class="fa fa-map-marker"></i>
                                    </div></button>
                                    </form>
                                    <div>
                                            <div class="event-head">${hubAddress.eventName}</div>
                                            <span>${hubAddress.hubZone.address1}</span>
                                    </div>

                                </div>
                            </div>
                                <div class="description">
                                    <p>${hubAddress.description}</p>
                                </div>
                           

                                <div class="venue">
                                    <div>
                                        <h3>Venue</h3>
                                    </div>
                                    <div>
                                        <div class="event-head">${hubAddress.eventName}</div>
                                            <span>${hubAddress.hubZone.address1}</span>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </div>
