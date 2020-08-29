package org.zhd.basic.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.baomidou.mybatisplus.annotation.*;

/**
* 地区
*
* @author samy
*/
@Data
@Entity
@Table(name = "t_area")
@TableName(value = "t_area")
@KeySequence("t_area_seq")
public class Area implements Serializable{
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
    * 地区编号
    */
    private String code;
    /**
    * 地区名称
    */
    private String name;
    /**
    * 备注
    */
    private String remark;
    /**
    * 父类编号
    */
    private String parentCode;
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