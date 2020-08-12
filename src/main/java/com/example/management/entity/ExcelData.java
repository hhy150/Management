package com.example.management.entity;

import java.util.List;

public class ExcelData {

    private String fileName;
    //表头
    private String[] head;
    private List<String[]> data;

    public ExcelData(String fileName, String[] head, List<String[]> data) {
        this.fileName = fileName;
        this.head = head;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getHead() {
        return head;
    }

    public List<String[]> getData() {
        return data;
    }
}
