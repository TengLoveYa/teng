package ${package.Controller};

import ${package.Other}utils.MyResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.HashMap;
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
  @Autowired
  private ${table.serviceName} ${table.entityPath}Service;

    /**
    * 分页多条件
    * @param hashMap
    * @return
    */
    @GetMapping("/toPage")
    public MyResult${"<?>"} toPage(@RequestParam HashMap<${"String"},Object> hashMap){
    return MyResult.ok(${table.entityPath}Service.toPage(hashMap));
    }

      /**
      * 分页多条件
      * @param hashMap
      * @return
      */
      @GetMapping("/findByMap")
      public MyResult${"<?>"} findByMap(@RequestParam HashMap<${"String"},Object> hashMap){
      return MyResult.ok(${table.entityPath}Service.listByMap(hashMap));
      }
    /**
    * 根据id添加或者修改
    * @param ${table.entityPath}
    * @return
    */
    @PostMapping("/toSave")
    public MyResult${"<?>"} toSave(@RequestBody ${entity} ${table.entityPath}){
    return MyResult.or(${table.entityPath}.insertOrUpdate());
    }

    /**
    * 删除根据多条件
    * @param ${table.entityPath}
    * @return
    */
    @DeleteMapping("/del")
    public MyResult${"<?>"} del(${entity} ${table.entityPath}){
    return MyResult.or(${table.entityPath}.pkVal()!=null?${table.entityPath}.deleteById():${table.entityPath}Service.remove(new UpdateWrapper<>(${table.entityPath})));
    }
    /**
    * id删除
    * @param id
    * @return
    */
    @DeleteMapping("/delById/{id}")
    public MyResult<?> delById(@PathVariable("id")Long id){
    return MyResult.or(${table.entityPath}Service.removeById(id));
    }

}
</#if>
