<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="scheduler"/><spr:message code="title.manage"/></title>
    <script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
 </head>
 <body>
		<div class="panel">
		<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="scheduler"/><spr:message code="title.manage"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link add" href="addJob1.ht"><span></span><spr:message code="menu.button.add"/></a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <table  cellpadding="1" cellspacing="1"   class="table-grid">
						    <thead>
							    <tr>
								    <th><spr:message code="scheduler.job.no"/></th>
								    <th><spr:message code="scheduler.job.name"/></th>
								    <th><spr:message code="scheduler.job.class"/></th>
								    <th><spr:message code="scheduler.job.desc"/></th>
								    <th><spr:message code="list.manage"/></th>
							    </tr>
						    </thead>
						    <tbody>
							    <c:forEach items="${jobList}" var="d" varStatus="status">
						           <tr class="${status.index%2==0?'odd':'even'}">
							           <td >${status.index +1}</td>
							           <td >${d.key.name }</td>
							           <td >${d.jobClass.name }</td>
							           <td >${d.description }</td>
							           <td style="width:50px;text-align:center" class="rowOps">
							                <a href="delJob.ht?jobName=${d.key.name }" class="link del" ><spr:message code="menu.button.del"/></a>
											<a href="addTrigger1.ht?jobName=${d.key.name }" class="link flowDesign"><spr:message code="scheduler.jobList.addPlan"/></a>
											<a href="getTriggersByJob.ht?jobName=${d.key.name }" class="link detail"><spr:message code="scheduler.jobList.planList"/></a>
											<a  href="executeJob.ht?jobName=${d.key.name}" class="link run"><spr:message code="menu.button.run"/></a> 
											<a href="getLogList.ht?jobName=${d.key.name}" class="link flowDesign"><spr:message code="menu.button.log"/></a>
							           </td>
						           </tr>
						        </c:forEach>
						    </tbody>
					    </table>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 