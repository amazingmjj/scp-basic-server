package org.zhd.basic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 *
 * @author juny
 */
@Data
@Entity
@Table(name = "t_data_dict")
@TableName(value = "t_data_dict")
@KeySequence("t_data_dict_seq")
public class DataDict implements Serializable {
    /**
     * 序列号
     */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 集团编号
     */
    private String memberCode;
    /**
     * 类别编号
     */
    private String classNameCode;
    /**
     * 类别名称
     */
    private String className;
    /**
     * 字典名称
     */
    private String name;
    /**
     * 字典值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;
    /**
     * 顺序
     */
    private Integer ddOrder;
    /**
     * 类型
     * 0 客户
     * 1 系统(不允许客户删除)
     */
    private Integer type;
    /**
     * 是否显示
     * 针对系统业务类别
     * 0 显示
     * 1 不显示
     */
    private Integer canShow;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;
    /**
     * 更新时间
     */
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updateAt;
}
