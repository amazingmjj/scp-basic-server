package org.zhd.basic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.xy.api.enums.StatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 部门
 *
 * @author juny
 */
@Data
@Entity
@Table(name = "t_dpt")
@TableName(value = "t_dpt")
@KeySequence("t_dpt_seq")
public class Dpt {
    /**
     * 自增
     */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 集团代码
     */
    private String memberCode;
    /**
     * 部门编号
     */
    private String code;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门负责人
     */
    private String manager;
    /**
     * 备注
     */
    private String remark;

    /**
     * 机构编号，多个用逗号分割
     */
    @Column(columnDefinition = "varchar2(255)")
    private String orgCodes;

    /**
     * 机构状态
     */
    private StatusEnum status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;

}
