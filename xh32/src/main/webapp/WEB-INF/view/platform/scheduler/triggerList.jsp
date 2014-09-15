<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*,
		org.quartz.JobDetail,
		org.quartz.Trigger,
		org.quartz.CronTrigger"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
	<head>
		<%@include file="/commons/include/get.jsp" %>
		<title><spr:message code="scheduler.jobList.planList"/></title>
	</head>
	<body>
		<div class="panel">
		<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="scheduler.jobList.planList"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link add" href="addTrigger1.ht?jobName=${jobName}"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link back"  href="getJobList.ht"><span></span><spr:message code="menu.button.back"/></a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					    <table  cellpadding="1" cellspacing="1"   class="table-grid">
						    <thead>
							    <tr>
								    <th><spr:message code="scheduler.job.no"/></th>
								    <th><spr:message code="sysJobLog.jobName"/></th>
								    <th><spr:message code="sysJobLog.trigName"/></th>
								    <th><spr:message code="sysJobLog.trigDesc"/></th>
								    <th><spr:message code="sysJobLog.state"/></th>
								    <th><spr:message code="list.manage"/></th>
							    </tr>
						    </thead>
						    <tbody>
						    <%
								List list=(List)request.getAttribute("list");
								HashMap mapState=(HashMap)request.getAttribute("mapState");
								for(int i=0;i<list.size();i++)
								{
									Trigger trig=(Trigger)list.get(i);
									String trigName=trig.getKey().getName();
									String jobName=trig.getJobKey().getName();
									Trigger.TriggerState state=(Trigger.TriggerState)mapState.get(trigName);
									
									String toggleUrl="toggleTriggerRun.ht?name=" + trigName ;
									String delTrigger="delTrigger.ht?name="+trigName ;
									String logList="getLogList.ht?trigName="+trigName+"&jobName="+jobName;
				 			%>
					           <tr class="${status.index%2==0?'odd':'even'}">
					           	<td>
						  	     <%=i+1 %>
					            </td>
					           <td><%=jobName %></td>
					           <td><%=trigName  %></td>
					           <td><%=trig.getDescription() %></td>
					           <td>
					           <%if(state==Trigger.TriggerState.NORMAL){%>
									<font color="green"><b><spr:message code="sysJobLog.state.normal"/></b></font>
								<%}
								else if(state==Trigger.TriggerState.PAUSED){%>
									<font color="red"><b><spr:message code="sysJobLog.state.paused"/></b></font>
								<%}
								else if(state==Trigger.TriggerState.ERROR){%>
									<spr:message code="sysJobLog.state.error"/>
								<%}
								else if(state==Trigger.TriggerState.COMPLETE){%>
									<spr:message code="sysJobLog.state.complete"/>
								<%}
								else if(state==Trigger.TriggerState.BLOCKED){%>
									<spr:message code="sysJobLog.state.blocked"/>
								<%}
								else if(state==Trigger.TriggerState.NONE){%>
									<spr:message code="sysJobLog.state.none"/>
								<%} %>
					           </td>
					           <td>
					               <%if(state==Trigger.TriggerState.NORMAL){%>
									<a   href="<%=toggleUrl%>" class="link edit"><spr:message code="menu.button.disabled"/></a>
									<%}
									else if(state==Trigger.TriggerState.PAUSED){%>
									<a  href="<%=toggleUrl%>" class="link edit"><spr:message code="menu.button.enable"/></a>
									<%} %>
									<a class="link del"   href="<%=delTrigger  %>" ><spr:message code="menu.button.del"/></a>
									
									<a  href="<%=logList %>" class="link flowDesign" ><spr:message code="menu.button.log"/></a>
					           </td>
					           </tr>
					       <%
				             }
				           %>
						    </tbody>
					    </table>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
	</body>
</html>