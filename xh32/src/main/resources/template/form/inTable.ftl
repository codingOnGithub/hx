<br>
<#function getFields fieldList>
    <#assign btn>
	 	<#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
				<td><@input field=field/> </td>
			</#if>
		</#list>
	</#assign>	
	<#return btn>
</#function>
	
<#function getFieldsName fieldList>
    <#assign btn>
	    <#list fieldList as field>
			<#if field.valueFrom != 2 && field.isHidden == 0>
				<th ><span i18nkey="${field.fieldName}">${field.fieldDesc}</span> </th>
			</#if>
		</#list>
	</#assign>
	<#return btn>
</#function>
<div class="subTableToolBar l-tab-links">
	<a class="link add" atype="add" href="javascript:;" onclick="return false;">${formLanguage.add}</a>
</div>
<table class="listTable" >
	<#assign teamcount=0 />
	<#list teamFields as team>
		<#list team.teamFields as teamfile>
			<#if teamfile.valueFrom != 2>
				<#assign teamcount=teamcount + 1>	
			</#if>
		</#list>
	</#list>
	<#assign count=0 />
	<#list fields as field>
		<#if field.valueFrom != 2>
			<#assign count=count + 1>	
		</#if>
	</#list>
	
    <tr>
    	<td colspan="${count+teamcount}"  class="formHead" >${table.tableDesc }</td>
    </tr>
    <#if teamFields??>
	    <tr>
	  	  <#list teamFields as team>
				<#assign teamcount1=0>	
				<#list team.teamFields as teamfile>
					<#if teamfile.valueFrom != 2>
						<#assign teamcount1=teamcount1 + 1>	
					</#if>
				</#list>
				<#if teamcount1 !=0>
					<td colspan="${teamcount1}" class="teamHead">${team.teamName }</td>
				</#if>
			</#list>
			<#if fields?size != 0>
	    		<td colspan="${count}" class="teamHead">${formLanguage.unGrouped}</td>
			</#if>
	    </tr>
    </#if>
	<tr class="headRow">
		<#list teamFields as team>
			${getFieldsName(team.teamFields)}
		</#list>
		${getFieldsName(fields)}
	</tr>
	<tr class="listRow" formType="edit">
		<#list teamFields as team>
			${getFields(team.teamFields)}
		</#list>
		${getFields(fields)}
	</tr>
</table>
