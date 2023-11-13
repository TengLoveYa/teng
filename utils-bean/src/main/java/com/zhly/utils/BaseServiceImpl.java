package com.zhly.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
* @author 李腾
* @datetime 2023/10/5 19:14
* @description 类对象
*/
public class BaseServiceImpl<D extends BaseMapper<P>,P> extends ServiceImpl<D,P> implements BaseService<P> {

@Override
public IPage<P> toPage(HashMap<String, Object> hashMap) {
Map.Entry<Page<P>, QueryWrapper<P>> entry = getPage(hashMap);
return page(entry.getKey(),entry.getValue());
}
@Override
public Map.Entry<Page<P>, QueryWrapper<P>> getPage(HashMap<String, Object> hashMap) {
Integer pageNum = 1;
Integer pageSize = 3;
try{
pageNum =  Integer.parseInt(hashMap.get("pageNum").toString()) ;
pageSize =  Integer.parseInt(hashMap.get("pageSize").toString()) ;
}catch (Exception e){
}
hashMap.remove("pageNum");
hashMap.remove("pageSize");
QueryWrapper<P> queryWrapper = new QueryWrapper<>();
if(hashMap.get("name")!=null){
queryWrapper.like("name",hashMap.get("name"));
hashMap.remove("name");
}
hashMap.forEach((k,v)->queryWrapper.eq(v!=null,k,v));
return new AbstractMap.SimpleEntry(new Page<>(pageNum, pageSize),queryWrapper);
}

@Override
public Map.Entry<Page<P>, QueryWrapper<P>> getPage(HashMap<String, Object> hashMap,String name) {

    Integer pageNum = 1;
    Integer pageSize = 3;
    try {
    pageNum = Integer.parseInt(hashMap.get("pageNum").toString());
    pageSize = Integer.parseInt(hashMap.get("pageSize").toString());
    } catch (Exception e) {
    }
    hashMap.remove("pageNum");
    hashMap.remove("pageSize");
    QueryWrapper<P> queryWrapper = new QueryWrapper<>();
    if (hashMap.get("name") != null) {
    queryWrapper.like(name+"name", hashMap.get("name"));
    hashMap.remove("name");
    }
    hashMap.forEach((k, v) -> queryWrapper.eq(v != null,name+ k, v));
    return new AbstractMap.SimpleEntry(new Page<>(pageNum, pageSize), queryWrapper);
    }
}
