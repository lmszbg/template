package org.sen.modules.dictionary.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DictionaryModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "code不能为空")
    @NotEmpty(message = "code不能为空")
    private String code;

    /**
     * 值
     */
    @NotNull(message = "val不能为空")
    @NotEmpty(message = "val不能为空")
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
    @NotNull(message = "groupId不能为空")
    @NotEmpty(message = "groupId不能为空")
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
}
