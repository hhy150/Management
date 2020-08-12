package com.example.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class MemAffair implements Serializable {
    private Long id;
    private Long memId;
    private Long affairId;
    @JsonIgnore
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public Long getAffairId() {
        return affairId;
    }

    public void setAffairId(Long affairId) {
        this.affairId = affairId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "MemAffair{" +
                "id=" + id +
                ", memId=" + memId +
                ", affairId=" + affairId +
                '}';
    }

}
