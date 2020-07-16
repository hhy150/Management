package com.example.management.entity;

import org.omg.PortableInterceptor.INACTIVE;

public class Club extends Admin{
    private String name;
    private String logo;     //url
    private String intro;
    private Integer IsDeleted;

    public Integer getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", clubName='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
