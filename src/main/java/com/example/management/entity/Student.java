package com.example.management.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.example.management.entity.enums.GenderType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@TableName("mem")
public class Student implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String stuName;
    @Pattern(regexp ="^[B|Q][1-9][0-9]*$")
    @TableField("stuid")
    private String stuId;
    @TableField("college")
    private String stuCollege;
    @TableField("major")
    private String stuMajor;

 /*   @TableField("gender")
    private Integer stuGender;   //0表示男，1表示女*/
    @TableField("gender")
    private GenderType stuGender;
    @JsonIgnore
    @TableField("password")
    private String stuPassword;
    @Pattern(regexp = "^[b|q][1-9][0-9]{7}(@njupt.edu.cn)$")
    @TableField("email")
    private String stuMail;

    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$")
    @TableField("phone")
    private String  stuPhone;
    @Pattern(regexp = "^[1-9][0-9]{4,}$")
    @TableField("qq")
    private String  stuQq;
    private Long deptId;
    @JsonIgnore
    @TableLogic
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public GenderType getStuGender() {
        return stuGender;
    }

    public void setStuGender(GenderType stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuMail() {
        return stuMail;
    }

    public void setStuMail(String stuMail) {
        this.stuMail = stuMail;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuQq() {
        return stuQq;
    }

    public void setStuQq(String stuQq) {
        this.stuQq = stuQq;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /** 不用输出部门信息*/
    @Override
    public String toString() {
        String sex =null;
        if (stuGender!=null) {
             sex= stuGender.getGender();
        }
            return "Student{" +
                "stuName='" + stuName + '\'' +
                ", stuId='" + stuId + '\'' +
                ", stuCollege='" + stuCollege + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuGender=" + sex +
                ", stuMail='" + stuMail + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuQq='" + stuQq + '\'' +
                '}';
    }
}
