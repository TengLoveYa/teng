package com.zhly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 李腾
 * @datetime 2023/11/13 19:36
 * @description 类对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CascaderVO {
    private String value;
    private String label;
    private List<CascaderVO> children;
    private String pid;

}
