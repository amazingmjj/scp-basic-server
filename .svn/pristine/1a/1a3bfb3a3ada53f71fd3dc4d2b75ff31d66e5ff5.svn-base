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
* 费用类别
*
* @author samy
*/
@Data
@Entity
@Table(name = "t_fee_category")
@TableName(value = "t_fee_category")
@KeySequence("t_fee_category_seq")
public class FeeCategory implements Serializable{
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
    * 编号
    */
    private String code;
    /**
    * 名称
    */
    private String name;
    /**
    * 类型
    */
    private String type;
    /**
    * 备注
    */
    private String remark;
    /**
    * 税率
    */
    private Double rate;
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
    /**
    * 所属机构
    */
    private String orgCode;
    /**
    * 所属机构名称
    */
    private String orgName;
}