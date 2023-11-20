package com.zhly.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author 李腾
 * @since 2023-11-13
 */
@Data
@TableName("building")
public class Building extends Model<Building> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建人
     */
    @TableField("name")
    private String name;

    /**
     * 类型0座1栋2楼3层
     */
    @TableField("type")
    private Integer type;

    /**
     * 父类id
     */
    @TableField("parent_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 状态
     */
    @TableField("status")
    @TableLogic
    private Boolean status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField("create_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("update_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateId;

    @TableField(exist = false)
    private List<Building> children;
    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
