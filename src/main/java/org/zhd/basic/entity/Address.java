package org.zhd.basic.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.zhd.basic.service.AddressService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 地址
 *
 * @author mjj
 */
@Data
@Entity
@Table(name = "t_address")
@TableName(value = "t_address")
@KeySequence("t_address_seq")
public class Address implements Serializable {
    /**
     * 序列号
     */
    @Id
    @TableId(type = IdType.INPUT)
    private Long id;
    /**
     * 地址编号
     */
    private String code;
    /**
     * 地址名称
     */
    private String name;
    /**
     * 上级地址编号（最上级-1）
     */
    private String parentCode;
    /**
     * 上级地址名称
     */
    @Transient
    @TableField(exist = false)
    private String parentName;

    public String getParentName() {

        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 备注
     */
    private String remark;
    /**
     * 下级列表
     */
    @Transient
    @TableField(exist = false)
    private List<Address> children;

    public List<Address> getChildren() {
        return children;
    }

    public void setChildren(List<Address> children) {
        this.children = children;
    }
}
