<%@page import="java.util.Random"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>DGX1 | Monitoring</title>

<link href="resources/core/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/core/font-awesome/css/font-awesome.css"
	rel="stylesheet">
<link href="resources/core/css/plugins/footable/footable.core.css" rel="stylesheet">
<link href="resources/core/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<link href="resources/core/css/animate.css" rel="stylesheet">
<link href="resources/core/css/style.css" rel="stylesheet">
<link href="resources/core/css/style2.css" rel="stylesheet">
<link href="resources/core/img/NVLogo_2D_WT2.png" rel="icon"></link>

</head>

<body>
<input type="hidden" id="NVIDIA_HOST" value="${dataListWrapper.nvidiaHost}">
<input type="hidden" id="BOSUN_HOST" value="${dataListWrapper.bosunHost}">
<input type="hidden" id="NVIDIA_GRAFANA_HOST" value="${dataListWrapper.grafanaHost}">
	<div id="wrapper">
		<jsp:include page="menu.jsp"></jsp:include>
		<%-- <nav class="navbar-default navbar-static-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav metismenu" id="side-menu">
				<li class="nav-header logo"
					style="border-bottom: 1px solid #5A5A5A;"><img alt="image"
					src="resources/core/img/logo.png" width="200" />

					<div class="logo-element">IN+</div></li>
				<c:forEach items="${dataListWrapper.clusterDataList}"
					var="clusterData" varStatus="status">
					<li class="qwerty"><a href="<c:url value="/nvidia"/>"><i
							class="fa fa-th-large"></i> <span class="nav-label">${clusterData.clusterId}</span><span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<c:forEach items="${dataListWrapper.nodeInfoDataList}"
								var="nodeInfoData" varStatus="status">
								<c:if test="${nodeInfoData.clusterId==clusterData.clusterId}">
									<li><a
										href="<c:url value="/node/${nodeInfoData.serialId}&${nodeInfoData.clusterId}"/>">${nodeInfoData.name}</a>
										<a href="<c:url value="/cluster/${nodeInfoData.clusterId}"/>">${nodeInfoData.name}</a>
									</li>
								</c:if>
							</c:forEach>
							<!-- <li><a href="node.html">Zury</a></li>
                        <li><a href="node.html">Tiger</a></li>
                        <li><a href="node.html">Hummer</a></li>
                        <li><a href="node.html">Agnes</a></li> -->

						</ul></li>
				</c:forEach>
				
				
				<li><a href="<c:url value="/jobs"/>"><i class="fa fa-list"></i> <span
						class="nav-label">Jobs</span></a></li>
				<!-- <li><a href="#"><i class="fa fa-exclamation"></i> <span
						class="nav-label">Alerts</span></a></li>
				<li><a href="#"><i class="fa fa-gear"></i> <span
						class="nav-label">Settings</span></a></li>
				-->

			</ul>

		</div>
		</nav> --%>

		<div id="page-wrapper" class="gray-bg">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top white-bg" role="navigation"
					style="margin-bottom: 0">
				<div class="navbar-header">
					<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
						href="#" style="padding: 7px 12px;"><i class="fa fa-bars"></i> </a>

				</div>
				<ul class="nav navbar-top-links navbar-right">
					<li><span class="m-r-sm text-muted welcome-message">Welcome
							David Williams.</span></li>

					<li ><a class=" count-info"
						href="#alert_tab"> <i class="fa fa-bell"></i> 
						
						<c:if test="${dataListWrapper.needAckList.size()>0}">
								<span class="label label-danger"> <c:out value="${dataListWrapper.needAckList.size()}"></c:out>	</span>
						</c:if>
						
					</a></li>
					<li><a href="/nvidia/logout"> <i class="fa fa-sign-out"></i>
							Log out
					</a></li>

				</ul>
				</nav>
			</div>
			<div class="wrapper wrapper-content">
			
				<div class="row">
					<div class="col-lg-12">
						<h2 style="float: left; margin-top: 0px;">CLUSTER:
							${dataListWrapper.clusterData.clusterId}</h2>
					</div>
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title" style="border-width: 0px 0 0;">
								<div class="ibox-tools">
									<i class="fa fa-gear fa-2x" style="float: left;"> </i> <small
										class="text-muted"><b>Metrics From:</b> <span
										id="selected_timeframe"></span></small>&nbsp;&nbsp;
									<div class="btn-group">
										<button data-toggle="dropdown"
											class="btn btn-primary btn-xs dropdown-toggle"
											aria-expanded="false">
											Action <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" id='myid'>
											<!-- <li id="1"><a href="#">Last 3 Days</a></li> -->
											<li id="2"><a href="#">Last 7 Days</a></li>
											<li class="divider"></li>
											<li id="3"><a href="#">Current</a></li>
										</ul>
									</div>
									
									<div class="btn-group" style="padding-right: 20px;">&nbsp;</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
                <div class="col-md-6">
				
					<!--
					<div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Nodes</h5>
                            
                        </div>

                        <div class="ibox-content inspinia-timeline">

                            <div class="timeline-item">
                                <div class="row">
                                    <div class="col-xs-3 date">
                                        <i class="fa fa-briefcase"></i>
                                        6:00 am
                                        <br>
                                        <small class="text-navy">2 hour ago</small>
                                    </div>
                                    <div class="col-xs-9 content no-top-border">
                                        <p class="m-b-xs"><strong>Meeting</strong></p>

                                        <p>Conference on the sales results for the previous year. Monica please examine sales trends in marketing and products.</p>

                                    </div>
                                </div>
                            </div>
                            
                            
                            
                        </div>
                    </div>
					-->
					
					
                    <div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>
								<i class="fa fa-share-alt"></i> Nodes
							</h5>
							
							
						</div>
						
						<div class="ibox-content">
							<table class="table table-hover no-margins">
								<thead>
									<tr>
										<th>Name</th>
										<th>IP Address</th>
										<th>Status</th>
										<th>Leader</th>
										<th>Options</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${dataListWrapper.nodeInfoDataList}"
										var="nodeInfoData" varStatus="status">
										<c:if
											test="${dataListWrapper.clusterData.clusterId==nodeInfoData.clusterId}">
											<tr>
												<td><a href="<c:url value="/node/${nodeInfoData.serialId}&${nodeInfoData.clusterId}"/>">${nodeInfoData.name}</a></td>
												<td>${nodeInfoData.ipAddress}</td>
												<td><c:if test="${nodeInfoData.status == 'Connected'}">
														<span class="labels label-primary">${nodeInfoData.status}</span>
													</c:if> <c:if test="${nodeInfoData.status == 'Disconnected'}">
														<span class="labels label-warning">${nodeInfoData.status}</span>
													</c:if> <c:if test="${nodeInfoData.status == 'NA'}">
														<small class="labels label-danger">${nodeInfoData.status}</small>
													</c:if></td>
												<td>${nodeInfoData.isLeader}</td>
												<td><a href="<c:url value="/node/${nodeInfoData.serialId}&${nodeInfoData.clusterId}"/>"> <i class="fa fa-eye primary" title="Details"></i></a></td>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					</div>
					<div class="col-md-6">	
                    <div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>
								<i class="fa fa-list"></i> Jobs
							</h5>
							 <div class="ibox-tools">
								<a class="btn btn-xs btn-primary" href="/nvidia/jobs">
									Add <i class="fa fa-plus"></i>
								</a>
								<!--
								<button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal">
									Add <i class="fa fa-plus"></i>
								</button>
								-->
							</div> 
						</div>
						<c:choose>
						<c:when test="${fn:length(dataListWrapper.jobTaskDataList) > 0 }"> 
						<div class="ibox-content table-responsive">
							<table class="table table-hover no-margins">
								<thead>
									<tr>
										
										<!-- <th>Job Id</th> -->
										<th>Job Name</th>
										<th>Time started</th>
										<th>Owner</th>
										<th>Node</th>
										<th>Port</th>
										<th>Status</th>
										<!-- <th>Time started</th>
										<th>Status</th> -->
										<!-- <th>Docker Image</th> -->
										<th>Options</th>
									</tr>
								</thead>
								<tbody>
									<input type="hidden" value="${fn:length(dataListWrapper.jobTaskDataList)}" id="STARTED_AT_COUNT"/>
									<c:forEach items="${dataListWrapper.jobTaskDataList}" var="jobTaskData" varStatus="status">
										<tr>
											<%-- <td>${task.id}</td> --%>
											<td>${jobTaskData.appId}</td>
											<td id="STARTED_AT_NEW_${status.index}"></td>								
											<input type="hidden" value='${jobTaskData.startedAt}' id="STARTED_AT_${status.index}"/>
											<td><span data-toggle="tooltip" data-placement="top" title="ram@gmail.com">${jobTaskData.owner}</span></td>
											
											
											<td>${jobTaskData.host}</td>
											<td><a href='http://${jobTaskData.host}:${jobTaskData.port}' target='_blank'>${jobTaskData.port}</a></td>
											<td>${jobTaskData.status}</td>
											<td width="15%">
												 <a class="btn" style="padding: 0px 0px;" data-toggle="popover" data-placement="top" data-html="true" data-content="
								<li><span>Link to container : ${jobTaskData.image} </span></li>
								<li><span>Job ID: ${jobTaskData.appId} </span></li>
								<li><span>Status : ${jobTaskData.status} </span></li>
								<li><span>Additional arguments : NA </span></li>
								<li><span>NFS server : NA </span></li>
								<c:if test="${jobTaskData.state!=null && jobTaskData.message!=null}">
								<br>
								<b><span><center>LastTaskFailure</center></span></b><hr style='margin:0px;'>
								<li><span>Host : ${jobTaskData.host} </span></li>
								<li><span>Message : ${jobTaskData.message} </span></li>
								<li><span>State : ${jobTaskData.state} </span></li></c:if>"><i class="fa fa-eye primary" title="Details"></i></a>
												 <span style="padding:5px;">&nbsp;</span>
                                                            <c:choose>  
															<c:when test="${jobTaskData.status=='Launched'}">
															<a style="cursor: pointer;" data-toggle="modal" data-target="#containerx" data-disp="" data-jobhost ="${jobTaskData.host}" data-containername="${jobTaskData.containerName}"> <i class="fa fa-desktop primary" title="Console"></i></a> 
															</c:when>
															<c:otherwise>
															<a href="http://${jobTaskData.host}:2375/containers/${jobTaskData.containerName}/attach?stdout=true&stderr=true&stream=true&logs=false" id="container" target="blank" disabled="disabled" class="btn-disabled"><i class="fa fa-desktop fa-1x"></i></a>
															</c:otherwise>
															</c:choose> 
															<span style="padding: 0px 0px;">&nbsp;</span>
															<a class="btn job_details" style="padding: 0px 0px;" href="${jobTaskData.appId}">
																<i class="fa fa-times-circle fa-1x text-danger"></i></a>
															</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						</c:when>
						  <c:otherwise>
							<div class="ibox-content table-responsive">
							<table class="table table-hover no-margins">
							<tr><td align="center" style="border-top: 0px solid #e7eaec; ! important"> Sorry. You have no Job data. You can start a new job by clicking on the +add button above.</td></tr>
							</table>
							</div>
						  </c:otherwise>
						</c:choose>
					</div>
					</div>
					
                </div>
				
                

				
				<!-- Alert Modal box -->


                               <div class="row">
                                       <div class="col-lg-6">
                                               <!--<iframe src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=1&fullscreen&from=1459189800000&to=1459244774102&theme=light" width="100%" height="300px" frameborder="0" scrolling="no" id="frame_change1"></iframe>-->
                                               <iframe src="" width="100%" height="300px" frameborder="0"
                                                       scrolling="no" id="frame_change1"></iframe>

                                       </div>
                                       <div class="col-lg-6">
                                               <!--<iframe src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=2&fullscreen&from=1459189800000&to=1459244774102&theme=light" width="100%" height="300px" frameborder="0" scrolling="no" id="frame_change2"></iframe>-->
                                               <iframe src="" width="100%" height="300px" frameborder="0"
                                                       scrolling="no" id="frame_change2"></iframe>
                                       </div>

                                       <div class="col-lg-12" style="margin-top: 10px;">&nbsp;</div>

                                       <div class="col-lg-6">
                                               <!--<iframe src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=3&fullscreen&from=1459189800000&to=1459244774102&theme=light" width="100%" height="300px" frameborder="0" scrolling="no" id="frame_change3"></iframe>-->
                                               <iframe src="" width="100%" height="300px" frameborder="0"
                                                       scrolling="no" id="frame_change3"></iframe>
                                       </div>
                                       <div class="col-lg-6">
                                               <!--<iframe src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=4&fullscreen&from=1459189800000&to=1459244774102&theme=light" width="100%" height="300px" frameborder="0" scrolling="no" id="frame_change4"></iframe>-->
                                               <iframe src="" width="100%" height="300px" frameborder="0"
                                                       scrolling="no" id="frame_change4"></iframe>
                                       </div>


                               </div>

								<!--  Alerts start -->
									<div class="row">
										<div class="col-md-6">
					                   		 <div class="ibox float-e-margins">
					
												<div class="ibox-title" style="background-color: #FFF;" id="alert_tab">
													<h3>
														<i class="fa fa-bell"></i> Alert Summary
													</h3>
													<small><i class="fa fa-tim"></i> You have
														${dataListWrapper.needAckList.size()} new alerts </small> 
														<%-- of which ${dataListWrapper.criticalCount} is critical.</small> --%>
												</div>
					
												<div class="ibox-content">
													<div class="row" >
														
														<div class="col-md-6">
															<a href="${dataListWrapper.ackCount}" id="ack" class="alert_box">
															<div class="widget navy-bg " style="overflow:auto;">
															  <div class="col-sm-2"><i class="fa fa-thumbs-up fa-2x"></i></div>
															  <div class="col-sm-9">
																<div class="text-center">
																	<h1 class="m-xs">  ${dataListWrapper.ackCount}</h1>
																	<h3 class="font-bold no-margins">Acknowledged</h3>
																	<!-- <small>amount</small> -->
																</div>
															  </div>
																
															</div>
															</a>
					
														</div>
					
														<div class="col-md-6">
														<a href="${dataListWrapper.needAckCount}" id="unack" class="alert_box">
					                                       <div class="widget blue-bg " style="overflow:auto;">
															  <div class="col-sm-2"><i class="fa fa-hand-o-right fa-2x"></i></div>
															  <div class="col-sm-10">
																<div class="text-center">
																	<h1 class="m-xs"> ${dataListWrapper.needAckCount}</h1>
																	<h3 class="font-bold no-margins">Needs Acknowledgement</h3>
																	<!-- <small>amount</small> -->
																</div>
															  </div>
															</div>
															</a>
														</div>
														</div>
														
														<div class="row" >
					                                     <div class="col-md-4">
														 <a href="${dataListWrapper.criticalCount}" id="critical" class="alert_box">
					                                       <div class="widget red-bg " style="overflow:auto;">
															  <div class="col-sm-2"><i class="fa fa-bell fa-2x"></i></div>
															  <div class="col-sm-9">
																<div class="text-center">
																	<h1 class="m-xs"> ${dataListWrapper.criticalCount}</h1>
																	<h3 class="font-bold no-margins">Critical</h3>
																	<!-- <small>amount</small> -->
																</div>
															  </div>
															</div>
															</a>
														</div>
														<div class="col-md-4">
														 <a href="${dataListWrapper.warningCount}" id="warning" class="alert_box">
					                                       <div class="widget yellow-bg " style="overflow:auto;">
															  <div class="col-sm-2"><i class="fa fa-warning fa-2x"></i></div>
															  <div class="col-sm-9 ">
																<div class="text-center">
																	<h1 class="m-xs"> ${dataListWrapper.warningCount}</h1>
																	<h3 class="font-bold no-margins">Warning</h3>
																	<!-- <small>amount</small> -->
																</div>
															  </div>
															</div>
															</a>
														</div>
									                    <div class="col-md-4">
														<a href="${dataListWrapper.unknownCount}" id="unknown" class="alert_box">
					                                       <div class="widget gray-bg " style="overflow:auto;">
															  <div class="col-sm-2"><i class="fa fa-shield fa-2x"></i></div>
															  <div class="col-sm-9">
																<div class="text-center">
																	<h1 class="m-xs"> ${dataListWrapper.unknownCount}</h1>
																	<h3 class="font-bold no-margins">Unknown</h3>
																	<!-- <small>amount</small> -->
																</div>
															  </div>
															</div>
															</a>
														</div>
													</div>								
												</div>
												<!-- ibox-content -->
											</div>
										</div>
										<div class="col-md-6">
									
											<div class="ibox-content ibox-heading" id="alert_tab_zero" style="margin-bottom:60px;">
												<h3><i class="fa fa-exclamation-triangle"></i> Alerts</h3>
												<span id="alert_tab_zero_message" style="margin: 0 0 1px;"></span><br><small>You can click on the above items to see the respective notifications</small>
											</div>
											
											<div class="ibox float-e-margins" id="alert_tab_data">
										   		<div class="ibox-title">
													<h5>
														<i class="fa fa-exclamation-triangle"></i> Alerts
													</h5>
												</div>
												<div class="ibox-content table-responsive">
													<input type="text" class="form-control input-sm m-b-xs"
														id="filter" placeholder="Search in table">
					
															<table class="footable table table-stripped" data-page-size="8" data-filter=#filter>
																<thead>
																	<tr>
																		<th>Name</th>
																		<th>Node</th>
																		<th>Status</th>
																		<th>Time</th>
																		<th>Options</th>
																		<th data-ignore="true" data-hide="all">Type</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${dataListWrapper.childList}" var="child"
																		varStatus="status">
																		<tr class="gradeX">
																			<td>${child.getAlertName()}</td>
																			<td>${child.getSerialId()}</td>
																			<td>${child.getStatus()}</td>
																			<td class="center"><small>${child.getAlertDate()}</small></td>
																			<td class="center"><a style="cursor: pointer;" data-toggle="modal" data-target="#alertx" data-disp="" data-alertstatusid ="${child.getAlertStatus()}" data-notificationid="${child.getAlertKey()}" id="${child.getAlertKey()}"> <i class="fa fa-eye primary" title="Details"></i></a>
																			</td>
																			<td class="center">${child.getAlertStatus()}</td>
																			<!--<td class="center"><a style="cursor: pointer;" id="polo" href="${child.getAlertKey()}"><i class="fa fa-eye"></i></a></td>-->
																		</tr>
																	</c:forEach>
					
																</tbody>
																<tfoot>
																	<tr>
																		<td colspan="5">
																			<ul class="pagination pull-right"></ul>
																		</td>
																	</tr>
																</tfoot>
															</table>
												</div>
											</div>
										</div>
									</div>
								<!--  Alerts end -->
								<%-- <div class="row">
									<div class="col-md-6">
									<div class="ibox float-e-margins">
										<div class="ibox-title">
										<h5>
											<i class="fa fa-list"></i> Nfs Details
										</h5>
										<div class="ibox-tools">
											<a class="btn btn-xs btn-primary" id="updateNFS"> Update </i>
											</a>
											<!--
			                                 <button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#myModal">
			                                Add <i class="fa fa-plus"></i> 
			                                 </button>
			                                 -->
										</div>
										</div>
										<div class="ibox-content table-responsive">
											<span data-bind="text: priceRating" id="nfsError" bgcolor="red"
												hidden></span>
												<span data-bind="text: priceRating1" id="nfsSuccess" bgcolor="red"
												hidden></span>
											<div class="form-group">
												<div class="row">
													<div class="col-md-12">
														<input type="text" placeholder="NFS_Server" id="nfsONE" value="${dataListWrapper.clusterData.nfs_one}"
															name="" class="form-control">
													</div>
															
												</div>
											</div>
										</div>
									</div>
									</div>											
								</div> --%>
                               <!--div class="row">
                                       <div class="col-lg-6">
                                               <iframe
                                                       src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=1&fullscreen&theme=light"
                                                       width="100%" height="300px" frameborder="0" scrolling="no"
                                                       id="frame_change1"></iframe>
                                       </div>
                                       <div class="col-lg-6">
                                               <iframe
                                                       src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=2&fullscreen&theme=light"
                                                       width="100%" height="300px" frameborder="0" scrolling="no"
                                                       id="frame_change2"></iframe>
                                       </div>

                                       <div class="col-lg-12" style="margin-top: 10px;">&nbsp;</div>

                                       <div class="col-lg-6">
                                               <iframe
                                                       src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=3&fullscreen&theme=light"
                                                       width="100%" height="300px" frameborder="0" scrolling="no"
                                                       id="frame_change3"></iframe>
                                       </div>
                                       <div class="col-lg-6">
                                               <iframe
                                                       src="http://54.153.26.252:3000/dashboard-solo/db/customer-1-current?panelId=4&fullscreen&theme=light"
                                                       width="100%" height="300px" frameborder="0" scrolling="no"
                                                       id="frame_change4"></iframe>
                                       </div>
                               </div-->



				<div class="col-lg-12" style="margin-top: 10px;">&nbsp;</div>
				
				
				

				<!-- Alert Modal box end -->


			</div>

			<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content animated fadeIn">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">New Job</h4>
							<small>Enter the following details to start a new job</small>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-lg-12">
									<form id="newJobForm" role="form">
										<div class="form-group">
											<input type="text" placeholder="Job Name" class="form-control" id="job_name">
										</div>
										<div class="form-group">
											<input type="text" placeholder="Docker URL" value="" class="form-control" id="docker_url">
										</div>
										<div>
										    <input type="hidden" placeholder="Job_Network" value="" class="form-control" id="job_network">										
										</div>
										<div class="form-group">
									<input type="checkbox" id="check" value="BRIDGE" />  BRIDGE 
										</div>
										<div>
											<button type="button"
												class="btn btn-sm btn-primary pull-right m-t-n-xs" id="start12">Start</button>
											<!--<button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>Start</strong></button>-->
										</div>
									</form>
									
								</div>
							</div>
						</div>


						<div class="modal-footer">
							<span class="text-danger" style="float:left;padding-left:5px;" id="NEWJOB_JSON"> </span>
							<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
						</div>


					</div>
				</div>
			</div>

			<!-- Alert Modal box -->
			<div id="alertx" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<div class="col-md-10">
						<div class="modal-content">
							<div class="modal-header bg-primary">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title">Alert title</h4>
							</div>
							<div class="modal-body">
								<p></p>
								
							 <span id="notification_alert"><img alt="image" src="resources/core/img/default-loader.gif" width="220" /></span> 
								<div class="col-sm-12" style="padding-left: 0px !important; display:none" id="notifications_actions">
								<input type="hidden" id="notification_subject">	
										<a class="btn btn-primary btn-xs" href="#" id="alert_ack">Acknowledge</a> 
										<a class="btn btn-warning btn-xs ng-disabled=" href="#" id="alert_close">Close</a>
								</div>
							</div>
							<div class="modal-footer">
								<small class="text-success" id="notification_confirmation" style="float:left;padding-bottom:3px;"></small>	
								<button type="button" class="btn btn-white" data-dismiss="modal">cancel</button>
							</div>



						</div>
					</div>
				</div>
			</div>
         <div id="containerx" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg">

					<div class="col-md-12">
						<div class="modal-content modal-lg">
							<div class="modal-header bg-primary">
								<button type="button" class="close intervalCancel" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title">Container Console</h4>
							</div>
							<div class="modal-body alert-modal">
								<p></p>
								
								<span id="container_console"></span>
								<div class="col-sm-12" style="padding-left: 0px !important; display:none" id="container_actions">
								<input type="hidden" id="container_subject">	
										<!--  <a class="btn btn-primary btn-xs" href="#" id="alert_ack">Acknowledge</a> 
										<a class="btn btn-warning btn-xs ng-disabled=" href="#" id="alert_close">Close</a>  -->
								</div>
							</div>
						<!-- 	<div class="modal-footer">
								<small class="text-success" id="container_confirmation" style="float:left;padding-bottom:3px;"></small>	
								<button type="button" class="btn btn-white" data-dismiss="modal">cancel</button>
							</div> -->
						</div>
					</div>
				</div>
			</div>


			<div class="footer">
				<!--  <div class="pull-right">
                10GB of <strong>250GB</strong> Free.
            </div> -->
				<div>
					Copyright &copy; 2016 NVIDIA Corporation
				</div>
			</div>
		</div>

	</div>

	<!-- Mainly scripts -->
	<script src="resources/core/js/jquery-2.1.1.js"></script>
	<script src="resources/core/js/bootstrap.min.js"></script>
	<script src="resources/core/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="resources/core/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>


	<!-- Custom and plugin javascript -->
	<script src="resources/core/js/inspinia.js"></script>
	<script src="resources/core/js/plugins/pace/pace.min.js"></script>

	<!-- jQuery UI -->
	<script src="resources/core/js/plugins/jquery-ui/jquery-ui.min.js"></script>

	<script src="resources/core/js/jquery.timeago.js"></script>
	<script src="resources/core/js/grafana.js"></script>
	<script src="resources/core/js/plugins/footable/footable.all.min.js"></script>
	
	<!-- Sweet alert -->
    <script src="resources/core/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="resources/core/js/plugins/moment/moment-with-locales.js"></script>
	
	<style>
	*, *:before, *:after {box-sizing:  border-box !important;}


.rowa {
 -moz-column-width: 18em;
 -webkit-column-width: 18em;
 -moz-column-gap: 1em;
 -webkit-column-gap:1em; 
  
}

.itema {
 display: inline-block;
 padding:  .25rem;
 width:  100%; 
}

.wella {
 position:relative;
 display: block;
}
	</style>
	
	<script>
		$(document).ready(function() {
		
			
			//$('[data-toggle="tooltip"]').tooltip({'placement': 'bottom'});
			//$("[data-toggle=tooltip]").tooltip();
			

			$('.footable').footable();
			$('.footable').trigger('footable_filter', {filter: "NeedAck"});
			var fil_count = $('.footable tbody tr:not(.footable-filtered)').length;
			if(fil_count<=0)
			{
				$('#alert_tab_zero_message').html('Sorry, You do not have any un acknowledged notifications.');
				$('#alert_tab_zero').show();
				$('#alert_tab_data').hide();
			}else
			{
				$('#alert_tab_zero').hide();
				$('#alert_tab_data').show();
			}
				
				
				var count = $('#STARTED_AT_COUNT').val();
				if(count)
				{
					var xco;
					for(xco = 0; xco < count; xco++){
						var date = $('#STARTED_AT_'+xco).val();
						var m = moment(date);
						if (m.isValid()) 
						{
							//alert(" THIS IS VALUID "+m);
							var elem=document.getElementById("STARTED_AT_NEW_"+xco);
							if(elem)
							{
								elem.innerHTML = m;
							}
							console.log("--------------------------------------------------------------");
							console.log(date);
							console.log(elem);
							console.log("--------------------------------------------------------------");
							
						}else
						{
							//alert(" THIS IS NOT VALUID "+m);
							console.log("--------------------------------------------------------------");
							console.log(" THERE IS NO TASK DATA IN THE JSON FOR ");
							console.log("--------------------------------------------------------------");
						}
					}
				}
			
			
		});
	</script>
	<script>
		var cur_dat;
		var pre_dat;
		var NVIDIA_HOST = $('#NVIDIA_HOST').val();
		jQuery(document).ready(function() {
					
					
					console.log(NVIDIA_HOST);
					
					$('#containerx').on('show.bs.modal', function(e) {
						
						$('#container_confirmation').html('');
						
						$('#container_actions').css("display", "none");
						//data-disp=" fir 1 st one" data-notificationID="1"
						var jobhost = e.relatedTarget.dataset.jobhost;
						var containername = e.relatedTarget.dataset.containername;
						//var jobhost = $(this).data('jobhost');
						//var containername = $(this).data('containername');
						console.log(e);
						console.log("-----------------");
						console.log($(this));
						//
						console.log("JobHost:"+jobhost+",ContainerName:"+containername);
						//alert(yourParameter);
						//var xyz = decodeURIComponent("http://localhost/nvidia/api/ack/key/"+yourParameter);

						//alert(xyz);
						var eurl = NVIDIA_HOST+"/container";
						//var eurl='http://52.24.185.151:2375/containers/attach7/attach?stdin=true&stdout=true&stderr=true&stream=true&logs=false';
						//var data1={jobhost:jobhost,containerName:containerName};
						var obj = {};

						obj.job_host = jobhost;
						obj.container_name = containername;
						
						$("#container_console").html('');
						
			        	var timeInt=setInterval(function(){
						$.ajax({
							type : "POST",
							contentType : 'application/json',
							//dataType : 'json',
							data: JSON.stringify(obj),
							//data : ({jobhost: jobhost, containerName:containername}),
							url : eurl,
							success : function(msg) {
								//alert(msg);
								$('img[src$="resources/core/img/default-loader.gif"]').remove();
								//console.log(msg);
								//console.log('{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}');
								// console.log(msg);
								//$('#container_console').html(msg.message);
								$("#container_console").append(msg.message+"<br/>");
								$('#container_subject').val(jobhost);
								$('#container_actions').css("display", "");
								
							},
							error : function() {
								console.log("Failed to load Information. Please report to admin");
							}
						});
						}, 5000);
				
						$(".intervalCancel").on("click", function(){
		                	console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		                	clearInterval(timeInt);
		                });

					});
					$('#alert_ack').click(function (e) 
					{
						$('#notification_confirmation').html('');
						e.preventDefault();
						console.log("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
						console.log(e);
						console.log($(this).attr('id'));
						console.log("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
						var nf_sub = $('#notification_subject').val();
						var al_str =NVIDIA_HOST +"/rest/alert/notify"
						var ur = '{"key_id":"'+nf_sub+'","action_id":"ack"}';
						
						$.ajax({
							url: al_str,
							contentType: 'application/json',
							data : ur,
							type: "post",
							success: function(msg)
							{
								console.log(msg);
								$('#notification_confirmation').html(msg.message);
							},
							error: function(msg)
							{
								console.log(msg);
								$('#notification_confirmation').html(msg.message);

							}
						});
						
					});
					
					$('#alert_close').click(function (e) 
					{
						$('#notification_confirmation').html('');
						e.preventDefault();
						console.log("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
						console.log(e);
						console.log($(this).attr('id'));
						console.log("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
						var nf_sub1 = $('#notification_subject').val();
						var al_str =NVIDIA_HOST +"/rest/alert/notify"
						var ur = '{"key_id":"'+nf_sub1+'","action_id":"forceClose"}';
						
						$.ajax({
							url: al_str,
							contentType: 'application/json',
							data : ur,
							type: "post",
							success: function(msg)
							{
								console.log(msg);
								$('#notification_confirmation').html(msg.message);
							},
							error: function(msg)
							{
								console.log(msg);
								$('#notification_confirmation').html(msg.message);

							}
						});
						
					});
					
					
					$('.alert_box').click(function (e) 
					{
						
						e.preventDefault();
						
						var box_id_count = $(this).attr('href');
						var box_id = $(this).attr('id');
						//alert(box_id);
						var footableFilter = $('.footable').data('footable-filter');
						//alert('about to filter table by "tech"');
						/*
						if (confirm('clear filter now?')) 
						{
							footableFilter.clearFilter();
						}
						*/
						
						
						
						var alert_mess = "Sorry, You do not have any un acknowledged notifications";
						switch (box_id)
						{
						   case 'ack':
						 	footableFilter.filter('Acknowledge'); 
							alert_mess = "Sorry, You do not have any acknowledged notifications";
						   break;
						
						   case 'critical': 
						   footableFilter.filter('critical');
						   alert_mess = "Sorry, You do not have any critical notifications";
						   break;
						
						   case 'unknown': 
						   footableFilter.filter('unknown');
						   alert_mess = "Sorry, You do not have any unknown notifications";
						   break;
						
						   case 'unack': 
						   footableFilter.filter('NeedAck');
						   alert_mess = "Sorry, You do not have any notifications that need acknowledgement";
						   break;
						
						   case 'warning': 
						   footableFilter.filter('warning');
						   alert_mess = "Sorry, You do not have any warnings";
						   break;
							
						   default:  
						   footableFilter.filter('');
						}
						var xfil_count = $('.footable tbody tr:not(.footable-filtered)').length;
						if(xfil_count<=0)
						{
							$('#alert_tab_zero_message').html(alert_mess);
							$('#alert_tab_zero').show();
							$('#alert_tab_data').hide();
						}else
						{
							$('#alert_tab_zero').hide();
							$('#alert_tab_data').show();
						}
						
					});
					
					$('.job_details').click(function (e) 
					{
						e.preventDefault();
						var xhref = $(this).attr('href');
						console.log(e);
						console.log("--------------------");
						console.log(xhref);
						
						swal({
							title: "Are you sure to kill "+xhref,
							text: "You will not be able to recover this",
							type: "warning",
							showCancelButton: true,
							confirmButtonColor: "#DD6B55",
							confirmButtonText: "Yes",
							closeOnConfirm: false
						}, function () 
						{
							var ta =NVIDIA_HOST +"/rest/job/delete";
								$.ajax({
								url: ta,
								contentType : 'application/json',
								data : xhref,
								type: "DELETE",
								success: function(msg)
								{
									console.log(msg);
									swal("Killed!", "Your job has been killed.", "success");	
									$("#myModal").modal('hide');
									window.location.reload();
									
								},
								error: function(msg){
									console.log(msg);
									swal("Sorry!", "Your request cannot be completed now. Please try later.", "success");
								}
								
								});
								
							
						});
					});
					
					
					//$('a').click(function(){
					$(".count-info").click(function(event) {
						event.preventDefault();
						$('html, body').animate({	
							scrollTop: $( $.attr(this, 'href') ).offset().top
						}, 800);
						return false;
					});


					$("#frame_change1").attr("src", cluster_cur.one);
					$("#frame_change2").attr("src", cluster_cur.two);
					$("#frame_change3").attr("src", cluster_cur.three);
					$("#frame_change4").attr("src", cluster_cur.four);

					jQuery("time.timeago").timeago();

					var monthNames = [ "January", "February", "March", "April",
							"May", "June", "July", "August", "September",
							"October", "November", "December" ];

					var days = 7;
					var date = new Date();
					var last = new Date(date.getTime() - (days * 24 * 60 * 60 * 1000));
					var pre_day = last.getDate();
					var pre_month = last.getMonth();
					var pre_year = last.getFullYear();

					var day = date.getDate();
					var monthIndex = date.getMonth();
					var year = date.getFullYear();
					console.log('--------------------');
					console.log(day, monthNames[monthIndex], year);
					console.log(pre_day, monthNames[pre_month], pre_year);
					console.log('-----------E---------');

					cur_dat = monthNames[monthIndex] + ' ' + day + ' ' + year;
					pre_dat = monthNames[pre_month] + ' ' + pre_day + ' '+ pre_year;

					var selected_tf = document.getElementById("selected_timeframe");
					selected_tf.textContent = cur_dat;

					/* $('#alert').modal({
						show: false
					});  
					 */
				});
	</script>

	<script>
		$("#myid li").click(function() {
			var change_id = $(this).attr("id"); // get id of clicked li	
			var selected_tf = document.getElementById("selected_timeframe");
			if (change_id == "3") {
				selected_tf.textContent = cur_dat;
				//alert(change_id);
				$("#frame_change1").attr("src", cluster_cur.one);
				$("#frame_change2").attr("src", cluster_cur.two);
				$("#frame_change3").attr("src", cluster_cur.three);
				$("#frame_change4").attr("src", cluster_cur.four);
			} else if (change_id == "2") {
				selected_tf.textContent = pre_dat + ' - ' + cur_dat;
				$("#frame_change2").attr("src", cluster_pre.one);
				$("#frame_change1").attr("src", cluster_pre.two);
				$("#frame_change3").attr("src", cluster_pre.three);
				$("#frame_change4").attr("src", cluster_pre.four);
			} else {
				selected_tf.textContent = " Last 1 Month";
			}

		});

		$('#click').click(function() {
			$('#loadingmessage').show();
			$.post('http://54.153.26.252/tempa/500.jsp', function(data) {
				$('#after_ajax').html(data);
				$('#loadingmessage').hide();
			});

		});

		 function displayVals(){
			 var check = $('#check:checked').val();
			 if(check){
			     $("#job_network").val(check);
			  }
			  else{
			  $("#job_network").val('');
			  }
			 }
			 var qqqq = window.setInterval( function(){
			 displayVals()},10 );
			 
		$('#start12').click(function(er) 
		{
			console.log(er);
			
			var job_name = $("#job_name").val();
			var docker_url = $("#docker_url").val();
			var job_network = $("#job_network").val();//BRIDGE
			//alert(job_network);
			//alert(job_name+" -- "+docker_url);
			
			var ta = '{"id":"'+job_name+'","image":"'+docker_url+'","network":"'+job_network+'"}';
			//alert(ta);
			var eurl2 =NVIDIA_HOST +"/rest/job/api";
			
			$.ajax({
				url: eurl2,
				contentType: 'application/json',
				data : ta,
				type: "post",
				success: function(msg)
				{
					console.log(msg);
					$('#NEWJOB_JSON').html(msg.message);
					
				},
				error: function(msg)
				{
					console.log(msg);
					$('#NEWJOB_JSON').html(msg.message);
				}
			});


		});
		
		$('#myModal').on('hidden.bs.modal', function () 
		{
			//alert(' MODAL HIDDEN ');
			$('#NEWJOB_JSON').html('');
			$(this).find('form').trigger('reset');
			window.location.reload();
		})

		$('#alertx').on('show.bs.modal', function(e) {
		
			 var alertParameter = e.relatedTarget.dataset.alertstatusid;
		     if(alertParameter=='Acknowledge'){
		    	 //Need implement disabled acknowledged
		        // $("#alert_ack").attr("hidden","hidden");
		         // $("#alert_ack").css("background-color","silver");
		         $("#alert_ack").hide();
		      }
		     
			$('#notification_confirmation').html('');
			$('#notifications_actions').css("display", "none");
			//data-disp=" fir 1 st one" data-notificationID="1"
			var yourParameter = e.relatedTarget.dataset.notificationid;
			var myBookId = $(this).data('notificationid');
			console.log(e);
			console.log("-----------------");
			console.log($(this));
			//
			//$('#notification_alert').html(yourParameter);
			//$(e.target).find('.modal-body').load('http://54.153.26.252:8070/api/status?ack=customer1.cluster1.node1.high.gpu.temp{}')
			//alert('dont');
			console.log(yourParameter);
			//alert(yourParameter);
			//var xyz = decodeURIComponent("http://localhost/nvidia/api/ack/key/"+yourParameter);

			//alert(xyz);
			var objectz = yourParameter;
			objectz = JSON.stringify(objectz);
			objectz = objectz.replace(/\"/g, "");
			var eurl =NVIDIA_HOST +"/api/ack/key";
			$.ajax({
				type : "POST",
				contentType : 'application/json',
				dataType : 'json',
				//data: JSON.stringify(objectz),
				data : objectz,
				url : eurl,
				success : function(msg) {

					console.log('{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}');
					console.log(msg);
					$('#notification_alert').html(msg.message);
					$('#notification_subject').val(yourParameter);
					$('#notifications_actions').css("display", "");
					
				},
				error : function() {
					console.log("Failed to load Information. Please report to admin");
				}
			});

		});
//	});
	</script>


</body>
</html>
