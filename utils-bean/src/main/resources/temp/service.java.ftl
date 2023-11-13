package ${package.Service};

import ${package.Other}utils.BaseService;
import ${package.Entity}.${entity};


/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends BaseService<${entity}> {

}
</#if>
