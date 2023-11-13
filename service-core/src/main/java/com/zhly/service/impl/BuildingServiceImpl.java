package com.zhly.service.impl;

import com.zhly.CascaderVO;
import com.zhly.utils.BaseServiceImpl;
import com.zhly.po.Building;
import com.zhly.dao.BuildingDAO;
import com.zhly.service.IBuildingService;
import com.zhly.utils.MyResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李腾
 * @since 2023-11-13
 */
@Service
public class BuildingServiceImpl extends BaseServiceImpl<BuildingDAO, Building> implements IBuildingService {
  @Autowired
  private BuildingDAO buildingDAO;

  /**
   * 树形结构
   *
   * @return
   */
  @Override
  public MyResult<?> toTree() {
    List<Building> list = list();
    Map<Long, List<Building>> map = list.stream().collect(Collectors.groupingBy(Building::getParentId));
    list.stream().forEach(obj->obj.setChildren(map.get(obj.getId())));
    return MyResult.ok(map.get(0L));
  }

  /**
   * 级联菜单
   *
   * @return
   */
  @Override
  public MyResult<?> cascader(String id) {
    List<Building> buildings = list();
    List<CascaderVO> list = buildings.stream().map(obj -> new CascaderVO(obj.getId().toString(), obj.getName(), null,obj.getParentId().toString())).collect(Collectors.toList());
    Map<String, List<CascaderVO>> map = list.stream().collect(Collectors.groupingBy(CascaderVO::getPid));
    list.stream().forEach(obj->obj.setChildren(map.get(obj.getValue())));
    if(StringUtils.isNotBlank(id)&&!"null".equals(id)&&!"undefined".equals(id)){
      try {
        CascaderVO children= list.stream().filter(obj->id.equals(obj.getValue())).findFirst().get();
        list.stream().filter(obj->children.getPid().equals(obj.getValue())).findFirst().get().getChildren().remove(children);
      }catch (Exception e){

      }
    }

    return MyResult.ok(map.get("0"));
  }

}