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
* 费用项目
*
* @author samy
*/
@Data
@Entity
@Table(name = "t_fee_item")
@TableName(value = "t_fee_item")
@KeySequence("t_fee_item_seq")
public class FeeItem implements Serializable{
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
    * 类别编号
    */
    private String parentCode;
    /**
    * 类别名称
    */
    private String parentName;
    /**
    * 结算方式
    */
    private String settleType;
    /**
    * 运输方式
    */
    private String transType;
    /**
    * 备注
    */
    private String remark;
    /**
    * 计价方式
    */
    private String priceType;
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
    * 无税单价
    */
    private Double unitPrice;
    /**
    * 税率
    */
    private Double rate;
    /**
    * 含税单价
    */
    private Double taxUnitPrice;
    /**
    * 重量尾数
    */
    private Integer weightMantissa;
    /**
    * 金额尾数
    */
    private Integer amountMantissa;
    /**
    * 金往来
    */
    private String amountContacts;
    /**
    * 进成本毛利
    */
    private Double inputCost;
    /**
    * 有无发票(1 有 2无)
    */
    private Integer hasInvoice;
    /**
    * 机构编号
    */
    private String orgCode;
    /**
    * 机构名称
    */
    private String orgName;
}