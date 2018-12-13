<#if header??>
${header}
</#if>
package ${config.providerJavaPackage}.${entity.packageName};

/**
 * Possible values for the {@code ${field.nameLowerCase}} column of the {@code ${entity.nameLowerCase}} table.
 */
public enum ${field.enumName} {
    <#list field.enumValues as enumValue>
    /**
     * ${enumValue.documentation!""}
     */
    ${enumValue.name}(${enumValue.constructor})<#if enumValue_has_next>,<#else>;</#if>

    </#list>
    
    <#list field.enumConstructors as enumConstructor> 
    public ${enumConstructor.typeName} ${enumConstructor.name};
    </#list>
    
    public ${field.enumName}(<#list field.enumConstructors as enumConstructor>${enumConstructor.typeName} ${enumConstructor.name}<#if enumConstructor_has_next>,<#else></#if></#list>) {
   	<#list field.enumConstructors as enumConstructor> 
    	this.${enumConstructor.name} = ${enumConstructor.name};
    </#list>
    }
}