package com.zhly.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.Map;

/**
* @author 李腾
* @datetime 2023/10/5 19:14
* @description 类对象
*/
public interface BaseService<P> extends IService<P> {
    IPage<P> toPage(HashMap<String,Object> hashMap);

    Map.Entry<Page<P>, QueryWrapper<P>> getPage(HashMap<String, Object> hashMap);

    Map.Entry<Page<P>, QueryWrapper<P>> getPage(HashMap<String, Object> hashMap,String name);
}
