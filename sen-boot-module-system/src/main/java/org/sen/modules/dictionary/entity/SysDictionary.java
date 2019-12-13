package org.sen.modules.dictionary.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author sen
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典
     */
    private String id;

    /**
     * 编码
     */
    private String code;

    /**
     * 值
     */
    private String val;

    /**
     * 描述
     */
    private String describe;

    /**
     * 名字
     */
    private String name;

    /**
     * 父id
     */
    private String patientId;

    /**
     * 组id
     */
    private String groupId;

    /**
     * 排序
     */
    private Integer sortId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接
     */
    private String url;

    /**
     * 组件
     */
    private String compose;

    private String p1;

    private String p2;

    private String p3;

    private String p4;

    private String p5;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;


}
