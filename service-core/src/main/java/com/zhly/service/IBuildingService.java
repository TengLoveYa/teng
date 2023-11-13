package com.zhly.service;

import com.zhly.utils.BaseService;
import com.zhly.po.Building;
import com.zhly.utils.MyResult;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李腾
 * @since 2023-11-13
 */
public interface IBuildingService extends BaseService<Building> {
    /**
     * 树形结构
     * @return
     */
    MyResult<?> toTree();

    /**
     * 级联菜单
     * @return
     */
    MyResult<?> cascader(String id);
}
