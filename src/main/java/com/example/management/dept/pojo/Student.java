package com.example.management.dept.pojo;


import javax.validation.constraints.NotNull;

public class Student {

    private Long id;
    @NotNull(message = "名字不能为空")
    private String stuName;

    @NotNull(message = "学号不能为空")
    private String stuId;

    @NotNull(message = "密码不能为空")
    private String stuCollege;

    @NotNull(message = "专业不能为空")
    private String stuMajor;

    @NotNull(message = "性别")
    private Integer stuGender;  //0表示男，1表示女

    @NotNull(message = "密码不能为空")
    private String stuPassword;

    @NotNull(message = "邮箱不能为空")
    private String stuMail;

    private Long deptId;

    @NotNull(message = "手机不能为空")
    private String  stuPhone;

    @NotNull(message = "qq不能为空")
    private String  stuQq;

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

    public Integer getStuGender() {
        return stuGender;
    }

    public void setStuGender(Integer stuGender) {
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", stuId='" + stuId + '\'' +
                ", stuCollege='" + stuCollege + '\'' +
                ", stuMajor='" + stuMajor + '\'' +
                ", stuGender=" + stuGender +
                ", stuMail='" + stuMail + '\'' +
                ", deptId=" + deptId +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuQq='" + stuQq + '\'' +
                '}';
    }
}
