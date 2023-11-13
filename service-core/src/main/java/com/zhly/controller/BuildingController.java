package com.zhly.controller;

import com.zhly.utils.MyResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhly.service.IBuildingService;
import com.zhly.po.Building;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李腾
 * @since 2023-11-13
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
  @Autowired
  private IBuildingService buildingService;

    /**
    * 获取柱状结构
    * @return
    */
    @GetMapping("/toTree")
    public MyResult<?> toTree(){
    return buildingService.toTree();
    }

      /**
      * 级联
      * @param id
      * @return
      */
      @GetMapping("/cascader/{id}")
      public MyResult<?> cascader(@PathVariable(name = "id") String id){
      return buildingService.cascader(id);
      }
    /**
    * 根据id添加或者修改
    * @param building
    * @return
    */
    @PostMapping("/toSave")
    public MyResult<?> toSave(@RequestBody Building building){
    return MyResult.or(building.insertOrUpdate());
    }

    /**
    * 删除根据多条件
    * @param building
    * @return
    */
    @DeleteMapping("/del")
    public MyResult<?> del(Building building){
    return MyResult.or(building.pkVal()!=null?building.deleteById():buildingService.remove(new UpdateWrapper<>(building)));
    }
    /**
    * id删除
    * @param id
    * @return
    */
    @DeleteMapping("/delById/{id}")
    public MyResult<?> delById(@PathVariable("id")Long id){
    return MyResult.or(buildingService.removeById(id));
    }

}
