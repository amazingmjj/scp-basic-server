package org.zhd.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.*;

/**
 * 实体表维护
 *
 * @author samy
 */
@Data
@Entity
@Table(name = "t_basic_seq")
@TableName(value = "t_basic_seq")
@KeySequence("t_basic_seq_seq")
public class BasicSeq implements Serializable {
    /**
     * 序列号
     */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 实体表名
     */
    private String tableName;
    /**
     * 唯一编号
     */
    private String tableCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间(系统自动记录)
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;
    /**
     * 更新时间(系统自动记录)
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;

    @Version
    @Column(columnDefinition = "int default 1")
    private int version;
}