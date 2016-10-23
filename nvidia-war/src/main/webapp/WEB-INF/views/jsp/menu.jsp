<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav metismenu" id="side-menu">
				<li class="nav-header logo"
					style="border-bottom: 1px solid #5A5A5A;"><img alt="image"
					src="<%=request.getContextPath() %>/resources/core/img/dashboard_logo.png" class="img-responsive dashboard_logo"> <!-- width="200" -->

					<div class="logo-element">IN+</div></li>
				<c:forEach items="${dataListWrapper.clusterDataList}" var="clusterData" varStatus="status">
					<li class="qwerty"><a href='<%=request.getContextPath() %>'><i
							class="fa fa-th-large"></i> <span class="nav-label">${clusterData.clusterId}</span><span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<c:forEach items="${dataListWrapper.nodeInfoDataList}"
								var="nodeInfoData" varStatus="status">
								<c:if test="${nodeInfoData.clusterId==clusterData.clusterId}">
                                <input type="hidden" id="clusterID" value="${clusterData.clusterId}">
									<li><a
										href='<%=request.getContextPath() %>/node/${nodeInfoData.serialId}&${nodeInfoData.clusterId}'>${nodeInfoData.name}</a>
									</li>
								</c:if>
							</c:forEach>
						</ul></li>
				</c:forEach>
				
				
				<li><a href='<%=request.getContextPath() %>/jobs'><i class="fa fa-list"></i> <span
						class="nav-label">Jobs</span></a></li>
				<li><a href='<%=request.getContextPath() %>/nfsDetails'><i class="fa fa-cog"></i> <span
						class="nav-label">Settings</span></a></li>		
				<!-- <li><a href="#"><i class="fa fa-exclamation"></i> <span
						class="nav-label">Alerts</span></a></li>
				<li><a href="#"><i class="fa fa-gear"></i> <span
						class="nav-label">Settings</span></a></li>
				-->

			</ul>

		</div>
		</nav>