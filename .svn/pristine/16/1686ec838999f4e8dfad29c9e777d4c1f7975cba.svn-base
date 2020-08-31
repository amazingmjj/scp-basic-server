package org.zhd.basic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 机构
 *
 * @author juny
 */
@Data
@Entity
@Table(name = "t_org")
@TableName(value = "t_org")
@KeySequence("t_org_seq")
public class Org implements Serializable {
    /**
     * 自增
     */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 集团编码
     */
    private String memberCode;

    /**
     * 集团名称
     */
    private String memberGroupName;

    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 简称
     */
    private String abbreviate;
    /**
     * 电话
     */
    private String phone;
    /**
     * 传真号
     */
    private String fax;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 邮件
     */
    private String email;
    /**
     * 开户行账号
     */
    private String openAccount;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 税号
     */
    private String tfn;
    /**
     * 地址
     */
    private String address;
    /**
     * 地区代码
     */
    private String area;
    /**
     * 备注
     */
    private String remark;

    /**
     * 对应往来单位代码
     */
    private String companyCode;

    /**
     * 对应往来单位名称
     */
    private String companyName;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;
}
