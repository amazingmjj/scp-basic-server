package org.zhd.basic.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.*;
import org.xy.api.enums.MarriageEnum;
import org.xy.api.enums.SexEnum;
import org.xy.api.enums.StatusEnum;

/**
 * 员工
 *
 * @author samy
 */
@Data
@Entity
@Table(name = "t_employee")
@TableName(value = "t_employee")
@KeySequence("t_employee_seq")
public class Employee implements Serializable {
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
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 机构编号(多个用,分割)
     */
    private String orgCodes;
    /**
     * 部门编号
     */
    private String dptCode;
    /**
     * 职位
     */
    private String job;
    /**
     * 性别(MALE 男 FEMALE 女 SECRET 保密)
     */
    private SexEnum sex;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 籍贯
     */
    private String birthNative;
    /**
     * 专业
     */
    private String professional;
    /**
     * 政治面貌
     */
    private String politicalLandscape;
    /**
     * 技术职称
     */
    private String techTitle;
    /**
     * 备注
     */
    private String remark;
    /**
     * 学历
     */
    private String edu;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 进入公司时间
     */
    private Date entryDate;
    /**
     * 居住地址
     */
    private String address;
    /**
     * 员工类别
     */
    private String category;
    /**
     * 状态(ENABLED 启用 DISABLED 停用 FROZON 冻结)
     */
    private StatusEnum status;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮件
     */
    private String email;
    /**
     * 工种名称
     */
    private String workTypeName;
    /**
     * 婚姻
     */
    private MarriageEnum marriage;
    /**
     * 集团编号
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
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateAt;
}