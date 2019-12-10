package org.sen.modules.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sen
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单标题
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 组件
     */
    private String component;

    /**
     * 组件名字
     */
    private String componentName;

    /**
     * 一级菜单跳转地址
     */
    private String redirect;

    /**
     * 菜单类型（0-一级菜单，1-子菜单，2-按钮权限）
     */
    private Integer menuType;

    /**
     * 菜单权限编码
     */
    private String perms;

    /**
     * 权限策略（1-显示， 2-禁用）
     */
    private String permsType;

    /**
     * 菜单排序
     */
    private Double sortNo;

    /**
     * 集合子路由（1-是，0-否）
     */
    private Integer alwaysShow;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 是否路由菜单（0-不是，1-是）默认1
     */
    private Integer isRoute;

    /**
     * 是否叶子节点（1-是，0-不是）
     */
    private Integer isLeaf;

    /**
     * 是否缓存改页面（1-是，0-不是）
     */
    private Integer keepAlive;

    /**
     * 是否隐藏路由（0-否，1-是）
     */
    private Integer hidden;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 跟新人
     */
    private String updateBy;

    /**
     * 跟新时间
     */
    private LocalDateTime updateTime;


}
