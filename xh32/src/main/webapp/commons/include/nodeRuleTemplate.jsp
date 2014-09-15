<!-- 规则模板 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="hidden">
		<input id="normal-input" class="short-input" name="judgeValue" type="text" />
		<select id="judgeCon-1" name="judgeCondition">
			<option value="=="><spr:message code="condition.eq" /></option>
			<option value="!="><spr:message code="condition.notEq" /></option>
			<option value=">"><spr:message code="condition.gt" /></option>
			<option value="&lt;"><spr:message code="condition.lt" /></option>
			<option value=">="><spr:message code="condition.gtEq" /></option>
			<option value="&lt;="><spr:message code="condition.ltEq"/></option>
		</select>
		<select id="judgeCon-2" name="judgeCondition">
			<option value="equals()"><spr:message code="condition.equals"/></option>
			<option value="!="><spr:message code="condition.notEq"/></option>
			<option value="equalsIgnoreCase()"><spr:message code="condition.equalsIgnoreCase"/></option>
			<option value="contains()"><spr:message code="condition.contains"/></option>
			<option value="notContains()"><spr:message code="condition.notContains"/></option>
		</select>
		<select id="judgeCon-4" name="judgeCondition">
			<option value="equals()"><spr:message code="condition.eq"/></option>
			<option value="!="><spr:message code="condition.notEq"/></option>
			<option value="contains()"><spr:message code="condition.contains"/></option>
			<option value="notContains()"><spr:message code="condition.notContains"/></option>
		</select>
		<select id="judgeCon-5" name="judgeCondition">
			<option value="contains()"><spr:message code="condition.contains"/></option>
			<option value="notContains()"><spr:message code="condition.notContains"/></option>
		</select>
		<input id="date-input" type="text" class="Wdate" />
		<div id="role-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="#" class="link role"><spr:message code="menu.button.choose"/></a>
		</div>
		<div id="position-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="#" class="link position"><spr:message code="menu.button.choose"/></a>
		</div>
		<div id="org-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="#" class="link org"><spr:message code="menu.button.choose"/></a>
		</div>
		<div id="user-div">
			<input type="hidden" value="">
			<input type="text" readonly="readonly" />
			<a href="#" class="link users"><spr:message code="menu.button.choose"/></a>
		</div>
		<textarea id="dic-select">
			<select>					
				<#list data as obj>
					<option value="\${obj.option}">\${obj.memo}</option>
				</#list>	
			</select>
		</textarea>
		<textarea id="dic-radio-checkbox">
			<#list data as obj>
				<label><input type="\${obj.type}" name="\${obj.name}" value="\${obj.option}"/>\${obj.memo}</label>
			</#list>
		</textarea>
		
		<select id="flowVarsSelect" class="left margin-set" name="flowVars" onchange="flowVarChange.apply(this)">
			<option value="">--<spr:message code="select.pleaseChoose"/>--</option>
			<optgroup label="<spr:message code='script.flowVar'/>"></optgroup>
			<c:forEach items="${flowVars}" var="flowVar">
				<option class="flowvar-item"  value="${flowVar.fieldName}" chosenopt="${flowVar.jsonOptions}" ctltype="${flowVar.controlType}" ftype="${flowVar.fieldType}" datefmt='${flowVar.datefmt}'>${flowVar.fieldDesc}</option>
			</c:forEach>
			<c:if test="${not empty defVars}">
			<optgroup label="<spr:message code='script.customVar'/>"></optgroup>
			<c:forEach items="${defVars}" var="defVars">
				<option class="flowvar-item"  value="${defVars.varKey}" chosenopt="" ctltype="0" ftype="${defVars.varDataType}" datefmt='yyyy-MM-dd'>${defVars.varName}</option>
			</c:forEach>
			</c:if>
		</select>
		<!-- 属性模板 -->
		<select id="paramKey" name="paramKey" class="left margin-set" onchange="changeCondition(this)">
			<c:forEach items="${sysParamList}" var="p" >
				<option title="${p.dataType }" value="${p.paramKey }">${p.paramName }</option>
			</c:forEach>
		</select>			
		
		<select id="paramCondition" name="paramCondition" class="left margin-set">
			<option value="=">=</option>
			<option value="!=">!=</option>
			<option value=">">></option>
			<option value="<"><</option>
			<option value=">=">>=</option>
			<option value="<="><=</option>
		</select>
		
		<input type="text" id="paramValue" name="paramValue" class="left margin-set" onchange="validateVal(this)"/>
		
		<div >
			<table id="condition-script-rule">
				<tr class="script-tr">
					<td>
						<input name="pk" type="checkbox" />
					</td>
					<td>
						<spr:message code="script.conditionScript"/>
					</td>
					<td name="conComType">
						<select name="comTypeSelect">
							<option value='0'><spr:message code="condition.or"/></option>
							<option value='1'><spr:message code="condition.and"/></option>
						</select>
					</td>
					<td>
						<a name="script" href="#" onclick="editConditionScript(this)"><spr:message code="script.script"/></a>
					</td>
				</tr>
			</table>
		</div>
</div>	