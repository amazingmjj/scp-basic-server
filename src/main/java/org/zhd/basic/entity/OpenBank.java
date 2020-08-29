package org.zhd.basic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* 开户银行
*
* @author samy
*/
@Data
@Entity
@Table(name = "t_open_bank")
@TableName(value = "t_open_bank")
@KeySequence("t_open_bank_seq")
public class OpenBank implements Serializable{
    /**
    * 序列号
    */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
    * 代码
    */
    private String code;
    /**
    * 开户银行
    */
    private String name;
    /**
    * 开户地址
    */
    private String address;
    /**
    * 银行电话
    */
    private String phone;
    /**
    * 联系人
    */
    private String linkman;
    /**
    * 所属集团
    */
    private String memberCode;
    /**
    * 创建时间(系统自动记录)
    */
    @TableField(fill = FieldFill.INSERT)
    private Date createAt;
    /**
    * 更新时间(系统自动记录)
    */
    @TableField(fill = FieldFill.INSERT)
    private Date updateAt;
}