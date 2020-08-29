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
* 品名大类
*
* @author samy
*/
@Data
@Entity
@Table(name = "t_goods_parent_name")
@TableName(value = "t_goods_parent_name")
@KeySequence("t_goods_parent_name_seq")
public class GoodsParentName implements Serializable{
    /**
    * 序列号
    */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
    * 编号
    */
    private String code;
    /**
    * 名称
    */
    private String name;
    /**
    * 集团编号
    */
    private String memberCode;
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
    @TableField(fill = FieldFill.UPDATE)
    private Date updateAt;
}