<%--
	time:2012-02-20 14:57:32
	desc:edit the 系统日历
--%>
<%@page import="sun.awt.color.CMM.CSAccessor"%>
<%@page import="com.hotent.core.util.StringUtil"%>
<%@page language="java" pageEncoding="UTF-8" import="java.util.*,com.hotent.platform.model.worktime.CalendarSetting;"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="sysCalendar.edit.title" /></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysCalendar"></script>
	<f:link href="calendar.css" ></f:link>
	
	<script type="text/javascript">
		var calId=${sysCalendar.id};
		var year=${wtYear};
		var month=${wtMon};
		
		var hasModify=false;
		
	
		var restTemplate='<div name="divSetting" wtType="2"><span><spr:message code="workTimeSetting.rest" /></span></div>';
		var workdayTemplate='<div name="divSetting" worktimeid="#worktimeid"  wtType="1"><div><spr:message code="workTimeSetting.work" /></div><div><spr:message code="workTimeSetting.shift" />：#wtName</div></div>';
		
		var previous = 'previous';
		var legal = 'legal';
		var weekend = 'weekend';
		var workday = 'workday';
		var active = 'active';
		var rest = 'rest';
		
		
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function(){
				var rtn=$("#sysCalendarForm").valid();
				if(rtn){
					saveCalendar(showResponse);
				}
			});
			$("#cal th").click(selAllCol);
			$("#cal td[column]").click(tdClickHandler);
		});
		
		//保存日历设置。
		function saveCalendar(response){
			var name=$("input[name='name']").val();
			var memo=$("input[name='memo']").val();
			var data={id:calId,name:name,memo:memo,year:year,month:month,days:[]};
			$("div[name='divDay']:has(div)").each(function(){
				var obj=$(this);
				var divSetting=$("div[name='divSetting']",obj);
				var day=obj.attr("day");
				var type=divSetting.attr("wtType");
				var dayObj={day:day,type:type};
				//上班
				if(type==1){
					var worktimeid=divSetting.attr("worktimeid");
					dayObj.worktimeid=worktimeid;
				}
				data.days.push(dayObj);
			});
			
			var json=JSON2.stringify (data);
			var params="calJson=" + json;
			
			$.post("save.ht",params,response);
		}
		
		//保存返回
		function showResponse(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){//成功
				$.ligerDialog.confirm(obj.getMessage()+$lang.operateTip.continueOperate,$lang.tip.msg,function(rtn){
					if(!rtn){
						var returnUrl=$("a.back").attr("href");
						location.href=returnUrl;
					}
					//改变数据修改状态，否则进行跳转时会再次提示是否保存
					hasModify = false ;
				});
		    }else{//失败
		    	$.ligerDialog.err($lang.tip.msg,$lang_system.sysCalendar.save.failure,obj.getMessage());
		    }
		};
		//单击表格处理
		function tdClickHandler(){
			var tdObj=$(this);
			activeGrid(tdObj);
		}
		
		
		//表格激活
		function activeGrid(tdObj){
			var clsName=tdObj.attr("class");
			if(tdObj.data("class")==undefined){
				tdObj.data("class",clsName);
				tdObj.removeClass(clsName).addClass(active);
			}
			else{
				var className=tdObj.data("class");
				if(clsName==active){
					tdObj.removeClass(active).addClass(className);
				}
				else{
					tdObj.removeClass(className).addClass(active);
				}
			}
		}
		
		// 选中整列的项
		function selAllCol(){
			var objHeader=$(this);
			var col=objHeader.attr("column");
			$("td[column='"+col+"']").each(function(){
				var tdObj=$(this);
				activeGrid(tdObj);
			});
		}
		
		// 修改选中项班次
		function changeCboWt(obj){
			var value=$(obj).val();
			if(value==1){
				$('#spanWorkTime').show();
			}else{
				$('#spanWorkTime').hide();
			}
		}
		
		// 清除选中项
		function cleanSelItem(){
			$("#cal td[column]").each(function(){
				var tdObj=$(this)
				var itemClass =tdObj.attr('class');
				if(itemClass==active){
					var clsName=tdObj.data("class");
					tdObj.removeClass(active).addClass(clsName);
				}
			});
		}
		
		// 选中项班次修改
		function changeSelItemWt(){
			var activeAmount=$('#cal td[class="active"]').length;
			if(activeAmount==0){
				$.ligerDialog.warn($lang_system.sysCalendar.selectOpt,$lang.tip.warn);
				return;
			}
			//班次类型
			var wtValue = $('#selWt').attr('value');
			//班次
			var wtSetValue = $('#selWtSet').attr('value');
			var wtSetText=$("#selWtSet").find("option:selected").text();
			//对日历进行了修改。
			hasModify=true;
			$('#cal div[name="divDay"]').each(function(){
				var objDiv=$(this);
				var objTd=objDiv.parent();
				var itemClass = objTd.attr('class');
				if(itemClass==active){
					var tmp="";
					//上班
					if(wtValue==1){
						tmp=workdayTemplate.replace("#worktimeid", wtSetValue).replace("#wtName", wtSetText);
						objTd.data("class",workday).attr("class",workday);
					}
					//休息
					else{
						tmp=restTemplate;
						objTd.data("class",rest).attr("class",rest);
					}
					objDiv.html(tmp);
				}
			});
		}
		
		// 提示是否保存该月数据
		function noticeSave(flag,year,month){
			var validResult = $("#sysCalendarForm").valid();
			if(!validResult) return false ;
	   		var callback = function(rtn) {
	   			if(rtn){
	   				saveCalendar(function(data){
	   					var obj=new com.hotent.form.ResultMessage(data);
	   					if(obj.isSuccess()){
	   						jumpTo(flag,year,month,obj.data.cause);
	   					}
	   					else{
	   						$.ligerDialog.err($lang.tip.error,$lang_system.sysCalendar.save.fail,obj.getMessage());
	   					}
		   			});
	   			}
	   			else{
	   				jumpTo(flag,year,month);
	   			}
 			};
 			if(hasModify){
 				$.ligerDialog.confirm($lang_system.sysCalendar.confirmSave,$lang.tip.msg,callback)	;
 			}
 			else{
 				jumpTo(flag,year,month);
 			}
		}
		//跳转
		function jumpTo(flag,year,month,sysCalendarId){
			var id = sysCalendarId ;
			if(!id) id = "${sysCalendar.id}" ;
			var url = 'edit.ht?id='+id+'&wtYear='+year+'&wtMon='+month;
			if(flag=='pre'){
				location.href = url + '&flag=pre';
			}else if(flag=='next'){
				location.href = url + '&flag=next';
			}else{
				location.href = url;
			}
		}
		//跳转		
		function jump(){
			var year=$("#wtYear").val();
			var month=$("#wtMon").val();
			noticeSave("",year,month);
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
		        <c:when test="${sysCalendar.id !=0}">
		            <span class="tbar-label"><spr:message code="sysCalendar.edit.span.edit" /></span>
		        </c:when>
		        <c:otherwise>
		            <span class="tbar-label"><spr:message code="sysCalendar.edit.span.add" /></span>
		        </c:otherwise>
			</c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save" /></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link prev" id="previousMonth"  href="javascript:noticeSave('pre',${wtYear},${wtMon})"><span></span><spr:message code="sysCalendar.previousMonth" /></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link next" id="nextMonth" href="javascript:noticeSave('next',${wtYear},${wtMon})"><span></span><spr:message code="sysCalendar.nextMonth" /></a></div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<form id="searchForm" method="post" action="edit.ht">
						<ul>
							<li style="float: left;"><span class="label" style="min-width:0px;"><spr:message code="calendarSetting.years" />:</span></li>
							<li style="float: left;margin-top:2px;">
								<select id="wtYear" name="wtYear" >
									<c:forEach var="year" items="${yearlist}">
										<option value="${year}" <c:if test="${wtYear==year}">selected="selected"</c:if> >${year}<spr:message code="calendarSetting.year" /></option>
									</c:forEach>
								</select>
							</li>
							<li style="float: left;"><span class="label" style="min-width:0px;"><spr:message code="calendarSetting.months" />:</span></li>
							<li style="float: left;margin-top:2px;">
								<select id="wtMon" name="wtMon" >
									<c:forEach var="i" begin="1" end="12" step="1">
								      <option value="${i}" <c:if test="${wtMon==i}">selected="selected"</c:if> >${i}<spr:message code="calendarSetting.month" /></option>
								    </c:forEach>
								</select>
							</li>
							<li style="float: left;">
								&nbsp;&nbsp;<input type="hidden" name="id" value="${sysCalendar.id}" />
							</li>
							<li style="float: left;">
								<a href="#" class="link search" onclick="jump()"><span></span><spr:message code="sysCalendar.switch" /></a>
							</li>
						</ul>
					</form>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a></div>
			</div>
		</div>
	</div>
	
	<div class="panel-body">
		<div class="panel-data" >
		<form id="sysCalendarForm" method="post" action="save.ht">
			<input type="hidden" name="id" value="${sysCalendar.id}" />
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th style="text-align: left;">
					<ul class="row">
						<li><spr:message code="sysCalendar.name" />: <input type="text" size="10" id="name" name="name" value="${sysCalendar.name}" class="inputText"/></li>
						<li><spr:message code="sysCalendar.memo" />:<input type="text" id="memo" name="memo" value="${sysCalendar.memo}" class="inputText"/></li>
						</ul>
					</th>
					<th style="text-align: left;">
					<ul class="row">
						<li style="margin-left:5px;min-width:100px;"><spr:message code="workTimeSetting.shift" />：
						<select id="selWt" name="selWt" onchange="changeCboWt(this)">
							<option value="1" selected="selected"><spr:message code="workTimeSetting.work" /></option>
							<option value="2"><spr:message code="workTimeSetting.rest" /></option>
						</select></li>
						<li style="min-width:100px;"><span id="spanWorkTime">
							<spr:message code="sysCalendar.time" />：
							<select id="selWtSet" name="selWtSet" >
								<c:forEach var="wtSettingItem" items="${wtSetting}">
									<option value="${wtSettingItem.id}">${wtSettingItem.name}</option>
								</c:forEach>
							</select>
						</span></li>
						
						<li><span style="color: red;font: 80%;margin-right:5px;line-height:24px;"><spr:message code="sysCalendar.tip" /></span></li>
						<li style="min-width:90px;"><a href='#' class='button'  onclick="changeSelItemWt()" ><span><spr:message code="sysCalendar.changeSel" /></span></a></li>
						<li><a href='#' class='button'  onclick="cleanSelItem()" ><span ><spr:message code="sysCalendar.cleanSel" /></span></a></li>
						</ul>
					</th>
				</tr>
			</table>
			
			<table id="cal" class="wtMonth" >
				<caption>
					<span class="wtDate">${wtYear}<spr:message code="sysCalendar.year" />${wtMon}<spr:message code="sysCalendar.month" /></span>
					<span class="monthFlag">
						<c:if test="${monthFlag==true}">
							<spr:message code="sysCalendar.warn" />
						</c:if>
					</span>
					<table  class="wtMonth" style="width: 250px;">
						<tr >
							
							<td class="workday common" ><span><spr:message code="sysCalendar.workday" /></td>
							<td class="rest common"><span><spr:message code="sysCalendar.rest" /></td>
							<td class="active common" ><spr:message code="sysCalendar.active" /></td>
							<td class="weekend common" ><spr:message code="sysCalendar.weekend" /></td>
							<td class="legal common" ><spr:message code="sysCalendar.legal" /></td>
							<td class="noSetting common" ><spr:message code="sysCalendar.noSetting" /></td>
						</tr>
					</table>
				</caption>
				<tr>
					<th column="1" ><span><spr:message code="sysCalendar.Sun" /></span></th>
					<th column="2" ><span><spr:message code="sysCalendar.Mon" /></span></th>
					<th column="3" ><span><spr:message code="sysCalendar.Tue" /></span></th>
					<th column="4" ><span><spr:message code="sysCalendar.Wed" /></span></th>
					<th column="5" ><span><spr:message code="sysCalendar.Thu" /></span></th>
					<th column="6" ><span><spr:message code="sysCalendar.Fri" /></span></th>
					<th column="7" ><span><spr:message code="sysCalendar.Sat" /></span></th>
				</tr>
				<%
				//取得本月的天数。
				int mondDays=(Integer)request.getAttribute("mondDays");
				//取得当月第一天为星期几。
				int firstDay=(Integer)request.getAttribute("firstDay");
				int actGird=mondDays + firstDay-1;
				int rows=(actGird % 7==0) ? (actGird / 7) :(actGird / 7 +1);
				int totalGrid=rows *7;
				
				Map settingMap = (Map)request.getAttribute("settingMap");
				Map vacationMap = (Map)request.getAttribute("vacationMap");
				%>
				<tr>
				<%
					for(int i=1;i<=totalGrid;i++){
						if(i<firstDay){
							out.println("<td class='previous'></td>");
						}
						else if(i<=actGird){
							int	index=(i%7==0)?7:i%7;
							int curDay=i-firstDay+1;
							boolean hasSetting=false;
						
							CalendarSetting calSetting=null;
							int wtType=0;
							String wtName="";
							String vacationName="";
							String clsName="noSetting";
							
							if(index==1 || index==7){
								clsName="weekend";
							}
							if(vacationMap.containsKey(curDay)){
								clsName="legal";
								vacationName=(String)vacationMap.get(curDay);
							}
							if(settingMap.containsKey(curDay)){
								calSetting = (CalendarSetting)settingMap.get(curDay);
								
								wtType=calSetting.getType();
								wtName=calSetting.getWtName();
								clsName=(wtType==2)?"rest":"workday";
							}
						%>
							<td   column="<%=index%>"  class="<%=clsName %>" >
								<div>
									<%=curDay %>
								</div>
								<div name="divDay" day="<%=curDay%>" >
									<% 
										if(wtType==2){ 
									%>
										<div name="divSetting" wtType="2">
											<span><spr:message code="workTimeSetting.rest" /></span>
										</div>
									<% 
										}
										else if(wtType==1){ 
										%>
										<div name="divSetting" worktimeid="<%=calSetting.getWorkTimeId()%>"  wtType="1">
											<div><spr:message code="workTimeSetting.work" /></div>
											<div><spr:message code="workTimeSetting.shift" />：<%if(wtName!=null){%><%=wtName %><%} %></div>
										</div>
									<%  } %>
									
								</div>
									<%
										if(!"".equals(vacationName)){
									%>
										<div><%=vacationName %></div>
									<%
										}
									%>
							</td>
						<%
						}
						else{
							out.println("<td class='previous'></td>");
						}
						if(i%7==0){
							out.println("</tr>");
						}
						if(i%7==0 && i<settingMap.size())
							out.println("<tr>");
					}
				%>
				</tr>
				
			</table>
			
		</form>
		</div>
		
	</div>
</div>
</body>
</html>


