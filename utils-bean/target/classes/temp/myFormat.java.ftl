package ${package.Other}utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
* @author 李腾
* @datetime 2023/9/14 14:31
* @description 类对象
*/
@Component
public class MyFormat implements MetaObjectHandler {

/**
* 插入元对象字段填充（用于插入时对公共字段的填充）
*
* @param metaObject 元对象
*/
@Override
public void insertFill(MetaObject metaObject) {
strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
strictInsertFill(metaObject,"startTime",LocalDateTime.class,LocalDateTime.now());
strictInsertFill(metaObject,"endTime",LocalDateTime.class,LocalDateTime.now().plusYears(3L));
}

/**
* 更新元对象字段填充（用于更新时对公共字段的填充）
*
* @param metaObject 元对象
*/
@Override
public void updateFill(MetaObject metaObject) {
strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
}
}