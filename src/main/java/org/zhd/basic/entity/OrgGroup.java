package org.zhd.basic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 集团表
 *
 * @author juny
 */
@Data
@Entity
@Table(name = "t_org_group")
@TableName("t_org_group")
@KeySequence("t_org_group_seq")
public class OrgGroup implements Serializable {
    /**
     * 自增
     */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 集团编号
     */
    @Column(unique = true)
    private String memberCode;

    /**
     * 集团名称
     */
    private String memberName;

    @TableField(fill = FieldFill.INSERT)
    private Date createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;


}
