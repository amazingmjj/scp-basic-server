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
* 货币
*
* @author samy
*/
@Data
@Entity
@Table(name = "t_currency")
@TableName(value = "t_currency")
@KeySequence("t_currency_seq")
public class Currency implements Serializable{
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
    * 汇率
    */
    private Double rate;
    /**
    * 备注
    */
    private String remark;
    /**
    * 折算方式(1 原币 * 汇率 2 原币 / 汇率)
    */
    private Integer convertWay;
    /**
    * 精度
    */
    private Integer precision;
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